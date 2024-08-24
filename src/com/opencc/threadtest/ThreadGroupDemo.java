package com.opencc.threadtest;

public class ThreadGroupDemo {
    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("group") {
            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                System.out.printf("%s : %s%n", thread.getName(), throwable.getMessage());
            }
        };

        new Thread(group, new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException("测试异常");

            }
        }).start();
    }
}
