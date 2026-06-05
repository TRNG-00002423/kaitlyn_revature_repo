def my_decorator(func):
    def wrapper():
        print("before the function call...")
        func()
        print("after the function call...")
    return wrapper

@my_decorator
def greet():
    print("Greetings!")

greet()

@my_decorator
def hello():
    print("Hello!")
