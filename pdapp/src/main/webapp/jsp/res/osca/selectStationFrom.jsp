<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
        ,

    "action":{
        <c:if test="${score.statusId ne 'Submit'}">
            "save":"保存"
        </c:if>
    },
    "fromType":"${fromType}",
    "score":{
         "scoreFlow":${pdfn:toJsonString(score.scoreFlow)},
         "doctorFlow":${pdfn:toJsonString(score.doctorFlow)},
         "doctorName":${pdfn:toJsonString(score.doctorName)},
         "recordYear":${pdfn:toJsonString(score.recordYear)},
         "roomRecordFlow":${pdfn:toJsonString(score.roomRecordFlow)},
         "roomFlow":${pdfn:toJsonString(score.roomFlow)},
         "roomName":${pdfn:toJsonString(score.roomName)},
         "clinicalFlow":${pdfn:toJsonString(score.clinicalFlow)},
         "clinicalName":${pdfn:toJsonString(score.clinicalName)},
         "stationFlow":${pdfn:toJsonString(score.stationFlow)},
         "stationName":${pdfn:toJsonString(score.stationName)},
         "isHaveFrom":${pdfn:toJsonString(score.isHaveFrom)},
         "fromFlow":${pdfn:toJsonString(score.fromFlow)},
         "fromName":${pdfn:toJsonString(score.fromName)},
         "fromTypeId":${pdfn:toJsonString(score.fromTypeId)},
         "fromUrl":${pdfn:toJsonString(score.fromUrl)},
         "examScore":${pdfn:toJsonString(score.examScore)},
         "statusId":${pdfn:toJsonString(score.statusId)},
         "statusName":${pdfn:toJsonString(score.statusName)},
         "partnerFlow":${pdfn:toJsonString(score.partnerFlow)},
         "partnerName":${pdfn:toJsonString(score.partnerName)},
         "siginImage":${pdfn:toJsonString(siginImage)}
        },
    "params":{
        "userFlow":${pdfn:toJsonString(paramMap.userFlow)},
        "doctorFlow":${pdfn:toJsonString(paramMap.doctorFlow)},
        "clinicalFlow":${pdfn:toJsonString(paramMap.clinicalFlow)},
        "stationFlow":${pdfn:toJsonString(paramMap.stationFlow)},
        "roomRecordFlow":${pdfn:toJsonString(paramMap.roomRecordFlow)},
        "fromFlow":${pdfn:toJsonString(paramMap.fromFlow)},
        "isRequired":${pdfn:toJsonString(paramMap.isRequired)},
        "scoreFlow":${pdfn:toJsonString(paramMap.scoreFlow)}
        },
    "oneFrom":{
        "fromType":"${fromType}",
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
                            "itemTatolScore":"${itemSubTotalScore}",
                            "subTatolExamScore":"${subTotalScore}"
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
