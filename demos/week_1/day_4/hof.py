def greet(name):
    return f"Hello {name}!"

# You can assign a function to a variable.
say_hello = greet
print(say_hello("Kaitlyn"))

# You can pass a function as an argument.
def apply(func, value):
    return func(value)

print(apply(greet, "bro"))

