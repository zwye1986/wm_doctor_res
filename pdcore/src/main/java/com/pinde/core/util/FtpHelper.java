package com.pinde.core.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.Properties;

/**
 * @author xxj
 */
public class FtpHelper implements Closeable {
    private FTPClient ftp = null;
    boolean _isLogin = false;


   // @Value("#{configProperties['ftp.switch']}")
    public String ftpSwitch;

   // @Value("#{configProperties['ftp.url']}")
    public String ftpUrl;

   // @Value("#{configProperties['ftp.port']}")
    public int ftpPort;

    //@Value("#{configProperties['ftp.username']}")
    public String ftpUserName;

   // @Value("#{configProperties['ftp.password']}")
    public String ftpPassWord;

    private static Logger logger = LoggerFactory.getLogger(FtpHelper.class);

    public static FtpHelper getInstance() {
        return new FtpHelper();
    }

    public boolean init()
    {
        InputStream input = null;
        try
        {
            // 获取到classpath下的文件
            input = Class.forName(FtpHelper.class.getName()).getResourceAsStream("/ftp.properties");
            Properties prop =  new  Properties();
            prop.load(input);
            this.ftpSwitch=prop.getProperty( "ftp.switch" ).trim();
            this.ftpUrl=prop.getProperty( "ftp.url" ).trim();
            String port=prop.getProperty( "ftp.port" ).trim();
            if(StringUtil.isBlank(port))
            {
                port="21";
            }
            this.ftpPort=Integer.valueOf(port);
            this.ftpUserName=prop.getProperty( "ftp.username" ).trim();
            this.ftpPassWord=prop.getProperty( "ftp.password" ).trim();
            input.close();
        } catch (ClassNotFoundException e)
        {
            logger.error("", e);
            return false;
        } catch (IOException e) {
            logger.error("", e);
            return false;
        }
        return true;
    }
    /**
     * ftp 匿名登录
     *
     */
    public boolean login() {
        //如果没有设置ftp用户可将username设为anonymous，密码为任意字符串
        return login(ftpUrl, ftpPort, ftpUserName, ftpPassWord);
    }

    /**
     * ftp登录
     *
     * @param ip    ftp服务地址
     * @param port  端口号
     * @param uname 用户名
     * @param pass  密码
     */
    public boolean login(String ip, int port, String uname, String pass) {
        ftp = new FTPClient();
        try {
            // 连接
            System.out.println("FTP===ip:" + ip+"====port:"+port);
            ftp.connect(ip, port);
            _isLogin = ftp.login(uname, pass);
            System.out.println("登录成功====" + _isLogin);
            // 检测连接是否成功
            int reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                System.err.println("FTP服务器拒绝连接 ");
                return false;
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    /**
     * 上传后触发
     */
   // public Function<FtpFileInfo, Boolean> onUploadFileAfter;

    /**
     * ftp上传文件
     *
     * @param localFileName 待上传文件
     * @param ftpDirName    ftp 目录名
     * @param ftpFileName   ftp目标文件
     * @return true||false
     */
    public boolean uploadFile(String localFileName
            , String ftpDirName
            , String ftpFileName) {
        return uploadFile(localFileName, ftpDirName, ftpFileName, false);
    }

    /**
     * ftp上传文件
     *
     * @param localFileName   待上传文件
     * @param ftpDirName      ftp 目录名
     * @param ftpFileName     ftp目标文件
     * @param deleteLocalFile 是否删除本地文件
     * @return true||false
     */
    public boolean uploadFile(String localFileName
            , String ftpDirName
            , String ftpFileName
            , boolean deleteLocalFile) {

        System.out.println("准备上传 [" + localFileName + "] 到 ftp://{" + ftpDirName + "}/{" + ftpFileName + "}");
//		if(StringExtend.isNullOrEmpty(ftpDirName))
//			ftpDirName="/";
        if (StringUtils.isEmpty(ftpFileName))
            throw new RuntimeException("上传文件必须填写文件名！");

        File srcFile = new File(localFileName);
        if (!srcFile.exists())
            throw new RuntimeException("文件不存在：" + localFileName);

        try (FileInputStream fis = new FileInputStream(srcFile)) {
            //上传文件
            boolean flag = uploadFile(fis,ftpDirName,ftpFileName);
            //上传前事件
//            if(onUploadFileAfter!=null){
//                onUploadFileAfter.apply(new FtpFileInfo(localFileName,ftpDirName,ftpFileName));
//            }
            //删除文件
            if(deleteLocalFile){
                srcFile.delete();
                System.out.println("ftp删除源文件：" + srcFile);
            }
            fis.close();
            return flag;
        } catch (Exception e) {
            logger.error("", e);
            return false;
        } finally {
        }
    }


    /**
     * ftp上传文件 (使用inputstream)
     *
//     * @param localFileName 待上传文件
     * @param ftpDirName    ftp 目录名
     * @param ftpFileName   ftp目标文件
     * @return true||false
     */
    public boolean uploadFile(FileInputStream uploadInputStream
            , String ftpDirName
            , String ftpFileName) {
        System.out.println("准备上传 [流] 到 ftp://"+ftpDirName+"/"+ftpFileName);
        if (StringUtils.isEmpty(ftpFileName))
            throw new RuntimeException("上传文件必须填写文件名！");

        try {
            // 设置上传目录(没有则创建)
            if (!createDir(ftpDirName)) {
                throw new RuntimeException("切入FTP目录失败：" + ftpDirName);
            }
            ftp.setBufferSize(1024);
            //解决上传中文 txt 文件乱码
            ftp.setControlEncoding("GBK");
            FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
            conf.setServerLanguageCode("zh");


            // 设置文件类型（二进制）
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            // 上传
            String fileName = new String(ftpFileName.getBytes("GBK"), "iso-8859-1");
            if (ftp.storeFile(fileName, uploadInputStream)) {
                uploadInputStream.close();
                System.out.println("文件上传成功："+ftpDirName+"/"+ftpFileName);
                return true;
            }

            return false;
        } catch (Exception e) {
            logger.error("", e);
            return false;
        } finally {
        }
    }

    public static void main(String[] args) {
//        String strImg ="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";
//        System.out.println(strImg);
//        GenerateImage(strImg,"d:222.png");
        final String path="E:\\workspace\\out\\artifacts\\pdsci2_war_exploded\\pdsciupload\\evalFiles\\2016Result\\02ba52a1ce1f47febf0ca4630a21908e\\临床部分\\01内科专业基地\\内科(消化内科)疾病种类临床技能操作.xlsx";

        final String path2="E:\\workspace\\out\\artifacts\\pdsci2_war_exploded\\pdsciupload\\evalFiles\\2016Result\\02ba52a1ce1f47febf0ca4630a21908e\\临床部分\\01内科专业基地\\内科(消化内科)疾病种类临床技能操作4.xlsx";
        final String ftpFileName=path.substring(path.lastIndexOf(File.separator)+1);
        String basePath="E:\\workspace\\out\\artifacts\\pdsci2_war_exploded\\pdsciupload";
        final String ftpDir=path.substring(basePath.length()+1,path.lastIndexOf(File.separator));
        System.err.println(File.separator);
        System.err.println(ftpFileName);
        System.err.println(ftpDir);
        FtpHelperUtil ftpHelperUtil=new FtpHelperUtil();
        //ftpHelperUtil.uploadFile(path,ftpDir,ftpFileName);
        ftpHelperUtil.downloadFile(path2,ftpDir,ftpFileName);
    }
    /**
     * 下载文件
     *
     * @param ftpDirName        ftp目录名
     * @param ftpFileName       ftp文件名
     * @param localFileFullName 本地文件名
     * @return
     * @author xxj
     */
    public boolean downloadFile(String ftpDirName,
                                String ftpFileName, String localFileFullName) {
        try {
            ftpDirName=ftpDirName.replace(File.separator,"/");
            if ("".equals(ftpDirName))
                ftpDirName = "/";
            String dir = new String(ftpDirName.getBytes("GBK"), "iso-8859-1");
            if (!ftp.changeWorkingDirectory(dir)) {
                System.out.println("切换目录失败：" + ftpDirName);
                return false;
            }

            ftp.setBufferSize(1024);
            //解决上传中文 txt 文件乱码
            ftp.setControlEncoding("GBK");
            FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
            conf.setServerLanguageCode("zh");

            // 设置文件类型（二进制）
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);

            FTPFile[] fs = ftp.listFiles();
            System.out.println("待下载文件原Name：" + ftpFileName);
            for (FTPFile ff : fs) {
                System.out.println("ftp文件Name：" + ff.getName());
                if (ff.getName().equals(ftpFileName)) {
                    FileOutputStream is = new FileOutputStream(new File(localFileFullName));
                    String ftpFileName2=new String(ff.getName().getBytes("GBK"), "iso-8859-1");
                    boolean f=  ftp.retrieveFile(ftpFileName2, is);
                    is.close();
                    System.out.println("下载ftp文件已下载：" + localFileFullName);
                    return true;
                }
            }
            System.out.println("下载ftp文件失败：" + ftpFileName + ";目录：" + ftpDirName);
            return false;
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }


    /**
     * 删除ftp上的文件
     *
     * @param ftpFileName
     * @return true || false
     */
    public boolean removeFile(String ftpFileName) {
        boolean flag = false;
        System.out.println("待删除文件："+ftpFileName);
        try {
            ftpFileName = new String(ftpFileName.getBytes("GBK"), "iso-8859-1");
            flag = ftp.deleteFile(ftpFileName);
            System.out.println("删除文件："+flag);
            return flag;
        } catch (IOException e) {
            logger.error("", e);
            return false;
        }
    }

    /**
     * 删除空目录
     *
     * @param dir
     * @return
     */
    public boolean removeDir(String dir) {

//        if (StringExtend.startWith(dir, "/"))
        if (dir.startsWith("/"))
            dir = "/" + dir;
        try {
            String d = new String(dir.toString().getBytes("GBK"), "iso-8859-1");
            return ftp.removeDirectory(d);
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }

    /**
     * 创建目录(有则切换目录，没有则创建目录)
     *
     * @param dir
     * @return
     */
    public boolean createDir(String dir) {
        if (StringUtils.isEmpty(dir))
            return true;
        dir=dir.replace(File.separator,"/");
        String d;
        try {
            //目录编码，解决中文路径问题
            d = new String(dir.toString().getBytes("GBK"), "iso-8859-1");
            //尝试切入目录
            if (ftp.changeWorkingDirectory(d))
                return true;
            if("/".equals(dir.substring(0,1))){
                dir = dir.substring(1);
            }
            if("/".equals(dir.substring(dir.length()-1,dir.length()))){
                dir = dir.substring(0,dir.length()-1);
            }
//            dir = StringExtend.trimStart(dir, "/");
//            dir = StringExtend.trimEnd(dir, "/");

            String[] arr = dir.split("/");
            StringBuffer sbfDir = new StringBuffer();
            //循环生成子目录
            for (String s : arr) {
                sbfDir.append("/");
                sbfDir.append(s);
                //目录编码，解决中文路径问题
                d = new String(sbfDir.toString().getBytes("GBK"), "iso-8859-1");
                //尝试切入目录
                if (ftp.changeWorkingDirectory(d))
                    continue;
                if (!ftp.makeDirectory(d)) {
                    System.out.println("[失败]ftp创建目录：" + sbfDir.toString());
                    return false;
                }
                System.out.println("[成功]创建ftp目录：" + sbfDir.toString());
            }
            //将目录切换至指定路径
            return ftp.changeWorkingDirectory(d);
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }


    /**
     * 销毁ftp连接
     */
    private void closeFtpConnection() {
        _isLogin = false;
        if (ftp != null) {
            if (ftp.isConnected()) {
                try {
                    ftp.logout();
                    ftp.disconnect();
                } catch (IOException e) {
                    logger.error("", e);
                }
            }
        }
    }


    /**
     * 销毁ftp连接
     */
    public void close() {
        this.closeFtpConnection();
    }

    public static class FtpFileInfo {
        public FtpFileInfo(String srcFile, String ftpDirName, String ftpFileName) {
            this.ftpDirName = ftpDirName;
            this.ftpFileName = ftpFileName;
            this.srcFile = srcFile;
        }

        String srcFile;
        String ftpDirName;
        String ftpFileName;
        String ftpFileFullName;

        public String getSrcFile() {
            return srcFile;
        }

        public void setSrcFile(String srcFile) {
            this.srcFile = srcFile;
        }

        public String getFtpDirName() {
            return ftpDirName;
        }

        public void setFtpDirName(String ftpDirName) {
            this.ftpDirName = ftpDirName;
        }

        public String getFtpFileName() {
            return ftpFileName;
        }

        public void setFtpFileName(String ftpFileName) {
            this.ftpFileName = ftpFileName;
        }

//        /**
//         * 获取ftp上传文件的完整路径名
//         *
//         * @return
//         * @author xxj
//         */
//        public String getFtpFileFullName() {
//            return PathExtend.Combine("/", ftpDirName, ftpFileName);
//        }

    }
}