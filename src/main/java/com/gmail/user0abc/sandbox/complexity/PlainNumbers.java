package com.gmail.user0abc.sandbox.complexity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergii Ivanov
 */
public class PlainNumbers implements DataGenerator {
    @Override
    public List generateData(int n) {
        List<Integer> arr = new ArrayList<>(n);
        for(int i = 0; i < n; i++){
            arr.add(i);
        }
        return arr;
    }
}
