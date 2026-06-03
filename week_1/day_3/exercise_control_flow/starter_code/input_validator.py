import re
def validate_password(password):
    eight_char = True if len(password) >= 8 else False
    has_lowercase = False
    has_uppercase = False
    has_digit = False
    has_special_char = False

    uppercase_regex = re.compile('[A-Z]')
    lowercase_regex = re.compile('[a-z]')
    digit_regex = re.compile('[0-9]')
    special_regex = re.compile('[!@#$%^&*]')

    errors = []

    if not eight_char:
        errors.append("Shorter than 8 characters")

    if uppercase_regex.search(password):
        has_uppercase = True
    else:
        errors.append("No uppercase letters")
    if lowercase_regex.search(password):
        has_lowercase = True
    else:
        errors.append("No lowercase letters")
    if digit_regex.search(password):
        has_digit = True
    else:
        errors.append("No digits")
    if special_regex.search(password):
        has_special_char = True
    else:
        errors.append("No special characters")
    
    valid = eight_char and has_lowercase and has_uppercase and has_digit and has_special_char

    return {
        "valid": valid,
        "errors": errors
    }


print(validate_password("Abc123!x"))    # valid
print(validate_password("abc"))         # too short, no upper, no digit, no special
print(validate_password("ABCDEFGH"))    # no lower, no digit, no special
print(validate_password("ABCDefgh1!"))  # valid