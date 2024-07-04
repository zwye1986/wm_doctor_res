<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "dataCount": ${dataCount},
    "examEndTime": ${pdfn:toJsonString(maxTimeMap.examEndTime)},
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
    "head":[
            <c:forEach var="s" varStatus="s1" begin="1" end="${maxStationSize}" step="1">
                {
                    "id":${pdfn:toJsonString(s)},
                    "name": ${pdfn:toJsonString(s)}
                }
                <c:if test="${not s1.last }">
                    ,
                </c:if>
            </c:forEach>
        ],
    "datas":[
        <c:forEach items="${doctorSignInList}" var="data" varStatus="s">
            <c:set var="headImg" value="${uploadBaseUrl}/${empty data.userHeadImg?'default.png':data.userHeadImg}"/>
            {
                "order": ${pdfn:toJsonString((param.pageIndex-1)*param.pageSize+s.count)},
                "headImg": ${pdfn:toJsonString(headImg)},
                "doctorFlow": ${pdfn:toJsonString(data.doctorFlow)},
                "doctorName": ${pdfn:toJsonString(data.doctorName)},
                "speName": ${pdfn:toJsonString(data.speName)},
                "clinicalFlow": ${pdfn:toJsonString(data.clinicalFlow)},
                "stations":[
                    <c:set var="allScore" value="0"></c:set>
                    <c:forEach var="s" varStatus="s1" begin="1" end="${maxStationSize}" step="1">
                        <c:set var="stationKey" value="${data.clinicalFlow}${s}"></c:set>
                        <c:set var="station" value="${assessStationMap[stationKey]}"></c:set>
                        <c:if test="${empty station}">
                            <c:set var="statusId" value="notStation"></c:set>
                            <c:set var="statusName" value="无此站"></c:set>
                            <c:set var="score" value="--"></c:set>
                            <c:set var="isDiffer" value="false"></c:set>
                            <c:set var="stationFlow" value=""></c:set>
                            <c:set var="stationFlow" value="false"></c:set>
                        </c:if>
                        <c:if test="${not empty station}">
                            <c:set var="statusId" value="haveStation"></c:set>
                            <c:set var="statusName" value="有此站"></c:set>
                            <c:set var="stationFlow" value="${station.stationFlow}"></c:set>
                            <c:set var="scoreKey" value="${data.doctorFlow}${data.clinicalFlow}${station.stationFlow}"></c:set>
                            <c:set var="examScore" value="${examScoreMap[scoreKey]}"></c:set>
                            <c:set var="isDiffer" value="${empty isDifferMap[scoreKey]?'false':isDifferMap[scoreKey]}"></c:set>
                            <c:set var="score" value="${examScore.examSaveScore}"></c:set>
                            <c:set var="allScore" value="${allScore+examScore.examSaveScore}"></c:set>
                        </c:if>
                        {
                            "id":${pdfn:toJsonString(s)},
                            "score": ${pdfn:toJsonString(score)},
                            "statusId": ${pdfn:toJsonString(statusId)},
                            "statusName": ${pdfn:toJsonString(statusName)},
                            "stationFlow": ${pdfn:toJsonString(stationFlow)},
                            "isDiffer": ${pdfn:toJsonString(isDiffer)}
                        }
                        <c:if test="${not s1.last }">
                            ,
                        </c:if>
                    </c:forEach>
                ],
                "allScore": ${pdfn:toFormatString(allScore)},
                "isSavePass": ${pdfn:toJsonString(data.isSavePass)}
            }
            <c:if test="${not s.last }">
                ,
            </c:if>
        </c:forEach>
        ]
    </c:if>
}
