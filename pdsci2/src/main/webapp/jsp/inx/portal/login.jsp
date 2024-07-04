<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>湖北省住院医师规范化培训公众服务平台</title>
    <jsp:include page="/jsp/inx/portal/htmlhead_portal.jsp" flush="true">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="bootstrap" value="true"/>
    </jsp:include>
</head>
<body>
  <div class="login-bg">
    <div class="login-box">
        <form id="loginForm" action="<s:url value='/login'/>" method="post">
          <div class="login-box2">
            <div class="login-logo">
              门户网站管理平台
            </div>
              <input type="text" style="display: none;" name="errorLoginPage" value="inx/portal/login"/>
            <div class="gwlogin-box">
                <span class="spanbg1"></span>
              <input type="text" class="gwloginInput1" name="userCode" placeholder="用户名／手机号">
            </div>
            <div class="gwlogin-box">
                <span class="spanbg2"></span>
              <input type="password" class="gwloginInput2"  name="userPasswd" placeholder="密码">
            </div>
            <div class="gwlogin-box  yzm-box">
                <span class="spanbg3"></span>
              <input type="text" class="gwloginInput3"  name="verifyCode" placeholder="验证码">
              <a href="javascript:reloadVerifyCode();" class="yzm1"><img src="<s:url value='/captcha'/>"></a>
            </div>
            <div class="gwforget-info clearfix">
              <span class="point-info">
                  <c:if test="${not empty loginErrorMessage}">
                      登录失败：${loginErrorMessage}
                  </c:if>
              </span>
            </div>
            <div class="gwlogin-box">
              <button type="submit" class="gwlogin-btn">登录</button>
            </div>
          </div>
        </form>
    </div>
    <div class="footer-box">
      <div class="footer1">
        <span>主管单位：</span>
        <span class="footer1-spanbd">湖北省卫生和计划生育委员会教科处</span>
        <span></span>
        <span>技术支持：</span>
        <span>南京品德网络信息技术有限公司</span>
      </div>
    </div>
  </div>
</body>
</html>