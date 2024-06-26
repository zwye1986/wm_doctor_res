
<%@include file="/jsp/common/doctype.jsp" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/sczyres/htmlhead-sczyres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="login" value="true"/>
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
	window.location.href="<s:url value='/jsp/inx/sczyres/register.jsp'/>";
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
}
function checkUser()
{
    var userCode = $("#userCode").val();
    var url = "<s:url value='/inx/jsres/checkUserCodeInBlack'/>";
    var data = {userCode:userCode};
    jboxPost(url,data,
            function(resp){
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
</script>
</head>

<body>
<div class="sczy_top_box">
	<div class="sczy_top">
    	<span class="fl">${pdfn:getCurrDate() }&nbsp;&nbsp;&nbsp;<font id="weekDateSpan"></font></span>
		<a class="fr">欢迎使用住院医师规范化培训招录平台!</a>
    </div>
</div>

<div class="sczy_banner_box">
	<img class="sczy_banner" src="<s:url value='/jsp/sczyres/images/sczy_banner2.jpg'/>" />
    
     <div class="sczy_weixin_bg">
     	<form id="loginForm" action="<s:url value='/inx/sczyres/login'/>" method="post">
           <div class="sczy_weixin">
           		<a class="sczy_chname">用户登录</a>
                <div class="sczy_enname"></div>
                 <div class="sczy_login">
                      <img src="<s:url value='/jsp/sczyres/images/sczy_login_pic1.png'/>" />
                      <input id="userCode" name="userCode" value="" placeholder="用户名"/>
                 </div>
                 <div class="sczy_mima">
                      <img src="<s:url value='/jsp/sczyres/images/sczy_login_pic2.png'/>" />
                      <input type="text" id="placepwd" style="display: none;" placeholder="密码" value=""/><input onpaste="return false" oncontextmenu="return false" oncopy="return false" oncut="return false" type="password" id="userPasswd" name="userPasswd" value="" placeholder="密码"/>
                 </div>
                 <div class="sczy_yanzm">
                      <input id="verifyCode" name="verifyCode" class="fl" value="" placeholder="验证码"/>
					  <img id="verifyImage" src="<s:url value='/captcha'/>" style="cursor:pointer;height: 40px;" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" />
                 </div> 
                              
                 <div class="jizhu">
                 <font class="red fl log_tips">
                 <c:if test="${not empty loginErrorMessage}">  
                  	${loginErrorMessage}
                  </c:if>
                  </font>
                 <a href="<s:url value='/inx/sczyres/forgetpasswd'/>" class="fr dengluu">忘记密码?</a>
                 </div>
                 
                 <div class="sczy_denglu">
                     <input type="submit" onclick="return checkForm();" class="sczy_login1 fl " style="cursor: pointer;" value="登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录"/>
                     <input type="button" onclick="register();" class="sczy_login2 fr" style="cursor: pointer;" value="注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;册"/>
                 </div>
           </div>
          </form>
      </div>
</div>
    
<div class="sczy_new_box">
	<div class="sczy_new">
    	<span class="fl sczy_xitong">系统公告</span>
    	<c:forEach items="${infos}" var="info">
        <div class="fl sczy_gongao">
        	<span>●</span>
        	<a class="notice_title" href="<s:url value='/inx/sczyres/noticeView'/>?infoFlow=${info.infoFlow}" target="_blank">${info.infoTitle}
	        <c:if test="${pdfn:signDaysBetweenTowDate(pdfn:getCurrDate(),pdfn:transDate(info.createTime))<=7}">
	     	<img src="<s:url value='/jsp/sczyres/images/sczy_new.png'/>"/>
	     	</c:if>
	     	</a>
     	</div>
        </c:forEach>
        <a href="<s:url value='/inx/sczyres/noticelist'/>" target="_blank" class="fr sczy_more">查看更多</a>
    </div>
</div>

<div class="sczy_footer_box">
	<a class="sczy_footer sczy_db">主管单位：四川省中医药管理局人事教育处 | 协管单位：四川省中医药毕业后教育委员会办公室</a>
</div>

</body>
</html>
