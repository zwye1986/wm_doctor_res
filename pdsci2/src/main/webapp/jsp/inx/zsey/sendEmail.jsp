<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="register" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">
function sendEmail(){
	var url = "<s:url value='/inx/hbres/reSendEmail'/>?userEmail=${userEmail}";
	jboxPost(url,null,function(resp){
		if(resp=="${GlobalConstant.FLAG_Y}"){
			jboxTip("发送成功");
			countDown(60);
		}else{
			jboxTip("发送失败");
		}
	},null,false);
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
    <jsp:include page="/jsp/hbres/head.jsp">
         <jsp:param value="/inx/hbres" name="indexUrl"/>
         <jsp:param value="true" name="notShowAccount"/>
     </jsp:include>
  
  <div class="content">
  
          <div class="step">
              <a href="#" class="step1">1、基本信息</a>
              <a href="#" class="step2  active2">2、邮箱激活</a>
              <a href="#" class="step3">3、选择类型</a>
              <a href="#" class="step4">4、信息登记</a>
              <a href="#" class="step5">5、完成</a>
          </div>
          
          <div class="secondStep" >
          
          	   <dl class="clearfix">
				   <dt class="fl"><img src="<s:url value='/css/skin/Email.png'/>" width="48" height="48"/></dt>
                    <dd class="fl">因报名时间集中，系统会短时间会发送很多激活账号邮件，导致qq邮箱，163邮箱等拒收招录系统发送的邮件，导致很多学员无法正常注册报名，请按下面指示进行操作,邮箱会稍后通知您进行确认,感谢您的理解！</p></dd>
               </dl>
            
               <dl class="clearfix" style="display: none;">
				   <dt class="fl"><img src="<s:url value='/css/skin/Email.png'/>" width="48" height="48"/></dt>
                    <dd class="fl">激活账号<p>感谢注册！确认邮件已发送至你的注册邮箱${userEmail}。请进入邮箱查看邮件，并激活账号。</p></dd>
               </dl>
               <c:set var="mailArray" value="${fn:split(userEmail,'@') }"/>
               <div class="buts" style="display: none;"><a href="http://mail.${mailArray[1]}" target="_blank" class="button button-blue">登录邮箱</a></div>
               
               <ul class="noEamil" style="display: none;">
                  <li>没收到邮件？</li>
                  <li>1、请检查邮箱地址是否正确，你可以返回<a href="<s:url value='/inx/hbres/register'/>">重新填写</a></li>
                  <li>2、检查你的邮件垃圾箱</li>
                  <li>3、若仍未收到确认，<span id="resendA">请尝试<a href="javascript:sendEmail();">重新发送</a></span><span id="jumpSapn"><span id="jumpTo" style="color:#558abe;">60</span>秒后请尝试重新发送</span>
                  </li>
               </ul>
                
                <ul class="noEamil" style="display: ${sysCfgMap['res_show_mail_content'] eq GlobalConstant.FLAG_Y?'':''};"> 
                	<li>
                		<p style="color: rgb(51, 51, 51); font-family: Verdana, sans-serif; font-size: 14px; line-height: 21px; white-space: normal; background-color: rgb(255, 255, 255);">
		            	你好!
		            	</p>
		            	<p style="color: rgb(51, 51, 51); font-family: Verdana, sans-serif; font-size: 14px; line-height: 21px; white-space: normal; background-color: rgb(255, 255, 255);">
		            	感谢你注册湖北省住院医师报名招录系统。&nbsp;
		            	<br/>
		            	你的登录邮箱为：${userEmail}。请点击以下链接激活帐号：
		            	<a href='http://hb.ezhupei.com/pdsci/inx/hbres/completeUserInfo?activationCode=${activeFlow}'>
							http://hb.ezhupei.com/pdsci/inx/hbres/completeUserInfo?activationCode=${activeFlow}
		            	</a>
		            	</p>
		            	<p style="color: rgb(51, 51, 51); font-family: Verdana, sans-serif; font-size: 14px; line-height: 21px; white-space: normal; background-color: rgb(255, 255, 255);">
		            	如果以上链接无法点击，请将上面的地址复制到你的浏览器(如IE)的地址栏进入系统。 （该链接在48小时内有效，48小时后需要重新注册）
		            	</p>
		            	<p>
		            	<br/>
		            	</p>
                	</li>
                </ul>
            </div>
  
  </div>
</div>
<c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
		<jsp:include page="/jsp/service.jsp"></jsp:include>
	</c:if>
<jsp:include page="/jsp/hbres/foot.jsp" />
</body>
</html>