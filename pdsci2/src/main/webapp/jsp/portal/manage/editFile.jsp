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
		<jsp:param name="jquery_form" value="true"/>
	</jsp:include>
<script type="text/javascript">
	function checkFile(file){
		<%--var filePath = file.value;--%>
		<%--var types = "${sysCfgMap['inx_image_support_suffix']}".split(",");--%>
		<%--var regStr = "/";--%>
		<%--for(var i = 0 ;i<types.length;i++){--%>
			<%--if(types[i]){--%>
				<%--if(i==(types.length-1)){--%>
					<%--regStr = regStr+"\\"+types[i]+'$';--%>
				<%--}else{--%>
					<%--regStr = regStr+"\\"+types[i]+'$|';--%>
				<%--}--%>
			<%--}--%>
		<%--}--%>
		<%--regStr = regStr+'/i';--%>
		<%--regStr = eval(regStr);--%>
		<%--if($.trim(filePath)!="" && !regStr.test(filePath)){--%>
			<%--file.value = "";--%>
			<%--jboxTip("请上传&nbsp;${sysCfgMap['inx_image_support_suffix']}格式的图片");--%>
		<%--}--%>
	}

	function checkUploadFile(){
		if (!$("#uploadFileForm").validationEngine("validate")) {
			return;
		}
		jboxConfirm("确认上传？",function(){
			jboxSubmit($("#uploadFileForm"),"<s:url value='/portal/manage/info/uploadFile'/>",function(resp){
				if(resp==1){
					jboxTip("上传成功");
					window.parent.frames['mainIframe'].window.search();
					top.jboxClose();
				}
			},null,false);
		})
	}

</script>
<style type="text/css">
</style>
</head>
<body>
<form id="uploadFileForm" method="post" action="<s:url value='/portal/manage/info/uploadFile'/>" enctype="multipart/form-data">
	<div class="content">
		<div class="title1 clearfix">
			<div id="uploadFileDiv" style="display: ${empty result?'':'none'}">
				<div style="height: 60px;text-align: center;vertical-align: middle;">
					<input type="file" name="uploadFile" class="validate[required]" style="border: none;" onchange="checkFile(this);"/>
				</div>
				<div class="button">
					<input class="search" type="button" value="确&#12288;定" onclick="checkUploadFile();" />
					<input class="search" type="button" value="取&#12288;消" onclick="jboxClose();" />
				</div>
			</div>
		</div>
	</div>
</form>
</body>
</html>