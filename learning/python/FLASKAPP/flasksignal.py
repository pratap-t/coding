from flask import Flask, current_app, request
from blinker import Namespace
import logging
logging.basicConfig(level=logging.DEBUG)

app = Flask(__name__)
app.secret_key = 'supersecretkey'

nmspc = Namespace()

def test_signal(app, message, **extra):
    logging.debug(message)
    test = nmspc.signal('test')
    test.connect(test_signal, app)

@app.route('/', methods=['POST','GET'])
def home():
    test = nmspc.signal('test signal')
    test.send(current_app, message='Send client IP address : ' + request.remote_addr)
    return 'Hello World'