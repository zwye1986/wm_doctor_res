<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)},
    "data": {
    	"iosVersion" : "1.0.11",
    	"iosURL" : "itms-services://?action=download-manifest&url=https://app.njpdxx.com/ios/plists/res_gzzyres/res_gzzyres_ios_1.0.11_release.plist",
    	"androidVersion" : "1.0.11",
    	"androidURL" : "https://app.njpdxx.com/ios/res_gzzyres/res_gzzyres_android_1.0.11_release.apk",
    	"updateDesc" : "1.功能调整."
    }
}
