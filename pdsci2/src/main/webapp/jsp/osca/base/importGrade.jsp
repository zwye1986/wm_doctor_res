<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
	</jsp:include>
	<script type="text/javascript">
		function daoRu(){
			if(false==$("#excelForm").validationEngine("validate")){
				return false;
			}
			var checkFileFlag = $("#checkFileFlag").val();
			if('${GlobalConstant.FLAG_Y}'!=checkFileFlag){
				jboxTip("请上传Excel文件！");
				return false;
			}
			jboxStartLoading();
			var url = "<s:url value='/osca/base/importGradeExcel'/>";
			jboxSubmit(
					$('#excelForm'),
					url,
					function(resp){
						top.jboxInfo(resp);
						if(resp.substring(0,5)=="${GlobalConstant.UPLOAD_SUCCESSED}"){
							window.parent.frames['mainIframe'].toPage4(1);
							jboxClose();
						}
					},
					function(resp){
						top.jboxEndLoading();
						top.jboxTip('${GlobalConstant.UPLOAD_FAIL}');
					},false);
		}

		function checkFile(file){
			var filePath = file.value;
			var suffix = filePath.substring(filePath.lastIndexOf(".")+1);
			if("xlsx" == suffix || "xls" == suffix){
				$("#checkFileFlag").val("${GlobalConstant.FLAG_Y}");
			}else{
				$("#checkFileFlag").val("${GlobalConstant.FLAG_N}");
				$(file).val(null);
				jboxTip("请上传Excel文件");
			}
		}
	</script>
</head>
<body>
<input type="hidden" id="checkFileFlag" name="checkFileFlag"/>
<form id="excelForm" method="post" enctype="multipart/form-data">
	<input type="hidden" name="clinicalFlow" value="${param.clinicalFlow}">
	<table class="basic" style="width: 100%;">
		<tr>
			<th>请选择导入</th>
			<td>
				<input id="file" type="file" name="file" onchange="checkFile(this);" class="validate[required]"/>
			</td>
		</tr>
		<tr>
			<th>模板文件</th>
			<td><span style="color:red;line-height:24px;">1.请使用成绩导出模板<br/>2.删除模板中总分和考核结果列（总分和考核结果系统自动计算）<br/>3.只对非缺考学员作成绩导入<br/>4.以准考证号与姓名精确到学员，确保其准确性</span></td>
		</tr>
	</table>
</form>
<div style="text-align: center; margin-top: 10px;">
	<input type="button" onclick="daoRu();" value="导&#12288;入" class="search"/>
	<input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
</div>
</body>
</html>