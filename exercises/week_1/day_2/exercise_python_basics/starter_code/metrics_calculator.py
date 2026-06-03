print("═══════════════════════════════════════\nQA Test Metrics Calculator\n═══════════════════════════════════════")

test_cases = int(input("Enter total test cases: "))
passed_tests = int(input("Enter passed tests: "))
execution_time = float(input("Enter total execution time (seconds): "))

print("───────────────────────────────────────\nTest Results Summary\n───────────────────────────────────────")

failed_tests = test_cases-passed_tests
pass_rate = str(round(passed_tests/test_cases * 100, 1)) + "%"
fail_rate = str(round(failed_tests/test_cases * 100, 1)) + "%"
avg_time_test = str(round(execution_time/test_cases, 2)) + "s"
total_time = str(round(execution_time, 2)) + "s"

print(f"{'Total tests:':<15}{test_cases:>10}")
print(f"{'Passed:':<15}{passed_tests:>10}")
print(f"{'Failed:':<15}{failed_tests:>10}")
print(f"{'Pass rate:':<15}{pass_rate:>10}")
print(f"{'Fail rate:':<15}{fail_rate:>10}")
print(f"{'Average time/test:':<15}{avg_time_test:>10}")
print(f"{'Total time:':<15}{total_time:>10}")
print()

pass_rate_float = passed_tests/test_cases
if pass_rate_float >= 0.95:
    print("Verdict: ✅ RELEASE APPROVED")
elif pass_rate_float >= 0.80:
    print("Verdict: ⚠️ CONDITIONAL RELEASE — review failures")
else:
    print("Verdict: ❌ RELEASE BLOCKED — too many failures")