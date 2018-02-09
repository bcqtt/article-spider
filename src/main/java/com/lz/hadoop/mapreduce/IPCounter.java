package com.lz.hadoop.mapreduce;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class IPCounter {
	
	static final String IN_PUT = "F:\\accesslog\\access_log_globalsearch.gionee.com_2017080123";  
    static final String OUT_PUT = "F:\\accesslog\\output";  
      
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException, ClassNotFoundException {  
          
        Configuration conf = new Configuration();  
          
        //FileSystem fileSystem = FileSystem.get(new URI(IN_PUT), conf);  
          
        Path outPath = new Path(OUT_PUT);  
          
//        if(fileSystem.exists(outPath)){  
//            fileSystem.delete(outPath,true);  
//        }  
          
        Job job = new Job(conf, IPCounter.class.getSimpleName());  
        job.setJarByClass(IPCounter.class);  
          
      FileInputFormat.addInputPath(job, new Path(IN_PUT));  
        //FileInputFormat.setInputPaths(job, IN_PUT);  
          
        job.setMapperClass(IpMapper.class);  
        job.setReducerClass(IpReduce.class);  
          
        job.setOutputKeyClass(Text.class);  
        job.setOutputValueClass(LongWritable.class);  
          
        FileOutputFormat.setOutputPath(job, outPath);  
          
        job.waitForCompletion(true);  
          
    }  
      
      
    static class IpMapper extends Mapper<LongWritable, Text, Text, LongWritable>{  
          
        protected void map(LongWritable key, Text value, Context context) throws IOException ,InterruptedException {  
            System.out.println(value.toString());  
            String ip[] = value.toString().split("- -");  
            for (int i = 0; i < ip.length; i++) {  
                System.out.print(ip[0].toString());  
            }  
            System.out.println("------------------");  
            String ipStr = ip[0].toString().trim();  
            System.out.println(ipStr+"-----");  
            context.write(new Text(ipStr), new LongWritable(1));  
              
        };  
          
    }  
      
    static class IpReduce extends Reducer<Text,LongWritable,Text, LongWritable>{  
          
        protected void reduce(Text k2, Iterable<LongWritable> v2, Context context) throws java.io.IOException ,InterruptedException {  
            Long counts = 0L;  
            for (LongWritable v : v2) {  
                counts += v.get();  
            }  
            context.write(k2, new LongWritable(counts));  
        };  
          
    }

}
