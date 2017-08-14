package com.lz.hadoop.hdfs;

import java.io.InputStream;
import java.net.URL;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;

public class UrlCat {
	
	static{
		URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
	}
	
	public static void main(String[] args){
		InputStream in = null;
		try {
			in = new URL("hdfs://localhost/testDir/note.txt").openStream();
			IOUtils.copyBytes(in, System.out, 4096,false);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			IOUtils.closeStream(in);
		}
	}

}
