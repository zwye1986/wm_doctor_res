<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/moment${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">

$(function(){
	$('.datepicker').datepicker();
});
function refEndDate(){
	var endDate = moment($("#startDate").val()).add('${schRotationDept.schMonth }', 'months').format("YYYY-MM-DD"); 
	$("#endDate").val(endDate);
}
function saveInDept(){
	if(false==$("#inDeptForm").validationEngine("validate")){
		return false;
	}
	if($("#endDate").val()<$("#startDate").val()){
		jboxTip("结束时间不能早于开始时间!");
		return false;
	}
	jboxConfirm("确定入科?",function(){
		jboxPost("<s:url value='/jsres/doctor/saveInDept'/>",$("#inDeptForm").serialize(),function(){
			jboxTip("${GlobalConstant.SAVE_SUCCESSED}");
			window.parent.process();
			jboxClose();		
		},null,true);
	});
}
</script>
</head>
<body>
			<form  id="inDeptForm" style="height: 250px;position: relative;">
			<table  style="width: 100%;border: 1px solid #e3e3e3;">
				<tr>
					<td style="border: 1px solid #e3e3e3;width:120px;height: 40px;padding-left: 10px;">标准科室
					<input type="hidden" name="recordFlow" value="${ schRotationDept.recordFlow}"/>
					</td>
					<td style="border: 1px solid #e3e3e3;padding-left: 10px;">${schRotationDept.standardDeptName }</td>
				</tr>
				
				<tr>
					<td style="border: 1px solid #e3e3e3;width:100px;height: 40px;padding-left: 10px;">要求轮转时间</td>
					<td style="border: 1px solid #e3e3e3;padding-left: 10px;height: 40px;">${schRotationDept.schMonth }个月</td>
				</tr>
				<tr>
					<td style="border: 1px solid #e3e3e3;width:100px;height: 40px;padding-left: 10px;">轮转时间</td>
					<td style="border: 1px solid #e3e3e3;">
						<input type="text" name="startDate" id="startDate" class="input validate[required] datepicker" onchange="refEndDate();" style="width: 100px;" readonly="readonly">-
						<input type="text" name="endDate"  id="endDate" class="input validate[required] datepicker" style="width: 100px;" readonly="readonly">
					</td>
				</tr>
			</table>
	</form>
	<div class="button">
				<input class="btn_blue" type="button" value="入&#12288;科" onclick="saveInDept();" />
				<input class="btn_blue" type="button" value="取&#12288;消" onclick="jboxClose();" />
			</div>		

</body>
</html>