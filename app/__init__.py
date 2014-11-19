from flask import Flask
from flask_bootstrap import Bootstrap
from animu import Animu

app = Flask(__name__)
Bootstrap(app)
from app import views
