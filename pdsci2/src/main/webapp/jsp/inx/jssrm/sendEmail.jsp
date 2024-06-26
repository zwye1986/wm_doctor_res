
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_placeholder" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_datePicker2" value="true"/>
		<jsp:param name="jquery_fullcalendar" value="true"/>
	</jsp:include>
	<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jssrm/css/style.css'/>"></link>
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
	var url = "<s:url value='/inx/jssrm/reSendEmail'/>?userEmail=${userEmail}";
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
    <div class="top" onclick="window.location.href='<s:url value='/inx/jssrm'/>'" style="cursor: pointer;">${sysCfgMap['sys_title_name']}</div>
    <div class="content" style="text-align: center;">
	<div class="notPass wjpsw" >
	<div style="width: 880px;margin:0 auto;text-align: left; border:1px solid #DADADA; background: #F9F9F9;">
       <div id="operDiv" style="margin-bottom: 50px;">
		<h1 class="reg_title">邮箱激活</h1>
	     <div style="margin-left: 50px;margin-top: 20px;">
	     		<dl class="clearfix">
					<dt class="fl"><img src="<s:url value='/css/skin/Email.png'/>" width="48" height="48"/></dt>
                    <dd class="fl">激活账号<br><span  style="color: #999;">感谢注册！确认邮件已发送至你的注册邮箱:${userEmail }。请进入邮箱查看邮件，并激活账号。</span></dd>
               </dl>
               <c:set var="mailArray" value="${fn:split(userEmail,'@') }"/>
               <div class="buts" style="display: none;"><a href="http://mail.${mailArray[1]}" target="_blank" class="button button-blue">登录邮箱</a></div>
               
               <ul class="noEamil" style="margin-top:30px;margin-left: 52px; ">
                  <li>没收到邮件？</li>
                  <li>1、请检查邮箱地址是否正确，你可以返回<a href="<s:url value='/inx/jssrm/register'/>">重新填写</a></li>
                  <li>2、检查你的邮件垃圾箱</li>
                  <li>3、若仍未收到确认，<span id="resendA">请尝试<a href="javascript:reSendEmail();">重新发送</a></span><span id="jumpSapn"><span id="jumpTo" style="color:#558abe;">60</span>秒后请尝试重新发送</span>
                  </li>
              </ul>
              <ul class="noEamil" style="margin-top:30px;margin-left: 52px; ">
	           		<li>您也可以 通过点击下方激活链接进行快速激活操作<br/>
	           		<font style="color: red">(请确保您填写的邮箱真实有效，以便您可以接收到报名审核结果与录取通知)</font></li>
	           		<li><a href='<s:url value="/inx/jssrm/activateuser?activationCode=${activationCode}"/>' style="font-size: 18px;">点此链接激活</a></li>
	           		<li><a href="<s:url value='/inx/jssrm'/>" class="line_c">返回登录</a></li>
               </ul>
	     </div>
	     </div>
	</div>
  </div>
    </div>
</div>
<div class="footer">主管单位：江苏省卫生和计划生育委员会</div>
</body>
</html>