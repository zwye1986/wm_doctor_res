<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	"resultId": ${resultId},
	"resultType": ${pdfn:toJsonString(resultType)},
	"data": {
		"iosVersion" : "1.0.3",
		"iosURL" : "itms-services://?action=download-manifest&url=https://app.njpdxx.com/ios/plists/res_gydxj/res_gydxj_ios_1.0.3_release.plist",
		"androidVersion" : "1.0.3",
		"androidURL" : "https://app.njpdxx.com/ios/res_gydxj/res_gydxj_android_1.0.3_release.apk",
		"updateDesc" : "版本更新"
	}
}
