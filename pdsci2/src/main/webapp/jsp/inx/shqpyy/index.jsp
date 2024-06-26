<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <link rel="stylesheet" type="text/css" href="<s:url value='/jsp/inx/shqpyy/css/font.css'/>"/>
    <style>
        .apk{ text-align:center; display:block; }
        .apk span.img{ display:none; z-index:100;}
        .apk a:hover{position:relative;}
        .apk a:hover span.img{display:block;position:absolute;top:-160px;left:-30px;}
    </style>
    <script type="text/javascript" >
        $(function(){
            $("body").keydown(function() {
                if (event.keyCode == "13") {//keyCode=13是回车键
                    $('#btnSubmit').click();
                }
            });
        });
        function reloadVerifyCode()
        {
            $("#verifyImage").attr("src","<s:url value='/captcha'/>?random="+Math.random());
        }
        function checkForm(){
            if(false==$("#loginForm").validationEngine("validate")){
                return false;
            }
            $("#loginForm").submit();
        }
    </script>
</head>
<body>
<div class="hb_CN">
    <div class="index_header">
        <div style="margin:50px 0px 0px -700px;height:100%;background-image:url(<s:url value='/jsp/inx/shqpyy/images/logo.png'/>);background-repeat:no-repeat;background-position:center;"></div>
    </div>
    <div class="content global">
        <div class="banner">
            <div class="banner_box">
                <div class="mainlogin">
                    <form id="loginForm" action="<s:url value='/login'/>" method="post">
                        <input type="hidden" name="errorLoginPage" value="inx/shqpyy/index">
                        <table class="logintb" border="0" cellpadding="0" cellspacing="0">
                            <tbody>
                            <tr><td height="50px;" align="center">用户登录</td></tr>
                            <tr>
                                <td height="40px"><span class="username"><i></i><input type="text" class="validate[required] loginsr" id="userCode" name="userCode" style="width:202px;" placeholder="用户名/手机号"></span></td>
                            </tr>
                            <tr><td height="12px"></td></tr>
                            <tr>
                                <td height="40px"><span class="password"><i></i><input type="password" class="validate[required] loginsr" style="width:202px;" placeholder="密码" name="userPasswd" /></span></td>
                            </tr>
                            <tr><td height="12px"></td></tr>
                            <tr>
                                <td height="40px" class="logintb_td">
                                        <span class="hbVerifyCode">
                                            <div style="float: left;">
                                                <i></i><input type="text" style="width:130px;" class="validate[required] loginsr_yz" id="verifyCode" name="verifyCode" placeholder="验证码">
                                            </div>
                                            <div style="float: right;">
                                                <img id="verifyImage" src="<s:url value='/captcha'/>" style="cursor:pointer;margin-right: -40px;" height="40px" width="100px" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" />
                                            </div>
                                        </span>
                                </td>
                            </tr>
                            <tr>
                                <td height="50px" class="log_tips">
                                    <font class="log_tip red fl">
                                        <c:if test="${not empty loginErrorMessage}">
                                            登录失败：${loginErrorMessage}
                                        </c:if>
                                    </font>
                                    <span class="fr"><a href="" onclick="alert('研发中。。。')">忘记密码？</a></span></td>
                            </tr>
                            <tr>
                                <td valign="top">
                                    <button class="btn_login" type="submit" id="btnSubmit" onclick="return checkForm();">登&#12288;录</button>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="index_footer">
        <div align="center" style="font-size: 15px">
            <c:if test="${applicationScope.sysCfgMap['res_app_cfg'] == 'Y'}">
                <div class="apk">
                    <a href="javascript:void(0);" style="color:#666;">APP客户端下载 <span class="img" style="z-index: 600;"><img height="150" width="150" style="width: 150px;height:150px;" src="${applicationScope.sysCfgMap['res_app_cfg_url']}"></span></a>
                </div>
            </c:if> 地址：上海市青浦区公园东路1158号，201700 联系电话：67009999、69719190
        </div>
    </div>
</div>
</body>
</html>
