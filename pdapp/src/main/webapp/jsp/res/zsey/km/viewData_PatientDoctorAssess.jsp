<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>

	<c:set var="isNine" value="${assessCfg.assessTypeId eq 'Nine'}"/>

	<c:set var="inputType" value="text"/>
	<c:if test="${!isAudit}">
		"action":{
			"save":"提交评分"
		},
	</c:if>
	"inputs":[
		<c:forEach items="${titleFormList}" var="assess" varStatus="assessStatus">

			{
				"inputId":${pdfn:toJsonString(assess.id)},
				"label":${pdfn:toJsonString(assess.name)},
				"tip":${pdfn:toJsonString(assess.name)},
				"score":${pdfn:toJsonString(assess.score)},
				<c:if test="${assess.evalTypeId eq 'EvalAll' }">
					"inputType":"text",
					"required":true,
					"readonly":${isAudit},
					"placeholder": "0~${assess.score}",
					"verify": {
						"max": ${pdfn:toJsonString(assess.score)},
						"type": "int"
					}
				</c:if>
				<c:if test="${assess.evalTypeId ne 'EvalAll' }">
					"inputType":"title"
				</c:if>
				,
				<c:set var="items" value="${assess.itemList}"/>
				"items":[
					<c:forEach items="${items}" var="item" varStatus="itemStatus">
						{
							"inputId":${pdfn:toJsonString(item.id)},
							"label":${pdfn:toJsonString(item.name)},
							"tip":${pdfn:toJsonString(item.name)},
							"score":${pdfn:toJsonString(item.score)},
							<c:if test="${assess.evalTypeId eq 'EvalAll' }">
								"inputType":"title"
							</c:if>
							<c:if test="${assess.evalTypeId ne 'EvalAll' }">
								"inputType":"text",
								"required":true,
								"readonly":${isAudit},
								"placeholder": "0~${item.score}",
								"verify": {
									"max": ${pdfn:toJsonString(item.score)},
									"type": "int"
								}
							</c:if>
						}
						<c:if test="${!itemStatus.last}">
							,
						</c:if>
					</c:forEach>
				]
			}
			,
		</c:forEach>
			{
				"inputId":"totalScore",
				"label":"总分",
				"tip":"总分",
				"score":60,
				"inputType":"text",
				"required":true,
				"readonly":true,
				"placeholder": "0~60",
				"items":[
				]
			},
			{
				"inputId":"evalName",
				"label":"患者",
				"tip":"患者",
				"score":0,
				"inputType":"input",
				"required":true,
				"readonly":${isAudit}
			}
	]
	,
	"values":[
		<c:forEach items="${titleFormList}" var="assess" varStatus="assessStatus">
			{
				"inputId":${pdfn:toJsonString(assess.id)},
				"value":${pdfn:toJsonString(gradeMap[assess.id]['score'])}
			}
			<c:set var="items" value="${assess.itemList}"/>
			<c:if test="${empty items}">
				,
			</c:if>
			<c:if test="${!empty items}">
				,
				<c:forEach items="${items}" var="item" varStatus="itemStatus">
					{
						"inputId":${pdfn:toJsonString(item.id)},
						"value":${pdfn:toJsonString(gradeMap[item.id]['score'])}
					}
					,
				</c:forEach>
			</c:if>
		</c:forEach>
			{
				"inputId":"totalScore",
				"value":${pdfn:toJsonString(gradeMap['totalScore'])}
			},
			{
				"inputId":"evalName",
				"value":${pdfn:toJsonString(gradeMap['evalName'])}
			}
	]
