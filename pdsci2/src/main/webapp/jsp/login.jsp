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
	<script type="text/javascript" src="<s:url value='/js/cryptojs/tripledes.js'/>"></script>
	<script type="text/javascript" src="<s:url value='/js/cryptojs/mode-ecb.js'/>"></script>
<style>
	.apk{ text-align:center; display:block; }
	.apk span.img{ display:none; z-index:100;}
	.apk a:hover{position:relative;}
	.apk a:hover span.img{display:block;position:absolute;top:-160px;left:-30px;}
</style>
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
	return true;
}
function encryptByDES(message, key) {
	var keyHex = CryptoJS.enc.Utf8.parse(key);
	var encrypted = CryptoJS.DES.encrypt(message, keyHex, {
		mode: CryptoJS.mode.ECB,
		padding: CryptoJS.pad.Pkcs7
	});
	return encrypted.toString();
}
function register(){
	location.href="<s:url value='/jsp/inx/nfykdx/register.jsp'/>";
}
</script>
</head>
<body style="background: #f4f4f4;">
<form name="loginForm" action="<s:url value='/login'/>" method="post">
	<div class="logo"></div>
	<div class="bg">
		<table width="100%" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td>&nbsp;</td>
				<td width="700"><table align="center" cellpadding="0"
						cellspacing="0" class="log">
						<tr>
							<td>
								<c:if test="${not empty applicationScope.sysCfgMap['sys_login_img']}">
								<img style="margin:-18px 0px 0px -45px;" src="<s:url value="/css/skin/${skinPath}/images/${applicationScope.sysCfgMap['sys_login_img']}_login.png"/>"/>
								</c:if>
								<c:if test="${empty applicationScope.sysCfgMap['sys_login_img']}">
								<img src="<s:url value="/css/skin/${skinPath}/images/all_login.png"/>"/>
								</c:if>
							</td>
							<td class="bg_shadown">
							</td>
							<td class="user"><table align="left" cellpadding="0"
									cellspacing="0">
									<tr>
										<td height="50">用户名：</td>
										<td colspan="2" align="left">
											<input name="userCode2" type="text" class="validate[required] logo_text" placeholder="用户名/手机号/Email" value=""/>
											<input type="hidden" name="userCode">
										</td>
									</tr>
									<tr>
										<td height="50">密&#12288;码：</td>
										<td colspan="2" align="left">
											<input name="userPasswd2" type="password" class="validate[required] logo_text" placeholder=""/>
											<input name="userPasswd" type="hidden" />
										</td>
									</tr>
									<tr>
										<td height="50">验证码：</td>
										<td colspan="2" align="left"><input name="verifyCode" type="text" class="validate[required] logo_text" placeholder=""/>
											<img id="verifyImage" src="<s:url value='/captcha'/>" style="cursor:pointer;margin-left: -85px;" height="45" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" /></td>
									</tr>
									<tr>
										<td height="50"></td>
										<td colspan="2" align="left" style="white-space:nowrap;">
											<input class="denglu" type="submit" value="登&#12288;录" onclick="return checkForm();"/>
											<c:if test="${applicationScope.sysCfgMap['xjgl_customer'] eq 'gzykdx'}">
												&#12288;
												<input class="denglu" type="submit" value="导师注册" onclick="register();"/>
											</c:if>
											<c:if test="${GlobalConstant.FLAG_N != applicationScope.sysCfgMap['sys_forget_password'] }">
											<c:set var="forgetUrl" value="/sys/user/forget/first" />
											<c:if test="${'userinfo' eq applicationScope.sysCfgMap['sys_forget_password_type'] }">
												<c:set var="forgetUrl" value="/reg/forget/first" />
											</c:if>
											<c:if test="${'phone' eq applicationScope.sysCfgMap['sys_forget_password_type'] }">
												<c:set var="forgetUrl" value="/sys/user/forget/phoneFirst" />
											</c:if>
											&#12288;&#12288;<a href="<s:url value='${forgetUrl }'/>">忘记密码?</a>
											</c:if>
										</td>
									</tr>
									<tr>
										<td colspan="3">
											<c:if test="${not empty loginErrorMessage}">
										       	<span style="color:red;">
													登录失败：${loginErrorMessage}
												</span>
											</c:if>
										</td>
									</tr>
								</table></td>
						</tr>
						<tr>
							<td colspan="3" class="footer">
								<c:if test="${not empty applicationScope.sysCfgMap['chrome_download_url']}">
									<a href="${applicationScope.sysCfgMap['chrome_download_url']}">
										<font color="red" style="font-size:14px;">请点击下载专用浏览器访问系统，以获得最佳访问速度和用户体验</font>
									</a>
								</c:if>
								<br />
								<br />
								<c:if test="${applicationScope.sysCfgMap['res_app_cfg'] == 'Y'}">
									<div class="apk">
										<a href="javascript:void(0);" style="color:#666;">APP客户端下载 <span class="img" style="z-index: 600;"><img height="150" width="150" style="width: 150px;height:150px;" src="${applicationScope.sysCfgMap['res_app_cfg_url']}"></span></a>
									</div>
									Copyright © 2001- 2018 <a href="http://www.njpdxx.com" target="_blank" style="color:#757575;">南京品德网络信息技术有限公司</a> All rights reserved. V${applicationScope.sysCfgMap['sys_version']}
								</c:if>
								<c:if test="${applicationScope.sysCfgMap['res_app_cfg'] != 'Y'}">
									技术支持：<a href="http://www.njpdxx.com" target="_blank" style="color:#757575;">南京品德网络信息技术有限公司</a><br />Copyright
									© 2001- 2017 Nanjing Character Network Information Technology Co.,Ltd. All rights reserved. V${applicationScope.sysCfgMap['sys_version']}
								</c:if>
							</td>
						</tr>
					</table></td>
				<td>&nbsp;</td>
			</tr>
		</table>
	</div>
</form>
</body>
</html>
