<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>${sysCfgMap['sys_title_name']}</title>
    <link rel="stylesheet" type="text/css" href="<s:url value='/jsp/inx/njresexam/css/style.css'/>"/>
    <jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="login" value="true"/>
        <jsp:param name="register" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <style>
        .login_bg{
            background-image: none!important;
            background-color:#C3E3EF !important;
            width: 336px;
            border-radius: 10px;
        }
        .login_box{
            margin-left: 32px!important;
        }
    </style>
    <script>
        $(function () {//获取当前星期几
            var weekDate = '星期' + '日一二三四五六'.charAt(new Date().getDay());
            $("#weekDateSpan").html(weekDate);
        });

        $(function () {
            var pwd = $("#placepwd");
            var password = $("#userPasswd");
            if (navigator.appName.indexOf("Microsoft Internet Explorer") > -1) {//处理ie浏览器placeholder和password的不兼容问题
                password.hide();
                pwd.show();

                pwd.focus(function () {
                    pwd.hide();
                    password.show().focus();
                });

                password.focusout(function () {
                    if (password.val().trim() === "") {
                        password.hide();
                        pwd.show();
                    }
                });
            }
        });


        function reloadVerifyCode() {
            $("#verifyImage").attr("src", "<s:url value='/captcha'/>?random=" + Math.random());
        }
        function checkForm() {
            if ($("#userCode").val() == "") {
                $(".log_tips").html("用户名不能为空!");
                return false;
            }
            if ($("#userPasswd").val() == "") {
                $(".log_tips").html("密码不能为空!");
                return false;
            }
            if ($("#verifyCode").val() == "") {
                $(".log_tips").html("验证码不能为空!");
                return false;
            }
            $("#loginForm").submit();
        }

    </script>

</head>
<body style="background: #fff;">
<div class="top_box" style="background:#fff">
    <div class="top">
        <div class="fl">${pdfn:getCurrDate()}&nbsp;&nbsp;<font id="weekDateSpan">${pdfn:getDayOfWeek()}</font></div>
        <span class="fr top_text" style="color:#949090">欢迎使用江苏省${pdfn:getCurrYear()}年结业技能考试准考证打印平台！</span>
    </div>
</div>


<div class="banner_page">
    <div class="banner_box1">
        <%--<img class="banner" src=""/>--%>
        <%--<img class="login_people" src="<s:url value='/jsp/inx/njresexam/images/login_bg.png'/>"/>--%>
        <%--<img class="banner_title" width="600" src="<s:url value='/jsp/inx/njresexam/images/title.png'/>"/>--%>
        <div class="lclz">
            <span class="fl logo"><img width="666" src="<s:url value="/jsp/inx/njresexam/images/title.png"/>"/></span>
        </div>
        <div class="login_bg" style="top:50px;background: url('<s:url value='/jsp/inx/njresexam/images/login_bg.png'/>');background-size: 353px 300px;">
            <form id="loginForm" action="<s:url value='/inx/njresexam/login'/>" method="post">
                <div class="login_box" style="margin-top: 44px;margin-left: 40px">
                    <div class="username" style="border:solid 1px #ffffff;margin-left: 10px">
                        <img style="margin-top: 5px; margin-right: 8px; margin-left: 5px" src="<s:url value='/jsp/inx/njresexam/images/name.png'/>"/>
                        <input id="userCode" name="userCode" value="" placeholder="姓名"/>
                    </div>
                    <div class="password" style="border:solid 1px #ffffff;margin-left: 10px">
                        <img style="margin-top: 5px; margin-right: 8px;margin-left: 5px" src="<s:url value='/jsp/inx/njresexam/images/pass.png'/>"/>
                        <input type="text" id="placepwd" style="display: none;" placeholder="证件号" value=""/><input
                            onpaste="return false" oncontextmenu="return false" oncopy="return false" oncut="return false"
                            type="password" id="userPasswd" name="userPasswd" value="" placeholder="证件号"/>
                    </div>
                    <div class="yanzm" >
                        <div class="yan fl" style="border:solid 1px #ffffff;margin-left: 10px">
                            <input id="verifyCode" name="verifyCode" class="fl"  style="width: 170px;" value="" placeholder="验证码"/>
                            <img id="verifyImage" src="<s:url value='/captcha'/>" style="cursor:pointer;height: 40px;padding-left: 20px; position: absolute;"
                                 onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张"/>
                        </div>
                    </div>
                    <span class="cw" style="padding-left: 10px">
                 <c:if test="${not empty loginErrorMessage}">
                     ${loginErrorMessage}
                 </c:if>
                 </span>
                    <div class="denglu" style="width:100%;text-align:center;margin-left: 20px">
                        <input class="login1 fl" onclick="return checkForm();" type="submit" value="登&nbsp;&nbsp;&nbsp;录"
                               style="margin-bottom: 10px; background: #1493E5"/>
                        <%--<a class="login1 fl" onclick="return checkForm();" style="margin-bottom: 10px;"><img src="<s:url value='/jsp/inx/njresexam/images/login_btn.png'/>"/> </a>--%>
                        <%--<input class="login2 fl" type="button" value="注&nbsp;&nbsp;&nbsp;册" onclick="register();"/>--%>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<%--<div class="new_box">--%>
    <%--<div class="new">--%>
        <%--<span class="fl xitong">系统公告</span>--%>
        <%--<c:forEach items="${infos}" var="info">--%>
            <%--<div class="fl gongao">--%>
                <%--<span>●</span>--%>
                <%--<a class="notice_title" href="<s:url value='/inx/jszy/noticeView'/>?infoFlow=${info.infoFlow}"--%>
                   <%--target="_blank">${info.infoTitle}--%>
                    <%--<c:if test="${pdfn:signDaysBetweenTowDate(pdfn:getCurrDate(),pdfn:transDate(info.createTime))<=7}">--%>
                        <%--<img src="<s:url value='/jsp/jszy/images/jszy_new.png'/>"/>--%>
                    <%--</c:if>--%>
                <%--</a>--%>
            <%--</div>--%>
        <%--</c:forEach>--%>
        <%--<a href="<s:url value='/inx/jszy/noticelist'/>" target="_blank" class="fr more">查看更多</a>--%>
    <%--</div>--%>
<%--</div>--%>
<div class="footer_box">
    <a class="login_footer db">主管单位：江苏省卫生健康委员会科教处 | 技术支持：南京品德网络信息技术有限公司</a>
</div>

</body>
</html>
