package com.gmail.user0abc.concurrency.active_object;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/*
Add queue to control calls to sensitive methods. Put all calls into queue and run one-by-one.
This spoils logic that expects old behaviour (see #spoiledLogic())
 */
public class Solved_ActiveObject
{

    public static void main(String[] arg)
    {
        new Solved_ActiveObject().doDemo();
    }

    class TestSubject extends Subject
    {
        private BlockingQueue<Runnable> callsQueue = new LinkedBlockingDeque<>();
        private Thread dispatcherThread;
        private boolean activeFlag;
        private int processedCallsCount = 0;
        private int doSomethingCount = 0;
        private int doSomethingElseCount = 0;

        @Override
        public void doSomething()
        {
            callsQueue.add(() -> {
                super.doSomething();
                doSomethingCount++;
            });
        }

        @Override
        public void doSomethingElse()
        {
            callsQueue.add(() -> {
                super.doSomethingElse();
                doSomethingElseCount++;
            });
        }

        public void activate()
        {
            activeFlag = true;
            dispatcherThread = new Thread(() -> {
                while (activeFlag) {
                    try {
                        callsQueue.take().run();
                        processedCallsCount++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            dispatcherThread.start();
        }

        public void deactivate()
        {
            callsQueue.add(() -> activeFlag = false);
            try {
                dispatcherThread.join(1000);
                System.out.println("Verify. Exiting after " + processedCallsCount + " calls: doSomethingCount = " + doSomethingCount + " / doSomethingElseCount = " + doSomethingElseCount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void doDemo()
    {
        System.out.println("Solved Active Object pattern: decouples method execution from method invocation");
        final Subject subject = new TestSubject();

        ((TestSubject) subject).activate();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                subject.doSomething();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                subject.doSomethingElse();
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join(1000);
            t2.join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ((TestSubject) subject).deactivate();

    }

    // after converting into active object some code won't work
    public void spoiledLogic(){
        // this code was working before pattern implementation...
        Subject oldSubject = new Subject();
        oldSubject.doSomething();
        assert(oldSubject.getVal()==1d);

        // ... and then its broken by pattern implementation
        Subject newSubject = new TestSubject();
        newSubject.doSomething();
        assert(newSubject.getVal()==1d);
    }

}
