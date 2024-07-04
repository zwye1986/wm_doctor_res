<%@include file="/jsp/common/doctype.jsp" %>
<%@ page import="com.pinde.sci.util.jsres.JsresUtil" %>
<html>
<head>
    <title>江苏省住院医师规范化培训-督导管理系统</title>
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

        function signMain(){
            jboxLoad("content","<s:url value='/jsres/supervisio/signMain'/>?userFlow=${sessionScope.currUser.userFlow}&roleFlag=${role}",true);
        }

        function subjectMain(localSubject){
            jboxLoad("content","<s:url value='/jsres/supervisio/subjectMain'/>?roleFlag=${role}"+"&localSubject="+localSubject,true);
        }

        function planScoreMain(){
            jboxLoad("content","<s:url value='/jsres/supervisio/planScoreMain'/>?roleFlag=${role}",true);
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
            <div class="head">
                <div class="head_inner">
                    <h1 class="logo">
                        <a href="<s:url value='${param.indexUrl}'/>">江苏省住院医师规范化培训管理平台—督导管理</a>
                    </h1>
                    <h1 class="logo">
                        <c:if test="${sessionScope.userLevelId eq 'management'}">
                            <a href="<s:url value='/jsres/manage/management'/>"><%=JsresUtil.getTitle(request, response, application)%>   </a>
                        </c:if>
                        <c:if test="${sessionScope.userLevelId eq 'expertLeader'}">
                            <a href="<s:url value='/jsres/manage/expertLeader'/>"><%=JsresUtil.getTitle(request, response, application)%>   </a>
                        </c:if>
                    </h1>
                    <div class="account">
                        <h2 class="head_right">${user.orgName }&emsp;${user.userName}</h2>
                        <div class="head_right">
                            <c:if test="${user.userLevelId eq 'management'}">
                                <a href="<s:url value='/jsres/manage/management'/>">首页</a>&#12288;
                            </c:if>
                            <c:if test="${user.userLevelId eq 'expertLeader'}">
                                <a href="<s:url value='/jsres/manage/expertLeader'/>">首页</a>&#12288;
                            </c:if>
                            <c:if test="${f}">
                                <a href="#" onclick="showSelect();">系统切换</a>&#12288;
                            </c:if>
                            <a href="<s:url value='/inx/jsres/logout'/>">退出</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="body">
                <div class="container">
                    <div class="content_side">
                        <dl class="menu menu_first">
                            <dt class="menu_title">
                                <i class="icon_menu menu_management"></i>督导管理
                            </dt>
                            <c:if test="${role eq 'expertLeader' || role eq 'management'}">
                                <dd class="menu_item"><a onclick="subjectMain();">项目管理</a></dd>
                            </c:if>
                            <c:if test="${role eq 'expertLeader' || role eq 'management'}">
                                <dd class="menu_item"><a onclick="planScoreMain();">专业评分</a></dd>
                            </c:if>
                            <dd class="menu_item"><a onclick="signMain();">签名设置</a></dd>
<%--                            <dt class="menu_title">--%>
<%--                                <i class="icon_menu menu_management"></i>基地管理--%>
<%--                            </dt>--%>
<%--                            <dd class="menu_item"><a onclick="subjectMain('Y');">基地项目</a></dd>--%>
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
