<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "speList":[
            {
                "speId": "",
                "speName": "全部专业"
            }
            <c:if test="${not empty  speList}">
                ,
            </c:if>
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
    "signDetail":{
            "all": ${pdfn:toJsonString(all)},
            "allSignInNum": ${pdfn:toJsonString(allSignInNum)},
            "allNoSignInNum": ${pdfn:toJsonString(allNoSignInNum)},
            "per": ${pdfn:toJsonString(per)}
        },
    "datas":[
             <c:forEach items="${skillsTimeExts}" var="data" varStatus="s">
                 {
                     "order":${pdfn:toJsonString(s.count)},
                     "speId": ${pdfn:toJsonString(data.speId)},
                     "speName": ${pdfn:toJsonString(data.speName)},
                     "clinicalFlow": ${pdfn:toJsonString(data.clinicalFlow)},
                     "clinicalName": ${pdfn:toJsonString(data.clinicalName)},
                     "timeList":[
                             <c:forEach items="${data.times}" var="t" varStatus="s1">
                                 <c:set value="${data.clinicalFlow}${t.examStartTime}${t.examEndTime}ALL" var="allKey"></c:set>
                                 <c:set value="${data.clinicalFlow}${t.examStartTime}${t.examEndTime}SignIn" var="signInKey"></c:set>
                                 <c:set value="${data.clinicalFlow}${t.examStartTime}${t.examEndTime}NoSignIn" var="noSignInKey"></c:set>
                                 {
                                     "recrodFlow": ${pdfn:toJsonString(t.recrodFlow)},
                                     "examStartTime": ${pdfn:toJsonString(t.examStartTime)},
                                     "examEndTime": ${pdfn:toJsonString(t.examEndTime)},
                                     "all": ${pdfn:toJsonString(countMap[allKey]+0)},
                                     "signIn": ${pdfn:toJsonString(countMap[signInKey]+0)},
                                     "noSignIn": ${pdfn:toJsonString(countMap[noSignInKey]+0)}
                                 }
                                 <c:if test="${not s1.last }">
                                   ,
                                 </c:if>
                             </c:forEach>
                        ]
                 }
             <c:if test="${not s.last }">
               ,
             </c:if>
             </c:forEach>
     ]
    </c:if>
}
