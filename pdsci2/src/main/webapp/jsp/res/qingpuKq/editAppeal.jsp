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
			var url = "<s:url value='/res/qingpuKq/saveAppeal'/>";
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
</script>
</head>
<body>
	<div class="mainright" align="center">
	<div class="content">
	<span class="title1 clearfix" >
		<form id="editForm" style="position: relative;" enctype="multipart/form-data">
		<input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
		<input type="hidden" name="recordFlow" value="${kq.recordFlow}"/>
		<input type="hidden" name="doctorFlow" value="${kq.doctorFlow}"/>
		<input type="hidden" name="doctorName" value="${kq.doctorName}"/>
		<input type="hidden" name="deptFlow" value="${process.deptFlow}"/>
		<table class="basic" style="width: 100%;">
			<colgroup>
				<col width="17%"/>
				<col width="33%"/>
				<col width="17%"/>
				<col width="33%"/>
			</colgroup>
			<tbody>
				<tr>
					<th><font color="red" >*</font>申诉类型：</th>
					<td colspan="3">
						<select name="qingpuKqTypeDetailId" class="qselect validate[required]" style="width: 110px;">
							<option value="">请选择</option>
							<c:forEach var="type" items="${qingpuKqAppealTypeEnumList}">
								<option value="${type.id}" <c:if test="${kq.qingpuKqTypeDetailId==type.id}">selected="selected"</c:if> >${type.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th><font color="red" >*</font>申诉时间：</th>
					<td>
						<input type="text" name="startDate" value="${kq.startDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
							   class="qtime validate[required]" readonly="readonly" style="width: 110px;"/>
						~ <input type="text" name="endDate" value="${kq.endDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
								 class="qtime validate[required]" readonly="readonly" style="width: 110px;"/>
					</td>
					<th><font color="red" >*</font>申诉天数：</th>
					<td>
						<input type="text" name="intervalDays" value="${kq.intervalDays}" class="qtext validate[required,custom[number]]">
					</td>
				</tr>
				<tr>
					<th><font color="red" >*</font>申诉事由：</th>
					<td colspan="3" style="text-align:left;padding: 5px;">
					     <textarea placeholder="申诉事由（限250字）。"  class="validate[required] validate[maxSize[250]] xltxtarea" name="doctorRemarks">${kq.doctorRemarks}</textarea>
			     	</td>
				</tr>
				<tr>
					<th>轮转科室：</th>
					<td>
						<c:if test="${empty kq.schDeptName}">${process.schDeptName}</c:if>
						<c:if test="${not empty kq.schDeptName}">${kq.schDeptName}</c:if>
						<input type="hidden" name="schDeptFlow" value="${empty kq.schDeptFlow?process.schDeptFlow:kq.schDeptFlow}">
						<input type="hidden" name="schDeptName" value="${empty kq.schDeptName?process.schDeptName:kq.schDeptName}">
					</td>
					<th>轮转时间：</th>
					<td>
						<c:if test="${empty kq.schDeptStartDate}">${process.startDate}</c:if>
						<c:if test="${not empty kq.schDeptStartDate}">${kq.schDeptStartDate}</c:if>
						~
						<c:if test="${empty kq.schDeptEndDate}">${process.endDate}</c:if>
						<c:if test="${not empty kq.schDeptEndDate}">${kq.schDeptEndDate}</c:if>
						<input type="hidden" name="schDeptStartDate" value="${empty kq.schDeptStartDate?process.startDate:kq.schDeptStartDate}">
						<input type="hidden" name="schDeptEndDate" value="${empty kq.schDeptEndDate?process.endDate:kq.schDeptEndDate}">
					</td>
				</tr>
			</tbody>
		</table>
			<input type="hidden" name="teacherFlow" value="${empty kq.teacherFlow?process.teacherUserFlow:kq.teacherFlow}">
			<input type="hidden" name="teacherName" value="${empty kq.teacherName?process.teacherUserName:kq.teacherName}">
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