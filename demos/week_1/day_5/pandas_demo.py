import numpy as np

a = [1, 2, 3]
b = [4, 5, 6]

result = [x + y for x, y in zip(a,b)] # [5, 7, 9]
print(result)

a = np.array([1, 2, 3])
b = np.array([4, 5, 6])

print(a + b)