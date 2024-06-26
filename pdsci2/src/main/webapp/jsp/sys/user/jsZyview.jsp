
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
	if('${user.userHeadImg}'==""&&$("#asshole").val()!="Y"){
		jboxTip("请上传头像");
		return false;
	}
	getUserResumeExtName();
	getFantasticFour();
	getRenYuanShuXing();
	jboxConfirm("确认保存？",function(){
			var url = "<s:url value='/res/doc/user/saveDocSimple4jszy'/>";
		jboxPost( url,$('#resDoctor').serialize(), function(data) {
				if('${GlobalConstant.SAVE_SUCCESSED}'==data){
					window.location.reload(true);
				}
			},null,true);

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
	if('${doctor.graduationYear}'==""||'${doctor.graduationYear}'==null){
		calculation();
	}
	checkIsPartner("${doctor.isPartner}");
	showSecond("${doctor.doctorCategoryId}");
});
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
function getRenYuanShuXing(){
	var personnelAttributeName = $("#personnelAttributeId").find("option:selected").text();
	$("#personnelAttributeName").val(personnelAttributeName);
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
function changeSourceProvincesName(){
	var sname = $("#orgProvId").find("option:selected").text();
	$("#sourceProvincesName").val(sname);
}
</script>
<style>
	table{ margin:10px 0;border-collapse: collapse;}
	caption,th,td{height:40px;}
	caption{line-height:40px;text-align:left;font-weight:bold; padding-left:10px; border-bottom:1px solid #ddd;color:#f60;margin-bottom: 10px;}
	th{text-align:right;font-weight:500;padding-right:5px; color:#333;}
	td{text-align:left; padding-left:5px; }
	[type='text']{width:150px;height: 22px;}
	select{width: 153px;height: 27px;}
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
					<td style="text-align: left;padding-left: 30px;width: 25%">性&#12288;&#12288;别：${user.sexName }</td>
					<td style="text-align: left;padding-left: 30px;">登&ensp;录&ensp;名：${user.userCode}</td>
					<td style="text-align: left;;width:200px" rowspan="5">
						<div style="width: 110px;height: 130px;margin: 5px auto 0px;">
							<img src="${sysCfgMap['upload_base_url']}/${user.userHeadImg}" id="showImg" width="100%"
								 height="100%" onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
						</div>
						<div>
							<span style="cursor: pointer;float: right;">
								[<a onclick="$('#imageFile').click();">${empty user.userHeadImg?'上传头像':'重新上传'}</a>]
							</span>
							<input type="file" id="imageFile" name="headImg" style="display: none" onchange="uploadImage();"/>
						</div>
					</td>
				</tr>
				<tr>
					<td style="text-align: left;padding-left: 30px;width: 25%">出生日期：${user.userBirthday }</td>
					<td style="text-align: left;padding-left: 30px;">年&#12288;&#12288;龄：${pdfn:calculateAge(user.userBirthday)}岁</td>
					<td style="text-align: left;padding-left: 30px;">身份证号：${user.idNo}</td>
				</tr>
				<tr>
					<td style="text-align: left;padding-left: 30px;">学&#12288;&#12288;历：${user.educationName }</td>
					<td style="text-align: left;padding-left: 30px;">学&#12288;&#12288;位：${user.degreeName}</td>
					<td style="text-align: left;padding-left: 30px;">工作单位：${user.orgName }</td>
				</tr>
				<tr>
					<td style="text-align: left;padding-left: 30px;">部&#12288;&#12288;门：${user.deptName }<c:if test="${not empty moreDept}">&#12288;[${moreDept}]</c:if></td>
					<td style="text-align: left;padding-left: 30px;">职&#12288;&#12288;务：${user.postName }</td>
					<td style="text-align: left;padding-left: 30px;">职&#12288;&#12288;称：${user.titleName }</td>
				</tr>
				<tr>
					<td style="text-align: left;padding-left: 30px;">手&#12288;&#12288;机：${user.userPhone }</td>
					<td style="text-align: left;padding-left: 30px;">邮&#12288;&#12288;件：${user.userEmail }</td>
					<td style="text-align: left;padding-left: 30px;"></td>
				</tr>
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
	<%--<input type="hidden" name="emergencyRelation" value="${userResumeExt.emergencyRelation}" />--%>
	<%--<input type="hidden" name="emergencyAddress" value="${userResumeExt.emergencyAddress}" />--%>
			<table style="width:100%;">
				<caption>基本信息</caption>
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
						${doctor.doctorCategoryName}
						<input hidden name="doctorCategoryId" value="${doctor.doctorCategoryId}"/>
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
						<input type="text" name="userName" value="${user.userName}" class="validate[required]"/>
					</td>
					<td style="vertical-align: top;width:200px" rowspan="12">
						<div style="width: 110px;height: 130px;margin: 5px auto 0px;">
							<img src="${sysCfgMap['upload_base_url']}/${user.userHeadImg}" id="showImg" width="100%"
								 height="100%" onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
						</div>
						<div>
						<span style="cursor: pointer;float: right;">
						[<a onclick="$('#imageFile').click();">${empty user.userHeadImg?'上传头像':'重新上传'}</a>]
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
						<select name="cretTypeId" class="validate[required]">
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
						<input type="text" name="idNo" id="idNo" value="${user.idNo}" class="validate[required]">
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
					<th>
						<font color="red">*</font>是否取得执业资格证：</th>
					<td>
						<label><input type="radio" name="doctorLicenseFlag" <c:if test="${doctor.doctorLicenseFlag eq 'Y'}">checked="checked"</c:if>  value="Y"  class="validate[required]" onchange="showId(this.value);"/>是</label>&#12288;
						<label><input type="radio" name="doctorLicenseFlag" <c:if test="${doctor.doctorLicenseFlag eq 'N'}">checked="checked"</c:if> value="N"  class="validate[required]" onchange="showId(this.value);"/>否</label>
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>手机：</th>
					<td>
						<input type="text" name="userPhone" value="${user.userPhone}" class="validate[required,custom[mobile]]"/>
					</td>
					<th >
						<font color="red">*</font>邮箱：</th>
					<td>
						<input type="text" name="userEmail" value="${user.userEmail}" class="validate[required,custom[email]]"/>
					</td>
					<th class="qualifiedNoTh">
						<font color="red">*</font>执业医师资格证号：
					</th>
					<td class="qualifiedNoTd">
						<input type="text" name="doctorLicenseNo" value="${doctor.doctorLicenseNo}" class="validate[required]" >
					</td>
				</tr>
				<script>
					$(function(){
						checkDoctorType('${doctor.doctorTypeId}');
					})
					function checkDoctorType(value) {
						if (value == 'Company'|| value=='CompanyEntrust') {
							$(".companyType").show();//工作单位和单位人类型
							$(".medicalInstitution").show();//医疗卫生机构
							$(".graduateType").hide();//派送学校
							$("input[name='workOrgName']").eq(1).attr("disabled",true);//在读院校为不可用
							$("select[name='workOrgId']").val("");//在读院校不可用
							$("input[name='workOrgName']").eq(0).removeAttr("disabled");//工作单位为可用
							var name = $("#medicalHeaithOrgId").val();
							changeMedicalThings(name)
						}else if(value == 'Graduate'){
							$(".graduateType").show();//派送学校
							$(".companyType").hide();//工作单位和单位人类型
							$(".medicalInstitution").hide();//医疗卫生机构
							$(".hospitalAttr").hide();//医院性质相关
							$(".basicmedicalInstitutionType").hide();//基层医疗卫生机构相关
							$("input[name='workOrgName']").eq(0).attr("disabled",true);//工作单位为不可用
							$("input[name='workOrgName']").eq(1).removeAttr("disabled");//在读院校可用
							$("select[name='workOrgId']").removeAttr("disabled");//在读院校可用
						}else{
							$(".medicalInstitution").hide();//医疗卫生机构
							$(".companyType").hide();//工作单位和单位人类型
							$(".graduateType").hide();//派送学校
							$(".hospitalAttr").hide();//医院性质相关
							$(".basicmedicalInstitutionType").hide();//基层医疗卫生机构相关
						}
					}
				</script>
				<tr>
					<input type="hidden" name="sendCityId" value="${doctor.sendCityId}"/>
					<input type="hidden" name="sendCityName" value="${doctor.sendCityName}"/>
					<th>
						<font color="red">*</font>学员类型：</th>
					<td>
						<select name="doctorTypeId" class="validate[required]" id="doctorTypeId" onchange="checkDoctorType(this.value);">
							<option></option>
							<c:forEach items="${dictTypeEnumDoctorTypeList}" var="dict">
								<option value="${dict.dictId}" <c:if test="${doctor.doctorTypeId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName }</option>
							</c:forEach>
						</select>
					</td>
					<th class="companyType">
						<font color="red">*</font>工作单位：</th>
					<td class="companyType">
						<input name="workOrgName" value="${doctor.workOrgName}" class="companyType validate[required]" type="text">
					</td>
					<th class="graduateType">
						<font color="red">*</font>在读院校：</th>
					<td class="graduateType">
						<select name="workOrgId" id="workOrgId" class="<c:if test="${!addFlag}">validate[required]</c:if> workOrgNameTd1" onchange="selectWorkOrg();">
							<option></option>
							<c:forEach items="${dictTypeEnumSendSchoolList}" var="dict">
								<option value="${dict.dictId}" <c:if test="${doctor.workOrgId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName }</option>
							</c:forEach>
						</select>
						<input name="workOrgName" id="workOrgName" value="${doctor.workOrgName}" type="hidden" class="workOrgNameTd1">
						<%--<input name="workOrgName" value="${doctor.workOrgName}" class="graduateType validate[required]" type="text">--%>
					</td>
					<th>
						<font color="red">*</font>固定电话：
					</th>
					<td>
						<input name="telephone" value="${extInfo.telephone}" class="input validate[required,custom[phone2]]"/>
					</td>
				</tr>
				<tr>
					<script>
						$(function(){
							var name = "${extInfo.medicalHeaithOrgId}";
							changeMedicalThings(name);
						});
						function changeMedicalThings(name){
							if(name=='1'){
								$(".hospitalAttr").show();//医院性质相关
								$("select[name='hospitalAttrId']").eq(0).attr("disabled",true);//后一个医院性质置为不可用
								$("select[name='hospitalAttrId']").eq(1).removeAttr("disabled");//前一个医院性质置为可用
								$(".basicmedicalInstitutionType").hide();//基层医疗卫生机构相关
							}else if(name=='3'){
								$(".hospitalAttr").hide();//医院性质相关
								$("select[name='hospitalAttrId']").eq(1).attr("disabled",true);//前一个医院性质置为不可用
								$("select[name='hospitalAttrId']").eq(0).removeAttr("disabled");//后一个医院性质置为可用
								$(".basicmedicalInstitutionType").show();//基层医疗卫生机构相关
							}else{
								$(".hospitalAttr").hide();//医院性质相关
								$(".basicmedicalInstitutionType").hide();//基层医疗卫生机构相关
							}
						}
					</script>
					<th><span class="red">*</span>人员属性：</th>
					<td>
						<select id="personnelAttributeId" class="select validate[required]" name="personnelAttributeId" >
							<option value="">请选择</option>
							<c:forEach items="${personnelAttributeEnumList}" var="tra">
								<option value="${tra.id}" <c:if test="${extInfo.personnelAttributeId eq tra.id}">selected="selected"</c:if>>${tra.name}</option>
							</c:forEach>
						</select>
					</td>
					<th class="medicalInstitution ">
						<font color="red">*</font>医疗卫生机构：</th>
					<td class="medicalInstitution">
						<select class="validate[required]" id="medicalHeaithOrgId" name="medicalHeaithOrgId" onchange="changeMedicalThings(this.value);" >
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumMedicalHeaithOrgList}" var="tra">
							<option value="${tra.dictId}" <c:if test="${extInfo.medicalHeaithOrgId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
						</c:forEach>
					</td>
				</tr>
				<tr style="display: none" class="basicmedicalInstitutionType">
					<th>
						<font color="red">*</font>医院性质：</th>
					<td>
						<select class="validate[required]" name="hospitalAttrId">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumHospitalAttrList}" var="tra">
								<option value="${tra.dictId}" <c:if test="${extInfo.hospitalAttrId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<font color="red">*</font>基层医疗卫生机构：
					</th>
					<td>
						<select class="validate[required]" name="basicHealthOrgId" >
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumBasicHealthOrgList}" var="tra">
								<option value="${tra.dictId}" <c:if test="${extInfo.basicHealthOrgId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<font color="red">*</font>基层医疗卫生机构等级：
					</th>
					<td>
						<select class="validate[required]" name="basicHealthOrgLevelId" >
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumBasicHealthOrgLevelList}" var="tra">
								<option value="${tra.dictId}" <c:if test="${extInfo.basicHealthOrgLevelId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr style="display: none" class="hospitalAttr">
					<th>
						<font color="red">*</font>医院性质：</th>
					<td>
						<select class="validate[required]" name="hospitalAttrId">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumHospitalAttrList}" var="tra">
								<option value="${tra.dictId}" <c:if test="${extInfo.hospitalAttrId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<font color="red">*</font>医院类别：</th>
					<td>
						<select class="validate[required]" name="hospitalCategoryId">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumHospitalCategoryList}" var="tra">
								<option value="${tra.dictId}" <c:if test="${extInfo.hospitalCategoryId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<font color="red">*</font>单位等级：</th>
					<td>
						<select class="validate[required]" name="baseAttributeId">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumBaseAttributeList}" var="tra">
								<option value="${tra.dictId}" <c:if test="${extInfo.baseAttributeId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>是否是对口支援：</th>
					<td>
						<label><input onclick="checkIsPartner(this.value)" type="radio" name="isPartner" class="validate[required]" <c:if test="${doctor.isPartner eq GlobalConstant.FLAG_Y}">checked="checked"</c:if>  value="${GlobalConstant.FLAG_Y}" />是</label>&#12288;
						<label><input onclick="checkIsPartner(this.value)" type="radio" name="isPartner" class="validate[required]" <c:if test="${doctor.isPartner eq GlobalConstant.FLAG_N}">checked="checked"</c:if>  value="${GlobalConstant.FLAG_N}" />否</label>
					</td>
					<th style="display: none" class="sourceProvinces">
						<font color="red">*</font>生源省份：</th>
					<td style="display: none" class="sourceProvinces">
						<span id="provCityAreaId">
							<select id="orgProvId" name="sourceProvincesId" onchange="changeSourceProvincesName();" class="province validate[required]" data-value="${doctor.sourceProvincesId}" data-first-title="选择省"></select>
						</span>
					</td>
				</tr>
				<script type="text/javascript">
					// 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
					$.cxSelect.defaults.url = "<s:url value='/js/provCityAreaJson.min.json'/>";
					$.cxSelect.defaults.nodata = "none";

					$("#provCityAreaId").cxSelect({
//								selects : ["province", "city", "area"],
						selects : ["province"],
						nodata : "none",
						firstValue : ""
					});
					function checkIsPartner (item){
						if(item == 'Y'){
							$(".sourceProvinces").show();
						}else {
							$(".sourceProvinces").hide();
							$("#sourceProvincesName").val("");
							$("#orgProvId").attr("data-value","");
						}
					}
				</script>
				<tr>
					<th>
						<font color="red">*</font>本科毕业院校：</th>
					<td>
						<input name="graduatedName" value="${extInfo.graduatedName}" type="text" class="validate[required]">
					</td>
					<th><font color="red">*</font>本科毕业年份：</th>
					<td>
						<input name="graduationTime" value="${extInfo.graduationTime}" type="text" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" class="validate[required]">
					</td>
					<th>
						<font color="red">*</font>本科毕业专业：</th>
					<td>
						<input name="specialized" value="${extInfo.specialized}" type="text" class="validate[required]">
					</td>
				</tr>
				<%--<tr>--%>
					<%--<th>--%>
						<%--<font color="red">*</font>学历：</th>--%>
					<%--<td>--%>
						<%--<select name="user.educationId" class="validate[required] ">--%>
							<%--<option value="">请选择</option>--%>
							<%--<c:forEach var="dict" items="${dictTypeEnumUserEducationList }">--%>
								<%--<option value="${dict.dictId }" <c:if test="${user.educationId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>--%>
							<%--</c:forEach>--%>
						<%--</select>--%>
					<%--</td>--%>
				<%--</tr>--%>
				<tr>
					<th><font color="red">*</font>是否为应届生：</th>
					<td>
						<label><input type="radio" name="isYearGraduate" class="validate[required]" <c:if test="${doctor.isYearGraduate eq 'Y'}">checked="checked"</c:if>  value="Y" />是</label>&#12288;
						<label><input type="radio" name="isYearGraduate" class="validate[required]" <c:if test="${doctor.isYearGraduate eq 'N'}">checked="checked"</c:if> value="N" />否</label>

					</td>
					<th><font color="red">*</font>订单定向培训：</th>
					<td>
						<label><input type="radio" name="isGeneralOrderOrientationTrainee" class="validate[required]" <c:if test="${extInfo.isGeneralOrderOrientationTrainee eq 'Y'}">checked="checked"</c:if>  value="Y" />是</label>&#12288;
						<label><input type="radio" name="isGeneralOrderOrientationTrainee" class="validate[required]" <c:if test="${extInfo.isGeneralOrderOrientationTrainee eq 'N'}">checked="checked"</c:if> value="N" />否</label>
					</td>
					<th><font color="red">*</font>学位：</th>
					<td>
						<select id="degree"name="degreeId" class="validate[required]" >
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
								<option value="${dict.dictId}" <c:if test="${ dict.dictId eq extInfo.degreeId }">selected</c:if>>${dict.dictName}</option>
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
							$(".masterInfo").show();
							var masterStatue = $("input[name='masterStatue']:checked").val();
							checkIsStudy(masterStatue,'master');
						}else{
							$(".masterInfo").hide();
						}
					}
					function checkIsDoctor(value){
						if(value=='Y'){
							$(".doctorInfo").show();
							var doctorStatue = $("input[name='doctorStatue']:checked").val();
							checkIsStudy(doctorStatue,'doctor');
						}else{
							$(".doctorInfo").hide();
						}
					}
					function checkIsStudy(value,typeId){
						if(value == "1"){
							$("."+typeId+"IsGrad").hide();//就读相关
						}else {
							$("."+typeId+"IsGrad").show();//毕业相关
						}
					}
				</script>
				<tr>
					<th><font color="red">*</font>是否为硕士：</th>
					<td>
						<label><input type="radio" name="isMaster" class="validate[required]" <c:if test="${extInfo.isMaster eq 'Y'}">checked="checked"</c:if> value="Y" onchange="checkIsMaster(this.value)"/>是</label>&#12288;
						<label><input type="radio" name="isMaster" class="validate[required]" <c:if test="${extInfo.isMaster eq 'N'}">checked="checked"</c:if> value="N" onchange="checkIsMaster(this.value)"/>否</label>
					</td>
					<th style="display: none" class="masterInfo"><font color="red">*</font>状态：</th>
					<td style="display: none" class="masterInfo">
						<label><input type="radio" name="masterStatue" class="validate[required]" <c:if test="${extInfo.masterStatue eq '1'}">checked="checked"</c:if> value="1" onchange="checkIsStudy(this.value,'master')"/>在读</label>&#12288;
						<label><input type="radio" name="masterStatue" class="validate[required]" <c:if test="${extInfo.masterStatue eq '2'}">checked="checked"</c:if> value="2" onchange="checkIsStudy(this.value,'master')"/>已毕业</label>
					</td>
					<th style="display: none" class="masterInfo"><font color="red">*</font>硕士就读院校：</th>
					<td style="display: none" class="masterInfo">
						<input name="masterGraSchoolName" value="${extInfo.masterGraSchoolName}" type="text" class="validate[required]">
					</td>
				</tr>
				<tr style="display: none" class="masterInfo">
					<th><font color="red">*</font>硕士入学时间：</th>
					<td>
						<input name="masterStartTime" value="${extInfo.masterStartTime}" type="text" class="validate[required]" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});">

					</td>
					<th><font color="red">*</font>所学专业：</th>
					<td>
						<input name="masterMajor" value="${extInfo.masterMajor}" type="text" class="validate[required]">

					</td>
				</tr>
				<tr style="display: none" class="masterInfo masterIsGrad">
					<th><font color="red">*</font>学位：</th>
					<td>
						<select name="masterDegreeId" class="select validate[required] master" >
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
								<option value="${dict.dictId}" <c:if test="${ dict.dictId eq extInfo.masterDegreeId}">selected</c:if>>${dict.dictName}</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<font color="red">*</font>学位类型：</th>
					<td>
						<select name="masterDegreeTypeId" class="validate[required]">
							<option value="">请选择</option>
							<option value="1"
									<c:if test="${extInfo.masterDegreeTypeId eq '1'}">selected</c:if>
							>专业型</option>
							<option  value="2"
									 <c:if test="${extInfo.masterDegreeTypeId eq '2'}">selected</c:if>
							>科学型</option>
						</select>
					</td>
					<th><font color="red">*</font>硕士毕业时间：</th>
					<td>
						<input name="masterGraTime" value="${extInfo.masterGraTime}" type="text" class="validate[required]" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});">

					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>是否为博士：</th>
					<td>
						<label><input type="radio" name="isDoctor" class="validate[required]" <c:if test="${extInfo.isDoctor eq 'Y'}">checked="checked"</c:if>  value="Y" onchange="checkIsDoctor(this.value)"/>是</label>&#12288;
						<label><input type="radio" name="isDoctor" class="validate[required]" <c:if test="${extInfo.isDoctor eq 'N'}">checked="checked"</c:if> value="N" onchange="checkIsDoctor(this.value)"/>否</label>

					</td>
					<th  style="display: none" class="doctorInfo">
						<font color="red">*</font>状态：</th>
					<td  style="display: none" class="doctorInfo">
						<label><input type="radio" name="doctorStatue" class="validate[required]" <c:if test="${extInfo.doctorStatue eq '1'}">checked="checked"</c:if>  value="1" onchange="checkIsStudy(this.value,'doctor')"/>在读</label>&#12288;
						<label><input type="radio" name="doctorStatue" class="validate[required]" <c:if test="${extInfo.doctorStatue eq '2'}">checked="checked"</c:if> value="2" onchange="checkIsStudy(this.value,'doctor')"/>已毕业</label>

					</td>
					<th  style="display: none" class="doctorInfo"><font color="red">*</font>博士就读院校：</th>
					<td  style="display: none" class="doctorInfo">
						<input name="doctorGraSchoolName" value="${extInfo.doctorGraSchoolName}" type="text" class="validate[required]">

					</td>
				</tr>
				<tr style="display: none" class="doctorInfo">
					<th><font color="red">*</font>博士入学时间：</th>
					<td>
						<input name="doctorStartTime" value="${extInfo.doctorStartTime}" type="text" class="validate[required]" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});">

					</td>
					<th><font color="red">*</font>所学专业：</th>
					<td>
						<input name="doctorMajor" value="${extInfo.doctorMajor}" type="text" class="validate[required]">
					</td>
				</tr>
				<tr style="display: none" class="doctorInfo doctorIsGrad">
					<th><font color="red">*</font>学位：</th>
					<td>
						<select name="doctorDegreeId" class="validate[required]" >
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
								<option value="${dict.dictId}" <c:if test="${ dict.dictId eq extInfo.doctorDegreeId }">selected</c:if>>${dict.dictName}</option>
							</c:forEach>
						</select>
					</td>
					<th><font color="red">*</font>学位类型：</th>
					<td>
						<select name="doctorDegreeTypeId" class="validate[required]">
							<option value="">请选择</option>
							<option value="1"
									<c:if test="${extInfo.doctorDegreeTypeId eq '1'}">selected</c:if>
							>专业型</option>
							<option value="2"
									<c:if test="${extInfo.doctorDegreeTypeId eq '2'}">selected</c:if>
							>科学型</option>
						</select>
					</td>
					<th><font color="red">*</font>博士毕业时间：</th>
					<td>
						<input name="doctorGraTime" value="${extInfo.doctorGraTime}" type="text" class="validate[required]" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});">

					</td>
				</tr>
			</table>
			<div style="display:none">
		<table>
			<input type="hidden" id="technologyQualificationName" name="technologyQualificationName" value="${extInfo.technologyQualificationName}"/>
			<input type="hidden" id="qualificationMaterialName" name="qualificationMaterialName" value="${extInfo.qualificationMaterialName}"/>
			<input type="hidden" id="practicingCategoryName" name="practicingCategoryName" value="${extInfo.practicingCategoryName}"/>
			<input type="hidden" id="practicingScopeName" name="practicingScopeName" value="${extInfo.practicingScopeName}"/>
			<!-- 学位类型Name-->
			<input type="hidden" id="masterDegreeTypeName" name="masterDegreeTypeName" value="${extInfo.masterDegreeTypeName}"/>
			<input type="hidden" id="doctorDegreeTypeName" name="doctorDegreeTypeName" value="${extInfo.doctorDegreeTypeName }"/>
			<!-- 最高学历相关信息-->
			<input type="hidden" id="uEducationName" name="user.educationName" value="${user.educationName}"/>
			<input type="hidden" id="dGraduatedName" name="doctor.graduatedName" value="${doctor.graduatedName}"/>
			<input type="hidden" id="dSpecialized" name="doctor.specialized" value="${doctor.specialized}"/>
			<input type="hidden" id="dGraduationTime" name="doctor.graduationTime" value="${doctor.graduationTime}"/>
			<!-- 生源省份Name-->
			<input type="hidden" id="sourceProvincesName" name="sourceProvincesName" value="${doctor.sourceProvincesName}"/>
				<%--人员属性--%>
			<input type="hidden" id="personnelAttributeName" name="personnelAttributeName" value="${extInfo.personnelAttributeName}"/>
				<%--与紧急联系人关系--%>
			<input type="hidden" id="emergencyRelation" name="emergencyRelation" value="${extInfo.emergencyRelation}"/>
				<%--紧急联系人地址--%>
			<input type="hidden" id="emergencyAddress" name="emergencyAddress" value="${extInfo.emergencyAddress}"/>

			<th>最高毕业证书编号：</th>
			<td ><input name="doctor.certificateNo" value="${doctor.certificateNo}" class="input validate[]"/></td>
			<th>最高毕业证书上传：</th>
			<td>
				<input type="hidden" id="certificateUriValue" name="certificateUri" value="${extInfo.certificateUri}"/>
			</td>
			</tr>
			<tr>
				<th>最高学位证书编号：</th>
				<td ><input name="doctor.degreeNo" value="${doctor.degreeNo}" class="input"/>&#12288;</td>
				<th>最高学位证书上传：</th>
				<td >
					<input type="hidden" id="degreeUriValue" name="degreeUri" value="${extInfo.degreeUri}"/>
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
				<th>执业类型：</th>
				<td style="padding-left:10px;">

					<select id="practicingCategoryId" name="practicingCategoryId" class="select" style="width: 160px;" onchange="changeCategoryId(this.value)">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumPracticeTypeList}" var="dictTypeEnumPracticeType">
							<option value="${dictTypeEnumPracticeType.dictId}"	 <c:if test='${extInfo.practicingCategoryId==dictTypeEnumPracticeType.dictId}'>selected</c:if>>
									${dictTypeEnumPracticeType.dictName}</option>
						</c:forEach>
					</select>
				</td>
				<th>执业范围：</th>
				<td style="padding-left:10px;">
					<select id="practicingScopeId" name="practicingScopeId" class="select" style="width: 160px;">
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
						if(dictId=="") {
							$('#practicingScopeId').val("");
						}
						$('#practicingScopeId option').hide();
						$('#practicingScopeId option[value=""]').show();
						//$('#practicingScopeId').val("${extInfo.practicingScopeId}");
						$('#practicingScopeId'+' option.${dictTypeEnumPracticeType.id}\\.'+dictId).show();
					}
					$(function(){
						changeCategoryId('${extInfo.practicingCategoryId}');
					});
				</script>
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
					<col width="14%"/>
					<col width="20%"/>
					<col width="12%"/>
					<col width="20%"/>
					<col width="12%"/>
					<col width="20%"/>
				</colgroup>
				<tr>
					<th>参培年份：</th>
					<td>
						<%--<select name="sessionNumber" id="sessionNumber" onchange="selRotation();calculation();">--%>
							<%--<option></option>--%>
							<%--<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">--%>
								<%--<option value="${dict.dictName}" ${doctor.sessionNumber eq dict.dictName?'selected="selected"':''}>${dict.dictName}</option>--%>
							<%--</c:forEach>--%>
						<%--</select>--%>
						<label id="sessionNumber">${doctor.sessionNumber}</label>
					</td>
					<th><span class="trainNameSpan trainExtralSpan">培养</span>年限：</th>
					<td>
						<%--<select name="trainingYears" id="trainingYears" onchange="calculation();">--%>
							<%--<option></option>--%>
							<%--<c:forEach items="${dictTypeEnumTrainingYearsList}" var="dict">--%>
								<%--<option value="${dict.dictName}" ${doctor.trainingYears eq dict.dictName?'selected':''}>${dict.dictName}</option>--%>
							<%--</c:forEach>--%>
						<%--</select>--%>
						<label id="trainingYears">${doctor.trainingYears}</label>
					</td>
					<th><span class="trainNameSpan">培训</span>专业：</th>
					<td>
						<%--<select name="trainingSpeId" id="trainingSpeId" onchange="selRotation();">--%>
							<%--<option></option>--%>
							<%--<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">--%>
								<%--<option value="${dict.dictId}" ${doctor.trainingSpeId eq dict.dictId?'selected="selected"':''}>${dict.dictName}</option>--%>
							<%--</c:forEach>--%>
						<%--</select>--%>
						<label>${doctor.trainingSpeName}</label>
					</td>
					</tr>
				<tr>
					<th><label class="rotationAttr">轮转方案：</label></th>
					<td>
						<%--<c:if test="${!rotationInUse}">--%>
							<%--<select name="rotationFlow" class=" rotationAttr" id="rotationFlow" onclick="checkCondition();">--%>
								<%--<option></option>--%>
								<%--<c:forEach items="${rotationList}" var="rotation">--%>
									<%--<option class="_${rotation.doctorCategoryId} _${rotation.speId} _rotation--%>
									<%--<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag && rotation.isOrgView eq GlobalConstant.RECORD_STATUS_Y}">--%>
										<%--_ isOrg--%>
										<%--<c:forEach items="${map[rotation.rotationFlow]}" var="resRotationOrg">--%>
										<%--_ ${resRotationOrg.orgFlow}--%>
										<%--</c:forEach>--%>
									<%--</c:if>--%>
									<%--" value="${rotation.rotationFlow}" ${(doctor.rotationFlow eq rotation.rotationFlow)?'selected':''}>${rotation.rotationName}</option>--%>
								<%--</c:forEach>--%>
							<%--</select>--%>
							<%--<font class="rotationAttr" color="red"></font>--%>
						<%--</c:if>--%>
						<%--<c:if test="${rotationInUse}">--%>
							<label>${doctor.rotationName}</label>
						<%--</c:if>--%>
					</td>
					<th><label>实际培训开始时间：</label></th>
					<td>
						<label>${doctor.inHosDate}</label>
					</td>
					<th>结业考核年份：</th>
					<td>
						<%--<input type="text" name="graduationYear" readonly="readonly" id="graduationYear"--%>
							   <%--onclick="WdatePicker({dateFmt:'yyyy'})">--%>
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
					<td></td><td></td>
				</tr>
			</table>

</form>
				<div style="text-align: center">
					<input type="button" value="保&#12288;存" class="search" onclick="saveDoc();">
				</div>
			</div>
		<tr>
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