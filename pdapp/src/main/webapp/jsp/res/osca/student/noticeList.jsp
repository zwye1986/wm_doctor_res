<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"pageIndex": ${param.pageIndex},
	"pageSize": ${param.pageSize},
	"dataCount": ${dataCount},
    "datas": [
		<c:forEach items="${infos}" var="info" varStatus="s">
	     	{
				"infoFlow":"${info.infoFlow}",
				"infoTitle":${pdfn:toJsonString(info.infoTitle)},
				"infoTime":${pdfn:toJsonString(pdfn:transDate(info.createTime))},
				"infoContent":${pdfn:toJsonString(pdfn:cutString(info.infoContent,18,true,1))}
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}
