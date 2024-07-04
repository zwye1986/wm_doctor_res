<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    	,
		"grade":
			{
			"recordFlow":${pdfn:toJsonString(grade.RECORD_FLOW)},
			"sid":${pdfn:toJsonString(grade.SID)},
			"userName":${pdfn:toJsonString(grade.USER_NAME)},
			"studentPeriod":${pdfn:toJsonString(grade.STUDENT_PERIOD)},
			"gradeTermName":${pdfn:toJsonString(grade.GRADE_TERM_NAME)},
			"trainCategoryName":${pdfn:toJsonString(grade.TRAIN_CATEGORY_NAME)},
			"courseName":${pdfn:toJsonString(grade.COURSE_NAME)},
			"courseTypeName":${pdfn:toJsonString(grade.COURSE_TYPE_NAME)},
			"coursePeriod":${pdfn:toJsonString(grade.COURSE_PERIOD)},
			"courseCredit":${pdfn:toJsonString(grade.COURSE_CREDIT)},
			"gainCredit":
			<c:choose>
				<c:when test="${grade.COURSE_GRADE eq 'Y' || grade.COURSE_GRADE eq 'Excellent' || grade.COURSE_GRADE eq 'Good' || grade.COURSE_GRADE eq 'Secondary' || grade.COURSE_GRADE eq 'Pass'}">
					${pdfn:toJsonString(grade.COURSE_CREDIT)}
				</c:when>
				<c:when test="${grade.COURSE_GRADE eq 'N' || grade.COURSE_GRADE eq 'UnPass'}">${pdfn:toJsonString(0)}</c:when>
				<c:otherwise>
					${((grade.DEGREE_COURSE_FLAG eq 'Y' && grade.COURSE_GRADE ge '75')||(grade.DEGREE_COURSE_FLAG ne 'Y' && grade.COURSE_GRADE ge '60'))?pdfn:toJsonString(grade.COURSE_CREDIT):pdfn:toJsonString(0)}
				</c:otherwise>
			</c:choose>
			,
			"degreeCourseFlag":${pdfn:toJsonString(grade.DEGREE_COURSE_FLAG eq 'Y'?'是':'否')},
			"studyWayName":${pdfn:toJsonString(grade.STUDY_WAY_NAME)},
			"assessTypeName":${pdfn:toJsonString(grade.ASSESS_TYPE_NAME)},
			"courseGrade":
			<c:choose>
				<c:when test="${grade.COURSE_GRADE eq 'Y' || grade.COURSE_GRADE eq 'N' || grade.COURSE_GRADE eq 'Excellent' || grade.COURSE_GRADE eq 'Good'
							 || grade.COURSE_GRADE eq 'Secondary' || grade.COURSE_GRADE eq 'Pass' || grade.COURSE_GRADE eq 'UnPass'}">
					<c:set var="gradeId" value="GyXjIsPassed.${grade.COURSE_GRADE}" />
					${pdfn:toJsonString(sysDictIdMap[gradeId])}
				</c:when>
				<c:otherwise>
					${pdfn:toJsonString(grade.COURSE_GRADE)}
				</c:otherwise>
			</c:choose>
			}
    </c:if>
}