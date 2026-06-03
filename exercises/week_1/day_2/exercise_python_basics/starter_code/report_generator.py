from texttable import Texttable

test_login = {"name": "test_login", "duration": 1200, "status": "✅ PASS"}
test_search = {"name": "test_search", "duration": 850, "status": "✅ PASS"}
test_checkout = {"name": "test_checkout", "duration": 2300, "status": "❌ FAIL"}
test_profile = {"name": "test_profile", "duration": 450, "status": "✅ PASS"}
test_logout = {"name": "test_logout", "duration": 100, "status": "✅ PASS"}

tests = [test_login, test_search, test_checkout, test_profile, test_logout]
headers = ["Test Name", "Duration", "Status"]

table = Texttable()
table.add_row(headers)

for dict in tests:
    table.add_row([f"{dict['name']:<16}", f"{dict['duration']:>10,} ms", f"{dict['status']:<16}"])

sum = 0
for test in tests:
    sum += test['duration']

total_tests = len(tests)
passed_tests = 0
for test in tests:
    if test["status"] == "✅ PASS":
        passed_tests += 1


table.add_row(["TOTAL", f"{sum:>10,} ms", f"Passed Tests: {str(passed_tests)+'/'+str(total_tests):<16}"])


print(table.draw())