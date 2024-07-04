<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)},
    "data": {
    	"iosVersion" : "1.1.6",
    	"iosURL" : "itms-services://?action=download-manifest&url=https://app.njpdxx.com/ios/plists/res_njmu_ios_1.1.6_release.plist",
    	"androidVersion" : "1.9",
    	"androidURL" : "https://app.njpdxx.com/ios/res_njmu/res_njmu_android_1.9_release.apk",
    	"updateDesc" : "系统优化"
    }
}
