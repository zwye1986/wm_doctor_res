<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="font" value="true"/>
</jsp:include>
<style type="text/css">
	#planForm tr th{
		text-align: right;
	}
	#planForm tr td{
		padding-left: 20px;
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
		var url = "<s:url value='/sczyres/manage/importTickets'/>";
		jboxSubmit(
				$('#excelForm'),
				url,
				function(resp){
					if(resp.substring(0,5)=="${GlobalConstant.UPLOAD_SUCCESSED}"){
						top.jboxInfo(resp);
						window.parent.search();
						top.jboxClose();
					}else{
						top.jboxEndLoading();
						top.jboxInfo(resp);
						top.jboxClose();
					}
				},
				function(resp){
					top.jboxEndLoading();
					top.jboxTip('${GlobalConstant.UPLOAD_FAIL}');
				},false);
		top.jboxEndLoading();
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
<div class="mainright">
	<div style="padding:0px 20px;margin-top:20px;">
		<input type="hidden" id="checkFileFlag"/>
		<form id="excelForm" method="post" enctype="multipart/form-data">
			<table width="100%" class="basic">
				<tr>
					<th width="30%">Excel文件：</th>
					<td>
						<input id="file" type="file" name="file" onchange="checkFile(this);" class="validate[required]"/>
					</td>
				</tr>
				<tr>
					<th>导入模板：</th>
					<td><a href="<s:url value='/jsp/sczyres/manage/ticketTemp.xlsx'/>">准考证导入.xlsx</a></td>
				</tr>
			</table>
			<br/>
			<p style="text-align: center;">
				<input type="button" onclick="importUsers();" value="准考证导入" class="btn_blue"/>
			</p>
		</form>
	</div>
</div>
<!-- 导入后得返回信息 -->
<div id="returnData"style="display: none;">
	<div style="top:10px;left:40px;position:absolute;">
		<span style="font-size:15px;"><label id="succCount"></label>
		<label id="failCount"></label></span>
	</div>
	<div style="top:40px;left:40px;position:absolute;">
		<div>
			<label id="title"style="font-size: 15px;"></label>
		</div>
		<div id="kuang" style="width:300px; height:130px; overflow:auto;margin-top: 5px;display: none;">
			<label id="line"style="font-size: 15px;"></label>
		</div>
	</div>
	<div style="bottom:5px;width:350px; text-align:center; position:absolute;">
		<input type="button" style="text-align: center;" class="btn_green" onclick="_dialogClose('openDialog');"value='确&#12288;定'/>
	</div>
</div>
</body>
</html>