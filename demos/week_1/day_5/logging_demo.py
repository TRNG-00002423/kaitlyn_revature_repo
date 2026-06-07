import logging
print("print vs. logging")
print("Application Started")
print("Something went wrong.")

logging.basicConfig(
    level=logging.DEBUG, # This is the minimum level of severity in order for a message to be logged. i.e. debug or worse.
    format="%(asctime)s | %(levelname)-8s | %(message)s",
    datefmt="%H:%M:%S"

)

logging.debug("This is a DEBUG message.")
logging.info("This is an INFO message.")
logging.warning("This is a WARNING message.")
logging.error("This is an ERROR message")

'''
Printing is usually considered to be part of your program, while logging instead provides additional information.
'''