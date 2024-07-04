<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	"resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"dataCount": ${dataCount},
	"dataList": [
		<c:forEach items="${dataList}" var="process" varStatus="status">
			<c:set var="user" value="${userMap[process.userFlow]}"/>
			<c:set var="doctor" value="${doctorMap[process.userFlow]}"/>
		{
			"processFlow": ${pdfn:toJsonString(process.processFlow)},
			"docFlow": ${pdfn:toJsonString(process.userFlow)},
			"userName": ${pdfn:toJsonString(user.userName)},
			"speName": ${pdfn:toJsonString(doctor.trainingSpeName)},
			"sessionNumber": ${pdfn:toJsonString(doctor.sessionNumber)},
			"orgFlow": ${pdfn:toJsonString(process.orgFlow)},
			"orgName": ${pdfn:toJsonString(process.orgName)},
			"deptFlow": ${pdfn:toJsonString(process.deptFlow)},
			"deptName": ${pdfn:toJsonString(process.deptName)},
			"schDeptFlow": ${pdfn:toJsonString(process.schDeptFlow)},
			"schDeptName": ${pdfn:toJsonString(process.schDeptName)},
			"schResultFlow": ${pdfn:toJsonString(process.schResultFlow)},
			"schStartDate": ${pdfn:toJsonString(process.schStartDate)},
			"schEndDate": ${pdfn:toJsonString(process.schEndDate)},
			"startDate": ${pdfn:toJsonString(process.startDate)},
			"endDate": ${pdfn:toJsonString(process.endDate)},
			"teacherUserFlow": ${pdfn:toJsonString(process.teacherUserFlow)},
			"teacherUserName": ${pdfn:toJsonString(process.teacherUserName)},
			"headUserFlow": ${pdfn:toJsonString(process.headUserFlow)},
			"headUserName": ${pdfn:toJsonString(process.headUserName)},
			"schFlag": ${pdfn:toJsonString(process.schFlag)},
			"schScore": ${pdfn:toJsonString(process.schScore)},
			"isCurrentFlag": ${pdfn:toJsonString(process.isCurrentFlag)},
			"schPer": ${pdfn:toJsonString(process.schPer)},
			"schType":"轮转中"
		}
			<c:if test="${not status.last }">
				,
			</c:if>
		</c:forEach>
	]
  </c:if>
}