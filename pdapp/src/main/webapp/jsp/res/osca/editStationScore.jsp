<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
        ,
    "fromType":"${fromType}",
    "haveFrom":"${haveFrom}",
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
        "userFlow":${pdfn:toJsonString(param.userFlow)},
        "doctorFlow":${pdfn:toJsonString(param.doctorFlow)},
        "fromFlow":${pdfn:toJsonString(param.fromFlow)},
        "scoreFlow":${pdfn:toJsonString(param.scoreFlow)}
        },
    "FGD":{
         "element":
            [
                <c:forEach items="${keySet}" var="key" varStatus="status">
                    <c:set var="title" value="${dataMap[key]}"></c:set>
                    <c:set var="itemList" value="${title['itemList']}"/>
                    <c:forEach items="${itemList}" var="item" varStatus="itemStatus">
                        {
                            "titleId": ${pdfn:toJsonString(title.id)},
                            "titleName": ${pdfn:toJsonString(title.name)},
                            "titleTypeName":${pdfn:toJsonString(title.typeName)},
                            "id":${pdfn:toJsonString(item.id)},
                            "name": ${pdfn:toJsonString(item.name)},
                            "score": ${pdfn:toJsonString(item.score)}
                        }
                        <c:if test="${(not itemStatus.last) and (not status.last) }">
                            ,
                        </c:if>
                    </c:forEach>
                </c:forEach>
            ],
         "values":[
                <c:forEach items="${keySet}" var="key" varStatus="status">
                    <c:set var="title" value="${dataMap[key]}"></c:set>
                    <c:set var="itemList" value="${title['itemList']}"/>
                    <c:forEach items="${itemList}" var="item" varStatus="itemStatus">
                        {
                            "titleId": ${pdfn:toJsonString(title.id)},
                            "titleName": ${pdfn:toJsonString(title.name)},
                            "titleTypeName":${pdfn:toJsonString(title.typeName)},
                            "id":${pdfn:toJsonString(item.id)},
                            "name": ${pdfn:toJsonString(item.name)},
                            "score": ${pdfn:toJsonString(item.score)},
                            <c:set value="${valueMap[item.id]}" var="detail"/>
                            "examScore":${pdfn:toJsonString(detail.examScore)}
                        }
                        <c:if test="${(not itemStatus.last) and (not status.last) }">
                            ,
                        </c:if>
                    </c:forEach>
                </c:forEach>
            ]
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
    </c:if>
}
