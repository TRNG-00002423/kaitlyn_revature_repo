from flask import Flask, request, jsonify, make_response
import json

students = {0: {"id": 0, "name": "Test Student", "course": "underwater basket-weaving"}}

app = Flask(__name__)

@app.get("/students")
def get_students():
    return jsonify(students)

@app.get("/students/<int:id>")
def get_student_by_id(id):
    if id not in students:
        return make_response(jsonify({"message": f"student with id {id} not found"}), 400)
    student = students[id]
    return jsonify(student)

@app.post("/students")
def add_student():
    data = request.get_json()
    if "id" not in data or "name" not in data or "course" not in data:
        return make_response(jsonify({"message": "malformed request"}), 401)
    id = int(data["id"])
    name = data["name"]
    course = data["course"]
    new_student = {"id": id, "name": name, "course": course}
    students[id] = new_student
    return make_response(jsonify(new_student), 201)

@app.put("/students/<int:id>")
def update_student(id):
    if id not in students:
        return make_response(jsonify({"message": f"student with id {id} not found"}), 400)
    data = request.get_json()
    name = data["name"]
    course = data["course"]
    students[id]["name"] = name
    students[id]["course"] = course
    return jsonify(students[id])

@app.delete("/students/<int:id>")
def delete_student(id):
    if id in students:
        students.pop(id)
    return jsonify({"message": "Student deleted successfully"})


if __name__ == "__main__":
    app.run(debug=True)