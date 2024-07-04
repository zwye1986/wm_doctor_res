<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"recTypes":[
		<c:forEach items="${enums}" var="bean" varStatus="s1">
			{
				"recTypeId":"${bean.id}",
				"recTypeName":"${bean.name}"
			}
			<c:if test="${not s1.last }">
				,
			</c:if>
		</c:forEach>
	],
	"dataCount": ${dataCount},
    "datas": [
		<c:forEach items="${list}" var="bean" varStatus="s">
	     	{
				<c:set var="currDate" value="${pdfn:getCurrDate()}"/>
				"userName":"${bean.doctorName}",
				"docFlow":"${bean.doctorFlow}",
				"schResultFlow":"${bean.schResultFlow}",
				"resultFlow":"${bean.resultFlow}",
				"sessionNumber":"${bean.sessionNumber}",
				"speName":"${bean.speName}",
				"processFlow":"${bean.processFlow}",
				"deptFlow":"${bean.deptFlow}",
				"deptName":"${bean.deptName}",
				"startDate":"${bean.startDate}",
				"endDate":"${bean.endDate}",
				"schDeptFlow":"${bean.schDeptFlow}",
				"schDeptName":"${bean.schDeptName}",
				"schStartDate":"${bean.schStartDate}",
				"schEndDate":"${bean.schEndDate}",
				"action":{
					"eval":"评价"
					<c:if test="${not empty recFormMap[bean.processFlow]}">
						,"view":"查看"
					</c:if>
				}
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}