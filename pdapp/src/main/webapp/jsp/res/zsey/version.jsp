<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	"resultId": ${resultId},
	"resultType": ${pdfn:toJsonString(resultType)},
	"data": {
		"iosVersion" : "1.0.11",
		"iosURL" : "itms-services://?action=download-manifest&url=https://app.njpdxx.com/ios/plists/res_zsey/res_zsey_ios_1.0.11_release.plist",
		"androidVersion" : "1.0.13",
		"androidURL" : "https://app.njpdxx.com/ios/res_zsey/res_zsey_android_1.0.13_release.apk",
		"updateDesc" : "1.2018-01-09 带教老师端功能优化."
	}
}
