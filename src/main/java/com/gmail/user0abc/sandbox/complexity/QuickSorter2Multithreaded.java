package com.gmail.user0abc.sandbox.complexity;

import java.util.List;
import java.util.Map;

/**
 * @author Sergii Ivanov
 */
public class QuickSorter2Multithreaded<T extends Comparable> implements Sorter<T> {
    private static final int THRESHOLD = 10000;
    private int opr = 0;
    private int itr = 0;
    ThreadLocal<Thread> leftThread, rightThread;

    @Override
    public List<T> sort(List<T> arr) {
        if(arr.size() > THRESHOLD+1){
            leftThread = new ThreadLocal<>();
            rightThread = new ThreadLocal<>();
        }
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
            if(right-start > THRESHOLD){
                final int fStart = start;
                final int fEnd = right;
                Thread t1 = new Thread(() -> {
                    quickSort(arr, fStart, fEnd);
                });
                rightThread.set(t1);
                t1.start();
            }else{
                quickSort(arr, start, right);
            }
        }
        if (left < end) {
            if(end-left > THRESHOLD){
                final int fStart = left;
                final int fEnd = end;
                Thread t2 = new Thread(()->{
                    quickSort(arr, fStart, fEnd);
                });
                leftThread.set(t2);
                t2.start();
            }else {
                quickSort(arr, left, end);
            }
        }
        if(leftThread != null && leftThread.get() != null){
            try {
                leftThread.get().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(rightThread != null && rightThread.get() != null){
            try {
                rightThread.get().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
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
        return itr;
    }

    @Override
    public int getOperations() {
        return opr;
    }
}
