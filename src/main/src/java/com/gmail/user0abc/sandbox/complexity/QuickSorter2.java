package com.gmail.user0abc.sandbox.complexity;

import java.util.List;

/**
 * @author Sergii Ivanov
 */
public class QuickSorter2<T extends Comparable> implements Sorter<T> {
    private int opr = 0;
    private int itr = 0;

    @Override
    public List<T> sort(List<T> arr) {
        return quickSort(arr, 0, arr.size() - 1);
    }

    private List<T> quickSort(List<T> arr, int start, int end) {
        T median = arr.get(start + (end - start) / 2);
        int left = start, right = end;
        while (left <= right) {
            itr++;
            while (arr.get(left).compareTo(median) < 0) {
                left++;
            }
            while (arr.get(right).compareTo(median) > 0) {
                right--;
            }
            if (left <= right) {
                swap(arr, left, right);
                opr++;
                left++;
                right--;
            }
        }
        if (start < right) {
            quickSort(arr, start, right);
        }
        if (left < end) {
            quickSort(arr, left, end);
        }
        return arr;
    }

    private void swap(List<T> arr, int pos1, int pos2) {
        T el = arr.get(pos1);
        arr.set(pos1, arr.get(pos2));
        arr.set(pos2, el);
    }

    @Override
    public int getIterations() {
        return itr;
    }

    @Override
    public int getOperations() {
        return opr;
    }
}
