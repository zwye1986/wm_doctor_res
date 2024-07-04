<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"dataCount": ${dataCount},
    "datas": [
		<c:forEach items="${list}" var="bean" varStatus="s">
	     	{
				"recFlow":"${bean.recFlow}",
				"recTypeId":"${bean.recTypeId}",
				"recTypeName":"${bean.recTypeName}",
				"userName":"${bean.operUserName}",
				"schDeptFlow":"${bean.schDeptFlow}",
				"schDeptName":"${bean.schDeptName}",
				"action":{
					"view":"查看"
				}
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}