package com.pinde.core.util;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;

public class FileUtil {
    /**
     * 下载或在线浏览
     * @param filePath 路径
     * @param response
     * @throws Exception
     */
    public static void downLoad(String filePath, String fileName, HttpServletResponse response) throws Exception {
        File file = new File(filePath);
        if (!file.exists()) {
            response.sendError(404, "File not found!");
            return;
        }
        boolean isOnLine = isImage(file);
        if(StringUtil.isBlank(fileName)){
            fileName = file.getName();
        }
        fileName = URLEncoder.encode(fileName, "UTF-8");
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        byte[] buffer = new byte[bis.available()];
        bis.read(buffer);
        bis.close();
        response.reset();
        if (isOnLine) {
            //在线打开方式
            URL u = new URL("file://" + filePath);
            response.setHeader("Content-Disposition", "inline; filename=" + fileName);
            response.setContentType(u.openConnection().getContentType());
        } else {
            //纯下载方式
            response.setContentType("application/x-msdownload;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setContentLength(buffer.length);
        }
        OutputStream os = response.getOutputStream();
        os.write(buffer);
        os.flush();
        os.close();
    }
    private static boolean isImage(File file) {
        boolean flag = false;
        try {
            ImageInputStream is = ImageIO.createImageInputStream(file);
            if(null == is) {
                return flag;
            }
            is.close();
            flag = true;
        } catch (Exception e) {
            e.getMessage();
        }
        return flag;
    }
}
