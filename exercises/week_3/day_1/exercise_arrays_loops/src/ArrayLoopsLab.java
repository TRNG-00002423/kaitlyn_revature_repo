import java.util.Arrays;

/**
 * Lab 1 — Arrays & loops. Implement the bodies.
 * See ../README.md
 */
public class ArrayLoopsLab {

    /** Reverse array in place. */
    public static void reverse(int[] data) {
        if (data == null) {
            return;
        }
        for (int i = 0; i < data.length / 2; i++) {
            int temp = data[i];
            data[i] = data[data.length - 1 - i];
            data[data.length - 1 - i] = temp;
        }
    }

    /** Smallest element; illegal if null or empty. */
    public static int min(int[] data) {
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("Cannot get min of null or empty array.");
        }
        int smallestValue = data[0];
        for (int i = 0; i < data.length; i++) {
            if (data[i] < smallestValue) {
                smallestValue = data[i];
            }
        }
        return smallestValue;
    }

    /** Largest element; illegal if null or empty. */
    public static int max(int[] data) {
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("Cannot get max of null or empty array.");
        }
        int largestValue = data[0];
        for (int i = 0; i < data.length; i++) {
            if (data[i] > largestValue) {
                largestValue = data[i];
            }
        }
        return largestValue;
    }

    /** In-place ascending sort using nested loops only (no Arrays.sort). */
    public static void sortAscending(int[] data) {
        // I am using bubble sort, which has a time complexity of O(n^2).
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (data[i] < data[j]) {
                    int temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                }
            }
        }
    }

    public static void showOffMethods(int[] demoArray) {
        System.out.println("Original array: " + Arrays.toString(demoArray));
        reverse(demoArray);
        System.out.println("Reversed array: " + Arrays.toString(demoArray));
        System.out.println("Minimum value: " + min(demoArray));
        System.out.println("Maximum value: " + max(demoArray));
        sortAscending(demoArray);
        System.out.println("Sorted (ascending) array: " + Arrays.toString(demoArray));
    }

    public static void main(String[] args) {
        System.out.println("Array Loops Lab");

        int[] array1 = { 8, 6, 7, 5, 3, 0, 9 };
        int[] array2 = { 1963, 1969, 2003, 2004, 1997, 1984, 2001 };
        System.out.println("\nArray 1:");
        showOffMethods(array1);
        System.out.println("\nArray 2: ");
        showOffMethods(array2);
    }
}