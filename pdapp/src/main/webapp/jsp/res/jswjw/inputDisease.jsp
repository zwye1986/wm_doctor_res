<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
    	{
            "inputId": "disease_pDate",
            "label": "日期",
            "required":true,
            "inputType": "date"
        },
    	{
            "inputId": "disease_pName",
            "label": "病人姓名",
            "required":true,
            "inputType": "text"
        },
        {
            "inputId": "disease_mrNo",
            "label": "病历号/病理号",
            "required":true,
            "inputType": "text"
        },
        {
            "inputId": "disease_diagName",
            "label": "病种名称",
            "required":true,
            <c:if test="${!isOther}">
	            "inputType": "combox",
	            "options": [
	                <c:forEach items="${diseaseDiagNameList}" var="diagName" varStatus="status">
				        {
		                    "optionId": ${pdfn:toJsonString(diagName.itemId)},
		                    "optionDesc": ${pdfn:toJsonString(diagName.itemName)}
		                }
	                	<c:if test="${not status.last }">
							,
						</c:if>
					</c:forEach>
	            ]
            </c:if>
            <c:if test="${isOther}">
            	"inputType": "text"
            </c:if>
        },
        {
            "inputId": "disease_diagType",
            "label": "诊断类型",
            "required":true,
            "inputType": "select",
            "options": [
               		{
	                    "optionId": "1",
	                    "optionDesc": "主要诊断"
	                },
	                {
	                    "optionId": "2",
	                    "optionDesc": "次要诊断"
	                },
	                {
	                    "optionId": "3",
	                    "optionDesc": "并行诊断"
	                }
            ]
        },
        {
            "inputId": "disease_isCharge",
            "label": "是否主管",
            "required":true,
            "inputType": "radio",
            "options": [
                {
                    "optionId": "1",
                    "optionDesc": "是"
                },
                {
                    "optionId": "0",
                    "optionDesc": "否"
                }
            ]
        },
        {
            "inputId": "disease_isRescue",
            "label": "是否抢救",
            "required":true,
            "inputType": "radio",
            "options": [
                {
                    "optionId": "1",
                    "optionDesc": "是"
                },
                {
                    "optionId": "0",
                    "optionDesc": "否"
                }
            ]
        },
        {
            "inputId": "disease_caseType",
            "label": "病例类型",
            "required":true,
            "inputType": "radio",
            "options": [
                {
                    "optionId": "0",
                    "optionDesc": "新收病人"
                },
                {
                    "optionId": "1",
                    "optionDesc": "书写规范住院大病例"
                }
                ,
                {
                    "optionId": "2",
                    "optionDesc": "学习病例"
                }
            ]
        },
        {
            "inputId": "disease_treatStep",
            "label": "转归情况",
            "required":true,
            "inputType": "textarea"
        }
