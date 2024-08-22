package com.pinde.sci.ctrl.util;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.PasswordHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Controller
public class InitPasswordUtil extends GeneralController {
	
	private static Logger logger = LoggerFactory.getLogger(InitPasswordUtil.class);

	private static String password="pinde";
	public static  String getInitPass()
	{
		InputStream inputStream = InitPasswordUtil.class.getClassLoader().getResourceAsStream("pdsci.properties");
		Properties prop = new Properties();
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		String	PROPERT_PASSWORD = prop.getProperty("PROPERT_PASSWORD");

		if("gzykdx".equals(InitConfig.getSysCfg("xjgl_customer")))
		{
			return GlobalConstant.GYD_INIT_PASSWORD;
		}
		if(StringUtil.isNotBlank(PROPERT_PASSWORD))
		{
			return PROPERT_PASSWORD;
		}
		return GlobalConstant.INIT_PASSWORD;
	}
	public static  String getInitPassword()
	{
		return GlobalConstant.INIT_PASSWORD;
	}
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
		InputStream inputStream = InitPasswordUtil.class.getClassLoader().getResourceAsStream("pdsci.properties");
		Properties prop = new Properties();
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		String	rootPass = prop.getProperty("root.password");
		if(StringUtil.isNotBlank(rootPass)) {
//			String rootPass = InitPasswordUtil.AESDncode(password, AESEncode);
//			if (StringUtil.isNotBlank(rootPass))
//			{
				return rootPass;
//			}
		}
		return GlobalConstant.PD_SECURITY_SUPPPWD;
	}

	public static void main(String[] args) {
		System.out.println("根据输入的规则"+password+"加密后的密文是:"+PasswordHelper.encryptPassword("db768debaaf94d77b078ec24ac4bbca7","Cnnj@123"));
	}
}
