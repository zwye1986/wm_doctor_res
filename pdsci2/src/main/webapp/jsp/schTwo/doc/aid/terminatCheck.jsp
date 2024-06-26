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
<script type="text/javascript">
	function filterDoctor(){
		$("[name='doctorFlow']").val("");
		var sessionNumber = $("#sessionNumber").val();
		var trainingSpeId = $("#trainingSpeId").val();
		var emptySessionNumber = sessionNumber == null || sessionNumber == '';
		var emptyTrainingSpeId = trainingSpeId == null || trainingSpeId == '';
		$(".name").hide();
		$(".name" + (emptySessionNumber?"":("._"+sessionNumber)) + (emptyTrainingSpeId?"":("._"+trainingSpeId))).show();
	}
	
	function saveTerminat(){
		if(!$("#schDoctor").validationEngine("validate")){
			return ;
		}
		var url = "<s:url value='/sch/doc/aid/saveTerminat'/>";
		var getdata = $('#schDoctor').serialize();
		jboxPost(url, getdata, function(data) {
			if('${GlobalConstant.SAVE_SUCCESSED}'==data){
				window.parent.frames['mainIframe'].window.search(); 
				jboxClose();		
			}
		});
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<form id="schDoctor">
			<div id="dept" style="width:100%; margin-top: 10px;margin-bottom: 10px;position: relative;" align="center">
				<table class="basic" style="font-size: 14px;width: 100%">
						<tr>
							<th style="width: 30%;">年级：</th>
							<td style="width: 70%;">
								<label>${doctor.sessionNumber }</label>
							</td>
						</tr>
						<tr>
							<th>培训专业：</th>
							<td>
								<label>${doctor.trainingSpeName }</label>
							</td>
						</tr>
						<tr>
							<th>姓名：</th>
							<td>
								<label>${doctor.doctorName }</label>
							</td>
						</tr>
						<tr>
							<th>终止时间：</th>
							<td>
								<label>${doctor.terminatDate }</label>
							</td>
						</tr>
						<tr>
							<th>终止原因：</th>
							<td>
								<label>${doctor.terminatReason }</label>
							</td>
						</tr>
					</table>
			</div>
			</form>
			<div align="center">
				<input type="button" class="search" onclick="jboxClose();" value="关&#12288;闭"/>
			</div>
	</div>
</div>
</div>
</body>
</html>