<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)},
    "data": {
    	"iosVersion" : "1.0.6",
    	"iosURL" : "itms-services://?action=download-manifest&url=https://app.njpdxx.com/ios/plists/res_xnres/res_xnres_ios_1.0.6_release.plist",
    	"androidVersion" : "1.0.5",
    	"androidURL" : "https://app.njpdxx.com/ios/res_xnres/res_xnres_android_1.0.5_release.apk",
		"updateDesc" : "1.2018-08-03 学员端改版."
    }
}
