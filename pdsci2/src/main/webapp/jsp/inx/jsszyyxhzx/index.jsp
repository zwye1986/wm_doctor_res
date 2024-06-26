
<%@include file="/jsp/common/doctype.jsp"%>
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
<script type="text/javascript" >
function reloadVerifyCode()
{
	$("#verifyImage").attr("src","<s:url value='/captcha'/>?random="+Math.random());
}
function checkForm(){
	if(false==$("#loginForm").validationEngine("validate")){
		return false;
	}
	return true;
}
</script>
<link type="text/css" rel="stylesheet" href="<s:url value='/jsp/inx/jsszyyxhzx/css/font.css'/>" />
</head>
<body style="background: #eef9ff;">
<form name="loginForm" action="<s:url value='/login'/>" method="post">
	<div class="loginbg">
		<div class="loginbar">
			<div class="mainlogin">
			  <div class="left">
				<table class="logintb" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td align="right" width="100px">用户名：</td>
						<td height="40px"><input type="text" class="validate[required] loginsr"
							style="width: 244px;" name="userCode" value="" placeholder="用户名/手机号/Email"/></td>
					</tr>
					<tr>
						<td height="20px" colspan="2"></td>
					</tr>
					<tr>
						<td align="right">密&nbsp;&nbsp;码：</td>
						<td height="40px"><input type="password" class="validate[required] loginsr"
							style="width: 244px;" name="userPasswd" value="" /></td>
					</tr>
					<tr>
						<td height="14px" colspan="2"></td>
					</tr>
					<tr>
						<td align="right" height="40px">验证码：</td>
						<td class="logintb_td"><input type="text"
							style="width: 110px;" class="validate[required] loginsr" name="verifyCode" value="" />
							<img id="verifyImage" src="<s:url value='/captcha'/>" style="cursor:pointer;" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" /></td>
					</tr>
					<tr>
						<td height="45px"></td>
						<td>
							<c:if test="${not empty loginErrorMessage}">
						       	<span style="color:red;">
									登录失败：${loginErrorMessage}
								</span>
							</c:if>							
					      	<input type="hidden" name="errorLoginPage" value="inx/jsszyyxhzx/index"/>
						</td>
					</tr>
					<tr>
						<td height="65px"></td>
						<td valign="top"><button class="btn_y" type="submit" onclick="return checkForm();">登&nbsp;&nbsp;录</button>&nbsp;&nbsp;&nbsp;
							<button class="btn_b" type="button"><a href="<s:url value='/reg/forget/first'/>">忘记密码？</a></button></td>
					</tr>
				</table>
			  </div>
			  <div class="left">
			    <div class="wechat">
                  <b>扫描并关<br/>注微信移动平台</b>
                </div>
			  </div>
			</div>
			<div class="leftlogin">
				<table class="lefttb" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td height="27px"></td>
					</tr>
					<tr>
						<td height="60px"><img
								src="<s:url value='/jsp/inx/jsszyyxhzx/images/suiji_1.png'/>" id="small"/></td>
					</tr>
					<tr>
						<td height="120px"><img src="<s:url value='/jsp/inx/jsszyyxhzx/images/linchuang.png'/>" id="large" /></td>
					</tr>
					<tr>
						<td height="105px"><img src="<s:url value='/jsp/inx/jsszyyxhzx/images/e-crf.png'/>" /></td>
					</tr>
					<tr>
						<td height="30px">
						<c:if test="${not empty applicationScope.sysCfgMap['chrome_download_url']}">
							<a href="${applicationScope.sysCfgMap['chrome_download_url']}">
								<font color="red" style="font-size:12px;">请点击下载专用浏览器访问系统，以获得最佳访问速度和用户体验</font>
							</a>
						</c:if>
						</td>
					</tr>
				
					<!--<tr>
						<td style="line-height:23px;">
						  <b>【企业号】</b> <br/><font color="#565656">供系统授权用户使用，方便移动客户端进行系统相关功能操作,需授权关注 </font><br/>
						  <b>【订阅号】</b><br/><font color="#565656">供大众使用，方便各类医疗资讯交互及医患交流互动</font>
						</td>
					</tr>  -->
				</table>
				<script type="text/javascript">
					function aa() {
						var imgSrc = [ "images/linchuang_1.png",
							"images/suiji_1.png"];
						document.getElementById("small").src = document
								.getElementById("small").src == imgSrc[0] ? imgSrc[1]
								: imgSrc[0];
					}
					function bb() {
						var imgSrc = [ "images/suiji.png",
							"images/linchuang.png"];
						document.getElementById("large").src = document
								.getElementById("large").src == imgSrc[0] ? imgSrc[1]
								: imgSrc[0];
					}
				</script>
			</div>
			<div class="loginlogo">
				<img src="<s:url value='/jsp/inx/jsszyyxhzx/images/loginlogo.png'/>">
			</div>	
			<div class="loginbottom">
			    <div class="system">
                 <ul id="list">
                   <li><i class="iphone"></i><a href="javascript:void(0);">iphone版 <span class="img"><img src="<s:url value='/jsp/inx/jsszyyxhzx/images/appcode.png'/>" /></span></a></li>
                   <li><i class="android"></i><a href="javascript:void(0);">Android版<span class="img"><img src="<s:url value='/jsp/inx/jsszyyxhzx/images/androidcode.png'/>" /></span></a></li>
                   <li><i class="web"></i><a href="javascript:void(0);">手机网页版 <span class="img"><img src="<s:url value='/jsp/inx/jsszyyxhzx/images/code.png'/>" /></span></a></li> 
                 </ul>
                </div>
				<%--技术支持：<a href="http://www.njpdkj.com" target="_blank" style="color:#757575;">南京品德网络信息技术有限公司</a><br /> --%>
				<%--Copyright © 2001- 2014 Character Technology, Inc. All rights reserved. V${applicationScope.sysCfgMap['sys_version']}--%>
			</div>
		</div>
	</div>
</form>
</body>
</html>