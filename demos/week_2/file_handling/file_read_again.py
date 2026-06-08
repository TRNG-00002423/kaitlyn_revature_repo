try:
    file = open("abc.txt", "r")
    content = file.read()
    print(content)
    file.close()
except FileNotFoundError:
    print("Unable to find file")
except:
    print("Some other error occured")