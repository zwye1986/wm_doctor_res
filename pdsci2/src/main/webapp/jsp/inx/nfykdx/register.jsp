<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="register" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<script type="text/javascript">
var tip = {
		"password":"密码长度不足6位，或使用了非法字符",
		"equals":"两次输入的密码不一致",
		"verifyCode":"验证码有误，请重新输入",
		"passwordEmpty":"密码不能为空"
};
function reloadVerifyCode(){
	$("#verifyImage").attr("src","<s:url value='/captcha'/>?random="+Math.random());
}
function checkPasswd(){
	var flag = true;
	var password = $(":password");
	password.each(function(){
		if(this.value == ""){
			$("."+$(this).attr("name")+"Err").text(tip.passwordEmpty);
			return flag=false;
		}
		if(this.value.length<6){
			$("."+$(this).attr("name")+"Err").text(tip.password);
			return flag=false;
		}
		$("."+$(this).attr("name")+"Err").text("");
	});
	if(flag && password[0].value != "" && password[1].value != ""){
		flag = password[0].value == password[1].value;
		$(".userPasswd2Err").text(flag?"":tip.equals);
	}
	return flag;
}
function register(){
	if(false==$("#registerForm").validationEngine("validate")){
		return false;
	}
	var idNo = $("#idNo").val();
	var isIDCard1=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
	var isIDCard2=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
	if(!(idNo.match(isIDCard1)|| idNo.match(isIDCard2))){
		$(".userIdNoErr").text("身份证格式有误");
		return;
	}
	if(checkPasswd()){
		if($("#verifyCode").val()==""){
			$("#verifyCodeErr").html("验证码不能为空");
			return;
		}
		jboxStartLoading();
		$("#registerForm").submit();
	}
}
</script>
</head>
<body>
<div class="yw">
	<c:set var="gzFlag" value="${applicationScope.sysCfgMap['xjgl_customer'] eq 'gzykdx'}"/>
    <div class="top" onclick="location.href='<s:url value='${gzFlag?"/login":"/inx/nfykdx"}'/>'" style="cursor: pointer;">${gzFlag?'广州':'南方'}医科大学研究生管理系统</div>
    <div class="content" style="text-align: center;">
		<div class="notPass wjpsw" >
	   		<div style="width: 680px;margin:0 auto;text-align: left; border:1px solid #DADADA; background: #F9F9F9;">
		   		<div id="operDiv">
					<h1 class="reg_title">注册信息</h1>
					<h2 class="line" style="font-weight:normal;">如已拥有帐户信息，则<a href="<s:url value='${gzFlag?"/login":"/inx/nfykdx"}'/>" class="line_c">在此登录</a></h2>
					<form id="registerForm" action="<s:url value='/inx/nfykdx/saveRegister'/>" method="post" style="position: relative; margin:20px 30px;">
				  		<table border="0" cellspacing="0" cellpadding="0" style="line-height: 50px;margin: 0 auto">
							<tr>
						  	<th style="text-align: right;width: 100px;"><font color="red">*</font>身份证号：</th>
						  		<td style="text-align: left;width: 500px;">
							  		<input name="idNo" id="idNo" value="${param.idNo}" type="text" class="validate[required] txt" style="width:250px;"/>
									<span style="color:red" class="userIdNoErr"></span>
						  		</td>
						  	</tr>
						  		<tr>
									<th style="text-align: right;width: 100px;"><font color="red">*</font>姓名：</th>
									<td style="text-align: left;width: 500px;">
										<input type="text" name="userName" value="${param.userName}"  id="userName" class="validate[required] txt" style="width:250px;"/>
										<span style="color:red" class="userNameErr"></span>
									</td>
							  	</tr>
							  	<tr>
									<th style="text-align: right;"><font color="red">*</font>密码：</th>
									<td style="text-align: left;">
										<input type="password" name="userPasswd" value="${param.userPasswd}" class="validate[required] txt" style="width:250px;"  onpaste="return false" oncontextmenu="return false" oncopy="return false" oncut="return false"/>
										<span style="color:red" class="userPasswdErr"></span>
									</td>
							  	</tr>
							  	<tr>
									<th style="text-align: right;"><font color="red">*</font>确认密码：</th>
									<td style="text-align: left;">
										<input type="password" name="userPasswd2" value="${param.userPasswd}" class="validate[required] txt" style="width:250px;"  onpaste="return false" oncontextmenu="return false" oncopy="return false" oncut="return false"/>
										<span style="color:red" class="userPasswd2Err"></span>
									</td>
							  	</tr>
					  		<tr>
								<th style="text-align: right;"><font color="red">*</font>验证码：</th>
								<td style="text-align: left;" >
									<input id="verifyCode"  name="verifyCode" type="text" class="validate[required] txt" style="width:150px;"/>
									<img id="verifyImage" src="<s:url value='/captcha'/>" style="cursor:pointer;vertical-align: middle;" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" />
									  <span style="color:red;" id="verifyCodeErr"></span>
								</td>
							  </tr>
					  	</table>
				  		<div style="text-align:left;margin: 20px auto 10px 178px">
							<input type="button" value="注&#12288;册" onclick="register();" id="registerButton" class="button button-blue" />
							<c:if test='${!empty errorMsg}'>
								<span style="color:red;">${errorMsg}</span>
							</c:if>
				  		</div>
			  		</form>
			  	</div>
			</div>
	  	</div>
    </div>
</div>
<div class="footer">技术支持：南京品德网络信息技术有限公司</div>
</body>
</html>