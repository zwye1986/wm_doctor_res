<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	"resultId":
	${resultId},
	"resultType":
	${pdfn:toJsonString(resultType)},
	"data": {
		"iosVersion": "1.1.7",
		"iosURL": "itms-services://?action=download-manifest&url=https://app.njpdxx.com/ios/plists/res_nfyy_ios_1.1.7_release.plist",
		"androidVersion": "1.1.10",
		"androidURL": "http://app.njpdxx.com/ios/res_nfyy/res_nfyy_android_1.1.10_release.apk",
		"updateDesc": "教秘APP上线,安卓版需先卸载老版本."
	}
}