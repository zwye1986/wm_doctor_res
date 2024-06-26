
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
		console.log("1");
		if(!$("#saveForm").validationEngine("validate")){
			console.log("2");
			return false;
		}
		console.log("3");
		var url = "<s:url value='/erp/report/saveAddFeedBack'/>";
		jboxPost(url, $("#saveForm").serialize(), function(resp) {
			if(resp=='${GlobalConstant.SAVE_SUCCESSED}') {
				setTimeout(function () {
					window.parent.frames['mainIframe'].window.search();
					jboxClose();
				}, 1000);
			}
		},null,true);
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form id="saveForm">
			<input type="hidden" value="${param.contractFlow}" name="contractFlow">
			<table class="basic" style="margin-top: 10px;">
				<colgroup>
				<col width="20%"/>
				<col width="80%"/>
				</colgroup>
				<tr>
					<th style="height: 40px;">跟进时间</th>
					<td style="text-align: left;"><input type="text" name="feedBackTime" value="" class="ctime validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width:170px;margin: 0;" readonly="readonly"/></td>
				</tr>
				<tr>
					<th>跟进反馈情况</th>
					<td style="text-align: left;width:100%;height: 100px;"><textarea name="feedBackContent" class="validate[required,maxSize[500]]"  style="width:100%;height: 100%;resize: none;"></textarea></td>
				</tr>
			</table>
		</form>

		<div class="button" style="width: 100%;">

			<input class="search" type="button" value="保&#12288;存" onclick="save();" />
			<input class="search" type="button" value="关&#12288;闭" onclick="jboxClose();" />
		</div>
	</div>
</div>
</body>
</html>