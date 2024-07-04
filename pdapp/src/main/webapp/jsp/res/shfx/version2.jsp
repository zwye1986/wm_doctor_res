<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)},
    "data": {
    	"iosVersion" : "1.0.8",
    	"iosURL" : "itms-services://?action=download-manifest&url=https://app.njpdxx.com/ios/plists/res_qpres2/res_qpres2_ios_1.0.8_release.plist",
    	"androidVersion" : "1.0.6",
    	"androidURL" : "https://app.njpdxx.com/ios/res_qpres2/res_qpres2_android_1.0.6_release.apk",
		"updateDesc" : "1.2018-07-24 系统功能优化."
    }
}
