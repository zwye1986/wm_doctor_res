
<%@include file="/jsp/common/doctype.jsp" %>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript">
	function importExcel(){
		if(!$("#planForm").validationEngine("validate")){
			return false;
		}
		var checkFileFlag = $("#checkFileFlag").val();
		if('${GlobalConstant.FLAG_Y}'!=checkFileFlag){
			jboxTip("请上传Excel文件！");
			return false;
		}
		var url = "<s:url value='/jsres/phyAss/importBaseUserExcel'/>";
		jboxSubmit($('#planForm'), url, function(resp){
			top.jboxInfo(resp);
			window.parent.toPage(1);
			top.jboxCloseMessager();
		});
	}
	function checkFile(file){
		var filePath = file.value;
		var suffix = filePath.substring(filePath.lastIndexOf(".")+1);
		if("xlsx" == suffix || "xls" == suffix){
			$("#checkFileFlag").val("${GlobalConstant.FLAG_Y}");
		}else{
			$("#checkFileFlag").val("${GlobalConstant.FLAG_N}");
			jboxTip("请上传Excel文件");
		}
	}

	function exportExcel() {
		var url = "<s:url value='/jsres/phyAss/expertBaseListEntryUserTemp'/>";
		jboxTip("下载中…………");
		jboxSubmit($("#planForm"), url, null, null, false);
		jboxEndLoading();
	}
</script>
<div style="padding:0px 20px;margin-top:30px;">
	<input type="hidden" id="checkFileFlag"/>
	<form id="planForm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="planFlow" value="${planFlow}">
		<table width="100%" class="grid">
			<tr>
				<th style="text-align: right;">Excel文件：</th>
				<td style="text-align: left;">
					<input id="file" type="file" name="file" onchange="checkFile(this);" class="validate[required]"/>
				</td>
			</tr>
			<tr>
				<th style="text-align: right;">导入模板：</th>
				<td style="text-align: left;">
					<a href="javascript:exportExcel();">下载模板</a>
				</td>
			</tr>
		</table>
	</form>
	<div align="center" style="margin-top: 20px; margin-bottom:20px;">
		<input type="button" class="btn_green" onclick="importExcel();" value="导&#12288;入"/>&#12288;
		<input type="button" class="btn_green" onclick="jboxClose();" value="关&#12288;闭"/>
	</div>
</div>