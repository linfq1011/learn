package com.linfq.learn.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * HdfsUtils.
 *
 * @author linfq
 * @date 2019/4/10 22:24
 */
public class HdfsUtils {

	private FileSystem fileSystem;

	/**
	 *
	 * @param url hdfs://hadoop-master:9000
	 */
	public HdfsUtils(String url) throws IOException {
		Configuration conf = new Configuration();
		conf.set("fs.default.name", url);
		fileSystem = FileSystem.get(conf);
	}

	/**
	 * 列出目录下面的节点.
	 *
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public List<Path> list(String fileName) throws IOException {
		FileStatus[] statuses = fileSystem.listStatus(new Path(fileName));
		return Arrays.stream(statuses).map(FileStatus::getPath).collect(toList());

	}

	public void text(String fileName) {
		// TODO 待实现
	}

	/**
	 * 上传文件
	 *
	 * @param src 源文件
	 * @param dest 目标文件
	 * @throws IOException
	 */
	public void put(String src, String dest) throws IOException {
		FileInputStream fis = new FileInputStream(src);
		FSDataOutputStream out = fileSystem.create(new Path(dest), true);
		IOUtils.copyBytes(fis, out, 4096, true);
	}

	/**
	 * 删除文件
	 *
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public boolean delete(String fileName) throws IOException {
		return fileSystem.delete(new Path(fileName), true);
	}

	/**
	 * 创建目录.
	 *
	 * @param dirName
	 */
	public boolean makeDir(String dirName) throws IOException {
		return fileSystem.mkdirs(new Path(dirName));
	}
}
