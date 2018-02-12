package com.gmail.user0abc.sandbox.multithreading;

import static com.gmail.user0abc.sandbox.Util.prn;

/**
 * @author Sergii Ivanov
 */
public class TimeLimit {

    public static void main(String[] args) {
        new TimeLimit().testTimeout();
    }

    private void testTimeout() {

        final Object monitor = new Object();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (monitor) {
                    prn(" ->morning work");
                    try {
                        monitor.wait(10000);
                    } catch (InterruptedException e) {
                        prn(" ->not sleeping!");
                    }
                    prn(" ->ready to work!");
                    while (true) {
                        try {
                            monitor.wait(10);
                        } catch (InterruptedException e) {
                            prn(" ->working!..");
                            break;
                        }
                    }
                    prn(" ->rest of the day");
                }
            }
        });
        prn("do your work");
        t.start();
        synchronized (this) {
            try {
                wait(1000);
            } catch (InterruptedException e) {
                prn("ouch");
            }
        }
        prn("wake up!");
        t.interrupt();
        synchronized (monitor) {
            monitor.notify();
        }
        synchronized (this) {
            try {
                wait(1000);
            } catch (InterruptedException e) {
                prn("ouch2");
            }
        }
        prn("hey you!");
        t.interrupt();
        synchronized (this) {
            try {
                wait(1000);
            } catch (InterruptedException e) {
                prn("ouch2");
            }
        }
        prn("stop!");
        try {
            t.join();
        } catch (InterruptedException e) {
            prn("join interrupted");
        }
        prn("day end");
    }

}
