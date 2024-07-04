<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
    	{
            "inputId": "operation_operDate",
            "label": "手术日期",
            "required":true,
            "inputType": "date"
        },
    	{
            "inputId": "operation_pName",
            "label": "病人姓名",
            "required":true,
            "inputType": "text"
        },
        {
            "inputId": "operation_mrNo",
            "label": "病历号/病理号/检查号",
            "required":true,
            "inputType": "text"
        },
        {
            "inputId": "operation_operName",
            "label": "手术名称",
            "required":true,
            <c:if test="${!isOther}">
	            "inputType": "combox",
	            "options": [
	                <c:forEach items="${operationOperNameList}" var="operName" varStatus="status">
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
            "inputId": "operation_operRole",
            "label": "术中职务",
            "required":true,
            "inputType": "radio",
            "options": [
			        {
	                    "optionId": "0",
	                    "optionDesc": "术者"
	                },
	                {
	                    "optionId": "1",
	                    "optionDesc": "一助"
	                },
	                {
	                    "optionId": "2",
	                    "optionDesc": "二助"
	                }
            ]
        },
        {
            "inputId": "operation_memo",
            "label": "备注",
            "required":false,
            "inputType": "textarea"
        }