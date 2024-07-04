<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)},
    "data": {
    	"iosVersion" : "1.0.20",
    	"iosURL" : "itms-services://?action=download-manifest&url=https://app.njpdxx.com/ios/plists/res_jszy/res_jszy_ios_1.0.20_release.plist",
    	"androidVersion" : "1.0.19",
    	"androidURL" : "https://app.njpdxx.com/ios/res_jszy/res_jszy_android_1.0.19_release.apk",
    	"updateDesc" : "1.2019-03-14 系统优化."
    }
}
