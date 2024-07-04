<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	"resultId": ${resultId},
	"resultType": ${pdfn:toJsonString(resultType)}
	<c:if test="${resultId eq '200' }">
	,
	<c:if test="${!isAudit}">
		"action":{
			"save":"提交"
		},
	</c:if>
	"haveForm":"${IsForm}",
	"configFlow":"${configFlow}",
	"processFlow":"${processFlow}",
	"doctorFlow":"${doctorFlow}",
	"recordFlow":"${recordFlow}",
	"attendance":"${eval.attendance}",
	"leave":"${eval.leave}",
	"absenteeism":"${eval.absenteeism}",
	"evalContent":${pdfn:toJsonString( eval.evalContent )},
	"evalScore":${pdfn:toJsonString( eval.evalScore )},
	"inputs":[
		 <c:if test="${IsForm eq 'Y'}">
				 <c:set var="count" value="1"></c:set>
				 <c:forEach items="${titleList}" var="title" varStatus="status">
					 <c:set var="itemList" value="${title.items}"/>
					 {
						"inputId":"group${count}",
						"inputType":"group",
						"lable":"${title.name}（${title.score}分）",
						"readonly":true,
						"required":true,
						"haveScore":false,
						"items":[
					 		<c:set var="count2" value="1"></c:set>
							 <c:forEach items="${itemList}" var="item" varStatus="itemStatus">
									{
										"inputId":${pdfn:toJsonString(item.id)},
										"inputType":"text",
										"lable":"${item.name}(${item.score}分)",
										"tip":"${item.name}",
										"placeholder": "0~${item.score}",
										"verify": {
											"max": "${item.score}",
											 "type": "int"
										},
										"haveScore":true,
										"readonly":${isAudit}
									}
								 <c:set var="count2" value="${count2+1}"></c:set>
								 <c:if test="${(not itemStatus.last) }">
									 ,
								 </c:if>
							 </c:forEach>
					 	]
					 }
					 <c:set var="count" value="${count+1}"></c:set>
					 <c:if test="${(not status.last) }">
						 ,
					 </c:if>
				</c:forEach>
		 </c:if>
	]
	,
	"values":[
				<c:if test="${IsForm eq 'Y'}">
					<c:forEach items="${titleList}" var="title" varStatus="status">
					<c:set var="itemList" value="${title.items}"/>
					<c:forEach items="${itemList}" var="item" varStatus="itemStatus">
						{
							"inputId":${pdfn:toJsonString(item.id)},
							"value":${pdfn:toJsonString(valueMap[item.id])}
						}
						,
						</c:forEach>
					</c:forEach>
				</c:if>
	]
	</c:if>
}