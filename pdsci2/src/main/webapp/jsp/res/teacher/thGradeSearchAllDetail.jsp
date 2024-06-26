<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="false"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
		<jsp:param name="jquery_ui_combobox" value="false"/>
		<jsp:param name="jquery_ui_sortable" value="false"/>
		<jsp:param name="jquery_cxselect" value="false"/>
		<jsp:param name="jquery_scrollTo" value="false"/>
		<jsp:param name="jquery_jcallout" value="false"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_fullcalendar" value="false"/>
		<jsp:param name="jquery_fngantt" value="false"/>
		<jsp:param name="jquery_fixedtableheader" value="true"/>
		<jsp:param name="jquery_placeholder" value="true"/>
		<jsp:param name="jquery_iealert" value="false"/>
	</jsp:include>
	<script type="text/javascript">
		function printDetail (){
			var headstr = "<html><head><title></title></head><body>";
			var footstr = "</body>";
			var newstr = $(".context").html();
			var oldstr = document.body.innerHTML;
			document.body.innerHTML = headstr+newstr+footstr;
			window.print();
			document.body.innerHTML = oldstr;
			return false;
		}
		function exportInfo()
		{
			jboxExpLoading($("#searchForm"),"<s:url value='/thres/head/exportInfoDetail'/>");
		}

	</script>
	<style>
		.basic tbody th{
			text-align: center;
		}
		.basic tbody td{
			padding: 0 0 0 0;
			text-align: center;
		}
	</style>
</head>
<body>
<form id="searchForm" method="post">
	<input type="hidden" value="${param.deptFlow}" name="deptFlow">
	<input type="hidden" value="${param.operStartDate}" name="operStartDate">
	<input type="hidden" value="${param.operEndDate}" name="operEndDate">
</form>
	<div style="width:100%;max-height: 500px;overflow: auto">
	<div  class="context">
		<c:if test="${empty evaluationCfg}">
			<div>
				未配置评分表
			</div>
		</c:if>
		<c:if test="${not empty evaluationCfg}">
			<table class="basic list" width="100%">

				<colgroup>
					<col width="300px"/>
					<col width="50px"/>
					<col width="70px"/>
					<col width="50px"/>
					<col width="70px"/>
					<col width="50px"/>
					<col width="70px"/>
					<col width="50px"/>
					<col width="70px"/>
				</colgroup>
				<tr>
					<th colspan="9">
						${evaluationCfg.cfgCodeName}（分析汇总）
					</th>
				</tr>
				<tr>
					<th >
						医院科室：${dept.deptName}
					</th>
					<th colspan="4">
						出科时间：${param.operStartDate}~${param.operEndDate}
					</th>
					<th colspan="4">
						份数：${fn:length(datas)+0}
					</th>
				</tr>
				<tr>
					<td>
						考评项
					</td>
					<td>
						优
					</td>
					<td>
						百分比
					</td>
					<td>
						良
					</td>
					<td>
						百分比
					</td>
					<td>
						中
					</td>
					<td>
						百分比
					</td>
					<td>
						差
					</td>
					<td>
						百分比
					</td>
				</tr>
					<c:forEach items="${titleFormList}" var="title">
						<c:forEach items="${title.itemList}" var="item">
							<c:set var="scoreKey1" value="${item.id}1"/>
							<c:set var="scoreKey2" value="${item.id}2"/>
							<c:set var="scoreKey3" value="${item.id}3"/>
							<c:set var="scoreKey4" value="${item.id}4"/>
							<tr>
								<td >${item.name}</td>
								<td >${countMap[scoreKey1]+0}</td>
								<td ><fmt:formatNumber type="number" value="${(countMap[scoreKey1]+0)/fn:length(datas)*100}" maxFractionDigits="2"/>%</td>
								<td >${countMap[scoreKey2]+0}</td>
								<td ><fmt:formatNumber type="number" value="${(countMap[scoreKey2]+0)/fn:length(datas)*100}" maxFractionDigits="2"/>%</td>
								<td >${countMap[scoreKey3]+0}</td>
								<td ><fmt:formatNumber type="number" value="${(countMap[scoreKey3]+0)/fn:length(datas)*100}" maxFractionDigits="2"/>%</td>
								<td >${countMap[scoreKey4]+0}</td>
								<td ><fmt:formatNumber type="number" value="${(countMap[scoreKey4]+0)/fn:length(datas)*100}" maxFractionDigits="2"/>%</td>
							</tr>
						</c:forEach>
					</c:forEach>
					<tr>
						<td >有无专人带教</td>
						<td >有</td>
						<td ><fmt:formatNumber type="number" value="${(countMap['teachyes']+0)/fn:length(datas)*100}" maxFractionDigits="2"/>%</td>
						<td >无</td>
						<td ><fmt:formatNumber type="number" value="${(countMap['teachno']+0)/fn:length(datas)*100}" maxFractionDigits="2"/>%</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>带教姓名</td>
						<td colspan="3">推荐优秀带教次数</td>
						<td colspan="5"> </td>
					</tr>
					<c:forEach items="${teachers}" var="tea">
						<tr>
							<td >${userMap[tea]}</td>
							<td colspan="3">${countMap[tea]+0}</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</c:forEach>
			</table>
		</c:if>
		<%--<center style="margin-top: 10px;"><input type="button"  class="search" value="关&#12288;闭" onclick="jboxClose();"/></center>--%>
	</div>
	<div align="center" style="margin-top: 10px;">
		<input type="button"  class="search" value="关&#12288;闭" onclick="jboxClose();"/>
		<c:if test="${not empty evaluationCfg}">
			<input type="button"  class="search" value="导&#12288;出" onclick="exportInfo();"/>
			<input type="button"  class="search" value="打&#12288;印" onclick="printDetail();"/>
		</c:if>
	</div>
</div>
</body>
</html>