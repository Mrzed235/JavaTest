package com.opencc.threadtest.ThreadpoolTest;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class CuntomThreadFactory implements ThreadFactory {
    /**
     * 计数器
     */
    private final AtomicInteger i = new AtomicInteger(1);
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName("线程" + i.getAndIncrement()+ "号");
        return thread;
    }
}
