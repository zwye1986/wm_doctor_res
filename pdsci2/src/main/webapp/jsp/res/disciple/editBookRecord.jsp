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
<script type="text/javascript">
	
	function save()
	{
		var studyStartTime=$("#studyStartTime").val();
		if(!studyStartTime)
		{
			jboxTip("学习开始时间未填写，无法保存！");
			return false;
		}
		var studyEndTime=$("#studyEndTime").val();
		if(!studyEndTime)
		{
			jboxTip("学习结束时间未填写，无法保存！");
			return false;
		}
		if(studyStartTime>studyEndTime)
		{
			jboxTip("学习结束时间小于学习结束时间，无法保存！");
			return false;
		}
		if(false == $("#saveForm").validationEngine("validate")){
			jboxTip("信息未填写完整，无法保存！");
			return false;
		}
		jboxConfirm("确认保存?",function(){
			jboxPost("<s:url value='/res/bookStudyRecord/saveBookRecord'/>",$('#saveForm').serialize(),function(resp){
				if(resp=="${GlobalConstant.SAVE_SUCCESSED}") {
					window.parent.frames['mainIframe'].window.toPage(1);
				}
			},null,true);
		});

	}

</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div width="100%" height="100%" style="margin-top: 20px">
			<form id="saveForm">
			<table class="table" width="100%">
					<input type="text" class=" inputText" value="${record.recordFlow}" name="recordFlow" hidden style="margin-left: 20px;"/>
					<input type="text" class=" inputText" value="${( empty record.doctorName)?user.userName:record.doctorName}" name="doctorName" hidden style="margin-left: 20px;"/>
					<input type="text" class=" inputText" value="${( empty record.doctorFlow)?user.userFlow:record.doctorFlow}" name="doctorFlow" hidden style="margin-left: 20px;"/>
					<input type="text" class=" inputText" value="${( empty record.teacherName)?doctor.discipleTeacherName:record.teacherName}" name="teacherName" hidden style="margin-left: 20px;"/>
					<input type="text" class=" inputText" value="${( empty record.teacherFlow)?doctor.discipleTeacherFlow:record.teacherFlow}" name="teacherFlow" hidden style="margin-left: 20px;"/>
				<tr style="height: 54px">
					<td style="text-align: right; "><span style="font-size: 18px;margin-right: 34px">学习时间</span></td>
					<td style="text-align: left;">
						<input type="text" class="validate[required]"id="studyStartTime" name="studyStartTime" value="${record.studyStartTime}" style="width: 100px;margin-left: 20px;height: 24px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" >
						~
						<input type="text" class="validate[required]" id="studyEndTime" name="studyEndTime" value="${record.studyEndTime}" style="width: 100px;height: 24px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" >
					</td>
				</tr>
				<tr>
					<td style="text-align: right; "><span style="font-size: 18px;margin-right: 34px">学习方式</span></td>
					<td style="text-align: left;">
						<input style="margin-left: 20px;" type="radio" class="validate[required]" name="studyActionId" value="JIZHONG" <c:if test="${record.studyActionId eq 'JIZHONG'}">checked</c:if> />集中
						<input type="radio" class="validate[required]" name="studyActionId" value="ZIXUE" <c:if test="${record.studyActionId eq 'ZIXUE'}">checked</c:if>/>自学
					</td>
				</tr>
				<tr>
					<td style="text-align: right; "><span style="font-size: 18px;margin-right: 34px">学习书目及内容</span></td>
					<td style="text-align: left;">
						<textarea class="validate[required]" style="margin-left: 20px;width: 80%;height: 230px;margin-top: 10px;" name="studyContent">${record.studyContent}</textarea>
					</td>
				</tr>
				<tr style="height: 54px">
					<td style="text-align: right; "><span style="font-size: 18px;margin-right: 34px">备注</span></td>
					<td style="text-align: left;">
						<textarea class="validate[maxSize[2000] required]" style="margin-left: 20px;width: 80%;height: 230px;margin-top: 10px;" name="remark">${record.remark}</textarea>
					</td>
				</tr>
				<tr style="height: 54px">
					<td colspan="2"><input type="button"  value="保&#12288;存" class="search" style="width: 70px"  onclick="save();" /></td>
				</tr>
			</table>
			</form>
		</div>
	</div>
</div>
</div>
</body>
</html>