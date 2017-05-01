package com.gmail.user0abc.sandbox.complexity;

import java.util.List;

/**
 * @author Sergii Ivanov
 */
public class BubbleSorter<T extends Comparable> implements Sorter<T> {
    private int iterations = 0, operations = 0;

    @Override
    public List<T> sort(List<T> arr) {
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < (arr.size() - 1); i++) {
                iterations++;
                if (arr.get(i).compareTo(arr.get(i + 1)) > 0) {
                    operations++;
                    swap(arr, i, i + 1);
                    sorted = false;
                }
            }
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
        return iterations;
    }

    @Override
    public int getOperations() {
        return operations;
    }
}
