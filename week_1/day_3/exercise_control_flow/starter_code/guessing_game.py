import random

x = random.randint(1, 100)
guesses = 7

while guesses > 0:
    guess = int(input(f"Guess a number (guesses left: {guesses}): "))
    if guess > x:
        print("Too high!")
        guesses -= 1
    elif guess < x:
        print("Too low!")
        guesses -=1
    else:
        break

if guess == x:
    print(f"You win! (attempts used: {7-guesses})")
else:
    print(f"You ran out of guesses. The answer was {x}.")