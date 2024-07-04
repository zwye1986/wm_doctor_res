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
            "label": "病历号",
            "required":true,
            "inputType": "text"
        },
        {
            "inputId": "skill_operName",
            "label": "操作技能",
            "required":true,
            "inputType": "combox",
            "options": [
                <c:forEach items="${skillOperNameList}" var="operName" varStatus="status">
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
            "inputId": "skill_result",
            "label": "成功",
            "required":true,
            "inputType": "radio",
            "options": [
                {
                    "optionId": "是",
                    "optionDesc": "是"
                },
                {
                    "optionId": "否",
                    "optionDesc": "否"
                }
            ]
        },
        {
            "inputId": "skill_memo",
            "label": "备注",
            "required":false,
            "inputType": "textarea"
        }
