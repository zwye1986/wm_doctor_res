<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)},
    "data": {
    	"iosVersion" : "1.0.19",
    	"iosURL" : "itms-services://?action=download-manifest&url=https://app.njpdxx.com/ios/plists/res_gzzyyy/res_gzzyyy_ios_1.0.19_release.plist",
    	"androidVersion" : "1.0.16",
    	"androidURL" : "https://app.njpdxx.com/ios/res_gzzyyy/res_gzzyyy_android_1.0.16_release.apk",
    	"updateDesc" : "1.2018-12-03 优化."
    }
}
