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
    ]
	</c:if>
}
