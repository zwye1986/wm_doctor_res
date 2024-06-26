<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>

<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="true"/>
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
	<jsp:param name="jquery_mask" value="true"/>
</jsp:include>
	<style type="text/css">
		.checkboxB{
			display: none;
			background-color:ghostwhite;
		}
	</style>
<script type="text/javascript">

	$(document).ready(function(){
	});
function save() {
	if(!$("#manageForm").validationEngine("validate")){
		return false;
	}
	jboxConfirm("确认保存信息？",function(){
		var url = "<s:url value='/erp/crm/productManage/saveManageProcess'/>";
		jboxPost(url,$("#manageForm").serialize(),function(resp){
			if('1'==resp){
				jboxTip("保存成功");
				setTimeout(function(){
					window.parent.frames['mainIframe'].window.toPage(1);
					jboxClose();
				},2000)
			}else
			{
				jboxTip(resp);
			}
		},null,false);
	},null);
}
</script>
</head>
<body>
<div class="mainright">
<div class="content">
	<div class="title1 clearfix">
			<form id="manageForm" >
				<input name="manageFlow" value="${manage.manageFlow}" type="hidden">
				<input name="userName" value="${user.userName}" type="hidden">
				<input name="userFlow" value="${user.userFlow}" type="hidden">
				<input name="processTime" value="${thisTime}" type="hidden">
				<c:if test="${type eq 'Complete' }">
					<input name="processTypeId" value="Complete" type="hidden">
					<input name="processTypeName" value="完成" type="hidden">
				</c:if>
				<c:if test="${type ne 'Complete' }">
					<input name="processTypeId" value="Processing" type="hidden">
					<input name="processTypeName" value="进行中" type="hidden">
				</c:if>
				<table width="100%" cellpadding="0" cellspacing="0" class="basic">
					<colgroup>
						<col width="30%"/>
						<col width="70%"/>
					</colgroup>
					<tr>
						<td style="text-align: right;padding-right: 10px;">
							<c:if test="${type eq 'Complete' }">
								项目总结：
							</c:if>
							<c:if test="${type ne 'Complete' }">
								跟进内容：
							</c:if>
						</td>
						<td>
						<textarea class="validate[required,maxSize[500]] xltxtarea" style="margin-left: 0px;resize: none;row-span: 4;"  placeholder="500个字符以内"
								  name="processContent"></textarea>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;">
							填写人：
						</td>
						<td>
							${user.userName}
						</td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;">
							填写时间：
						</td>
						<td>
							${thisTime}
						</td>
					</tr>
				</table>
				<div class="button">
					<input id="saveButton" class="search" type="button" value="保&#12288;存" onclick="save();" />
					<input class="search" type="button" value="关&#12288;闭" onclick="jboxClose();" />
				</div>
			</form>
			</div>
		</div>
	</div>
</body>
</html>