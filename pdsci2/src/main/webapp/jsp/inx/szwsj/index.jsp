<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
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
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/inx/szwsj/css/basic.css'/>" /> 
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/inx/szwsj/css/font.css'/>" />  

<style>
body,html{
	overflow:auto;
	position: relative;
}
</style>
<style>
#player{position:relative;width:320px;height:233px;overflow:hidden; border-right:1px solid #ccc;float: left;}
#player a{color:#333;}
#player *{border:0;padding:0;margin:0;}
#player .Limg{position:relative;}
#player .Limg li{position:absolute;top:0;left:0;background:#fff;}
#player .Limg li img{border:1px solid #FFFFFF; margin:0; width:320px;height:205px;}
#player .Limg li p{line-height:25px; font-size:14px; text-align:center; color:#000;}
#player .Nubbt{position:absolute;z-index:9;right:5px;bottom:28px; line-height:25px;}
#player .Nubbt span{ float:left; border:1px solid #999999;background:#121210;padding:1px 5px;margin:0 2px; font-style:normal;cursor:pointer; color:#FFFFFF;}
#player .Nubbt span.on{background:#CC0000;color:#FFFFFF;}
</style>

<script language=javascript type="text/javascript">
function reloadVerifyCode()
{
	$("#verifyImage").attr("src","<s:url value='/captcha'/>?random="+Math.random());
}

function checkForm(){
	if(false==$("#loginForm").validationEngine("validate")){
		return false;
	}
	return true;
}

function doSearch() {
	document.forms[0].action="<s:url value='/inx/szwsj/doSearch?'/>";
	document.forms[0].submit();
}

function doClose(){
	window.opener = null;
	window.close();
}

function logout(){
	window.location.href="<s:url value='/logout.do'/>";
}

function regist(){
	window.location.href="<s:url value='/reg/srm/go'/>";
}

$(document).ready(function(){
	jboxLoad("player","<s:url value='/inx/szwsj/queryImgNews?jsp=inx/szwsj/imgNews_list&endIndex=5'/>");
	jboxLoad("news_li","<s:url value='/inx/szwsj/queryData?columnId=LM01&jsp=inx/szwsj/news_list&endIndex=9'/>");
	jboxLoad("down","<s:url value='/inx/szwsj/queryData?columnId=LM02&jsp=inx/szwsj/download_list&endIndex=6'/>");
	jboxLoad("notice","<s:url value='/inx/szwsj/queryData?columnId=LM03&jsp=inx/szwsj/notice_list&endIndex=6'/>");
	jboxLoad("train","<s:url value='/inx/szwsj/queryData?columnId=LM05&jsp=inx/szwsj/train_list&endIndex=6'/>");
	jboxLoad("law","<s:url value='/inx/szwsj/queryData?columnId=LM04&jsp=inx/szwsj/law_list&endIndex=6'/>");
	
	/* jboxLoad("expert","<s:url value='/inx/wxwsj/queryData?columnId=LM05&jsp=inx/wxwsj/index_expert&endIndex=7&imgUrl=${GlobalConstant.FLAG_Y}'/>"); */
});

</script>

</head>

<body>
<div id="menhu">
	<%@include file="header.jsp" %>
	<div id="menhu_con">
		<div class="mh_user">
			<%-- <form id="loginForm" action="<s:url value='/inx/szwsj/inxLogin/login'/>" method="post"> --%>
			 <div class="yh">
			 <form id="loginForm" action="<s:url value='/login'/>" method="post" style="width:281px; height:190px; overflow:hidden;">
			   <c:choose>
			 	<c:when test="${empty sessionScope.currUser }">
			 		<dl>
				      <dt class="mh_l">用户名</dt>
				      <dd class="mh_r">
				     	 <input name="userCode" type="text" class="validate[required] logo_text kuang" style="width: 160px;" placeholder="用户名/手机号/Email" value=""/>
					  </dd>
				      <dt class="mh_l">密  码</dt>
				      <dd class="mh_r">
				      	 <input name="userPasswd" type="password" class="validate[required] logo_text kuang" style="width: 160px;" placeholder=""/>
				      </dd>
				      <dt class="mh_l">验证码</dt>
				      <dd class="mh_r">
				     	 <input name="verifyCode" type="text" class="validate[required] logo_text" style="width: 70px;" placeholder=""/>
				     	 <img id="verifyImage" src="<s:url value='/captcha'/>" style="cursor:pointer; width: 110px; float: left;" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" />
				      </dd>
				      <dt class="mh_l">&nbsp;</dt>
      				  <dd class="mh_r">
      				  	<c:choose>
				      		<c:when test="${not empty loginErrorMessage}">				      		
				     			<p>&nbsp;${loginErrorMessage}</p>
				      		</c:when>
				      		<c:otherwise>
				      		<p><a href="<s:url value='/reg/forget/first'/>">忘记密码?</a></p>
				      		</c:otherwise>
				      	</c:choose>
				      </dd>
				      
				      <dt class="mh_l">
				      	<input type="hidden" name="errorLoginPage" value="inx/szwsj/index"/>
				      	<input type="hidden" name="successLoginPage" value="inx/szwsj/index"/>
				      </dt>
				      <dd class="mh_r">
				      	<input type="submit" class="mh_denglu" value="登 录" onclick="return checkForm();"/>
				      	<input type="button" class="mh_quxiao" value="注 册" onclick="regist();"/>
				      </dd>
				   </dl>
			 	</c:when>
			 	<c:otherwise>
			 		<span class="us">${sessionScope.currUser.userName}</span>
			 		<span>您好，欢迎登录本系统!!</span>
        			<span style="text-align:center;">
        				<input type="button" class="mh_denglu" value="退  出" onclick="logout();"/>
        				<input type="button" class="mh_quxiao" value="关  闭" onclick="doClose();"/>
        			</span>
			 	</c:otherwise>
			 </c:choose>
	    	</form>
			 </div>
	    </div>
	    
	    
	    <div id="news">
	      <h1><span><a href="<s:url value='/inx/szwsj/queryByColumnId?columnId=LM01&id=xwzx'/>">&gt;&gt;more</a></span>新闻中心</h1>
	     
	 		<div id="player">
	 			<!-- 新闻中心 -->
			</div> 
	
			<div id="news_li" class="news_li">			
			
			</div>
	    </div>
	    
	    <div class="down" id="down">
			<!-- 下载中心 -->
		</div>
		
	    <div class="notice" id="notice">
			<!-- 通知公告 -->
	    </div> 
	    
		<div class="train" id="train">
			<!-- 继续教育 -->
		</div>
	    
		<div class="law" id="law">
			<!-- 政策法规 -->
		</div>
	    
	     <div class="link">
			 <img src="<s:url value='/'/>jsp/inx/szwsj/images/portal/link.png" width="970" height="44" usemap="#Map"
				  style="text-align:center;" border="0"/>
	    <map name="Map" id="Map">
	      <area shape="rect" coords="4,5,133,40" href="http://wsb.moh.gov.cn/" title="国家卫生部" />
	      <area shape="rect" coords="164,3,315,42" href="http://www.jswst.gov.cn/index.html"  title="江苏省省卫生厅" />
	      <area shape="rect" coords="347,4,479,43" href="http://wskj.jswst.gov.cn/Plugin/WebSite/Index.aspx"  title="江苏省卫生厅科教信息管理平台"/>
	      <area shape="rect" coords="509,3,642,41" href="http://www.suzhou.gov.cn/"  title="苏州市政府"/>
	      <area shape="rect" coords="677,3,809,42" href="http://www.szwsj.gov.cn/"  title="苏州市卫生计生委"/>
	      <area shape="rect" coords="836,4,970,45" href="http://kjxm.szkj.gov.cn/"  title="苏州市科技局" />
	    </map>
	    </div>
	 </div>
	<%@include file="footer.jsp" %>
</div>
</body>
</html>