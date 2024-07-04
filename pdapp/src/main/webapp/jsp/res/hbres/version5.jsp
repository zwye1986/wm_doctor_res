<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	"resultId": ${resultId},
	"resultType": ${pdfn:toJsonString(resultType)},
	"data": {
		"iosVersion" : "1.0.31",
		"iosURL" : "itms-services://?action=download-manifest&url=https://app.njpdxx.com/ios/plists/res_yun_zunyiwuyuan/res_yun_zunyiwuyuan_ios_1.0.31_release.plist",
		"androidVersion" : "1.0.25",
		"androidURL" : "https://app.njpdxx.com/ios/res_yun_zunyiwuyuan/res_yun_zunyiwuyuan_android_1.0.25_release.apk",
		"updateDesc" : "1.系统更新"
	}
}
