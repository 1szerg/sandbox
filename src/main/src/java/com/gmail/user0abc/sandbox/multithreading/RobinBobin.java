package com.gmail.user0abc.sandbox.multithreading;

import static com.gmail.user0abc.sandbox.Util.line;
import static com.gmail.user0abc.sandbox.Util.prn;
import static com.gmail.user0abc.sandbox.multithreading.RobinBobin.run_count;

/**
 * @author Sergii Ivanov
 */
public class RobinBobin
{
    public static int run_count = 0;
    public static void main(String[] args)
    {
        new RobinBobin().test();
    }

    private void test()
    {
        final int N = 10;
        Thread[] pool = new Thread[N];
        for(int i = 0; i < N; i++)
        {
            Thread t = new Thread(new rbWorker(String.valueOf(i), this));
            pool[i] = t;
            t.start();
        }
        while(true)
        {
            Object self = new Object();
            synchronized(self)
            {
                try
                {
                    self.wait(1000L);
                }
                catch(InterruptedException e)
                {
                    prn("ouch");
                }
            }
            synchronized(this)
            {
                prn(" < "+run_count+" > ");
                run_count = 0;
                this.notify();
            }
        }
    }

}

class rbWorker implements Runnable
{

    Object monitor;
    String label;

    public rbWorker(String label, Object monitor)
    {
        this.label = label;
        this.monitor = monitor;
    }

    @Override
    public void run()
    {
        while(true)
        {
            synchronized(monitor)
            {
                try
                {
                    monitor.wait();
                }
                catch(InterruptedException e)
                {
                    prn(label + " interrupted");
                }
                line(label);
                run_count ++;
                monitor.notify();
            }
        }
    }
}
