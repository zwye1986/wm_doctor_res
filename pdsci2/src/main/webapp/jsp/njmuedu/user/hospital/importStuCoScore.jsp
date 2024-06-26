<%--<%@ page language="java" contentType="text/html; charset=UTF-8"--%>
    <%--pageEncoding="UTF-8"%>--%>
<%--<%@include file="/jsp/common/doctype.jsp" %>--%>
<html>
<head>
<jsp:include page="/jsp/njmuedu/htmlhead-njmuedu.jsp">
	<jsp:param name="findCourse" value="true"/>
	<jsp:param name="jquery_scrollTo" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>

	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>

</jsp:include>
<style type="text/css">
	#excelForm tr th{
		text-align: right;
	}
	#excelForm tr td{
		text-align: left;
		padding-left: 100px;
	}
	.course-table {
		border: none;
		border-collapse: collapse;
		background: #fefefe;
		box-shadow: 0 0 1px #C8EAFF;
	}
	.course-table th {
		background: #2582BB;
		color: #fff;
		font-weight: normal;
		border-bottom: 1px solid #fff;
	}
	.course-table th, .course-table td {
		height: 40px;
		line-height: 40px;
		text-align: center;
		font-size: 13px;
		font-family: '微软雅黑';
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
	var url = "<s:url value='/njmuedu/user/importStuCoScoreExcel'/>";
	jboxSubmit(
		$('#excelForm'),
		url,
		function(resp){
            jboxEndLoading();
            top.jboxInfo(resp);
            if(resp.substring(0,5)=="${GlobalConstant.UPLOAD_SUCCESSED}"){
                //  window.parent.frames['mainIframe'].window.search();
                window.parent.toPage2(1);
                top.jboxClose();
            }
		},
		function(resp){
			jboxEndLoading();
			top.jboxTip('${GlobalConstant.UPLOAD_FAIL}');		
		},false);
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
<%--<div class="mainright">--%>
<%--<div class="content">--%>
<div class="title1 clearfix"></div>
<input type="hidden" id="checkFileFlag" name="checkFileFlag"/>
<form id="excelForm" method="post" enctype="multipart/form-data">
	<table width="100%" class="course-table">
	    <tr>
		 	<th width="30%">Excel文件：</th>
			<td >
				<input id="file" type="file" name="file" onchange="checkFile(this);" class="validate[required]"/>
			</td>
	  	</tr>
		<tr>
			<th>模板文件：</th>
			<td><a href="<s:url value='/jsp/njmuedu/user/hospital/studentScoreTemplate.xlsx'/>">学员成绩导入模板.xlsx</a></td>
		</tr>
	</table>
	<br/>
</form>
<div class="module-tabs" >
	<ul style="padding-left: 275px;">
		<li><a  href="javascript:void(0);" onclick="importUsers();" class="add" >学员成绩导入</a></li>
	</ul>
</div>
<%--</div>--%>
<%--</div>--%>
</body>
</html>