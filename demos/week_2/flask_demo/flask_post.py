from flask import Flask, request, jsonify

app = Flask(__name__)
@app.route("/sum", methods=["POST"])
def sum():
    data = request.get_json()
    print(data)
    x = data["num1"]
    y = data["num2"]
    return jsonify({"result": int(x)+int(y)})


if __name__=="__main__":
    app.run(debug=True)