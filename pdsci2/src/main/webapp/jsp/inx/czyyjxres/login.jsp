<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>潮州市人民医院进修教育管理系统</title>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/czyyjxres/css/login.css'/>"/>
    <script type="text/javascript" src="<s:url value='/js/jquery-1.8.3${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <jsp:include page="/jsp/czyyjxres/htmlhead-czyyjxres.jsp">
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<script>
    $(document).keydown(function(event){
        if(event.keyCode == 13){ //绑定回车
            checkForm(); ///自动/触发登录按钮
        }
    });
    function register(){
        window.location.href="<s:url value='/jsp/inx/czyyjxres/register.jsp'/>";
    }
    function reloadVerifyCode()
    {
        $("#verifyImage").attr("src","<s:url value='/inx/czyyjxres/captcha'/>?random="+Math.random());
    }
    function checkForm(){
        if($("#userCode").val()==""){
            $(".log_tips").html("用户名不能为空!");
            return;
        }
        if($("#userPasswd").val()==""){
            $(".log_tips").html("密码不能为空!");
            return;
        }
        if($("#verifyCode").val()==""){
            $(".log_tips").html("验证码不能为空!");
            return;
        }
        $("#loginForm").submit();
    }
</script>
</head>

<body>
<div id="con_main">
    <div class="main">
        <div class="logo">
            <img src="<s:url value='/jsp/czyyjxres/images/logo-1.png'/>" alt="logo" title="logo" style="margin-left: -441px;">
        </div>
        <div class="banner">
            <h3><img src="<s:url value='/jsp/czyyjxres/images/sys_logo.png'/>" style="margin-left: -415px;"></h3>
            <form id="loginForm" action="<s:url value='/inx/czyyjxres/login'/>" method="post">
                <div class="bg_login">
                    <table>
                        <tr>
                            <th>用户登录</th>
                        </tr>
                        <tr>
                            <td>
                                <img src="<s:url value='/jsp/czyyjxres/images/user_1.png'/>">
                                <input style="width:180px;margin-top: 2px;" type="text" id="userCode" name="userCode" placeholder="用户名">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <img alt="用户名" title="用户名" src="<s:url value='/jsp/czyyjxres/images/password_1.png'/>">
                                <input style="width:180px;margin-top: 2px;" type="password" id="userPasswd" name="userPasswd" placeholder="密码">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <img alt="验证码" title="验证码" src="<s:url value='/jsp/czyyjxres/images/yzm.png'/>">
                                <input style="margin-top: 2px;width: 100px;float: left" type="text" id="verifyCode" name="verifyCode" placeholder="验证码" >
                                <img id="verifyImage" src="<s:url value='/inx/czyyjxres/captcha'/>" style="cursor:pointer;width:64px;height:28px;" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" />
                            </td>
                        </tr>
                        <tr>
                            <td class="last" style="border: none;">
                                <div style="text-align: left;color: red;font-size: 14px;margin-top: -5px;" class="log_tips">${loginErrorMessage}</div>
                                <div style="border:none;">
                                    <a onclick="checkForm()"><input type="button" class="dl" style="cursor:pointer;" value="登录"></a>
                                    <a onclick="register()"><input type="button" class="zz" style="cursor:pointer;" value="注册"></a>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </form>
        </div>
        <div class="notice">
            <ul>
                <li><h4>系统公告</h4></li>
                <c:forEach items="${infos}" var="info">
                    <li><a class="first" href="<s:url value='/inx/czyyjxres/noticeView'/>?infoFlow=${info.infoFlow}" target="_blank">${info.infoTitle}</a></li>
                </c:forEach>
                <li><a class="more" href="<s:url value='/inx/czyyjxres/noticelist'/>" target="_blank">查看更多</a></li>
            </ul>
        </div>
    </div>
    <div class="bottom">
        <span><a href="http://www.njpdkj.com/" target="_blank">技术支持：南京品德科技有限责任公司</a></span>
    </div>
</div>
</body>
</html>
