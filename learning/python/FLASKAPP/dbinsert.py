from flask import Flask, request, render_template
import sqlite3
from encryption import encpwd

app = Flask(__name__)
@app.route('/addrec', methods=['GET', 'POST'])
def addrec():
    if request.method == 'POST':
        conn=sqlite3.connect('mydata.db')
        cur=conn.cursor()
        msg=''

        try:
            nm=request.form['name']
            gndr=request.form['gender']
            course=request.form['course']
            mob=request.form['mobile']
            usr=request.form['user']
            pw=request.form['pwd']
            ins="INSERT INTO Students VALUES(?,?,?,?,?,?)"
            cur.execute(ins,(nm,gndr,course,mob,usr,encpwd(pw)))
            conn.commit()
            msg="Record successfully added"
        except Exception as e:
            conn.rollback()
            msg="error in insert operation"
        finally:
            return render_template("record.html",msg=msg)
        
@app.route('/list')
def list():
    con=sqlite3.connect("mydata.db")
    con.row_factory=sqlite3.Row
    cur=con.cursor()
    cur.execute("select * from Students;")
    students=cur.fetchall()
    return render_template("studentlist.html", students=students)