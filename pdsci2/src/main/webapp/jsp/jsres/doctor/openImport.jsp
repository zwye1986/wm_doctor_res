<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="basic" value="true" />
		<jsp:param name="jbox" value="true" />
		<jsp:param name="font" value="true" />
		<jsp:param name="jquery_form" value="true" />
		<jsp:param name="jquery_validation" value="true" />
	</jsp:include>
	<script type="text/javascript">
		function save() {

			var recTypeId=$("#recTypeId").val();
			if(!recTypeId)
			{
				jboxTip("请选择数据类型！");
				return false;
			}

			if(${empty param.recordFlow})
			{
				jboxTip("请选择标准科室");
				return false;
			}
			if(${empty param.processFlow})
			{
				jboxTip("请选择轮转科室");
				return false;
			}
			if(${ param.doctorFlow ne sessionScope.currUser.userFlow})
			{
				jboxTip("当前用户已变更，请关闭页面");
				return false;
			}
			if(false==$("#excelForm").validationEngine("validate")){
				return false;
			}
			var checkFileFlag = $("#checkFileFlag").val();
			if('${GlobalConstant.FLAG_Y}'!=checkFileFlag){
				jboxTip("请上传Excel文件！");
				return false;
			}
			var length=0;
			jboxStartLoading();
			var url = "<s:url value='/jsres/doctor/importData'/>";
			jboxSubmit(
					$('#excelForm'),
					url,
					function(resp){
						jboxEndLoading();
						jboxInfo(resp);
						if(resp.substring(0,5)=="${GlobalConstant.UPLOAD_SUCCESSED}"){
							setTimeout(function(){
								top.trainRegister();
								jboxClose();
							},1000);
						}
					},
					function(data){
						jboxEndLoading();
					},false);
		}
		function downImportFile() {

			var recTypeId=$("#recTypeId").val();
			if(!recTypeId)
			{
				jboxTip("请选择数据类型！");
				return false;
			}

			if(${empty param.recordFlow})
			{
				jboxTip("请选择标准科室");
				return false;
			}
			if(${empty param.processFlow})
			{
				jboxTip("请选择轮转科室");
				return false;
			}
			if(${ param.doctorFlow ne sessionScope.currUser.userFlow})
			{
				jboxTip("当前用户已变更，请关闭页面");
				return false;
			}

			var url = "<s:url value='/jsres/doctor/downImportFile'/>?recordFlow=${param.recordFlow}&processFlow=${param.processFlow}&doctorFlow=${param.doctorFlow}&recTypeId="+recTypeId ;
			jboxExpLoading(null,url);
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
<div class="infoAudit">
	<div class="div_table" style="overflow: hidden;">
		<input type="hidden" id="checkFileFlag" name="checkFileFlag"/>
		<form id="excelForm" method="post" enctype="multipart/form-data">
			<input type="hidden" name="doctorFlow" value="${param.doctorFlow }"/>
			<input type="hidden" name="recordFlow" value="${param.recordFlow }"/>
			<input type="hidden" name="processFlow" value="${param.processFlow }"/>
			<table border="0" cellpadding="0" cellspacing="0" class="base_info">
				<colgroup>
					<col width="30%" />
					<col width="70%" />
				</colgroup>
				<tbody>
				<br/>
				<tr>
					<th>数据类型：</th>
					<td>
						<select id="recTypeId" name="recTypeId" class="select" style="width:150px;">
							<option value="">请选择</option>
							<option value="SkillRegistry">操作技能</option>
							<option value="DiseaseRegistry">病种</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>导入模板：</th>
					<td><a onclick="downImportFile();">下载</a>（请先将模板的单元格格式设置成【文字文本】，再填写数据进行导入）</td>
				</tr>
				<tr>
					<th>导入文件：</th>
					<td><input name="file" type="file"  onchange="checkFile(this);" class="validate[required]"> </td>
				</tr>
				<tr>
					<td colspan="2"><font color="red">请修改模板中的表头文字，同时请按【填写说明】填写相应数据信息</font>  </td>
				</tr>
				</tbody>
			</table>
		</form>

		<div class="button">
			<c:set var="f" value="true"/>
			<c:if test="${empty param.recordFlow}">
				<font color="red">请选择标准科室</font>
				<c:set var="f" value="false"/>
			</c:if>
			<c:if test="${empty param.processFlow}">
				<font color="red">请选择轮转科室</font>
				<c:set var="f" value="false"/>
			</c:if>
			<c:if test="${ param.doctorFlow ne sessionScope.currUser.userFlow}">
				<font color="red">当前用户已变更，请关闭页面</font>
				<c:set var="f" value="false"/>
			</c:if>
			<c:if test="${f}">
				<input type="button" class="btn_green" onclick="save();" value="保&#12288;存"/>
			</c:if>
			<input type="button" class="btn_green" onclick="jboxClose();" value="关&#12288;闭"/>
		</div>
	</div>
</div>
</body>
</html>