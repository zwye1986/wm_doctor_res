<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)},
    "data": {
    	"iosVersion" : "1.0.7",
    	"iosURL" : "itms-services://?action=download-manifest&url=https://app.njpdxx.com/ios/plists/res_njmu2_ios_1.0.7_release.plist",
    	"androidVersion" : "1.0.5",
    	"androidURL" : "https://app.njpdxx.com/ios/res_njmu2/res_njmu2_android_1.0.5_release.apk",
    	"updateDesc" : "版本升级"
    }
}
