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
	$(document).ready(function () {
		var userFlows = '${userFlows}';
		if (userFlows!=null && userFlows!=''){
			var flows = userFlows.split(",");
		}
		var itemIdList = $("input");
		for (var i = 0; i < itemIdList.length; i++) {
			if (itemIdList[i].value==flows[0] || itemIdList[i].value==flows[1] || itemIdList[i].value==flows[2] || itemIdList[i].value==flows[3]){
				$(itemIdList[i]).attr("checked","checked");
			}
		}
	});
</script>
<c:if test="${empty list}">
	<div class="search_table" style="width: 100%;padding: 0 20px">
		<table border="0" cellpadding="0" cellspacing="0" class="grid">
			<tr>
				<th>专家姓名</th>
				<th>手机号码</th>
				<th>专业</th>
			</tr>
			<tr>
				<td colspan="3" >无记录！</td>
			</tr>
		</table>
	</div>
</c:if>
<c:if test="${not empty list}">
	<div class="main_bd clearfix" style="width: 100%;padding: 0 20px;overflow: auto;max-height: 360px;">
		<table id="dataTable" border="0" cellpadding="0" cellspacing="0" class="grid" >
			<thead>
			<tr>
				<th colspan="2">专家姓名</th>
				<th>手机号码</th>
				<th>专业</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="s" varStatus="status">
				<tr class="fixTrTd">
					<td>
						<input type="checkbox" name="userFlow" value="${s.userFlow}" itemName="${s.userName}"/>
					</td>
					<td style="text-align: left;">${s.userName}</td>
					<td>${s.userPhone}</td>
					<td>${s.resTrainingSpeName}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</c:if>

      
