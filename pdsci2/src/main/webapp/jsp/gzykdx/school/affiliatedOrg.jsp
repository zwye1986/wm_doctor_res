<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>
<script type="text/javascript">
	function toPage(page){
		$("#currentPage").val(page);
		$("#searchForm").submit();
	}
	function addInfo(userFlow){
		var title = userFlow == ""?"新增":"编辑";
		var url = "<s:url value='/gzykdx/school/addAffiliatedOrg?userFlow='/>"+userFlow;
		jboxOpen(url, title,600,360,true);
	}
	function resetPwd(userFlow){
		jboxConfirm("重置密码之后为：123456！", function(){
			var url = "<s:url value='/gzykdx/school/resetPwd?userFlow='/>"+userFlow;
			jboxPost(url, null, function(resp){
				if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
					location.reload();
				}
			});
		});
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form id="searchForm" action="<s:url value="/gzykdx/school/affiliatedOrgManage"/>" method="post">
			<div class="choseDivNewStyle">
				<input id="currentPage" type="hidden" name="currentPage" value="1"/>
				<span></span>机构名称：
				<select class="select" style="width:137px;" name="orgFlow">
					<option value="">全部</option>
					<c:forEach var="org" items="${applicationScope.sysOrgList}">
						<c:if test="${org.isSecondFlag eq 'Y'}">
							<option value="${org.orgFlow}" <c:if test="${param.orgFlow eq org.orgFlow}">selected="selected"</c:if>>${org.orgName}</option>
						</c:if>
					</c:forEach>
				</select>
				<span style="padding-left:20px;"></span>
				<input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
				<input type="button" class="search" value="新&#12288;增" onclick="addInfo('')"/>
			</div>
		</form>
		<table class="xllist" style="width:100%;">
			<tr>
				<th style="width:80px;">序号</th>
				<th style="width:140px;">机构名称</th>
				<th style="width:120px;">联系人</th>
				<th style="width:120px;">联系方式</th>
				<th style="width:120px;">管理员账户</th>
				<th style="width:140px;">操作</th>
			</tr>
			<c:forEach items="${dataList}" var="info" varStatus="i">
				<tr>
					<td>${i.index + 1}</td>
					<td>${info.orgName}</td>
					<td>${info.userName}</td>
					<td>${info.userPhone}</td>
					<td>${info.userCode}</td>
					<td>
						<a onclick="addInfo('${info.userFlow}');" style="cursor:pointer;color:#4195c5;"><c:out value="编辑"/></a>
						<a onclick="resetPwd('${info.userFlow}');" style="cursor:pointer;color:#4195c5;"><c:out value="重置密码"/></a>
				</td>
				</tr>
			</c:forEach>
		</table>
		<div style="margin-top:65px;">
			<c:set var="pageView" value="${pdfn:getPageView(dataList)}" scope="request"/>
			<pd:pagination toPage="toPage"/>
		</div>
	</div>
</div>
</body>	
</html>