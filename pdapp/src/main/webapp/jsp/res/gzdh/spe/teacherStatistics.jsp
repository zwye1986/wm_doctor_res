<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	"resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"all":"${countMap['qty1']}",
	"noAll":"${countMap['qty2']}",
	"datas":[
		<c:forEach items="${list}" var="d" varStatus="s1">
			{
				"deptFlow":"${d.deptFlow}",
				"deptName":"${d.deptName}",
				"allCount":"${d.qty1}",
				"noCount":"${d.qty2}"
			}
			<c:if test="${not s1.last }">
				,
			</c:if>
		</c:forEach>
		]
  </c:if>
}