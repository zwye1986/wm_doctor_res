<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>江苏省住院医师规范化培训-督导管理系统</title>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="consult" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_cxselect" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <script type="text/javascript"
            src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script>
        $(document).ready(function () {
            $(".menu_item a").click(function () {
                $(".select").removeClass("select");
                $(this).addClass("select");
            });
            setBodyHeight();
            $('.menu_item a:first').click();
            if ("${speFlag}" != "0") {
                $("#speFlag").show();
            }
            if ("${backFlag}" == "1") {
                $("#backFlag").show();
            }
            if ("${baseFlag}" != "0") {
                $("#baseFlag").show();
            }
            getSpandAndshousuo();
        });

        //菜单收缩展开yuh
        function getSpandAndshousuo() {
            var $all_dl = $("dl");
            for (var i = 0; i < $all_dl.length; i++) {
                $all_dl[i].id = "dl_" + i;
                $("#dl_" + i + " dt").css("cursor", "pointer");//图标手形

                $("#dl_" + i + " dt").on("click", function () {
                    var parentId = $(this)[0].parentElement.id;
                    var $all_dd = $("#" + parentId + " dd");
                    for (var j = 0; j < $all_dd.length; j++) {
                        if ($all_dd[j].hidden) {
                            $all_dd[j].hidden = false;
                        } else {
                            $all_dd[j].hidden = true;
                        }
                    }
                });
            }
        }

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

        function signMain(){
            jboxLoad("content","<s:url value='/jsres/supervisio/signMain'/>?userFlow=${sessionScope.currUser.userFlow}&roleFlag=expert",true);
        }

        function subjectMain(){
            jboxLoad("content","<s:url value='/jsres/supervisio/subjectMain'/>?roleFlag=expert",true);
        }

        function planScoreMain(){
            jboxLoad("content","<s:url value='/jsres/supervisio/planScoreMain'/>?roleFlag=expert",true);
        }

    </script>
    <style>
        body{overflow:hidden;}
    </style>
</head>

<body>
<div style="overflow:auto;" id="indexBody">
    <div class="bd_bg">
        <div class="yw">
            <div class="head_inner">
                <h1 class="logo">江苏省住院医师规范化培训-督导管理系统</h1>
                <div class="account" style="margin-top:35px; ">
                    <h2>
                        <a style="padding-right:13px; border-right:1px solid #459fd0;" href="<s:url value='${param.indexUrl}'/>">首页</a>&#12288;
                        ${sessionScope.UserLevel} &#12288;  ${sessionScope.currUser.userName} &#12288;<a href="<s:url value='/inx/supervisio/logout'/>">退出</a>
                    </h2>
                </div>
            </div>

            <div class="body">
                <div class="container">
                    <div class="content_side">
                        <dl class="menu menu_first">
                            <dt class="menu_title">
                                <i class="icon_menu menu_management"></i>督导管理
                            </dt>
                            <dd class="menu_item"><a onclick="planScoreMain();">专业评分</a></dd>
                            <dd class="menu_item"><a onclick="signMain();">签名设置</a></dd>
                        </dl>
                    </div>
                    <div class="col_main" id="content">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
        <jsp:include page="/jsp/service.jsp"></jsp:include>
    </c:if>
    <div class="foot">
        <div class="foot_inner">
            主管单位：${sysCfgMap['the_competent_unit']} | 技术支持：南京品德网络信息技术有限公司
        </div>
    </div>
</div>
</body>
</html>
