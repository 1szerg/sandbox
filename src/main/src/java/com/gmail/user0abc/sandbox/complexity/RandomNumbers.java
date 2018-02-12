package com.gmail.user0abc.sandbox.complexity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Sergii Ivanov
 */
public class RandomNumbers implements DataGenerator {
    private long seed = System.currentTimeMillis();

    public RandomNumbers(long seed) {
        this.seed = seed;
    }

    @Override
    public List<Integer> generateData(int n) {
        Random r = new Random(seed);
        List<Integer> arr = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            arr.add(r.nextInt(n));
        }
        return arr;
    }
}
