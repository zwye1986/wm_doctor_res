<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<script type="text/javascript">
function checkFile(file){
	var filePath = file.value;
	var types = "${sysCfgMap['inx_image_support_suffix']}".split(",");
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
		jboxTip("请上传&nbsp;${sysCfgMap['inx_image_support_suffix']}格式的图片");
	}
}

function checkUploadFile(){
	if ($("#uploadFileForm").validationEngine("validate")) {
		jboxStartLoading();
		$(":file.auto:hidden").attr("disabled",true);
		$("#uploadFileForm").submit();
	}
}

function returnUrl(){
	if('${changeFlag}'=='Y'){
		top.document['jbox-message-iframe'].$('#${operType}').text("重新上传");
		top.document['jbox-message-iframe'].$('#${operType}').text("重新上传");
		top.document['jbox-message-iframe'].$('#${operType}Value').val("${filePath}");
		var filePath = "${sysCfgMap['upload_base_url']}/${filePath}";
		top.document['jbox-message-iframe'].$('#${operType}Del').show();
		top.document['jbox-message-iframe'].$('#${operType}Span').show();
		top.document['jbox-message-iframe'].$('#${operType}Span').find("a").attr('href',filePath);
		if('${filePath}' != ""){
			top.document['jbox-message-iframe'].$('#${operType}').removeClass("validate[required]");
		}
	}else if('${changeFlag}'=='S'){
		window.parent.frames['mainIframe'].$('#${operType}').text("重新上传");
		window.parent.frames['mainIframe'].$('#${operType}Value').val("${filePath}");
		var filePath = "${sysCfgMap['upload_base_url']}/${filePath}";
		window.parent.frames['mainIframe'].$('#${operType}Del').show();
		window.parent.frames['mainIframe'].$('#${operType}Span').show();
		window.parent.frames['mainIframe'].$('#${operType}Span').find("a").attr('href',filePath);
		if('${filePath}' != ""){
			window.parent.frames['mainIframe'].$('#${operType}').removeClass("validate[required]");
		}
	} else {
		window.parent.$('#${operType}').text("重新上传");
		window.parent.$('#${operType}Value').val("${filePath}");
		var filePath = "${sysCfgMap['upload_base_url']}/${filePath}";
		window.parent.$('#${operType}Del').show();
		window.parent.$('#${operType}Span').show();
		window.parent.$('#${operType}Span').find("a").attr('href',filePath);
		if('${filePath}' != ""){
			window.parent.$('#${operType}').removeClass("validate[required]");
		}
	}
	doClose();
}

function reUpload(){
	$('#uploadErrorDiv').hide();
	$('#uploadFileDiv').show();
}

function doClose() {
	if('${changeFlag}'=='Y' || '${changeFlag}'=='S'){
		jboxClose();
	}
	var openDialog = top.dialog.get('openDialog');
	if(openDialog !=null && openDialog.open){
//		jboxTip("操作成功");
		setTimeout(function(){
			openDialog.close().remove();
		},500);
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
<form id="uploadFileForm" method="post" action="<s:url value='/inx/hbres/checkUploadFile'/>" enctype="multipart/form-data">
<input type="hidden" name="operType" value="${operType }">
<input type="hidden" name="changeFlag" value="${changeFlag }">
<div class="content">
	<div class="title1 clearfix">
		<div id="uploadFileDiv" style="display: ${empty result?'':'none'}">
			<div style="height: 60px;text-align: center;vertical-align: middle;">
			<input type="file" name="uploadFile" class="validate[required] auto" style="border: none;" onchange="checkFile(this);" accept="${sysCfgMap['inx_image_support_mime']}"/>
			</div>
			<div class="button">
				<input class="search" type="button" value="确&#12288;定" onclick="checkUploadFile();" />
				<input class="search" type="button" value="取&#12288;消" onclick="doClose();" />
			</div>
		</div>
		<div id="uploadErrorDiv" style="display: ${result==GlobalConstant.FLAG_N?'':'none'}">
			<div style="color: red;height: 60px;text-align: center;vertical-align: middle;">
			${fileErrorMsg[1]}
			</div>
			<div class="button">
				<input class="search" type="button" value="重新上传" onclick="reUpload();" />
				<input class="search" type="button" value="取&#12288;消" onclick="doClose();" />
			</div>
		</div>
	</div>
</div>
</form>
</body>
</html>