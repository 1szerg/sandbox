package com.gmail.user0abc.sandbox.multithreading;/* $Id$
 * Created by sergii.ivanov on 4/23/2017.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.gmail.user0abc.sandbox.Util.prn;

public class MultiThreadingTest
{
    private static final int N = 10000;

    public static void main(String[] arg)
    {
        new MultiThreadingTest().threadAnalysis();
    }

    private void threadAnalysis()
    {
        Random r = new Random();
        long timer = System.currentTimeMillis();
        List<Thread> pool = new ArrayList<>(N);
        //List<Worker> workers = new ArrayList<>(N);
        final List<Double> results = new ArrayList<>(N);
        for(int i = 0; i < N; i++)
        {
            double a = r.nextDouble();
            double b = r.nextDouble();
            Worker w = new Worker().setValues(a, b);
            w.setResultHandler(new ResultHandler()
            {
                @Override
                public void saveResult(double res)
                {
                    synchronized(results)
                    {
                        results.add(res);
                    }
                }
            });
            //            workers.add(w);
            Thread t = new Thread(w);
            pool.add(t);
            t.start();
        }
        for(Thread t : pool)
        {
            try
            {
                t.join();
            }
            catch(InterruptedException e)
            {
                prn("Interrupted!");
            }
        }
        int nulls = 0;
        int count = 0;
        for(Double res : results)
        {
            count++;
            if(res == null)
                nulls++;
        }
        prn("[" + (System.currentTimeMillis() - timer) + "] " + nulls + " nulls in " + count + " of " + N + " size = "
                + results.size());
    }

    class Worker implements Runnable, Calculator
    {
        double a;
        double b;
        private ResultHandler resultHandler;

        @Override
        public double calculate(double a, double b)
        {
            return a + b;
        }

        Worker setValues(double a, double b)
        {
            this.a = a;
            this.b = b;
            return this;
        }

        @Override
        public void run()
        {
            resultHandler.saveResult(this.calculate(a, b));
        }

        void setResultHandler(ResultHandler resultHandler)
        {
            this.resultHandler = resultHandler;
        }
    }

    interface Calculator
    {
        double calculate(double a, double b);
    }

    interface ResultHandler
    {
        void saveResult(double res);
    }
}
