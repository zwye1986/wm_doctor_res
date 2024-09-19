<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	 "cfgFlow": ${pdfn:toJsonString(assessCfg.cfgFlow)},
	 "resultId": ${resultId},
     "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"processFlow":${pdfn:toJsonString(param.processFlow)},
	"deptFlow":${pdfn:toJsonString(param.deptFlow)},
	"resultFlow":${pdfn:toJsonString(param.resultFlow)},
	"recTypeId":${pdfn:toJsonString(param.recTypeId)},
	"recFlow":${pdfn:toJsonString(rec.recFlow)},
	"items":[
		<c:if test="${empty rec}">
		<c:forEach items="${assessMaps}" var="ass">
		<c:forEach items="${ass.items}" var="item" varStatus="s">
		{
			"inputId":${pdfn:toJsonString(item.id)},
			"type":${pdfn:toJsonString(item.type)},
			"name":${pdfn:toJsonString(item.name)},
			"score":${pdfn:toJsonString(gradeMap[item.id]['score'])}
		}

		<c:if test="${not s.last }">
	,
		</c:if>
		</c:forEach>
		</c:forEach>
		</c:if>
		<c:if test="${not empty rec}">
		<c:forEach items="${titleFormList}" var="title">
		<c:forEach items="${title.itemList}" var="item" varStatus="s">
		{
			"inputId":${pdfn:toJsonString(item.id)},
			"type":${pdfn:toJsonString(item.type)},
			"name":${pdfn:toJsonString(item.name)},
			"score":${pdfn:toJsonString(gradeMap[item.id]['score'])}
		}

		<c:if test="${not s.last }">
	,
		</c:if>
		</c:forEach>
		</c:forEach>
		</c:if>
	,
		{
			"name":"主观评价",
			"inputId":"lostReason",
			"score":${pdfn:toJsonString(gradeMap['lostReason'])}
		},
		{
			"name":"总分",
			"inputId":"totalScore",
			"score":${pdfn:toJsonString(gradeMap['totalScore'])}
		}
	]
	</c:if>
}
