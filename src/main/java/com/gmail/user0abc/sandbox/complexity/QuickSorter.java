package com.gmail.user0abc.sandbox.complexity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergii Ivanov
 */
public class QuickSorter<T extends Comparable> implements Sorter<T> {
    private int op=0;
    private int it=0;

    @Override
    public List<T> sort(List<T> arr) {
        List<T> temp = quickSort(arr);
        for(int i = 0; i < arr.size(); i++)arr.set(i, temp.get(i));
        return arr;
    }

    private List<T> quickSort(List<T> arr){
        it++;
        if(arr.size()>1){
            if(arr.size()==2){
                op++;
                return merge(lowerList(arr,1), upperList(arr,1));
            }
            int medianIndex = threeMedian(arr);
            List<T> lower = lowerList(arr,medianIndex);
            List<T> upper = upperList(arr,medianIndex);
            if(lower.size()>0 && upper.size() > 0){
                arr = merge(quickSort(lower), quickSort(upper));
            }else{
                medianIndex = lowestMedian(arr);
                lower = lowerList(arr,medianIndex);
                upper = upperList(arr,medianIndex);
                arr = merge(lower,quickSort(upper));
            }
        }
        return arr;
    }


    private int lowestMedian(List<T> arr) {
        int lowestIndex = 0;
        for(int i = 1; i < arr.size(); i++){
            it++;
            if(arr.get(lowestIndex).compareTo(arr.get(i)) > 0){
                lowestIndex = i;
                op++;
            }
        }
        return lowestIndex;
    }

    private List<T> merge(List<T> lower, List<T> upper) {
        for(int i = 0; i < upper.size(); i++){
            it++;
            lower.add(upper.get(i));
            op++;
        }
        return lower;
    }

    private List<T> upperList(List<T> arr, int thresholdIndex) {
        List<T> part = new ArrayList<>();
        for(int i = 0; i < arr.size(); i++){
            it++;
            if(arr.get(i).compareTo(arr.get(thresholdIndex)) > 0){
                part.add(arr.get(i));
                op++;
            }
        }
        return part;
    }

    private List<T> lowerList(List<T> arr, int thresholdIndex) {
        List<T> part = new ArrayList<>();
        for(int i = 0; i < arr.size(); i++){
            it++;
            if(arr.get(i).compareTo(arr.get(thresholdIndex)) <= 0){
                part.add(arr.get(i));
                op++;
            }
        }
        return part;
    }

    private int threeMedian(List<T> arr) {
        /*three points median*/
        int p1=0, p2=arr.size()-1, p3 = arr.size()/2;
        op++;
        if((arr.get(p1).compareTo(arr.get(p2)) > 0 && arr.get(p1).compareTo(arr.get(p3)) < 0) || (arr.get(p1).compareTo(arr.get(p3)) == 0)){
            return p1;
        }
        op++;
        if((arr.get(p2).compareTo(arr.get(p1)) > 0 && arr.get(p2).compareTo(arr.get(p3)) < 0) || (arr.get(p2).compareTo(arr.get(p3)) == 0)){
            return p2;
        }
        return p3;
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
