<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>中山大学孙逸仙纪念医院住院医师规范化培训管理平台</title>
<jsp:include page="htmlhead-zsey.jsp" flush="true">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
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

<%--function register(){--%>
	 <%--window.location.href="<s:url value='/inx/zsey/register'/>";--%>
<%--}--%>
function reloadVerifyCode()
{
	$("#verifyImage").attr("src","<s:url value='/captcha'/>?random="+Math.random());
}
function checkForm(){
	if($("#userCode").val()==""){
		$(".log_tip").html("用户名不能为空!");
		return false;
	}
	if($("#userPasswd").val()==""){
		$(".log_tip").html("密码不能为空!");
		return false;
	}
	if($("#verifyCode").val()==""){
		$(".log_tip").html("验证码不能为空!");
		return false;
	}
	return true;
}

function showQr(type){
    $(".QR").hide();
    $(".QR_"+type).show();
}
function hideThis(type){
    $(".QR_"+type).hide();
}
</script>
</head>
<body>
<div class="hb_CN">
    <div class="index_header">
      <div class="header_box">
        <h1 class="fl">${pdfn:getCurrDate() }&nbsp;&nbsp;<font id="weekDateSpan"></font></h1>
        <%--<h1 class="fl"><img width="300" src="<s:url value='/jsp/inx/zsey/images/logo_login.png'/>"></h1>--%>
        <h2 class="fr"><!-- <a>设为首页</a><a class="no_extra">加入收藏</a> --></h2>
          <div class="code fr">
              <%--<div class="android" onmouseover="showQr('android');" onmouseout="hideThis('android')"></div>--%>
              <div class="iphone" onmouseover="showQr('iphone');" onmouseout="hideThis('iphone')"></div>
              <%--<font class="fr">轮转手册登记请扫描二维码</font>--%>
              <%--<img class="Q_hand fr" src="<s:url value='/jsp/inx/zsey/images/Q_hand.png'/>" />--%>
              <div class="QR_iphone QR" style="display: none;"><img class="QR_code" width="123" height="123" src="<s:url value='/jsp/inx/zsey/images/download.png'/>" /></div>
              <%--<div class="QR_android QR" style="display: none;"><img class="QR_code" src="<s:url value='/jsp/inx/zsey/images/weixin_hbres.png'/>" /></div>--%>
          </div>
      </div>
    </div>
    <div class="content global">
        <div class="banner">
           <div class="banner_box">
             <div class="banner_logo"><img width="590" src="<s:url value='/jsp/inx/zsey/images/banner_logo.png'/>"></div>
                 <div class="mainlogin">
                    <form id="loginForm" action="<s:url value='/inx/zsey/login'/>" method="post">
                        <table class="logintb" border="0" cellpadding="0" cellspacing="0">
                              <tbody>
                                  <tr><td height="50px;" align="center">用户登录</td></tr>
                                  <tr>
                                    <td height="40px"><span class="username"><i></i><input type="text" class="loginsr" id="userCode" name="userCode" style="width:202px;" placeholder="用户名/手机号" value=""></span></td>
                                  </tr>
                                  <tr><td height="12px"></td></tr>
                                  <tr>
                                      <%--onpaste="return false" oncontextmenu="return false" oncopy="return false" oncut="return false"--%>
                                    <td height="40px"><span class="password"><i></i><input type="text" class="loginsr" id="placepwd" style="width:202px;display: none;" placeholder="密码" value=""/><input type="password" class="loginsr" id="userPasswd" style="width:202px;" placeholder="密码" name="userPasswd" value=""/></span></td>
                                  </tr>
                                  <tr><td height="12px"></td></tr>
                                  <tr>
                                    <td height="40px" class="logintb_td">
                                        <span class="hbVerifyCode">
                                            <div style="float: left;">
                                                <i></i><input type="text" style="width:130px;" class="loginsr_yz" id="verifyCode" name="verifyCode" placeholder="验证码" value="">
                                            </div>
                                            <div style="float: right;">
                                                <img id="verifyImage" src="<s:url value='/captcha'/>" style="cursor:pointer;margin-right: -40px;" height="40px" width="100px" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" />
                                            </div>
                                        </span>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td height="50px" class="log_tips">
                                    <font class="log_tip red fl">
                                    <c:if test="${not empty loginErrorMessage}">
                                                        登录失败：${loginErrorMessage}
                                    </c:if>
                                    </font>
                                    <%--<span class="fr"><a href="<s:url value='/inx/zsey/forgetpasswd'/>" target="_blank">忘记密码？</a></span></td>--%>
                                  </tr>
                                  <tr>
                                    <td valign="top">
                                      <button class="btn_login" type="submit" style="width:260px;" onclick="return checkForm();">登&nbsp;&nbsp;录</button>&nbsp;
                                      <%--<button class="btn_login" type="button" onclick="register();">立即注册</button>--%>
                                    </td>
                                  </tr>
                            </tbody>
                        </table>
                    </form>
              </div>
           </div>
        </div>
    </div>
    <div class="notice_login">
        <c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
            <jsp:include page="/jsp/service.jsp" flush="true"></jsp:include>
        </c:if>
       <h1 class="index_title">通知公告<p>Announcement</p></h1>
       <div class="notice_bd">
       <c:forEach items="${infos}" var="info">
       	   <dl>
            <dt><h1>${pdfn:split(pdfn:transDate(info.infoTime),'-')[2] }</h1>
            <h2>${pdfn:split(pdfn:transDate(info.infoTime),'-')[0] }.${pdfn:split(pdfn:transDate(info.infoTime),'-')[1] }</h2></dt>
            <dd>
              <h1><a href="<s:url value='/inx/zsey/noticeview'/>?infoFlow=${info.infoFlow}" target="_blank">${info.infoTitle}</a></h1>
              <h2>${pdfn:escapeHtmlTag(info.infoContent,'50') }</h2>
            </dd>
          </dl>
       </c:forEach>
       </div>
       <div class="notice_more"><a href="<s:url value='/inx/zsey/noticelist'/>" target="_blank">更多</a></div>
    </div>
    <div class="index_footer" style="margin-top:40px;">
        <div align="center" style="font-size: 15px">
            <%--<img src="<s:url value='/jsp/jsres/images/count.png'/>" />&#12288;总访问量：<font color="red">${count}</font>&#12288;当前访问量：<font color="red">${pdfn:getOnlineUserCount()}</font><br/><br/>--%>
            主管单位：中山大学孙逸仙纪念医院科教处   |  技术支持：南京品德网络信息技术有限公司
        </div>
    </div>
  </div>
</body>
</html>