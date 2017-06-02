package com.lz.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class Test1 {
	
	public static void main(String[] args) throws IOException {
		String host = "192.168.87.133:2181";
		int sessionTimeOut = 30000;
		
		ZooKeeper zooKeeper = new ZooKeeper(host,sessionTimeOut,null);
		try {
			zooKeeper.create("/root", "root data".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			zooKeeper.setData("/root", "hello".getBytes(), -1);
		} catch (KeeperException | InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
