
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<title>${applicationScope.sysCfgMap['study_platform'] eq 'szslyy'?'苏州市住院医师公共科目学习平台':'南京医科大学研究生在线课程学习平台'}</title>
<meta name="keywords" content=""/>
<meta name="description" content=""/>
<jsp:include page="/jsp/njmuedu/htmlhead-njmuedu.jsp">
	<jsp:param name="index" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<script>
function reloadVerifyCode()
{
	$("#verifyImage").attr("src","<s:url value='/captcha'/>?random="+Math.random());
}
function toUrl(url){
	window.location.href=url;
}
function login(){
	if(false == $("#loginForm").validationEngine("validate")){
		return false;
	}
	$("#loginForm").submit();
}
</script>
</head>
<body>
<c:set var="szFlag" value="${applicationScope.sysCfgMap['study_platform'] eq 'szslyy'}"/>
<div class="top_box">
	<div class="top">
    	<span class="riqi"><img src="<s:url value='/jsp/njmuedu/css/images/${szFlag?"sznjmu_logo":"njmu_logo"}.png'/>" /></span>
       <!--  <a href="" class="fr liji" >立即注册</a>
        <a class="fr" >第一次使用学习平台？</a> -->
    </div>
</div>

<div class="banner_box">
   <form id="loginForm" action="<s:url value='/inx/njmuedu/login'/>"  method="post">
	<img class="banner" src="<s:url value='/jsp/njmuedu/css/images/${szFlag?"sznjmu_banner5":"njmu_banner5"}.png'/>" />
     <div class="weixin_bg">
           <div class="weixin">
                 <a class="db login_title" >用户登录</a>
                 <div class="login">
                      <img src="<s:url value='/jsp/njmuedu/css/images/njmu_login_pic1.png'/>" />
                      <input value="${param.userCode }" name="userCode" placeholder="用户名" class="validate[required]"/>    
                 </div>
                 <div class="mima">
                      <img src="<s:url value='/jsp/njmuedu/css/images/njmu_login_pic2.png'/>" />
                      <input type="password" name="userPasswd" placeholder="密码" class="validate[required]"/>
                 </div>
                 <div class="yanzm">
                     <input type="text" name="verifyCode" placeholder="验证码" class="validate[required]"/>
                     <img id="verifyImage" src="<s:url value='/captcha'/>" style="cursor:pointer;height: 40px;" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" />
                 </div>
                 
                 <div class="jizhu">
                 <c:if test="${GlobalConstant.FLAG_N != applicationScope.sysCfgMap['sys_forget_password'] }">
					<c:set var="forgetUrl" value="/sys/user/forget/first" />
					<c:if test="${'userinfo' eq applicationScope.sysCfgMap['sys_forget_password_type'] }">
						<c:set var="forgetUrl" value="/reg/forget/first" />
				    </c:if>
					<c:if test="${'phone' eq applicationScope.sysCfgMap['sys_forget_password_type'] }">
						<c:set var="forgetUrl" value="/sys/user/forget/phoneFirst" />
					</c:if>
                      <!-- <input type="checkbox" class="jizhu_01 fl">
                      <a class="fl">记住密码</a> -->
                      <a href="<s:url value='${forgetUrl }'/>" class="fr dengluu">忘记密码?</a>
                    </c:if>
                  <c:if test="${not empty message}">
                  <a class="red fl">登录失败：${message}</a>
                 </c:if>
                 </div>
                 
                 
                 <div class="denglu fl">
                 	<input type="submit" class="login1 fl" onclick="login();" value="登&#12288;录" />
                 	<!-- <input type="submit" class="login2 fr" onclick="checkForm();" value="注&#12288;册" /> -->
                 </div>
           </div>
           
      </div>
    </form>
</div>

<div class="footer_box">
	<a class="footer db">技术支持：南京品德网络信息技术有限公司</a>
</div>
</body>
</html>