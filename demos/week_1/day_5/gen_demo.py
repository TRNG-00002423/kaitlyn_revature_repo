def get_numbers():
    numbers = []

    for i in range(1, 6):
        numbers.append(i)
    
    return numbers

result = get_numbers()
print(result)

def get_numbers_generated():
    for i in range(1, 6):
        yield i # Whenever a function encounters yield, the execution is paused.

result_gen = get_numbers_generated()
print(f"prints gibberish: {result_gen}")
print("Generated objects are iterable.")
for num in result_gen:
    print(num)

