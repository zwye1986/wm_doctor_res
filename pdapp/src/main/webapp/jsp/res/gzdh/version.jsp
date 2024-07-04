<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	"resultId": ${resultId},
	"resultType": ${pdfn:toJsonString(resultType)},
	"data": {
		"iosVersion" : "1.0.5",
		"iosURL" : "itms-services://?action=download-manifest&url=https://app.njpdxx.com/ios/plists/res_gzdh/res_gzdh_ios_1.0.5_release.plist",
		"androidVersion" : "1.0.5",
		"androidURL" : "https://app.njpdxx.com/ios/res_gzdh/res_gzdh_android_1.0.5_release.apk",
		"updateDesc" : "初始版本"
	}
}
