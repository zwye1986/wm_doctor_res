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
	jboxStartLoading();
	var url = "<s:url value='/gyxjgl/user/importSchoolRollFromExcel'/>";
	jboxSubmit(
		$('#excelForm'),
		url,
		function(resp){
			top.jboxInfo(resp);
			if(resp.substring(0,5)=="${GlobalConstant.UPLOAD_SUCCESSED}"){
				window.parent.frames['mainIframe'].window.location.reload();
			}
		},
		function(resp){
			top.jboxEndLoading();
			top.jboxTip('${GlobalConstant.UPLOAD_FAIL}');		
		},false);
}

function printSid(){
	if(false==$("#exportForm").validationEngine("validate")){
		return;
	}
	var startSid=$("#startSid").val();
	var endSid=$("#endSid").val();
	if(startSid > endSid){
		jboxTip("起始学号不能大于结束学号！");
		return;
	}
	if(endSid - startSid > 49){
		jboxTip("学号跨度不超过50！");
		return;
	}
	var url = "<s:url value='/gyxjgl/user/printYuLian4AdminOL'/>?startSid="+startSid+"&endSid="+endSid;
	jboxTip("打印中，请稍等...");
	setTimeout(function(){
		$("#printA").attr("href",url);
		$("#clickSpan").click();
	},2000);
}
</script>
</head>
<body>
<input type="hidden" id="checkFileFlag" name="checkFileFlag"/>
<form id="exportForm" method="post" enctype="multipart/form-data">
	<table class="basic" style="width: 100%;">
		<tr>
			<td>
				打印的学号范围：<font style="color: red;">（*学号跨度不超过50）</font><br/>
				<input type="text" id="startSid" name="startSid" value="${param.startSid}" style="width: 120px;" class="validate[required]">
				-
				<input type="text" id="endSid" name="endSid" value="${param.endSid}" style="width: 120px;" class="validate[required]">
			</td>
		</tr>
	</table>
</form>
	<div style="text-align: center; margin-top: 10px;">
		<a hidden="hidden" target="_blank" id="printA"><span id="clickSpan"/></a>
		<input type="button" onclick="printSid();" value="打&#12288;印" class="search"/>
		<input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
	</div>
</body>
</html>