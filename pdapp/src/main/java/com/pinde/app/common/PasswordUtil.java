package com.pinde.app.common;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.util.PasswordHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Controller
public class PasswordUtil {
	
	private static Logger logger = LoggerFactory.getLogger(PasswordUtil.class);

	private static String password="pinde";
	private static String SECURITY_SUPPPWD="2a93ef719a22f79028835959bb90a8ef";
	public  static  boolean isRootPass(String userPass)
	{
		if(PasswordHelper.encryptPassword(password,userPass).equals(getRootPass()))
		{
			return true;
		}
		return false;
	}
	public static  String getRootPass()
	{
		InputStream inputStream = PasswordUtil.class.getClassLoader().getResourceAsStream("appCfg.properties");
		Properties prop = new Properties();
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		String	rootPass = prop.getProperty("app.root.password");
		if(StringUtil.isNotBlank(rootPass)) {
				return rootPass;
		}
		return SECURITY_SUPPPWD;
	}

	public static void main(String[] args) {
		//System.out.println("根据输入的规则"+password+"加密后的密文是:"+PasswordHelper.encryptPassword(password,"@teh666"));
	}
}
