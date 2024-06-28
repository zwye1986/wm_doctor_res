<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>江苏督导管理系统</title>
    <jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
    </jsp:include>
    <script>
        $(document).ready(function(){
            $(".menu_item a").click(function(){
                $(".select").removeClass("select");
                $(this).addClass("select");
            });
            setBodyHeight();
            $('.menu_item a:first').click();
        });
        function setBodyHeight(){
            if (navigator.appName.indexOf("Microsoft Internet Explorer")>-1) {//处理ie浏览器placeholder和password的不兼容问题
                if(navigator.appVersion.indexOf("MSIE 7.0")>-1){
                    $("#indexBody").css("height",window.innerHeight+"px");
                }else if(navigator.appVersion.indexOf("MSIE 8.0")>-1){
                    $("#indexBody").css("height",document.documentElement.offsetHeight+"px");
                }else{
                    $("#indexBody").css("height",window.innerHeight+"px");
                }
            } else {
                $("#indexBody").css("height",window.innerHeight+"px");
            }
        }
        onresize=function(){
            setBodyHeight();
        };

        function planScoreMain(){
            jboxLoad("content","<s:url value='/jsres/supervisio/planScoreMain'/>?roleFlag=${role}",true);
        }
        function signMain(){
            jboxLoad("content","<s:url value='/jsres/supervisio/signMain'/>?userFlow=${sessionScope.currUser.userFlow}&roleFlag=${role}",true);
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
            <jsp:include page="/jsp/hbres/head.jsp" flush="true">
                <jsp:param value="/hbres/supervisio/index/baseExpert" name="indexUrl"/>
                <jsp:param value="ddgl" name="logName"/>
            </jsp:include>
            <div class="body">
                <div class="container">
                    <div class="content_side">
                        <dl class="menu menu_first">
                            <dt class="menu_title">
                                <i class="icon_menu menu_management"></i>督导管理
                            </dt>
                            <dd class="menu_item"><a onclick="planScoreMain();">专业评分</a></dd>
<%--                            <dd class="menu_item"><a onclick="signMain();">签名设置</a></dd>--%>

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
    <jsp:include page="/jsp/hbres/foot.jsp"  flush="true"/>
</div>
</body>
</html>
