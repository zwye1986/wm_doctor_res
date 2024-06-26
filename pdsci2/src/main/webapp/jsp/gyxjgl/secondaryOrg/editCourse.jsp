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
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>
<script type="text/javascript">

	function saveOpt(statusId){
		if(false == $("#myForm").validationEngine("validate")){
			return false;
		}
		jboxConfirm('确认'+ (statusId=='Save'?'保存':'提交') + '?',function(){
			jboxPost("<s:url value='/gyxjgl/secondaryOrg/saveCourse?flag=${param.flag}'/>&statusId="+statusId , $("#myForm").serialize() , function(resp){
				window.parent.frames['mainIframe'].location.reload(true);
			} , null , true);
		});
	}
	function audit(status){
		var v=status=="${GlobalConstant.FLAG_Y }"?"通过":"不通过";
		jboxConfirm('确认审核'+v+'?',function(){
			jboxPost("<s:url value='/gyxjgl/change/apply/auditApply'/>?recordFlow="+recordFlow+"&status="+status ,$("#form").serialize()  , function(resp){
					top.document.mainIframe.location.reload();
					jboxClose();
				if("${GlobalConstant.OPRE_SUCCESSED}"==resp){
				}
			} , null , true);
		});
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form id="myForm">
			<table class="basic" style="width:100%;border:0px;">
				<tr>
					<td style="padding:0px;border:0px;">
						<input type="hidden" name="courseFlow" value="${course.courseFlow}">
						<c:if test="${param.flag eq '1'}">
							<textarea class="validate[required] xltextarea" style="width:100%;height:150px;" ${param.viewFlag eq 'Y'?"readonly='readonly'":''} name="classApplyMemo">${course.classApplyMemo}</textarea>
						</c:if>
						<c:if test="${param.flag eq '2'}">
							<textarea class="validate[required] xltextarea" style="width:100%;height:150px;" ${param.viewFlag eq 'Y'?"readonly='readonly'":''} name="courseScheduleMemo">${course.courseScheduleMemo}</textarea>
						</c:if>
						<c:if test="${param.flag eq '3'}">
							<textarea class="validate[required] xltextarea" style="width:100%;height:150px;" ${param.viewFlag eq 'Y'?"readonly='readonly'":''} name="courseEvaluateMemo">${course.courseEvaluateMemo}</textarea>
						</c:if>
					</td>
				</tr>
			</table>
		</form>
		<div style="text-align:center;margin-top:20px;">
			<c:if test="${param.viewFlag ne 'Y' && ((param.flag eq '1' && course.classApplyFlag eq 'Save')||(param.flag eq '2' && course.courseScheduleFlag eq 'Save')||(param.flag eq '3' && course.courseEvaluateFlag eq 'Save'))}">
				<input type="button" class="search" value="保&#12288;存" onclick="saveOpt('Save');"/>
			</c:if>
			<input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
			<c:if test="${param.viewFlag ne 'Y'}">
				<input type="button" class="search" value="提&#12288;交" onclick="saveOpt('Submit');"/>
			</c:if>
		</div>
	</div>
</div>
</body>
</html>