<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)},
    "data": {
    	"iosVersion" : "1.0.10",
    	"iosURL" : "itms-services://?action=download-manifest&url=https://app.njpdxx.com/ios/plists/res_shfx/res_shfx_ios_1.0.10_release.plist",
    	"androidVersion" : "1.0.8",
    	"androidURL" : "https://app.njpdxx.com/ios/res_shfx/res_shfx_android_1.0.8_release.apk",
		"updateDesc" : "1.功能上线."
    }
}
