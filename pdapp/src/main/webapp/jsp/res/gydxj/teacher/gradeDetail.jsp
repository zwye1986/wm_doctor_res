<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    	,
		"action":{
			"save":${pdfn:toJsonString(grade.SUBMIT_FLAG eq 'Y'?'':'保存')}
		},
		<c:set var="gradeId" value="GyXjIsPassed.${grade.COURSE_GRADE}" />
		"gradeInfo": [
			{"inputId": "recordFlow", "label": "成绩流水号", "value": ${pdfn:toJsonString(grade.RECORD_FLOW)} ,"readonly":true, "isHidden": true},
			{"inputId": "userFlow", "label": "用户流水号", "value": ${pdfn:toJsonString(grade.USER_FLOW)} ,"readonly":true, "isHidden": true},
			{"inputId": "userName", "label": "姓名", "value": ${pdfn:toJsonString(grade.USER_NAME)} ,"readonly":true},
			{"inputId": "sid", "label": "学号", "value": ${pdfn:toJsonString(grade.SID)} ,"readonly":true},
			{"inputId": "courseName", "label": "课程名称", "value": ${pdfn:toJsonString(grade.COURSE_NAME)} ,"readonly":true},
			{"inputId": "courseTypeName", "label": "课程类型", "value": ${pdfn:toJsonString(grade.COURSE_TYPE_NAME)} ,"readonly":true},
			{"inputId": "coursePeriod", "label": "学时", "value": ${pdfn:toJsonString(grade.COURSE_PERIOD)} ,"readonly":true},
			{"inputId": "courseCredit", "label": "学分", "value": ${pdfn:toJsonString(grade.COURSE_CREDIT)} ,"readonly":true},
			{"inputId": "studyWayName", "label": "修读方式", "value": ${pdfn:toJsonString(grade.STUDY_WAY_NAME)} ,"readonly":true},
			{"inputId": "assessTypeName", "label": "考核方式", "value": ${pdfn:toJsonString(grade.ASSESS_TYPE_NAME)} ,"readonly":true},
			{"inputId": "pacificGrade", "label": "平时成绩", "value": ${pdfn:toJsonString(grade.PACIFIC_GRADE)} ,"readonly":false,"required":true},
			{"inputId": "teamEndGrade", "label": "期末成绩", "value": ${pdfn:toJsonString(grade.TEAM_END_GRADE)} ,"readonly":false,"required":true},
			{"inputId": "courseGrade", "label": "成绩", "value": ${pdfn:toJsonString(empty sysDictIdMap[gradeId]?grade.COURSE_GRADE:sysDictIdMap[gradeId])} ,"readonly":false,"required":true},
			{"inputId": "gradeTermName", "label": "获得学期", "value": ${pdfn:toJsonString(grade.GRADE_TERM_NAME)} ,"readonly":true}
		]
    </c:if>
}