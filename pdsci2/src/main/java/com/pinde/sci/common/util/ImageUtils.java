package com.pinde.sci.common.util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
public class ImageUtils {
//	public final static void pressImage(String pressImg, String targetImg,
//            int x, int y) {
//        try {
//            //目标文件
//            File _file = new File(targetImg);
//            Image src = ImageIO.read(_file);
//            int wideth = src.getWidth(null);
//            int height = src.getHeight(null);
//            BufferedImage image = new BufferedImage(wideth, height,
//                    BufferedImage.TYPE_INT_RGB);
//            Graphics g = image.createGraphics();
//            g.drawImage(src, 0, 0, wideth, height, null);
//
//            //水印文件
//            File _filebiao = new File(pressImg);
//            Image src_biao = ImageIO.read(_filebiao);
//            int wideth_biao = src_biao.getWidth(null);
//            int height_biao = src_biao.getHeight(null);
//            g.drawImage(src_biao, (wideth - wideth_biao) / 2,
//                    (height - height_biao) / 2, wideth_biao, height_biao, null);
//            //水印文件结束
//            g.dispose();
//            FileOutputStream out = new FileOutputStream(targetImg);
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            encoder.encode(image);
//            out.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//	public static void pressText(String pressText, File imgFile,
//            String fontName, int fontStyle, int color, int fontSize, int x,
//            int y) {
//        try {
//            Image src = ImageIO.read(imgFile);
//            int wideth = src.getWidth(null);
//            int height = src.getHeight(null);
//            BufferedImage image = new BufferedImage(wideth, height,
//                    BufferedImage.TYPE_INT_RGB);
//            Graphics g = image.createGraphics();
//            g.drawImage(src, 0, 0, wideth, height, null);
//
//            g.setColor(Color.RED);
//            g.setFont(new Font(fontName, fontStyle, fontSize));
//
//            g.drawString(pressText, wideth - fontSize - x, height - fontSize
//                    / 2 - y);
//            g.dispose();
//            FileOutputStream out = new FileOutputStream(imgFile);
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            encoder.encode(image);
//            out.close();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
}
