package com.lz.hadoop;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.lz.hadoop.WordCount.Reduce;

public class UrlCount extends Configured implements Tool{
	
	public static class Map extends Mapper<LongWritable, Text, Text, IntWritable> {
		private final static IntWritable one = new IntWritable(1);
		private Text sqlStr = new Text();
		
		public void map(LongWritable key, Text value, Context context) throws IOException,InterruptedException{
			String line = value.toString();
			StringTokenizer tokenizer = new StringTokenizer(line," - ");
			while(tokenizer.hasMoreTokens()){
				sqlStr.set(tokenizer.nextToken());
				if(sqlStr.toString().contains("重定向的URL")){
					context.write(sqlStr, one);
				}else{
					continue;
				}
			}
		}
	}
	
	public static class Reduce extends Reducer<Text, IntWritable, Text, IntWritable>{
		public void reduce(Text key,Iterable<IntWritable> values,Context context) throws IOException, InterruptedException{
			int sum = 0;
			for(IntWritable val : values){
				sum += val.get();
			}
			context.write(key, new IntWritable(sum));
		}
	}
	

	@Override
	public int run(String[] args) throws Exception {
		Job job = new Job(getConf());
		job.setJarByClass(UrlCount.class);
		job.setJobName("wordcount-new");
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		job.setMapperClass(Map.class);
		job.setReducerClass(Reducer.class);
		job.setCombinerClass(Reduce.class);  //本地合并数据。如果不使用则不会合并数据，直接将结果输出，其中包含很多重复数据
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		FileInputFormat.setInputPaths(job, new Path("C:\\Users\\gionee\\Desktop\\上线包\\ssm.log"));
		FileOutputFormat.setOutputPath(job, new Path("C:\\Users\\gionee\\Desktop\\上线包\\result"));
		
		boolean success = job.waitForCompletion(true);
		
		return success?0:1;
	}
	
	public static void main(String args[]) throws Exception {
		int ret = ToolRunner.run(new UrlCount(), args);
		System.exit(ret);
	}
	

}
