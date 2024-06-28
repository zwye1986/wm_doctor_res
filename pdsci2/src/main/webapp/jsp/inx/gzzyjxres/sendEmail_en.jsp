
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/gzzyjxres/htmlhead-gzzyjxres_en.jsp">
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
	jboxTip("Send successfully!");
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
		<div class="top" onclick="window.location.href='<s:url value='/inx/gzzyjxrecruit'/>'" style="cursor: pointer;">${sysCfgMap['jx_top_name_en']}</div>

        <div class="content" style="text-align: center;">
		<div class="notPass wjpsw" >
   <div style="width: 880px;margin:0 auto;text-align: left; border:1px solid #DADADA; background: #F9F9F9;">
       <div id="operDiv" style="margin-bottom: 50px;">
		<%--<h1 class="reg_title">邮箱激活</h1>--%>
	     <div <%--style="margin-left: 50px;margin-top: 20px;"--%>>
	     		<%--<dl class="clearfix">
					<dt class="fl"><img src="<s:url value='/css/skin/Email.png'/>" width="48" height="48"/></dt>
                    <dd class="fl">激活账号<br><span  style="color: #999;">感谢注册！确认邮件已发送至你的注册邮箱:${userEmail }。请进入邮箱查看邮件，并激活账号。</span></dd>
               </dl>
               <c:set var="mailArray" value="${fn:split(userEmail,'@') }"/>
               <div class="buts" style="display: none;"><a href="http://mail.${mailArray[1]}" target="_blank" class="button button-blue">登录邮箱</a></div>
               
               <ul class="noEamil" style="margin-top:30px;margin-left: 52px; ">
                  <li>没收到邮件？</li>
                  <li>1、请检查邮箱地址是否正确，你可以返回<a href="<s:url value='/jsp/inx/gzzyjxres/register.jsp'/>">重新填写</a></li>
                  <li>2、检查你的邮件垃圾箱</li>
                  <li>3、若仍未收到确认，<span id="resendA">请尝试<a href="javascript:sendEmail();">重新发送</a></span><span id="jumpSapn"><span id="jumpTo" style="color:#558abe;">60</span>秒后请尝试重新发送</span>
                  </li>
               </ul>--%>
                <ul class="noEamil" style="margin-top:50px;/*margin-left: 52px;*/ text-align: center;font-size: 20px; ">
               	<%--<li>您也可以 通过点击下方激活链接进行快速激活操作<br/>
               	<font style="color: red">(请确保您填写的邮箱真实有效，以便您可以接收到报名审核结果与录取通知)</font></li>--%>
             	 	 <li><a href='<s:url value="/inx/gzzyjxrecruit/activateuser?activationCode=${activationCode}"/>'>Click this link to activate</a></li>
                </ul>
	     </div>
	     </div>
	</div>
  </div>
    </div>
</div>
<c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
		<jsp:include page="/jsp/service.jsp" flush="true"></jsp:include>
	</c:if>
<%--<div class="footer">--%>
	<%--<c:if test="${sysCfgMap['jx_templete_name'] eq 'cd'}">--%>
	<%--主管单位：四川省卫生厅科教处 | 协管单位：四川省毕业后医学继续教育委员会--%>
	<%--</c:if>--%>
	<%--<c:if test="${sysCfgMap['jx_templete_name'] eq 'xz'}">--%>
		<%--主办单位：徐州中心医院--%>
	<%--</c:if>--%>
<%--</div>--%>
    <div class="footer"><jsp:include page="/jsp/gzzyjxres/english_foot.jsp" flush="true"/></div>

</body>
</html>