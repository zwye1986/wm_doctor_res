<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"recForm": ${pdfn:toJsonString(recForm)},
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
				"recFlow":"${recFormMap[bean.processFlow]}",
				"formScore":"${formScoreMap[bean.processFlow]}",
				"action":{
					<c:choose>
						<c:when test="${empty recFormMap[bean.processFlow] and recForm eq 'Teacher_360'}">
							"eval":"评价学员"
						</c:when>
						<c:otherwise>
							"view":"查看评价"
						</c:otherwise>
					</c:choose>
				}
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}