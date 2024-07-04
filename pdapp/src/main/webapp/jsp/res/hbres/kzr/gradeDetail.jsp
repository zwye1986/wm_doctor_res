<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	 "resultId": ${resultId},
     "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"totalScore": ${pdfn:toJsonString(gradeMap['totalScore'])},
	<c:set var="isNine" value="${assessCfg.assessTypeId eq 'Nine'}"/>
	<c:set var="allScore" value="0"/>
	"titles":[
		<c:forEach items="${titleFormList}" var="title" varStatus="assessStatus">

			<c:set var="allScore" value="${allScore+title.score}"/>
			<c:set var="items" value="${title.itemList}"/>
			{
				"inputId":${pdfn:toJsonString(title.id)},
				"label":${pdfn:toJsonString(title.name)},
				"score":${pdfn:toJsonString(title.score)},
				"inputType":"title",
				"items":[
					<c:forEach items="${items}" var="item" varStatus="itemStatus">
						<c:set var="scoreInputId" value="${item.id}_score"/>
						<c:set var="lostReasonInputId" value="${item.id}_lostReason"/>
						{
							"inputId":${pdfn:toJsonString(item.id)},
							"label":${pdfn:toJsonString(item.name)},
							"tip":${pdfn:toJsonString(item.name)},
							"inputType":"item",
							"score":
							<c:if test="${!isNine}">
								"${item.score}",
							</c:if>
							<c:if test="${isNine}">
								"9",
							</c:if>
							"evlScore":"${gradeMap[item.id]['score']}"
						}
						<c:if test="${!itemStatus.last}">
						,
						</c:if>
					</c:forEach>
				]
			}
			<c:if test='${!assessStatus.last}'>
				,
			</c:if>
		</c:forEach>
	],
	"allScore": ${pdfn:toJsonString(allScore)}
	</c:if>
}
