<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>

<c:set var="isRead" value="${!(info.auditStatusId eq 'Apply' or info.auditStatusId eq 'UnQualified' or empty info.auditStatusId)}"></c:set>
<c:if test="${info.auditStatusId eq 'Apply' or info.auditStatusId eq 'UnQualified' or empty info.auditStatusId}">
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
                "inputId":"studyStartDate",
                "label":"跟师学习时间：",
                "img":"calendar",
                "inputType":"date",
                "verify": {
                    "dateFmt": "yyyy年MM月dd日",
                    "type": "date"
                },
                "readonly":${isRead},
                "required":true,
                "value":${pdfn:toJsonString(info.studyStartDate)}
            },
            {
                "inputId":"doctorFlow",
                "label":"跟师规培学员标识符：",
                "inputType":"hidden",
                "readonly":true,
                "required":true,
                "value":${pdfn:toJsonString(empty info.doctorFlow?user.userFlow:info.doctorFlow)}
            },
            {
                "inputId":"doctorName",
                "label":"跟师规培学员姓名：",
                "inputType":"text",
                "verify": {
                    "length": "50",
                    "type": "text"
                },
                "readonly":${isRead},
                "required":true,
                "value":${pdfn:toJsonString(empty info.doctorName?user.userName:info.doctorName)}
            },
            {
                "inputId":"teacherFlow",
                "label":"师承指导老师标识符：",
                "inputType":"hidden",
                "readonly":true,
                "required":true,
                "value":${pdfn:toJsonString(empty info.teacherFlow?doctor.discipleTeacherFlow:info.teacherFlow)}
            },
            {
                "inputId":"teacherName",
                "label":"师承指导老师姓名：",
                "inputType":"text",
                "verify": {
                    "length": "50",
                    "type": "text"
                },
                "readonly":${isRead},
                "required":true,
                "value":${pdfn:toJsonString(empty info.teacherName?tea.userName:info.teacherName)}
            },
            {
                "inputId":"studyContent",
                "label":"跟师临证（实践）主要病种（内容）：",
                "placeholder":"",
                "inputType":"textarea",
                "verify": {
                    "length": 2000,
                    "type": "textarea"
                },
                "readonly":${isRead},
                "required":true,
                "value":${pdfn:toJsonString(info.studyContent)}
            },
            {
                "inputId":"experienceContent",
                "label":"跟师心得体会（要求理论联系实际，不少于500字）：",
                "placeholder":"",
                "inputType":"textarea",
                "verify": {
                    "length": 2000,
                    "min-length": 500,
                    "type": "textarea"
                },
                "readonly":${isRead},
                "required":true,
                "value":${pdfn:toJsonString(info.experienceContent)}
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
                "value":${pdfn:toJsonString(user.userName)}
            },
<c:set var="currDate" value="${pdfn:getCurrDate()}"/>
            {
                "inputId":"studentSignTime",
                "label":"学员签名日期：",
                "inputType":"text",
                "readonly":true,
                "required":true,
                "value":${pdfn:toJsonString(empty info.studentSignTime? currDate:pdfn:transDate(info.studentSignTime))}
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
            <c:if test="${info.auditStatusId eq 'Qualified' or  info.auditStatusId eq 'UnQualified'}">
             ,
            {
                "inputId":"auditContent",
                "label":"师承指导老师批阅意见：",
                "inputType":"textarea",
                "verify": {
                    "length": 2000,
                    "type": "textarea"
                },
                "readonly":true,
                "required":true,
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
]