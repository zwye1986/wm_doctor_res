<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<title>${sysCfgMap['sys_title_name']}</title>
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="font" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_cxselect" value="true"/>
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
	</jsp:include>
	<script type="text/javascript">
		/**
		 * 保存
		 */
		function save(){
			if(false==$("#editForm").validationEngine("validate")){
				return false;
			}
			var recFlow = $("#recFlow").val();
			var doctorFlow = $("#doctorFlow").val();
			var processFlow = $("#processFlow").val();
			var schDeptFlow = $("#schDeptFlow").val();
			var $recTypeId = $("#editForm input[name='recTypeId']:checked");
			var recTypeId = $recTypeId.val();
			if(recTypeId == '' || recTypeId == undefined){
			    jboxTip("请选择评估表单！");
			    return false;
			}
			var recTypeName;
			if (recTypeId == 'TeacherDoctorGrade') {
				recTypeName = "住院医师评临床专业指导医师估量表";
			} else {
				recTypeName = "住院医师评估医技专业指导医师量表";
			}
			<%--jboxOpen("<s:url value='/res/teacherEvaDoctor/grade'/>?recTypeId=" + recTypeId + "&recFlow=" + recFlow+ "&doctorFlow=" + doctorFlow,--%>
			<%--		'带教评估学员', 1200, 800);--%>
			// window.parent.grade(recTypeName, recTypeId, recFlow, '', '', '', '', doctorFlow);
			// jboxClose();
			top.grade(recTypeName, recTypeId, recFlow, processFlow, '', schDeptFlow, '', doctorFlow);
			top.jboxCloseMessager();
		}

	</script>
</head>

<body>
<div class="infoAudit">
	<div class="div_table">
		<form id="editForm" style="position: relative;" method="post">
			<input type="hidden" name="doctorFlow"  id="doctorFlow" value="${doctorFlow}"/>
			<input type="hidden" id="recFlow" name="recFlow" value="${recFlow}"/>
			<input type="hidden" name="schDeptFlow" id="schDeptFlow" value="${param.schDeptFlow}"/>
			<input type="hidden" name="processFlow" id="processFlow" value="${param.processFlow}"/>
			<table border="0" cellpadding="0" cellspacing="0" class="base_info">
				<colgroup>
					<col width="40%"/>
					<col width="60%"/>
				</colgroup>
				<tbody>
				<tr>
					<th><span id="red" class="red">*</span>评分表单：</th>
					<td id="catSpeTd">
						<span><input type="radio" name="recTypeId" value="TeacherDoctorAssess" />临床专业指导医师评估住院医师量表</span>&#12288;&#12288;
						<span><input type="radio" name="recTypeId" value="TeacherDoctorAssessTwo" />医技专业指导医师评估住院医师量表</span>
					</td>
				</tr>
				</tbody>
			</table>
			</form>

		<div align="center" style="margin-top: 20px; margin-bottom:20px;">
			<input type="button" id="saveBtn" class="btn_green" onclick="save();" value="确定"/>&nbsp;
			<input type="button" class="btn_green" onclick="top.jboxCloseMessager();" value="关闭"/>&nbsp;
		</div>
	</div>
</div>
</body>
</html>