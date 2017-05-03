package com.gmail.user0abc.sandbox.complexity;

import java.util.List;

/**
 * @author Sergii Ivanov
 */
public class HeapSorter<T extends Comparable> implements Sorter<T> {
    private int it = 0;
    private int op = 0;
    private int n;

    @Override
    public List<T> sort(List<T> arr) {
        return heapSort(arr);
    }

    private List<T> heapSort(List<T> arr) {
        buildHeap(arr);
        for(int i=n; i>0; i--){
            swap(arr, 0, i);
            n--;
            maxHeap(arr, 0);
        }
        return arr;
    }

    private void buildHeap(List<T> arr) {
        n = arr.size()-1;
        for(int i = n/2; i>= 0; i--){
            it++;
            maxHeap(arr, i);
        }
    }

    private void maxHeap(List<T> arr, int edge) {
        int left = 2 * edge;
        int right = 2 * edge + 1;
        int largest = edge;
        if(left <= n && arr.get(left).compareTo(arr.get(edge)) > 0){
            largest = left;
        }
        if(right <= n && arr.get(right).compareTo(arr.get(largest)) > 0){
            largest = right;
        }
        if(largest != edge){
            swap(arr, edge, largest);
            op++;
            maxHeap(arr, largest);
        }

    }

    private void swap(List<T> arr, int pos1, int pos2) {
        T el = arr.get(pos1);
        arr.set(pos1, arr.get(pos2));
        arr.set(pos2, el);
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
