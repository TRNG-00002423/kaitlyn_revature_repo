import sys

name = input("What is your name? ")
role = input("What is your role? ")
print(f"Hello, {name}! Your role is {role}.")
print(f"Python version: {'.'.join(map(str, sys.version_info[:3]))}")