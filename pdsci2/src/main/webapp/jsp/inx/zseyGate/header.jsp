<%@include file="/jsp/common/doctype.jsp" %>

	<div class="banner">
		<img src="<s:url value='/jsp/inx/zseyGate/images/${empty param.roleFlow ?\'All\':param.roleFlow}_banner.png'/>">
	</div>
	<c:if test="${(param.roleFlow eq 'All' or empty param.roleFlow)  or param.notTab eq 'Y' }">
		<div class="nav">
			<li id="All"><a href="<s:url value='/inx/zseyGate?roleFlow=All&modelId=${empty param.modelId ?\'All\':param.modelId}'/>" <c:if test="${param.roleFlow eq 'All' or empty param.roleFlow}">class="select" </c:if>>教学网站</a></li>
			<li id="1f579eecdd974b52899320f0f226aac4"><a href="<s:url value='/inx/zseyGate?roleFlow=1f579eecdd974b52899320f0f226aac4&modelId=${empty param.modelId ?\'All\':param.modelId}'/>"<c:if test="${param.roleFlow eq '1f579eecdd974b52899320f0f226aac4'}">class="select" </c:if>>本科生教育</a></li>
			<li id="f2621a36794b490b9ee2ad1184a1a996"><a href="<s:url value='/inx/zseyGate?roleFlow=f2621a36794b490b9ee2ad1184a1a996&modelId=${empty param.modelId ?\'All\':param.modelId}'/>"<c:if test="${param.roleFlow eq 'f2621a36794b490b9ee2ad1184a1a996'}">class="select" </c:if>>研究生教育</a></li>
			<li id="0b9e7ef396a24e129d09131bbd4f1ff4"><a href="<s:url value='/inx/zseyGate?roleFlow=0b9e7ef396a24e129d09131bbd4f1ff4&modelId=${empty param.modelId ?\'All\':param.modelId}'/>"<c:if test="${param.roleFlow eq '0b9e7ef396a24e129d09131bbd4f1ff4'}">class="select" </c:if>>住院医师培训</a></li>
			<li id="e2f1f47808334ed3976b4bcf4cffb43e"><a href="<s:url value='/inx/zseyGate?roleFlow=e2f1f47808334ed3976b4bcf4cffb43e&modelId=${empty param.modelId ?\'All\':param.modelId}'/>"<c:if test="${param.roleFlow eq 'e2f1f47808334ed3976b4bcf4cffb43e'}">class="select" </c:if>>进修管理</a></li>
			<li id="642caf7e58494875b528734dcf8b6829"><a href="<s:url value='/inx/zseyGate?roleFlow=642caf7e58494875b528734dcf8b6829&tabId=tab1&modelId=${empty param.modelId ?\'All\':param.modelId}'/>"<c:if test="${param.roleFlow eq '642caf7e58494875b528734dcf8b6829'}">class="select" </c:if>>护理教育</a></li>
		</div>
	</c:if>
	<c:if test="${param.roleFlow eq '1f579eecdd974b52899320f0f226aac4' and  param.notTab ne 'Y'}">
		<jsp:include page="headers/1f579eecdd974b52899320f0f226aac4.jsp">
			<jsp:param value="${ param.roleFlow }" name="roleFlow"/>
			<jsp:param value="${ param.modelId }" name="modelId"/>
		</jsp:include>
	</c:if>
	<c:if test="${param.roleFlow eq 'f2621a36794b490b9ee2ad1184a1a996' and  param.notTab ne 'Y'}">
		<jsp:include page="headers/f2621a36794b490b9ee2ad1184a1a996.jsp">
			<jsp:param value="${ param.roleFlow }" name="roleFlow"/>
			<jsp:param value="${ param.modelId }" name="modelId"/>
		</jsp:include>
	</c:if>
	<c:if test="${param.roleFlow eq '0b9e7ef396a24e129d09131bbd4f1ff4' and  param.notTab ne 'Y'}">
		<jsp:include page="headers/0b9e7ef396a24e129d09131bbd4f1ff4.jsp">
			<jsp:param value="${ param.roleFlow }" name="roleFlow"/>
			<jsp:param value="${ param.modelId }" name="modelId"/>
		</jsp:include>
	</c:if>
	<c:if test="${param.roleFlow eq 'e2f1f47808334ed3976b4bcf4cffb43e' and  param.notTab ne 'Y'}">
		<jsp:include page="headers/e2f1f47808334ed3976b4bcf4cffb43e.jsp">
			<jsp:param value="${ param.roleFlow }" name="roleFlow"/>
			<jsp:param value="${ param.modelId }" name="modelId"/>
		</jsp:include>
	</c:if>
	<c:if test="${param.roleFlow eq '642caf7e58494875b528734dcf8b6829' and  param.notTab ne 'Y'}">
		<jsp:include page="headers/642caf7e58494875b528734dcf8b6829.jsp">
			<jsp:param value="${ param.roleFlow }" name="roleFlow"/>
			<jsp:param value="${ param.modelId }" name="modelId"/>
			<jsp:param value="${ param.tabId }" name="tabId"/>
		</jsp:include>
	</c:if>
	<script>
		$(document).ready(function(){
			var modelId="${empty param.modelId ?'All':param.modelId}";
			var btns=sysMap[modelId].funcList;
			if(btns)
			{
				$(".nav").find("li").hide();
				var strings=btns.split(",");
				for(var i=0;i<strings.length;i++)
				{
					$("#"+strings[i]).show();
				}
			}
		});
		function  showLogin(menuId,modelId,wsId)
		{
			var url="<s:url value='/inx/zseyGate/gateLoginPage'/>?menuId="+menuId+"&modelId="+modelId+"&wsId="+wsId;
			console.log(url);
			jboxOpen(url,"用户登录",500,400);
		}
	</script>