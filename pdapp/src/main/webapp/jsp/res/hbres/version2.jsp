<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	"resultId": ${resultId},
	"resultType": ${pdfn:toJsonString(resultType)},
	"data": {
		"iosVersion" : "1.0.30",
		"iosURL" : "itms-services://?action=download-manifest&url=https://app.njpdxx.com/ios/plists/res_yun_zhengzhou/res_yun_zhengzhou_ios_1.0.30_release.plist",
		"androidVersion" : "1.0.24",
		"androidURL" : "https://app.njpdxx.com/ios/res_yun_zhengzhou/res_yun_zhengzhou_android_1.0.24_release.apk",
		"updateDesc" : "1.系统更新"
	}
}
