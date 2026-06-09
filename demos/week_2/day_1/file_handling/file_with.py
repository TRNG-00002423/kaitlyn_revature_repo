import pickle
numbers = [1, 2, 3, 4, 5, 6, 7]

# the with keyword is a context manager

# The with statement does the setup
with open("num_pick.dat", "wb") as file:
    pickle.dump(numbers, file)
    # No need to close the file.

with open("num_pick.dat", "rb") as file:
    data = pickle.load(file)
    print(data)