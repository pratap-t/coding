from flask import Flask
print(__name__)
app = Flask(__name__)
# @app.route('/hello')
@app.route('/')
def hello():
    return 'Hello World!'
app.add_url_rule('/hello','hello',hello)

def welcome():
    return 'Welcome to flask'

app.add_url_rule('/welcome','welcome', welcome)