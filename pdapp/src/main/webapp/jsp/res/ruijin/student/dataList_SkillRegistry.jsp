<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"action":{
		"add":"新增",
		"del":"删除"
	},
	"head":[
		{
			"headId":"skill_mrNo",
			"label":"病历号"
		},
		{
			"headId":"skill_operName",
			"label":"操作名称"
		},
		{
			"headId":"skill_result",
			"label":"是否成功"
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
	<c:set var="auditKey" value="${param.deptFlow}${param.funcFlow}${param.cataFlow}Audit"/>
    <c:set var="finishedKey" value="${param.deptFlow}${param.funcFlow}${param.cataFlow}Finished"/>
    <c:set var="reqKey" value="${param.deptFlow}${param.funcFlow}${param.cataFlow}ReqNum"/>
    <c:set var="progessKey" value="${param.deptFlow}${param.funcFlow}${param.cataFlow}"/>
	"category":{
		"auditedNum":${perMap[auditKey]+0},
		"finishedNum":${perMap[finishedKey]+0},
		"labelsNum":3,
		"neededNum":${perMap[reqKey]+0},
		"progress":${perMap[progessKey]+0},
		"title":"操作技能"
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
				"skill_operDate":${pdfn:toJsonString(recMap.skill_operDate)},
				"skill_pName":${pdfn:toJsonString(recMap.skill_pName)},
				"skill_operName":${pdfn:toJsonString(recMap.skill_operName)},
				"skill_result":${pdfn:toJsonString(recMap.skill_result)},
				"skill_mrNo":${pdfn:toJsonString(recMap.skill_mrNo)},
				"statusDesc":${pdfn:toJsonString(auditName)},
				"statusId":${pdfn:toJsonString(auditId)}
			}
			<c:if test="${!status.last}">
				,
			</c:if>
		</c:forEach>
	]