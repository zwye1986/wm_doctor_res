<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)},
    "data": {
    	"iosVersion" : "1.1.4",
    	"iosURL" : "itms-services://?action=download-manifest&url=https://app.njpdxx.com/ios/plists/res_jswjw_ios_1.1.4_release.plist",
    	"androidVersion" : "1.1.6",
    	"androidURL" : "http://app.njpdxx.com/ios/res_jswjw/res_jswjw_android_1.1.6_release.apk",
    	"updateDesc" : "2015-11-11. 1:美化界面 2:解决部分bug"
    }
}
