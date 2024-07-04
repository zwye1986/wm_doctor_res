<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	"resultId": ${resultId},
	"resultType": ${pdfn:toJsonString(resultType)},
	"data": {
		"iosVersion" : "1.0.32",
		"iosURL" : "itms-services://?action=download-manifest&url=https://app.njpdxx.com/ios/plists/res_yun_chaozhou_shixi/res_yun_chaozhou_shixi_ios_1.0.32_release.plist",
		"androidVersion" : "1.0.26",
		"androidURL" : "https://app.njpdxx.com/ios/res_yun_chaozhou_shixi/res_yun_chaozhou_shixi_android_1.0.26_release.apk",
		"updateDesc" : "1.系统更新"
	}
}
