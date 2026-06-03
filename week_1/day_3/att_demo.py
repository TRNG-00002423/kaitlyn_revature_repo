class Employee:
    company = "Revature" # class attributes
    emp_count = 0
    def __init__(self, name, role):
        self.name = name # instance attributes
        self.role = role
        Employee.emp_count += 1

    @classmethod
    # A class method works as an alternative to the constructor.
    def admin(cls, name):
        return cls(name, "admin")

empl1 = Employee("Oscar", "QA Engineer")
empl2 = Employee("Cody", "QA Engineer")
empl3 = Employee.admin("Jasdhir")
print(empl1.name)
print(empl2.name)
print(empl3.name)

print(Employee.company)
print(empl1.company)
print(empl2.company)
print(empl3.company)

print(Employee.emp_count)
print(empl1.emp_count)
print(empl2.emp_count)
print(empl3.emp_count)
