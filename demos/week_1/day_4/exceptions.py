
# In Java, exception handling revolves around 5 keywords:
# try, catch, finally, throw, throws

# In Python, exception handling uses 4 keywords
# try, except, else, finally

# The try block is the block where problematic code is to be returned.
try:
   result = int(input("Enter a number: ")) 
# The except block handles the result of an exception in the problematic code.
except ValueError as e:
   print(f"That is not a number -- {e}")
   raise ZeroDivisionError("Some text")
except (TypeError, KeyError) as e:
   print({e})
# The finally block is the code that is called regardless of whether any exceptions are caught in the try block. (clean up operations)
else:
   print("No exception")
finally:
   print("clean up code")



# jumping, loud, i wail out
# glancing, time is ticking down