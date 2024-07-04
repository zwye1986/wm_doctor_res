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
			{"inputId": "courseFlow", "label": "课程flow", "value": ${pdfn:toJsonString(course.courseFlow)} ,"readonly":true, "isHidden": true},
			{"inputId": "courseName", "label": "课程名称", "value": ${pdfn:toJsonString(course.courseName)} ,"readonly":false},
			{"inputId": "courseCode", "label": "课程代码", "value": ${pdfn:toJsonString(course.courseCode)} ,"readonly":false},
			{"inputId": "courseNameEn", "label": "英文名称", "value": ${pdfn:toJsonString(course.courseNameEn)} ,"readonly":false},
			{"inputId": "gradationId", "label": "授课层次", "value": ${pdfn:toJsonString(course.gradationId)} ,"readonly":false,"inputType":"select",
				"options":[{"optionId": "","optionDesc": "请选择"},<c:forEach items="${dictTypeEnumGyTrainTypeList}" var="data" varStatus="s">{"optionId": ${pdfn:toJsonString(data.dictId)},"optionDesc": ${pdfn:toJsonString(data.dictName)}}<c:if test="${not s.last }">,</c:if></c:forEach>]},
			{"inputId": "coursePeriodTeach", "label": "讲授学时", "value": ${pdfn:toJsonString(course.coursePeriodTeach)} ,"readonly":false},
			{"inputId": "coursePeriodExper", "label": "实验学时", "value": ${pdfn:toJsonString(course.coursePeriodExper)} ,"readonly":false},
			{"inputId": "courseTypeId", "label": "课程类别", "value": ${pdfn:toJsonString(course.courseTypeId)} ,"readonly":false,"inputType":"select",
				"options":[{"optionId": "","optionDesc": "请选择"},<c:forEach items="${xjglCourseTypeEnumList}" var="data" varStatus="s">{"optionId": ${pdfn:toJsonString(data.id)},"optionDesc": ${pdfn:toJsonString(data.name)}}<c:if test="${not s.last }">,</c:if></c:forEach>]},
			{"inputId": "coursePeriodMachine", "label": "上机学时", "value": ${pdfn:toJsonString(course.coursePeriodMachine)} ,"readonly":false},
			{"inputId": "coursePeriodOther", "label": "其他学时", "value": ${pdfn:toJsonString(course.coursePeriodOther)} ,"readonly":false},
			{"inputId": "coursePeriod", "label": "总学时", "value": ${pdfn:toJsonString(course.coursePeriod)} ,"readonly":false},
			{"inputId": "courseUserCount", "label": "容纳人数", "value": ${pdfn:toJsonString(course.courseUserCount)} ,"readonly":false},
			{"inputId": "courseMoudleId", "label": "所属模块", "value": ${pdfn:toJsonString(course.courseMoudleId)} ,"readonly":false,"inputType":"select",
				"options":[{"optionId": "","optionDesc": "请选择"},<c:forEach items="${dictTypeEnumGyXjCourseMoudleList}" var="data" varStatus="s">{"optionId": ${pdfn:toJsonString(data.dictId)},"optionDesc": ${pdfn:toJsonString(data.dictName)}}<c:if test="${not s.last }">,</c:if></c:forEach>]},
			{"inputId": "courseCredit", "label": "学分", "value": ${pdfn:toJsonString(course.courseCredit)} ,"readonly":false},
			{"inputId": "assumeOrgFlow", "label": "承担单位id", "value": ${pdfn:toJsonString(course.assumeOrgFlow)} ,"readonly":true, "isHidden": true},
			{"inputId": "assumeOrgName", "label": "承担单位", "value": ${pdfn:toJsonString(course.assumeOrgName)} ,"readonly":true},
			{"inputId": "courseSession", "label": "所属学年", "value": ${pdfn:toJsonString(course.courseSession)} ,"readonly":true,"inputType":"date"},
			{"inputId": "courseSpeakerFlow", "label": "教学组长id", "value": ${pdfn:toJsonString(course.courseSpeakerFlow)} ,"readonly":true, "isHidden": true},
			{"inputId": "courseSpeaker", "label": "教学组长", "value": ${pdfn:toJsonString(course.courseSpeaker)} ,"readonly":true},
			{"inputId": "courseSpeakerPhone", "label": "联系电话", "value": ${pdfn:toJsonString(course.courseSpeakerPhone)} ,"readonly":false},
			{"inputId": "preCourse", "label": "前置课程代码", "value": ${pdfn:toJsonString(course.preCourse)} ,"readonly":false},
			{"inputId": "preCourse", "label": "授课老师", "value": ${pdfn:toJsonString(course.preCourse)} ,"readonly":false},
			{"inputId": "effectiveGrade", "label": "有效成绩", "value": ${pdfn:toJsonString(course.effectiveGrade)} ,"readonly":false},
			{"inputId": "courseIntro", "label": "课程简介", "value": ${pdfn:toJsonString(course.courseIntro)} ,"readonly":false}
		]
    </c:if>
}