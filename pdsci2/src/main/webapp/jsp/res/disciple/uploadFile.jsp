<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>
<script type="text/javascript">
function checkFile(file){
	var filePath = file.value;
	var types = "${sysCfgMap['jszy_file_support_suffix']}".split(",");
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
		jboxTip("请上传【${sysCfgMap['jszy_file_support_suffix']}】格式的文件");
	}
}

function checkUploadFile(){
	if ($("#uploadFileForm").validationEngine("validate")) {
		jboxStartLoading();
		$(":file.auto:hidden").attr("disabled",true);
		jboxSubmit($("#uploadFileForm"),"<s:url value='/res/disciple/checkUploadFile'/>",function(resp){
			if(resp=="${GlobalConstant.SAVE_SUCCESSED}")
			{
				window.parent.frames['mainIframe'].window.refresh();
			}
		},null,true)
	}
}
function doClose() {
	window.parent.frames['mainIframe'].window.refresh();
	var openDialog = top.dialog.get('openDialog');
	if(openDialog !=null && openDialog.open){
		openDialog.close().remove();
	}
}
</script>
</head>
<body>
<div class="infoAudit">
	<form id="uploadFileForm" method="post" style="position:relative;" action=""enctype="multipart/form-data">
		<input type="hidden" name="fileFlow" value="${param.fileFlow}"/>
		<input type="hidden" name="productType" value="GRADUATION_FILE"/>
		<div id="uploadFileDiv">
			<div style="text-align: center;vertical-align: middle;">
				<table style="width: 100%;border: 1px solid #e3e3e3; height: 100px;margin-top: 25px;">
					<tr>
						<td style="text-align: right;border: 1px solid #e3e3e3;">上传文件：</td>
						<td style="text-align: left;padding-left: 10px;border: 1px solid #e3e3e3;">
						<input type="file" name="uploadFile" class="validate[required] auto" style="border: none;" onchange="checkFile(this);"
							   accept="${sysCfgMap['jszy_file_support_suffix']}"/>
						</td>
					</tr>
					<c:if test="${empty file}">
						<tr>
							<td style="text-align: right;border: 1px solid #e3e3e3;">已上传论文名称：</td>
							<td style="text-align: left;padding-left: 10px;border: 1px solid #e3e3e3;">未上传过结业论文</td>
						</tr>
					</c:if>
					<c:if test="${not empty file}">
						<tr>
							<td style="text-align: right;border: 1px solid #e3e3e3;">已上传论文名称：</td>
							<td style="text-align: left;padding-left: 10px;border: 1px solid #e3e3e3;">${file.fileName}</td>
						</tr>
					</c:if>
					<tr>
						<td  style="text-align: right;border: 1px solid #e3e3e3;">允许上传后缀格式：</td>
						<td style="text-align: left;padding-left: 10px;border: 1px solid #e3e3e3;">${sysCfgMap['jszy_file_support_suffix']}</td>
					</tr>
				</table>
			</div>
			<div class="button">
				<input class="search" type="button" value="确&#12288;定" onclick="checkUploadFile();" />
				<input class="search" type="button" value="取&#12288;消" onclick="doClose();" />
			</div>
		</div>
	</form>
</div>
</body>
</html>