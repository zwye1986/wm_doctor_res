<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="compatible" value="true"/>
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
		<jsp:param name="jquery_ui_combobox" value="false"/>
		<jsp:param name="jquery_ui_sortable" value="false"/>
		<jsp:param name="jquery_cxselect" value="false"/>
		<jsp:param name="jquery_scrollTo" value="false"/>
		<jsp:param name="jquery_jcallout" value="false"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_fullcalendar" value="false"/>
		<jsp:param name="jquery_fngantt" value="false"/>
		<jsp:param name="jquery_fixedtableheader" value="true"/>
		<jsp:param name="jquery_placeholder" value="true"/>
		<jsp:param name="jquery_iealert" value="false"/>
		<jsp:param name="jquery_ztree" value="true"/>
		<jsp:param name="kindeditor" value="true"/>
	</jsp:include>
	<script type="text/javascript"
			src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			//实例化编辑器
			KindEditor.ready(function(K) {
				var editor = K.create('#rotationStandard_ueditor', {
					uploadJson : "<s:url value='/kindeditor/upload'/>",
					fileManagerJson : "<s:url value='/kindeditor/upload'/>",
					resizeType : 0,
					allowPreviewEmoticons : false,
					allowImageUpload : true,
					items : [
						'source', '|', 'undo', 'redo', '|', 'preview','template', 'cut', 'copy', 'paste',
						'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
						'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
						'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
						'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
						'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
						'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'link', 'unlink', '|'],//image打开本地上传图片必须写,重要的事情说三遍
					afterBlur : function() {
						//焦点问题，这里不写会出问题.同步KindEditor的值到textarea文本框
						this.sync();
					}
				});
			});
		});

		function closeJbox() {
			jboxConfirm("是否确认关闭？", function () {
				top.jboxClose();
			}, null);
		}

		function saveMessage(){
			if($("#title").val().length==0){
				jboxTip("公告标题不能为空!");
				return;
			}
			if($("#title").val().length>50){
				jboxTip("公告标题不能大于50个字符或汉字!");
				return;
			}
			var title = $.trim($("#title").val());
			var messageFlow = $("#messageFlow").val();
			var content = $("#rotationStandard_ueditor").val();
			var requestData ={
				"messageFlow":messageFlow,
				"messageTitle":title,
				"messageContent":content
			};
			jboxStartLoading();
			var url = "<s:url value='/jsres/message/saveMessage'/>";
			jboxPost(url,requestData,function(resp){
				if(resp=='${GlobalConstant.OPERATE_SUCCESSED}'){
					window.parent.toPage(1);
					setTimeout(function(){
						top.jboxClose();
					},500);
				}
			},null,true);
		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<form id="rotationStandard" method="post">
				<input type="hidden" id="messageFlow" name="messageFlow" value="${message.messageFlow}"/>
				<input type="hidden" id="orgFlow" name="orgFlow" value=""/>
				<input type="hidden" id="orgName" name="orgName" value=""/>

				<table style="width: 100%;">
					<tr>
						<th class="td_blue" width="100px">标&#12288;&#12288;题：</th>
						<td colspan="3">
							<input  class="validate[required,maxSize[100]] text-input xltext" id="title"
								   type="text" style="width: 90%;margin: 10px 0px" value="${message.messageTitle}"/>
						</td>
					</tr>

					<tr>
						<th class="td_blue">资讯内容：</th>
						<td colspan="3">
							<textarea style="height: 350px;width: 750px"  id="rotationStandard_ueditor" name="schStandard" >${message.messageContent}</textarea>
						</td>
					</tr>
				</table>
					<div align="center" style="margin-top: 10px;">
						<input type="button" value="保&#12288;存" class="search" onclick="saveMessage();"/>
						<input type="button" value="关&#12288;闭" class="search" onclick="top.jboxClose();"/>
					</div>

			</form>
		</div>
	</div>
</div>
</div>
</body>
</html>