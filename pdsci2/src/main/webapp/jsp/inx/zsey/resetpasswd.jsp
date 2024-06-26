<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>湖北省住院医师规范化培训招录系统</title>
<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="register" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">
var tip = {
		"password":"密码长度不足6位，或使用了非法字符",
		"equals":"两次输入的密码不一致",
		"passwordEmpty":"密码不能为空"
};

	function modPasswd() {
		if(false==$("#modPasswdForm").validationEngine("validate")){
			return ;
		}
		if(!checkPasswd()){
			return ;
		}
		var url = "<s:url value='/inx/hbres/savePasswd'/>?actionId=${actionId}";
		var data = $('#modPasswdForm').serialize();
		jboxPost(url, data, function(res) {
			if ("${GlobalConstant.RESET_SUCCESSED}"==res) {
				jboxTip("${GlobalConstant.RESET_SUCCESSED}");
				setTimeout(function(){
					window.location.href="<s:url value='/inx/hbres'/>";
				},1000);
			} else if ("${GlobalConstant.SAVE_FAIL}"==res) {
				jboxTip("该账号不存在");
			}
		});
	}
	
	function checkPasswd(){
		var flag = false;
		var password = $(":password");
		password.each(function(){
			if(!(flag = $.trim(this.value) != "")){
				$("."+$(this).attr("name")+"_br").show();
				$("."+$(this).attr("name")).text(tip.passwordEmpty);
				return false;
			}else if(!(flag = this.value.length>=6)){
				$("."+$(this).attr("name")+"_br").show();
				$("."+$(this).attr("name")).text(tip.password);
				return false;
			}else{
				$("."+$(this).attr("name")+"_br").hide();
				$("."+$(this).attr("name")).text("");
			}
		});
		return flag?checkEquals(password):flag;
	}
	
	function checkEquals(password){
		if(password[0].value != "" && password[1].value != ""){
			var flag = password[0].value == password[1].value;
			if (flag) {
				$(".userPasswd2_br").hide();
			} else {
				$(".userPasswd2_br").show();
			}
			$(".userPasswd2").text(flag?"":tip.equals);
			return flag;
		}else{
			return false;
		}
	}
</script>
</head>
<body>
  <jsp:include page="/jsp/hbres/head.jsp">
         <jsp:param value="/inx/hbres" name="indexUrl"/>
         <jsp:param value="true" name="notShowAccount"/>
     </jsp:include>
  <div class="content">
   <div class="notPass wjpsw" >
   <div style="width: 900px;height: 450px;margin:0 auto;text-align: left; border:1px solid #DADADA; background: #F9F9F9;">
		<h1 class="reg_title">忘记密码--第二步--重置密码</h1>
    	<h2 class="line" style="font-weight:normal;">如已拥有帐户信息，则<a href="<s:url value='/inx/hbres'/>" class="line_c">在此登录</a></h2>
        <form id="modPasswdForm" style="position: relative; margin:30px 20px 20px 30px;">
   	 	<div style="height: 220px;" >
        <table  border="0" cellspacing="0" cellpadding="0" style="line-height: 35px;margin-top: 0">
          <tr>
            <th style="text-align: right;">账&#12288;&#12288;&#12288;号：</th>
            <td style="text-align: left;">${userCode }</td>
          </tr>
          <tr>
            <th style="vertical-align: top; text-align: right;">输入新密码：</th>
            <td style="text-align: left;">
            	<input type="password" class="txt" name="userPasswd" onchange="checkPasswd();" onpaste="return false" oncontextmenu="return false" oncopy="return false" oncut="return false"/>
            	<span class="userPasswd_br" style="display:none;"><br/></span><span class="userPasswd" style="color: #c00"></span>
                <br/><font style=" color:#999;">字母、数字或者英文符号，最短6位，区分大小写</font>
            </td>
          </tr>
          <tr>
            <th style="vertical-align: top;text-align: right;">确认新密码：</th>
            <td style="text-align: left;">
            	<input type="password" class="txt" name="userPasswd2" onchange="checkPasswd();" onpaste="return false" oncontextmenu="return false" oncopy="return false" oncut="return false"/>
            	<span class="userPasswd2_br" style="display:none;"><br/></span><span class="userPasswd2" style="color: #c00"></span>
                <br/><font style=" color:#999;">请再次输入密码</font>
            </td>
          </tr>
        </table>
        </div>
        <div style="text-align:left;margin-top: 20px;margin-left:70px;">
		    <input type="button" value="确定" onclick="modPasswd();" class="button  button-blue" />
		</div>
        </form>
	</div>
  </div>
</div>

<jsp:include page="/jsp/hbres/foot.jsp" />

</body>
</html>
