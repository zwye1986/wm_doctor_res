<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>成都中医药大学附属医院住院医师规范化培训管理系统</title>
<c:set var="min" value=".min"/>
	<script type="text/javascript" src="<s:url value='/js/jquery-1.8.3${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="jbox" value="true" />
	<jsp:param name="jquery_form" value="false" />
	<jsp:param name="jquery_validation" value="true" />
	<jsp:param name="jquery_placeholder" value="true" />
</jsp:include>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/inx/cdedu/css/index_font.css?t=1'/>" />
<script>
function reloadVerifyCode(){
	$("#verifyImage").attr("src","<s:url value='/captcha'/>?random="+Math.random());
}

function checkForm(){
	if(false==$("#loginForm").validationEngine("validate")){
		return false;
	}
	$("#loginForm").submit();
}

function logout(){
	window.location.href="<s:url value='/inx/cdedu/logout'/>";
}

$(document).ready(function(){
	jboxLoad("noticeDiv","<s:url value='/inx/cdedu/queryData?columnId=LM03&jsp=inx/cdedu/index_notice&endIndex=2'/>", true);
});
</script>
</head>

<body>
<%@include file="header.jsp"%>
<div id="banner">
	<div class="banner">
    	<!--<div class="bird"></div>-->
		<img class="kid" src="<s:url value='/'/>jsp/inx/cdedu/images/kids.png"/>
		<img class="word" src="<s:url value='/'/>jsp/inx/cdedu/images/word.png"/>
		<img class="hehua" src="<s:url value='/'/>jsp/inx/cdedu/images/hehua.png"/>
        <div class="login fr">
        	<div class="con">
        	<c:choose>
		 	<c:when test="${empty sessionScope.currUser or (!empty sessionScope.currUser && !empty loginErrorMessage)}">
			<form id="loginForm" action="<s:url value='/inx/cdedu/login'/>" method="post">
				<input type="hidden" name="errorLoginPage" value="inx/cdedu/index" />
				<input type="hidden" name="flag" value="res" />
        		<div class="user">
					<img src="<s:url value='/jsp/inx/cdedu/images/user.png'/>">
        			<input type="text" name="userCode" value="" class="validate[required]" placeholder="用户名"/>
        		</div>
            	<div class="user" style="margin-top: 13px;">
					<img src="<s:url value='/jsp/inx/cdedu/images/suo.png'/>">
            		<input type="password" name="userPasswd" class="validate[required]" placeholder="密码" />
            	</div>
            	<div class="yanzm">
					<img src="<s:url value='/jsp/inx/cdedu/images/key.png'/>">
                    <input name="verifyCode" type="text" class="validate[required] fl" style="width:100px;" placeholder="验证码" />
					<img id="verifyImage" src="<s:url value='/captcha'/>" style="cursor: pointer; width:100px;height: 34px;padding-left: 30px;position: absolute;" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" />
                </div> 
                <span class="cw">
                	<c:if test="${not empty loginErrorMessage}">
						&nbsp;<font color="red">${loginErrorMessage}</font>
					</c:if>
					<%--&nbsp;<a href="<s:url value='/reg/forget/first'/>">忘记密码</a>--%>
                </span>
                <div class="denglu">
                     <input type="submit" class="login1 fl" value="登&nbsp;&nbsp;&nbsp;录" onclick="return checkForm();" style="margin-bottom: 10px;">
                     <!-- <input type="button" class="login2 fl" value="注&nbsp;&nbsp;&nbsp;册" onclick="register();"> -->
                </div>
            </form>
			</c:when>
			<c:otherwise>
				<div style="text-align: center; margin-top:60px;" class="denglu">
					<span style="font-size:18px; color:#f00;line-height:30px;">${sessionScope.currUser.userName}</span><br/>
					<span style="font-size:18px;">您好，欢迎登录本系统!</span><br/><br/>
					<input type="button" onclick="logout()" value="退&#12288;出" class="login2"/>
				</div>
			</c:otherwise>
			</c:choose>
            </div>   
        </div>
    </div>
</div>
<div class="new_box" id="noticeDiv">

</div>
<div class="apk">
	&nbsp;<a href="javascript:void(0);" title="手机浏览器扫一扫下载">APP客户端<span class="img" style="z-index: 600;"><img src="http://app.njpdxx.com/ios/res_sctcm120/appCode/code.png"></span></a>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
