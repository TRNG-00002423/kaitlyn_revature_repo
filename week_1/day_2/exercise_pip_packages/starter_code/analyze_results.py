import pandas as pd
df = pd.read_csv("test_data.csv")
print(f"Total number of tests: {df.shape[0]}\n")
print("Columns and their types:")
print(df.dtypes, "\n")
print("First 5 rows:")
print(df.head(), "\n")

passed_tests = df['status'].value_counts()['pass']
failed_tests = df['status'].value_counts()['fail']
total_tests = passed_tests + failed_tests
pass_rate = passed_tests / total_tests * 100


print(f"Overall pass rate: {pass_rate}%")
mean = df['duration_ms'].mean()
shortest_name = df['test_name'][df['duration_ms'].idxmin()]
longest_name = df['test_name'][df['duration_ms'].idxmax()]
shortest = df['duration_ms'].min()
longest = df['duration_ms'].max()
print(f"Average test duration: {round(mean,2)}ms ({round(df['duration_ms'].mean()/1000,2)}s)")
print(f"Fastest test: {shortest_name} ({round(shortest,2)}ms)")
print(f"Slowest test: {longest_name} ({round(longest,2)}ms)")