import pandas as pd

s = [10, 20, 30, 40]

print(s)

data = {
    "name": ["Ken", "John", "Audy"],
    "age": [47, 28, 29],
    "marks": [86, 87, 85]
}

df = pd.DataFrame(data)
print(df.head())
print(df.info())

print(df["name"])

high_marks = df[df["marks"]>85]
print(high_marks)

df["passed"] = df["marks"] > 60
print(df.head())