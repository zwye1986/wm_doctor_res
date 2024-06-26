<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>南京中医院专硕登录系统</title>
<link href="<s:url value='/jsp/inx/nzyres/style.css'/>" rel="stylesheet" type="text/css">
<script>
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
</script>
</head>

<body>
<form id="loginForm" action="<s:url value='/login'/>" method="post">
<input type="hidden" name="errorLoginPage" value="inx/nzyres/login"/>
<div class="top_box">
	<div class="nzy_top">
    	<span class="fl nzy_logo"><img src="<s:url value='/jsp/inx/nzyres/images/logo.png'/>"></span>
		<span class="fr top_text"></span>
    </div>
</div>

<div class="banner_box">
	<img class="banner" src="<s:url value='/jsp/inx/nzyres/images/banner2.png'/>">
    <img class="people" src="<s:url value='/jsp/inx/nzyres/images/banner_people.png'/>">
    <img class="banner_title" src="<s:url value='/jsp/inx/nzyres/images/banner_title.png'/>">
     <div class="login_bg">
           <div class="login_box">
           		<span class="login_title">用户登录</span>
                 <div class="username">
                      <img src="<s:url value='/jsp/inx/nzyres/images/login_pic1.png'/>">
                       <input id="userCode" class="validate[required]" name="userCode" value="" placeholder="用户名"/>     
                 </div>
                 <div class="password">
                      <img src="<s:url value='/jsp/inx/nzyres/images/login_pic2.png'/>">
                       <input type="text" id="placepwd" style="display: none;" placeholder="密码" value=""/>
                      <input onpaste="return false" class="validate[required]" oncontextmenu="return false" oncopy="return false" oncut="return false" type="password" id="userPasswd" name="userPasswd" value="" placeholder="密码"/>
                 </div>
                 <div class="yanzm">
                      <input name="verifyCode" value="" placeholder="验证码" class="validate[required] yzm" style="width:100px;">
					  <img src="<s:url value='/captcha'/>" >
                 </div>              
                 <span class="cw"><c:if test="${not empty loginErrorMessage}">
							登录失败：${loginErrorMessage}
						</c:if></span>
                 <div class="nzy_denglu">
                     <input class="login1 fl" type="submit" onclick="return checkForm();" value="登&nbsp;&nbsp;&nbsp;录">
                 </div>
           </div>
      </div>
</div>
  
<div class="new_box">
	<div class="new">
    	<span class="fl xitong">系统公告</span>
    	<c:forEach items="${infos}" var="info">
        <div class="fl gongao">
        	<span>●</span>
        	<a class="notice_title" href="<s:url value='/inx/nzyres/noticeView'/>?infoFlow=${info.infoFlow}" target="_blank">${info.infoTitle}
	        <c:if test="${pdfn:signDaysBetweenTowDate(pdfn:getCurrDate(),pdfn:transDate(info.createTime))<=7}">
	     	<img src="<s:url value='/jsp/inx/nzyres/images/new.png'/>"/>
	     	</c:if>
	     	</a>
     	</div>
        </c:forEach>
        <a href="<s:url value='/inx/nzyres/noticelist'/>" target="_blank" class="fr more">查看更多</a>
    </div>
</div>

<div class="nzy_footer_box">
	<a class="nzy_footer db">技术支持：南京品德网络信息技术有限公司</a>
</div>
</form>
</body>
</html>
