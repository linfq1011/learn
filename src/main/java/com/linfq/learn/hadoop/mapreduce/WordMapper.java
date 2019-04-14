package com.linfq.learn.hadoop.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * WordMapper.
 *
 * @author linfq
 * @date 2019/4/14 20:40
 */
public class WordMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		final IntWritable one = new IntWritable(1);
		String s = value.toString();
		String[] words = s.split(" ");
		for (String word : words) {
			context.write(new Text(word), one);
		}
	}
}
