<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"action":{
		"add":"新增",
		"del":"删除"
	},
	"head":[
		{
			"headId":"title",
			"label":"题目"
		},
		{
			"headId":"articleTitle",
			"label":"文章题目"
		},
		{
			"headId":"teaching",
			"label":"教学"
		},
		{
			"headId":"lectureTitle",
			"label":"讲座题目"
		},
		{
			"headId":"assessment",
			"label":"考核者"
		}
	],
	"search":[
        {
            "name":"未审核",
            "key":"join",
            "value":"NotAudit",
            "type":"tab"
        },
        {
            "name":"已审核",
            "key":"join",
            "value":"Audited",
            "type":"tab"
        }
    ],
 	<c:set var="auditKey" value="${param.deptFlow}${param.funcFlow}Audit"/>
    <c:set var="finishedKey" value="${param.deptFlow}${param.funcFlow}Finished"/>
    <c:set var="reqKey" value="${param.deptFlow}${param.funcFlow}ReqNum"/>
    <c:set var="progessKey" value="${param.deptFlow}${param.funcFlow}"/>
	"category":{
		"auditedNum":${perMap[auditKey]+0},
		"finishedNum":${perMap[finishedKey]+0},
		"labelsNum":3,
		"neededNum":${perMap[reqKey]+0},
		"progress":${perMap[progessKey]+0},
		"title":"参加活动"
	},
	"datas":[
		<c:forEach items="${recMaps}" var="recMap" varStatus="status">
			<c:set var="rec" value="${recMap.rec}"/>
			<c:set var="auditName" value="未审核"/>
			<c:set var="auditId" value="Saved"/>
			<c:if test="${!empty rec.auditStatusId}">
				<c:if test="${rec.auditStatusId eq 'TeacherAuditY'}">
					<c:set var="auditName" value="通过"/>
					<c:set var="auditId" value="isPass"/>
				</c:if>
				<c:if test="${rec.auditStatusId eq 'TeacherAuditN'}">
					<c:set var="auditName" value="不通过"/>
					<c:set var="auditId" value="notPass"/>
				</c:if>
			</c:if>
			{
				"dataFlow":${pdfn:toJsonString(rec.recFlow)},
				"title":${pdfn:toJsonString(recMap.title)},
				"articleTitle":${pdfn:toJsonString(recMap.articleTitle)},
				"teaching":${pdfn:toJsonString(recMap.teaching)},
				"lectureTitle":${pdfn:toJsonString(recMap.lectureTitle)},
				"assessment":${pdfn:toJsonString(recMap.assessment)},
				"statusDesc":${pdfn:toJsonString(auditName)},
				"statusId":${pdfn:toJsonString(auditId)}
			}
			<c:if test="${!status.last}">
				,
			</c:if>
		</c:forEach>
	]