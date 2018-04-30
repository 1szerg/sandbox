package com.gmail.user0abc.concurrency.active_object;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Solved_ActiveObject
{

    public static void main(String[] arg)
    {
        new Solved_ActiveObject().doDemo();
    }

    class TestSubject extends Subject
    {
        private BlockingQueue<Runnable> queue = new LinkedBlockingDeque<>();
        private Thread dispatcher;
        Subject parent = new Subject();
        boolean active;
        int callsCount = 0;
        int call1 = 0;
        int call2 = 0;

        @Override
        void doSomething()
        {
            queue.add(() -> {
                parent.doSomething();
                call1++;
            });
        }

        @Override
        void doSomethingElse()
        {
            queue.add(() -> {
                parent.doSomethingElse();
                call2++;
            });
        }

        @Override
        double getVal()
        {
            return parent.getVal();
        }

        public void activate()
        {
            active = true;
            dispatcher = new Thread(() -> {
                while (active) {
                    try {
                        queue.take().run();
                        callsCount++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            dispatcher.start();
        }

        public void deactivate()
        {
            queue.add(() -> active = false);
            try {
                dispatcher.join(1000);
                System.out.println("Exiting after " + callsCount + " calls: " + call1 + " / " + call2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    final Subject problem = new TestSubject();

    public void doDemo()
    {
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

}
