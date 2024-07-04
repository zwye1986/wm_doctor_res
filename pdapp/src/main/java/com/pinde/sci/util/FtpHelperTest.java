package com.pinde.sci.util;

import com.pinde.core.util.DateUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


/**
 * @author xxj
 */
public class FtpHelperTest {
    FtpHelper ftp = null;

    public void InitBinder() {
        ftp = FtpHelper.getInstance();
        ftp.init();
        ftp.login();//匿名登录
    }

    public void finish() {
        ftp.close();
    }

    public void testUplodFileStream() {
        try {
            long time = System.currentTimeMillis();

            String localFile = "D:/1.txt";
            String ftpDir = "temp/abc/ddd";
            String ftpFile = "img-" + DateUtil.getCurrDateTime() + ".txt";

            FileInputStream fi = new FileInputStream(localFile);

            boolean success = ftp.uploadFile(fi, ftpDir, ftpFile);
            
            //System.out.println("time=====" + (System.currentTimeMillis() - time));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void testOnAfter() {
        String localFile = "D:/Temp/resource/img.jpg";
        String ftpDir = "/aa/bb";
        String ftpFile = "img-" + DateUtil.getCurrDateTime() + ".jpg";



        //上传
        boolean success = ftp.uploadFile(localFile, ftpDir, ftpFile);
        

    }

    public void testUplodaFile() {
        String localFile = "D:/Temp/resource/img.jpg";
        String ftpDir = "/aa/bb";
        String ftpFile = "img-" + DateUtil.getCurrDateTime() + ".jpg";
        boolean success = ftp.uploadFile(localFile, ftpDir, ftpFile);
        

        localFile = "D:/Temp/resource/text.txt";
        ftpDir = "/aa/bb/c1";
        ftpFile = "text-" + DateUtil.getCurrDateTime() + ".txt";
        success = ftp.uploadFile(localFile, ftpDir, ftpFile);
        


        localFile = "D:/Temp/resource/文本.txt";
        ftpDir = "/aa/bb/c2";
        ftpFile = "文本-" + DateUtil.getCurrDateTime() + ".txt";
        success = ftp.uploadFile(localFile, ftpDir, ftpFile);
        

    }

    public void testRemove() {
        boolean success = false;
        String file = "/temp/文本-20170509175507.txt";
        success = ftp.removeFile(file);

        
    }

    public void crateDir() {
        String dir = "aa/bb";
        boolean success = ftp.createDir(dir);
        System.out.print(success ? "成功" : "失败");
        //System.out.println(dir);

        dir = "aa/bb/c1";
        success = ftp.createDir(dir);
        System.out.print(success ? "成功" : "失败");
        //System.out.println(dir);


        dir = "aa/bb/c2";
        success = ftp.createDir(dir);
        System.out.print(success ? "成功" : "失败");
        //System.out.println(dir);

        
    }

    public void removeDir() {
        String dir = "aa/bb";
        boolean success = ftp.removeDir(dir);
        System.out.print(success ? "成功" : "失败");
        //System.out.println(dir);

        dir = "aa/bb/c1";
        success = ftp.removeDir(dir);
        System.out.print(success ? "成功" : "失败");
        //System.out.println(dir);


        dir = "aa/bb/c2";
        success = ftp.removeDir(dir);
        System.out.print(success ? "成功" : "失败");
        //System.out.println(dir);

        
    }

    public void downloadFile() {
        String ftpDirName = "/aa/bb";
        String ftpFileName = "img-20170606110355.jpg";
        String localFileFullName = "D:/Temp/pdf/down/down-" + DateUtil.getCurrDateTime() + ".jpg";
        boolean result = ftp.downloadFile(ftpDirName, ftpFileName, localFileFullName);


        ftpDirName = "/";
        ftpFileName = "文本.txt";
        localFileFullName = "D:/Temp/pdf/down/down-" + DateUtil.getCurrDateTime() + ".txt";
        result = ftp.downloadFile(ftpDirName, ftpFileName, localFileFullName);
    }
}