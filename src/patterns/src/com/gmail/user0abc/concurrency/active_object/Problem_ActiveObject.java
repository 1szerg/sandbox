package com.gmail.user0abc.concurrency.active_object;

/*
The class is dangerous in a multithreading scenario because both methods can be called simultaneously,
so the value of val (which is not atomic—it's updated in multiple steps) could be undefined—a classic race condition.
You can, of course, use synchronization to solve this problem, which in this trivial case is easy.
But once the class becomes realistically complex, synchronization can become very difficult.
 */
public class Problem_ActiveObject
{

    public static void main(String[] args){
        new Problem_ActiveObject().demo();
    }

    void demo(){
        final Subject subject = new Subject();

        Thread t1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                for(int i = 0; i < 100000; i++) {
                    subject.doSomething();
                }
            }
        });

        Thread t2 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                for(int i = 0; i < 100000; i++){
                    subject.doSomethingElse();
                }
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
    }
}
