package com.lz.thread.pool;

import java.util.concurrent.CountDownLatch;

public class LostUpdate implements Runnable {
	
	private CountDownLatch countDown;
    public LostUpdate(CountDownLatch countDown){
        this.countDown = countDown;
    }

	@Override
	public void run() {
		for(int i=0;i<10000;i++) {
			System.out.println("输出：" + i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//表示一次任务完成
		countDown.countDown();

	}

}
