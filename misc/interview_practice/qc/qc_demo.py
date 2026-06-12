def get_largest_value(numbers):
    if len(numbers) < 1:
        return -1 # error: numbers is empty
    largest_value = numbers[0]
    for number in numbers:
        if number > largest_value:
            largest_value = number
    return largest_value

print(get_largest_value([8, 6, 7, 5, 3, 0, 9]))
print(get_largest_value([]))
print(get_largest_value([9, 239874, 2, -1]))
