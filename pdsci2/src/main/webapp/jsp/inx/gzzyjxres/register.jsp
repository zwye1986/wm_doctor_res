<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/gzzyjxres/htmlhead-gzzyjxres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="register" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<script type="text/javascript">
var tip = {
		"idNo":"证件号不能为空或格式不正确",
		"password":"密码长度在6~16位之间，或使用了非法字符",
		"equals":"两次输入的密码不一致",
		"verifyCode":"验证码有误，请重新输入",
		"passwordEmpty":"密码不能为空"
};
function reloadVerifyCode(){
	$("#verifyImage").attr("src","<s:url value='/captcha'/>?random="+Math.random());
}
function checkPasswd(){
	var flag = false;
	var password = $(":password");
	password.each(function(){
		if(!(flag = $.trim(this.value) != "")){
			$("."+$(this).attr("name")+"Err").text(tip.passwordEmpty);
			return false;
		}else if(!(flag = this.value.length>=6)){
			$("."+$(this).attr("name")+"Err").text(tip.password);
			return false;
		}else if(!(flag = this.value.length<=16)){
			$("."+$(this).attr("name")+"Err").text(tip.password);
			return false;
		}else{
			$("."+$(this).attr("name")+"Err").text("");
		}
	});
	return flag?checkEquals(password):flag;
}
function checkEquals(password){
	if(password[0].value != "" && password[1].value != ""){
		var flag = password[0].value == password[1].value;
		$(".userPasswd2Err").text(flag?"":tip.equals);
		return flag;
	}else{
		return false;
	}
}
function checkMail(){
	var mail = $("#userEmail").val();
	var mailReg = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i;
	var flag = mailReg.test(mail) && $.trim(mail) != "";
	if(!flag){
		$(".userEmailErr").text(tip.email);
	}
	return flag;
}
function checkIdNo(){
	var mail = $("#idNo").val();
	var mailReg = /^(\d{18}|\d{15}|\d{17}[xX])$/i;
	var flag = mailReg.test(mail) && $.trim(mail) != "";
	if(!flag){
		$(".idNoErr").text(tip.idNo);
	}
	return flag;
}
function clearErrorInfo(){
	$(".idNoErr").html("");
	$(".userPasswdErr").html("");
	$(".userPasswd2Err").html("");
}
function register(){

	if(false==$("#registerForm").validationEngine("validate")){
		return false;
	}
	clearErrorInfo();
//	if(!checkIdNo()){
//		return;
//	}
	var url = "<s:url value='/sys/user/edit/checkEmailAndUserCodeAndIdNo'/>";
	jboxPost(url,{"idNo":$("#idNo").val()},
		function(resp){
			if(resp){
				jboxTip(resp);
			}else{
				if(checkPasswd()){
					jboxStartLoading();
					$("#registerForm").submit();
				}
			}
		},
		null,false);
}
</script>
</head>
<body>
<div class="yw">
    <div class="top" onclick="window.location.href='<s:url value='/inx/gzzyjxres'/>'" style="cursor: pointer;">${sysCfgMap['jx_top_name']}</div>
	<div class="content" style="text-align: center;">
		<div class="notPass wjpsw" >
			<div style="width: 800px;margin:0 auto;text-align: left; border:1px solid #DADADA; background: #F9F9F9;">
				<div style="margin-top:20px;">
					<h2 class="reg_title" style="float:left;">注册信息<br/>Registration Information</h2>
					<h2 class="line" style="font-weight:normal;float:right;text-align:right;padding-right:20px;">如已拥有帐户信息，则<a href="<s:url value='/inx/gzzyjxres'/>" class="line_c">在此登录</a><br/>
						If you have the account information,<a href="<s:url value='/inx/gzzyjxres'/>" class="line_c">click here to login</a>
					</h2>
				</div>
				<form id="registerForm" action="<s:url value='/inx/gzzyjxres/regist'/>" method="post" style="position: relative; margin:65px 30px;">
					  <table border="0" style="width:800px;line-height: 50px;border-spacing:15px;">
						  <tr>
							  <th class="" style="text-align: right;width: 100px;line-height:20px;"><font color="red">*</font>证件类型：<br/><font style="font-size:13px;">certificate type</font>
							  </th>
							  <td>
								  <select name="cretTypeId" id="cretTypeId"  class="validate[required] select" style="width: 292px;border: 1px solid #e7e7eb;height: 36px;">
									  <option value="">请选择</option>
									  <c:forEach items="${certificateTypesEnumList}" var="certificateType">
										  <option value="${certificateType.id}" ${certificateType.id eq cretTypeId?'selected':''}>${certificateType.name}</option>
									  </c:forEach>
								  </select>
							  </td>
						  </tr>
						  <tr>
							<th style="text-align: right;width: 100px;line-height:20px;"><font color="red">*</font>证件号码：<br/><font style="font-size:13px;">ID number</font></th>
							<td style="text-align: left;width: 500px;">
								<input type="text" name="idNo" id="idNo" placeholder="请填写有效证件号码" class="validate[required,<%--custom[chinaIdLoose]]--%> txt" style="width:290px;" value="" />
								<span style="color:red" class="idNoErr"></span>
							</td>
						  </tr>
						  <tr>
							<th style="text-align: right;line-height:20px;"><font color="red">*</font>密码：<br/><font style="font-size:13px;">password</font></th>
							<td style="text-align: left;">
								<input type="password" name="userPasswd"  placeholder="密码长度必须为6~16位之间"class="validate[required] txt" style="width:290px;"  onpaste="return false" oncontextmenu="return false" oncopy="return false" oncut="return false"/>
								<span style="color:red" class="userPasswdErr"></span>
							</td>
						  </tr>
						  <tr>
							<th style="text-align: right;line-height:20px;"><font color="red">*</font>确认密码：<br/><font style="font-size:13px;">confirm password</font></th>
							<td style="text-align: left;">
								<input type="password" name="userPasswd2" placeholder="与密码保持一致" class="validate[required] txt" style="width:290px;"  onpaste="return false" oncontextmenu="return false" oncopy="return false" oncut="return false"/>
								<span style="color:red" class="userPasswd2Err"></span>
							</td>
						  </tr>
						  <tr>
							<th style="text-align: right;line-height:20px;"><font color="red">*</font>验证码：<br/><font style="font-size:13px;">verification code</font></th>
							<td style="text-align: left;" >
								<input id="verifyCode"  name="verifyCode" type="text" class="validate[required] txt" style="width:150px;"/>
								<img id="verifyImage" src="<s:url value='/captcha'/>" style="cursor:pointer;vertical-align: middle;" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" />
								  <span style="color:red;" id="verifyCodeErr"></span>
							</td>
						  </tr>
					  </table>
					  <div style="text-align:left;margin:5px auto 10px 210px">
							<input type="button" value="注册" onclick="register();" id="registerButton" class="button  button-blue" />
							 <c:if test='${!empty errorMsg}'>
								<span style="color:red;">${errorMsg}</span>
							</c:if>
					  </div>
				  </form>
			</div>
		</div>
    </div>
</div>
<c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
	<jsp:include page="/jsp/service.jsp" flush="true"></jsp:include>
</c:if>
<div class="footer"><jsp:include page="/jsp/gzzyjxres/foot.jsp" flush="true"/></div>
</body>
</html>