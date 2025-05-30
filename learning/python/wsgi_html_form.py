from wsgiref.simple_server import make_server
from urllib.parse import parse_qs

form = '''
<html>
  <head>
    <title> Hello User! </title>
  </head>
  <body>
    <form method="GET" action="">
      <label>Hello</label>
      <input type="text" name="name" >
      <input type="submit" value="Go">
    </form>
    {greeting}
  </body>
  </html>
'''

def app(environ, start_response):
    greeting = ""

    if environ['REQUEST_METHOD'] == 'GET':
        d = parse_qs(environ['QUERY_STRING'])
        user = d.get('name', [None])[0]
        if user:
            greeting = f"<h2>Hello, {user}!</h2>"
    
    html = form.format(greeting=greeting)
    
    status = '200 OK'
    headers = [('Content-type', 'text/html')]
    start_response(status, headers)
    return [html.encode('utf-8')]

# httpd = make_server('', 8000, app)
# print('Serving on port 8000...')

# httpd.serve_forever()

with make_server('', 8000, app) as httpd:
    print("Listening on port 8000...")
    httpd.serve_forever()