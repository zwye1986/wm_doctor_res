<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<script>
$(function(){//获取当前星期几
	var weekDate = '星期'+'日一二三四五六'.charAt(new Date().getDay());
	$("#weekDateSpan").html(weekDate);
});

$(function(){
    var pwd = $("#placepwd");
    var password = $("#userPasswd");
    if (navigator.appName.indexOf("Microsoft Internet Explorer")>-1) {//处理ie浏览器placeholder和password的不兼容问题
    	password.hide();
    	pwd.show();
    	
    	pwd.focus(function(){
            pwd.hide();
            password.show().focus();
        });
         
        password.focusout(function(){
            if(password.val().trim() === ""){
                password.hide();
                pwd.show();
            }
        });
    }
});

function register(){
	window.location.href="<s:url value='/jsp/inx/jsres/register.jsp'/>";
}
function reloadVerifyCode()
{
	$("#verifyImage").attr("src","<s:url value='/captcha'/>?random="+Math.random());
}
function checkForm(){
	if($("#userCode").val()==""){
		$(".log_tips").html("用户名不能为空!");
		return false;
	}
	if($("#userPasswd").val()==""){
		$(".log_tips").html("密码不能为空!");
		return false;
	}
	if($("#verifyCode").val()==""){
		$(".log_tips").html("验证码不能为空!");
		return false;
	}
	return true;
}
function showQr(type){
	$(".QR").hide();
	$(".QR_"+type).show();
}
function hideThis(type){
	$(".QR_"+type).hide();
}
</script>
</head>

<body>
<div class="header">
	<span>
	  <div class="fl">${pdfn:getCurrDate() }&nbsp;&nbsp;<font id="weekDateSpan"></font></div>
	  <div class="code fr">
	     <div class="android" onmouseover="showQr('android');" onmouseout="hideThis('android')"></div>
	     <div class="iphone" onmouseover="showQr('iphone');" onmouseout="hideThis('iphone')"></div>
	     <font class="fr">轮转手册登记请扫描二维码</font>
	      <img class="Q_hand fr" src="<s:url value='/jsp/jsres/images/Q_hand.png'/>" />
	      <div class="QR_android QR" style="display: none;"><img class="QR_code" src="<s:url value='/jsp/jsres/images/android.png'/>" /></div>
	      <div class="QR_iphone QR" style="display: none;"><img class="QR_code" src="<s:url value='/jsp/jsres/images/iphone.png'/>" /></div>	     
	  </div>
	</span>
</div>
<div class="content global">
	<div class="banner">
		<div class="logo_index">
    		<div class="load fr">
    		<form id="loginForm" action="<s:url value='/inx/jsres/login'/>" method="post">
            	<div class="con">
					<div class="user_load"><input type="text" id="userCode" name="userCode" class="text" placeholder="用户名" value="${param.userCode }" /></div>
                    <div class="pass"><input type="text" id="placepwd" class="text" style="display: none;" placeholder="密&nbsp;&nbsp;码" value=""/><input  type="password" id="userPasswd" name="userPasswd" class="text" value="" placeholder="密&nbsp;&nbsp;码"/></div>
                    <div><input id="verifyCode" name="verifyCode" type="text" class="yz" value="" placeholder="验&nbsp;证&nbsp;码" /><img id="verifyImage" src="<s:url value='/captcha'/>" style="cursor:pointer;height: 40px;" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" /></div>
                  
                    <div class="ts">
                    	<div class="fl">
                    	<font class="log_tips">
                    	<c:if test="${not empty loginErrorMessage}">  
					                 登录失败：${loginErrorMessage}
					    </c:if>
					    </font>
					    <c:if test="${empty loginErrorMessage}">&#12288;</c:if>
					    </div>
					    
					    <div class="fr"><a href="<s:url value='/inx/jsres/forgetpasswd'/>" target="_blank">忘记密码?</a></div>
					</div>
                    <div>
                      <c:if test="${empty sysCfgMap['jsres_is_register'] or sysCfgMap['jsres_is_register'] eq 'Y' }">
                    	<input type="submit" onclick="return checkForm();" class="butt_load" value="登&nbsp;&nbsp;录" />
                        <input type="button" onclick="register();" class="butt_cancel" value="注&nbsp;&nbsp;册" />
                      </c:if>
                      <c:if test="${sysCfgMap['jsres_is_register'] eq 'N' }">
                        <input type="submit" onclick="return checkForm();" class="butt_load" style="width:300px; " value="登&nbsp;&nbsp;录" />
                      </c:if>
                    </div>
                    <div class="hint">最低分辨率支持：1200*800px</div>
                </div>
               </form>
			</div>
    		
    	</div>
    </div> 
</div>
<div class="link">
   <div class="scroll-box">
   		<p>友情链接</p>
        <div class="picScroll">
			<ul>
				<li><a href="http://www.nhfpc.gov.cn/"  target="_blank"><img src="<s:url value='/jsp/jsres/images/pic1.png'/>" /></a></li>
				<li><a href="http://www.satcm.gov.cn/"  target="_blank"><img src="<s:url value='/jsp/jsres/images/pic2.png'/>" /></a></li>
				<li><a href="http://www.jswst.gov.cn/jsswshjhsywyh/index.html"  target="_blank"><img src="<s:url value='/jsp/jsres/images/pic3.png'/>" /></a></li>
				<li><a href="http://www.jstcm.gov.cn"  target="_blank"><img src="<s:url value='/jsp/jsres/images/pic4.png'/>" /></a></li>
			</ul>
			<a class="prev"></a>
			<a class="next"></a>
		</div>
		<script type="text/javascript">jQuery(".picScroll").slide({ mainCell:"ul",autoPlay:true,effect:"left", vis:4, scroll:3, autoPage:true, pnLoop:false });</script>
    </div>
</div>
 <c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
		<jsp:include page="/jsp/service.jsp" flush="true"></jsp:include>
	</c:if>
<div class="footer_index">
	<div align="center">
		<img src="<s:url value='/jsp/jsres/images/count.png'/>" />&#12288;总访问量：<font color="red">${count}</font>&#12288;当前访问量：<font color="red">${pdfn:getOnlineUserCount()}</font>
	</div>
	主管单位：${sysCfgMap['the_competent_unit']}   |  技术支持：南京品德网络信息技术有限公司

</div>

</body>
</html>
