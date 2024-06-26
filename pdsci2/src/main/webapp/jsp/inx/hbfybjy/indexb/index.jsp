<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head id="Head1">
    <title>
        湖北省妇幼保健院住院医师规范化培训管理系统
    </title>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" >

    <link href="<s:url value='/jsp/inx/hbfybjy/indexb/css/Default.css'/>" type="text/css" rel="stylesheet">
    <link href="<s:url value='/jsp/inx/hbfybjy/indexb/css/StyleSheet1.css'/>" rel="stylesheet" type="text/css">
    <script>
    $(function(){//获取当前星期几
    var weekDate = '星期'+'日一二三四五六'.charAt(new Date().getDay());
    $("#weekDateSpan").html(weekDate);
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
<body style="background: #fff;">
<div class="main_container">
    <%--<div>--%>
        <%--<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE"--%>
               <%--value="/wEPDwUKLTg3MTQ3NDQ2OA9kFgICAw9kFgQCBQ8QZA8WCGYCAQICAgMCBAIFAgYCBxYIEAUM5L2P6Zmi5Yy75biIBQEyZxAFDOW4puaVmeiAgeW4iAUBNmcQBQnnp5HkuLvku7sFAjE0ZxAFD+ezu+e7n+euoeeQhuWRmAUCMzBnEAUP5Z+65Zyw566h55CG5ZGYBQIzMmcQBQ/ljLvpmaLnrqHnkIblkZgFAjMzZxAFDOaVmeWtpuenmOS5pgUCMzZnEAUG6Zmi57O7BQIzOGcWAWZkAgsPFgIeC18hSXRlbUNvdW50Av////8PZGTYXo+5F7oSj+bDDTyTpZKUCRo5uA==">--%>
    <%--</div>--%>
    <div class="header">
	  <span>
	  <div class="fl">${pdfn:getCurrDate()}<font id="weekDateSpan"></font></div>
      </span>
    </div>
   <!-- </div>-->
    <div class="banner_box">
       <div class="lclz">
            <img src="<s:url value='/jsp/inx/hbfybjy/indexb/image/banner_title.png'/>"></div>
        <div class="banner">
            <img src="<s:url value='/jsp/inx/hbfybjy/indexb/image/1234.png'/>"></div>
        
        <div class="weixin_bg">
            <div class="weixin">
                <form id="loginForm" action="<s:url value='/inx/hbfybjy/login'/>" method="post">
                <span class="chname">用户登录</span>
                <div class="lc_login">
                    <img src="<s:url value='/jsp/inx/hbfybjy/indexb/image/name.png'/>">
                    <input class="validate[required]" type="text" id="userCode" name="userCode" placeholder="用户名/手机号" >
                </div>
                <div class="mima">
                    <img src="<s:url value='/jsp/inx/hbfybjy/indexb/image/password.png'/>">
                    <input class="validate[required]" type="password" id="txtPassWord" placeholder="密码" name="userPasswd" />
                </div>
                <div class="yanzm">
                      <div class="yan fl">
                         <input class="fl validate[required]" id="verifyCode" name="verifyCode" placeholder="验证码" style="width: 88px;height: 28px;">
                      </div>
                      <img id="verifyImage" src="<s:url value='/captcha'/>" style="cursor:pointer;width: 128px;height: 50px;" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" />
                 </div>

                    <span id="lblError">
                        <c:if test="${not empty loginErrorMessage}">
                            登录失败：${loginErrorMessage}
                        </c:if>
                    </span>
                <div class="denglux">
                    <input type="submit" value="登　录" onclick="checkForm();" style="margin-top:25px;">
                </div>
                </form>
            </div>
        </div>
    </div>
    <div class="new_box">
        <div class="new">
        <span class="fl xitong" >通知公告</span>
        <c:forEach items="${infos}" var="info">
           <div class="fl gongao">
        	<span>●</span>
        	<a class="notice_title" href="<s:url value='/inx/hbres/noticeview'/>?infoFlow=${info.infoFlow}" target="_blank">${info.infoTitle} ${pdfn:escapeHtmlTag(info.infoContent,'20') }</a>
     	</div>
        </c:forEach>
        <a href="<s:url value='/inx/hbres/noticelist'/>" target="_blank" class="fr more">查看更多</a>
        </div>
    </div>
    <div class="footer_box">
        <span class="footer db">技术支持：南京品德网络信息技术有限公司　服务电话：020-38039876&nbsp;&nbsp;
            <c:if test="${not empty applicationScope.sysCfgMap['chrome_download_url']}">
                <a target="_blank" style="color:black; font-size:14px;" href="${applicationScope.sysCfgMap['chrome_download_url']}">专用浏览器下载</a>
            </c:if>
        </span>
    </div>
</div>
</body>
</html>