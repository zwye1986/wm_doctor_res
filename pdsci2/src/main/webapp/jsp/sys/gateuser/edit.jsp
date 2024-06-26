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
	</jsp:include>
	<script type="text/javascript">

		function save(){
			$("#userCode").val($.trim($("#userCode").val()));
			if (!$("#userForm").validationEngine("validate")) {
				return;
			}
			var url = "<s:url value='/sys/gateuser/saveUser'/>";

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
					<table style="width: 100%;" cellpadding="0" cellspacing="0" class="basic">
						<tr>
							<td style="min-width: 80px;text-align: right;">用户名：</td>
							<td>
								<input  name="userCode" id="userCode" class="validate[required] xltext" type="text" value="${user.userCode}"/>
							</td>
						</tr>
						<tr>
							<td style="min-width: 80px;text-align: right;">系统管理：</td>
							<td>
								<c:forEach items="${sysRoleList}" var="role">
									<div style="width:99.9%;float: left">
										<label id="${role.roleFlow }" class="otherDept">
											<input type="checkbox" name="roleFlows" class="validate[required]" value="${role.roleFlow }"
												   <c:if test="${fn:contains(roleFlows.roleName, role.roleFlow) }">checked</c:if>
											/>${role.roleName}&#12288;&#12288;</label>
									</div>
								</c:forEach>
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