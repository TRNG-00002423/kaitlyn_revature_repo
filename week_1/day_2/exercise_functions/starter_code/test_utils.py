import re
def format_test_name(name):
    """Convert a human-readable name to a test function name.

    Example:
        format_test_name("Valid Login") → "test_valid_login"
        format_test_name("  Search Results Page  ") → "test_search_results_page"

    Rules:
        - Lowercase
        - Spaces replaced with underscores
        - Leading/trailing whitespace stripped
        - Prefixed with "test_"
    """
    # I know there's a way to do this with regex, but I'm a
    name = re.sub('[^A-Za-z0-9 _]+', '', name)
    return "test_" + name.strip().lower().replace(" ", "_")

def is_valid_test_name(name):
    """Check if a string is a valid test function name.

    Rules:
        - Must start with "test_"
        - Must contain only lowercase letters, digits, and underscores
        - Must be at least 6 characters (e.g., "test_x")

    Returns: bool
    """
    if name[:5] != "test_":
        return False
    if re.sub('[^A-Za-z0-9_]+', '', name) != name: # i.e. If you remove all special characters from the test name, is the string identical?
        return False
    if len(name) < 6:
        return False
    return True


# Tests
assert format_test_name("Valid Login") == "test_valid_login"
assert format_test_name("  Search Results  ") == "test_search_results"
assert is_valid_test_name("test_login") == True
assert is_valid_test_name("login_test") == False
assert is_valid_test_name("test_") == False