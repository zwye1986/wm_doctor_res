<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="cKey" value="${param.deptFlow}CaseRegistry"/>
<c:set var="drKey" value="${param.deptFlow}DiseaseRegistry"/>
<c:set var="orKey" value="${param.deptFlow}OperationRegistry"/>
<c:set var="srKey" value="${param.deptFlow}SkillRegistry"/>
<c:set var="cmKey" value="${param.deptFlow}CampaignNoItemRegistry"/>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"funcList": [
        <c:forEach var="recType" items="${list}" varStatus="s">

            <c:set var="key" value="${param.deptFlow}${recType.id}"/>
            {
                <c:if test="${recType.haveItem eq 'N'}">
                    "funcTypeId": "dataInput1N",
                    "funcTypeName": "数据列表",
                </c:if>
                <c:if test="${recType.haveItem eq 'Y'}">
                    "funcTypeId": "dataInputNN",
                    "funcTypeName": "数据列表",
                </c:if>
                "funcFlow": "${recType.id}",
                "funcName": "${recType.name}",
                "disabled": ${disabled},
                "disabledTip": ${pdfn:toJsonString(disabledTip)},
                "img": "${recType.id}",
                "progress":${pdfn:toJsonString(perMap[key]+0)}
            }
            <c:if test="${!s.last}">
                ,
            </c:if>
        </c:forEach>
    ]
    </c:if>
}
