<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)},
    "data": {
    	"iosVersion" : "1.0.0",
    	"iosURL" : "itms-services://?action=download-manifest&url=https://app.njpdxx.com/ios/plists/res_nydyjs_ios_1.0.0_release.plist",
    	"androidVersion" : "1.0.0",
    	"androidURL" : "https://app.njpdxx.com/ios/res_njmu/res_nydyjs_android_1.0.0_release.apk",
    	"updateDesc" : "1.首次发布."
    }
}
