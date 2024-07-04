<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "info": {
            "recId": ${pdfn:toJsonString(resultData.recId)},
            "schDeptName": ${pdfn:toJsonString(resultData.schDeptName)},
            "stuContent": ${pdfn:toJsonString(resultData.stuContent)},
            "teaContent": ${pdfn:toJsonString(resultData.teaContent )},
            "checkStatus": ${pdfn:toJsonString(resultData.checkStatus )},
            "checkStatusName": ${pdfn:toJsonString(resultData.checkStatusName )},
            "sessionNumber": "${resultData.sessionNumber}",
            "docName": "${resultData.docName}",
            "docFlow": "${resultData.docFlow}",
            "hosSecId": "${resultData.hosSecId}",
            "endTime": "${resultData.endTime}",
            "startTime": "${resultData.startTime}"
    }
    </c:if>
}
