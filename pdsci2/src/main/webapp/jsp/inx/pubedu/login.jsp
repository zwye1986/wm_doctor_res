<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head id="Head1">
    <title>公共科目管理平台</title>
    <script type="text/javascript" src="<s:url value='/js/jquery-1.8.3${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <link rel="stylesheet" type="text/css" href="<s:url value='/jsp/inx/pubedu/css/Default.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<s:url value='/jsp/inx/pubedu/css/StyleSheet1.css'/>"/>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="jbox" value="true"/>
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
<body <%--style="background: #fff;"--%>>
    <div class="main_container">
        <form id="loginForm"  action="<s:url value='/inx/pubedu/login'/>" method="post" >
            <div class="header">
                <div class="fl">
                    <span class="fl">${pdfn:getCurrDate() }&nbsp;&nbsp;&nbsp;<font id="weekDateSpan"></font></span>
                </div>
            </div>
            <div class="banner_page">
                <div class="banner_box">
                    <div class="lclz">
                        <img src="<s:url value='/jsp/inx/pubedu/image/banner_title.png'/>">
<%--                        <div class="banner">--%>
<%--                            <img src="<s:url value='/jsp/inx/pubedu/image/1234.png'/>">--%>
<%--                        </div>--%>
                    </div>
                    <div class="weixin_bg" style="top: 40px">
                        <div class="weixin">
                            <span class="chname">公共科目学习平台</span>
                            <div class="lc_login">
                                <img src="<s:url value='/jsp/inx/pubedu/image/name.png'/>">
                                <input type="text" class="validate[required]" name="userCode" placeholder="用户名" >
                            </div>
                            <div class="mima">
                                <img src="<s:url value='/jsp/inx/pubedu/image/password.png'/>">
                                <input type="password" class="validate[required]" name="userPasswd" placeholder="密码">
                            </div>
                            <div class="mima">
                                <img src="<s:url value='/jsp/inx/pubedu/image/verifyCode.png'/>">
                                <input id="verifyCode" class=" validate[required]"  name="verifyCode" placeholder="验证码">
                                <img src="<s:url value='/captcha'/>" id="verifyImage"  style="cursor:pointer;float: right;margin-top: -47px;margin-right: -61px;" onClick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张"/>
                            </div>
                            <div class="jizhu">
                                <c:if test="${not empty loginErrorMessage}">
                                    <span>${loginErrorMessage}</span>
                                </c:if>
                            </div>
                            <div class="denglu">
                                <input type="submit" value="登&#12288;录" onclick="checkForm();">
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="footer_box">
            <span class="footer db"><a href="https://beian.miit.gov.cn/" target="_blank" style="color: #4C4C4C;font-size: 14px;">工信部备案号：苏ICP备14054231号-1</a> &nbsp; 技术支持：南京品德网络信息技术有限公司　服务电话：025-69815356&#12288;69815357</span>
            </div>
        </form>
    </div>
</body>
</html>
