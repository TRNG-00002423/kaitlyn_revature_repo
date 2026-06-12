
/**
 * Pair exercise — build sorted array, pick target, time both searches.
 * TODO: complete main after SearchLib is implemented.
 */
import java.util.Random;;

public class SearchBenchmark {

    // There was some inconsistency in the docs - I'm unsure whether N is supposed
    // to be 100,000 or 1,000,000 in round 1.
    final static int ROUND_1_N = 1000000;
    final static int ROUND_2_N = 5000000;

    public static void runLinearTest(int[] arr, int target) {
        long start = System.currentTimeMillis();
        int index = SearchLib.linearSearch(arr, target);
        long elapsedTime = System.currentTimeMillis() - start;
        assert index != -1 : "Index not found.";
        assert arr[index] == target : "Linear search test returned an inaccurate result.";
        System.out.println(
                "Linear search: Found value " + arr[index] + " at index " + index + " in " + elapsedTime + "ms.");
    }

    public static void runBinaryTest(int[] arr, int target) {
        long start = System.currentTimeMillis();
        int index = SearchLib.binarySearch(arr, target);
        long elapsedTime = System.currentTimeMillis() - start;
        assert index != -1 : "Index not found.";
        assert arr[index] == target : "Binary search test returned an inaccurate result.";
        System.out.println(
                "Binary search: Found value " + arr[index] + " at index " + index + " in " + elapsedTime + "ms.");
    }

    public static void main(String[] args) {
        // SearchLib.linearSearch vs binarySearch
        int[] evenInts1 = buildSortedEvens(ROUND_1_N);
        int[] evenInts2 = buildSortedEvens(ROUND_2_N);

        Random r = new Random();
        int target1 = evenInts1[r.nextInt(evenInts1.length - 1)];
        int target2 = evenInts2[r.nextInt(evenInts2.length - 1)];

        System.out.println("Round 1: N = " + ROUND_1_N);
        runLinearTest(evenInts1, target1);
        runBinaryTest(evenInts1, target1);

        System.out.println("Round 2: N = " + ROUND_2_N);
        runLinearTest(evenInts2, target2);
        runBinaryTest(evenInts2, target2);
    }

    static int[] buildSortedEvens(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i * 2;
        }
        return arr;
    }
}