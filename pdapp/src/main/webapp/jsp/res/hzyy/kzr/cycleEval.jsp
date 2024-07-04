<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
	,"datas":[
            {
                "inputId":"eval",
                "label":"过程评价",
                "inputType":"textarea",
                "required": true,
                "readonly": true,
                "value":${pdfn:toJsonString(evalInfo.Eval)}
            }
         ]
    </c:if>
}