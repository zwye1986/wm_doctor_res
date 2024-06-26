<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="<s:url value='/jsp/inx/yunZhuPei/css/common.css'/>">
  <script src="<s:url value='/jsp/inx/yunZhuPei/js/jquery-1.10.2.min.js'/>"></script>
  <script src="<s:url value='/jsp/inx/yunZhuPei/js/common.js'/>"></script>
  <script type="text/javascript" src="<s:url value='/js/cryptojs/tripledes.js'/>"></script>
  <script type="text/javascript" src="<s:url value='/js/cryptojs/mode-ecb.js'/>"></script>
  <%@ include file="/jsp/token.jsp" %>
  <title>品德云住培</title>
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
  <script type="text/javascript" >
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
  <style>
    .apk{ text-align:center; display:block; }
    .apk span.img{ display:none; z-index:100;}
    .apk a:hover{position:relative;}
    .apk a:hover span.img{display:block;position:absolute;top:-160px;left:-30px;}
  </style>
</head>
<body>
  <div class="top">
    <div class="logo-box clearfix">
      <img src="<s:url value='/jsp/inx/yunZhuPei/images/logo.png'/>">
      <%--<span>上海交通大学医学院附属瑞金医院</span>--%>
    </div>
    <%--<div class="logo-box clearfix">--%>
      <%--<img src="<s:url value='/jsp/inx/yunZhuPei/images/logo2.png'/>">--%>
      <%--<span>上海市瑞金红十字医院</span>--%>
    <%--</div>--%>
    
  </div>
  <div class="middle">
    <div class="middle-box clearfix">
      <div class="left-box">
        <%--<div class="left-box-sub">--%>
          <%--<div>专科医师规范化培训管理平台</div>--%>
          <%--<div>Standardized training management platform for specialists</div>--%>
        <%--</div>--%>
      </div>
      <form id="loginForm" action="<s:url value='/inx/yunZhuPei/login'/>" method="post">
      <div class="login-box">
        <div class="login-title">用户登录</div>
        <div class="input-box">
          <img src="<s:url value='/jsp/inx/yunZhuPei/images/icon-user.png'/>">
          <input name="userCode2" type="text" class="validate[required]" placeholder="用户名/手机号/Email" value=""/>
          <input type="hidden" name="userCode">
        </div>
        <div class="input-box">
          <img src="<s:url value='/jsp/inx/yunZhuPei/images/icon-pwd.png'/>">
          <input name="userPasswd2" type="password" class="validate[required]" placeholder=""/>
          <input name="userPasswd" type="hidden" />
        </div>
        <div class="input-box input-yzm">
          <img src="<s:url value='/jsp/inx/yunZhuPei/images/icon-yzm.png'/>">
          <input name="verifyCode" type="text" class="validate[required]" placeholder=""/>
          <img id="verifyImage" class="yzm-img" src="<s:url value='/captcha'/>" style="cursor:pointer" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" /></td>
        </div>
        <div class="info">
          <c:if test="${not empty loginErrorMessage}">
              <span>
                  登录失败：${loginErrorMessage}
              </span>
          </c:if>
        </div>
        <div class="input-box has-margin">
          <button type="button" onclick="checkForm();" style="cursor: pointer;">登&emsp;录</button>
        </div>
      </div>
      </form>
    </div>
  </div>
  <div class="footer">
    <c:if test="${applicationScope.sysCfgMap['res_app_cfg'] == 'Y'}">
      <div class="apk">
        <a href="javascript:void(0);" style="color:#666;">APP客户端下载 <span class="img" style="z-index: 600;"><img height="150" width="150" style="width: 150px;height:150px;" src="${applicationScope.sysCfgMap['res_app_cfg_url']}"></span></a>
      </div>
    </c:if>
    <div>技术支持：南京品德网络信息技术有限公司</div>
    <div>Copyright © 2001-${pdfn:getCurrYear()} Nanjing Character Network Information Technology Co.,Ltd. All rights reserved.</div>
  </div>
</body>
</html>