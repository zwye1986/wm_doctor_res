<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "times":[
        <c:forEach items="${times}" var="bean" varStatus="s">
        {
            "yearMonth":"${bean}",
            "monthFirstDay":"${pdfn:getFirstDayOfMonth(bean)}",
            "monthLastDay":"${pdfn:getLastDayOfMonth(bean)}"
        }
        <c:if test="${not s.last }">
         ,
        </c:if>
        </c:forEach>
    ]
    ,
    "stuImgList":[
        <c:forEach items="${stuImgList}" var="bean" varStatus="s">
        {
            <c:set var="fileUrl" value="${baseUrl}/${bean.fileUrl}"></c:set>
            "fileFlow":${pdfn:toJsonString(bean.fileFlow)},
            "toturFlow":${pdfn:toJsonString(bean.toturFlow)},
            "fileUrl":${pdfn:toJsonString(fileUrl)},
            "fileSuffix":${pdfn:toJsonString(pdfn:getFileSuffix(fileUrl))},
            "fileName":${pdfn:toJsonString(bean.fileName)},
            "dateTime":${pdfn:toJsonString(bean.dateTime)}
        }
        <c:if test="${not s.last }">
         ,
        </c:if>
        </c:forEach>
    ]
    ,
    "stuSchImgList":[
        <c:forEach items="${stuSchImgList}" var="bean" varStatus="s">
        {
            <c:set var="fileUrl" value="${baseUrl}/${bean.fileUrl}"></c:set>
            "fileFlow":${pdfn:toJsonString(bean.fileFlow)},
            "toturFlow":${pdfn:toJsonString(bean.toturFlow)},
            "fileUrl":${pdfn:toJsonString(fileUrl)},
            "fileSuffix":${pdfn:toJsonString(pdfn:getFileSuffix(fileUrl))},
            "fileName":${pdfn:toJsonString(bean.fileName)},
            "dateTime":${pdfn:toJsonString(bean.dateTime)}
        }
        <c:if test="${not s.last }">
         ,
        </c:if>
        </c:forEach>
    ]
    ,
    "allYearImgList":[
        <c:forEach items="${allYearImgList}" var="bean" varStatus="s">
        {
            <c:set var="fileUrl" value="${baseUrl}/${bean.fileUrl}"></c:set>
            "fileFlow":${pdfn:toJsonString(bean.fileFlow)},
            "toturFlow":${pdfn:toJsonString(bean.toturFlow)},
            "fileUrl":${pdfn:toJsonString(fileUrl)},
            "fileSuffix":${pdfn:toJsonString(pdfn:getFileSuffix(fileUrl))},
            "fileName":${pdfn:toJsonString(bean.fileName)},
            "dateTime":${pdfn:toJsonString(bean.dateTime)}
        }
        <c:if test="${not s.last }">
         ,
        </c:if>
        </c:forEach>
    ]
    ,
    "preYearImgList":[
        <c:forEach items="${preYearImgList}" var="bean" varStatus="s">
        {
            <c:set var="fileUrl" value="${baseUrl}/${bean.fileUrl}"></c:set>
            "fileFlow":${pdfn:toJsonString(bean.fileFlow)},
            "toturFlow":${pdfn:toJsonString(bean.toturFlow)},
            "fileUrl":${pdfn:toJsonString(fileUrl)},
            "fileSuffix":${pdfn:toJsonString(pdfn:getFileSuffix(fileUrl))},
            "fileName":${pdfn:toJsonString(bean.fileName)},
            "dateTime":${pdfn:toJsonString(bean.dateTime)}
        }
        <c:if test="${not s.last }">
         ,
        </c:if>
        </c:forEach>
    ]
    ,
    "afterYearImgList":[
        <c:forEach items="${afterYearImgList}" var="bean" varStatus="s">
        {
            <c:set var="fileUrl" value="${baseUrl}/${bean.fileUrl}"></c:set>
            "fileFlow":${pdfn:toJsonString(bean.fileFlow)},
            "toturFlow":${pdfn:toJsonString(bean.toturFlow)},
            "fileUrl":${pdfn:toJsonString(fileUrl)},
            "fileSuffix":${pdfn:toJsonString(pdfn:getFileSuffix(fileUrl))},
            "fileName":${pdfn:toJsonString(bean.fileName)},
            "dateTime":${pdfn:toJsonString(bean.dateTime)}
        }
        <c:if test="${not s.last }">
         ,
        </c:if>
        </c:forEach>
    ]
    </c:if>
}
