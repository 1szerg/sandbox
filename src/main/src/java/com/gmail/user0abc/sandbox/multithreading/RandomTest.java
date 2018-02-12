package com.gmail.user0abc.sandbox.multithreading;/* $Id$
 * Created by sergii.ivanov on 4/23/2017.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static com.gmail.user0abc.sandbox.Util.prn;

public class RandomTest
{
    static int N = 100000;
    static int MAX = 100000;

    public static void main(String[] args)
    {
        while(N <= MAX)
        {
            prn("Test with " + N + " iterations");
            new RandomTest().doTest("simpleLoop", () -> new RandomTest().simpleLoop());
            new RandomTest().doTest("simpleCallable", () -> new RandomTest().simpleCallable());
            new RandomTest().doTest("simpleRunnable", () -> new RandomTest().simpleRunnable());
            N = N * 10;
        }
    }

    void doTest(String name, Testee testee){
        long t = System.currentTimeMillis();
        testee.run();
        long t2 = System.currentTimeMillis();
        Float operationsPerMs = new Float(N / Math.max(1,t2-t));
        prn(" "+name+" - "+(System.currentTimeMillis()-t)+" ms, "+operationsPerMs.intValue()+" op/ms");
    }

    private void simpleCallable()
    {
        ExecutorService service = Executors.newFixedThreadPool(8);
        for(int i = 0; i < N; i++)
        {
            Future<Double> res = service.submit(new Callable<Double>()
            {
                @Override
                public Double call() throws Exception
                {
                    return new Calculator().calculate();
                }
            });

        }
        service.shutdown();
        try
        {
            service.awaitTermination(120l, TimeUnit.SECONDS);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    private void simpleRunnable()
    {
        final int maxThreads = 8;
        ThreadGroup group = new ThreadGroup("pool");
        List<Thread> pool = new ArrayList<>(N);
        for(int i = 0; i < N; i++)
        {
            while(group.activeCount() > maxThreads){
                for(Thread t: pool)
                {
                    if(t != null && t.isAlive()){
                        try
                        {
                            t.join();
                            //pool.remove(t);
                        }
                        catch(InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }
            Thread t = new Thread(group, new Runnable()
            {
                @Override
                public void run()
                {
                    new Calculator().calculate();
                }
            },"t " + i);
            pool.add(t);
            t.start();
        }
    }

    private void simpleLoop()
    {
        for(int i = 0; i < N; i++)
        {
            new Calculator().calculate();
        }
    }

    class Calculator{
        double calculate(){
            double res = 0d;
            for(int i = 0; i < 1000; i++) res = res + Math.random() * Math.random();
            return res;
        }
    }

    interface Testee
    {
        void run();
    }
}
