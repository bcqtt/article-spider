package com.lz.hadoop.zookeeper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import com.lz.util.SerializeUtil;

public class Test1 {
	
	public static String host = "192.168.87.132:2181";
	public static int sessionTimeOut = 30000;
	public static String root = "/zktest";
	
	public static void createZnode() throws IOException, KeeperException, InterruptedException{
		ZooKeeper zooKeeper = new ZooKeeper(host,sessionTimeOut,new MyTestWatcher());
		Stat stat = zooKeeper.exists(root, false);
		if(stat == null){
			try {
				zooKeeper.create(root, "zktestData".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
				zooKeeper.setData(root, "这个节点数据".getBytes(), -1);
				Stat st = new Stat();
				byte[] data = zooKeeper.getData(root, false, stat);
				System.out.println("获取到的节点数据：" + SerializeUtil.unserializeForList(data));
			} catch (KeeperException | InterruptedException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("节点存在！");
		}
	}
	
	public static void delZnode() throws IOException{
		ZooKeeper zooKeeper = new ZooKeeper(host,sessionTimeOut,new MyTestWatcher());
		try {
			if(zooKeeper.exists(root, false)!=null){
				zooKeeper.delete(root, -1);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (KeeperException e) {
			e.printStackTrace();
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
