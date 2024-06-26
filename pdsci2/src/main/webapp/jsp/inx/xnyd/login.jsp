<!doctype html>
<html>
<head>

    <meta charset="utf-8">
    <title>西南医科大学附属中医医院住院医师规范化培训平台</title>
    <link href="<s:url value='/'/>jsp/inx/xnyd/css/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<s:url value='/js/jquery-1.8.3${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
</head>

<script>
    function reloadVerifyCode()
    {
        $("#verifyImage").attr("src","<s:url value='/captcha'/>?random="+Math.random());
    }
    function checkForm() {
        if ($("#userCode").val() == "") {
            $(".cw").html("用户名不能为空!");
            return false;
        }
        if ($("#userPasswd").val() == "") {
            $(".cw").html("密码不能为空!");
            return false;
        }
        if ($("#verifyCode").val() == "") {
            $(".cw").html("验证码不能为空!");
            return false;
        }

        var f = checkUser();
        if (f) {
            return true;
        } else {
            return false;
        }
//	return true;
    }

    function showQr(type) {
        $(".QR").hide();
        $(".QR_" + type).show();
    }
    function hideThis(type) {
        $(".QR_" + type).hide();
    }
</script>
<body>
<div class="top_box">
    <div class="top">
        <span class="fl logo"><img src="<s:url value='/'/>jsp/inx/xnyd/images/logo.png"> </span>
    </div>
    <div class="code fr">
        <div class="android" onmouseover="showQr('android');" onmouseout="hideThis('android')"></div>
        <div class="QR_android QR"><img <%--class="QR_code"--%> src="<s:url value='/'/>jsp/inx/xnyd/images/downloadLead.png"></div>
    </div>
</div>

<div class="banner_box">
    <img class="banner" src="<s:url value='/'/>jsp/inx/xnyd/images/banner.png">
    <img class="people" src="<s:url value='/'/>jsp/inx/xnyd/images/banner_people.png">

    <div class="login_bg">
        <form id="loginForm" action="<s:url value='/inx/xnyd/login'/>" method="post">
            <input type="hidden" name="errorLoginPage" value="/inx/xnyd/login" />
            <div class="login_box">
                <span class="login_title"><img src="<s:url value='/'/>jsp/inx/xnyd/images/login_title.png"></span>
                <div class="username">
                    <img src="<s:url value='/'/>jsp/inx/xnyd/images/login_pic1.png">
                    <input type="text" class="loginsr" id="userCode" name="userCode" placeholder="用户名" value="">
                </div>
                <div class="password">
                    <img src="<s:url value='/'/>jsp/inx/xnyd/images/login_pic2.png">
                    <input type="password" class="loginsr" id="userPasswd" placeholder="密码" name="userPasswd" value=""/>
                </div>
                <div class="yanzm">
                    <div class="yan fl"><input type="text" id="verifyCode" name="verifyCode" placeholder="验证码" value=""></div>
                    <img id="verifyImage" src="<s:url value='/captcha'/>" style="cursor:pointer;" height="40px" width="100px" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" />
                </div>
                <span class="cw">
                    <c:if test="${not empty loginErrorMessage}">
                        登录失败：${loginErrorMessage}
                    </c:if>
                </span>
                <div class="denglu">
                    <input class="login1 fl" type="submit" onclick="return checkForm();" value="登&nbsp;&nbsp;&nbsp;录">
                    <!--<input class="login2 fr" type="button" value="注&nbsp;&nbsp;&nbsp;册">-->
                </div>
            </div>
        </form>
    </div>
</div>

<!--<div class="new_box">
	<div class="new">
    	<span class="fl xitong">系统公告</span>
        <div class="fl gongao"><span>●</span><a href="" target="_blank">公众平台自定义菜单功能优化上线</a><img src="images/new.png"></div>
        <div class="fl gongao"><span>●</span><a href="" target="_blank">公众平台带参数二维码接口更新</a><img src="images/new.png"></div>
        <a href="" class="fr more">查看更多>></a>
    </div>
</div>-->

<div class="footer_box">
    <a class="footer db">技术支持：南京品德网络信息技术有限公司&nbsp;&nbsp;|&nbsp;Tel&nbsp;:&nbsp;025-68581968 68581986</a>
</div>

</body>
</html>