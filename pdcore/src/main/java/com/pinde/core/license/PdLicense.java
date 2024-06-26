package com.pinde.core.license;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.lic.hardware.MechineInfo;
import com.verhas.licensor.License;
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
			(byte)0x06, 
			(byte)0x86, (byte)0x22, (byte)0xBC, (byte)0x44, (byte)0xCF, (byte)0x51, (byte)0xA6, (byte)0x39, 
			(byte)0x01, (byte)0x8F, (byte)0x3D, (byte)0xA1, (byte)0x90, (byte)0x82, (byte)0x6F, (byte)0x4A, 
			(byte)0xBF, (byte)0x6A, (byte)0x5A, (byte)0xF6, (byte)0x43, (byte)0x74, (byte)0x8C, (byte)0xAC, 
			(byte)0x74, (byte)0x77, (byte)0x90, (byte)0x6B, (byte)0x80, (byte)0x05, (byte)0x82, 
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
			if(StringUtil.isNotEquals(MechineInfo.getMachineId(), machineId)){
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
	
	public static String getMachineId(){
	    return MechineInfo.getMachineId();
	}
	
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
