<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"action":{
		"add":"新增",
		"del":"删除"
	},
	"head":[
        <c:forEach var="v" items="${views}" varStatus="s">
            {
                "headId":"${v.itemName}",
                "label":"${v.title}"
            }
            <c:if test="${!s.last}">
                ,
            </c:if>
        </c:forEach>
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
		"title":"${recTypeName}"
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

                <c:forEach var="v" items="${views}" varStatus="s">
                    "${v.itemName}":${pdfn:toJsonString(recMap[v.itemName])},
                </c:forEach>
                "dataFlow":${pdfn:toJsonString(rec.recFlow)},
                "statusDesc":${pdfn:toJsonString(auditName)},
                "statusId":${pdfn:toJsonString(auditId)}
			}
			<c:if test="${!status.last}">
				,
			</c:if>
		</c:forEach>
	]