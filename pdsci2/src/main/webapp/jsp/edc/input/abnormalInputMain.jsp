
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
	<jsp:param name="jquery_fixedtableheader" value="false"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<script type="text/javascript">
	function searchAbnormalInput(orgFlow) {
		jboxGet("<s:url value='/edc/input/abnormalInputList?orgFlow='/>" + orgFlow, null, function(resp) {
			$("#listDiv").html(resp);
		}, null, false);
	}
	$(document).ready(function() {
		searchAbnormalInput('');
	});
</script>
</head>
<body>
	<div class="mainright">
		<div class="content">
				<div class="title1 clearfix">
			&#12288;&#12288;参与机构：
			<select id="orgFlow" name="orgFlow" class="xlname" style="width: 200px;"
							onchange="searchAbnormalInput(this.value);">
							<option value=""></option>
							<c:forEach items="${pdfn:filterProjOrg(pubProjOrgList)}" var="projOrg">
								<option value="${projOrg.orgFlow}"
									<c:if test="${projOrg.orgFlow==orgFlow }">selected</c:if>>${projOrg.centerNo }&#12288;${applicationScope.sysOrgMap[projOrg.orgFlow].orgName}
								</option>
							</c:forEach>
						</select>
				</div>
				<hr />
			<div id="listDiv"></div>
		</div>
	</div>
			
</body>
</html>