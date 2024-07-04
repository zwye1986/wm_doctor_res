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
            <c:forEach items="${deptList}" var="d" varStatus="s">
                {
                    "HosSecName": ${pdfn:toJsonString(d.HosSecName)},
                    "HosSecID": ${pdfn:toJsonString(d.HosSecID)}
                }
                <c:if test='${not s.last}'>
                    ,
                </c:if>
            </c:forEach>
    ]
	</c:if>
}
