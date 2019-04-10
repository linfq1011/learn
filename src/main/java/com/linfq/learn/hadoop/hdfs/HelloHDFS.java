package com.linfq.learn.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 * HelloHDFS.
 *
 * @author linfq
 * @date 2019/4/7 16:02
 */
public class HelloHDFS {

	public static void main(String[] args) throws Exception {
		System.setProperty("hadoop.home.dir", "E:/Program Files/hadoop-2.7.3");

		/// URL例子程序
		/*URL url = new URL("https://www.baidu.com");
		InputStream in = url.openStream();
		IOUtils.copyBytes(in, System.out, 4096, true);*/

		/// 使用URL读取HDFS文件
		/*URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
		URL url = new URL("hdfs://hadoop-master:9000/hello.txt");
		InputStream in = url.openStream();
		IOUtils.copyBytes(in, System.out, 4096, true);*/

		// FileSystem
		Configuration conf = new Configuration();
		conf.set("fs.default.name", "hdfs://hadoop-master:9000");
//		conf.set("dfs.replication", "5");
		FileSystem fileSystem = FileSystem.get(conf);

		/// FileSystem创建/删除/判断目录
		// 创建
		/*boolean success = fileSystem.mkdirs(new Path("/linfq"));
		System.out.println(success);
		// 判断文件或目录是否存在
		success = fileSystem.exists(new Path("/linfq"));
		System.out.println(success);
		// 删除
		success = fileSystem.delete(new Path("/linfq"), true);
		System.out.println(success);
		// 再次判断是否删除成功
		success = fileSystem.exists(new Path("/linfq"));
		System.out.println(success);*/

		// 上传windows文件到hdfs
		/*FileInputStream fis = new FileInputStream("D:/settings.xml");
		FSDataOutputStream out = fileSystem.create(new Path("/test.data"), true);
		IOUtils.copyBytes(fis, out, 4096, true);*/

		// 原始方式上传，可以实现上传进度
		/*FileInputStream fis = new FileInputStream("D:/settings.xml");
		FSDataOutputStream out = fileSystem.create(new Path("/test.data"), true);
		byte[] buf = new byte[4096];
		int len = fis.read(buf);
		while (len != -1) {
			out.write(buf, 0, len);
			len = fis.read(buf);
		}
		fis.close();
		out.close();*/

		// 列举目录下的信息
		FileStatus[] statuses = fileSystem.listStatus(new Path("/"));
		System.out.println(statuses.length);
		for (FileStatus status : statuses) {
			// 文件路径
			System.out.println(status.getPath());
			// 权限信息
			System.out.println(status.getPermission());
			// 备份数量
			System.out.println(status.getReplication());
		}

	}
}
