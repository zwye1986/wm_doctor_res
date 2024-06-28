<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>${sysCfgMap['sys_title_name']}</title>
    <jsp:include page="/jsp/recruit/htmlhead-recruit.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="login" value="true"/>
        <jsp:param name="register" value="f"/>
        <jsp:param name="font" value="f"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>

    <script>
        $(function(){//获取当前星期几
            var weekDate = '星期'+'日一二三四五六'.charAt(new Date().getDay());
            $("#weekDateSpan").html(weekDate);
        });

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

        function register(){
            window.location.href="<s:url value='/jsp/recruit/inx/register.jsp'/>";
        }
        function reloadVerifyCode()
        {
            $("#verifyImage").attr("src","<s:url value='/captcha'/>?random="+Math.random());
        }
        function checkForm(){
            if($("#userCode").val()==""){
                $(".log_tips").html("用户名不能为空!");
                return false;
            }
            if($("#userPasswd").val()==""){
                $(".log_tips").html("密码不能为空!");
                return false;
            }
            if($("#verifyCode").val()==""){
                $(".log_tips").html("验证码不能为空!");
                return false;
            }

            $("#loginForm").submit();
        }


    </script>

</head>
<body>
<div class="bg">
    <form id="loginForm" action="<s:url value='/inx/recruit/login'/>" method="post">
        <div class="login">

            <p class="lg-title">用户登录</p>

            <div class="lg-inp">
                <i class="iconfont icon-user"></i>
                <input id="userCode" class="text-inp" name="userCode" value="" placeholder="用户名"/>
            </div>

            <div class="lg-inp">
                <i class="iconfont icon-lock"></i>
                <%--<input type="text" id="placepwd" placeholder="密码" value=""/>--%>
                <input  type="password" class="text-inp" id="userPasswd" name="userPasswd" value="" placeholder="密码"/>
            </div>

            <div class="validation">
                <div class="lg-inp lg-vali">
                    <i class="iconfont icon-mail"></i>
                    <input id="verifyCode" name="verifyCode" class="text-inp" value="" style="width: 90%;" placeholder="验证码"/>
                </div>
                <img class="val-img" id="verifyImage" src="<s:url value='/captcha'/>" style="cursor:pointer;height: 40px; padding-left: 50px; position: absolute;" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" />
            </div>

            <span class="cw">
                 <c:if test="${not empty loginErrorMessage}">
                     ${loginErrorMessage}
                 </c:if>
                 </span>


            <div class="sel">
                <input class="button select" onclick="return checkForm();" type="submit" value="登&nbsp;&nbsp;&nbsp;录" style="margin-bottom: 10px;"/>
                <input class="button button-box" type="button" value="注&nbsp;&nbsp;&nbsp;册" onclick="register();"/>
            </div>


        </div>
    </form>
</div>
<c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
    <jsp:include page="/jsp/service.jsp" flush="true"></jsp:include>
</c:if>

</body>
</html>