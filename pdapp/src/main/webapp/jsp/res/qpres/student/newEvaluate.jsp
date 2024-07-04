<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
	    ,
	<c:set var="inputType" value="text"/>
	<c:if test="${empty resLectureEvaDetail}">
        "action":{
            "save":"提交"
        },
    </c:if>
	"inputs":[
		<c:forEach items="${assessMaps}" var="assess" varStatus="assessStatus">
			{
				"inputId":${pdfn:toJsonString(assess.id)},
				"label":${pdfn:toJsonString(assess.name)},
				"tip":${pdfn:toJsonString(assess.name)},
				"inputType":"title"
			}
			<c:set var="items" value="${assess.items}"/>

			<c:if test="${empty items}">
				<c:if test="${!assessStatus.last}">
					,
				</c:if>
			</c:if>

			<c:if test="${!empty items}">
				,
				<c:forEach items="${items}" var="item" varStatus="itemStatus">
					<c:set var="scoreInputId" value="${item.id}_score"/>
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
						"required":true,
						"placeholder": "0~${item.score}"
						<c:if test="${!isNine}">
							,
							"verify": {
				                "max": ${pdfn:toJsonString(item.score)},
				                "type": "int"
			            	}
		            	</c:if>
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
						}
						<c:if test="${!itemStatus.last}">
							,
						</c:if>
				</c:forEach>
			</c:if>
		</c:forEach>
		,
		{
			"inputId":"evaContent",
			"label":"意见或建议",
			"inputType":"textarea",
			"required":true
		}
	]
	,
	"values":[
		<c:forEach items="${formDataMap}" var="data" varStatus="dataStatus">
			{
				"inputId":${pdfn:toJsonString(data.inputId)},
				"value":${pdfn:toJsonString(data.value)}
			}
			,
		</c:forEach>
		{
			"inputId":"evaContent",
			"value":${pdfn:toJsonString(resLectureEvaDetail.evaContent)}
		}
	]
    </c:if>
}