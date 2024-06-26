<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="register" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>

<script type="text/javascript">
	var isAgree = false;
	
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
	
	function register(){
		if(!$("#registerForm").validationEngine("validate")){
			return;
		}
		var url = "<s:url value='/sys/user/edit/checkEmailAndUserCode'/>";
		jboxPost(url,{"userEmail":$("#userEmail").val()},
			function(resp){
				if(resp){
					$(".userEmail").text(resp);
				}else{
					$(".userEmail").text("");
					if(checkPasswd()){
						jboxStartLoading();
						$("#registerForm").submit();
					}
				}
			},
			null,false);
	}
	
	function activeButton(){
		var check = $("#agree:checked");
		isAgree = check.length>0;
		if(isAgree){
			$("#registerButton").addClass("button-blue");
		}else{
			$("#registerButton").removeClass("button-blue");
		}
	}
	
	function checkPasswd(){
		var flag = false;
		var password = $(":password");
		password.each(function(){
			if(!(flag = $.trim(this.value) != "")){
				$("."+$(this).attr("name")).text(tip.passwordEmpty);
				return false;
			}else if(!(flag = this.value.length>=6)){
				$("."+$(this).attr("name")).text(tip.password);
				return false;
			}else{
				$("."+$(this).attr("name")).text("");
			}
		});
		return flag?checkEquals(password):flag;
	}
	
	function checkEquals(password){
		if(password[0].value != "" && password[1].value != ""){
			var flag = password[0].value == password[1].value;
			$(".userPasswd2").text(flag?"":tip.equals);
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
			$(".userEmail").text(tip.email);
		}
		return flag;
	}
	
	function checkMailRepeat(){
		if(checkMail()){
			var url = "<s:url value='/sys/user/edit/checkEmailAndUserCode'/>";
			jboxPost(url,{"userEmail":$("#userEmail").val()},

				function(resp){
					if(resp){
						$(".userEmail").text(resp);
					}else{
						$(".userEmail").text("");
					}
				},
				null,false);
		}
	}
</script>
</head>
<body>
	<div class="yw">
    <jsp:include page="/jsp/hbres/head.jsp">
         <jsp:param value="/inx/hbres" name="indexUrl"/>
         <jsp:param value="true" name="notShowAccount"/>
     </jsp:include>
    <div class="content">
       <div class="step">
           <a href="#" class="step1 active1">1、基本信息</a>
           <a href="#" class="step2">2、邮箱激活</a>
           <a href="#" class="step3">3、选择类型</a>
           <a href="#" class="step4">4、信息登记</a>
           <a href="#" class="step5">5、完成</a>
       </div>
       <div class="firstStep clearfix">
         <div class="left1 fl">
             <p>每个邮箱仅能申请一次.<span style="color: red;">&nbsp;&nbsp;&nbsp;&nbsp;${errorMsg}</span></p>
             <form id="registerForm" action="<s:url value='/inx/hbres/register'/>" method="post" style="margin: 10px;">
           <dl>
                 <dt>邮箱</dt>
                 <dd class="import">
                 	<input type="text" id="userEmail" class="validate[required,maxSize[50],custom[email]]" name="userEmail" onchange="checkMailRepeat(null);">
                 </dd>
                 <dd class="message">
                   <span class="userEmail"></span>
                   <p>作为登录账号，请填写未被平台注册绑定的邮箱</p>
                 </dd>
           </dl>
           <dl>
                 <dt>密码</dt>
                 <dd class="import">
                 	<input type="password" name="userPasswd" onchange="checkPasswd();" onpaste="return false" oncontextmenu="return false" oncopy="return false" oncut="return false"/>
                 </dd>
                 <dd class="message">
                   <span class="userPasswd"></span>
                   <p>字母、数字或者英文符号，最短6位，区分大小写</p>
                 </dd>
           </dl>
           <dl>
                 <dt>确认密码</dt>
                 <dd class="import">
                 	<input type="password" name="userPasswd2" onchange="checkPasswd();" onpaste="return false" oncontextmenu="return false" oncopy="return false" oncut="return false"/>
                 </dd>
                 <dd class="message">
                   <span class="userPasswd2"></span>
                   <p>请再次输入密码</p>
                 </dd>
           </dl>
             <dl>
                 <dt>验证码</dt>
                 <dd class="import">
                 	<input name="verifyCode" type="text" class="yzm" style="width:145px;" placeholder="验证码" />
	               	<img id="verifyImage" src="<s:url value='/captcha'/>" width="80" height="32"/>
	                <a href="javascript:;" onclick="reloadVerifyCode();">换一张</a>
                 </dd>
                 <dd class="message">
                   <span class="yzmError"></span>
                 </dd>
             </dl>
             <!-- <div class="agree">
             	<label>
		             <input id="agree" type="checkbox" onclick="activeButton();"/>
		        	   同意<a href="#">《湖北省住院医师报名招录系统使用协议》</a>
        	   </label>
        	</div> -->
             </form>
             <div class="buts">
             	<a id="registerButton" href="javascript:register();"  class="button button-blue">注册</a>
             	
             </div>
			</div>
		<div class="right fr">已有平台账号？<a href="<s:url value='/inx/hbres'/>">立即登录</a></div>
      </div>
    </div>
</div>
<c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
    <jsp:include page="/jsp/service.jsp"></jsp:include>
</c:if>
<jsp:include page="/jsp/hbres/foot.jsp" />
</body>
</html>