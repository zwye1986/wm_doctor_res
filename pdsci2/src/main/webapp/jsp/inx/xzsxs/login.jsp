<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script type="text/javascript" src="<s:url value='/js/jquery-1.8.3${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <link rel="stylesheet" type="text/css" href="<s:url value='/jsp/inx/xzsxs/css/font.css'/>"/>
    <title>徐州市中心医院实习生管理平台</title>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <script>
        function reloadVerifyCode(){
            $("#verifyImage").attr("src","<s:url value='/captcha'/>?random="+Math.random());
        }
        $(document).keydown(function(event){
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
<div>
    <h1><img src="<s:url value='/jsp/inx/xzsxs/images/logo.png'/>"></h1>
    <div class="bg">
        <div><img class="cloud_left" src="<s:url value='/jsp/inx/xzsxs/images/cloud_left.png'/>"></div>
        <div><img class="cloud_right" src="<s:url value='/jsp/inx/xzsxs/images/cloud_right.png'/>"></div>
        <div class="login">
            <form id="loginForm" action="<s:url value='/inx/xzsxs/login'/>" method="post">
            <table>
                <th>用户登录</th>
                <tr>
                    <td>
                        <img src="<s:url value='/jsp/inx/xzsxs/images/user.png'/>">
                        <input type="text" class="loginsr validate[required]" name="userCode" placeholder="用户名">
                    </td>
                </tr>
                <tr>
                    <td>
                        <img src="<s:url value='/jsp/inx/xzsxs/images/password.png'/>">
                        <input type="password" class="loginsr validate[required]" name="userPasswd" placeholder="密码">
                    </td>
                </tr>
                <tr>
                    <td>
                        <img src="<s:url value='/jsp/inx/xzsxs/images/yzm.png'/>">
                        <input type="text" class="yzm validate[required]" name="verifyCode" placeholder="验证码"
                        style="margin-top: 2px;width: 100px;float: left">
                        <img class="img_yzm" id="verifyImage" src="<s:url value='/captcha'/>" style="cursor: pointer;float: left;width: 121px;"
                             onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张">
                        <c:if test="${not empty loginErrorMessage}">
                            <div style="text-align: center;color: red;font-size: 20px;">${loginErrorMessage}</div>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td class="last">
                        <input type="button" class="btn" value="登&nbsp;&nbsp;录" onclick="checkForm()" style="cursor: pointer">
                    </td>
                </tr>
            </table>
            </form>
        </div>
    </div>
    <div class="foot">
        <span>主办单位：徐州市中心医院&nbsp;&nbsp;地址：徐州市解放南路199号</span>
        <a href="http://www.njpdkj.com/">技术支持：南京品德科技有限责任公司&nbsp;&nbsp;联系电话：025-69815757</a>
    </div>
</div>
</body>
</html>
