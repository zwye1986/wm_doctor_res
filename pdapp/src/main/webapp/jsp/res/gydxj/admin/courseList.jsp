<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    	,
		"gradationIdList" : [
			<c:forEach items="${dictTypeEnumGyTrainTypeList}" var="gra" varStatus="i">
				{
					"id" : ${pdfn:toJsonString(gra.dictId)},
					"name" : ${pdfn:toJsonString(gra.dictName)}
				}
				<c:if test="${!i.last}">
					,
				</c:if>
			</c:forEach>
		],
		"courseList": [
			<c:forEach items="${courseList}" var="edu" varStatus="i">
				{
				"courseFlow":${pdfn:toJsonString(edu.courseFlow)},
				"courseCode":${pdfn:toJsonString(edu.courseCode)},
				"courseName":${pdfn:toJsonString(edu.courseName)},
				"courseSession":${pdfn:toJsonString(edu.courseSession)},
				"gradationId":${pdfn:toJsonString(edu.gradationId)},
				"gradationName":${pdfn:toJsonString(edu.gradationName)},
				"courseCredit":${pdfn:toJsonString(edu.courseCredit)},
				"coursePeriod":${pdfn:toJsonString(edu.coursePeriod)},
				"courseTypeId":${pdfn:toJsonString(edu.courseTypeId)},
				"courseTypeName":${pdfn:toJsonString(edu.courseTypeName)}
				}
				<c:if test="${!i.last}">
					,
				</c:if>
			</c:forEach>
		],
		"dataCount": ${dataCount}
    </c:if>
}