<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="register" value="true"/>
</jsp:include>
</head>
<body>
<div class="yw">
    <div class="top" onclick="location.href='<s:url value='/inx/nfykdx'/>'" style="cursor: pointer;">南方医科大学研究生管理系统</div>
    <div class="content" style="text-align: center;">
		<div class="notPass wjpsw" >
	   		<div style="width: 880px;margin:0 auto;text-align: left; border:1px solid #DADADA; background: #F9F9F9;">
		   		<div id="operDiv" style="margin-bottom: 50px;">
					<h1 class="reg_title">导师注册</h1>
					<div style="margin-left: 50px;margin-top: 20px;">
							注册成功！<a href="<s:url value='/inx/nfykdx'/>">点击登录</a>
					</div>
			 	</div>
			</div>
		</div>
    </div>
</div>
<div class="footer">技术支持：南京品德网络信息技术有限公司</div>
</body>
</html>