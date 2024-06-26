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
	function edit(deptFlow)
	{
		if(deptFlow)
		{
			var url="<s:url value="/res/inprocess/info/admin"/>?deptFlow="+deptFlow;
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
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<div id="base">
				<table class="xllist">
						<colgroup>
							<col width="25%"/>
							<col width="25%"/>
							<col width="25%"/>
							<col width="25%"/>
						</colgroup>
					<tr>
						<th style="text-align: center;">科室名称</th>
						<th style="text-align: center;">入科教育信息</th>
						<th style="text-align: center;">发布时间</th>
						<th style="text-align: center;">操作</th>
					</tr>
					<c:if test="${not empty infos}">
						<c:forEach items="${infos}" var="info" varStatus="num">
							<tr>
								<td>${info.deptName }</td>
								<td>入科教育信息</td>
								<td>${pdfn:transDate(info.createTime)}</td>
								<td>
									<a onclick="edit('${info.deptFlow}');" style="color:blue;cursor: pointer;">编辑</a>
									<%--<input type="button" value="编&#12288;辑" class="search" onclick="edit('${info.deptFlow}');"/>--%>
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
		</div>
	</div>
</div>
</body>
</html>