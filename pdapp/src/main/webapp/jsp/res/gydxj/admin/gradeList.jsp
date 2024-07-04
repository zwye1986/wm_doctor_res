<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    	,
		"gradeList": [
			<c:forEach items="${gradeList}" var="edu" varStatus="i">
				{
				"recordFlow":${pdfn:toJsonString(edu.RECORD_FLOW)},
				"userName":${pdfn:toJsonString(edu.USER_NAME)},
				"courseCode":${pdfn:toJsonString(edu.COURSE_CODE)},
				"courseName":${pdfn:toJsonString(edu.COURSE_NAME)},
				"studentPeriod":${pdfn:toJsonString(edu.STUDENT_PERIOD)},
				"gradeTermName":${pdfn:toJsonString(edu.GRADE_TERM_NAME)},
				"courseGrade":
				<c:choose>
					<c:when test="${edu.COURSE_GRADE eq 'Y' || edu.COURSE_GRADE eq 'N' || edu.COURSE_GRADE eq 'Excellent' || edu.COURSE_GRADE eq 'Good'
								 || edu.COURSE_GRADE eq 'Secondary' || edu.COURSE_GRADE eq 'Pass' || edu.COURSE_GRADE eq 'UnPass'}">
						<c:set var="gradeId" value="GyXjIsPassed.${edu.COURSE_GRADE}" />
						${pdfn:toJsonString(sysDictIdMap[gradeId])}
					</c:when>
					<c:otherwise>
						${pdfn:toJsonString(edu.COURSE_GRADE)}
					</c:otherwise>
				</c:choose>
				}
				<c:if test="${!i.last}">
					,
				</c:if>
			</c:forEach>
		],
		"dataCount": ${dataCount}
    </c:if>
}