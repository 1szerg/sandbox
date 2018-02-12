package com.gmail.user0abc.sandbox.multithreading;/* $Id$
 * Created by sergii.ivanov on 4/23/2017.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.gmail.user0abc.sandbox.Util.prn;

public class CallableTest
{
    private static final int N = 10000;
    
    public static void main(String[] args){
        new CallableTest().doAnalysis();
    }

    private void doAnalysis()
    {
        Random r = new Random();
        long timer = System.currentTimeMillis();
        ExecutorService service = Executors.newFixedThreadPool(8);
        List<Future<Double>> results = new ArrayList<>(N);
        for(int i = 0; i < N; i++)
        {
            double a = r.nextDouble();
            double b = r.nextDouble();
            CallableTest.Worker w = new CallableTest.Worker().setValues(a, b);
            results.add(service.submit(w));
        }
        service.shutdown();

        int nulls = 0;
        int count = 0;
        for(Future<Double> res : results)
        {
            Double result = null;
            try
            {
                result = res.get();
            }
            catch(InterruptedException e)
            {
                prn("Interrupted! "+e.getLocalizedMessage());
            }
            catch(ExecutionException e)
            {
                prn("Execution fails! "+e.getLocalizedMessage());
            }
            count++;
            if(result == null)
                nulls++;
        }
        prn("[" + (System.currentTimeMillis() - timer) + "] " + nulls + " nulls in " + count + " of " + N + " size = "
                + results.size());
    }

    class Worker implements Callable<Double>, CallableTest.Calculator
    {
        double a;
        double b;
        private CallableTest.ResultHandler resultHandler;

        @Override
        public double calculate(double a, double b)
        {
            return a + b;
        }

        CallableTest.Worker setValues(double a, double b)
        {
            this.a = a;
            this.b = b;
            return this;
        }

        void setResultHandler(CallableTest.ResultHandler resultHandler)
        {
            this.resultHandler = resultHandler;
        }

        @Override
        public Double call() throws Exception
        {
            return this.calculate(a, b);
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
