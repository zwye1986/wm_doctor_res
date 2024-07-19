<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<script type="text/javascript">
function checkFile(file){
	var filePath = file.value;

	var types = "jpeg,jpg".split(",");
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
		jboxTip("请上传jpg/jpeg格式的图片");
	}
}

function checkUploadFile(){
	if ($("#uploadFileForm").validationEngine("validate")) {
		jboxStartLoading();
		$(":file.auto:hidden").attr("disabled",true);
		var url = "<s:url value='/jsres/doctor/checkUploadFile'/>";
		$("#uploadFileForm").submit();
	}
}

function returnUrl(){
	if('${param.second}'=='Y'){
		top.document['jbox-message-iframe'].$('#${param.operType}').text("重新上传");
		top.document['jbox-message-iframe'].$('#${param.operType}Value').val("${filePath}");
		var filePath = "${sysCfgMap['upload_base_url']}/${filePath}";
		top.document['jbox-message-iframe'].$('#${param.operType}Del').show();
		top.document['jbox-message-iframe'].$('#${param.operType}Span').show();
		top.document['jbox-message-iframe'].$('#${param.operType}Span').find("a").attr('href',filePath);
		top.document['jbox-message-iframe'].$("#${param.operType}Se").hide();
	}else{
		window.parent.$('#${param.operType}').text("重新上传");
		window.parent.$('#${param.operType}Value').val("${filePath}");
		var filePath = "${sysCfgMap['upload_base_url']}/${filePath}";
		window.parent.$('#${param.operType}Del').show();
		window.parent.$('#${param.operType}Span').show();
		window.parent.$('#${param.operType}Span').find("a").attr('href',filePath);
		window.parent.$("#${param.operType}Se").hide();
	}
	doClose();
}

function reUpload(){
	$('#uploadErrorDiv').hide();
	$('#uploadFileDiv').show();
}

function doClose() {
	if('${param.second}'=='Y'){
		top.jboxClose();
	}
	var openDialog = top.dialog.get('openDialog');
	if(openDialog !=null && openDialog.open){
		openDialog.close().remove();
	}
}

$(document).ready(function(){
	if ("${GlobalConstant.FLAG_Y}"=="${result}") {
		returnUrl();
	}
});	
</script>
</head>
<body>
<div class="infoAudit">
	<form id="uploadFileForm" method="post" style="position:relative;" action="<s:url value='/jsres/doctor/checkUploadFile'/>" enctype="multipart/form-data">
		<input type="hidden" name="second" value="${param.second}"/>
		<input type="hidden" name="operType" value="${param.operType}"/>
		<input type="hidden" name="userFlow" value="${param.userFlow}"/>
		<div id="uploadFileDiv" style="display: ${empty result?'':'none'}">
			<div style="text-align: center;vertical-align: middle;">
				<table style="width: 100%;border: 1px solid #e3e3e3; height: 100px;margin-top: 25px;">
					<tr><td style="border: 1px solid #e3e3e3;">上传文件：</td><td style="text-align: left;padding-left: 10px;"><input type="file" name="uploadFile" class="validate[required] auto" style="border: none;" onchange="checkFile(this);" accept="${sysCfgMap['inx_image_support_mime']}"/></td></tr>
					<tr>
						<td colspan="2" style="text-align: left;padding-left: 10px;">
							<c:if test="${param.operType eq 'idcardFrontImg'}">
								<span class="red">照片要求：<br/>住院医师身份证件扫描件人像页<br/>文件格式为JPG/JPEG，分辨率不小于640*480，文件大小不超过300K</span>
							</c:if>
							<c:if test="${param.operType eq 'idcardOppositeImg'}">
								<span class="red">照片要求：<br/>住院医师身份证件扫描件国徽页<br/>文件格式为JPG/JPEG，分辨率不小于640*480，文件大小不超过300K</span>
							</c:if>
						</td>
					</tr>
				</table>
			</div>
			<div class="button">
				<input class="btn_green" type="button" value="确&#12288;定" onclick="checkUploadFile();" />
				<input class="btn_green" type="button" value="取&#12288;消" onclick="doClose();" />
			</div>
		</div>
		<div id="uploadErrorDiv" style="display: ${empty fileErrorMsg?'none':''}">
			<div style="color: red;height: 60px;text-align: center;vertical-align: middle;"><B>${fileErrorMsg}</B></div>
			<div class="button">
				<input class="btn_green" type="button" value="重新上传" onclick="reUpload();" />
				<input class="btn_green" type="button" value="取&#12288;消" onclick="doClose();" />
			</div>
		</div>
	</form>
</div>
</body>
</html>