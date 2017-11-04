package com.lz.hadoop;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

public class DomainCounter
{
	
	static final String IN_PUT = "C:\\Users\\gionee\\Desktop\\上线包\\ssm.log";  
    static final String OUT_PUT = "C:\\Users\\gionee\\Desktop\\上线包\\domain"; 
    
    //匹配域名的正则表达式
    static String pattern = ",?((\\w+?\\.)+?(com|net))";
      
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException, ClassNotFoundException {  
          
        Configuration conf = new Configuration();  
          
        //FileSystem fileSystem = FileSystem.get(new URI(IN_PUT), conf);  
          
        Path outPath = new Path(OUT_PUT);  
          
//        if(fileSystem.exists(outPath)){  
//            fileSystem.delete(outPath,true);  
//        }  
          
        Job job = new Job(conf, DomainCounter.class.getSimpleName());  
        job.setJarByClass(DomainCounter.class);  
          
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
            String textline = value.toString();
            if(textline.contains("重定向的URL")){
            	Pattern p = Pattern.compile(pattern);
            	Matcher m = p.matcher(textline);
            	if(m.find()){
            		//匹配结果
                    String domain = m.group();
                    context.write(new Text(domain), new LongWritable(1));  
            	}
            }
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
