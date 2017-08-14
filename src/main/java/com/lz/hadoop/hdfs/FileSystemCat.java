package com.lz.hadoop.hdfs;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class FileSystemCat {
	public static void main(String[] args) throws IOException {
		String uri = "hdfs://localhost/testDir/note.txt";
		
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(uri),conf);
		
		InputStream in = null;
		try {
			in = fs.open(new Path(uri));
			//String result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
			//System.out.println(result);
			IOUtils.copyBytes(in, System.out, 4096,false);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			IOUtils.closeStream(in);
		}
		
	}
}
