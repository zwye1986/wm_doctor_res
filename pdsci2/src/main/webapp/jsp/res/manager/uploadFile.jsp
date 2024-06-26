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
		var filePath = file.value.toUpperCase();
		var types = "RAR,ZIP,DOC,DOCX,PPT,PPTX,XLS,XLSX".split(",");
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
			jboxTip("请上传RAR,ZIP,DOC,DOCX,PPT,PPTX,XLS,XLSX格式的文件");
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
	var lectureFlow = '#${lectureFlow}'.replace(/\./g, '\\.');
	window.parent.frames['mainIframe'].$(lectureFlow+'Up').text("重新上传");
	var filePath = "${sysCfgMap['upload_base_url']}/${filePath}";
	window.parent.frames['mainIframe'].$(lectureFlow+'Del').show();
	window.parent.frames['mainIframe'].$(lectureFlow+'Span').show();
	window.parent.frames['mainIframe'].$(lectureFlow+'Span').find("a").attr('href',filePath);
	doClose();
}

function reUpload(){
	$('#uploadErrorDiv').hide();
	$('#uploadFileDiv').show();
}

function doClose() {
	window.parent.frames['mainIframe'].window.search();
	jboxClose();
}

</script>
</head>
<body>
<form id="uploadFileForm" method="post" action="<s:url value='/res/lecture4qingpu/checkUploadFile'/>" enctype="multipart/form-data">
<input type="hidden" name="lectureFlow" value="${lectureFlow}">
<div class="content">
	<div class="title1 clearfix">
		<div id="uploadFileDiv" style="display: ${empty result?'':'none'}">
			<div style="height: 60px;text-align: center;vertical-align: middle;">
			<input type="file" name="uploadFile" class="validate[required] auto" style="border: none;" onchange="checkFile(this);"
			/>
			</div>
			<div class="button">
				<input class="search" type="button" value="确&#12288;定" onclick="checkUploadFile();" />
				<input class="search" type="button" value="取&#12288;消" onclick="doClose();" />
			</div>
		</div>
		<%--<div id="uploadErrorDiv" style="display: ${result==GlobalConstant.FLAG_N?'':'none'}">--%>
			<%--<div style="color: red;height: 60px;text-align: center;vertical-align: middle;">--%>
			<%--${fileErrorMsg[1]}--%>
			<%--</div>--%>
			<%--<div class="button">--%>
				<%--<input class="search" type="button" value="重新上传" onclick="reUpload();" />--%>
				<%--<input class="search" type="button" value="取&#12288;消" onclick="doClose();" />--%>
			<%--</div>--%>
		<%--</div>--%>
	</div>
</div>
</form>
</body>
</html>