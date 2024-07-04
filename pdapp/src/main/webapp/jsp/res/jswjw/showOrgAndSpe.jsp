<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "orgDatas": [
		<c:forEach items="${resultMap['joint']}" var="org" varStatus="s">
	     	{
				"optionId": ${pdfn:toJsonString(org.jointOrgFlow)},
				"optionDesc": ${pdfn:toJsonString(org.jointOrgName)}
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ],
        "speDatas":[
        <c:forEach items="${resultMap['main']}" var="spe" varStatus="m">
            {
            "optionId": ${pdfn:toJsonString(spe.speId)},
            "optionDesc": ${pdfn:toJsonString(spe.speName)}
            }
            <c:if test="${not m.last }">
                ,
            </c:if>
        </c:forEach>

        ]
    </c:if>
}
