import numpy as np

a = [1, 2, 3]
b = [4, 5, 6]

result = [x + y for x, y in zip(a,b)] # [5, 7, 9]
print(result)

# numpy makes list/array calculations easier and simpler so that we don't have to do this.

a = np.array([1, 2, 3])
b = np.array([4, 5, 6])

print(a + b)
arr = np.array([[1, 2, 3],
               [4, 5, 6]])
print(arr)

def divider():
    print("*"*20)


arr1 = np.array([1, 2, 3, 4])
print(arr1 * 2)
divider()
print(np.square(arr1))
divider()
arr2 = np.array([1, 4, 9, 16])
print(np.sqrt(arr2))
divider()
print(np.sum(arr2))
arr3 = np.array([8, 6, 7, 5, 3, 0, 9])
divider()
print(np.max(arr3))
divider()
print(np.min(arr3))
divider()
print(np.zeros(5))
print(np.ones((2, 3)))
print(np.shape(arr3))