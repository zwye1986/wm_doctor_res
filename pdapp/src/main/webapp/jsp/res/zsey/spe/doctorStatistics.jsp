<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	"resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"doctorTypes":[
		<c:forEach items="${doctorTypes}" var="d" varStatus="s1">
			<c:if test="${not empty docTypeMap[d.id] and docTypeMap[d.id] ne '0'}">
			{
				"id":"${d.id}",
				"name":"${d.name}",
				"count":"${empty docTypeMap[d.id]?'0':docTypeMap[d.id]}"
			}
			<c:if test="${not s1.last }">
				,
			</c:if>
			</c:if>
		</c:forEach>
		],
	"dicts":[
		<c:forEach items="${dicts}" var="d" varStatus="s1">
			<c:if test="${not empty docEducationMap[d.dictId] and docEducationMap[d.dictId] ne '0'}">
			{
				"id":"${d.dictId}",
				"name":"${d.dictName}",
				"count":"${empty docEducationMap[d.dictId]?'0':docEducationMap[d.dictId]}"
			}
			<c:if test="${not s1.last }">
				,
			</c:if>
			</c:if>
		</c:forEach>
		]
  </c:if>
}