class Dog:
    def __init__(self, name, breed, age):
        self.name = name
        self.breed = breed
        self.age = age
    
    def describe(self):
        return f"{self.name} is a {self.age} year old {self.breed}."
    
    def bark(self):
        return f"{self.name} says: Woof"
    
rex = Dog("Rex", "German Shepherd", 3)
print(rex.name)
print(rex.describe())
print(rex.bark())

luna = Dog("Luna", "Golden Retriever", 5)
print(luna.describe())