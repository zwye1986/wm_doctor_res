<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"dataCount": ${dataCount},
    "datas": [
		<c:forEach items="${list}" var="u" varStatus="s1">

			<c:if test="${empty u.userHeadImg}">
				<c:set var="userHeadImg" value=""/>
			</c:if>
			<c:if test="${not empty u.userHeadImg}">
				<c:set var="userHeadImg" value="${uploadBaseUrl}/${u.userHeadImg}"/>
			</c:if>
			{
				"userFlow":${pdfn:toJsonString(u.userFlow)},
				"userName":${pdfn:toJsonString(u.userName)},
				"userHeadImg":${pdfn:toJsonString(userHeadImg)},
				"sessionNumber":${pdfn:toJsonString(u.sessionNumber)},
				"trainingSpeName":${pdfn:toJsonString(u.trainingSpeName)},
				"ringId":${pdfn:toJsonString(u.ringId)},
				"ringName":${pdfn:toJsonString(u.ringName)},
				"userPhone":${pdfn:toJsonString(u.userPhone)},
				<c:if test="${u.doctorTypeId eq 'Company'}">
					"departMentFlow": ${pdfn:toJsonString(u.departMentFlow)},
					"departMentName": ${pdfn:toJsonString(u.departMentName)}
				</c:if>
				<c:if test="${u.doctorTypeId eq 'Social'}">
					"departMentFlow": "--",
					"departMentName": "--"
				</c:if>
				<c:if test="${u.doctorTypeId ne 'Company' and u.doctorTypeId ne 'Social' }">
					"departMentFlow": ${pdfn:toJsonString(u.workOrgId)},
					"departMentName": ${pdfn:toJsonString(u.workOrgName2)}
				</c:if>
			}
			<c:if test="${!status.last}">
				,
			</c:if>
		</c:forEach>
    ]
    </c:if>
}