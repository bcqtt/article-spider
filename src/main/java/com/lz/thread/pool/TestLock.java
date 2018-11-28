package com.lz.thread.pool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestLock {
	public static void main(String[] args) throws InterruptedException {
        //创建线程池，里面有10个线程，共执行100次+1操作
        final int THREAD_COUNT=10;
        final int RUN_TIME=100;
        
        ExecutorService threadPool=Executors.newFixedThreadPool(THREAD_COUNT);
        //用CountDownLatch保证主线程等待所有任务完成
        CountDownLatch count=new CountDownLatch(RUN_TIME);
        
        for(int i=0;i<RUN_TIME;i++)
            threadPool.execute(new LostUpdate(count));
        
        threadPool.shutdown();
        count.await();
        //提示所有任务执行完
        System.out.println("finish");
    }
}
