# In order to read from a file, the file must exist.
# If you try to read from a file that doesn't exist, a FileNotFound error will be thrown.
file = open("data.txt", "r") # "r": read mode

# Read the entire file
# content = file.read()
# print(content)
# print("*" * 20)

# Read all line
lines= file.readlines()
print(lines)

# Always close the file at the end.
file.close()