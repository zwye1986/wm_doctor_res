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
				"userName":"${bean.userName}",
				"docFlow":"${bean.userFlow}",
				"schResultFlow":"${bean.schResultFlow}",
				"resultFlow":"${bean.resultFlow}",
				"sessionNumber":"${bean.sessionNumber}",
				"speName":"${bean.speName}",
				"teacherUserName":"${bean.teacherUserName}",
				"processFlow":"${bean.processFlow}",
				"orgFlow":"${bean.orgFlow}",
				"orgName":"${bean.orgName}",
				"deptFlow":"${bean.deptFlow}",
				"deptName":"${bean.deptName}",
				"schDeptFlow":"${bean.schDeptFlow}",
				"schDeptName":"${bean.schDeptName}",
				"schStartDate":"${bean.schStartDate}",
				"schEndDate":"${bean.schEndDate}",
				"recFlow":"${recFormMap[bean.processFlow]}",
				"formScore":"${formScoreMap[bean.processFlow]}",
				"action":{
					<c:choose>
						<c:when test="${empty recFormMap[bean.processFlow] and recForm ne 'Teacher_360'}">
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