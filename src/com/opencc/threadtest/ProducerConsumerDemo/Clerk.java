package com.opencc.threadtest.ProducerConsumerDemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 模拟消费者和生产者的关系
 */
public class Clerk {

    private int product = -1; //值为-1的时候表示没有产品

    private Lock lock = new ReentrantLock(); //new一个可重入锁
    private Condition ProducerCondition = lock.newCondition(); //new一个Producer condition对象
    private Condition ConsumerCondition = lock.newCondition(); //new一个Consumer condition对象

    public void setProduct(int product) throws InterruptedException {
        lock.lock();
        try {
            waitIfFull(); //判断是否有产品，如果有则进入wait，释放锁，让消费者线程来竞争锁，并且消费掉产品后再来继续生产产品
            this.product = product; //收货
            System.out.println("生产者设定： "+ this.product);
            ConsumerCondition.signal(); //通知消费者线程来消费
        } finally {
            lock.unlock();
        }
    }

    private void waitIfFull() throws InterruptedException {
        while (this.product != -1) {
            ProducerCondition.await(); //生产者集合等待
        }
    }

    public int getProduct() throws InterruptedException {
        lock.lock();
        try {
            waitIfEmpty(); //如果此时货物为空则wait，等待生产者生产好notify我再来继续后面的流程获取货物。
            int p = this.product;
            this.product = -1; //表示产品被取走，置为-1
            System.out.println("消费者取走产品：" + p);
            ProducerCondition.signal(); //通知等待集合中的线程，例如生产者来生产产品
            return p;
        } finally {
            lock.unlock();
        }
    }

    private void waitIfEmpty() throws InterruptedException {
        while (this.product == -1) {
            ConsumerCondition.await(); //消费者等待集合等待
        }
    }
}
