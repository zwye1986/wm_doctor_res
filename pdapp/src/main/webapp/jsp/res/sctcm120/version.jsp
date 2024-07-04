<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)},
    "data": {
    	"iosVersion" : "1.0.16",
    	"iosURL" : "itms-services://?action=download-manifest&url=https://app.njpdxx.com/ios/plists/res_sctcm120/res_sctcm120_ios_1.0.16_release.plist",
    	"androidVersion" : "1.0.12",
    	"androidURL" : "https://app.njpdxx.com/ios/res_sctcm120/res_sctcm120_android_1.0.12_release.apk",
		"updateDesc" : "1.功能优化."    }
}
