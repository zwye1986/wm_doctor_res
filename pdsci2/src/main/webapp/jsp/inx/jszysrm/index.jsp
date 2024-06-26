<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>江苏省中医药局科研管理平台</title>
    <link rel="stylesheet" type="text/css" href="<s:url value='/jsp/inx/jszysrm/style.css'/>"/>
    <script type="text/javascript" src="<s:url value='/js/jquery-1.9.1.min.js'/>"></script>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function reloadVerifyCode() {
            $("#verifyImage").attr("src", "<s:url value='/captcha'/>?random=" + Math.random());
        }
        function checkForm() {
            if (false == $("#loginForm").validationEngine("validate")) {
                return false;
            }
            $("#loginForm").submit();
        }
        function register() {
            var url = "<s:url value='/reg/srm/go'/>";
            window.location.href = url;
        }

        $(document).ready(function () {
            jboxLoad("newsDiv", "<s:url value='/inx/jszysrm/queryData?columnId=LM03&jsp=inx/jszysrm/index_news&endIndex=5&isWithBlobs=Y'/>", true);
            jboxLoad("download", "<s:url value='/inx/jszysrm/queryData?columnId=LM02&jsp=inx/jszysrm/index_download&endIndex=5'/>", true);
        });
    </script>

<body>
<!--header-->
<div class="top_box">
    <div class="top">
        <span class="logo"><img src="<s:url value='/'/>jsp/inx/jszysrm/images/logo.png"></span>
    </div>
    <div class="banner_box">
        <img src="<s:url value='/jsp/inx/jszysrm/'/>images/banner.png">
        <div class="weixin_bg">
            <form id="loginForm" action="<s:url value='/inx/jszysrm/login'/>" method="post">
                <input type="hidden" name="errorLoginPage" value="/inx/jszysrm/index"/>
                <div class="weixin">
                    <a class="db login_title"><img src="<s:url value='/jsp/inx/jszysrm/images/name.png'/>"></a>
                    <div class="login">
                        <img src="<s:url value='/'/>jsp/inx/jszysrm/images/login_01.png">
                        <input type="text" name="userCode" value="" class="validate[required] user"
                               placeholder="用户名"/>
                    </div>
                    <div class="mima">
                        <img src="<s:url value='/'/>jsp/inx/jszysrm/images/mima_01.png">
                        <input type="password" name="userPasswd" class="validate[required] user" placeholder="密码"/>
                    </div>
                    <div class="yanzm">
                        <input name="verifyCode" value="" placeholder="验证码"/>
                        <img class="fr" id="verifyImage" src="<s:url value='/captcha'/>"
                             style="cursor:pointer;height: 40px;" onclick="reloadVerifyCode();" title="点击更换验证码"
                             alt="看不清，换一张"/>
                    </div>

                    <span class="cw" style="color: #ff0000"><c:if test="${not empty loginErrorMessage}">
                        &nbsp;${loginErrorMessage}
                    </c:if></span>
                    <div class="denglu">
                        <button class="btn_login" type="submit" onclick="return checkForm();">登&nbsp;&nbsp;录</button>
                        <%--<a href="javascript:void(0)" onclick="return checkForm()"
                           class="login1 fl">登&#12288;&#12288;录</a>--%>
                        <a href="javascript:void(0)" onclick="register()" class="login2 fr">注&#12288;&#12288;册</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<!--系统公告-->
<div class="contentbox_01">
    <div class="leftcontent fl">
        <div class="new_01">
            <img src="<s:url value='/'/>jsp/inx/jszysrm/images/xx.png" class="fl">
            <a class="fl title">通知公告</a>
            <a target="_blank" href="<s:url value='/inx/jszysrm/queryByColumnId?columnId=LM03'/>" class="fr more"><img
                    src="<s:url value='/'/>jsp/inx/jszysrm/images/more.png"></a>
        </div>
        <div id="newsDiv" class="new_02" style="height: 200px">

        </div>
    </div>

    <div class="rightcontent fr">
        <div class="download_01">
            <img src="<s:url value='/'/>jsp/inx/jszysrm/images/xx.png" class="fl">
            <a class="fl title">下载中心</a>
            <a target="_blank" href="<s:url value='/inx/jszysrm/queryByColumnId?columnId=LM02'/>" class="fr more"><img
                    src="<s:url value='/'/>jsp/inx/jszysrm/images/more.png"></a>
        </div>
        <div id="download" class="download_02" style="height: 200px">

        </div>

    </div>
</div>

<div class="contentbox_02">

    <div class="friendly_01">
        <img src="<s:url value='/'/>jsp/inx/jszysrm/images/xx.png" class="fl">
        <a class="fl title">友情链接</a>
        <%--<a href="" class="fr more"><img src="<s:url value='/'/>jsp/inx/jszysrm/images/more.png"></a>--%>
    </div>
    <div class="friendly_02">
        <ul class="fl link">
            <a href="http://www.jswst.gov.cn/jstcm/" class="link_01" target="_blank"><img src="<s:url value='/jsp/inx/jszysrm/'/>images/logo_170.gif"></a>
            <a href="http://www.gov.cn/" class="link_01" target="_blank"><img src="<s:url value='/jsp/inx/jszysrm/'/>images/loge_172.gif"></a>
            <a href="http://www.satcm.gov.cn/" class="link_01" target="_blank"><img src="<s:url value='/jsp/inx/jszysrm/'/>images/loge_174.gif"></a>
            <a href="http://www.moh.gov.cn/" class="link_01" target="_blank"><img src="<s:url value='/jsp/inx/jszysrm/'/>images/loge_176.gif"></a>
            <a href="http://www.js.gov.cn/" class="link_01" target="_blank"><img src="<s:url value='/jsp/inx/jszysrm/'/>images/loge_178.gif"></a>
            <a  class="last-link" href="http://wskj.jswst.gov.cn/pdsci/" class="link_01" target="_blank"><img src="<s:url value='/jsp/inx/jszysrm/'/>images/loge_173.png"></a>
        </ul>
    </div>

</div>

<div class="footer">
    <div style="width: 100%;text-align: center"><span>主办单位：江苏省中医药局</span></div>
    <div style="width: 100%;text-align: center">
        <span>技术支持：<a href="http://www.njpdkj.com/">南京品德网络信息技术有限公司</a>&nbsp;&nbsp;|&nbsp;&nbsp;TEL：025-68581986 68581968</span>
    </div>
</div>
</body>
</html>
