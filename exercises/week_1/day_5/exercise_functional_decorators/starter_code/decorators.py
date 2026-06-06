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

# Implement (3-layer nesting: factory → decorator → wrapper)
def retry(max_attempts=3, delay=0.5, exceptions=(Exception,)):
    """Parameterized decorator that retries on failure.

    Args:
        max_attempts: Maximum number of tries
        delay: Seconds between retries
        exceptions: Tuple of exception types to catch

    Prints progress: "⚠️ Attempt {n}/{max}: {error}. Retrying in {delay}s..."
    On final failure: "💥 {func_name} failed after {max} attempts"
    """
    def decorator(func):
        @wraps(func)
        def wrapper(*args, **kwargs):
            attempt = 1
            while attempt <= max_attempts:
                try:
                    return func(*args, **kwargs)
                except exceptions:
                    if attempt == max_attempts:
                        print(f"💥 {func.__name__} failed after {max_attempts} attempts")
                    else:
                        print(f"⚠️ Attempt {attempt}/{max_attempts}: {exceptions}. Retrying in {delay}s...")
                        time.sleep(delay)
                    attempt += 1
            return func(*args, **kwargs)
        return wrapper
            
    return decorator

attempt_count = 0

@retry(max_attempts=3, delay=0.1)
def flaky_function():
    global attempt_count
    attempt_count += 1
    if attempt_count < 3:
        raise ConnectionError("Server unavailable")
    return "success"

result = flaky_function()
assert result == "success"



def log_calls():
    """Decorator that logs function calls with arguments and return value.

    Output:
        "📞 Calling func_name(arg1, arg2, key=val)"
        "✅ func_name → return_value"
    """
    def decorator(func):
        def wrapper(*args, **kwargs):
            print(f"📞 Calling {func.__name__}({args}, {kwargs})")
            return_value = func(*args, **kwargs)
            print(f"✅ {func.__name__} → {return_value}")
            return return_value
        return wrapper
    return decorator

@log_calls()
def scream(text, num_exclamations):
    return f"{text.upper()}" + ("!" * num_exclamations)

scream("hello", 20)