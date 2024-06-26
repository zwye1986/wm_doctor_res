<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="register" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="true"/>
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
			$(".emailTip").hide();
			return false;
		}else if(!(flag = this.value.length>=6)){
			$("."+$(this).attr("name")+"Err").text(tip.password);
			$(".emailTip").hide();
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
		$(".emailTip").hide();
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
	if($("#idNo").validationEngine("validate")){
		return false;
	}
	var idNo = $("#idNo").val();
	var url = "<s:url value='/inx/jsres/checkIdNo'/>";
	jboxPost(url,{"idNo":$("#idNo").val()}, function(resp){
		if(resp){
			$(".userIdNoErr").text(resp);
			$(".emailTip").hide();
		}else {
			$(".userIdNoErr").text("");
			$(".emailTip").show();
		}
	},null,false);
}
function checkUserPhone(){
	if($("#userPhone").validationEngine("validate")){
		return false;
	}
	var userPhone = $("#userPhone").val();
	var url = "<s:url value='/inx/jsres/checkUserPhone'/>";
	jboxPost(url,{"userPhone":$("#userPhone").val()}, function(resp){
		if(resp){
			$(".userPhoneErr").text(resp);
			$(".emailTip").hide();
		}else {
			$(".userPhoneErr").text("");
			$(".emailTip").show();
		}
	},null,false);
}
function register(){
	if(false==$("#registerForm").validationEngine("validate")){
		return false;
	}
	if(!checkMail()){
		return;
	}
	var url = "<s:url value='/inx/jsres/checkEmail'/>";
	jboxPost(url,{"userEmail":$("#userEmail").val()},
		function(resp){
			if(resp=='${GlobalConstant.USER_EMAIL_REPETE}'){
				$(".userEmailErr").text(resp);
				$(".emailTip").hide();
			}else if(resp=='${GlobalConstant.USER_PHONE_REPETE}'){
				$(".userPhoneErr").text(resp);
				$(".emailTip").hide();
			}else{
				$(".userEmailErr").text("");
				$(".emailTip").show();
				if(checkPasswd()){
					if($("#verifyCode").val()==""){
						$("#verifyCodeErr").html("验证码不能为空");
						return;
					}
					jboxStartLoading();
					$("#registerForm").submit();
				}
			}
		},
		null,false);
}
	function checkEmail(){
		if($("#userEmail").validationEngine("validate")){
			return false;
		}
		var url = "<s:url value='/inx/jsres/checkEmail'/>";
		jboxPost(url,{"userEmail":$("#userEmail").val()}, function(resp){
			if(resp){
				$(".userEmailErr").text(resp);
				$(".emailTip").hide();
			}else {
				$(".userEmailErr").text("");
				$(".emailTip").show();
			}
		},null,false);
	}
</script>
</head>
<body>
	<div class="yw">
    <div class="top">临床技能考核管理系统（OSCE）</div>
    <div class="content" style="text-align: center;">
		<div class="notPass wjpsw" >
   <div style="width: 680px;margin:0 auto;text-align: left; border:1px solid #DADADA; background: #F9F9F9;">
       <div id="operDiv">
		<h1 class="reg_title">注册信息</h1>
    	<h2 class="line" style="font-weight:normal;">如已拥有帐户信息，则<a href="<s:url value='/jsp/inx/osce/index.jsp'/>" class="line_c">在此登录</a></h2>
        <form id="registerForm" action="<s:url value='/inx/osce/saveRegister'/>" method="post" style="position: relative; margin:20px 30px;">
         	  <table border="0" cellspacing="0" cellpadding="0" style="line-height: 50px;margin: 0 auto">
				  <tr>
					<th style="text-align: right;width: 100px;"><font color="red">*</font>邮箱：</th>
					<td style="text-align: left;width: 500px;">
						<input type="text" name="userEmail" value="${param.userEmail}"  id="userEmail" class="validate[required,custom[email]] txt" style="width:250px;" onblur="checkEmail();"/>
						<span style="color:red" class="userEmailErr"></span><font color="#f80" class="emailTip">将作为你登录系统的用户名！</font>
					</td>
				  </tr>
				  <tr>
					  <th style="text-align: right;width: 100px;"><font color="red">*</font>证件类型：</th>
					  <td style="text-align: left;width: 500px;">
						  <select id="cretTypeId" name="cretTypeId" class="txt validate[required]" style="width:250px;">
							  <option value="">请选择</option>
							  <c:forEach items="${certificateTypeEnumList}" var="cret">
								  <option value="${cret.id}" ${param.cretTypeId eq cret.id?'selected':''}>${cret.name}</option>
							  </c:forEach>
						  </select>
						  <span style="color:red"></span><font color="#f80" class="emailTip">请选择证件类型</font>
					  </td>
				  </tr>
				  <tr>
					  <th style="text-align: right;width: 100px;"><font color="red">*</font>证件号码：</th>
					  <td style="text-align: left;width: 500px;">
						  <input name="idNo" id="idNo" value="${param.idNo}" type="text" class="validate[required<%--,custom[chinaIdLoose]--%>] txt" style="width:250px;" onblur="checkIdNo();"/>
						  <span style="color:red" class="userIdNoErr"></span><font color="#f80" class="emailTip">请输入个人证件号码，保存后无法修改</font>
					  </td>
				  </tr>
				  <tr>
					  <th style="text-align: right;width: 100px;"><font color="red">*</font>手机号码：</th>
					  <td style="text-align: left;width: 500px;">
						  <input name="userPhone" id="userPhone" value="${param.userPhone}" type="text" class="validate[required] txt" style="width:250px;" onblur="checkUserPhone();"/>
						  <span style="color:red" class="userPhoneErr"></span><font color="#f80" class="emailTip">请输入个人常用手机号码</font>
					  </td>
				  </tr>
				  <tr>
					<th style="text-align: right;"><font color="red">*</font>密码：</th>
					<td style="text-align: left;">
						<input type="password" name="userPasswd" value="${param.userPasswd}" class="validate[required] txt" style="width:250px;"  onpaste="return false" oncontextmenu="return false" oncopy="return false" oncut="return false"/>
						<span style="color:red" class="userPasswdErr"></span><font color="#f80" class="emailTip">请输入个人登录密码</font>
					</td>
				  </tr>
				  <tr>
					<th style="text-align: right;"><font color="red">*</font>确认密码：</th>
					<td style="text-align: left;">
						<input type="password" name="userPasswd2" value="${param.userPasswd}" class="validate[required] txt" style="width:250px;"  onpaste="return false" oncontextmenu="return false" oncopy="return false" oncut="return false"/>
						<span style="color:red" class="userPasswd2Err"></span><font color="#f80" class="emailTip">请再次输入个人登录密码</font>
					</td>
				  </tr>
				  <tr>
					<th style="text-align: right;"><font color="red">*</font>验证码：</th>
					<td style="text-align: left;" >
						<input id="verifyCode"  name="verifyCode" type="text" class="validate[required] txt" style="width:150px;"/>
						<img id="verifyImage" src="<s:url value='/captcha'/>" style="cursor:pointer;vertical-align: middle;" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" />
						  <span style="color:red;" id="verifyCodeErr"></span><font style="margin-left: -55px;" color="#f80" class="emailTip">请输入左侧4位数字信息</font>
					</td>
				  </tr>
	          </table>
	          <div style="text-align:left;margin: 20px auto 10px 178px">
		      		<input type="button" value="注&#12288;册" onclick="register();" id="registerButton" class="button  button-blue" />
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
	<div class="footer">
		<a href="https://beian.miit.gov.cn/" target="_blank" style="color:#FFFFFF;">工信部备案号：苏ICP备14054231号-1</a>  &nbsp;
		技术支持：南京品德网络信息技术有限公司 服务电话：025-69815356 69815357&emsp;<a target="_blank" style="color:#000; font-size:14px;" href="${applicationScope.sysCfgMap['chrome_download_url']}">专用浏览器下载</a>	</div>
</body>
</html>