package com.lz.art.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.lz.art.redis.RedisLock;

public class LockTest {
	//不加锁
	/*
    static class Outputer {
        public void output(String name) {
            for(int i=0; i<name.length(); i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }
    }
    */
    
	/*
    //使用java5中的锁
    static class Outputer{
        Lock lock = new ReentrantLock();
        public void output(String name) {
            //传统java加锁
            //synchronized (Outputer.class){
            lock.lock();
            try {
                for(int i=0; i<name.length(); i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
            }finally{
                //任何情况下都有释放锁
                lock.unlock();
            }  
            //}
        }
    }
    */
	
	//使用RedisLock
	static class Outputer {
	    //创建一个名为redisLock的RedisLock类型的锁
	    RedisLock redisLock = new RedisLock("redisLock");
	    public void output(String name) {
	        //上锁
	        redisLock.lock();
	        try {
	            for(int i=0; i<name.length(); i++) {
	                System.out.print(name.charAt(i));
	            }
	            System.out.println();
	        }finally{
	            //任何情况下都要释放锁
	            redisLock.unlock();
	        }  
	    }
	}
    
    public static void main(String[] args) {
        final Outputer output = new Outputer();
        //线程1打印zhangsan
        new Thread(new Runnable(){
            @Override
            public void run() {
                while(true) {
                     try{
                         Thread.sleep(1000);
                     }catch(InterruptedException e) {
                         e.printStackTrace();
                     }
                     output.output("张三疯");
                }  
            }
        }).start();
         
        //线程2打印lingsi
        new Thread(new Runnable(){
            @Override
            public void run() {
                while(true) {
                     try{
                         Thread.sleep(1000);
                     }catch(InterruptedException e) {
                         e.printStackTrace();
                     }
                     output.output("李四");
                }
            }
        }).start();
         
        //线程3打印wangwu
        new Thread(new Runnable(){
            @Override
            public void run() {
                while(true) {
                     try{
                         Thread.sleep(1000);
                     }catch(InterruptedException e) {
                         e.printStackTrace();
                     }
                     output.output("王五六七");
                }
            }
        }).start();
        
        //线程3打印zhaoliu
        new Thread(new Runnable(){
        	@Override
        	public void run() {
        		while(true) {
        			try{
        				Thread.sleep(1000);
        			}catch(InterruptedException e) {
        				e.printStackTrace();
        			}
        			output.output("赵框因");
        		}
        	}
        }).start();
    }
}
