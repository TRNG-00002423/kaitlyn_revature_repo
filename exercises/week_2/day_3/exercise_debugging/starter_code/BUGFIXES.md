# Bug fixes


## Bug 1
- **Symptom:** `Exception in thread "main" java.lang.NullPointerException: Cannot invoke "String.trim()" because "<parameter1>" is null at BuggyReport.buildLabel(BuggyReport.java:37) at BuggyReport.main(BuggyReport.java:15)`
- **Root cause:** `buildLabel` doesn't account for `user` being null.
- **Fix:** Before assigning the value of the String `trimmed` to `user.trim()`, which throws `NullPointerException` when `user` is null, check if `user` is null first. If it is null, change `user`'s value to an empty String.

## Bug 2
- **Symptom:** When role level is higher than or equal to required level, the user is denied access.
- **Root cause:** `allowAccess` returns `roleLevel < required`.
- **Fix:** Change `allowAccess` to instead return `roleLevel >= required`.


## Bug 3
- **Symptom:** `average()` always returns an integer, when a floating-point average is supposed to be returned
- **Root cause:** `average()` returns integer division; `sum / values.length` is cast to a double because of the return type of `average()`, but integer division is still performed, as `sum` and `values.length`.
- **Fix:** Cast one of the values in the division to a double, like `(double) sum / values.length`. `sum / (double) values.length` also would've worked here.

## Bug 4
- **Symptom:** `findFirst()` always returns -1, regardless of what is passed in.
- **Root cause:** `findFirst()` declares an appropriate return value `found`, but `found` is not returned and only declared within the scope of the for loop, making it inaccessible to return.
- **Fix:** Change `return -1;` to `return found;`. Declare `found` at the start of the function and initialize it to `-1`. This way, `findFirst()` can still return -1 if the target is never found. Instead of declaring a new variable named `found` on every iteration of the for loop, assign the value of the existing variable.

## Bug 5
- **Symptom:** Off-by-one error. `Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3 at BuggyReport.countWords(BuggyReport.java:72)`
- **Root cause:** In the static function `countWords`, the for loop iterates through indices 0 up to *and including* the length of `words`. Since the last member of `words` has an index of `words.length-1`, attempting to access `words[words.length]` causes an error.
- **Fix:** Change `for (int i = 0; i <= words.length; i++)` to `for (int i = 0; i < words.length; i++)`.