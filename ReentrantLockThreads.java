package com.threads.nc;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Екатерина on 30.07.2017.
 */

/** "сквозная синхронизация"*/

public class ReentrantLockThreads {
    public static void main(String[] args) throws Exception{
        Resource3 res= new Resource3();
        res.i=5;
        res.j=5;
        MyThread3 myThread1= new MyThread3(res);
        myThread1.start();
        MyThread3 myThread2= new MyThread3(res);
        myThread2.start();
        myThread1.join();
        myThread2.join();
        System.out.println(res.i +" "+ res.j);
    }
}
class MyThread3 extends Thread{
    Resource3 resource;

    MyThread3(Resource3 res){
        resource=res;
    }
    @Override
    public void run(){
        resource.incrementI();
        resource.incrementJ();
    }
}

class Resource3 {
    Lock lock = new ReentrantLock();
    int i;
    int j;
     public  void incrementI(){
         lock.lock();
         int i = this.i;
         if(Thread.currentThread().getName().equals("Thread-0")){
             Thread.yield();
         }
         i++;
         this.i= i;
         lock.unlock();
     }
    public  void incrementJ(){
        lock.lock();
        int j = this.j;
        if(Thread.currentThread().getName().equals("Thread-0")){
            Thread.yield();
        }
        j++;
        this.j= j;
        lock.unlock();
    }
}