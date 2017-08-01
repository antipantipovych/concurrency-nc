package com.threads.nc;

/**
 * Created by Екатерина on 29.07.2017.
 */

import java.util.concurrent.*;

/**
 * Два способа создания потоков.
 *  Демонстрация недетерминированного порядка выполнения команд процессов
 */

public class Main {
    public static void main(String[] args) throws Exception{

        MyRunnable myRunnable= new MyRunnable();
        new Thread(myRunnable).start();

        MyThread myThread= new MyThread();
        myThread.start();

        MyThread myThread1= new MyThread();
        myThread1.start();

        System.out.println("Current Thread "+ Thread.currentThread().getName());
    }
}

class MyThread extends Thread{
    @Override
    public void run(){
       System.out.println("Thread "+ getName());
        for( int i = 0 ; i < 400 ; i++){
            System.out.println(Thread.currentThread().getName()+" ; i = " + i);
            //  в данном выводе возможно чередование потоков thread и thread1
        }
    }
}

class MyRunnable implements Runnable{
    @Override
    public void run(){

        System.out.println("Runnable "+ Thread.currentThread().getName());
    }
}
