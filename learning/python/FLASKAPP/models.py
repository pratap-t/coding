from flask import Flask
from flask_sqlalchemy import SQLAlchemy

app=Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///flaskdatabase.db'
app.config['SECRETKEY'] = "thisisasecretkey"
db=SQLAlchemy(app)

class students(db.Model):
    name=db.Column(db.String(20))
    course=db.Column(db.String(20))
    gender=db.Column(db.String(20))
    mobile=db.Column(db.Integer)
    username=db.Column(db.String(6), primary_key=True)
    pasword=db.Column(db.String(8), nullable=False)

    def __init__(self, name, course, gender, mobile, username, password):
        self.name=name
        self.course=course
        self.gender=gender
        self.mobile=mobile
        self.username=username
        self.pasword=password

class books(db.Model):
    bookID=db.Column(db.String(6), primary_key=True)
    title=db.Column(db.String(20))
    author=db.Column(db.String(20))
    price=db.Column(db.Integer)

    def __init__(self, id, title, author, price):
        self.bookID=id
        self.title=title
        self.Author=author
        self.price=price

    def serialize(self):
        return {
            "bookID": self.bookID,
            "title": self.title,
            "author": self.author,
            "price": self.price
        }