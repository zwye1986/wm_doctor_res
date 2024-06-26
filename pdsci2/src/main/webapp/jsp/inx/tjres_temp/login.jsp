<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>${sysCfgMap['sys_title_name']}</title>
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
<body style="background: none;">
<div class="top_box" style="background:#eee">
    <div class="top">
        <span class="fl logo"><img width="600" src="<s:url value="/jsp/inx/tjres/images/title.png"/>"/></span>
        <span class="fr top_text" style="color:#949090">欢迎使用天津结业考试管理平台！</span>
    </div>
</div>
<div class="banner_box" style="bottom:48px;background-image: no-repeat; background:#fff url('<s:url value='/jsp/inx/tjres/images/banner.png'/>') bottom center">
    <%--<img class="banner" src=""/>--%>
    <%--<img class="login_people" src="<s:url value='/jsp/inx/tjres/images/login_bg.png'/>"/>--%>
    <%--<img class="banner_title" width="600" src="<s:url value='/jsp/inx/tjres/images/title.png'/>"/>--%>
    <div class="login_bg" style="top:20%;background: url('<s:url value='/jsp/inx/tjres/images/login_bg.png'/>')">
        <form id="loginForm" action="<s:url value='/inx/tjres/login'/>" method="post">
            <div class="login_box" style="margin-top: 24px">
                <span class="login_title" ><img style="margin-bottom: 20px" src="<s:url value='/jsp/inx/tjres/images/login_title.png'/>"/></span>
                <div class="username" style="border:solid 1px #ffffff;margin-left: 10px">
                    <img style="margin-top: 5px; margin-right: 8px;" src="<s:url value='/jsp/inx/tjres/images/name.png'/>"/>
                    <input id="userCode" name="userCode" value="" placeholder="学员编号"/>
                </div>
                <div class="password" style="border:solid 1px #ffffff;margin-left: 10px">
                    <img style="margin-top: 5px; margin-right: 8px;" src="<s:url value='/jsp/inx/tjres/images/pass.png'/>"/>
                    <input type="text" id="placepwd" style="display: none;" placeholder="密码" value=""/><input
                        onpaste="return false" oncontextmenu="return false" oncopy="return false" oncut="return false"
                        type="password" id="userPasswd" name="userPasswd" value="" placeholder="密码"/>
                </div>
                <div class="yanzm">
                    <div class="yan fl" style="border:solid 1px #ffffff;margin-left: 10px">
                        <input id="verifyCode" name="verifyCode" class="fl" value="" placeholder="验证码"/>
                    </div>
                    <!-- 					  <img src="images/pic1.png" /> -->
                    <img id="verifyImage" src="<s:url value='/captcha'/>" style="cursor:pointer;height: 40px;"
                         onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张"/>
                </div>
                 <span class="cw">
                 <c:if test="${not empty loginErrorMessage}">
                     ${loginErrorMessage}
                 </c:if>
                 </span>
                <div class="denglu" style="width:100%;text-align:center;margin-left: 20px">
                    <input class="login1 fl" onclick="return checkForm();" type="submit" value="登&nbsp;&nbsp;&nbsp;录"
                           style="margin-bottom: 10px; background: #4962E0"/>
                    <%--<a class="login1 fl" onclick="return checkForm();" style="margin-bottom: 10px;"><img src="<s:url value='/jsp/inx/tjres/images/login_btn.png'/>"/> </a>--%>
                    <%--<input class="login2 fl" type="button" value="注&nbsp;&nbsp;&nbsp;册" onclick="register();"/>--%>
                </div>
            </div>
        </form>
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
    <a class="login_footer db">主管单位：天津市卫生和计划生育委员会 | 技术支持：南京品德网络信息技术有限公司</a>
</div>

</body>
</html>
