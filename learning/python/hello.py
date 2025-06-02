from flask import Flask
# set FLASK_APP=hello.py
# set FLASK_ENV=development
# flask run
# python -m flask run

app = Flask(__name__)
@app.route('/')
def hello():
    return 'Hello Flask!'