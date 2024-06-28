<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>江苏省住院医师规范化培训督导管理系统</title>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="register" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="font" value="true"/>
    </jsp:include>
    <link rel="stylesheet" type="text/css" href="<s:url value="/jsp/jsres/css/phone.css"/>"/>
    <script type="text/javascript">
        function goToLigin() {
            window.location.href = "<s:url value='/inx/supervisio'/>";
       }


    </script>
</head>
<body>
<div class="yw"  style="width: 100%; min-height: 100%;">
    <div class="head">
        <div class="head_inner">
            <h1 class="logo">
                <a href="<s:url value='/inx/supervisio'/>">江苏省住院医师规范化培训督导管理系统</a>
            </h1>
        </div>
    </div>
    <div class="content cont_box" style="text-align: center; max-width: 1240px;width: 60%;">
        <div class="notPass wjpsw" style=" position: relative;   padding: 0;width: 100%;height: 100%;">
            <div style="position:absolute;top: 0;left: 0;right: 0;bottom: 0;margin: auto;height: 300px">
                <img src="<s:url value='/jsp/jsres/images/successChange.png'/>" alt="">
                <h1 class="reg_title" style="margin: 0;letter-spacing: 10px;padding-top: 50px">修改成功</h1>
                <div style="">
                    <input type="button" value="登&nbsp;&nbsp;录" onclick="goToLigin()" id="registerButton"
                           class="btn_blue nextBtn" style="margin-top: 50px;width: 120px;"/>

                </div>
            </div>
            <%--            </div>--%>
        </div>
    </div>
    <div class="footer">技术支持：南京品德网络信息技术有限公司</div>
</div>
<c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
    <jsp:include page="/jsp/service.jsp" flush="true"></jsp:include>
</c:if>

</body>
</html>