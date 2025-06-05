from flask import Flask, render_template, request
app = Flask(__name__)
@app.route('/form')
def form():
    return render_template('register.html')
@app.route('/register', methods=['GET', 'POST'])
def register():
    if request.method == 'POST':
        nm = request.form.get('name')
        course = request.form.get('course')
        return """
            <h2>Data received from Client browser with POST method</h2>
            <b>Name:</b> {} <br>
            <b>Course:</b> {} <br>
            """.format(nm, course)