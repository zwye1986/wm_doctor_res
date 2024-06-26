<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script>
</script>
<style type="text/css">
</style>
<script type="text/javascript">
	function edit(deptFlow,orgFlow)
	{
		if(deptFlow)
		{
			var url="<s:url value="/res/inprocess/info/global"/>?deptFlow="+deptFlow+"&orgFlow="+orgFlow;
			var width = (window.screen.width) * 0.7;
			var height = (window.screen.height) * 0.7;
			jboxStartLoading();
			jboxOpen(url, "编辑科室入科教育信息", width, height);
		}else{
			jboxTip("请选择需要编辑的信息！");
		}
	}

	function refresh(){
		jboxStartLoading();
		window.location.reload(true);
		jboxEndLoading();
	}
	function toPage(page) {
		if(page){
			$("#currentPage").val(page);
		}
		search();
	}
	function search() {
		jboxStartLoading();
		$("#searchForm").submit();
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<c:if test="${role eq GlobalConstant.DEPT_LIST_GLOBAL || role eq GlobalConstant.USER_LIST_UNIVERSITY}">
				<form id="searchForm" action="<s:url value="/res/inprocess/list/${role}" />" method="post">
					<input id="currentPage" type="hidden" name="currentPage" value=""/>
					<div class="queryDiv" style="padding-left: 20px;max-width: 980px;min-width:980px;">
							<div class="inputDiv">
								<label class="qlable">培训基地：</label>
								<select name="orgFlow" class="qselect" >
									<option value="">全部</option>
									<c:forEach items="${orgs}" var="org">
										<option value="${org.orgFlow}" ${param.orgFlow eq org.orgFlow?'selected':''}>${org.orgName}</option>
									</c:forEach>
								</select>
							</div>
						<div class="lastDiv">
							<input type="button" class="searchInput" onclick="search();" value="查&#12288;询">
						</div>
					</div>
				</form>
			</c:if>
			<div id="base">
				<table class="xllist">
						<colgroup>
							<col width="20%"/>
							<col width="20%"/>
							<col width="25%"/>
							<col width="20%"/>
							<col width="15%"/>
						</colgroup>
					<tr>
						<th style="text-align: center;">培训基地</th>
						<th style="text-align: center;">科室名称</th>
						<th style="text-align: center;">入科教育信息</th>
						<th style="text-align: center;">发布时间</th>
						<th style="text-align: center;">操作</th>
					</tr>
					<c:if test="${not empty infos}">
						<c:forEach items="${infos}" var="info" varStatus="num">
							<tr>
								<td>${info.orgName }</td>
								<td>${info.deptName }</td>
								<td>入科教育信息</td>
								<td>${pdfn:transDate(info.createTime)}</td>
								<td>
									<a onclick="edit('${info.deptFlow}','${info.orgFlow}');" style="color:blue;cursor: pointer;">查看</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty infos}">
						<tr>
							<td colspan="10">暂无信息！</td>
						</tr>
					</c:if>
				</table>
			</div>
			<div class="resultDiv">
				<c:set var="pageView" value="${pdfn:getPageView(infos)}" scope="request"></c:set>
				<pd:pagination toPage="toPage"/>
			</div>
		</div>
	</div>
</div>
</body>
</html>