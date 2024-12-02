package com.pinde.core.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
//	public final static String MD5(String s) {
//		try {
//			byte[] btInput = s.getBytes();
//			MessageDigest mdInst = MessageDigest.getInstance("MD5");
//			mdInst.update(btInput);
//			byte[] md = mdInst.digest();
//			StringBuffer sb = new StringBuffer();
//			for (int i = 0; i < md.length; i++) {
//				int val = ((int) md[i]) & 0xff;
//				if (val < 16)
//					sb.append("0");
//				sb.append(Integer.toHexString(val));
//
//			}
//			return sb.toString().toLowerCase();
//		} catch (Exception e) {
//			return null;
//		}
//	}

	public static String parseMultipleVal(String val,String sp){
		if(StringUtil.isNotBlank(val) && StringUtil.isNotBlank(sp)){
			String[] results = val.split(sp);
			if(results!=null){
				int length = results.length;
				for(int i = 0 ; i < length ; i++){
					results[i] = toJsonString(results[i]);
				}
				return StringUtils.join(results,sp);
			}
		}
		return toJsonString("");
	}


	
	public static int countMatches(String str,String rex){
		return StringUtils.countMatches(str, rex);
	}

	public static String uncapitalise(String str){
		return StringUtils.uncapitalize(str);
	}
	
	public static String defaultString(String str){
		return StringUtils.defaultString(str);
	}
	
	public static String defaultIfEmpty(String str,String defaultStr){
		return StringUtils.defaultIfEmpty(str,defaultStr);
	}
	
	public static boolean isBlank(String str){
		return StringUtils.isBlank(str);
	}
	
	public static boolean isNotBlank(String str){
		return StringUtils.isNotBlank(str);
	}
	
	public static boolean isEmpty(String str){
		return StringUtils.isEmpty(str);
	}
	
	public static boolean isNotEmpty(String str){
		return StringUtils.isNotEmpty(str);
	}
	
	public static String leftPad(String str,int size,String padStr){
		return StringUtils.leftPad(str, size, padStr);
	}
	//only for web jsp use
	public static String toHtml(String str){
		return defaultString(StringEscapeUtils.escapeHtml3(str));
	}
	
	public static String transferredXml(String strSource, String strFrom, String strTo) {
		if (strSource == null) return null;
		int i = 0;
		if ((i = strSource.indexOf(strFrom, i)) >= 0) {
			char[] cSrc = strSource.toCharArray();
			char[] cTo = strTo.toCharArray();
			int len = strFrom.length();
			StringBuffer buf = new StringBuffer(cSrc.length);
			buf.append(cSrc, 0, i).append(cTo);
			i += len;
			int j = i;
			while ((i = strSource.indexOf(strFrom, i)) > 0) {
				buf.append(cSrc, j, i - j).append(cTo);
				i += len;
				j = i;
			}
			buf.append(cSrc, j, cSrc.length - j);
			return buf.toString();
		}
		return strSource;
	}
	
	public static String abbreviate(String str,int maxWidth){
		return StringUtils.abbreviate(str, maxWidth);
	}
	
	public static String toString(Object object) {
    	return ToStringBuilder.reflectionToString(object,ToStringStyle.MULTI_LINE_STYLE);
    }

	/**
	 * Clob类型转成String
	 */
	public static String ClobToString(Clob clob) throws SQLException, IOException {

		String reString = "";
		Reader is = clob.getCharacterStream();// 得到流
		BufferedReader br = new BufferedReader(is);
		String s = br.readLine();
		StringBuffer sb = new StringBuffer();
		while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
			sb.append(s);
			s = br.readLine();
		}
		reString = sb.toString();
		return reString;
	}

	public static boolean isEquals(String str1,String str2){
		return StringUtils.equals(str1, str2);
	}

    /**
	 * 比较多个值与第一个值是否相同
	 * @param str1
	 * @param str2
	 * @return
	 * @author macs
	 * @since 2017-08-08
     */
	public static boolean isEquals(String str1, String... str2){
		for(String str : str2){
			if(StringUtils.equals(str1, str)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isNotEquals(String str1,String str2){
		return !StringUtils.equals(str1, str2);
	}

	public static boolean isNotNullAndEquala(String str1,String str2,String str){
		if (isEmpty(str1) || isEmpty(str2) || isEmpty(str)){
			return false;
		}
		if (isNotEquals(str1,str)){
			return false;
		}
		if (isNotEquals(str2,str)){
			return false;
		}
		return true;
	}

	public static String toLowerCase(String str){
		return str.toLowerCase();
	}
	
	public static boolean contains(String str, String searchStr){
		return StringUtils.contains(str, searchStr);
	}

	public static boolean contains2(String str, String searchStr){
		if( StringUtils.isBlank(str)){
			return false;
		}
		List<String> result = Arrays.asList(str.split(","));
		return result.contains(searchStr);
	}

	public static String capitalize(String str){
		return StringUtils.capitalize(str);
	}
	
	public static String uncapitalize(String str){
		return StringUtils.uncapitalize(str);
	}

	public static String getFileSuffix(String str){
		if(isNotBlank(str))
		{
			if(str.lastIndexOf(".")>0&&(str.lastIndexOf(".") + 1)<str.length()) {
				return str.substring(str.lastIndexOf(".") + 1);
			}
		}
		return "";
	}
	
	/**  
	  * 替换一个字符串中的某些指定字符  
	  * @param strData String 原始字符串  
	  * @param regex String 要替换的字符串  
	  * @param replacement String 替代字符串  
	  * @return String 替换后的字符串  
	  */  
	 public static String replaceString(String strData, String regex,   
	         String replacement)   
	 {   
	     if (strData == null)   
	     {   
	         return null;   
	     }   
	     int index;   
	     index = strData.indexOf(regex);   
	     String strNew = "";   
	     if (index >= 0)   
	     {   
	         while (index >= 0)   
	         {   
	             strNew += strData.substring(0, index) + replacement;   
	             strData = strData.substring(index + regex.length());   
	             index = strData.indexOf(regex);   
	         }   
	         strNew += strData;   
	         return strNew;   
	     }   
	     return strData;   
	 }   
	 
	 /**  
	  * 替换字符串中特殊字符  
	  */  
	public static String encodeString(String strData)   
	 {   
	     if (strData == null)   
	     {   
	         return "";   
	     }   
	     strData = replaceString(strData, "&", "&amp;");   
	     strData = replaceString(strData, "<", "&lt;");   
	     strData = replaceString(strData, ">", "&gt;");   
	     strData = replaceString(strData, "\'", "&apos;");
	     strData = replaceString(strData, "\"", "&quot;");   
	     return strData;
	 }
	/**
	 *
	 */
	public static String encodeString2(String strData)
	{
		if (strData == null)
		{
			return "";
		}
		strData = replaceString(strData, "\\", "\\\\");//单个反斜杠转为双个反斜杠 \\
		strData = replaceString(strData, "\'", "\\'");//单引号转为 \'
		strData = replaceString(strData, "\"", "\\\"");//双引号转为 \"
		return strData;
	}
	/**
	  * 还原字符串中特殊字符  
	  */  
	public static String decodeString(String strData)   
	 {   
	     strData = replaceString(strData, "&lt;", "<");   
	     strData = replaceString(strData, "&gt;", ">");   
	     strData = replaceString(strData, "&apos;", "\'");
	     strData = replaceString(strData, "&quot;", "\"");   
	     strData = replaceString(strData, "&amp;", "&");   
	     return strData;   
	 }  
	
	/*public static final Set hashSet; 
	 public static final char substitute = '\uFFFD'; 
	    static { 
	        final String escapeString = "\u0000\u0001\u0002\u0003\u0004\u0005" + 
	            "\u0006\u0007\u0008\u000B\u000C\u000E\u000F\u0010\u0011\u0012" + 
	            "\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001A\u001B\u001C" + 
	            "\u001D\u001E\u001F\uFFFE\uFFFF"; 

	        hashSet = new HashSet(); 
	        for (int i = 0; i < escapeString.length(); i++) { 
	         hashSet.add(escapeString.charAt(i)); 
	        } 
	    } */

	    public static String escapeCharacters(String xmlStr) {  
	    	       StringBuilder sb = new StringBuilder();  
	    	       char[] chs = xmlStr.toCharArray();  
	    	       //System.out.println("filter before=" +chs.length);  
	    	       for(char ch : chs) {  
	    	           if((ch >= 0x00 && ch <= 0x08)  
	    	               || (ch >= 0x0b && ch <= 0x0c)  
	    	               || (ch >= 0x0e && ch <= 0x1f)) {  
	    	               //eat...  
	    	           } else {  
	    	               sb.append(ch);  
	    	           }  
	    	       }  
	    	       //System.out.println("filter after=" +sb.length());  
	    	       return sb.toString();  
	    	   }  

	    public static String buildFieldName(Field field) {
	    	StringBuffer fieldName = new StringBuffer();
			for (int j = 0; j < field.getName().length(); j++) {
				 char c = field.getName().charAt(j);
				 if (Character.isLowerCase(c)) {
					 fieldName.append(c);
				 } else {
					 fieldName.append("_" + Character.toLowerCase(c));
				 }
			}
			return fieldName.toString();
	    }
	    
	    /**
	      *
	      * @param str
	      *         需要过滤的字符串
	      * @return
	      * @Description:过滤数字以外的字符
	      */
	     public static String filterUnNumber(String str) {
	         // 只允数字
	         String regEx = "[^0-9]";
	         Pattern p = Pattern.compile(regEx);
	         Matcher m = p.matcher(str);
	     //替换与模式匹配的所有字符（即非数字的字符将被""替换）
	         return m.replaceAll("").trim();
	 
	    }

	   //字符串替换
	 	public static String replace(String strSource, String strFrom, String strTo) {
	 		if (strSource == null) {
	 			return null;
	 		}
	 		int i = 0;
	 		if ((i = strSource.indexOf(strFrom, i)) >= 0) {
	 			char[] cSrc = strSource.toCharArray();
	 			char[] cTo = strTo.toCharArray();
	 			int len = strFrom.length();
	 			StringBuffer buf = new StringBuffer(cSrc.length);
	 			buf.append(cSrc, 0, i).append(cTo);
	 			i += len;
	 			int j = i;
	 			while ((i = strSource.indexOf(strFrom, i)) > 0) {
	 				buf.append(cSrc, j, i - j).append(cTo);
	 				i += len;
	 				j = i;
	 			}
	 			buf.append(cSrc, j, cSrc.length - j);
	 			return buf.toString();
	 		}
	 		return strSource;
	 	}
	 	

		
		/**
	     * 去除html代码
	     * @param inputString
	     * @return
	     */
	    public static String Html2Text(String inputString) {
	    	
	    	 String htmlStr = inputString; 
		        String textStr ="";
		        java.util.regex.Pattern p_script;
		        java.util.regex.Matcher m_script;
		        java.util.regex.Pattern p_style;
		        java.util.regex.Matcher m_style;
		        java.util.regex.Pattern p_html;
		        java.util.regex.Matcher m_html;          
		        java.util.regex.Pattern p_ba;
		        java.util.regex.Matcher m_ba;
		        java.util.regex.Pattern p_ba2;
		        java.util.regex.Matcher m_ba2;
		        
		        try {
		            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; 
		            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; 
		            String regEx_html = "<[^>]+>";
		            String patternStr = "\\s+";
		            String patternStr2 = "&[a-zA-Z0-9]+;";
		            
		            p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
		            m_script = p_script.matcher(htmlStr);
		            htmlStr = m_script.replaceAll(""); 

		            p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
		            m_style = p_style.matcher(htmlStr);
		            htmlStr = m_style.replaceAll(""); 
		         
		            p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
		            m_html = p_html.matcher(htmlStr);
		            htmlStr = m_html.replaceAll(""); 
		            
		            p_ba = Pattern.compile(patternStr,Pattern.CASE_INSENSITIVE);
		            m_ba = p_ba.matcher(htmlStr);
		            htmlStr = m_ba.replaceAll(""); 
		            
		            p_ba2 = Pattern.compile(patternStr2,Pattern.CASE_INSENSITIVE);
		            m_ba2 = p_ba2.matcher(htmlStr);
		            htmlStr = m_ba2.replaceAll(""); 
		         
		         textStr = htmlStr;
		         
		        }catch(Exception e) {
		                    System.err.println("Html2Text: " + e.getMessage());
		        }          
		        return textStr;
	    	
	    }

	    
	    /**
	     * 过滤字符串中html代码并截取字符串
	     * @param baseString 原始字符串
	     * @param length 截取长度
	     * @param isAdd 是否加省略号 ture：添加，false：不添加
	     * @return 
	     */
	    public static String cutString(String baseString,int length,boolean isAdd){
	    	String newString = Html2Text(baseString);
	    	if(newString!=null && !"".equals(newString)){
	    		newString = newString.trim();
	    			if(isAdd){
	    				newString = subString(newString, length, "......");
		    		} else {
		    			newString = subString(newString, length, "");
		    		}
	    	}
	    	return newString;
	    }
	    /**
	     * 过滤字符串中html代码并截取字符串
	     * @param baseString 原始字符串
	     * @param length 截取长度
	     * @param isAdd 是否加省略号 ture：添加，false：不添加
	     * @param count 省略号个数：3：（3个），其他（6个）
	     * @return
	     */
	    public static String cutString(String baseString,int length,boolean isAdd,int count){
	    	String newString = Html2Text(baseString);
	    	if(newString!=null && !"".equals(newString)){
	    		newString = newString.trim();
	    			if(isAdd){
	    				if(count==3){
	    					newString = subString(newString, length, "...");
	    				} else{
	    					newString = subString(newString, length, "......");
	    				}
		    		} else {
		    			newString = subString(newString, length, "");
		    		}
	    		
	    	}
	    	return newString;
	    }
	    
	    public static String subString(String text, int length, String endWith) {         
	    	        int textLength = text.length();   
	    	        int byteLength = 0;   
	    	        StringBuffer returnStr =  new StringBuffer();   
	    	        for(int i = 0; i<textLength && byteLength < length*2; i++){   
	    	            String str_i = text.substring(i, i+1);    
	    	            if(str_i.getBytes().length == 1){//英文   
	    	                byteLength++;   
	    	            }else{//中文   
	    	                byteLength += 2 ;   
	    	           }   
	    	            returnStr.append(str_i);   
	    	        }   
	    	        try {   
	    	            if(byteLength<text.getBytes("GBK").length){//getBytes("GBK")每个汉字长2，getBytes("UTF-8")每个汉字长度为3   
	    	                returnStr.append(endWith);   
	    	            }   
	    	        } catch (UnsupportedEncodingException e) {   
	    	            e.printStackTrace();   
	    	        }   
	    	        return returnStr.toString();   
	    	    } 
	    
	    /**
	     * 用红色标记字符串中的关键字
	     * @param baseString 原字符串
	     * @param keyword 要标记的关键字
	     * @return 标记后的字符串
	     */
	    public static String markKeyword(String baseString,String keyword){
	    	if(baseString!=null&&!"".equals(baseString)&&keyword!=null&&!"".equals(keyword)){
	    		baseString = replace(baseString,keyword,"<font style='color:red;'>"+keyword+"</font>");
	    	}
	    	return baseString;
	    }
	    /**
		 * Method split.
		 * 通过分隔符（字符串）将字符串分隔为字符串数组
		 * @param strSc 源字符串
		 * @param separator 分隔符（字符串）
		 * @return String[] 字符串数组
		 */
		public static String[] split(String strSc, String separator) {
			String temp = strSc + "";
			String[] ret = null;
			try {
				if (temp != null && separator != null) {
					int lent = countSeparator(temp, separator) + 1;
					ret = new String[lent];
					int endindex = 0;
					int sptlent = separator.length();
					for (int i = 0; i < lent - 1; i++) {
						endindex = temp.indexOf(separator);
						ret[i] = temp.substring(0, endindex);
						temp = temp.substring(endindex + sptlent);
					}
					ret[lent - 1] = temp;
				}
				else if (temp != null && separator == null) {
					ret = new String[1];
					ret[0] = temp;
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return ret;
		}
		/**
		 * Method countSeparator.
		 * 计算一个字符串中分隔符（字符串）的个数
		 * @param strSc 源字符串
		 * @param separator 分隔符（字符串）
		 * @return int 分隔符（字符串）的个数
		 */
		public static int countSeparator(String strSc, String separator) {
			int count = 0;
			if (strSc != null && separator != null) {
				int endindex = 0;
				int sptlent = separator.length();
				while ((endindex = strSc.indexOf(separator, endindex)) > -1) {
					endindex += sptlent;
					count++;
				}
			}
			return count;
		}
		public static String getCodeString(String codeStr){
			if(codeStr==null||"".equals(codeStr)){ 
				return "";
			}
			String[] codes = codeStr.split(",");
			 for (int i = 0; i < codes.length; i++) {    
			   for (int j = i + 1; j < codes.length; j++) {   
				   int n1 = Integer.parseInt((String) codes[i]);
				   int n2 = Integer.parseInt((String) codes[j]);
				   if (n1 > n2) {   
					   codes[i]=n2+"";
					   codes[j]=n1+"";
			    	}   
			   }   
			 }   
			String str = "";
			for(int i=0;i< codes.length;i++){
				int code = Integer.parseInt((String) codes[i]); 
				if(i==0){
					str += code;
				}else {
					int precode = Integer.parseInt((String)codes[i-1]);
					if(code-precode==1){
						str+=","+code;
					}else {
						str+="_"+code;
					}
				}
			}
			String resultStr = "";
			String[] result = str.split("_");
			for(int i=0;i<result.length;i++){
				String temp = result[i];
				String[] fe = temp.split(",");
				if(resultStr.length()>0){
					resultStr +=",";
				}
				if(fe.length==1){
					resultStr +=fe[0];
				}else {
					resultStr += fe[0]+"-"+fe[fe.length-1];
				}
			}
			return resultStr;
		}
		/**
		 * 将Null或空字符串替换为指定字符串
		 * @param baseString 原字符串
		 * @param newString 替换字符串
		 * @return
		 */
		public static  String replaceNull(String baseString,String newString){
			if(StringUtil.isEmpty(baseString)||StringUtil.isBlank(baseString)){
				baseString = newString;
			}
			return baseString;
		}
		
		public static String toJsonString(String json){
			return JSON.toJSONString(json);
		}

	public static String toFormatString(String str){
		Float b = Float.parseFloat(str);
		int num = 1; //设几就是保留几位小数
		String numstr = "";
		numstr = new BigDecimal(b).setScale(num, BigDecimal.ROUND_HALF_UP) + "";
		return numstr;
	}
		
		public static void main(String [] args){
//			String json = "\"\t";
//			System.out.println(toJsonString(json));
//			String s="2016325300BYY001";
//			System.out.println(completeNoSplit(s));
			System.out.println(toFormatString("3.199999999907"));

		}

		public static  String sub(String text,Integer start,Integer end){
			if (StringUtil.isEmpty(text) || text.length()<start){
				return "";
			}
			if (text.length()<end){
				end=text.length();
			}
			String substring = text.substring(start, end);
			return substring;
		}

		/**
		 * 剔除<html>的标签 
		 * @param content 要处理的内容
		 * @param num  截取保留的位数
		 * @return
		 */
		public static String escapeHtmlTag(String content, String num) {
			if (StringUtil.isNotBlank(content)) {
				content = Html2Text(content);
				if (StringUtil.isNotBlank(num)) {
					content = cutString(content,Integer.parseInt(num),true);
				}
			}
			return content;
		}
		
		public static Integer stringToInt(String str){
			try{
				Float f = Float.parseFloat(str);
				int i = (int) (f/1);
				return i;
			}catch(Exception e){
				e.printStackTrace();
				return 0;	
			}
			
		}
		public static String completeNoSplit(String str){
			if(StringUtil.isBlank(str))
			{
				return "";
			}
			String result="";
			try {
				result = str.substring(0, 4) + " " + str.substring(4, 6) + " " + str.substring(6, 10) + " " + str.substring(10, 13) + " " + str.substring(13, 16);
			}catch (Exception e)
			{
				result=str;
			}
			return result;
		}

	public static Boolean objIsExist(Object object, List<Object> objectList){
		if(objectList != null && objectList.size() > 0 && object != null && objectList.contains(object)){
			return true;
		}
		return false;
	}

	/**
	 * *号加密
	 * @param name
	 * @return
	 */
	public static String replaceNameX(String name){
		String res = "";
		if (name.length() == 2){
			res = name.replaceAll("^.", "*");
		}else if (name.length() > 2){
			res = name.replaceAll("(?<=.).(?=.)", "*");
		}
		return res;

	}


	/**
	 * 计算百分比
	 * @param divisor	除数
	 * @param dividend 被除数
	 * @return
	 */
	public static String calculatePercentage(String divisor,String dividend ){
		if (StringUtil.isEmpty(divisor) || StringUtil.isEmpty(dividend)){
			return "";
		}
		if (dividend.compareTo("0")==0 && divisor.compareTo("0")==0 ){
			return "0.00%";
		}
		if (dividend.compareTo("0")==0){
			return "100.00%";
		}
		double p3 = Double.parseDouble(divisor) / Double.parseDouble(dividend);
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(2);
		double result =1.0;
		result=result-p3;
		return nf.format(result);
	}


	public static String convertYearNum(String date){
		ArrayList<String> list = new ArrayList<>();
		list.add("〇");
		list.add("一");
		list.add("二");
		list.add("三");
		list.add("四");
		list.add("五");
		list.add("六");
		list.add("七");
		list.add("八");
		list.add("九");

		StringBuffer buffer=new StringBuffer();
		if (StringUtil.isNotBlank(date)){
			for (int i = 0; i < date.length(); i++) {
				if (i<4 || i==6){
					int num = Integer.parseInt(date.substring(i, i + 1));
					buffer=buffer.append(list.get(num));
				}
				if (i==4){
					buffer=buffer.append("年");
				}
				if (i==5 && !date.substring(i, i + 1).equals("0")){
					buffer=buffer.append("十");
				}
				if (i==6){
					buffer.append("月");
					return buffer.toString();
				}
			}
		}
		return buffer.toString();
	}


}
