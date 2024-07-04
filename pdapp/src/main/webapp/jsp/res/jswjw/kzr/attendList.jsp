<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"dataCount": ${dataCount},
    "datas": [
		<c:forEach items="${list}" var="bean" varStatus="s">
	     	{
				"userName":"${bean.userName}",
				"schStartDate":"${bean.schStartDate}",
				"schEndDate":"${bean.schEndDate}",
				"userHeadImg":"${bean.userHeadImg}",
				"docFlow":"${bean.userFlow}",
				"resultFlow":"${bean.resultFlow}",
				"sessionNumber":"${bean.sessionNumber}",
				"speName":"${bean.speName}",
				"deptName":"${bean.deptName}",
				"cq":"${bean.cq+0}",
				"qq":"${bean.qq+0}",
				"lx":"${bean.lx+0}"

			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}