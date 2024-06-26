<script type="text/javascript">
$(function(){
	changeWorkAdress("${doctor.doctorTypeId}");
	changeLevel("${doctor.workOrgLevelId}");
	$("#showImg").load(function(){
		$("#idNoWatermark").show();
	});
	//隐藏本科，硕士和博士操作
	checkBx("${userResumeExt.isMaster}",'master');
	checkBx("${userResumeExt.isDoctor}",'doctor');
	//隐藏在读，专科操作
	checkBx("${userResumeExt.isReading}",'reading');
	checkBx("${userResumeExt.isJuniorCollege}",'junior');
    checkBx("${userResumeExt.isUndergraduate}",'undergrad');
	changeOrgLevel("${userResumeExt.medicalHeaithOrgId}");

	// 学历
	showDiploma("graduate","${userResumeExt.isCollegeHaveDiploma}");
	showDiploma("master","${userResumeExt.isMasterHaveDiploma}");
	showDiploma("doctor","${userResumeExt.isDoctorHaveDiploma}");
    showDiploma("undergrad","${userResumeExt.isCollegeHaveDiploma}");

	// 学位
	showDegree("graduate","${userResumeExt.isCollegeDegreeCertificate}");
	showDegree("master","${userResumeExt.isMasterDegreeCertificate}");
	showDegree("doctor","${userResumeExt.isDoctorDegreeCertificate}");

	westernSupport("${userResumeExt.westernSupportResidents}");
	showTime("${userResumeExt.isPassQualifyingExamination}");
	examsTime("${userResumeExt.isHaveQualificationCertificate}");
	showTime2("${userResumeExt.isHavePracticingCategory}");
	// 英语 外语能力
	var foreignSkillsValue = "${doctor.englishGradeExamType}"
	if('4' == foreignSkillsValue){
		$(".foreignSkillsClass").show();
	}
	var englishGradeExamTypeValue = "${doctor.englishGradeExamType}"
	if('5' == englishGradeExamTypeValue){
		$(".englishGradeExamClass").hide();
		$(".englishAbilityClass").hide();
	}
	var englishAbility = "${doctor.englishAbility}"
	if('1' == englishAbility){
		$(".englishGradeExamClass").hide();
	}
});

function changeLevel(orgLevel){
	if(orgLevel!=""){
		$("#"+orgLevel).show();
	}
}
function checkBx(value,type){

	if(value=="${GlobalConstant.FLAG_N}"){
		$("."+type).hide();
		$("."+type+"Th").hide();
		$("."+type+"Td").attr("colspan",3);
		$("."+type).val("");
		$("."+type+"Red").hide();
	}else{
		$("."+type).show();
		$("."+type+"Th").show();
		$("."+type+"Td").removeAttr("colspan");
		$("."+type+"Red").show();
	}
}

function changeWorkAdress(doctorTypeId){
	if(doctorTypeId == "${jsResDocTypeEnumCompanyEntrust.id}")
	{
		$(".workUniteCreditCode").show();
	}else{
		$("#workUniteCreditCode").val("");
		$(".workUniteCreditCode").hide();
	}
	if(doctorTypeId == "${jsResDocTypeEnumCompany.id}"||doctorTypeId == "${jsResDocTypeEnumCompanyEntrust.id}"){
		if($("#Td").is(":hidden"))
		{
			$("#medicalHeaithOrgIdTd").attr("colspan",4);
		}else{
			$("#medicalHeaithOrgIdTd").removeAttr("colspan");
		}
		$(".school").hide();
		$(".address").show();
		$("#psxx").val("");
		$("#doctorTypeNameTd").removeAttr("colspan");
	}
	if(doctorTypeId == "${jsResDocTypeEnumSocial.id}"){
		$(".school").hide();
		$(".hospital").hide();
		$(".medical").hide();
		$(".hospital").val("");
		$(".medical").val("");
		$(".address").hide();
		$(".address").val("");
		$("#psdw").val("");
		$("#psxx").val("");
		$("#orgRank").val("");
		$("#orgLevel").val("");
		$("#doctorTypeNameTd").attr("colspan",4);
	}
	if(doctorTypeId == "${jsResDocTypeEnumGraduate.id}"){
		$(".address").hide();
		$(".hospital").hide();
		$(".address").val("");
		$(".medical").hide();
		$(".hospital").val("");
		$(".medical").val("");
		$("#psdw").val("");
		$(".school").show();
		$("#orgRank").val("");
		$("#orgLevel").val("");
		$("#doctorTypeNameTd").removeAttr("colspan");
	}
	if(doctorTypeId==""){
		$(".school").hide();
		$(".address").hide();
		$("#doctorTypeNameTd").attr("colspan",4);
	}
}
function changeOrgLevel(value){
	if(value!=""){
		$(".dict").hide();
		$("#"+value).show();
	}else{
		$(".dict").hide();
	}
	if(value=="1"){
		$(".medical").hide();
		$("#medicalHeaithOrgIdTd").removeAttr("colspan");
		$(".hospital").show();
		$("#TD").attr("colspan",2);
	} 
	if(value=="2"){
		$(".medical").hide();
		$("#medicalHeaithOrgIdTd").attr("colspan",4);
		$(".hospital").hide();
	}
	if(value==""){
		$(".medical").hide();
		$("#medicalHeaithOrgIdTd").attr("colspan",4);
		$(".hospital").hide();
	}
	if(value=="3"){
		$(".medical").show();
		$(".hospital").hide();
		$("#medicalHeaithOrgIdTd").removeAttr("colspan");
	}
}
function showDiploma(obj,flag){
	if(flag == "Y"){
		$("#"+obj+"Th1").show();
		$("."+obj+"Th1").show();
	}else{
		$("#"+obj+"Th1").hide();
		$("."+obj+"Th1").hide();
	}
}
function showDegree(obj,flag){
	if(flag == "Y"){
        $("."+obj+"Th2").show();
		$("#"+obj+"Th2").show();
	}else{
        $("."+obj+"Th2").hide();
        $("#"+obj+"Th2").hide();

	}
}
function westernSupport(flag){
	if(flag == "Y"){
		$(".western").show();
	}else{
//		$(".westernValue").each(function(){
//			$(this).val("");
//		});
		$(".western").hide();
	}
}

function examsTime(flag){
	if(flag == "Y"){
		$(".examTime").show();
	}else{
//		$(".examTimeValue").each(function(){
//			$(this).val("");
//		});
		$(".examTime").hide();
	}
	showScoreType();
}

function showTime(flag){
	if(flag == "Y"){
		$(".examinationTime").show();
	}else{
		$(".examinationTimeValue").each(function(){
			$(this).val("");
		});
		$(".examinationTime").hide();
	}
	showScoreType();
}
function showScoreType()
{
	var isPassQualifyingExamination='${userResumeExt.isPassQualifyingExamination}';
	var isHaveQualificationCertificate='${userResumeExt.isHaveQualificationCertificate}';
	if(isPassQualifyingExamination=="Y"&& isHaveQualificationCertificate!="Y")
	{
		$(".scoreType").show();
	}else{
		$(".scoreType").hide();
	}
}
function showTime2(flag){
	if(flag == "Y"){
		$(".showTime2").show();
	}else{
		$(".showTime2Value").each(function(){
			$(this).val("");
		});
		$(".showTime2").hide();
	}
}
</script>
 <div class="${empty isDoctor?'infoAudit':''}">
	<div class="${'open' eq param.type?'infoAudit2':'main_bd' }">
		<div class="${'open' eq param.type?'search_table':'div_table' }">
			<h4>基本信息</h4>
			<table border="0" cellpadding="0" cellspacing="0" class="base_info">
			<colgroup>
				<col width="15%"/>
				<col width="30%"/>
				<col width="15%"/>
				<col width="21%"/>
				<col width="19%"/>
			</colgroup>
			<tbody>
			    <tr>
			    	<th>姓&#12288;&#12288;名：</th>
			        <td>${user.userName}</td>
			        <th>性&#12288;&#12288;别：</th>
			        <td class="fontc" style="padding-left:10px;">${user.sexName}</td>
			        <td rowspan="6" style="text-align: center;">
			        	<font id="idNoWatermark"  style="display:none;font-size:9px;color:red; position:relative; top: 110px;">${user.idNo }</font>
						<img src="${sysCfgMap['upload_base_url']}/${user.userHeadImg}" style="margin-top: -30px;"
							 id="showImg" width="130px" height="150px"
							 onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
			        </td>
			    </tr>
			    <tr>
			    	<th>证件类型：</th>
			        <td>${user.cretTypeName}</td>
			        <th>证&nbsp;件&nbsp;&nbsp;号：</th>
			        <td>${user.idNo}</td>
				</tr>
			    <tr>
			    	<th>民&#12288;&#12288;族：</th>
			        <td>${user.nationName}</td>
			        <th>生&#12288;&#12288;日：</th>
			        <td>${user.userBirthday}</td>
			    </tr>
				<tr>
					<th>婚姻状况：</th>
					<td >
							${userResumeExt.maritalStatus eq 1?"未婚":""}
							${userResumeExt.maritalStatus eq 2?"已婚":""}
							${userResumeExt.maritalStatus eq 3?"初婚":""}
							${userResumeExt.maritalStatus eq 4?"再婚":""}
							${userResumeExt.maritalStatus eq 5?"复婚":""}
							${userResumeExt.maritalStatus eq 6?"丧偶":""}
							${userResumeExt.maritalStatus eq 7?"离婚":""}
					</td>
					<th>国&#12288;&#12288;籍：</th>
					<td>${user.nationalityName}</td>
				</tr>
			    <tr>
			    	<th>手&#12288;&#12288;机：</th>
			        <td readonly="readonly">${user.userPhone}</td>
			        <th>固定电话：</th>
			        <td>${userResumeExt.telephone}</td>
               </tr>
			    <tr>
			    	<th>计算机能力：</th>
			        <td>${doctor.computerSkills}</td>
			        <th class="foreignSkillsClass" style="display: none">外语能力：</th>
			        <td class="foreignSkillsClass" style="display: none">${doctor.foreignSkills}</td>
			    </tr>
				<tr >
					<th><span class="red">*</span>外语等级考试类型：</th>
					<td>
						<c:forEach items="${dictTypeEnumEnglishGradeExamTypeList}" var="nationalityEnum">
							<c:if test="${doctor.englishGradeExamType eq nationalityEnum.dictId}">
								${nationalityEnum.dictName}
							</c:if>
						</c:forEach>
					</td>

					<th class="englishAbilityClass">英语能力：</th>
					<td class="englishAbilityClass">
						<c:forEach items="${dictTypeEnumEnglishAbilityList}" var="nationalityEnum">
							<c:if test="${doctor.englishAbility eq nationalityEnum.dictId}">
								${nationalityEnum.dictName}
							</c:if>
						</c:forEach>
					</td>
				</tr>
				<tr class="englishGradeExamClass">
					<th>外语等级考试证书编号：</th>
					<td>${doctor.englishCertificateNumber}</td>
					<th>外语等级考试证书取得日期：</th>
					<td>${doctor.englishCertificateDate}</td>
				</tr>
				<tr>
			    	<th>身份证正面：</th>
			        <td>
						<c:if test="${!empty user.idcardFrontImg}">
							[<a href="${sysCfgMap['upload_base_url']}/${user.idcardFrontImg}" target="_blank">查看图片</a>]&nbsp;
						</c:if>
						<c:if test="${empty user.idcardFrontImg}">无</c:if>
					</td>
			        <th>身份证反面：</th>
					<td>
						<c:if test="${!empty user.idcardOppositeImg}">
							[<a href="${sysCfgMap['upload_base_url']}/${user.idcardOppositeImg}" target="_blank">查看图片</a>]&nbsp;
						</c:if>
						<c:if test="${empty user.idcardFrontImg}">无</c:if>
					</td>
			    </tr>
			    <tr>
			    	<th>电子邮箱：</th>
			        <td colspan="4">${user.userEmail}</td>
			    </tr>
			    <tr>
			    	<th>人员类型：</th>
			        <td id="doctorTypeNameTd">${doctor.doctorTypeName}</td>
			         <th class="school">派送学校：</th>
			        <td class="school" colspan="2">
			        	<label>${doctor.workOrgName}</label>
			        </td>
			        <th class="address">派送单位：</th>
			        <td colspan="2" class="address">${doctor.workOrgName}</td>
			    </tr>
				<tr class="workUniteCreditCode">
					<th>工作单位统一信用代码(15或18位)：</th>
					<td colspan="4">
						${userResumeExt.workUniteCreditCode}
					</td>
				</tr>
				<tr class="workUniteCreditCode" style="display: none;">
					<th>工作单位：</th>
					<td >
						${userResumeExt.workUnit}
					</td>
					<th>是否军队医院：</th>
					<td colspan="2">
						<c:if test="${userResumeExt.armyHospital eq GlobalConstant.FLAG_Y }">是</c:if>
						<c:if test="${userResumeExt.armyHospital eq GlobalConstant.FLAG_N }">否</c:if>
					</td>
				</tr>
<!-- 			     <tr class="address"> -->
<!-- 			    	<th>单位等级：</th> -->
<!-- 		    	    <td colspan="4" style="padding-left:10px;"> -->
<%-- 		    	    <c:forEach items="${dictTypeEnumBaseLevelList}" var="tra"> --%>
<%-- 						<c:if test="${doctor.workOrgLevelId eq tra.dictId}">${tra.dictName}</c:if> --%>
<%-- 					</c:forEach> --%>
<!-- 		    	    </td> -->
<!-- 			    </tr> -->
				<tr>
					<th>医疗卫生机构：</th>
					<td colspan="4">${userResumeExt.medicalHeaithOrgName}</td>
					<%--<th class="hospital" style="display: none;">医院属性：</th>
					<td colspan="2" class="hospital">${userResumeExt.hospitalAttrName}</td>
					<th class="medical" style="display: none;">基层医疗卫生机构：</th>
					<td colspan="2"  class="medical" >${userResumeExt.basicHealthOrgName}</td>--%>
				</tr>
			<%--	<tr style="display: none;" class="hospital">
					<th>医院类别：</th>
					<td>${userResumeExt.hospitalCategoryName}</td>
					<th>单位性质：</th>
			    	<td colspan="2">${userResumeExt.baseAttributeName}</td>
				</tr>--%>
			    <tr>
			    	<th>本人通讯地址：</th>
			        <td colspan="4">${user.userAddress}</td>
			    </tr>
			    <tr>
			    	<th>紧急联系人：</th>
			        <td>${doctor.emergencyName}</td>
			        <th>联系人手机：</th>
			        <td colspan="2">${doctor.emergencyPhone}</td>
			    </tr>
			    <tr>
			    	<th>紧急联系人地址：</th>
			        <td colspan="4">${userResumeExt.emergencyAddress}</td>
			    </tr>
				<tr>
					<th>户口所在地省行政区划：</th>
					<td colspan="4">
							${userResumeExt.locationOfProvince eq 1?"北京":""}
							${userResumeExt.locationOfProvince eq 2?"天津":""}
							${userResumeExt.locationOfProvince eq 3?"河北":""}
							${userResumeExt.locationOfProvince eq 4?"山西":""}
							${userResumeExt.locationOfProvince eq 5?"内蒙古":""}
							${userResumeExt.locationOfProvince eq 6?"辽宁":""}
							${userResumeExt.locationOfProvince eq 7?"吉林":""}
							${userResumeExt.locationOfProvince eq 8?"黑龙江":""}
							${userResumeExt.locationOfProvince eq 9?"上海":""}
							${userResumeExt.locationOfProvince eq 10?"江苏":""}
							${userResumeExt.locationOfProvince eq 11?"浙江":""}
							${userResumeExt.locationOfProvince eq 12?"安徽":""}
							${userResumeExt.locationOfProvince eq 13?"福建":""}
							${userResumeExt.locationOfProvince eq 14?"江西":""}
							${userResumeExt.locationOfProvince eq 15?"山东":""}
							${userResumeExt.locationOfProvince eq 16?"河南":""}
							${userResumeExt.locationOfProvince eq 17?"湖北":""}
							${userResumeExt.locationOfProvince eq 18?"湖南":""}
							${userResumeExt.locationOfProvince eq 19?"广东":""}
							${userResumeExt.locationOfProvince eq 20?"广西":""}
							${userResumeExt.locationOfProvince eq 21?"海南":""}
							${userResumeExt.locationOfProvince eq 22?"重庆":""}
							${userResumeExt.locationOfProvince eq 23?"四川":""}
							${userResumeExt.locationOfProvince eq 24?"贵州":""}
							${userResumeExt.locationOfProvince eq 25?"云南":""}
							${userResumeExt.locationOfProvince eq 26?"西藏":""}
							${userResumeExt.locationOfProvince eq 27?"陕西":""}
							${userResumeExt.locationOfProvince eq 28?"甘肃":""}
							${userResumeExt.locationOfProvince eq 29?"青海":""}
							${userResumeExt.locationOfProvince eq 30?"宁夏":""}
							${userResumeExt.locationOfProvince eq 31?"新疆":""}
							${userResumeExt.locationOfProvince eq 32?"兵团":""}
							${userResumeExt.locationOfProvince eq 33?"香港":""}
							${userResumeExt.locationOfProvince eq 34?"澳门":""}
							${userResumeExt.locationOfProvince eq 35?"台湾":""}
						</select>
					</td>
				</tr>
			 </tbody>
		</table>
	</div>
	<div class="div_table">
		<h4>教育情况</h4>		
		<table border="0" cellpadding="0" cellspacing="0" class="base_info">
			<colgroup>
				<col width="15%"></col>
				<col width="30%"></col>
				<col width="15%"></col>
				<col width="40%"></col>
			</colgroup>
			<tbody>
				<tr>
					<th>是否为应届生：</th>			    
			        <td>&nbsp;<c:if test="${doctor.isYearGraduate eq GlobalConstant.FLAG_Y }">是</c:if>
			        	 <c:if test="${doctor.isYearGraduate eq GlobalConstant.FLAG_N }">否</c:if>
			        </td>
					<th>是否为农村订单定向免费医学毕业生：</th>
			        <td>&nbsp;<c:if test="${userResumeExt.isGeneralOrderOrientationTrainee eq GlobalConstant.FLAG_Y }">是</c:if>
			        	 <c:if test="${userResumeExt.isGeneralOrderOrientationTrainee eq GlobalConstant.FLAG_N }">否</c:if>
			        </td>
				</tr>
				<%--在读--%>
				<tr id="reading">
					<th><span class="red">*</span>是否在读：</th>
					<td colspan="3">
						<c:if test="${userResumeExt.isReading eq GlobalConstant.FLAG_Y }">是</c:if>
						<c:if test="${userResumeExt.isReading eq GlobalConstant.FLAG_N }">否</c:if>
					</td>
				</tr>
				<tr  class="readingTh" >
					<th><span class="red yy">*</span>在读学历：</th>
					<td>
						<c:forEach var="dict" items="${dictTypeEnumUserEducationList }">
							<c:if test="${userResumeExt.readingCollege eq dict.dictId}">
								${dict.dictName}
							</c:if>
						</c:forEach>
					</td>
					<th><span class="red yy">*</span>预计毕业时间：</th>
					<td>${userResumeExt.readingDate}</td>
				</tr>
				<tr class="readingTh"  >
					<th>在读院校：</th>
					<td>${userResumeExt.readingSchoolName}</td>
					<c:if test="${userResumeExt.readingSchoolId eq GlobalConstant.OTHER_GRADUATE_SCHOOL_CODE }">
						<th><span class="red">*</span>其他在读院校：</th>
						<td>${userResumeExt.readingOtherSchoolsName}</td>
					</c:if>
				</tr>
				<tr class="readingTh"  >
					<th>在读专业：</th>
					<td>${userResumeExt.readingProfession}</td>
					<c:if test="${userResumeExt.readingProfessionId eq GlobalConstant.OTHER_GRADUATEMAJOR_CODE }">
						<th><span class="red">*</span>其他在读专业：</th>
						<td>${userResumeExt.readingOtherProfession}</td>
					</c:if>
				</tr>
				<%--在读--%>

				<%-- 大专 start--%>
				<c:if test="${user.trainingTypeId eq 'AssiGeneral'}">
				<tr>
					<th><span class="red">*</span> 是否大专：</th>
					<td colspan="3" id="junior">
						<c:if test="${userResumeExt.isJuniorCollege eq GlobalConstant.FLAG_Y }">是</c:if>
						<c:if test="${userResumeExt.isJuniorCollege eq GlobalConstant.FLAG_N }">否</c:if>
					</td>
				</tr>
				<tr  class="juniorTh">
					<th ><span class="red">*</span> 是否全日制：</th>
					<td >
						<c:if test="${userResumeExt.juniorCollegeFullTime eq GlobalConstant.FLAG_Y }">是</c:if>
						<c:if test="${userResumeExt.juniorCollegeFullTime eq GlobalConstant.FLAG_N }">否</c:if>
					</td>
					<th><span class="red">*</span>大专毕业时间：</th>
					<td>${userResumeExt.juniorCollegeGradate}</td>
				</tr>
				<tr  class="juniorTh">
					<th>大专毕业院校：</th>
					<td>${userResumeExt.juniorCollegeSchoolName}</td>
					<c:if test="${userResumeExt.juniorCollegeSchoolId eq GlobalConstant.OTHER_GRADUATE_SCHOOL_CODE }">
						<th><span class="red">*</span>其他大专毕业院校：</th>
						<td>${userResumeExt.juniorOtherSchoolsName}</td>
					</c:if>
				</tr>
				<tr  class="juniorTh">
					<th>大专毕业专业：</th>
					<td>${userResumeExt.juniorCollegeProfession}</td>
					<c:if test="${userResumeExt.juniorCollegeProfessionId eq GlobalConstant.OTHER_GRADUATEMAJOR_CODE }">
						<th>其他大专在读专业：</th>
						<td>${userResumeExt.juniorOtherProfession}</td>
					</c:if>
				</tr>

				<tr  class="juniorTh">
					<th>是否获得毕业证书：</th>
					<td>
						<c:if test="${userResumeExt.isJuniorCollegeCertificate eq GlobalConstant.FLAG_Y }">是</c:if>
						<c:if test="${userResumeExt.isJuniorCollegeCertificate eq GlobalConstant.FLAG_N }">否</c:if>
					</td>
					<th class="isCertificateTh3" >证书照片：</th>
					<td class="isCertificateTh3" >
						<c:if test="${!empty userResumeExt.juniorCollegeUrl}">
							[<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.juniorCollegeUrl}" target="_blank">查看图片</a>]&nbsp;
						</c:if>
						<c:if test="${empty userResumeExt.juniorCollegeUrl}">无</c:if>
					</td>
				</tr>
				<tr class="juniorTh isCertificateTh3"  style="display: none" >
					<th>学历证书编号：</th>
					<td>${userResumeExt.juniorCollegeCode}</td>
					<th>证书取得时间：</th>
					<td>${userResumeExt.juniorCollegeDate}</td>
				</tr>
				</c:if>
				<%-- 大专 end--%>

				<%-- 本科 start--%>
				<c:if test="${user.trainingTypeId eq 'DoctorTrainingSpe'}">
				<tr>
					<th><span class="red">*</span> 是否本科：</th>
					<td colspan="3" id="undergrad">
						<c:if test="${userResumeExt.isUndergraduate eq GlobalConstant.FLAG_Y }">是</c:if>
						<c:if test="${userResumeExt.isUndergraduate eq GlobalConstant.FLAG_N }">否</c:if>
					</td>
				</tr>
			     <tr class="undergradTh">
			    	<th>本科毕业院校：</th>
			        <td>${userResumeExt.graduatedName}</td>
					 <th>本科毕业时间：</th>
					 <td >${userResumeExt.graduationTime}</td>
			    </tr>
			    <tr class="undergradTh">
					<th>本科毕业专业：</th>
					<td>${userResumeExt.specialized}</td>
			        <th>学位：</th>
		            <td >${userResumeExt.degreeName}</td>
			    </tr>
				<tr class="graduateTh undergradTh">
					<th>是否获得毕业证书：</th>
					<td>
						<c:if test="${userResumeExt.isCollegeHaveDiploma eq GlobalConstant.FLAG_Y }">是</c:if>
						<c:if test="${userResumeExt.isCollegeHaveDiploma eq GlobalConstant.FLAG_N }">否</c:if>
					</td>
					<th>是否获得学位证书：</th>
					<td>
						<c:if test="${userResumeExt.isCollegeDegreeCertificate eq GlobalConstant.FLAG_Y }">是</c:if>
						<c:if test="${userResumeExt.isCollegeDegreeCertificate eq GlobalConstant.FLAG_N }">否</c:if>
					</td>
				</tr>

                <tr class="graduateTh1" style="display: none">
                    <th>学历证书照片：</th>
                    <td>
                        <c:if test="${!empty userResumeExt.undergraduateUrl}">
                            [<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.undergraduateUrl}" target="_blank">查看学历证书</a>]&nbsp;
                        </c:if>
                    </td>
                </tr>
				<tr  class="graduateTh1" style="display: none">
					<th>学历证书编号：</th>
					<td>
						${userResumeExt.collegeDiplomaNo}
					</td>
					<th>学历证书取得时间：</th>
					<td>
						${userResumeExt.collegeDiplomaTime}
					</td>
				</tr>
                <tr  class="graduateTh2" style="display: none">
                    <th>学位证书照片：</th>
                    <td>
                        <c:if test="${!empty userResumeExt.undergraduateDegreeUrl}">
                            [<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.undergraduateDegreeUrl}" target="_blank">查看学位证书</a>]&nbsp;
                        </c:if>
                    </td>
                </tr>
				<tr  class="graduateTh2" style="display: none">
					<th>学位证书编号：</th>
					<td>
						${userResumeExt.collegeDegreeNo}
					</td>
					<th>学位证书取得时间：</th>
					<td>
						${userResumeExt.collegeDegreeTime}
					</td>
				</tr>
				</c:if>
				<tr class="graduateTh">
			        <th>最高学历：</th>
			        <td colspan="3">${user.educationName}</td>
			    </tr>
				<c:if test="${user.trainingTypeId eq 'DoctorTrainingSpe'}">
			    <tr>
			    	<th>是否为硕士：</th>
			    	<td id="master" <c:if test="${userResumeExt.isMaster eq GlobalConstant.FLAG_N}">colspan="3"</c:if>>
			    		<c:if test="${userResumeExt.isMaster eq GlobalConstant.FLAG_Y}">是</c:if>
			    		<c:if test="${userResumeExt.isMaster eq GlobalConstant.FLAG_N}">否</c:if>
			    	</td>
			    	<th class="masterTh" style="display: none;">硕士学位类型：</th>
			    	<td class="masterTh" colspan="2">
							<c:if test="${userResumeExt.masterDegreeTypeId eq '1' }">专业型</c:if>
							<c:if test="${userResumeExt.masterDegreeTypeId eq '2' }">学术型</c:if>
					</td>
			    </tr>
		  	    <tr class="masterTh">
			      <th>硕士毕业院校：</th>
			       <td>${userResumeExt.masterGraSchoolName }</td>
			       <th>硕士毕业时间：</th>
			       <td>${userResumeExt.masterGraTime }</td>
		    	</tr>
		  	    <tr class="masterTh">
			      <th>硕士毕业专业：</th>
			       <td>${userResumeExt.masterMajor }</td>
			      <th>学位：</th>
			       <td>${userResumeExt.masterDegreeName}</td>
		    	</tr>
				<tr class="masterTh">
					<th>是否获得毕业证书：</th>
					<td>
						<c:if test="${userResumeExt.isMasterHaveDiploma eq GlobalConstant.FLAG_Y }">是</c:if>
						<c:if test="${userResumeExt.isMasterHaveDiploma eq GlobalConstant.FLAG_N }">否</c:if>
					</td>
					<th>是否获得学位证书：</th>
					<td>
						<c:if test="${userResumeExt.isMasterDegreeCertificate eq GlobalConstant.FLAG_Y }">是</c:if>
						<c:if test="${userResumeExt.isMasterDegreeCertificate eq GlobalConstant.FLAG_N }">否</c:if>
					</td>
				</tr>

				<tr class="masterTh1" style="display: none">
                    <th>学历证书照片：</th>
                    <td colspan="4">
                        <c:if test="${!empty userResumeExt.masterEducationUrl}">
                            [<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.masterEducationUrl}" target="_blank">查看学历证书</a>]&nbsp;
                        </c:if>
                    </td>
				</tr>
                <tr class="masterTh1" style="display: none">
					<th>学历证书编号：</th>
					<td>
						${userResumeExt.masterDiplomaNo}
					</td>
					<th>学历证书取得时间：</th>
					<td>
						${userResumeExt.masterDiplomaTime}
					</td>
				</tr>
                <tr class="masterTh2" style="display: none">
                    <th>学位证书照片：</th>
                    <td>
                        <c:if test="${!empty userResumeExt.masterCertificateUrl}">
                            [<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.masterCertificateUrl}" target="_blank">查看学位证书</a>]&nbsp;
                        </c:if>
                    </td>
                </tr>
				<tr class="" id="masterTh2" style="display: none">
					<th>学位证书编号：</th>
					<td>
						${userResumeExt.masterDegreeNo}
					</td>
					<th>学位证书取得时间：</th>
					<td>
						${userResumeExt.masterDegreeTime}
					</td>
				</tr>
				<%-- 本科 end--%>

			    <tr>
			    	<th>是否为博士：</th>
			    	<td id="doctor">
			    		<c:if test="${userResumeExt.isDoctor eq GlobalConstant.FLAG_Y}">是</c:if>
			    		<c:if test="${userResumeExt.isDoctor eq GlobalConstant.FLAG_N}">否</c:if>
			    	</td>
			    	<th class="doctorTh" style="display: none;">博士学位类型：</th>
					<td class="doctorTh" colspan="2">
						<c:if test="${userResumeExt.doctorDegreeTypeId eq '1' }">专业型</c:if>
						<c:if test="${userResumeExt.doctorDegreeTypeId eq '2' }">学术型</c:if>
					</td>
			    </tr>
		  	    <tr class="doctorTh">
			      <th>博士毕业院校：</th>
			       <td>${userResumeExt.doctorGraSchoolName }</td>
			       <th>博士毕业时间：</th>
			       <td>${userResumeExt.doctorGraTime }</td>
		    	</tr>
		  	    <tr class=doctorTh>
			      <th>博士毕业专业：</th>
			       <td>${userResumeExt.doctorMajor }</td>
			      <th>学位：</th>
			       <td>${userResumeExt.doctorDegreeName}</td>
		    	</tr>

				<tr class="doctorTh">
					<th>是否获得毕业证书：</th>
					<td>
						<c:if test="${userResumeExt.isDoctorHaveDiploma eq 	GlobalConstant.FLAG_Y }">是</c:if>
						<c:if test="${userResumeExt.isDoctorHaveDiploma eq 	GlobalConstant.FLAG_N }">否</c:if>
					</td>
					<th>是否获得学位证书：</th>
					<td>
						<c:if test="${userResumeExt.isDoctorDegreeCertificate eq 	GlobalConstant.FLAG_Y }">是</c:if>
						<c:if test="${userResumeExt.isDoctorDegreeCertificate eq 	GlobalConstant.FLAG_N }">否</c:if>
					</td>
				</tr>


				<tr class="doctorTh1" style="display: none;">
                    <th>学历证书照片：</th>
                    <td>
                        <c:if test="${!empty userResumeExt.doctorEducationUrl}">
                            [<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.doctorEducationUrl}" target="_blank">查看学历证书</a>]&nbsp;
                        </c:if>
                    </td>
				</tr>
                <tr class="doctorTh1" style="display: none;">
					<th>学历证书编号：</th>
					<td>
						${userResumeExt.doctorDiplomaNo}
					</td>
					<th>学历证书取得时间：</th>
					<td>
						${userResumeExt.doctorDiplomaTime}
					</td>
				</tr>
                <tr class="doctorTh2" style="display: none;">
                    <th>学位证书照片：</th>
                    <td>
                        <c:if test="${!empty userResumeExt.doctorCertificateUrl}">
                            [<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.doctorCertificateUrl}" target="_blank">查看学位证书</a>]&nbsp;
                        </c:if>
                    </td>
                </tr>
				<tr class="doctorTh2" style="display: none;">
					<th>学位证书编号：</th>
					<td>
						${userResumeExt.doctorDegreeNo}
					</td>
					<th>学位证书取得时间：</th>
					<td>
						${userResumeExt.doctorDegreeTime}
					</td>
				</tr>
				</c:if>
			    <tr>
			        <th>最高毕业证书编号：</th>
			        <td>${doctor.certificateNo}</td>
			        <th>最高毕业证书上传：</th>
			        <td colspan="2">
			        	<span id="certificateUriSpan" style="display:${!empty userResumeExt.certificateUri?'':'none'} ">
							[<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.certificateUri}" target="_blank">查看毕业证书</a>]
						</span>
					</td>			       
			    </tr>
		        <tr>
			        <th>最高学位证书编号：</th>
			        <td>${doctor.degreeNo}</td>
			        <th>最高学位证书上传：</th>
			        <td colspan="2">
			        	<a style="color:#459ae9;float:right;margin-right:30px;"></a>
			        	<span id="degreeUriSpan" style="display:${!empty userResumeExt.degreeUri?'':'none'} ">
							[<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.degreeUri}" target="_blank">查看学位证书</a>]
						</span>
			        </td>	      
			    </tr>
			 </tbody>
		</table></div>
		<div class="div_table" style="display: none;">
		<h4>现有资格信息</h4>
		<table border="0" align="center" cellpadding="0" cellspacing="0" class="base_info">
		<colgroup>
				<col width="15%"></col>
				<col width="30%"></col>
				<col width="15%"></col>
				<col width="40%"></col>
			</colgroup>
			<tbody>
			    <tr>
			    	<th>专业技术资格：</th>
			        <td>${userResumeExt.technologyQualificationName}</td>
			        <th>取得日期：</th>
			        <td colspan="2">${userResumeExt.getTechnologyQualificationDate}</td>
			    </tr>
			     <tr>
			    	<th>执业资格材料：</th>
			        <td>${userResumeExt.qualificationMaterialName}</td>
			        <th>资格材料编码：</th>
			        <td colspan="2">${userResumeExt.qualificationMaterialCode}</td>
			    </tr>
			     <tr>
			    	<th>执业类别：</th>
			        <td>${userResumeExt.practicingCategoryName}</td>
			        <th>执业范围：</th>
		         	<td colspan="2">${userResumeExt.practicingScopeName}</td>
			    </tr>
			    <tr>
			    	<th>资格证书上传：</th>
			    	<td colspan="4">
			    		<a style="color:#459ae9;float:right;margin-right:30px;"></a>
			        	<span id="qualificationMaterialUriSpan" style="display:${!empty userResumeExt.qualificationMaterialUri?'':'none'} ">
							[<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.qualificationMaterialUri}" target="_blank">查看资格证书</a>]
						</span>
			    	</td>
			    </tr>
			  </tbody>
			</table>
		</div>

		<div class="div_table">
			<h4>医师资格信息</h4>
			<table border="0" align="center" cellpadding="0" cellspacing="0" class="base_info">
				<colgroup>
					<col width="20%"/>
					<col width="30%"/>
					<col width="20%"/>
					<col width="30%"/>
				</colgroup>
				<tbody>
				<tr>
					<th>是否通过医师资格考试：</th>
					<td>
						<c:if test="${userResumeExt.isPassQualifyingExamination eq 	GlobalConstant.FLAG_Y }">是</c:if>
						<c:if test="${userResumeExt.isPassQualifyingExamination eq 	GlobalConstant.FLAG_N }">否</c:if>
					</td>

					<th  style="display: none" class="examinationTime">通过医师资格考试时间：</th>
					<td  style="display: none" class="examinationTime">
						${userResumeExt.passQualifyingExaminationTime}
					</td>
				</tr>

				<tr>
					<th>是否获得医师资格证书：</th>
					<td>
						<c:if test="${userResumeExt.isHaveQualificationCertificate eq 	GlobalConstant.FLAG_Y }">是</c:if>
						<c:if test="${userResumeExt.isHaveQualificationCertificate eq 	GlobalConstant.FLAG_N }">否</c:if>
					</td>

					<th style="display: none" class="examTime">取得医师资格证书时间：</th>
					<td style="display: none" class="examTime">
						${userResumeExt.haveQualificationCertificateTime}
					</td>
				</tr>
				<tr style="display: none" class="examTime">

					<th>医师资格级别：</th>
					<td>
						${userResumeExt.physicianQualificationLevel eq 'zyys'?'执业医师':''}
						${userResumeExt.physicianQualificationLevel eq 'zyzlys'?'执业助理医师':''}
					</td>
					<th>医师资格类别：</th>
					<td>
						${userResumeExt.physicianQualificationClass eq 'lc'?'临床':''}
						${userResumeExt.physicianQualificationClass eq 'kq'?'口腔':''}
						${userResumeExt.physicianQualificationClass eq 'ggws'?'公共卫生':''}
						${userResumeExt.physicianQualificationClass eq 'zy'?'中医':''}

					</td>
				</tr>
				<tr style="display: none" class="examTime">
					<th>医师资格证书编码：</th>
					<td >
						${userResumeExt.doctorQualificationCertificateCode }
					</td>

					<th>上传证书材料：</th>
					<td>
						<span id="doctorQualificationCertificateUrlSpan" style="display:${!empty userResumeExt.doctorQualificationCertificateUrl?'':'none'} ">
							[<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.doctorQualificationCertificateUrl}" target="_blank">查看证书材料</a>]&nbsp;
						</span>
					</td>
				</tr>
				<tr class="scoreType" style="display: none;">
					<th>成绩单类型：</th>
					<td>
						${userResumeExt.qualificationMaterialName2}
					</td>
					<th>成绩单附件：</th>
					<td>
						<input type="hidden" id="qualificationMaterialId2UrlValue" name="userResumeExt.qualificationMaterialId2Url" value="${userResumeExt.qualificationMaterialId2Url}"/>
						<span id="qualificationMaterialId2UrlSpan" style="display:${!empty userResumeExt.qualificationMaterialId2Url?'':'none'} ">
							[<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.qualificationMaterialId2Url}" target="_blank">查看</a>]&nbsp;
						</span>
					</td>
				</tr>
				<tr>
					<th>是否获得医师执业证书：</th>
					<td>
						<c:if test="${userResumeExt.isHavePracticingCategory eq 	GlobalConstant.FLAG_Y }">是</c:if>
						<c:if test="${userResumeExt.isHavePracticingCategory eq 	GlobalConstant.FLAG_N }">否</c:if>
					</td>

					<th class="showTime2" style="display: none;">取得医师执业证书时间：</th>
					<td class="showTime2" style="display: none;">
						${userResumeExt.havePracticingCategoryTime}
					</td>

				</tr>

				<tr  class="showTime2" style="display: none;">
					<th>执业类型：</th>
					<td>
							<c:forEach items="${dictTypeEnumPracticeTypeList}" var="dictTypeEnumPracticeType">
								<c:if test='${userResumeExt.practicingCategoryLevelId==dictTypeEnumPracticeType.dictId}'>
								${dictTypeEnumPracticeType.dictName}
								</c:if>

							</c:forEach>
					</td>
					<th>执业范围：</th>
					<td>
						<c:forEach items="${dictTypeEnumPracticeTypeList}" var="dict">
							<c:set var="dictKey" value="dictTypeEnumPracticeType.${dict.dictId}List"/>
							<c:forEach items="${applicationScope[dictKey]}" var="scope">
								<c:if test='${userResumeExt.practicingCategoryScopeId==scope.dictId and dict.dictId == userResumeExt.practicingCategoryLevelId}'>
									${scope.dictName}
								</c:if>
							</c:forEach>
						</c:forEach>
					</td>
					<%--<script>--%>
						<%--function changePracticingCategoryId(dictId){--%>
							<%--if(dictId=="") {--%>
								<%--$('#practicingCategoryScopeId').val("");--%>
							<%--}--%>
							<%--$('#practicingCategoryScopeId option').hide();--%>
							<%--$('#practicingCategoryScopeId option[value=""]').show();--%>
							<%--//$('#practicingScopeId').val("${userResumeExt.practicingScopeId}");--%>
							<%--$('#practicingCategoryScopeId'+' option.${dictTypeEnumPracticeType.id}\\.'+dictId).show();--%>
						<%--}--%>
						<%--$(function(){--%>
							<%--changePracticingCategoryId('${userResumeExt.practicingCategoryLevelId}');--%>
						<%--});--%>
					<%--</script>--%>
				</tr>

				<tr  class="showTime2" style="display: none;">

					<th>医师执业证书编码：</th>
					<td >
						${userResumeExt.doctorPracticingCategoryCode }
					</td>

					<th>上传证书材料：</th>
					<td>
						<input type="hidden" id="doctorPracticingCategoryUrlValue" name="userResumeExt.doctorPracticingCategoryUrl" value="${userResumeExt.doctorPracticingCategoryUrl}"/>
						<span id="doctorPracticingCategoryUrlSpan" style="display:${!empty userResumeExt.doctorPracticingCategoryUrl?'':'none'} ">
							[<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.doctorPracticingCategoryUrl}" target="_blank">查看证书材料</a>]&nbsp;
						</span>
					</td>
				</tr>
				<tr>
					<th>特殊岗位证明材料：</th>
					<td colspan="3">
						<input type="hidden" id="specialCertificationUriValue" name="userResumeExt.specialCertificationUri" value="${userResumeExt.specialCertificationUri}"/>
						<span id="specialCertificationUriSpan" style="display:${!empty userResumeExt.specialCertificationUri?'':'none'} ">
							[<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.specialCertificationUri}" target="_blank">查看证明材料</a>]&nbsp;
						</span>
					</td>
				</tr>
				</tbody>
			</table>
		</div>
		<div class="div_table">
			<h4>西部支援情况</h4>
			<table border="0" align="center" cellpadding="0" cellspacing="0" class="base_info">
				<colgroup>
					<col width="50%"/>
					<col width="50%"/>
				</colgroup>
				<tbody>
				<tr>
					<th>是否为西部支援行动住院医师：</th>
					<td >
						<c:if test="${userResumeExt.westernSupportResidents eq 	GlobalConstant.FLAG_Y }">是</c:if>
						<c:if test="${userResumeExt.westernSupportResidents eq 	GlobalConstant.FLAG_N }">否</c:if>
					</td>
				</tr>

				<tr style="display: none" class="western">
					<th>西部支援行动住院医师送出省份：</th>
					<td >
						${userResumeExt.westernSupportResidentsSendProvince}
					</td>
				</tr>
				<tr style="display: none" class="western">
					<th>西部支援行动住院医师送出单位统一社会信用代码：</th>
					<td >
						${userResumeExt.westernSupportResidentsSendWorkUnitCode}
					</td>
				</tr>
				<tr style="display: none" class="western">
					<th>西部支援行动住院医师送出单位：</th>
					<td >
						${userResumeExt.westernSupportResidentsSendWorkUnit}
					</td>
				</tr>
				<tr style="display: none" class="western">
					<th>西部支援行动住院医师接收省份：</th>
					<td >
						${userResumeExt.westernSupportResidentsReceiveProvince}
					</td>
				</tr>
				<tr style="display: none" class="western">
					<th>西部支援行动住院医师接收省份培训基地(医院)统一社会信用代码：</th>
					<td >
						${userResumeExt.westernSupportResidentsReceiveHospitalCode}
					</td>
				</tr>
				<tr style="display: none" class="western">
					<th>西部支援行动住院医师接收省份培训基地(医院)：</th>
					<td >
						${userResumeExt.westernSupportResidentsReceiveHospital}
					</td>
				</tr>

				</tbody>
			</table>
		</div>
		<c:if test="${empty canSave}">
			<div class="btn_info">
			<c:if test="${empty param.hideApprove}">
				<input type="button" style="width:100px;" class="btn_blue" onclick="" value="通过"></input>
				<input type="button" style="width:100px;" class="btn_red" onclick="" value="不通过"></input>
			</c:if>
				<c:if test="${'open' eq param.openType}">
					<input type="button" style="width:100px;" class="btn_green" onclick="jboxClose();" value="关闭"></input>
				</c:if>
			</div>
		</c:if>
	</div>
</div>