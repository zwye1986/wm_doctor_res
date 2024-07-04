<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "pageIndex": ${param.pageIndex},
    "pageSize": ${param.pageSize},
    "dataCount": ${dataCount},
    "planList": [
     <c:forEach items="${schPlans}" var="schPlan" varStatus="status">
	     	{
				"schDeptFlow":"${schPlan.schDeptFlow}",
				"schDeptName":"${schPlan.schDeptName}",
				"schDeptImg":"<s:url value='/images/xx.png'/>",
				"schStartDate":"${schPlan.schStartDate}",
                "schEndDate":"${schPlan.schEndDate}",
                "schStatusId":"${schPlan.schStatusId}",
                "schStatusDesc":"${schPlan.schStatusDesc}",
                "teacherFlow":"${schPlan.teacherFlow}",
                "teacherName":"${schPlan.teacherName}",
                "deptHeadFlow":"${schPlan.deptHeadFlow}",
                "deptHeadName":"${schPlan.deptHeadName}",
                "startDate":"${schPlan.startDate}",
                "endDate":"${schPlan.endDate}",
                "schScore":${schPlan.schScore},
                "SecID":"${schPlan.SecID}",
                "SpecialtyID":"${schPlan.SpecialtyID}",
			}
			<c:if test="${not status.last}">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}
