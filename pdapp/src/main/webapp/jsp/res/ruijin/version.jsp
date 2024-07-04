<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)},
    "data": {
    	"iosVersion" : "1.0.12",
    	"iosURL" : "itms-services://?action=download-manifest&url=https://app.njpdxx.com/ios/plists/res_ruijin/res_ruijin_ios_1.0.12_release.plist",
    	"androidVersion" : "1.0.10",
    	"androidURL" : "https://app.njpdxx.com/ios/res_ruijin/res_ruijin_android_1.0.10_release.apk",
		"updateDesc" : "1.2018-11-23 系统更新."
	}
}
