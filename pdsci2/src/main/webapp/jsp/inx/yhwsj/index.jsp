<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>余杭区卫计人事管理平台</title>

<c:set var="min" value=".min"/>
<link rel="stylesheet" type="text/css" href="<s:url value='/css/skin/${skinPath}/fenye.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
	<script type="text/javascript" src="<s:url value='/js/jquery-1.8.3${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script type="text/javascript" src="<s:url value='/js/json2${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="false"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>

<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/inx/yhwsj/css/style.css'/>" />
<script>
	function reloadVerifyCode(){
		$("#verifyImage").attr("src","<s:url value='/captcha'/>?random="+Math.random());
	}
	
	function checkForm(){
		if(false==$("#loginForm").validationEngine("validate")){
			return false;
		}
		$("#loginForm").submit();
	}

	function logout(){
		window.location.href="<s:url value='/inx/yhwsj/logout'/>";
	}

	function regist(){
		window.location.href="<s:url value='/reg/srm/go'/>";
	}
	
	$(document).ready(function(){
		jboxLoad("noticeDiv","<s:url value='/inx/yhwsj/queryData?columnId=LM03&jsp=inx/yhwsj/index_notice&endIndex=8'/>");
		jboxLoad("downloadDiv","<s:url value='/inx/yhwsj/queryData?columnId=LM02&jsp=inx/yhwsj/index_downloads&endIndex=8'/>");
	});
	
</script>

</head>


<body>
<%@include file="header.jsp" %>

<div class="banner_box">
	<img src="<s:url value='/'/>jsp/inx/yhwsj/images/banner.png">
</div>

<div class="contentbox_01">
	<div class="leftcontent fl" id="noticeDiv">
        
    </div>
    <div class="login fr">
        <div class="login_01">
        	<a>用户登录</a>
        </div>
        <div class="login_02">
        <form id="loginForm" action="<s:url value='/login'/>" method="post" style="overflow:hidden;">
   			<input type="hidden" name="errorLoginPage" value="inx/yhwsj/index"/>
    		<input type="hidden" name="successLoginPage" value="inx/yhwsj/index"/>
    		<c:choose>
			 	<c:when test="${empty sessionScope.currUser}">
		            <div class="user"><a>账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</a><input name="userCode" type="text" placeholder="" value="" class="validate[required]"/></div>
		            <div class="mima"><a>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</a><input name="userPasswd" type="password" class="validate[required]"/></div>
		            <div class="yanzheng" style="margin-top: 0px;margin-bottom: 0px;">
		            	<a>验&nbsp;证&nbsp;码</a><input name="verifyCode" type="text" class="validate[required]" style="width:163px;margin: 0px; padding: 0px;"/>
						<img id="verifyImage" src="<s:url value='/captcha'/>" style="cursor:pointer; width: 110px; float:right;margin:-32px -40px 0px 0px;" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" />
		            </div>
		            <c:choose>
			      		<c:when test="${not empty loginErrorMessage}">				      		
		     			 		<span style="color: red;margin-left: 80px;">${loginErrorMessage}</span>
		     			 		&nbsp;<a href="<s:url value='/reg/forget/first'/>" style="float: right;">忘记密码？</a>
			      		</c:when>
			      		<c:otherwise>
				             <span class="forget"><a href="<s:url value='/reg/forget/first'/>">忘记密码？</a></span><br/>
			      		</c:otherwise>
			      	</c:choose>
		           
		            <div class="login_03" style="padding-top: 0px;">
		            	<input type="submit" class="l_01" value="登&#12288;录" onclick="return checkForm();" style="cursor: pointer;"/>
		            	<input type="button" class="l_02" value="注&#12288;册" onclick="regist();" style="cursor: pointer;"/>
		            </div>
	            </c:when>
			 	<c:otherwise>
			 		<div style="text-align: center; margin-top:30px;height: 140px;">
	 					<span style="font-size:18px; color:#f00;line-height:30px;">${sessionScope.currUser.userName}</span><br/>
 						<span style="font-size:18px;">您好，欢迎登录本系统!</span><br/><br/>
 						<input type="button" value="退&#12288;出" onclick="logout();" style="cursor: pointer; width: 60px;background-color:#56b571;"/>
 					</div>
			 	</c:otherwise>
		 	</c:choose>
        </form>
        </div>
    </div>
</div>

<div class="contentbox_02" id="downloadDiv">
   
</div>


<%@include file="footer.jsp" %>
</body>
</html>
