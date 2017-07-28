package com.lz.zookeeper;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;

public class MyTestWatcher2 implements Watcher{
	
	 private CountDownLatch connectedLatch;  
	   
	 MyTestWatcher2(CountDownLatch connectedLatch) {  
         this.connectedLatch = connectedLatch;  
     }  

	@Override
	public void process(WatchedEvent event) {
		
		if (event.getState() == KeeperState.SyncConnected) {  
            connectedLatch.countDown();  
        } 
		
		System.out.println("hello zookeeper");
        System.out.println(String.format("hello event! type=%s, stat=%s, path=%s",event.getType(),event.getState(),event.getPath()));
		
		if(event.getType()==EventType.NodeDeleted){
			System.out.println("节点被删除！节点删除事件触发");
		}
		
		if(event.getType()==EventType.NodeChildrenChanged){
			System.out.println("修改节点的子节点信息");
		}
		
		if(event.getType()==EventType.NodeCreated){
			System.out.println("节点创建事件触发！");
		}
		
		if(event.getType()==EventType.NodeDataChanged){
			System.out.println("节点数据修改！");
		}
	}
	
}
