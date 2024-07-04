<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"dataCount": ${dataCount},
    "datas": [
		<c:forEach items="${sysUserList}" var="bean" varStatus="s">
	     	{
				"userName":"${bean.userName}",
				"userFlow":"${bean.userFlow}",
				"avg":"${averageMap[bean.userFlow]}",
				"gradeList":[
					<c:forEach items="${studentListMap[bean.userFlow]}" var="g" varStatus="s1">
						{
							"recFlow":"${g.recFlow}",
							"docName":"${g.operUserName}",
							"docFlow":"${g.operUserFlow}",
							"gradeScore":"${gradeScoreMap[g.recFlow]}"
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