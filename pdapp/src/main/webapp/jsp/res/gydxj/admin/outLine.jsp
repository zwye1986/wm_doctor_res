<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    	,
		"courseInfo": {
			"courseName":${pdfn:toJsonString(eduCourse.courseName)},
			"coursePeriod":${pdfn:toJsonString(eduCourse.coursePeriod)},
			"courseCredit":${pdfn:toJsonString(eduCourse.courseCredit)},
			"assumeOrgName":${pdfn:toJsonString(eduCourse.assumeOrgName)},
			"content":
				<c:if test="${typeId eq 'outLine'}">
					${pdfn:toJsonString(eduCourse.outlineContent)}
				</c:if>
				<c:if test="${typeId eq 'teaching'}">
					${pdfn:toJsonString(eduCourse.teachingContent)}
				</c:if>
		}
    </c:if>
}