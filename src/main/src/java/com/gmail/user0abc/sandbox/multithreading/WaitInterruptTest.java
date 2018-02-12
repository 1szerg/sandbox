package com.gmail.user0abc.sandbox.multithreading;/* $Id$
 * Created by sergii.ivanov on 4/24/2017.
 */

import java.util.Arrays;
import java.util.Random;

import static com.gmail.user0abc.sandbox.Util.prn;

public class WaitInterruptTest
{
    public static void main(String[] args){
        new WaitInterruptTest().doTest();
    }

    private void doTest()
    {
        Provider p = new Provider();
        p.start();
        synchronized(p){
            try{
                p.wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            prn("array is "+ p.box[p.box.length-1]+" at "+p.i);
        }
    }

    class Provider extends Thread{
        Random r = new Random();
        int i;
        Integer[] box = new Integer[100];
        @Override
        public void run()
        {
            synchronized(this)
            {
                for(i = 0; i < box.length; i++)
                {
                    box[i] = r.nextInt(100000);
                    try
                    {
                        wait(10);
                    }
                    catch(InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                notify();
            }
        }
    }
}
