package com.pinde.sci.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pinde.core.util.StringUtil;
import com.pinde.res.ctrl.jswjw.JswjwWxController;
import com.pinde.res.model.jswjw.mo.QQMapResultExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author: lmin
 * @Description:根据经纬度获取地址
 * @Modified By:
 */
public class QQMapUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static Logger logger = LoggerFactory.getLogger(QQMapUtil.class);

    /**
     * @param lat 纬度
     * @param lng 经度
     * @return
     */
    public static String getAddress(String lat, String lng) throws IOException {
        JSONObject obj = getLocationInfo(lat, lng).getJSONObject("result");
        logger.debug("地址："+obj);
        if(null == obj){
            return null;
        }
        String address = obj.get("address").toString();
        if(StringUtil.isBlank(address)){
            return null;
        }
//        QQMapResultExt ext = fromJson(obj.toString(), QQMapResultExt.class);
        return address;
    }

    public static JSONObject getLocationInfo(String lat, String lng) throws IOException {
        String secretKey = PropertiesUtils.getValue("secretKey");
//        String secretKey = "TKCBZ-KZGLR-4EOWY-WFRSK-VXNT3-5IB4K";
        String urlString = "https://apis.map.qq.com/ws/geocoder/v1/?location=" + lat + "," + lng + "&key=" + secretKey + "&get_poi=1";
        logger.debug("请求经纬度url:" + urlString);
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoInput(true);
        conn.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
        String line;
        String res = "";
        while ((line = in.readLine()) != null) {
            res += line + "\n";
        }
        in.close();
        JSONObject jsonObject = JSONObject.parseObject(res);
        return jsonObject;
    }

    /**
     * 将JSON转为POJO
     */
    public static <T> T fromJson(String json, Class<T> type) {
        T pojo;
        try {
            pojo = OBJECT_MAPPER.readValue(json, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
            //e.printStackTrace();
        }
        return pojo;

    }

    public static void main(String[] args) throws IOException {
        System.out.println(getAddress("31.99226", "118.7787"));
//        System.out.println(getDistance(31.974695,118.768001, 31.972342, 118.778696 ));
    }

    private static double EARTH_RADIUS = 6378137;
    /**
     * 通过经纬度获取距离(单位：米)
     *
     * @param lat1 纬度1
     * @param lng1 经度1
     * @param lat2 纬度2
     * @param lng2 经度2
     * @return 距离
     */
    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        //double len=s/EARTH_SEA;
        //s = s / 1000;
        return s;
    }

    /**
     * @param lat1   纬度
     * @param lat2   纬度
     * @param lng1   经度
     * @param lng2   经度
     * @param radius 判断一个点是否在圆形区域内,比较坐标点与圆心的距离是否小于半径
     */
    public static boolean isInCircle(double lng1, double lat1, double lng2, double lat2, double radius) {
        double distance = getDistance(lat1, lng1, lat2, lng2);
        if (distance > radius) {
            return false;
        } else {
            //System.err.println("经度："+lng1+"维度："+lat1+"经度："+lng2+"维度："+lat2+"距离:"+distance);
            return true;
        }
    }

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }
}
