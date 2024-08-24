package com.opencc.threadtest.ProducerConsumerDemo;

public class ProducerConsumerTest {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        new Thread(new Producer(clerk)).start();
        new Thread(new Consumer(clerk)).start();
    }
}
