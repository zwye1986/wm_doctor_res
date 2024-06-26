<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>临床技能考核管理系统</title>
    <link rel="stylesheet" type="text/css" href="<s:url value='/jsp/inx/osce/css/style.css'/>"/>
    <script type="text/javascript" src="<s:url value='/js/jquery-1.8.3${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript" src="<s:url value='/js/json2${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript" src="<s:url value='/js/common.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <style>
        .apk{ text-align:center; display:block; margin:8px 0px -6px 0px;}
        .apk span.img{ display:none; z-index:100;}
        .apk a:hover{position:relative;}
        .apk a:hover span.img{display:block;position:absolute;top:-160px;left:-30px;}
    </style>
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
        function register(){
            window.location.href="<s:url value='/jsp/inx/osce/register.jsp'/>";
        }
    </script>
</head>
<body style="background: #fff;">
<div class="main_container">
    <form id="loginForm" action="<s:url value='/inx/osce/login'/>" method="post">
        <div class="header">
            <span>
              <div class="fl">${pdfn:getCurrDate()}&nbsp;&nbsp;<font id="weekDateSpan">${pdfn:getDayOfWeek()}</font></div>
            </span>
        </div>
        <div class="banner_page">
            <div class="banner_box">
                <div class="lclz"><img src="<s:url value='/jsp/inx/osce/images/banner_title.png'/>" height="40px"></div>
<%--                <div class="banner"><img src="<s:url value='/jsp/inx/osce/images/1234.png'/>"></div>--%>
                <div class="weixin_bg" style="top:40px;">
                    <div class="weixin">
                        <span class="chname">用户登录</span>
                        <div class="lc_login">
                            <img src="<s:url value='/jsp/inx/osce/images/name.png'/>">
                            <input class="validate[required]" type="text" name="userCode" placeholder="用户名">
                        </div>
                        <div class="mima">
                            <img src="<s:url value='/jsp/inx/osce/images/password.png'/>">
                            <input type="password" class="validate[required]" name="userPasswd" placeholder="密码">
                        </div>
                        <div class="mima">
                            <%--<div class="yan fl">--%>
                            <img src="<s:url value='/jsp/inx/osce/images/verifyCode.png'/>">
                            <input type="text" style="color:#a1a4a7;" class="validate[required]" name="verifyCode" placeholder="验证码">
                            <%--</div>--%>
                            <img style="width:120px;cursor:pointer;float: right;margin-top: -47px;margin-right: -50px;" id="verifyImage" src="<s:url value='/captcha'/>" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" />
                        </div>
                        <div class="tishi">
                            <c:if test="${not empty loginErrorMessage}">
                                登录失败：${loginErrorMessage}
                            </c:if>
                        </div>
                        <span>&#12288;</span>
                        <c:if test="${sysCfgMap['osca_open_submit'] ne 'Y'}">
                            <div class="denglu">
                                <input type="submit" class="login1 fl" name="imgButton" value="登　录" onclick="checkForm();">
                            </div>
                        </c:if>
                        <c:if test="${sysCfgMap['osca_open_submit'] eq 'Y'}">
                            <div class="denglu2">
                                <input type="submit" class="login1 fl" name="imgButton" value="登　录" onclick="checkForm();">
                                <input type="button" class="login1 fl" name="imgButton" value="注  册" onclick="register();" style="margin-left: 10px;">
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>

        <div class="new_box" id="initNoticeDiv">

        </div>
        <div class="footer_box" style="text-align:center;">
            <c:if test="${applicationScope.sysCfgMap['res_app_cfg'] == 'Y'}">
                <div class="apk">
                    <a href="javascript:void(0);" style="color:white;">APP客户端下载 <span class="img" style="z-index: 600;"><img height="150" width="150" style="width: 150px;height:150px;" src="${applicationScope.sysCfgMap['res_app_cfg_url']}"></span></a>
                </div>
            </c:if>
            <span class="footer db">
                <a href="https://beian.miit.gov.cn/" target="_blank" style="color:#FFFFFF;">工信部备案号：苏ICP备14054231号-1</a>&nbsp;
                技术支持：南京品德网络信息技术有限公司　服务电话：025-69815356 69815357 &nbsp;&nbsp;
                <a target="_blank" style="color:White; font-size:14px;" href="${applicationScope.sysCfgMap['chrome_download_url']}">专用浏览器下载</a>
            </span>
        </div>
    </form>
</div>
</body>
</html>
