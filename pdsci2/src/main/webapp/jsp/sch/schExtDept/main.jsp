<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	.selSysDept span{color: red;}
</style>

<script type="text/javascript">

	$(document).ready(function(){
		changeSpeId();
		getExtStudents(1);
	});
	function changeSpeId() {
		var typeId = $("#doctorCategoryId").val();
		$("select[name=trainingSpeId] option").remove();
		if (typeId && typeId != '${recDocCategoryEnumDoctor.id}') {
			$("#" + typeId + "_select").find("option").each(function () {
				$(this).clone().appendTo($("#trainingSpeId"));
			});
		} else {
			$("#DoctorTrainingSpe_select").find("option").each(function () {
				$(this).clone().appendTo($("#trainingSpeId"));
			});
		}
	}
	function initDepts()
	{
		$("#schRotationDepts").html("");
		$("#schExternalDepts").html("");
		$("#schExternalDeptTimes").html("");
	}
	function changRotation()
	{
		var speId=$("#trainingSpeId").val();
		var url = "<s:url value='/sch/schExtDept/changRotation'/>?speId="+speId;
		jboxGet(url, null, function(resp){
			$("select[name=rotationFlow] option[value != '']").remove();
			if(resp!=""){
				var dataObj = eval("("+resp+")");
				if(dataObj.length == 1){
					for(var i = 0; i<dataObj.length;i++){
						var speId = dataObj[i].rotationFlow;
						var speName = dataObj[i].rotationName;
						$option =$("<option selected=selected></option>");
						$option.attr("value",speId);
						$option.text(speName);
						$("select[name=rotationFlow]").append($option);
					}
					var rotationObj = $("#rotationFlow");
					initDepts();
					searchDept(rotationObj);
					search();
				}else {
					for(var i = 0; i<dataObj.length;i++){
						var speId = dataObj[i].rotationFlow;
						var speName = dataObj[i].rotationName;
						$option =$("<option></option>");
						$option.attr("value",speId);
						$option.text(speName);
						$("select[name=rotationFlow]").append($option);
					}
					search();
				}
			}
		}, null , false);
	}
	function searchDept(obj)
	{
		$("#schExternalDepts").html("");
		$("#schExternalDeptTimes").html("");
		var rotationFlow=$(obj).val();
		var sessionNumber=$("#sessionNumber").val();
		jboxPostLoad("schRotationDepts" ,"<s:url value='/sch/schExtDept/searchDept'/>?rotationFlow="+rotationFlow,null , true);
	}
	function search()
	{
		$("#students").html("");
		var sessionNumber=$("#sessionNumber").val();
		if(sessionNumber=="")
		{
			jboxTip("请选择年级！");
			return false;
		}
		var trainingSpeId=$("#trainingSpeId").val();
		if(trainingSpeId=="")
		{
			jboxTip("请选择专业！");
			return false;
		}
		var rotationFlow=$("#rotationFlow").val();
		if(rotationFlow=="")
		{
			jboxTip("请选择轮转方案！");
			return false;
		}
		jboxPostLoad("students" ,"<s:url value='/sch/schExtDept/students'/>" ,$("#doctorSearchForm").serialize() , true);
		getExtStudents(1);
	}
	function delDoctorProcess(resultFlow,processFlow)
	{

		var url = '<s:url value="/sch/schExtDept/delDoctorProcess"/>?resultFlow='+resultFlow+"&processFlow="+processFlow;
		jboxConfirm("确认删除？" , function(){
			jboxGet(url , null , function(resp){
				if(resp=="${GlobalConstant.DELETE_SUCCESSED}")
				{
					getExtStudents(1);
				}
			} , null , true);
		});
	}
	function getExtStudents(page)
	{
		if(page!=undefined){
			$("#currentPage").val(page);
		}
		jboxPostLoad("extStudents" ,"<s:url value='/sch/schExtDept/getExtStudents'/>" ,$("#doctorSearchForm").serialize() , true);
	}
	function exportExtStudents()
	{
		var url = "<s:url value='/sch/schExtDept/exportExtStudents'/>";
		jboxTip("导出中…………");
		jboxExp($("#doctorSearchForm"),url);
	}
	function save()
	{
		//方案下标准科室流水号
		var recordFlow =  $(".selSysDept").attr("recordFlow") || "";
		//轮转科室
		var schDeptFlow =  $(".selSchDept").attr("schDeptFlow") || "";
		//开放时间
		var recordFlows ="";
		$("#schExternalDeptTimes input[name='recordFlows']:checked").each(function(){
			recordFlows+="&recordFlows="+$(this).val();
		});
		//选中的学员
		var doctorFlows="";
		$("#students .selUser").each(function(){
			doctorFlows+="&doctorFlows="+$(this).attr("userFlow");
		});
		if(recordFlow=="")
		{
			jboxTip("请选择标准科室！");
			return false;
		}
		if(schDeptFlow=="")
		{
			jboxTip("请选择轮转科室！");
			return false;
		}
		if(recordFlows=="")
		{
			jboxTip("请选择轮转时间！");
			return false;
		}
		if(doctorFlows=="")
		{
			jboxTip("请选择需要安排的学员！");
			return false;
		}
		var url = '<s:url value="/sch/schExtDept/addDoctorProcess"/>?recordFlow='+recordFlow+"&schDeptFlow="+schDeptFlow+recordFlows+doctorFlows;
				jboxConfirm("确认为这些学员添加轮转科室？" , function(){
					jboxGet(url , null , function(resp){
					if(resp=="${GlobalConstant.SAVE_SUCCESSED}")
					{
						getExtStudents(1);
					}
			} , null , true);
		});
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="title1 clearfix">
		<form  id="doctorSearchForm" method="post">
			<input type="hidden" id="currentPage" name="currentPage" value="">
			<div class="queryDiv">
				<div class="inputDiv">
					<label class="qlable">培训类别：</label>
					<select name="doctorCategoryId" id="doctorCategoryId" class="qselect" onchange="changeSpeId();">
						<c:forEach items="${recDocCategoryEnumList}" var="category">
							<c:set var="res_doctor_category_key" value="res_doctor_category_${category.id}"/>
							<c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y}">
								<option value="${category.id}" ${doctor.doctorCategoryId eq category.id?'selected':''}>${category.name}</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
				<div class="inputDiv">
					<label class="qlable">年&#12288;&#12288;级：</label>
					<select name="sessionNumber" id="sessionNumber" class="qselect">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">
							<option value="${dict.dictName}" ${dict.dictName eq doctor.sessionNumber?'selected':''}>${dict.dictName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="inputDiv">
					<label class="qlable">培训专业：</label>
					<select name="trainingSpeId" id="trainingSpeId"class="qselect" onchange="initDepts();changRotation();">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
							<option value="${dict.dictId}" ${doctor.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="inputDiv">
					<label class="qlable">轮转方案：</label>
					<select name="rotationFlow" id="rotationFlow" class="qselect" onchange="initDepts();searchDept(this);">
						<option value="">请选择</option>
					</select>
				</div>
				<div class="lastDiv">
					<input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
				</div>
			</div>
		</form>
	</div>
	<div class="content" style="width: 95%;margin-left:10px;">

		<br>
		<div>
			<font color="blue">Tip:请先选择年级及培训专业！</font>
		</div>
		<table class="basic" style="width:100%;margin-top: 20px;">
			<tr style="width: 100%;">
				<td style="width: 24%">
					<p>
						<h2>标准轮转科室</h2>
					</p>
				</td>
				<td style="width: 24%">
					<p>
						<h2>医院开放科室</h2>
					</p>
				</td>
				<td style="width: 24%">
					<p>
						<h2>对外开放时间</h2>
					</p>
				</td>
				<td style="width: 24%">
					<p>
						<h2>学员</h2>
					</p>
				</td>
			</tr>
			<tr>
				<td style="padding-left: 0px;">
					<div id="schRotationDepts" style="width: 100%;min-height: 450px;max-height: 450px;overflow: auto;">

					</div>
				</td>
				<td style="padding-left: 0px;">
					<div id="schExternalDepts" style="width: 100%;min-height: 450px;max-height: 450px;overflow: auto;">

					</div>
				</td>
				<td style="padding-left: 0px;">
					<div id="schExternalDeptTimes" style="width: 100%;min-height: 450px;max-height: 450px;overflow: auto;">

					</div>
				</td>
				<td style="padding-left: 0px;">
					<div id="students" style="width: 100%;min-height: 450px;max-height: 450px;overflow: auto;">

					</div>
				</td>
			</tr>
			<tr>
				<td style="text-align: center;" colspan="4">
					<input type="button" value="保&#12288;存" class="search" onclick="save();"/>
				</td>
			</tr>
		</table>

		<div id="extStudents" style="width: 100%;margin-top: 10px;">

		</div>
	</div>
	<div style="display: none;">

		<c:forEach items="${recDocCategoryEnumList}" var="category">
			<c:set var="res_doctor_category_key" value="res_doctor_category_${category.id}"/>
			<c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y}">
				<select id="${category.id}_select">
					<option value="">请选择</option>
					<c:set var="dictName" value="dictTypeEnum${category.id }List" />
					<c:forEach items="${applicationScope[dictName]}" var="trainSpe">
						<option value="${trainSpe.dictId }">${trainSpe.dictName }</option>
					</c:forEach>
				</select>
			</c:if>
		</c:forEach>

		<select id="DoctorTrainingSpe_select">
			<option value="">请选择</option>
			<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
				<option value="${dict.dictId}">${dict.dictName}</option>
			</c:forEach>
		</select>
	</div>
</div>
</body>
</html>