<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
		,
		"orgName":"${orgName}",
		"orgCityName":"${orgCityName}",
		"roleFlag":"${roleFlag}",
		"isRead":"${isRead}",
		"subjectFlow":"${subjectFlow}",
		"subjectActivitiFlows":"${subjectActivitiFlows}",
		"editFlag":
		<c:if test="${empty editFlag}">
		"Y",
		</c:if>
		<c:if test="${not empty editFlag}">
			"${editFlag}",
		</c:if>
		"evaluationDate":"${evaluationDate}",
		"speSignUrl":"${speSignUrl}",
		"formName":"${formName}",
		<c:if test="${not empty subjectUser}">
			"speScoreTotal":"${subjectUser.speScoreTotal}",
			"speContent":"${subjectUser.speContent}",
			"proportion":"${subjectUser.proportion}",
			"supervisioResults":"${subjectUser.supervisioResults}",
		</c:if>
	    "speScoreList": [
		<c:if test="${not empty speScoreList}">
	    <c:forEach items="${speScoreList}" var="s" varStatus="status">
			{
				"scoreFlow":${pdfn:toJsonString(s.scoreFlow)},
				"subjectFlow":${pdfn:toJsonString(s.subjectFlow)},
				"subjectName":${pdfn:toJsonString(s.subjectName)},
				"orgFlow":${pdfn:toJsonString(s.orgFlow)},
				"orgName":${pdfn:toJsonString(s.orgName)},
				"speId":${pdfn:toJsonString(s.speId)},
				"speName":${pdfn:toJsonString(s.speName)},
				"itemId":${pdfn:toJsonString(s.itemId)},
				"itemName":${pdfn:toJsonString(s.itemName)},
				"evaluationYear":${pdfn:toJsonString(s.evaluationYear)},
				"ownerScore":${pdfn:toJsonString(s.ownerScore)},
				"speScore":${pdfn:toJsonString(s.speScore)},
				"speReason":${pdfn:toJsonString(s.speReason)},
				"speUserFlow":${pdfn:toJsonString(s.speUserFlow)},
				"speUserName":${pdfn:toJsonString(s.speUserName)},
				"speContent":${pdfn:toJsonString(s.speContent)}
			}
	        <c:if test="${!status.last}">
	        	,
	        </c:if>
	    </c:forEach>
		</c:if>
	    ],
		<%--	自评分	--%>
		"ownerScoreList": [
		<c:if test="${not empty ownerScoreList}">
			<c:forEach items="${ownerScoreList}" var="s" varStatus="status">
				{
				"scoreFlow":${pdfn:toJsonString(s.scoreFlow)},
				"subjectFlow":${pdfn:toJsonString(s.subjectFlow)},
				"subjectName":${pdfn:toJsonString(s.subjectName)},
				"orgFlow":${pdfn:toJsonString(s.orgFlow)},
				"orgName":${pdfn:toJsonString(s.orgName)},
				"speId":${pdfn:toJsonString(s.speId)},
				"speName":${pdfn:toJsonString(s.speName)},
				"itemId":${pdfn:toJsonString(s.itemId)},
				"itemName":${pdfn:toJsonString(s.itemName)},
				"evaluationYear":${pdfn:toJsonString(s.evaluationYear)},
				"ownerScore":${pdfn:toJsonString(s.ownerScore)},
				"speScore":${pdfn:toJsonString(s.speScore)},
				"speReason":${pdfn:toJsonString(s.speReason)},
				"speUserFlow":${pdfn:toJsonString(s.speUserFlow)},
				"speUserName":${pdfn:toJsonString(s.speUserName)},
				"speContent":${pdfn:toJsonString(s.speContent)}
				}
				<c:if test="${!status.last}">
					,
				</c:if>
			</c:forEach>
		</c:if>
		],
		<%--	专家评分	--%>
		"supervisioFileList": [
		<c:if test="${not empty supervisioFileList}">
			<c:forEach items="${supervisioFileList}" var="s" varStatus="status">
				{
				"recordFlow":${pdfn:toJsonString(s.recordFlow)},
				"orgFlow":${pdfn:toJsonString(s.orgFlow)},
				"orgName":${pdfn:toJsonString(s.orgName)},
				"itemId":${pdfn:toJsonString(s.itemId)},
				"itemName":${pdfn:toJsonString(s.itemName)},
				"planYear":${pdfn:toJsonString(s.planYear)},
				"fileName":${pdfn:toJsonString(s.fileName)},
				"fileUrl":${pdfn:toJsonString(s.fileUrl)},
				"speId":${pdfn:toJsonString(s.speId)}
				}
				<c:if test="${!status.last}">
					,
				</c:if>
			</c:forEach>
		</c:if>
		],
		<%--	附表分	--%>
		"scoreList": [
		<c:if test="${not empty scoreList}">
			<c:forEach items="${scoreList}" var="s" varStatus="status">
				{
				"itemId":${pdfn:toJsonString(s.itemId)},
				"itemName":${pdfn:toJsonString(s.itemName)},
				"score":${pdfn:toJsonString(s.score)},
				"itemDetailed":${pdfn:toJsonString(s.itemDetailed)}
				}
				<c:if test="${!status.last}">
					,
				</c:if>
			</c:forEach>
		</c:if>
		]

    </c:if>
}
