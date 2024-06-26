package com.pinde.sci.YuYinUtil;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class TtsUtil {

    //  填写网页上申请的appkey 如 $apiKey="UBy9t7PotNMm6TKnLUhl7o9m"
    private static String appKey = "UBy9t7PotNMm6TKnLUhl7o9m";

    // 填写网页上申请的APP SECRET 如 $secretKey="qWbfm7Yd1cP5BvCK7YSgz175Ny3Bitf2"
    private static String secretKey = "qWbfm7Yd1cP5BvCK7YSgz175Ny3Bitf2";

    // 发音人选择, 0为普通女声，1为普通男生，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女声
    private static int per = 0;
    // 语速，取值0-15，默认为5中语速
    private static int spd = 5;
    // 音调，取值0-15，默认为5中语调
    private static int pit = 5;
    // 音量，取值0-9，默认为5中音量
    private static int vol = 5;

    // 下载的文件格式, 3：mp3(default) 4： pcm-16k 5： pcm-8k 6. wav
    private static int aue = 3;

    public static String url = "http://tsn.baidu.com/text2audio"; // 可以使用https

    public static  String cuid = "OSCA-JAVA-HOUKAO";

    public static Map<String,String>  run(String text) throws IOException, DemoException {
        Map<String,String> map=new HashMap();
        map.put("code","1");
        map.put("msg",GlobalConstant.OPRE_SUCCESSED);

        TokenHolder holder = new TokenHolder(appKey, secretKey, TokenHolder.ASR_SCOPE);
        holder.refresh();
        String token = holder.getToken();

        // text 的内容为"欢迎使用百度语音合成"的urlencode,utf-8 编码
        // 可以百度搜索"urlencode"
        // 此处2次urlencode， 确保特殊字符被正确编码
        String params = "tex=" + ConnUtil.urlEncode(ConnUtil.urlEncode(text));
        params += "&per=" + per;
        params += "&spd=" + spd;
        params += "&pit=" + pit;
        params += "&vol=" + vol;
        params += "&cuid=" + cuid;
        params += "&tok=" + token;
        params += "&aue=" + aue;
        params += "&lan=zh&ctp=1";
        System.out.println(url + "?" + params); // 反馈请带上此url，浏览器上可以测试
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setConnectTimeout(5000);
        PrintWriter printWriter = new PrintWriter(conn.getOutputStream());
        printWriter.write(params);
        printWriter.close();
        String contentType = conn.getContentType();
        if (contentType.contains("audio/")) {
            byte[] bytes = ConnUtil.getResponseBytes(conn);
            String format = getFormat(aue);

            String url = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_url")) + File.separator + "yuyin";
            String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + "yuyin";
            File fileDir = new File(newDir);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            String filePath= File.separator + PkUtil.getUUID() +"."+ format;
            File file = new File(newDir+filePath); // 打开mp3文件即可播放
            FileOutputStream os = new FileOutputStream(file);
            os.write(bytes);
            os.close();
            System.out.println("audio file write to " + file.getAbsolutePath());
            map.put("msg",GlobalConstant.OPRE_SUCCESSED);
            map.put("url",url+filePath);
            return map;
        } else {
            map.put("code","0");
            map.put("msg","语音合成失败，请重试！");
            return map;
        }
    }

    // 下载的文件格式, 3：mp3(default) 4： pcm-16k 5： pcm-8k 6. wav
    private static String getFormat(int aue) {
        String[] formats = {"mp3", "pcm", "pcm", "wav"};
        return formats[aue - 3];
    }
    public static void main(String[] args) throws LineUnavailableException,
            UnsupportedAudioFileException, IOException {
        File file = new File("D:/Program Files (x86)/Apache22/htdocs/pdsciupload/yuyin/f76da54caa064722b1ba67b5cacab534.mp3");
        Clip clip = AudioSystem.getClip();
        AudioInputStream ais = AudioSystem.getAudioInputStream(file);
        clip.open(ais);
        System.out.println( clip.getMicrosecondLength() / 1000000D + " s" );//获取音频文件时长
    }
}
