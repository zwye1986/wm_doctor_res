<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    	,
		"classList": [
			<c:forEach items="${classList}" var="edu" varStatus="i">
				{
				"recordFlow":${pdfn:toJsonString(edu.RECORD_FLOW)},
				"courseFlow":${pdfn:toJsonString(edu.COURSE_FLOW)},
				"courseCode":${pdfn:toJsonString(edu.COURSE_CODE)},
				"courseName":${pdfn:toJsonString(edu.CLASS_COURSE_NAME)},
				"classDate":${pdfn:toJsonString(edu.CLASS_TIME)},
				"classTime":${pdfn:toJsonString(empty timeMap[edu.CLASS_ORDER]?edu.CLASS_ORDER:timeMap[edu.CLASS_ORDER])},
				"classId":${pdfn:toJsonString(edu.CLASS_ID)},
				"className":${pdfn:toJsonString(edu.CLASS_NAME)},
				"joinNum":${pdfn:toJsonString(edu.JOIN_NUM)},
				"totleNum":${pdfn:toJsonString(edu.TOTLE_NUM)},
				"classRate":<c:if test="${edu.TOTLE_NUM eq 0}">0</c:if><c:if test="${edu.TOTLE_NUM ne 0}"><fmt:formatNumber type="number" value="${edu.JOIN_NUM*100/edu.TOTLE_NUM}" maxFractionDigits="1"/></c:if>
				}
				<c:if test="${!i.last}">
					,
				</c:if>
			</c:forEach>
		],
		"dataCount": ${dataCount}
    </c:if>
}