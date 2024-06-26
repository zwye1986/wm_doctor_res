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
	function checkUploadFile(){
		if (!$("#submitForm").validationEngine("validate")) {
			return;
		}
		jboxConfirm("确认保存？",function(){
			jboxSubmit($("#submitForm"),"<s:url value='/portal/manage/info/editSuggest'/>",function(resp){
				if(resp==1){
					jboxTip("操作成功");
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
<body style="overflow: auto">
<form id="submitForm" method="post" action="<s:url value='/portal/manage/info/uploadFile'/>" enctype="multipart/form-data">
	<input type="hidden" name="recordFlow" value="${suggest.recordFlow}">
	<div class="content">
		<div class="title1 clearfix">
			<textarea name="replyContent" class="validate[maxSize[1000]] textarea" style="width:98%;height:180px;">${suggest.replyContent}</textarea>
			<div class="button">
				<input class="search" type="button" value="确&#12288;定" onclick="checkUploadFile();" />
				<input class="search" type="button" value="取&#12288;消" onclick="jboxClose();" />
			</div>
		</div>
	</div>
</form>
</body>
</html>