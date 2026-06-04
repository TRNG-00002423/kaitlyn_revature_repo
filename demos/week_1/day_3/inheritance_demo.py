class Animal:
    def __init__(self, name):
        self.name = name
    def speak(self):
        return "..."
    
class Dog(Animal):
    def speak(self):
        return "Woof!"

class Cat(Animal):
    def speak(self):
        return "Meow!" 
    
rex = Dog("Rex")
print(rex.speak())

socks = Cat("Socks")
print(socks.speak())