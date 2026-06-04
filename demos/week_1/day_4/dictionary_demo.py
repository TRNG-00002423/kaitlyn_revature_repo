# dictionary: key-value pairs

student = {
    "name": "Kaitlyn",
    "age": 23,
    "grade": "S++"
}

print(student["name"])
print(student.get("age"))

student["grade"] = "S+++"
student["city"] = "Boston"

for key, value in student.items():
    print(key, value)

print(student.keys())
print(student.values())
print(student.items())