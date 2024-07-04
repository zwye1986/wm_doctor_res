<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    	,
		"courseList": [
			<c:forEach items="${courseList}" var="edu" varStatus="i">
				{
				"courseFlow":${pdfn:toJsonString(edu.COURSE_FLOW)},
				"courseCode":${pdfn:toJsonString(edu.COURSE_CODE)},
				"courseName":${pdfn:toJsonString(edu.COURSE_NAME)},
				"courseSession":${pdfn:toJsonString(edu.COURSE_SESSION)},
				"gradationId":${pdfn:toJsonString(edu.GRADATION_ID)},
				"gradationName":${pdfn:toJsonString(edu.GRADATION_NAME)},
				"courseCredit":${pdfn:toJsonString(edu.COURSE_CREDIT)},
				"coursePeriod":${pdfn:toJsonString(edu.COURSE_PERIOD)},
				"courseTypeId":${pdfn:toJsonString(edu.COURSE_TYPE_ID)},
				"courseTypeName":${pdfn:toJsonString(edu.COURSE_TYPE_NAME)},
				<c:set value="qrCode:${edu.COURSE_FLOW}" var="course"/>
				"signText":${pdfn:toJsonString(course)},
				<c:if test="${edu.TOTLE_NUM eq '0'}">
					"processRate":${pdfn:toJsonString(0)},
				</c:if>
				<c:if test="${edu.TOTLE_NUM ne '0'}">
					<c:set value="${edu.END_NUM*100/edu.TOTLE_NUM}" var="process"/>
					"processRate":${pdfn:toJsonString(process)},
				</c:if>
				"classRate":${pdfn:toJsonString(avgMap[edu.COURSE_FLOW])}
				}
				<c:if test="${!i.last}">
					,
				</c:if>
			</c:forEach>
		],
		"dataCount": ${dataCount}
    </c:if>
}