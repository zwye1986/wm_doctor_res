<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="login" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<style type="text/css">
	#boxHome .item:HOVER{background-color: #eee;}
</style>
<script type="text/javascript">
var content='';
String.prototype.htmlFormart = function(){
	var html = this;
	for(var index in arguments){
		var reg = new RegExp('\\{'+index+'\\}','g');
		html = html.replace(reg,arguments[index]);
	}
	return html;
};
	function importCourse(){
		if(false==$("#excelForm").validationEngine("validate")){
			return false;
		}
		var checkFileFlag = $("#checkFileFlag").val();
		if('${GlobalConstant.FLAG_Y}'!=checkFileFlag){
			jboxTip("请上传Excel文件！");
			return false;
		}
		jboxStartLoading();
		var url = "<s:url value='/jsres/statistic/importTeacherExcel'/>";
		 jboxSubmit(
				$('#excelForm'),
				url,
				function(data){
					data =  JSON.parse(data);
					if(data!=""){
						jboxEndLoading();
						$("#resultDiv").show();
						$("#result").html("成功上传"+data.succCount+"条记录,"+"失败"+data.loseCount+"条记录");
						var result="";
						for(var i=0;i<data.loseList.length;i++){
							result+=data.loseList[i]+"   ";
						}
						$("#title").html("失败的行号为：");
						$("#lose").html("&#12288;"+result);
					}
			},
				function(data){
				jboxTip("导入失败！");
					top.jboxEndLoading();
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
<body onunload="top.search();">
<input type="hidden" id="checkFileFlag" name="checkFileFlag"/>
<form id="excelForm" method="post" enctype="multipart/form-data">
	<table class="grid" style="width: 100%;margin-top: 15px">
		<tr>
			<th width="150px">选择导入</th>
			<td>
				<input id="file" type="file" name="file" onchange="checkFile(this);" class="validate[required]" style="text-align: center;"/>
			</td>
		</tr>
		<tr>
			<th>模板文件</th>
			<td>
				<label style="float: left;margin-left: 35px"><a href="<s:url value='/jsp/jsres/daochu/teacherTrainImport.xls' />">培训师资信息导入模板.xlsx</a></label>
			</td>
		</tr>
	</table>
	<div style="text-align: center; margin-top: 10px;">
		<input type="button" onclick="importCourse();" class="btn_green" value="导&#12288;入"/>
		<input type="button" class="btn_green" value="关&#12288;闭" onclick="jboxClose();"/>
	</div>
	<div style="text-align: center; margin-top: 10px;display: none;" id="resultDiv">
		<font color="red"size="1"><label id="result"></label></font><br>
<!-- 		<font color="red"><label id="lose"></label></font> -->
	</div>
</form>
