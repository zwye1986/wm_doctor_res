<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8" %>
{
"resultId": ${resultId},
"resultType": ${pdfn:toJsonString(resultType)},
"data": {
"iosVersion" : "${versionIosNumber}",
"iosURL" :
"itms-services://?action=download-manifest&url=https://app.njpdxx.com/ios/plists/res_jswjw/res_jswjw_ios_${versionIosNumber}
_release.plist",
"androidVersion" : "${versionAndroidNumber}",
"androidURL" : "https://app.njpdxx.com/ios/res_jswjw/res_jswjw_android_${versionAndroidNumber}_release.apk",
"updateDesc" : "1.2019-04-11 系统功能优化.",
"onlineCountNum":"${onlineCountNum}",

}
<%--"data": {--%>
<%--"iosVersion" : "1.3.23",--%>
<%--"iosURL" : "itms-services://?action=download-manifest&url=https://app.njpdxx.com/ios/plists/res_jswjw/res_jswjw_ios_1.3.23_debug.plist",--%>
<%--"androidVersion" : "1.4.14",--%>
<%--"androidURL" : "http://app.njpdxx.com/ios/res_jswjw/res_jswjw_android_1.4.16_debug.apk",--%>
<%--"updateDesc" : "1.2018-02-02 系统功能优化."--%>
<%--}--%>
}
