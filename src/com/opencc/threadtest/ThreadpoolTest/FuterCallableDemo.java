package com.opencc.threadtest.ThreadpoolTest;

import java.sql.Time;
import java.util.concurrent.*;

public class FuterCallableDemo {
    static long fibonacci(long n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

//    public static void main(String[] args) throws Exception {
//        FutureTask<Long> the30th =  new FutureTask<>(() -> fibonacci(30));
//        System.out.println("老板我要30个费式数，待会来拿....");
//        new Thread(the30th).start();
//        while (!the30th.isDone()) {
//            System.out.println("忙别的事去了");
//        }
//        System.out.printf("第30个费式数：%d%n", the30th.get());
//    }

    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newCachedThreadPool();
        Future<Long> the30th =  service.submit(()->fibonacci(30));
        System.out.println("老板我要30个费式数，待会来拿....");
        while (!the30th.isDone()) {
            System.out.println("忙别的事去了");
        }
        System.out.printf("第30个费式数：%d%n", the30th.get());
        service.shutdown();
    }
}
