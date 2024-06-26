<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<style type="text/css">
	.table {
		border: 1px solid #e3e3e3;
	}
	.table tr:nth-child(2n) {
		background-color: #fcfcfc;
		transition: all 0.125s ease-in-out 0s;
	}
	.table tr:hover {
		background: #fbf8e9 none repeat scroll 0 0;
	}
	.table th, .table td {
		border-bottom: 1px solid #e3e3e3;
		border-right: 1px solid #e3e3e3;
		text-align: center;
	}
	.table th {
		background: rgba(0, 0, 0, 0) url("<s:url value='/jsp/res/disciple/images/table.png'/>") repeat-x scroll 0 0;
		color: #585858;
		height: 30px;
	}
	.table td {
		height: 30px;
		line-height: 25px;
		text-align: center;
		word-break: break-all;
	}
</style>
<c:set var="isNew" value="${empty resDiscipleInfo}"></c:set>
<script type="text/javascript">
	$(function(){
		<c:if test="${!isNew}">
		$(".readonly").attr("disabled","disabled");
		</c:if>
	});
	function showSave(obj)
	{
		$(obj).hide();
		$("#save").show();
		$(".readonly").removeAttr("disabled");
	}
	function showIndex(){
		var src="<s:url value="/res/disciple/discipleIndex"></s:url>";
		window.location.href= src;
	}
	function showRemark()
	{
		jboxOpenContent($("#remark").html(),"说  明",800,400);
	}
	function save()
	{
		var workOrgName=$("#workOrgName").val();
		if(!workOrgName)
		{
			jboxTip("选派单位未填写，无法保存！");
			return false;
		}
		var discipleStartDate=$("#discipleStartDate").val();
		if(!discipleStartDate)
		{
			jboxTip("跟师开始时间未填写，无法保存！");
			return false;
		}
		var discipleEndDate=$("#discipleEndDate").val();
		if(!discipleEndDate)
		{
			jboxTip("跟师结束时间未填写，无法保存！");
			return false;
		}
		if(discipleStartDate>discipleEndDate)
		{
			jboxTip("跟师结束时间小于跟师结束时间，无法保存！");
			return false;
		}
		jboxConfirm("确认保存?",function(){
			jboxPost("<s:url value='/res/disciple/saveDiscipleInfo'/>",$('#saveForm').serialize(),function(resp){
				if(resp=="${GlobalConstant.SAVE_SUCCESSED}") {
					showIndex();
				}
			},null,true);
		});

	}

</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div width="80%" height="100%" style="margin-top: 20px">
			<table class="table" width="80%">
				<tr style="height: 58px">
					<th style="text-align: left;width: 50%;border-right: 0px; "><span style="font-size: 18px;margin-left: 34px">${(empty sysCfgMap['zy_xxfmsf'])?'江苏省':sysCfgMap['zy_xxfmsf']}中医住院医师规范化培训跟师学习手册</span></th>
					<th style="text-align: right;width: 50%;border-left: 0px;"><input type="button" class="search" onclick="showRemark()" value="说&#12288;明" style="margin-right: 20px;"></th>
				</tr>
			</table>
			<br>
			<table class="table" width="80%">
				<form id="saveForm">
					<input type="text" class="inputText" value="${resDiscipleInfo.discipleFlow}" name="discipleFlow" hidden style="margin-left: 20px;">
				<tr style="height: 54px">
					<td style="text-align: right;width:30%;border-right: 0px; "><span style="font-size: 18px;margin-right: 34px">姓&#12288;&#12288;名</span></td>
					<td style="text-align: left;width: 70%;border-left: 0px;">
						<input type="text" class="inputText" value="${( empty resDiscipleInfo.doctorName)?resDoctor.doctorName:resDiscipleInfo.doctorName}" disabled style="margin-left: 20px;height: 24px;width: 250px;">
						<input type="text" class="inputText" value="${( empty resDiscipleInfo.doctorName)?resDoctor.doctorName:resDiscipleInfo.doctorName}" name="doctorName" hidden style="margin-left: 20px;height: 24px;width: 250px;">
						<input type="text" class="inputText" value="${( empty resDiscipleInfo.doctorFlow)?resDoctor.doctorFlow:resDiscipleInfo.doctorFlow}" name="doctorFlow" hidden style="margin-left: 20px;">
					</td>
				</tr>
				<tr style="height: 54px">
					<td style="text-align: right;width:30%;border-right: 0px; "><span style="font-size: 18px;margin-right: 34px">学员账号</span></td>
					<td style="text-align: left;width: 70%;border-left: 0px;">
						<input type="text" class="inputText" value="${currUser.userCode}" disabled style="margin-left: 20px;height: 24px;width: 250px;">
					</td>
				</tr>
				<tr style="height: 54px">
					<td style="text-align: right;width: 30%;border-right: 0px; "><span style="font-size: 18px;margin-right: 34px">培训专业</span></td>
					<td style="text-align: left;width: 70%;border-left: 0px;">
						<input type="text" class="inputText" value="${( empty resDiscipleInfo.speName)?resDoctor.trainingSpeName:resDiscipleInfo.speName}" disabled style="margin-left: 20px;height: 24px;width: 250px;">
						<input type="text" class="inputText" value="${( empty resDiscipleInfo.speName)?resDoctor.trainingSpeName:resDiscipleInfo.speName}" name="speName" hidden style="margin-left: 20px;height: 24px;width: 250px;">
						<input type="text" class="inputText" value="${( empty resDiscipleInfo.speId)?resDoctor.trainingSpeId:resDiscipleInfo.speId}" name="speId" hidden style="margin-left: 20px;">
					</td>
				</tr>
				<tr style="height: 54px">
					<td style="text-align: right;width: 30%;border-right: 0px; "><span style="font-size: 18px;margin-right: 34px">选派单位</span></td>
					<td style="text-align: left;width: 70%;border-left: 0px;"><input type="text" class="readonly inputText validate[required]" id="workOrgName" name="workOrgName" value="${resDiscipleInfo.workOrgName}" style="margin-left: 20px;height: 24px;width: 250px;"></td>
				</tr>
				<tr style="height: 54px">
					<td style="text-align: right;width: 30%;border-right: 0px; "><span style="font-size: 18px;margin-right: 34px">培训基地</span></td>
					<td style="text-align: left;width: 70%;border-left: 0px;">
						<input type="text" class="inputText" value="${( empty resDiscipleInfo.orgName)?resDoctor.orgName:resDiscipleInfo.orgName}"disabled style="margin-left: 20px;height: 24px;width: 250px;">
						<input type="text" class="inputText" value="${( empty resDiscipleInfo.orgName)?resDoctor.orgName:resDiscipleInfo.orgName}" name="orgName" hidden style="margin-left: 20px;height: 24px;width: 250px;">
						<input type="text" class="inputText" value="${( empty resDiscipleInfo.orgFlow)?resDoctor.orgFlow:resDiscipleInfo.orgFlow}" name="orgFlow" hidden style="margin-left: 20px;">
					</td>
				</tr>
				<tr style="height: 54px">
					<td style="text-align: right;width: 30%;border-right: 0px; "><span style="font-size: 18px;margin-right: 34px">师承老师</span></td>
					<td style="text-align: left;width: 70%;border-left: 0px;"><input type="text" class="inputText" value="${teacherNames}" disabled style="margin-left: 20px;height: 24px;width: 250px;"></td>
				</tr>
				<tr style="height: 54px">
					<td style="text-align: right;width: 30%;border-right: 0px; "><span style="font-size: 18px;margin-right: 34px">跟师时间</span></td>
					<td style="text-align: left;width: 70%;border-left: 0px;">
						<input type="text" class="readonly validate[required]"id="discipleStartDate"  name="discipleStartDate" value="${resDiscipleInfo.discipleStartDate}" style="width: 100px;margin-left: 20px;height: 24px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" >
						 —— <input type="text" class="readonly validate[required]" id="discipleEndDate" name="discipleEndDate" value="${resDiscipleInfo.discipleEndDate}" style="width: 100px;height: 24px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" >
					</td>
				</tr>
				</form>
				<tr style="height: 54px">
					<td colspan="2">
						<input type="button"  <c:if test="${!isNew}">hidden</c:if> value="保&#12288;存" id="save" class="search" onclick="save();" />
						<input type="button"  <c:if test="${isNew}">hidden</c:if>  value="编&#12288;辑" class="search" onclick="showSave(this);" />
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div id="remark" style="display: none;">
		<p>1、本手册供中医住院医师规范化培训学员跟师学习记录登记和考核使用。</p>
		<br>
		<p>2、跟师学习的规培学员须按照《${(empty sysCfgMap['zy_xxfmsf'])?'江苏省':sysCfgMap['zy_xxfmsf']}中医住院医师规范化培训跟师学习管理暂定规定》及时、客观、详细地填写，严禁弄虚作假。</p>
		<br>
		<p>3、本手册平时由规培学员保管，考核检查时，规培学员应将手册交培训基地有关管理部门。</p>
		<br>
		<p>4、填写手册要求内容真实准确，字迹工整，不得涂改或缺项、缺页。</p>
		<br>
		<p>5、手册采用活页形式，规培学员和培训基地可以根据需要添加。跟师学习结束后，应汇总装订成册，交由培训基地统一归档保管。</p>
		<br>
		<p>6、本手册为记录规培学员跟师学习情况及考核情况的重要业务档案，要妥善保管。</p>
		<br>
	</div>
</div>
</div>
</body>
</html>