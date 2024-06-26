<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="login" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<style type="text/css">
	#boxHome .item:HOVER{background-color: #eee;}
</style>
<script type="text/javascript">

	$(document).ready(function(){
		if ("${GlobalConstant.FLAG_Y}"=="${pageFlag}") {
			window.parent.jboxTip("申请成功！");
			jboxClose();
		}
	});

	function checkFile(file){
		var filePath = file.value;
		var types = ".jpg,.png,.jpeg".split(",");
		var regStr = "/";
		for(var i = 0 ;i<types.length;i++){
			if(types[i]){
				if(i==(types.length-1)){
					regStr = regStr+"\\"+types[i]+'$';
				}else{
					regStr = regStr+"\\"+types[i]+'$|';
				}
			}
		}
		regStr = regStr+'/i';
		regStr = eval(regStr);
		if($.trim(filePath)!="" && !regStr.test(filePath)){
			file.value = "";
			jboxTip("请上传&nbsp;.jpg,.png,.jpeg格式的文件");
		}
	}

	function saveTeacherApplication() {
		if ($("#excelForm").validationEngine("validate")) {
			$("#excelForm").submit();
		}
	}

</script>
</head>
<body>
<c:if test="${empty applicationFlag}">
	<form id="excelForm" method="post" action="<s:url value='/jsres/statistic/saveTeacherApplication'/>" enctype="multipart/form-data">
		<table class="grid" style="width: 100%;margin-top: 15px">
			<input  type="text" name="recordFlow" id="recordFlow"  value="${teacher.recordFlow}" style="display: none;"/>
			<input  type="text" name="roleId" id="roleId"  value="${roleId}" style="display: none;"/>
			<tr>
				<th width="150px"><font color="red" >*</font>师资类型</th>
				<c:if test="${roleId == 'student'}">
					<td colspan="3" style="text-align: left;">
						<select class="select" name="applicationTeacherLevelId" style="width: 100px">
							<option <c:if test="${applicationTeacherLevelId eq 'GeneralFaculty'}">selected="selected"</c:if> value="GeneralFaculty">一般师资</option>
							<option <c:if test="${applicationTeacherLevelId eq 'KeyFaculty'}">selected="selected"</c:if> value="KeyFaculty">骨干师资</option>
						</select>
					</td>
				</c:if>
				<c:if test="${roleId != 'student'}">
					<td colspan="3" style="text-align: left;">
						<input  type="text" name="applicationTeacherLevelId" class="select validate[required]" hidden value="KeyFaculty"  style="text-align: left;width: 150px;"/>
						<input  type="text" class="select validate[required]" disabled value="骨干师资"  style="text-align: left;width: 150px;"/>
					</td>
				</c:if>
			</tr>
			<tr>
				<th><font color="red" >*</font>申请说明</th>
				<td colspan="3">
					<textarea style="height: 150px;width: 630px" id="applicationMessage" class="validate[required]" name="applicationMessage" ></textarea>
				</td>
			</tr>
			<tr>
				<th width="150px"><font color="red" >*</font>师资证明</th>
				<td colspan="3">
					<input type="file" name="uploadFile" class="validate[required]" style="margin-right: 115px" onchange="checkFile(this);"/>
				</td>
			</tr>
			<tr>
				<td style="text-align: center;padding-left: 10px;" colspan="4">允许上传后缀格式：.jpg,.png,.jpeg</td>
			</tr>
		</table>
	</form>
	<div style="text-align: center; margin-top: 10px;">
		<input type="button" onclick="saveTeacherApplication();" class="btn_green" value="确&#12288;认"/>
		<input type="button" class="btn_green" value="取&#12288;消" onclick="jboxClose();"/>
	</div>
</c:if>
<c:if test="${not empty applicationFlag && applicationFlag == 'N'}">
	<div style="text-align: center; margin-top: 100px;">
		<font size="6">您存在未审核完毕的申请流程，请耐心等待审核完毕！</font>
	</div>
</c:if>

