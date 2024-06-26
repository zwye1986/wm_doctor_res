<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<style type="text/css">
	.boxHome .item:HOVER{background-color: #eee;}
</style>
<script type="text/javascript">
$(document).ready(function(){
	changeType("${param.applyTypeId}");
});

	function changeType(obj){
		$(".condition").hide();
		$("."+obj).show();
	}
function audit(recordFlow,status){
	if(status=="${}")
	jboxConfirm("确认提交?",function(){
		jboxPost("<s:url value='/xjgl/change/apply/submitApply'/>" , data , function(resp){
			if("${GlobalConstant.OPRE_SUCCESSED}"==resp){
				setTimeout(function(){
//	 				submit(applyTypeId,userFlow);
				},1000);
			}
		} , null , true);
		});
}
</script>
</head>
<body>
	<div class="mainright">
		<div class="content">
			<form id='submitForm' style="position:relative;">
			<table class="basic" style="width: 100%;margin: 10px 0px;">
				<tr><th style="text-align: left;padding-left: 10px;">审核意见</th></tr>
				<tr>
					<td>
						<div style="margin-bottom:5px;margin-top:3px; margin-right: 3px;"><textarea style="width: 99%; height: 100px;" placeholder="请输入终审意见"></textarea></div>
					</td>
					
				</tr>
				<tr>
				<td style="text-align: right;padding-right: 15px;">批准人：<input type="text" style="width: 100px;"/> 
				&#12288;批准日期：<input name="stopStudyEndTime" value="" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width:129px; text-align: left;" class="xltext ctime" type="text"></td>
				</tr>
			</table>
			</form>
		</div>
		<div style="text-align: center;">
				<input type="button" class="search" value="审核通过" onclick="audit('${param.recordFlow}','${GlobalConstant.FLAG_Y }');"/>
				<input type="button" class="search" value="审核不通过" onclick="audit('${param.recordFlow}','${GlobalConstant.FLAG_N }');"/>
				<input type="button" class="search" value="打&#12288;印" onclick=""/>
			</div>
	</div>
</body>
</html>