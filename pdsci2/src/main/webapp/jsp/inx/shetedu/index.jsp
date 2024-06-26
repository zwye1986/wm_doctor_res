<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="false"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="false"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="false"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="false"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<script type="text/javascript" >
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

function reloadVerifyCode()
{
	$("#verifyImage").attr("src","<s:url value='/captcha'/>?random="+Math.random());
}
function checkForm(){
	if(false==$("#loginForm").validationEngine("validate")){
		return false;
	}
	$("#loginForm").submit();;
}
</script>
</head>
<body>
<form id="loginForm" action="<s:url value='/inx/shetedu/login'/>" method="post">
<input type="hidden" name="errorLoginPage" value="inx/shetedu/index"/>
<div class="shet_top_box">
	<div class="shet_top">
    	<span class="fl">${pdfn:getCurrDate() }&nbsp;&nbsp;&nbsp;<font id="weekDateSpan"></font></span>
        <!-- <a href="" class="fr" >设为首页</a>
        <span class="fr">&nbsp;&nbsp;|&nbsp;&nbsp;</span>
        <a href="" class="fr" >加入收藏</a> -->
    </div>
</div>

<div class="shet_banner_box">
	<img class="shet_banner" src="<s:url value="/css/skin/${skinPath}/images/shet_banner.png"/>" />
     <div class="shet_weixin_bg">
           <div class="shet_weixin">
                 <div class="shet_login">
                      <img src="<s:url value="/css/skin/${skinPath}/images/shet_login_pic1.png"/>" />
                      <input name="userCode" value="" class="validate[required]" placeholder="邮箱/QQ号" />
                 </div>
                 <div class="shet_mima">
                      <img src="<s:url value="/css/skin/${skinPath}/images/shet_login_pic2.png"/>" />
                      <input type="text" id="placepwd" style="display: none;" placeholder="密码" value=""/>
                      <input id="userPasswd" name="userPasswd" type="password" class="validate[required]" value="" placeholder="密码" />
                 </div>
                 <div class="shet_yanzm">
                      <input name="verifyCode" class="validate[required]" value="" placeholder="验证码" />
					  <img id="verifyImage" src="<s:url value='/captcha'/>" style="cursor:pointer;height: 40px;" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" />
                 </div> 
                 <div class="njmu_jizhu">
                      <p class="fl red">
                      <c:if test="${not empty loginErrorMessage}">
							登录失败：${loginErrorMessage}
						</c:if>
                      </p>	
                      <c:set var="forgetUrl" value="/sys/user/forget/first" />
					  <c:if test="${'userinfo' eq applicationScope.sysCfgMap['sys_forget_password_type'] }">
						  <c:set var="forgetUrl" value="/reg/forget/first" />
					  </c:if>
					  <c:if test="${'phone' eq applicationScope.sysCfgMap['sys_forget_password_type'] }">
						  <c:set var="forgetUrl" value="/sys/user/forget/phoneFirst" />
					  </c:if>
					  <!-- 
                      <a href="<s:url value='${forgetUrl }' />" class="fr njmu_dengluu">忘记密码?</a>
					   -->
                 </div>
                 <div class="shet_denglu">
                 	<input type="submit" class="shet_login1 fl" onclick="checkForm();" value="登&#12288;录" />
                 </div>
           </div>
      </div>
</div>
    
<div class="shet_new_box">
	<div class="shet_new">
    	<span class="fl shet_xitong">系统公告</span>
    	<c:forEach items="${infos}" var="info">
        <div class="fl shet_gongao">
        	<span>●</span>
        	<a class="notice_title" href="<s:url value='/inx/shetedu/noticeView'/>?infoFlow=${info.infoFlow}" target="_blank">${info.infoTitle}
	        <c:if test="${pdfn:signDaysBetweenTowDate(pdfn:getCurrDate(),pdfn:transDate(info.createTime))<=7}">
	     	<img src="<s:url value='/css/skin/${skinPath}/images/njmu_new.png'/>"/>
	     	</c:if>
	     	</a>
     	</div>
        </c:forEach>
        <a href="<s:url value='/inx/shetedu/noticelist'/>" target="_blank" class="fr njmu_more">查看更多>></a>
    </div>
</div>

<div class="shet_footer_box">
	<a class="shet_footer shet_db">技术支持：南京品德网络信息技术有限公司</a>
</div>


</form>
</body>
</html>
