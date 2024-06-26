<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
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
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/inx/njmures/css/login.css'/>" />
<script type="text/javascript" >
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
	$("#loginForm").submit();;
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
<form id="loginForm" action="<s:url value='/login'/>" method="post">
<input type="hidden" name="errorLoginPage" value="inx/njmures/index"/>
<div class="njmu_top_box">
	<div class="njmu_top">
    	<span class="njmu_riqi"><img src="<s:url value="/jsp/inx/njmures/images/njmu_logo.png"/>" /></span>
        <div class="code fr">
	     <div class="android" onmouseover="showQr('android');" onmouseout="hideThis('android')"></div>
	     <div class="iphone" onmouseover="showQr('iphone');" onmouseout="hideThis('iphone')"> </div>
	     <font class="fr">请扫描二维码下载</font>
	      <div class="QR_android QR" style="display: none;"><img class="QR_code" src="<s:url value='/jsp/inx/njmures/images/android.png'/>" /></div>
	      <div class="QR_iphone QR" style="display: none;"><img class="QR_code" src="<s:url value='/jsp/inx/njmures/images/iphone.png'/>" /></div>	     
	  </div>
    </div>
</div>
<div class="njmu_banner_box">
	<img class="njmu_banner" src="<s:url value="/jsp/inx/njmures/images/njmu_banner2.png"/>" />
     <div class="njmu_weixin_bg">
           <div class="njmu_weixin">
                 <a class="njmu_db njmu_login_title" >用户登录</a>
                 <div class="njmu_login">
                      <img src="<s:url value="/jsp/inx/njmures/images/njmu_login_pic1.png"/>" />
                      <input class="validate[required]" name="userCode" value="" placeholder="登录名/学号">
                 </div>
                 <div class="njmu_mima">
                      <img src="<s:url value="/jsp/inx/njmures/images/njmu_login_pic2.png"/>" />
                      <input type="text" id="placepwd" style="display: none;" placeholder="密码" value=""/>
                      <input class="validate[required]" id="userPasswd" name="userPasswd" type="password" value="" placeholder="密码">
                 </div>
                 <div class="njmu_yzm">
                      <input name="verifyCode" value="" placeholder="验证码" class="validate[required] yzm" style="width:120px;">
                      <img id="verifyImage" src="<s:url value='/captcha'/>" style="cursor:pointer;height: 40px;" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" />
                 </div>
                 <div class="njmu_jizhu">
                      <p class="fl red">
                      	<c:if test="${not empty loginErrorMessage}">
							登录失败：${loginErrorMessage}
						</c:if>
						</p>
                      <c:set var="forgetUrl" value="/sys/user/forget/first" />
					  <c:if test="${'userinfo' eq applicationScope.sysCfgMap['sys_forget_password_type'] }">
						  <c:set var="forgetUrl" value="/reg/forget/first" />
					  </c:if>
					  <c:if test="${'phone' eq applicationScope.sysCfgMap['sys_forget_password_type'] }">
						  <c:set var="forgetUrl" value="/sys/user/forget/phoneFirst" />
					  </c:if>
                      <a href="<s:url value='${forgetUrl }'/>" class="fr njmu_dengluu">忘记密码?</a>
                 </div>
                 <input type="submit" class="njmu_denglu njmu_db" onclick="checkForm();" value="登&#12288;录" />
           </div>
      </div>
</div>
    
<div class="njmu_new_box">
	<div class="njmu_new">
    	<span class="fl njmu_xitong">系统公告</span>
    	<c:forEach items="${infos}" var="info">
        <div class="fl njmu_gongao">
        	<span>●</span>
        	<a class="notice_title" href="<s:url value='/inx/njmures/noticeView'/>?infoFlow=${info.infoFlow}" target="_blank">${info.infoTitle}
	        <c:if test="${pdfn:signDaysBetweenTowDate(pdfn:getCurrDate(),pdfn:transDate(info.createTime))<=7}">
	     	<img src="<s:url value='/jsp/inx/njmures/images/njmu_new.png'/>"/>
	     	</c:if>
	     	</a>
     	</div>
        </c:forEach>
        <a href="<s:url value='/inx/njmures/noticelist'/>" target="_blank" class="fr njmu_more">查看更多>></a>
    </div>
</div>

<div class="njmu_footer_box">
	<a class="njmu_footer njmu_db" href="http://www.njpdxx.com">技术支持：南京品德网络信息技术有限公司</a>
</div>

</form>
</body>
</html>
