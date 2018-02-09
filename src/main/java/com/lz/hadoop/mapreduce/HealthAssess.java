package com.lz.hadoop.mapreduce;

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

public class HealthAssess extends Configured implements Tool{
	
	public static class Map extends Mapper<LongWritable, Text, Text, IntWritable>{
		public void map(LongWritable key,Text value,Context context) throws IOException, InterruptedException{
			String line = value.toString();
			
			//以#为分隔符，将输入的文件分割为单个记录
			StringTokenizer tokenizerArticle = new StringTokenizer(line,"#");
			while(tokenizerArticle.hasMoreTokens()){
				//将每个记录分割成姓名和分数
				StringTokenizer tokenizerLine = new StringTokenizer(tokenizerArticle.nextToken());
				while(tokenizerLine.hasMoreTokens()){
					String strName = tokenizerLine.nextToken();
					if(tokenizerLine.hasMoreTokens()){
						String strScore = tokenizerLine.nextToken();
						Text name = new Text(strName);
						int scoreInt = Integer.parseInt(strScore);  //该组织的状况得分
						context.write(name, new IntWritable(scoreInt));
					}
				}
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
