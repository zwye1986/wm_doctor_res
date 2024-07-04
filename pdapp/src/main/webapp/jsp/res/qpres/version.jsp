<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)},
    "data": {
    	"iosVersion" : "1.0.9",
    	"iosURL" : "itms-services://?action=download-manifest&url=https://app.njpdxx.com/ios/plists/res_qpres/res_qpres_ios_1.0.9_release.plist",
    	"androidVersion" : "1.0.7",
    	"androidURL" : "https://app.njpdxx.com/ios/res_qpres/res_qpres_android_1.0.7_release.apk",
		"updateDesc" : "1. 功能调整."
    }
}
