package com.opencc.threadtest;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 百度面试题，两个线程交替打印1~100，考察点：线程通信，notify和wait
 */
class NumberPrint implements Runnable {

    private int num = 0; //定义自增量初始值

    @Override
    public void run() {
        while (true) {
            synchronized (this) {//锁的是对象，两个线程交替打印但是使用的是同一个继承Runnable的对象。
                notify();
                if (num < 100) {
                    num++; //先自增，从1 开始打印
                    System.out.println(Thread.currentThread().getName() + "=====" + num);
                }else {
                    break;
                }

                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

public class TestPrintNumer {
    public static void main(String[] args) {
        NumberPrint numberPrint = new NumberPrint();
        Thread thread1 = new Thread(numberPrint, "Thread--1");
        Thread thread2 = new Thread(numberPrint, "Thread--2"); //synchorized锁定的是同一个对象实例，两个线程相互竞争锁，
        // 一个线程执行完打印完整个代码块之后wait进入到阻塞态，通知另一个线程notify，让他继续执行，等待另一个线程执行完wait后释放锁之后通知自己来继续操作，相互交替
        thread1.start();
        thread2.start();
    }
}
