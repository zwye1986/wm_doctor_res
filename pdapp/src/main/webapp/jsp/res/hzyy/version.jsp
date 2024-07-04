<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)},
    "data": {
    	"iosVersion" : "1.0.7",
    	"iosURL" : "itms-services://?action=download-manifest&url=https://app.njpdxx.com/ios/plists/res_hzyy/res_hzyy_ios_1.0.7_release.plist",
    	"androidVersion" : "1.0.7",
    	"androidURL" : "https://app.njpdxx.com/ios/res_hzyy/res_hzyy_android_1.0.7_release.apk",
    	"updateDesc" : "1.系统更新."
    }
}
