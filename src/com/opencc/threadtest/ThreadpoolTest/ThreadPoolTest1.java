package com.opencc.threadtest.ThreadpoolTest;

import java.util.concurrent.*;

public class ThreadPoolTest1 {
    public static void main(String[] args) {
        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>(10);
        BlockingQueue<Runnable> blockingQueue2 = new ArrayBlockingQueue<>(20);

        /**
         * 最好使用原生方式创建线程池参数分别为：
         * corePoolSize -要保留在池中的线程数，即使它们是空闲的，除非设置了allowCoreThreadTimeOut
         * maximumPoolSize -允许在池中的最大线程数
         * keepAliveTime -当线程数大于核心时，这是多余的空闲线程将在终止前等待新任务的最大时间。
         * unit——keepAliveTime参数的时间单位。
         * workQueue——在执行任务之前用来保存任务的队列。此队列将仅保存由execute方法提交的可运行任务。
         * threadFactory——当执行器创建一个新的线程处理程序时使用的工厂——当执行因为线程边界和队列容量达到而被阻塞时使用的处理程序
         *
         */
        //核心线程数10，非核心线程数15加起来为25，非核心线程10秒没任务十秒后死掉。
        ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 25, 10,
                TimeUnit.SECONDS, blockingQueue2, new CuntomThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        // 阿里巴巴java开发手册中不建议使用newSingleThreadExecutor、newCachedThreadPool和newFixedThreadPool，
        // 原因是newSingleThreadExecutor和newFixedThreadPool，其内部都会使用BlockingQueue<Runnable>去存储任务，其构造函数在设置队列容量时为Integer.MAX_VALUE，可能堆积大量请求，从而导致OOM。
        // newCachedThreadPool内部可能会创建Integer.MAX_VALUE个线程，也可能会导致OOM


        //核心线程数和最大线程数是一样的，那说明都是核心线程数，存活时间为0，不会被销毁，任务队列为Integer.MAX_VALUE，可能会导致OOM
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        //核心线程数和最大线程数都是1，只有一个线程且是核心线程数目为1，存活时间为0，不会被销毁，任务队列为Integer.MAX_VALUE个线程，也可能会导致OOM
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        //核心线程数为0，最大线程数为Integer.MAX_VALUE，全部是非核心线程，60秒时间一到就会销毁，但是这样会倒是OOM，也不推荐
        ExecutorService executorService3 = Executors.newCachedThreadPool();
        MyTask2 task1 =  new MyTask2();
        MyTask2 task2 =  new MyTask2();
        MyTask2 task3 =  new MyTask2();
        pool.execute(task1);//execute是无返回结果的任务
        pool.execute(task2);
        pool.execute(task3);
        pool.shutdown();

        Future<?> submit = executorService.submit(task1);

    }
}

class MyTask2 implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
