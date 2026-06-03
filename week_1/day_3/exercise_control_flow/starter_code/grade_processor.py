import sys
import csv
SENTINEL_VALUE = -999
scores = []

with open('grades.csv') as csvfile:
    reader = csv.reader(csvfile, delimiter=',')
    for line in reader:
        for score in line:
            scores.append(int(score))
        

grades = [None] * len(scores)
sum = 0
lowest_grade = sys.maxsize
highest_grade = -1 * sys.maxsize
valid_scores = 0
distribution = {
    'A': 0,
    'B': 0,
    'C': 0,
    'D': 0,
    'F': 0
}

for i, score in enumerate(scores):

    if score == SENTINEL_VALUE:
        break
    if score < 0:
        grades[i] = "invalid"
        continue

    sum += score

    if score < lowest_grade:
        lowest_grade = score
    if score > highest_grade:
        highest_grade = score
    valid_scores += 1

    if score >= 90:
        grades[i] = 'A'
        distribution['A'] += 1
    elif score >= 80:
        grades[i] = 'B'
        distribution['B'] += 1
    elif score >= 70:
        grades[i] = 'C'
        distribution['C'] += 1
    elif score >= 60:
        grades[i] = 'D'
        distribution['D'] += 1
    else:
        grades[i] = 'F'
        distribution['F'] += 1

average = sum / valid_scores

print("===Grade Processor===\n")
print(f"Students' grades: {grades}")
print(f"Class average: {average}")
print(f"Lowest grade: {lowest_grade}")
print(f"Highest grade: {highest_grade}")

print("\n===Distribution===\n")
print(distribution)