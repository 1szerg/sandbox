package com.gmail.user0abc.sandbox.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Sergii Ivanov
 */
public class AutoboxingAndGenerics {
    static int round = 0;


    public static void main(String[] args){
        List<Integer> ints = new ArrayList<>();
        ints.add((Integer) randoms());
        ints.add((Integer) randoms());
        ints.add((Integer) randoms());
        ints.add((Integer) randoms());
        ints.add((Integer) randoms());
        ints.add((Integer) randoms());
        for(Object o: ints){
            System.out.print(String.valueOf(o)+", ");
        }
    }

    private static Object randoms() {
        switch (round){
            case 0: round++; return 2;
            case 1: round++; return "2";
        }
        return null;
    }

}
