package com.gmail.user0abc.sandbox.multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Sergii Ivanov
 */
public class LockTest {

    private Lock lock = new ReentrantLock(true);

    public static void main(String[] args){
        new LockTest().doLockTest();
    }

    private void doLockTest() {
        for(int i = 0; i < 5; i++){
            final int id = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j = 0; j < 3; j++){
                        System.out.print(">"+id+" ");
                    }
                }
            }).start();
        }
    }
}
