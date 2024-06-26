<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/zseyjxres/htmlhead-gzzyjxres.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="font" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
	</jsp:include>
	<style type="text/css">
		#boxHome .item:HOVER{background-color: #eee;}
	</style>
	<script type="text/javascript">
		function daoRu(){
			if(false==$("#excelForm").validationEngine("validate")){
				return false;
			}
			var checkFileFlag = $("#checkFileFlag").val();
			if('${GlobalConstant.FLAG_Y}'!=checkFileFlag){
				jboxTip("请上传Excel文件！");
				return false;
			}
			var url = "<s:url value='/zseyjxres/hospital/importStaff'/>";
			jboxSubmit(
					$('#excelForm'),
					url,
					function(resp){
						top.jboxEndLoading();
						top.jboxTip(resp);
						if(resp.substring(0,5)=="${GlobalConstant.UPLOAD_SUCCESSED}"){
							top.toPage(1);
							top.jboxClose();
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
	<div style="height: 50px"></div>
	<table class="grid" style="width: 90%;margin-left: 5%;">
		<tr>
			<th>请选择导入</th>
			<td style="text-align: left">
				<input id="file" type="file" name="file" onchange="checkFile(this);" class="validate[required]"/>
			</td>
		</tr>
		<tr>
			<th>模板文件</th>
			<td style="text-align: left"><a href="<s:url value='/jsp/zseyjxres/hospital/temeplete/importStaffs.xlsx'/>">医院导入模板.xlsx</a></td>
		</tr>
	</table>
</form>
<div style="text-align: center; margin-top: 10px;">
	<input type="button" onclick="daoRu();" value="导&#12288;入" class="btn_green"/>&#12288;
	<input type="button" class="btn_green" value="关&#12288;闭" onclick="jboxClose();"/>
</div>
</body>
</html>