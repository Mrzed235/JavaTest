package com.opencc.threadtest;

public class SyncThreadTest implements Runnable {

    private static int count;

    public SyncThreadTest() {
        count = 0;
    }

    @Override
    public void run() {
        synchronized (SyncThreadTest.class) {
            while (true) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new SyncThreadTest(), "SyncThread1");
        Thread thread2 = new Thread(new SyncThreadTest(), "SyncThread2");
        thread1.start();
        thread2.start();
    }
}


