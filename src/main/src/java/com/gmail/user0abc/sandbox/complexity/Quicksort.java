package com.gmail.user0abc.sandbox.complexity;

/**
 * @author Sergii Ivanov
 */
public class Quicksort {
    private int[] numbers;
    private int number;

    public void sort(int[] values) {
        // check for empty or null array
        if (values == null || values.length == 0) {
            return;
        }
        this.numbers = values;
        number = values.length;
        quicksort(0, number - 1);
    }

    private void quicksort(int start, int end) {
        int left = start, right = end;
        // Get the pivot element from the middle of the list
        int pivot = numbers[start + (end - start) / 2];

        // Divide into two lists
        while (left <= right) {
            // If the current value from the left list is smaller than the pivot
            // element then get the next element from the left list
            while (numbers[left] < pivot) {
                left++;
            }
            // If the current value from the right list is larger than the pivot
            // element then get the next element from the right list
            while (numbers[right] > pivot) {
                right--;
            }

            // If we have found a value in the left list which is larger than
            // the pivot element and if we have found a value in the right list
            // which is smaller than the pivot element then we exchange the
            // values.
            // As we are done we can increase i and j
            if (left <= right) {
                exchange(left, right);
                left++;
                right--;
            }
        }
        // Recursion
        if (start < right)
            quicksort(start, right);
        if (left < end)
            quicksort(left, end);
    }

    private void exchange(int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }
}
