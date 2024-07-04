<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}

    <c:if test="${resultId eq '200' }">,
        "topTips": ${pdfn:toJsonString(topTips)},
        "buttomTips": ${pdfn:toJsonString(buttomTips)},
        "gsxdLength": ${pdfn:toJsonString(gsxdLength)},
        "jdxxLength": ${pdfn:toJsonString(jdxxLength)},
        "jdya_lzsb": ${pdfn:toJsonString(jdya_lzsb)}
    </c:if>
}