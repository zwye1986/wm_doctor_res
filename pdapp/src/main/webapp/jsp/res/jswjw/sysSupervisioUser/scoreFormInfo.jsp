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
		"editFlag":"${editFlag}",
		"evaluationDate":"${evaluationDate}",
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
		]

    </c:if>
}
