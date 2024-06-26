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
	function checkUploadFile() {
		var openTime = $("#fjxx").val();
		if (openTime == null || openTime == "") {
			jboxTip("请上传&nbsp;.docx格式的文件");
			jboxEndLoading();
			return false;
		}
		if ($("#uploadFileForm").validationEngine("validate")) {
			jboxStartLoading();
			$(":file.auto:hidden").attr("disabled", true);
			$("#uploadFileForm").submit();
		}
	}

	function returnUrl() {
		window.parent.toPage(1);
		doClose();
	}

	function reUpload() {
		$('#uploadErrorDiv').hide();
		$('#uploadFileDiv').show();
	}

	function doClose() {
		if ('${param.second}' == 'Y') {
			top.jboxClose();
		}
		var openDialog = top.dialog.get('openDialog');
		if (openDialog != null && openDialog.open) {
			openDialog.close().remove();
		}
	}

	$(document).ready(function () {
		if ("${GlobalConstant.FLAG_Y}" == "${result}") {
			window.parent.toPage(1);
			doClose();
		} else if ("${GlobalConstant.FLAG_N}" == "${result}") {
			jboxTip("保存失败");
		}
	});

	function checkFile(file){
		var filePath = file.value;
		var suffix = filePath.substring(filePath.lastIndexOf(".")+1);
		if("docx" == suffix){
			$("#checkFileFlag").val("${GlobalConstant.FLAG_Y}");
		}else{
			$("#checkFileFlag").val("${GlobalConstant.FLAG_N}");
			jboxTip("请上传模板文件");
		}
	}

</script>
<div class="infoAudit">
	<form id="uploadFileForm" method="post" style="position:relative;"
		  action="<s:url value='/jsres/supervisio/saveFeedback'/>" enctype="multipart/form-data">
		<input type="hidden" name="operType" value="${operType }">
		<input type="hidden" name="subjectActivitiFlows" value="${subjectActivitiFlows}"/>
		<input type="hidden" name="type" value="${type}"/>
		<input type="hidden" name="subjectFlow" value="${subjectFlow}"/>
		<div id="uploadFileDiv" style="display: ${empty result?'':'none'}">
			<div style="text-align: center;vertical-align: middle;">
				<table style="width: 100%;border: 1px solid #e3e3e3; height: 100px;margin-top: 25px;">
					<tr>
						<th style="border: 1px solid #e3e3e3;background-color: #f4f5f9;">上传文件：</th>
						<td style="text-align: left;padding-left: 10px;border: 1px solid #e3e3e3;"><input type="file" name="file"
																				class=" auto"
																				style="border: none;"
																				onchange="checkFile(this);" id="fjxx"
																				accept=".docx"/>
						</td>
					</tr>
					<tr>
						<th style="border: 1px solid #e3e3e3;background-color: #f4f5f9;">上传模板：</th>
						<td style="text-align: left;padding-left: 10px;border: 1px solid #e3e3e3;">
							<c:if test="${type eq 'sup'}">
								<a href="<s:url value='/jsp/jsres/hospital/supervisio/supFeedback.docx'/>">督导反馈上传模板.docx</a>
							</c:if>
							<c:if test="${type eq 'spe'}">
								<a href="<s:url value='/jsp/jsres/hospital/supervisio/speFeedback.docx'/>">（专业）基地自评报告模板.docx</a>
							</c:if>
						</td>
					</tr>
				</table>
			</div>
			<div class="button">
				<input class="btn_green" type="button" value="确&#12288;定" onclick="checkUploadFile();"/>
				<input class="btn_green" type="button" value="取&#12288;消" onclick="doClose();"/>
			</div>
		</div>
		<div id="uploadErrorDiv" style="display: ${empty fileErrorMsg?'none':''}">
			<div style="color: red;height: 60px;text-align: center;vertical-align: middle;"><B>${fileErrorMsg}</B></div>
			<div class="button">
				<input class="btn_green" type="button" value="重新上传" onclick="reUpload();"/>
				<input class="btn_green" type="button" value="取&#12288;消" onclick="doClose();"/>
			</div>
		</div>
	</form>
</div>