# Sets: Mutable, unordered, unique
ids = {1, 2, 3, 4, 5}

empty_set = set()

numbers = {1, 2, 2, 3, 4, 5, 5, 3}
from_list = set([10, 20, 20, 30])

ids = {1, 2, 3}
ids.add(4)
ids.add(2)
print(ids)

ids.remove(1)
# ids.remove(100) # -> throws an error

ids.discard(2)
ids.discard(100) # does not throw an error

val = ids.pop()
print(f"val is {val}")

ids.clear()

fruits = {"apple", "orange", "mango", "tomato"}
vegetables = {"cabbage", "carrot", "lettuce", "tomato"}

fruits_and_vegetables = fruits.union(vegetables)
both = fruits.intersection(vegetables)
print(fruits_and_vegetables)
print(both)