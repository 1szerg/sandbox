package com.gmail.user0abc.puzzles;

import org.junit.Test;

import java.util.Random;

public class RepeatingNumbers
{
    @Test
    public void findMostRepeatingNumberSmall()
    {
        int[] in = new int[]{1, 2, 3, 4, 5, 6, 9, 8, 7, 3};
        System.out.println(mostRepeatingBrute(in));
    }

    @Test
    public void findMostRepeatingNumberBig()
    {
        int[] cases = new int[]{100, 1000, 10000, 100000};
        for(int N : cases){
            long t = System.currentTimeMillis();
            int[] in = new int[N];
            Random r = new Random();
            for (int i = 0; i < N; i++) in[i] = r.nextInt(10);
            System.out.println(N+" - "+mostRepeatingBrute(in)+" in "+(System.currentTimeMillis()-t)+" ms");
       }
        for(int N : cases){
            long t = System.currentTimeMillis();
            int[] in = new int[N];
            Random r = new Random();
            for (int i = 0; i < N; i++) in[i] = r.nextInt(N);
            System.out.println(N+" - "+mostRepeatingBrute(in)+" in "+(System.currentTimeMillis()-t)+" ms");
        }
    }

    @Test
    public void findMostRepeatingNumberAllSame()
    {
        int[] in = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        System.out.println(mostRepeatingBrute(in));
    }

    @Test
    public void findMostRepeatingNumberSame()
    {
        int[] in = new int[]{1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6};
        System.out.println(mostRepeatingBrute(in));
    }

    private int mostRepeatingBrute(int[] arr)
    {
        int score = 1;
        int x = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[x] == arr[i]) score++;
        }
        for (int y = 1; y < arr.length; y++) {
            if (arr[x] == arr[y]) continue;
            int rival = 1;
            for (int z = y + 1; z < arr.length; z++) {
                if (arr[z] == arr[y]) rival++;
            }
            if (rival > score) {
                x = y;
                score = rival;
            }
        }
        return arr[x];
    }
}
