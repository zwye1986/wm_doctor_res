<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	"resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"years":[
			<c:forEach step="1" begin="${year-2}" end="${year}" varStatus="num" var="y">
				{
					"id":"${y}",
					"name":"${y}"
				}
				<c:if test="${not num.last }">
				,
				</c:if>
			</c:forEach>
		],
	"datas":[
		<c:forEach items="${dicts}" var="d" varStatus="s1">
			{
				"id":"${d.dictId}",
				"name":"${d.dictName}",
				"list":[
					<c:forEach step="1" begin="${year-2}" end="${year}" varStatus="num" var="y">
						<c:set value="${y}${d.dictId}" var="key"></c:set>
						{
							"id":"${y}",
							"name":"${y}",
							"count":"${(empty docEducationMap[key]?'0':docEducationMap[key])}"
						}
						<c:if test="${not num.last }">
							,
						</c:if>
					</c:forEach>
				]
			}
			<c:if test="${not s1.last }">
				,
			</c:if>
		</c:forEach>
		]
  </c:if>
}