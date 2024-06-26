<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>湖北省住院医师规范化培训招录系统</title>
<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>

<script type="text/javascript">
function modPasswd(userFlow){
	var url = "<s:url value='/hbres/singup/modPasswd'/>?userFlow="+userFlow;
	jboxOpen(url , "修改密码" , 500 , 300 , true);
}

function modUserCode(userFlow){
	if($("#userCode").validationEngine("validate")){
		return false;
	}
	if('${user.userEmail}'==$("#userCode").val()){
		jboxTip("登录名与邮箱重复，无法修改!");
		return;
	}
	jboxConfirm("确认修改?修改后无法再次修改登录名!"  , function(){
		jboxPost("<c:url value='/hbres/singup/modusercode'/>" , {"userFlow":userFlow , "userCode":$("#userCode").val()} , function(resp){
			if(resp=="1"){
				location.href="<s:url value='/hbres/singup/doctor/userInfo'/>";
			}else{
				jboxTip(resp);
			}

		} , null , false);
	});

}

function emailMain(userFlow){
	window.location.href="<s:url value='/hbres/singup/user/emailMain?userFlow='/>"+ userFlow+
			"&source=${GlobalConstant.RES_ROLE_SCOPE_DOCTOR}";
}

function phoneMain(userFlow){
	window.location.href="<s:url value='/hbres/singup/user/phoneMain?userFlow='/>"+ userFlow+
			"&source=${GlobalConstant.RES_ROLE_SCOPE_DOCTOR}";
}

</script>
<style>
    .form_tab th{
        line-height: 2;
    }
</style>
</head>

<body>
  <div class="main_wrap">
    <div class="user-contain">
      <h1>个人信息</h1>
      <p style="font-size: 14px;float: right;"><a href='javascript:modPasswd("${user.userFlow}")'>修改密码</a></p>
      <div class="user-bd">
        <h2 style="color: #459ae9">个人信息类型</h2>


        <table cellpadding="0" cellspacing="0" border="0" class="form_tab">
          <colgroup>
            <col width="20%" />
            <col width="20%" />
            <col width="20%" />
            <col width="20%" />
            <col width="20%" />
          </colgroup>
          <tr class="odd">
            <th>医师类型：</th>
            <td>${doctor.doctorTypeName}</td>
            <th>&nbsp;</th>
            <td>&nbsp;</td>
            <th>&nbsp;</th>
          </tr>
            <c:if test="${doctor.doctorTypeId eq hBRecDocTypeEnumCompany.id || doctor.doctorTypeId eq hBRecDocTypeEnumCompanyEntrust.id}">
                <tr class="even">
                    <th>工作单位名称：</th>
                    <td>${doctor.workOrgName}</td>
                    <th>委培单位同意证明：</th>
                    <td>
                        <c:if test="${!empty doctor.dispatchFile}">
                            [<a href="${sysCfgMap['upload_base_url']}/${doctor.dispatchFile}" target="_blank">点击查看</a>]
                        </c:if>
                    </td>
                    <th>&nbsp;</th>
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
                    <tr class="even">
                        <th>医院类别：</th>
                        <td>${extInfo.hospitalCategoryName}</td>
                        <th>单位性质：</th>
                        <td>${extInfo.baseAttributeName}</td>
                        <td></td>
                    </tr>
                </c:if>
            </c:if>
            <c:if test="${doctor.doctorTypeId eq hBRecDocTypeEnumGraduate.id}">
                <tr class="even">
                    <th>在读院校名称：</th>
                    <td>${doctor.workOrgName}</td>
                    <th>&nbsp;</th>
                    <td>&nbsp;</td>
                    <th>&nbsp;</th>
                </tr>
            </c:if>
        </table>
      </div>
      <div class="user-bd">
        <h2 style="color: #459ae9">个人信息</h2>
        <table cellpadding="0" cellspacing="0" border="0" class="form_tab">
          <colgroup>
            <col width="20%" />
            <col width="20%" />
            <col width="20%" />
            <col width="20%" />
            <col width="20%" />
          </colgroup>
          <tr class="even">
             <c:if test='${user.userCode eq user.userEmail}'>
                <th>登录名：</th>
                <td colspan="4">
                    <input type="text" class="validate[required,custom[userCode]] input" style="vertical-align: initial;" id="userCode" value="${user.userCode}"/>
                    <a href="javascript:void(0);" onclick="modUserCode('${user.userFlow}');">修改</a>&#12288;<font color='red'>您只可以修改一次用户名(不同于邮箱)，修改后可使用用户名登录系统.</font>
                </td>
             </c:if>
             <c:if test='${user.userCode!=user.userEmail}'>
                <th>登录名：</th>
                <td colspan="4">${user.userCode}</td>
             </c:if>
          </tr>
          <tr class="odd">
            <th>姓名：</th>
            <td>${user.userName}</td>
            <th>性别：</th>
            <td>${user.sexName}</td>
            <td rowspan="6" style="padding-left: 20px;">
            	<img src="${sysCfgMap['upload_base_url']}/${doctor.doctorHeadImg}" width="140px" height="200px"/>
			</td>
          </tr>
          <tr class="even">
              <th>出生日期：</th>
              <td>${user.userBirthday}</td>
              <th>年龄：</th>
              <td>${extInfo.age}</td>
          </tr>
			<tr class="odd">
                <th>证件类型：</th>
                <td>${user.cretTypeName}</td>
                <th>证件号码：</th>
                <td>${user.idNo} </td>
			</tr>
			<tr class="even">
                <th>民族：</th>
                <td>${user.nationName}</td>
                <th>婚姻状况：</th>
                <td>${extInfo.maritalStatusName}</td>
            </tr>
            <tr class="odd">
                <th>手机：</th>
                <td>${user.userPhone}
                    <c:if test="${GlobalConstant.FLAG_N eq sysCfgMap['user_edit_phone']}">
                        &#12288;<a href="javascript:phoneMain('${currUser.userFlow}');">修改</a>
                    </c:if>
                </td>
                <th>QQ：</th>
                <td>${user.userQq} </td>
            </tr>
			</tr>
			<tr class="even">
                <th>邮箱：</th>
                <td>${user.userEmail}
                    <c:if test="${GlobalConstant.FLAG_N eq sysCfgMap['user_edit_mail']}">
                        &#12288;<a href="javascript:emailMain('${currUser.userFlow}');">修改</a>
                    </c:if>
                </td>
                <th>计算机能力：</th>
                <td>${doctor.computerSkills} </td>
            </tr>
            <tr class="odd">
                <th>外语能力：</th>
                <td>${doctor.foreignSkills} </td>
                <th>通讯地址：</th>
                <td>${user.userAddress}</td>
			</tr>
            <tr class="even">
                <th>人员属性：</th>
                <td>${extInfo.personnelAttributeName} </td>
                <th>紧急联系人：</th>
                <td>${doctor.emergencyName}</td>
            </tr>
            <tr class="odd">
                <th>紧急联系方式：</th>
                <td>${doctor.emergencyPhone} </td>
                <th>与本人关系：</th>
                <td>${doctor.emergencyRelation}</td>
            </tr>
            <tr class="even">
                <th>户口所在地省行政区划：</th>
                <td>${extInfo.locationOfProvinceName}</td>
                <th></th><td></td>
            </tr>
          </table>
        </div>
        <div class="user-bd">
            <h2 style="color: #459ae9">教育情况</h2>
            <table cellpadding="0" cellspacing="0" border="0" class="form_tab">
                <colgroup>
                    <col width="20%" />
                    <col width="20%" />
                    <col width="20%" />
                    <col width="20%" />
                    <col width="20%" />
                </colgroup>
                <tr class="odd">
                    <th>是否为应届生：</th>
                    <td><c:if test="${doctor.isYearGraduate eq 'Y'}">是</c:if><c:if test="${doctor.isYearGraduate eq 'N'}">否</c:if></td>
                    <th>是否全科订单走向学员：</th>
                    <td><c:if test="${extInfo.isGeneralOrderOrientationTrainee eq 'Y'}">是</c:if><c:if test="${extInfo.isGeneralOrderOrientationTrainee eq 'N'}">否</c:if></td>
                </tr>
                <c:if test="${extInfo.isCollegeDegree eq 'Y'}">
                    <tr class="even">
                        <th>是否为专科：</th>
                        <td>是</td>
                        <th>专科毕业院校：</th>
                        <td>${extInfo.collegeName}</td>
                    </tr>
                    <tr class="odd">
                        <th>专科毕业时间：</th>
                        <td>${extInfo.collegeGraduationTime}</td>
                        <th>专科毕业专业：</th>
                        <td>${extInfo.collegeGraduationSpe}</td>
                    </tr>
                    <tr class="even">
                        <th>专科学位：</th>
                        <td>${extInfo.collegeDegree}</td>
                        <th></th><td></td>
                    </tr>
                </c:if>
                <c:if test="${extInfo.isCollegeDegree eq 'N'}">
                    <tr class="even">
                        <th>是否为专科：</th>
                        <td>否</td>
                        <th></th><td></td>
                    </tr>
                </c:if>
                <c:if test="${extInfo.isUndergraduateDegree eq 'Y'}">
                    <tr class="odd">
                        <th>是否为本科：</th>
                        <td>是</td>
                        <th>本科毕业院校：</th>
                        <td>${extInfo.graduatedName}</td>
                    </tr>
                    <tr class="even">
                        <th>本科毕业时间：</th>
                        <td>${extInfo.graduationTime}</td>
                        <th>本科毕业专业：</th>
                        <td>${extInfo.zbkbySpe}</td>
                    </tr>
                    <tr class="odd">
                        <th>本科学位：</th>
                        <td>${extInfo.undergraduateDegreeName}</td>
                        <th></th><td></td>
                    </tr>
                </c:if>
                <c:if test="${extInfo.isUndergraduateDegree eq 'N'}">
                    <tr class="odd">
                        <th>是否为本科：</th>
                        <td>否</td>
                        <th></th><td></td>
                    </tr>
                </c:if>
                <c:if test="${extInfo.isMaster eq 'Y'}">
                    <tr class="even">
                        <th>是否为硕士：</th>
                        <td>是</td>
                        <th>硕士毕业院校：</th>
                        <td>${extInfo.masterGraSchoolName}</td>
                    </tr>
                    <tr class="odd">
                        <th>硕士毕业时间：</th>
                        <td>${extInfo.masterGraTime}</td>
                        <th>硕士毕业专业：</th>
                        <td>${extInfo.masterMajor}</td>
                    </tr>
                    <tr class="even">
                        <th>硕士学位：</th>
                        <td>${extInfo.masterDegreeName}</td>
                        <th>硕士学位类型：</th>
                        <td>${extInfo.masterDegreeTypeName}</td>
                    </tr>
                </c:if>
                <c:if test="${extInfo.isMaster eq 'N'}">
                    <tr class="even">
                        <th>是否为硕士：</th>
                        <td>否</td>
                        <th></th><td></td>
                    </tr>
                </c:if>
                <c:if test="${extInfo.isDoctor eq 'Y'}">
                    <tr class="odd">
                        <th>是否为博士：</th>
                        <td>是</td>
                        <th>博士毕业院校：</th>
                        <td>${extInfo.doctorGraSchoolName}</td>
                    </tr>
                    <tr class="even">
                        <th>博士毕业时间：</th>
                        <td>${extInfo.doctorGraTime}</td>
                        <th>博士毕业专业：</th>
                        <td>${extInfo.doctorMajor}</td>
                    </tr>
                    <tr class="odd">
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
                    <tr class="odd">
                        <th>是否为博士：</th>
                        <td>否</td>
                        <th></th><td></td>
                    </tr>
                </c:if>
                <c:if test="${extInfo.hasTakenMasterExam eq 'Y'}">
                    <tr class="even">
                        <th>是否参加研究生考试：</th>
                        <td>是</td>
                        <th>研究生考试成绩：</th>
                        <td>${extInfo.masterExamResult}</td>
                    </tr>
                    <tr class="odd">
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
                <tr class="even">
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
                <tr class="odd">
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
                <tr class="even">
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
                <tr class="odd">
                    <th>最高毕业证书：</th>
                    <td>
                        <c:if test="${!empty extInfo.certificateUri}">
                            [<a href="${sysCfgMap['upload_base_url']}/${extInfo.certificateUri}" target="_blank">点击查看</a>]
                        </c:if>
                    </td>
                </tr>
            </table>
        </div>
        <div class="user-bd">
            <h2 style="color: #459ae9">医师资格信息</h2>
            <table cellpadding="0" cellspacing="0" border="0" class="form_tab">
                <colgroup>
                    <col width="20%" />
                    <col width="20%" />
                    <col width="20%" />
                    <col width="20%" />
                    <col width="20%" />
                </colgroup>
                <c:if test="${extInfo.isPassQualifyingExamination eq 'Y'}">
                    <tr class="even">
                        <th>是否通过医师资格考试：</th>
                        <td>是</td>
                        <th>通过医师资格考试时间：</th>
                        <td>${extInfo.passQualifyingExaminationTime}</td>
                    </tr>
                </c:if>
                <c:if test="${extInfo.isPassQualifyingExamination eq 'N'}">
                    <tr class="even">
                        <th>是否通过医师资格考试：</th>
                        <td>否</td>
                        <th></th><td></td>
                    </tr>
                </c:if>
                <c:if test="${doctor.doctorLicenseFlag eq 'N'}">
                    <tr class="odd">
                        <th>是否取得执业医师资格证：</th>
                        <td>否</td>
                        <th></th>
                        <td></td>
                    </tr>
                </c:if>
                <c:if test="${doctor.doctorLicenseFlag eq 'Y'}">
                    <tr class="odd">
                        <th>是否取得执业医师资格证：</th>
                        <td>是</td>
                        <th>取得医师资格证书时间：</th>
                        <td>${extInfo.haveQualificationCertificateTime}</td>
                    </tr>
                    <tr class="even">
                        <th>医师资格级别：</th>
                        <td>${extInfo.physicianQualificationLevel eq 'zyys'?'执业医师':''}${extInfo.physicianQualificationLevel eq 'zyzlys'?'执业助理医师':''}</td>
                        <th>医师资格类别：</th>
                        <td>${extInfo.physicianQualificationClassName}</td>
                    </tr>
                    <tr class="odd">
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
                    <tr class="even">
                        <th>是否获得医师执业证书：</th>
                        <td>是</td>
                        <th>取得医师执业证书时间：</th>
                        <td>${extInfo.havePracticingCategoryTime}</td>
                    </tr>
                    <tr class="odd">
                        <th>执业类型：</th>
                        <td>${extInfo.practicingCategoryName}</td>
                        <th>执业范围：</th>
                        <td>${extInfo.practicingScopeName}</td>
                    </tr>
                    <tr class="even">
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
                    <tr class="even">
                        <th>是否获得医师执业证书：</th>
                        <td>否</td>
                        <th></th>
                        <td></td>
                    </tr>
                </c:if>
            </table>
        </div>
        <div class="user-bd">
            <h2 style="color: #459ae9">西部支援情况</h2>
            <table cellpadding="0" cellspacing="0" border="0" class="form_tab">
                <colgroup>
                    <col width="20%" />
                    <col width="20%" />
                    <col width="20%" />
                    <col width="20%" />
                    <col width="20%" />
                </colgroup>
                <c:if test="${extInfo.isAssistance eq 'Y'}">
                    <tr class="odd">
                        <th>是否为西部支援行动住院医师：</th>
                        <td>是</td>
                        <th>西部支援行动住院医师送出省份：</th>
                        <td>${extInfo.assistanceProvince}</td>
                    </tr>
                    <tr class="even">
                        <th>西部支援行动住院医师送出单位：</th>
                        <td>${extInfo.westernSupportResidentsSendWorkUnit}</td>
                        <th>西部支援行动住院医师送出单位是否军队医院：</th>
                        <td>${extInfo.isMilitary eq 'Y'?'是':'否'}</td>
                    </tr>
                    <c:if test="${not empty extInfo.assistanceCode}">
                        <tr class="odd">
                            <th>西部支援行动住院医师送出单位统一社会信用代码：</th>
                            <td>${extInfo.assistanceCode}</td>
                        </tr>
                    </c:if>
                </c:if>
                <c:if test="${extInfo.isAssistance ne 'Y'}">
                    <tr class="odd">
                        <th>是否为西部支援行动住院医师：</th>
                        <td>否</td>
                        <th></th><td></td>
                    </tr>
                </c:if>
            </table>
        </div>
    </div>
  </div>
</body>
</html>
