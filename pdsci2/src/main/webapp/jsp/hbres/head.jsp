
<div class="head">
	<div class="head_inner">
		<h1 class="logo">
			<a href="<s:url value='${param.indexUrl}'/>">江苏省住院医师规范化培训管理平台—督导管理</a>
		</h1>
		<c:if test="${not param.notShowAccount}">
		<div class="account">
			<h2>${sessionScope.currUser.orgName }&emsp;${empty param.logName?'':sessionScope.currUser.userName}</h2>
			<div class="head_right">
				<c:if test="${param.logName ne 'graduate'}">
				<a href="<s:url value='${param.indexUrl}'/>">首页</a>&#12288;
				</c:if>
				<c:if test="${param.AuditGraduationRole ne 'Y'}">
				<a href="#" onclick="showSelect();">系统切换</a>&#12288;
				</c:if>
				<a href="<s:url value='/inx/hbres/logout'/>">退出</a>
			</div>
		</div>
		</c:if>
	</div>
</div>