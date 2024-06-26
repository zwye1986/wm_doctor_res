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
function editFormHidden(isHidden){
	if(isHidden){
		$("#editForm").hide();
		$("#down").show();
		$("#up").hide();
	}else{
		$("#editForm").show();
		$("#up").show();
		$("#down").hide();
	}
}
function pt(){
		var oldstr = document.body.innerHTML;
		$(".notPrint").hide();
		$(".infoAudit").css("height","100%");
		window.print();
		document.body.innerHTML = oldstr;
}
</script>
</head>
<body style="width:100%; min-width: inherit; height:100%;background-image:none; background:#fff;">
	<div class="infoAudit" >
		<table border="0" width=100% cellspacing="0" cellpadding="0">
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

		<table border="0" width=100% cellspacing="0" cellpadding="0">
			<caption style="color:#459ae9;">个人信息</caption>
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
				<th>手机：</th>
				<td>${user.userPhone} </td>
			</tr>
			<tr>
				<th>邮箱：</th>
				<td>${user.userEmail} </td>
				<th>通讯地址：</th>
				<td>${user.userAddress}</td>
			</tr>
			<tr>
				<th>人员属性：</th>
				<td>${extInfo.personnelAttributeName}</td>
				<th>是否取得&#12288;<br>执业医师资格证：</th>
				<td>${doctor.doctorLicenseFlag eq 'N'?'否':'是'}</td>
			</tr>
			<c:if test="${doctor.doctorLicenseFlag eq 'Y'}">
				<tr>
					<th>医师资格证号：</th>
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
					<th>是否取得执业证：</th>
					<td>是</td>
					<th>执业医师证号：</th>
					<td>${doctor.practPhysicianCertificateNo}</td>
				</tr>
			</c:if>
			<c:if test="${doctor.practPhysicianFlag ne 'Y'}">
				<tr>
					<th>是否取得执业证：</th>
					<td>否</td>
					<th></th>
					<td></td>
				</tr>
			</c:if>
			<tr>
				<th>紧急联系人：</th>
				<td>${doctor.emergencyName}</td>
				<th>紧急联系方式：</th>
				<td>${doctor.emergencyPhone}</td>
				<td></td>
			</tr>
			<tr>
				<th>与本人关系：</th>
				<td>${doctor.emergencyRelation}</td>
				<th>是否为援疆援藏&#12288;<br>住院医师：</th>
				<td>${extInfo.isAssistance eq 'Y'?'是':'否'}</td>
			</tr>
			<c:if test="${extInfo.isAssistance eq 'Y'}">
			<tr>
				<th>援疆援藏住院医师&#12288;<br>送出省份：</th>
				<td>${extInfo.assistanceProvince}</td>
				<th style="line-height: 25.5px;">援疆援藏住院医师送&#12288;<br>出单位统一社会信用&#12288;<br>代码/组织机构代码：</th>
				<td>${extInfo.assistanceCode}</td>
			</tr>
			</c:if>
			<c:if test="${not empty extInfo.isJoinTest}">
				<tr>
					<th>是否愿意参加全省住&#12288;<br>培招录笔试：</th>
					<td>${extInfo.isJoinTest eq 'Y'?'是':'否'}</td>
					<th></th>
					<td></td>
				</tr>
			</c:if>
		</table>

		<table border="0" width=100% cellspacing="0" cellpadding="0" >
			<caption style="color:#459ae9;">学历信息登记</caption>
			<tr>
				<th width="15%">是否为应届生：</th>
				<td width="25%"><c:if test="${doctor.isYearGraduate eq 'Y'}">是</c:if><c:if test="${doctor.isYearGraduate eq 'N'}">否</c:if></td>
				<th>是否全科订单走向学员：</th>
				<td><c:if test="${extInfo.isGeneralOrderOrientationTrainee eq 'Y'}">是</c:if><c:if test="${extInfo.isGeneralOrderOrientationTrainee eq 'N'}">否</c:if></td>
			</tr>
			<tr>
				<th width="15%">专/本科毕业院校：</th>
				<td>${extInfo.graduatedName}</td>
				<th>专/本科毕业时间：</th>
				<td>${extInfo.graduationTime}</td>
			</tr>
			<tr>
				<th>专/本科毕业专业：</th>
				<td>${extInfo.zbkbySpe}</td>
				<th>学位：</th>
				<td>${user.degreeName}</td>
			</tr>
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
					<tr>
						<th>最高学历毕业专业：</th>
						<td>
							<c:forEach items="${dictTypeEnumGraduateMajorList}" var="dict">
								${doctor.specialized eq dict.dictId?dict.dictName:''}
							</c:forEach>
						</td>
						<th>最高学历：</th>
						<td>
							<c:forEach items="${dictTypeEnumUserEducationList}" var="dict">
								${user.educationId eq dict.dictId?dict.dictName:''}
							</c:forEach>
						</td>
					</tr>
					<tr class="notPrint">
						<th>最高学历毕业证书：</th>
						<td>
							<c:if test="${!empty extInfo.certificateUri}">
								[<a href="${sysCfgMap['upload_base_url']}/${extInfo.certificateUri}" target="_blank">点击查看</a>]
							</c:if>
						</td>
						<th>最高学历学位证书：</th>
						<td>
							<c:if test="${!empty extInfo.degreeUri}">
								[<a href="${sysCfgMap['upload_base_url']}/${extInfo.degreeUri}" target="_blank">点击查看</a>]
							</c:if>
						</td>
					</tr>
				</c:if>
				<c:if test="${extInfo.isDoctor eq 'N'}">
					<tr>
						<th>是否为博士：</th>
						<td>否</td>
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
						<th class="notPrint">最高学历毕业证书：</th>
						<td>
							<c:if test="${!empty extInfo.certificateUri}">
								[<a href="${sysCfgMap['upload_base_url']}/${extInfo.certificateUri}" target="_blank">点击查看</a>]
							</c:if>
						</td>
					</tr>
					<tr>
						<th>最高学历学位证书：</th>
						<td>
							<c:if test="${!empty extInfo.degreeUri}">
								[<a href="${sysCfgMap['upload_base_url']}/${extInfo.degreeUri}" target="_blank">点击查看</a>]
							</c:if>
						</td>
					</tr>
				</c:if>
			</c:if>
			<c:if test="${extInfo.isMaster eq 'N'}">
				<c:if test="${extInfo.hasTakenMasterExam eq 'Y'}">
					<tr>
						<th>是否为硕士：</th>
						<td>否</td>
						<th>是否参加研究生考试：</th>
						<td>是</td>
					</tr>
					<tr>
						<th>研究生考试成绩：</th>
						<td>${extInfo.masterExamResult}</td>
						<th class="notPrint">考试成绩证明材料：</th>
						<td class="notPrint">
							<c:if test="${!empty extInfo.examCertificateUri}">
								[<a href="${sysCfgMap['upload_base_url']}/${extInfo.examCertificateUri}" target="_blank">点击查看</a>]
							</c:if>
						</td>
					</tr>
				</c:if>
				<c:if test="${extInfo.hasTakenMasterExam eq 'N'}">
					<tr>
						<th>是否为硕士：</th>
						<td>否</td>
						<th>是否参加研究生考试：</th>
						<td>否</td>
					</tr>
				</c:if>
				<tr>
					<th>最高学历毕业专业：</th>
					<td>
						<c:forEach items="${dictTypeEnumGraduateMajorList}" var="dict">
							${doctor.specialized eq dict.dictId?dict.dictName:''}
						</c:forEach>
					</td>
					<th>最高学历：</th>
					<td>
						<c:forEach items="${dictTypeEnumUserEducationList}" var="dict">
							${user.educationId eq dict.dictId?dict.dictName:''}
						</c:forEach>
					</td>
				</tr>
				<tr class="notPrint">
					<th>最高学历毕业证书：</th>
					<td>
						<c:if test="${!empty extInfo.certificateUri}">
							[<a href="${sysCfgMap['upload_base_url']}/${extInfo.certificateUri}" target="_blank">点击查看</a>]
						</c:if>
					</td>
					<th>最高学历学位证书：</th>
					<td>
						<c:if test="${!empty extInfo.degreeUri}">
							[<a href="${sysCfgMap['upload_base_url']}/${extInfo.degreeUri}" target="_blank">点击查看</a>]
						</c:if>
					</td>
				</tr>
			</c:if>
		</table>

		<table border="0" width=100% cellspacing="0" cellpadding="0" >
			<caption style="color:#459ae9;">报考信息</caption>
			
			<tr>
				<th width="15%">志愿院校：</th>
				<td width="25%">${recruit.orgName}</td>
				<th width="15%">志愿专业：</th>
				<td width="25%">${recruit.speName}</td>
			</tr>
			<tr>
				<th width="15%">是否服从专业调剂：</th>
				<td width="25%">${GlobalConstant.FLAG_Y eq recruit.swapFlag?'是':'否' }</td>

			</tr>
			<tr>
				<th>笔试成绩：</th>
				<td colspan="3">${recruit.examResult}</td>
			</tr>
		</table>
		 
		 <div>
		     <p class="caption"  style="color:#459ae9;">招录记录
		     <span class="fr notPrint">
		     <a title="收缩" id="up" onclick=""><img src="<s:url value='/css/skin/up.png'/>"/></a>
		     <a title="展开" id="down" onclick="" style="display: none"><img
					 src="<s:url value='/css/skin/down.png'/>"/></a>
             </span>
             </p>
		     <table id='history' class="grid">
		         <thead>
		             <tr>
		                 <th style="text-align:center;">志愿</th>
		                 <th style="text-align:center;">专业</th>
		                 <th style="text-align:center;">类型</th>
		                 <th style="text-align:center;">笔试成绩</th>
		                 <th style="text-align:center;">操作/面试成绩</th>
		                 <th style="text-align:center;">填报(或调剂)时间</th>
		                 <th style="text-align:center;">录取结果</th>
		             </tr>
		         </thead>
		         
		             <c:forEach items="${doctorRecruits}" var="historyRec">
		                 <tr>
		                     <td style="text-align:center;">${historyRec.orgName}</td>
		                     <td style="text-align:center;">${historyRec.speName}</td>
		                     <td style="text-align:center;">${historyRec.recruitTypeName}</td>
		                     <td style="text-align:center;">${historyRec.examResult}</td>
		                     <td style="text-align:center;">${historyRec.operResult}/${historyRec.auditionResult}</td>
		                     <td style="text-align:center;">${historyRec.recruitDate}</td>
		                     <td style="text-align:center;">
		                         <c:choose>
		                             <c:when test='${historyRec.confirmFlag eq "F"}'>
		                                 <span>已过期</span>
		                             </c:when>
		                             <c:when test='${historyRec.confirmFlag eq "N"}'>
		                                 <span>放弃录取</span>
		                             </c:when>
		                             <c:when test='${historyRec.confirmFlag eq "Y"}'>
		                                 <span>已确认录取</span>
		                             </c:when>
		                             <c:when test='${historyRec.recruitFlag eq "N"}'>
		                                 <span>未录取</span>
		                             </c:when>
		                             <c:when test='${historyRec.recruitFlag eq "Y"}'>
		                                 <span>已录取(等待学员确认)</span>
		                             </c:when>
		                         </c:choose>
		                     </td>
		                 </tr>
		             </c:forEach>
		         
		     </table>
		 </div>
		 
		<div align="center" style="margin-top: 20px; margin-bottom:20px;" class="notPrint">
			<a class="btn_grey" id="printBtn" onclick="pt();">&nbsp;打&nbsp;印&nbsp;</a>&nbsp;&nbsp;&nbsp;
			<a class="btn_grey" id="closeBtn" href="javascript:jboxClose();">&nbsp;关&nbsp;闭&nbsp;</a>
		</div>
	</div>
</body>
</html>