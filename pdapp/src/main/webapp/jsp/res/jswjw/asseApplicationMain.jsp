<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8" %>
{
"resultId": ${resultId},
"resultType": ${pdfn:toJsonString(resultType)}
<c:if test="${resultId eq '200' }">
    ,
    <c:if test="${'Y'eq apply}">
        <c:if test="${avgBiMap.avgComplete >= 80 and doctorRecruit.catSpeId eq 'DoctorTrainingSpe'}">
            "submitButton":"Y",
        </c:if>
        <c:if test="${doctorRecruit.catSpeId ne 'DoctorTrainingSpe' && submitBtnShow eq 'Y'}">
            "submitButton":"Y",
        </c:if>
    </c:if>
    <c:set var="applyTime" value="${'Y'eq inApplyTime }"></c:set>
    <%-- 结业申请审核状态 --%>
    <c:set var="auditStatusId" value="${not empty jsresGraduationApply.auditStatusId and (jsresGraduationApply.auditStatusId eq 'LocalNotPassed')
					or (jsresGraduationApply.auditStatusId eq 'ChargeNotPassed') or (jsresGraduationApply.auditStatusId eq 'Black')}"></c:set>
    <c:if test="${applyTime and  auditStatusId}">
        "newSubmitButton":"Y",
    </c:if>
    "doctorFlow":"${resDoctor.doctorFlow}",
    "doctorInfo":
    [{
    "label":"姓名",
    "inputId":"userName",
    "value":"${user.userName}"
    },{
    "label":"性别",
    "inputId":"userSex",
    <c:if test="${user.sexId eq 'Man'}">
        "value":"男"
    </c:if>
    <c:if test="${user.sexId eq 'Woman'}">
        "value":"女"
    </c:if>
    },{
    "label":"照片",
    "inputId":"userHeadImg",
    "type":"img",
    "value":"${upload_base_url}/${user.userHeadImg}"
    },{
    "label":"身份证类型",
    "inputId":"idNoType",
    <c:if test="${user.cretTypeId eq '01'}">
        "value":"身份证"
    </c:if>
    <c:if test="${user.cretTypeId eq '02'}">
        "value":"军官证"
    </c:if>
    <c:if test="${user.cretTypeId eq '03'}">
        "value":"港澳台身份证"
    </c:if>
    <c:if test="${user.cretTypeId eq '04'}">
        "value":"华侨身份证"
    </c:if>
    <c:if test="${user.cretTypeId eq '05'}">
        "value":"护照"
    </c:if>
    <c:if test="${user.cretTypeId eq '06'}">
        "value":"港澳居民来往内地通行证"
    </c:if>
    <c:if test="${user.cretTypeId eq '07'}">
        "value":"台湾居民来往内地通行证"
    </c:if>
    },{
    "label":"证件号",
    "inputId":"idNo",
    "value":"${user.idNo}"
    },{
    "label":"年级",
    "inputId":"sessionNumber",
    "value":"${doctorRecruit.sessionNumber}"
    },{
    "label":"结业年份",
    "inputId":"graduationYear",
    "value":"${doctorRecruit.graduationYear}"
    },{
    "label":"人员类型",
    "inputId":"doctorTypeName",
    "value":"${resDoctor.doctorTypeName}"
    },{
    "label":"培训基地",
    "inputId":"orgName",
    "value":"${doctorRecruit.orgName}"
    },{
    "label":"培训类别",
    "inputId":"speName",
    "value":"${doctorRecruit.speName}"
    },{
    "label":"学位",
    "inputId":"educationId",
    <c:if test="${user.educationId eq '191'}">"value":"无学位"</c:if>
    <c:if test="${user.educationId ne '191'}">
        <c:choose>
            <c:when test="${ not empty userResumeExt.doctorDegreeName}">
                "value":"${ userResumeExt.doctorDegreeName}"
            </c:when>
            <c:when test="${ not empty userResumeExt.masterDegreeName}">
                "value":"${userResumeExt.masterDegreeName}"
            </c:when>
            <c:otherwise>
                "value":"${userResumeExt.degreeName}"
            </c:otherwise>
        </c:choose>
    </c:if>
    },{
    "label":"联系方式",
    "inputId":"userPhone",
    "value":"${user.userPhone}"
    }
    ],
    "examinationInfo":[
    {
    "label":"学历",
    "inputId":"educationName",
    "value":"${user.educationName}"
    },{
    "label":"毕业证书编号",
    "inputId":"certificateNo",
    "value":"${resDoctor.certificateNo}"
    },{
    "label":"培训开始时间",
    "inputId":"startDate",
    "value":"${startDate}"
    },{
    "label":"培训结束时间",
    "inputId":"endDate",
    "value":"${endDate}"
    },{
    "label":"报考资格材料",
    "inputId":"graduationMaterialName",
    "value":"${practicingMap.graduationMaterialName}"
    },{
    "label":"报考资格材料编码",
    "inputId":"graduationMaterialCode",
    "value":"${practicingMap.graduationMaterialCode}"
    },{
    "label":"取得资格时间",
    "inputId":"graduationMaterialTime",
    "value":"${practicingMap.graduationMaterialTime}"
    },{
    "label":"执业类型",
    "inputId":"graduationCategory",
    <c:forEach items="${dictTypeEnumPracticeTypeList}" var="dictTypeEnumPracticeType">
        <c:if test='${practicingMap.graduationCategoryId eq dictTypeEnumPracticeType.dictId}'>
            "value":"${dictTypeEnumPracticeType.dictName}"
        </c:if>
    </c:forEach>
    <c:if test="${practicingMap.graduationCategoryId eq '暂无'}">
        "value":"暂无"
    </c:if>
    },{
    "label":"执业范围",
    "inputId":"practiceArea",
    <c:forEach items="${dictTypeEnumPracticeTypeList}" var="dict">
        <c:set var="dictKey" value="dictTypeEnumPracticeType.${dict.dictId}List"/>
        <c:forEach items="${applicationScope[dictKey]}" var="scope">
            <c:if test='${ practicingMap.graduationScopeId eq scope.dictId and dict.dictId eq practicingMap.graduationCategoryId}'>
                "value":"${scope.dictName}"
            </c:if>
        </c:forEach>
    </c:forEach>
    <c:if test="${practicingMap.graduationScopeId eq '暂无'}">
        "value":"暂无"
    </c:if>
    },{
    "label":"培训年限",
    "inputId":"trainYear",
    <c:if test="${'OneYear' eq doctorRecruit.trainYear}">
        "value":"一年"
    </c:if>
    <c:if test="${'TwoYear' eq doctorRecruit.trainYear}">
        "value":"两年"
    </c:if>
    <c:if test="${'ThreeYear' eq doctorRecruit.trainYear}">
        "value":"三年"
    </c:if>
    },{
    "label":"减免附件",
    "inputId":"proveFileUrl",
    "type":"img",
    <c:if test="${'TwoYear' eq doctorRecruit.trainYear ||  'OneYear' eq doctorRecruit.trainYear}">
        <c:if test="${'DoctorTrainingSpe' eq doctorRecruit.catSpeId}">
            <c:if test="${not empty doctorRecruit.proveFileUrl}">
                "value":"${upload_base_url}/${doctorRecruit.proveFileUrl}"
            </c:if>
        </c:if>
        <c:if test="${'DoctorTrainingSpe' ne doctorRecruit.catSpeId}">
            "value":"无减免证明"
        </c:if>
    </c:if>
    },{
    "label":"培训登记手册完成情况",
    "inputId":"registeManua",
    "type":"select",
    <c:if test="${not empty userResumeExt.registeManua and userResumeExt.registeManua == 1}">
        "value":"已完成"
    </c:if>
    <c:if test="${not empty userResumeExt.registeManua and userResumeExt.registeManua == 0}">
        "value":"未完成"
    </c:if>
    <c:if test="${empty userResumeExt.registeManua}">
        "value":"请选择"
    </c:if>
    },{
    "label":"实际轮转时间",
    "inputId":"allMonth",
    "value":"${allMonth}"
    },{
    "label":"数据填写比例",
    "inputId":"avgComplete",
    "value":"${avgBiMap.avgComplete}"
    },{
    "label":"数据审核比例",
    "inputId":"avgAudit",
    "value":"${avgBiMap.avgAudit}"
    },{
    "label":"是否军队人员",
    "inputId":"armyType",
    <c:if test="${empty userResumeExt.armyType}">
        "value":"未填写,请到培训信息完善！"
    </c:if>
    <c:if test="${not empty userResumeExt.armyType}">
        <c:if test="${userResumeExt.armyType eq 'NO'}">
            "value":"否"
        </c:if>
        <c:if test="${userResumeExt.armyType eq 'ActiveArmy'}">
            "value":"现役军人"
        </c:if>
        <c:if test="${userResumeExt.armyType eq 'CivilPersonnel'}">
            "value":"文职人员"
        </c:if>
        <c:if test="${userResumeExt.armyType eq 'MilitaryAcademy'}">
            "value":"军队院校研究生"
        </c:if>
    </c:if>
    },{
    "label":"受疫情影响未完成的培训",
    "inputId":"remark",
    "type":"textArea",
    "value":"${jsresGraduationApply.remark}"
    }
    ],
    "publicCourseScore":[
    {
    "label":"卫生法律和法规",
    "inputId":"lawScore",
    "type":"edit",
    "value":"${extScore.lawScore}"
    },{
    "label":"循证医学",
    "inputId":"medicineScore",
    "type":"edit",
    "value":"${extScore.medicineScore}"
    },{
    "label":"临床思维与人际沟通",
    "inputId":"clinicalScore",
    "type":"edit",
    "value":"${extScore.clinicalScore}"
    },{
    "label":"重点传染病防治知识",
    "inputId":"ckScore",
    "type":"edit",
    "value":"${extScore.ckScore}"
    },{
    "label":"是否合格",
    "inputId":"isQualified",
    "value":"以上传附件为准"
    }
    ],
    "theoryScore":"${publicScore.theoryScore}",
    "recruitFlow":"${recruitFlow}",

    "examinationMaterials":[{
    "label":"毕业证书",
    "inputId":"certificateUri",
    "type":"img",
    <c:if test="${not empty userResumeExt.certificateUri}">
        "value":"${upload_base_url}/${userResumeExt.certificateUri}"
    </c:if>
    <c:if test="${empty userResumeExt.certificateUri}">
        "value":"暂未上传"
    </c:if>
    },
    <c:if test="${userResumeExt.isPassQualifyingExamination eq 	'N'}">
        {
        "label":"医师执业证书",
        "inputId":"certificateUriTwo",
        "type":"img",
        <c:if test="${'暂无' ne practicingMap.graduationMaterialUri}">
            <c:if test="${not empty practicingMap.graduationMaterialUri}">
                "value":"${upload_base_url}/${practicingMap.graduationMaterialUri}"
            </c:if>
            <c:if test="${empty practicingMap.graduationMaterialUri}">
                "value":"暂未上传"

            </c:if>
        </c:if>
        <c:if test="${'暂无' eq practicingMap.graduationMaterialUri}">
            "value":"暂未上传"
        </c:if>
        },
    </c:if>

    <c:if test="${userResumeExt.isPassQualifyingExamination eq 	'Y' }">
        <c:if test="${userResumeExt.isHaveQualificationCertificate eq 	'N' }">
            {
            "label":"成绩单",
            "inputId":"certificateUriThree",
            "type":"img",
            <c:if test="${not  empty userResumeExt.qualificationMaterialId2Url}">
                "value":"${upload_base_url}/${userResumeExt.qualificationMaterialId2Url}"
            </c:if>
            <c:if test="${empty userResumeExt.qualificationMaterialId2Url}">
                "value":"暂未上传"
            </c:if>
            },
        </c:if>
    </c:if>
    <c:if test="${userResumeExt.isPassQualifyingExamination eq 	'Y'}">
        <c:if test="${userResumeExt.isHaveQualificationCertificate eq 	'Y' }">
            <c:if test="${userResumeExt.isHavePracticingCategory eq 	'N' }">
                {
                "label":"医师资格证书",
                "inputId":"certificateUriTwo",
                "type":"img",
                <c:if test="${not  empty userResumeExt.doctorQualificationCertificateUrl}">
                    "value":"${upload_base_url}/${userResumeExt.doctorQualificationCertificateUrl}"
                </c:if>
                <c:if test="${empty userResumeExt.doctorQualificationCertificateUrl}">
                    "value":"暂未上传"
                </c:if>
                },
            </c:if>
        </c:if>
    </c:if>
    <c:if test="${userResumeExt.isPassQualifyingExamination eq 	'Y' }">
        <c:if test="${userResumeExt.isHaveQualificationCertificate eq 	'Y' }">
            <c:if test="${userResumeExt.isHavePracticingCategory eq 	'Y' }">
                {
                "label":"医师执业证书",
                "inputId":"certificateUriFive",
                "type":"img",
                <c:if test="${not  empty userResumeExt.doctorPracticingCategoryUrl}">
                    "value":"${upload_base_url}/${userResumeExt.doctorPracticingCategoryUrl}"
                </c:if>
                <c:if test="${empty userResumeExt.doctorPracticingCategoryUrl}">
                    "value":"暂未上传"
                </c:if>
                },
            </c:if>
        </c:if>
    </c:if>
    {
    "label":"公共科目成绩",
    "inputId":"filePath",
    "type":"img",
    <c:if test="${not empty file.filePath}">
        "value":"${upload_base_url}/${file.filePath}"
<%--        "value":""--%>
    </c:if>
    },
    {
    "label":"重新上传",
    "inputId":"filePathUpload",
    <c:if test="${not empty file.filePath}">
        <c:if test="${empty jsresGraduationApply}">
            "value":"重新上传"
        </c:if>
        <c:if test="${not empty jsresGraduationApply}">
            <c:if test="${jsresGraduationApply.auditStatusId eq 'LocalNotPassed' or
								jsresGraduationApply.auditStatusId eq 'ChargeNotPassed' or
							jsresGraduationApply.auditStatusId eq 'GlobalNotPassed'	}">
                "value":"重新上传"
            </c:if>
        </c:if>
    </c:if>
    }
    ],
    <c:if test="${not empty jsresGraduationApply.localAuditStatusId || not empty jsresGraduationApply.cityAuditStatusId
			||not empty jsresGraduationApply.globalAuditStatusId}">
        "auditOpinion":[{
        "label":"基地意见",
        "inputId":"localReason",
        <c:if test="${not empty jsresGraduationApply.localAuditStatusId}">
            <c:if test="${not empty jsresGraduationApply.localReason}">
                "value":"${jsresGraduationApply.localReason}"
            </c:if>
        </c:if>
        <c:if test="${empty jsresGraduationApply.localAuditStatusId}">
            "value":""
        </c:if>
        <c:if test="${not empty jsresGraduationApply.localAuditStatusId}">
            <c:if test="${empty jsresGraduationApply.localReason}">
                "value":""
            </c:if>
        </c:if>
        },{
        "label":"市局意见",
        "inputId":"cityReason",
        <c:if test="${not empty jsresGraduationApply.cityAuditStatusId}">
            <c:if test="${not empty jsresGraduationApply.cityReason}">
                "value":"${jsresGraduationApply.cityReason}"
            </c:if>
        </c:if>
        <c:if test="${empty jsresGraduationApply.cityAuditStatusId}">
            "value":""
        </c:if>
        <c:if test="${not empty jsresGraduationApply.cityAuditStatusId}">
            <c:if test="${empty jsresGraduationApply.cityReason}">
                "value":""
            </c:if>
        </c:if>
        },{
        "label":"省厅意见",
        "inputId":"globalReason",
        <c:if test="${not empty jsresGraduationApply.globalAuditStatusId}">
            <c:set var="k" value="${jsresGraduationApply.testId}_asse_application"/>
            <c:choose>
                <c:when test="${ sysCfgMap[k] eq 'Y'}">
                    <c:if test="${not empty jsresGraduationApply.globalReason}">
                        "value":"${jsresGraduationApply.globalReason}"
                    </c:if>
                </c:when>
            </c:choose>
        </c:if>
        <c:if test="${empty jsresGraduationApply.globalAuditStatusId}">
            "value":""
        </c:if>
        <c:if test="${not empty jsresGraduationApply.globalAuditStatusId}">
            <c:set var="k" value="${jsresGraduationApply.testId}_asse_application"/>
            <c:choose>
                <c:when test="${ sysCfgMap[k] eq 'Y'}">
                    <c:if test="${empty jsresGraduationApply.globalReason}">
                        "value":""
                    </c:if>
                </c:when>
                <c:otherwise >
                    "value":"省厅审核中"
                </c:otherwise>
            </c:choose>
        </c:if>
        }],
    </c:if>

    "dataList":[
    <c:set value="" var="lastGroupFlow"></c:set>
    <c:set value="" var="lastDeptFlow"></c:set>
    <c:set value="0" var="allSchMonth"></c:set>
    <c:set value="0" var="allRealMonth"></c:set>
    <c:forEach items="${groupList}" var="group" varStatus="groupStatus">
        {
        "rotationType":"<c:if test="${rotation.isStage eq GlobalConstant.FLAG_Y}">${group.schStageName}</c:if>${group.groupName}【${dept.isRequired == GlobalConstant.FLAG_N?"选科":"必轮"}】 (轮转时间：${group.schMonth}月<c:if test="${group.isRequired eq GlobalConstant.FLAG_N}">${group.selTypeName}：${group.deptNum}<c:if test="${group.selTypeId eq schSelTypeEnumFree.id}">~${group.maxDeptNum}</c:if></c:if>)",
        "rotationList":[
        <c:forEach items="${rotationDeptMap[group.groupFlow]}" var="dept" varStatus="deptStatus">
            <c:set var="imagelist" value="${afterImgMap[dept.recordFlow]}"></c:set>
            {
            <c:set value="${allSchMonth+0+dept.schMonth}" var="allSchMonth"></c:set>
            <c:set var="resultKey" value="${group.groupFlow}${dept.standardDeptId}"/>
            <c:set value="${allRealMonth+0+(empty realMonthMap[resultKey] ? 0 : realMonthMap[resultKey])}" var="allRealMonth"></c:set>
            <c:set var="bi" value="${biMap[dept.recordFlow]}"></c:set>
            <c:if test="${not empty resultMap[resultKey]}">
                <c:forEach items="${resultMap[resultKey]}" var="result" varStatus="resultStatus">
                    <c:choose>
                        <c:when test="${!(lastGroupFlow eq group.groupFlow) and
    								!(lastDeptFlow eq dept.recordFlow) and resultStatus.first
    								}">
                            "standardDeptName":"${dept.standardDeptName}",
                            "schMonth":"${dept.schMonth}/${empty realMonthMap[resultKey] ? 0 : realMonthMap[resultKey]}",
                            "schDeptName":"${result.schDeptName}",
                            "schDate":"${result.schStartDate}~${result.schEndDate}",
                            <c:forEach var="image" items="${imagelist}" varStatus="status">
                                "imageUrl":"${image.imageUrl}",
                            </c:forEach>
                            "completeBi":"${empty bi.completeBi ? 0: bi.completeBi}${bi.completeBi=='-' ? '':'%'}",
                            "outCompleteBi":"${empty bi.outCompleteBi ? 0: bi.outCompleteBi}${bi.outCompleteBi=='-' ? '':'%'}",
                            "auditBi":"${empty bi.auditBi ? 0:bi.auditBi}${bi.auditBi=='-' ? '':'%'}"
                        </c:when>
                        <c:when test="${(lastGroupFlow eq group.groupFlow) and
    								!(lastDeptFlow eq dept.recordFlow) and resultStatus.first
    								}">
                            "standardDeptName":"${dept.standardDeptName}",
                            "schMonth":"${dept.schMonth}/${empty realMonthMap[resultKey] ? 0 : realMonthMap[resultKey]}",
                            "schDeptName":"${result.schDeptName}",
                            "schDate":"${result.schStartDate}~${result.schEndDate}",
                                <c:forEach var="image" items="${imagelist}" varStatus="status">
                                    "imageUrl":"${image.imageUrl}",
                                </c:forEach>
                            "completeBi":"${empty bi.completeBi ? 0: bi.completeBi}${bi.completeBi=='-' ? '':'%'}",
                            "outCompleteBi":"${empty bi.outCompleteBi ? 0: bi.outCompleteBi}${bi.outCompleteBi=='-' ? '':'%'}",
                            "auditBi":"${empty bi.auditBi ? 0:bi.auditBi}${bi.auditBi=='-' ? '':'%'}"
                        </c:when>
                        <c:when test="${(lastGroupFlow eq group.groupFlow) and
    								(lastDeptFlow eq dept.recordFlow) and !(resultStatus.first)}">
                            ,
                            "schDeptName":"${result.schDeptName}",
                            "schDate":"${result.schStartDate}~${result.schEndDate}"
                        </c:when>
                    </c:choose>
                    <c:set value="${group.groupFlow}" var="lastGroupFlow"></c:set>
                    <c:set value="${dept.recordFlow}" var="lastDeptFlow"></c:set>
                </c:forEach>
            </c:if>
            <c:if test="${empty resultMap[resultKey]}">
                "standardDeptName":"${dept.standardDeptName}",
                "schMonth":"${dept.schMonth}/0",
                <c:set var="imagelist" value="${afterImgMap[dept.recordFlow]}"></c:set>
                    <c:forEach var="image" items="${imagelist}" varStatus="status">
                        "imageUrl":"${image.imageUrl}",
                    </c:forEach>
                "completeBi":"${empty bi.completeBi ? 0: bi.completeBi}${bi.completeBi=='-' ? '':'%'}",
                "outCompleteBi":"${empty bi.outCompleteBi ? 0: bi.outCompleteBi}${bi.outCompleteBi=='-' ? '':'%'}",
                "auditBi":"${empty bi.auditBi ? 0:bi.auditBi}${bi.auditBi=='-' ? '':'%'}"
            </c:if>

            <c:set value="${group.groupFlow}" var="lastGroupFlow"></c:set>
            <c:set value="${dept.recordFlow}" var="lastDeptFlow"></c:set>
            }
            <c:if test="${not deptStatus.last}">
                ,
            </c:if>
        </c:forEach>
        ]
        },
    </c:forEach>
    {
    "allSchMonth":"${allSchMonth}/${allRealMonth}",
    "completeBiAvg":"${empty avgBiMap.avgComplete?0:avgBiMap.avgComplete}%",
    "outCompleteBiAvg":"${empty avgBiMap.avgOutComplete ? 0:avgBiMap.avgOutComplete}%",
    "avgAuditBiAvg":"${empty avgBiMap.avgAudit?0:avgBiMap.avgAudit}%",
    "over":"over"
    }
    ]



</c:if>
}