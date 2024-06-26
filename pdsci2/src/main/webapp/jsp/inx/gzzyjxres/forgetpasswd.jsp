<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/gzzyjxres/htmlhead-gzzyjxres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="register" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<script>
function sendResetPassEmail(){
	if(false==$("#forgetPasswdForm").validationEngine("validate")){
		return ;
	}
	jboxPost("<s:url value='/inx/gzzyjxres/sendResetPassEmail'/>?userEmail="+$("#userEmail").val()
			+"&verifyCode="+$("#verifyCode").val(),null,function(resp){
		$("#userEmail").val("");
		$("#verifyCode").val("");
		$("#errorMessage").html("");
		$("#errTip").hide();
		var result = resp['result'];
		if(result=="${GlobalConstant.FLAG_Y}"){
			$("#operDiv").hide();
			$("#errTip").hide();
			$("#successTip").show();
			$("#userEmailSpan").html(resp['userEmail']);
		}else if(result=="${GlobalConstant.FLAG_N}"){
			$("#successTip").hide();
			$("#errTip").show();
		}else if(result=="${GlobalConstant.FLAG_F}"){
			$("#errorMessage").html(resp['errorMessage']);
		}
	},null,false);
}

function reloadVerifyCode() {
	$("#verifyImage").attr("src","<s:url value='/captchaComplex'/>?random="+Math.random());
}
</script>
</head>
<body>
  <div class="top">${sysCfgMap['sys_title_name']}</div>
  <div class="content" >
  <div class="notPass wjpsw" >
   <div style="width: 900px;height: 300px;margin:0 auto;text-align: left; border:1px solid #DADADA; background: #F9F9F9;">
       <div id="operDiv">
		<h1 class="reg_title">忘记密码--第一步--输入账户信息</h1>
    	<h2 class="line" style="font-weight:normal;">如已拥有帐户信息，则<a href="<s:url value='/inx/gzzyjxres'/>" class="line_c">在此登录</a></h2>
        <form id="forgetPasswdForm" style="position: relative; margin:30px 20px 20px 30px;">
         	  <table border="0" cellspacing="0" cellpadding="0" style="line-height: 50px;margin-top: 0">
         	  <tr>
         	  	<th style="text-align: right;">邮箱/身份证/手机号：</th>
         	  	<td style="text-align: left;">
         	  		<input type="text" id="userEmail" class="validate[required] txt" style="width:290px;" placeholder="邮箱/身份证/手机号" value="" />
         	  		<span style="display:none;color:red;" id="errTip">用户不存在，请确认填写信息无误</span>
         	  	</td>
         	  </tr>
         	  <tr>
         	  	<th style="text-align: right;">验证码：</th>
         	  	<td style="text-align: left;" >
         	  		<input id="verifyCode" type="text" class="validate[required] txt" style="width:150px;"/>
					<img id="verifyImage" src="<s:url value='/captchaComplex'/>" style="cursor:pointer;vertical-align: middle;" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" />
	                  <span style="color:red;" id="errorMessage"></span>
         	  	</td>
         	  </tr>
	          </table>
	          <div style="text-align:left;margin-top: 20px;margin-left:126px;">
		      		<input type="button" value="找回密码" onclick="sendResetPassEmail();" class="button  button-blue" />
		      </div>
	      </form>
	      </div>
          <div style="display:none ;margin: 40px;line-height: 30px;text-align: left;" id="successTip">
		      <font style="font-weight: bold;font-size: 15px;">系统已发送一封邮件到您的邮箱&nbsp;<font color="#ff8b0f" id="userEmailSpan"></font></font><br/>
			     请登录邮箱，点击重置密码链接修改密码.<br/>
		  </div>
	</div>
  </div>
</div>

<div class="footer">主管单位：四川省卫生厅科教处 | 协管单位：四川省毕业后医学继续教育委员会</div>

</body>
</html>
