package com.pinde.sci.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件操作工具类
 */
public class FileUtil {
    /*public static void main(String args[]){
        try {
            readfile("E:\\red5-server-1.0.5\\webapps\\pdred5\\streams\\reseduCourseVideo");
        } catch (IOException e) {
             logger.error("",e);
        }
    }*/

    /**
     * 文件拷贝的方法
     * @param path
     * @param copyPath
     * @throws IOException
     */
    public static void copyFile(String path, String copyPath) {
        File filePath = new File(path);
        DataInputStream read ;
        DataOutputStream write;
        if(filePath.isDirectory()){
            File[] list = filePath.listFiles();
            for(int i=0; i<list.length; i++){
                String newPath = path + File.separator + list[i].getName();
                String newCopyPath = copyPath + File.separator + list[i].getName();
                File newFile = new File(copyPath);
                if(!newFile.exists()){
                    //newFile.mkdir();
                    newFile.mkdirs();
                }
                copyFile(newPath, newCopyPath);
            }
        }else if(filePath.isFile()){
            try {
                File copyFile=new File(copyPath);
                if(!copyFile.exists()) {
                    read = new DataInputStream(
                            new BufferedInputStream(new FileInputStream(path)));
                    write = new DataOutputStream(
                            new BufferedOutputStream(new FileOutputStream(copyPath)));
                    byte[] buf = new byte[1024 * 512];
                    while (read.read(buf) != -1) {
                        write.write(buf);
                    }
                    read.close();
                    write.close();
                }
            } catch (IOException e){
                System.out.println("com.pinde.sci.common.util.FileUtil.copyFile() IOException:" + e.getMessage());
            }
        }else{
            System.err.println(filePath +":该文件或路径不存在！");
        }
    }




    /**
     * 读取路径下的所有文件信息（包含子文件夹及文件夹内的信息）
     *
     * @param filePath
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static boolean readfile(String filePath) throws IOException {
        try {
            Map<String,List<String>> map = new HashMap<String,List<String>>();
            File file = new File(filePath);
            if(file.isDirectory()) {
                System.out.println("文件夹=============="+filePath);
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File readFile = new File(filePath + "\\" + filelist[i]);
                    if (!readFile.isDirectory()) {
                        Map<String,String> fileMessage = new HashMap<String,String>();
                        //文件大小（MB）
                        double size = (double) readFile.length() / 1024 / 1024;
                        String fileSize = "";
                        if (size < 1) {
                            size = size * 1024;
                            BigDecimal sizeMath = new BigDecimal(Double.toString(size));
                            fileSize = sizeMath.setScale(2, BigDecimal.ROUND_HALF_UP) + "KB";
                        } else {
                            BigDecimal sizeMath = new BigDecimal(Double.toString(size));
                            fileSize = sizeMath.setScale(2, BigDecimal.ROUND_HALF_UP) + "MB";
                        }
                        fileMessage.put(readFile.getName(),fileSize);
                        /*System.out.println("文件路径===============" + readFile.getPath());
                        System.out.println("文件绝对路径============" + readFile.getAbsolutePath());
                        System.out.println("文件名称================" + readFile.getName());
                        System.out.println("文件大小================" + fileSize);*/
                    } else if (readFile.isDirectory()) {
                        /*如果是文件夹递归*/
                        readfile(filePath + "\\" + filelist[i]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("readfile()   Exception:" + e.getMessage());
        }
        return true;
    }

    /**
     * 文件删除
     *
     * @param delpath
     * @return
     */
    public static boolean deletefile(String delpath) {
        boolean delSuccess = false;
        try {
            File file = new File(delpath);
            if (!file.isDirectory()) {
                System.out.println("1");
                delSuccess = file.delete();
            } else if (file.isDirectory()) {
                System.out.println("2");
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File delfile = new File(delpath + "\\" + filelist[i]);
                    if (!delfile.isDirectory()) {
                        delSuccess = delfile.delete();
                    } else if (delfile.isDirectory()) {
                        deletefile(delpath + "\\" + filelist[i]);
                    }
                }
                file.delete();
            }
        } catch (Exception e) {
            logger.error("", e);
            delSuccess = false;
        }
        return delSuccess;
    }

    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);


}
