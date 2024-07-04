<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "dataType":${pdfn:toJsonString(param.type)},
    "inputs": [
     	<c:if test="${param.type eq 'Paper'}">
    		<jsp:include page="inputPaper.jsp"></jsp:include>
    	</c:if>
    	<c:if test="${param.type eq 'Part'}">
    		<jsp:include page="inputPart.jsp"></jsp:include>
    	</c:if>
    ],
    "recordFlow":"${resultData.recordFlow}",
    "values": [
        <c:if test="${param.type eq 'Paper' and not empty resultData}">
            {
                "inputId": "paperDate",
                "value":"${resultData.paperDate}"
            },
            {
                "inputId": "paperTitle",
                "value":"${resultData.paperTitle}"
            },
            {
                "inputId": "paperTypeId",
                "value":"${resultData.paperTypeId}"
            },
            {
                "inputId": "publishedJournals",
                "value":"${resultData.publishedJournals}"
            },
            {
                "inputId": "author",
                "value":"${resultData.author}"
            }
        </c:if>
        <c:if test="${param.type eq 'Part' and not empty resultData}">
            {
                "inputId": "participationDate",
                "value":"${resultData.participationDate}"
            },
            {
                "inputId": "participationRoom",
                "value":"${resultData.participationRoom}"
            },
            {
                "inputId": "participationAuthor",
                "value":"${resultData.participationAuthor}"
            },
            {
                "inputId": "participationTitle",
                "value":"${resultData.participationTitle}"
            },
            {
                "inputId": "participationRole",
                "value":"${resultData.participationRole}"
            },
            {
                "inputId": "participationComplete",
                "value":"${resultData.participationComplete}"
            }
        </c:if>
        ]
    </c:if>
}
