<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"dataCount": ${dataCount},
	"codeType": ${pdfn:toJsonString(codeType)},
	"activityCodeTime":5,
    "datas": [
		<c:forEach items="${list}" var="b" varStatus="s">
	     	{
				"activityFlow":"${b.activityFlow}",
				"speakerFlow":"${b.speakerFlow}",
				"activityName":"${b.activityName}",
				"activityTypeName":"${b.activityTypeName}",
				"activityAddress":"${b.activityAddress}",
				"activityRemark":"${b.activityRemark}",
				"resultFlow":"${b.resultFlow}",
				"evalScore":"${b.evalScore+0}",
				"userName":"${b.userName}",
				"deptName":"${b.deptName}",
				"startTime":"${b.startTime}",
				"endTime":"${b.endTime}",
				"fileFlow":"${b.fileFlow}",
				"fileName":"${b.fileName}",
				"isRegiest":"${b.isRegiest}",
				"isScan":"${b.isScan}",
				"isScan2":"${b.isScan2}",
				"qrCode1":${pdfn:toJsonString(b.qrCode1)},
				"qrCode2":${pdfn:toJsonString(b.qrCode2)},
				"action":[
					<c:if test="${(fn:length(resultMap[b.activityFlow])+0)<=0}">
						{
							"id":"del",
							"name":"删除"
						}
					</c:if>
				]

			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}
