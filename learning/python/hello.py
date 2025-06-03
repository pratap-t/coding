from flask import Flask
# set FLASK_APP=hello.py
# set FLASK_ENV=development
# flask run
# python -m flask run
# To make the localhost publicly available, specify 0.0.0.0 as --host parameter: 
# flask run --host=0.0.0.0
# export FLASK_DEBUG=1
app = Flask(__name__)
@app.route('/')
def hello():
    return '<h2>Hello Flask!</h2>'
