
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/sczyres/htmlhead-sczyres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="register" value="true"/>
	<jsp:param name="jbox" value="true"/>
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

<%--function register(){--%>
	<%--if(!$("#registerForm").validationEngine("validate")){--%>
		<%--return;--%>
	<%--}--%>
<%--//	if(!checkMail()){--%>
<%--//		return;--%>
<%--//	}--%>
	<%--var url = "<s:url value='/sys/user/edit/checkEmailAndUserCode'/>";--%>
	<%--jboxPost(url,{"userEmail":$("#userEmail").val()},--%>
		<%--function(resp){--%>
			<%--if(resp){--%>
				<%--$(".userEmailErr").text(resp);--%>
			<%--}else{--%>
				<%--$(".userEmailErr").text("");--%>
//				if(checkPasswd()){
//					if($("#verifyCode").val()==""){
//						$("#verifyCodeErr").html("验证码不能为空");
//						return;
//					}
//					jboxStartLoading();
//					$("#registerForm").submit();
//				}
			<%--}--%>
		<%--},--%>
		<%--null,false);--%>
<%--}--%>

function register(){
	if(!$("#registerForm").validationEngine("validate")){
		return;
	}
	if($(".userIdNoErr").text()!=""){
		return;
	}
	if(!checkPasswd()) {
		return;
	}
	if ($("#verifyCode").val() == "") {
		$("#verifyCodeErr").html("验证码不能为空");
		return;
	}
	jboxStartLoading();
	$("#registerForm").submit();
}
function checkIdNo(){
	if($("#idNo").validationEngine("validate")){
		return false;
	}
	var idNo = $("#idNo").val();
	var url = "<s:url value='/inx/jszy/checkIdNo'/>";
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
</script>
</head>
<body>
	<div class="yw">
    <div class="top" onclick="window.location.href='<s:url value='/inx/sczyres'/>'" style="cursor: pointer;">${sysCfgMap['sys_title_name']}</div>
    <div class="content" style="text-align: center;">
		<div class="notPass wjpsw" >
   <div style="width: 800px;margin:0 auto;text-align: left; border:1px solid #DADADA; background: #F9F9F9;">
       <div id="operDiv">
		<h1 class="reg_title">注册信息</h1>
    	<h2 class="line" style="font-weight:normal;">如已拥有帐户信息，则<a href="<s:url value='/inx/sczyres'/>" class="line_c">在此登录</a></h2>
        <form id="registerForm" action="<s:url value='/inx/sczyres/regist'/>" method="post" style="position: relative; margin:20px 30px;">
         	  <table border="0" cellspacing="0" cellpadding="0"  style="width:800px;line-height: 50px;margin: 0 auto">
			  <tr>
				  <th style="text-align: right;width: 100px;"><font color="red">*</font>身份证号码：</th>
				  <td style="text-align: left;width: 500px;">
					  <input name="idNo" id="idNo" value="" type="text" class="validate[required,custom[chinaId]] txt" style="width:290px;" onblur="checkIdNo();"/>
					  <span style="color:red" class="userIdNoErr"></span><font color="#f80" class="emailTip">作为登录用户名</font>
				  </td>
			  </tr>
			  <%--<tr>--%>
         	  	<%--<th style="text-align: right;width: 100px;"><font color="red">*</font>邮箱：</th>--%>
         	  	<%--<td style="text-align: left;width: 500px;">--%>
         	  		<%--<input type="text" name="userEmail" id="userEmail" class="validate[required,custom[email]] txt" style="width:290px;" value="" />--%>
         	  		<%--<span style="color:red" class="userEmailErr"></span>--%>
         	  	<%--</td>--%>
         	  <%--</tr>--%>
         	  <tr>
         	  	<th style="text-align: right;"><font color="red">*</font>密码：</th>
         	  	<td style="text-align: left;">
         	  		<input type="password" name="userPasswd" class="validate[required] txt" style="width:290px;"  onpaste="return false" oncontextmenu="return false" oncopy="return false" oncut="return false"/>
         	  		<span style="color:red" class="userPasswdErr"></span>
         	  	</td>
         	  </tr>
         	  <tr>
         	  	<th style="text-align: right;"><font color="red">*</font>确认密码：</th>
         	  	<td style="text-align: left;">
         	  		<input type="password" name="userPasswd2" class="validate[required] txt" style="width:290px;"  onpaste="return false" oncontextmenu="return false" oncopy="return false" oncut="return false"/>
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
</div>
<c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
		<jsp:include page="/jsp/service.jsp"></jsp:include>
	</c:if>
<div class="footer">主管单位：四川省卫生厅科教处 | 协管单位：四川省毕业后医学继续教育委员会</div>
</body>
</html>