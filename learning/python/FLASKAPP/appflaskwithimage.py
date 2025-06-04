from flask import Flask, render_template

app = Flask(__name__)
@app.route('/<name>')
def hello(name):
    return render_template('btn.html', name=name)
