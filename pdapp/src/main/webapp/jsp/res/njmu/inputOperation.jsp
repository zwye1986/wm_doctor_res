<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
    	{
            "inputId": "operation_operDate",
            "label": "操作日期",
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
            "label": "病历号",
            "required":true,
            "inputType": "text"
        },
        {
            "inputId": "operation_operName",
            "label": "操作名称",
            "required":true,
            "inputType": "combox",
            "options": [
                <c:forEach items="${operationOperNameList}" var="operName" varStatus="status">
			        <c:if test="${operName.operId eq param.cataFlow }">
					{
	                    "optionId": ${pdfn:toJsonString(operName.operName)},
	                    "optionDesc": ${pdfn:toJsonString(operName.operName)}
	                }
					</c:if>
				</c:forEach>
            ]
        },
        {
            "inputId": "operation_operRole",
            "label": "术者/助手",
            "required":true,
            "inputType": "radio",
            "options": [
                <c:forEach items="${operRoleList}" var="operRole" varStatus="status">
			        {
	                    "optionId": ${pdfn:toJsonString(operRole.dicitem)},
	                    "optionDesc": ${pdfn:toJsonString(operRole.dicitem)}
	                }
			        <c:if test="${not status.last }">
							,
					</c:if>
				</c:forEach>
            ]
        },
        {
            "inputId": "operation_memo",
            "label": "备注",
            "required":false,
            "inputType": "textarea"
        }