<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>湖北省住院医师规范化培训招录系统</title>
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
	function save(){
		if(!$("#infoForm").validationEngine("validate")){
			return;
		}
		jboxConfirm("确认保存？",function(){
			jboxStartLoading();
			var doctorTypeNameText = $("#doctorTypeId option:selected").text();
			$("#doctorTypeName").val(doctorTypeNameText);
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
			jboxPost('<s:url value="/inx/hbres/refinishUserInfo"/>',$("#infoForm").serialize(),function(resp){
				if(resp == '操作成功'){
					jboxTip("操作成功");
					jboxEndLoading();
					setTimeout(function(){
						jboxCloseMessager();
					},1000);
					<c:if test="${param.role eq 'hospital'}">
					window.parent.searchDoctor();
					</c:if>
				}
			},null,true);
		})
	}

	$(function(){
		$('#graduationTime').datepicker();
		$('#userBirthday').datepicker();
		$('#masterGraTime').datepicker();
		$('#doctorGraTime').datepicker();
		<c:if test="${doctor.doctorTypeId eq hBRecDocTypeEnumCompany.id || doctor.doctorTypeId eq hBRecDocTypeEnumCompanyEntrust.id}">
		$(".workOrgName").show();
		</c:if>
		<c:if test="${doctor.doctorTypeId eq hBRecDocTypeEnumGraduate.id}">
		$(".workOrgName2").show();
		</c:if>
		<c:if test="${extInfo.medicalHeaithOrgId eq '3'}">
		$(".w3").show();
		</c:if>
		<c:if test="${extInfo.medicalHeaithOrgId eq '1'}">
		$(".w1").show();
		</c:if>
		<c:if test="${doctor.doctorLicenseFlag eq 'Y'}">
		$(".doctorLicense").show();
		</c:if>
		<c:if test="${doctor.doctorLicenseFlag ne 'Y'}">
		$(".doctorLicense").hide();
		</c:if>
		<c:if test="${doctor.practPhysicianFlag eq 'Y'}">
		$(".qualifiedNo").show();
		</c:if>
		<c:if test="${doctor.practPhysicianFlag ne 'Y'}">
		$(".qualifiedNo").hide();
		</c:if>
		<c:if test="${extInfo.isDoctor eq 'Y'}">
		$(".isDoctor").show();
		</c:if>
		<c:if test="${extInfo.isMaster eq 'Y'}">
		$(".isMaster").show();
		</c:if>
		<c:if test="${extInfo.isMaster eq 'N'}">
		$(".isDoctor").hide();
		</c:if>
		<c:if test="${extInfo.isAssistance eq 'Y'}">
		$(".isAssistance").show();
		</c:if>
		<c:if test="${extInfo.isAssistance ne 'Y'}">
		$(".isAssistance").hide();
		</c:if>
	});

	function changeDoctorType(doctorTypeId){
		if(doctorTypeId=='${hBRecDocTypeEnumCompany.id}'||doctorTypeId=='${hBRecDocTypeEnumCompanyEntrust.id}'){
			$(".workOrgName").show();
			$(".workOrgName2").hide();
		}else if(doctorTypeId=="${hBRecDocTypeEnumSocial.id}"){
			$(".workOrgName").hide();
			$(".workOrgName2").hide();
		}else{
			$(".workOrgName").hide();
			$(".workOrgName2").show();
		}
	}
	function changeMedicalThings(medicalHeaithOrgId){
		if(medicalHeaithOrgId=='1'){
			$(".w1").show();
			$(".w3").hide();
		}else if(medicalHeaithOrgId=='3'){
			$(".w3").show();
			$(".w1").hide();
		}else{
			$(".w3").hide();
			$(".w1").hide();
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

	function checkIsMaster(obj){
		if($(obj).val() == "Y"){
			$(".isMaster").show();
		}else{
			$(".isMaster").hide();
			$(".isMaster input,.isMaster select").each(function(){
				$(this).val("");
			});
			$("#Ndoctor").attr("checked","checked");
			$(".isDoctor").hide();
		}
	}

	function uploadFile(type,typeName,changeFlag) {
		jboxOpen("<s:url value='/inx/hbres/uploadFile'/>?operType="+type+"&changeFlag=Y","上传"+typeName,450,150);
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
</script>
</head>
<body style="width:100%; min-width: inherit; height:100%;background-image:none; background:#fff;">
	<div class="select infoAudit" >
		<form id="infoForm" method="post">
		<table border="0" width="945" cellspacing="0" cellpadding="0">
			<input type="hidden" name="userFlow" value="${user.userFlow}"/>
			<input type="hidden" name="doctorFlow" value="${user.userFlow}"/>
			<input type="hidden" name="userCode" value="${user.userCode}"/>
			<input type="hidden" id="examCertificateUriFileValue" name="examCertificateUri" value="${extInfo.examCertificateUri }">
			<caption style="color:#459ae9;">个人信息类型</caption>
	     	 <tr>
				<th width="15%">医师类型：</th>
				<td width="28%">
					<select class="select validate[required]" name="doctorTypeId" id="doctorTypeId" onchange="changeDoctorType(this.value)">
						<c:forEach items="${hBRecDocTypeEnumList}" var="item">
							<option value="${item.id}" ${item.id eq doctor.doctorTypeId?"selected":""}>${item.name}</option>
						</c:forEach>
					</select>
					<input type="hidden" id="doctorTypeName" name="doctorTypeName" value="${doctor.doctorTypeName}">
				</td>
				<th width="15%"></th>
				<td width="25%"></td>
				<td width="20%" colspan="4"></td>
			 </tr>

				<tr class="workOrgName" style="display:none;">
					<th>委培单位同意证明：</th>
					<td>
						<span id="dispatchFileFSpan" style="display:${!empty doctor.dispatchFile?'':'none'} ">
		              	[<a href="${sysCfgMap['upload_base_url']}/${doctor.dispatchFile}" target="_blank">查看图片</a>]
		            </span>
						<a id="dispatchFileF" href="javascript:uploadFile('dispatchFileF','单位同意证明');" class="btn">${empty doctor.dispatchFile?'':'重新'}上传</a>
						<a id="dispatchFileFDel" href="javascript:delFile('dispatchFileF');" class="btn" style="${empty doctor.dispatchFile?'display:none':''}">删除</a>
						<input type="hidden" id="dispatchFileFValue" name="dispatchFile" value="${doctor.dispatchFile }">
					</td>
				</tr>
				<%--<c:if test="${extInfo.medicalHeaithOrgId eq '3'}"><!--基础医院-->--%>
					<tr class="workOrgName" style="display:none;">
						<th class="workOrgName" style="display:none;">医疗卫生机构：</th>
						<td class="workOrgName" style="display:none;">
							<select class="select select validate[required]" id="medicalHeaithOrgId" name="medicalHeaithOrgId" onchange="changeMedicalThings(this.value);" >
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumMedicalHeaithOrgList}" var="tra">
								<option value="${tra.dictId}" <c:if test="${extInfo.medicalHeaithOrgId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
							</c:forEach>
							</select>
							<input type="hidden" id="medicalHeaithOrgName" name="medicalHeaithOrgName" value="${extInfo.medicalHeaithOrgName}">
						</td>
						<th class="w3" style="display:none;">基层医疗卫生机构类型：</th>
						<td class="w3" style="display:none;">
							<select class="select select validate[required]" id="basicHealthOrgId" name="basicHealthOrgId" >
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumBasicHealthOrgList}" var="tra">
								<option value="${tra.dictId}" <c:if test="${extInfo.basicHealthOrgId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
							</c:forEach>
							</select>
							<input type="hidden" id="basicHealthOrgName" name="basicHealthOrgName" value="${extInfo.basicHealthOrgName}">
						</td>
					</tr>
				<%--</c:if>--%>
				<%--<c:if test="${extInfo.medicalHeaithOrgId eq '1'}">--%>
					<tr class="w1" style="display:none;">
						<th>医院属性：</th>
						<td>
							<select class="select select validate[required]" id="hospitalAttrId" name="hospitalAttrId">
								<option value="">请选择</option>
								<c:forEach items="${dictTypeEnumHospitalAttrList}" var="tra">
									<option value="${tra.dictId}" <c:if test="${extInfo.hospitalAttrId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
								</c:forEach>
							</select>
							<input type="hidden" id="hospitalAttrName" name="hospitalAttrName" value="${extInfo.hospitalAttrName}">
						</td>
						<th>医院类别：</th>
						<td>
							<select class="select select validate[required]" id="hospitalCategoryId" name="hospitalCategoryId">
								<option value="">请选择</option>
								<c:forEach items="${dictTypeEnumHospitalCategoryList}" var="tra">
									<option value="${tra.dictId}" <c:if test="${extInfo.hospitalCategoryId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
								</c:forEach>
							</select>
							<input type="hidden" id="hospitalCategoryName" name="hospitalCategoryName" value="${extInfo.hospitalCategoryName}">
						</td>
					</tr>
					<tr class="w1" style="display: none;">
						<th>单位性质：</th>
						<td>
							<select class="select select validate[required]" id="baseAttributeId" name="baseAttributeId">
								<option value="">请选择</option>
								<c:forEach items="${dictTypeEnumBaseAttributeList}" var="tra">
									<option value="${tra.dictId}" <c:if test="${extInfo.baseAttributeId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
								</c:forEach>
							</select>
							<input type="hidden" id="baseAttributeName" name="baseAttributeName" value="${extInfo.baseAttributeName}">
						</td>
						<th></th>
						<td></td>
					</tr>
				<%--</c:if>--%>
				<tr class="workOrgName2" style="display:none;">
					<th>在读院校名称：</th>
					<td colspan="3">
						<input type="text" name="workOrgName2" value="${doctor.workOrgName}" class="select select validate[required]"/>
					</td>
				</tr>

		</table>
		
		<table border="0" width="945" cellspacing="0" cellpadding="0">
			<caption style="color:#459ae9;">个人信息</caption>
	     	 <tr>
				<th width="15%">姓名：</th>
				<td width="28%">
					<input type="text" name="userName" value="${user.userName}" class="select select validate[required,maxSize[20]]"/>
				</td>
				<th width="15%">性别：</th>
				<td width="25%">
					<input type="radio" class='validate[required] sex' name="sexId" value='${userSexEnumMan.id}' ${user.sexId eq userSexEnumMan.id?'checked':''}/>${userSexEnumMan.name}&#12288;
					<input type="radio" class='validate[required] sex' name="sexId" value='${userSexEnumWoman.id}' ${user.sexId eq userSexEnumWoman.id?'checked':''}/>${userSexEnumWoman.name}
				</td>
				<td width="20%" rowspan="6">
					<img src="${sysCfgMap['upload_base_url']}/${doctor.doctorHeadImg}"  width="140px" height="200px"/>
					<a id="doctorHeadImgFile" href="javascript:uploadFile('doctorHeadImgFile','证件照');" class="${empty doctor.doctorHeadImg?'validate[required]':''}" >${empty doctor.doctorHeadImg?'':'重新'}上传</a>(保存后更新)
					<input type="hidden" id="doctorHeadImgFileValue" name="doctorHeadImg" value="${doctor.doctorHeadImg }">
				</td>
			</tr>
			<tr>
				<th>出生日期：</th>
				<td>
					<input type="text" id="userBirthday" readonly="readonly" name="userBirthday" value="${user.userBirthday}" onchange="checkBirthDay(this.value)" class="select validate[required,custom[dateFormat]]"/>
				</td>
				<th>年龄：</th>
				<td>
					<input type="text" name="age" value="${extInfo.age}" class="select validate[required]"/>
				</td>
			</tr>
			<tr>
				<th>证件类型：</th>
				<td>
					<select id="cretTypeId" name="cretTypeId" class="select xlselect validate[required]">
						<option value="">请选择</option>
						<c:forEach items="${certificateTypeEnumList}" var="cret">
							<option value="${cret.id}" ${user.cretTypeId eq cret.id?'selected':''}>${cret.name}</option>
						</c:forEach>
					</select>
				</td>
				<th>证件号码：</th>
				<td>
					<input type="text" name="idNo" value="${user.idNo}" class="select validate[required]" onchange="checkSelf(idNoIsOk=(this.value=='${user.idNo}'),'idNoResult');"/>
				</td>
			</tr>
			<tr>
				<th>民族：</th>
				<td>
					<select name="nationId" class="select validate[required]">
						<option/>
						<c:forEach items="${userNationEnumList}" var="nation">
							<option value="${nation.id}" ${user.nationId eq nation.id?'selected':''}>${nation.name}</option>
						</c:forEach>
					</select>
				</td>
				<th>手机：</th>
				<td>
					<input type="text" name="userPhone" value="${user.userPhone}" class="select validate[required,custom[mobile]]" onchange="checkSelf(phoneIsOk=(this.value=='${user.userPhone}'),'phoneResult');"/>
				</td>
			</tr>
			<tr>
				<th>邮箱：</th>
				<td>
					<input type="text" name="userEmail" value="${user.userEmail}" readonly="readonly" class="select"/>
				</td>
				<th>通讯地址：</th>
				<td>
					<input type="text" name="userAddress" class="select validate[required , maxSize[30]]" value="${user.userAddress}"/>
				</td>
			</tr>
			<tr>
				<th>人员属性：</th>
				<td>
					<select id="personnelAttributeId" name="personnelAttributeId" class="select xlselect validate[required]">
						<option value="">请选择</option>
						<c:forEach items="${personnelAttributeEnumList}" var="attr">
							<option value="${attr.id}" ${extInfo.personnelAttributeId eq attr.id?'selected':''}>${attr.name}</option>
						</c:forEach>
					</select>
				</td>
				<th></th>
				<td></td>
			</tr>
			<tr>
				<th>是否取得&#12288;<br>执业医师资格证：</th>
				<td>
					<label><input type="radio" name="doctorLicenseFlag" <c:if test="${doctor.doctorLicenseFlag eq 'Y'}">checked="checked"</c:if>  value="Y"  class="select validate[required]" onchange="showId(this);"/>是</label>&#12288;
					<label><input type="radio" name="doctorLicenseFlag" <c:if test="${doctor.doctorLicenseFlag eq 'N'}">checked="checked"</c:if> value="N"  class="select validate[required]" onchange="showId(this);"/>否</label>
				</td>
				<th class="doctorLicense">医师资格证号：</th>
				<td class="doctorLicense">
					<input type="text" name="doctorLicenseNo" value="${doctor.doctorLicenseNo}" class="select validate[required] " >
				</td>
			</tr>
			<tr class="doctorLicense">
				<th>医师资格证：</th>
				<td>
					<span id="qualifiedFileFSpan" style="display:${!empty doctor.qualifiedFile?'':'none'} ">
	              	[<a href="${sysCfgMap['upload_base_url']}/${doctor.qualifiedFile}" target="_blank">查看图片</a>]
	            	</span>
					<a id="qualifiedFileF" href="javascript:uploadFile('qualifiedFileF','医师资格证');" class="btn">${empty doctor.qualifiedFile?'':'重新'}上传</a>
					<a id="qualifiedFileFDel" href="javascript:delFile('qualifiedFileF');" class="btn" style="${empty doctor.qualifiedFile?'display:none':''}">删除</a>
					<input type="hidden" id="qualifiedFileFValue" name="qualifiedFile" value="${doctor.qualifiedFile }">
				</td>
				<th></th>
				<td></td>
			</tr>
			<tr>
				<th>是否取得执业证：</th>
				<td>
					<label><input type="radio" name="practPhysicianFlag" <c:if test="${doctor.practPhysicianFlag eq 'Y'}">checked="checked"</c:if>  value="Y"  class="select validate[required]" onchange="showId2(this);"/>是</label>&#12288;
					<label><input type="radio" name="practPhysicianFlag" <c:if test="${doctor.practPhysicianFlag eq 'N'}">checked="checked"</c:if> value="N"  class="select validate[required]" onchange="showId2(this);"/>否</label>
				</td>
				<th class="qualifiedNo">执业医师证号：</th>
				<td class="qualifiedNo">
					<input type="text" name="practPhysicianCertificateNo" value="${doctor.practPhysicianCertificateNo}" class="select validate[required]" >
				</td>
			</tr>

			<tr>
				<th>是否为援疆援藏&#12288;<br>住院医师：</th>
				<td>
					<label><input type="radio" name="isAssistance" <c:if test="${extInfo.isAssistance eq 'Y'}">checked="checked"</c:if>  value="Y"  class="select validate[required]" onchange="showId3(this);"/>是</label>&#12288;
					<label><input type="radio" name="isAssistance" <c:if test="${extInfo.isAssistance eq 'N'}">checked="checked"</c:if> value="N"  class="select validate[required]" onchange="showId3(this);"/>否</label>
				</td>
				<th class="isAssistance">援疆援藏住院医师&#12288;<br>送出省份：</th>
				<td class="isAssistance">
					<input type="text" name="assistanceProvince" value="${extInfo.assistanceProvince}" class="select validate[required]" >
				</td>
			</tr>
			<tr>
				<th class="isAssistance">援疆援藏住院医师&#12288;<br>送出单位统一社会&#12288;<br>信用代码/组织机构&#12288;<br>代码：</th>
				<td class="isAssistance"><input type="text" name="assistanceCode" value="${extInfo.assistanceCode}" class="select"></td>
				<th>紧急联系人：</th>
				<td><input type="text" name="emergencyName" value="${doctor.emergencyName}" class="select"/></td>
			</tr>

			<tr>
				<th>紧急联系方式：</th>
				<td><input type="text" name="emergencyPhone" value="${doctor.emergencyPhone}" class="select"/></td>
				<th>与本人关系：</th>
				<td><input type="text" name="emergencyRelation" value="${doctor.emergencyRelation}" class="select"/></td>
			</tr>
		</table>
		
		<table border="0" width="945" cellspacing="0" cellpadding="0" >
			<caption style="color:#459ae9;">学历信息登记</caption>
			<tr>
				<th width="15%">是否为应届生：</th>
				<td width="28%"><label><input type="radio" name="isYearGraduate" class="select validate[required]" <c:if test="${doctor.isYearGraduate eq 'Y'}">checked="checked"</c:if>  value="Y" />是</label>&#12288;
					<label><input type="radio" name="isYearGraduate" class="select validate[required]" <c:if test="${doctor.isYearGraduate eq 'N'}">checked="checked"</c:if> value="N" />否</label></td>
				<th>是否全科订单走向学员：</th>
				<td><label><input type="radio" name="isGeneralOrderOrientationTrainee" class="select validate[required]" <c:if test="${extInfo.isGeneralOrderOrientationTrainee eq 'Y'}">checked="checked"</c:if>  value="Y" />是</label>&#12288;
					<label><input type="radio" name="isGeneralOrderOrientationTrainee" class="select validate[required]" <c:if test="${extInfo.isGeneralOrderOrientationTrainee eq 'N'}">checked="checked"</c:if> value="N" />否</label></td>
			</tr>
			<tr>
				<th width="15%">专/本科毕业院校：</th>
				<td><input name="graduatedName" value="${extInfo.graduatedName}" type="text" class="select validate[required]"></td>
				<th>专/本科毕业时间：</th>
				<td><input id="graduationTime" name="graduationTime" value="${extInfo.graduationTime}" type="text" readonly="readonly" class="select validate[required,custom[dateFormat]]"></td>
			</tr>
			<tr>
				<th>专/本科毕业专业：</th>
				<td><input name="zbkbySpe" value="${extInfo.zbkbySpe}" type="text" class="select validate[required]"></td>
				<th>学位：</th>
				<td><select name="degreeId" class="select validate[required]">
					<option></option>
					<c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
						<option value="${dict.dictId}" ${user.degreeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
					</c:forEach>
				</select></td>
			</tr>
				<tr>
					<th>是否为硕士：</th>
					<td>
						<label><input type="radio" name="isMaster" class="validate[required]" <c:if test="${extInfo.isMaster eq 'Y'}">checked="checked"</c:if> value="Y" onchange="checkIsMaster(this)"/>是</label>&#12288;
						<label><input type="radio" name="isMaster" class="validate[required]" <c:if test="${extInfo.isMaster eq 'N'}">checked="checked"</c:if> value="N" onchange="checkIsMaster(this)"/>否</label>
					</td>
					<th class="isMaster" style="display: none">硕士毕业院校：</th>
					<td class="isMaster" style="display: none">
						<input name="masterGraSchoolName" value="${extInfo.masterGraSchoolName}" type="text" class="select validate[required]">
					</td>
				</tr>
				<tr class="isMaster" style="display: none">
					<th>硕士毕业时间：</th>
					<td>
						<input id="masterGraTime" name="masterGraTime" value="${extInfo.masterGraTime}" type="text" class="select validate[required,custom[dateFormat]]" readonly="readonly">
					</td>
					<th>硕士毕业专业：</th>
					<td>
						<input name="masterMajor" value="${extInfo.masterMajor}" type="text" class="select validate[required]">
					</td>
				</tr>
				<tr class="isMaster" style="display: none">
					<th>硕士学位：</th>
					<td>
						<select id="masterDegreeId" name="masterDegreeId" class="select validate[required]">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
								<option value="${dict.dictId}" <c:if test="${ dict.dictId eq extInfo.masterDegreeId}">selected</c:if>>${dict.dictName}</option>
							</c:forEach>
						</select>
						<input type="hidden" id="masterDegreeName" name="masterDegreeName" value="${extInfo.masterDegreeName}">
					</td>
					<th>硕士学位类型：</th>
					<td>
						<select id="masterDegreeTypeId" name="masterDegreeTypeId" class="select validate[required]">
							<option value="1" <c:if test="${extInfo.masterDegreeTypeId eq '1'}">selected</c:if>>专业型</option>
							<option  value="2" <c:if test="${extInfo.masterDegreeTypeId eq '2'}">selected</c:if>>科学型</option>
						</select>
						<input type="hidden" id="masterDegreeTypeName" name="masterDegreeTypeName" value="${extInfo.masterDegreeTypeName}">
					</td>
				</tr>

					<tr>
						<th>是否为博士：</th>
						<td>
							<label><input type="radio" name="isDoctor" class="" <c:if test="${extInfo.isDoctor eq 'Y'}">checked="checked"</c:if>  value="Y" onchange="checkIsDoctor(this)"/>是</label>&#12288;
							<label><input type="radio" name="isDoctor" class="" <c:if test="${extInfo.isDoctor eq 'N'}">checked="checked"</c:if> value="N" id="Ndoctor" onchange="checkIsDoctor(this)"/>否</label>
						</td>
						<th class="isDoctor" style="display: none">博士毕业院校：</th>
						<td class="isDoctor" style="display: none">
							<input name="doctorGraSchoolName" value="${extInfo.doctorGraSchoolName}" type="text" class="select validate[required]">
						</td>
					</tr>
					<tr class="isDoctor" style="display: none">
						<th>博士毕业时间：</th>
						<td>
							<input id="doctorGraTime" name="doctorGraTime" value="${extInfo.doctorGraTime}" type="text" class="select validate[required,custom[dateFormat]]" readonly="readonly">
						</td>
						<th>博士毕业专业：</th>
						<td>
							<input name="doctorMajor" value="${extInfo.doctorMajor}" type="text" class="select validate[required]">
						</td>
					</tr>
					<tr class="isDoctor" style="display: none">
						<th>博士学位：</th>
						<td>
							<select name="doctorDegreeId" class="select validate[required]" >
								<option value="">请选择</option>
								<c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
									<option value="${dict.dictId}" <c:if test="${ dict.dictId eq extInfo.doctorDegreeId }">selected</c:if>>${dict.dictName}</option>
								</c:forEach>
							</select>
						</td>
						<th>博士学位类型：</th>
						<td>
							<select name="doctorDegreeType" class="select validate[required]">
								<option value="1" <c:if test="${extInfo.doctorDegreeType eq '1'}">selected</c:if>>专业型</option>
								<option value="2" <c:if test="${extInfo.doctorDegreeType eq '2'}">selected</c:if>>科学型</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>最高学历毕业专业：</th>
						<td>
							<select name="specialized" class="validate[required] select">
								<option></option>
								<c:forEach items="${dictTypeEnumGraduateMajorList}" var="dict">
									<option value="${dict.dictId}" ${doctor.specialized eq dict.dictId?'selected':''}>${dict.dictName}</option>
								</c:forEach>
							</select>
						</td>
						<th>最高学历：</th>
						<td>
							<select name="educationId" class="validate[required] select">
								<option></option>
								<c:forEach items="${dictTypeEnumUserEducationList}" var="dict">
									<option value="${dict.dictId}" ${user.educationId eq dict.dictId?'selected':''}>${dict.dictName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
			<tr>
				<th>最高学历毕业证书：</th>
				<td>
					<span id="certificateUriFileSpan" style="display:${!empty extInfo.certificateUri?'':'none'} ">
	              	[<a href="${sysCfgMap['upload_base_url']}/${extInfo.certificateUri}" target="_blank">查看图片</a>]
	            </span>
					<a id="certificateUriFile" href="javascript:uploadFile('certificateUriFile','毕业证书');" class="btn ">${empty extInfo.certificateUri?'':'重新'}上传</a>
					<a id="certificateUriFileDel" href="javascript:delFile('certificateUriFile');" class="btn" style="${empty extInfo.certificateUri?'display:none':''}">删除</a>
					<input type="hidden" id="certificateUriFileValue" name="certificateUri" value="${extInfo.certificateUri }">

				</td>
				<th>最高学历学位证书：</th>
				<td>
					<span id="degreeUriFileSpan" style="display:${!empty extInfo.degreeUri?'':'none'} ">
	              	[<a href="${sysCfgMap['upload_base_url']}/${extInfo.degreeUri}" target="_blank">查看图片</a>]
	            </span>
					<a id="degreeUriFile" href="javascript:uploadFile('degreeUriFile','学位证书');" class="btn">${empty extInfo.degreeUri?'':'重新'}上传</a>
					<a id="degreeUriFileDel" href="javascript:delFile('degreeUriFile');" class="btn" style="${empty extInfo.degreeUri?'display:none':''}">删除</a>
					<input type="hidden" id="degreeUriFileValue" name="degreeUri" value="${extInfo.degreeUri }">

				</td>
			</tr>
		</table>
		</form>
		<div align="center" style="margin-top: 20px; margin-bottom:20px;">
			<input type="button" style="width:100px;" class="btn_blue" onclick="save();" value="保&#12288;存"></input>
			<input type="button" style="width:100px;" class="btn_blue" onclick="top.jboxCloseMessager();" value="关&#12288;闭"></input>
		</div>
	</div>
</body>
</html>