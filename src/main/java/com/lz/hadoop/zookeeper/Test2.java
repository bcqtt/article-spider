package com.lz.hadoop.zookeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooKeeper.States;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.data.Stat;

public class Test2 {
	
	public static String host = "192.168.87.133:2181";
	public static int sessionTimeOut = 30000;
	public static String root = "/root";
	
	public static Test2 test;
	public static ZooKeeper zookeeper = null;
	
	public static void waitUntilConnected(ZooKeeper zooKeeper, CountDownLatch connectedLatch) {  
        if (States.CONNECTING == zooKeeper.getState()) {  
            try {  
                connectedLatch.await();  
            } catch (InterruptedException e) {  
                throw new IllegalStateException(e);  
            }  
        }  
    }  
	
	static public Test2 getInstance(){  
        if(test == null){  
        	test = new Test2();  
        }  
        return test;  
    } 
	
	public boolean init(String hostports, int times){  
        try{  
            CountDownLatch connectedLatch = new CountDownLatch(1);  
            Watcher watcher = new MyTestWatcher2(connectedLatch);  
            zookeeper = new ZooKeeper(hostports, times, watcher);  
            waitUntilConnected(zookeeper, connectedLatch);  
        }  
        catch(Exception e){  
            System.out.println(e);  
            return false;  
        }  
        return true;  
    }
	
	public static void createZnode() throws IOException, KeeperException, InterruptedException{
		boolean flag = Test2.getInstance().init(host, 20000);
		if(flag){
			Stat stat = zookeeper.exists(root, false);
			if(stat == null){
				try {
					zookeeper.create(root, "root data".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
					zookeeper.setData(root, "hello".getBytes(), -1);
					Stat st = new Stat();
					byte[] data = zookeeper.getData("/root", false, stat);
					System.out.println("当前节点状态信息：" + st.toString());
				} catch (KeeperException | InterruptedException e) {
					e.printStackTrace();
				}
			}else{
				System.out.println("节点存在！");
			}
		}
	}
	
	public static void delZnode() throws IOException{
		boolean flag = Test2.getInstance().init(host, 20000);
		if(flag){
			try {
				if(zookeeper.exists(root, false)!=null){
					zookeeper.delete("/root", -1);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (KeeperException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		try {
			createZnode();
		} catch (KeeperException | InterruptedException e) {
			e.printStackTrace();
		}
		//delZnode();
		
	}
	
}
