package com.gmail.user0abc.sandbox.complexity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergii Ivanov
 */
public class SelectionSorter<T extends Comparable> implements Sorter<T> {
    private int op=0, it=0;
    @Override
    public List<T> sort(List<T> arr) {
        for(int i = 0; i < arr.size(); i++){
            int lowIndex = i;
            for(int j = i+1; j < arr.size(); j++){
                it++;
                if(arr.get(lowIndex).compareTo(arr.get(j)) > 0){
                    lowIndex = j;
                }
            }
            swap(arr, i, lowIndex);
            op++;
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
        return op;
    }

    @Override
    public int getOperations() {
        return it;
    }
}
