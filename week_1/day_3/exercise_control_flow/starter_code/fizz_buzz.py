def fizzbuzz(n):
    fizz = n % 3 == 0
    buzz = n % 5 == 0
    boom = n % 7 == 0
    if fizz and buzz and boom:
        print("FizzBuzzBoom")
    elif fizz and buzz:
        print("FizzBuzz")
    elif fizz and boom:
        print("FzizzBoom")
    elif buzz and boom:
        print("BuzzBoom")
    elif fizz:
        print("Fizz")
    elif buzz:
        print("Buzz")
    elif boom:
        print("Boom")
    else:
        print(n)

fizzbuzz(105)