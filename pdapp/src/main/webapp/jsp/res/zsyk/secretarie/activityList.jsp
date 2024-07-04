<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
	 <c:if test="${resultId eq '200' }">
    ,
    "pageIndex": ${param.pageIndex},
    "pageSize": ${param.pageSize},
    "dataCount":${dataCount},
    "datas":[
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
    ]
	</c:if>
}
