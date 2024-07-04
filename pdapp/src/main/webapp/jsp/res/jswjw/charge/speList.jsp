<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	<%--"catSpeId": "${catSpeId}",--%>
    "doctorTrainingSpeDatas": [
		<c:forEach items="${doctorTrainingSpeList}" var="b" varStatus="s">
	     	{
				"speId":"${b.dictId}",
				"speName":"${b.dictName}",
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     	</c:forEach>
    ],
	"assiGeneralDatas": [
		<c:forEach items="${assiGeneralList}" var="b" varStatus="s">
			{
			"speId":"${b.dictId}",
			"speName":"${b.dictName}",
			}
			<c:if test="${not s.last }">
				,
			</c:if>
		</c:forEach>
	]
    </c:if>
}
