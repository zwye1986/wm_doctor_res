<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
	 <c:if test="${resultId eq '200' }">
    ,
    "inProcess":{
        "AllNum": ${pdfn:toJsonString(empty inProcessCount['AllNum']?0:inProcessCount['AllNum'])},
        "InNum": ${pdfn:toJsonString(empty inProcessCount['InNum']?0:inProcessCount['InNum'])},
        "OutNum": ${pdfn:toJsonString(empty inProcessCount['OutNum']?0:inProcessCount['OutNum'])}
    } ,
    "pageIndex": ${param.pageIndex},
    "pageSize": ${param.pageSize},
    "dataCount":${dataCount},
    "datas":[
        <c:if test="${param.indexType eq 'inProcess'}">
            <c:forEach items="${studentList}" var="stu" varStatus="s">
                {
                    "UserID": ${pdfn:toJsonString(stu.UserID)},
                    "StartYear": ${pdfn:toJsonString(stu.StartYear)},
                    "SpName": ${pdfn:toJsonString(stu.SpName)},
                    "StartTime": ${pdfn:toJsonString(stu.StartTime)},
                    "EndTime": ${pdfn:toJsonString(stu.EndTime)},
                    "RStartTime": ${pdfn:toJsonString(stu.RStartTime)},
                    "REndTime": ${pdfn:toJsonString(stu.REndTime)},
                    "CheckStatus": ${pdfn:toJsonString(stu.CheckStatus)},
                    "TrueName": ${pdfn:toJsonString(stu.TrueName)},
                    "HosSecName": ${pdfn:toJsonString(stu.HosSecName)},
                    "UserTecID": ${pdfn:toJsonString(stu.UserTecID)},
                    "TecTrueName": ${pdfn:toJsonString(stu.TecTrueName)},
                    "SectionManagerID": ${pdfn:toJsonString(stu.SectionManagerID)},
                    "SectionManagerName": ${pdfn:toJsonString(stu.SectionManagerName)},
                    "UCSID": ${pdfn:toJsonString(stu.UCSID)},
                    "CySecID": ${pdfn:toJsonString(stu.CySecID)},
                    "SFZC": ${pdfn:toJsonString(stu.SFZC)},
                    "UserType": ${pdfn:toJsonString(stu.UserType)},
                    "UserTypeName": ${pdfn:toJsonString(stu.UserTypeName)}
                }
                <c:if test='${not s.last}'>
                    ,
                </c:if>
            </c:forEach>
        </c:if>
        <c:if test="${param.indexType eq 'outProcess'}">
            <c:forEach items="${studentList}" var="stu" varStatus="s">
                {
                    "UserID": ${pdfn:toJsonString(stu.UserID)},
                    "StartYear": ${pdfn:toJsonString(stu.StartYear)},
                    "SpName": ${pdfn:toJsonString(stu.SpName)},
                    "TrueName": ${pdfn:toJsonString(stu.TrueName)},
                    "HosSecName": ${pdfn:toJsonString(stu.HosSecName)},
                    "UCSID": ${pdfn:toJsonString(stu.UCSID)},
                    "UserType": ${pdfn:toJsonString(stu.UserType)},
                    "UserTypeName": ${pdfn:toJsonString(stu.UserTypeName)},
                    "TecTrueName": ${pdfn:toJsonString(stu.TecTrueName)},
                    "StartTime": ${pdfn:toJsonString(stu.StartTime)},
                    "EndTime": ${pdfn:toJsonString(stu.EndTime)},
                    "RStartTime": ${pdfn:toJsonString(stu.RStartTime)},
                    "REndTime": ${pdfn:toJsonString(stu.REndTime)},
                    "UserTecID": ${pdfn:toJsonString(stu.UserTecID)},
                    "SectionManagerID": ${pdfn:toJsonString(stu.SectionManagerID)},
                    "SectionManagerName": ${pdfn:toJsonString(stu.SectionManagerName)},
                    "UserNowState": ${pdfn:toJsonString(stu.UserNowState)},
                    "CySecID": ${pdfn:toJsonString(stu.CySecID)}
                }
                <c:if test='${not s.last}'>
                    ,
                </c:if>
            </c:forEach>
        </c:if>
        <c:if test="${param.indexType eq 'teachPlan'}">
            <c:forEach items="${studentList}" var="stu" varStatus="s">
                {
                    "UmpireScore": ${pdfn:toJsonString(stu.UmpireScore)},
                    "CaDisMainSpeakerType": ${pdfn:toJsonString(stu.CaDisMainSpeakerType)},
                    "CaDisMainSpeaker": ${pdfn:toJsonString(stu.CaDisMainSpeaker)},
                    "DicItem": ${pdfn:toJsonString(stu.DicItem)},
                    "CaName": ${pdfn:toJsonString(stu.CaName)},
                    "CaDisTime": ${pdfn:toJsonString(stu.CaDisTime)},
                    "CaEndDisTime": ${pdfn:toJsonString(stu.CaEndDisTime)},
                    "CheckStatus": ${pdfn:toJsonString(stu.CheckStatus)},
                    "CheckStatusName": ${pdfn:toJsonString(stu.CheckStatusName)},
                    "CaDisID": ${pdfn:toJsonString(stu.CaDisID)},
                    "CySecID": ${pdfn:toJsonString(stu.CySecID)},
                    "CaDisPlayClass": ${pdfn:toJsonString(stu.CaDisPlayClass)},
                    "CaDisPeriod": ${pdfn:toJsonString(stu.CaDisPeriod)},
                    "CaDisDes": ${pdfn:toJsonString(stu.CaDisDes)},
                    "UserID": ${pdfn:toJsonString(stu.UserID)}
                }
                <c:if test='${not s.last}'>
                    ,
                </c:if>
            </c:forEach>
        </c:if>
    ]
	</c:if>
}
