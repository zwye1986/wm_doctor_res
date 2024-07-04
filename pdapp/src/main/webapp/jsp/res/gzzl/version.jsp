<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)},
    "data": {
    	"iosVersion" : "1.0.2",
    	"iosURL" : "itms-services://?action=download-manifest&url=https://app.njpdxx.com/ios/plists/res_gzzl/res_gzzl_ios_1.0.2_release.plist",
    	"androidVersion" : "1.0.1",
    	"androidURL" : "https://app.njpdxx.com/ios/res_gzzl/res_gzzl_android_1.0.1_release.apk",
		"updateDesc" : "1.系统更新"
    }
}
