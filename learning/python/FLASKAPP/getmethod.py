from flask import Flask, request
app = Flask(__name__)
@app.route('/')
def index():
    name = request.args.get('name')
    age = request.args.get('age')
    return "<h1>Data received by GET method<br>name:{} age:{}</h1>".format(name, age)