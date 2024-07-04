<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
        "pageIndex":1,
        "pageSize":10,
        "dataCount":${dataCount},
        "noteTypeId":${pdfn:toJsonString(param.noteTypeId)},
        "datas":[
            <c:forEach items="${discipleFiles}" var="discipleFile" varStatus="s">
            {
                "fileFlow":${pdfn:toJsonString(discipleFile.fileFlow)},
                "fileName":${pdfn:toJsonString(discipleFile.fileName)},
                <c:set value="${uploadPath}/${discipleFile.filePath}" var="filePath"></c:set>
                "filePath":${pdfn:toJsonString(filePath)},
                "action":{
                <c:if test="${info.auditStatusId eq 'Apply' or empty info.auditStatusId}">
                    "del":"删除"
                </c:if>
                }
            }
            <c:if test="${!s.last}">
                ,
            </c:if>
        </c:forEach>
    ]
    </c:if>
}