# from wsgiref.simple_server import make_server
import time

def application(environ, start_response):
    # Record the start time
    start_time = time.time()
    host=environ.get('HTTP_HOST')
    start_response("200 OK", [("Content-type", "text/html")])
    ret = [("<h2>Hello World!<br/> From WSGI Server :%s</h2>" % (host)).encode("utf-8")]
    # Record the end time
    end_time = time.time()
    # Calculate the duration
    response_time = (end_time - start_time) * 1000  # Convert to milliseconds
    ret.append(("<p>Response time: %.2f seconds</p>" % response_time).encode("utf-8"))
    # Log the response time
    print(f"Response time: {response_time:.2f} seconds")
    # Return the response
    return ret

# server = make_server('localhost', 8000, application)
# server.serve_forever()