package com.threads.nc;

/**
 * Created by Екатерина on 30.07.2017.
 */

/** демонстрация синхронизированных методов и синхронизированных блоков, в том числе статических классов.
 * При отсутствии синхронизации возможны разные результаты выполнения программы.
*/
public class SynchronizedThreads {
    public static void main(String [] args) throws Exception{
        Resource res= new Resource();
        res.i=5;
        StaticResource.i=4;
        MyThread1 myThread1= new MyThread1(res);
        myThread1.start();
        MyThread1 myThread12= new MyThread1(res);
        myThread12.start();
        myThread1.join();
        myThread12.join();
        System.out.println("Resource : "+res.i);
        System.out.println("Static Resource : "+StaticResource.i);
    }
}
class MyThread1 extends Thread{
    Resource resource;

    MyThread1(Resource res){
        resource=res;
    }

    @Override
    public void run(){
        resource.increment();
        StaticResource.increment();
    }
}

class Resource {
    int i;

     public  void increment() {
         synchronized (this) {
             int i = this.i;
             i++;
             this.i = i;
         }
     }

    //также можно заменить синхронизацию блоком на синхронизированный метод:
    // ( различия имеются на уровне байт-кода)
//    public  synchronized void increment() {
//            int i = this.i;
//            i++;
//            this.i = i;
//
//    }
}

class StaticResource {
    static int i;

    // синхронизация блоками в статических методах в качестве монитора использует сам класс
    public static void increment(){
        synchronized (StaticResource.class) {
            int i = StaticResource.i;
            i++;
            StaticResource.i = i;
        }
    }

    //также можно заменить синхронизацию блоком на синхронизированный метод:
    // ( различия имеются на уровне байт-кода)
//    public static synchronized void increment(){
//            int i = StaticResource.i;
//            i++;
//            StaticResource.i = i;
//    }
}


