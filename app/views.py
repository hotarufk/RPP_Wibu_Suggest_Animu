"""

  RPP Project
  Version 1.0

  Views for RPP Project

"""
from app import app
from flask import render_template, request, Response, redirect, flash
from flask_wtf import Form, RecaptchaField
from wtforms import StringField, HiddenField, ValidationError, RadioField, BooleanField, SubmitField, IntegerField, FormField, validators
from wtforms.validators import Required

# These should be configured through Flask-Appconfig
app.config['SECRET_KEY'] = 'SECRETKEY' # dummy
app.config['RECAPTCHA_PUBLIC_KEY'] = 'PUBLICKEY'

class Tweet:

  def __init__(self, tweetText, tweetId=None, tweetCreated=None):
    if tweetId != None:
      self.tweetId = tweetId
    self.tweetText = tweetText
    if tweetCreated != None:
      self.tweetCreated = tweetCreated

class TweetForm(Form):
  field1 = StringField('Your Tweet', [validators.required()])
  submit_button = SubmitField('Tweet')

  def validate_hidden_field(form, field):
    raise ValidationError('Always wrong')

@app.route('/', methods=['GET', 'POST'])
@app.route('/index', methods=['GET', 'POST'])
def index():
    form = TweetForm()
    if request.method == 'GET':
        return render_template('index.html', form=form)
    else:
        if form.validate_on_submit():
            return redirect('/')
