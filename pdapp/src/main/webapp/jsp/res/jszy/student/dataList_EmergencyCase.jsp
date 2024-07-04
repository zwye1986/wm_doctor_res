<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"action":{
		"add":"新增",
		"del":"删除"
	},
	"head":[
		{
			"headId":"case",
			"label":"病例类型"
		},
		{
			"headId":"diseaseName",
			"label":"疾病名称"
		},
		{
			"headId":"cases",
			"label":"例次"
		},
		{
			"headId":"date",
			"label":"日期"
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
		"title":"门急诊病例"
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
				"case":${pdfn:toJsonString(recMap['case'])},
				"diseaseName":${pdfn:toJsonString(recMap.diseaseName)},
				"cases":${pdfn:toJsonString(recMap.cases)},
				"date":${pdfn:toJsonString(recMap.date)},
				"statusDesc":${pdfn:toJsonString(auditName)},
				"statusId":${pdfn:toJsonString(auditId)}
			}
			<c:if test="${!status.last}">
				,
			</c:if>
		</c:forEach>
	]