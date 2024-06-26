<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>临床技能训练中心管理系统</title>
    <script type="text/javascript" src="<s:url value='/js/jquery-1.8.3${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <link rel="stylesheet" type="text/css" href="<s:url value='/jsp/inx/lcjn/css/font.css'/>"/>
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
        function testDisplay(){
            $(".side-bar").hide();
        }
    </script>
</head>
<body style="background:#f9f9f9">
<div class="loginbg">
    <div class="loginbar">
        <div class="mainlogin">
            <form id="loginForm" action="<s:url value='/inx/lcjn/login'/>" method="post">
                <table class="logintb" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td class="logintb_td"><input type="text" style="width:260px;" class="validate[required] loginsr" name="userCode" placeholder="用户名"/></td>
                    </tr>
                    <tr><td height="20px">&nbsp;</td></tr>
                    <tr>
                        <td class="logintb_td"><input type="password" style="width:260px;" class="validate[required] loginsr" name="userPasswd" placeholder="密码"/></td>
                    </tr>
                    <tr><td height="20px">&nbsp;</td></tr>
                    <tr>
                        <td class="logintb_td"><input type="text" class="validate[required] loginsr" name="verifyCode" placeholder="验证码"/>
                            <img style="width:102px;height:42px;cursor:pointer;float:right;" id="verifyImage" src="<s:url value='/captcha'/>" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" />
                        </td>
                    </tr>
                    <c:if test="${not empty loginErrorMessage}">
                        <tr><td style="color:red;">登录失败：${loginErrorMessage}</td></tr>
                    </c:if>
                    <tr>
                        <td align="center" height="42px">
                            <input type="submit" class="btn_y" name="imgButton" value="登&nbsp;&nbsp;录" onclick="checkForm();">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div class="loginlogo">
            <c:if test="${applicationScope.sysCfgMap['lcjn_customer'] ne 'gzzyyy'}">
                <img src="<s:url value='/jsp/inx/lcjn/images/${applicationScope.sysCfgMap["lcjn_customer"] eq "gzdh"?"dhlogo.png":"logo.png"}'/>">
            </c:if>
        </div>
        <div class="title">
            临床技能训练中心管理系统
        </div>
        <div class="side-bar" style="display:none;">
            <a href="javascript:void(0);" class="icon-chat" title="浏览器扫码">手机版
                <div class="chat-tips"><i></i><img style="width:138px;height:138px;" src="<s:url value='/jsp/inx/lcjn/images/lcjnapp.png'/>"></div>
            </a>
            <div class="close" onClick="testDisplay()">关闭</div>
        </div>
        <div class="loginbottom">
            技术支持：南京品德网络信息技术有限公司 &nbsp;&nbsp;&nbsp;服务电话：025-68581968 68581986
        </div>
    </div>
</div>
</body>
</html>
