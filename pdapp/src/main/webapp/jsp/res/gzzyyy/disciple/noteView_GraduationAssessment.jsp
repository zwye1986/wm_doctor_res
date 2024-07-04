<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="isRead" value="${!(info.auditStatusId != 'AdminAudit' and info.auditStatusId !='DiscipleAudit'
			and info.auditStatusId !='Submit'	)}"></c:set>
<c:set var="isShowSelect"  value="${(info.auditStatusId eq 'AdminAudit')}" />
<c:if test="${(info.auditStatusId != 'AdminAudit' and info.auditStatusId !='DiscipleAudit'
			and info.auditStatusId !='Submit'	)}">
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
                "required":false,
                "value":${pdfn:toJsonString(info.recordFlow)}
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
                <c:set var="discipleTime" value="自${pdfn:transDateTimeForPattern(info.studyStartDate,'yyyy-MM-dd','yyyy')}年${pdfn:transDateTimeForPattern(info.studyStartDate,'yyyy-MM-dd','MM')}月${pdfn:transDateTimeForPattern(info.studyStartDate,'yyyy-MM-dd','dd')}日至${pdfn:transDateTimeForPattern(info.studyEndDate,'yyyy-MM-dd','yyyy')}年${pdfn:transDateTimeForPattern(info.studyEndDate,'yyyy-MM-dd','MM')}月${pdfn:transDateTimeForPattern(info.studyEndDate,'yyyy-MM-dd','dd')}日"></c:set>
            </c:if>
            <c:if test="${!isShowSelect}">
                <c:set var="discipleTime" value="自${pdfn:transDateTimeForPattern(info.discipleStartDate,'yyyy-MM-dd','yyyy')}年${pdfn:transDateTimeForPattern(info.discipleStartDate,'yyyy-MM-dd','MM')}月${pdfn:transDateTimeForPattern(info.discipleStartDate,'yyyy-MM-dd','dd')}日至${pdfn:transDateTimeForPattern(info.discipleEndDate,'yyyy-MM-dd','yyyy')}年${pdfn:transDateTimeForPattern(info.discipleEndDate,'yyyy-MM-dd','MM')}月${pdfn:transDateTimeForPattern(info.discipleEndDate,'yyyy-MM-dd','dd')}日"></c:set>
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
                <c:if test="${isShowSelect}">
                    "value":${pdfn:toJsonString(info.studyStartDate)}
                </c:if>
                <c:if test="${!isShowSelect}">
                    "value":${pdfn:toJsonString(info.discipleStartDate)}
                </c:if>
                
            },
            {
                "inputId":"discipleEndDate",
                "label":"跟师结束时间：",
                "inputType":"hidden",
                "readonly":true,
                "required":true,
                <c:if test="${isShowSelect}">
                    "value":${pdfn:toJsonString(info.discipleEndDate)}
                </c:if>
                <c:if test="${!isShowSelect}">
                    "value":${pdfn:toJsonString(info.discipleEndDate)}
                </c:if>
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
            {
                "inputId":"annualHege",
                "label":"年度考核合格次数：",
                "placeholder":"",
                "inputType":"text",
                "verify": {
                    "length": 10,
                    "type": "text"
                },
                "readonly":true,
                "required":true,
                <c:if test="${!isShowSelect}">
                    "value":${pdfn:toJsonString(info.yearHegeCount)}
                </c:if>
                <c:if test="${isShowSelect}">
                    "value":${pdfn:toJsonString(info.annualHege)}
                </c:if>
            },
            {
                "inputId":"annualNotHege",
                "label":"年度考核不合格次数：",
                "placeholder":"",
                "inputType":"text",
                "verify": {
                    "length": 10,
                    "type": "text"
                },
                "readonly":true,
                "required":true,
                <c:if test="${!isShowSelect}">
                    "value":${pdfn:toJsonString(info.yearBuhegeCount)}
                </c:if>
                <c:if test="${isShowSelect}">
                    "value":${pdfn:toJsonString(info.annualNotHege)}
                </c:if>
            },
            {
                "inputId":"graduationFile",
                "label":"结业论文题目：",
                "placeholder":"",
                "inputType":"text",
                "verify": {
                    "length": 10,
                    "type": "text"
                },
                "readonly":true,
                "required":false,
                <c:if test="${isShowSelect}">
					<c:if test="${empty info.graduationThesisTitle}">
						"value":"尚未上传结业论文"
					</c:if>
					<c:if test="${not empty info.graduationThesisTitle}">
						"value":${pdfn:toJsonString(info.graduationThesisTitle)}
                    </c:if>
				</c:if>
				<c:if test="${!isShowSelect}">
					<c:if test="${empty info.fileName}">
                        "value":"尚未上传结业论文"
					</c:if>
					<c:if test="${not empty info.fileName}">
						"value":${pdfn:toJsonString(info.fileName)}
					</c:if>
				</c:if>
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
                "inputId":"experienceContent",
                "label":"自我鉴定：",
                "placeholder":"自我鉴定（简述执行跟师学习计划情况，三年来师承学习所取得的成绩和存在的问题等）",
                "inputType":"textarea",
                "verify": {
                    "length": 2000,
                    "min-length": 1000,
                    "type": "textarea"
                },
                "readonly":${isShowSelect},
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
                "required":true,
                <c:if test="${!isRead}">
                    "value":${pdfn:toJsonString(currDate)}
                </c:if>
                <c:if test="${isShowSelect or isRead}">
                    "value":${pdfn:toJsonString(pdfn:transDate(info.experienceSignTime))}
                </c:if>
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
                "label":"指导老师意见：",
                "placeholder":"指导老师意见（对跟师规培学员进行总体评价，重点评价跟师学习态度、主要成绩、结业论文质量，并明确是否同意跟师规培学员参加结业考核）",
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
                "value":${pdfn:toJsonString(pdfn:transDate(info.auditTime))}
            }
            </c:if>
            <c:if test="${not empty info.auditContent}">
             ,
            {
                "inputId":"adminContent",
                "label":"单位考核意见：（包括：对跟师规培学员职业道德、工作态度、劳动纪律、跟师效果等进行综合评价，并按照跟师学习考核打分表对结业考核是否合格提出明确意见。）：",
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