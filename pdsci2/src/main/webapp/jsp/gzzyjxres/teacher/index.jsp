<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>${sysCfgMap['sys_title_name']}</title>
    <jsp:include page="/jsp/gzzyjxres/htmlhead-gzzyjxres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="login" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
    </jsp:include>
    <script>
        $(document).ready(function () {
            $(".menu_item a").click(function () {
                $(".select").removeClass("select");
                $(this).addClass("select");
            });
            setBodyHeight();
            $("#main").click();
            $("#main").find("span").trigger("click");
        });
        function setBodyHeight() {
            if (navigator.appName.indexOf("Microsoft Internet Explorer") > -1) {//处理ie浏览器placeholder和password的不兼容问题
                if (navigator.appVersion.indexOf("MSIE 7.0") > -1) {
                    $("#indexBody").css("height", window.innerHeight + "px");
                } else if (navigator.appVersion.indexOf("MSIE 8.0") > -1) {
                    $("#indexBody").css("height", document.documentElement.offsetHeight + "px");
                } else {
                    $("#indexBody").css("height", window.innerHeight + "px");
                }
            } else {
                $("#indexBody").css("height", window.innerHeight + "px");
            }
        }

        onresize = function () {
            setBodyHeight();
        }

        function changeTea() {
            var url = "<s:url value='/gzzyjxres/secretaries/changeTea'/>";
            jboxLoad("content", url, true);
        }
        /**
         * 学员评价
         */
        function evaluation(resp) {
            var roleId = resp;
            var url = "<s:url value='/gzzyjxres/evaluation/evaluationManage?kaoherenRoleId=Teacher&roleId='/>" + roleId;
            jboxLoad("content", url, true);
        }
    </script>
</head>
<body>
<div style="overflow:auto;" id="indexBody">
    <div class="bd_bg">
        <div class="yw">
            <jsp:include page="/jsp/gzzyjxres/head.jsp" flush="true"/>
            <div class="body">
                <div class="container">
                    <div class="content_side">
                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_management"></i>评价管理
                            </dt>
                            <dd class="menu_item" id="main"><a onclick="evaluation('Doctor');"><span>学员评价</span></a></dd>
                        </dl>
                    </div>
                    <div class="col_main" id="content">

                    </div>
                </div>
            </div>
        </div>
    </div>
    <c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
        <jsp:include page="/jsp/service.jsp" flush="true"></jsp:include>
    </c:if>
    <jsp:include page="/jsp/gzzyjxres/foot.jsp" flush="true"/>
</div>
</body>
</html>
