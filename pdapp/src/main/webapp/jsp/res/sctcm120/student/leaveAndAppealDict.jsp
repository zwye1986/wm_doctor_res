<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "leaveTypes": [
		<c:forEach items="${leaveTypes}" var="b" varStatus="s">
	     	{
				"typeId":"${b.dictId}",
				"typeName":"${b.dictName}"
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    ,
    "status": [
	     	{
				"typeId":"",
				"typeName":"全部"
			}
			,
	     	{
				"typeId":"Auditing",
				"typeName":"待审核"
			}
			,
	     	{
				"typeId":"Passed",
				"typeName":"审核通过"
			}
			,
	     	{
				"typeId":"UnPassed",
				"typeName":"审核不通过"
			}
			,
	     	{
				"typeId":"Revoke",
				"typeName":"已撤销"
			}
    ]
    ,
    "appealTypes": [
		<c:forEach items="${appealTypes}" var="b" varStatus="s">
	     	{
				"typeId":"${b.dictId}",
				"typeName":"${b.dictName}"
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    ,
    "processes": [
		<c:forEach items="${processes}" var="b" varStatus="s">
	     	{
				"processFlow":"${b.processFlow}",
				"schDeptName":"${b.schDeptName}",
				"schStartDate":"${b.schStartDate}",
				"schEndDate":"${b.schEndDate}"
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}
