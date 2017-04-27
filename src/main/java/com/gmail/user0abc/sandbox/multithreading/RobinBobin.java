package com.gmail.user0abc.sandbox.multithreading;

import static com.gmail.user0abc.sandbox.Util.prn;

/**
 * @author Sergii Ivanov
 */
public class RobinBobin
{

    public static void main(String[] args){
        new RobinBobin().test();
    }

    private void test()
    {
        final int N = 10;
        Thread[] pool = new Thread[N];
        for(int i = 0; i < N; i++){
            Thread t = new Thread(new rbWorker("rb "+i, this));
            pool[i] = t;
            t.start();
        }
        synchronized (this){
            this.notifyAll();
        }
    }

    class rbWorker implements Runnable{

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
            synchronized (monitor){
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    prn(label + " interrupted");
                }
                prn(label);
                monitor.notify();
            }
        }
    }

}
