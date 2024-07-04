<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "titleDate": {
        "deptName":"轮转科室"
        <c:if test="${not empty titleDate}">,</c:if>
        <c:forEach items="${titleDate}" var="title"  varStatus="status">
            <c:set var="year" value="${title.substring(0,4)}"/>
            <c:set var="week" value="${title.substring(5)}"/>
            <c:set var="title" value="${year}年${week}周"/>
            <c:set var="key" value="title_${status.count}"/>
            "${key}":"${title}"
            <c:if test="${!status.last}">
                ,
            </c:if>
        </c:forEach>
        },
	"data":[
        <c:forEach items="${schDeptList}" var="schDept">
            <c:if test="${empty deptFlow || deptFlow ==schDept.schDeptFlow}">
                {
                "deptName":"${schDept.schDeptName}"
                <c:if test="${not empty titleDate}">,</c:if>
                <c:forEach items="${titleDate}" var="title" varStatus="status1">
                    <c:set var="key1" value="title_${status1.count}"/>
                    <c:set var="key" value="${schDept.schDeptFlow}${title}"/>
                    "${key1}":{ "count":"${resultListMap[key].size()+0}","names":"<c:if test="${!empty resultListMap[key]}"><c:forEach items="${resultListMap[key]}" var="result" varStatus="status2">${result.doctorName}<c:if test="${!status2.last}">,</c:if></c:forEach></c:if>"}
                    <c:if test="${!status1.last}">
                        ,
                    </c:if>
                </c:forEach>
                }
                <c:if test="${!status.last}">
                    ,
                </c:if>
            </c:if>
        </c:forEach>

        ]
    </c:if>
}