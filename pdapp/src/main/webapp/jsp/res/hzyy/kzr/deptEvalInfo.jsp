<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
	<c:if test="${resultId eq '200' }">
        ,"year":"${year}"
        ,"datas":[
				 <c:forEach items="${evals}" var="eval" varStatus="status">
					 {
						"ReqItemName":"${eval.ReqItemName}",
						"Grade":"${eval.Grade}",
						"ExamInfoDF":"${eval.ExamInfoDF}",
						"HosSecID":"${eval.HosSecID}",
						"HosSecName":"${eval.HosSecName}"
					 }
                    <c:if test="${(not itemStatus.last) }">
                     ,
                    </c:if>
				</c:forEach>
        ]
	</c:if>
}
