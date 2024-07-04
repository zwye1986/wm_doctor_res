<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"action":{
		"add":"新增",
		"del":"删除"
	},
	"head":[
		{
			"headId":"diseaseName",
			"label":"病种名称"
		},
		{
			"headId":"patientName",
			"label":"病人姓名"
		},
		{
			"headId":"caseNo",
			"label":"病历号/检查号"
		},
		{
			"headId": "treatMeasure",
			"label": "治疗措施",
		},
		{
			"headId":"recType",
			"label":"类型"
		},
		{
			"headId":"inHosDate",
			"label":"入院日期"
		},
		{
			"headId":"recStatus",
			"label":"状态"
		},
		{
			"headId":"caseType",
			"label":"病历类型"
		},
		{
			"headId":"remark",
			"label":"备注"
		},
		{
			"headId":"record",
			"label":"记录"
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
		"title":"病种"
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
				"diseaseName":${pdfn:toJsonString(recMap.diseaseName)},
				"patientName":${pdfn:toJsonString(recMap.patientName)},
				"caseNo":${pdfn:toJsonString(recMap.caseNo)},
				"treatMeasure":${pdfn:toJsonString(recMap.treatMeasure)},
				"recType":${pdfn:toJsonString(recMap.recType)},
				"inHosDate":${pdfn:toJsonString(recMap.inHosDate)},
				"recStatus":${pdfn:toJsonString(recMap.recStatus)},
				"caseType":${pdfn:toJsonString(recMap.caseType)},
				"remark":${pdfn:toJsonString(recMap.remark)},
				"record":${pdfn:toJsonString(recMap.record)},
				"statusDesc":${pdfn:toJsonString(auditName)},
				"statusId":${pdfn:toJsonString(auditId)}
			}
			<c:if test="${!status.last}">
				,
			</c:if>
		</c:forEach>
	]