package com.gmail.user0abc.sandbox.complexity;

import java.util.List;

/**
 * @author Sergii Ivanov
 */
public class InsertionSorter<T extends Comparable> implements Sorter<T>
{
    private int op=0, it=0;
    @Override
    public List<T> sort(List<T> arr)
    {
        for(int i = 1; i < arr.size(); i++){
            if(arr.get(i-1).compareTo(arr.get(i)) > 0){
                it++;
                for(int j = i; j > 0 && (arr.get(j-1).compareTo(arr.get(j)) > 0); j--){
                    it++;
                    swap(arr, j, j-1);
                    op++;
                }
            }
        }
        return arr;
    }

    @Override
    public int getIterations()
    {
        return it;
    }

    @Override
    public int getOperations()
    {
        return op;
    }

    private void swap(List<T> arr, int pos1, int pos2) {
        T el = arr.get(pos1);
        arr.set(pos1, arr.get(pos2));
        arr.set(pos2, el);
    }
}
