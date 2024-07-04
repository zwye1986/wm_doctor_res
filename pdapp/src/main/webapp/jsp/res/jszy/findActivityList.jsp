<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"dataCount": ${dataCount},
	"depts": [
	<c:if test="${not empty depts}">
		{
		"deptFlow":"",
		"deptName":"全部科室"
		},
	</c:if>
	<c:forEach items="${depts}" var="dept" varStatus="s">
		{
		"deptFlow":"${dept.deptFlow}",
		"deptName":"${dept.deptName}"
		}
		<c:if test="${not s.last }">
			,
		</c:if>
	</c:forEach>
	],
    "datas": [
		<c:forEach items="${list}" var="b" varStatus="s">
	     	{
				"activityFlow": ${pdfn:toJsonString(b.activityFlow)},
				"activityName": ${pdfn:toJsonString(b.activityName)},
				"activityTypeName": ${pdfn:toJsonString(b.activityTypeName)},
				"activityAddress": ${pdfn:toJsonString(b.activityAddress)},
				"activityRemark": ${pdfn:toJsonString(b.activityRemark)},
				"resultFlow": ${pdfn:toJsonString(b.resultFlow)},
				"userName": ${pdfn:toJsonString(b.userName)},
				"deptName": ${pdfn:toJsonString(b.deptName)},
				"startTime": ${pdfn:toJsonString(b.startTime)},
				"endTime": ${pdfn:toJsonString(b.endTime)},
				"fileName": ${pdfn:toJsonString(b.fileName)},
				"isRegiest": ${pdfn:toJsonString(b.isRegiest)},
				"isScan": ${pdfn:toJsonString(b.isScan)},
				"isScan2": ${pdfn:toJsonString(b.isScan2)},
				"operId":
					<c:choose>
						<c:when test="${param.typeId eq 'isNew' and empty b.resultFlow and b.startTime >= nowDate}">
							"Regiest",
						</c:when>
						<c:when test="${param.typeId  eq 'isEval' and b.isScan eq 'Y'and b.isScan2 eq 'Y' and empty b.evalScore2}">
							"Evaluate",
						</c:when>
						<c:when test="${param.typeId  eq 'isEval' and b.isScan eq 'Y'and b.isScan2 eq 'Y' and not empty b.evalScore2}">
							"ShowEvaluate",
						</c:when>
						<c:when test="${ not empty b.resultFlow and b.isRegiest eq 'Y' and b.isScan ne 'Y'}">
							"CannelRegiest",
						</c:when>
						<c:when test="${ not empty b.resultFlow and b.isRegiest eq 'N' and b.isScan ne 'Y' and b.startTime >= nowDate}">
							"Regiest",
						</c:when>
						<c:otherwise>
							"",
						</c:otherwise>
					</c:choose>
				"operName":
					<c:choose>
						<c:when test="${param.typeId eq 'isNew' and empty b.resultFlow and b.startTime >= nowDate}">
							"报名",
						</c:when>
						<c:when test="${param.typeId  eq 'isEval' and b.isScan eq 'Y'and b.isScan2 eq 'Y' and empty b.evalScore2}">
							"评价",
						</c:when>
						<c:when test="${param.typeId  eq 'isEval' and b.isScan eq 'Y'and b.isScan2 eq 'Y' and not empty b.evalScore2}">
							"查看评价",
						</c:when>
						<c:when test="${not empty b.resultFlow and b.isRegiest eq 'Y' and b.isScan ne 'Y'}">
							"取消报名",
						</c:when>
						<c:when test="${ not empty b.resultFlow and b.isRegiest eq 'N' and b.isScan ne 'Y' and b.startTime >= nowDate}">
							"报名",
						</c:when>
						<c:otherwise>
							"",
						</c:otherwise>
					</c:choose>

			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}
