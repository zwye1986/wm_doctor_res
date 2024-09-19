package com.pinde.core.license;

import com.javax0.license3j.licensor.License;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ServerUtil;
import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.lic.hardware.MechineInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PdLicense {
	
	private static Logger logger = LoggerFactory.getLogger(PdLicense.class);
	
	private static String MACHINE_ID = "machineId";
	private static String ISSUE_DATE = "issueDate";
	private static String VALID_DATE = "validDate";
	private static String WORK_STATION = "workStation";
	
	private static String machineId = "59adb24e-f3cd-3e02-97f0-5b395827453f";
	private static String issueDate = "2014-04-01";
	private static String validDate = "2014-04-30";
	private static String workStation = "";
	               
	private static byte [] digest = new byte[] {
			(byte)0x36,
			(byte)0xAB, (byte)0x97, (byte)0xDE, (byte)0x20, (byte)0x9A, (byte)0x6D, (byte)0xF9, (byte)0xBB,
			(byte)0x43, (byte)0xA8, (byte)0x8F, (byte)0xCD, (byte)0xD3, (byte)0x8F, (byte)0x14, (byte)0x00,
			(byte)0xAE, (byte)0xEC, (byte)0x9D, (byte)0x82, (byte)0x06, (byte)0x54, (byte)0x64, (byte)0x76,
			(byte)0x21, (byte)0x8A, (byte)0x64, (byte)0x49, (byte)0x46, (byte)0x3A, (byte)0xB0, (byte)0xD3,
			(byte)0xF6, (byte)0xDB, (byte)0xB1, (byte)0xC1, (byte)0x31, (byte)0xFC, (byte)0xC2, (byte)0xCE,
			(byte)0x9A, (byte)0x04, (byte)0x30, (byte)0x39, (byte)0x61, (byte)0x28, (byte)0x8A, (byte)0xFA,
			(byte)0x38, (byte)0x36, (byte)0xF6, (byte)0x3C, (byte)0x0B, (byte)0x36, (byte)0xAC, (byte)0x5E,
			(byte)0xC6, (byte)0x2B, (byte)0x57, (byte)0xB5, (byte)0x52, (byte)0xD0, (byte)0x1D,
	};
	
//	private static License lic = null;
	
	private static boolean inited = false;

//	private static void init(){
//		lic = new License();
//		try {
//			lic.loadKeyRing(SpringUtil.getResource("classpath:pubring.gpg").getFile(), digest);
////			lic.loadKeyRingFromResource("pubring.gpg", digest);
//			File licenseFile = SpringUtil.getResource("classpath:license.key").getFile();
//			lic.setLicenseEncodedFromFile(licenseFile.getAbsolutePath());
//			machineId = lic.getFeature(MACHINE_ID);
//			issueDate = lic.getFeature(ISSUE_DATE);
//			validDate = lic.getFeature(VALID_DATE);
//			workStation = lic.getFeature(WORK_STATION);
//			inited = true;
//		}catch (Exception e) {
//			logger.error("系统验证错误，无法获取公钥或License文件！",e);
//			inited = false;
//		}	
//	}
	
	public static boolean checkLicense(String licenseStringEncoded) {
//		if(inited==false){
//			init();
//		}
//		if(inited==false){
//			return false;
//		}
		
		License lic = new License();
		try {
			lic.loadKeyRing(SpringUtil.getResource("classpath:pubring.gpg").getFile(), digest);
			lic.setLicenseEncoded(licenseStringEncoded);
				
			machineId = lic.getFeature(MACHINE_ID);
			issueDate = lic.getFeature(ISSUE_DATE);
			validDate = lic.getFeature(VALID_DATE);
			workStation = lic.getFeature(WORK_STATION);
			if(lic.isVerified()){
				inited = true;
			}else{
				inited = false;
			}
		}catch (Exception e) {
			logger.error("系统验证错误，无法获取公钥或License文件！",e);
			inited = false;
		}
		
		
		try {			
			if(StringUtil.isNotEquals(ServerUtil.getMachineId(), machineId)){
				logger.debug("系统验证错误，硬件ID不匹配！");				
				return false;				
			}			
			return true;
		} catch (Exception e) {
			logger.error("系统验证错误，无法获取公钥或License文件！",e);
			return false;
		}
	}
	
	public static boolean checkValid(){		
//		if(inited==false){
//			init();
//		}
		if(inited==false){
			return false;
		}
		String today = DateUtil.getCurrDate();
		if (issueDate != null) {
			if (DateUtil.signDaysBetweenTowDate(issueDate,today)>0) {
				logger.debug("系统验证错误，发行日期太晚了,系统时间被更改了！");		
				return false;
			}				
		}
		
		if (validDate != null) {
			if (DateUtil.signDaysBetweenTowDate(today,validDate)>0) {
				logger.debug("系统验证错误，License过期！");	
				return false;
			}
		}
		return true;		
	}
	
//	public static String getMachineId(){
//	    return MechineInfo.getMachineId();
//	}
	
	public static void main(String args [] ){
		
	}
	
	public static List<String> getLicensedWorkStation(){
		List<String> workStations = new ArrayList<String>();
		String [] wsids = workStation.split(",");
		if(wsids!=null){
			for(String wsid : wsids){
				workStations.add(wsid);
			}
		}
		return workStations;
	}
	

	public static String getIssueDate() {
		return issueDate;
	}

	public static void setIssueDate(String issueDate) {
		PdLicense.issueDate = issueDate;
	}

	public static String getValidDate() {
		return validDate;
	}

	public static void setValidDate(String validDate) {
		PdLicense.validDate = validDate;
	}

}
