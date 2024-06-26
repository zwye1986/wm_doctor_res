<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<style type="text/css">
	.basic td,tr{border: 0}
</style>
<script type="text/javascript">
	function registOpt(){
		jboxOpen("<s:url value='/gyxjgl/user/patronageRegister'/>","任免登记",660,360);
	}
	function exportOpt(){
		var url = "<s:url value='/gyxjgl/user/patronageExcel'/>";
		jboxTip("导出中…………");
		jboxSubmit($("#recSearchForm"), url, null, null, false);
		jboxEndLoading();
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<table style="width: 100%;min-width:1080px;margin: 10px 0px;border: none;">
			<form id="recSearchForm" method="post" action="<s:url value='/gyxjgl/user/ideologyManage'/>">
				<input id="currentPage" type="hidden" name="currentPage" value="1"/>
				<tr>
					<td style="border: none;line-height: 260%;">
						年级：<input type="text" name="" value="" style="width:133px;"/>&#12288;
						姓名：<input type="text" name="" value="" style="width:133px;"/>&#12288;
						专业：<input type="text" name="" value="" style="width:133px;"/>&#12288;
						班级：<input type="text" name="" value="" style="width:133px;"/>&#12288;
						<input type="button" class="search" onclick="" value="查&#12288;询" /><br/>
						<input type="button" class="search" onclick="registOpt()" value="任免登记" />
						<input type="button" class="search" onclick="exportOpt()" value="导&#12288;出" />
					</td>
				</tr>
			</form>
		</table>
		<table class="xllist" style="width: 100%;" class="table">
			<tr style="font-weight: bold;">
				<td>年级</td>
				<td>姓名</td>
				<td>专业</td>
				<td>班级</td>
				<td>任免情况</td>
			</tr>
			<c:forEach items="${dataList}" var="info">
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</c:forEach>
			<c:if test="${empty dataList}">
				<tr><td colspan="5" >无记录！</td></tr>
			</c:if>
		</table>
		<div>
	       	<c:set var="pageView" value="${pdfn:getPageView(dataList)}" scope="request"></c:set>
			<pd:pagination toPage="toPage"/>
	    </div>
	</div>
</div>
</body>
</html>