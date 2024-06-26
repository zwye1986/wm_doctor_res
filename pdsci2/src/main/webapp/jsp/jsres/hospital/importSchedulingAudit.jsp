
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
		// var rotationFlow = $("#rotationFlow").val();
		// if (rotationFlow==''){
		// 	jboxTip("请选择要导入模板的培训方案！");
		// 	return
		// }

		if(!$("#planForm").validationEngine("validate")){
			return false;
		}
		var checkFileFlag = $("#checkFileFlag").val();
		if('${GlobalConstant.FLAG_Y}'!=checkFileFlag){
			jboxTip("请上传Excel文件！");
			return false;
		}
		jboxStartLoading();
		var url = "<s:url value='/jsres/doctorRecruit/importSchedulingAuditExcel'/>";
		jboxSubmit($('#planForm'), url,
			function(resp){
				jboxEndLoading();
				if (resp=='${GlobalConstant.UPLOAD_FAIL}'){
					top.jboxInfo(resp);
				}else if (resp.substring(0,5)=='${GlobalConstant.UPLOAD_SUCCESSED}'){
					top.jboxInfo(resp);
					window.parent.toPage(1);
					top.jboxClose();
				}else {
					top.jboxInfo(resp);
				}
			}
		);
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

	function exportTemp() {
		// var rotationFlow = $("#rotationFlow").val();
		// if (rotationFlow==''){
		// 	jboxTip("请选择要导出模板的培训方案！");
		// 	return
		// }
		var url = "<s:url value='/jsres/doctorRecruit/expertSchTemp'/>";
		jboxTip("导出中…………");
		jboxExp($("#planForm"), url);
	}
</script>
<div style="padding:0px 20px;margin-top:30px;">
	<input type="hidden" id="checkFileFlag"/>
	<form id="planForm" method="post" enctype="multipart/form-data">
		<table width="100%" class="grid">
<%--			<tr>--%>
<%--				<th style="text-align: right;">培训方案：</th>--%>
<%--				<td style="text-align: left;">--%>
<%--					<select name="rotationFlow" id="rotationFlow" class="select" style="width: 280px;">--%>
<%--						<option value="">请选择</option>--%>
<%--						<c:forEach items="${list}" var="s">--%>
<%--							<option value="${s.rotationFlow}">${s.rotationName}</option>--%>
<%--						</c:forEach>--%>
<%--					</select>--%>
<%--				</td>--%>
<%--			</tr>--%>
			<tr>
				<th style="text-align: right;">Excel文件：</th>
				<td style="text-align: left;">
					<input id="file" type="file" name="file" onchange="checkFile(this);" class="validate[required]"/>
				</td>
			</tr>
			<tr>
				<th style="text-align: right;">导入模板：</th>
					<td style="text-align: left;"><a onclick="exportTemp();">导入模板.xlsx</a></td>
			</tr>
		</table>
	</form>
	<div align="center" style="margin-top: 20px; margin-bottom:20px;">
		<input type="button" class="btn_green" onclick="importExcel();" value="导&#12288;入"/>&#12288;
		<input type="button" class="btn_green" onclick="jboxClose();" value="关&#12288;闭"/>
	</div>
</div>