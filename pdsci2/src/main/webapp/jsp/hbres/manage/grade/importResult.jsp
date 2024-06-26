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
</jsp:include>
<style type="text/css">
	#excelForm tr th{
		text-align: right;
	}
	#excelForm tr td{
		text-align: left;
		padding-left: 100px;
	}
	.searchCss{
		background-color: #44b549;
		color: #fff;
		border: none;
		display: inline-block;
		overflow: visible;
		padding: 0 20px;
		height: 30px;
		line-height: 28px;
		vertical-align: middle;
		text-align: center;
		text-decoration: none;
		border-radius: 3px;
		-moz-border-radius: 3px;
		-webkit-border-radius: 3px;
		font-size: 14px;
		cursor: pointer;
		font-family: "Microsoft YaHei";
	}
</style>
<script type="text/javascript">
function importExcel(){
	if(false==$("#excelForm").validationEngine("validate")){
		return false;
	}
	var checkFileFlag = $("#checkFileFlag").val();
	if('${GlobalConstant.FLAG_Y}'!=checkFileFlag){
		jboxTip("请上传Excel文件！");
		return false;
	}
	jboxStartLoading();
	var url = "<s:url value='/hbres/grade/importExcel'/>";
	jboxSubmit(
		$('#excelForm'),
		url,
		function(resp){
			if(resp == "1"){
				window.parent.gradeInput();
				jboxEndLoading();
				var openDialog = top.dialog.get('openDialog');
				if(openDialog !=null && openDialog.open){
					openDialog.close().remove();
				}
			}else{
				jboxEndLoading();
				jboxTip(resp);
			}
		},
		function(resp){
			jboxEndLoading();
			jboxTip('${GlobalConstant.UPLOAD_FAIL}');		
		},false
	);
}
function importGrade(){
	if(false==$("#gradeForm").validationEngine("validate")){
		return false;
	}
	var url = "<s:url value='/hbres/grade/importGrade'/>";
	jboxSubmit(
			$('#gradeForm'),
			url,
			function(resp){
				if(resp == "1"){
					jboxTip("录入成绩成功！");
					var openDialog = top.dialog.get('openDialog');
					if(openDialog !=null && openDialog.open){
						openDialog.close().remove();
					}
				}else{
					jboxTip(resp);
				}
			},
			null,
			false
	);
}
function checkFile(file){
	var filePath = file.value;
	var suffix = filePath.substring(filePath.lastIndexOf(".")+1);
	if("xlsx" == suffix || "xls" == suffix){
		$("#checkFileFlag").val("${GlobalConstant.FLAG_Y}");
	}else{
		$("#checkFileFlag").val("${GlobalConstant.FLAG_N}");
		jboxTip("请上传Excel文件");
	}
}

</script>
</head>
<body>
<div class="mainright">
<div class="content">
<div class="title1 clearfix"></div>
<input type="hidden" id="checkFileFlag" name="checkFileFlag"/>
	<c:if test="${zsGradeFlag eq 'Y'}">
		<form id="gradeForm" method="post" enctype="multipart/form-data">
			<table width="100%" class="basic">
				<tr>
					<th width="30%">专硕理论成绩：</th>
					<td>
						<input type="hidden" name="examFlow" value="${param.examFlow}"/>
						<input type="text" id="grade" name="grade" class="validate[required,min[0],custom[number]]"/>
					</td>
				</tr>
			</table>
			<br/>
			<p style="text-align: center;">
				<input type="button" onclick="importGrade();" value="录入成绩" class="searchCss"/>
			</p>
		</form>
	</c:if>
	<c:if test="${zsGradeFlag ne 'Y'}">
		<form id="excelForm" method="post" enctype="multipart/form-data">
			<table width="100%" class="basic">
				<tr>
					<th width="30%">Excel文件：</th>
					<td>
						<input type="hidden" name="examFlow" value="${param.examFlow}"/>
						<input id="file" type="file" name="file" onchange="checkFile(this);" class="validate[required]"/>
					</td>
				</tr>
				<tr>
					<th width="30%">导入模板：</th>
					<td><a href="<s:url value='/jsp/hbres/print/examResult.xlsx'/>">成绩导入模板.xlsx</a></td>
				</tr>
			</table>
			<br/>
			<p style="text-align: center;">
				<input type="button" onclick="importExcel();" value="导入成绩" class="searchCss"/>
			</p>
		</form>
	</c:if>
</div>
</div>
</body>
</html>