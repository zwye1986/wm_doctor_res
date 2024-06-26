<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<script type="text/javascript">
	function saveDeptReq(){
		if(false==$("#excelForm").validationEngine("validate")){
			return false;
		}
		var checkFileFlag = $("#checkFileFlag").val();
		if('${GlobalConstant.FLAG_Y}'!=checkFileFlag){
			jboxTip("请上传Excel文件！");
			return false;
		}
		jboxStartLoading();
		var url = "<s:url value='/sch/template/importDeptReqExcel'/>";
		jboxSubmit(
				$('#excelForm'),
				url,
				function(resp){
					top.jboxInfo(resp);
					if(resp.substring(0,5)=="${GlobalConstant.UPLOAD_SUCCESSED}"){
						window.parent.frames['mainIframe'].window.toPage();
						top.jboxClose();
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
	function bindValue(val){
		$("#recTypeName").val(val);
	}
</script>
</head>
<body>
<input type="hidden" id="checkFileFlag" name="checkFileFlag"/>
<form id="excelForm" method="post" enctype="multipart/form-data">
	<input type="hidden" name="relRecordFlow" value="${param.relRecordFlow}">
	<input type="hidden" name="rotationFlow" value="${param.rotationFlow}">
	<input type="hidden" name="recTypeName" id="recTypeName">
	<table class="basic" style="width: 100%;">
		<tr>
			<th>要求类别</th>
			<td>
				<select name="recTypeId" class="xlselect validate[required]" style="margin-right: 0px" onchange="bindValue(this.value)">
					<option value="">请选择</option>
					<c:if test="${param.typeId eq jszyTCMPracticEnumN.id}">
						<c:forEach items="${registryTypeEnumList}" var="regType">
							<c:set value="res_registry_type_${regType.id}" var="viewCfgKey"/>
							<c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y && GlobalConstant.FLAG_Y eq regType.haveReq && pdfn:findChineseOrWestern(rotation.rotationTypeId,regType.id)}">
								<option value="${regType.id}">${regType.name}</option>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${param.typeId eq jszyTCMPracticEnumBasicPractice.id}">
						<c:forEach items="${practicRegistryTypeEnumList}" var="regType">
							<c:set value="practic_registry_type_${regType.id}" var="viewCfgKey"/>
							<c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y && GlobalConstant.FLAG_Y eq regType.haveReq && pdfn:findChineseOrWestern(rotation.rotationTypeId,regType.id)}">
								<option value="${regType.id}">${regType.name}</option>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${typeId eq jszyTCMPracticEnumTheoreticalStudy.id}">
						<c:forEach items="${theoreticalRegistryTypeEnumList}" var="regType">
							<c:set value="theoretical_registry_type_${regType.id}" var="viewCfgKey"/>
							<c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y && GlobalConstant.FLAG_Y eq regType.haveReq && pdfn:findChineseOrWestern(rotation.rotationTypeId,regType.id)}">
								<option value="${regType.id}">${regType.name}</option>
							</c:if>
						</c:forEach>
					</c:if>
				</select>
			</td>
		</tr>
		<tr>
			<th>导入文件</th>
			<td>
				<input id="file" type="file" name="file" onchange="checkFile(this);" class="validate[required]"/>
			</td>
		</tr>
		<tr>
			<th>模板文件</th>
			<td><a href="<s:url value='/jsp/sch/template/deptReqTemp.xlsx'/>">轮转要求数据模板.xlsx</a></td>
		</tr>
	</table>
	<div style="text-align: center; margin-top: 10px;">
		<input type="button" onclick="saveDeptReq();" class="search" value="提&#12288;交"/>
		<input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
	</div>
</form>
</body>
</html>