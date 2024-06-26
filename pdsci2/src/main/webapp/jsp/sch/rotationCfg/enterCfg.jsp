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
	function save(){
		if(false==$("#searchForm").validationEngine("validate")){
			return false;
		}
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		if(startDate>endDate){
			jboxTip("开始日期不能大于结束日期");
			return false;
		}
		var url = "<s:url value='/sch/rotationCfg/saveEnterCfg'/>";
		 var data = $('#searchForm').serialize();
		 jboxPost(url, data, function(resp) {
			 if(resp=="保存成功！") {
			 window.location.reload(true);
			 }
		 }, null, true);
	}
</script>
</head>
<body>
<div class="content">
	<div class="title1 clearfix">
		<div id="tagContent">
			<fieldset>
				<legend>选科时间设置</legend>
				<div style="text-align: center">
					<form id="searchForm" method="post" >
						<input type="hidden" name="cfgFlow" value="${resEnterOpenCfg.cfgFlow}">
						<input type="hidden" name="orgFlow" value="${orgFlow}">
						<div class="div_search" style="padding: 10px;">
							选科时间：
							<input type="text" class="validate[required] xltext" id="startDate" name="startDate" value="${resEnterOpenCfg.startDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" readonly="readonly">
							~&#12288;
							<input type="text" class="validate[required] xltext" id="endDate"   name="endDate"   value="${resEnterOpenCfg.endDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" readonly="readonly">
						</div>
					</form>
				</div>
			<div class="button">
				<input type="button" class="search" onclick="save();" value="保&#12288;存"/>
			</div>
			</fieldset>
		</div>
	</div>
</div>
</body>
</html>