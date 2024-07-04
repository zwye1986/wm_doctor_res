<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="isRead" value="${!(info.auditStatusId eq 'Apply' or empty info.auditStatusId
                                            or 'DiscipleBack' eq info.auditStatusId
                                            or 'AdminBack' eq info.auditStatusId)}"></c:set>

<c:set var="isShowSelect" value="${(info.auditStatusId eq 'AdminAudit'or info.auditStatusId eq 'Submit'or info.auditStatusId eq 'DiscipleAudit')}" />
<c:if test="${info.auditStatusId eq 'Apply' or empty info.auditStatusId
                                            or 'DiscipleBack' eq info.auditStatusId
                                            or 'AdminBack' eq info.auditStatusId}">
    "action":{
         "save":"保存",
         "submit":"提交",
    },
</c:if>
"datas":[
            {
                "inputId":"recordFlow",
                "label":"流水号",
                "inputType":"hidden",
                "readonly":true,
                "required":true,
                "value":${pdfn:toJsonString(recordFlow)}
            },
            {
                "inputId":"recordYear",
                "label":"年度：",
                "img":"calendar",
                "inputType":"date",
                "verify": {
                    "dateFmt": "yyyy",
                    "type": "date"
                },
                "readonly":true,
                "required":true,
                "value":${pdfn:toJsonString(info.recordYear)}
            },
            {
                "inputId":"doctorFlow",
                "label":"跟师规培学员标识符：",
                "inputType":"hidden",
                "readonly":true,
                "required":true,
                "value":${pdfn:toJsonString(info.doctorFlow)}
            },
            {
                "inputId":"doctorName",
                "label":"跟师规培学员姓名：",
                "inputType":"text",
                "verify": {
                    "length": "50",
                    "type": "text"
                },
                "readonly":true,
                "required":true,
                "value":${pdfn:toJsonString(empty info.doctorName?user.userName:info.doctorName)}
            },
            {
                "inputId":"teacherName",
                "label":"师承指导老师姓名：",
                "inputType":"text",
                "verify": {
                    "length": "50",
                    "type": "text"
                },
                "readonly":true,
                "required":true,
                "value":${pdfn:toJsonString(empty info.teacherName?info.discipleTeacherName:info.teacherName)}
            },
            {
                "inputId":"teacherFlow",
                "label":"师承指导老师标识符：",
                "inputType":"hidden",
                "readonly":true,
                "required":true,
                "value":${pdfn:toJsonString(empty info.teacherFlow?info.discipleTeacherFlow:info.teacherFlow)}
            },
            <c:if test="${isShowSelect}">
                <c:set var="discipleTime" value="${pdfn:transDateTimeForPattern(info.studyStartDate,'yyyy-MM-dd','yyyy')}年${pdfn:transDateTimeForPattern(info.studyStartDate,'yyyy-MM-dd','MM')}月${pdfn:transDateTimeForPattern(info.studyStartDate,'yyyy-MM-dd','dd')}日——${pdfn:transDateTimeForPattern(info.studyEndDate,'yyyy-MM-dd','yyyy')}年${pdfn:transDateTimeForPattern(info.studyEndDate,'yyyy-MM-dd','MM')}月${pdfn:transDateTimeForPattern(info.studyEndDate,'yyyy-MM-dd','dd')}日"></c:set>
            </c:if>
            <c:if test="${!isShowSelect}">
                <c:set var="discipleTime" value="${pdfn:transDateTimeForPattern(info.discipleStartDate,'yyyy-MM-dd','yyyy')}年${pdfn:transDateTimeForPattern(info.discipleStartDate,'yyyy-MM-dd','MM')}月${pdfn:transDateTimeForPattern(info.discipleStartDate,'yyyy-MM-dd','dd')}日——${pdfn:transDateTimeForPattern(info.discipleEndDate,'yyyy-MM-dd','yyyy')}年${pdfn:transDateTimeForPattern(info.discipleEndDate,'yyyy-MM-dd','MM')}月${pdfn:transDateTimeForPattern(info.discipleEndDate,'yyyy-MM-dd','dd')}日"></c:set>
            </c:if>
            {
                "inputId":"discipleTime",
                "label":"跟师时间：",
                "placeholder":"",
                "inputType":"text",
                "verify": {
                    "length": 2000,
                    "type": "text"
                },
                "readonly":true,
                "required":false,
                "value":${pdfn:toJsonString(discipleTime)}
            },
            {
                "inputId":"studyStartDate",
                "label":"跟师开始时间：",
                "inputType":"hidden",
                "readonly":true,
                "required":true,
                "value":${pdfn:toJsonString(info.discipleStartDate)}
            },
            {
                "inputId":"studyEndDate",
                "label":"跟师结束时间：",
                "inputType":"hidden",
                "readonly":true,
                "required":true,
                "value":${pdfn:toJsonString(info.discipleEndDate)}
            },
            {
                "inputId":"dayCount",
                "label":"跟师天数：",
                "placeholder":"",
                "inputType":"text",
                "verify": {
                    "length": 10,
                    "type": "text"
                },
                "readonly":true,
                "required":true,
                <c:if test="${!isShowSelect}">
                    "value":${pdfn:toJsonString(info.daySelectCount)}
                </c:if>
                <c:if test="${isShowSelect}">
                    "value":${pdfn:toJsonString(info.dayCount)}
                </c:if>
            },
            {
                "inputId":"group1",
                "keylabel":"完成项目类型",
                "valuelabel":"完成数量",
                "placeholder":"",
                "inputType":"group",
                "items":[
                    {
                        "inputId":"noteCount",
                        "label":"跟师笔记",
                        "placeholder":"",
                        "inputType":"text",
                        "verify": {
                            "length": 10,
                            "type": "text"
                        },
                        "readonly":true,
                        "required":true,
                        <c:if test="${!isShowSelect}">
                            "value":${pdfn:toJsonString(info.noteSelectCount)}
                        </c:if>
                        <c:if test="${isShowSelect}">
                            "value":${pdfn:toJsonString(info.noteCount)}
                        </c:if>
                    },
                    {
                        "inputId":"experienceCount",
                        "label":"跟师心得",
                        "placeholder":"",
                        "inputType":"text",
                        "verify": {
                            "length": 10,
                            "type": "text"
                        },
                        "readonly":true,
                        "required":true,
                        <c:if test="${!isShowSelect}">
                            "value":${pdfn:toJsonString(info.experienceSelectCount)}
                        </c:if>
                        <c:if test="${isShowSelect}">
                            "value":${pdfn:toJsonString(info.experienceCount)}
                        </c:if>
                    },
                    {
                        "inputId":"tcmCount",
                        "label":"中医经典学习",
                        "placeholder":"",
                        "inputType":"text",
                        "verify": {
                            "length": 10,
                            "type": "text"
                        },
                        "readonly":true,
                        "required":true,
                        <c:if test="${!isShowSelect}">
                            "value":${pdfn:toJsonString(info.bookStudyCount)}
                        </c:if>
                        <c:if test="${isShowSelect}">
                            "value":${pdfn:toJsonString(info.tcmCount)}
                        </c:if>
                    },
                    {
                        "inputId":"xxthCount",
                        "label":"经典学习体会",
                        "placeholder":"",
                        "inputType":"text",
                        "verify": {
                            "length": 10,
                            "type": "text"
                        },
                        "readonly":true,
                        "required":true,
                        <c:if test="${!isShowSelect}">
                            "value":${pdfn:toJsonString(info.bookexpSelectCount)}
                        </c:if>
                        <c:if test="${isShowSelect}">
                            "value":${pdfn:toJsonString(info.xxthCount)}
                        </c:if>
                    },
                    {
                        "inputId":"typicalCasesCount",
                        "label":"典型医案",
                        "placeholder":"",
                        "inputType":"text",
                        "verify": {
                            "length": 10,
                            "type": "text"
                        },
                        "readonly":true,
                        "required":true,
                        <c:if test="${!isShowSelect}">
                            "value":${pdfn:toJsonString(info.casesCount)}
                        </c:if>
                        <c:if test="${isShowSelect}">
                            "value":${pdfn:toJsonString(info.typicalCasesCount)}
                        </c:if>
                    }
                ]
            },

            <c:set var="imgUrl" value="${uploadPath}/${info.assessmentImgUrl}"></c:set>
            <c:set var="imgName" value="查看"></c:set>
            <c:if test="${empty info.assessmentImgUrl}">
                <c:set var="imgUrl" value=""></c:set>
                <c:set var="imgName" value="尚未打分，暂时无法查看"></c:set>
            </c:if>
            {
                "inputId":"assessmentImgUrl",
                "label":"跟师学习考核打分表：",
                "name":${pdfn:toJsonString(imgName)},
                "inputType":"img",
                "readonly":true,
                "required":false,
                "value":${pdfn:toJsonString(imgUrl)}
            },
            {
                "inputId":"compleleContent",
                "label":"培养计划完成情况（如未完成，需说明理由）：",
                "placeholder":"",
                "inputType":"textarea",
                "verify": {
                    "length": 2000,
                    "min-length": 1000,
                    "type": "textarea"
                },
                "readonly":${isRead},
                "required":true,
                "value":${pdfn:toJsonString(info.compleleContent)}
            },
            {
                "inputId":"doctorSignName",
                "label":"跟师规培学员签名：",
                "inputType":"text",
                "verify": {
                    "length": "50",
                    "type": "text"
                },
                "readonly":true,
                "required":true,
                "value":${pdfn:toJsonString(info.doctorName)}
            },
            <c:set var="currDate" value="${pdfn:getCurrDate()}"/>
            {
                "inputId":"compleleSignTime",
                "label":"学员签名日期：",
                "inputType":"text",
                "readonly":true,
                "required":true,
                "value":${pdfn:toJsonString(pdfn:transDate(empty info.compleleSignTime?currDate:info.compleleSignTime))}
            },
            {
                "inputId":"experienceContent",
                "label":"主要继承成绩（包括典型病案收集，老师经验整理，经典学习，跟师心得，论文（著）发表/出版情况及科研情况等）：",
                "placeholder":"",
                "inputType":"textarea",
                "verify": {
                    "length": 2000,
                    "min-length": 1000,
                    "type": "textarea"
                },
                "readonly":${isRead},
                "required":true,
                "value":${pdfn:toJsonString(info.experienceContent)}
            },
            {
                "inputId":"doctorSignName2",
                "label":"跟师规培学员签名：",
                "inputType":"text",
                "verify": {
                    "length": "50",
                    "type": "text"
                },
                "readonly":true,
                "required":false,
                "value":${pdfn:toJsonString(info.doctorName)}
            },
            <c:set var="currDate" value="${pdfn:getCurrDate()}"/>
            {
                "inputId":"experienceSignTime",
                "label":"学员签名日期：",
                "inputType":"text",
                "readonly":true,
                "required":false,
                "value":${pdfn:toJsonString(pdfn:transDate(empty info.experienceSignTime?currDate:info.experienceSignTime))}
            },
            {
                "inputId":"files",
                "label":"附件：",
                "inputType":"files",
                "readonly":true,
                "required":false,
                "items":[
                    <c:forEach items="${discipleFiles}" var="discipleFile" varStatus="s">
                        {
                            "inputId":${pdfn:toJsonString(discipleFile.fileFlow)},
                            "label":"",
                            "name":${pdfn:toJsonString(discipleFile.fileName)},
                            "content":${pdfn:toJsonString(discipleFile.fileRemark)},
                            "inputType":"img",
                            "readonly":true,
                            "required":false,
                            <c:set value="${uploadPath}/${discipleFile.filePath}" var="filePath"></c:set>
                            "value":${pdfn:toJsonString(filePath)}
                        }
                        <c:if test="${!s.last}">
                            ,
                        </c:if>
                    </c:forEach>
                ]
            }
            <c:if test="${not empty info.auditContent}">
             ,
            {
                "inputId":"auditContent",
                "label":"指导老师对规培学员本年度学习情况的评语（对跟师规培学员学习态度、工作态度、跟师效果等作出评议）：",
                "inputType":"textarea",
                "verify": {
                    "length": 2000,
                    "type": "textarea"
                },
                "readonly":true,
                "required":false,
                "value":${pdfn:toJsonString(info.auditContent)}
            },
                <c:if test="${siginUrlisHave eq 'Y'}">
                    {
                        "inputId":"teacherSignName",
                        "label":"师承签名：",
                        "inputType":"img",
                        "readonly":true,
                        "required":false,
                        "value":${pdfn:toJsonString(siginUrl)}
                    },
                </c:if>
                <c:if test="${siginUrlisHave ne 'Y'}">
                    {
                        "inputId":"teacherSignName",
                        "label":"师承签名：",
                        "inputType":"text",
                        "verify": {
                            "length": "50",
                            "type": "text"
                        },
                        "readonly":true,
                        "required":false,
                        "value":${pdfn:toJsonString(tea.userName)}
                    },
                </c:if>
            {
                "inputId":"auditTime",
                "label":"师承签名日期：",
                "inputType":"text",
                "readonly":true,
                "required":true,
                "value":${pdfn:toJsonString(empty pdfn:transDate(info.auditTime) ? currDate : pdfn:transDate(info.auditTime))}
            }
            </c:if>
            <c:if test="${not empty info.auditContent}">
             ,
            {
                "inputId":"adminContent",
                "label":"单位考核意见：（包括：本年度继承教学实施情况的总体评价，对跟师学员的学习态度、工作态度、跟师教学质量和效果等作出评议，并按照跟师学习考核打分表对年度考核是否合格提出明确意见。）：",
                "inputType":"textarea",
                "verify": {
                    "length": 2000,
                    "type": "textarea"
                },
                "readonly":true,
                "required":false,
                "value":${pdfn:toJsonString(info.adminContent)}
            },
                <c:if test="${siginUrl2isHave eq 'Y'}">
                    {
                        "inputId":"adminSignName",
                        "label":"负责人签名：",
                        "inputType":"img",
                        "readonly":true,
                        "required":false,
                        "value":${pdfn:toJsonString(siginUrl2)}
                    },
                </c:if>
                <c:if test="${siginUrl2isHave ne 'Y'}">
                    {
                        "inputId":"adminUserName",
                        "label":"负责人签名：",
                        "inputType":"text",
                        "verify": {
                            "length": "50",
                            "type": "text"
                        },
                        "readonly":true,
                        "required":false,
                        "value":${pdfn:toJsonString(info.adminUserName)}
                    },
                </c:if>
            {
                "inputId":"adminTime",
                "label":"签名日期：",
                "inputType":"text",
                "readonly":true,
                "required":true,
                "value":${pdfn:toJsonString(empty pdfn:transDate(info.adminTime) ? currDate : pdfn:transDate(info.adminTime))}
            }
            </c:if>
]