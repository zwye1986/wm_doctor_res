<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)},
    "data": {
    	"iosVersion" : "1.0.0",
    	"iosURL" : "itms-services://?action=download-manifest&url=https://app.njpdxx.com/ios/plists/res_eval/res_eval_ios_1.0.0_release.plist",
    	"androidVersion" : "1.0.0",
    	"androidURL" : "https://app.njpdxx.com/ios/res_eval/res_eval_android_1.0.0_release.apk",
    	"updateDesc" : "1.2017-08-31 首次发布."
    }
}
