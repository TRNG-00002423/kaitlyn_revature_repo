import pandas as pd
import numpy as np

df = pd.read_csv('iris.csv')
print(df.sample())

print(df.columns)

print(np.linspace(1, 10, 5))
print(df.info())

sepal_length = df["sepal.length"]
print(sepal_length)
length_and_width = df[["sepal.length", "sepal.width"]]

print(df.loc[1])
print(df.loc[0])


print(df[df["variety"] == "Virginica"])
print(df[df["petal.width"] < 2])

print(df[((df["petal.width"] < 2) & (df["petal.length"] < 2))])

print(df[df["variety"].isin(["Setosa", "Versicolor"])])

df["petal.length.one.longer"] = df["petal.length"] + 1
print(df["petal.length.one.longer"])

df["large.petals"] = np.where(df["petal.length"], "Yes", "No")
print(df["large.petals"].sample())

df_sorted = df.sort_values("petal.width", ascending=True)
print(df_sorted.head())