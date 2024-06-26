<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/recruit/htmlhead-recruit.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="login" value="f"/>
	<jsp:param name="register" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<script type="text/javascript">
var tip = {
		"email":"邮箱不能为空或格式不正确",
		"password":"密码长度不足6位，或使用了非法字符",
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

function register(){

	if(!$("#registerForm").validationEngine("validate")) {
		return false;
	}
	if(!checkPasswd()){
		return false;
	}
	jboxPost("<s:url value='/inx/recruit/regist'/>", $("#registerForm").serialize(), function (resp) {
		jboxTip(resp);
		if(resp=="${GlobalConstant.USER_REG_SUCCESSED}")
		{
			setTimeout(function() {
				window.location.href = "<s:url value="/inx/recruit"/>";
			},1000);
		}else{
			reloadVerifyCode()
		}

	}, null, true);
}
</script>
</head>
<body>
<div class="bg" style="width: 100%; min-height: 100%; position: relative;">
    	<div class="head">
			<div class="head_inner">
				<h1 class="logo">
					<img style="margin-top: 30px;" onclick="window.location.href='<s:url value='/inx/recruit'/>'" style="cursor: pointer;" src="<s:url value=''/>"/>
				</h1>
			</div>
		</div>
    <div class="register" style="margin-top: 0px;">
		<c:if test="${empty sysCfgMap['recruit_org_flow']}">

			<div class="notPass wjpsw" >
				<div style="width: 800px;margin:0 auto;text-align: left; border:1px solid #DADADA; background: #F9F9F9;">
					未设置招录机构信息，请联系系统管理员！
				</div>
			</div>
		</c:if>
		<c:if test="${not empty sysCfgMap['recruit_org_flow']}">

			<div class="notPass wjpsw" >
				<div style="width: 800px;margin:0 auto;text-align: left; border:1px solid #DADADA; background: #F9F9F9;">
					<div id="operDiv">
						<h1 class="reg_title">注册信息</h1>
						<h2 class="line" style="font-weight:normal;">如已拥有帐户信息，则<a href="<s:url value='/inx/recruit'/>" class="line_c">在此登录</a></h2>
						<form id="registerForm" action="<s:url value='/inx/jszy/regist'/>" method="post" style="position: relative; margin:20px 30px;">
							<input type="hidden" name="orgFlow" value="${sysCfgMap['recruit_org_flow']}">
							<table border="0" cellspacing="0" cellpadding="0"  style="width:800px;line-height: 50px;margin: 0 auto">
								<tr>
									<th style="text-align: right;width: 100px;"><font color="red">*</font>用户名：</th>
									<td style="text-align: left;width: 500px;">
										<input type="text" name="userCode" id="userCode"  title="用户名必须以字母开头，长度在3-18之间，只能包含字符、数字和下划线" class="validate[required,custom[userCode]] txt" style="width:320px;" value="" />
										<span style="color:red" class="userCodeErr"></span>
									</td>
								</tr>
								<tr>
									<th style="text-align: right;width: 100px;"><font color="red">*</font>姓名：</th>
									<td style="text-align: left;width: 500px;">
										<input type="text" name="userName" id="userName" class="validate[required] txt" style="width:320px;" value="" />
										<span style="color:red" class="userNameErr"></span>
									</td>
								</tr>
								<tr>
									<th style="text-align: right;width: 100px;"><font color="red">*</font>手机号码：</th>
									<td style="text-align: left;width: 500px;">
										<input type="text" name="userPhone" id="userPhone" class="validate[required,custom[mobile]] txt" style="width:320px;" value="" />
										<span style="color:red" class="userPhoneErr"></span>
									</td>
								</tr>
								<tr>
									<th style="text-align: right;width: 100px;"><font color="red">*</font>身份证号：</th>
									<td style="text-align: left;width: 500px;">
										<input type="text" name="idNo" id="idNo" class="validate[required,custom[chinaId]] txt" style="width:320px;" value="" />
										<span style="color:red" class="idNoErr"></span>
									</td>
								</tr>
								<tr>
									<th style="text-align: right;"><font color="red">*</font>登录密码：</th>
									<td style="text-align: left;">
										<input type="password" name="userPasswd" class="validate[required] txt" style="width:320px;"  onpaste="return false" oncontextmenu="return false" oncopy="return false" oncut="return false"/>
										<span style="color:red" class="userPasswdErr"></span>
									</td>
								</tr>
								<tr>
									<th style="text-align: right;"><font color="red">*</font>确认密码：</th>
									<td style="text-align: left;">
										<input type="password" name="userPasswd2" class="validate[required] txt" style="width:320px;"  onpaste="return false" oncontextmenu="return false" oncopy="return false" oncut="return false"/>
										<span style="color:red" class="userPasswd2Err"></span>
									</td>
								</tr>

								<tr>
									<th style="text-align: right;"><font color="red">*</font>验证码：</th>
									<td style="text-align: left;" >
										<input id="verifyCode"  name="verifyCode" type="text" class="validate[required] txt" style="width:320px;"/>
										<img id="verifyImage" src="<s:url value='/captcha'/>" style="cursor:pointer;position: absolute;margin-left: -85px;float: left;margin-top: -6px;" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" />
										<span style="color:red;" id="verifyCodeErr"></span>
									</td>
								</tr>
								<tr>
									<th colspan="2" style="text-align: left;"><font color="red">注：请填写真实资料信息，否则影响审核结果。 </font></th>
								</tr>
							</table>
							<div style="text-align:left;margin: 20px auto 10px 240px">
								<input type="button" value="注&#12288;册" onclick="register();" id="registerButton" class="btn_blue" style="margin-top: 0px;"/>
							</div>
						</form>
					</div>
				</div>
			</div>
		</c:if>
    </div>
	<div class="footer">技术支持：南京品德网络信息技术有限公司</div>
</div>
<c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
	<jsp:include page="/jsp/service.jsp"></jsp:include>
</c:if>
</body>
</html>