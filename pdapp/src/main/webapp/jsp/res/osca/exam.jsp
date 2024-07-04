<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
        ,
    "fromType":"${fromType}",
    "haveFrom":"${haveFrom}",
    "haveOne":"${haveOne}",
    "stationScore":"${stationScore}",
    "examStatusId":${pdfn:toJsonString(examScoreStatusId)},
    <c:set var="headImg" value="${uploadBaseUrl}/${empty docUser.userHeadImg?'default.png':docUser.userHeadImg}"/>
    "headImg": ${pdfn:toJsonString(headImg)},
    "score":{
         "scoreFlow":${pdfn:toJsonString(NotHavaFromScore.scoreFlow)},
         "doctorFlow":${pdfn:toJsonString(NotHavaFromScore.doctorFlow)},
         "doctorName":${pdfn:toJsonString(NotHavaFromScore.doctorName)},
         "recordYear":${pdfn:toJsonString(NotHavaFromScore.recordYear)},
         "roomRecordFlow":${pdfn:toJsonString(NotHavaFromScore.roomRecordFlow)},
         "roomFlow":${pdfn:toJsonString(NotHavaFromScore.roomFlow)},
         "roomName":${pdfn:toJsonString(NotHavaFromScore.roomName)},
         "clinicalFlow":${pdfn:toJsonString(NotHavaFromScore.clinicalFlow)},
         "clinicalName":${pdfn:toJsonString(NotHavaFromScore.clinicalName)},
         "stationFlow":${pdfn:toJsonString(NotHavaFromScore.stationFlow)},
         "stationName":${pdfn:toJsonString(NotHavaFromScore.stationName)},
         "isHaveFrom":${pdfn:toJsonString(NotHavaFromScore.isHaveFrom)},
         "fromFlow":${pdfn:toJsonString(NotHavaFromScore.fromFlow)},
         "fromName":${pdfn:toJsonString(NotHavaFromScore.fromName)},
         "fromTypeId":${pdfn:toJsonString(NotHavaFromScore.fromTypeId)},
         "fromUrl":${pdfn:toJsonString(NotHavaFromScore.fromUrl)},
         "examScore":${pdfn:toJsonString(NotHavaFromScore.examScore)},
         "statusId":${pdfn:toJsonString(NotHavaFromScore.statusId)},
         "statusName":${pdfn:toJsonString(NotHavaFromScore.statusName)},
         "partnerFlow":${pdfn:toJsonString(NotHavaFromScore.partnerFlow)},
         "partnerName":${pdfn:toJsonString(NotHavaFromScore.partnerName)},
         "siginImage":${pdfn:toJsonString(siginImage)}
        },
    "params":{
        "userFlow":${pdfn:toJsonString(paramMap.userFlow)},
        "doctorFlow":${pdfn:toJsonString(paramMap.doctorFlow)},
        "clinicalFlow":${pdfn:toJsonString(paramMap.clinicalFlow)},
        "stationFlow":${pdfn:toJsonString(paramMap.stationFlow)},
        "roomRecordFlow":${pdfn:toJsonString(paramMap.roomRecordFlow)},
        "fromFlow":${pdfn:toJsonString(paramMap.fromFlow)},
        "scoreFlow":${pdfn:toJsonString(paramMap.scoreFlow)},
        "isRequired":${pdfn:toJsonString(b.isRequired)}
        },
    "froms":
        [
        <c:forEach items="${froms}" var="from1" varStatus="status">
            {
                "stationFlow": ${pdfn:toJsonString(station.stationFlow)},
                "stationName": ${pdfn:toJsonString(station.stationName)},
                "clinicalFlow":${pdfn:toJsonString(skillsAssessment.clinicalFlow)},
                "clinicalName":${pdfn:toJsonString(skillsAssessment.clinicalName)},
                "subjectName": ${pdfn:toJsonString(station.subjectName)},
                "subjectFlow": ${pdfn:toJsonString(station.subjectFlow)},
                "fromFlow": ${pdfn:toJsonString(from1.fromFlow)},
                "fromName": ${pdfn:toJsonString(from1.fromName)},
                "isRequired":${pdfn:toJsonString(from1.isRequired)},
                <c:set value="${scoreMap[from1.fromFlow]}" var="score"></c:set>
                "scoreFlow":${pdfn:toJsonString(score.scoreFlow)},
                "examScore":${pdfn:toJsonString(score.examScore)},
                "statusId":${pdfn:toJsonString(score.statusId)},
                "statusName":${pdfn:toJsonString(score.statusName)}
            }
            <c:if test="${not status.last }">
                ,
            </c:if>
        </c:forEach>
        ],
    "notRequiredFroms":
        [
        <c:forEach items="${notRequiredFroms}" var="from1" varStatus="status">
            {
                "stationFlow": ${pdfn:toJsonString(station.stationFlow)},
                "stationName": ${pdfn:toJsonString(station.stationName)},
                "clinicalFlow":${pdfn:toJsonString(skillsAssessment.clinicalFlow)},
                "clinicalName":${pdfn:toJsonString(skillsAssessment.clinicalName)},
                "subjectName": ${pdfn:toJsonString(station.subjectName)},
                "subjectFlow": ${pdfn:toJsonString(station.subjectFlow)},
                "fromFlow": ${pdfn:toJsonString(from1.fromFlow)},
                "fromName": ${pdfn:toJsonString(from1.fromName)},
                "isRequired":${pdfn:toJsonString(from1.isRequired)},
                <c:set value="${scoreMap[from1.fromFlow]}" var="score"></c:set>
                "scoreFlow":${pdfn:toJsonString(score.scoreFlow)},
                "examScore":${pdfn:toJsonString(score.examScore)},
                "statusId":${pdfn:toJsonString(score.statusId)},
                "statusName":${pdfn:toJsonString(score.statusName)}
            }
            <c:if test="${not status.last }">
                ,
            </c:if>
        </c:forEach>
        ],
    "requiredFroms":
        [
        <c:forEach items="${requiredFroms}" var="from1" varStatus="status">
            {
                "stationFlow": ${pdfn:toJsonString(station.stationFlow)},
                "stationName": ${pdfn:toJsonString(station.stationName)},
                "clinicalFlow":${pdfn:toJsonString(skillsAssessment.clinicalFlow)},
                "clinicalName":${pdfn:toJsonString(skillsAssessment.clinicalName)},
                "subjectName": ${pdfn:toJsonString(station.subjectName)},
                "subjectFlow": ${pdfn:toJsonString(station.subjectFlow)},
                "fromFlow": ${pdfn:toJsonString(from1.fromFlow)},
                "fromName": ${pdfn:toJsonString(from1.fromName)},
                "isRequired":${pdfn:toJsonString(from1.isRequired)},
                <c:set value="${scoreMap[from1.fromFlow]}" var="score"></c:set>
                "scoreFlow":${pdfn:toJsonString(score.scoreFlow)},
                "examScore":${pdfn:toJsonString(score.examScore)},
                "statusId":${pdfn:toJsonString(score.statusId)},
                "statusName":${pdfn:toJsonString(score.statusName)}
            }
            <c:if test="${not status.last }">
                ,
            </c:if>
        </c:forEach>
        ],
    "oneFrom":{
        "fromType":"${fromType}",
        "fromParams":{
            "userFlow":${pdfn:toJsonString(param.userFlow)},
            "doctorFlow":${pdfn:toJsonString(param.doctorFlow)},
            "fromFlow":${pdfn:toJsonString(from.fromFlow)},
            "clinicalFlow":${pdfn:toJsonString(param.clinicalFlow)},
            "stationFlow":${pdfn:toJsonString(param.stationFlow)},
            "roomRecordFlow":${pdfn:toJsonString(param.roomRecordFlow)},
            "isRequired":${pdfn:toJsonString(b.isRequired)}
            },
        "from":
            {
            "fromFlow": ${pdfn:toJsonString(from.fromFlow)},
            "fromName": ${pdfn:toJsonString(from.fromName)},
            "fromTypeId": ${pdfn:toJsonString(from.fromTypeId)},
            "fromTypeName": ${pdfn:toJsonString(from.fromTypeName)},
            "fromUrl": ${pdfn:toJsonString(from.fromUrl)},
            "isReleased": ${pdfn:toJsonString(from.isReleased)},
            "fromScore": ${pdfn:toJsonString(from.fromScore)}
        },
        "FGD":{
            <c:set var="tatolScore" value="0"></c:set>
            <c:set var="tatolExamScore" value="0"></c:set>
             "element":
                [
                    <c:set var="count" value="1"></c:set>
                    <c:forEach items="${titleList}" var="title" varStatus="status">
                        <c:set var="itemSubTotalScore" value="0"></c:set>
                        <c:set var="subTotalScore" value="0"></c:set>
                        {
                            "titleId": ${pdfn:toJsonString(title.id)},
                            "titleName" : ${pdfn:toJsonString(title.name)},
                            "itemForm" : [
                                <c:set var="itemList" value="${title.items}"/>
                                <c:forEach items="${itemList}" var="item" varStatus="itemStatus">
                                    {
                                        "a":"${count}",
                                        "titleTypeName":${pdfn:toJsonString(item.name)},
                                        "id":${pdfn:toJsonString(item.id)},
                                        "name": ${pdfn:toJsonString(title.typeName)},
                                        "score": ${pdfn:toJsonString(item.score)},
                                        <c:set value="${valueMap[item.id]}" var="detail"/>
                                        "examScore":${pdfn:toJsonString(detail.examScore)}
                                    }
                                    <c:set var="itemSubTotalScore" value="${itemSubTotalScore + item.score}"></c:set>
                                    <c:set var="subTotalScore" value="${subTotalScore + detail.examScore}"></c:set>
                                    <c:set var="count" value="${count+1}"></c:set>
                                    <c:if test="${not itemStatus.last }">
                                        ,
                                    </c:if>
                                </c:forEach>
                            ],
                            <c:set var="tatolScore" value="${tatolScore + itemSubTotalScore}"></c:set>
                            <c:set var="tatolExamScore" value="${tatolExamScore + subTotalScore}"></c:set>
                            "itemTatolScore":"${pdfn:toFormatString(itemSubTotalScore)}",
                            "subTatolExamScore":"${pdfn:toFormatString(subTotalScore)}"
                        }
                        <c:if test="${not status.last }">
                            ,
                        </c:if>
                    </c:forEach>
                ],
                "tatolScore":"${tatolScore}",
                "tatolExamScore":"${tatolExamScore}"
            },
        "GD":{
            "jspType": ${pdfn:toJsonString(jspType)},
            "values":[
                  <c:forEach items="${keySet}" var="key" varStatus="status">
                        {
                            "key": ${pdfn:toJsonString(key)},
                            "value": ${pdfn:toJsonString(valueMap[key])}
                        }
                        <c:if test="${(not status.last) }">
                            ,
                        </c:if>
                </c:forEach>
                ]
            }
    }
    </c:if>
}
