numbers = [1, 2, 3, 4, 5]

for number in numbers:
    print(number)

print('~' * 20)

my_itr = iter(numbers)
print(next(my_itr))
print(next(my_itr))