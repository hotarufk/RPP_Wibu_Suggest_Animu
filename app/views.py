"""

  RPP Project
  Version 1.0

  Views for RPP Project

"""
import operator
from app import app
from flask import render_template, request, Response, redirect, flash
from flask_wtf import Form, RecaptchaField
from wtforms import StringField, HiddenField, ValidationError, DecimalField, SelectField, SubmitField, IntegerField, FormField, validators
from wtforms.validators import Required

# These should be configured through Flask-Appconfig
app.config['SECRET_KEY'] = 'SECRETKEY' # dummy
app.config['RECAPTCHA_PUBLIC_KEY'] = 'PUBLICKEY'

class Animu:
#data [1] itu genre
#data [2] itu score
#data [3] itu Director
#data [4] itu Storyboard
#data [5] itu character design
#data [6] itu sound director
#data [7] itu producer
#data [8] itu animation producer
#data [9] itu VA
# Fix the initialization
  def __init__(self,Genre, Score, Director,VA,Link,Storyboard=None,Character_Design=None,Sound_Director=None,Producer=None,Animation_Producer=None):
    self.Title = Title
    self.Score = Score
    self.Genre = Genre
    self.VA = VA
    self.Director = Director
    self.Link = Link

class SearchForm(Form):
  genre = SelectField(u'Genre', [validators.optional()])
  min_score = DecimalField(u'Minimum Score (0.00 - 10.00)', [validators.optional()])
  max_score = DecimalField(u'Maximum Score (0.00 - 10.00)', [validators.optional()])
  additional_keyword = StringField(u'Additional Keyword', [validators.optional()])
  submit_button = SubmitField('Search')

  def validate_hidden_field(form, field):
    raise ValidationError('Always wrong')

@app.route('/', methods=['GET', 'POST'])
@app.route('/index', methods=['GET', 'POST'])
def index():
    form = SearchForm()
    # dynamically retrieve Genre from object databases in here if possible
    form.genre.choices = [('action', 'action'), ('adventure', 'adventure'), ('comedy', 'comedy'), ('drama', 'drama'), ('romance', 'romance'), ('science fiction', 'science fiction'), ('slice of life', 'slice of life')]
    if request.method == 'GET':
        return render_template('index.html', form=form, search=False)
    else:
        form.genre.data = request.form.get('genre')
        form.min_score.data = request.form.get('min_score')
        form.max_score.data = request.form.get('max_score')
        form.additional_keyword.data = request.form.get('additional_keyword')
        if form.validate_on_submit():
            # search here
            search_result = []
            return render_template('index.html', form=form, result=search_result, search=True)
        else:
            flash('Error!', 'error')
            return render_template('index.html', form=form, search=False)
