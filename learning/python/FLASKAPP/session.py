from flask import Flask, render_template, request, redirect, url_for, session
import secrets
import os
from dotenv import load_dotenv

app = Flask(__name__)

# Load environment variables from .env file
load_dotenv(dotenv_path="./development.env")
SECRET_KEY = os.getenv("SECRET_KEY")
if not SECRET_KEY:
    raise ValueError("No SECRET_KEY set for Flask application. Set it in the development.env file.")

app.secret_key = SECRET_KEY
@app.route('/')
def index():
    bgi='white.png'
    if 'bgi' in session:
        bgi = session['bgi']
    session['bgi']=bgi
    bgfile = url_for('static', filename=bgi)
    return render_template('index.html', bgfile=bgfile)

@app.route('/changebg')
def changebg():
    if 'bgi' in session:
        bgi = session['bgi']
    bgfile = url_for('static', filename=bgi)
    return render_template('changebg.html', bgfile=bgfile)

@app.route('/newbg', methods=['GET','POST'])
def newbg():
    if request.method=='POST':
        bgi=request.form['bgi']
        session['bgi']=bgi
        return redirect(url_for('index'))
    else:
        # Handle GET request
        bgi = session.get('bgi', 'white.png')
        bgfile = url_for('static', filename=bgi)
        return render_template('changebg.html', bgfile=bgfile)