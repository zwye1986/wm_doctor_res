<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
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
	<jsp:param name="ueditor" value="true"/>
</jsp:include>
<script>
	function save(){
		if(!$("#editForm").validationEngine("validate")){
			return false;
		}
		var startDate = $("input[name='startDate']").val();
		var endDate = $("input[name='endDate']").val();
		if(endDate < startDate){
			jboxTip("结束日期不能早于开始日期！");
			return false;
		}
		jboxConfirm("确认保存？",function(){
			var url = "<s:url value='/res/qingpuKq/saveSubmit'/>";
			jboxPost(url,$('#editForm').serialize(),function (resp) {
				if ("1" == resp) {
					window.parent.frames['mainIframe'].search();
					setTimeout(function () {
						jboxClose();
					}, 1000);
				}
			},null,false);
		})
	}
	function changeDate(){
		var schDeptName = $("select[name='doctorFlow'] option:selected").attr("schDeptName");
		$(".schDeptName").text(schDeptName);

		var schDeptDate = $("select[name='doctorFlow'] option:selected").attr("schStartDate")+"~"+$("select[name='doctorFlow'] option:selected").attr("schEndDate");
		$(".schDeptDate").text(schDeptDate);

		var schDeptFlow = $("select[name='doctorFlow'] option:selected").attr("schDeptFlow");
		$("#schDeptFlow").val(schDeptFlow);

		var schDeptName = $("select[name='doctorFlow'] option:selected").attr("schDeptName");
		$("#schDeptName").val(schDeptName);

		var schDeptStartDate = $("select[name='doctorFlow'] option:selected").attr("schStartDate");
		$("#schDeptStartDate").val(schDeptStartDate);

		var schDeptEndDate = $("select[name='doctorFlow'] option:selected").attr("schEndDate");
		$("#schDeptEndDate").val(schDeptEndDate);

		var doctorName = $("select[name='doctorFlow'] option:selected").attr("userName");
		$("#doctorName").val(doctorName);
	}
</script>
</head>
<body>
	<div class="mainright" align="center">
	<div class="content">
	<span class="title1 clearfix" >
		<form id="editForm" style="position: relative;" enctype="multipart/form-data">
		<input type="hidden" name="roleFlag" value="${roleFlag}"/>
		<input type="hidden" name="recordFlow" value="${kq.recordFlow}"/>
		<input type="hidden" id="doctorName" name="doctorName" value="${kq.doctorName}"/>
		<input type="hidden" id="schDeptFlow" name="schDeptFlow" value="${kq.schDeptFlow}">
		<input type="hidden" id="schDeptName" name="schDeptName" value="${kq.schDeptName}">
		<input type="hidden" id="schDeptStartDate" name="schDeptStartDate" value="${kq.schDeptStartDate}">
		<input type="hidden" id="schDeptEndDate" name="schDeptEndDate" value="${kq.schDeptEndDate}">
		<%--<input type="hidden" name="deptFlow" value="${process.deptFlow}"/>--%>
		<table class="basic" style="width: 100%;">
			<colgroup>
				<col width="17%"/>
				<col width="33%"/>
				<col width="17%"/>
				<col width="33%"/>
			</colgroup>
			<tbody>
				<tr>
					<th><font color="red" >*</font>学员姓名：</th>
					<td colspan="3">
						<select name="doctorFlow" class="qselect validate[required]" onchange="changeDate()">
							<option value="">请选择</option>
							<c:forEach var="user" items="${users}">
								<option value="${user.USER_FLOW}" <c:if test="${kq.doctorFlow eq user.USER_FLOW}">selected="selected"</c:if>
										schDeptFlow="${user.SCH_DEPT_FLOW}" schDeptName="${user.SCH_DEPT_NAME}"
										schStartDate="${user.SCH_START_DATE}" schEndDate="${user.SCH_END_DATE}"
										userName="${user.USER_NAME}"
								>${user.USER_NAME}&#12288;${user.SCH_DEPT_NAME}（${user.SCH_START_DATE}~${user.SCH_END_DATE}）</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th><font color="red" >*</font>违纪类型：</th>
					<td colspan="3">
						<select name="qingpuKqTypeDetailId" class="qselect validate[required]" style="width: 110px;">
							<option value="">请选择</option>
							<c:forEach var="type" items="${qingpuKqSubmitTypeEnumList}">
								<option value="${type.id}" <c:if test="${kq.qingpuKqTypeDetailId eq type.id}">selected="selected"</c:if> >${type.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th><font color="red" >*</font>违纪时间：</th>
					<td>
						<input type="text" name="startDate" value="${kq.startDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
							   class="qtime validate[required]" readonly="readonly" style="width: 110px;"/>
						~ <input type="text" name="endDate" value="${kq.endDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
								 class="qtime validate[required]" readonly="readonly" style="width: 110px;"/>
					</td>
					<th><font color="red" >*</font>违纪天数：</th>
					<td>
						<input type="text" name="intervalDays" value="${kq.intervalDays}" class="qtext validate[required,custom[number]]">
					</td>
				</tr>
				<tr>
					<th><font color="red" >*</font>违纪详情：</th>
					<td colspan="3" style="text-align:left;padding: 5px;">
					     <textarea placeholder="违纪详情（限250字）。"  class="validate[required] validate[maxSize[250]] xltxtarea" name="doctorRemarks">${kq.doctorRemarks}</textarea>
			     	</td>
				</tr>
				<tr>
					<th>轮转科室：</th>
					<td class="schDeptName">
						${kq.schDeptName}
					</td>
					<th>轮转时间：</th>
					<td class="schDeptDate">
						${kq.schDeptStartDate}
						~
						${kq.schDeptEndDate}
					</td>
				</tr>
			</tbody>
		</table>
		</form>
		<p style="text-align: center;">
	      	<input type="button" onclick="save();" class="search" value="保&#12288;存"/>
	      	<input type="button" onclick="jboxClose();" class="search" value="关&#12288;闭"/>
	    </p>
	</div>
	</div>
	</div>
</body>
</html>