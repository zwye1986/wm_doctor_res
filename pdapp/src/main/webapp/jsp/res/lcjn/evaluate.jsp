<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"course": {
		"courseFlow":"${info.courseFlow}",
		"courseName":"${info.courseName}",
		"coursePeopleNum":"${info.coursePeopleNum}",
		"courseAddress":"${info.courseAddress}",
		"courseDemo":"${info.courseDemo}",
		"appointStartTime":"${info.appointStartTime}",
		"appointEndTime":"${info.appointEndTime}",
		"isReleased":"${info.isReleased}",
		"isScoreReleased":"${info.isScoreReleased}",
		"codeInfo":"${info.codeInfo}",
		"evaluate":{
			"evaluateFlow":"",
			"evalContent":""
		},
		"evaluateDetails":[
			<c:forEach items="${courseEvaList}" var="bean" varStatus="s">
				{
					"dictId":"${bean.dictId}",
					"dictName":"${bean.dictName}",
					"recordFlow":"",
					"evaluateFlow":"",
					"evalScore":""
				}
				<c:if test="${not s.last }">
				,
				</c:if>
			</c:forEach>
		]
	},
	"teas":[
		<c:forEach items="${teas}" var="bean" varStatus="s">
			{
				"recordFlow":"${bean.recordFlow}",
				"userFlow":"${bean.userFlow}",
				"userName":"${bean.userName}",
				"teachingCost":"${bean.teachingCost}",
				"evaluate":{
					"teaEvaluateFlow":"",
					"evalContent":""
				},
				"evaluateDetails" :
				[
					<c:forEach items="${teacherEvaList}" var="bean" varStatus="s">
					{
						"dictId":"${bean.dictId}",
						"dictName":"${bean.dictName}",
						"recordFlow":"",
						"teaEvaluateFlow":"",
						"evalScore":""
					}
					<c:if test="${not s.last }">
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