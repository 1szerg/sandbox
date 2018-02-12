package com.gmail.user0abc.sandbox.multithreading;/* $Id$
 * Created by sergii.ivanov on 4/26/2017.
 */

import static com.gmail.user0abc.sandbox.Util.prn;

public class StackSize
{
    static long count = 0;
    public static void main(String[] arg){
        try{
            incCount1();
        }catch(StackOverflowError err){}
        prn("count1 = "+count);
        count = 0;
        try{
            incCount2();
        }catch(StackOverflowError err){}
        prn("count2 = "+count);
        count = 0;
        try{
            incCount3();
        }catch(StackOverflowError err){}
        prn("count3 = "+count);
    }

    private static void incCount1()
    {
        count++;
        incCount1();
    }

    private static void incCount2()
    {
        count++;
        long d1 = 0l;
        long d2 = 0l;
        long d3 = 0l;
        long d4 = 0l;
        long d5 = 0l;
        long d6 = 0l;
        long d7 = 0l;
        long d8 = 0l;
        long d9 = 0l;
        long dA = 0l;
        incCount2();
    }

    private static void incCount3()
    {
        count++;
        long[] arr = {0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l};
        incCount3();
    }

}
