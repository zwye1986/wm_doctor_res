<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"action":{
		"add":"新增",
		"del":"删除"
	},
	"head":[
		{
			"headId":"disease_diagName",
			"label":"病种名称"
		},
		{
			"headId":"disease_pDate",
			"label":"日期"
		},
		{
			"headId":"disease_pName",
			"label":"病人姓名"
		},
		{
			"headId":"disease_diagType",
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
				"disease_diagName":${pdfn:toJsonString(recMap.disease_diagName)},
				"disease_pDate":${pdfn:toJsonString(recMap.disease_pDate)},
				"disease_pName":${pdfn:toJsonString(recMap.disease_pName)},
				"disease_diagType":${pdfn:toJsonString(recMap.disease_diagType)},
				"statusDesc":${pdfn:toJsonString(auditName)},
				"statusId":${pdfn:toJsonString(auditId)}
			}
			<c:if test="${!status.last}">
				,
			</c:if>
		</c:forEach>
	]