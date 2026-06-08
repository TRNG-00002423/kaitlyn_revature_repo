from flask import Flask, request, jsonify

app = Flask(__name__)


# When ever you use @app.route(), the below function will be called.
# If no method is mentioned, the default method is GET.
# If you want another method, the method can be specified with a a second parameter.
# @app.route("/student", methods = ["GET", "POST"])
@app.get("/student")
def student():
    # JSON data (dict)
    data = {
        "id": 1,
        "name": "Alice",
        "course": "Python"
    }
    return jsonify(data)



if __name__ == "__main__":
    app.run(debug=True)