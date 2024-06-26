
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

<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

<script type="text/javascript">
function edit(userFlow) {
	jboxOpen("<s:url value='/sys/user/edit/${GlobalConstant.USER_LIST_PERSONAL}?userFlow='/>"
			+ userFlow, "修改个人信息", 900, 400);
}

function saveDoc(){
	if(!$("#resDoctor").validationEngine("validate")){
		return;
	}
	var idNo=$("#idNo").val();
	if(idNo!=""){
		if (!(/^[0-9a-zA-Z]{8}$/.test(idNo)) &&!(/^[0-9a-zA-Z]{9}$/.test(idNo)) && !(/^[0-9a-zA-Z]{11}$/.test(idNo)) && !(/^[0-9a-zA-Z]{15}$/.test(idNo)) && !(/^[0-9a-zA-Z]{18}$/.test(idNo))) {
			jboxTip("请输入8位或9位或11位或15位或18位的证件号（只能是数字和字母组合）");
			return false;
		}
	}
    //什么什么省份
    var locationOfProvinceName = $("#locationOfProvince option:selected").text();
    $("#locationOfProvinceName").val(locationOfProvinceName);
    //婚姻状态
    var maritalStatusName = $("#maritalStatus option:selected").text();
    $("#maritalStatusName").val(maritalStatusName);
    //本科学位
    var undergraduateDegreeName = $("#undergraduateDegreeId option:selected").text();
    $("#undergraduateDegreeName").val(undergraduateDegreeName);
    //医师资格类别
    var physicianQualificationClassName = $("#physicianQualificationClass option:selected").text();
    $("#physicianQualificationClassName").val(physicianQualificationClassName);
    //执业类别
    var practicingCategoryName = $("#practicingCategoryId option:selected").text();
    $("#practicingCategoryName").val(practicingCategoryName);
    //执业范围
    var practicingScopeName = $("#practicingScopeId option:selected").text();
    $("#practicingScopeName").val(practicingScopeName);
    //人员属性
    var personnelAttributeName = $("#personnelAttributeId option:selected").text();
    $("#personnelAttributeName").val(personnelAttributeName);
    //硕士学位类型
    var masterDegreeTypeText = $("#masterDegreeTypeId option:selected").text();
    $("#masterDegreeTypeName").val(masterDegreeTypeText);
    //博士学位
    var doctorDegreeName = $("#doctorDegreeId option:selected").text();
    $("#doctorDegreeName").val(doctorDegreeName);
    //博士学位类型
    var doctorDegreeTypeName = $("#doctorDegreeType option:selected").text();
    $("#doctorDegreeTypeName").val(doctorDegreeTypeName);
    //医院属性
    var hospitalAttrText = $("#hospitalAttrId option:selected").text();
    $("#hospitalAttrName").val(hospitalAttrText);
    //医院类别
    var hospitalCategoryText = $("#hospitalCategoryId option:selected").text();
    $("#hospitalCategoryName").val(hospitalCategoryText);
    //单位性质
    var baseAttributeText = $("#baseAttributeId option:selected").text();
    $("#baseAttributeName").val(baseAttributeText);
    //医疗卫生机构
    var medicalHeaithOrgText = $("#medicalHeaithOrgId option:selected").text();
    $("#medicalHeaithOrgName").val(medicalHeaithOrgText);
    //基层医疗卫生机构
    var basicHealthOrgText = $("#basicHealthOrgId option:selected").text();
    $("#basicHealthOrgName").val(basicHealthOrgText);
	if('${user.userHeadImg}'==""&&$("#asshole").val()!="Y"){
		jboxTip("请上传头像");
		return false;
	}
	getUserResumeExtName();
	getFantasticFour();
	jboxConfirm("确认保存？",function(){
        	$("#idNo").attr("disabled",false);
			var url = "<s:url value='/res/doc/user/saveDocSimple'/>";
			jboxSubmit( $('#resDoctor'),url, function(data) {
				if('${GlobalConstant.SAVE_SUCCESSED}'==data){
					window.location.reload();
				}
			},true);

	})
}

$(document).ready(function(){
	<c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap['sys_user_show_resum']}">
		loadEdu();
	 	loadWork();
		loadAcademic();
		loadProj();
		loadTrain();
		loadThesis();
		loadBook();
		loadPatent();
		loadSat();
	</c:if>
	if('${doctor.doctorLicenseFlag}'=='N') $(".qualifiedNoTh, .qualifiedNoTd").hide();
	if('${doctor.doctorLicenseFlag}'=='Y') $(".qualifiedNoTh, .qualifiedNoTd").show();
	if('${doctor.practPhysicianFlag}'=='N') $(".PhysicianCertificateTr").hide();
	if('${doctor.practPhysicianFlag}'=='Y') $(".PhysicianCertificateTr").show();
	if('${doctor.graduationYear}'==""||'${doctor.graduationYear}'==null){
		calculation();
	}
	showSecond("${doctor.doctorCategoryId}");
	var isCollegeDegree = '${extInfo.isCollegeDegree}';
	isCollege(isCollegeDegree);
	var isUndergraduateDegree = '${extInfo.isUndergraduateDegree}';
	checkIsUndergraduateDegree(isUndergraduateDegree);
	showId3("${extInfo.isAssistance}");
	if('${extInfo.isAssistance}'=='Y'){
		showAssistanceCode('${extInfo.isMilitary}');
	}
	showTime("${extInfo.isPassQualifyingExamination}");
	examsTime("${doctor.doctorLicenseFlag}");
	showTime2("${doctor.practPhysicianFlag}");
});
function isCollege(value){
	if(value=='Y'){
		$(".collegeDegreeContent").show();
	}else{
		$(".collegeDegreeContent").hide();
		$(".collegeDegreeContent input[type!='radio'],.collegeDegreeContent select").each(function(){
			$(this).val("");
		});
	}
}

function checkIsUndergraduateDegree(value){
	if(value=='Y'){
		$(".undergraduateDegreeContent").show();
	}else{
		$(".undergraduateDegreeContent").hide();
		$(".undergraduateDegreeContent input[type!='radio'],.undergraduateDegreeContent select").each(function(){
			$(this).val("");
		});
	}
}
function calculation(){
	var y;
	if($("#trainingYears").text()=='1') y=1;
	else if($("#trainingYears").text()=='2') y=2;
	else if($("#trainingYears").text()=='3') y=3;
	else y=0;
	var year = parseInt($("#sessionNumber").text())+parseInt(y);
	if(year!=0&&!isNaN(year)&&year>'${doctor.graduationYear}'){
		$("#graduationYear").text(year);
	}
}

function loadEdu(){
	jboxLoad("edu","<s:url value='/pub/resume/loadView?type=education&userFlow=${user.userFlow}&editFlag=${param.editFlag}'/>");
}

function loadWork(){
	jboxLoad("work","<s:url value='/pub/resume/loadView?type=work&userFlow=${user.userFlow}&editFlag=${param.editFlag}'/>");
}

function loadAcademic(){
	jboxLoad("academic","<s:url value='/pub/resume/loadView?type=academic&userFlow=${user.userFlow}&editFlag=${param.editFlag}'/>");
}

function loadProj(){
	jboxLoad("proj","<s:url value='/pub/resume/loadView?type=proj&userFlow=${user.userFlow}'/>");
}

function loadTrain(){
	jboxLoad("train","<s:url value='/pub/resume/loadView?type=train&userFlow=${user.userFlow}'/>");
}


function loadThesis(){
	var url = "<s:url value='/pub/resume/loadView'/>?type=thesis&userFlow=${user.userFlow}";
	jboxLoad("thesis",url+achievementFlag());
}

function loadBook(){
	var url = "<s:url value='/pub/resume/loadView'/>?type=book&userFlow=${user.userFlow}";
	jboxLoad("book",url+achievementFlag());
}

function loadPatent(){
	var url = "<s:url value='/pub/resume/loadView'/>?type=patent&userFlow=${user.userFlow}";
	jboxLoad("patent",url+achievementFlag());
}

function loadSat(){
	var url = "<s:url value='/pub/resume/loadView'/>?type=sat&userFlow=${user.userFlow}";
	jboxLoad("sat",url+achievementFlag());
}

function searchUser(){
	window.location.reload();
}

function slideToggle(obj){
	$("."+obj).slideToggle("fast");
}

function print(){
}

function achievementFlag(){
	if("${sessionScope.currWsId}" != "${GlobalConstant.SRM_WS_ID}"){
		return "&editFlag=${param.editFlag}";
	}
}

var isSch = {
	<c:forEach items="${recDocCategoryEnumList}" var="category">
	<c:set var="res_doctor_category_key" value="res_doctor_category_${category.id }"/>
	<c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y }">
	<c:set var="isSchKey" value="res_doctor_category_${category.id}_sch"/>
	"${category.id}":"${sysCfgMap[isSchKey]}",
	</c:if>
	</c:forEach>
};
function changeDocCategory(){
	var doctorCategoryId = $("#doctorCategoryId").val();

	if("${GlobalConstant.FLAG_N}"!=isSch[doctorCategoryId])
	{
		var value=$("#doctorTypeId").val();
		if (value == 'Company') {
			$(".departMentFlow").show();
		}else{
			$(".departMentFlow").hide();
			$("#departMentFlow").val("");
		}
	}else{
		$(".departMentFlow").hide();
		$("#departMentFlow").val("");
	}
}

function showSecond(doctorCategoryId)
{
	if(doctorCategoryId!="")
	{
		if(doctorCategoryId =="${recDocCategoryEnumChineseMedicine.id}"||doctorCategoryId =="${recDocCategoryEnumTCMGeneral.id}")
		{
			$(".secondTr").show();
		}else{
			$(".secondTr").hide();
		}
	}else{
		$(".secondTr").hide();
	}
}
function uploadImage(){
	$.ajaxFileUpload({
		url:"<s:url value='/sys/user/userHeadImgUpload'/>?userFlow=${user.userFlow}",
		secureuri:false,
		fileElementId:'imageFile',
		dataType: 'json',
		success: function (data, status){
			if(data.indexOf("success")==-1){
				jboxTip(data);
			}else{
				var arr = [];
				arr = data.split(":");
				$("#userImg").val(arr[1]);
				var url = "${sysCfgMap['upload_base_url']}/"+ arr[1];
				$("#showImg").attr("src",url);
				$("#asshole").val("Y");
			}
		},
		error: function (data, status, e){
			jboxTip('${GlobalConstant.UPLOAD_FAIL}');
		},
		complete:function(){
			$("#imageFile").val("");
		}
	});
}
function showId(radio){
	if(radio=='Y') $(".qualifiedNoTh,.qualifiedNoTd").show();
	else $(".qualifiedNoTh,.qualifiedNoTd").hide();
}
function showId2(radio){
	if(radio=='Y') $(".PhysicianCertificateTr").show();
	else $(".PhysicianCertificateTr").hide();
}

function getFantasticFour(){
	if($(':radio[name="userResumeExt.isDoctor"]:checked').val()=='${GlobalConstant.FLAG_Y}'){
		$("#uEducationName").val("博士");
		$("#dGraduatedName").val($("#bsbyyx").val());
		$("#dSpecialized").val($("#bsbyzy").val());
		$("#dGraduationTime").val($("#bsbysj").val());
	}else if($(':radio[name="userResumeExt.isMaster"]:checked').val()=='${GlobalConstant.FLAG_Y}'){
		$("#uEducationName").val("硕士");
		$("#dGraduatedName").val($("#byyx").val());
		$("#dSpecialized").val($("#ssbyzy").val());
		$("#dGraduationTime").val($("#ssbysj").val());
	}else{
		$("#uEducationName").val($("#degree  option:selected").text());
		$("#dGraduatedName").val($("#bkbyyx").val());
		$("#dSpecialized").val($("#bkbyzy").val());
		$("#dGraduationTime").val($("#bkbysj").val());
	}
}
function getUserResumeExtName(){
	var technologyQualificationId = $("#technologyQualificationId").val();
	var technologyQualificationName = "";
	if(technologyQualificationId != ""){
		technologyQualificationName = $(	"#technologyQualificationId :selected").text();
	}
	$("#technologyQualificationName").val(technologyQualificationName);

	var qualificationMaterialId = $("#qualificationMaterialId").val();
	var qualificationMaterialName = "";
	if(qualificationMaterialId != ""){
		qualificationMaterialName = $("#qualificationMaterialId :selected").text();
	}
	$("#qualificationMaterialName").val(qualificationMaterialName);

	var practicingCategoryId = $("#practicingCategoryId").val();
	var practicingCategoryName = "";
	if(practicingCategoryId != ""){
		practicingCategoryName = $("#practicingCategoryId :selected").text();
	}
	$("#practicingCategoryName").val(practicingCategoryName);

	var practicingScopeId = $("#practicingScopeId").val();
	var practicingScopeName= "";
	if(practicingScopeId != ""){
		practicingScopeName = $("#practicingScopeId :selected").text();
	}
	$("#practicingScopeName").val(practicingScopeName);
}
//文件功能
function uploadFile(type,typeName,changeFlag) {
	jboxOpen("<s:url value='/inx/hbres/uploadFile'/>?operType="+type+"&changeFlag=S","上传"+typeName,450,150);
}

function setNameById(id,val) {
	var name="";
	<c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
	   if("${dict.dictId}"==val)
	   {
		   name="${dict.dictName}"
	   }
	</c:forEach>
	console.log($("#"+id));
	$("#"+id).val(name);
}
function delFile(type) {
	$("#"+type+"Del").hide();
	$("#"+type+"Span").hide();
	$("#"+type).text("上传");
	if(type != "dispatchFileF" && type != "qualifiedFileF" && type != "degreeUriFile"){//委培证明,医师资格证,最高学历证,最高学位证 非必传
		$("#"+type).addClass("validate[required]");
	}
	$("#"+type+"Value").val("");
}
function showTime(flag){
	if(flag == "Y"){
		$(".examinationTime").show();
//		$("input[name='doctorLicenseFlag'][value='Y']").removeAttr("disabled");
//		$("input[name='practPhysicianFlag'][value='Y']").removeAttr("disabled");
	}else{
		$("#passQualifyingExaminationTime").val("");
		$(".examinationTime").hide();
//		examsTime("N");
//		$("input[name='doctorLicenseFlag'][value='Y']").attr("disabled","disabled");
//		$("input[name='doctorLicenseFlag'][value='N']").attr("checked","checked");
//		showTime2("N");
//		$("input[name='practPhysicianFlag'][value='Y']").attr("disabled","disabled");
//		$("input[name='practPhysicianFlag'][value='N']").attr("checked","checked");
	}
}

function examsTime(flag){
	if(flag == "Y"){
		$(".examTime").show();
//		$("input[name='practPhysicianFlag'][value='Y']").removeAttr("disabled");
	}else{
		$(".examTime input,.examTime select").each(function(){
			$(this).val("");
		});
		$(".examTime").hide();
//		showTime2("N");
//		$("input[name='practPhysicianFlag'][value='Y']").attr("disabled","disabled");
//		$("input[name='practPhysicianFlag'][value='N']").attr("checked","checked");
	}
}

function showTime2(flag){
	if(flag == "Y"){
		$(".showTime2").show();
	}else{
		$(".showTime2 input,.showTime2 select").each(function(){
			$(this).val("");
		});
		$(".showTime2").hide();
	}
}
function showId3(obj){
	if(obj == "Y"){
		$(".isAssistance").show();
	}else{
		$(".isAssistance").hide();
		$(".isAssistance input[type!='radio'],.isAssistance select").each(function(){
			$(this).val("");
		});
	}
}

function showAssistanceCode(value){
	if(value == "N"){
		$(".assistanceCode").show();
	}else{
		$(".assistanceCode").hide();
		$("input[name='assistanceCode']").val("");
	}
}
</script>
<style>
	table{ margin:10px 0;border-collapse: collapse;}
	caption,th,td{height:40px;}
	caption{line-height:40px;text-align:left;font-weight:bold; padding-left:10px; border-bottom:1px solid #ddd;color:#3385ff;margin-bottom: 10px;}
	th{text-align:right;font-weight:500;padding-right:5px; color:#333;}
	td{text-align:left; padding-left:5px; }
	[type='text']{width:150px;height: 22px;}
	select{width: 153px;height: 27px;}
	.cHeight th,.cHeight td{
		height:0;
	}
</style>
</head>
<body>
<div class="mainright">
		<div class="content">
	<div class="title1 clearfix">
		<div style="margin-bottom: 10px;" align="right">
		<!-- 
		<input type="checkbox" title="全选"/>类型：<input type="checkbox"/>教育经历&#12288;<input type="checkbox"/>工作经历&#12288;<input type="checkbox"/>学会任职&#12288;
		<input type="checkbox"/>课题情况&#12288;<input type="checkbox"/>培训情况&#12288;<input type="checkbox"/>论文&#12288;<input type="checkbox"/>著作&#12288;
		<input type="checkbox"/>专利&#12288;<input type="checkbox"/>获奖&#12288;
		 -->
			<%--下载没有效果，并不知道需求，先去掉按钮--%>
		 <%--<input type="button" value="下&#12288;载" class="search" onclick="print();"/>--%>
		 </div>
		<!-- 基本信息 -->
		<c:if test="${!isDoctor eq true}">
		<div id="base">
			<table  class="xllist">
				<tr>
					<th colspan="4" style="text-align: left;padding-left: 20px;">基本信息
						<c:if test="${param.editFlag == GlobalConstant.FLAG_Y && sessionScope.currWsId !=GlobalConstant.ERP_WS_ID}">
							<c:if test="${sysCfgMap['sys_user_show_info_edit'] eq GlobalConstant.FLAG_Y}">
								<a style="float: right;padding-right: 10px;" href="javascript:edit('${ user.userFlow}')">[修改]</a>
							</c:if>
						</c:if>
					</th>
				</tr>
				<tr>
					<td style="text-align: left;padding-left: 30px;width: 25%">姓&#12288;&#12288;名：${user.userName}</td>
					<td style="text-align: left;padding-left: 30px;width: 25%">性&#12288;别：${user.sexName }</td>
					<td style="text-align: left;padding-left: 30px;">登&nbsp;录&nbsp;名&nbsp;：${user.userCode}</td>
					<c:if test="${sessionScope.currWsId ne 'gzykdx'}">
					<td style="text-align: left;;width:200px" rowspan="5">
						<div style="width: 110px;height: 130px;margin: 5px auto 0px;">
							<img src="${sysCfgMap['upload_base_url']}/${user.userHeadImg}" id="showImg" width="100%"
								 height="100%" onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
						</div>
						<div style="text-align: center;">
							<span style="cursor: pointer;text-align: center;">
								[<a onclick="$('#imageFile').click();">${empty user.userHeadImg?'上传头像':'重新上传'}</a>]
							</span>
							<input type="file" id="imageFile" name="headImg" style="display: none" onchange="uploadImage();"/>
						</div>
					</td>
					</c:if>
				</tr>
				<tr>
					<td style="text-align: left;padding-left: 30px;width: 25%">出生日期：${user.userBirthday }</td>
					<td style="text-align: left;padding-left: 30px;">年&#12288;龄：${pdfn:calculateAge(user.userBirthday)}岁</td>
					<td style="text-align: left;padding-left: 30px;">身份证号：${user.idNo}</td>
				</tr>
				<tr>
					<td style="text-align: left;padding-left: 30px;">学&#12288;&#12288;历：${user.educationName }</td>
					<td style="text-align: left;padding-left: 30px;">学&#12288;位：${user.degreeName}</td>
					<td style="text-align: left;padding-left: 30px;">工作单位：${user.orgName }</td>
				</tr>
				<tr>
					<td style="text-align: left;padding-left: 30px;">部&#12288;&#12288;门：${user.deptName }<c:if test="${not empty moreDept}">&#12288;[${moreDept}]</c:if></td>
					<td style="text-align: left;padding-left: 30px;">职&#12288;务：${user.postName }</td>
					<td style="text-align: left;padding-left: 30px;">职&#12288;&#12288;称：${user.titleName }</td>
				</tr>
				<tr>
					<td style="text-align: left;padding-left: 30px;">手&#12288;&#12288;机：${user.userPhone }</td>
					<td style="text-align: left;padding-left: 30px;">邮&#12288;件：${user.userEmail }</td>
					<td style="text-align: left;padding-left: 30px;"></td>
				</tr>
				<c:if test="${sessionScope.currWsId==GlobalConstant.SRM_WS_ID}">
				<tr>
					<td style="text-align: left;padding-left: 30px;">个人科研账户：${user.accountNo }</td>
					<td style="text-align: left;padding-left: 30px;"></td>
					<td style="text-align: left;padding-left: 30px;"></td>
					<td style="text-align: left;padding-left: 30px;"></td>
				</tr>
				</c:if>
				<c:if test="${isTeacher}">
					<tr>
						<td style="text-align: left;padding-left: 30px;">证书级别：${user.certificateLevelName }</td>
						<td style="text-align: left;padding-left: 30px;">取得日期：${user.certificateTime }</td>
						<td style="text-align: left;padding-left: 30px;">证书附件：<c:if test="${not empty file}">
							<a id="down" target="_blank" href="${sysCfgMap['upload_base_url']}${file.filePath}">${file.fileName}</a></c:if></td>
						<td style="text-align: left;padding-left: 30px;"></td>
					</tr>
				</c:if>
			</table>
		</div>
		</c:if>
		<c:if test="${isDoctor eq true}">
			<div style="margin-right:10% ">
	<input type="hidden" id="asshole">
<form id="resDoctor" action="<s:url value='/res/doc/user/saveDocSimple'/>" method="post">
	<input type="hidden" name="doctorFlow" value="${doctor.doctorFlow}"/>
	<input type="hidden" name="orgFlow" value="${doctor.orgFlow}"/>
	<input type="hidden" name="userFlow" value="${user.userFlow}"/>
			<table style="width:100%;">
				<caption>人员类型信息</caption>
				<colgroup>
					<col width="12%"/>
					<col width="16%"/>
					<col width="14%"/>
					<col width="16%"/>
					<col width="14%"/>
					<col width="16%"/>
					<col width="10%"/>
				</colgroup>
				<tr class="cHeight">
					<th></th><td></td><th></th><td></td><th></th><td></td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>学员类型：
					</th>
					<td>
						<c:if test="${not empty doctor.doctorTypeId}">
							<input type="hidden"  name="doctorTypeId"  id="doctorTypeId" value="${doctor.doctorTypeId}">
							<c:forEach items="${dictTypeEnumDoctorTypeList}" var="dict">
								<c:if test="${doctor.doctorTypeId eq dict.dictId}">${dict.dictName }</c:if>
							</c:forEach>
						</c:if>
						<c:if test="${empty doctor.doctorTypeId}">
							<select name="doctorTypeId" class="validate[required]" id="doctorTypeId" onchange="checkDoctorType(this.value),checkDoctorType2(this.value);">
								<option></option>
								<c:forEach items="${dictTypeEnumDoctorTypeList}" var="dict">
									<option value="${dict.dictId}" <c:if test="${doctor.doctorTypeId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName }</option>
								</c:forEach>
							</select>
						</c:if>
					</td>
					<th class="workOrgNameTh">
						<font color="red">*</font>工作单位：
					</th>
					<td class="workOrgNameTd">
							${doctor.workOrgName}
					</td>
					<th class="prove"><font color="red">*</font>委培单位同意证明：</th>
					<td class="prove">
						<span id="dispatchFileFSpan" style="display:${!empty doctor.dispatchFile?'':'none'} ">
						[<a href="${sysCfgMap['upload_base_url']}/${doctor.dispatchFile}" target="_blank">查看图片</a>]
						</span>
						<a id="dispatchFileF" href="javascript:uploadFile('dispatchFileF','单位同意证明');" style="color:blue" class="${empty doctor.dispatchFile?'validate[required]':''}">${empty doctor.dispatchFile?'':'重新'}上传</a>
						<a id="dispatchFileFDel" href="javascript:delFile('dispatchFileF');"  style="color:blue;${empty doctor.dispatchFile?'display:none':''}">删除</a>
						<input type="hidden" id="dispatchFileFValue" name="dispatchFile" value="${doctor.dispatchFile }">
					</td>
				</tr>
				<tr>
					<th class="medicalInstitutionTh">
						<font color="red">*</font>医疗卫生机构：</th>
					<td class="medicalInstitutionTd">
						<select class="validate[required]" id="medicalHeaithOrgId" name="medicalHeaithOrgId" id="medicalHeaithOrgId" onchange="changeMedicalThings(this.value);" >
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumMedicalHeaithOrgList}" var="tra">
								<option value="${tra.dictId}" <c:if test="${extInfo.medicalHeaithOrgId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
							</c:forEach>
						</select>
						<input type="hidden" id="medicalHeaithOrgName" name="medicalHeaithOrgName" value="${extInfo.medicalHeaithOrgName}">
					</td>
					<th class="basicmedicalInstitutionTypeTh">
						<font color="red">*</font>基层医疗卫生机构类型：
					</th>
					<td class="basicmedicalInstitutionTypeTd">
						<select class="validate[required]" id="basicHealthOrgId" name="basicHealthOrgId" >
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumBasicHealthOrgList}" var="tra">
								<option value="${tra.dictId}" <c:if test="${extInfo.basicHealthOrgId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
							</c:forEach>
						</select>
						<input type="hidden" id="basicHealthOrgName" name="basicHealthOrgName" value="${extInfo.basicHealthOrgName}">
					</td>
				</tr>
				<tr class="hospitalTr">
					<th>
						<font color="red">*</font>医院属性：</th>
					<td>
						<select class="validate[required]" id="hospitalAttrId" name="hospitalAttrId">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumHospitalAttrList}" var="tra">
								<option value="${tra.dictId}" <c:if test="${extInfo.hospitalAttrId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
							</c:forEach>
						</select>
						<input type="hidden" id="hospitalAttrName" name="hospitalAttrName" value="${extInfo.hospitalAttrName}">
					</td>
					<th>
						<font color="red">*</font>医院类别：</th>
					<td>
						<select class="validate[required]" id="hospitalCategoryId" name="hospitalCategoryId">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumHospitalCategoryList}" var="tra">
								<option value="${tra.dictId}" <c:if test="${extInfo.hospitalCategoryId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
							</c:forEach>
						</select>
						<input type="hidden" id="hospitalCategoryName" name="hospitalCategoryName" value="${extInfo.hospitalCategoryName}">
					</td>
					<th>
						<font color="red">*</font>单位性质：</th>
					<td>
						<select class="validate[required]" id="baseAttributeId" name="baseAttributeId">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumBaseAttributeList}" var="tra">
								<option value="${tra.dictId}" <c:if test="${extInfo.baseAttributeId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
							</c:forEach>
						</select>
						<input type="hidden" id="baseAttributeName" name="baseAttributeName" value="${extInfo.baseAttributeName}">
					</td>
				</tr>
			</table>
			<table style="width:100%;">
				<caption>个人信息</caption>
				<colgroup>
					<col width="12%"/>
					<col width="16%"/>
					<col width="14%"/>
					<col width="16%"/>
					<col width="14%"/>
					<col width="16%"/>
					<col width="10%"/>
				</colgroup>
				<tr>
					<th>
						<font color="red">*</font>培训类别：</th>
					<td>
						<select name="doctorCategoryId" class="validate[required]" id="doctorCategoryId" onchange="changeDocCategory();showSecond(this.value);">
							<option></option>
							<c:forEach items="${recDocCategoryEnumList}" var="category">
								<c:set var="res_doctor_category_key" value="res_doctor_category_${category.id }"/>
								<c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y }">
									<option value="${category.id}" ${doctor.doctorCategoryId eq category.id?'selected':''}>${category.name}</option>
								</c:if>
							</c:forEach>
						</select>
					</td>
					<th>
						<c:if test="${empty user}">
							<font color="red">*</font>
						</c:if>
						<c:if test="${not empty user}">
							<font color="">&nbsp;</font>
						</c:if>用户名：</th>
					<td>
						<c:if test="${empty user}">
							<input type="text" name="userCode" value="${user.userCode}" class="validate[required,custom[userCode]]">
						</c:if>
						<c:if test="${!empty user}">
							${user.userCode}
							<input type="hidden" name="userCode" value="${user.userCode}"/>
						</c:if>
					</td>
					<th>
						<font color="red">*</font>真实姓名：</th>
					<td>
						<input type="text" name="userName" value="${user.userName}" class="validate[required]" disabled="disabled"/>
					</td>
					<td style="vertical-align: top;width:200px" rowspan="12">
						<div style="width: 110px;height: 130px;margin: 5px auto 0px;">
							<img src="${sysCfgMap['upload_base_url']}/${user.userHeadImg}" id="showImg" width="100%"
								 height="100%" onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
						</div>
						<div style="text-align: center;">
						<span style="cursor: pointer;">
						[<a title="照片要求：1、2寸白底彩色免冠照片，JPG格式，分辨率不小于413*626，大小不超过300K，人像正立；2、该照片用于结业证书，请慎重认真上传！" onclick="$('#imageFile').click();">${empty user.userHeadImg?'上传头像':'重新上传'}</a>]
						</span>
							<input type="file" id="imageFile" name="headImg" style="display: none"
								   onchange="uploadImage();"/>
						</div>
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>性别：</th>
					<td>
						<label><input type="radio" name="sexId" class="validate[required]" <c:if test="${user.sexId eq userSexEnumMan.id}">checked="checked"</c:if>  value="${userSexEnumMan.id}" />男</label>&#12288;
						<label><input type="radio" name="sexId" class="validate[required]" <c:if test="${user.sexId eq userSexEnumWoman.id}">checked="checked"</c:if> value="${userSexEnumWoman.id}" />女</label>
					</td>
					<th>
						<font color="red">*</font>年龄：</th>
					<td>
						<input type="text" name="age" value="${extInfo.age}" class="validate[required]"/>
					</td>
					<th>
						<font color="red">*</font>证件类型：</th>
					<td>
						<select name="cretTypeId" class="validate[required]" disabled="disabled">
							<c:forEach items="${certificateTypeEnumList}" var="certType">
								<option value="${certType.id}"
										<c:if test="${user.cretTypeId eq certType.id}">selected</c:if>>${certType.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>证件号码：</th>
					<td>
						<input type="text" name="idNo" id="idNo" value="${user.idNo}" class="validate[required]" disabled="disabled">
					</td>
					<th>
						<font color="red">*</font>民族：</th>
					<td>
						<select name="nationId" class="validate[required]">
							<option/>
							<c:forEach items="${userNationEnumList}" var="nation">
								<option value="${nation.id}" ${user.nationId eq nation.id?'selected':''}>${nation.name}</option>
							</c:forEach>
						</select>
					</td>
					<th><font color="red">*</font>人员属性：</th>
					<td>
						<select id="personnelAttributeId" name="personnelAttributeId" class="validate[required]">
							<option value="">请选择</option>
							<c:forEach items="${personnelAttributeEnumList}" var="attr">
								<option value="${attr.id}" ${extInfo.personnelAttributeId eq attr.id?'selected':''}>${attr.name}</option>
							</c:forEach>
						</select>
						<input id="personnelAttributeName" name="personnelAttributeName" value="${extInfo.personnelAttributeName}" type="hidden">
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>手机：</th>
					<td>
						<input type="text" name="userPhone" value="${user.userPhone}" class="validate[required,custom[mobile]]"/>
					</td>
					<th >
						<font color="red">*</font>邮箱：
					</th>
					<td>
						<input type="text" name="userEmail" value="${user.userEmail}" class="validate[required,custom[email]]"/>
					</td>
					<th >
						<font color="red">*</font>出生日期：</th>
					<td>
						<input name="userBirthday" value="${user.userBirthday}" type="text" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" class="validate[required]">
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>婚姻状况：
						<input type="hidden" name="maritalStatusName" id="maritalStatusName" value="${extInfo.maritalStatusName}">
					</th>
					<td>
						<select name="maritalStatus" id="maritalStatus" class="validate[required]">
							<option value="">请选择</option>
							<option value="1" ${extInfo.maritalStatus eq 1?"selected":"style=''"}>未婚</option>
							<option value="2" ${extInfo.maritalStatus eq 2?"selected":"style=''"}>已婚</option>
							<option value="3" ${extInfo.maritalStatus eq 3?"selected":"style=''"}>初婚</option>
							<option value="4" ${extInfo.maritalStatus eq 4?"selected":"style=''"}>再婚</option>
							<option value="5" ${extInfo.maritalStatus eq 5?"selected":"style=''"}>复婚</option>
							<option value="6" ${extInfo.maritalStatus eq 6?"selected":"style=''"}>丧偶</option>
							<option value="7" ${extInfo.maritalStatus eq 7?"selected":"style=''"}>离婚</option>
						</select>
					</td>
					<th>
						<font color="red">*</font>QQ号码：
					</th>
					<td>
						<input type="text" name="userQq" value="${user.userQq}" class="validate[required]" />
					</td>
					<th>
						<font color="red">*</font>计算机能力：
					</th>
					<td>
						<input type="text" name="computerSkills" value="${doctor.computerSkills}" class="validate[required]"/>
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>外语能力：
					</th>
					<td>
						<input type="text" name="foreignSkills" value="${doctor.foreignSkills}" class="validate[required]"/>
					</td>
					<th>
						<font color="red">*</font>通讯地址：
					</th>
					<td>
						<input type="text" name="userAddress" class="validate[required , maxSize[30]]" value="${user.userAddress}"/>
					</td>
					<th>
						<font color="red">*</font>户口所在地省行政区划：
						<input type="hidden" name="locationOfProvinceName" id="locationOfProvinceName" value="${extInfo.locationOfProvinceName}">
					</th>
					<td>
						<select name="locationOfProvince" id="locationOfProvince" class="validate[required]">
							<option value="">请选择</option>
							<option value="1" ${extInfo.locationOfProvince eq 1?"selected":"style=''"}>北京</option>
							<option value="2" ${extInfo.locationOfProvince eq 2?"selected":"style=''"}>天津</option>
							<option value="3" ${extInfo.locationOfProvince eq 3?"selected":"style=''"}>河北</option>
							<option value="4" ${extInfo.locationOfProvince eq 4?"selected":"style=''"}>山西</option>
							<option value="5" ${extInfo.locationOfProvince eq 5?"selected":"style=''"}>内蒙古</option>
							<option value="6" ${extInfo.locationOfProvince eq 6?"selected":"style=''"}>辽宁</option>
							<option value="7" ${extInfo.locationOfProvince eq 7?"selected":"style=''"}>吉林</option>
							<option value="8" ${extInfo.locationOfProvince eq 8?"selected":"style=''"}>黑龙江</option>
							<option value="9" ${extInfo.locationOfProvince eq 9?"selected":"style=''"}>上海</option>
							<option value="10" ${extInfo.locationOfProvince eq 10?"selected":"style=''"}>江苏</option>
							<option value="11" ${extInfo.locationOfProvince eq 11?"selected":"style=''"}>浙江</option>
							<option value="12" ${extInfo.locationOfProvince eq 12?"selected":"style=''"}>安徽</option>
							<option value="13" ${extInfo.locationOfProvince eq 13?"selected":"style=''"}>福建</option>
							<option value="14" ${extInfo.locationOfProvince eq 14?"selected":"style=''"}>江西</option>
							<option value="15" ${extInfo.locationOfProvince eq 15?"selected":"style=''"}>山东</option>
							<option value="16" ${extInfo.locationOfProvince eq 16?"selected":"style=''"}>河南</option>
							<option value="17" ${extInfo.locationOfProvince eq 17?"selected":"style=''"}>湖北</option>
							<option value="18" ${extInfo.locationOfProvince eq 18?"selected":"style=''"}>湖南</option>
							<option value="19" ${extInfo.locationOfProvince eq 19?"selected":"style=''"}>广东</option>
							<option value="20" ${extInfo.locationOfProvince eq 20?"selected":"style=''"}>广西</option>
							<option value="21" ${extInfo.locationOfProvince eq 21?"selected":"style=''"}>海南</option>
							<option value="22" ${extInfo.locationOfProvince eq 22?"selected":"style=''"}>重庆</option>
							<option value="23" ${extInfo.locationOfProvince eq 23?"selected":"style=''"}>四川</option>
							<option value="24" ${extInfo.locationOfProvince eq 24?"selected":"style=''"}>贵州</option>
							<option value="25" ${extInfo.locationOfProvince eq 25?"selected":"style=''"}>云南</option>
							<option value="26" ${extInfo.locationOfProvince eq 26?"selected":"style=''"}>西藏</option>
							<option value="27" ${extInfo.locationOfProvince eq 27?"selected":"style=''"}>陕西</option>
							<option value="28" ${extInfo.locationOfProvince eq 28?"selected":"style=''"}>甘肃</option>
							<option value="29" ${extInfo.locationOfProvince eq 29?"selected":"style=''"}>青海</option>
							<option value="30" ${extInfo.locationOfProvince eq 30?"selected":"style=''"}>宁夏</option>
							<option value="31" ${extInfo.locationOfProvince eq 31?"selected":"style=''"}>新疆</option>
							<option value="32" ${extInfo.locationOfProvince eq 32?"selected":"style=''"}>兵团</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>紧急联系人：
					</th>
					<td>
						<input type="text" name="emergencyName" value="${doctor.emergencyName}" class="validate[required]"/>
					</td>
					<th>
						<font color="red">*</font>紧急联系方式：
					</th>
					<td>
						<input type="text" name="emergencyPhone" value="${doctor.emergencyPhone}" class="validate[required]"/>
					</td>
					<th>
						<font color="red">*</font>与本人关系：
					</th>
					<td>
						<input type="text" name="emergencyRelation" value="${doctor.emergencyRelation}" class="validate[required]"/>
					</td>
				</tr>
			</table>
			<table style="width:100%;">
				<caption>教育情况</caption>
				<colgroup>
					<col width="12%"/>
					<col width="16%"/>
					<col width="14%"/>
					<col width="16%"/>
					<col width="14%"/>
					<col width="16%"/>
					<col width="10%"/>
				</colgroup>
				<tr>
					<th><font color="red">*</font>是否为应届生：</th>
					<td>
						<label><input type="radio" name="isYearGraduate" class="validate[required]" <c:if test="${doctor.isYearGraduate eq 'Y'}">checked="checked"</c:if>  value="Y" />是</label>&#12288;
						<label><input type="radio" name="isYearGraduate" class="validate[required]" <c:if test="${doctor.isYearGraduate eq 'N'}">checked="checked"</c:if> value="N" />否</label>
					</td>
					<th><font color="red">*</font>是否全科定向学员：</th>
					<td>
						<label><input type="radio" name="isGeneralOrderOrientationTrainee" class="validate[required]" <c:if test="${extInfo.isGeneralOrderOrientationTrainee eq 'Y'}">checked="checked"</c:if>  value="Y" />是</label>&#12288;
						<label><input type="radio" name="isGeneralOrderOrientationTrainee" class="validate[required]" <c:if test="${extInfo.isGeneralOrderOrientationTrainee eq 'N'}">checked="checked"</c:if> value="N" />否</label>
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>是否为专科：
					</th>
					<td>
						<label><input type="radio" name="isCollegeDegree" class="validate[required]" <c:if test="${extInfo.isCollegeDegree eq 'Y'}">checked="checked"</c:if> value="Y" onchange="isCollege(this.value)"/>是</label>&#12288;
						<label><input type="radio" name="isCollegeDegree" class="validate[required]" <c:if test="${extInfo.isCollegeDegree eq 'N'}">checked="checked"</c:if> value="N" onchange="isCollege(this.value)"/>否</label>
					</td>
					<th class="collegeDegreeContent">
						<font color="red">*</font>专科毕业院校：
					</th>
					<td class="collegeDegreeContent">
						<input name="collegeName" value="${extInfo.collegeName}" type="text" class="validate[required]">
					</td>
					<th class="collegeDegreeContent">
						<font color="red">*</font>专科毕业时间：
					</th>
					<td class="collegeDegreeContent">
						<input name="collegeGraduationTime" value="${extInfo.collegeGraduationTime}" type="text" readonly="readonly" class="validate[required]" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});">
					</td>
				</tr>
				<tr class="collegeDegreeContent">
					<th>
						<font color="red">*</font>专科毕业专业：
					</th>
					<td>
						<input name="collegeGraduationSpe" value="${extInfo.collegeGraduationSpe}" type="text" class="validate[required]">
					</td>
					<th>
						<font color="red">*</font>专科学位：
					</th>
					<td>
						<input name="collegeDegree" value="${extInfo.collegeDegree}" type="text" class="validate[required]">
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>是否为本科：
					</th>
					<td>
						<label><input type="radio" name="isUndergraduateDegree" class="validate[required]" <c:if test="${extInfo.isUndergraduateDegree eq 'Y'}">checked="checked"</c:if> value="Y" onchange="checkIsUndergraduateDegree(this.value)"/>是</label>&#12288;
						<label><input type="radio" name="isUndergraduateDegree" class="validate[required]" <c:if test="${extInfo.isUndergraduateDegree eq 'N'}">checked="checked"</c:if> value="N" onchange="checkIsUndergraduateDegree(this.value)"/>否</label>
					</td>
					<th class="undergraduateDegreeContent">
						<font color="red">*</font>本科毕业院校：
					</th>
					<td class="undergraduateDegreeContent">
						<input name="graduatedName" value="${extInfo.graduatedName}" type="text" class="validate[required]">
					</td>
					<th class="undergraduateDegreeContent">
						<font color="red">*</font>本科毕业时间：
					</th>
					<td class="undergraduateDegreeContent">
						<input name="graduationTime" value="${extInfo.graduationTime}" type="text" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" class="validate[required]">
					</td>
				<tr class="undergraduateDegreeContent">
					<th>
						<font color="red">*</font>本科毕业专业：
					</th>
					<td>
						<%--<input id="zbkbySpe" name="zbkbySpe" value="${extInfo.zbkbySpe}" type="text" class="validate[required]">--%>
						<select name="zbkbySpe" class="validate[required]">
							<option value="">请选择</option>
							<option ${extInfo.zbkbySpe eq '临床医学'?'selected':''}>临床医学</option>
							<option ${extInfo.zbkbySpe eq '口腔医学'?'selected':''}>口腔医学</option>
							<option ${extInfo.zbkbySpe eq '医学影像学'?'selected':''}>医学影像学</option>
							<option ${extInfo.zbkbySpe eq '麻醉学'?'selected':''}>麻醉学</option>
							<option ${extInfo.zbkbySpe eq '其他'?'selected':''}>其他</option>
						</select>
					</td>
					<th>
						<font color="red">*</font>本科学位：
						<input type="hidden" name="undergraduateDegreeName" id="undergraduateDegreeName" value="${extInfo.undergraduateDegreeName}">
					</th>
					<td>
						<select name="undergraduateDegreeId" id="undergraduateDegreeId" class="validate[required]">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
								<option value="${dict.dictId}" <c:if test="${dict.dictId eq extInfo.undergraduateDegreeId}">selected</c:if>>${dict.dictName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<script>
					$(function(){
						var isMaster = '${extInfo.isMaster}';
						checkIsMaster(isMaster);
						var isDoctor = '${extInfo.isDoctor}';
						checkIsDoctor(isDoctor);
					})
					function checkIsMaster(value){
						if(value=='Y'){
							$(".masterTh1").show();
							$(".masterTh2").show();
							$(".masterTd1").show();
							$(".masterTd2").show();
							$(".masterTr").show();
						}else{
							$(".masterTh1").hide();
							$(".masterTh2").hide();
							$(".masterTd1").hide();
							$(".masterTd2").hide();
							$(".masterTr").hide();
						}
					}
					function checkIsDoctor(value){
						if(value=='Y'){
							$(".doctorTh1").show();
							$(".doctorTh2").show();
							$(".doctorTd1").show();
							$(".doctorTd2").show();
							$(".doctorTr").show();
						}else{
							$(".doctorTh1").hide();
							$(".doctorTh2").hide();
							$(".doctorTd1").hide();
							$(".doctorTd2").hide();
							$(".doctorTr").hide();
						}
					}
				</script>
				<tr>
					<th><font color="red">*</font>是否为硕士：</th>
					<td>
						<label><input type="radio" name="isMaster" class="validate[required]" <c:if test="${extInfo.isMaster eq 'Y'}">checked="checked"</c:if> value="Y" onchange="checkIsMaster(this.value)"/>是</label>&#12288;
						<label><input type="radio" name="isMaster" class="validate[required]" <c:if test="${extInfo.isMaster eq 'N'}">checked="checked"</c:if> value="N" onchange="checkIsMaster(this.value)"/>否</label>
					</td>
					<th class="masterTh1"><font color="red">*</font>硕士毕业院校：</th>
					<td class="masterTd1">
						<input name="masterGraSchoolName" value="${extInfo.masterGraSchoolName}" type="text" class="validate[required]">
					</td>
					<th class="masterTh2"><font color="red">*</font>硕士毕业时间：</th>
					<td class="masterTd2">
						<input name="masterGraTime" value="${extInfo.masterGraTime}" type="text" class="validate[required]" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});">

					</td>
				</tr>
				<tr class="masterTr">
					<th><font color="red">*</font>硕士毕业专业：</th>
					<td>
						<input name="masterMajor" value="${extInfo.masterMajor}" type="text" class="validate[required]">

					</td>
					<th><font color="red">*</font>硕士学位：</th>
					<td>
						<input id="masterDegreeName" name="masterDegreeName" hidden value="${extInfo.masterDegreeName}" type="text">
						<select name="masterDegreeId" onchange="setNameById('masterDegreeName',this.value)" class="select validate[required] master" >
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
								<option value="${dict.dictId}" <c:if test="${ dict.dictId eq extInfo.masterDegreeId}">selected</c:if>>${dict.dictName}</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<font color="red">*</font>学位类型：</th>
					<td>
						<select name="masterDegreeTypeId" id="masterDegreeTypeId" class="validate[required]">
							<option value="1"
									<c:if test="${extInfo.masterDegreeTypeId eq '1'}">selected</c:if>
							>专业型</option>
							<option  value="2"
									 <c:if test="${extInfo.masterDegreeTypeId eq '2'}">selected</c:if>
							>科学型</option>
						</select>
						<input type="hidden" id="masterDegreeTypeName" name="masterDegreeTypeName" value="${extInfo.masterDegreeTypeName}">
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>是否为博士：</th>
					<td>
						<label><input type="radio" name="isDoctor" class="validate[required]" <c:if test="${extInfo.isDoctor eq 'Y'}">checked="checked"</c:if>  value="Y" onchange="checkIsDoctor(this.value)"/>是</label>&#12288;
						<label><input type="radio" name="isDoctor" class="validate[required]" <c:if test="${extInfo.isDoctor eq 'N'}">checked="checked"</c:if> value="N" onchange="checkIsDoctor(this.value)"/>否</label>

					</td>
					<th class="doctorTh1"><font color="red">*</font>博士毕业院校：</th>
					<td class="doctorTd1">
						<input name="doctorGraSchoolName" value="${extInfo.doctorGraSchoolName}" type="text" class="validate[required]">

					</td>
					<th class="doctorTh2"><font color="red">*</font>博士毕业时间：</th>
					<td class="doctorTd2">
						<input name="doctorGraTime" value="${extInfo.doctorGraTime}" type="text" class="validate[required]" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});">

					</td>
				</tr>
				<tr class="doctorTr">
					<th><font color="red">*</font>博士毕业专业：</th>
					<td>
						<input name="doctorMajor" value="${extInfo.doctorMajor}" type="text" class="validate[required]">

					</td>
					<th><font color="red">*</font>博士学位：</th>
					<td>
						<select id="doctorDegreeId" name="doctorDegreeId" onchange="setNameById('doctorDegreeName',this.value)" class="validate[required]" >
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
								<option value="${dict.dictId}" <c:if test="${ dict.dictId eq extInfo.doctorDegreeId }">selected</c:if>>${dict.dictName}</option>
							</c:forEach>
						</select>
						<input id="doctorDegreeName" name="doctorDegreeName" hidden value="${extInfo.doctorDegreeName}" type="text">
					</td>
					<th><font color="red">*</font>学位类型：</th>
					<td>
						<select name="doctorDegreeType" id="doctorDegreeType" class="validate[required]">
							<option value="1"
									<c:if test="${extInfo.doctorDegreeType eq '1'}">selected</c:if>
							>专业型</option>
							<option value="2"
									<c:if test="${extInfo.doctorDegreeType eq '2'}">selected</c:if>
							>科学型</option>
						</select>
						<input type="hidden" id="doctorDegreeTypeName" name="doctorDegreeTypeName" value="${extInfo.doctorDegreeTypeName}">
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>最高学历：</th>
					<td>
						<select name="educationId" class="validate[required] ">
							<option value="">请选择</option>
							<c:forEach var="dict" items="${dictTypeEnumUserEducationList }">
								<option value="${dict.dictId }" <c:if test="${user.educationId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
							</c:forEach>
						</select>
					</td>
					<th><font color="red">*</font>最高学位：</th>
					<td>
						<input id="degreeName" name="degreeName" hidden value="${user.degreeName}" type="text">
						<select id="degreeId" name="degreeId" onchange="setNameById('degreeName',this.value)"  class="validate[required]" >
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
								<option value="${dict.dictId}" <c:if test="${ dict.dictId eq user.degreeId }">selected</c:if>>${dict.dictName}</option>
							</c:forEach>
						</select>
					</td>
					<th><font color="red">*</font>最高学历毕业专业：</th>
					<td>
						<select name="specialized" class="validate[required]">
							<option></option>
							<c:forEach items="${dictTypeEnumGraduateMajorList}" var="dict">
								<option value="${dict.dictId}" ${doctor.specialized eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th><font color="red">*</font>最高毕业证书：</th>
					<td>
					<span id="certificateUriFileSpan" style="display:${!empty extInfo.certificateUri?'':'none'} ">
	              	[<a href="${sysCfgMap['upload_base_url']}/${extInfo.certificateUri}" target="_blank">查看图片</a>]
	            	</span>
						<a id="certificateUriFile" href="javascript:uploadFile('certificateUriFile','毕业证书');" style="color: blue"class="${empty extInfo.certificateUri?'validate[required]':''}">${empty extInfo.certificateUri?'':'重新'}上传</a>
						<a id="certificateUriFileDel" href="javascript:delFile('certificateUriFile');" class="" style="color: blue;${empty extInfo.certificateUri?'display:none':''}">删除</a>
						<input type="hidden" id="certificateUriFileValue" name="certificateUri" value="${extInfo.certificateUri }">
					</td>
					<th><font color="red">*</font>最高毕业证书编号：</th>
					<td>
						<input name="certificateCode" value="${extInfo.certificateCode}" type="text" class="validate[required]">
					</td>
					<th><font color="red">*</font>最高学位证书：</th>
					<td>
					<span id="degreeUriFileSpan" style="display:${!empty extInfo.degreeUri?'':'none'} ">
	              	[<a href="${sysCfgMap['upload_base_url']}/${extInfo.degreeUri}" target="_blank">查看图片</a>]
	            	</span>
						<a id="degreeUriFile" href="javascript:uploadFile('degreeUriFile','学位证书');" style="color: blue" class="${empty extInfo.degreeUri?'validate[required]':''}">${empty extInfo.degreeUri?'':'重新'}上传</a>
						<a id="degreeUriFileDel" href="javascript:delFile('degreeUriFile');" class="" style="color: blue;${empty extInfo.degreeUri?'display:none':''}">删除</a>
						<input type="hidden" id="degreeUriFileValue" name="degreeUri" value="${extInfo.degreeUri }">
					</td>
				</tr>
				<tr>
					<th><font color="red">*</font>最高学位证书编号：</th>
					<td>
						<input type="text" name="degreeCode" value="${extInfo.degreeCode}" class="validate[required]" >
					</td>
				</tr>
			</table>
			<table style="width:100%;">
				<caption>医师资格信息</caption>
				<colgroup>
					<col width="12%"/>
					<col width="16%"/>
					<col width="14%"/>
					<col width="16%"/>
					<col width="14%"/>
					<col width="16%"/>
					<col width="10%"/>
				</colgroup>
				<tr class="cHeight">
					<th></th><td></td><th></th><td></td><th></th><td></td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>是否通过医师资格考试：</th>
					<td>
						<label><input type="radio" name="isPassQualifyingExamination" <c:if test="${extInfo.isPassQualifyingExamination eq 'Y'}">checked="checked"</c:if>  value="Y"  class="validate[required]" onchange="showTime('Y')" />是</label>&#12288;
						<label><input type="radio" name="isPassQualifyingExamination" <c:if test="${extInfo.isPassQualifyingExamination eq 'N'}">checked="checked"</c:if> value="N"  class="validate[required]" onchange="showTime('N')" />否</label>
					</td>
					<th class="examinationTime">
						<font color="red">*</font>通过医师资格考试时间：
					</th>
					<td class="examinationTime" style="display: none">
						<input name="passQualifyingExaminationTime" value="${extInfo.passQualifyingExaminationTime}" type="text"
							class="validate[required]" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});">
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>是否获得医师资格证书：</th>
					<td>
						<label><input type="radio" name="doctorLicenseFlag" <c:if test="${doctor.doctorLicenseFlag eq 'Y'}">checked="checked"</c:if>  value="Y"  class="validate[required]" onchange="examsTime('Y');"/>是</label>&#12288;
						<label><input type="radio" name="doctorLicenseFlag" <c:if test="${doctor.doctorLicenseFlag eq 'N'}">checked="checked"</c:if> value="N"  class="validate[required]" onchange="examsTime('N')"/>否</label>
					</td>
					<th style="display: none" class="examTime">
						<font color="red">*</font>取得医师资格证书时间：
					</th>
					<td style="display: none" class="examTime">
						<input id="haveQualificationCertificateTime" name="haveQualificationCertificateTime" value="${extInfo.haveQualificationCertificateTime}"
							   type="text" class="validate[required]" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});">
					</td>
					<th style="display: none" class="examTime">
						<font color="red">*</font>医师资格级别：
					</th>
					<td style="display: none" class="examTime">
						<select name="physicianQualificationLevel" class="validate[required]">
							<option value="">请选择</option>
							<option value="zyys" ${extInfo.physicianQualificationLevel eq 'zyys'?'selected':''}>执业医师</option>
							<option value="zyzlys" ${extInfo.physicianQualificationLevel eq 'zyzlys'?'selected':''}>执业助理医师</option>
						</select>
					</td>
				</tr>
				<tr style="display: none" class="examTime">
					<th>
						<font color="red">*</font>医师资格类别：
						<input type="hidden" name="physicianQualificationClassName" id="physicianQualificationClassName" value="${extInfo.physicianQualificationClassName}">
					</th>
					<td>
						<select name="physicianQualificationClass" id="physicianQualificationClass" class="validate[required]">
							<option value="">请选择</option>
							<option value="lc" ${extInfo.physicianQualificationClass eq 'lc'?'selected':''}>临床</option>
							<option value="kq" ${extInfo.physicianQualificationClass eq 'kq'?'selected':''}>口腔</option>
							<option value="ggws" ${extInfo.physicianQualificationClass eq 'ggws'?'selected':''}>公共卫生</option>
							<option value="zy" ${extInfo.physicianQualificationClass eq 'zy'?'selected':''}>中医</option>
						</select>
					</td>
					<th>
						<font color="red">*</font>医师资格证书编码：
					</th>
					<td>
						<input type="text" name="doctorLicenseNo" value="${doctor.doctorLicenseNo}" class="validate[custom[integer],required]">
					</td>
					<th><font color="red">*</font>医师资格证上传：</th>
					<td>
					<span id="qualifiedFileFileSpan" style="display:${!empty doctor.qualifiedFile?'':'none'} ">
	              	[<a href="${sysCfgMap['upload_base_url']}/${doctor.qualifiedFile}" target="_blank">查看图片</a>]
	            	</span>
						<a id="qualifiedFileFile" href="javascript:uploadFile('qualifiedFileFile','执业医师资格证书');" style="color: blue"class="${empty doctor.qualifiedFile?'validate[required]':''}">${empty doctor.qualifiedFile?'':'重新'}上传</a>
						<a id="qualifiedFileFileDel" href="javascript:delFile('qualifiedFileFile');" class="" style="color: blue;${empty doctor.qualifiedFile?'display:none':''}">删除</a>
						<input type="hidden" id="qualifiedFileFileValue" name="qualifiedFile" value="${doctor.qualifiedFile}">
					</td>
				</tr>
				<script>
					$(function(){
						checkDoctorType('${doctor.doctorTypeId}');
						$(".workOrgNameTh,.workOrgNameTd").hide();
						checkDoctorType2('${doctor.doctorTypeId}');
					})
					function checkDoctorType(value) {
						if (value == 'Company'||value == 'CompanyEntrust'||value == 'ExternalEntrust') {
							$(".workOrgNameTh,.workOrgNameTd,.medicalInstitutionTh,.medicalInstitutionTd,.prove").show();
							changeMedicalThings($("#medicalHeaithOrgId option:selected").val());
						}else{
							$(".workOrgNameTh,.workOrgNameTd,.medicalInstitutionTh,.medicalInstitutionTd,.prove").hide();
							changeMedicalThings("");
						}

						var doctorCategoryId = $("#doctorCategoryId").val();
						if("${GlobalConstant.FLAG_N}"!=isSch[doctorCategoryId])
						{
							if (value == 'Company') {
								$(".departMentFlow").show();
							}else{
								$(".departMentFlow").hide();
								$("#departMentFlow").val("");
							}
						}else{
							$(".departMentFlow").hide();
							$("#departMentFlow").val("");
						}
					}
					function checkDoctorType2(value){
						if(value=='Graduate') {
							$(".workOrgNameTh").html("<font color='red'>*</font>在读院校：");
							$(".workOrgNameTh,.workOrgNameTd").show();
						}
						else if(value=='Company'||value == 'CompanyEntrust'||value == 'ExternalEntrust') {
							$(".workOrgNameTh").html("<font color='red'>*</font>工作单位：");
							$(".workOrgNameTh,.workOrgNameTd").show();
						}else{
							$(".workOrgNameTh,.workOrgNameTd").hide();
						}
					}
					function changeMedicalThings(name){
						if(name=='3'){
							$(".hospitalTr").hide();
							$(".basicmedicalInstitutionTypeTh").show();
							$(".basicmedicalInstitutionTypeTd").show();
						}else if(name=='1'){
							$(".hospitalTr").show();
							$(".basicmedicalInstitutionTypeTh").hide();
							$(".basicmedicalInstitutionTypeTd").hide();
						}else{
							$(".hospitalTr").hide();
							$(".basicmedicalInstitutionTypeTh").hide();
							$(".basicmedicalInstitutionTypeTd").hide();
						}
					}
				</script>
				<tr>
					<th><font color="red">*</font>是否获得医师执业证书：</th>
					<td>
						<label><input type="radio" name="practPhysicianFlag" <c:if test="${doctor.practPhysicianFlag eq 'Y'}">checked="checked"</c:if>  value="Y"  class="validate[required]" onchange="showTime2('Y')"/>是</label>&#12288;
						<label><input type="radio" name="practPhysicianFlag" <c:if test="${doctor.practPhysicianFlag eq 'N'}">checked="checked"</c:if> value="N"  class="validate[required]" onchange="showTime2('N')"/>否</label>
					</td>
					<th class="showTime2" style="display: none;">
						<font color="red">*</font>取得医师执业证书时间：
					</th>

					<td class="showTime2" style="display: none;">
						<input id="havePracticingCategoryTime" name="havePracticingCategoryTime" value="${extInfo.havePracticingCategoryTime}"
							   type="text" class="validate[required]" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});">
					</td>
					<th class="showTime2" style="display: none;">
						<font color="red">*</font>医师执业证书编码：
					</th>
					<td class="showTime2" style="display: none;">
						<input type="text" name="practPhysicianCertificateNo" value="${doctor.practPhysicianCertificateNo}" class="validate[custom[integer],required]" >
					</td>
				</tr>
                <tr class="showTime2" style="display: none;">
					<th><font color="red">*</font>医师执业证书：</th>
					<td>
					<span id="doctorPracticingCategoryUrlFileSpan" style="display:${!empty extInfo.doctorPracticingCategoryUrl?'':'none'} ">
	              	[<a href="${sysCfgMap['upload_base_url']}/${extInfo.doctorPracticingCategoryUrl}" target="_blank">查看图片</a>]
	            	</span>
						<a id="doctorPracticingCategoryUrlFile" href="javascript:uploadFile('doctorPracticingCategoryUrlFile','医师执业证书');" style="color: blue"class="${empty extInfo.doctorPracticingCategoryUrl?'validate[required]':''}">${empty extInfo.doctorPracticingCategoryUrl?'':'重新'}上传</a>
						<a id="doctorPracticingCategoryUrlFileDel" href="javascript:delFile('doctorPracticingCategoryUrlFile');" class="" style="color: blue;${empty extInfo.doctorPracticingCategoryUrl?'display:none':''}">删除</a>
						<input type="hidden" id="doctorPracticingCategoryUrlFileValue" name="doctorPracticingCategoryUrl" value="${extInfo.doctorPracticingCategoryUrl}">
					</td>
                    <th><font color="red">*</font>执业类型：</th>
					<input type="hidden" name="practicingCategoryName" id="practicingCategoryName" value="${extInfo.practicingCategoryName}">
                    <td>
                        <select id="practicingCategoryId" name="practicingCategoryId" class="validate[required]"onchange="changeCategoryId(this.value)">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumPracticeTypeList}" var="dictTypeEnumPracticeType">
                                <option value="${dictTypeEnumPracticeType.dictId}"	 <c:if test='${extInfo.practicingCategoryId==dictTypeEnumPracticeType.dictId}'>selected</c:if>>
                                        ${dictTypeEnumPracticeType.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <th><font color="red">*</font>执业范围：</th>
					<input type="hidden" value="${extInfo.practicingScopeName}" name="practicingScopeName" id="practicingScopeName">
                    <td>
                        <select id="practicingScopeId" name="practicingScopeId" class="validate[required]">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumPracticeTypeList}" var="dict">
                                <c:set var="dictKey" value="dictTypeEnumPracticeType.${dict.dictId}List"/>
                                <c:forEach items="${applicationScope[dictKey]}" var="scope">
                                    <option class="${scope.dictTypeId}" value="${scope.dictId}" <c:if test='${extInfo.practicingScopeId==scope.dictId and dict.dictId == extInfo.practicingCategoryId}'>selected</c:if>>
                                            ${scope.dictName}</option>
                                </c:forEach>
                            </c:forEach>
                        </select>
                    </td>
                    <script>
                        function changeCategoryId(dictId){
							$('#practicingScopeId').val("");
                            $('#practicingScopeId option').hide();
                            $('#practicingScopeId option[value=""]').show();
                            $('#practicingScopeId'+' option.${dictTypeEnumPracticeType.id}\\.'+dictId).show();
                        }
						$(function(){
							var practicingCategoryId = '${extInfo.practicingCategoryId}';
							var practicingScopeId = '${extInfo.practicingScopeId}';
							if(!practicingScopeId){
								$('#practicingScopeId').val("");
							}
							$('#practicingScopeId option').hide();
							$('#practicingScopeId option[value=""]').show();
							$('#practicingScopeId'+' option.${dictTypeEnumPracticeType.id}\\.'+practicingCategoryId).show();
						});
                    </script>
                </tr>
			</table>
			<table style="width: 100%">
				<caption>西部支援情况</caption>
				<colgroup>
					<col width="12%"/>
					<col width="16%"/>
					<col width="14%"/>
					<col width="16%"/>
					<col width="14%"/>
					<col width="16%"/>
					<col width="10%"/>
				</colgroup>
				<tr class="cHeight">
					<th></th><td></td><th></th><td></td><th></th><td></td>
				</tr>
				<tr>
					<th><font color="red">*</font>否为西部支援行动&#12288;<br>住院医师：</th>
					<td>
						<label><input type="radio" name="isAssistance" <c:if test="${extInfo.isAssistance eq 'Y'}">checked="checked"</c:if>  value="Y"  class="validate[required]" onchange="showId3(this.value);"/>是</label>&#12288;
						<label><input type="radio" name="isAssistance" <c:if test="${extInfo.isAssistance eq 'N'}">checked="checked"</c:if> value="N"  class="validate[required]" onchange="showId3(this.value);"/>否</label>
					</td>
					<th class="isAssistance"><font color="red">*</font>西部支援行动住院&#12288;<br>医师送出省份：</th>
					<td class="isAssistance"><input type="text" name="assistanceProvince" value="${extInfo.assistanceProvince}" class="validate[required]" ></td>
					<th class="isAssistance"><font color="red">*</font>西部支援行动住院&#12288;<br>医师送出单位：</th>
					<td class="isAssistance"><input type="text" name="westernSupportResidentsSendWorkUnit" value="${extInfo.westernSupportResidentsSendWorkUnit}" class="validate[required]" ></td>
				</tr>
				<tr class="isAssistance">
					<th><font color="red">*</font>西部支援行动住院&#12288;<br>医师送出单位是否军队医院：</th>
					<td>
						<label><input type="radio" name="isMilitary" <c:if test="${extInfo.isMilitary eq 'Y'}">checked="checked"</c:if> value="Y"  class="validate[required]" onchange="showAssistanceCode('Y')"/>是</label>&#12288;
						<label><input type="radio" name="isMilitary" <c:if test="${extInfo.isMilitary eq 'N'}">checked="checked"</c:if> value="N"  class="validate[required]" onchange="showAssistanceCode('N')"/>否</label>
					</td>
					<th class="assistanceCode" style="display: none;"><font color="red">*</font>西部支援行动住院&#12288;<br>医师送出单位统一&#12288;<br>社会信用代码：</th>
					<td class="assistanceCode" style="display: none;">
						<input type="text" name="assistanceCode" value="${extInfo.assistanceCode}" class="validate[required]">
					</td>
				</tr>
			</table>
			<div style="display:none">
		<table>
			<input type="hidden" id="technologyQualificationName" name="technologyQualificationName" value="${extInfo.technologyQualificationName}"/>
			<input type="hidden" id="qualificationMaterialName" name="qualificationMaterialName" value="${extInfo.qualificationMaterialName}"/>
			<!-- 最高学历相关信息-->
			<input type="hidden" id="uEducationName" name="user.educationName" value="${user.educationName}"/>
			<input type="hidden" id="dGraduatedName" name="doctor.graduatedName" value="${doctor.graduatedName}"/>
			<input type="hidden" id="dGraduationTime" name="doctor.graduationTime" value="${doctor.graduationTime}"/>
			<th>最高毕业证书编号：</th>
			<td ><input name="doctor.certificateNo" value="${doctor.certificateNo}" class="input validate[]"/></td>
			<td ></td>
			<th>最高毕业证书上传：</th>
			<td>
				<%--<input type="hidden" id="certificateUriValue" name="certificateUri" value="${extInfo.certificateUri}"/>--%>
			</td>
			</tr>
		</table>
		<table border="0" align="center" cellpadding="0" cellspacing="0" class="base_info">
			<colgroup>
				<col width="14%"></col>
				<col width="36%"></col>
				<col width="14%"></col>
				<col width="36%"></col>
			</colgroup>
			<tbody>
			<tr>
				<th>专业技术资格：</th>
				<td style="padding-left:10px;">
					<select id="technologyQualificationId" name="technologyQualificationId" class="select" style="width: 160px;">
						<option value="">请选择</option>
						<option value="171" ${extInfo.technologyQualificationId eq '171'?'selected':''}>住院医师</option>
						<option value="172" ${extInfo.technologyQualificationId eq '172'?'selected':''}>主治医师</option>
						<option value="173" ${extInfo.technologyQualificationId eq '173'?'selected':''}>住院中医师</option>
						<option value="174" ${extInfo.technologyQualificationId eq '174'?'selected':''}>主治中医师</option>
						<option value="196" ${extInfo.technologyQualificationId eq '196'?'selected':''}>无</option>
					</select>
				</td>
				<th>取得日期：</th>
				<td colspan="2">
					<input name="getTechnologyQualificationDate" value="${extInfo.getTechnologyQualificationDate}" class="input datepicker" style="width: 149px;" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<th>执业资格材料：</th>
				<td style="padding-left:10px;">
					<select id="qualificationMaterialId" name="qualificationMaterialId" class="select" style="width: 160px;">
						<option value="">请选择</option>
						<option value="176" ${extInfo.qualificationMaterialId eq '176'?'selected':''}>医师执业证书</option>
						<option value="177" ${extInfo.qualificationMaterialId eq '177'?'selected':''}>医师资格证书</option>
						<option value="178" ${extInfo.qualificationMaterialId eq '178'?'selected':''}>已通过国家执业医师考试的成绩单</option>
						<option value="200" ${extInfo.qualificationMaterialId eq '200'?'selected':''}>助理执业医师证书（定向大专）</option>
						<option value="201" ${extInfo.qualificationMaterialId eq '201'?'selected':''}>已通过国家助理执业医师考试的成绩单（定向大专）</option>
						<option value="202" ${extInfo.qualificationMaterialId eq '202'?'selected':''}>无</option>
					</select>
				</td>
				<th>资格材料编码：</th>
				<td colspan="2"><input name="qualificationMaterialCode" value="${extInfo.qualificationMaterialCode}" class="input"/></td>
			</tr>

			<tr>
				<th>资格证书上传：</th>
				<td colspan="3">
					<input type="hidden" id="qualificationMaterialUriValue" name="qualificationMaterialUri" value="${extInfo.qualificationMaterialUri}"/>
				</td>
			</tr>
			</tbody>
		</table>
	</div>
			<table id="rotationInfo" style="width:100%;">
				<caption>培训信息</caption>
				<colgroup>
					<col width="12%"/>
					<col width="16%"/>
					<col width="14%"/>
					<col width="16%"/>
					<col width="14%"/>
					<col width="16%"/>
					<col width="10%"/>
				</colgroup>
				<tr>
					<th>参培年份：</th>
					<td>
						<label id="sessionNumber">${doctor.sessionNumber}</label>
					</td>
					<th><span class="trainNameSpan trainExtralSpan">培养</span>年限：</th>
					<td>
						<label id="trainingYears">${doctor.trainingYears}</label>
					</td>
					<th><span class="trainNameSpan">培训</span>专业：</th>
					<td>
						<label>${doctor.trainingSpeName}</label>
					</td>
				</tr>
				<tr>
					<th><label class="rotationAttr">轮转方案：</label></th>
					<td>
						<label>${doctor.rotationName}</label>
					</td>
					<th><label>实际培训开始时间：</label></th>
					<td>
						<label>${doctor.inHosDate}</label>
					</td>
					<th>结业考核年份：</th>
					<td>
						 <label id="graduationYear">${doctor.graduationYear}</label>
					</td>
				</tr>
				<tr class="secondTr" style="display:none;">
					<th>二级专业：</th>
					<td>${doctor.secondSpeName}
					</td>
					<th>二级轮转方案：</th>
					<td colspan="3">${doctor.secondRotationName}
					</td>
				</tr>
				<tr>
					<th><span class="userNameSpan">医师</span>状态：</th>
					<td>
							<input name="doctorStatusId" type="hidden" value="${doctor.doctorStatusId}"/>
							<select name="" disabled="disabled">
								<option></option>
								<c:forEach var="dict" items="${dictTypeEnumDoctorStatusList}">
									<option value="${dict.dictId}" ${doctor.doctorStatusId eq dict.dictId?'selected':''}>${dict.dictName}</option>
								</c:forEach>
							</select>
					</td>
					<tr class="departMentFlow" style="display:none;">
						<th>
							<font color="red">*</font>所在科室：</th>
						<td>
							<select class="validate[required]" name="departMentFlow" id="departMentFlow"  >
								<option value="">请选择</option>
								<c:forEach items="${depts}" var="tra">
									<option value="${tra.deptFlow}" <c:if test="${doctor.departMentFlow eq tra.deptFlow}">selected="selected"</c:if>>${tra.deptName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<c:if test="${doctor.doctorCategoryId eq recDocCategoryEnumGraduate.id}">
						<th>专业方向：</th>
						<td>${doctor.standardDeptName}</td>
					</c:if>
				</tr>
				<tr>
					<td colspan="20">
						<div style="text-align: center">
							<input type="button" value="保&#12288;存" class="search" onclick="saveDoc();">
						</div>
					</td>
				</tr>
			</table>
</form>

			</div>
		</c:if>
		
		<!-- 教育经历 -->
		<div id="edu" style="padding-top: 10px;"></div>
		
		<!-- 工作经历 -->
		<div id="work" style="padding-top: 10px;"></div>
		
		<!-- 学会任职 -->
		<div id="academic" style="padding-top: 10px;"></div>
		
		<!-- 课题情况 -->
		<div id="proj" style="padding-top: 10px;"></div>
		
		<!-- 培训情况 -->
		<div id="train" style="padding-top: 10px;"></div>
		
		<!-- 论文 -->
		<div id="thesis" style="padding-top: 10px;"></div>
		<!-- 著作 -->
		<div id="book" style="padding-top: 10px;"></div>
		<!-- 专利 -->
		<div id="patent" style="padding-top: 10px;"></div>
		<!-- 论文 -->
		<div id="sat" style="padding-top: 10px;"></div>
		
	</div>
</div>
</div>
</body>
</html>