package com.threads.nc;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Екатерина on 30.07.2017.
 */
public class TryLockThreads {
    static Lock lock = new ReentrantLock();

    public static void main(String[] args)throws Exception{
        new MyThread4().start();
        new MyThread5().start();
    }

    static class MyThread4 extends Thread{
        public void run(){
            lock.lock();
            System.out.println(Thread.currentThread().getName()+ " start working");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+ " stop working");
            lock.unlock();
        }
    }

    static class MyThread5 extends Thread{
        public void run() {
            while (true) {
                if (lock.tryLock()) {
                    System.out.println(Thread.currentThread().getName() + " working");
                    break;
                } else {
                    System.out.println(Thread.currentThread().getName() + " waiting");
                }
            }
            System.out.println(Thread.currentThread().getName() + " stop working");

        }
    }
}
