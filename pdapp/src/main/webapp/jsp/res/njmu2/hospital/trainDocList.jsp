<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"pageIndex": ${pageIndex},
    "pageSize": ${pageSize},
    "dataCount": ${dataCount},
    "doctorList": [
    	<c:forEach items="${doctorExtList}" var="doctorExt" varStatus="status">
			<c:set var="result" value="${processCountMap[doctorExt.doctorFlow]}/${resultCountMap[doctorExt.doctorFlow]}"/>
	        {
	            "userName": ${pdfn:toJsonString(doctorExt.sysUser.userName)},
	            "sexName": ${pdfn:toJsonString(doctorExt.sysUser.sexName)},
	            "userPhone": ${pdfn:toJsonString(doctorExt.sysUser.userPhone)},
	            "sessionNumber": ${pdfn:toJsonString(doctorExt.sessionNumber)},
	            "trainingSpeName": ${pdfn:toJsonString(doctorExt.trainingSpeName)},
	            "inHosDate": ${pdfn:toJsonString(doctorExt.inHosDate)},
	            "idNo": ${pdfn:toJsonString(doctorExt.sysUser.idNo)},
				"rotateInfo": ${pdfn:toJsonString(result)},
				"doctorFlow": ${pdfn:toJsonString(doctorExt.doctorFlow)}
	        }
			<c:if test="${!status.last}">
				,
			</c:if>
    	</c:forEach>
    ]
    </c:if>
}