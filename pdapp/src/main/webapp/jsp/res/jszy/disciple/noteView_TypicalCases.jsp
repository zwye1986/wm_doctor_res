<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>

<c:set var="isRead" value="${!(info.auditStatusId eq 'Apply'or info.auditStatusId eq 'UnQualified' or empty info.auditStatusId)}"></c:set>
<c:if test="${info.auditStatusId eq 'Apply' or info.auditStatusId eq 'UnQualified'or empty info.auditStatusId}">
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
                "inputId":"peopleName",
                "label":"患者姓名：",
                "placeholder":"点击填写",
                "inputType":"text",
                "verify": {
                    "length": "50",
                    "type": "text"
                },
                "readonly":${isRead},
                "required":true,
                "value":${pdfn:toJsonString(info.peopleName)}
            },
            {
                "inputId":"sexId",
                "label":"性别：",
                "placeholder":"点击选择",
                "inputType":"select",
				"options": [
					{
						"optionId": "Man",
						"optionDesc": "男"
					},
					{
						"optionId": "Woman",
						"optionDesc": "女"
					}
				],
                "readonly":${isRead},
                "required":true,
                "value":${pdfn:toJsonString(info.sexId)}
            },
            {
                "inputId":"birthDate",
                "label":"出生日期：",
                "placeholder":"点击选择",
                "inputType":"date",
                "verify": {
                    "dateFmt": "yyyy-MM-dd",
                    "type": "date"
                },
                "readonly":${isRead},
                "required":true,
                "value":${pdfn:toJsonString(info.birthDate)}
            },
            {
                "inputId":"visitDate",
                "label":"就诊日期：",
                "placeholder":"点击选择",
                "inputType":"date",
                "verify": {
                    "dateFmt": "yyyy-MM-dd",
                    "type": "date"
                },
                "readonly":${isRead},
                "required":true,
                "value":${pdfn:toJsonString(info.visitDate)}
            },
            {
                "inputId":"visitActionId",
                "label":"初诊、复诊：",
                "placeholder":"点击选择",
                "inputType":"select",
				"options": [
					{
						"optionId": "chuzhen",
						"optionDesc": "初诊"
					},
					{
						"optionId": "fuzhen",
						"optionDesc": "复诊"
					}
				],
                "readonly":${isRead},
                "required":true,
                "value":${pdfn:toJsonString( info.visitActionId)}
            },
            {
                "inputId":"solarTerms",
                "label":"发病节气：",
                "inputType":"textarea",
                "verify": {
                    "length": "200",
                    "type": "textarea"
                },
                "readonly":${isRead},
                "required":true,
                "value":${pdfn:toJsonString(info.solarTerms)}
            },
            {
                "inputId":"mainSuit",
                "label":"主诉：",
                "inputType":"textarea",
                "verify": {
                    "length": "400",
                    "type": "textarea"
                },
                "readonly":${isRead},
                "required":true,
                "value":${pdfn:toJsonString(info.mainSuit)}
            },
            {
                "inputId":"presentDiseaseHistory",
                "label":"现病史：",
                "inputType":"textarea",
                "verify": {
                    "length": "400",
                    "type": "textarea"
                },
                "readonly":${isRead},
                "required":false,
                "value":${pdfn:toJsonString(info.presentDiseaseHistory)}
            },
            {
                "inputId":"previousDiseaseHistory",
                "label":"既往史：",
                "inputType":"textarea",
                "verify": {
                    "length": "400",
                    "type": "textarea"
                },
                "readonly":${isRead},
                "required":false,
                "value":${pdfn:toJsonString(info.previousDiseaseHistory)}
            },
            {
                "inputId":"allergicHistory",
                "label":"过敏史：",
                "inputType":"textarea",
                "verify": {
                    "length": "400",
                    "type": "textarea"
                },
                "readonly":${isRead},
                "required":false,
                "value":${pdfn:toJsonString(info.allergicHistory)}
            },
            {
                "inputId":"physicalExamination",
                "label":"体格检查：",
                "inputType":"textarea",
                "verify": {
                    "length": "400",
                    "type": "textarea"
                },
                "readonly":${isRead},
                "required":false,
                "value":${pdfn:toJsonString(info.physicalExamination)}
            },
            {
                "inputId":"accessoryExamination",
                "label":"辅助检查：",
                "inputType":"textarea",
                "verify": {
                    "length": "400",
                    "type": "textarea"
                },
                "readonly":${isRead},
                "required":false,
                "value":${pdfn:toJsonString(info.accessoryExamination)}
            },
            {
                "inputId":"tcmDiagnosis",
                "label":"中医诊断：",
                "inputType":"textarea",
                "verify": {
                    "length": "400",
                    "type": "textarea"
                },
                "readonly":${isRead},
                "required":false,
                "value":${pdfn:toJsonString(info.tcmDiagnosis)}
            },
            {
                "inputId":"syndromeDiagnosis",
                "label":"证候诊断：",
                "inputType":"textarea",
                "verify": {
                    "length": "400",
                    "type": "textarea"
                },
                "readonly":${isRead},
                "required":false,
                "value":${pdfn:toJsonString(info.syndromeDiagnosis)}
            },
            {
                "inputId":"westernDiagnosis",
                "label":"西医诊断：",
                "inputType":"textarea",
                "verify": {
                    "length": "400",
                    "type": "textarea"
                },
                "readonly":${isRead},
                "required":false,
                "value":${pdfn:toJsonString(info.westernDiagnosis)}
            },
            {
                "inputId":"therapy",
                "label":"治法：",
                "inputType":"textarea",
                "verify": {
                    "length": "400",
                    "type": "textarea"
                },
                "readonly":${isRead},
                "required":false,
                "value":${pdfn:toJsonString(info.therapy)}
            },
            {
                "inputId":"prescription",
                "label":"处方：",
                "inputType":"textarea",
                "verify": {
                    "length": "400",
                    "type": "textarea"
                },
                "readonly":${isRead},
                "required":false,
                "value":${pdfn:toJsonString(info.prescription)}
            },
            {
                "inputId":"returnVisit",
                "label":"复诊：",
                "inputType":"textarea",
                "verify": {
                    "length": "400",
                    "type": "textarea"
                },
                "readonly":${isRead},
                "required":false,
                "value":${pdfn:toJsonString(info.returnVisit)}
            },
            {
                "inputId":"experienceContent",
                "label":"临证随笔或心得（${jdya_lzsb}）：",
                "placeholder":"本次跟师主要情况（诊疗人次、主要病种等）、典型病例摘录、指导老师指导意见、个人心得体会等",
                "inputType":"textarea",
                "verify": {
                    "length": 400,
                    "type": "textarea"
                },
                "readonly":${isRead},
                "required":false,
                "value":${pdfn:toJsonString(info.experienceContent)}
            },
            {
                "inputId":"doctorName",
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
            {
                "inputId":"studentSignTime",
                "label":"学员签名日期：",
                "inputType":"text",
                "readonly":true,
                "required":true,
                "value":${pdfn:toJsonString(empty signTime?currDate:signTime)}
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
                "value":${pdfn:toJsonString( empty auditTime ?currDate : auditTime)}
            }
            </c:if>
]