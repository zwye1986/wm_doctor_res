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
	var types = "JPG,jpg".split(",");
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
		jboxTip("请上传JPG格式的文件");
	}
}
$(document).ready(function(){
	if ("${GlobalConstant.FLAG_Y}"=="${result}") {
		returnUrl();
	}
	if('${tooBig}'){
		jboxTip("${tooBig}");
	}
});

function checkUploadFile(){
	if ($("#uploadFileForm").validationEngine("validate")) {
		jboxStartLoading();
		$(":file.auto:hidden").attr("disabled",true);
		$("#uploadFileForm").submit();
	}
}

function returnUrl(){
	var operType = '#${operType}'.replace(/\./g, '\\.');
	window.parent.$(operType+'Up').text("重新上传");
	window.parent.$(operType+'Value').val("${filePath}");
	var filePath = "${sysCfgMap['upload_base_url']}/${filePath}";
	window.parent.$(operType+'Del').show();
	window.parent.$(operType+'Span').show();
	window.parent.$(operType+'Span').find("a").attr('href',filePath);
	doClose();
}

function reUpload(){
	$('#uploadErrorDiv').hide();
	$('#uploadFileDiv').show();
}

function doClose() {
	top.jboxClose();
}

</script>
</head>
<body>
<form id="uploadFileForm" method="post" action="<s:url value='/sczyres/doctor/checkUploadFile'/>" enctype="multipart/form-data">
<input type="hidden" name="operType" value="${operType}">
<div class="content">
	<div class="title1 clearfix">
		<div id="uploadFileDiv" style="display: ${empty result?'':'none'}">
			<div style="height: 60px;text-align: center;vertical-align: middle;">
			<input type="file" name="uploadFile" class="validate[required] auto" style="border: none;"
			  onchange="checkFile(this);"
			/>
			</div>
			<div class="button">
				<input class="search" type="button" value="确&#12288;定" onclick="checkUploadFile();" />
				<input class="search" type="button" value="取&#12288;消" onclick="doClose()" />
			</div>
		</div>
	</div>
</div>
</form>
</body>
</html>