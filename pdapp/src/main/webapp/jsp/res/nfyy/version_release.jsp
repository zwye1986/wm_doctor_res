<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	"resultId":
	${resultId},
	"resultType":
	${pdfn:toJsonString(resultType)},
	"data": {
		"iosVersion": "1.1.3",
		"iosURL": "itms-services://?action=download-manifest&url=https://app.njpdxx.com/ios/plists/res_nfyy_ios_1.1.3_release.plist",
		"androidVersion": "1.1.7",
		"androidURL": "http://app.njpdxx.com/ios/res_nfyy/res_nfyy_android_1.1.7_release.apk",
		"updateDesc": "2016-04-07. 支持安卓端学员查看出科分数."
	}
}