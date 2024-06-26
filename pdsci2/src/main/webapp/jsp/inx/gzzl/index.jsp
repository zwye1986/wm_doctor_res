<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>广州医科大学研究生管理系统</title>
    <<script type="text/javascript" src="<s:url value='/js/jquery-1.8.3${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <link rel="stylesheet" type="text/css" href="<s:url value='/jsp/inx/gzzl/css/font.css'/>"/>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <script type="text/javascript" >
        $(function(){
            var pwd = $("#placepwd");
            var password = $("#userPasswd");
            if (navigator.appName.indexOf("Microsoft Internet Explorer")>-1) {//处理ie浏览器placeholder和password的不兼容问题
                password.hide();
                pwd.show();

                pwd.focus(function(){
                    pwd.hide();
                    password.show().focus();
                });

                password.focusout(function(){
                    if(password.val().trim() === ""){
                        password.hide();
                        pwd.show();
                    }
                });
            }
            jboxLoad("initNoticeDiv","<s:url value='/inx/osce/initNotice'/>",null,true);
        });
        function reloadVerifyCode()
        {
            $("#verifyImage").attr("src","<s:url value='/captcha'/>?random="+Math.random());
        }
        function checkForm(){
            if(false==$("#loginForm").validationEngine("validate")){
                return false;
            }
            $("#loginForm").submit();;
        }
        function showTeacherRegist(){
            window.location.href="<s:url value='/jsp/gzykdx/teacher/registerTeacher.jsp'/>";
            <%--var width=(window.screen.width)*0.7;--%>
            <%--var height=(window.screen.height)*0.7;--%>
            <%--jboxStartLoading();--%>
            <%--jboxOpen("<s:url value='/jsp/gzykdx/teacher/registerTeacher.jsp'/>", "导师注册", width, height);--%>
        }
    </script>
</head>
<body>
<div class="top_box">
    <div class="top">
        <span class="fl logo"><img src="<s:url value='/jsp//inx/gzzl/images/logo.png'/>"></span>
    </div>
</div>
<div  class="banner-box">
    <div class="banner">
        <div class="weixin_bg">
            <div class="weixin">
                <form id="loginForm" action="<s:url value='/inx/gzzl/login'/>" method="post">
                    <div class="lc_login">
                        <img src="<s:url value='/jsp//inx/gzzl/images/name.png'/>">
                        <input type="text" class="validate[required]" name="userCode" placeholder="用户名">
                    </div>
                    <div class="mima">
                        <img src="<s:url value='/jsp//inx/gzzl/images/key.png'/>">
                        <input type="password" class="validate[required]" name="userPasswd" placeholder="密码">
                    </div>
                    <div class="yanzm">
                        <div class="yan fl">
                            <input name="verifyCode" class="validate[required] fl" placeholder="验证码">
                        </div>
                        <img style="width:102px;height:42px;cursor:pointer;" id="verifyImage" src="<s:url value='/captcha'/>" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" />
                    </div>
                    <c:if test="${not empty loginErrorMessage}">
                        <div style="color:red;">登录失败：${loginErrorMessage}</div>
                    </c:if>
                    <div class="denglu">
                        <input type="submit" name="imgButton" value="登　录" onclick="checkForm();">
                        <input type="button" value="导师注册" onclick="showTeacherRegist();">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="footer_box">
        <span class="footer">技术支持：南京品德科技有限责任公司　服务电话：020-38039876&nbsp;&nbsp;
			<a target="_blank" style="color:#000; font-size:14px;" href="${applicationScope.sysCfgMap['chrome_download_url']}">专用浏览器下载</a>
		</span>
</div>
</body>
</html>
