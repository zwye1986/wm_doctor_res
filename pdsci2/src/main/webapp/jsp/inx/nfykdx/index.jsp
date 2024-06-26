<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <link rel="stylesheet" type="text/css" href="<s:url value='/jsp/inx/nfykdx/css/font.css'/>"/>
    <script type="text/javascript" src="<s:url value='/js/cryptojs/tripledes.js'/>"></script>
    <script type="text/javascript" src="<s:url value='/js/cryptojs/mode-ecb.js'/>"></script>
    <script type="text/javascript" >
        $(function(){
            $("body").keydown(function() {
                if (event.keyCode == "13") {//keyCode=13是回车键
                    $('#btnSumit').click();
                }
            });
        })
        function reloadVerifyCode()
        {
            $("#verifyImage").attr("src","<s:url value='/captcha'/>?random="+Math.random());
        }
        function checkForm(){
            if(false==$("#loginForm").validationEngine("validate")){
                return false;
            }
            var userCode = encryptByDES($("input[name='userCode2']").val(),"${csrftoken}");
            var userPasswd = encryptByDES($("input[name='userPasswd2']").val(),"${csrftoken}");
            $("input[name='userCode']").val(userCode);
            $("input[name='userPasswd']").val(userPasswd);
            $("#loginForm").submit();
        }
        function register(){
            location.href="<s:url value='/jsp/inx/nfykdx/register.jsp'/>";
        }
        function encryptByDES(message, key) {
            var keyHex = CryptoJS.enc.Utf8.parse(key);
            var encrypted = CryptoJS.DES.encrypt(message, keyHex, {
                mode: CryptoJS.mode.ECB,
                padding: CryptoJS.pad.Pkcs7
            });
            return encrypted.toString();
        }
    </script>
</head>
<body style="background:url(<s:url value='/jsp/inx/nfykdx/images/login_bg.png'/>);">
<form id="loginForm" action="<s:url value='/login'/>" method="post">
    <input type="hidden" name="errorLoginPage" value="inx/nfykdx/index"/>
    <div class="login-body">
        <div class="login-bar">
            <div class="mainlogin">
                <table class="logintb" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td width="87px"></td>
                        <td height="39px">
                            <input type="text" style="width:185px;" class="validate[required] loginsr" name="userCode2" placeholder="用户账号/学号/邮箱/手机号">
                            <input type="hidden" name="userCode">
                        </td>
                    </tr>
                    <tr><td height="17px" colspan="2"></td></tr>
                    <tr>
                        <td width="87px"></td>
                        <td height="39px">
                            <input class="validate[required] loginsr" name="userPasswd2" type="password" placeholder="密码" style="width:185px;" />
                            <input name="userPasswd" type="hidden" />
                        </td>
                    </tr>
                    <tr><td height="16px" colspan="2"></td></tr>
                    <tr>
                        <td width="87px"></td>
                        <td height="39px" ><input type="text" name="verifyCode"  class="validate[required] loginsr" value="" placeholder="验证码" style="width:110px; " />
                            <img id="verifyImage" src="<s:url value='/captcha'/>" style="cursor:pointer;height: 40px;padding-left:30px;margin-right: -60px;" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" />
                        </td>
                    </tr>
                    <tr>
                        <td height="45px" colspan="2"><font class="log-tips">
                            <c:if test="${not empty loginErrorMessage}">
                                登录失败：${loginErrorMessage}
                            </c:if>
                        </font></td>
                    </tr>
                    <tr>
                        <c:set var="registerFlag" value="${pdfn:getCurrDateTime('yyyy-MM-dd') ge start.cfgValue and end.cfgValue ge pdfn:getCurrDateTime('yyyy-MM-dd')}"/>
                        <td valign="top" colspan="2" style="padding-left:15px;">
                            <input class="btn-dl" type="button" id="btnSumit" <c:if test="${!registerFlag}">style="width:280px;" </c:if> onclick="return checkForm();" value="登&nbsp;&nbsp;录"/>
                            <c:if test="${registerFlag}">
                                <input class="btn-dl" type="button" onclick="register();" value="导师注册"/>
                            </c:if>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="logintitle">
            <img src="<s:url value='/jsp/inx/nfykdx/images/login_title.png'/>">
        </div>
        <div class="loginlogo">
            <img src="<s:url value='/jsp/inx/nfykdx/images/login_logo.png'/>">
        </div>
        <div class="logincloud"></div>
        <c:if test="${applicationScope.sysCfgMap['res_app_cfg'] == 'Y' and not empty applicationScope.sysCfgMap['res_app_cfg_url'] }">
            <div class="apk">
                <a href="javascript:void(0);" style="color:#435182;">APP客户端下载 <span class="img" style="z-index: 600;"><img height="150" width="150" style="width: 150px;height:150px;" src="${applicationScope.sysCfgMap['res_app_cfg_url']}"></span></a>
            </div>
        </c:if>
        <div class="loginbottom">
            技术支持：南京品德网络信息技术有限公司
        </div>
    </div>
</form>
</body>
</html>
