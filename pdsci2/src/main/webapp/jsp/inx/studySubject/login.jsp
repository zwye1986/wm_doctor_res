
  <link rel="stylesheet" href="<s:url value='/jsp/inx/studySubject/login.css'/>">
  <script src="<s:url value='/jsp/inx/yunZhuPei/js/jquery-1.10.2.min.js'/>"></script>
  <script src="<s:url value='/jsp/inx/yunZhuPei/js/common.js'/>"></script>
  <script type="text/javascript" src="<s:url value='/js/cryptojs/tripledes.js'/>"></script>
  <script type="text/javascript" src="<s:url value='/js/cryptojs/mode-ecb.js'/>"></script>
  <%@ include file="/jsp/token.jsp" %>
  <jsp:include page="/jsp/common/htmlhead.jsp">
    <jsp:param name="basic" value="false"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="jquery_form" value="false"/>
    <jsp:param name="jquery_ui_tooltip" value="false"/>
    <jsp:param name="jquery_ui_combobox" value="false"/>
    <jsp:param name="jquery_ui_sortable" value="false"/>
    <jsp:param name="jquery_cxselect" value="false"/>
    <jsp:param name="jquery_scrollTo" value="false"/>
    <jsp:param name="jquery_jcallout" value="false"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="false"/>
    <jsp:param name="jquery_fullcalendar" value="false"/>
    <jsp:param name="jquery_fngantt" value="false"/>
    <jsp:param name="jquery_fixedtableheader" value="false"/>
    <jsp:param name="jquery_placeholder" value="true"/>
    <jsp:param name="jquery_iealert" value="false"/>
  </jsp:include>
  <head>
  <title>公共科目学习平台</title>
  <script type="text/javascript">
    function reloadVerifyCode()
    {
      $("#verifyImage").attr("src","<s:url value='/captcha'/>?random="+Math.random());
    }
    function checkForm(){
      if(false==$("#loginForm").validationEngine("validate")){
        return false;
      }
      var userCode = encryptByDES($("input[name='userCode2']").val(),"${csrftoken}");
      var userPasswd = encryptByDES($("input[name='userPasswd2']").val(),"${csrftoken}");
      $("input[name='userCode']").val(userCode);
      $("input[name='userPasswd']").val(userPasswd);
      $("#loginForm").submit();
    }
    function encryptByDES(message, key) {
      var keyHex = CryptoJS.enc.Utf8.parse(key);
      var encrypted = CryptoJS.DES.encrypt(message, keyHex, {
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.Pkcs7
      });
      return encrypted.toString();
    }
  </script>
</head>
<body>
<form id="loginForm" action="<s:url value='/inx/studySubject/login'/>" method="post">
  <div class="login-bg">
    <div class="login-title">苏州市住院医师公共科目学习平台</div>
    <div class="login-box">
        <div class="box-top">
          <span>欢迎登录</span>
        </div>

        <div class="box-body">
            <div class="input-box">
              <span class="iconbg1"></span>
              <input name="userCode2" type="text" class="validate[required]" placeholder="用户名/手机号" value=""/>
              <input type="hidden" name="userCode">
            </div>
            <div class="input-box">
              <span class="iconbg2"></span>
              <input name="userPasswd2" type="password" class="validate[required]"  placeholder="密码"/>
              <input name="userPasswd" type="hidden" />
            </div>
            <div class="input-box input-yzmBox">
              <span class="iconbg3"></span>
              <input name="verifyCode" type="text" class="validate[required]" placeholder="验证码"/>
              <span class="yzmLink"><img id="verifyImage" class="yzm-img" src="<s:url value='/captcha'/>" style="cursor:pointer" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" /></span>
            </div>
            <div class="input-info">
              <c:if test="${not empty loginErrorMessage}">
                <span>
                    登录失败：${loginErrorMessage}
                </span>
              </c:if>
            </div>
            <div class="input-btnBox">
              <%--<button type="button" class="input-btn">立即登录</button>--%>
              <button type="button" onclick="checkForm();"  class="input-btn">立即登录</button>
            </div>
        </div>

    </div>
    <div class="login-footer">主管单位：苏州市卫生和计划生育委员会 | 技术支持：南京品德科技有限责任公司</div>
  </div>
</form>
</body>