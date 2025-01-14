<%@ page import="com.pinde.sci.util.JsresUtil" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<link href="<s:url value='/jsp/inx/jsres/orgLogin/style4.css'/>?v=${applicationScope.sysCfgMap['sys_version']}" rel="stylesheet" type="text/css">
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
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

		function register(){
			window.location.href="<s:url value='/jsp/inx/jsres/register.jsp'/>";
		}
		function reloadVerifyCode()
		{
			$("#verifyImage").attr("src","<s:url value='/captcha'/>?random="+Math.random());
		}
		function checkForm(){
			if($("#userCode").val()==""){
				$(".log_tips").html("用户名不能为空!");
				return false;
			}
			if($("#userPasswd").val()==""){
				$(".log_tips").html("密码不能为空!");
				return false;
			}
			if($("#verifyCode").val()==""){
				$(".log_tips").html("验证码不能为空!");
				return false;
			}
			var f=checkUser();
			if(f){
				return true;
			}else {
				return false;
			}
		}
		function checkUser()
		{
			var userCode = $("#userCode").val();
			var url = "<s:url value='/inx/jsres/checkUserCodeInBlack'/>";
			var data = {userCode:userCode};
			jboxPost(url,data,
					function(resp){
						if(resp != "" && resp != null && typeof(resp) != 'undefined'){
							var height=(window.screen.height)*0.3;
							var width=(window.screen.width)*0.5;
							jboxOpenContent(resp,"提示信息",width,height,true);
							return false;
						}else {
							$("#loginForm").submit();
							return true;
						}
					}, null, false);
		}

		function showQr(type){
			$(".QR").hide();
			$(".QR_"+type).show();
		}
		function hideThis(type){
			$(".QR_"+type).hide();
		}
	</script>
	<style>
		.fr{float:right;}
		/*二维码*/
		.code{ width:600px;height:220px;}
		.code font{font-weight:normal; display:inline; font-size:15px; margin-left:5px;line-height: 60px;
			text-align: left;}
		.code .Q_hand{ vertical-align:bottom;margin-top: 10px;}
		.code .android{ width:162px; height:40px; margin-left:15px; background:url(<s:url value='/jsp/jsres/images/wechat_w.png'/>) no-repeat; float:right; display:inline-block;  margin-top: 10px;}
		.code .iphone{ width:162px; height:40px; margin-left:15px; background:url(<s:url value='/jsp/jsres/images/download_w.png'/>) no-repeat; float:right; display:inline-block; margin-top: 10px;}
		.code .android:hover{ width:162px; height:40px; margin-left:15px; background:url(<s:url value='/jsp/jsres/images/wechat_b.png'/>) no-repeat; float:right; display:inline-block;  margin-top: 10px; cursor:pointer;}
		.code .iphone:hover{ width:162px; height:40px; margin-left:15px; background:url(<s:url value='/jsp/jsres/images/download_b.png'/>) no-repeat; float:right; display:inline-block; margin-top: 10px; cursor:pointer;}
		.code .QR_android,.code .QR_iphone{width:162px; height:166px;background:url(<s:url value='/jsp/jsres/images/Q_kuang.png'/>) no-repeat; }
		.code .QR_android{position: absolute;  margin-left: 438px; margin-top:52px;}
		.code .QR_iphone{position: absolute; margin-left: 260px; margin-top:52px;}
		.code .QR_code{ padding-top: 25px; padding-left: 18px;}
	</style>
</head>
<body>
<div class="top_box">
	<div class="top">
		<span class="fl">${pdfn:getCurrDate() }&nbsp;&nbsp;<font id="weekDateSpan"></font></span>

			<div class="code fr">
				<div class="android" onmouseover="showQr('android');" onmouseout="hideThis('android')"></div>
				<div class="iphone" onmouseover="showQr('iphone');" onmouseout="hideThis('iphone')"></div>
				<font class="fr">轮转手册登记请扫描二维码</font>
				<img class="Q_hand fr" src="<s:url value='/jsp/jsres/images/Q_hand.png'/>" />
				<div class="QR_android QR" style="display: none;"><img class="QR_code" src="<s:url value='/jsp/jsres/images/jsQrcode.jpg'/>" /></div>
				<div class="QR_iphone QR" style="display: none;"><img class="QR_code" src="<s:url value='/jsp/jsres/images/jsapp.jpg'/>" /></div>
			</div>
	</div>
</div>

<div class="banner_box">
	<img class="banner" src="<s:url value='/jsp/inx/jsres/orgLogin/images/banner2.png'/>">
	<a class="banner_text"><img src="<%=JsresUtil.getBannerUrl(request,response,application)%>"></a>
	<div class="weixin_bg">
		<form id="loginForm" action="<s:url value='/inx/jsres/login'/>" method="post">
			<div class="weixin">
				<a class="chname">用户登录</a>
				<a class="enname"> User Login</a>
				<div class="login">
					<img src="<s:url value='/jsp/inx/jsres/orgLogin/images/login_pic1.png'/>">
					<input id="userCode" name="userCode" class="text" placeholder="用户名" value="${param.userCode }">
				</div>
				<div class="mima">
					<img src="<s:url value='/jsp/inx/jsres/orgLogin/images/login_pic2.png'/>">
					<input type="text" id="placepwd" class="text" style="display: none;" placeholder="密&nbsp;&nbsp;码" value=""/>
					<input type="password" id="userPasswd" name="userPasswd" class="text" value="" placeholder="密&nbsp;&nbsp;码"/>
				</div>
				<div class="yanzm">
					<input id="verifyCode" name="verifyCode" type="text" class="fl" value="" placeholder="验&nbsp;证&nbsp;码" />
					<img id="verifyImage" src="<s:url value='/captcha'/>" class="fr" style="cursor:pointer;height: 40px;" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" />
				</div>
				<a class="cw">
					<c:if test="${not empty loginErrorMessage}">
						登录失败：${loginErrorMessage}
					</c:if>
					<c:if test="${empty loginErrorMessage}">&#12288;</c:if>
				</a>
				<div class="denglu">
					<c:if test="${empty sysCfgMap['jsres_is_register'] or sysCfgMap['jsres_is_register'] eq 'Y' }">
						<input type="submit" onclick="return checkForm();" class="login1 fl" style="width: 120px;line-height: 38px;height: 38px;text-align: center; font-size: 16px;  color: #FFF;font-family: 微软雅黑;" value="登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录" />
						<input type="button" onclick="register();" class="login2 fr" style="width: 120px;line-height: 38px;height: 38px;text-align: center; font-size: 16px;  color: #FFF;font-family: "微软雅黑";" value="注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;册" />
					</c:if>
					<c:if test="${sysCfgMap['jsres_is_register'] eq 'N' }">
						<input type="submit" onclick="return checkForm();" class="login1 fl" style="width: 120px;line-height: 38px;height: 38px;text-align: center; font-size: 16px;  color: #FFF;font-family: 微软雅黑;" style="width:300px; " value="登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录" />
					</c:if>
				</div>
			</div>
		</form>
	</div>
</div>

<div class="footer_box">
	<a class="footer db">版权所有：南京品德网络信息技术有限公司   技术支持：025-69815356 69815357</a>
</div>

</body>
</html>
