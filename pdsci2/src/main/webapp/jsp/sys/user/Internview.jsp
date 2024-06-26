
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
	if('${user.userHeadImg}'==""&&$("#asshole").val()!="Y"){
		jboxTip("请上传头像");
		return false;
	}
	getUserResumeExtName();
	getFantasticFour();
	jboxConfirm("确认保存？",function(){
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
	if('${doctor.graduationYear}'==""||'${doctor.graduationYear}'==null){
		calculation();
	}
});
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

</script>
<style>
	table{ margin:10px 0;border-collapse: collapse;}
	caption,th,td{height:40px;}
	caption{line-height:40px;text-align:left;font-weight:bold; padding-left:10px; border-bottom:1px solid #ddd;color:#3385ff;margin-bottom: 10px;}
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
					<td style="text-align: left;padding-left: 30px;width: 25%">性&#12288;别：${user.sexName }</td>
					<td style="text-align: left;padding-left: 30px;">登&nbsp;录&nbsp;名&nbsp;：${user.userCode}</td>
					<c:if test="${sessionScope.currWsId ne 'gzykdx'}">
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
	<input name="doctorCategoryId" type="hidden" value="${recDocCategoryEnumIntern.id}"/>
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
					<th>
						<font color="red">*</font>性别：</th>
					<td>
						<label><input type="radio" name="sexId" class="validate[required]" <c:if test="${user.sexId eq userSexEnumMan.id}">checked="checked"</c:if>  value="${userSexEnumMan.id}" />男</label>&#12288;
						<label><input type="radio" name="sexId" class="validate[required]" <c:if test="${user.sexId eq userSexEnumWoman.id}">checked="checked"</c:if> value="${userSexEnumWoman.id}" />女</label>
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
					<th>
						<font color="red">*</font>证件号码：</th>
					<td>
						<input type="text" name="idNo" id="idNo" value="${user.idNo}" class="validate[required]">
					</td>
				</tr>
				<tr>
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
						<font color="red">*</font>手机：</th>
					<td>
						<input type="text" name="userPhone" value="${user.userPhone}" class="validate[required,custom[mobile]]"/>
					</td>
					<th >
						<font color="red">*</font>邮箱：</th>
					<td>
						<input type="text" name="userEmail" value="${user.userEmail}" class="validate[required,custom[email]]"/>
						<input type="hidden" name="doctorTypeId" value="${recDocCategoryEnumGraduate.id}"  id="doctorTypeId" />
						<input name="doctorStatusId" type="hidden" value="${resDoctorStatusEnumTraining.id}"/>
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
					<th>年&#12288;&#12288;级：</th>
					<td>
						<label id="sessionNumber">${doctor.sessionNumber}</label>
					</td>
					<th>实习年限：</th>
					<td>
						<label id="trainingYears">${doctor.trainingYears}</label>
					</td>
					<th>实习基地：</th>
					<td>
						<label>${doctor.orgName}</label>
					</td>
				</tr>
				<tr>
					<th>实习专业：</th>
					<td>
						<label>${doctor.trainingSpeName}</label>
					</td>
					<th><label class="rotationAttr">轮转方案：</label></th>
					<td><label>${doctor.rotationName}</label></td>
					<th>派送院校：</th>
					<td>
						 ${doctor.workOrgName}
					</td>
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