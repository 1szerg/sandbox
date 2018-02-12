package com.gmail.user0abc.sandbox.complexity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.gmail.user0abc.sandbox.Util.prn;

/**
 * @author Sergii Ivanov
 */
public class MergeSort {
    private static final int N = 10000;
    private int[] numbers;
    private int[] helper;
    public int ops = 0, its = 0, deepest = 0;

    private int number;

    public static void main(String[] ar) {
        Random r = new Random(0l);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = r.nextInt(N);
        }
        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(arr);
        List<Integer> l = new ArrayList<>();
        for (int v : arr) l.add(v);
        prn("done its=" + mergeSort.its + " depth=" + mergeSort.deepest);
    }

    public void sort(int[] values) {
        this.numbers = values;
        number = values.length;
        this.helper = new int[number];
        mergesort(0, number - 1);
    }

    private void mergesort(int low, int high) {
        int lvl = Thread.currentThread().getStackTrace().length;
        if (lvl > deepest) deepest = lvl;
        its++;
        // check if low is smaller than high, if not then the array is sorted
        if (low < high) {
            ops++;
            // Get the index of the element which is in the middle
            int middle = low + (high - low) / 2;
            // Sort the left side of the array
            mergesort(low, middle);
            // Sort the right side of the array
            mergesort(middle + 1, high);
            // Combine them both
            merge(low, middle, high);
        }
    }

    private void merge(int low, int middle, int high) {

        // Copy both parts into the helper array
        for (int i = low; i <= high; i++) {
            helper[i] = numbers[i];
            its++;
        }

        int i = low;
        int j = middle + 1;
        int k = low;
        // Copy the smallest values from either the left or the right side back
        // to the original array
        while (i <= middle && j <= high) {
            its++;
            if (helper[i] <= helper[j]) {
                numbers[k] = helper[i];
                i++;
            } else {
                numbers[k] = helper[j];
                j++;
            }
            k++;
        }
        // Copy the rest of the left side of the array into the target array
        while (i <= middle) {
            numbers[k] = helper[i];
            k++;
            i++;
        }

    }
}