<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="<s:url value='/js/jquery-1.8.3${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <title>徐州市中心医院科研管理系统</title>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <script>
        function reloadVerifyCode(){
            $("#verifyImage").attr("src","<s:url value='/captcha'/>?random="+Math.random());
        }$(document).keydown(function(event){
            if(event.keyCode == 13){ //绑定回车
                checkForm(); ///自动/触发登录按钮
            }
        });
        function checkForm(){
            if(false==$("#loginForm").validationEngine("validate")){
                return false;
            }
            $("#loginForm").submit();
        }

    </script>
</head>

<body>
<div class="loginbg">
    <div class="loginlogo">
        <img src="<s:url value='/jsp/inx/xzky/images/logo.png'/>">
    </div>
    <div class="login">
        <form id="loginForm" action="<s:url value='/inx/xzky/login'/>" method="post">
            <table>
                <th>用户登录</th>
                <tr>
                    <td>
                        <img src="<s:url value='/jsp/inx/xzky/images/user.png'/>">
                        <input type="text" class="loginsr validate[required]" name="userCode" placeholder="用户名" >
                    </td>
                </tr>
                <tr>
                    <td>
                        <img src="<s:url value='/jsp/inx/xzky/images/password.png'/>">
                        <input type="password" class="loginsr validate[required]" name="userPasswd" placeholder="密码">
                    </td>
                </tr>
                <tr>
                    <td>
                        <div style="height: 46px;padding-left: 0;">
                            <img style="margin-top: 4px" src="<s:url value='/jsp/inx/xzsxs/images/yzm.png'/>">
                            <input type="text" style="width: 200px;margin-top: 4px" class="loginsr validate[required]" name="verifyCode" placeholder="验证码" >
                            <img  style="width:100px; height:48px;cursor: pointer;padding: 1px;margin-right: -30px;float: right" id="verifyImage" src="<s:url value='/captcha'/>"
                                  onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" >
                        </div>
                    </td>
                </tr>
            </table>

            <div class="btn_y" style="margin-top: -5px">
                <c:if test="${not empty loginErrorMessage}">
                    <font color="red" size="2" style="margin-left:70px;">${loginErrorMessage}</font>
                </c:if>
                <c:if test="${empty loginErrorMessage}">
                    <font color="red" size="2" style="margin-left:70px;">&nbsp;</font>
                </c:if>
                <a onclick="checkForm();">  登&nbsp;&nbsp;&nbsp;&nbsp;录</a>
            </div>
        </form>
    </div>


    <div class="loginbottom">
        <span>主办单位：徐州市中心医院&nbsp;&nbsp;&nbsp;地址：徐州市解放南路199号</span><br>
        <a href="http://www.njpdkj.com/">技术支持：南京品德科技有限责任公司&nbsp;&nbsp;&nbsp;联系电话：025-69815757</a>
    </div>
</div>
</body>
</html>