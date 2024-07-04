<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)},
    "data": {
    	"iosVersion" : "1.0.0",
    	"iosURL" : "itms-services://?action=download-manifest&url=https://app.njpdxx.com/ios/plists/res_lcjn/res_lcjn_ios_1.0.0_release.plist",
    	"androidVersion" : "1.0.0",
    	"androidURL" : "http://app.njpdxx.com/ios/res_lcjn/res_lcjn_android_1.0.0_release.apk",
    	"updateDesc" : "2016-12-12. 系统发布"
    }
}
