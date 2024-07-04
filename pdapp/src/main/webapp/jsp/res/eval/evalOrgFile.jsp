<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
"resultId": ${resultId},
"resultType": ${pdfn:toJsonString(resultType)}
<c:if test="${resultId eq '200' }">
    ,
    "data":{
            "userFlow":${pdfn:toJsonString(param.userFlow)},
            "evalYear":${pdfn:toJsonString(param.evalYear)},
            "orgFlow":${pdfn:toJsonString(param.orgFlow)},
            "cfgFlow":${pdfn:toJsonString(param.cfgFlow)}
    },
    "evalScore":${pdfn:toJsonString(result.evalScore)}
</c:if>
}
