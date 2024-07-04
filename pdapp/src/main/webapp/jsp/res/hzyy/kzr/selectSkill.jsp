<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
	<c:if test="${resultId eq '200' }">

        ,"datas":[

                {
                    "inputId":"TrueName",
                    "inputType":"text",
                    "label":"学员姓名",
                    "readonly":true,
                    "required":false,
                    "value":${pdfn:toJsonString(cycleInfo.TrueName)}
                },
                {
                    "inputId":"StartYear",
                    "inputType":"text",
                    "label":"年　级",
                    "readonly":true,
                    "required":false,
                    "value":${pdfn:toJsonString(cycleInfo.StartYear)}
                },
                {
                    "inputId":"HosSecName",
                    "inputType":"text",
                    "label":"轮转科室",
                    "readonly":true,
                    "required":false,
                    "value":${pdfn:toJsonString(cycleInfo.HosSecName )}
                },
                {
                    "inputId":"CycleDate",
                    "inputType":"text",
                    "label":"轮转时间",
                    "readonly":true,
                    "required":false,
                    "value":${pdfn:toJsonString(cycleInfo.CycleDate )}
                },
                {
                    "inputId":"Skill_PatientName",
                    "inputType":"text",
                    "label":"病人姓名",
                    "readonly":true,
                    "required":true,
                    "value":${pdfn:toJsonString(outSkillMap.Skill_PatientName )}
                },
                {
                    "inputId":"Skill_No",
                    "inputType":"text",
                    "label":"住院号",
                    "readonly":true,
                    "required":true,
                    "value":${pdfn:toJsonString(outSkillMap.Skill_No )}
                },
                {
                    "inputId":"Skill_Time",
                    "inputType":"date",
                    "label":"考核日期",
                    "readonly":true,
                    "required":true,
                    "verify": {
                        "dateFmt": "yyyy-MM-dd",
                        "type": "date"
                    },
                    "value":${pdfn:toJsonString(empty outSkillMap.Skill_Time ? pdfn:getCurrDate(): outSkillMap.Skill_Time)}
                },
				 <c:set var="count" value="1"></c:set>
				 <c:forEach items="${items}" var="title" varStatus="status">
					 <c:set var="itemList" value="${itemsMap[title.TItemFlow]}"/>
					 {
						"inputId":"${title.TItemFlow}",
						"inputType":"group",
						"label":"${title.TItemName}",
						"readonly":true,
						"required":true,
						"haveScore":false,
						"items":[
					 		<c:set var="count2" value="1"></c:set>
							 <c:forEach items="${itemList}" var="item" varStatus="itemStatus">
									{
										"inputId":${pdfn:toJsonString(item.TItemFlow)},
										"inputType":"text",
                                        <c:set value="${item.TItemName}(${item.TItemScore}分)" var="label"></c:set>
                                        "label":${pdfn:toJsonString(label)},
										"tip":"${item.TItemName}",
										"placeholder": "0~${item.TItemScore}",
										"verify": {
											"max": "${item.TItemScore}",
											 "type": "int"
										},
										"haveScore":true,
                                        "readonly":true,
                                        "required":true,
                                        "value":"${itemScoreMap[item.TItemFlow]}"
									}
								 <c:set var="count2" value="${count2+1}"></c:set>
								 <c:if test="${(not itemStatus.last) }">
									 ,
								 </c:if>
							 </c:forEach>
					 	]
					 }
					 <c:set var="count" value="${count+1}"></c:set>
                     ,
				</c:forEach>

                {
                    "inputId":"Skill_Score",
                    "inputType":"text",
                    "label":"分数",
                    "readonly":true,
                    "required":true,
                    "value":${pdfn:toJsonString(outSkillMap.Skill_Score )}
                },
                {
                    "inputId":"Skill_ExaminerOne",
                    "inputType":"text",
                    "label":"考官一",
                    "readonly":true,
                    "required":false,
                    "value":${pdfn:toJsonString(outSkillMap.Skill_ExaminerOne)}
                },
                {
                    "inputId":"Skill_ExaminerTwo",
                    "inputType":"text",
                    "label":"考官二",
                    "readonly":true,
                    "required":false,
                    "value":${pdfn:toJsonString(outSkillMap.Skill_ExaminerTwo)}
                },
                {
                    "inputId":"imglist",
                    "inputType":"imgs",
                    "label":"附件上传",
                    "readonly":true,
                    "required":true,
                    "haveScore":false,
                    "max":"3",
                    "imgs":[
                        <c:forEach items="${fileList}" var="file" varStatus="itemStatus">
                        {
                            "fileFlow":${pdfn:toJsonString(file.AttachID)},
                            <c:set var="fileUrl" value="${baseUrl}/${file.AttachPath}/${file.AttachFileName}"></c:set>
                            "fileUrl":${pdfn:toJsonString(fileUrl)},
                            "fileName":${pdfn:toJsonString(file.AttachName)}
                        }
                        <c:if test="${(not itemStatus.last) }">
                        ,
                        </c:if>
                        </c:forEach>
                    ]
                }
        ]
	</c:if>
}
