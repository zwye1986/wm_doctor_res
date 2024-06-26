<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="basic" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="register" value="true"/>
</jsp:include>
<script type="text/javascript">
	function getInfo(userFlow){
		jboxOpen("<s:url value='/hbres/singup/manage/userInfo?flag=view'/>&userFlow="+userFlow,"用户信息",1000,550);
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
	              <a href="#" class="step2">2、邮箱激活</a>
	              <a href="#" class="step3">3、选择类型</a>
	              <a href="#" class="step4">4、信息登记</a>
	              <a href="#" class="step5 active5">5、完成</a>
	          </div>
	    <div class="fifthStep">
	            <div class="notPass_left">
	            <img src="<s:url value='/jsp/hbres/images/waiting.jpg'/>"  />
		  			报名资格审核会在准考证打印（${recruitCfg.printBeginDate}）前全部完成，请耐心等待，<br/>
					随时关注系统动态，以免错过退回修改的信息，一旦错过将无法完成规培报名，请相互转告！
	            </div>
	            <div class="notPass_right">
                  <p style="text-align: left;padding-left: 50px;padding-right: 50px;">
                                                    您的信息已提交审核，请耐心等待!
                 <br/><br/>
                                                    请妥善保管以下信息！可通过邮箱/身份证号/手机号登录系统
                                                    查询审核结果及进行后续报名操作(如：打印准考证、填报志愿等)
                 <br/> <br/>                                  
                                                   您的注册邮箱为：${userEmail}<br/><br/>
                                                   您的注册身份证号为：${userIdno}<br/><br/>
                                                   您的注册手机号为：${userPhone}<br/><br/>
												<c:if test="${!empty sessionScope.currUser}">
					                               <a onclick="getInfo('${userFlow}')">学员详情</a>
												</c:if><br/><br/>
					  招录报名截止时间：${recruitCfg.regBeginDate}~${recruitCfg.regEndDate}<br/>
					  打印准考证日期范围：${recruitCfg.printBeginDate}~${recruitCfg.printEndDate}<br/>
					  志愿填报日期范围：${recruitCfg.wishBeginDate}~${recruitCfg.wishEndDate}<br/>
					  确认录取结果日期范围：${recruitCfg.admitBeginDate}~${recruitCfg.admitEndDate}<br/>
					  学员调剂日期范围：${recruitCfg.swapBeginDate}~${recruitCfg.swapEndDate}
				  </p>

					  <img style="margin: 0" src="<s:url value="/jsp/inx/hbres/images/erweima.png" />"/>
					  <p style="color:red;line-height: 17px;text-align: left;padding-left:50px;">关注“湖北住培”微信公众号，<br>掌握招录住培最新动态，了解住培最新咨询！</p>
                </div>
	          </div>
	  </div>
	  
	</div>
	<c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
		<jsp:include page="/jsp/service.jsp"></jsp:include>
	</c:if>
    <jsp:include page="/jsp/hbres/foot.jsp" />
</body>
</html>