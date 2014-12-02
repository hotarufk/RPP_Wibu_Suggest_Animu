"""

  RPP Project
  Version 1.0

  Views for RPP Project

"""
import ast
import operator
from app import app
from search import preprocess, preprocess_zero
from flask import render_template, request, Response, redirect, flash
from flask_wtf import Form, RecaptchaField
from wtforms import StringField, HiddenField, ValidationError, DecimalField, SelectField, SubmitField, IntegerField, FormField, validators
from wtforms.validators import Required

# These should be configured through Flask-Appconfig
app.config['SECRET_KEY'] = 'SECRETKEY' # dummy
app.config['RECAPTCHA_PUBLIC_KEY'] = 'PUBLICKEY'

# Database
data = []
f = open('full.txt', 'r')
for x in range(0, 1000):
	dataAnimu = f.readline() 
	dataAnimu = ast.literal_eval(dataAnimu)
	data.append(dataAnimu)

class SearchForm(Form):
  anime_title = StringField(u'Animation Title', [validators.optional()])
  submit_button = SubmitField('Search')

  def validate_hidden_field(form, field):
    raise ValidationError('Always wrong')

@app.route('/', methods=['GET', 'POST'])
@app.route('/index', methods=['GET', 'POST'])
def index():
    form = SearchForm()
    # dynamically retrieve Anime title from object databases in here if possible
    if request.method == 'GET':
        return render_template('index.html', form=form, search=False)
    else:
        form.anime_title.data = request.form.get('anime_title')
        if form.validate_on_submit():
            # search here
            search_result = []
            search_result = preprocess_zero(data=data, animeTitle=request.form.get('anime_title'))

            return render_template('index.html', form=form, result=search_result, search=True)
        else:
            flash('Error!', 'error')
            return render_template('index.html', form=form, search=False)
