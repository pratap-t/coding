from flask import Flask, request, render_template, make_response
app = Flask(__name__)
@app.route('/setcookie', methods=['POST'])
def setcookie():
    if request.method == 'POST':
        user = request.form['name']
        resp = make_response(render_template('setcookie.html'))
        resp.set_cookie('userID', user)
        return resp

@app.route('/readcookie')
def readcookie():
    name = request.cookies.get('userID')
    return render_template('readcookie.html', name=name)