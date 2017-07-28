package com.lz.hadoop;

import java.io.IOException;
import java.util.Iterator;
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

public class ScoreCalculate extends Configured implements Tool {
	
	public static class Map extends Mapper<LongWritable,Text,Text,IntWritable> {
		public void map(LongWritable key ,Text value,Context context) throws IOException, InterruptedException{
			String line = value.toString(); //将输入的纯文本数据转化为String
			System.out.println(line);
			
			//将输入的数据首先按行进行分割
			StringTokenizer tokenizerArticle = new StringTokenizer(line,"\n");
			while(tokenizerArticle.hasMoreTokens()){
				StringTokenizer tokenizerLine = new StringTokenizer(tokenizerArticle.nextToken());
				String strName = tokenizerLine.nextToken(); //学生姓名部分
				String strScore = tokenizerLine.nextToken();//成绩部分
				
				Text name = new Text(strName);             //name of student
				int scoreInt = Integer.parseInt(strScore); //score of student
				context.write(name, new IntWritable(scoreInt));  //输出姓名和成绩
			}
		}
	}
	
	public static class Reduce extends Reducer<Text,IntWritable,Text,IntWritable>{
		public void reduce(Text key,Iterable<IntWritable> values,Context context) throws IOException, InterruptedException{
			int sum = 0;
			int count = 0;
			Iterator<IntWritable> iterator = values.iterator();
			while(iterator.hasNext()){
				sum += iterator.next().get();  //计算总分
				count++;  //统计总的科目数
			}
			int average = sum/count;  //计算平均成绩
			context.write(key, new IntWritable(average));
		}
	}

	@Override
	public int run(String[] args) throws Exception {
		Job job = new Job(getConf());
		job.setJarByClass(ScoreCalculate.class);
		job.setJobName("ScoreCalculate");
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		job.setMapperClass(Map.class);
		job.setReducerClass(Reducer.class);
		job.setCombinerClass(Reduce.class);  //本地合并数据。如果不使用则不会合并数据，直接将结果输出，其中包含很多重复数据
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		FileInputFormat.setInputPaths(job, new Path("F:\\test\\score.txt"));
		FileOutputFormat.setOutputPath(job, new Path("F:\\test\\output4"));
		
		boolean success = job.waitForCompletion(true);
		
		return success?0:1;
	}
	
	public static void main(String args[]) throws Exception {
		int ret = ToolRunner.run(new ScoreCalculate(), args);
		System.exit(ret);
	}

}
