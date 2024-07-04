<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)},
    "data": {
    	"iosVersion" : "1.0.0",
    	"iosURL" : "itms-services://?action=download-manifest&url=https://app.njpdxx.com/ios/plists/res_mbgl/res_mbgl_ios_1.0.0_release.plist",
    	"androidVersion" : "1.0.0",
    	"androidURL" : "http://app.njpdxx.com/ios/res_mbgl/res_mbgl_android_1.0.0_release.apk",
    	"updateDesc" : "1.2018-09-29 系统功能优化."
    }
}
