<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"action":{
		"add":"新增",
		"del":"删除"
	},
	"head":[
		{
			"headId":"activity_way",
			"label":"活动形式"
		},
		{
			"headId":"activity_speaker",
			"label":"主讲人"
		},
		{
			"headId":"activity_period",
			"label":"学时"
		},
		{
			"headId":"activity_content",
			"label":"内容"
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
				<c:set var="auditName" value="已审核"/>
				<c:set var="auditId" value="Audited"/>
			</c:if>
			{
				"dataFlow":${pdfn:toJsonString(rec.recFlow)},
				"activity_date":${pdfn:toJsonString(recMap.activity_date)},
				"activity_way":${pdfn:toJsonString(recMap.activity_way)},
				"activity_speaker":${pdfn:toJsonString(recMap.activity_speaker)},
				"activity_period":${pdfn:toJsonString(recMap.activity_period)},
				"activity_content":${pdfn:toJsonString(recMap.activity_content)},
				"statusDesc":${pdfn:toJsonString(auditName)},
				"statusId":${pdfn:toJsonString(auditId)}
			}
			<c:if test="${!status.last}">
				,
			</c:if>
		</c:forEach>
	]