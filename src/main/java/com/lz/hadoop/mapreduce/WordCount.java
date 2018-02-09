package com.lz.hadoop.mapreduce;

import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

public class WordCount  
{  
    /** 
     * MapReduceBase类:实现了Mapper和Reducer接口的基类（其中的方法只是实现接口，而未作任何事情） 
     * Mapper接口： 
     * WritableComparable接口：实现WritableComparable的类可以相互比较。所有被用作key的类应该实现此接口。 
     * Reporter 则可用于报告整个应用的运行进度，本例中未使用。  
     *  
     */  
    public static class Map extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable>{  
        /** 
         * LongWritable, IntWritable, Text 均是 Hadoop 中实现的用于封装 Java 数据类型的类，这些类实现了WritableComparable接口， 
         * 都能够被串行化从而便于在分布式环境中进行数据交换，你可以将它们分别视为long,int,String 的替代品。 
         */  
        private final static IntWritable one = new IntWritable(1);  
        private Text word = new Text();  
          
        /** 
         * Mapper接口中的map方法： 
         * void map(K1 key, V1 value, OutputCollector<K2,V2> output, Reporter reporter) 
         * 映射一个单个的输入k/v对到一个中间的k/v对 
         * 输出对不需要和输入对是相同的类型，输入对可以映射到0个或多个输出对。 
         * OutputCollector接口：收集Mapper和Reducer输出的<k,v>对。 
         * OutputCollector接口的collect(k, v)方法:增加一个(k,v)对到output 
         */  
        public void map(LongWritable key, Text value,  
                OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {  
            String line = value.toString();  
            StringTokenizer tokenizer = new StringTokenizer(line);  
            while (tokenizer.hasMoreTokens())  
            {  
                word.set(tokenizer.nextToken());  
                output.collect(word, one);  
            }  
        }  
    }  
    public static class Reduce extends MapReduceBase implements  
            Reducer<Text, IntWritable, Text, IntWritable>  
    {  
        public void reduce(Text key, Iterator<IntWritable> values,  
                OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException  
        {  
            int sum = 0;  
            while (values.hasNext())  
            {  
                sum += values.next().get();  
            }  
            output.collect(key, new IntWritable(sum));  
        }  
    }  
    public static void main(String[] args) throws Exception  
    {  
        /** 
         * JobConf：map/reduce的job配置类，向hadoop框架描述map-reduce执行的工作 
         * 构造方法：JobConf()、JobConf(Class exampleClass)、JobConf(Configuration conf)等 
         */  
        JobConf conf = new JobConf(WordCount.class);  
        conf.setJobName("wordcount");           //设置一个用户定义的job名称  
        conf.setOutputKeyClass(Text.class);    //为job的输出数据设置Key类  
        conf.setOutputValueClass(IntWritable.class);   //为job输出设置value类  
        conf.setMapperClass(Map.class);         //为job设置Mapper类  
        conf.setCombinerClass(Reduce.class);      //为job设置Combiner类  
        conf.setReducerClass(Reduce.class);        //为job设置Reduce类  
        conf.setInputFormat(TextInputFormat.class);    //为map-reduce任务设置InputFormat实现类  
        conf.setOutputFormat(TextOutputFormat.class);  //为map-reduce任务设置OutputFormat实现类  
        /** 
         * InputFormat描述map-reduce中对job的输入定义 
         * setInputPaths():为map-reduce job设置路径数组作为输入列表 
         * setInputPath()：为map-reduce job设置路径数组作为输出列表 
         */  
        FileInputFormat.setInputPaths(conf, new Path("F:\\test\\file1.txt"));  
        FileOutputFormat.setOutputPath(conf, new Path("F:\\test\\output"));  
        JobClient.runJob(conf);         //运行一个job  
    }  
}
