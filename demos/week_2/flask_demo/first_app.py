from flask import Flask, request

app = Flask(__name__)
# route is a Flask decorator
@app.route("/")
def home():
    return "Hello Flask"

@app.route("/about")
def about():
    return "About Page"

@app.route("/contact")
def contact():
    return "Contact Page"

# url parameter / path parameter
# /user/<name>/
@app.route("/user/<name>")
def user_name(name):
    return f"Hello {name}!"

# query string
# /user?name=<name>
@app.route("/user1")
def user1_name():
    name = request.args.get("name")
    return f"Hello {name}!!!"

if __name__ == "__main__":
    app.run(debug=True)