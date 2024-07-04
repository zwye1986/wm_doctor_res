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
                    "speName": ${pdfn:toJsonString(data.speName)},
                    "isSelect":"${speId eq data.speId ? 'Y':'N'}"
                }
                <c:if test="${not s.last }">
                    ,
                </c:if>
            </c:forEach>
        ],
    "tables":[
        <c:forEach items="${skillsAssessmentList}" var="bean" varStatus="s">
            <c:set value="${doctorAssessMap[bean.clinicalFlow]}" var="assess"></c:set>
            {
                <c:set var="type" value="${passCfg[bean.subjectFlow]}"></c:set>
                <c:set var="mainKey" value="${bean.subjectFlow}Main"></c:set>
                <c:set var="stations" value="${subjectStationsMap[bean.subjectFlow]}"></c:set>
                <c:set var="partMap" value="${partAllMap[bean.subjectFlow]}"></c:set>
                "clinicalFlow": ${pdfn:toJsonString(bean.clinicalFlow)},
                "clinicalName": ${pdfn:toJsonString(bean.clinicalName)},
                "passScore": ${pdfn:toJsonString(subjectStationsMap[mainKey].allScore+0)},
                "maxScore": ${pdfn:toJsonString(skillMaxScoreMap[bean.clinicalFlow]+0)},
                "minScore": ${pdfn:toJsonString(skillMinScoreMap[bean.clinicalFlow]+0)},
                "type": ${pdfn:toJsonString(type)},
                "examDetail":{
                    "allExamNum": ${pdfn:toJsonString(allExamNumMap[bean.clinicalFlow]+0)},
                    "passExamNum": ${pdfn:toJsonString(passExamNumMap[bean.clinicalFlow]+0)},
                    "unPassExamNum": ${pdfn:toJsonString(unPassExamNumMap[bean.clinicalFlow]+0)},
                    "per": ${pdfn:toJsonString(perMap[bean.clinicalFlow]+0)}
                },
                "datas":[
                    <c:if test="${type eq 'ALL' or type eq 'ALLNOTCFG'}">
                        <c:forEach var="doc" items="${assess.doctorAssessments}" varStatus="s1">
                            <c:set var="scoreKey" value="${bean.clinicalFlow}${doc.doctorFlow}"></c:set>
                            {
                                "id":${pdfn:toJsonString(doc.doctorFlow)},
                                "name":${pdfn:toJsonString(doc.doctorName)},
                                "isPass":${pdfn:toJsonString(doc.isSavePass)},
                                "score":${pdfn:toJsonString(allScoreMap[scoreKey]+0)}
                            }
                            <c:if test="${not s1.last }">
                                ,
                            </c:if>
                        </c:forEach>
                    </c:if>
                    <c:if test="${type eq 'PART'}">
                        <c:forEach var="partInfo" items="${partMap}" varStatus="s1">
                            <c:set var="part" value="${partInfo.value[0]}"></c:set>
                            <c:set var="numKey" value="${bean.clinicalFlow}${partInfo.key}"></c:set>
                            {
                                "id":${pdfn:toJsonString(partInfo.key)},
                                "name":${pdfn:toJsonString(part.partName)},
                                "passNum":${pdfn:toJsonString(partPassExamNumMap[numKey]+0)},
                                "unPassNum":${pdfn:toJsonString(partUnPassExamNumMap[numKey]+0)}
                            }
                            <c:if test="${not s1.last }">
                                ,
                            </c:if>
                        </c:forEach>
                    </c:if>
                    <c:if test="${type eq 'STATION'}">
                        <c:forEach var="station" items="${stations}" varStatus="s1">
                            <c:set var="numKey" value="${bean.clinicalFlow}${station.stationFlow}"></c:set>
                            {
                                "id":${pdfn:toJsonString(station.stationFlow)},
                                "name":${pdfn:toJsonString(station.stationName)},
                                "passNum":${pdfn:toJsonString(stationPassExamNumMap[numKey]+0)},
                                "unPassNum":${pdfn:toJsonString(stationUnPassExamNumMap[numKey]+0)}
                            }
                            <c:if test="${not s1.last }">
                                ,
                            </c:if>
                        </c:forEach>
                    </c:if>
                ]
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
            "maxScore": ${pdfn:toJsonString(maxScore)},
            "minScore": ${pdfn:toJsonString(minScore)},
            "per": ${pdfn:toJsonString(per)}
    }
    </c:if>
}
