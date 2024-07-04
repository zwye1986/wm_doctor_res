<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	 "cfgFlow": ${pdfn:toJsonString(cfgFlow)},
	 "resultId": ${resultId},
     "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	<c:set var="isNine" value="${assessCfg.assessTypeId eq 'Nine'}"/>
	<c:set var="inputType" value="text"/>
	<c:if test="${isNine}">
		<c:set var="inputType" value="select"/>
	</c:if>
	"inputs":[
		<c:forEach items="${assessMaps}" var="assess" varStatus="assessStatus">
			{
				"inputId":${pdfn:toJsonString(assess.id)},
				"label":${pdfn:toJsonString(assess.name)},
				"inputType":"title"
			}
			
				<c:set var="items" value="${assess.items}"/>
				<c:if test="${!(assessStatus.last && empty items)}">
				,
			</c:if>
				<c:forEach items="${items}" var="item" varStatus="itemStatus">
					<c:set var="scoreInputId" value="${item.id}_score"/>
					<c:set var="lostReasonInputId" value="${item.id}_lostReason"/>
					
					{
						"inputId":${pdfn:toJsonString(item.id)},
						"label":${pdfn:toJsonString(item.name)},
						"tip":${pdfn:toJsonString(item.name)},
						"inputType":"title"
					},
					{
						"inputId":${pdfn:toJsonString(scoreInputId)},
						"label":"分数",
						"inputType":${pdfn:toJsonString(inputType)},
		            	"placeholder": "0~${item.score}",
						"verify": {
			                "max": ${pdfn:toJsonString(item.score)},
			                "type": "int"
		            	},
						"required":true
						<c:if test="${isNine}">
							,
							"options": [
							    <c:forEach var="score" begin="1" end="9" step="1" varStatus="scorestatus">
									{
									    "optionId": "${score}",
										"optionDesc": "${score}"
									}
									<c:if test='${!scorestatus.last}'>
										,
									</c:if>
								</c:forEach>
							]
						</c:if>
						},
						{
							"inputId":${pdfn:toJsonString(lostReasonInputId)},
							"label":"扣分原因",
							"inputType":"text",
							"required":false
						}
						<c:if test="${!itemStatus.last}">
							,
						</c:if>
				</c:forEach>
			<c:if test='${!assessStatus.last}'>
				,
			</c:if>
		</c:forEach>
	]
	,
	"values":[
		<c:forEach items="${formDataMap}" var="data" varStatus="status">
			{
				"inputId":${pdfn:toJsonString(data.inputId)},
				"value":${pdfn:toJsonString(data.value)}
			}
			<c:if test="${!status.last}">
							,
						</c:if>
		</c:forEach>
	]
	</c:if>
}
