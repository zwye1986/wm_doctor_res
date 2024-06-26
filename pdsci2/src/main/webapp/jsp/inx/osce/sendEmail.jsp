<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="register" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="true"/>
</jsp:include>
<script type="text/javascript">
$(document).ready(function(){
	countDown(60);
});

function countDown(secs){     
	 var jumpTo = document.getElementById('jumpTo');
	 jumpTo.innerHTML=secs;  
	 if(--secs>0){
		 $("#jumpSapn").show();
		 $("#resendA").hide();
		 setTimeout("countDown("+secs+")",1000);
	 }else{       
	     $("#resendA").show(); 
	     $("#jumpSapn").hide();
	 }     
}

function reSendEmail(){
	var url = "<s:url value='/inx/jsres/reSendEmail'/>?userEmail=${userEmail}";
	jboxPost(url, null, function(resp){
		if("${GlobalConstant.FLAG_Y}" == resp){
			jboxTip("发送成功");
			countDown(60);
		}else{
			jboxTip("发送失败");
		}
	}, null, false);
}
</script>
</head>
<body>
	<div class="yw">
    <div class="top">临床技能考核管理系统（OSCE）</div>
    <div class="content" style="text-align: center;">
	<div class="notPass wjpsw" >
	<div style="width: 880px;margin:0 auto;text-align: left; border:1px solid #DADADA; background: #F9F9F9;">
       <div id="operDiv" style="margin-bottom: 50px;">
		<h1 class="reg_title">注册成功</h1>
	     <div style="margin-left: 50px;margin-top: 15px;">
              <ul class="noEamil" style="margin-top:15px;margin-left: 52px; ">
	           		<li>请点击下方链接返回登录页面登录<br/>
	           		<li><a href="<s:url value='/inx/osce'/>" class="line_c">返回登录</a></li>
               </ul>
	     </div>
	     </div>
	</div>
  </div>
    </div>
</div>
	<div class="footer">技术支持：南京品德网络信息技术有限公司 </div>
</body>
</html>