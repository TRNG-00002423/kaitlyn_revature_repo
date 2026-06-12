/**
 * Pair exercise — build sorted array, pick target, time both searches.
 * TODO: complete main after SearchLib is implemented.
 */

public class SearchBenchmark {

    // There was some inconsistency in the docs - I'm unsure whether N is supposed
    // to be 100,000 or 1,000,000 in round 1.
    final static int ROUND_1_N = 100000;
    final static int ROUND_2_N = 5000000;

    public static void main(String[] args) {
        // TODO: size N, fill sorted even integers, pick target, time
        // SearchLib.linearSearch vs binarySearch
        int[] evenInts1 = buildSortedEvens(ROUND_1_N);
        int[] evenInts2 = buildSortedEvens(ROUND_2_N);

        System.out.println("Implement benchmark");
    }

    static int[] buildSortedEvens(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i * 2;
        }
        return arr;
    }
}