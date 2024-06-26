<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
</head>
<body>
    <c:if test="${param.wsId eq 'osca'}">
        <jsp:forward page="/jsp/inx/osce/index.jsp"></jsp:forward>
        <c:set var="flag" value="true"/>
    </c:if>
    <c:if test="${param.wsId eq 'lcjn' || param.wsId eq 'zseylcjn'}">
        <jsp:forward page="/inx/${param.wsId}"></jsp:forward>
        <c:set var="flag" value="true"/>
    </c:if>
    <%--临床技能考核及训练中心作为其他系统的中转站，”注销“不能走后台产品配置，故特别设置注销跳转页面--%>
    <c:if test="${!flag}">
        <c:choose>
            <c:when test="${empty applicationScope.sysCfgMap['sys_logout_url'] }">
                <c:if test="${applicationScope.sysCfgMap['sys_index_url'] eq '/inx/zsey'}">
                    <jsp:forward page="/inx/zsey/loginpage"></jsp:forward>
                </c:if>
                <c:if test="${!(applicationScope.sysCfgMap['sys_index_url'] eq '/inx/zsey')}">
                    <jsp:forward page="/index.jsp"></jsp:forward>
                </c:if>
            </c:when>
            <c:otherwise>
                <script>
                    window.location.href='${applicationScope.sysCfgMap["sys_logout_url"]}';
                </script>
            </c:otherwise>
        </c:choose>
    </c:if>
</body>
</html>