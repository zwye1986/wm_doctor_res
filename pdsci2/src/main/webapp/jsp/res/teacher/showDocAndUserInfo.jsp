<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	table{ margin:10px 0;border-collapse: collapse;}
	caption,th,td{height:40px;}
	caption{line-height:40px;text-align:left;font-weight:bold; padding-left:10px; border-bottom:1px solid #ddd;color:#2f8cef;margin-bottom: 10px;}
	th{text-align:right;font-weight:500;padding-right:5px; color:#333;}
	td{text-align:left; padding-left:5px;}
	[type='text']{width:150px;height: 22px;}
	select{width: 153px;height: 27px;}
</style>

<script type="text/javascript">
	var condition = "._${doctor.doctorCategoryId}._${doctor.trainingSpeId}";
	function saveDoc(){
		if($("#resDoctor").validationEngine("validate")){
			var url = "<s:url value='/res/doc/user/saveDoc'/>";
			var getdata = $('#resDoctor').serialize();
			jboxPost(url, getdata, function(data) {
				if('${GlobalConstant.SAVE_SUCCESSED}'==data){
					window.parent.frames['mainIframe'].window.searchUser(); 
					jboxClose();
				}
			},null,true);
		}
	}
	
	$(function(){
		$(".xltext,.xlselect").css("margin-right","0px");
		$("._rotation").hide();
		selRotation();
	});
	
	function cutBirthday(idNo){	
		$("[name='userBirthday']").val(idNo.substr(6,4)+"-"+idNo.substr(10,2)+"-"+idNo.substr(12,2));
	}
	
	function selRotation(){
		$("#rotationFlow").val("");
		$("._rotation").hide();
// 		var sessionNumber = $("#sessionNumber").val();
		var doctorCategoryId = $("#doctorCategoryId").val();
		var trainingSpeId = $("#trainingSpeId").val();
		if(doctorCategoryId && trainingSpeId){
			var selector = "._"+doctorCategoryId+"._"+trainingSpeId;
			$(selector).show();
			if(condition==selector){
				$(selector+"[value='${doctor.rotationFlow}']").attr("selected","selected");
			}else{
				$(selector+":first").attr("selected","selected");
			}
		}
	}
	
	function checkCondition(){
		if(!$("#doctorCategoryId").val()){
			jboxTip("请选择人员类型!");
			return;
		}
// 		if(!$("#sessionNumber").val()){
// 			jboxTip("请选择年级!");
// 			return;
// 		}
		if(!$("#trainingSpeId").val()){
			jboxTip("请选择专业!");
			return;
		}
	}
	
	function infoOper(id,a){
		$("#"+id).slideToggle();
		$(a).find("img").toggle();
	}
	
	function changeUserInfo(doctorCategoryId){
		$("#workInfo").hide();
		$("#work").hide();
		$(".userNameSpan").html("学员");
		$(".schoolNameSpan").html("在读");
		$(".graduateSpan").hide();
		$(".educationSpan").show();
		$(".degreeSpan").hide();
		$(".trainNameSpan").html("培训");
		$(".trainExtralSpan").html("培养");
		if (doctorCategoryId=="${recDocCategoryEnumDoctor.id}" || 
				doctorCategoryId=="${recDocCategoryEnumInDoctor.id}" ||
				doctorCategoryId=="${recDocCategoryEnumOutDoctor.id}" ||
				doctorCategoryId=="${recDocCategoryEnumFieldTrain.id}" ||
				doctorCategoryId=="${recDocCategoryEnumUnderGrad.id}" ||
				doctorCategoryId=="${recDocCategoryEnumSpecialist.id}" ||
				doctorCategoryId=="${recDocCategoryEnumGeneralDoctor.id}") 
		{//住院医师、本院规培、外院规培、外地委培、本科生、专科医师、普通医生
			$(".userNameSpan").html("医师");	
			$(".schoolNameSpan").html("毕业");
			$(".graduateSpan").show();	//增加毕业证号和毕业时间
			$(".degreeSpan").show();	//增加学位
			$("#workInfo").show();	//增加工作单位和职务
			$("#work").show();	//增加工作经历
		} else if (doctorCategoryId=="${recDocCategoryEnumScholar.id}") {//进修生
			$(".schoolNameSpan").html("毕业");
			$(".graduateSpan").show();	//增加毕业证号和毕业时间
			$(".degreeSpan").show();	//增加学位
			$(".trainNameSpan").html("进修");
			$("#work").show();	//增加工作经历
		} else if (doctorCategoryId=="${recDocCategoryEnumEightYear.id}") {//八年制
			$(".educationSpan").hide();	//隐藏学历
		} else if (doctorCategoryId=="${recDocCategoryEnumIntern.id}") {//实习生
			$(".trainNameSpan").html("实习");
		}
	}
	

	$(document).ready(function(){
		changeUserInfo("${doctor.doctorCategoryId}");
		changeTerminat("${doctor.doctorStatusId}");
		loadEdu();
	 	loadWork();
	});
	
	function loadEdu(){
		jboxLoad("edu","<s:url value='/pub/resume/loadView?type=education&userFlow=${user.userFlow}&editFlag=${GlobalConstant.FLAG_Y}&source=resDoc'/>");
	}

	function loadWork(){
		jboxLoad("work","<s:url value='/pub/resume/loadView?type=work&userFlow=${user.userFlow}&editFlag=${GlobalConstant.FLAG_Y}&source=resDoc'/>");
	}
	
	//根据医师状态决定展示终止还是结业内容
	function changeTerminat(doctorStatusId){
		if ("${resDoctorStatusEnumTerminat.id}"==doctorStatusId) {
			$(".${resDoctorStatusEnumTerminat.id}").show();
			$(".${resDoctorStatusEnumGraduation.id}").hide();
		} else if ("${resDoctorStatusEnumGraduation.id}"==doctorStatusId) {
			$(".${resDoctorStatusEnumGraduation.id}").show();
			$(".${resDoctorStatusEnumTerminat.id}").hide();
		} else {
			$(".${resDoctorStatusEnumTerminat.id}").hide();
			$(".${resDoctorStatusEnumGraduation.id}").hide();
		}
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
	<div class="title1 clearfix">
		<form id="resDoctor" style="position: relative;">
		<input type="hidden" name="doctorFlow" value="${doctor.doctorFlow}"/>
		<input type="hidden" name="userFlow" value="${user.userFlow}"/>
			<table style="width:100%;">
				<caption>人员类型信息</caption>
				<colgroup>
					<col width="12%"/>
					<col width="20%"/>
					<col width="12%"/>
					<col width="20%"/>
					<col width="16%"/>
					<col width="20%"/>
				</colgroup>
				<tr>
					<th>学员类型：</th>
					<td>${doctor.doctorTypeName}</td>
				</tr>
				<c:if test="${doctor.doctorTypeId eq hBRecDocTypeEnumCompany.id || doctor.doctorTypeId eq hBRecDocTypeEnumCompanyEntrust.id}">
					<tr>
						<th>工作单位：</th>
						<td>${doctor.workOrgName}</td>
						<th>委培单位同意证明：</th>
						<td>
							<c:if test="${!empty doctor.dispatchFile}">
								[<a href="${sysCfgMap['upload_base_url']}/${doctor.dispatchFile}" target="_blank">点击查看</a>]
							</c:if>
						</td>
						<th></th>
						<td></td>
					</tr>
					<c:if test="${extInfo.medicalHeaithOrgId eq '3'}"><!--基础医院-->
						<tr>
							<th>医疗卫生机构：</th>
							<td>${extInfo.medicalHeaithOrgName}</td>
							<th>基层医疗卫生机构类型：</th>
							<td>${extInfo.basicHealthOrgName}</td>
						</tr>
					</c:if>
					<c:if test="${extInfo.medicalHeaithOrgId eq '1'}">
						<tr>
							<th>医院属性：</th>
							<td>${extInfo.hospitalAttrName}</td>
							<th>医院类别：</th>
							<td>${extInfo.hospitalCategoryName}</td>
							<th>单位性质：</th>
							<td>${extInfo.baseAttributeName}</td>
						</tr>
					</c:if>
				</c:if>
				<c:if test="${doctor.doctorTypeId eq hBRecDocTypeEnumGraduate.id}">
					<tr>
						<th>在读院校名称：</th>
						<td>${doctor.workOrgName}</td>
					</tr>
				</c:if>
			</table>
			<table style="width:100%;">
				<caption>个人信息</caption>
				<colgroup>
					<col width="12%"/>
					<col width="20%"/>
					<col width="12%"/>
					<col width="20%"/>
					<col width="16%"/>
					<col width="20%"/>
				</colgroup>
				<tr>
					<th>培训类别：</th>
					<td>${doctor.doctorCategoryName}</td>
					<th>用户名：</th>
					<td>${user.userCode}</td>
					<th>真实姓名：</th>
					<td>${user.userName}</td>
				</tr>
				<tr>
					<th>性别：</th>
					<td>${user.sexName}	</td>
					<th>年龄：</th>
					<td>${extInfo.age}</td>
					<th>证件类型：</th>
					<td>${user.cretTypeName}</td>
				</tr>
				<tr>
					<th>证件号码：</th>
					<td>${user.idNo}</td>
					<th>民族：</th>
					<td>
						<c:forEach items="${userNationEnumList}" var="nation">
							<c:if test="${user.nationId eq nation.id}">${nation.name}</c:if>
						</c:forEach>
					</td>
					<th>人员属性：</th>
					<td>${extInfo.personnelAttributeName}</td>
				</tr>
				<tr>
					<th>手机：</th>
					<td>${user.userPhone}</td>
					<th >邮箱：</th>
					<td>${user.userEmail}</td>
					<th >出生日期：</th>
					<td>${user.userBirthday}</td>
				</tr>
				<tr>
					<th>婚姻状况：</th>
					<td>${extInfo.maritalStatusName}</td>
					<th>QQ号码：</th>
					<td>${user.userQq}</td>
					<th>计算机能力：</th>
					<td>${doctor.computerSkills}</td>
				</tr>
				<tr>
					<th>外语能力：</th>
					<td>${doctor.foreignSkills}</td>
					<th>通讯地址：</th>
					<td>${user.userAddress}</td>
					<th>户口所在地省行政区划：</th>
					<td>${extInfo.locationOfProvinceName}</td>
				</tr>
				<tr>
					<th>紧急联系人：</th>
					<td>${doctor.emergencyName}</td>
					<th>紧急联系方式：</th>
					<td>${doctor.emergencyPhone}</td>
					<th>与本人关系：</th>
					<td>${doctor.emergencyRelation}</td>
				</tr>
			</table>
			<table style="width:100%;">
				<caption>教育情况</caption>
				<colgroup>
					<col width="12%"/>
					<col width="20%"/>
					<col width="12%"/>
					<col width="20%"/>
					<col width="16%"/>
					<col width="20%"/>
				</colgroup>
				<tr>
					<th>是否为应届生：</th>
					<td><c:if test="${doctor.isYearGraduate eq 'Y'}">是</c:if><c:if test="${doctor.isYearGraduate eq 'N'}">否</c:if></td>
					<th>是否全科定向&#12288;<br>学员：</th>
					<td><c:if test="${extInfo.isGeneralOrderOrientationTrainee eq 'Y'}">是</c:if><c:if test="${extInfo.isGeneralOrderOrientationTrainee eq 'N'}">否</c:if></td>
					<th>证件照：</th>
					<td>
						[<a href="${sysCfgMap['upload_base_url']}/${doctor.doctorHeadImg}" target="_blank">查看图片</a>]
					</td>
				</tr>
				<c:if test="${extInfo.isCollegeDegree eq 'Y'}">
					<tr>
						<th>是否为专科：</th>
						<td>是</td>
						<th>专科毕业院校：</th>
						<td>${extInfo.collegeName}</td>
						<th>专科毕业时间：</th>
						<td>${extInfo.collegeGraduationTime}</td>
					</tr>
					<tr>
						<th>专科毕业专业：</th>
						<td>${extInfo.collegeGraduationSpe}</td>
						<th>专科学位：</th>
						<td>${extInfo.collegeDegree}</td>
					</tr>
				</c:if>
				<c:if test="${extInfo.isCollegeDegree eq 'N'}">
					<tr>
						<th>是否为专科：</th>
						<td>否</td>
						<th></th><td></td><th></th><td></td>
					</tr>
				</c:if>
				<c:if test="${extInfo.isUndergraduateDegree eq 'Y'}">
					<tr>
						<th>是否为本科：</th>
						<td>是</td>
						<th>本科毕业院校：</th>
						<td>${extInfo.graduatedName}</td>
						<th>本科毕业时间：</th>
						<td>${extInfo.graduationTime}</td>
					</tr>
					<tr>
						<th>本科毕业专业：</th>
						<td>${extInfo.zbkbySpe}</td>
						<th>本科学位：</th>
						<td>${extInfo.undergraduateDegreeName}</td>
					</tr>
				</c:if>
				<c:if test="${extInfo.isUndergraduateDegree eq 'N'}">
					<tr>
						<th>是否为本科：</th>
						<td>否</td>
						<th></th><td></td><th></th><td></td>
					</tr>
				</c:if>
				<c:if test="${extInfo.isMaster eq 'Y'}">
					<tr>
						<th>是否为硕士：</th>
						<td>是</td>
						<th>硕士毕业院校：</th>
						<td>${extInfo.masterGraSchoolName}</td>
						<th>硕士毕业时间：</th>
						<td>${extInfo.masterGraTime}</td>
					</tr>
					<tr>
						<th>硕士毕业专业：</th>
						<td>${extInfo.masterMajor}</td>
						<th>硕士学位：</th>
						<td>${extInfo.masterDegreeName}</td>
						<th>硕士学位类型：</th>
						<td>${extInfo.masterDegreeTypeName}</td>
					</tr>
				</c:if>
				<c:if test="${extInfo.isMaster eq 'N'}">
					<tr>
						<th>是否为硕士：</th>
						<td>否</td>
						<th></th><td></td><th></th><td></td>
					</tr>
				</c:if>
				<c:if test="${extInfo.isDoctor eq 'Y'}">
					<tr>
						<th>是否为博士：</th>
						<td>是</td>
						<th>博士毕业院校：</th>
						<td>${extInfo.doctorGraSchoolName}</td>
						<th>博士毕业时间：</th>
						<td>${extInfo.doctorGraTime}</td>
					</tr>
					<tr>
						<th>博士毕业专业：</th>
						<td>${extInfo.doctorMajor}</td>
						<th>博士学位：</th>
						<td>${extInfo.doctorDegreeName}</td>
						<th>博士学位类型：</th>
						<td>
							<c:if test="${extInfo.doctorDegreeType eq '1'}">专业型</c:if>
							<c:if test="${extInfo.doctorDegreeType eq '2'}">科学型</c:if>
						</td>
					</tr>
				</c:if>
				<c:if test="${extInfo.isDoctor eq 'N'}">
					<tr>
						<th>是否为博士：</th>
						<td>否</td>
						<th></th><td></td><th></th><td></td>
					</tr>
				</c:if>
				<tr>
					<th>最高学历：</th>
					<td>
						<c:forEach items="${dictTypeEnumUserEducationList}" var="dict">
							${user.educationId eq dict.dictId?dict.dictName:''}
						</c:forEach>
					</td>
					<th>最高学位：</th>
					<td>
						${user.degreeName}
					</td>
					<th>最高学历毕业专业：</th>
					<td>
						<c:forEach items="${dictTypeEnumGraduateMajorList}" var="dict">
							${doctor.specialized eq dict.dictId?dict.dictName:''}
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th>最高毕业证书：</th>
					<td>[<a href="${sysCfgMap['upload_base_url']}/${extInfo.certificateUri}" target="_blank">查看图片</a>]</td>
					<th>最高学位证书：</th>
					<td>[<a href="${sysCfgMap['upload_base_url']}/${extInfo.degreeUri}" target="_blank">查看图片</a>]</td>
					<th>最高学位证书&emsp;<br/>编号：</th>
					<td>${extInfo.degreeCode}</td>
				</tr>
			</table>
			<table style="width:100%;">
				<caption>医师资格信息</caption>
				<colgroup>
					<col width="12%"/>
					<col width="20%"/>
					<col width="12%"/>
					<col width="20%"/>
					<col width="16%"/>
					<col width="20%"/>
				</colgroup>
				<c:if test="${extInfo.isPassQualifyingExamination eq 'Y'}">
					<tr>
						<th>是否通过医师资格考试：</th>
						<td>是</td>
						<th>通过医师资格考试时间：</th>
						<td>${extInfo.passQualifyingExaminationTime}</td>
					</tr>
				</c:if>
				<c:if test="${extInfo.isPassQualifyingExamination eq 'N'}">
					<tr>
						<th>是否通过医师资格考试：</th>
						<td>否</td>
						<th></th><td></td>
					</tr>
				</c:if>
				<c:if test="${doctor.doctorLicenseFlag eq 'N'}">
					<tr>
						<th>是否取得执业医师资格证：</th>
						<td>否</td>
						<th></th>
						<td></td>
					</tr>
				</c:if>
				<c:if test="${doctor.doctorLicenseFlag eq 'Y'}">
					<tr>
						<th>是否取得执业医师资格证：</th>
						<td>是</td>
						<th>取得医师资格证书时间：</th>
						<td>${extInfo.haveQualificationCertificateTime}</td>
						<th>医师资格级别：</th>
						<td>${extInfo.physicianQualificationLevel eq 'zyys'?'执业医师':''}${extInfo.physicianQualificationLevel eq 'zyzlys'?'执业助理医师':''}</td>
					</tr>
					<tr>
						<th>医师资格类别：</th>
						<td>${extInfo.physicianQualificationClassName}</td>
						<th>医师资格证书编码：</th>
						<td>${doctor.doctorLicenseNo}</td>
						<th>医师资格证：</th>
						<td>
							<c:if test="${!empty doctor.qualifiedFile}">
								[<a href="${sysCfgMap['upload_base_url']}/${doctor.qualifiedFile}" target="_blank">点击查看</a>]
							</c:if>
						</td>
					</tr>
				</c:if>
				<c:if test="${doctor.practPhysicianFlag eq 'Y'}">
					<tr>
						<th>是否获得医师执业证书：</th>
						<td>是</td>
						<th>取得医师执业证书时间：</th>
						<td>${extInfo.havePracticingCategoryTime}</td>
						<th>执业类型：</th>
						<td>${extInfo.practicingCategoryName}</td>
					</tr>
					<tr>
						<th>执业范围：</th>
						<td>${extInfo.practicingScopeName}</td>
						<th>医师执业证书编码：</th>
						<td>${doctor.practPhysicianCertificateNo}</td>
						<th>医师执业证书：</th>
						<td>
							<c:if test="${!empty extInfo.doctorPracticingCategoryUrl}">
								[<a href="${sysCfgMap['upload_base_url']}/${extInfo.doctorPracticingCategoryUrl}" target="_blank">点击查看</a>]
							</c:if>
						</td>
					</tr>
				</c:if>
				<c:if test="${doctor.practPhysicianFlag ne 'Y'}">
					<tr>
						<th>是否获得医师执业证书：</th>
						<td>否</td>
						<th></th>
						<td></td>
					</tr>
				</c:if>
			</table>
			<table style="width:100%;">
				<caption>西部支援情况</caption>
				<colgroup>
					<col width="12%"/>
					<col width="20%"/>
					<col width="12%"/>
					<col width="20%"/>
					<col width="16%"/>
					<col width="20%"/>
				</colgroup>
				<c:if test="${extInfo.isAssistance eq 'Y'}">
					<tr>
						<th>是否为西部支援行动住院医师：</th>
						<td>是</td>
						<th>西部支援行动住院医师送出省份：</th>
						<td>${extInfo.assistanceProvince}</td>
						<th>西部支援行动住院医师送出单位：</th>
						<td>${extInfo.westernSupportResidentsSendWorkUnit}</td>
					</tr>
					<tr>
						<th>西部支援行动住院医师送出单位是否军队医院：</th>
						<td>${extInfo.isMilitary eq 'Y'?'是':'否'}</td>
						<c:if test="${not empty extInfo.assistanceCode}">
							<th>西部支援行动住院医师送出单位统一社会信用代码：</th>
							<td>${extInfo.assistanceCode}</td>
						</c:if>
					</tr>
				</c:if>
				<c:if test="${extInfo.isAssistance ne 'Y'}">
					<tr>
						<th>是否为西部支援行动住院医师：</th>
						<td>否</td>
						<th></th><td></td>
					</tr>
				</c:if>
			</table>
		<table style="width:100%;">
			<caption>培训信息</caption>
			<colgroup>
				<col width="12%"/>
				<col width="20%"/>
				<col width="12%"/>
				<col width="20%"/>
				<col width="16%"/>
				<col width="20%"/>
			</colgroup>
			<tr>
				<th>年&#12288;&#12288;级：</th>
				<td>${doctor.sessionNumber}
				</td>
				<th><span class="trainNameSpan trainExtralSpan">培养</span>年限：</th>
				<td colspan="3">${doctor.trainingYears }
				</td>
			</tr>
			<tr>
				<th><span class="trainNameSpan">培训</span>基地：</th>
				<td>${doctor.orgName}</td>
				<th><span class="trainNameSpan">培训</span>专业：</th>
				<td>${doctor.trainingSpeName}
				</td>
				<th>轮转方案：</th>
				<td colspan="3">${doctor.rotationName }
				</td>
			</tr>
			<tr>
				<th><span class="userNameSpan">医师</span>状态：</th>
				<td>${doctor.doctorStatusName }
				</td>
				<th class="${resDoctorStatusEnumTerminat.id}">终止原因：</th>
				<td class="${resDoctorStatusEnumTerminat.id}">
					${doctor.terminatReason}
				</td>
				<th class="${resDoctorStatusEnumTerminat.id}">终止时间：</th>
				<td class="${resDoctorStatusEnumTerminat.id}">
					${doctor.terminatDate}
				</td>
				<th class="${resDoctorStatusEnumGraduation.id}">结业时间：</th>
				<td class="${resDoctorStatusEnumGraduation.id}">${doctor.completeDate }
				</td>
				<th class="${resDoctorStatusEnumGraduation.id}">结业证书编号：</th>
				<td class="${resDoctorStatusEnumGraduation.id}">${doctor.completeNo}</td>
			</tr>
		</table>
		</form>
		<p style="text-align: center;">
       		<input type="button" onclick="jboxClose();" class="search" value="关&#12288;闭"/>
       </p>
		</div>
	</div>
</div>
</body>
</html>