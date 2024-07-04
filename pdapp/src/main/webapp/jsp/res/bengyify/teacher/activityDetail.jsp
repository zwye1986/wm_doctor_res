<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
	 <c:if test="${resultId eq '200' }">
    ,
    "data": 
        {
            "CaDisID":${pdfn:toJsonString(activityDetail.CaDisID)},
            "CaName":${pdfn:toJsonString(activityDetail.CaName)},
            "CaCount":${pdfn:toJsonString(activityDetail.CaCount)},
            "CheckCaCount":${pdfn:toJsonString(activityDetail.CheckCaCount)},
            "CaDisTime":${pdfn:toJsonString(activityDetail.CaDisTime)},
            "CaEndDisTime":${pdfn:toJsonString(activityDetail.CaEndDisTime)},
            "CaDisContent":${pdfn:toJsonString(activityDetail.CaDisContent)},
            "CaDisPlayClass":${pdfn:toJsonString(activityDetail.CaDisPlayClass)},
            "CaDisPeriod":${pdfn:toJsonString(activityDetail.CaDisPeriod)},
            "CaDisMainSpeaker":${pdfn:toJsonString(activityDetail.CaDisMainSpeaker)},
            "CaDisDes":${pdfn:toJsonString(activityDetail.CaDisDes)},
            "AttachName":${pdfn:toJsonString(activityDetail.AttachName)},
            "AttachPath":${pdfn:toJsonString(activityDetail.AttachPath)},
            "AttachFileName":${pdfn:toJsonString(activityDetail.AttachFileName)},
            "UserID":${pdfn:toJsonString(activityDetail.UserID)},
            "CaAddress":${pdfn:toJsonString(activityDetail.CaAddress)},
            "DicItem":${pdfn:toJsonString(activityDetail.DicItem)},
            "CaDisMainSpeakerType":${pdfn:toJsonString(activityDetail.CaDisMainSpeakerType)},
            "CaDisMainSpeakerTypeName":${pdfn:toJsonString(activityDetail.CaDisMainSpeakerTypeName)},
            "CheckStatus":${pdfn:toJsonString(activityDetail.CheckStatus)},
            "CheckStatusName":${pdfn:toJsonString(activityDetail.CheckStatusName)}
        }
	</c:if>
}
