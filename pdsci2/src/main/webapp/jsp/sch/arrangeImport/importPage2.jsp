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
	function importArrange(){
		if(false==$("#excelForm").validationEngine("validate")){
			return false;
		}
		var startDate=$('#startDate').val();
		if(!startDate){
			jboxTip("请选择开始月份！");
			return false;
		}
		var endDate=$('#endDate').val();
		if(!endDate){
			jboxTip("请选择结束月份！");
			return false;
		}
		if(startDate>endDate)
		{
			jboxTip("开始月份不得大于结束月份！");
			return false;
		}
		var checkFileFlag = $("#checkFileFlag").val();
		if('${GlobalConstant.FLAG_Y}'!=checkFileFlag){
			jboxTip("请上传Excel文件！");
			return false;
		}
		var length=0;
		jboxStartLoading();
		var url = "<s:url value='/sch/arrangeImport/importArrange2'/>";
		 jboxSubmit(
				$('#excelForm'),
				url,
				function(resp){
					jboxInfo(resp);
					if(resp.substring(0,5)=="${GlobalConstant.UPLOAD_SUCCESSED}"){
						setTimeout(function(){
							window.parent.frames["mainIframe"].window.viewUser();
							jboxClose();
						},1000);
					}
				},
				function(data){
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
	function downGroup(){
		jboxExpLoading(null,"<s:url value='/sch/arrangeImport/downGroupInfo'/>");
	}
	function downMoban(){

		var startDate=$('#startDate').val();
		if(!startDate){
			jboxTip("请选择开始月份！");
			return false;
		}
		var endDate=$('#endDate').val();
		if(!endDate){
			jboxTip("请选择结束月份！");
			return false;
		}
		if(startDate>endDate)
		{
			jboxTip("开始月份不得大于结束月份！");
			return false;
		}
		jboxExpLoading($("#excelForm"),"<s:url value='/sch/arrangeImport/downMoban'/>");
	}
</script>
</head>
<body>
<input type="hidden" id="checkFileFlag" name="checkFileFlag"/>
<form id="excelForm" method="post" enctype="multipart/form-data">
	<table class="basic" style="width: 100%;">
		<tr>
			<th width="150px">排班开始月份</th>
			<td>
				<input id="startDate" name="startDate" type="text" class="inputText ctime"
						 style="width: 160px;" readonly="readonly"onClick="WdatePicker({dateFmt:'yyyy-MM'})"
						 />
			</td>
		</tr>
		<tr>
			<th width="150px">排班结束月份</th>
			<td>

				<input id="endDate" name="endDate" type="text" class="inputText ctime"
					   style="width: 160px;" readonly="readonly"onClick="WdatePicker({dateFmt:'yyyy-MM'})"
				/>
			</td>
		</tr>
		<tr>
			<th width="150px">选择导入</th>
			<td>
				<input id="file" type="file" name="file" onchange="checkFile(this);" class="validate[required]"/>
			</td>
		</tr>
		<tr>
			<th>模板文件</th>
			<td><a href="javascript:void(0);" onclick="downMoban();">排班导入模板.xls</a></td>
		</tr>
	</table>
	<div style="text-align: left;">
			注意事项：
		<label style="color: red">
			(1)、学员证件号必须是系统中已经存在的，且模板中的字段请设置文本格式。<br>&#12288;&#12288;&#12288;&#12288;&#12288;
			(2)、模板中所以字段不能为空。<br>&#12288;&#12288;&#12288;&#12288;&#12288;
			(3)、主任与带教必须所属对应的科室。<br>&#12288;&#12288;&#12288;&#12288;&#12288;
			(4)、学员未开始轮转，排班信息以最后一次为准。如果已开始轮转，则无法使用导入。<br>&#12288;&#12288;&#12288;&#12288;&#12288;
		</label>
	</div>
	<div style="text-align: center; margin-top: 10px;">
		<input type="button" onclick="importArrange();" class="search" value="导&#12288;入"/>
		<input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
	</div>
</form>
<!-- 用于显示导入信息的div -->
<div id="returnData"style="display: none;">
	<div style="top:10px;left:40px;position:absolute;">
		<span style="font-size:15px;"><label id="succCount"></label>
		<label id="loseCount"></label></span>
	</div>
	<div style="top:40px;left:40px;position:absolute;">
		<div>
			<label id="title"style="font-size: 15px;"></label>
		</div>
		<div id="kuang" class="kuang" style="width:300px; height:130px; overflow:auto;margin-top: 5px;display: none;">
			<label id="hanghao"style="font-size: 15px;"></label>
		</div>
	</div>	
	<div style="bottom:20px;width:350px; text-align:center; position:absolute;">
		<input type="button" style="text-align: center;width: 70px;" class="search" onclick="_dialogClose('openDialog');"value='确&#12288;定'/>
	</div>
</div>	
</body>
</html>