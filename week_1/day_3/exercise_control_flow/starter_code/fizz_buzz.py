def fizzbuzz(n, fizz=3, buzz=5, boom=7):
    fizz = n % fizz == 0
    buzz = n % buzz == 0
    boom = n % boom == 0
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

fizzbuzz(105) # regular mode
fizzbuzz(42, fizz=2, buzz=6, boom=7) # custom mode