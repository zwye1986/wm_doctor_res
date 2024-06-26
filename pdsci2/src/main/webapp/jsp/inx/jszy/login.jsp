<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="login" value="true"/>
	<jsp:param name="register" value="true"/>
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
	window.location.href="<s:url value='/jsp/inx/jszy/register.jsp'/>";
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

	var f=checkUser();
	if(f){
		return true;
	}else {
		return false;
	}
//	return true;
}

function checkUser()
{
	var userCode = $("#userCode").val();
	var url = "<s:url value='/inx/jszy/checkUserCodeInBlack'/>";
	var data = {userCode:userCode};
	jboxPost(url,data,
			function(resp){
//				console.log(resp);
//				console.log(resp != ""&&resp != null &&typeof(resp) != 'undefined');
				if(resp != "" && resp != null && typeof(resp) != 'undefined'){
					var height=(window.screen.height)*0.3;
					var width=(window.screen.width)*0.5;
					jboxOpenContent(resp,"提示信息",width,height,true);
					return false;
				}else {
					$("#loginForm").submit();
					return true;
				}
			}, null, false);
}

function showQr(type){
	$(".QR").hide();
	$(".QR_"+type).show();
}
function hideThis(type){
	$(".QR_"+type).hide();
}
</script>
<style>
	.code{ width:600px;}
	.code .android{ width:162px; height:40px; margin-left:15px; background:url(<s:url value='/jsp/inx/jszy/images/WeChat.png'/>) no-repeat; float:right; display:inline-block;  margin-top: 10px;}
	.code .iphone{ width:162px; height:40px; margin-left:15px; background:url(<s:url value='/jsp/inx/jszy/images/app.png'/>) no-repeat; float:right; display:inline-block; margin-top: 10px;}
	.code .android:hover{ width:162px; height:40px; margin-left:15px; background:url(<s:url value='/jsp/inx/jszy/images/WeChat_hover.png'/>) no-repeat; float:right; display:inline-block;  margin-top: 10px; cursor:pointer;}
	.code .iphone:hover{ width:162px; height:40px; margin-left:15px; background:url(<s:url value='/jsp/inx/jszy/images/app_hover.png'/>) no-repeat; float:right; display:inline-block; margin-top: 10px; cursor:pointer;}
	.code .QR_android,.code .QR_iphone{width:162px; height:166px;background:url(<s:url value='/jsp/inx/jszy/images/Q_kuang.png'/>) no-repeat; }
	.code .QR_android{position: absolute;  margin-left: 438px; margin-top:52px;}
	.code .QR_iphone{position: absolute; margin-left: 260px; margin-top:52px;}
	.code .QR_code{ padding-top: 25px; padding-left: 18px;}
</style>
</head>
<body>
<div class="top_box" style="overflow: visible;">
	<div class="top">
    	<span class="fl logo"><img style="display: none;" src="<s:url value='/jsp/jszy/images/logo.png'/>" /></span>
		<div class="code fr" style="padding-top: 6px;">
			<div class="android" onmouseover="showQr('android');" onmouseout="hideThis('android')"></div>
			<div class="iphone" onmouseover="showQr('iphone');" onmouseout="hideThis('iphone')"></div>
			<div class="QR_android QR" style="display:none ;"><img class="QR_code" src="<s:url value='/jsp/inx/jszy/img/jsQrcode.jpg'/>" /></div>
			<div class="QR_iphone QR" style="display:none ;"><img class="QR_code" src="<s:url value='/jsp/inx/jszy/img/jszyappQrcode.jpg'/>" /></div>
		</div>
    </div>
</div>

<div class="banner_box">
	<img class="banner" src="<s:url value='/jsp/jszy/images/banner.png'/>" />
    <img class="login_people" src="<s:url value='/jsp/jszy/images/banner_people.png'/>"/>
    <img class="banner_title" src="<s:url value='/jsp/jszy/images/banner_title.png'/>"/>
     <div class="login_bg">
     	<form id="loginForm" action="<s:url value='/inx/jszy/login'/>" method="post">
           <div class="login_box">
           		<span class="login_title"><img src="<s:url value='/jsp/jszy/images/login_title.png'/>"/></span>
                 <div class="username">
                      <img src="<s:url value='/jsp/jszy/images/login_pic1.png'/>"/>
                      <input id="userCode" name="userCode" value="" placeholder="邮箱"/>
                 </div>
                 <div class="password">
                      <img src="<s:url value='/jsp/jszy/images/login_pic2.png'/>"/>
                      <input type="text" id="placepwd" style="display: none;" placeholder="密码" value=""/><input  type="password" id="userPasswd" name="userPasswd" value="" placeholder="密码"/>
                 </div>

			   <div class="sczy_mima">
				   <img src="<s:url value='/jsp/jszy/images/login_pic3.png'/>" />
				   <input id="verifyCode" name="verifyCode" class="fl" value="" style="width: 100px;" placeholder="验证码"/>
				   <img id="verifyImage" src="<s:url value='/captcha'/>" style="cursor:pointer;height: 34px; padding-left: 50px; position: absolute;" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" />
			   </div>
                <%-- <div class="yanzm">
                      <div class="yan fl">
                      	<input id="verifyCode" name="verifyCode" class="fl" value="" placeholder="验证码"/>
                      </div>
<!-- 					  <img src="images/pic1.png" /> -->
					  <img id="verifyImage" src="<s:url value='/captcha'/>" style="cursor:pointer;height: 40px;" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" />
                 </div>     --%>
                 <span class="cw">
                 <c:if test="${not empty loginErrorMessage}">  
                  	${loginErrorMessage}
                  </c:if>
                 </span>
                 <div class="denglu">
                     <input class="login1 fl" onclick="return checkForm();" type="submit" value="登&nbsp;&nbsp;&nbsp;录" style="margin-bottom: 10px;"/>
                     <input class="login2 fl" type="button" value="注&nbsp;&nbsp;&nbsp;册" onclick="register();"/>
                 </div>
           </div>
          </form>
      </div>
</div>
    
<div class="new_box">
	<div class="new">
    	<span class="fl xitong">系统公告</span>
    	<c:forEach items="${infos}" var="info">
        <div class="fl gongao">
        	<span>●</span>
        	<a class="notice_title" href="<s:url value='/inx/jszy/noticeView'/>?infoFlow=${info.infoFlow}" target="_blank">${info.infoTitle}
	        <c:if test="${pdfn:signDaysBetweenTowDate(pdfn:getCurrDate(),pdfn:transDate(info.createTime))<=7}">
	     	<img src="<s:url value='/jsp/jszy/images/jszy_new.png'/>"/>
	     	</c:if>
	     	</a>
     	</div>
        </c:forEach>
        <a href="<s:url value='/inx/jszy/noticelist'/>" target="_blank" class="fr more">查看更多</a>
    </div>
</div>
<c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
		<jsp:include page="/jsp/service.jsp"></jsp:include>
	</c:if>
<div class="footer_box">
	<a class="login_footer db">主管单位：江苏省中医药管理局 | 技术支持：南京品德网络信息技术有限公司</a>
</div>

</body>
</html>
