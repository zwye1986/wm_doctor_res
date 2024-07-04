package com.pinde.sci.util;

import com.pinde.core.util.DateUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


/**
 * Created by Administrator on 2017/9/2.
 */
public class FtpHelperUtil {

    FtpHelper ftp = null;
    boolean loginFlag = false;
    boolean initFlag = false;

    public void init() {
        ftp = FtpHelper.getInstance();
        initFlag = ftp.init();
    }
    public void  ftpLogin(){
        if (initFlag)
            loginFlag = ftp.login();//匿名登录
    }


    public void finish() {
        ftp.close();
    }


    public void uploadFile(String localFilePath, String ftpDir, String ftpFileName) {
        try {
            init();
            if(initFlag&&"Y".equals(ftp.ftpSwitch)) {
                ftpLogin();
                if (loginFlag) {
                    long time = System.currentTimeMillis();
                    FileInputStream fi = new FileInputStream(localFilePath);

                    boolean success = ftp.uploadFile(fi, ftpDir, ftpFileName);
                    //System.out.println("time=====" + (System.currentTimeMillis() - time));
                } else {
                    System.err.println("FTP服务器连接失败");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (loginFlag) {
                finish();
            }
        }
    }

    public void downloadFile(String localFilePath, String ftpDir, String ftpFileName) {
        try {
            init();
            if(initFlag&&"Y".equals(ftp.ftpSwitch)) {
                ftpLogin();
                if (loginFlag) {
                    long time = System.currentTimeMillis();
                    ftp.downloadFile(ftpDir, ftpFileName, localFilePath);
                    //System.out.println("time=====" + (System.currentTimeMillis() - time));
                } else {
                    System.err.println("FTP服务器连接失败");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (loginFlag) {
                finish();
            }
        }
    }
}
