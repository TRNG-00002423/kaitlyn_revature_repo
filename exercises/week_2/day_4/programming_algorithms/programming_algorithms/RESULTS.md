The following is an example output of SearchBenchmark.java:

| Round | Linear Search | Binary Search|
| -------- | ------- | ------- |
| Round 1 (N=1,000,000) | 8ms | 0ms
| Round 2 (N = 5,000,000) | 10ms  | 0ms

With the values of N provided, the runtime of the binary search for Java to precisely record it in milliseconds.

# Linear vs binary — results
## Contributors
Not applicable, doing this assignment solo.

## Round 1 (N = 1,000,000)

| Algorithm | Time (ms) | Notes |
|-----------|-----------|-------|
| Linear | 8 ms | |
| Binary | 0 ms| Too short for Java to record|

## Round 2 (N = 5,000,000)

| Algorithm | Time (ms) | Notes |
|-----------|-----------|-------|
| Linear | 10 ms | |
| Binary |0 ms | Too short for Java to record|



## Big-O discussion
These results seem to be consistent with our understanding of the the time complexity of linear and binary search.

Linear search has a time complexity of $O(n)$. Therefore, as the size of the arrays passed in to the linear search function increased, the time it takes to search for a target value increases proportionally.

Binary search has a time complexity of $O(\log n)$, meaning that as $N$ increases, the time complexity will only increase very slightly. Large increases in $N$ will have a much smaller effect on runtime compared to linear search.

## Caveats (JVM, cache, warmup)
This exercise was significantly limited by the fact that binary search was consistently faster than 1 millisecond for all values of $N$. I attempted to increase $N$ such that the result of binary search would take longer than a millisecond to compute, but Java's heap memory ended up stopping me first.