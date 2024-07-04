<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
		{
            "inputId": "skill_operDate",
            "label": "操作日期",
            "required":true,
            "inputType": "date"
        },
    	{
            "inputId": "skill_pName",
            "label": "病人姓名",
            "required":true,
            "inputType": "text"
        },
        {
            "inputId": "skill_mrNo",
            "label": "病历号/病理号/检查号",
            "required":true,
            "inputType": "text"
        },
        {
            "inputId": "skill_operName",
            "label": "操作名称",
            "required":true,
            <c:if test="${!isOther}">
	            "inputType": "combox",
	            "options": [
	                <c:forEach items="${skillOperNameList}" var="operName" varStatus="status">
						{
		                    "optionId": ${pdfn:toJsonString(operName.itemId)},
		                    "optionDesc": ${pdfn:toJsonString(operName.itemName)}
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
            "inputId": "skill_result",
            "label": "成功",
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
            "inputId": "fail_reason",
            "label": "失败原因",
            "required":false,
            "inputType": "textarea"
        }
