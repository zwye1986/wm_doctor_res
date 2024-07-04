<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)},
    "data": {
    	"iosVersion" : "1.0.2",
    	"iosURL" : "itms-services://?action=download-manifest&url=https://app.njpdxx.com/ios/plists/res_osca/res_osca_ios_1.0.2_release.plist",
    	"androidVersion" : "1.0.2",
    	"androidURL" : "http://app.njpdxx.com/ios/res_osca/res_osca_android_1.0.2_release.apk",
    	"updateDesc" : "2018-03-02. 管理员端上线"
    }
}
