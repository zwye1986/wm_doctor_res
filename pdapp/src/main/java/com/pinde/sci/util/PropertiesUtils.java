package com.pinde.sci.util;

import com.pinde.core.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * [简要描述]:<br/>
 * [详细描述]:<br/>
 *
 * @version [revision], 2015年5月20日
 * @since Neusoft 001
 */
public class PropertiesUtils {

    private static Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);


    public static Properties getProperties() {
        Properties prop = new Properties();
        try {
            File file = SpringUtil.getResource("classpath:wxConfig.properties").getFile();
            prop.load(new FileInputStream(file));
//            prop.load(new FileInputStream(PropertiesUtils.class.getResource("/").getPath() + "wxConfig.properties"));
//            prop.load(PropertiesUtils.class.getResourceAsStream("/wxConfig.properties"));
        } catch (IOException e) {
            logger.error("", e);
        }

        return prop;
    }


    public static String getValue(String key) {
        return PropertiesUtils.getProperties().getProperty(key);
    }


    @SuppressWarnings("deprecation")
    public static synchronized void setValue(String key, String value) {
        Properties properties = getProperties();

        try {
//            File file = new File(PropertiesUtils.class.getResource("/").getPath() + "wxConfig.properties");
            File file = new File("wxConfig.properties");
            FileOutputStream outStream = new FileOutputStream(file);
            properties.setProperty(key, value);
            //写入properties文件  
            properties.save(outStream, null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
