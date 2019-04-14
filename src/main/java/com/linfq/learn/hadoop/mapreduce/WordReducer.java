package com.linfq.learn.hadoop.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * WordReducer.
 *
 * @author linfq
 * @date 2019/4/14 20:57
 */
public class WordReducer extends Reducer<Text, IntWritable, Text, LongWritable> {

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		long count = 0;
		for (IntWritable v : values) {
			count += v.get();
		}

		context.write(key, new LongWritable(count));
	}
}
