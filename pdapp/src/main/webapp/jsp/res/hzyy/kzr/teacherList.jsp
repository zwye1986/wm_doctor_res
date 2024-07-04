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
            <c:forEach items="${teacherList}" var="d" varStatus="s">
                {
                    "teaName": ${pdfn:toJsonString(d.TrueName)},
                    "HosSecName": ${pdfn:toJsonString(d.HosSecName)},
                    "teaFlow": ${pdfn:toJsonString(d.UserID)}
                }
                <c:if test='${not s.last}'>
                    ,
                </c:if>
            </c:forEach>
    ]
	</c:if>
}
