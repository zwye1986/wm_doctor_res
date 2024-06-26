<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <script src="<s:url value='/js/jquery-1.9.1.min.js'/>" type="text/javascript"></script>
    <style type="text/css">

        table.gridtable {
            font-family: verdana,arial,sans-serif;
            font-size:11px;
            color:#333333;
            border-width: 1px;
            border-color: #666666;
            border-collapse: collapse;
        }
        table.gridtable th {
            border-width: 1px;
            padding: 5px;
            border-style: solid;
            border-color: #666666;
            background-color: #dedede;
            text-align: right;
        }
        table.gridtable td {
            border-width: 1px;
            padding: 5px;
            border-style: solid;
            border-color: #666666;
            background-color: #ffffff;
            text-align: left;
        }
    </style>
    <script src="<s:url value='/js/jquery.jqprint-0.3.js'/>" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            window.print();
        });
    </script>
</head>
<body>
<div id="printDiv" style="overflow-y: auto;overflow-x: visible;">
        <div style="margin-bottom:10px;" class="div1">
            <table class="gridtable" style="width:97%; margin-top: 3px; margin-bottom: 10px;">
                <tr><th colspan="99" style="text-align: center;">基本信息</th></tr>
                <tr>
                    <th style="width:20%;"><span >学号：</span></th>
                    <td style="width:30%;">
                        <label>${eduUser.sid}</label>
                    </td>
                    <th style="width:20%;"><span >证件类型：</span></th>
                    <td style="width:30%;">
                        <label>${sysUser.cretTypeName }</label>
                    </td>
                </tr>
                <tr>
                    <th><span >姓名：</span></th>
                    <td>
                        <label>${sysUser.userName }</label>
                    </td>
                    <th><span >证件号码：</span></th>
                    <td>
                        <label>${sysUser.idNo }</label>
                    </td>
                </tr>
                <tr>
                    <th><span >姓名拼音：</span></th>
                    <td>
                        <label>${eduUser.nameSpell }</label>
                    </td>
                    <th><span >民族：</span></th>
                    <td>
                        <label>${sysUser.nationName}</label>
                    </td>
                </tr>
                <tr>
                    <th><span >性别：</span></th>
                    <td>
                        <label>${sysUser.sexName }</label>
                    </td>
                    <th><span >班级：</span></th>
                    <td>
                        <label>${eduUser.className}</label>
                    </td>
                </tr>
                <tr>
                    <th><span >出生日期：</span></th>
                    <td>
                        <label>${sysUser.userBirthday }</label>
                    </td>
                    <th><span >专业名称：</span></th>
                    <td>
                        <label>[${eduUser.majorId}]${eduUser.majorName}</label>
                    </td>
                </tr>
                <tr>
                    <th><span >入学年级：</span></th>
                    <td>
                        <label>${eduUser.period }</label>
                    </td>
                    <th><span >培养层次：</span></th>
                    <td>
                        <label>${eduUser.trainTypeName}</label>
                    </td>
                </tr>
                <tr>
                    <th><span >学位分委员会：</span></th>
                    <td>
                        <c:forEach items="${deptList }" var="dept">
                            ${dept.deptFlow eq sysUser.deptFlow?dept.deptName:'' }
                        </c:forEach>
                    </td>
                    <th><span >是否5+3培养模式：</span></th>
                    <td>
                        <label>
                            <c:if test="${eduUser.is5plus3==GlobalConstant.FLAG_Y}">是</c:if>
                            <c:if test="${eduUser.is5plus3==GlobalConstant.FLAG_N}">否</c:if>
                        </label>
                    </td>
                </tr>
                <tr>
                    <th><span >培养单位：</span></th>
                    <td>
                        <label>${resDoctor.orgName }</label>
                    </td>
                    <th><span >研究方向：</span></th>
                    <td>
                        <label>${eduUser.researchDirName }</label>
                    </td>
                </tr>
                <tr>
                    <th><span >培养类型：</span></th>
                    <td>
                        <label>${eduUser.trainCategoryName}</label>
                    </td>
                    <th></th>
                    <td></td>
                </tr>
                <tr>
                    <th><span >导师一：</span></th>
                    <td>
                        <label>${eduUser.firstTeacher }</label>
                    </td>
                    <th><span >导师二：</span></th>
                    <td>
                        <label>${eduUser.secondTeacher }</label>
                    </td>
                </tr>
                <tr>
                    <th><span >学籍状态：</span></th>
                    <td>
                        <label>${eduUser.schoolRollStatusName}</label>
                    </td>
                    <th><span >学籍注册时间：</span></th>
                    <td>
                        <label>${extInfoForm.xjRegDate }</label>
                    </td>
                </tr>
                <tr>
                    <th><span >是否报到：</span></th>
                    <td>
                        <label>
                            <c:if test="${eduUser.isReported eq GlobalConstant.FLAG_Y}">是</c:if>
                            <c:if test="${eduUser.isReported eq GlobalConstant.FLAG_N}">否</c:if>
                        </label>
                    </td>
                    <th><span >报到时间：</span></th>
                    <td>
                        <label>${extInfoForm.reportedDate}</label>
                    </td>
                </tr>
                <tr>
                    <th><span >在校状态：</span></th>
                    <td>
                        <label>${eduUser.atSchoolStatusName }</label>
                    </td>
                    <th><span >学习形式：</span></th>
                    <td>
                        <label>${eduUser.studyFormName}</label>
                    </td>
                </tr>
                <tr>
                    <th><span >是否休学：</span></th>
                    <td>
                        <label>
                            <c:if test="${eduUser.isOutOfSchool eq GlobalConstant.FLAG_Y}">是</c:if>
                            <c:if test="${eduUser.isOutOfSchool eq GlobalConstant.FLAG_N}">否</c:if>
                        </label>
                    </td>
                    <th><span >${eduUser.isOutOfSchool eq GlobalConstant.FLAG_Y?'休学日期：':''}</span></th>
                    <td>
                        <label>${eduUser.isOutOfSchool eq GlobalConstant.FLAG_Y?extInfoForm.outOfSchoolDate:''}</label>
                    </td>
                </tr>
                <tr>
                    <th><span >是否复学：</span></th>
                    <td>
                        <label>
                            <c:if test="${eduUser.isBackToSchool eq GlobalConstant.FLAG_Y}">是</c:if>
                            <c:if test="${eduUser.isBackToSchool eq GlobalConstant.FLAG_N}">否</c:if>
                        </label>
                    </td>
                    <th><span >${eduUser.isBackToSchool eq GlobalConstant.FLAG_Y?'复学日期：':''}</span></th>
                    <td>
                        <label>${eduUser.isBackToSchool eq GlobalConstant.FLAG_Y?extInfoForm.backToSchoolDate:''}</label>
                    </td>
                </tr>
                <tr>
                    <th><span >退学时间：</span></th>
                    <td>
                        <label>${extInfoForm.dropOutSchoolDate}</label>
                    </td>
                    <th></th>
                    <td></td>
                </tr>
            </table>
            <table class="gridtable" style="width:97%; margin-top: 3px; margin-bottom: 10px;page-break-after:always;">
                <tr><th colspan="99" style="text-align: center;">录取信息</th></tr>
                <tr>
                    <th style="width:20%;"><span >考生编号：</span></th>
                    <td style="width:30%;">
                        <label>${eduUser.studentCode }</label>
                    </td>
                    <th style="width:20%;"><span >考生来源：</span></th>
                    <td style="width:30%;">
                        <label>
                            <c:forEach items="${dictTypeEnumGyStudentSourceList}" var="studentSource">
                                ${eduUser.studentSourceId eq studentSource.dictId?studentSource.dictName:''}
                            </c:forEach>
                        </label>
                    </td>
                </tr>
                <tr>
                    <th><span >录取类别：</span></th>
                    <td>
                        <label>${eduUser.admitTypeName}</label>
                    </td>
                    <th><span >招生季节：</span></th>
                    <td>
                        <label>${eduUser.recruitSeasonName}</label>
                    </td>
                </tr>
                <tr>
                    <th><span >招录方式：</span></th>
                    <td>
                        <label>
                            <c:forEach items="${dictTypeEnumGyRecruitTypeList}" var="dict">
                                ${extInfoForm.recruitType eq dict.dictId? dict.dictName:''}
                            </c:forEach>
                        </label>
                    </td>
                    <th><span >是否破格：</span></th>
                    <td>
                        <label>${eduUser.isExceptionalName}</label>
                    </td>
                </tr>
                <tr>
                    <th><span >外国语名称：</span></th>
                    <td>
                        <label>${extInfoForm.foreignLanguageName }</label>
                    </td>
                    <th><span >外国语成绩：</span></th>
                    <td>
                        <label>${extInfoForm.foreignLanguageScore }</label>
                    </td>
                </tr>
                <tr>
                    <th><span >政治理论：</span></th>
                    <td>
                        <label>${extInfoForm.politicalTheoryName }</label>
                    </td>
                    <th><span >政治理论成绩：</span></th>
                    <td>
                        <label>${extInfoForm.politicalTheoryScore }</label>
                    </td>
                </tr>
                <tr>
                    <th><span >业务课一：</span></th>
                    <td>
                        <label>${extInfoForm.firstSubjectName }</label>
                    </td>
                    <th><span >业务课一成绩：</span></th>
                    <td>
                        <label>${extInfoForm.firstSubjectScore }</label>
                    </td>
                </tr>
                <tr>
                    <th><span >业务课二：</span></th>
                    <td>
                        <label>${extInfoForm.secondSubjectName }</label>
                    </td>
                    <th><span >业务课二成绩：</span></th>
                    <td>
                        <label>${extInfoForm.secondSubjectScore }</label>
                    </td>
                </tr>
                <tr>
                    <th><span >加试科一：</span></th>
                    <td>
                        ${extInfoForm.firstAddExamName }
                    </td>
                    <th><span >加试科一成绩：</span></th>
                    <td>
                        <label>${extInfoForm.firstAddExamScore }</label>
                    </td>
                </tr>
                <tr>
                    <th><span >加试科二：</span></th>
                    <td>
                        <label>${extInfoForm.secondAddExamName }</label>
                    </td>
                    <th><span >加试科二成绩：</span></th>
                    <td>
                        <label>${extInfoForm.secondAddExamScore }</label>
                    </td>
                </tr>
                <tr>
                    <th><span >复试成绩：</span></th>
                    <td>
                        <label>${extInfoForm.reexamineScore }</label>
                    </td>
                    <th><span >总分：</span></th>
                    <td>
                        <label>${extInfoForm.totalPointsScore }</label>
                    </td>
                </tr>
            </table>
            <table class="gridtable" style="width:97%; margin-top: 3px; margin-bottom: 10px;">
                <tr><th colspan="99" style="text-align: center;">必填信息</th></tr>
                    <tr>
                        <td rowspan="5" style="width:20%; text-align:center;margin-top: 2px;">
                            <img src="${sysCfgMap['upload_base_url']}/${sysUser.userHeadImg}" width="100px;" height="130px;" onerror="this.src='<s:url value="/jsp/gyxjgl/images/up-pic.jpg"/>'"/>
                        </td>
                        <%--<td></td>--%>
                        <th><span >籍贯：</span></th>
                        <td colspan="2">
                            <label>${sysUser.nativePlaceProvName}${sysUser.nativePlaceCityName}${sysUser.nativePlaceAreaName}</label>
                        </td>
                    </tr>
                    <tr>
                        <%--<td></td>--%>
                        <th><span >婚姻状况：</span></th>
                        <td colspan="2">
                            <label>${sysUser.maritalName}</label>
                        </td>
                    </tr>
                    <tr>
                        <%--<td></td>--%>
                        <th><span >生育状况：</span></th>
                        <td  colspan="2">
                            <label>${sysUser.bearName }</label>
                        </td>
                    </tr>
                    <tr>
                        <%--<td></td>--%>
                        <th><span >政治面貌：</span></th>
                        <td  colspan="2">
                            ${sysUser.politicsStatusName}
                        </td>
                    </tr>
                    <tr>
                        <%--<td></td>--%>
                        <th><span >入党（团）时间：</span></th>
                        <td colspan="2" >
                            <label>${extInfoForm.joinOrgTime }</label>
                        </td>
                    </tr>
                    <tr>
                        <th style="width:20%"><span >党团关系是否转入：</span></th>
                        <td style="width:30%">
                            <c:if test="${extInfoForm.isRelationInto==GlobalConstant.FLAG_Y}">是</c:if>
                            <c:if test="${extInfoForm.isRelationInto==GlobalConstant.FLAG_N}">否</c:if>
                        </td>
                        <th style="width:20%;"><span >${extInfoForm.isRelationInto eq GlobalConstant.FLAG_Y?'转入年月日：':''}</span></th>
                        <td style="width:30%">
                            <label>${extInfoForm.isRelationInto eq GlobalConstant.FLAG_Y?(empty extInfoForm.joinTime?'等待老师录入':extInfoForm.joinTime):''}</label>
                        </td>
                    </tr>
                    <tr>
                        <th><span >入学前户口所在地：</span></th>
                        <td colspan="3">
                            ${domicileName[0]}${domicileName[1]}${domicileName[2]}
                        </td>
                    </tr>
                    <tr>
                        <th><span >入学前户口所在详细地址：</span></th>
                        <td colspan="3">
                            <label>${sysUser.domicilePlaceAddress }</label>
                        </td>
                    </tr>
                    <tr>
                        <th><span >户口是否需要迁入我校：</span></th>
                        <td>
                            <label>
                                <c:if test="${extInfoForm.hkInSchool eq '1'}">是</c:if>
                                <c:if test="${extInfoForm.hkInSchool eq '2'}">否</c:if>
                            </label>
                        </td>
                        <th><span >${extInfoForm.hkInSchool eq '1'?'户口迁移证编号：':''}</span></th>
                        <td>
                            <label>${extInfoForm.hkInSchool eq '1'?extInfoForm.hkChangeNo:'' }</label>
                        </td>
                    </tr>
                    <tr>
                        <th><span >原学习或工作单位：</span></th>
                        <td colspan="3">
                            <label>${extInfoForm.oldOrgName }</label>
                        </td>
                    </tr>
                    <tr>
                        <th><span >原档案所在单位：</span></th>
                        <td>
                            <label>${extInfoForm.oldFileLocationOrg }</label>
                        </td>
                        <th style="width:20%;"><span >原档案所在单位邮编：</span></th>
                        <td style="width:30%;">
                            <label>${extInfoForm.oldFileLocationOrgCode }</label>
                        </td>
                    </tr>
                    <tr>
                        <th><span >档案所在地：</span></th>
                        <td colspan="3">
                            ${recordName[0]}${recordName[1]}${recordName[2]}
                        </td>
                    </tr>
                    <tr>
                        <th><span >现家庭住址：</span></th>
                        <td colspan="3">
                            <label>${extInfoForm.nowResideAddress }</label>
                        </td>
                    </tr>
                    <tr>
                        <th><span >紧急联系人姓名：</span></th>
                        <td>
                            ${extInfoForm.linkMan }
                        </td>
                        <th><span >紧急联系人电话：</span></th>
                        <td>
                            <label>${extInfoForm.telephone }</label>
                        </td>
                    </tr>
                    <tr>
                        <th><span >是否有医师资格证：</span></th>
                        <td>
                            <c:if test="${extInfoForm.isDoctorQuaCert==GlobalConstant.FLAG_Y}">是</c:if>
                            <c:if test="${extInfoForm.isDoctorQuaCert==GlobalConstant.FLAG_N}">否</c:if>
                        </td>
                        <th><span >${extInfoForm.isDoctorQuaCert eq GlobalConstant.FLAG_Y?'资格证编号：':''}</span></th>
                        <td>
                            <label>${extInfoForm.isDoctorQuaCert eq GlobalConstant.FLAG_Y?extInfoForm.codeDoctorQuaCert:''}</label>
                        </td>
                    </tr>
                    <tr>
                        <th><span >是否有执业医师执照：</span></th>
                        <td>
                            <c:if test="${extInfoForm.isMedicalPractitioner==GlobalConstant.FLAG_Y}">是</c:if>
                            <c:if test="${extInfoForm.isMedicalPractitioner==GlobalConstant.FLAG_N}">否</c:if>
                        </td>
                        <th><span >${extInfoForm.isMedicalPractitioner eq GlobalConstant.FLAG_Y?'执照编号：':''}</span></th>
                        <td>
                            <label>${extInfoForm.isMedicalPractitioner eq GlobalConstant.FLAG_Y?extInfoForm.codeMedicalPractitioner:''}</label>
                        </td>
                    </tr>
                    <tr>
                        <th><span >是否住宿：</span></th>
                        <td>
                            <c:if test="${extInfoForm.isStay==GlobalConstant.FLAG_Y}">是</c:if>
                            <c:if test="${extInfoForm.isStay==GlobalConstant.FLAG_N}">否</c:if>
                        </td>
                        <th>&#12288;<span >${extInfoForm.isStay eq GlobalConstant.FLAG_Y?'宿舍号：':''}</span></th>
                        <td>
                            ${extInfoForm.isStay eq GlobalConstant.FLAG_Y?extInfoForm.roomNumStay:''}
                        </td>
                    </tr>
                    <tr>
                        <th><span >本人手机号码：</span></th>
                        <td>
                            ${sysUser.userPhone }
                        </td>
                        <th><span >电子邮箱：</span></th>
                        <td>
                            ${sysUser.userEmail }
                        </td>
                    </tr>
                    <tr>
                        <th><span >微信号：</span></th>
                        <td>
                            ${sysUser.weiXinName }
                        </td>
                        <th><span >QQ号：</span></th>
                        <td>
                            ${sysUser.userQq }
                        </td>
                    </tr>
                    <tr>
                        <th><span >身高：</span></th>
                        <td>
                            ${extInfoForm.height }
                        </td>
                        <th><span >血型：</span></th>
                        <td>
                            ${extInfoForm.bloodType }
                        </td>
                    </tr>
                    <tr>
                        <th>&#12288;<span >医保卡号：</span></th>
                        <td>
                            ${extInfoForm.ybCardNo }
                        </td>
                        <th></th>
                        <td></td>
                    </tr>
                    <tr>
                        <th><span >是否有固定收入：</span></th>
                        <td>
                            <c:if test="${extInfoForm.isFixedIncome==GlobalConstant.FLAG_Y}">是</c:if>
                            <c:if test="${extInfoForm.isFixedIncome==GlobalConstant.FLAG_N}">否</c:if>
                        </td>
                        <th><span >是否五保户：</span></th>
                        <td>
                            <c:if test="${extInfoForm.isFiveGuarantees==GlobalConstant.FLAG_Y}">是</c:if>
                            <c:if test="${extInfoForm.isFiveGuarantees==GlobalConstant.FLAG_N}">否</c:if>
                        </td>
                    </tr>
                    <tr>
                        <th><span >是否低保：</span></th>
                        <td>
                            <c:if test="${extInfoForm.isLowGuarantees==GlobalConstant.FLAG_Y}">是</c:if>
                            <c:if test="${extInfoForm.isLowGuarantees==GlobalConstant.FLAG_N}">否</c:if>
                        </td>
                        <th><span >是否扶贫户：</span></th>
                        <td>
                            <c:if test="${extInfoForm.isPoor==GlobalConstant.FLAG_Y}">是</c:if>
                            <c:if test="${extInfoForm.isPoor==GlobalConstant.FLAG_N}">否</c:if>
                        </td>
                    </tr>
                    <tr>
                        <th><span >是否孤儿：</span></th>
                        <td>
                            <c:if test="${extInfoForm.isOrphan==GlobalConstant.FLAG_Y}">是</c:if>
                            <c:if test="${extInfoForm.isOrphan==GlobalConstant.FLAG_N}">否</c:if>
                        </td>
                        <th><span >是否单亲家庭子女：</span></th>
                        <td>
                            <c:if test="${extInfoForm.isSingleParent==GlobalConstant.FLAG_Y}">是</c:if>
                            <c:if test="${extInfoForm.isSingleParent==GlobalConstant.FLAG_N}">否</c:if>
                        </td>
                    </tr>
                    <tr>
                        <th><span >是否残疾人子女：</span></th>
                        <td>
                            <c:if test="${extInfoForm.isDisabledChildren==GlobalConstant.FLAG_Y}">是</c:if>
                            <c:if test="${extInfoForm.isDisabledChildren==GlobalConstant.FLAG_N}">否</c:if>
                        </td>
                        <th><span >本人是否残疾：</span></th>
                        <td>
                            <c:if test="${extInfoForm.isDisabled==GlobalConstant.FLAG_Y}">是</c:if>
                            <c:if test="${extInfoForm.isDisabled==GlobalConstant.FLAG_N}">否</c:if>
                        </td>
                    </tr>
                    <tr>
                        <th><span >残疾类别：</span></th>
                        <td>
                            <c:if test="${extInfoForm.isDisabledLevel eq '1'}">视力残疾</c:if>
                            <c:if test="${extInfoForm.isDisabledLevel eq '2'}">听力残疾</c:if>
                            <c:if test="${extInfoForm.isDisabledLevel eq '3'}">智力残疾</c:if>
                            <c:if test="${extInfoForm.isDisabledLevel eq '4'}">其他残疾</c:if>
                        </td>
                        <th><span >是否父母丧失劳动能力：</span></th>
                        <td>
                            <c:if test="${extInfoForm.isLostAbilityParent==GlobalConstant.FLAG_Y}">是</c:if>
                            <c:if test="${extInfoForm.isLostAbilityParent==GlobalConstant.FLAG_N}">否</c:if>
                        </td>
                    </tr>
                    <tr>
                        <th><span >是否家中有大病患者：</span></th>
                        <td>
                            <c:if test="${extInfoForm.isSeriousIllness==GlobalConstant.FLAG_Y}">是</c:if>
                            <c:if test="${extInfoForm.isSeriousIllness==GlobalConstant.FLAG_N}">否</c:if>
                        </td>
                        <th><span >是否建档立卡的贫困户：</span></th>
                        <td>
                            <c:if test="${extInfoForm.isFilingRiser==GlobalConstant.FLAG_Y}">是</c:if>
                            <c:if test="${extInfoForm.isFilingRiser==GlobalConstant.FLAG_N}">否</c:if>
                        </td>
                    </tr>
                    <tr>
                        <th><span >是否低收入家庭：</span></th>
                        <td>
                            <c:if test="${extInfoForm.isLowIncomeFamilies==GlobalConstant.FLAG_Y}">是</c:if>
                            <c:if test="${extInfoForm.isLowIncomeFamilies==GlobalConstant.FLAG_N}">否</c:if>
                        </td>
                        <th><span >是否军烈属或优抚子女：</span></th>
                        <td>
                            <c:if test="${extInfoForm.isSpecialCareChildren==GlobalConstant.FLAG_Y}">是</c:if>
                            <c:if test="${extInfoForm.isSpecialCareChildren==GlobalConstant.FLAG_N}">否</c:if>
                        </td>
                    </tr>
                    <tr>
                        <th><span >家庭人均年收入：</span></th>
                        <td>
                            <label>${extInfoForm.annualIncome}</label>
                        </td>
                        <th><span >家庭主要收入来源类型：</span></th>
                        <td>
                            <c:if test="${extInfoForm.majorSourceIncome eq '1'}">工资</c:if>
                            <c:if test="${extInfoForm.majorSourceIncome eq '2'}">奖金</c:if>
                            <c:if test="${extInfoForm.majorSourceIncome eq '3'}">津贴</c:if>
                            <c:if test="${extInfoForm.majorSourceIncome eq '4'}">补贴</c:if>
                            <c:if test="${extInfoForm.majorSourceIncome eq '5'}">其他劳动收入</c:if>
                            <c:if test="${extInfoForm.majorSourceIncome eq '6'}">自谋职业收入</c:if>
                            <c:if test="${extInfoForm.majorSourceIncome eq '5' || extInfoForm.majorSourceIncome eq '6'}">&#12288;${extInfoForm.majorSourceIncomeDesc}</c:if>
                        </td>
                    </tr>
                    <tr>
                        <th><span >家庭是否遭受自然灾害：</span></th>
                        <td>
                            <c:if test="${extInfoForm.isSufferNaturalDisasters==GlobalConstant.FLAG_Y}">是</c:if>
                            <c:if test="${extInfoForm.isSufferNaturalDisasters==GlobalConstant.FLAG_N}">否</c:if>
                        </td>
                        <th><span >自然灾害具体情况描述：</span></th>
                        <td>
                            <label>${extInfoForm.isSufferNaturalDisasters==GlobalConstant.FLAG_Y?extInfoForm.naturalDisastersDesc:''}</label>
                        </td>
                    </tr>
                    <tr>
                        <th><span >家庭是否遭受突发意外事件：</span></th>
                        <td>
                            <c:if test="${extInfoForm.isSufferEmergency==GlobalConstant.FLAG_Y}">是</c:if>
                            <c:if test="${extInfoForm.isSufferEmergency==GlobalConstant.FLAG_N}">否</c:if>
                        </td>
                        <th><span >突发意外事件具体描述：</span></th>
                        <td>
                            <label>${extInfoForm.isSufferEmergency==GlobalConstant.FLAG_Y?extInfoForm.emergencyDesc:''}</label>
                        </td>
                    </tr>
                    <tr>
                        <th><span >家庭欠债金额：</span></th>
                        <td>
                            <label>${extInfoForm.debtMoney}</label>
                        </td>
                        <th><span >家庭欠债原因：</span></th>
                        <td>
                            <label>${extInfoForm.debtReason}</label>
                        </td>
                    </tr>
                    <tr>
                        <th><span >家庭人口数：</span></th>
                        <td>
                            <label>${extInfoForm.familyPopulation}</label>
                        </td>
                        <th><span >劳动力人口数：</span></th>
                        <td>
                            <label>${extInfoForm.workingPopulation}</label>
                        </td>
                    </tr>
                    <tr>
                        <th><span >家庭成员失业人数：</span></th>
                        <td>
                            <label>${extInfoForm.unemployedPopulation}</label>
                        </td>
                        <th><span >赡养人口数：</span></th>
                        <td>
                            <label>${extInfoForm.supportPopulation}</label>
                        </td>
                    </tr>
                    <tr>
                        <th><span >其他信息：</span></th>
                        <td>
                            <label>${extInfoForm.otherDisastersDesc}</label>
                        </td>
                        <th><span >是否农村低保户：</span></th>
                        <td>
                            <c:if test="${extInfoForm.isRuralLowInsured==GlobalConstant.FLAG_Y}">是</c:if>
                            <c:if test="${extInfoForm.isRuralLowInsured==GlobalConstant.FLAG_N}">否</c:if>
                        </td>
                    </tr>
                    <tr>
                        <th><span >是否农村特困供养：</span></th>
                        <td>
                            <c:if test="${extInfoForm.isRuralPoverty==GlobalConstant.FLAG_Y}">是</c:if>
                            <c:if test="${extInfoForm.isRuralPoverty==GlobalConstant.FLAG_N}">否</c:if>
                        </td>
                        <th><span >其他：</span></th>
                        <td>
                            <label>${extInfoForm.otherDifficultyDesc}</label>
                        </td>
                    </tr>
                </table>
            <table class="gridtable" style="width:97%; margin-top: 3px; margin-bottom: 10px;">
                <tr><th colspan="99" style="text-align: center;">选填信息</th></tr>
                <tr>
                    <th style="width:20%;"><span >普通话水平：</span></th>
                    <td style="width:30%;">
                        <label>${extInfoForm.mandarinLevel }</label>
                    </td>
                    <th style="width:20%;"><span >外语水平：</span></th>
                    <td style="width:30%;">
                        <label>${extInfoForm.foreignLanguageLevel }</label>
                    </td>
                </tr>
                <tr>
                    <th><span >计算机水平：</span></th>
                    <td>
                        <label>${extInfoForm.computerLevel }</label>
                    </td>
                    <th></th>
                    <td></td>
                </tr>
                <tr>
                    <th><span >学制：</span></th>
                    <td>
                        <label>${extInfoForm.schoolSystem }</label>
                    </td>
                    <th><span >生源省市：</span></th>
                    <td>
                        <label>${extInfoForm.studentSourceArea }</label>
                    </td>
                </tr>
                <tr>
                    <th><span >宿舍地址：</span></th>
                    <td>
                        <label>${extInfoForm.dormitoryAdd }</label>
                    </td>
                    <th><span >家庭电话：</span></th>
                    <td>
                        <label>${extInfoForm.homePhone }</label>
                    </td>
                </tr>
                <tr>
                    <th><span >配偶姓名：</span></th>
                    <td>
                        <label>${extInfoForm.mateName }</label>
                    </td>
                    <th><span >配偶身份证：</span></th>
                    <td>
                        <label>${extInfoForm.mateIdNo }</label>
                    </td>
                </tr>
                <tr>
                    <th><span >配偶工作单位：</span></th>
                    <td colspan="3">
                        <label>${extInfoForm.spouseUnit }</label>
                    </td>
                </tr>
                <tr>
                    <th><span >特长：</span></th>
                    <td colspan="3">
                        <label>${extInfoForm.speciality }</label>
                    </td>
                </tr>
                <tr>
                    <th><span >本人主要简历：</span></th>
                    <td colspan="3">
                        <label>${extInfoForm.mainResume }</label>
                    </td>
                </tr>
                <tr>
                    <th><span >入学前奖惩情况：</span></th>
                    <td colspan="3">
                        <label>${extInfoForm.reAndPuStatusContent }</label>
                    </td>
                </tr>
                <tr>
                    <th><span >曾担任过合作教学工作&#12288;<br>和进行何种科研工作：</span></th>
                    <td colspan="3">
                        <label>${extInfoForm.scientificTogether }</label>
                    </td>
                </tr>
                <tr>
                    <th><span >备注：</span></th>
                    <td colspan="3">
                        <label>${extInfoForm.remark }</label>
                    </td>
                </tr>
            </table>
            <table class="gridtable" style="width:97%; margin-top: 3px; margin-bottom: 10px;">
                <tr><th colspan="99" style="text-align: center;">学费信息</th></tr>
                <tr>
                    <th style="width:20%;"><span >缴费账号：</span></th>
                    <td style="width:30%;">
                        <label>${extInfoForm.accountNum }</label>
                    </td>
                    <th style="width:20%;"><span >学费总额：</span></th>
                    <td style="width:30%;">
                        <label>${extInfoForm.tuitionFee }</label>
                    </td>
                </tr>
                <tr>
                    <th><span >已缴纳学费：</span></th>
                    <td>
                        <label>${extInfoForm.paytuitionFee }</label>
                    </td>
                    <th><span >欠缴纳学费：</span></th>
                    <td>
                        <label>${extInfoForm.arrearageFee }</label>
                    </td>
                </tr>
                <tr>
                    <th><span >住宿费：</span></th>
                    <td>
                        <label>${extInfoForm.dormitoryFee }</label>
                    </td>
                    <th><span >已缴纳住宿费：</span></th>
                    <td>
                        <label>${extInfoForm.payDormitoryFee }</label>
                    </td>
                </tr>
                <tr>
                    <th><span >欠缴纳住宿费：</span></th>
                    <td>
                        <label>${extInfoForm.arrearageDormitoryFee }</label>
                    </td>
                    <th></th>
                    <td></td>
                </tr>
            </table>
            <table class="gridtable" style="width:97%; margin-top: 3px; margin-bottom: 10px;">
                <tr><th colspan="99" style="text-align: center;">已获学历</th></tr>
                <tr>
                    <th style="width:20%;"><span >第一学历：</span></th>
                    <td style="width:30%;">
                        <label>
                            <c:forEach items="${dictTypeEnumGyFirstEducationList}" var="firstEducation">
                                ${extInfoForm.firstEducationContentId eq firstEducation.dictId ? firstEducation.dictName:''}
                            </c:forEach>
                        </label>
                    </td>
                    <th style="width:20%;"><span >前置学历：</span></th>
                    <td style="width:30%;">
                        <label>
                            <c:forEach items="${dictTypeEnumGyPreGraduationList}" var="dict">
                                ${extInfoForm.preGraduation eq dict.dictId ? dict.dictName:''}
                            </c:forEach>
                        </label>
                    </td>
                </tr>
                <tr>
                    <th><span >是否获得本科学历：</span></th>
                    <td>
                        <label>
                            <c:if test="${extInfoForm.bkgain eq '1'}">是</c:if>
                            <c:if test="${extInfoForm.bkgain eq '2'}">否</c:if>
                        </label>
                    </td>
                    <th style="width:20%;"><span >${extInfoForm.bkgain eq '1'?'获得本科学历的学习形式：':''}</span></th>
                    <td style="width:30%;">
                        <label>
                            <c:forEach items="${dictTypeEnumGyUnderStudyFormList}" var="dict">
                                ${extInfoForm.underStudyForm eq dict.dictId && extInfoForm.bkgain eq '1'?dict.dictName:''}
                            </c:forEach>
                        </label>
                    </td>
                </tr>
                <c:if test="${extInfoForm.bkgain eq '1'}">
                    <tr>
                        <th><span >本科毕业年月：</span></th>
                        <td>
                            <label>${extInfoForm.underGraduateTime }</label>
                        </td>
                        <th><span >本科毕业证书编号：</span></th>
                        <td style="word-break : break-all;">
                            <label>${extInfoForm.underDiplomaCode }</label>
                        </td>
                    </tr>
                    <tr>
                        <th><span >本科毕业单位名称：</span></th>
                        <td>
                            <label>${extInfoForm.underGraduateOrgName }</label>
                        </td>
                        <th><span >本科毕业专业名称：</span></th>
                        <td>
                            <label>${extInfoForm.underGraduateMajor }</label>
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <th><span >是否获得学士学位：</span></th>
                    <td>
                        <label>
                            <c:if test="${extInfoForm.xsgain eq '1'}">是</c:if>
                            <c:if test="${extInfoForm.xsgain eq '2'}">否</c:if>
                        </label>
                    </td>
                    <th></th>
                    <td></td>
                </tr>
                <c:if test="${extInfoForm.xsgain eq '1'}">
                    <tr>
                        <th><span >获得学士学位年月：</span></th>
                        <td>
                            <label>${extInfoForm.underAwardDegreeTime }</label>
                        </td>
                        <th><span >学士学位证书编号：</span></th>
                        <td>
                            <label>${extInfoForm.underDegreeCertCode }</label>
                        </td>
                    </tr>
                    <tr>
                        <th><span >获学士学位单位名称：</span></th>
                        <td>
                            <label>${extInfoForm.underAwardDegreeOrg }</label>
                        </td>
                        <th><span >获学士学位专业名称：</span></th>
                        <td>
                            <label>${ extInfoForm.underMajor}</label>
                        </td>
                    </tr>
                    <tr>
                        <th><span >获得学士学位学科门类：</span></th>
                        <td>
                            <label>${extInfoForm.gotBachelorCertSubject}</label>
                        </td>
                        <th></th>
                        <td></td>
                    </tr>
                </c:if>
                <c:if test="${eduUser.trainTypeId ne '1'}">
                    <tr>
                        <th><span >最后学位证编号：</span></th>
                        <td>
                            <label>${extInfoForm.code }</label>
                        </td>
                        <th></th>
                        <td></td>
                    </tr>
                </c:if>
                <c:if test="${!empty eduUser.trainTypeId && eduUser.trainTypeId > '1' }">
                    <tr>
                        <th><span >是否获得硕士研究生学历：</span></th>
                        <td>
                            <label>
                                <c:if test="${extInfoForm.ssyjsgain eq '1'}">是</c:if>
                                <c:if test="${extInfoForm.ssyjsgain eq '2'}">否</c:if>
                            </label>
                        </td>
                        <th></th>
                        <td></td>
                    </tr>
                    <c:if test="${extInfoForm.ssyjsgain eq '1'}">
                        <tr>
                            <th><span >硕士研究生毕业年月：</span></th>
                            <td>
                                <label>${extInfoForm.masterGraduateTime }</label>
                            </td>
                            <th><span >硕士研究生毕业证编号：</span></th>
                            <td>
                                <label>${extInfoForm.masterDiplomaCode }</label>
                            </td>
                        </tr>
                        <tr>
                            <th><span >硕士研究生毕业单位名称：</span></th>
                            <td>
                                <label>${extInfoForm.masterUnitName }</label>
                            </td>
                            <th><span >硕士研究生毕业专业名称：</span></th>
                            <td>
                                <label>${extInfoForm.masterGraduateMajor }</label>
                            </td>
                        </tr>
                    </c:if>

                    <tr>
                        <th><span >是否获得硕士学位：</span></th>
                        <td>
                            <label>
                                <c:if test="${extInfoForm.ssxwgain eq '1'}">是</c:if>
                                <c:if test="${extInfoForm.ssxwgain eq '2'}">否</c:if>
                            </label>
                        </td>
                        <th><span >${extInfoForm.ssxwgain eq '1'?'获得硕士学位的学习形式：':''}</span></th>
                        <td>
                            <label>
                                <c:forEach items="${dictTypeEnumGyMasterStudyFormList}" var="dict">
                                    ${extInfoForm.masterStudyForm eq dict.dictId && extInfoForm.ssxwgain eq '1'?dict.dictName:''}
                                </c:forEach>
                            </label>
                        </td>
                    </tr>
                    <c:if test="${extInfoForm.ssxwgain eq '1'}">
                        <tr>
                            <th><span >获得硕士学位年月：</span></th>
                            <td>
                                <label>${extInfoForm.masterAwardDegreeTime }</label>
                            </td>
                            <th><span >硕士学位证书编号：</span></th>
                            <td>
                                <label>${extInfoForm.masterDegreeCertCode }</label>
                            </td>
                        </tr>
                        <tr>
                            <th><span >获得硕士学位单位名称：</span></th>
                            <td>
                                <label>${extInfoForm.masterAwardDegreeOrg }</label>
                            </td>
                            <th><span >获得硕士学位学科门类：</span></th>
                            <td>
                                    ${extInfoForm.masterSubject}
                            </td>
                        </tr>
                        <tr>
                            <th><span >获得硕士学位专业：</span></th>
                            <td>
                                <label>${ extInfoForm.gotMasterCertSpe}</label>
                            </td>
                            <th></th>
                            <td></td>
                        </tr>
                    </c:if>
                </c:if>
            </table>
            <table class="gridtable" style="width:97%; margin-top: 3px; margin-bottom: 10px;">
                <tr><th colspan="99" style="text-align: center;">攻读学历</th></tr>
                <tr>
                    <th style="width: 20%"><span >预毕业时间：</span></th>
                    <td style="width: 30%">
                        ${extInfoForm.planGraduateTime }
                    </td>
                    <th style="width: 20%"><span >是否毕业：</span></th>
                    <td>
                        ${extInfoForm.graduateFlag eq '1'?'是':''}
                        ${extInfoForm.graduateFlag eq '2'?'否':''}
                    </td>
                </tr>
                <tr>
                    <th><span >毕业时间：</span></th>
                    <td>
                        ${eduUser.graduateTime }
                    </td>
                    <th><span >毕业证书编号：</span></th>
                    <td>
                        ${eduUser.diplomaCode}
                    </td>
                </tr>
                <tr>
                    <th><span >是否授予学位：</span></th>
                    <td>
                        <c:if test="${eduUser.awardDegreeFlag==GlobalConstant.FLAG_Y}">是</c:if>
                        <c:if test="${eduUser.awardDegreeFlag==GlobalConstant.FLAG_N}">否</c:if>
                    </td>
                    <th><span >授予学科门类：</span></th>
                    <td>
                        <label>${extInfoForm.awardSubjectCategory}</label>
                    </td>
                </tr>
                <tr>
                    <th><span >授予学位类别：</span></th>
                    <td>
                        ${eduUser.awardDegreeCategoryName }
                    </td>
                    <th><span >授予学位时间：</span></th>
                    <td>
                        <label>${eduUser.awardDegreeTime }</label>
                    </td>
                </tr>
                <tr>
                    <th><span >授予学位证书编号：</span></th>
                    <td>
                        ${eduUser.awardDegreeCertCode }
                    </td>
                    <th></th>
                    <td></td>
                </tr>
            </table>
            <table class="gridtable" style="width:97%; margin-top: 3px; margin-bottom: 10px;">
                <tr><th colspan="99" style="text-align: center;">论文信息</th></tr>
                <tr>
                    <th style="width:20%"><span >论文题目：</span></th>
                    <td style="width:30%">
                        ${extInfoForm.paperTitle }
                    </td>
                    <th style="width:20%"><span >论文选题来源：</span></th>
                    <td style="width:30%">
                        <c:forEach items="${dictTypeEnumGyPaperSourceList}" var="dict">
                            ${extInfoForm.paperSource eq dict.dictId?dict.dictName:''}
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <th><span >论文关键词：</span></th>
                    <td>
                        ${extInfoForm.paperKey }
                    </td>
                    <th><span >论文类型：</span></th>
                    <td>
                        <c:forEach items="${dictTypeEnumGyPaperTypeList}" var="dict">
                            ${extInfoForm.paperType eq dict.dictId?dict.dictName:''}
                        </c:forEach>
                    </td>
                </tr>
            </table>
            <table class="gridtable" style="width:97%; margin-top: 3px; margin-bottom: 10px;">
                <tr><th colspan="99" style="text-align: center;">就业信息</th></tr>
                <tr>
                    <th style="width:20%"><span >获学位后去向：</span></th>
                    <td >
                        <c:forEach items="${dictTypeEnumGyGotDegreeDirectionList}" var="dict">
                            ${extInfoForm.degreeDirection eq dict.dictId?dict.dictName:''}
                        </c:forEach>
                    </td>
                    <th></th>
                    <td></td>
                </tr>
                <tr>
                    <th><span >就业单位性质：</span></th>
                    <td>
                        <c:forEach items="${dictTypeEnumGyUnitNatureList}" var="dict">
                            ${extInfoForm.unitNature eq dict.dictId?dict.dictName:''}
                        </c:forEach>
                    </td>
                    <th></th>
                    <td></td>
                </tr>
                <tr>
                    <th style="white-space:nowrap;"><span >就业单位所在省（直辖市）：</span></th>
                    <td>
                        ${extInfoForm.unitAddress }
                    </td>
                    <th></th>
                    <td></td>
                </tr>
                <tr>
                    <th><span >工作性质：</span></th>
                    <td>
                        <c:forEach items="${dictTypeEnumGyWorkNatureList}" var="dict">
                            ${extInfoForm.workNature eq dict.dictId?dict.dictName:''}
                        </c:forEach>
                    </td>
                    <th></th>
                    <td></td>
                </tr>
                <tr>
                    <th style="width:20%"><span >就业单位名称：</span></th>
                    <td style="width:30%">
                        ${extInfoForm.unitName }
                    </td>
                    <th style="width:20%"><span >就业单位联系人：</span></th>
                    <td style="width:30%">
                        ${extInfoForm.unitLinkMan }
                    </td>
                </tr>
                <tr>
                    <th><span >就业单位联系人电话：</span></th>
                    <td>
                        ${extInfoForm.unitLinkManPhone }
                    </td>
                    <th><span >就业单位联系人邮箱：</span></th>
                    <td>
                        ${extInfoForm.unitLinkManEmail }
                    </td>
                </tr>
            </table>
        </div>
</div>
</body>
</html>