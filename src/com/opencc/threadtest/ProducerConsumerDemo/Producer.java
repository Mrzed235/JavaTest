package com.opencc.threadtest.ProducerConsumerDemo;

public class Producer implements Runnable{
    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println("生产者开始生产整数.....");
        for (int i = 1; i <= 10 ; i++) {
            try {
                clerk.setProduct(i); //将产品交给店员
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
