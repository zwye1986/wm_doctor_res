<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "speList":[
            <c:forEach items="${speList}" var="data" varStatus="s">
                {
                    "speId": ${pdfn:toJsonString(data.speId)},
                    "speName": ${pdfn:toJsonString(data.speName)}
                }
                <c:if test="${not s.last }">
                    ,
                </c:if>
            </c:forEach>
        ],
    "examDetail":{
            "allExamNum": ${pdfn:toJsonString(allExamNum)},
            "passExamNum": ${pdfn:toJsonString(passExamNum)},
            "unPassExamNum": ${pdfn:toJsonString(unPassExamNum)},
            "per": ${pdfn:toJsonString(per)}
        },
    "signinMap":{
            "allNum":${pdfn:toJsonString(allSignInNum)},
            "items":[
                        <c:forEach items="${speList}" var="data" varStatus="s">
                            {
                                "speId": ${pdfn:toJsonString(data.speId)},
                                "speCount": ${pdfn:toJsonString(signinMap[data.speId]+0)}
                            }
                            <c:if test="${not s.last }">
                                ,
                            </c:if>
                        </c:forEach>
                    ]
        },
    "partnerMap":{
            "allNum":${pdfn:toJsonString(allPartnerNum)},
            "items":[
                        <c:forEach items="${speList}" var="data" varStatus="s">
                            {
                                "speId": ${pdfn:toJsonString(data.speId)},
                                "speCount": ${pdfn:toJsonString(partnerMap[data.speId]+0)}
                            }
                            <c:if test="${not s.last }">
                                ,
                            </c:if>
                        </c:forEach>
                    ]
        },
    "roomMap":{
            "allNum":${pdfn:toJsonString(allRoomNum)},
            "items":[
                        <c:forEach items="${speList}" var="data" varStatus="s">
                            {
                                "speId": ${pdfn:toJsonString(data.speId)},
                                "speCount": ${pdfn:toJsonString(roomMap[data.speId]+0)}
                            }
                            <c:if test="${not s.last }">
                                ,
                            </c:if>
                        </c:forEach>
                    ]
        }
    </c:if>
}
