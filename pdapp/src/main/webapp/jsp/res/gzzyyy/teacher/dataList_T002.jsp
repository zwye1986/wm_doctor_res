<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"action":{
		"save":"一键审核"
	},
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
	"head":[
		{
			"headId":"cata",
			"label":" 类型"
		},
		{
			"headId":"time",
			"label":"填写日期"
		},
		{
			"headId":"content",
			"label":"填写内容"
		}
	],
	"datas":[
		<c:forEach items="${recMaps}" var="recMap" varStatus="status">
			<c:set var="rec" value="${recMap.rec}" />
			<c:set var="auditName" value="未审核"/>
			<c:set var="auditId" value="Saved"/>
			<c:if test="${!empty rec.auditStatusId}">
				<c:set var="auditName" value="已审核"/>
				<c:set var="auditId" value="Audited"/>
			</c:if>
			{
				"dataFlow":${pdfn:toJsonString(rec.recFlow)},
				"cata":${pdfn:toJsonString(rec.recTypeName)},
				"time":${pdfn:toJsonString(pdfn:transDate(rec.operTime))},
				"content":${pdfn:toJsonString(recMap.content)},
				"statusDesc":${pdfn:toJsonString(auditName)},
				"statusId":${pdfn:toJsonString(auditId)}
			}
			<c:if test="${!status.last}">
				,
			</c:if>
		</c:forEach>
	]