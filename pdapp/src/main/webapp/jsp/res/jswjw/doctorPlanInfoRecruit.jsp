<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"doctorFlow":${pdfn:toJsonString(userFlow)},
	"orgFlow":${pdfn:toJsonString(orgFlow)},
	"orgName":${pdfn:toJsonString(orgName)},
	"catSpeName":${pdfn:toJsonString(catSpeName)},
	"speName":${pdfn:toJsonString(speName)},
	"jointOrgFlow":${pdfn:toJsonString(jointOrgFlow)},
	"jointOrgName":${pdfn:toJsonString(jointOrgName)},
	"recruitYear":${pdfn:toJsonString(assignYear)},
	"sessionNumber":${pdfn:toJsonString(assignYear)},
	"placeId":${pdfn:toJsonString(placeId)},
	"placeName":${pdfn:toJsonString(placeName)},
	"inJointOrgTrain":${pdfn:toJsonString(inJointOrgTrain)},
	"speId":${pdfn:toJsonString(speId)}



    </c:if>
}
