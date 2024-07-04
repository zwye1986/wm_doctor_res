<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"dataCount": ${dataCount + 1},
	"doccount": "${count+0}",
	"tcount": "${tcount+0}",
    "datas": [
		<c:if test="${param.pageIndex eq '1'}">
			{
				"teacherFlow":"",
				"userName":"全部",
				"userHeadImg":"",
				"deptFlow":"",
				"deptName":""
			}
			<c:if test="${not empty list}">
				,
			</c:if>
		</c:if>
		<c:forEach items="${list}" var="bean" varStatus="s">
	     	{
				"teacherFlow":"${bean.userFlow}",
				"userName":"${bean.userName}",
				"userHeadImg":"${bean.userHeadImg}",
				"deptFlow":"${bean.deptFlow}",
				"deptName":"${bean.deptName}"
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}