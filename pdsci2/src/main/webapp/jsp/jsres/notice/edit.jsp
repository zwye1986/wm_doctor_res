
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="font" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="kindeditor" value="true"/>
</jsp:include>

<script type="text/javascript">
	$(document).ready(function(){
		//实例化编辑器
		var editor = KindEditor.create('#rotationStandard_ueditor', {
			uploadJson : "<s:url value='/kindeditor/upload'/>",
			fileManagerJson : "<s:url value='/kindeditor/upload'/>",
			resizeType : 0,
			allowPreviewEmoticons : false,
			allowImageUpload : true,
			items : [
				'source', '|', 'undo', 'redo', '|',
				'preview','template', 'cut', 'copy', 'paste',
				'plainpaste', 'wordpaste', '|', 'justifyleft',
				'justifycenter', 'justifyright',
				'justifyfull', 'insertorderedlist',
				'insertunorderedlist', 'indent', 'outdent', 'subscript',
				'superscript', 'clearhtml', 'quickformat', 'selectall',
				'|', 'fullscreen', '/',
				'formatblock', 'fontname', 'fontsize', '|', 'forecolor',
				'hilitecolor', 'bold',
				'italic', 'underline', 'strikethrough', 'lineheight',
				'removeformat', '|', 'image',
				'flash', 'media', 'insertfile', 'table', 'hr',
				'emoticons', 'link', 'unlink', '|'],//image打开本地上传图片必须写,重要的事情说三遍
			afterBlur : function() {
				//焦点问题，这里不写会出问题.同步KindEditor的值到textarea文本框
				this.sync();
			}
		});
	});

	function saveNotice(){
		if($("#title").val().length==0){
			jboxTip("公告标题不能为空!");
			return;
		}
		if($("#title").val().length>50){
			jboxTip("公告标题不能大于50个字符或汉字!");
			return;
		}
		var title = $.trim($("#title").val());
		var infoFlow = $("#infoFlow").val();
		var content = $("#rotationStandard_ueditor").val();
		var columnId = $("#columnId").val();
		var requestData ={
				"infoFlow":infoFlow,
				"infoTitle":title,
				"infoContent":content,
				"columnId":columnId
		};
		jboxStartLoading();
		var url = "<s:url value='/jsres/notice/saveNotice/global'/>";
		jboxPost(url,requestData,function(resp){
			if(resp=='${GlobalConstant.OPRE_SUCCESSED}'){
				window.parent.toPage(1);
				setTimeout(function(){
					top.jboxClose();
				},500);
			}
		},null,true);
	}
</script>

</head>
<body style="overflow: auto;height: 480px;width: 900px;">
<div id="bodyDiv" style="height: 100%;overflow: auto;">
	<div style="margin-bottom: 20px;">
		<div style="margin-top: 5px;padding-left: 15px;padding-right: 15px;">
			<form id="editForm" method="post">
				<input type="hidden" id="infoFlow" name="infoFlow" value="${info.infoFlow}"/>
				<input type="hidden" id="orgFlow" name="orgFlow" value=""/>
				<input type="hidden" id="orgName" name="orgName" value=""/>
				<input type="hidden" id="columnId" name="columnId" value="LM05"/>
				<div style="height: auto;margin-top: 5px;">
					<table style="width: 100%;">
						<tr>
							<td width="10%" style="text-align: right;"><span style="color: red;">*</span>标题：</td>
							<td width="60%"><input id="title" name="title" class="input" value="${info.infoTitle}" style="height: 22px;width:98%;"/></td>
							<td width="10%"><input type="button" id="saveBtn" value="发&#12288;布" onclick="saveNotice();" class="btn_green"/></td>
						</tr>
						<tr>
							<td></td>
							<td colspan="3" style="padding-top: 4px">
								<textarea style="height: 350px;width: 704px;padding-top: 20px" id="rotationStandard_ueditor" name="content" >${info.infoContent}</textarea>
							</td>
						</tr>
					</table>
				</div>
<%--				<script id="ueditor" name="content" type="text/plain" style="height:250px;">${info.infoContent}</script>--%>
			</form>
		</div>
	</div>
</div>
</body>
</html>
