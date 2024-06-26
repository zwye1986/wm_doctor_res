<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <title>中山大学孙逸仙纪念医院住院医师规范化培训管理平台</title>
    <link rel="shortcut icon" href="<s:url value='/jsp/inx/zsey/images/favicon.ico'/>" />
    <link href="<s:url value='/jsp/inx/zseyGate/css/style.css'/>" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<s:url value='/jsp\inx\zseyGate\funcMap.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
</head>

<body>
<div class="centerbox">
    <jsp:include page="header.jsp">
        <jsp:param value="${ param.roleFlow }" name="roleFlow"/>
        <jsp:param value="${ param.modelId }" name="modelId"/>
    </jsp:include>

    <c:if test="${param.roleFlow eq 'All'}">
        <c:set var="url" value="/inx/zseyGate"></c:set>
    </c:if>
    <c:if test="${param.roleFlow ne 'All'}">
        <c:set var="url" value="/inx/zseyGate/queryByRoleFlow?roleFlow=${roleFlow}'"></c:set>
    </c:if>
    <div class="soncon">
        <div class="sontitle">
            <span class="fl sonnewtitle">${columnName}</span>
            <span class="fr sonnewnap">您现在的位置：<a href="<s:url value='${url}'/>">${tabName}</a>><a href="<s:url value='/inx/zseyGate/queryByRoleFlowAndColumnId?roleFlow=${param.roleFlow}&columnId=${param.columnId}&currentPage=1'/>">${columnName}</a></span>
        </div>
        <div class="newcon">
            <span class="newcontitle">${info.infoTitle}</span>
            <span class="newcontime">${info.infoTime}</span>
            <p>${info.infoContent}</p>
        </div>
        <div class="fright">
            <span class="fl">
                <c:choose>
                    <c:when test="${not empty beforeInfo}">上一条：<a href="<s:url value='/inx/zseyGate/getByInfoFlow?infoFlow=${beforeInfo.infoFlow }&roleFlow=${empty param.roleFlow?\'All\': param.roleFlow}&modelId=${empty param.modelId?\'All\': param.modelId}&columnId=${param.columnId}'/>">${pdfn:cutString(beforeInfo.infoTitle,20,true,6)}</a></c:when>
                    <c:otherwise>已置顶</c:otherwise>
                </c:choose>
            </span>
            <span class="fr">
                <c:choose>
                    <c:when test="${not empty afterInfo}">下一条：<a href="<s:url value='/inx/zseyGate/getByInfoFlow?infoFlow=${afterInfo.infoFlow }&roleFlow=${empty param.roleFlow?\'All\': param.roleFlow}&modelId=${empty param.modelId?\'All\': param.modelId}&columnId=${param.columnId}'/>">${pdfn:cutString(afterInfo.infoTitle,20,true,6)}</a></c:when>
                <c:otherwise>已置底</c:otherwise>
                </c:choose>
            </span>
        </div>
    </div>

    <%@include file="footer.jsp" %>
</div>
</body>
</html>
