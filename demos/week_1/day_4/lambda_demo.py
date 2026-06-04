from functools import reduce


def add(a, b):
    return a + b

add_lambda = lambda a, b: a + b

print(add(2, 3))
print(add_lambda(2, 3))

# map, reduce, filter, zip

# map() applies a function for every element of an iterable
numbers = [1, 2, 3, 4, 5]
double = list(map(lambda x: x*2, numbers))
print(double)

names = ["Mitzy", "Meia Noite", "Socks", "Scarlett", "Clementine"]
caps = list(map(lambda name: name.upper(), names))
print(caps)

numbers = list(range(1, 10))
evens = list(filter(lambda number: number % 2 == 0, numbers))
print(evens)

# reduce(): often used as an aggregator
numbers = list(range(1, 5))
sum = reduce(lambda x, y: x + y, numbers)
print(sum)

# zip(): takes 2 or more iterables and combines them element by element in tuples
colors = ["tortoiseshell", "black", "tuxedo", "tabby", "tortoiseshell"]
zip_cat_color = zip(names, colors)
list_cat_color = list(zip_cat_color)
print(list_cat_color)