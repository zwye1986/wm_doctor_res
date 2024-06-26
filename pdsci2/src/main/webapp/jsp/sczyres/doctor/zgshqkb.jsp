<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/jsp/sczyres/htmlhead-sczyres.jsp">
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
	if('${param.editFlag}'=='N'){
		$("input:not([type='button'])").attr('disabled','disabled');
		$("select").attr('disabled','disabled');
		$(".saveButton").hide();
		$("#userImgSpan").hide();
		$(".fileTd a:not(.view)").hide();
	}
	changeDoctorQualification('${doctor.trainingSpeId}');
	showCoordinationHospital('${extInfo.isCoordinationBase}');
	showMakeUp('${extInfo.isMakeUp}')
});


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
				jboxTip('${GlobalConstant.UPLOAD_SUCCESSED}');
				var arr = new Array();
				arr = data.split(":");
				$("#userImg").val(arr[1]);
				var url = "${sysCfgMap['upload_base_url']}/"+ arr[1];
				$("#showImg").attr("src",url);
				$("#headimgurl").val(arr[1]);
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

function save(){
	if(!$("#doctorForm").validationEngine("validate")){
		return;
	}
	var sessionNumber = $("[name='sessionNumber']").val();
	var graduationYear = $("[name='graduationYear']").val();
	if(graduationYear<=sessionNumber){
		jboxTip("结业年份必须大于入培业年份");
		$("[name='graduationYear']").focus();
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
	$("#cretTypeName").val($("#cretTypeId option:selected").text());
	$("#doctorTypeName").val($("#doctorTypeId option:selected").text());
	$("#birthProvName").val($("#birthProvId :selected").text());
	$("#birthCityName").val($("#birthCityId :selected").text());
	$("#birthAreaName").val($("#birthAreaId :selected").text());
	$("#educationName").val($("#educationId option:selected").text());
	$("#degreeName").val($("#degreeId option:selected").text());
	$("#trainingSpeName").val($("#trainingSpeId option:selected").text());
	$("#nationName").val($("#nationId :selected").text());
	$("#coordinationBaseAttributeName").val($("#coordinationBaseAttributeId :selected").text());
	$("#makeUpTypeName").val($("#makeUpTypeId :selected").text());
	var fileData = [];
	$(".fileTd").each(function(){
		var fileFlow = $(this).find('[name="fileFlow"]').val()
		var fileName = $(this).find('[name="fileName"]').val()
		var filePath = $(this).find('[name="filePath"]').val()
		var singleData = {fileFlow:fileFlow,fileName:fileName,filePath:filePath}
		fileData.push(singleData);
	});
	jboxPost("<s:url value='/sczyres/doctor/saveBaseInfo'/>" , $("#doctorForm").serialize()+"&jsonData="+JSON.stringify(fileData) , function(resp){
		if(resp=="1"){
			jboxTip("保存成功");
		}else{
			jboxTip(resp);
		}
	} , null , false);
}

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
function uploadFile(type) {
	jboxOpen("<s:url value='/sczyres/doctor/uploadFile'/>?operType="+type,"上传附件",450,150);
}

function delFile(type) {
	var type = type.replace(/\./g, '\\.');
	$("#"+type+"Del").hide();
	$("#"+type+"Span").hide();
	$("#"+type+"Up").text("上传");
	$("#"+type+"Value").val("");
}
function print(){
	var url ="<s:url value='/sczyres/doctor/zgshqkb?printFlag=Y'/>";
	window.open(url);
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
    <form id='doctorForm' style="position:relative;">
    <input type="hidden" name="userFlow" value="${user.userFlow}"/>
    <input type="hidden" name="doctorFlow" value="${doctor.doctorFlow}"/>
    <input type="hidden" name="recordFlow" value="${apply.recordFlow}"/>
    <div class="main_bd">
       <div class="div_table">
          <div class="score_frame">
            <h1>四川省中医住院医师规范化培训实践技能考核申请表（${year}年）</h1>
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
						   <label>
						   <input type="radio" class='validate[required]' style="width:auto;" name="sexId" sexName="${userSexEnumMan.name}" value='${userSexEnumMan.id}' ${user.sexId eq userSexEnumMan.id?'checked':''}/>${userSexEnumMan.name}
					   		</label>
						   &nbsp;
						   <label>
							   <input type="radio" class='validate[required]' style="width:auto;" name="sexId" sexName="${userSexEnumWoman.name}" value='${userSexEnumWoman.id}' ${user.sexId eq userSexEnumWoman.id?'checked':''}/>${userSexEnumWoman.name}
						   </label>
						   <input type="hidden" name="sexName" id="sexName">
					   </td>
	                   <td id="imgTd" rowspan="5" style="text-align: center;padding-top:5px;">
						   <img src="${sysCfgMap['upload_base_url']}/${user.userHeadImg}" id="showImg" width="80%"
								height="160px" onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
							<span style="cursor: pointer; display:block;" id="userImgSpan">
								[<a onclick="$('#imageFile').click();">${empty user.userHeadImg?'上传头像':'重新上传'}</a>]
							</span>
							<input type="file" id="imageFile" name="headImg" style="display: none" onchange="uploadImage();"/>
							<input type="hidden" id="headimgurl" value=""/>
						   <p style="text-align: center;width: 100%;" title="上传照片为白底2寸免冠证件照片，头部占照片尺寸的2/3,白色背景无边框，人像正立清晰（jpg格式），分辨率不小于413*626，大小为150-300k">照片要求</p>
						   <%--<div></div>--%>
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
						   <input id="userBirthday" name="userBirthday"  value="${user.userBirthday}"  class="validate[required] input datepicker" style="width: 149px;" readonly="readonly"/>
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
						   <div style="color:red">培训基地若为协同基地，请选择对应的协同基地</div>
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
							  <a id="${s}Up" href="javascript:uploadFile('${s}');" style="color:blue">${empty fileMap[s].filePath?'':'重新'}上传</a>
							  <a id="${s}Del" href="javascript:delFile('${s}');"  style="color:blue;${empty fileMap[s].filePath?'display:none':''}">删除</a>
							  <input type="hidden" name="fileName" value="file${s}">
							  <input type="hidden" name="fileFlow" value="${fileMap[s].fileFlow}">
							  <input type="hidden" id="${s}Value" name="filePath" value="${fileMap[s].filePath}">
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
		<c:if test="${param.showPrint eq 'Y'}">
	    <input class="btn_blue" type="button" onclick="print();" value="打&#12288;印"/>
		</c:if>
    </div>
    </form>
</div>
<div style="display: none">
		<!-- 模板 -->
</div>
</div>
