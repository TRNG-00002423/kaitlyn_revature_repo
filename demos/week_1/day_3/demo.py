score = 80
if score >= 90:
    grade = 'A'
else:
    grade = 'F'
print(f"Score {score} -> Grade {grade}")

grade = 'PASS' if score >= 60 else 'FAIL'



score_List = [95, 85, 65, 75, 55]
grade_list = []
for i, score in enumerate(score_List):
    if score >= 90:
        grade_list.append('A')
    elif score >= 80:
        grade_list.append('B')
    elif score >= 70:
        grade_list.append('C')
    else:
        grade_list.append('F')
    print(f"Score {score} -> Grade {grade_list[i]}")

tests = ["login", "search", "checkout", "logout"]
for test in tests:
    print(test.upper())
i = 0
while i < len(tests):
    print(tests[i].upper())
    i += 1

numbers = [4, 5, 7, 2, -5, 9, -2, 5, -3, 1, 0, 8]
for number in numbers:
    if number < 0:
        print(f"skipped")
        continue
    if number == 0:
        break
    print(number)
    