<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)},
    "data": {
    	"iosVersion" : "1.0.0",
    	"iosURL" : "itms-services://?action=download-manifest&url=https://app.njpdxx.com/ios/plists/res_shzs/res_shzs_ios_1.0.0_release.plist",
    	"androidVersion" : "1.0.0",
    	"androidURL" : "https://app.njpdxx.com/ios/res_shzs/res_shzs_android_1.0.0_release.apk",
		"updateDesc" : "1.2018-06-28 处理部分手机闪退问题"
    }
}
