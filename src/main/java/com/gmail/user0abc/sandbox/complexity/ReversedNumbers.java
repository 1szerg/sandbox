package com.gmail.user0abc.sandbox.complexity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergii Ivanov
 */
public class ReversedNumbers implements DataGenerator {
    @Override
    public List<Integer> generateData(int n) {
        List<Integer> arr = new ArrayList<>(n);
        for(int i = n; i > 0; i--){
            arr.add(i);
        }
        return arr;
    }
}
