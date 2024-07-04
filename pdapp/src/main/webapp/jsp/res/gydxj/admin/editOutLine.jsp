<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	"resultId": ${resultId},
	"resultType": ${pdfn:toJsonString(resultType)}
	<c:if test="${resultId eq '200' }">
		,
		"action":{
			"save":"保存"
		},
		"courseInfo": [
			{"inputId": "courseFlow", "label": "课程流水号", "value": ${pdfn:toJsonString(eduCourse.courseFlow)} ,"readonly":true, "isHidden": true},
			{"inputId": "courseName", "label": "课程名称", "value": ${pdfn:toJsonString(eduCourse.courseName)} ,"readonly":true},
			{"inputId": "coursePeriod", "label": "课程学时", "value": ${pdfn:toJsonString(eduCourse.coursePeriod)} ,"readonly":true},
			{"inputId": "courseCredit", "label": "课程学分", "value": ${pdfn:toJsonString(eduCourse.courseCredit)} ,"readonly":true},
			{"inputId": "assumeOrgName", "label": "开课单位", "value": ${pdfn:toJsonString(eduCourse.assumeOrgName)} ,"readonly":true}
			<c:if test="${typeId eq 'outLine'}">
				,{"inputId": "outlineContent", "label": "大纲内容", "value": ${pdfn:toJsonString(eduCourse.outlineContent)} ,"readonly":false,"inputType":"textarea"}
			</c:if>
			<c:if test="${typeId eq 'teaching'}">
				,{"inputId": "teachingContent", "label": "教学内容", "value": ${pdfn:toJsonString(eduCourse.teachingContent)} ,"readonly":false,"inputType":"textarea"}
			</c:if>
		]
	</c:if>
}