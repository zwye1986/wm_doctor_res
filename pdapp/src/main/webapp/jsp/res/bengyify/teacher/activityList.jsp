<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
	 <c:if test="${resultId eq '200' }">
    ,
    "pageIndex": ${param.pageIndex},
    "pageSize": ${param.pageSize},
    "dataCount":${dataCount},
    "datas": [
        <c:forEach items="${activityList}" var="activity" varStatus="status">
        {
            "CaDisID":"${activity.CaDisID}",
            "CaName":"${activity.CaName}",
            "CaCount":"${activity.CaCount}",
            "CheckCaCount":"${activity.CheckCaCount}",
            "CaDisTime":"${activity.CaDisTime}",
            "CaEndDisTime":"${activity.CaEndDisTime}",
            "CaDisContent":"${activity.CaDisContent}",
            "CaDisPlayClass":"${activity.CaDisPlayClass}",
            "CaDisPeriod":"${activity.CaDisPeriod}",
            "CaDisMainSpeaker":"${activity.CaDisMainSpeaker}",
            "CaDisDes":"${activity.CaDisDes}",
            "UserID":"${activity.UserID}",
            "CaAddress":"${activity.CaAddress}",
            "DicItem":"${activity.DicItem}",
            "CaDisMainSpeakerType":"${activity.CaDisMainSpeakerType}",
            "CaDisMainSpeakerTypeName":"${activity.CaDisMainSpeakerTypeName}",
            "CheckStatus":"${activity.CheckStatus}",
            "CheckStatusName":"${activity.CheckStatusName}"
        }
        <c:if test='${not status.last}'>
    ,
        </c:if>
        </c:forEach>
    ]
	</c:if>
}
