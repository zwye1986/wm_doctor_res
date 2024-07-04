<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"dataCount": ${dataCount},
    "datas": [
		<c:forEach items="${datas}" var="data" varStatus="s">
	     	{
				"userName": ${pdfn:toJsonString(data.USER_NAME)},
				"userFlow": ${pdfn:toJsonString(data.DOCTOR_FLOW)},
				"speId": ${pdfn:toJsonString(data.SPE_ID)},
				"isLocal": ${pdfn:toJsonString(data.IS_LOCAL)},
				"speName": ${pdfn:toJsonString(data.OSA_NAME)},
				"isPass": ${pdfn:toJsonString(data.IS_PASS)},
				"isPassName": ${pdfn:toJsonString(data.IS_PASS_NAME)},
				"clinicalFlow": ${pdfn:toJsonString(data.CLINICAL_FLOW)},
				"clinicalName": ${pdfn:toJsonString(data.CLINICAL_NAME)},
				"isShowFrom": ${pdfn:toJsonString(data.IS_SHOW_FROOM)},
				"examStartTime":${pdfn:toJsonString(data.EXAM_START_TIME)},
				"examEndTime":${pdfn:toJsonString(data.EXAM_END_TIME)},
				"allScore": ${pdfn:toJsonString(data.ALL_SCORE)}
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}