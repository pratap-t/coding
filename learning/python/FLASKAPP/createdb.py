from flask import Flask
import sqlite3

app = Flask(__name__)
conn = sqlite3.connect("mydata.db")
curr = conn.cursor()
createqry = '''
CREATE TABLE IF NOT EXISTS Students (
    Name String(20) NOT NULL,
    Course String(20),
    Gender String(20),
    Mobile Integer(10),
    Username String(6) PRIMARY KEY NOT NULL,
    Password TEXT(8) NOT NULL
    );
    '''
curr.execute(createqry)