package com.pinde.core.util;

import java.util.UUID;

//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;

public class PkUtil {

	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static String getGUID() {
		return UUID.randomUUID().toString();
	}
	
	public static void main(String [] args){
		for(int i=0;i<10000;i++){
			System.err.println(getUUID());			
		}
	}
	
}
