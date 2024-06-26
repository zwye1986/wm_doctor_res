
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
	<jsp:param name="jquery_form" value="false" />
	<jsp:param name="jquery_validation" value="true" />
</jsp:include>
<style type="text/css">
</style>
<script type="text/javascript">
	function save2(absenceFlow, agreeFlag){
		if(!$("#save").validationEngine("validate")){
			return;
		}
		var title = "同意";
		if("${GlobalConstant.FLAG_N}" == agreeFlag){
			title = "不同意";
		}
		jboxConfirm("确认"+ title +"请假? " ,  function(){
			jboxStartLoading();
			var url = "<s:url value='/res/teacher/saveAbsenceAudit'/>";
			var data = {
					absenceFlow : absenceFlow,
					<c:if test="${GlobalConstant.RES_ROLE_SCOPE_TEACHER == resRoleScope}">
						teacherAgreeFlag:agreeFlag,
						teacherAuditInfo:$("#auditInfo").val()
					</c:if>
					<c:if test="${GlobalConstant.RES_ROLE_SCOPE_HEAD == resRoleScope}">
						headAgreeFlag:agreeFlag,
						headAuditInfo:$("#auditInfo").val()
					</c:if>
					<c:if test="${GlobalConstant.RES_ROLE_SCOPE_MANAGER == resRoleScope}">
						managerAgreeFlag:agreeFlag,
						managerAuditInfo:$("#auditInfo").val()
					</c:if>
			};
			jboxPost(url, data,
				function(resp){
					if("${GlobalConstant.SAVE_SUCCESSED}" == resp){
						window.parent.frames['mainIframe'].location.reload(true);
						jboxClose();
					}
				}, null, true);
			
		});
	}
</script>

</head>
<body>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
				<form id="save" style="position: relative;">
				<textarea id="auditInfo" style="width: 100%;height: 180px;" class="validate[maxSize[200]]"></textarea>
				</form>
			</div>
			<div style="text-align: center">
				<input value="保&#12288;存" type="button" class="search" onclick="save2('${absenceFlow}','${agreeFlag}')">&#12288;
					<input value="取&#12288;消" type="button" class="search" onclick="jboxClose();">
			</div>
		</div>
	</div>
</body>
</html>