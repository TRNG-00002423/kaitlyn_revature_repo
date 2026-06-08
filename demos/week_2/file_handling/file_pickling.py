# Pickling

import pickle
file = open("num_pickles.dat", "wb")

numbers = [n * 10 for n in range(1, 100)]
pickle.dump(numbers, file)
file.close()


# Unpickling
file = open("num_pickles.dat", "rb")
data = pickle.load(file) # loads file into memory
print(data)

# always remember to close the file please please please
file.close()
