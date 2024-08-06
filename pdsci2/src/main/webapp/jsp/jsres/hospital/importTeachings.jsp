<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="font" value="true"/>
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
<style type="text/css">
	#excelForm tr th{
		text-align: right;
	}
	#excelForm tr td{
		text-align: left;
		padding-left: 100px;
	}
</style>
<script type="text/javascript">
function importUsers(){
	if(false==$("#excelForm").validationEngine("validate")){
		return false;
	}
	var checkFileFlag = $("#checkFileFlag").val();
	if('${GlobalConstant.FLAG_Y}'!=checkFileFlag){
		jboxTip("请上传Excel文件！");
		return false;
	}
	jboxStartLoading();
	var url = "<s:url value='/jsres/manage/importTeachingFromExcel'/>";
	jboxSubmit(
		$('#excelForm'),
		url,
		function(resp){
			jboxInfo(resp);
			if(resp.substring(0,5)=="${GlobalConstant.UPLOAD_SUCCESSED}"){
				setTimeout(function(){
					window.parent.search();
					jboxClose();
				},2000);
			}else{
				jboxEndLoading();
			}
		},
		null,false);
}

function checkFile(file){
	var filePath = file.value;
	var suffix = filePath.substring(filePath.lastIndexOf(".")+1);
	if("xlsx" == suffix || "xls" == suffix){
		$("#checkFileFlag").val("${GlobalConstant.FLAG_Y}");
	}else{
		$("#checkFileFlag").val("${GlobalConstant.FLAG_N}");
		$(file).val(null);
		jboxTip("请上传Excel文件");
	}
}

</script>
</head>
<body>
<input type="hidden" id="checkFileFlag" name="checkFileFlag"/>
<form id="excelForm" method="post" enctype="multipart/form-data">
	<table class="grid" style="width: 100%;margin-top: 30px">
	    <tr>
		 	<th width="30%">Excel文件：</th>
			<td style="padding-left:10px;">
				<input id="file" type="file" name="file" onchange="checkFile(this);" class="validate[required]"/>
			</td>
	  	</tr>
		<tr>
			<th>模板文件</th>
			<td style="padding-left:10px;"><a href="<s:url value='/jsp/sys/impactorFactor/template/teacherTemplate.xlsx'/>">师资导入模板.xlsx</a></td>
		</tr>
	</table>
	<br/>
	<p style="text-align: center;">
		<input type="button" onclick="importUsers();" value="师资导入" class="btn_green"/>
	</p>
</form>
</body>
</html>