package com.pinde.core.util;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import java.io.*;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * 把多个文件打包到一个文件
 * 
 * @author xiaofei.hu
 * 
 */
public class ZipUtil {

	static final int BUFFER = 2048;
	/**
	 * The buffer.
	 */
	protected static byte buf[] = new byte[1024];

	public static void toZip(File[] files, File zipFile) {

	}
	
	/**
	 * 遍历目录并添加文件.
	 *
	 * @param jos
	 *            - JAR 输出流
	 * @param file
	 *            - 目录文件名
	 * @param pathName
	 *            - ZIP中的目录名
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private static void recurseFiles(ZipOutputStream jos, File file,
			String pathName) throws IOException, FileNotFoundException {
		if (file.isDirectory()) {

			pathName = pathName + file.getName() + "/";
			jos.putNextEntry(new ZipEntry(pathName));
			String fileNames[] = file.list();
			if (fileNames != null) {
				for (int i = 0; i < fileNames.length; i++)
					recurseFiles(jos, new File(file, fileNames[i]), pathName);

			}
		} else {
			ZipEntry jarEntry = new ZipEntry(pathName + file.getName());
			// System.out.println(pathName + "  " + file.getName());
			FileInputStream fin = new FileInputStream(file);
			BufferedInputStream in = new BufferedInputStream(fin);
			jos.putNextEntry(jarEntry);
			jos.setEncoding("gbk");
			int len;
			while ((len = in.read(buf)) >= 0)
				jos.write(buf, 0, len);
			in.close();
			jos.closeEntry();
		}
	}

	private static void recurseNjmueduFiles(ZipOutputStream jos, File file,
			String pathName) throws IOException, FileNotFoundException {
		if (file.isDirectory()) {

			pathName = pathName + "/";
			jos.putNextEntry(new ZipEntry(pathName));
			String fileNames[] = file.list();
			if (fileNames != null) {
				for (int i = 0; i < fileNames.length; i++)
					recurseFiles(jos, new File(file, fileNames[i]), pathName);

			}
		} else {
			ZipEntry jarEntry = new ZipEntry(pathName);
			// System.out.println(pathName + "  " + file.getName());
			FileInputStream fin = new FileInputStream(file);
			BufferedInputStream in = new BufferedInputStream(fin);
			jos.putNextEntry(jarEntry);
			jos.setEncoding("GBK");
			int len;
			while ((len = in.read(buf)) >= 0)
				jos.write(buf, 0, len);
			in.close();
			jos.closeEntry();
		}
	}

	public static void toZip(List<File> files, File zipFile,
			String zipFolderName, int level) throws IOException,
			FileNotFoundException {
		level = checkZipLevel(level);

		if (zipFolderName == null) {
			zipFolderName = "";
		}

		ZipOutputStream jos = new ZipOutputStream(new FileOutputStream(zipFile));
		jos.setLevel(level);

		for (int i = 0; i < files.size(); i++) {
			recurseFiles(jos, files.get(i), zipFolderName);
		}

		jos.close();

	}

	/**
	 * 创建 ZIP/JAR 文件.
	 *
	 * @param directory
	 *            - 要添加的目录
	 * @param zipFile
	 *            - 保存的 ZIP 文件名
	 * @param zipFolderName
	 *            - ZIP 中的路径名
	 * @param level
	 *            - 压缩级别(0~9)
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void makeDirectoryToZip(File directory, File zipFile,
			String zipFolderName, int level) throws IOException,
			FileNotFoundException {
		level = checkZipLevel(level);

		if (zipFolderName == null) {
			zipFolderName = "";
		}

		ZipOutputStream jos = new ZipOutputStream(new FileOutputStream(zipFile));
		jos.setLevel(level);

		String fileNames[] = directory.list();
		if (fileNames != null) {
			for (int i = 0; i < fileNames.length; i++)
				recurseFiles(jos, new File(directory, fileNames[i]),
						zipFolderName);

		}
		if(directory.exists()){
			deleteDir(directory);
		}
		jos.close();
	}
	private static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			//递归删除目录中的子目录下
			for (int i=0; i<children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		return dir.delete();
	}

	public static void makeNjmueduStuWorkDirectoryToZip(File directory, Map<String, String> userMap, File zipFile,
			String zipFolderName, int level) throws IOException,
			FileNotFoundException {
		level = checkZipLevel(level);

		if (zipFolderName == null) {
			zipFolderName = "";
		}

		ZipOutputStream jos = new ZipOutputStream(new FileOutputStream(zipFile));
		jos.setLevel(level);

		String fileNames[] = directory.list();
		if (fileNames != null) {
			for (int i = 0; i < fileNames.length; i++){
				String oldFileName = fileNames[i];
				int endIndex = oldFileName.lastIndexOf(".");
				String key = oldFileName.substring(0, endIndex);
				String value = userMap.get(key);
				if(StringUtil.isNotBlank(value)){
					String newName = value + oldFileName.substring(endIndex);
					recurseNjmueduFiles(jos, new File(directory, fileNames[i]), newName);
				}
			}
		}
		jos.close();
	}

	/**
	 * 检查并设置有效的压缩级别.
	 *
	 * @param level
	 *            - 压缩级别
	 * @return 有效的压缩级别或者默认压缩级别
	 */
	public static int checkZipLevel(int level) {
		if (level < 0 || level > 9)
			level = 7;
		return level;
	}

	public static void unZip(File zipFile,String zipFolderName) throws IOException {
		ZipFile zis = new ZipFile(zipFile);
		Enumeration<ZipEntry> e = zis.getEntries();
		while(e.hasMoreElements()){
			ZipEntry entry = (ZipEntry) e.nextElement();
			File file = new File(zipFolderName+entry.getName());
			
			if(entry.isDirectory()){
				file.mkdirs();
				continue;
			}
			InputStream inputStream = zis.getInputStream(entry);
			File parent = file.getParentFile();
            if(parent!=null&&!parent.exists()){
                parent.mkdirs();
            }
            byte[] buf = new byte[BUFFER];
            int readedBytes = 0;
            FileOutputStream fileOut = new FileOutputStream(file);
            while((readedBytes = inputStream.read(buf) ) > 0){
                fileOut.write(buf , 0 , readedBytes );
            }
            fileOut.close();
            inputStream.close();			
		}
		zis.close();
	}

	//下载directory目录下且存在list文件名的的文件
	public static void makeDirectoryToZip(File directory, List<String> list, File zipFile, int level) throws IOException{
		level = checkZipLevel(level);
		ZipOutputStream jos = new ZipOutputStream(new FileOutputStream(zipFile));
		jos.setLevel(level);
		String fileNames[] = directory.list();
		if (null != fileNames && fileNames.length > 0) {
			for (int i = 0; i < fileNames.length; i++){
				String fileName = fileNames[i];
				if(null != list && list.contains(fileName)){
					recurseNjmueduFiles(jos, new File(directory, fileNames[i]), fileName);
				}
			}
		}
		jos.close();
	}
}
