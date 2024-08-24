package com.opencc.threadtest.ProducerConsumerDemo;

public class Consumer implements Runnable{

    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println("消费者开始消耗整数....");
        for (int i = 1; i <= 10 ; i++) {
            try {
                System.out.println("消费者取走: "+ clerk.getProduct());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
