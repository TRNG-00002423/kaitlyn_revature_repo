class MathUtil:

    @staticmethod # neither instance nor class
    def is_even(x):
        return x % 2 == 0
    
    def cel_to_fer(c):
        return c * (9/5) + 32
    
print(MathUtil.is_even(20))