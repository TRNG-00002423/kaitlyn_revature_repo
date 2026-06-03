# Task 1 Response: Installing pandas installed 4 dependencies: python-dateutil, tzdata, pytz, and numpy.

import pandas as pd
import matplotlib.pyplot as plt
df = pd.read_csv("test_data.csv")

print("══════════════════════════════════════")
print("Test Results Analysis")
print("══════════════════════════════════════")

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
print(f"Average test duration: {round(mean,2):,}ms ({round(df['duration_ms'].mean()/1000,2):,}s)")
print(f"Fastest test: {shortest_name} ({round(shortest,2):,}ms)")
print(f"Slowest test: {longest_name} ({round(longest,2):,}ms)")
print(f"Standard deviation of test durations: {round(df['duration_ms'].std(),2)}ms")

print("\n  ── By Module ──")
def format_mean(_s):
    return str(round(_s.mean(),2)) + "ms"

def format_pass_rate(_s):
    passed_tests = _s.value_counts()['pass']
    total_tests = _s.count()
    return str(round(passed_tests*100/total_tests, 2)) + '%'

aggregated_data = df.groupby("module").agg(
    tests = ("test_name", "count"),
    pass_rate = ("status", format_pass_rate),
    avg_duration = ("duration_ms", format_mean),
)
aggregated_data = aggregated_data.rename(columns={"tests": "Total Tests", "pass_rate": "Pass Rate", "avg_duration": "Avg Duration"})
print(aggregated_data)

print('\n  ── Failed Tests ──')
failed_df = df[df['status'] == 'fail'][['test_name', 'module', 'duration_ms']]
format_mapping = {"duration_ms": "{:,}ms"}
for key, value in format_mapping.items():
    failed_df[key] = failed_df[key].apply(value.format)
print(failed_df)

df.plot.show()