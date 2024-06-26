<%@include file="/jsp/common/doctype.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>江苏省卫计委科研管理平台</title>
<c:set var="min" value=".min"/>
	<script type="text/javascript" src="<s:url value='/js/jquery-1.8.3${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="jbox" value="true" />
	<jsp:param name="jquery_form" value="false" />
	<jsp:param name="jquery_validation" value="true" />
	<jsp:param name="jquery_placeholder" value="true" />
</jsp:include>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/inx/jssrm/css/font.css'/>" />
<style>
#player .Nubbt{position:absolute;z-index:9;right:5px;bottom:38px; line-height:20px;}
#player .Nubbt span{ float:left; border:1px solid #999999;background:#121210;padding:1px 5px;margin:0 2px; font-style:normal;cursor:pointer; color:#FFFFFF;}
#player .Nubbt span.on{background:#CC0000;color:#FFFFFF;}
</style>
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

function register(){
	//var url = "<s:url value='/inx/jssrm/register'/>";
	var url = "<s:url value='/reg/srm/go'/>";
	window.location.href=url;
}

function logout(){
	window.location.href="<s:url value='/inx/jssrm/logout'/>";
}

$(document).ready(function(){
	$("html,body").animate({scrollTop:$("#navUl").offset().top},100);
	jboxLoad("newsDiv","<s:url value='/inx/jssrm/queryData?columnId=LM03&jsp=inx/jssrm/index_news&endIndex=4&isWithBlobs=Y'/>", true);
	//jboxLoad("noticeDiv","<s:url value='/inx/jssrm/queryData?columnId=LM03&jsp=inx/jssrm/index_notice&endIndex=8'/>", true);
	//jboxLoad("policyDiv","<s:url value='/inx/jssrm/queryData?columnId=LM04&jsp=inx/jssrm/index_notice&endIndex=8'/>", true);
	jboxLoad("download","<s:url value='/inx/jssrm/queryData?columnId=LM02&jsp=inx/jssrm/index_download&endIndex=5'/>", true);
	//jboxLoad("player","<s:url value='/inx/jssrm/queryImgNews?jsp=inx/jssrm/index_imgNews&endIndex=7'/>", true);
});
</script>

</head>


<body>
	<%@include file="header.jsp"%>

	<div id="banner">
		<div class="banner">
			<div class="login">
			<c:choose>
			 	<c:when test="${empty sessionScope.currUser}">
				<form id="loginForm" action="<s:url value='/inx/jssrm/login'/>" method="post">
					<%--<input type="hidden" name="successLoginPage" value="inx/jssrm/index" /> --%>
					<input type="hidden" name="errorLoginPage" value="inx/jssrm/index" />
					<table cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td width="50" height="55"><img src="<s:url value='/'/>jsp/inx/jssrm/images/user.png"/></td>
							<td><input type="text" name="userCode" value="" class="validate[required] user" placeholder="用户名" /></td>
						</tr>
						<tr>
							<td height="55"><img src="<s:url value='/'/>jsp/inx/jssrm/images/password.png"/></td>
							<td><input type="password" name="userPasswd" class="validate[required] user" placeholder="密码" /></td>
						</tr>
						<tr>
							<td height="55"><img src="<s:url value='/'/>jsp/inx/jssrm/images/verifyCode.png"/></td>
							<td>
								<div style="height: 40px;width: 245px;padding-left: 0;background-color: #ffffff">
								<input name="verifyCode" type="text" class="validate[required] yzm" placeholder="验证码" />
								<img id="verifyImage" src="<s:url value='/captcha'/>" style="cursor: pointer;position: relative;right: -160px;top:-45px" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" />
									</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" height="30">
								<c:if test="${not empty loginErrorMessage}">
									&nbsp;<font color="red">${loginErrorMessage}</font>
								</c:if>
								&nbsp;<a href="<s:url value='/reg/forget/first'/>">忘记密码？</a>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="submit" onclick="return checkForm()" value="登&#12288;录" class="butt" />
								<input type="button" onclick="register()" value="注&#12288;册" class="submit" />
							</td>
						</tr>
					</table>
				</form>
				</c:when>
				<c:otherwise>
				<div style="text-align: center; margin-top:60px;">
					<span style="font-size:18px; color:#f00;line-height:30px;">${sessionScope.currUser.userName}</span><br/>
					<span style="font-size:18px;">您好，欢迎登录本系统!</span><br/><br/>
					<input type="button" onclick="logout()" value="退&#12288;出" class="butt" />
				</div>
				</c:otherwise>
			</c:choose>
			</div>
		</div>
	</div>
	
	<div id="news">
		<h1>
			<img src="<s:url value='/'/>jsp/inx/jssrm/images/notice.png"/>
		</h1>
		<div class="news">
			<div class="anno_box fl" id="newsDiv">
				<!-- 新闻中心 -->
			</div>
			<!--  
			<div class="main" style="float: right;" id="player">
				<!--新闻图片->
			</div>
			-->
			<div class="more"><a href="<s:url value='/inx/jssrm/queryByColumnId?columnId=LM03'/>" class="more">查看更多+</a></div>
		</div>
	</div>
<!-- 
	<div id="notice">
		<div class="content">
			<div class="notice fl" id="noticeDiv">
				<!-- 通知公告 ->
			</div>
			<div class="line_center fl"></div>
			<div class="policy fr" id="policyDiv">
				<!-- 政策法规 ->
			</div>
		</div>
	</div>
 -->
	<div class="down">
		<div id="download">
			<!-- 下载中心 -->
		</div>
	</div>
	

	<%@include file="footer.jsp"%>
</body>
</html>
