<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)},
    "IsPush": ${pdfn:toJsonString(IsPush)},
    "IsFirst": ${pdfn:toJsonString(isFirst)},
    "PushImgUrl": ${pdfn:toJsonString(PushImgUrl)},
    "AndroidDownUrl": ${pdfn:toJsonString(AndroidDownUrl)},
    "IosDownUrl": ${pdfn:toJsonString(IosDownUrl)},
    "PushType": ${pdfn:toJsonString(PushType)}
}

