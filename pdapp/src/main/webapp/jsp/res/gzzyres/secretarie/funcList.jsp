<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
	 <c:if test="${resultId eq '200' }">
    ,
    "funcList":[
        <c:forEach items="${funcList}" var="func" varStatus="s">
        {
            "funcId": ${pdfn:toJsonString(func.funcId)},
            "funcName": ${pdfn:toJsonString(func.funcName)}
        }
            <c:if test='${not s.last}'>
                ,
            </c:if>
        </c:forEach>
    ]
	</c:if>
}
