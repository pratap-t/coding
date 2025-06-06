from flask import Flask, flash, redirect, render_template, request, url_for
import secrets
import os
from dotenv import load_dotenv

app = Flask(__name__)

# Load environment variables from .env file
load_dotenv(dotenv_path="./development.env")
app.secret_key = os.getenv("SECRET_KEY")
if not app.secret_key:
    raise ValueError("No secret_key set for Flask application. Set it in the development.env file.")

@app.route('/', methods=['GET', 'POST'])
def login():
    username = request.form.get('username')
    password = request.form.get('password')

    if request.method == 'POST':
        if not username or not password:
            flash('Username and password is required', 'error')
            return redirect(url_for('login'))
        
        if len(password) in range(1,9):
               flash('Weak pasword!', 'warning')
        
        if username not in ['admin', 'manager', 'supervisor']:
             flash('You were successfully logged in', 'info')
             return redirect(url_for('success'))
        else:
             flash('User name unavailable', 'error')
             return redirect(url_for('login'))
        return render_template('login.html')
    
    return render_template('login.html')

@app.route('/success')
def success():
     return "<h1>Login Successful</h1>"