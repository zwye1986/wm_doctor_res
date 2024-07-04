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
            <c:forEach items="${leaveList}" var="l" varStatus="s">
                {
                    "TrueName": ${pdfn:toJsonString(l.TrueName)},
                    "DicItem": ${pdfn:toJsonString(l.DicItem)},
                    "CheckDays": ${pdfn:toJsonString(l.CheckDays)},
                    "LEAVEDAYS": ${pdfn:toJsonString(l.LEAVEDAYS)},
                    "LeaveStartTime": ${pdfn:toJsonString(l.LeaveStartTime)},
                    "LeaveEndTime": ${pdfn:toJsonString(l.LeaveEndTime)},
                    "LeaveCause": ${pdfn:toJsonString(l.LeaveCause)}
                }
                <c:if test='${not s.last}'>
                    ,
                </c:if>
            </c:forEach>
    ]
	</c:if>
}
