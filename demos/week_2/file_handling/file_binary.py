file = open("numbers.dat", "wb") # "wb" = write binary
file.write(bytes([10, 20, 30, 40]))

file.close()

file = open("numbers.dat", "rb")
data = file.read()
print(data)
file.close()