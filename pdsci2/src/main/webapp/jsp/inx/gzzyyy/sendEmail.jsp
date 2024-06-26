<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="register" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="true"/>
</jsp:include>
<script type="text/javascript">
function sendEmail(){
	jboxTip("发送成功");
	countDown(60);
}

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
</script>
</head>
<body>
	<div class="yw">
    <div class="register_top" onclick="window.location.href='<s:url value='/inx/jszy'/>'" style="cursor: pointer;">${sysCfgMap['sys_title_name']}</div>
    <div class="register_content" style="text-align: center;">
		<div class="notPass wjpsw" >
   <div style="width: 880px;margin:0 auto;text-align: left; border:1px solid #DADADA; background: #F9F9F9;">
       <div id="operDiv" style="margin-bottom: 50px;">
		<h1 class="reg_title">帐号激活</h1>
	     <div style="margin-left: 50px;margin-top: 20px;">
                <ul class="noEamil" style="margin-top:30px;margin-left: 52px; "> 
               	<li>您也可以 通过点击下方激活链接进行快速激活操作</li>
             	 	 <li><a href='<s:url value="/inx/jszy/activateuser?activationCode=${activationCode}"/>'>点此链接激活</a></li>
                </ul>
	     </div>
	     </div>
	</div>
  </div>
    </div>
</div>
<c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
		<jsp:include page="/jsp/service.jsp"></jsp:include>
	</c:if>
<div class="footer">主管单位：江苏省卫生厅科教处 | 协管单位：江苏省毕业后医学继续教育委员会</div>
</body>
</html>