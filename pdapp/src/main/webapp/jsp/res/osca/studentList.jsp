<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"dataCount": ${dataCount},
    "datas": [
		<c:forEach items="${list}" var="data" varStatus="s">
	     	{

			<c:set var="headImg" value="${uploadBaseUrl}/${empty data.docUser.userHeadImg?'default.png':data.docUser.userHeadImg}"/>
				"userName": ${pdfn:toJsonString(data.docUser.userName)},
				"doctorFlow": ${pdfn:toJsonString(data.docUser.userFlow)},
				"headImg": ${pdfn:toJsonString(headImg)},
				"trainingSpeName": ${pdfn:toJsonString(data.doctor.trainingSpeName)},
				"sessionNumber": ${pdfn:toJsonString(data.doctor.sessionNumber)},
				"examTime": ${pdfn:toJsonString(data.bean.examTime)},
				"statusId": ${pdfn:toJsonString(data.bean.statusId)},
				"statusName": ${pdfn:toJsonString(data.bean.statusName)},
				"clinicalFlow":${pdfn:toJsonString(data.codeInfo.clinicalFlow)},
				"recordFlow":${pdfn:toJsonString(data.codeInfo.recordFlow)},
				"dataFlow":${pdfn:toJsonString(data.bean.recordFlow)}
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}