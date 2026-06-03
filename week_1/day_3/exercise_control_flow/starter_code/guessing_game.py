import random
import math

x = random.randint(1, 100)
guesses = 7
right = 100
left = 1

while guesses > 0:
    binary_hint = left + (right-left)//2
    
    print(f"Guesses left: {guesses}")
    guess = int(input(f"Guess a number (Binary search hint: {binary_hint}): "))
    if guess > x:
        print("Too high!")
        guesses -= 1
        right = guess - 1
    elif guess < x:
        print("Too low!")
        guesses -=1
        left = guess + 1
    else:
        break

if guess == x:
    print(f"You win! (attempts used: {7-guesses})")
else:
    print(f"You ran out of guesses. The answer was {x}.")