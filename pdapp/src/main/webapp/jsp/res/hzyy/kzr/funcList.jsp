<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
	 <c:if test="${resultId eq '200' }">
    ,
    "funcList":[
        <c:forEach items="${funcList}" var="func" varStatus="s">
            {
                "funcTypeId": ${pdfn:toJsonString(func.funcId)},
                "funcTypeName": ${pdfn:toJsonString(func.funcName)},
                "funcFlow": ${pdfn:toJsonString(func.funcId)},
                "funcName": ${pdfn:toJsonString(func.funcName)},
                "img": ${pdfn:toJsonString(func.img)}
            }
            <c:if test='${not s.last}'>
                ,
            </c:if>
        </c:forEach>
    ]
	</c:if>
}
