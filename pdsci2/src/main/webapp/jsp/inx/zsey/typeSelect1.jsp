<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="register" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">
	var idNoIsOk = ${!empty user};
	var phoneIsOk = ${!empty user};
	
	function next(id,name){
		$('#doctor').show();
		$("#typeName").text(name);
		$("[name='doctorTypeId']").val(id);
		if(id == "${hBRecDocTypeEnumSocial.id}"){//行业人
			$('#doctor').hide();
			$("#doctor input,#doctor select").each(function(){
				$(this).val("");
			});
		} else if(id == "${hBRecDocTypeEnumCompany.id}" || id == "${hBRecDocTypeEnumCompanyEntrust.id}"){//单位人/委培人
			$('.ssmnv').show();
		} else {//四证合一
			$('#doctor').hide();
			$("#doctor input,#doctor select").each(function(){
				$(this).val("");
			});
			$('.graduated').show();
		}
		nextStep();
	}
	
	function submitInfo(){
		if(!$("#infoForm").validationEngine("validate")){
			return;
		}
		if(!idNoIsOk){
			jboxTip($("#idNoResult").text());
			return;
		}
		if(!phoneIsOk){
			jboxTip($("#phoneResult").text());
			return;
		}
		//医疗卫生机构
		var medicalHeaithOrgText = $("#medicalHeaithOrgId option:selected").text();
		$("#medicalHeaithOrgName").val(medicalHeaithOrgText);
		//基层医疗卫生机构
		var basicHealthOrgText = $("#basicHealthOrgId option:selected").text();
		$("#basicHealthOrgName").val(basicHealthOrgText);
		//医院属性
		var hospitalAttrText = $("#hospitalAttrId option:selected").text();
		$("#hospitalAttrName").val(hospitalAttrText);
		//医院类别
		var hospitalCategoryText = $("#hospitalCategoryId option:selected").text();
		$("#hospitalCategoryName").val(hospitalCategoryText);
		//单位性质
		var baseAttributeText = $("#baseAttributeId option:selected").text();
		$("#baseAttributeName").val(baseAttributeText);
		//硕士学位
		var masterDegreeText = $("#masterDegreeId option:selected").text();
		$("#masterDegreeName").val(masterDegreeText);
		//硕士学位类型
		var masterDegreeTypeText = $("#masterDegreeTypeId option:selected").text();
		$("#masterDegreeTypeName").val(masterDegreeTypeText);
		$("#infoForm").submit();
	}

	function nextStep(){
		$(".thirdStep,.fourthStep").toggle();
		$(".stepOn3").toggleClass("active3");
		$(".stepOn4").toggleClass("active4");
	}
	
	function cutBirthday(idNo){	
		$("input[name='userBirthday']").val(idNo.substr(6,4)+"-"+idNo.substr(10,2)+"-"+idNo.substr(12,2)).keyup();
	}
	function checkIdNo(){
		var cretTypeId = $("#cretTypeId").val();
		if(!cretTypeId){
			jboxTip("请先选择证件类型");
			$("input[name='idNo']").val("");
			return ;
		}
		var idNo = $("input[name='idNo']").val();
		if(cretTypeId == "01"){
			if(!/^\d{17}(\d|x)$/i.test(idNo)){
				idNoIsOk = false;
				$("#idNoResult").text("身份证长度或格式错误");
				return;
			}
			var areaCode = idNo.substring(0,6);
			if(parseFloat(areaCode)<110000 || parseFloat(areaCode)>820000){
				idNoIsOk = false;
				$("#idNoResult").text("前6位行政区划代码错误");
				return;
			}
			//检查出生日期是否正确
			var dtmBirth = new Date(idNo.substring(6,10) + "/" + idNo.substring(10,12) + "/" + idNo.substring(12,14));
			var bGoodDay = (dtmBirth.getFullYear() == Number(idNo.substring(6,10))) && ((dtmBirth.getMonth() + 1) == Number(idNo.substring(10,12))) && (dtmBirth.getDate() == Number(idNo.substring(12,14)));
			if (!bGoodDay || parseFloat(idNo.substring(6,10)) <1900 || parseFloat(idNo.substring(6,10)) >3000) {
				idNoIsOk = false;
				$("#idNoResult").text("出生日期信息错误");
				return;
			}
		}
		var url = "<s:url value='/inx/hbres/checkidno'/>";
		jboxPost(url , {"cretTypeId":cretTypeId, "idNo":idNo} , function(resp){
			idNoIsOk = resp=="1";
			if(resp=="1"){
				if(cretTypeId=='${certificateTypeEnumShenfenzheng.id}'){
					cutBirthday(idNo);
				}
			}else{
				$("#idNoResult").text(resp);
				if (resp=="${GlobalConstant.USER_ID_NO_REPETE}") {
					$("#idNoResult").append($("#repeatIdNoSpan").html());
				}
				$("input[name='userBirthday']").val("");
			}
		} , null , false);
	}
	
	function checkPhone(){
		var phone = $("input[name='userPhone']").val();
		var url = "<s:url value='/inx/hbres/checkuserphone'/>";
		jboxPost(url , {"userPhone":phone} , function(resp){
			phoneIsOk = resp=="1";
			if(resp!="1"){
				$("#phoneResult").text(resp);
			}
		} , null , false);
	}
	
	function checkSelf(isSelf,msgId){
		$("#"+msgId).text("");
		if(!isSelf){
			if(msgId=="idNoResult"){
				checkIdNo();
			}
			if(msgId=="phoneResult"){
				checkPhone();
			}
		}
	}

	$(document).ready(function(){
		$('#graduationTime').datepicker();
		$('#userBirthday').datepicker();
		$('#masterGraTime').datepicker();
		$('#doctorGraTime').datepicker();
		setTimeout(function(){
			changeMedicalThings($("#medicalHeaithOrgId").val());
		},100);
		if(!isNaN($("#masterExamResult").val()) && !isNaN('${applicationScope.sysCfgMap['res_master_score_on']}')){
			if(parseFloat($("#masterExamResult").val()) >= parseFloat('${applicationScope.sysCfgMap['res_master_score_on']}')){//研究生考试成绩大于等于设置过分线
				$(".testPart").show();
			}else{
				$(".testPart").hide();
			}
		}
		<c:if test="${extInfo.isMaster eq 'Y'}">
			$(".isMaster").show();
			$(".noMaster").hide();
		</c:if>
		<c:if test="${extInfo.isMaster ne 'Y'}">
			$(".isMaster").hide();
			$(".noMaster").show();
		</c:if>
		<c:if test="${extInfo.hasTakenMasterExam eq 'Y'}">
			$(".isExam").show();
		</c:if>
		<c:if test="${extInfo.hasTakenMasterExam ne 'Y'}">
			$(".isExam").hide();
		</c:if>
		<c:if test="${doctor.doctorLicenseFlag eq 'Y'}">
			$(".doctorLicense").show();
		</c:if>
		<c:if test="${doctor.doctorLicenseFlag ne 'Y'}">
			$(".doctorLicense").hide();
		</c:if>
		<c:if test="${extInfo.qualifiedNoFlag eq 'Y'}">
			$(".qualifiedNo").show();
		</c:if>
		<c:if test="${extInfo.qualifiedNoFlag ne 'Y'}">
			$(".qualifiedNo").hide();
		</c:if>
		<c:if test="${extInfo.isDoctor eq 'Y'}">
			$(".isDoctor").show();
		</c:if>
		<c:if test="${extInfo.isDoctor ne 'Y'}">
			$(".isDoctor").hide();
		</c:if>
		<c:if test="${extInfo.isAssistance eq 'Y'}">
		$(".isAssistance").show();
		</c:if>
		<c:if test="${extInfo.isAssistance ne 'Y'}">
		$(".isAssistance").hide();
		</c:if>
		<c:if test='${useCompleteUserInfo=="Y"}'>
		    next('${doctor.doctorTypeId}','${doctor.doctorTypeName}');
			var msg = "请确认信息是否正确，确认后无法修改！";
			if('${doctor.isUnderLine eq "Y"}'){
				msg = "请确认信息是否正确，然后重新登录系统！";
			}
			jboxConfirm(msg,function(){
				var data = {
					userFlow:"${user.userFlow}",
					statusId:"Activated"
				};
				jboxPost("<s:url value='/inx/hbres/userAudit'/>",data,
					function(resp){
						if(resp == "${GlobalConstant.OPRE_SUCCESSED}"){
							window.parent.$(".nextBut").hide();
							window.parent.location.href="<s:url value='/inx/hbres/completeUserInfo'/>?activationCode=${user.userFlow}";
							jboxClose();
						}
					}
					,null,true);
			});
		</c:if>
		<c:if test='${not empty errorMsg}'>
		    next('${doctor.doctorTypeId}','${doctor.doctorTypeName}');
		</c:if>
	});

	function uploadFile(type,typeName) {
		jboxOpen("<s:url value='/inx/hbres/uploadFile'/>?operType="+type,"上传"+typeName,450,150);
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
	function changeMedicalThings(value){
		if(value == "3") {//基层医院
			$(".basicmedicalInstitutionType").show();
		}else{
			$(".basicmedicalInstitutionType").hide();
			$(".basicmedicalInstitutionType input,.basicmedicalInstitutionType select").each(function(){
				$(this).val("");
			});
		}
		if(value == "1"){//医院
			$(".subssmnv").show();
		}else{
			$(".subssmnv").hide();
			$(".subssmnv input,.subssmnv select").each(function(){
				$(this).val("");
			});
		}
	}
	function checkIsMaster(obj){
		if($(obj).val() == "Y"){
			$(".isMaster").show();
			$(".noMaster").hide();
			$(".noMaster input[type!='radio'],.noMaster select").each(function(){
				$(this).val("");
			});
		}else{
			$(".isMaster").hide();
			$(".noMaster2").show();
			if($(".Y").attr("checked")=="checked"){
				$(".isExam").show();
			}else{
				$(".isExam").hide();
			}
			$(".isMaster input[type!='radio'],.isMaster select").each(function(){
				$(this).val("");
			});
		}
	}
	function checkIsDoctor(obj){
		if($(obj).val() == "Y"){
			$(".isDoctor").show();
		}else{
			$(".isDoctor").hide();
			$(".isDoctor input,.isDoctor select").each(function(){
				$(this).val("");
			});
		}
	}
	function checkIsExam(obj){
		if($(obj).val() == "Y"){
			$(".isExam").show();
		}else{
			$(".isExam").hide();
			$(".isExam input[type!='radio'],.isExam select").each(function(){
				$(this).val("");
			});
		}
	}
	function showId(obj){
		if($(obj).val() == "Y"){
			$(".doctorLicense").show();
		}else{
			$(".doctorLicense").hide();
			$(".doctorLicense input,.doctorLicense select").each(function(){
				$(this).val("");
			});
		}
	}
	function showId2(obj){
		if($(obj).val() == "Y"){
			$(".qualifiedNo").show();
		}else{
			$(".qualifiedNo").hide();
			$(".qualifiedNo input,.qualifiedNo select").each(function(){
				$(this).val("");
			});
		}
	}
	function showId3(obj){
		if($(obj).val() == "Y"){
			$(".isAssistance").show();
		}else{
			$(".isAssistance").hide();
			$(".isAssistance input,.isAssistance select").each(function(){
				$(this).val("");
			});
		}
	}
	function joinTest(obj){
		if(!isNaN($(obj).val()) && !isNaN('${applicationScope.sysCfgMap['res_master_score_on']}')){
			if(parseFloat($(obj).val()) >= parseFloat('${applicationScope.sysCfgMap['res_master_score_on']}')){//研究生考试成绩大于等于设置过分线
				$(".testPart").show();
			}else{
				$(".testPart").hide();
				$(".testPart input[type!='radio'],.testPart select").each(function(){
					$(this).val("");
				});
			}
		}else{
			jboxTip("请填写正确格式成绩！");
			$(obj).val("");
		}
	}
	function checkBirthDay(value){
		if(parseFloat(value.substring(0,4)) < 1990 || parseFloat(value.substring(0,4)) > 3000){
			$("#userBirthday").val("");
			jboxTip("出生日期不合实际");
		}
	}
</script>
</head>
<body>
<div class="yw">
  <jsp:include page="/jsp/hbres/head.jsp" flush="true">
         <jsp:param value="/inx/hbres" name="indexUrl"/>
         <jsp:param value="true" name="notShowAccount"/>
     </jsp:include>
  <div class="content">
      <div class="step">
          <a href="#" class="step1">1、基本信息</a>
          <a href="#" class="step2">2、邮箱激活</a>
          <a href="#" class="step3 active3 stepOn3">3、选择类型</a>
          <a href="#" class="step4 stepOn4">4、信息登记</a>
          <a href="#" class="step5">5、完成</a>
      </div>
     <div class="thirdStep" id="thirdStep">
        <span>&nbsp;&nbsp;请选择个人信息类型，一旦成功建立账号，类型不可更改</span>
        <ul class="clearfix" style="${isRegistrationDate?'':'margin-left:12.5%'}">
			<c:if test="${isRegistrationDate}">
			<li onclick="next('${hBRecDocTypeEnumGraduate.id}','${hBRecDocTypeEnumGraduate.name}');">
				<h3 class="type1">${hBRecDocTypeEnumGraduate.name}</h3>
				<p>考入高等医学院校临床专业学位硕士研究生的在校学生。</p>
				<a href="javascript:;">选择并继续</a>
			</li>
			</c:if>
			<li onclick="next('${hBRecDocTypeEnumSocial.id}','${hBRecDocTypeEnumSocial.name}');">
				<h3 class="type2">${hBRecDocTypeEnumSocial.name}</h3>
				<p>未落实工作单位参加住院医师规范化培训的人员</p>
				<a href="javascript:;">选择并继续</a>
			</li>
			<li onclick="next('${hBRecDocTypeEnumCompany.id}','${hBRecDocTypeEnumCompany.name}');">
				<h3 class="type3">${hBRecDocTypeEnumCompany.name}</h3>
				<p>已落实工作单位参加住院医师规范化培训的人员</p>
				<a href="javascript:;">选择并继续</a>
			</li>
			<li onclick="next('${hBRecDocTypeEnumCompanyEntrust.id}','${hBRecDocTypeEnumCompanyEntrust.name}');">
				<h3 class="type4">${hBRecDocTypeEnumCompanyEntrust.name}</h3>
				<p>工作单位委托参加住院医师规范化培训的人员</p>
				<a href="javascript:;">选择并继续</a>
			</li>
        </ul>
      </div>
     <form id="infoForm" method="post" action="<s:url value='/inx/hbres/finishUserInfo'/>" enctype="multipart/form-data">
     	<input type="hidden" name="userFlow" value="${user.userFlow}"/>
     	<input type="hidden" name="doctorFlow" value="${user.userFlow}"/>
     	<input type="hidden" name="doctorTypeId" value="${doctor.doctorTypeId}"/>
        <div class="fourthStep" id="fourthStep" style="display: none;">
            <p style="color: red;">${errorMsg}</p>
            <h3>个人信息类型</h3>
            <p>您选择的个人信息类型为：<font id="typeName"></font></p>
            <div id="doctor">
            	<dl class="clearfix">
					<dt>
						<font color="red">*</font>
						<span class="ssmnv">工作单位名称</span>
					</dt>
	              <dd class="import ssmnv">
	                  <input type="text" name="workOrgName" class="validate[required,maxSize[20]]" value="${doctor.workOrgName}"/>
	              </dd>
				  <dd class="message ssmnv">个人实际工作单位完整名称</dd>
	            </dl>
				<dl class="clearfix ssmnv">
	              <dt>委培单位同意证明</dt>
	              <dd class="import" style="width: 300px;">
	              	<span id="dispatchFileFSpan" style="display:${!empty doctor.dispatchFile?'':'none'} ">
		              	[<a href="${sysCfgMap['upload_base_url']}/${doctor.dispatchFile}" target="_blank">查看图片</a>]
		            </span>
	              	<a id="dispatchFileF" href="javascript:uploadFile('dispatchFileF','单位同意证明');" class="btn">${empty doctor.dispatchFile?'':'重新'}上传</a>
	              	<a id="dispatchFileFDel" href="javascript:delFile('dispatchFileF');" class="btn" style="${empty doctor.dispatchFile?'display:none':''}">删除</a>
              		<input type="hidden" id="dispatchFileFValue" name="dispatchFile" value="${doctor.dispatchFile }">
	              </dd>
	              <dd class="message" style="color: red;line-height:20px;">
	              	限传&nbsp;${sysCfgMap['inx_image_support_suffix']}格式的图片，大小不超过${sysCfgMap['inx_image_limit_size']}M！
	              </dd>
	            </dl>
				<dl class="clearfix ssmnv">
					<dt><font color="red">*</font>医疗卫生机构</dt>
					<dd class="import">
						<select class="validate[required]" id="medicalHeaithOrgId" name="medicalHeaithOrgId" onchange="changeMedicalThings(this.value);" >
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumMedicalHeaithOrgList}" var="tra">
								<option value="${tra.dictId}" <c:if test="${extInfo.medicalHeaithOrgId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
							</c:forEach>
						</select>
						<input type="hidden" id="medicalHeaithOrgName" name="medicalHeaithOrgName" value="${extInfo.medicalHeaithOrgName}">
						<span style="margin-left:20px;" class="basicmedicalInstitutionType">
							<font color="red">*</font>基层医疗卫生机构类型
								<span style="margin-left:20px;"></span>
								<select class="validate[required]" id="basicHealthOrgId" name="basicHealthOrgId" >
									<option value="">请选择</option>
									<c:forEach items="${dictTypeEnumBasicHealthOrgList}" var="tra">
										<option value="${tra.dictId}" <c:if test="${extInfo.basicHealthOrgId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
									</c:forEach>
								</select>
							<input type="hidden" id="basicHealthOrgName" name="basicHealthOrgName" value="${extInfo.basicHealthOrgName}">
						</span>
					</dd>
				</dl>
				<dl class="clearfix ssmnv subssmnv">
					<dt><font color="red">*</font>医院属性</dt>
					<dd class="import">
						<select class="validate[required]" id="hospitalAttrId" name="hospitalAttrId">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumHospitalAttrList}" var="tra">
								<option value="${tra.dictId}" <c:if test="${extInfo.hospitalAttrId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
							</c:forEach>
						</select>
						<input type="hidden" id="hospitalAttrName" name="hospitalAttrName" value="${extInfo.hospitalAttrName}">
						<span style="margin-left:20px;"></span>
						<font color="red">*</font>医院类别
						<span style="margin-left:20px;"></span>
						<select class="validate[required]" id="hospitalCategoryId" name="hospitalCategoryId">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumHospitalCategoryList}" var="tra">
								<option value="${tra.dictId}" <c:if test="${extInfo.hospitalCategoryId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
							</c:forEach>
						</select>
						<input type="hidden" id="hospitalCategoryName" name="hospitalCategoryName" value="${extInfo.hospitalCategoryName}">
						<span style="margin-left:20px;"></span>
						<font color="red">*</font>单位性质
						<span style="margin-left:20px;"></span>
						<select class="validate[required]" id="baseAttributeId" name="baseAttributeId">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumBaseAttributeList}" var="tra">
								<option value="${tra.dictId}" <c:if test="${extInfo.baseAttributeId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
							</c:forEach>
						</select>
						<input type="hidden" id="baseAttributeName" name="baseAttributeName" value="${extInfo.baseAttributeName}">
					</dd>
				</dl>
            </div>
			<div class="graduated" style="display: none;">
				<dl class="clearfix">
					<dt><font color="red">*</font>在读院校名称</dt>
					<dd class="import">
						<input type="text" name="workOrgName2" value="${doctor.workOrgName}" class="validate[required]"/>
					</dd>
				</dl>
			</div>
            <h3>用户信息登记</h3>
            <dl class="clearfix">
              <dt><font color="red">*</font>姓名</dt>
              <dd class="import">
              	<input type="text" name="userName" value="${user.userName}" class="validate[required,maxSize[20]]"/>
              </dd>
              <dd class="message">个人真实姓名</dd>
            </dl>
            <dl class="clearfix">
              <dt><font color="red">*</font>性别</dt>
              <dd>
				<input type="radio" class='validate[required] sex' name="sexId" value='${userSexEnumMan.id}' ${user.sexId eq userSexEnumMan.id?'checked':''}/>${userSexEnumMan.name}&#12288;
				<input type="radio" class='validate[required] sex' name="sexId" value='${userSexEnumWoman.id}' ${user.sexId eq userSexEnumWoman.id?'checked':''}/>${userSexEnumWoman.name}
              </dd>
            </dl>
			<dl class="clearfix">
				<dt><font color="red">*</font>出生日期</dt>
				<dd class="import">
					<input type="text" id="userBirthday" name="userBirthday" value="${user.userBirthday}" onchange="checkBirthDay(this.value)" class="validate[required,custom[dateFormat]]"/>
				</dd>
				<dd class="message">yyyy-MM-dd</dd>
			</dl>
			<dl class="clearfix">
				<dt><font color="red">*</font>年龄</dt>
				<dd class="import">
					<input type="text" name="age" value="${extInfo.age}" class="validate[required]"/>
				</dd>
			</dl>
			<dl class="clearfix">
				<dt><font color="red">*</font>人员属性</dt>
				<dd class="import">
					<select id="personnelAttributeId" name="personnelAttributeId" class="xlselect validate[required]">
						<option value="">请选择</option>
						<c:forEach items="${personnelAttributeEnumList}" var="attr">
							<option value="${attr.id}" ${extInfo.personnelAttributeId eq attr.id?'selected':''}>${attr.name}</option>
						</c:forEach>
					</select>
				</dd>
			</dl>
			<dl class="clearfix">
              <dt><font color="red">*</font>证件类型</dt>
              <dd class="import">
				<select id="cretTypeId" name="cretTypeId" class="xlselect validate[required]">
					<option value="">请选择</option>
					<c:forEach items="${certificateTypeEnumList}" var="cret">
						<option value="${cret.id}" ${user.cretTypeId eq cret.id?'selected':''}>${cret.name}</option>
					</c:forEach>
				</select>
              </dd>
            </dl>
            <dl class="clearfix">
              <dt><font color="red">*</font>证件号码</dt>
              <dd class="import">
              	<input type="text" name="idNo" value="${user.idNo}" class="validate[required]" onchange="checkSelf(idNoIsOk=(this.value=='${user.idNo}'),'idNoResult');"/>
              </dd>
              <dd class="message">个人真实证件号码</dd>
              <dd class="message_1">
              	<font color="red" id="idNoResult"></font>
              </dd>
            </dl>
			<dl class="clearfix">
				<dt><font color="red">*</font>民族</dt>
				<dd class="import">
					<select name="nationId" class="validate[required]">
						<option/>
						<c:forEach items="${userNationEnumList}" var="nation">
							<option value="${nation.id}" ${user.nationId eq nation.id?'selected':''}>${nation.name}</option>
						</c:forEach>
					</select>
				</dd>
			</dl>
            <dl class="clearfix">
              <dt><font color="red">*</font>手机</dt>
              <dd class="import">
              	<input type="text" name="userPhone" value="${user.userPhone}" class="validate[required,custom[mobile]]" onchange="checkSelf(phoneIsOk=(this.value=='${user.userPhone}'),'phoneResult');"/>
              </dd>
              <dd class="message">个人常用手机号码</dd>
              <dd class="message_1">
              	<font color="red" id="phoneResult"></font>
              </dd>
            </dl>
			<dl class="clearfix">
				<dt><font color="red">*</font>邮箱</dt>
				<dd class="import">
					<input type="text" name="userEmail" value="${user.userEmail}" readonly="readonly"/>
				</dd>
				<dd class="message">个人常用电子邮箱</dd>
			</dl>
            <dl class="clearfix">
              <dt><font color="red">*</font>通讯地址</dt>
              <dd class="import">
                  <input type="text" name="userAddress" class="validate[required , maxSize[30]]" value="${user.userAddress}"/>
              </dd>
              <dd class="message">个人常用通讯地址</dd>
            </dl>
            <dl class="clearfix">
              <dt><font color="red">*</font>证件照</dt>
              <dd class="import" style="width: 300px;">
				  <span id="doctorHeadImgFileSpan" style="display:${!empty doctor.doctorHeadImg?'':'none'} ">
						[<a href="${sysCfgMap['upload_base_url']}/${doctor.doctorHeadImg}" target="_blank">查看图片</a>]
				  </span>
              	  <a id="doctorHeadImgFile" href="javascript:uploadFile('doctorHeadImgFile','证件照');" class="btn ${empty doctor.doctorHeadImg?'validate[required]':''}" >${empty doctor.doctorHeadImg?'':'重新'}上传</a>
			      <a id="doctorHeadImgFileDel" href="javascript:delFile('doctorHeadImgFile');" class="btn" style="${empty doctor.doctorHeadImg?'display:none':''}">删除</a>
              	  <input type="hidden" id="doctorHeadImgFileValue" name="doctorHeadImg" value="${doctor.doctorHeadImg }">
			  </dd>
              <dd class="message" style="color: red;line-height:20px;">
				  照片要求：2寸白底彩色免冠照片，JPG格式，分辨率不小于413*626，大小不超过300K，人像正立。
			  </dd>
            </dl>
			<dl class="clearfix">
				<dt style="line-height:20px;"><font color="red">*</font>是否取得执业医师资格证</dt>
				<dd>
					<label><input type="radio" name="doctorLicenseFlag" <c:if test="${doctor.doctorLicenseFlag eq 'Y'}">checked="checked"</c:if>  value="Y"  class="validate[required]" onchange="showId(this);"/>是</label>&#12288;
					<label><input type="radio" name="doctorLicenseFlag" <c:if test="${doctor.doctorLicenseFlag eq 'N'}">checked="checked"</c:if> value="N"  class="validate[required]" onchange="showId(this);"/>否</label>
				</dd>
			</dl>
			<div class="doctorLicense">
				<dl class="clearfix">
					<dt><font color="red">*</font>医师资格证号</dt>
					<dd class="import">
						<input type="text" name="doctorLicenseNo" value="${doctor.doctorLicenseNo}" class="validate[required]" >
					</dd>
				</dl>
				<dl class="clearfix">
				<dt>医师资格证</dt>
				<dd class="import" style="width: 300px;">
              	<span id="qualifiedFileFSpan" style="display:${!empty doctor.qualifiedFile?'':'none'} ">
	              	[<a href="${sysCfgMap['upload_base_url']}/${doctor.qualifiedFile}" target="_blank">查看图片</a>]
	            </span>
					<a id="qualifiedFileF" href="javascript:uploadFile('qualifiedFileF','医师资格证');" class="btn">${empty doctor.qualifiedFile?'':'重新'}上传</a>
					<a id="qualifiedFileFDel" href="javascript:delFile('qualifiedFileF');" class="btn" style="${empty doctor.qualifiedFile?'display:none':''}">删除</a>
					<input type="hidden" id="qualifiedFileFValue" name="qualifiedFile" value="${doctor.qualifiedFile }">
				</dd>
				<dd class="message" style="color: red;line-height:20px;">
					限传&nbsp;${sysCfgMap['inx_image_support_suffix']}格式的图片，大小不超过${sysCfgMap['inx_image_limit_size']}M！
				</dd>
			</dl>
			</div>
			<dl class="clearfix">
				<dt style="line-height:20px;"><font color="red">*</font>是否取得执业证</dt>
				<dd>
					<label><input type="radio" name="practPhysicianFlag" <c:if test="${doctor.practPhysicianFlag eq 'Y'}">checked="checked"</c:if>  value="Y"  class="validate[required]" onchange="showId2(this);"/>是</label>&#12288;
					<label><input type="radio" name="practPhysicianFlag" <c:if test="${doctor.practPhysicianFlag eq 'N'}">checked="checked"</c:if> value="N"  class="validate[required]" onchange="showId2(this);"/>否</label>
				</dd>
			</dl>
			<div class="qualifiedNo">
				<dl class="clearfix">
					<dt><font color="red">*</font>执业医师证号</dt>
					<dd class="import">
						<input type="text" name="practPhysicianCertificateNo" value="${doctor.practPhysicianCertificateNo}" class="validate[required]" >
					</dd>
				</dl>
			</div>
			<dl class="clearfix">
				<dt style="line-height:20px;"><font color="red">*</font>是否为援疆援藏住院医师</dt>
				<dd>
					<label><input type="radio" name="isAssistance" <c:if test="${extInfo.isAssistance eq 'Y'}">checked="checked"</c:if>  value="Y"  class="validate[required]" onchange="showId3(this);"/>是</label>&#12288;
					<label><input type="radio" name="isAssistance" <c:if test="${extInfo.isAssistance eq 'N'}">checked="checked"</c:if> value="N"  class="validate[required]" onchange="showId3(this);"/>否</label>
				</dd>
			</dl>
			<div class="isAssistance">
				<dl class="clearfix">
					<dt><font color="red">*</font>援疆援藏住院医师送出省份</dt>
					<dd class="import">
						<input type="text" name="assistanceProvince" value="${extInfo.assistanceProvince}" class="validate[required]" >
					</dd>
				</dl>
				<dl class="clearfix">
					<dt>援疆援藏住院医师送出单位统一社会信用代码/组织机构代码</dt>
					<dd class="import">
						<input type="text" name="assistanceCode" value="${extInfo.assistanceCode}">
					</dd>
				</dl>
			</div>
            <dl class="clearfix">
              <dt>紧急联系人</dt>
              <dd class="import">
                  <input type="text" name="emergencyName" value="${doctor.emergencyName}"/>
              </dd>
               <dd class="message">确保能够及时联系到您</dd>
            </dl>
            <dl class="clearfix">
              <dt style="line-height:20px;">紧急联系方式</dt>
              <dd class="import">
                  <input type="text" name="emergencyPhone" value="${doctor.emergencyPhone}"/>
              </dd>
              <dd class="message">确保信号畅通</dd>
            </dl>
            <dl class="clearfix">
              <dt>与本人关系</dt>
              <dd class="import"><input type="text" name="emergencyRelation" value="${doctor.emergencyRelation}"/></dd>
            </dl>
            <h3>学历信息登记</h3>
			<dl class="clearfix">
				<dt><font color="red">*</font>是否为应届生</dt>
				<dd>
					<label><input type="radio" name="isYearGraduate" class="validate[required]" <c:if test="${doctor.isYearGraduate eq 'Y'}">checked="checked"</c:if>  value="Y" />是</label>&#12288;
					<label><input type="radio" name="isYearGraduate" class="validate[required]" <c:if test="${doctor.isYearGraduate eq 'N'}">checked="checked"</c:if> value="N" />否</label>
				</dd>
			</dl>
			<dl class="clearfix">
				<dt style="line-height:20px;"><font color="red">*</font>是否全科订单定向学员</dt>
				<dd>
					<label><input type="radio" name="isGeneralOrderOrientationTrainee" class="validate[required]" <c:if test="${extInfo.isGeneralOrderOrientationTrainee eq 'Y'}">checked="checked"</c:if>  value="Y" />是</label>&#12288;
					<label><input type="radio" name="isGeneralOrderOrientationTrainee" class="validate[required]" <c:if test="${extInfo.isGeneralOrderOrientationTrainee eq 'N'}">checked="checked"</c:if> value="N" />否</label>
				</dd>
			</dl>
            <dl class="clearfix">
              <dt><font color="red">*</font>专/本科毕业院校</dt>
              <dd class="import">
				  <input name="graduatedName" value="${extInfo.graduatedName}" type="text" class="validate[required]">
              </dd>
            </dl>
			<dl class="clearfix">
				<dt><font color="red">*</font>专/本科毕业时间</dt>
				<dd class="import">
					<input id="graduationTime" name="graduationTime" value="${extInfo.graduationTime}" type="text" readonly="readonly" class="validate[required,custom[dateFormat]]">
				</dd>
			</dl>
            <dl class="clearfix">
              <dt><font color="red">*</font>专/本科毕业专业</dt>
              <dd class="import">
				  <input name="zbkbySpe" value="${extInfo.zbkbySpe}" type="text" class="validate[required]">
              </dd>
              <dd class="message">
              </dd>
            </dl>
			<dl class="clearfix" >
				<dt><font color="red">*</font>学位</dt>
				<dd class="import">
					<select name="degreeId" class="validate[required]">
						<option></option>
						<c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
							<option value="${dict.dictId}" ${user.degreeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
						</c:forEach>
					</select>
				</dd>
			</dl>
			<dl class="clearfix">
				<dt><font color="red">*</font>是否为硕士</dt>
				<dd>
					<label><input type="radio" name="isMaster" class="validate[required]" <c:if test="${extInfo.isMaster eq 'Y'}">checked="checked"</c:if> value="Y" onchange="checkIsMaster(this)"/>是</label>&#12288;
					<label><input type="radio" name="isMaster" class="validate[required]" <c:if test="${extInfo.isMaster eq 'N'}">checked="checked"</c:if> value="N" onchange="checkIsMaster(this)"/>否</label>
				</dd>
			</dl>
			<div class="isMaster">
				<dl class="clearfix">
					<dt><font color="red">*</font>硕士毕业院校</dt>
					<dd class="import">
						<input name="masterGraSchoolName" value="${extInfo.masterGraSchoolName}" type="text" class="validate[required]">

					</dd>
				</dl>
				<dl class="clearfix">
					<dt><font color="red">*</font>硕士毕业时间</dt>
					<dd class="import">
						<input id="masterGraTime" name="masterGraTime" value="${extInfo.masterGraTime}" type="text" class="validate[required,custom[dateFormat]]" readonly="readonly">
					</dd>
				</dl>
				<dl class="clearfix">
					<dt><font color="red">*</font>硕士毕业专业</dt>
					<dd class="import">
						<input name="masterMajor" value="${extInfo.masterMajor}" type="text" class="validate[required]">
					</dd>
				</dl>
				<dl class="clearfix">
					<dt><font color="red">*</font>硕士学位</dt>
					<dd class="import">
						<select id="masterDegreeId" name="masterDegreeId" class="select validate[required]">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
								<option value="${dict.dictId}" <c:if test="${ dict.dictId eq extInfo.masterDegreeId}">selected</c:if>>${dict.dictName}</option>
							</c:forEach>
						</select>
						<input type="hidden" id="masterDegreeName" name="masterDegreeName" value="${extInfo.masterDegreeName}">
					</dd>
				</dl>
				<dl class="clearfix">
					<dt><font color="red">*</font>硕士学位类型</dt>
					<dd class="import">
						<select id="masterDegreeTypeId" name="masterDegreeTypeId" class="validate[required]">
							<option value="1" <c:if test="${extInfo.masterDegreeTypeId eq '1'}">selected</c:if>>专业型</option>
							<option  value="2" <c:if test="${extInfo.masterDegreeTypeId eq '2'}">selected</c:if>>科学型</option>
						</select>
						<input type="hidden" id="masterDegreeTypeName" name="masterDegreeTypeName" value="${extInfo.masterDegreeTypeName}">
					</dd>
				</dl>
				<dl class="clearfix">
					<dt><font color="red">*</font>是否为博士</dt>
					<dd>
						<label><input type="radio" name="isDoctor" class="validate[required]" <c:if test="${extInfo.isDoctor eq 'Y'}">checked="checked"</c:if>  value="Y" onchange="checkIsDoctor(this)"/>是</label>&#12288;
						<label><input type="radio" name="isDoctor" class="validate[required]" <c:if test="${extInfo.isDoctor eq 'N'}">checked="checked"</c:if> value="N" onchange="checkIsDoctor(this)"/>否</label>
					</dd>
				</dl>
				<div class="isDoctor">
					<dl class="clearfix">
						<dt><font color="red">*</font>博士毕业院校</dt>
						<dd class="import">
							<input name="doctorGraSchoolName" value="${extInfo.doctorGraSchoolName}" type="text" class="validate[required]">
						</dd>
					</dl>
					<dl class="clearfix">
						<dt><font color="red">*</font>博士毕业时间</dt>
						<dd class="import">
							<input id="doctorGraTime" name="doctorGraTime" value="${extInfo.doctorGraTime}" type="text" class="validate[required,custom[dateFormat]]" readonly="readonly">
						</dd>
					</dl>
					<dl class="clearfix">
						<dt><font color="red">*</font>博士毕业专业</dt>
						<dd class="import">
							<input name="doctorMajor" value="${extInfo.doctorMajor}" type="text" class="validate[required]">
						</dd>
					</dl>
					<dl class="clearfix">
						<dt><font color="red">*</font>博士学位</dt>
						<dd class="import">
							<select name="doctorDegreeId" class="validate[required]" >
								<option value="">请选择</option>
								<c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
									<option value="${dict.dictId}" <c:if test="${ dict.dictId eq extInfo.doctorDegreeId }">selected</c:if>>${dict.dictName}</option>
								</c:forEach>
							</select>
						</dd>
					</dl>
					<dl class="clearfix">
						<dt><font color="red">*</font>博士学位类型</dt>
						<dd class="import">
							<select name="doctorDegreeType" class="validate[required]">
								<option value="1" <c:if test="${extInfo.doctorDegreeType eq '1'}">selected</c:if>>专业型</option>
								<option value="2" <c:if test="${extInfo.doctorDegreeType eq '2'}">selected</c:if>>科学型</option>
							</select>
						</dd>
					</dl>
				</div>
			</div>
			<div class="noMaster noMaster2">
				<dl class="clearfix">
					<dt style="line-height:20px;"><font color="red">*</font>是否参加研究生考试</dt>
					<dd>
						<label><input type="radio" name="hasTakenMasterExam" class="validate[required] Y" <c:if test="${extInfo.hasTakenMasterExam eq 'Y'}">checked="checked"</c:if>  value="Y" onchange="checkIsExam(this)"/>是</label>&#12288;
						<label><input type="radio" name="hasTakenMasterExam" class="validate[required]" <c:if test="${extInfo.hasTakenMasterExam eq 'N'}">checked="checked"</c:if> value="N" onchange="checkIsExam(this)"/>否</label>
					</dd>
				</dl>
			</div>
			<div class="noMaster isExam">
				<dl class="clearfix">
					<dt><font color="red">*</font>研究生考试成绩</dt>
					<dd class="import">
						<input id="masterExamResult" name="masterExamResult" value="${extInfo.masterExamResult}" type="text" class="validate[required]" onchange="joinTest(this)">
					</dd>
				</dl>
				<dl class="clearfix testPart">
					<dt><font color="red">*</font>考试成绩证明材料</dt>
					<dd class="import" style="width: 300px;">
              	<span id="examCertificateUriFileSpan" style="display:${!empty extInfo.examCertificateUri?'':'none'} ">
	              	[<a href="${sysCfgMap['upload_base_url']}/${extInfo.examCertificateUri}" target="_blank">查看图片</a>]
	            </span>
						<a id="examCertificateUriFile" href="javascript:uploadFile('examCertificateUriFile','成绩证明');" class="btn ${empty extInfo.examCertificateUri?'validate[required]':''}">${empty extInfo.examCertificateUri?'':'重新'}上传</a>
						<a id="examCertificateUriFileDel" href="javascript:delFile('examCertificateUriFile');" class="btn" style="${empty extInfo.examCertificateUri?'display:none':''}">删除</a>
						<input type="hidden" id="examCertificateUriFileValue" name="examCertificateUri" value="${extInfo.examCertificateUri }">
					</dd>
					<dd class="message" style="color: red;line-height:20px;">
						限传&nbsp;${sysCfgMap['inx_image_support_suffix']}格式的图片，大小不超过${sysCfgMap['inx_image_limit_size']}M！
					</dd>
				</dl>
				<dl class="clearfix testPart">
					<dt><font color="red">*</font>是否愿意参加全省住培招录笔试</dt>
					<dd>
						<label><input type="radio" name="isJoinTest" class="validate[required]" <c:if test="${extInfo.isJoinTest eq 'Y'}">checked="checked"</c:if>  value="Y" onchange=""/>是</label>&#12288;
						<label><input type="radio" name="isJoinTest" class="validate[required]" <c:if test="${extInfo.isJoinTest eq 'N'}">checked="checked"</c:if> value="N" onchange=""/>否</label>
					</dd>
				</dl>
			</div>
            <dl class="clearfix">
              <dt><font color="red">*</font>最高学历毕业专业</dt>
              <dd class="import"><!--doctor.specialized必须存储最高学历毕业专业id，分配准考证涉及-->
				<select name="specialized" class="validate[required]">
					<option></option>
					<c:forEach items="${dictTypeEnumGraduateMajorList}" var="dict">
						<option value="${dict.dictId}" ${doctor.specialized eq dict.dictId?'selected':''}>${dict.dictName}</option>
					</c:forEach>
				</select>
              </dd>
            </dl>
			<dl class="clearfix">
				<dt><font color="red">*</font>最高学历</dt>
				<dd class="import">
					<select name="educationId" class="validate[required]">
						<option></option>
						<c:forEach items="${dictTypeEnumUserEducationList}" var="dict">
							<option value="${dict.dictId}" ${user.educationId eq dict.dictId?'selected':''}>${dict.dictName}</option>
						</c:forEach>
					</select>
				</dd>
			</dl>
            <dl class="clearfix">
              <dt><font color="red">*</font>最高学历毕业证书</dt>
              <dd class="import" style="width: 300px;">
              	<span id="certificateUriFileSpan" style="display:${!empty extInfo.certificateUri?'':'none'} ">
	              	[<a href="${sysCfgMap['upload_base_url']}/${extInfo.certificateUri}" target="_blank">查看图片</a>]
	            </span>
              	<a id="certificateUriFile" href="javascript:uploadFile('certificateUriFile','毕业证书');" class="btn ${empty extInfo.certificateUri?'validate[required]':''}">${empty extInfo.certificateUri?'':'重新'}上传</a>
              	<a id="certificateUriFileDel" href="javascript:delFile('certificateUriFile');" class="btn" style="${empty extInfo.certificateUri?'display:none':''}">删除</a>
              	<input type="hidden" id="certificateUriFileValue" name="certificateUri" value="${extInfo.certificateUri }">
              </dd>
              <dd class="message" style="color: red;line-height:20px;">
              	限传&nbsp;${sysCfgMap['inx_image_support_suffix']}格式的图片，大小不超过${sysCfgMap['inx_image_limit_size']}M！<br/>未取得证书的学员上传相应的学信网中的“学籍信息”或者学校的学历证明
              </dd>
            </dl>
            <dl class="clearfix">
              <dt>最高学历学位证书</dt>
              <dd class="import" style="width: 300px;">
              	<span id="degreeUriFileSpan" style="display:${!empty extInfo.degreeUri?'':'none'} ">
	              	[<a href="${sysCfgMap['upload_base_url']}/${extInfo.degreeUri}" target="_blank">查看图片</a>]
	            </span>
              	<a id="degreeUriFile" href="javascript:uploadFile('degreeUriFile','学位证书');" class="btn">${empty extInfo.degreeUri?'':'重新'}上传</a>
              	<a id="degreeUriFileDel" href="javascript:delFile('degreeUriFile');" class="btn" style="${empty extInfo.degreeUri?'display:none':''}">删除</a>
              	<input type="hidden" id="degreeUriFileValue" name="degreeUri" value="${extInfo.degreeUri }">
              </dd>
              <dd class="message" style="color: red;line-height:20px;">
              	限传&nbsp;${sysCfgMap['inx_image_support_suffix']}格式的图片，大小不超过${sysCfgMap['inx_image_limit_size']}M！
              </dd>
            </dl>
            <h3 style="margin-bottom:0;"></h3>
            <div class="buts"><a href="javascript:next();" class="button button-blue nextBut">上一步</a><a href="javascript:submitInfo();" class="button  button-blue nextBut">下一步</a></div>
          </div>
	</form>
  </div>
</div>
	<span id="repeatIdNoSpan" style="display: none;">请<a href='<s:url value='/inx/hbres'/>' target="_blank" style="color: blue;"> 登录 </a>或者<a href='<s:url value='/inx/hbres/forgetpasswd'/>' target="_blank" style="color: blue;"> 找回密码 </a></span>
	<c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
		<jsp:include page="/jsp/service.jsp" flush="true"></jsp:include>
	</c:if>
	<jsp:include page="/jsp/hbres/foot.jsp"  flush="true"/>
</body>
</html>