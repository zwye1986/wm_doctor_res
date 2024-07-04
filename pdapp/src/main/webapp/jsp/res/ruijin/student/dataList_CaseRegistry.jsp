<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"action":{
		"add":"新增",
		"del":"删除"
	},
	"head":[
		{
			"headId":"mr_no",
			"label":"病历号"
		},
		{
			"headId":"disease_pName",
			"label":"疾病名称"
		},
		{
			"headId":"mr_diagType",
			"label":"诊断类型"
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
		"title":"大病历"
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
				"mr_pName":${pdfn:toJsonString(recMap.mr_pName)},
				"mr_no":${pdfn:toJsonString(recMap.mr_no)},
				"disease_pName":${pdfn:toJsonString(recMap.disease_pName)},
				"mr_diagType":${pdfn:toJsonString(recMap.mr_diagType)},
				"statusDesc":${pdfn:toJsonString(auditName)},
				"statusId":${pdfn:toJsonString(auditId)}
			}
			<c:if test="${!status.last}">
				,
			</c:if>
		</c:forEach>
	]