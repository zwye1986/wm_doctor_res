<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "doctorFlow":"${param.doctorFlow}",
    "processFlow":"${param.processFlow}",
    "resultFlow":"${param.resultFlow}",
	"funcLists":[

        <c:forEach items="${list}" var="bean" varStatus="s">
            {
                "recFlow":"${bean.recFlow}",
                "typeId":"${bean.typeId}",
                "totalScore":"${bean.totalScore}",
                "evalRoleName":"${bean.evalName}"
            }
            <c:if test="${not s.last }">
                ,
            </c:if>
        </c:forEach>
		],
    </c:if>
}