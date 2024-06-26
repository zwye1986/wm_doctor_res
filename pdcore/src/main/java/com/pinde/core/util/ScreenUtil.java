package com.pinde.core.util;

import java.awt.*;

/**
 * @author www.0001.Ga
 */
public class ScreenUtil {
    private static final double IN_TO_CM =  2.54;//1英寸等于2.54厘米

    //屏幕宽度 像素
    private int widthPX;
    //屏幕高度 像素
    private int heightPX;
    //屏幕宽度 厘米
    private double widthCM;
    //屏幕高度 厘米
    private double heightCM;
    //屏幕宽度 英寸
    private double widthIN;
    //屏幕高度 英寸
    private double heightIN;
    //dpi 意思是说一英寸多少个象素
    private int dpi;

    public ScreenUtil(){
        Dimension screensize   =  Toolkit.getDefaultToolkit().getScreenSize();
        this.widthPX = (int)screensize.getWidth();
        this.heightPX = (int)screensize.getHeight();
        //屏幕的物理大小还需要知道屏幕的dpi 意思是说一英寸多少个象素
        this.dpi = Toolkit.getDefaultToolkit().getScreenResolution();
        this.heightIN = heightPX/dpi;
        this.widthIN = widthPX/dpi;

        this.heightCM = heightIN * IN_TO_CM;
        this.widthCM = widthIN * IN_TO_CM;

    }

    public double getCm(double px) throws Exception {
        if(dpi != 0){
            px = px/dpi*IN_TO_CM;
            return px;
        }else{
            throw new Exception("当前类未加载！");
        }

    }

    public int getWidthPX() {
        return widthPX;
    }

    public void setWidthPX(int widthPX) {
        this.widthPX = widthPX;
    }

    public int getHeightPX() {
        return heightPX;
    }

    public void setHeightPX(int heightPX) {
        this.heightPX = heightPX;
    }

    public double getWidthCM() {
        return widthCM;
    }

    public void setWidthCM(double widthCM) {
        this.widthCM = widthCM;
    }

    public double getHeightCM() {
        return heightCM;
    }

    public void setHeightCM(double heightCM) {
        this.heightCM = heightCM;
    }

    public double getWidthIN() {
        return widthIN;
    }

    public void setWidthIN(double widthIN) {
        this.widthIN = widthIN;
    }

    public double getHeightIN() {
        return heightIN;
    }

    public void setHeightIN(double heightIN) {
        this.heightIN = heightIN;
    }

    public int getDpi() {
        return dpi;
    }

    public void setDpi(int dpi) {
        this.dpi = dpi;
    }
}
