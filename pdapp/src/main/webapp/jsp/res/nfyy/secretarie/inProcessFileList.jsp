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
            <c:forEach items="${inProcessFileList}" var="l" varStatus="s">
                {
                    "ReadSecDocumentId": ${pdfn:toJsonString(l.ReadSecDocumentId)},
                    "Title": ${pdfn:toJsonString(l.Title)},
                    "HosSecName": ${pdfn:toJsonString(l.HosSecName)},
                    "RoleName": ${pdfn:toJsonString(l.RoleName)}
                }
                <c:if test='${not s.last}'>
                    ,
                </c:if>
            </c:forEach>
    ]
	</c:if>
}
