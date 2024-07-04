<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${not empty file}">
        ,"file": {
            "recordFlow":${pdfn:toJsonString(file.recordFlow)},
            "orgFlow":${pdfn:toJsonString(file.orgFlow)},
            "orgName":${pdfn:toJsonString(file.orgName)},
            "itemId":${pdfn:toJsonString(file.itemId)},
            "itemName":${pdfn:toJsonString(file.itemName)},
            "planYear":${pdfn:toJsonString(file.planYear)},
            "fileName":${pdfn:toJsonString(file.fileName)},
            "fileUrl":${pdfn:toJsonString(file.fileUrl)},
            "speId":${pdfn:toJsonString(file.speId)}
        }
    </c:if>
}
