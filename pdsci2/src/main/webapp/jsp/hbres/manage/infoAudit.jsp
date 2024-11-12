<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>湖北省住院医师规范化培训招录系统</title>
<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">
	function audit(statusId,title){
		if("${regStatusEnumUnPassed.id}"==statusId){
			if($.trim($("#auditAgree").val())==""){
				jboxTip("审核意见不能为空!");
				return;
			}
		}
		jboxConfirm("确认"+title+"?",function(){
			var data = {
					doctorFlow:"${doctor.doctorFlow}",
					disactiveReason:$("#auditAgree").val(),
					<c:if test='${sysCfgMap["res_rereg"] eq "Y"}'>
					reeditFlag:$("#reeditFlag")[0].checked?"${GlobalConstant.FLAG_Y}":"${GlobalConstant.FLAG_N}",
					</c:if>
					userFlow:"${user.userFlow}",
					doctorStatusId:statusId
			};
			jboxPost("<s:url value='/hbres/singup/manage/userAudit'/>",data,
				function(resp){
					if(resp == "${GlobalConstant.OPRE_SUCCESSED}"){
						window.parent.$(".btn_green1").click();
						jboxClose();
					}
				}
			,null,true);
		});
	}
	function auditRegist(statusId,title){
		if("${regBaseStatusEnumNotPassed.id}"==statusId){
			if($.trim($("#auditInfo").val())==""){
				jboxTip("审核意见不能为空!");
				return;
			}
		}
		jboxConfirm("确认"+title+"?",function(){
			var data = {
				'doctorFlow':"${doctor.doctorFlow}",
				'recordFlow':"${param.recordFlow}",
				'auditInfo':$("#auditInfo").val(),
				'auditStatusId':statusId
			};
			jboxPost("<s:url value='/hbres/singup/hospital/auditRegist'/>",data,
					function(resp){
						if(resp == 1){
							window.parent.$(".btn_green1").click();
							jboxClose();
						}
					}
					,null,false);
		});
	}
</script>
</head>
<body style="width:100%; min-width: inherit; height:100%;background-image:none; background:#fff;">
	<div class="infoAudit" >
		<table border="0" width="945" cellspacing="0" cellpadding="0">
			<caption style="color:#459ae9;">个人信息类型</caption>
	     	 <tr>
				<th width="15%">医师类型：</th>
				<td width="25%">${doctor.doctorTypeName}</td>
				<th width="15%"></th>
				<td width="25%"></td>
				<td width="20%" colspan="4"></td>
			</tr>
			<c:if test="${doctor.doctorTypeId eq hBRecDocTypeEnumCompany.id || doctor.doctorTypeId eq hBRecDocTypeEnumCompanyEntrust.id}">
				<tr>
					<th>工作单位名称：</th>
					<td>${doctor.workOrgName}</td>
					<th>委培单位同意证明：</th>
					<td>
						<c:if test="${!empty doctor.dispatchFile}">
							[<a href="${sysCfgMap['upload_base_url']}/${doctor.dispatchFile}" target="_blank">点击查看</a>]
						</c:if>
					</td>
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
						<th>医疗卫生机构：</th>
						<td>${extInfo.medicalHeaithOrgName}</td>
						<th>医院属性：</th>
						<td>${extInfo.hospitalAttrName}</td>
					</tr>
					<tr>
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
					<td colspan="3">${doctor.workOrgName}</td>
				</tr>
			</c:if>
		</table>
		
		<table border="0" width="945" cellspacing="0" cellpadding="0">
			<caption style="color:#459ae9;">个人信息</caption>
			<colgroup>
				<col width="25%"/>
				<col width="25%"/>
				<col width="25%"/>
				<col width="25%"/>
			</colgroup>
	     	 <tr>
				<th width="15%">姓名：</th>
				<td width="25%">${user.userName}</td>
				<th width="15%">性别：</th>
				<td width="25%">${user.sexName}</td>
				<td width="20%" rowspan="6">
					<img src="${sysCfgMap['upload_base_url']}/${doctor.doctorHeadImg}"  width="140px" height="200px"/>
				</td>
			</tr>
			<tr>
				<th>出生日期：</th>
				<td>${user.userBirthday}</td>
				<th>年龄：</th>
				<td>${extInfo.age}</td>
			</tr>
			<tr>
				<th>证件类型：</th>
				<td>${user.cretTypeName}</td>
				<th>证件号码：</th>
				<td>${user.idNo} </td>
			</tr>
			<tr>
				<th>民族：</th>
				<td>${user.nationName}</td>
				<th>婚姻状况：</th>
				<td>${extInfo.maritalStatusName}</td>
			</tr>
			<tr>
				<th>手机：</th>
				<td>${user.userPhone} </td>
				<th>QQ：</th>
				<td>${user.userQq} </td>
			</tr>
			<tr>
				<th>邮箱：</th>
				<td>${user.userEmail} </td>
				<th>计算机能力：</th>
				<td>${doctor.computerSkills} </td>
			</tr>
			<tr>
				<th>外语能力：</th>
				<td>${doctor.foreignSkills} </td>
				<th>通讯地址：</th>
				<td>${user.userAddress}</td>
			</tr>
			<tr>
				<th>人员属性：</th>
				<td>${extInfo.personnelAttributeName} </td>
				<th>紧急联系人：</th>
				<td>${doctor.emergencyName}</td>
			</tr>
			<tr>
				<th>紧急联系方式：</th>
				<td>${doctor.emergencyPhone} </td>
				<th>与本人关系：</th>
				<td>${doctor.emergencyRelation}</td>
			</tr>
			<tr>
				<th>户口所在地省行政区划：</th>
				<td>${extInfo.locationOfProvinceName}</td>
                <th>是否军队人员：</th>
                <td>
                    <c:if test="${not empty userResumeExt.armyType}">
                        ${pdfn:getArmyTypeEnumName(userResumeExt.armyType)}
                    </c:if>
                </td>
			</tr>
		</table>

		<table border="0" width="945" cellspacing="0" cellpadding="0" >
			<caption style="color:#459ae9;">教育情况</caption>
			<colgroup>
				<col width="25%"/>
				<col width="25%"/>
				<col width="25%"/>
				<col width="25%"/>
			</colgroup>
			<tr>
				<th>是否为应届生：</th>
				<td><c:if test="${doctor.isYearGraduate eq 'Y'}">是</c:if><c:if test="${doctor.isYearGraduate eq 'N'}">否</c:if></td>
				<th>是否全科订单走向学员：</th>
				<td><c:if test="${extInfo.isGeneralOrderOrientationTrainee eq 'Y'}">是</c:if><c:if test="${extInfo.isGeneralOrderOrientationTrainee eq 'N'}">否</c:if></td>
			</tr>
			<c:if test="${extInfo.isCollegeDegree eq 'Y'}">
				<tr>
					<th>是否为专科：</th>
					<td>是</td>
					<th>专科毕业院校：</th>
					<td>${extInfo.collegeName}</td>
				</tr>
				<tr>
					<th>专科毕业时间：</th>
					<td>${extInfo.collegeGraduationTime}</td>
					<th>专科毕业专业：</th>
					<td>${extInfo.collegeGraduationSpe}</td>
				</tr>
				<tr>
					<th>专科学位：</th>
					<td>${extInfo.collegeDegree}</td>
				</tr>
			</c:if>
			<c:if test="${extInfo.isCollegeDegree eq 'N'}">
				<tr>
					<th>是否为专科：</th>
					<td>否</td>
				</tr>
			</c:if>
			<c:if test="${extInfo.isUndergraduateDegree eq 'Y'}">
				<tr>
					<th>是否为本科：</th>
					<td>是</td>
					<th>本科毕业院校：</th>
					<td>${extInfo.graduatedName}</td>
				</tr>
				<tr>
					<th>本科毕业时间：</th>
					<td>${extInfo.graduationTime}</td>
					<th>本科毕业专业：</th>
					<td>${extInfo.zbkbySpe}</td>
				</tr>
				<tr>
					<th>本科学位：</th>
					<td>${extInfo.undergraduateDegreeName}</td>
				</tr>
			</c:if>
			<c:if test="${extInfo.isUndergraduateDegree eq 'N'}">
				<tr>
					<th>是否为本科：</th>
					<td>否</td>
				</tr>
			</c:if>
			<c:if test="${extInfo.isMaster eq 'Y'}">
				<tr>
					<th>是否为硕士：</th>
					<td>是</td>
					<th>硕士毕业院校：</th>
					<td>${extInfo.masterGraSchoolName}</td>
				</tr>
				<tr>
					<th>硕士毕业时间：</th>
					<td>${extInfo.masterGraTime}</td>
					<th>硕士毕业专业：</th>
					<td>${extInfo.masterMajor}</td>
				</tr>
				<tr>
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
				</tr>
			</c:if>
			<c:if test="${extInfo.isDoctor eq 'Y'}">
				<tr>
					<th>是否为博士：</th>
					<td>是</td>
					<th>博士毕业院校：</th>
					<td>${extInfo.doctorGraSchoolName}</td>
				</tr>
				<tr>
					<th>博士毕业时间：</th>
					<td>${extInfo.doctorGraTime}</td>
					<th>博士毕业专业：</th>
					<td>${extInfo.doctorMajor}</td>
				</tr>
				<tr>
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
				</tr>
			</c:if>
			<c:if test="${extInfo.hasTakenMasterExam eq 'Y'}">
				<tr>
					<th>是否参加研究生考试：</th>
					<td>是</td>
					<th>研究生考试成绩：</th>
					<td>${extInfo.masterExamResult}</td>
				</tr>
				<tr>
					<th>考试成绩证明材料：</th>
					<td>
						<c:if test="${!empty extInfo.examCertificateUri}">
							[<a href="${sysCfgMap['upload_base_url']}/${extInfo.examCertificateUri}" target="_blank">点击查看</a>]
						</c:if>
					</td>
					<th>是否愿意参加全省住培招录笔试：</th>
					<td>
						${extInfo.isJoinTest eq 'Y'?'是':'否'}
					</td>
				</tr>
			</c:if>
			<tr>
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
				<th>最高学历：</th>
				<td>
					<c:forEach items="${dictTypeEnumUserEducationList}" var="dict">
						${user.educationId eq dict.dictId?dict.dictName:''}
					</c:forEach>
				</td>
				<th>最高毕业证书编号：</th>
				<td>
					${extInfo.certificateCode}
				</td>
			</tr>
			<tr>
				<th>最高学位证书：</th>
				<td>
					<c:if test="${!empty extInfo.degreeUri}">
						[<a href="${sysCfgMap['upload_base_url']}/${extInfo.degreeUri}" target="_blank">点击查看</a>]
					</c:if>
				</td>
				<th>最高学位证书编号：</th>
				<td>
					${extInfo.degreeCode}
				</td>
			</tr>
			<tr>
				<th>最高毕业证书：</th>
				<td>
					<c:if test="${!empty extInfo.certificateUri}">
						[<a href="${sysCfgMap['upload_base_url']}/${extInfo.certificateUri}" target="_blank">点击查看</a>]
					</c:if>
				</td>
			</tr>
		</table>

		<table border="0" width="945" cellspacing="0" cellpadding="0">
			<caption style="color:#459ae9;">医师资格信息</caption>
			<colgroup>
				<col width="25%"/>
				<col width="25%"/>
				<col width="25%"/>
				<col width="25%"/>
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
				</tr>
				<tr>
					<th>医师资格级别：</th>
					<td>${extInfo.physicianQualificationLevel eq 'zyys'?'执业医师':''}${extInfo.physicianQualificationLevel eq 'zyzlys'?'执业助理医师':''}</td>
					<th>医师资格类别：</th>
					<td>${extInfo.physicianQualificationClassName}</td>
				</tr>
				<tr>
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
				</tr>
				<tr>
					<th>执业类型：</th>
					<td>${extInfo.practicingCategoryName}</td>
					<th>执业范围：</th>
					<td>${extInfo.practicingScopeName}</td>
				</tr>
				<tr>
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

		<table border="0" width="945" cellspacing="0" cellpadding="0">
			<caption style="color:#459ae9;">西部支援情况</caption>
			<colgroup>
				<col width="25%"/>
				<col width="25%"/>
				<col width="25%"/>
				<col width="25%"/>
			</colgroup>
			<c:if test="${extInfo.isAssistance eq 'Y'}">
				<tr>
					<th>是否为西部支援行动住院医师：</th>
					<td>是</td>
					<th>西部支援行动住院医师送出省份：</th>
					<td>${extInfo.assistanceProvince}</td>
				</tr>
				<tr>
					<th>西部支援行动住院医师送出单位：</th>
					<td>${extInfo.westernSupportResidentsSendWorkUnit}</td>
					<th>西部支援行动住院医师送出单位是否军队医院：</th>
					<td>${extInfo.isMilitary eq 'Y'?'是':'否'}</td>
				</tr>
				<c:if test="${not empty extInfo.assistanceCode}">
				<tr>
					<th>西部支援行动住院医师送出单位统一社会信用代码：</th>
					<td>${extInfo.assistanceCode}</td>
				</tr>
				</c:if>
			</c:if>
			<c:if test="${extInfo.isAssistance ne 'Y'}">
				<tr>
					<th>是否为西部支援行动住院医师：</th>
					<td>否</td>
				</tr>
			</c:if>
		</table>

		<c:set var="isFree" value="${pdfn:isFreeGlobal()}"></c:set>
		<c:if test="${doctor.doctorStatusId eq regStatusEnumPassing.id && empty param.flag && !isFree}">
			<table border="0" width="945" cellspacing="0" cellpadding="0">
				<caption style="color:#459ae9;">审核意见</caption>
				<tr>
					<td colspan="4" style="padding-top:10px;padding-left:0;">
						<textarea id="auditAgree"></textarea>
					</td>
				</tr>
				<c:if test='${sysCfgMap["res_rereg"] eq "Y"}'>
				<tr>
					<td colspan="4" style="padding-top:10px;padding-left:0;">
						<label><input type="checkbox" id="reeditFlag" />&nbsp;如若审核不通过，允许修改资料后重新报名</label>
					</td>
				</tr>
				</c:if>
			</table>
		</c:if>

		<c:if test="${param.flag eq 'auditRegist'  && !isFree}">
			<table border="0" width="945" cellspacing="0" cellpadding="0">
				<caption style="color:#459ae9;">审核意见</caption>
				<tr>
					<td colspan="4" style="padding-top:10px;padding-left:0;">
						<textarea id="auditInfo"></textarea>
					</td>
				</tr>
			</table>
		</c:if>
		
		<div align="center" style="margin-top: 20px; margin-bottom:20px;">
			<c:if test="${!isFree}">
				<c:if test="${doctor.doctorStatusId eq regStatusEnumPassing.id && empty param.flag}">
					<input type="button" style="width:100px;" class="btn_blue" onclick="javascript:audit('${regStatusEnumPassed.id}','审核通过');" value="通&#12288;过"></input>
					<input type="button" style="width:100px;" class="btn_red" onclick="javascript:audit('${regStatusEnumUnPassed.id}','审核不通过');" value="不通过"></input>
				</c:if>
				<c:if test="${param.flag eq 'auditRegist'}">
					<input type="button" style="width:100px;" class="btn_blue" onclick="javascript:auditRegist('${resBaseStatusEnumPassed.id}','审核通过');" value="通&#12288;过"></input>
					<input type="button" style="width:100px;" class="btn_red" onclick="javascript:auditRegist('${resBaseStatusEnumNotPassed.id}','审核不通过');" value="不通过"></input>
				</c:if>
			</c:if>
			<input type="button" style="width:100px;" class="btn_grey" onclick="javascript:jboxClose();" value="关&#12288;闭"></input>
		</div>
	</div>
</body>
</html>