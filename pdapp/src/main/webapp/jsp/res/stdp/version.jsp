<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)},
    "data": {
    	"iosVersion" : "1.0.0",
    	"iosURL" : "itms-services://?action=download-manifest&url=https://app.njpdxx.com/ios/plists/res_stdp_ios_1.0.0_debug.plist",
    	"androidVersion" : "1.0",
    	"androidURL" : "https://app.njpdxx.com/ios/res_stdp/res_stdp_android_1.0_debug.apk",
    	"updateDesc" : "1. 初始版本."
    }
}
