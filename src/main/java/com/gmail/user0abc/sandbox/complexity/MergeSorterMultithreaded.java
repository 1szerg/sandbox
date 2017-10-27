package com.gmail.user0abc.sandbox.complexity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.gmail.user0abc.sandbox.Util.prn;

/**
 * @author Sergii Ivanov
 */
public class MergeSorterMultithreaded<T extends Comparable> implements Sorter<T> {
    private static final int THRESHOLD = 10000;
    int op = 0, it = 0;
    List<T> temp;

    @Override
    public List<T> sort(List<T> arr) {
        temp = new ArrayList<>(arr.size());
        for (int i = 0; i < arr.size(); i++) {
            temp.add(null);
        }
        return mergeSort(arr, 0, arr.size() - 1);
    }

    private List<T> mergeSort(List<T> arr, int start, int end) {
        if (start < end) {
            it++;
            int mid = start + (end - start) / 2;
            if(THRESHOLD < (end - start) / 2){
                Thread t1 = new Thread(() -> {
                    mergeSort(arr, start, mid);
                });
                Thread t2 = new Thread(() -> {
                    mergeSort(arr, mid + 1, end);
                });
                try {
                    t1.join();
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                mergeSort(arr, start, mid);
                mergeSort(arr, mid + 1, end);
            }
            merge(arr, start, mid, end);
        }
        return arr;
    }

    private void merge(List<T> arr, int start, int mid, int end) {
        for (int i = start; i <= end; i++) {
            temp.set(i, arr.get(i));
        }

        int left = start;
        int right = mid + 1;
        int pos = start;
        while (left <= mid && right <= end) {
            op++;
            it++;
            if (temp.get(left).compareTo(temp.get(right)) < 0) {
                arr.set(pos, temp.get(left));
                left++;
            } else {
                arr.set(pos, temp.get(right));
                right++;
            }
            pos++;
        }
        while (left <= mid) {
            op++;
            it++;
            arr.set(pos, temp.get(left));
            left++;
            pos++;
        }
    }

    @Override
    public int getIterations() {
        return it;
    }

    @Override
    public int getOperations() {
        return op;
    }
}
