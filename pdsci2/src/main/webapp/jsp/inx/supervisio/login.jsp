<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>江苏省督导管理系统</title>
    <link rel="stylesheet" type="text/css" href="<s:url value='/jsp/inx/supervisio/css/style.css'/>"/>
    <script type="text/javascript" src="<s:url value='/js/jquery-1.8.3${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript" src="<s:url value='/js/json2${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript" src="<s:url value='/js/common.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="rsasecurity" value="true"/>
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
        });
        function reloadVerifyCode() {
            $("#verifyImage").attr("src","<s:url value='/supervisioCaptcha'/>?random="+Math.random());
        }
        function checkForm(){
            if($("#userCode").val()==""){
                $(".log_tip").html("用户名不能为空!");
                return false;
            }
            if($("#userPasswd").val()==""){
                $(".log_tip").html("密码不能为空!");
                return false;
            }
            if($("#verifyCode").val()==""){
                $(".log_tip").html("验证码不能为空!");
                return false;
            }
            var f=checkUser();
            if(f){
                return true;
            }else {
                return false;
            }
        }

        function checkUser(){
            var loginUrl = "<s:url value='/inx/supervisio/login'/>";
            var data = {userCode : $("#userCode").val(),userPasswd : $("#userPasswd").val(), verifyCode : $("#verifyCode").val()};
            var param = JSON.stringify(data);
            // 加密  公钥指数  ""  公钥系数
            if("${pkExponent}" && "${pkModulus}"){
                var key = RSAUtils.getKeyPair("${pkExponent}", "", "${pkModulus}");
                data = RSAUtils.encryptedString(key, encodeURI(param));
            }
            document.write("<form action='"+ loginUrl +"' method='post' id='form' name='form' style='display:none'>");
            document.write("<input type='hidden' name='data' value='"+ data +"' />");
            document.write("</form>");
            document.form.submit();
            return true;
        }
    </script>
</head>
<body style="background: #fff;">
<div class="main_container">
    <form id="loginForm" action="<s:url value='/inx/supervisio/login'/>" method="post">
        <div class="header">
            <span>
              <div class="fl">${pdfn:getCurrDate()}&nbsp;&nbsp;<font id="weekDateSpan">${pdfn:getDayOfWeek()}</font></div>
            </span>
        </div>
        <div class="banner_page">
            <div class="banner_box">
                <div class="lclz"><img src="<s:url value='/jsp/inx/supervisio/images/banner_title.png'/>" height="40px"></div>
                <div class="weixin_bg" style="top:40px;">
                    <div class="weixin">
                        <span class="chname">用户登录</span>
                        <div class="lc_login">
                            <img src="<s:url value='/jsp/inx/supervisio/images/name.png'/>">
                            <input class="validate[required]" type="text" name="userCode" id="userCode" placeholder="用户名">
                        </div>
                        <div class="mima">
                            <img src="<s:url value='/jsp/inx/supervisio/images/password.png'/>">
                            <input type="password" class="validate[required]" name="userPasswd" id="userPasswd" placeholder="密码">
                        </div>
                        <div class="mima">
                            <img src="<s:url value='/jsp/inx/supervisio/images/verifyCode.png'/>">
                            <input type="text" style="color:#a1a4a7;width: 140px" class="validate[required]" name="verifyCode" id="verifyCode" placeholder="验证码">
                            <img style="width:120px;cursor:pointer;float: right;margin-top: -47px;margin-right: -40px;" id="verifyImage" src="<s:url value='/supervisioCaptcha'/>" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" />
                        </div>
                        <div class="tishi">
                            <c:if test="${not empty loginErrorMessage}">
                                登录失败：${loginErrorMessage}
                            </c:if>
                        </div>
                        <span>&#12288;</span>
                        <div class="denglu">
                            <input type="submit" class="login1 fl" name="imgButton" value="登　录" onclick="checkForm();">
                        </div>
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
