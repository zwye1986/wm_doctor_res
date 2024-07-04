<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    	,
		"courseInfo":
			{
			"courseName":${pdfn:toJsonString(course.courseName)},
			"courseTypeName":${pdfn:toJsonString(course.courseTypeName)},
			"courseNameEn":${pdfn:toJsonString(course.courseNameEn)},
			"courseCredit":${pdfn:toJsonString(course.courseCredit)},
			"coursePeriodTeach":${pdfn:toJsonString(course.coursePeriodTeach)},
			"coursePeriodMachine":${pdfn:toJsonString(course.coursePeriodMachine)},
			<c:set var="pro" value="${classInfo.JOIN_NUM}/${classInfo.TOTLE_NUM}"/>
			"process":${pdfn:toJsonString(pro)},
			<c:set var="rate" value="${classInfo.JOIN_NUM/classInfo.TOTLE_NUM*100}"/>
			"classRate":${pdfn:toJsonString(rate)},
			"classroomName":${pdfn:toJsonString(classInfo.CLASSROOM_NAME)},
			"classDate":${pdfn:toJsonString(classInfo.CLASS_TIME)},
			"classTime":${pdfn:toJsonString(empty timeMap[classInfo.CLASS_ORDER]?classInfo.CLASS_ORDER:timeMap[classInfo.CLASS_ORDER])},
			"classTeacherName":${pdfn:toJsonString(teacher.classTeacherName)},
			}
    </c:if>
}