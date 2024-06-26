<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="false"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
		<jsp:param name="jquery_ui_combobox" value="false"/>
		<jsp:param name="jquery_ui_sortable" value="false"/>
		<jsp:param name="jquery_scrollTo" value="false"/>
		<jsp:param name="jquery_jcallout" value="false"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_fullcalendar" value="false"/>
		<jsp:param name="jquery_fngantt" value="false"/>
		<jsp:param name="jquery_fixedtableheader" value="true"/>
		<jsp:param name="jquery_placeholder" value="true"/>
		<jsp:param name="jquery_iealert" value="false"/>
		<jsp:param name="jquery_cxselect" value="true"/>
	</jsp:include>
	<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

	<script type="text/javascript">

		function save(){
			$("#userCode").val($.trim($("#userCode").val()));
			if (!$("#userForm").validationEngine("validate")) {
				return;
			}
			var nativePlaceProvName = $("#nativePlaceProvId :selected").text();
			$("#nativePlaceProvName").val(nativePlaceProvName);
			var nativePlaceCityName = $("#nativePlaceCityId :selected").text();
			$("#nativePlaceCityName").val(nativePlaceCityName);
			var url = "<s:url value='/portal/user/saveUser'/>";
			jboxConfirm("确认保存?",function () {
				jboxPost(url, $("#userForm").serialize(), function(resp){
					if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
						window.parent.frames['mainIframe'].window.toPage(1);
						setTimeout(function(){
							jboxClose();
						},1000);
					}
				}, null, true);
			});
		}
		function uploadImage(){
			$.ajaxFileUpload({
				url:"<s:url value='/sys/user/userHeadImgUpload'/>?userFlow=${user.userFlow}",
				secureuri:false,
				fileElementId:'imageFile',
				dataType: 'json',
				success: function (data, status){
					if(data.indexOf("success")==-1){
						jboxTip(data);
					}else{
						var arr = [];
						arr = data.split(":");
						$("#userHeadImg").val(arr[1]);
						var url = "${sysCfgMap['upload_base_url']}/"+ arr[1];
						$("#showImg").attr("src",url);
					}
				},
				error: function (data, status, e){
					jboxTip('${GlobalConstant.UPLOAD_FAIL}');
				},
				complete:function(){
					$("#imageFile").val("");
				}
			});
		}
	</script>
</head>
<body>
<div class="content">
	<div class="title1 clearfix">
		<div id="tagContent">
			<div class="tagContent selectTag" id="tagContent0">
				<div>
					<form id="userForm" >
					<input type="hidden" name="userFlow" value="${user.userFlow}"/>
					<input type="hidden" name="roleFlows" value="${roleFlow}"/>
					<input type="hidden" name="nationId" value="portal"/>
					<table style="width: 100%;" cellpadding="0" cellspacing="0" class="basic">
						<tr>
							<td style="min-width: 80px;text-align: right;">用户名：</td>
							<td>
								<input  name="userCode" id="userCode" class="validate[required] xltext" type="text" value="${user.userCode}"/>
							</td>
						</tr>
						<tr>
							<td style="min-width: 80px;text-align: right;">姓名：</td>
							<td>
								<input  name="userName" id="userName" class="validate[required] xltext" type="text" value="${user.userName}"/>
							</td>
						</tr>
						<tr>
							<td style="min-width: 80px;text-align: right;">职务：</td>
							<td>
								<input  name="titleName" id="titleName" class="xltext" type="text" value="${user.titleName}"/>
							</td>
						</tr>
						<tr>
							<td style="min-width: 80px;text-align: right;">头像：</td>
							<td>
								<div style="width: 110px;height: 130px;margin: 5px auto 0px;">
									<img src="${sysCfgMap['upload_base_url']}/${user.userHeadImg}" id="showImg" width="100%"
										 height="100%" onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
								</div>
								<div>
								<span style="cursor: pointer;padding-left:44px;">
								[<a onclick="$('#imageFile').click();">${empty user.userHeadImg?'上传头像':'重新上传'}</a>]
								</span>
									<input type="file" id="imageFile" name="headImg" style="display: none"
										   onchange="uploadImage();"/>
								</div>
								<input name="userHeadImg" id="userHeadImg" type="hidden" value="${user.userHeadImg}">
							</td>
						</tr>
					</table>

					</form>
					<div class="button">
						<input class="search" type="button" value="保&#12288;存"  onclick="save();" />
						<input class="search" type="button" value="关&#12288;闭"  onclick="jboxClose();" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>