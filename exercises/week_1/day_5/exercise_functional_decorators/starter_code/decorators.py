import time
from functools import wraps

def timer(func):
    """Decorator that measures and prints execution time.

    Output format: "⏱️ {func_name} completed in {seconds:.4f}s"
    """
    @wraps(func)
    def wrapper():
        start = time.time()
        end = time.time()
        print(f"⏱️ {func.__name__} completed in {end-start:.4f}s")
        return func()


    return wrapper

@timer
def slow_operation():
    time.sleep(0.5)
    return "done"

result = slow_operation()
# Should print: ⏱️ slow_operation completed in 0.50XXs
assert result == "done"
assert slow_operation.__name__ == "slow_operation"  # @wraps preserves metadata