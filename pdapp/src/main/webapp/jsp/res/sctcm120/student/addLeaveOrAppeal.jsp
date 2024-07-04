<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"startDate":	${pdfn:toJsonString(startDate)},
	"endDate":	${pdfn:toJsonString(endDate)},
	"kqTypeId":	${pdfn:toJsonString(param.kqTypeId)},
	"recordFlow": ${pdfn:toJsonString(recordFlow)},
	"intervalDays": ${pdfn:toJsonString(intervalDays)},
	"admin":[
		{
			"isAudit":"Y",
			"userName":"${admin.userName}"
		}
	],
	"less":[
		{
			"isAudit":"${less.teacherFlag}",
			"userName":"${less.teacherName}"
		},
		{
			"isAudit":"${less.headFlag}",
			"userName":"${less.headName}"
		},
		{
			"isAudit":"${less.tutorFlag}",
			"userName":"${less.tutorName}"
		},
		{
			"isAudit":"${less.managerFlag}",
			"userName":"${less.managerName}"
		}
	],
	"greater":[
		{
			"isAudit":"${greater.teacherFlag}",
			"userName":"${greater.teacherName}"
		},
		{
			"isAudit":"${greater.headFlag}",
			"userName":"${greater.headName}"
		},
		{
			"isAudit":"${greater.tutorFlag}",
			"userName":"${greater.tutorName}"
		},
		{
			"isAudit":"${greater.managerFlag}",
			"userName":"${greater.managerName}"
		}
	]
    </c:if>
}
