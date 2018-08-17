package com.gmail.user0abc.sandbox.tests;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.gmail.user0abc.sandbox.Util.prn;

public class ShrinkArrayTest
{

    @Test
    public void shrinkArray(){
        int N = 100;
        List<Integer> arr = new ArrayList<>();
        for(int i = 0; i < N; i++){
            arr.add(i+1);
        }
        Integer[] barr = arr.toArray(new Integer[10]);
        System.out.println(barr.length);
    }

}
