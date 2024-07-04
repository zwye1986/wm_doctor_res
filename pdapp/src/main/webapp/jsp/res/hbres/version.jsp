<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	"resultId": ${resultId},
	"resultType": ${pdfn:toJsonString(resultType)},
	"data": {
		"iosVersion" : "1.0.32",
		"iosURL" : "itms-services://?action=download-manifest&url=https://app.njpdxx.com/ios/plists/res_hbres/res_hbres_ios_1.0.32_release.plist",
		"androidVersion" : "1.0.29",
		"androidURL" : "https://app.njpdxx.com/ios/res_hbres/res_hbres_android_1.0.29_release.apk",
		"updateDesc" : "1.2019-01-21 系统优化"
	}
}
