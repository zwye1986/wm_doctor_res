<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>The First Affiliated Hosp.of GUCM</title>
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
        window.location.href="<s:url value='/jsp/inx/czyyjxres/register_en.jsp'/>";
    }
    function reloadVerifyCode()
    {
        $("#verifyImage").attr("src","<s:url value='/inx/czyyjxres/captcha'/>?random="+Math.random());
    }
    function checkForm(){
        if($("#userCode").val()==""){
            $(".log_tips").html("User name cannot be empty!");
            return;
        }
        if($("#userPasswd").val()==""){
            $(".log_tips").html("Password cannot be empty!");
            return;
        }
        if($("#verifyCode").val()==""){
            $(".log_tips").html("The verification code cannot be null!");
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
            <img src="<s:url value='/jsp/czyyjxres/images/logo_en.png'/>" alt="logo" title="logo" style="margin-left: -441px;">
        </div>
        <div class="banner">
            <h3><img src="<s:url value='/jsp/czyyjxres/images/sys_logo_en.png'/>" style="margin-left: -415px;"></h3>
            <form id="loginForm" action="<s:url value='/inx/czyyjxrecruit/login'/>" method="post">
                <div class="bg_login">
                    <table>
                        <tr>
                            <th>User login</th>
                        </tr>
                        <tr>
                            <td>
                                <img src="<s:url value='/jsp/czyyjxres/images/user_1.png'/>">
                                <input style="width:180px;margin-top: 2px;" type="text" id="userCode" name="userCode" placeholder="User name">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <img alt="User name" title="User name" src="<s:url value='/jsp/czyyjxres/images/password_1.png'/>">
                                <input style="width:180px;margin-top: 2px;" type="password" id="userPasswd" name="userPasswd" placeholder="password">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <img alt="Verification Code" title="Verification Code" src="<s:url value='/jsp/czyyjxres/images/yzm.png'/>">
                                <input style="margin-top: 2px;width: 115px;float: left" type="text" id="verifyCode" name="verifyCode" placeholder="Verification Code" >
                                <img id="verifyImage" src="<s:url value='/inx/czyyjxres/captcha'/>" style="cursor:pointer;width:64px;height:28px;" onclick="reloadVerifyCode();" title="Click the change verification code" alt="I can't see it. Change it" />
                            </td>
                        </tr>
                        <tr>
                            <td class="last" style="border: none;">
                                <div style="text-align: left;color: red;font-size: 14px;margin-top: -5px;" class="log_tips">${loginErrorMessage}</div>
                                <div style="border:none;">
                                    <a onclick="checkForm()"><input type="button" class="dl" style="cursor:pointer;" value="Sign in"></a>
                                    <a onclick="register()"><input type="button" class="zz" style="cursor:pointer;" value="Register"></a>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
