package com.pinde.res.ctrl.jswjw;

import com.pinde.sci.util.FtpHelperUtil;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/5/25.
 */
public class ImageTest {

    public static void main(String[] args) {
//        String strImg ="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";
//        //System.out.println(strImg);
//        GenerateImage(strImg,"d:222.png");
        final String path="E:\\workspace\\out\\artifacts\\pdsci2_war_exploded\\pdsciupload\\evalFiles\\2016Result\\02ba52a1ce1f47febf0ca4630a21908e\\临床部分\\01内科专业基地\\内科(消化内科)疾病种类临床技能操作.xlsx";

        final String path2="E:\\workspace\\out\\artifacts\\pdsci2_war_exploded\\pdsciupload\\evalFiles\\2016Result\\02ba52a1ce1f47febf0ca4630a21908e\\临床部分\\01内科专业基地\\内科(消化内科)疾病种类临床技能操作2.xlsx";
        final String ftpFileName=path.substring(path.lastIndexOf(File.separator)+1);
        String basePath="E:\\workspace\\out\\artifacts\\pdsci2_war_exploded\\pdsciupload";
        final String ftpDir=path.substring(basePath.length()+1,path.lastIndexOf(File.separator));
        System.err.println(File.separator);
        System.err.println(ftpFileName);
        System.err.println(ftpDir);
        ExecutorService executor = Executors.newCachedThreadPool();
        FutureTask<String> future = new FutureTask<String>(new Callable<String>() {
            public String call() throws Exception{ //建议抛出异常
                try {
                    FtpHelperUtil ftpHelperUtil=new FtpHelperUtil();
                    //ftpHelperUtil.uploadFile(path,ftpDir,ftpFileName);
                    ftpHelperUtil.downloadFile(path2,ftpDir,ftpFileName);
                    return "Hello Welcome!";
                }
                catch(Exception e) {
                    throw new Exception("Callable terminated with Exception!"); // call方法可以抛出异常
                }
            }
        });
        executor.execute(future);
        long t = System.currentTimeMillis();
        try {

//            String result = future.get(3000, TimeUnit.MILLISECONDS); //取得结果，同时设置超时执行时间为5秒。
            String result = future.get(); //取得结果，同时设置超时执行时间为5秒。
            System.err.println("result is " + result + ", time is " + (System.currentTimeMillis() - t));
        } catch (InterruptedException e) {
            future.cancel(true);
            System.err.println("Interrupte time is " + (System.currentTimeMillis() - t));
        } catch (ExecutionException e) {
            future.cancel(true);
            System.err.println("Throw Exception time is " + (System.currentTimeMillis() - t));
//        } catch (TimeoutException e) {
//            future.cancel(true);
//            System.err.println("Timeout time is " + (System.currentTimeMillis() - t));
        } finally {
            executor.shutdown();
        }
        System.err.println("121saddq");
    }

    //图片转化成base64字符串
    public static String GetImageStr(String imgPath) {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        //待处理的图片
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);//返回Base64编码过的字节数组字符串
    }

    //base64字符串转化成图片
    public static boolean GenerateImage(String imgStr,String savePath) {   //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            //生成jpeg图片//新生成的图片
            OutputStream out = new FileOutputStream(savePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
