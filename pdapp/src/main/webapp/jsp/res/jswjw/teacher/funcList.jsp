<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"funcList": [
        {
            "title": "数据审核",
            "order": 1,
            "dataType": "audit",
            "label": "",
            "progress": ""
        },
        {
            "title": '出科小结',
            "order": 2,
            "dataType": "afterSummary",
            "label": "90分"
        }
    ]
    </c:if>
}
