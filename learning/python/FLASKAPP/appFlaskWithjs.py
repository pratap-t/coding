from flask import Flask, render_template
app = Flask(__name__)
@app.route('/<name>')
def index(name):
    # return render_template('index.html', title=name)
    name = "{}".format(name)

    marks = [{'Name':'Kiran','Marks':76}, {'Name':'Abdulla', 'Marks':33},{'Name':'Sameer','Marks':67},{'Name':'Zeenat', 'Marks':51},]
    return render_template('index.html', college=name, marks=marks)