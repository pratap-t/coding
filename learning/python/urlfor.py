from flask import Flask, url_for, abort
app = Flask(__name__)

@app.route('/')
def index():
    url1 = url_for('hello')
    url2 = url_for('welcome')
    return "<a href={}>click here for Hello</a>".format(url1)+\
        " <a href={}>click here for Welcome</a>".format(url2)

def hello():
    abort(401)
    # return 'Hello World!'

def welcome():
    return 'Welcome to Flask'

app.add_url_rule('/hello', 'hello', hello)
app.add_url_rule('/welcome', 'welcome', welcome)