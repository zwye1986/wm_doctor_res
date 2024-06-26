<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<jsp:include page="/jsp/sczyres/htmlhead-sczyres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="login" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_scrollTo" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<style>
	.base_info th,.base_info td{height:45px;}
</style>
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script>
	$(document).ready(function(){
		$('[name="sessionNumber"]').datepicker({
			startView: 2,
			maxViewMode: 2,
			minViewMode:2,
			format:'yyyy'
		});
		$('[name="graduationYear"]').datepicker({
			startView: 2,
			maxViewMode: 2,
			minViewMode:2,
			format:'yyyy'
		});
		$('.datepicker').datepicker();
		changeDoctorType('${doctor.doctorTypeId}');
		changeDoctorQualification('${doctor.trainingSpeId}');
		showCoordinationHospital('${extInfo.isCoordinationBase}');
		showMakeUp('${extInfo.isMakeUp}')
		$("input:not([type='button'])").attr('disabled','disabled');
		$("input[type='text']").css('height','30px');
		$("select").attr('disabled','disabled');
		$(".saveButton").hide();
		$("#zyqkDiv a").css('visibility','hidden');
		$("#zyDiv a").css('visibility','hidden');
	});

	function changeDoctorType(doctorTypeId){
		if(doctorTypeId=='${sczyRecDocTypeEnumAgency.id}'){
			$(".workOrg").show();
			$(".address").show();
			$(".agencyA").show();
			$(".industryA").hide();
			$(".workSchool").hide();
			changeOrgLevel($("#medicalHeaithOrgId").val());
		}else if(doctorTypeId=="${sczyRecDocTypeEnumEntrust.id}"){
			$(".workOrg").show();
			$(".address").show();
			$(".agencyA").hide();
			$(".industryA").show();
			$(".workSchool").hide();
			changeOrgLevel($("#medicalHeaithOrgId").val());
		}else if(doctorTypeId=="${sczyRecDocTypeEnumGraduate.id}"){
			$(".workOrg").hide();
			$(".workSchool").show();
			$(".address,.medical,.hospital").hide();
		}else {
			$(".workOrg").hide();
			$(".workSchool").hide();
			$(".address,.medical,.hospital").hide();
		}
	}
	function changeOrgLevel(value){
		if(value=="1"){
			$(".medical").hide();
			$("#medicalHeaithOrgIdTd").removeAttr("colspan");
			$(".hospital").show();
			$("#TD").attr("colspan",3);
		}
//	if(value=="2"){
//		$(".medical").hide();
//		$("#medicalHeaithOrgIdTd").attr("colspan",5);
//		$(".hospital").hide();
//	}
		if(value==""<%-- | value=="4" --%>){
			$(".medical").hide();
			$("#medicalHeaithOrgIdTd").attr("colspan",5);
			$(".hospital").hide();
		}
		if(value=="3"){
			$(".hospital").hide();
			$(".medical").show();
			$("#medicalHeaithOrgIdTd").removeAttr("colspan");
		}
		if(value=="5"){
			$(".hospital").hide();
			$(".medical").hide();
			$("#medicalHeaithOrgIdTd").attr("colspan",5);
		}
	}
	function showCoordinationHospital(value){
		if(value=='Y'){
			$(".coordinationHospital").show();
		}else {
			$(".coordinationHospital").hide();
		}
	}
	function showMakeUp(value){
		if(value=='Y'){
			$(".makeUpType").show();
			$("#TD2").attr("colspan",1);
		}else {
			$(".makeUpType").hide();
			$("#TD2").attr("colspan",3);
		}
	}
	function audit67(flag,recordFlow,level){
		var len = $("#remarkContent").val().length;
		if(len>250){
			jboxTip("审核意见不能超过250个字")
			return;
		}
		jboxConfirm("确认"+(flag=='1'?'通过':'退回')+'?',function(){
			jboxPost("<s:url value='/sczyres/hosptial/auditApply'/>?flag="+flag+"&recordFlow="+recordFlow+"&level="+level,
					$("#remark").serialize(),function(resp){
						if(resp=='1'){
							jboxTip("操作成功");
							top.search();
							top.jboxClose();
						}
					},null,false);
		})
	}

	function chargeAudit0(flag,recordFlow){
		jboxConfirm("确认"+(flag=='1'?'通过？':'退回？'),function(){
			jboxPost("<s:url value='/sczyres/manage/auditApply'/>?flag="+flag+"&recordFlow="+recordFlow,$("#remark").serialize(),function(resp){
				if(resp=='1'){
					jboxTip("操作成功");
					top.search();
					top.jboxClose();
				}
			},null,false);
		})
	}
	function changeDoctorQualification(value){
		if(value=='1'||value=='2'){
			$(".doctorQualification").show();
			$(".AssistantDoctorQualification").hide();
		}else if(value=='3'){
			$(".AssistantDoctorQualification").show();
			$(".doctorQualification").hide();
		}else{
			$(".AssistantDoctorQualification").hide();
			$(".doctorQualification").hide();
		}
	}
</script>
<div id="singupContent">
	<div id='docTypeForm'>
		<p id="errorMsg" style="color: red;"></p>
		<%--<form id='doctorForm'>--%>
			<input type="hidden" name="userFlow" value="${user.userFlow}"/>
			<input type="hidden" name="doctorFlow" value="${doctor.doctorFlow}"/>
			<input type="hidden" name="recordFlow" value="${apply.recordFlow}"/>
			<div class="main_bd">
				<div class="div_table">
					<div class="score_frame">
						<h1>四川省中医住院医师规范化培训实践技能考核申请表（2019年）</h1>
						<div class="div_table">
							<h4>基本信息</h4>
							<table border="0" cellpadding="0" cellspacing="0" class="base_info">
								<colgroup>
									<col width="14%"/>
									<col width="20%"/>
									<col width="14%"/>
									<col width="10%"/>
									<col width="12%"/>
									<col width="20%"/>
								</colgroup>
								<tbody>
								<tr>
									<th><font color="red">*</font>姓名：</th>
									<td><input type='text' class='input validate[required]' id="userName" name="userName" value="${user.userName}"/></td>
									<th><font color="red">*</font>性别：</th>
									<td colspan="2">
										&nbsp;${user.sexName}
										<%--<label>--%>
											<%--<input type="radio" class='validate[required]' style="width:auto;" name="sexId" sexName="${userSexEnumMan.name}" value='${userSexEnumMan.id}' ${user.sexId eq userSexEnumMan.id?'checked':''}/>${userSexEnumMan.name}--%>
										<%--</label>--%>
										<%--&nbsp;--%>
										<%--<label>--%>
											<%--<input type="radio" class='validate[required]' style="width:auto;" name="sexId" sexName="${userSexEnumWoman.name}" value='${userSexEnumWoman.id}' ${user.sexId eq userSexEnumWoman.id?'checked':''}/>${userSexEnumWoman.name}--%>
										<%--</label>--%>
										<%--<input type="hidden" name="sexName" id="sexName">--%>
									</td>
									<td id="imgTd" rowspan="5" style="text-align: center;padding-top:5px;">
										<img src="${sysCfgMap['upload_base_url']}/${user.userHeadImg}" id="showImg" width="80%"
											 height="160px" onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
										<%--<span style="cursor: pointer; display:block;" id="userImgSpan">--%>
										<%--[<a onclick="$('#imageFile').click();">${empty user.userHeadImg?'上传头像':'重新上传'}</a>]--%>
										<%--</span>--%>
										<%--<input type="file" id="imageFile" name="headImg" style="display: none" onchange="uploadImage();"/>--%>
										<%--<input type="hidden" id="headimgurl" value=""/>--%>
										<%--<div>照片要求：2吋白底，jpg或png格式，大小2M以内</div>--%>
									</td>
								</tr>
								<tr>
									<th><font color="red">*</font>民族：</th>
									<td>
										<select name="nationId" id="nationId" class="validate[required] select" style="width: 160px;">
											<option value="">请选择</option>
											<c:forEach items="${userNationEnumList}" var="userNation">
												<option value="${userNation.id}" ${userNation.id eq user.nationId?'selected':''}>${userNation.name}</option>
											</c:forEach>
										</select>
										<input type='hidden' name="nationName" id="nationName" value="${user.nationName}"/>
									</td>
									<th><font color="red">*</font>出生日期：</th>
									<td colspan="2">
										<input id="userBirthday"  value="${user.userBirthday}"  class="validate[required] input datepicker" style="width: 149px;" readonly="readonly"/>
									</td>
								</tr>
								<tr>
									<th><font color="red">*</font>证件类型：</th>
									<td style="padding-left: 10px;">
										<select id="cretTypeId" name="cretTypeId" class="select validate[required]" style="width: 160px;">
											<option value="">请选择</option>
											<c:forEach items="${certificateTypeEnumList}" var="cret">
												<option value="${cret.id}" ${user.cretTypeId eq cret.id?'selected':''}>${cret.name}</option>
											</c:forEach>
										</select>
										<input name="cretTypeName" type="hidden" id="cretTypeName"/>
									</td>
									<th><font color="red">*</font>证件号：</th>
									<td colspan="2">
										<input type="text" name="idNo" value="${user.idNo}" class="input validate[custom[chinaIdLoose],required]"/>
									</td>
								</tr>
								<tr>
									<th><font color="red">*</font>结业考核年份：</th>
									<td><input type='text' class='input validate[required] datepicker' name="graduationYear" value="${doctor.graduationYear}" readonly="readonly"/></td>
									<th><font color="red">*</font>联系方式：</th>
									<td colspan="2">
										<input type='text' class='input validate[required]' name="userPhone" value="${user.userPhone}" />
									</td>
								</tr>
								<tr>
									<th><font color="red">*</font>入培年级：</th>
									<td><input type='text' class='input validate[required]' name="sessionNumber" value="${doctor.sessionNumber}" readonly="readonly"/></td>
									<th><font color="red">*</font>培训身份：</th>
									<td colspan="2">
										<select name="doctorTypeId" id="doctorTypeId" class="select validate[required]"
												style="width: 160px;margin-left: 5px;" onchange="changeDoctorType(this.value)">
											<option value="">请选择</option>
											<c:forEach items="${sczyRecDocTypeEnumList}" var="doctorType">
												<option value="${doctorType.id}" ${doctorType.id eq doctor.doctorTypeId?'selected':''}>${doctorType.name}</option>
											</c:forEach>
										</select>
										<input type="hidden" name="doctorTypeName" id="doctorTypeName"/>
									</td>
								</tr>
								<tr class="workOrg" style="display: none">
									<th><font color="red">*</font><a class="industryA">委托培养单位：</a><a class="agencyA">本单位：</a></th>
									<td colspan="5">
										<input type="text" class="input validate[required]" name="workOrgName1" value="${doctor.workOrgName}"  style="width:285px;">
									</td>
								</tr>
								<tr class="address">
									<th><span class="red">*</span><a class="industryA">医疗卫生机构：</a><a class="agencyA">所在单位信息：</a></th>
									<td colspan="5" id="medicalHeaithOrgIdTd">
										&nbsp;<select class="select address validate[required]" id="medicalHeaithOrgId" name="medicalHeaithOrgId" onchange="changeOrgLevel(this.value);" style="width: 160px">
										<option value="">请选择</option>
										<c:forEach items="${dictTypeEnumMedicalHeaithOrgList}" var="tra">
											<option value="${tra.dictId}" <c:if test="${extInfo.medicalHeaithOrgId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
										</c:forEach>
									</select>
										<input type="hidden" name="medicalHeaithOrgName" id="medicalHeaithOrgName">
									</td>
									<th class="hospital medical" style="display: none;"><span class="red">*</span>医院性质：</th>
									<td class="hospital medical" style="display: none;" colspan="3" id="TD">
										&nbsp;<select class="select hospital medical validate[required]" id="hospitalAttrId" name="hospitalAttrId"style="width: 160px">
										<option value="">请选择</option>
										<c:forEach items="${dictTypeEnumHospitalAttrList}" var="tra">
											<option value="${tra.dictId}" <c:if test="${extInfo.hospitalAttrId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
										</c:forEach>
									</select>
										<input type="hidden" name="hospitalAttrName" id="hospitalAttrName">
									</td>
								</tr>
								<tr style="display: none;" class="medical">
									<th class="medical" style="display: none;"><span class="red">*</span>基层医疗&#12288;<br/>卫生机构：</th>
									<td class="medical" style="display: none;">
										&nbsp;<select class="select medical validate[required]" id="basicHealthOrgId" name="basicHealthOrgId"style="width: 160px">
										<option value="">请选择</option>
										<c:forEach items="${dictTypeEnumBasicHealthOrgList}" var="tra">
											<option value="${tra.dictId}" <c:if test="${extInfo.basicHealthOrgId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
										</c:forEach>
									</select>
										<input type="hidden" name="basicHealthOrgName" id="basicHealthOrgName">
									</td>
									<th><span class="red">*</span>基层医疗卫&#12288;<br/>生机构等级：</th>
									<td colspan="3">
										&nbsp;<select class="select medical validate[required]" id="basicHealthOrgLevelId" name="basicHealthOrgLevelId"style="width: 160px">
										<option value="">请选择</option>
										<c:forEach items="${dictTypeEnumBasicHealthOrgLevelList}" var="level">
											<option value="${level.dictId}" <c:if test="${extInfo.basicHealthOrgLevelId eq level.dictId}">selected="selected"</c:if>>${level.dictName}</option>
										</c:forEach>
									</select>
										<input type="hidden" name="basicHealthOrgLevelName" id="basicHealthOrgLevelName">
									</td>
								</tr>
								<tr style="display: none;" class="hospital">
									<th><span class="red">*</span>医院类别：</th>
									<td>
										&nbsp;<select class="select hospital  validate[required]" id="hospitalCategoryId" name="hospitalCategoryId"style="width: 160px">
										<option value="">请选择</option>
										<c:forEach items="${dictTypeEnumHospitalCategoryList}" var="tra">
											<option value="${tra.dictId}" <c:if test="${extInfo.hospitalCategoryId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
										</c:forEach>
									</select>
										<input type="hidden" name="hospitalCategoryName" id="hospitalCategoryName">
									</td>
									<th><span class="red">*</span>单位等级：</th>
									<td colspan="3">
										&nbsp;<select class="select hospital validate[required]" id="baseAttributeId" name="baseAttributeId"style="width: 160px">
										<option value="">请选择</option>
										<c:forEach items="${dictTypeEnumBaseAttributeList}" var="tra">
											<option value="${tra.dictId}" <c:if test="${extInfo.baseAttributeId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
										</c:forEach>
									</select>
										<input type="hidden" name="baseAttributeName" id="baseAttributeName">
									</td>
								</tr>
								<tr>
									<th>
										<font color="red">*</font>是否协同单位：
									</th>
									<td>
										<label><input type="radio" class='validate[required]' name="isCoordinationBase" value="${GlobalConstant.FLAG_Y}"
													  <c:if test="${extInfo.isCoordinationBase eq GlobalConstant.FLAG_Y}">checked</c:if> onchange="showCoordinationHospital('Y')"
										/>&nbsp;是</label>
										&nbsp;
										<label><input type="radio" class='validate[required]' name="isCoordinationBase" value="${GlobalConstant.FLAG_N}"
													  <c:if test="${extInfo.isCoordinationBase eq GlobalConstant.FLAG_N}">checked</c:if> onchange="showCoordinationHospital('N')"
										/>&nbsp;否</label>
									</td>
									<td colspan="4"></td>
								</tr>
								<tr style="display: none;" class="coordinationHospital">
									<th><span class="red">*</span>协同单位名称：</th>
									<td>
										<input type="text" class="input validate[required]" name="coordinationHospitalName" value="${extInfo.coordinationHospitalName}">
									</td>
									<th><span class="red">*</span>协同单位等级：</th>
									<td colspan="3">
										&nbsp;<select class="select validate[required]" id="coordinationBaseAttributeId" name="coordinationBaseAttributeId"style="width: 160px">
										<option value="">请选择</option>
										<c:forEach items="${dictTypeEnumBaseAttributeList}" var="tra">
											<option value="${tra.dictId}" <c:if test="${extInfo.coordinationBaseAttributeId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
										</c:forEach>
									</select>
										<input type="hidden" name="coordinationBaseAttributeName" id="coordinationBaseAttributeName">
									</td>
								</tr>
								<tr class="workSchool" style="display: none">
									<th><font color="red">*</font>最高学历毕业&#12288;<br/>院校：</th>
									<td colspan="5">
										<input type="text" class="input validate[required]" name="workOrgName2" value="${doctor.workOrgName}"  style="width:285px;">
									</td>
								</tr>
								<tr>
									<th><font color="red">*</font>是否应届生：</th>
									<td>
										<label><input type="radio" class='validate[required]' name="yearGraduateFlag" value="${GlobalConstant.FLAG_Y}"
													  <c:if test="${extInfo.yearGraduateFlag eq GlobalConstant.FLAG_Y}">checked</c:if>
										/>&nbsp;是</label>
										&nbsp;
										<label><input type="radio" class='validate[required]' name="yearGraduateFlag" value="${GlobalConstant.FLAG_N}"
													  <c:if test="${extInfo.yearGraduateFlag eq GlobalConstant.FLAG_N}">checked</c:if>
										/>&nbsp;否</label>
									</td>
									<th><font color="red">*</font>是否补考人员：</th>
									<td id="TD2">
										<label><input type="radio" class='validate[required]' name="isMakeUp" value="${GlobalConstant.FLAG_Y}" onchange="showMakeUp('Y')"
													  <c:if test="${extInfo.isMakeUp eq GlobalConstant.FLAG_Y}">checked</c:if>
										/>&nbsp;是</label>
										&nbsp;
										<label><input type="radio" class='validate[required]' name="isMakeUp" value="${GlobalConstant.FLAG_N}" onchange="showMakeUp('N')"
													  <c:if test="${extInfo.isMakeUp eq GlobalConstant.FLAG_N}">checked</c:if>
										/>&nbsp;否</label>
									</td>
									<th class="makeUpType">
										<font color="red">*</font>补考类型：
									</th>
									<td class="makeUpType">
										<select name="makeUpTypeId" class="select validate[required]" style="width: 160px;margin-left: 5px;">
											<option value="">请选择</option>
											<option value="1" ${extInfo.makeUpTypeId eq '1'?'selected':''}>理论</option>
											<option value="2" ${extInfo.makeUpTypeId eq '2'?'selected':''}>技能</option>
											<option value="3" ${extInfo.makeUpTypeId eq '3'?'selected':''}>全部</option>
										</select>
										<input type="hidden" name="makeUpTypeName" id="makeUpTypeName" value="${extInfo.makeUpTypeName}">
									</td>
								</tr>
								<tr>
									<th><font color="red">*</font>培训基地：</th>
									<td colspan="5">
										<select class="select validate[required]" id="orgFlow" name="orgFlow" style="width:300px;margin-left: 5px;">
											<option value="">请选择</option>
											<c:forEach items="${hospitals}" var="hosptial">
												<option value='${hosptial.orgFlow}' <c:if test='${user.orgFlow eq hosptial.orgFlow}'>selected="selected"</c:if>>${hosptial.orgName}</option>
											</c:forEach>
										</select>
										<input type="hidden" name="orgName" id="orgName">
									</td>
								</tr>
								<tr>
									<th><font color="red">*</font>家庭住址：</th>
									<td colspan="5" style="padding-top: 5px;">
						   <span id="provCityAreaId" style="padding-left: 5px;">
							   <select id="birthProvId" name="homeProvId" style="width: 149px" class="province select validate[required]" data-value="${extInfo.homeProvId}" data-first-title="选择省"></select>
							   <select id="birthCityId" name="homeCityId" class="city select validate[required]" data-value="${extInfo.homeCityId}" data-first-title="选择市"></select>
							   <select id="birthAreaId" name="homeAreaId" class="area select validate[required]" data-value="${extInfo.homeAreaId}" data-first-title="选择地区"></select>
						   </span>
										<input id="birthProvName" name="homeProvName" type="hidden" value="${extInfo.homeProvName}">
										<input id="birthCityName" name="homeCityName" type="hidden" value="${extInfo.homeCityName}">
										<input id="birthAreaName" name="homeAreaName" type="hidden" value="${extInfo.homeAreaName}">
										<script type="text/javascript">
											// 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
											$.cxSelect.defaults.url = "<s:url value='/js/provCityAreaJson.min.json'/>";
											$.cxSelect.defaults.nodata = "none";

											$("#provCityAreaId").cxSelect({
												selects : ["province", "city", "area"],
												nodata : "none",
												firstValue : ""
											});
										</script>
										<input type='text' class='input validate[required]' style="width: 240px;" name="userAddress" value="${user.userAddress}"/>
										<%--<div style="color: red">&nbsp;详细家庭住址用于培训相关资料邮寄，务必详细准确</div>--%>
									</td>
								</tr>
								</tbody>
							</table>
						</div>

						<div class="div_table" ID="contactway">
							<h4>资格信息</h4>
							<table border="0" cellpadding="0" cellspacing="0" class="base_info">
								<colgroup>
									<col width="16%"/>
									<col width="23%"/>
									<col width="16%"/>
									<col width="16%"/>
									<col width="16%"/>
									<col width="9%"/>
								</colgroup>
								<tbody>
								<tr>
									<th><font color="red">*</font>学历：</th>
									<td>
										<select name="educationId" id="educationId" class="select validate[required]" style="width: 160px;margin-left: 5px;">
											<option></option>
											<c:forEach items="${dictTypeEnumUserEducationList}" var="dict">
												<option value="${dict.dictId}" ${user.educationId eq dict.dictId?'selected':''}>${dict.dictName}</option>
											</c:forEach>
										</select>
										<input type="hidden" name="educationName" id="educationName">
									</td>
									<th><font color="red">*</font>学历证书编号：</th>
									<td colspan="2">
										<input type="text" class="input validate[required]" style="width: 300px;" name="certificateNo" value='${doctor.certificateNo}'/>
									</td>
								</tr>
								<tr>
									<th>学位：</th>
									<td>
										<select name="degreeId" id="degreeId" class="select" style="width: 160px;margin-left: 5px;">
											<option></option>
											<c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
												<option value="${dict.dictId}" ${user.degreeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
											</c:forEach>
										</select>
										<input type="hidden" name="degreeName" id="degreeName">
									</td>
									<th>学位证书编号：</th>
									<td colspan="2">
										<input type="text" class="input" style="width: 300px;" name="degreeNo" value='${doctor.degreeNo}'/>
									</td>
								</tr>
								<tr>
									<th><font color="red">*</font>培训时长：</th>
									<td>
										<input type="text" class="input validate[costum[number],required]" name="trainingYears" value="${doctor.trainingYears}">月
									</td>
									<th><font color="red">*</font>培训起止时间：</th>
									<td colspan="2">
										<input type="text" class="input validate[required] datepicker" style="width: 128px;" name="trainingStartDate" value="${extInfo.trainingStartDate}"/>
										~
										<input type="text" class="input validate[required] datepicker" style="width: 128px;" name="trainingEndDate" value="${extInfo.trainingEndDate}"/>

									</td>
								</tr>
								<tr>
									<th><font color="red">*</font>所学专业：</th>
									<td>
										<select name="trainingSpeId" id="trainingSpeId" class="select validate[required]" style="width: 160px;margin-left: 5px;" onchange="changeDoctorQualification(this.value)">
											<option value="">请选择</option>
											<option value="1" ${doctor.trainingSpeId eq '1'?'selected':''}>中医</option>
											<option value="2" ${doctor.trainingSpeId eq '2'?'selected':''}>中医全科</option>
											<option value="3" ${doctor.trainingSpeId eq '3'?'selected':''}>中医助理全科</option>
										</select>
										<input type="hidden" name="trainingSpeName" id="trainingSpeName">
									</td>
									<th><font color="red">*</font>培训登记手册完成&#12288;<br/>情况：</th>
									<td style="padding-left: 10px;" colspan="2">
										<label><input type="radio" class="validate[required]" value="Y" name="handBookInfo" ${extInfo.handBookInfo eq 'Y'?'checked':''}>完成</label>
										<label><input type="radio" class="validate[required]" value="N" name="handBookInfo" ${extInfo.handBookInfo eq 'N'?'checked':''}>未完成</label>
									</td>
								</tr>
								<tr class="doctorQualification">
									<th><font color="red">*</font>医师资格证书编号：</th>
									<td>
										<input type="text" class="input validate[required]" name="doctorLicenseNo" value="${doctor.doctorLicenseNo}">
									</td>
									<th>医师执业证书编号：</th>
									<td colspan="2">
										<input type="text" class="input" name="qualifiedNo" value="${doctor.qualifiedNo}" style="width: 300px;">
									</td>
								</tr>
								<tr class="AssistantDoctorQualification">
									<th><font color="red">*</font>助理医师资格证书&emsp;<br>编号：</th>
									<td colspan="4">
										<input type="text" class="input validate[required]" name="assistantQualificationCertificateCode" value="${extInfo.assistantQualificationCertificateCode}">
									</td>
								</tr>
								</tbody>
							</table>
						</div>


						<div class="div_table">
							<h4>附件上传</h4>
							<table id="fileTable" border="0" cellpadding="0" cellspacing="0" class="base_info">
								<tbody>
								<colgroup>
									<col width="25%"/>
									<col width="25%"/>
									<col width="25%"/>
									<col width="25%"/>
								</colgroup>
								<tr>
									<td colspan="4">
										附件上传说明：<br/>
										1、报名学员需上传《身份证》、《医师资格证书》的扫描件；<br/>
										2、《医师资格证书》需上传所有内页扫描件，1-2页、3-4页分开上传（样例见样例<a href='<s:url value="/jsp/sczyres/css/执业医师资格证1-2.jpg"/>' target="_blank" style="color:blue">1-2页</a>，
										<a href='<s:url value="/jsp/sczyres/css/执业医师资格证3-4.jpg"/>' target="_blank" style="color:blue">3-4页</a>）；<br/>
										3、《身份证》需上传正反面扫面件；<br/>
										4、扫描件格式为JPG，单个文件大小不超过5M，文件名请勿包含中文字符。
									</td>
								</tr>
								<tr>
									<td>资格证书1-2页</td>
									<td>资格证书3-4页</td>
									<td>身份证正面</td>
									<td>身份证反面</td>
								</tr>
								<tr>
									<c:forEach begin="1" end="4" step="1" var="s">
										<td class="fileTd">
										  <span id="${s}Span" style="display:${empty fileMap[s].filePath?'none':''} ">
											 <a class="view" href="${sysCfgMap['upload_base_url']}${fileMap[s].filePath}" target="_blank" style="color:blue">查看</a>
										  </span>
											<%--<a id="${s}Up" href="javascript:uploadFile('${s}');" style="color:blue">${empty fileMap[s].filePath?'':'重新'}上传</a>--%>
											<%--<a id="${s}Del" href="javascript:delFile('${s}');"  style="color:blue;${empty fileMap[s].filePath?'display:none':''}">删除</a>--%>
											<%--<input type="hidden" name="fileName" value="file${s}">--%>
											<%--<input type="hidden" name="fileFlow" value="${fileMap[s].fileFlow}">--%>
											<%--<input type="hidden" id="${s}Value" name="filePath" value="${fileMap[s].filePath}">--%>
										</td>
									</c:forEach>
								</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>

			<div id="nextPage" class="button" style="margin: 30px;">
				<input class="btn_blue saveButton" type="button" onclick="save();" value="保&#12288;存"/>
			</div>
		<%--</form>--%>
	</div>
</div>





<script>
$(document).ready(function(){
<%--$('.datepicker').datepicker();--%>
changeDiv('${doctor.trainingSpeId}');

	var id ='';//计算轮转总时间
	if($("#trainingSpeId option:selected").val()=='1'){
		id = 'zyDiv';
	}else if($("#trainingSpeId option:selected").val()=='2'){
		id = 'zyqkDiv';
	} else{
		id = 'zyzlqkDiv';
	}
	var sum = 0;
	$("#"+id+" tr:gt(0):not(.gsxx)").each(function(){
		if($(this).find('.startDate').val() &&  $(this).find('.endDate').val()){
			var startDate = $(this).find('.startDate').val();
			var endDate = $(this).find('.endDate').val();
			var single =  datedifference(startDate, endDate)+1;
			sum+=single;
		}
	});
	var content = (sum/30).toFixed(2);
	var content2 = (sum/7).toFixed(2);
	if(id == 'zyDiv' || id == 'zyqkDiv'){
		$("#"+id+" .sum").text(content+"月");
	}else{
		$("#"+id+" .sum").text(content2+"周");
	}
});

function save(){
	if(!$("#doctorForm").validationEngine("validate")){
		return;
	}
	var trainingStartDate = $("[name='trainingStartDate']").val();
	var trainingEndDate = $("[name='trainingEndDate']").val();
	if(trainingEndDate<=trainingStartDate){
		jboxTip("培训结束时间必须大于培训开始时间");
		$("[name='trainingEndDate']").focus();
		return;
	}
	$("#orgName").val($("#orgFlow option:selected").text());
	var sexName = $("[name=sexId]:checked").attr("sexName");
	$("#sexName").val(sexName);
	$("#doctorTypeName").val($("#doctorTypeId option:selected").text());
	$("#educationName").val($("#educationId option:selected").text());
	$("#degreeName").val($("#degreeId option:selected").text());
	$("#trainingSpeName").val($("#trainingSpeId option:selected").text());
	var data = [];
	var id ='';
	if($("#trainingSpeId option:selected").val()=='1'){
		id = 'zyDiv';
	}else if($("#trainingSpeId option:selected").val()=='2'){
		id = 'zyqkDiv';
	} else{
		id = 'zyzlqkDiv';
	}
	$("#"+id+" tr:gt(0)").each(function(){
		if($(this).find('.deptName')){
			var deptName = $(this).find('.deptName').val();
			var startDate = $(this).find('.startDate').val();
			var endDate = $(this).find('.endDate').val();
			var recordFlow = '';
			if($(this).find('.recordFlow').val()){
				recordFlow = $(this).find('.recordFlow').val();
			}
			var speId = $(this).attr('class');
			var single = {deptName:deptName,startDate:startDate,endDate:endDate,recordFlow:recordFlow,speId:speId};
			data.push(single);
		}
	});
	var jsonData = {schInfo:data,baseInfo:$("#doctorForm").serializeJson(),graduationYear:'${graduationYear}'}
	jboxPost("<s:url value='/sczyres/doctor/saveSchInfo'/>" ,{jsonData:JSON.stringify(jsonData)} , function(resp){
		if(resp=="1"){
			jboxTip("保存成功");
			jboxLoad("content2" , "<s:url value='/sczyres/doctor/lzqkshb'/>" , true);
		}else{
			jboxTip(resp);
		}
	} , null , false);
}
function addTd(obj){
var rowspan = parseInt($(obj).closest('td').attr('rowspan'))+1;
$(obj).closest('td').attr('rowspan',rowspan);
var c = $(obj).closest('td').attr('class');
var cloneTr = $("#template tr:eq(0)").clone();
cloneTr.addClass(c);
$("."+c+":not(td)").last().after(cloneTr);
$('.datepicker').datepicker();
}

function delTd(obj){
jboxConfirm("确认删除该科室记录？",function(){
var recordFlow = $(obj).closest("tr").find(".recordFlow").val();
jboxPost("<s:url value='/sczyres/doctor/delSchInfo'/>?recordFlow="+recordFlow,null,function(resp){
if(resp==1){
jboxTip("操作成功");
}
},null,false);
var c = $(obj).closest('tr').attr('class');
var rowspan = parseInt($('.'+c+' td').eq(0).attr('rowspan'))-1;
$('.'+c+' td').eq(0).attr('rowspan',rowspan);
$(obj).closest('tr').remove();
})
}
function changeDiv(trainingSpeId){
	if(trainingSpeId=='1'){
		$("#zyDiv").show();
		$("#zyqkDiv").hide();
		$("#zyzlqkDiv").hide();
		$(".zigezheng").show();
		$(".zhulizigezheng").hide();
	}
	if(trainingSpeId=='2'){
		$("#zyDiv").hide();
		$("#zyqkDiv").show();
		$("#zyzlqkDiv").hide();
		$(".zigezheng").show();
		$(".zhulizigezheng").hide();
	}
	if(trainingSpeId=='3'){
		$("#zyDiv").hide();
		$("#zyqkDiv").hide();
		$("#zyzlqkDiv").show();
		$(".zigezheng").hide();
		$(".zhulizigezheng").show();
	}
}

//两个时间相差天数 兼容firefox chrome
function datedifference(sDate1, sDate2) {    //sDate1和sDate2是2006-12-18格式
var dateSpan,
tempDate,
iDays;
sDate1 = Date.parse(sDate1);
sDate2 = Date.parse(sDate2);
dateSpan = sDate2 - sDate1;
dateSpan = Math.abs(dateSpan);
iDays = Math.floor(dateSpan / (24 * 3600 * 1000));
return iDays
};


</script>
<div id="singupContent">
<div id='docTypeForm'>
<%--<form id='doctorForm'>--%>
<p id="errorMsg" style="color: red;"></p>
<input type="hidden" name="userFlow" value="${user.userFlow}"/>
<input type="hidden" name="doctorFlow" value="${doctor.doctorFlow}"/>
<div class="main_bd">
<div class="div_table">
<div class="score_frame">
<h1>培训基地轮转信息表</h1>
<div class="div_table">
<table border="0" cellpadding="0" cellspacing="0" class="base_info">
<colgroup>
<col width="16%"/>
<col width="16%"/>
<col width="16%"/>
<col width="15%"/>
<col width="16%"/>
<col width="16%"/>
</colgroup>
	<tbody>
	<tr>
		<th><font color="red">*</font>所学专业：</th>
		<td>
			<select name="trainingSpeId" id="trainingSpeId" class="select validate[required]"
					style="width: 160px;margin-left: 5px;" onchange="changeDiv(this.value);">
				<option value="">请选择</option>
				<option value="1" ${doctor.trainingSpeId eq '1'?'selected':''}>中医</option>
				<option value="2" ${doctor.trainingSpeId eq '2'?'selected':''}>中医全科</option>
				<option value="3" ${doctor.trainingSpeId eq '3'?'selected':''}>中医助理全科</option>
			</select>
			<input type="hidden" name="trainingSpeName" id="trainingSpeName">
		</td>
		<th><font color="red">*</font>培训基地：</th>
		<td colspan="3">
			<select class="select validate[required]" id="orgFlow" name="orgFlow" style="width:300px;margin-left: 5px;">
				<option value="">请选择</option>
				<c:forEach items="${hospitals}" var="hosptial">
					<option value='${hosptial.orgFlow}' <c:if test='${user.orgFlow eq hosptial.orgFlow}'>selected="selected"</c:if>>${hosptial.orgName}</option>
				</c:forEach>
			</select>
			<input type="hidden" name="orgName" id="orgName">
		</td>
	</tr>
	<tr>
		<th><font color="red">*</font>姓名：</th>
		<td><input type='text' class='input validate[required]' id="userName" name="userName" value="${user.userName}"/></td>
		<th><font color="red">*</font>性别：</th>
		<td>&nbsp;
			<label>
				<input type="radio" class='validate[required]' style="width:auto;" name="sexId" sexName="${userSexEnumMan.name}" value='${userSexEnumMan.id}' ${user.sexId eq userSexEnumMan.id?'checked':''}/>${userSexEnumMan.name}
			</label>
			&nbsp;
			<label>
				<input type="radio" class='validate[required]' style="width:auto;" name="sexId" sexName="${userSexEnumWoman.name}" value='${userSexEnumWoman.id}' ${user.sexId eq userSexEnumWoman.id?'checked':''}/>${userSexEnumWoman.name}
			</label>
			<input type="hidden" name="sexName" id="sexName">
		</td>
		<th><font color="red">*</font>身份证号码：</th>
		<td>
			<input type="text" name="idNo" value="${user.idNo}" class="input validate[required]"/>
		</td>
	</tr>
	<tr>
		<th><font color="red">*</font>学历：</th>
		<td>
			<select name="educationId" id="educationId" class="select validate[required]" style="width: 160px;margin-left: 5px;">
				<option></option>
				<c:forEach items="${dictTypeEnumUserEducationList}" var="dict">
					<option value="${dict.dictId}" ${user.educationId eq dict.dictId?'selected':''}>${dict.dictName}</option>
				</c:forEach>
			</select>
			<input type="hidden" name="educationName" id="educationName">
		</td>
		<th>学位：</th>
		<td>
			<select name="degreeId" id="degreeId" class="select" style="width: 120px;margin-left: 5px;">
				<option></option>
				<c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
					<option value="${dict.dictId}" ${user.degreeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
				</c:forEach>
			</select>
			<input type="hidden" name="degreeName" id="degreeName">
		</td>
		<th><font color="red">*</font>学员身份：</th>
		<td>
			<select name="doctorTypeId" id="doctorTypeId" class="select validate[required]"
					style="width: 160px;margin-left: 5px;">
				<option value="">请选择</option>
				<c:forEach items="${sczyRecDocTypeEnumList}" var="doctorType">
					<option value="${doctorType.id}" ${doctorType.id eq doctor.doctorTypeId?'selected':''}>${doctorType.name}</option>
				</c:forEach>
			</select>
			<input type="hidden" name="doctorTypeName" id="doctorTypeName"/>
		</td>
	</tr>
	<tr>
		<th class="zigezheng"><font color="red">*</font>医师资格证书&#12288;<br/>编号：</th>
		<td class="zigezheng">
			<input type="text" class="input validate[required]" name="doctorLicenseNo" value="${doctor.doctorLicenseNo}">
		</td>
		<th class="zhulizigezheng"><font color="red">*</font>助理医师资格&#12288;<br/>证书编号：</th>
		<td class="zhulizigezheng">
			<input type="text" class="input validate[required]" name="assistantQualificationCertificateCode" value="${extInfo.assistantQualificationCertificateCode}">
		</td>
		<th><font color="red">*</font>培训起止时间：</th>
		<td colspan="3">
			<input type="text" class="input validate[required] datepicker" style="width: 120px;" name="trainingStartDate" value="${extInfo.trainingStartDate}"/>
			~
			<input type="text" class="input validate[required] datepicker" style="width: 120px;" name="trainingEndDate" value="${extInfo.trainingEndDate}"/>
		</td>
	</tr>
	</tbody>
</table>
</div>

<div class="div_table" ID="zyqkDiv" style="display: none;">
<h4>轮转情况</h4>
<table border="0" cellpadding="0" cellspacing="0" class="base_info">
<colgroup>
<col width="30%"/>
<col width="25%"/>
<col width="35%"/>
</colgroup>
<tbody>
<tr>
<th style="text-align: center">学科名称</th>
<th style="text-align: center">科室</th>
<th style="text-align: center">时间</th>
</tr>
<c:if test="${empty resultMap['llxx']}">
	<tr class="llxx">
	<td rowspan="1" style="text-align: center" class="llxx">理论学习(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
	<td style="text-align: center">
	&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;">
	</td>
	<td style="text-align: center">
	<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" name="" value=""/>
	~
	<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" name="" value=""/>
	</td>
	</tr>
</c:if>
<c:if test="${not empty resultMap['llxx']}">
	<c:forEach items="${resultMap['llxx']}" var="result" varStatus="s">
		<c:if test="${s.index eq 0}">
			<tr class="llxx">
			<td rowspan="${resultMap['llxx'].size()}" style="text-align: center" class="llxx">理论学习(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
			<td style="text-align: center">
			&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
		<c:if test="${s.index ne 0}">
			<tr class="llxx">
			<td style="text-align: center">
			<a onclick="delTd(this)">删除</a>
			<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
	</c:forEach>
</c:if>
<c:if test="${empty resultMap['zynk']}">
	<tr class="zynk">
	<td rowspan="1" style="text-align: center" class="zynk">中医内科(10个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
	<td style="text-align: center">
	&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;">
	</td>
	<td style="text-align: center">
	<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" name="" value=""/>
	~
	<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" name="" value=""/>
	</td>
	</tr>
</c:if>
<c:if test="${not empty resultMap['zynk']}">
	<c:forEach items="${resultMap['zynk']}" var="result" varStatus="s">
		<c:if test="${s.index eq 0}">
			<tr class="zynk">
			<td rowspan="${resultMap['zynk'].size()}" style="text-align: center" class="zynk">中医内科(10个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
			<td style="text-align: center">
			&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
		<c:if test="${s.index ne 0}">
			<tr class="zynk">
			<td style="text-align: center">
			<a onclick="delTd(this)">删除</a>
			<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
	</c:forEach>
</c:if>

<c:if test="${empty resultMap['zywk']}">
	<tr class="zywk">
	<td rowspan="1" style="text-align: center" class="zywk">中医外科(2个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
	<td style="text-align: center">
	&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;">
	</td>
	<td style="text-align: center">
	<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" name="" value=""/>
	~
	<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" name="" value=""/>
	</td>
	</tr>
</c:if>
<c:if test="${not empty resultMap['zywk']}">
	<c:forEach items="${resultMap['zywk']}" var="result" varStatus="s">
		<c:if test="${s.index eq 0}">
			<tr class="zywk">
			<td rowspan="${resultMap['zywk'].size()}" style="text-align: center" class="zywk">中医外科(2个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
			<td style="text-align: center">
			&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
		<c:if test="${s.index ne 0}">
			<tr class="zywk">
			<td style="text-align: center">
			<a onclick="delTd(this)">删除</a>
			<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
	</c:forEach>
</c:if>


<c:if test="${empty resultMap['zyfk']}">
	<tr class="zyfk">
	<td rowspan="1" style="text-align: center" class="zyfk">中医妇科(1个月))<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
	<td style="text-align: center">
	&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;">
	</td>
	<td style="text-align: center">
	<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" name="" value=""/>
	~
	<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" name="" value=""/>
	</td>
	</tr>
</c:if>
<c:if test="${not empty resultMap['zyfk']}">
	<c:forEach items="${resultMap['zyfk']}" var="result" varStatus="s">
		<c:if test="${s.index eq 0}">
			<tr class="zyfk">
			<td rowspan="${resultMap['zyfk'].size()}" style="text-align: center" class="zyfk">中医妇科(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
			<td style="text-align: center">
			&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
		<c:if test="${s.index ne 0}">
			<tr class="zyfk">
			<td style="text-align: center">
			<a onclick="delTd(this)">删除</a>
			<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
	</c:forEach>
</c:if>


<c:if test="${empty resultMap['zyek']}">
	<tr class="zyek">
	<td rowspan="1" style="text-align: center" class="zyek">中医儿科(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
	<td style="text-align: center">
	&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;">
	</td>
	<td style="text-align: center">
	<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" name="" value=""/>
	~
	<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" name="" value=""/>
	</td>
	</tr>
</c:if>
<c:if test="${not empty resultMap['zyek']}">
	<c:forEach items="${resultMap['zyek']}" var="result" varStatus="s">
		<c:if test="${s.index eq 0}">
			<tr class="zyek">
			<td rowspan="${resultMap['zyek'].size()}" style="text-align: center" class="zyek">中医儿科(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
			<td style="text-align: center">
			&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
		<c:if test="${s.index ne 0}">
			<tr class="zyek">
			<td style="text-align: center">
			<a onclick="delTd(this)">删除</a>
			<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
	</c:forEach>
</c:if>


<c:if test="${empty resultMap['zjk']}">
	<tr class="zjk">
	<td rowspan="1" style="text-align: center" class="zjk">针灸科(2个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
	<td style="text-align: center">
	&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;">
	</td>
	<td style="text-align: center">
	<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" name="" value=""/>
	~
	<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" name="" value=""/>
	</td>
	</tr>
</c:if>
<c:if test="${not empty resultMap['zjk']}">
	<c:forEach items="${resultMap['zjk']}" var="result" varStatus="s">
		<c:if test="${s.index eq 0}">
			<tr class="zjk">
			<td rowspan="${resultMap['zjk'].size()}" style="text-align: center" class="zjk">针灸科(2个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
			<td style="text-align: center">
			&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
		<c:if test="${s.index ne 0}">
			<tr class="zjk">
			<td style="text-align: center">
			<a onclick="delTd(this)">删除</a>
			<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
	</c:forEach>
</c:if>


<c:if test="${empty resultMap['tnk']}">
	<tr class="tnk">
	<td rowspan="1" style="text-align: center" class="tnk">推拿科(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
	<td style="text-align: center">
	&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;">
	</td>
	<td style="text-align: center">
	<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" name="" value=""/>
	~
	<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" name="" value=""/>
	</td>
	</tr>
</c:if>
<c:if test="${not empty resultMap['tnk']}">
	<c:forEach items="${resultMap['tnk']}" var="result" varStatus="s">
		<c:if test="${s.index eq 0}">
			<tr class="tnk">
			<td rowspan="${resultMap['tnk'].size()}" style="text-align: center" class="tnk">推拿科(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
			<td style="text-align: center">
			&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
		<c:if test="${s.index ne 0}">
			<tr class="tnk">
			<td style="text-align: center">
			<a onclick="delTd(this)">删除</a>
			<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
	</c:forEach>
</c:if>


<c:if test="${empty resultMap['zykfk']}">
	<tr class="zykfk">
	<td rowspan="1" style="text-align: center" class="zykfk">中医康复科(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
	<td style="text-align: center">
	&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;">
	</td>
	<td style="text-align: center">
	<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" name="" value=""/>
	~
	<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" name="" value=""/>
	</td>
	</tr>
</c:if>
<c:if test="${not empty resultMap['zykfk']}">
	<c:forEach items="${resultMap['zykfk']}" var="result" varStatus="s">
		<c:if test="${s.index eq 0}">
			<tr class="zykfk">
			<td rowspan="${resultMap['zykfk'].size()}" style="text-align: center" class="zykfk">中医康复科(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
			<td style="text-align: center">
			&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
		<c:if test="${s.index ne 0}">
			<tr class="zykfk">
			<td style="text-align: center">
			<a onclick="delTd(this)">删除</a>
			<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
	</c:forEach>
</c:if>


<c:if test="${empty resultMap['zygsk']}">
	<tr class="zygsk">
	<td rowspan="1" style="text-align: center" class="zygsk">中医骨伤科(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
	<td style="text-align: center">
	&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;">
	</td>
	<td style="text-align: center">
	<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" name="" value=""/>
	~
	<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" name="" value=""/>
	</td>
	</tr>
</c:if>
<c:if test="${not empty resultMap['zygsk']}">
	<c:forEach items="${resultMap['zygsk']}" var="result" varStatus="s">
		<c:if test="${s.index eq 0}">
			<tr class="zygsk">
			<td rowspan="${resultMap['zygsk'].size()}" style="text-align: center" class="zygsk">中医骨伤科(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
			<td style="text-align: center">
			&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
		<c:if test="${s.index ne 0}">
			<tr class="zygsk">
			<td style="text-align: center">
			<a onclick="delTd(this)">删除</a>
			<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
	</c:forEach>
</c:if>


<c:if test="${empty resultMap['zyebhk']}">
	<tr class="zyebhk">
	<td rowspan="1" style="text-align: center" class="zyebhk">中医耳鼻喉科(与眼科共1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
	<td style="text-align: center">
	&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;">
	</td>
	<td style="text-align: center">
	<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" name="" value=""/>
	~
	<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" name="" value=""/>
	</td>
	</tr>
</c:if>
<c:if test="${not empty resultMap['zyebhk']}">
	<c:forEach items="${resultMap['zyebhk']}" var="result" varStatus="s">
		<c:if test="${s.index eq 0}">
			<tr class="zyebhk">
			<td rowspan="${resultMap['zyebhk'].size()}" style="text-align: center" class="zyebhk">中医耳鼻喉科(与眼科共1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
			<td style="text-align: center">
			&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
		<c:if test="${s.index ne 0}">
			<tr class="zyebhk">
			<td style="text-align: center">
			<a onclick="delTd(this)">删除</a>
			<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
	</c:forEach>
</c:if>


<c:if test="${empty resultMap['zyyk']}">
	<tr class="zyyk">
	<td rowspan="1" style="text-align: center" class="zyyk">中医眼科(与耳鼻喉科共1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
	<td style="text-align: center">
	&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;">
	</td>
	<td style="text-align: center">
	<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" name="" value=""/>
	~
	<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" name="" value=""/>
	</td>
	</tr>
</c:if>
<c:if test="${not empty resultMap['zyyk']}">
	<c:forEach items="${resultMap['zyyk']}" var="result" varStatus="s">
		<c:if test="${s.index eq 0}">
			<tr class="zyyk">
			<td rowspan="${resultMap['zyyk'].size()}" style="text-align: center" class="zyyk">中医眼科(与耳鼻喉科共1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
			<td style="text-align: center">
			&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
		<c:if test="${s.index ne 0}">
			<tr class="zyyk">
			<td style="text-align: center">
			<a onclick="delTd(this)">删除</a>
			<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
	</c:forEach>
</c:if>


<c:if test="${empty resultMap['zk']}">
	<tr class="zk">
	<td rowspan="1" style="text-align: center" class="zk">诊科（包括院前急救）(3个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
	<td style="text-align: center">
	&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;">
	</td>
	<td style="text-align: center">
	<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" name="" value=""/>
	~
	<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" name="" value=""/>
	</td>
	</tr>
</c:if>
<c:if test="${not empty resultMap['zk']}">
	<c:forEach items="${resultMap['zk']}" var="result" varStatus="s">
		<c:if test="${s.index eq 0}">
			<tr class="zk">
			<td rowspan="${resultMap['zk'].size()}" style="text-align: center" class="zk">诊科（包括院前急救）(3个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
			<td style="text-align: center">
			&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
		<c:if test="${s.index ne 0}">
			<tr class="zk">
			<td style="text-align: center">
			<a onclick="delTd(this)">删除</a>
			<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
	</c:forEach>
</c:if>


<c:if test="${empty resultMap['pwk']}">
	<tr class="pwk">
	<td rowspan="1" style="text-align: center" class="pwk">普外科(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
	<td style="text-align: center">
	&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;">
	</td>
	<td style="text-align: center">
	<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" name="" value=""/>
	~
	<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" name="" value=""/>
	</td>
	</tr>
</c:if>
<c:if test="${not empty resultMap['pwk']}">
	<c:forEach items="${resultMap['pwk']}" var="result" varStatus="s">
		<c:if test="${s.index eq 0}">
			<tr class="pwk">
			<td rowspan="${resultMap['pwk'].size()}" style="text-align: center" class="pwk">普外科(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
			<td style="text-align: center">
			&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
		<c:if test="${s.index ne 0}">
			<tr class="pwk">
			<td style="text-align: center">
			<a onclick="delTd(this)">删除</a>
			<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
	</c:forEach>
</c:if>


<c:if test="${empty resultMap['fzks']}">
	<tr class="fzks">
	<td rowspan="1" style="text-align: center" class="fzks">辅助科室(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
	<td style="text-align: center">
	&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;">
	</td>
	<td style="text-align: center">
	<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" name="" value=""/>
	~
	<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" name="" value=""/>
	</td>
	</tr>
</c:if>
<c:if test="${not empty resultMap['fzks']}">
	<c:forEach items="${resultMap['fzks']}" var="result" varStatus="s">
		<c:if test="${s.index eq 0}">
			<tr class="fzks">
			<td rowspan="${resultMap['fzks'].size()}" style="text-align: center" class="fzks">辅助科室(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
			<td style="text-align: center">
			&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
		<c:if test="${s.index ne 0}">
			<tr class="fzks">
			<td style="text-align: center">
			<a onclick="delTd(this)">删除</a>
			<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
	</c:forEach>
</c:if>


<c:if test="${empty resultMap['xx']}">
	<tr class="xx">
	<td rowspan="1" style="text-align: center" class="xx">选修(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
	<td style="text-align: center">
	&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;">
	</td>
	<td style="text-align: center">
	<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" name="" value=""/>
	~
	<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" name="" value=""/>
	</td>
	</tr>
</c:if>
<c:if test="${not empty resultMap['xx']}">
	<c:forEach items="${resultMap['xx']}" var="result" varStatus="s">
		<c:if test="${s.index eq 0}">
			<tr class="xx">
			<td rowspan="${resultMap['xx'].size()}" style="text-align: center" class="xx">选修(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
			<td style="text-align: center">
			&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
		<c:if test="${s.index ne 0}">
			<tr class="xx">
			<td style="text-align: center">
			<a onclick="delTd(this)">删除</a>
			<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
	</c:forEach>
</c:if>


<c:if test="${empty resultMap['gsxx']}">
	<tr class="gsxx">
	<td rowspan="1" style="text-align: center" class="gsxx">跟师学习(半天/周)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
	<td style="text-align: center">
	&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;">
	</td>
	<td style="text-align: center">
	<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" name="" value=""/>
	~
	<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" name="" value=""/>
	</td>
	</tr>
</c:if>
<c:if test="${not empty resultMap['gsxx']}">
	<c:forEach items="${resultMap['gsxx']}" var="result" varStatus="s">
		<c:if test="${s.index eq 0}">
			<tr class="gsxx">
			<td rowspan="${resultMap['gsxx'].size()}" style="text-align: center" class="gsxx">跟师学习(半天/周)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
			<td style="text-align: center">
			&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
		<c:if test="${s.index ne 0}">
			<tr class="gsxx">
			<td style="text-align: center">
			<a onclick="delTd(this)">删除</a>
			<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
	</c:forEach>
</c:if>


<c:if test="${empty resultMap['jcsj']}">
	<tr class="jcsj">
	<td rowspan="1" style="text-align: center" class="jcsj">基层实践(6个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
	<td style="text-align: center">
	&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;">
	</td>
	<td style="text-align: center">
	<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" name="" value=""/>
	~
	<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" name="" value=""/>
	</td>
	</tr>
</c:if>
<c:if test="${not empty resultMap['jcsj']}">
	<c:forEach items="${resultMap['jcsj']}" var="result" varStatus="s">
		<c:if test="${s.index eq 0}">
			<tr class="jcsj">
			<td rowspan="${resultMap['jcsj'].size()}" style="text-align: center" class="jcsj">基层实践(6个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
			<td style="text-align: center">
			&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
		<c:if test="${s.index ne 0}">
			<tr class="jcsj">
			<td style="text-align: center">
			<a onclick="delTd(this)">删除</a>
			<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
	</c:forEach>
</c:if>

<c:if test="${empty resultMap['jdks']}">
	<tr class="jdks">
		<td rowspan="1" style="text-align: center" class="jdks">机动科室<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
		<td style="text-align: center">
			&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
		</td>
		<td style="text-align: center">
			<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
			~
			<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
		</td>
	</tr>
</c:if>
<c:if test="${not empty resultMap['jdks']}">
	<c:forEach items="${resultMap['jdks']}" var="result" varStatus="s">
		<c:if test="${s.index eq 0}">
			<tr class="jdks">
				<td rowspan="${resultMap['jdks'].size()}" style="text-align: center" class="jdks">机动科室<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
				<td style="text-align: center">
					&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
				</td>
				<td style="text-align: center">
					<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
					~
					<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
				</td>
				<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
		<c:if test="${s.index ne 0}">
			<tr class="jdks">
				<td style="text-align: center">
					<a onclick="delTd(this)">删除</a>
					<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
				</td>
				<td style="text-align: center">
					<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
					~
					<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
				</td>
				<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
	</c:forEach>
</c:if>
<tr>
<td style="text-align: center">合计(33个月)</td>
<td class="sum" style="text-align: center" colspan="2"></td>
</tr>
<tr>
<td colspan="3">
对照《住院医师规范化培训细则》，本人培训内容完成情况如上，申请参加
<input class="input validate[required]" id="graduationYear" value="${graduationYear}" readonly="readonly" style="width: 120px;">
年度结业考试资格审核。
</td>
</tr>
</tbody>
</table>
</div>

	<div class="div_table" ID="zyzlqkDiv" style="display: none">
		<h4>轮转情况</h4>
		<table border="0" cellpadding="0" cellspacing="0" class="base_info">
			<colgroup>
				<col width="30%"/>
				<col width="25%"/>
				<col width="35%"/>
			</colgroup>
			<tbody>
			<tr>
				<th style="text-align: center">学科名称</th>
				<th style="text-align: center">科室</th>
				<th style="text-align: center">时间</th>
			</tr>


			<c:if test="${empty resultMap['zynk']}">
				<tr class="zynk">
					<td rowspan="1" style="text-align: center" class="zynk">中医内科（心血管8周，呼吸（肺病）6周，
						消化（脾胃病）4周，内分泌代谢4周，泌尿（肾病）4周，血液或肿瘤4周，风湿4周，神经4周）（38周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
					<td style="text-align: center">
						&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
					</td>
					<td style="text-align: center">
						<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
						~
						<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
					</td>
				</tr>
			</c:if>
			<c:if test="${not empty resultMap['zynk']}">
				<c:forEach items="${resultMap['zynk']}" var="result" varStatus="s">
					<c:if test="${s.index eq 0}">
						<tr class="zynk">
							<td rowspan="${resultMap['zynk'].size()}" style="text-align: center" class="zynk">中医内科（心血管8周，呼吸（肺病）6周，
								消化（脾胃病）4周，内分泌代谢4周，泌尿（肾病）4周，血液或肿瘤4周，风湿4周，神经4周）（38周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							<td style="text-align: center">
								&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
							</td>
							<input type="hidden" class="recordFlow" value="${result.recordFlow}">
						</tr>
					</c:if>
					<c:if test="${s.index ne 0}">
						<tr class="zynk">
							<td style="text-align: center">
								<a onclick="delTd(this)">删除</a>
								<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
							</td>
							<input type="hidden" class="recordFlow" value="${result.recordFlow}">
						</tr>
					</c:if>
				</c:forEach>
			</c:if>

			<c:if test="${empty resultMap['zywk']}">
				<tr class="zywk">
					<td rowspan="1" style="text-align: center" class="zywk">急诊科（含院前急救）（8周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
					<td style="text-align: center">
						&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
					</td>
					<td style="text-align: center">
						<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
						~
						<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
					</td>
				</tr>
			</c:if>
			<c:if test="${not empty resultMap['zywk']}">
				<c:forEach items="${resultMap['zywk']}" var="result" varStatus="s">
					<c:if test="${s.index eq 0}">
						<tr class="zywk">
							<td rowspan="${resultMap['zywk'].size()}" style="text-align: center" class="zywk">急诊科（含院前急救）（8周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							<td style="text-align: center">
								&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
							</td>
							<input type="hidden" class="recordFlow" value="${result.recordFlow}">
						</tr>
					</c:if>
					<c:if test="${s.index ne 0}">
						<tr class="zywk">
							<td style="text-align: center">
								<a onclick="delTd(this)">删除</a>
								<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
							</td>
							<input type="hidden" class="recordFlow" value="${result.recordFlow}">
						</tr>
					</c:if>
				</c:forEach>
			</c:if>


			<c:if test="${empty resultMap['zyfk']}">
				<tr class="zyfk">
					<td rowspan="1" style="text-align: center" class="zyfk">中医外科（6周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
					<td style="text-align: center">
						&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
					</td>
					<td style="text-align: center">
						<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
						~
						<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
					</td>
				</tr>
			</c:if>
			<c:if test="${not empty resultMap['zyfk']}">
				<c:forEach items="${resultMap['zyfk']}" var="result" varStatus="s">
					<c:if test="${s.index eq 0}">
						<tr class="zyfk">
							<td rowspan="${resultMap['zyfk'].size()}" style="text-align: center" class="zyfk">中医外科（6周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							<td style="text-align: center">
								&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
							</td>
							<input type="hidden" class="recordFlow" value="${result.recordFlow}">
						</tr>
					</c:if>
					<c:if test="${s.index ne 0}">
						<tr class="zyfk">
							<td style="text-align: center">
								<a onclick="delTd(this)">删除</a>
								<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
							</td>
							<input type="hidden" class="recordFlow" value="${result.recordFlow}">
						</tr>
					</c:if>
				</c:forEach>
			</c:if>


			<c:if test="${empty resultMap['zyek']}">
				<tr class="zyek">
					<td rowspan="1" style="text-align: center" class="zyek">中医妇科（4周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
					<td style="text-align: center">
						&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
					</td>
					<td style="text-align: center">
						<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
						~
						<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
					</td>
				</tr>
			</c:if>
			<c:if test="${not empty resultMap['zyek']}">
				<c:forEach items="${resultMap['zyek']}" var="result" varStatus="s">
					<c:if test="${s.index eq 0}">
						<tr class="zyek">
							<td rowspan="${resultMap['zyek'].size()}" style="text-align: center" class="zyek">中医妇科（4周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							<td style="text-align: center">
								&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
							</td>
							<input type="hidden" class="recordFlow" value="${result.recordFlow}">
						</tr>
					</c:if>
					<c:if test="${s.index ne 0}">
						<tr class="zyek">
							<td style="text-align: center">
								<a onclick="delTd(this)">删除</a>
								<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
							</td>
							<input type="hidden" class="recordFlow" value="${result.recordFlow}">
						</tr>
					</c:if>
				</c:forEach>
			</c:if>


			<c:if test="${empty resultMap['zjk']}">
				<tr class="zjk">
					<td rowspan="1" style="text-align: center" class="zjk">中医儿科（4周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
					<td style="text-align: center">
						&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
					</td>
					<td style="text-align: center">
						<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
						~
						<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
					</td>
				</tr>
			</c:if>
			<c:if test="${not empty resultMap['zjk']}">
				<c:forEach items="${resultMap['zjk']}" var="result" varStatus="s">
					<c:if test="${s.index eq 0}">
						<tr class="zjk">
							<td rowspan="${resultMap['zjk'].size()}" style="text-align: center" class="zjk">中医儿科（4周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							<td style="text-align: center">
								&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
							</td>
							<input type="hidden" class="recordFlow" value="${result.recordFlow}">
						</tr>
					</c:if>
					<c:if test="${s.index ne 0}">
						<tr class="zjk">
							<td style="text-align: center">
								<a onclick="delTd(this)">删除</a>
								<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
							</td>
							<input type="hidden" class="recordFlow" value="${result.recordFlow}">
						</tr>
					</c:if>
				</c:forEach>
			</c:if>


			<c:if test="${empty resultMap['tnk']}">
				<tr class="tnk">
					<td rowspan="1" style="text-align: center" class="tnk">针灸科<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
					<td style="text-align: center">
						&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
					</td>
					<td style="text-align: center">
						<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
						~
						<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
					</td>
				</tr>
			</c:if>
			<c:if test="${not empty resultMap['tnk']}">
				<c:forEach items="${resultMap['tnk']}" var="result" varStatus="s">
					<c:if test="${s.index eq 0}">
						<tr class="tnk">
							<td rowspan="${resultMap['tnk'].size()}" style="text-align: center" class="tnk">针灸科<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							<td style="text-align: center">
								&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
							</td>
							<input type="hidden" class="recordFlow" value="${result.recordFlow}">
						</tr>
					</c:if>
					<c:if test="${s.index ne 0}">
						<tr class="tnk">
							<td style="text-align: center">
								<a onclick="delTd(this)">删除</a>
								<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
							</td>
							<input type="hidden" class="recordFlow" value="${result.recordFlow}">
						</tr>
					</c:if>
				</c:forEach>
			</c:if>


			<c:if test="${empty resultMap['zykfk']}">
				<tr class="zykfk">
					<td rowspan="1" style="text-align: center" class="zykfk">推拿科（10周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
					<td style="text-align: center">
						&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
					</td>
					<td style="text-align: center">
						<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
						~
						<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
					</td>
				</tr>
			</c:if>
			<c:if test="${not empty resultMap['zykfk']}">
				<c:forEach items="${resultMap['zykfk']}" var="result" varStatus="s">
					<c:if test="${s.index eq 0}">
						<tr class="zykfk">
							<td rowspan="${resultMap['zykfk'].size()}" style="text-align: center" class="zykfk">推拿科（10周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							<td style="text-align: center">
								&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
							</td>
							<input type="hidden" class="recordFlow" value="${result.recordFlow}">
						</tr>
					</c:if>
					<c:if test="${s.index ne 0}">
						<tr class="zykfk">
							<td style="text-align: center">
								<a onclick="delTd(this)">删除</a>
								<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
							</td>
							<input type="hidden" class="recordFlow" value="${result.recordFlow}">
						</tr>
					</c:if>
				</c:forEach>
			</c:if>


			<c:if test="${empty resultMap['zygsk']}">
				<tr class="zygsk">
					<td rowspan="1" style="text-align: center" class="zygsk">中医康复科<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
					<td style="text-align: center">
						&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
					</td>
					<td style="text-align: center">
						<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
						~
						<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
					</td>
				</tr>
			</c:if>
			<c:if test="${not empty resultMap['zygsk']}">
				<c:forEach items="${resultMap['zygsk']}" var="result" varStatus="s">
					<c:if test="${s.index eq 0}">
						<tr class="zygsk">
							<td rowspan="${resultMap['zygsk'].size()}" style="text-align: center" class="zygsk">中医康复科<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							<td style="text-align: center">
								&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
							</td>
							<input type="hidden" class="recordFlow" value="${result.recordFlow}">
						</tr>
					</c:if>
					<c:if test="${s.index ne 0}">
						<tr class="zygsk">
							<td style="text-align: center">
								<a onclick="delTd(this)">删除</a>
								<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
							</td>
							<input type="hidden" class="recordFlow" value="${result.recordFlow}">
						</tr>
					</c:if>
				</c:forEach>
			</c:if>


			<c:if test="${empty resultMap['zyebhk']}">
				<tr class="zyebhk">
					<td rowspan="1" style="text-align: center" class="zyebhk">中医骨伤科（4周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
					<td style="text-align: center">
						&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
					</td>
					<td style="text-align: center">
						<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
						~
						<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
					</td>
				</tr>
			</c:if>
			<c:if test="${not empty resultMap['zyebhk']}">
				<c:forEach items="${resultMap['zyebhk']}" var="result" varStatus="s">
					<c:if test="${s.index eq 0}">
						<tr class="zyebhk">
							<td rowspan="${resultMap['zyebhk'].size()}" style="text-align: center" class="zyebhk">中医骨伤科（4周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							<td style="text-align: center">
								&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
							</td>
							<input type="hidden" class="recordFlow" value="${result.recordFlow}">
						</tr>
					</c:if>
					<c:if test="${s.index ne 0}">
						<tr class="zyebhk">
							<td style="text-align: center">
								<a onclick="delTd(this)">删除</a>
								<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
							</td>
							<input type="hidden" class="recordFlow" value="${result.recordFlow}">
						</tr>
					</c:if>
				</c:forEach>
			</c:if>


			<c:if test="${empty resultMap['zyyk']}">
				<tr class="zyyk">
					<td rowspan="1" style="text-align: center" class="zyyk">中医眼科(2周)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
					<td style="text-align: center">
						&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
					</td>
					<td style="text-align: center">
						<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
						~
						<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
					</td>
				</tr>
			</c:if>
			<c:if test="${not empty resultMap['zyyk']}">
				<c:forEach items="${resultMap['zyyk']}" var="result" varStatus="s">
					<c:if test="${s.index eq 0}">
						<tr class="zyyk">
							<td rowspan="${resultMap['zyyk'].size()}" style="text-align: center" class="zyyk">中医眼科(2周)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							<td style="text-align: center">
								&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
							</td>
							<input type="hidden" class="recordFlow" value="${result.recordFlow}">
						</tr>
					</c:if>
					<c:if test="${s.index ne 0}">
						<tr class="zyyk">
							<td style="text-align: center">
								<a onclick="delTd(this)">删除</a>
								<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
							</td>
							<input type="hidden" class="recordFlow" value="${result.recordFlow}">
						</tr>
					</c:if>
				</c:forEach>
			</c:if>


			<c:if test="${empty resultMap['fzks']}">
				<tr class="fzks">
					<td rowspan="1" style="text-align: center" class="fzks">中医耳鼻咽喉科（2周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
					<td style="text-align: center">
						&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
					</td>
					<td style="text-align: center">
						<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
						~
						<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
					</td>
				</tr>
			</c:if>
			<c:if test="${not empty resultMap['fzks']}">
				<c:forEach items="${resultMap['fzks']}" var="result" varStatus="s">
					<c:if test="${s.index eq 0}">
						<tr class="fzks">
							<td rowspan="${resultMap['fzks'].size()}" style="text-align: center" class="fzks">中医耳鼻咽喉科（2周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							<td style="text-align: center">
								&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
							</td>
							<input type="hidden" class="recordFlow" value="${result.recordFlow}">
						</tr>
					</c:if>
					<c:if test="${s.index ne 0}">
						<tr class="fzks">
							<td style="text-align: center">
								<a onclick="delTd(this)">删除</a>
								<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
							</td>
							<input type="hidden" class="recordFlow" value="${result.recordFlow}">
						</tr>
					</c:if>
				</c:forEach>
			</c:if>


			<c:if test="${empty resultMap['lchl2']}">
				<tr class="lchl2">
					<td rowspan="1" style="text-align: center" class="lchl2">临床护理（2周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
					<td style="text-align: center">
						&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
					</td>
					<td style="text-align: center">
						<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
						~
						<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
					</td>
				</tr>
			</c:if>
			<c:if test="${not empty resultMap['lchl2']}">
				<c:forEach items="${resultMap['lchl2']}" var="result" varStatus="s">
					<c:if test="${s.index eq 0}">
						<tr class="lchl2">
							<td rowspan="${resultMap['lchl2'].size()}" style="text-align: center" class="lchl2">临床护理（2周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							<td style="text-align: center">
								&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
							</td>
							<input type="hidden" class="recordFlow" value="${result.recordFlow}">
						</tr>
					</c:if>
					<c:if test="${s.index ne 0}">
						<tr class="lchl2">
							<td style="text-align: center">
								<a onclick="delTd(this)">删除</a>
								<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
							</td>
							<input type="hidden" class="recordFlow" value="${result.recordFlow}">
						</tr>
					</c:if>
				</c:forEach>
			</c:if>


			<c:if test="${empty resultMap['xgzk']}">
				<tr class="xgzk">
					<td rowspan="1" style="text-align: center" class="xgzk">辅助科室（2周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
					<td style="text-align: center">
						&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
					</td>
					<td style="text-align: center">
						<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
						~
						<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
					</td>
				</tr>
			</c:if>
			<c:if test="${not empty resultMap['xgzk']}">
				<c:forEach items="${resultMap['xgzk']}" var="result" varStatus="s">
					<c:if test="${s.index eq 0}">
						<tr class="xgzk">
							<td rowspan="${resultMap['xgzk'].size()}" style="text-align: center" class="xgzk">辅助科室（2周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							<td style="text-align: center">
								&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
							</td>
							<input type="hidden" class="recordFlow" value="${result.recordFlow}">
						</tr>
					</c:if>
					<c:if test="${s.index ne 0}">
						<tr class="xgzk">
							<td style="text-align: center">
								<a onclick="delTd(this)">删除</a>
								<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
							</td>
							<input type="hidden" class="recordFlow" value="${result.recordFlow}">
						</tr>
					</c:if>
				</c:forEach>
			</c:if>

			<c:if test="${empty resultMap['gsxx']}">
				<tr class="gsxx">
					<td rowspan="1" style="text-align: center" class="gsxx">跟师学习<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
					<td style="text-align: center">
						&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
					</td>
					<td style="text-align: center">
						<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
						~
						<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
					</td>
				</tr>
			</c:if>
			<c:if test="${not empty resultMap['gsxx']}">
				<c:forEach items="${resultMap['gsxx']}" var="result" varStatus="s">
					<c:if test="${s.index eq 0}">
						<tr class="gsxx">
							<td rowspan="${resultMap['gsxx'].size()}" style="text-align: center" class="gsxx">跟师学习<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							<td style="text-align: center">
								&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
							</td>
							<input type="hidden" class="recordFlow" value="${result.recordFlow}">
						</tr>
					</c:if>
					<c:if test="${s.index ne 0}">
						<tr class="gsxx">
							<td style="text-align: center">
								<a onclick="delTd(this)">删除</a>
								<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
							</td>
							<input type="hidden" class="recordFlow" value="${result.recordFlow}">
						</tr>
					</c:if>
				</c:forEach>
			</c:if>

			<c:if test="${empty resultMap['jcsj']}">
				<tr class="jcsj">
					<td rowspan="1" style="text-align: center" class="jcsj">基层实践（16周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
					<td style="text-align: center">
						&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
					</td>
					<td style="text-align: center">
						<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
						~
						<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
					</td>
				</tr>
			</c:if>
			<c:if test="${not empty resultMap['jcsj']}">
				<c:forEach items="${resultMap['jcsj']}" var="result" varStatus="s">
					<c:if test="${s.index eq 0}">
						<tr class="jcsj">
							<td rowspan="${resultMap['jcsj'].size()}" style="text-align: center" class="jcsj">基层实践（16周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							<td style="text-align: center">
								&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
							</td>
							<input type="hidden" class="recordFlow" value="${result.recordFlow}">
						</tr>
					</c:if>
					<c:if test="${s.index ne 0}">
						<tr class="jcsj">
							<td style="text-align: center">
								<a onclick="delTd(this)">删除</a>
								<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
							</td>
							<input type="hidden" class="recordFlow" value="${result.recordFlow}">
						</tr>
					</c:if>
				</c:forEach>
			</c:if>

			<tr>
				<td style="text-align: center;">合计(98周)</td>
				<td class="sum" style="text-align: center" colspan="2"></td>
			</tr>
			<tr>
				<td colspan="3">
					对照《住院医师规范化培训细则》，本人培训内容完成情况如上，申请参加
					<input class="input validate[required]" id="graduationYear" value="${graduationYear}" readonly="readonly" style="width: 120px;">
					年度结业考试资格审核。
				</td>
			</tr>
			</tbody>
		</table>
	</div>

<div class="div_table" ID="zyDiv" style="display: none">
<h4>轮转情况</h4>
<table border="0" cellpadding="0" cellspacing="0" class="base_info">
<colgroup>
<col width="30%"/>
<col width="25%"/>
<col width="35%"/>
</colgroup>
<tbody>
<tr>
<th style="text-align: center">学科名称</th>
<th style="text-align: center">科室</th>
<th style="text-align: center">时间</th>
</tr>


<c:if test="${empty resultMap['zynk']}">
	<tr class="zynk">
	<td rowspan="1" style="text-align: center" class="zynk">中医内科(12个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
	<td style="text-align: center">
	&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;">
	</td>
	<td style="text-align: center">
	<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" name="" value=""/>
	~
	<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" name="" value=""/>
	</td>
	</tr>
</c:if>
<c:if test="${not empty resultMap['zynk']}">
	<c:forEach items="${resultMap['zynk']}" var="result" varStatus="s">
		<c:if test="${s.index eq 0}">
			<tr class="zynk">
			<td rowspan="${resultMap['zynk'].size()}" style="text-align: center" class="zynk">中医内科(12个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
			<td style="text-align: center">
			&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
		<c:if test="${s.index ne 0}">
			<tr class="zynk">
			<td style="text-align: center">
			<a onclick="delTd(this)">删除</a>
			<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
	</c:forEach>
</c:if>

<c:if test="${empty resultMap['zywk']}">
	<tr class="zywk">
	<td rowspan="1" style="text-align: center" class="zywk">中医外科(2个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
	<td style="text-align: center">
	&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;">
	</td>
	<td style="text-align: center">
	<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" name="" value=""/>
	~
	<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" name="" value=""/>
	</td>
	</tr>
</c:if>
<c:if test="${not empty resultMap['zywk']}">
	<c:forEach items="${resultMap['zywk']}" var="result" varStatus="s">
		<c:if test="${s.index eq 0}">
			<tr class="zywk">
			<td rowspan="${resultMap['zywk'].size()}" style="text-align: center" class="zywk">中医外科(2个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
			<td style="text-align: center">
			&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
		<c:if test="${s.index ne 0}">
			<tr class="zywk">
			<td style="text-align: center">
			<a onclick="delTd(this)">删除</a>
			<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
	</c:forEach>
</c:if>


<c:if test="${empty resultMap['zyfk']}">
	<tr class="zyfk">
	<td rowspan="1" style="text-align: center" class="zyfk">中医妇科(2个月))<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
	<td style="text-align: center">
	&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;">
	</td>
	<td style="text-align: center">
	<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" name="" value=""/>
	~
	<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" name="" value=""/>
	</td>
	</tr>
</c:if>
<c:if test="${not empty resultMap['zyfk']}">
	<c:forEach items="${resultMap['zyfk']}" var="result" varStatus="s">
		<c:if test="${s.index eq 0}">
			<tr class="zyfk">
			<td rowspan="${resultMap['zyfk'].size()}" style="text-align: center" class="zyfk">中医妇科(2个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
			<td style="text-align: center">
			&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
		<c:if test="${s.index ne 0}">
			<tr class="zyfk">
			<td style="text-align: center">
			<a onclick="delTd(this)">删除</a>
			<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
	</c:forEach>
</c:if>


<c:if test="${empty resultMap['zyek']}">
	<tr class="zyek">
	<td rowspan="1" style="text-align: center" class="zyek">中医儿科(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
	<td style="text-align: center">
	&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;">
	</td>
	<td style="text-align: center">
	<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" name="" value=""/>
	~
	<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" name="" value=""/>
	</td>
	</tr>
</c:if>
<c:if test="${not empty resultMap['zyek']}">
	<c:forEach items="${resultMap['zyek']}" var="result" varStatus="s">
		<c:if test="${s.index eq 0}">
			<tr class="zyek">
			<td rowspan="${resultMap['zyek'].size()}" style="text-align: center" class="zyek">中医儿科(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
			<td style="text-align: center">
			&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
		<c:if test="${s.index ne 0}">
			<tr class="zyek">
			<td style="text-align: center">
			<a onclick="delTd(this)">删除</a>
			<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
	</c:forEach>
</c:if>


<c:if test="${empty resultMap['zjk']}">
	<tr class="zjk">
	<td rowspan="1" style="text-align: center" class="zjk">针灸科(与推拿康复共3个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
	<td style="text-align: center">
	&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;">
	</td>
	<td style="text-align: center">
	<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" name="" value=""/>
	~
	<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" name="" value=""/>
	</td>
	</tr>
</c:if>
<c:if test="${not empty resultMap['zjk']}">
	<c:forEach items="${resultMap['zjk']}" var="result" varStatus="s">
		<c:if test="${s.index eq 0}">
			<tr class="zjk">
			<td rowspan="${resultMap['zjk'].size()}" style="text-align: center" class="zjk">针灸科(与推拿康复共3个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
			<td style="text-align: center">
			&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
		<c:if test="${s.index ne 0}">
			<tr class="zjk">
			<td style="text-align: center">
			<a onclick="delTd(this)">删除</a>
			<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
	</c:forEach>
</c:if>


<c:if test="${empty resultMap['tnk']}">
	<tr class="tnk">
	<td rowspan="1" style="text-align: center" class="tnk">推拿科(与针灸康复共3个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
	<td style="text-align: center">
	&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;">
	</td>
	<td style="text-align: center">
	<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" name="" value=""/>
	~
	<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" name="" value=""/>
	</td>
	</tr>
</c:if>
<c:if test="${not empty resultMap['tnk']}">
	<c:forEach items="${resultMap['tnk']}" var="result" varStatus="s">
		<c:if test="${s.index eq 0}">
			<tr class="tnk">
			<td rowspan="${resultMap['tnk'].size()}" style="text-align: center" class="tnk">推拿科(与针灸康复共3个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
			<td style="text-align: center">
			&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
		<c:if test="${s.index ne 0}">
			<tr class="tnk">
			<td style="text-align: center">
			<a onclick="delTd(this)">删除</a>
			<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
	</c:forEach>
</c:if>


<c:if test="${empty resultMap['zykfk']}">
	<tr class="zykfk">
	<td rowspan="1" style="text-align: center" class="zykfk">中医康复科(与针灸推拿共3个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
	<td style="text-align: center">
	&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;">
	</td>
	<td style="text-align: center">
	<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" name="" value=""/>
	~
	<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" name="" value=""/>
	</td>
	</tr>
</c:if>
<c:if test="${not empty resultMap['zykfk']}">
	<c:forEach items="${resultMap['zykfk']}" var="result" varStatus="s">
		<c:if test="${s.index eq 0}">
			<tr class="zykfk">
			<td rowspan="${resultMap['zykfk'].size()}" style="text-align: center" class="zykfk">中医康复科(与针灸推拿共3个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
			<td style="text-align: center">
			&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
		<c:if test="${s.index ne 0}">
			<tr class="zykfk">
			<td style="text-align: center">
			<a onclick="delTd(this)">删除</a>
			<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
	</c:forEach>
</c:if>


<c:if test="${empty resultMap['zygsk']}">
	<tr class="zygsk">
	<td rowspan="1" style="text-align: center" class="zygsk">中医骨伤科(2个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
	<td style="text-align: center">
	&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;">
	</td>
	<td style="text-align: center">
	<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" name="" value=""/>
	~
	<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" name="" value=""/>
	</td>
	</tr>
</c:if>
<c:if test="${not empty resultMap['zygsk']}">
	<c:forEach items="${resultMap['zygsk']}" var="result" varStatus="s">
		<c:if test="${s.index eq 0}">
			<tr class="zygsk">
			<td rowspan="${resultMap['zygsk'].size()}" style="text-align: center" class="zygsk">中医骨伤科(2个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
			<td style="text-align: center">
			&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
		<c:if test="${s.index ne 0}">
			<tr class="zygsk">
			<td style="text-align: center">
			<a onclick="delTd(this)">删除</a>
			<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
	</c:forEach>
</c:if>


<c:if test="${empty resultMap['zyebhk']}">
	<tr class="zyebhk">
	<td rowspan="1" style="text-align: center" class="zyebhk">中医耳鼻喉科(与眼科共1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
	<td style="text-align: center">
	&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;">
	</td>
	<td style="text-align: center">
	<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" name="" value=""/>
	~
	<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" name="" value=""/>
	</td>
	</tr>
</c:if>
<c:if test="${not empty resultMap['zyebhk']}">
	<c:forEach items="${resultMap['zyebhk']}" var="result" varStatus="s">
		<c:if test="${s.index eq 0}">
			<tr class="zyebhk">
			<td rowspan="${resultMap['zyebhk'].size()}" style="text-align: center" class="zyebhk">中医耳鼻喉科(与眼科共1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
			<td style="text-align: center">
			&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
		<c:if test="${s.index ne 0}">
			<tr class="zyebhk">
			<td style="text-align: center">
			<a onclick="delTd(this)">删除</a>
			<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
	</c:forEach>
</c:if>


<c:if test="${empty resultMap['zyyk']}">
	<tr class="zyyk">
	<td rowspan="1" style="text-align: center" class="zyyk">中医眼科(与耳鼻喉科共1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
	<td style="text-align: center">
	&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;">
	</td>
	<td style="text-align: center">
	<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" name="" value=""/>
	~
	<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" name="" value=""/>
	</td>
	</tr>
</c:if>
<c:if test="${not empty resultMap['zyyk']}">
	<c:forEach items="${resultMap['zyyk']}" var="result" varStatus="s">
		<c:if test="${s.index eq 0}">
			<tr class="zyyk">
			<td rowspan="${resultMap['zyyk'].size()}" style="text-align: center" class="zyyk">中医眼科(与耳鼻喉科共1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
			<td style="text-align: center">
			&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
		<c:if test="${s.index ne 0}">
			<tr class="zyyk">
			<td style="text-align: center">
			<a onclick="delTd(this)">删除</a>
			<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
	</c:forEach>
</c:if>


<c:if test="${empty resultMap['fzks']}">
	<tr class="fzks">
	<td rowspan="1" style="text-align: center" class="fzks">辅助科室(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
	<td style="text-align: center">
	&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;">
	</td>
	<td style="text-align: center">
	<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" name="" value=""/>
	~
	<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" name="" value=""/>
	</td>
	</tr>
</c:if>
<c:if test="${not empty resultMap['fzks']}">
	<c:forEach items="${resultMap['fzks']}" var="result" varStatus="s">
		<c:if test="${s.index eq 0}">
			<tr class="fzks">
			<td rowspan="${resultMap['fzks'].size()}" style="text-align: center" class="fzks">辅助科室(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
			<td style="text-align: center">
			&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
		<c:if test="${s.index ne 0}">
			<tr class="fzks">
			<td style="text-align: center">
			<a onclick="delTd(this)">删除</a>
			<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
	</c:forEach>
</c:if>


<c:if test="${empty resultMap['gsxx']}">
	<tr class="gsxx">
	<td rowspan="1" style="text-align: center" class="gsxx">跟师学习(半天/周)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
	<td style="text-align: center">
	&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;">
	</td>
	<td style="text-align: center">
	<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" name="" value=""/>
	~
	<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" name="" value=""/>
	</td>
	</tr>
</c:if>
<c:if test="${not empty resultMap['gsxx']}">
	<c:forEach items="${resultMap['gsxx']}" var="result" varStatus="s">
		<c:if test="${s.index eq 0}">
			<tr class="gsxx">
			<td rowspan="${resultMap['gsxx'].size()}" style="text-align: center" class="gsxx">跟师学习(半天/周)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
			<td style="text-align: center">
			&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
		<c:if test="${s.index ne 0}">
			<tr class="gsxx">
			<td style="text-align: center">
			<a onclick="delTd(this)">删除</a>
			<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
	</c:forEach>
</c:if>


<c:if test="${empty resultMap['xgzk']}">
	<tr class="xgzk">
	<td rowspan="1" style="text-align: center" class="xgzk">相关专科(9个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
	<td style="text-align: center">
	&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;">
	</td>
	<td style="text-align: center">
	<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" name="" value=""/>
	~
	<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" name="" value=""/>
	</td>
	</tr>
</c:if>
<c:if test="${not empty resultMap['xgzk']}">
	<c:forEach items="${resultMap['xgzk']}" var="result" varStatus="s">
		<c:if test="${s.index eq 0}">
			<tr class="xgzk">
			<td rowspan="${resultMap['xgzk'].size()}" style="text-align: center" class="xgzk">相关专科(9个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
			<td style="text-align: center">
			&#12288;&#12288;<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
		<c:if test="${s.index ne 0}">
			<tr class="xgzk">
			<td style="text-align: center">
			<a onclick="delTd(this)">删除</a>
			<input type="text" class="input validate[required] deptName" style="width: 180px;" value="${result.deptName}">
			</td>
			<td style="text-align: center">
			<input type="text" class="input validate[required] datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
			~
			<input type="text" class="input validate[required] datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
			</td>
			<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
	</c:forEach>
</c:if>

<c:if test="${empty resultMap['jdks']}">
	<tr class="jdks">
		<td rowspan="1" style="text-align: center" class="jdks">机动科室<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
		<td style="text-align: center">
			&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
		</td>
		<td style="text-align: center">
			<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
			~
			<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
		</td>
	</tr>
</c:if>
<c:if test="${not empty resultMap['jdks']}">
	<c:forEach items="${resultMap['jdks']}" var="result" varStatus="s">
		<c:if test="${s.index eq 0}">
			<tr class="jdks">
				<td rowspan="${resultMap['jdks'].size()}" style="text-align: center" class="jdks">机动科室<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
				<td style="text-align: center">
					&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
				</td>
				<td style="text-align: center">
					<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
					~
					<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
				</td>
				<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
		<c:if test="${s.index ne 0}">
			<tr class="jdks">
				<td style="text-align: center">
					<a onclick="delTd(this)">删除</a>
					<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
				</td>
				<td style="text-align: center">
					<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
					~
					<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
				</td>
				<input type="hidden" class="recordFlow" value="${result.recordFlow}">
			</tr>
		</c:if>
	</c:forEach>
</c:if>

<tr>
<td style="text-align: center">合计(33个月)</td>
<td class="sum" style="text-align: center" colspan="2"></td>
</tr>
<tr>
<td colspan="3">
对照《住院医师规范化培训细则》，本人培训内容完成情况如上，申请参加
<input class="input validate[required]" id="graduationYear" value="${graduationYear}" readonly="readonly" style="width: 120px;">
年度结业考试资格审核。
</td>
</tr>
</tbody>
</table>
</div>

</div>
</div>
</div>
<%--</form>--%>
</div>
</div>
</div>

<script>
	<c:if test="${param.role eq 'hospital' && param.level eq 'Main'}">
		<c:if test="${apply.xtOrgStatusId eq '1'}">
			$(function(){
				$(".remark").hide();
			});
		</c:if>
	</c:if>
	<c:if test="${param.role eq 'hospital' && param.level eq 'Joint'}">
		<c:if test="${apply.orgStatusId eq '1'}">
			$(function(){
				$(".remark").hide();
			});
		</c:if>
	</c:if>
	<c:if test="${param.role eq 'charge'}">
		<c:if test="${apply.chargeStatusId eq '1'}">
			$(function(){
				$(".remark").hide();
			});
		</c:if>
	</c:if>
</script>
<div class="div_table">
	<h4 class="remark">审核意见：</h4>
	<div style="text-align: center;">
		<form class="remark" id="remark" style="position: relative">
		<textarea id="remarkContent" class="validate[maxSize[250]]" name="remarkContent" style="margin-bottom:10px;height: 80px;"></textarea>
		</form>
		<c:if test="${param.role eq 'hospital' && param.level eq 'Main'}">
			<c:if test="${apply.xtOrgStatusId eq '-1'}">
				<input type="button" value="通&#12288;过" onclick="audit67('1','${apply.recordFlow}','Main')" class="btn_blue">&#12288;
				<input type="button" value="退&#12288;回" onclick="audit67('0','${apply.recordFlow}','Main')" class="btn_blue">&#12288;
			</c:if>
			<c:if test="${apply.xtOrgStatusId eq '1'}">
				<c:if test="${apply.chargeStatusId ne '1'}">
					基地已通过&#12288;
				</c:if>
				<c:if test="${apply.chargeStatusId eq '1'}">
					中管局已通过&#12288;
				</c:if>
			</c:if>
		</c:if>
		<c:if test="${param.role eq 'hospital' && param.level eq 'Joint'}">
			<c:if test="${apply.orgStatusId eq '-1'}">
				<input type="button" value="通&#12288;过" onclick="audit67('1','${apply.recordFlow}','Joint')" class="btn_blue">&#12288;
				<input type="button" value="退&#12288;回" onclick="audit67('0','${apply.recordFlow}','Joint')" class="btn_blue">&#12288;
			</c:if>
			<c:if test="${apply.orgStatusId eq '1'}">
				<c:if test="${apply.xtOrgStatusId ne '1'}">
					协同基地已通过&#12288;
				</c:if>
				<c:if test="${apply.xtOrgStatusId eq '1'}">
					主基地已通过&#12288;
				</c:if>
				<c:if test="${apply.chargeStatusId eq '1'}">
					中管局已通过&#12288;
				</c:if>
			</c:if>
		</c:if>
		<c:if test="${param.role eq 'charge'}">
			<c:if test="${apply.chargeStatusId eq '-1'}">
				<input type="button" value="通&#12288;过" onclick="chargeAudit0('1','${apply.recordFlow}')" class="btn_blue">&#12288;
				<input type="button" value="退&#12288;回" onclick="chargeAudit0('0','${apply.recordFlow}')" class="btn_blue">&#12288;
			</c:if>
			<c:if test="${apply.chargeStatusId eq '1'}">
				已通过&#12288;
			</c:if>
		</c:if>
		<input type="button" value="关&#12288;闭" onclick="jboxClose();" class="btn_blue">
	</div>
</div>
