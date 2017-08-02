package com.lz.hadoop.hdfs;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;

public class MultipleFileUpload {
	//声明两个从不同文件系统类型的静态变量
    private static FileSystem fs = null;
    private static FileSystem local = null;

      public static void main(String[] args) throws Exception {
         //指定在元数据目录的地址在linux环境下 
         String  srcPath="F:/accesslog/*";
         String  dstPath="hdfs://localhost:9000/accesslog/";
         //调用上传到HDFS
         listFile(srcPath, dstPath);
      }

      public static void listFile(String srcPath,String dstPath) throws Exception{
          //读取配置文件
          Configuration conf=new Configuration();
          //指定HDFS地址
          URI uri=new URI("hdfs://localhost:9000");
          fs = FileSystem.get(uri,conf);
          // 获取本地文件系统
          local=FileSystem.getLocal(conf);
          //获取文件目录
          FileStatus[] listFile=local.globStatus(new Path(srcPath));
          //获取文件路径
          Path[]  listPath=FileUtil.stat2Paths(listFile);
          //输出文件路径
          Path outPath=new Path(dstPath);
          //循环遍历所有文件路径
          for(Path p:listPath){
        	  System.out.println("上传文件：" + p.toUri().getPath());
              fs.copyFromLocalFile(p, outPath);
          }

      }
      
}
