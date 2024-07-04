<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"dataCount": ${dataCount},
    "datas": [
		<c:forEach items="${roomList}" var="data" varStatus="s">
	     	{
				"roomFlow": ${pdfn:toJsonString(data.dictId)},
				"roomName": ${pdfn:toJsonString(data.dictName)},
				"partners":[
					<c:forEach items="${itemsMap[data.dictId]}" var="item" varStatus="s1">
						{
							"partnerFlow": ${pdfn:toJsonString(item.partnerFlow)},
							"partnerName": ${pdfn:toJsonString(item.partnerName)},
							"speName": ${pdfn:toJsonString(item.speName)},
							"stationName": ${pdfn:toJsonString(item.stationName)},
							"stationFlow": ${pdfn:toJsonString(item.stationFlow)},
							"doctorFlow": ${pdfn:toJsonString(item.doctorFlow)},
							"doctorName": ${pdfn:toJsonString(item.doctorName)},
							"notExamNum": ${pdfn:toJsonString(item.notExamNum+0)},
							"assessIngNum": ${pdfn:toJsonString(item.AssessIng+0)},
							"assessmentNum": ${pdfn:toJsonString(item.Assessment+0)}
						}
						<c:if test="${not s1.last }">
							,
						</c:if>
					</c:forEach>

				]
			}
			<c:if test="${not s.last }">
				,
			</c:if>
    	 </c:forEach>
    ]
    </c:if>
}