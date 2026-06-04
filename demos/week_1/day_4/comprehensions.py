# Comprehensions are the Pythonic way to transform and filter data.
# Comprehensions are compact, declarative syntax.

# calculate squares of numbers from 1 to 9.

for num in range(1, 10):
    sq = num ** 2
    print(sq)

squares = [x ** 2 for x in range(1, 10)]
print(squares)

even_squares = [x ** 2 for x in range(1, 10, 2)]
# or [x ** 2 for x in range(1, 10) if x%2 == 0]
print(even_squares)

names = ["Alice", "Bob", "Charlie"]
# dictionary comprehension
name_lengths = {name: len(name) for name in names}
print(name_lengths)
