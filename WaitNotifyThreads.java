package com.threads.nc;

/**
 * Created by Екатерина on 30.07.2017.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

//пример использования wait и notify.
//Поток Man считывает с консоли строку и вносит ее в массив и уведомляет об этом Machine
//Machine ждет уведомления и выводит строку обратно на консоль
//Методы wait и notify должны быть внутри синхронизированного блока.

public class WaitNotifyThreads {

    //лист строк, с которым работает Man и Machine, выступает в качестве монитора синхронизации
    static List<String> strings = Collections.synchronizedList(new ArrayList<String>());

    public static void main(String[] args) throws Exception {
        new Man().start();
        new Machine().start();
    }

    static class Man extends Thread {

        public void run() {
            Scanner scanner= new Scanner(System.in);
            while (true) {
                synchronized (strings) {
                    // Man внес строку в список
                    strings.add(scanner.nextLine());
                    // Man уведомил Machine
                    strings.notify();
                }
                try{
                    Thread.sleep(100);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

        }
    }

    static class Machine extends Thread {

        public void run() {
            while (true){
                synchronized (strings) {
                   try{
                       //Machine ждет уведомления
                       strings.wait();
                   }
                   catch(InterruptedException e){
                       e.printStackTrace();
                   }
                    //выводит на экран
                    System.out.println(strings.remove(0));
                }

            }
        }
    }
}