<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    	,
		"applyTypeList": [
			{"applyTypeId":"","applyTypeName":${pdfn:toJsonString("全部")}},
			<c:forEach items="${userChangeApplyTypeEnumList}" var="app" varStatus="i">
				{
				"applyTypeId":${pdfn:toJsonString(app.id)},
				"applyTypeName":${pdfn:toJsonString(app.name)}
				}
				<c:if test="${!i.last}">
					,
				</c:if>
			</c:forEach>
		],
		"statusIdList": [
			{"applyTypeId":"","applyTypeName":${pdfn:toJsonString("全部")}},
			<c:forEach items="${userChangeApplyStatusEnumList}" var="sta" varStatus="i">
				{
				"applyTypeId":${pdfn:toJsonString(sta.id)},
				"applyTypeName":${pdfn:toJsonString(sta.name)}
				}
				<c:if test="${!i.last}">
					,
				</c:if>
			</c:forEach>
		]
    </c:if>
}