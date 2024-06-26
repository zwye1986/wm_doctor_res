<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <title>成都中医药大学继续教育</title>
    <link href="<s:url value='/jsp/inx/cdedudoor/css/style.css'/>" rel="stylesheet" type="text/css">
</head>

<body>
<div class="centerbox">
    <%@include file="header.jsp" %>

    <div class="soncon">
        <div class="sonright">
            <div class="sontitle">
                <span class="fl sonnewnap">您现在的位置：<a href="<s:url value='/inx/cdedudoor'/>">首页</a>><a href="<s:url value='/inx/cdedudoor/queryByColumnId?columnId=LM03'/>">${info.columnName}</a></span>
            </div>
        </div>
        <div class="newcon">
            <span class="newcontitle">${info.infoTitle}</span>
            <span class="newcontime">${info.infoTime}</span>
            <p>${info.infoContent}</p>
        </div>
        <div class="fright">
            <span class="fl">
                <c:choose>
                    <c:when test="${not empty beforeInfo}">上一条：<a href="<s:url value='/inx/cdedudoor/getByInfoFlow?infoFlow=${beforeInfo.infoFlow }'/>">${pdfn:cutString(beforeInfo.infoTitle,20,true,6)}</a></c:when>
                    <c:otherwise>已置顶</c:otherwise>
                </c:choose>
            </span>
            <span class="fr">
                <c:choose>
                    <c:when test="${not empty afterInfo}">下一条：<a href="<s:url value='/inx/cdedudoor/getByInfoFlow?infoFlow=${afterInfo.infoFlow }'/>">${pdfn:cutString(afterInfo.infoTitle,20,true,6)}</a></c:when>
                <c:otherwise>已置底</c:otherwise>
                </c:choose>
            </span>
        </div>
    </div>

    <%@include file="footer.jsp" %>
</div>
</body>
</html>
