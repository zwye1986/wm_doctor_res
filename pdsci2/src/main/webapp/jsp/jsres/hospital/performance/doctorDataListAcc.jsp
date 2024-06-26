<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="true"/>
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
<script type="text/javascript" src="<s:url value='/js/Scoll/Scorll2.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<link rel="stylesheet" type="text/css" href="<s:url value='/js/viewer/viewer2.min.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<script type="text/javascript" src="<s:url value='/js/viewer/viewer.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#currentPage").val("${param.currentPage}");
	});


</script>

	<c:if test="${empty list}">
		<div class="main_bd clearfix" style="width: 100%;margin-left: 15px">
			<table border="0" cellpadding="0" cellspacing="0" class="grid">
				<tr>
					<th>学员姓名</th>
					<th>手机号码</th>
					<th>证件号</th>
					<th>年级</th>
					<th>专业</th>
					<th>人员类型</th>
					<th>应填写量</th>
					<th>已填写量</th>
					<th>未填写量</th>
					<th>填写率</th>
				</tr>
				<tr>
					<td colspan="10" >无记录！</td>
				</tr>
			</table>
		</div>
	</c:if>
	<c:if test="${not empty list}">
		<div class="main_bd clearfix" style="width: 100%;margin-left: 15px">
			<table id="dataTable" border="0" cellpadding="0" cellspacing="0" class="grid" >
				<thead>
				<tr>
					<th>学员姓名</th>
					<th>手机号码</th>
					<th>证件号</th>
					<th>年级</th>
					<th>专业</th>
					<th>人员类型</th>
					<th>应填写量</th>
					<th>已填写量</th>
					<th>未填写量</th>
					<th>填写率</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${list}" var="s" varStatus="status">
					<tr class="fixTrTd">
						<td>${s.userName}</td>
						<td>${s.userPhone}</td>
						<td>${s.idNo}</td>
						<td>${s.sessionNumber}</td>
						<td>${s.speName}</td>
						<td>${s.doctorTypeName}</td>
						<td>${s.reqNum}</td>
						<td>${s.completeNum}</td>
						<td>${s.reqNum - s.completeNum}</td>
						<td>${pdfn:getPercent(s.completeNum, s.reqNum)}</td>
					</tr>
				</c:forEach>
				</tbody>
		</table>
		</div>
	</c:if>

    <div class="page" style="text-align: center">
		<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>
	</div>
      
