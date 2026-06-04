# List: ordered and mutable collection of items

# Empty list
tests = []

# List with some values
tests = ["login", "search", "checkout"]
mixed_list = [1, "login", "search", "checkout", True]

print(tests[0]) # "login"
print(tests[-1]) # "checkout"

# modify a list
tests[1] = "advanced search"
tests.append("login") # adds "login" to the end of the list
tests.insert(0, "open page")
print(tests)

tests.remove("login") # removes the first instance of "login" from tests
print(tests)
removed = tests.pop()

del tests[0]

print(f"the length of tests is {len(tests)}")

"some value" in tests # returns True or False

tests.index("checkout")
tests.count("login")

tests.sort() # ascending order
tests.sort(reverse=True) # descending order

numbers = [1, 2, 3, 4, 5, 6]
print(numbers[1:4]) # slicing -> a piece of the list
print(numbers[:4])
print(numbers[3:])