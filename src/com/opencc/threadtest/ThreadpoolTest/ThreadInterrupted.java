package com.opencc.threadtest.ThreadpoolTest;

class Mytask implements Runnable {

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(3000);
                System.out.println(1);
            }catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(2);
                System.out.println("线程状态为：" + Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt();
                System.out.println("线程状态为：" + Thread.currentThread().isInterrupted());
            }
        }
        if (Thread.currentThread().isInterrupted()) {
            System.out.println("现在是被中断状态");
        }
        System.out.println("task is interrupted");
        System.out.println("线程状态为：" + Thread.currentThread().isInterrupted());
    }
}
public class ThreadInterrupted {
    public static void main(String[] args) throws InterruptedException {
//        System.out.println("初始中断状态：" + Thread.currentThread().isInterrupted());
//        Thread.currentThread().interrupt();
//        System.out.println("执行完interrupt方法后的中断状态：" + Thread.currentThread().isInterrupted());
//        System.out.println("首次调用interrupted方法返回结果：" + Thread.interrupted());
//        System.out.println("此时的中断状态：" + Thread.currentThread().isInterrupted());
//        System.out.println("第二次调用interrupted方法返回结果：" + Thread.interrupted());
//        System.out.println("此时的中断状态：" + Thread.currentThread().isInterrupted());
        Thread task =  new Thread(new Mytask());
        task.start();
        Thread.sleep(500);
        task.interrupt();
    }
}
