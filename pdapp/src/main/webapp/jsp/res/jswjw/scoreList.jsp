<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    
    <c:if test="${resultId eq '200' }">
    ,
    "skillList": [
     	<c:forEach items="${skillList}" var="skill" varStatus="status">
	     	{
				"scoreYear": ${pdfn:toJsonString(skill.scorePhaseId)},
				<c:if test="${2 eq skill.skillScore}">
					"isPass":"缺考",
				</c:if>
				<c:if test="${1 eq skill.skillScore}">
					"isPass":"合格",
				</c:if>
				<c:if test="${0 eq skill.skillScore}">
					"isPass":"不合格",
				</c:if>
				<c:set var="extScore" value="${skillExtScoreMap[skill.scoreFlow]}"></c:set>
				"firstStationScore": ${pdfn:toJsonString(extScore.firstStationScore)},
				"secondStationScore": ${pdfn:toJsonString(extScore.secondStationScore)},
				"thirdStationScore": ${pdfn:toJsonString(extScore.thirdStationScore)},
				"fourthStationScore": ${pdfn:toJsonString(extScore.fourthStationScore)},
				"fifthStationScore": ${pdfn:toJsonString(extScore.fifthStationScore)},
				"sixthStationScore": ${pdfn:toJsonString(extScore.sixthStationScore)},
				"seventhStationScore": ${pdfn:toJsonString(extScore.seventhStationScore)},
				"eighthStationScore": ${pdfn:toJsonString(extScore.eighthStationScore)},
				"ninthStationScore": ${pdfn:toJsonString(extScore.ninthStationScore)}
			}
			<c:if test="${not status.last }">
				,
			</c:if>
     	</c:forEach>
    ]
    ,
    "theoryList": [
     	<c:forEach items="${theoryList}" var="theory" varStatus="status">
	     	{
				"scoreYear": ${pdfn:toJsonString(theory.scorePhaseId)},
				"theoryScore":${pdfn:toJsonString(theory.theoryScore)},
		 		<%--<c:set var="scorePass" value="${scoreCfgMap[theory.scorePhaseId]}"/>--%>
		 		<%--<c:if test="${empty scorePass}">--%>
					<%--<c:set var="scorePass" value="60"/>--%>
				<%--</c:if>--%>
		 		<%--<c:if test="${scorePass<=theory.theoryScore}">--%>
					<%--"isPass":"是"--%>
				<%--</c:if>--%>
		 		<%--<c:if test="${scorePass>theory.theoryScore}">--%>
					<%--"isPass":"否"--%>
				<%--</c:if>--%>
				<c:if test="${2 eq theory.theoryScore}">
					"isPass":"缺考",
				</c:if>
				<c:if test="${1 eq theory.theoryScore}">
					"isPass":"合格",
				</c:if>
				<c:if test="${0 eq theory.theoryScore}">
					"isPass":"不合格",
				</c:if>
			}
			<c:if test="${not status.last }">
				,
			</c:if>
     	</c:forEach>
    ]
    </c:if>
}
