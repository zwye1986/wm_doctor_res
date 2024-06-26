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
							<td style="min-width: 80px;text-align: right;">中西医类型：</td>
							<td>
								<label><input  name="weiXinId" class="validate[required]" type="radio" value="0" ${user.weiXinId eq '0'?'checked':''}/>中医</label>&#12288;
								<label><input  name="weiXinId" class="validate[required]" type="radio" value="1" ${user.weiXinId eq '1'?'checked':''}/>西医</label>
							</td>
						</tr>
						<tr>
							<td style="min-width: 80px;text-align: right;">基地类型：</td>
							<td>
								<label><input  name="weiXinStatusId" class="validate[required]" type="radio" value="0" ${user.weiXinStatusId eq '0'?'checked':''}/>国家基地</label>&#12288;
								<label><input  name="weiXinStatusId" class="validate[required]" type="radio" value="1" ${user.weiXinStatusId eq '1'?'checked':''}/>协同基地</label>
							</td>
						</tr>
						<tr>
							<td style="min-width: 80px;text-align: right;">所属地区：</td>
							<td>
								<div id="provCityAreaId">
									<select id="nativePlaceProvId" name="nativePlaceProvId" class="validate[required] province xlselect" data-value="${user.nativePlaceProvId}" data-first-title="选择省" style="width: 105px;"></select>
									<select id="nativePlaceCityId" name="nativePlaceCityId" class="validate[required] city xlselect" data-value="${user.nativePlaceCityId}" data-first-title="选择市" style="width: 105px;"></select>
								</div>
								<input id="nativePlaceProvName" name="nativePlaceProvName" type="hidden" value="${user.nativePlaceProvName}">
								<input id="nativePlaceCityName" name="nativePlaceCityName" type="hidden" value="${user.nativePlaceCityName}">
								<script type="text/javascript">
									// 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
									$.cxSelect.defaults.url = "<s:url value='/js/provCityAreaJson.min.json'/>";
									$.cxSelect.defaults.nodata = "none";

									$("#provCityAreaId").cxSelect({
										selects : ["province", "city"],
										nodata : "none",
										firstValue : ""
									});
								</script>
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