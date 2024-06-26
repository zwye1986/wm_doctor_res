<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="true"/>
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
});

$(document).ready(function(){
	$('#applyYear').datepicker({
		startView: 2,
		maxViewMode: 2,
		minViewMode:2,
		format:'yyyy'
	});
});
var d=null;
function showInfo(tip)
{
	 d= top.dialog({
		id:"artTip",
		padding:5,
		content:tip,
		backdropOpacity: 0.1
	});
	d.show();
}
function closeInfo()
{
	if(d!=null)
		d.close().remove();
}
function postAction(url,tip,obj)
{
	showInfo(tip);
//	$(obj).attr("disabled","disabled");
	$("input[type='button']").attr("disabled","disabled");
	jboxPost(url, null, function(resp) {
		closeInfo();
		console.log(resp);
//		$(obj).removeAttr("disabled");
		$("input[type='button']").removeAttr("disabled");
	}, null, true);
}

function postAction2(url,tip,obj,okFunc)
{
	showInfo(tip);
//	$(obj).attr("disabled","disabled");
	$("input[type='button']").attr("disabled","disabled");
	jboxPost(url, null, function(resp) {
		closeInfo();
		if(okFunc!=null){
			okFunc(resp);
		}
		$("input[type='button']").removeAttr("disabled");
	}, null, false);
}
function selectAfter2(obj)
{
	var applyYear=$('#applyYear').val();
	if(!applyYear){
		jboxTip("结业资格申请年份！");
		return false;
	}
	var doctorFlow=$('#doctorFlow').val();
	if(!doctorFlow){
		jboxTip("请输入学员流水号！");
		return false;
	}
	var url = "<s:url value='/jsres/temp/selectAfter2'/>?applyYear="+applyYear+"&doctorFlow="+doctorFlow;
	postAction(url,"出科考核表抽取中，请稍后。。。",obj);
}
function selectAfter(obj)
{
	var applyYear=$('#applyYear').val();
	if(!applyYear){
		jboxTip("结业资格申请年份！");
		return false;
	}
	var url = "<s:url value='/jsres/temp/selectAfter'/>?applyYear="+applyYear;
	postAction(url,"出科考核表抽取中，请稍后。。。",obj);
}
function updateInfo(obj)
{
	var applyYear=$('#applyYear').val();
	if(!applyYear){
		jboxTip("结业资格申请年份！");
		return false;
	}
	var url = "<s:url value='/jsres/temp/updateInfo'/>?applyYear="+applyYear;
	postAction(url,"学员证书信息更新中，请稍后。。。",obj);
}
function updateUriAuditInfo(obj)
{
	var url = "<s:url value='/jsres/temp/updateUriAuditInfo'/>";
	postAction(url,"高校基地带教审核情况更新中，请稍后。。。",obj);
}
function chouqu(obj)
{
	var url = "<s:url value='/jsres/temp/chouquPhoto'/>";
//	postAction(url,"抽取学员头像中，请稍后。。。",obj);

	if(false==$("#excelForm").validationEngine("validate")){
		return false;
	}
	var checkFileFlag = $("#checkFileFlag").val();
	if('${GlobalConstant.FLAG_Y}'!=checkFileFlag){
		jboxTip("请上传Excel文件！");
		return false;
	}
	showInfo("抽取学员头像中，请稍后。。。");
//	$(obj).attr("disabled","disabled");
	$("input[type='button']").attr("disabled","disabled");
	jboxSubmit(
			$('#excelForm'),
			url,
			function(resp){
				closeInfo();
				$(obj).removeAttr("disabled");
				$("input[type='button']").removeAttr("disabled");
				if(resp.substring(0,5)=="${GlobalConstant.UPLOAD_SUCCESSED}")
					jboxInfo('${GlobalConstant.UPLOAD_SUCCESSED}');

			},
			function(resp){
				closeInfo();
				$(obj).removeAttr("disabled");
				$("input[type='button']").removeAttr("disabled");
				jboxInfo('${GlobalConstant.UPLOAD_FAIL}');
			},false)
	;
}
function addTempIdNo(obj)
{
	var url = "<s:url value='/jsres/temp/addTempIdNo'/>";

	if(false==$("#excelForm2").validationEngine("validate")){
		return false;
	}
	var checkFileFlag = $("#checkFileFlag2").val();
	if('${GlobalConstant.FLAG_Y}'!=checkFileFlag){
		jboxTip("请上传Excel文件！");
		return false;
	}
	showInfo("插入临时证件号，请稍后。。。");
//	$(obj).attr("disabled","disabled");
	$("input[type='button']").attr("disabled","disabled");
	jboxSubmit(
			$('#excelForm2'),
			url,
			function(resp){
				closeInfo();
				$(obj).removeAttr("disabled");
				$("input[type='button']").removeAttr("disabled");
				if(resp.substring(0,5)=="${GlobalConstant.UPLOAD_SUCCESSED}")
					jboxInfo('${GlobalConstant.UPLOAD_SUCCESSED}');

			},
			function(resp){
				closeInfo();
				$(obj).removeAttr("disabled");
				$("input[type='button']").removeAttr("disabled");
				jboxInfo('${GlobalConstant.UPLOAD_FAIL}');
			},false)
	;
}
function addUserInfo(obj)
{
	var url = "<s:url value='/jsres/temp/addUserInfo'/>";

	if(false==$("#excelForm3").validationEngine("validate")){
		return false;
	}
	var checkFileFlag = $("#checkFileFlag3").val();
	if('${GlobalConstant.FLAG_Y}'!=checkFileFlag){
		jboxTip("请上传Excel文件！");
		return false;
	}
	showInfo("插入招录学员信息，请稍后。。。");
//	$(obj).attr("disabled","disabled");
	$("input[type='button']").attr("disabled","disabled");
	jboxSubmit(
			$('#excelForm3'),
			url,
			function(resp){
				closeInfo();
				$(obj).removeAttr("disabled");
				$("input[type='button']").removeAttr("disabled");
				if(resp.substring(0,5)=="${GlobalConstant.UPLOAD_SUCCESSED}")
					jboxInfo('${GlobalConstant.UPLOAD_SUCCESSED}');

			},
			function(resp){
				closeInfo();
				$(obj).removeAttr("disabled");
				$("input[type='button']").removeAttr("disabled");
				jboxInfo('${GlobalConstant.UPLOAD_FAIL}');
			},false)
	;
}
function upDateRecruitInfo(obj)
{
	var url = "<s:url value='/jsres/temp/upDateRecruitInfo'/>";

	if(false==$("#excelForm4").validationEngine("validate")){
		return false;
	}
	var checkFileFlag = $("#checkFileFlag4").val();
	if('${GlobalConstant.FLAG_Y}'!=checkFileFlag){
		jboxTip("请上传Excel文件！");
		return false;
	}
	showInfo("更新招录信息，请稍后。。。");
//	$(obj).attr("disabled","disabled");
	$("input[type='button']").attr("disabled","disabled");
	jboxSubmit(
			$('#excelForm4'),
			url,
			function(resp){
				closeInfo();
				$(obj).removeAttr("disabled");
				$("input[type='button']").removeAttr("disabled");
				if(resp.substring(0,5)=="${GlobalConstant.UPLOAD_SUCCESSED}")
					jboxInfo('${GlobalConstant.UPLOAD_SUCCESSED}');
				console.log(resp);
			},
			function(resp){
				closeInfo();
				$(obj).removeAttr("disabled");
				$("input[type='button']").removeAttr("disabled");
				jboxInfo('${GlobalConstant.UPLOAD_FAIL}');
			},false)
	;
}
function updateRecruitOrgInfo(obj)
{
	var url = "<s:url value='/jsres/temp/updateRecruitOrgInfo'/>";

	if(false==$("#excelForm5").validationEngine("validate")){
		return false;
	}
	var checkFileFlag = $("#checkFileFlag5").val();
	if('${GlobalConstant.FLAG_Y}'!=checkFileFlag){
		jboxTip("请上传Excel文件！");
		return false;
	}
	showInfo("更新招录信息，请稍后。。。");
//	$(obj).attr("disabled","disabled");
	$("input[type='button']").attr("disabled","disabled");
	jboxSubmit(
			$('#excelForm5'),
			url,
			function(resp){
				closeInfo();
				$(obj).removeAttr("disabled");
				$("input[type='button']").removeAttr("disabled");
				if(resp.substring(0,5)=="${GlobalConstant.UPLOAD_SUCCESSED}")
					jboxInfo('${GlobalConstant.UPLOAD_SUCCESSED}');
				console.log(resp);
			},
			function(resp){
				closeInfo();
				$(obj).removeAttr("disabled");
				$("input[type='button']").removeAttr("disabled");
				jboxInfo('${GlobalConstant.UPLOAD_FAIL}');
			},false)
	;
}
function updateRecruitSpeInfo(obj)
{
	var url = "<s:url value='/jsres/temp/updateRecruitSpeInfo'/>";

	if(false==$("#excelForm6").validationEngine("validate")){
		return false;
	}
	var checkFileFlag = $("#checkFileFlag6").val();
	if('${GlobalConstant.FLAG_Y}'!=checkFileFlag){
		jboxTip("请上传Excel文件！");
		return false;
	}
	showInfo("更新招录信息，请稍后。。。");
//	$(obj).attr("disabled","disabled");
	$("input[type='button']").attr("disabled","disabled");
	jboxSubmit(
			$('#excelForm6'),
			url,
			function(resp){
				closeInfo();
				$(obj).removeAttr("disabled");
				$("input[type='button']").removeAttr("disabled");
				if(resp.substring(0,5)=="${GlobalConstant.UPLOAD_SUCCESSED}")
					jboxInfo('${GlobalConstant.UPLOAD_SUCCESSED}');
				console.log(resp);
			},
			function(resp){
				closeInfo();
				$(obj).removeAttr("disabled");
				$("input[type='button']").removeAttr("disabled");
				jboxInfo('${GlobalConstant.UPLOAD_FAIL}');
			},false)
	;
}
function updateReturenInfo(obj)
{
	var url = "<s:url value='/jsres/temp/updateReturenInfo'/>";

	if(false==$("#excelForm7").validationEngine("validate")){
		return false;
	}
	var checkFileFlag = $("#checkFileFlag7").val();
	if('${GlobalConstant.FLAG_Y}'!=checkFileFlag){
		jboxTip("请上传Excel文件！");
		return false;
	}
	showInfo("更新招录信息，请稍后。。。");
//	$(obj).attr("disabled","disabled");
	$("input[type='button']").attr("disabled","disabled");
	jboxSubmit(
			$('#excelForm7'),
			url,
			function(resp){
				closeInfo();
				$(obj).removeAttr("disabled");
				$("input[type='button']").removeAttr("disabled");
				if(resp.substring(0,5)=="${GlobalConstant.UPLOAD_SUCCESSED}")
					jboxInfo('${GlobalConstant.UPLOAD_SUCCESSED}');
				console.log(resp);
			},
			function(resp){
				closeInfo();
				$(obj).removeAttr("disabled");
				$("input[type='button']").removeAttr("disabled");
				jboxInfo('${GlobalConstant.UPLOAD_FAIL}');
			},false)
	;
}
function updateDeptDetail(obj)
{
	var applyYear=$('#applyYear').val();
	if(!applyYear){
		jboxTip("结业资格申请年份！");
		return false;
	}
	var url = "<s:url value='/jsres/temp/updateDeptDetail'/>?applyYear="+applyYear;
	postAction(url,"学员科室百分比【第一步】更新中，请稍后。。。",obj);
}
function updateDeptTemp(obj)
{
	var applyYear=$('#applyYear').val();
	if(!applyYear){
		jboxTip("结业资格申请年份！");
		return false;
	}
	var url = "<s:url value='/jsres/temp/updateDeptTemp'/>?applyYear="+applyYear;
	postAction(url,"学员科室百分比【第二步】更新中，请稍后。。。",obj);
}
function updateDeptAvgTemp(obj)
{
	var applyYear=$('#applyYear').val();
	if(!applyYear){
		jboxTip("结业资格申请年份！");
		return false;
	}
	var url = "<s:url value='/jsres/temp/updateDeptAvgTemp'/>?applyYear="+applyYear;
	postAction(url,"学员科室百分比【第三步】更新中，请稍后。。。",obj);
}
function updateRecruitAvgTemp(obj)
{
	var applyYear=$('#applyYear').val();
	if(!applyYear){
		jboxTip("结业资格申请年份！");
		return false;
	}
	var url = "<s:url value='/jsres/temp/updateRecruitAvgTemp'/>?applyYear="+applyYear;
	postAction(url,"学员科室百分比【第四步】更新中，请稍后。。。",obj);
}
function updateResult(obj)
{
	var url = "<s:url value='/jsres/temp/updateResult'/>";
	postAction(url,"学员轮转记录更新中，请稍后。。。",obj);
}
function updateAfter(obj)
{
	var url = "<s:url value='/jsres/temp/updateAfterEvalutaion'/>";
	postAction(url,"出科考核表看不到更新中，请稍后。。。",obj);
}

function addUser(obj)
{
	var url = "<s:url value='/jsres/temp/addUser'/>";
	postAction(url,"学员科室百分比【第四步】更新中，请稍后。。。",obj);
}
function addExamTeaRole(obj)
{
	var url = "<s:url value='/jsres/temp/addExamTeaRole'/>";
	postAction(url,"处理考官角色信息中，请稍后。。。",obj);
}

function checkFile(file){
	var filePath = file.value;
	var suffix = filePath.substring(filePath.lastIndexOf(".")+1);
	var index=filePath.lastIndexOf("\\")+1;
	var fileName = filePath.substring(index,filePath.lastIndexOf("."));
	alert(fileName);
	if("xlsx" == suffix || "xls" == suffix){
		$("#checkFileFlag").val("${GlobalConstant.FLAG_Y}");
		$("#dirUrl").val("D:/temp/userImage/"+fileName);
	}else{
		$("#checkFileFlag").val("${GlobalConstant.FLAG_N}");
		$(file).val(null);
		jboxTip("请上传Excel文件");
	}
}
function checkFile2(file){
	var filePath = file.value;
	var suffix = filePath.substring(filePath.lastIndexOf(".")+1);
	var index=filePath.lastIndexOf("\\")+1;
	var fileName = filePath.substring(index,filePath.lastIndexOf("."));
	alert(fileName);
	if("xlsx" == suffix || "xls" == suffix){
		$("#checkFileFlag2").val("${GlobalConstant.FLAG_Y}");
	}else{
		$("#checkFileFlag2").val("${GlobalConstant.FLAG_N}");
		jboxTip("请上传Excel文件");
	}
}
function checkFile3(file){
	var filePath = file.value;
	var suffix = filePath.substring(filePath.lastIndexOf(".")+1);
	var index=filePath.lastIndexOf("\\")+1;
	var fileName = filePath.substring(index,filePath.lastIndexOf("."));
	alert(fileName);
	if("xlsx" == suffix || "xls" == suffix){
		$("#checkFileFlag3").val("${GlobalConstant.FLAG_Y}");
	}else{
		$("#checkFileFlag3").val("${GlobalConstant.FLAG_N}");
		jboxTip("请上传Excel文件");
	}
}
function checkFile4(file){
	var filePath = file.value;
	var suffix = filePath.substring(filePath.lastIndexOf(".")+1);
	var index=filePath.lastIndexOf("\\")+1;
	var fileName = filePath.substring(index,filePath.lastIndexOf("."));
	alert(fileName);
	if("xlsx" == suffix || "xls" == suffix){
		$("#checkFileFlag4").val("${GlobalConstant.FLAG_Y}");
	}else{
		$("#checkFileFlag4").val("${GlobalConstant.FLAG_N}");
		jboxTip("请上传Excel文件");
	}
}
function checkFile5(file){
	var filePath = file.value;
	var suffix = filePath.substring(filePath.lastIndexOf(".")+1);
	var index=filePath.lastIndexOf("\\")+1;
	var fileName = filePath.substring(index,filePath.lastIndexOf("."));
	alert(fileName);
	if("xlsx" == suffix || "xls" == suffix){
		$("#checkFileFlag5").val("${GlobalConstant.FLAG_Y}");
	}else{
		$("#checkFileFlag5").val("${GlobalConstant.FLAG_N}");
		jboxTip("请上传Excel文件");
	}
}
function checkFile6(file){
	var filePath = file.value;
	var suffix = filePath.substring(filePath.lastIndexOf(".")+1);
	var index=filePath.lastIndexOf("\\")+1;
	var fileName = filePath.substring(index,filePath.lastIndexOf("."));
	alert(fileName);
	if("xlsx" == suffix || "xls" == suffix){
		$("#checkFileFlag6").val("${GlobalConstant.FLAG_Y}");
	}else{
		$("#checkFileFlag6").val("${GlobalConstant.FLAG_N}");
		jboxTip("请上传Excel文件");
	}
}
function checkFile7(file){
	var filePath = file.value;
	var suffix = filePath.substring(filePath.lastIndexOf(".")+1);
	var index=filePath.lastIndexOf("\\")+1;
	var fileName = filePath.substring(index,filePath.lastIndexOf("."));
	alert(fileName);
	if("xlsx" == suffix || "xls" == suffix){
		$("#checkFileFlag7").val("${GlobalConstant.FLAG_Y}");
	}else{
		$("#checkFileFlag7").val("${GlobalConstant.FLAG_N}");
		jboxTip("请上传Excel文件");
	}
}
function apply2(obj) {
	var applyYear=$('#applyYear').val();
	if(!applyYear){
		jboxTip("请结业资格申请年份！");
		return false;
	}
	var recruitFlow = $('#recruitFlow').val();
	if(!recruitFlow){
		jboxTip("请选择更新记录！");
		return false;
	}
	var applyFlow = $("#recruitFlow option:selected").attr("id");

	var doctorFlow = $("#recruitFlow option:selected").attr("doctorFlow");
	if(!doctorFlow){
		jboxTip("请选择更新记录！");
		return false;
	}

	var url = "<s:url value='/jsres/temp/reAsseApply?'/>applyYear="+applyYear+"&doctorFlow=" + doctorFlow +"&recruitFlow=" + recruitFlow;

		postAction2(url,"数据更新，请勿多次重新更新！",obj,function(resp){
			if ("1" == resp) {
				$("#"+applyFlow).remove();
				jboxTip("更新成功!");
			}else	if ("0" == resp) {
				jboxTip("更新失败!");
			}else{
				jboxTip(resp);
			}
		});
}

function updatePer(obj) {
	var applyYear=$('#applyYear').val();
	if(!applyYear){
		jboxTip("请结业资格申请年份！");
		return false;
	}
	var recruitFlow = $('#recruitFlow').val();
	if(!recruitFlow){
		jboxTip("请选择更新记录！");
		return false;
	}
	var applyFlow = $("#recruitFlow option:selected").attr("id");

	var doctorFlow = $("#recruitFlow option:selected").attr("doctorFlow");
	if(!doctorFlow){
		jboxTip("请选择更新记录！");
		return false;
	}

	var url = "<s:url value='/jsres/temp/updatePer?'/>applyYear="+applyYear+"&doctorFlow=" + doctorFlow +"&recruitFlow=" + recruitFlow;

		postAction2(url,"百分比数据更新，请勿多次重新更新！",obj,function(resp){
			if ("1" == resp) {
				$("#"+applyFlow).remove();
				jboxTip("更新成功!");
			}else	if ("0" == resp) {
				jboxTip("更新失败!");
			}else{
				jboxTip(resp);
			}
		});
}

function updatePer2(obj) {
	var applyYear=$('#applyYear').val();
	if(!applyYear){
		jboxTip("请结业资格申请年份！");
		return false;
	}
	var recruitFlow = $('#recruitFlow').val();
	if(!recruitFlow){
		jboxTip("请选择更新记录！");
		return false;
	}
	var applyFlow = $("#recruitFlow option:selected").attr("id");

	var doctorFlow = $("#recruitFlow option:selected").attr("doctorFlow");
	if(!doctorFlow){
		jboxTip("请选择更新记录！");
		return false;
	}
	var url = "<s:url value='/jsres/temp/updatePer?'/>applyYear="+applyYear+"&doctorFlow=" + doctorFlow +"&recruitFlow=" + recruitFlow;
	postAction2(url,"数据更新，请勿多次重新更新！",obj,function(resp){
		console.log(url)+"==========resp:"+resp;
		if ("1" == resp) {
			$("#"+applyFlow).remove();
			jboxTip("更新成功!");
			updatePer2(obj);
		}else	if ("0" == resp) {
			jboxTip("更新失败!");
		}else{
			jboxTip(resp);
		}
	});
}
function apply3(obj) {
	var applyYear=$('#applyYear').val();
	if(!applyYear){
		jboxTip("请结业资格申请年份！");
		return false;
	}
	var recruitFlow = $('#recruitFlow').val();
	if(!recruitFlow){
		jboxTip("请选择更新记录！");
		return false;
	}
	var applyFlow = $("#recruitFlow option:selected").attr("id");

	var doctorFlow = $("#recruitFlow option:selected").attr("doctorFlow");
	if(!doctorFlow){
		jboxTip("请选择更新记录！");
		return false;
	}

	var url = "<s:url value='/jsres/temp/reAsseApply?'/>applyYear="+applyYear+"&doctorFlow=" + doctorFlow +"&recruitFlow=" + recruitFlow;

	postAction2(url,"数据更新，请勿多次重新更新！",obj,function(resp){
		console.log(url)+"==========resp:"+resp;
		if ("1" == resp) {
			$("#"+applyFlow).remove();
			jboxTip("更新成功!");
			apply3(obj);
		}else	if ("0" == resp) {
			jboxTip("更新失败!");
		}else{
			jboxTip(resp);
		}
	});
}
function queryApplyList(obj)
{
	var applyYear=$('#applyYear').val();
	if(!applyYear){
		jboxTip("请结业资格申请年份！");
		return false;
	}
	var url = "<s:url value='/jsres/temp/queryApplyList'/>?applyYear="+applyYear;
	postAction2(url,"查询学员结业申请记录，请稍后。。。",obj,function(resp){
		$("#recruitFlow option").remove();
		if(resp!=""){
			var dataObj = resp;
			for(var i = 0; i<dataObj.length;i++){
				var recruitFlow = dataObj[i].recruitFlow;
				var applyFlow = dataObj[i].applyFlow;
				var doctorName = dataObj[i].doctorName;
				var doctorFlow = dataObj[i].doctorFlow;
				$option =$("<option></option>");
				$option.attr("value",recruitFlow);
				$option.attr("id",applyFlow);
				$option.attr("doctorFlow",doctorFlow);
				$option.text(doctorName);
				$("#recruitFlow").append($option);
			}
		}
	});
}

function insertSptUserInfo(obj)
{
	var url = "<s:url value='/jsres/temp/insertSptUserInfo'/>";

	if(false==$("#excelForm8").validationEngine("validate")){
		return false;
	}
	showInfo("更新招录信息，请稍后。。。");
//	$(obj).attr("disabled","disabled");
	$("input[type='button']").attr("disabled","disabled");
	jboxSubmit(
			$('#excelForm8'),
			url,
			function(resp){
				closeInfo();
				$(obj).removeAttr("disabled");
				$("input[type='button']").removeAttr("disabled");
				if(resp.substring(0,5)=="${GlobalConstant.UPLOAD_SUCCESSED}")
					jboxInfo('${GlobalConstant.UPLOAD_SUCCESSED}');
				console.log(resp);
			},
			function(resp){
				closeInfo();
				$(obj).removeAttr("disabled");
				$("input[type='button']").removeAttr("disabled");
				jboxInfo('${GlobalConstant.UPLOAD_FAIL}');
			},false)
	;
}
function insertGjptUserInfo(obj)
{
	var url = "<s:url value='/jsres/temp/insertGjptUserInfo'/>";

	if(false==$("#excelForm9").validationEngine("validate")){
		return false;
	}
	showInfo("更新招录信息，请稍后。。。");
//	$(obj).attr("disabled","disabled");
	$("input[type='button']").attr("disabled","disabled");
	jboxSubmit(
			$('#excelForm9'),
			url,
			function(resp){
				closeInfo();
				$(obj).removeAttr("disabled");
				$("input[type='button']").removeAttr("disabled");
				if(resp.substring(0,5)=="${GlobalConstant.UPLOAD_SUCCESSED}")
					jboxInfo('${GlobalConstant.UPLOAD_SUCCESSED}');
				console.log(resp);
			},
			function(resp){
				closeInfo();
				$(obj).removeAttr("disabled");
				$("input[type='button']").removeAttr("disabled");
				jboxInfo('${GlobalConstant.UPLOAD_FAIL}');
			},false)
	;
}
</script>
<script type="text/javascript">
	String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {
		if (!RegExp.prototype.isPrototypeOf(reallyDo)) {
			return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);
		} else {
			return this.replace(reallyDo, replaceWith);
		}
	}
</script>
	<div class="div_search" style="width: 70%;line-height:normal;text-align: center;">
		<div>
			<h4>江苏西医数据处理(<font color="red">现场环境缓慢，请耐心等待</font>)</h4>
			<table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">
				<tr>
					<td class="td_left" style="width: 50%;text-align: right">
						学员流水号:
					</td>
					<td class=""style="width: 50%;text-align: left">
						<input type="text" id="doctorFlow" name="doctorFlow"  class="input"  style="width: 100px;margin-left: 0px"/>
					</td>
				</tr>
				<tr>
					<td class="td_left" style="width: 50%;text-align: right">
						结业资格申请年份:
					</td>
					<td class=""style="width: 50%;text-align: left">
						<input type="text" id="applyYear" name="applyYear"  class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>
						<input class="btn_green" type="button" value="申请记录" onclick="queryApplyList(this);"/>
					</td>
				</tr>
				<tr>
					<td class="td_left" style="width: 50%;text-align: right">
						申请记录:
					</td>
					<td class=""style="width: 70%;text-align: left " colspan="2">
						<select type="text" id="recruitFlow" name="recruitFlow" class="input"
								style="width: 400px;margin-left: 0px">
						</select>
					</td>
				</tr>
				<tr>
					<td class="td_left" style="width: 60%;text-align: center" colspan="2">
						<input class="btn_green" type="button" value="单个更新" onclick="apply2(this);">
						<input class="btn_green" type="button" value="所有学员更新" onclick="apply3(this);">
					</td>
				</tr>

				<tr>
					<td class="td_left" style="width: 60%;text-align: center" colspan="2">
						学员不超过100的百分比更新
					</td>
				</tr>
				<tr>
					<td class="td_left" style="width: 60%;text-align: center" colspan="2">
						<input class="btn_green" type="button" value="单个百分比更新" onclick="updatePer(this);">
						<input class="btn_green" type="button" value="所有学员" onclick="updatePer2(this);">
					</td>
				</tr>
				<tr>
					<td class="td_left" style="width: 50%;text-align: right">
						单个学员出科考核表数据抽取:
					</td>
					<td class=""style="width: 50%;text-align: left">
						<input class="btn_green" type="button" value="开始抽取" onclick="selectAfter2(this);"/>
					</td>
				</tr>
				<tr>
					<td class="td_left" style="width: 50%;text-align: right">
						学员出科考核表数据抽取:
					</td>
					<td class=""style="width: 50%;text-align: left">
						<input class="btn_green" type="button" value="开始抽取" onclick="selectAfter(this);"/>
					</td>
				</tr>
				<tr>
					<td class="td_left" style="width: 50%;text-align: right">
						更新学员相关证书信息:
					</td>
					<td class=""style="width: 50%;text-align: left">
						<input class="btn_green" type="button" value="开始更新" onclick="updateInfo(this);"/>
					</td>
				</tr>
				<tr>
					<td class="td_left" style="width: 50%;text-align: right">
						高校基地带教审核情况更新:
					</td>
					<td class=""style="width: 50%;text-align: left">
						<input class="btn_green" type="button" value="开始更新" onclick="updateUriAuditInfo(this);"/>
					</td>
				</tr>
				<tr>
					<td class="td_left" style="width: 50%;text-align: right">
						学员资格审查百分比:
					</td>
					<td class=""style="width: 50%;text-align: left">
						<input class="btn_green" type="button" value="第一步" onclick="updateDeptDetail(this);"/>
						<input class="btn_green" type="button" value="第二步" onclick="updateDeptTemp(this);"/>
						<input class="btn_green" type="button" value="第三步" onclick="updateDeptAvgTemp(this);"/>
						<input class="btn_green" type="button" value="第四步" onclick="updateRecruitAvgTemp(this);"/>
					</td>
				</tr>
				<tr>
					<td class="td_left" style="width: 50%;text-align: right">
						更新学员上传的出科考核表信息:
					</td>
					<td class=""style="width: 50%;text-align: left">
						<input class="btn_green" type="button" value="开始更新" onclick="updateResult(this);"/>
					</td>
				</tr>
				<tr>
					<td class="td_left" style="width: 50%;text-align: right">
						更新出科考核表审核后，医院管理员看不到的问题:
					</td>
					<td class=""style="width: 50%;text-align: left">
						<input class="btn_green" type="button" value="开始更新" onclick="updateAfter(this);"/>
					</td>
				</tr>
				<tr>
					<td class="td_left" style="width: 50%;text-align: right">
						开发库新增学员:
					</td>
					<td class=""style="width: 50%;text-align: left">
						<input class="btn_green" type="button" value="开始新增" onclick="addUser(this);"/>
					</td>
				</tr>
				<tr>
					<td class="td_left" style="width: 50%;text-align: right">
						初始化所有考官人员的角色:
					</td>
					<td class=""style="width: 50%;text-align: left">
						<input class="btn_green" type="button" value="开始处理" onclick="addExamTeaRole(this);"/>
					</td>
				</tr>
			</table>

			<form id="excelForm" method="post" enctype="multipart/form-data">
				<input type="hidden" id="checkFileFlag" name="checkFileFlag"/>
				<input type="hidden" value="D:/temp/userImage" name="dirUrl" id="dirUrl"/>
				<table  class="grid" style="width: 100%;">
					<tr>
						<th style="text-align: right;">请选择导入</th>
						<td style="text-align: left;">
							<input id="file" type="file" name="file" onchange="checkFile(this);" class="validate[required]"/>
						</td>
					</tr>
					<tr>
						<td class="td_left" style="width: 50%;text-align: right">
							抽取学员头像:
						</td>
						<td class=""style="width: 50%;text-align: left">
							<input class="btn_green" type="button" value="抽取" onclick="chouqu(this);"/>
						</td>
					</tr>
				</table>
			</form>
			<form id="excelForm2" method="post" enctype="multipart/form-data">
				<input type="hidden" id="checkFileFlag2" name="checkFileFlag"/>
				<table  class="grid" style="width: 100%;">
					<tr>
						<th style="text-align: right;">请选择导入</th>
						<td style="text-align: left;">
							<input id="file2" type="file" name="file" onchange="checkFile2(this);" class="validate[required]"/>
						</td>
					</tr>
					<tr>
						<td class="td_left" style="width: 50%;text-align: right">
							插入临时数据:
						</td>
						<td class=""style="width: 50%;text-align: left">
							<input class="btn_green" type="button" value="插入" onclick="addTempIdNo(this);"/>
						</td>
					</tr>
				</table>
			</form>
			<form id="excelForm3" method="post" enctype="multipart/form-data">
				<input type="hidden" id="checkFileFlag3" name="checkFileFlag"/>
				<table  class="grid" style="width: 100%;">
					<tr>
						<th style="text-align: right;">请选择导入</th>
						<td style="text-align: left;">
							<input id="file3" type="file" name="file" onchange="checkFile3(this);" class="validate[required]"/>
						</td>
					</tr>
					<tr>
						<td class="td_left" style="width: 50%;text-align: right">
							插入招录人员:
						</td>
						<td class=""style="width: 50%;text-align: left">
							<input class="btn_green" type="button" value="插入" onclick="addUserInfo(this);"/>
						</td>
					</tr>
				</table>
			</form>
			<form id="excelForm4" method="post" enctype="multipart/form-data">
				<input type="hidden" id="checkFileFlag4" name="checkFileFlag"/>
				<table  class="grid" style="width: 100%;">
					<tr>
						<th style="text-align: right;">请选择导入</th>
						<td style="text-align: left;">
							<input id="file4" type="file" name="file" onchange="checkFile4(this);" class="validate[required]"/>
						</td>
					</tr>
					<tr>
						<td class="td_left" style="width: 50%;text-align: right">
							更新招录信息:
						</td>
						<td class=""style="width: 50%;text-align: left">
							<input class="btn_green" type="button" value="插入" onclick="upDateRecruitInfo(this);"/>
						</td>
					</tr>
				</table>
			</form>
			<form id="excelForm5" method="post" enctype="multipart/form-data">
				<input type="hidden" id="checkFileFlag5" name="checkFileFlag"/>
				<table  class="grid" style="width: 100%;">
					<tr>
						<th style="text-align: right;">请选择导入</th>
						<td style="text-align: left;">
							<input id="file4" type="file" name="file" onchange="checkFile5(this);" class="validate[required]"/>
						</td>
					</tr>
					<tr>
						<td class="td_left" style="width: 50%;text-align: right">
							更新培训基地:
						</td>
						<td class=""style="width: 50%;text-align: left">
							<input class="btn_green" type="button" value="更新" onclick="updateRecruitOrgInfo(this);"/>
						</td>
					</tr>
				</table>
			</form>
			<form id="excelForm6" method="post" enctype="multipart/form-data">
				<input type="hidden" id="checkFileFlag6" name="checkFileFlag"/>
				<table  class="grid" style="width: 100%;">
					<tr>
						<th style="text-align: right;">请选择导入</th>
						<td style="text-align: left;">
							<input id="file4" type="file" name="file" onchange="checkFile6(this);" class="validate[required]"/>
						</td>
					</tr>
					<tr>
						<td class="td_left" style="width: 50%;text-align: right">
							更新培训专业:
						</td>
						<td class=""style="width: 50%;text-align: left">
							<input class="btn_green" type="button" value="更新" onclick="updateRecruitSpeInfo(this);"/>
						</td>
					</tr>
				</table>
			</form>
			<form id="excelForm7" method="post" enctype="multipart/form-data">
				<input type="hidden" id="checkFileFlag7" name="checkFileFlag"/>
				<table  class="grid" style="width: 100%;">
					<tr>
						<th style="text-align: right;">请选择导入</th>
						<td style="text-align: left;">
							<input id="file7" type="file" name="file" onchange="checkFile7(this);" class="validate[required]"/>
						</td>
					</tr>
					<tr>
						<td class="td_left" style="width: 50%;text-align: right">
							更新退培人员:
						</td>
						<td class=""style="width: 50%;text-align: left">
							<input class="btn_green" type="button" value="更新" onclick="updateReturenInfo(this);"/>
						</td>
					</tr>
				</table>
			</form>
			<form id="excelForm8" method="post" enctype="multipart/form-data">
				<input type="hidden" id="checkFileFlag8" name="checkFileFlag"/>
				<table  class="grid" style="width: 100%;">
					<tr>
						<th style="text-align: right;">请选择导入</th>
						<td style="text-align: left;">
							<input id="file8" type="file" name="file"  class="validate[required]"/>
						</td>
					</tr>
					<tr>
						<td class="td_left" style="width: 50%;text-align: right">
							输入省平台人员:
						</td>
						<td class=""style="width: 50%;text-align: left">
							<input class="btn_green" type="button" value="更新" onclick="insertSptUserInfo(this);"/>
						</td>
					</tr>
				</table>
			</form>
			<form id="excelForm9" method="post" enctype="multipart/form-data">
				<input type="hidden" id="checkFileFlag9" name="checkFileFlag"/>
				<table  class="grid" style="width: 100%;">
					<tr>
						<th style="text-align: right;">请选择导入</th>
						<td style="text-align: left;">
							<input id="file9" type="file" name="file"  class="validate[required]"/>
						</td>
					</tr>
					<tr>
						<td class="td_left" style="width: 50%;text-align: right">
							输入国家平台人员:
						</td>
						<td class=""style="width: 50%;text-align: left">
							<input class="btn_green" type="button" value="更新" onclick="insertGjptUserInfo(this);"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div>
			<p>注：1、测试环境更新可以依次执行。</p>
			<p>&#12288;&#12288;2、现场环境更新后，请勿执行。</p>
		</div>
    </div>
<div>
</div>