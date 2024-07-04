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
            "label": "病历号",
            "required":true,
            "inputType": "text"
        },
        {
            "inputId": "disease_diagName",
            "label": "疾病名称",
            "required":true,
            "inputType": "combox",
            "options": [
                <c:forEach items="${diseaseDiagNameList}" var="diagName" varStatus="status">
                	<c:if test="${diagName.diagId eq param.cataFlow }">
			        {
	                    "optionId": ${pdfn:toJsonString(diagName.diagName)},
	                    "optionDesc": ${pdfn:toJsonString(diagName.diagName)}
	                }
					</c:if>
				</c:forEach>
            ]
        },
        {
            "inputId": "disease_diagType",
            "label": "诊断类型",
            "required":true,
            "inputType": "select",
            "options": [
                <c:forEach items="${diagTypeList}" var="diagType" varStatus="status">
			        {
	                    "optionId": ${pdfn:toJsonString(diagType.dicitem)},
	                    "optionDesc": ${pdfn:toJsonString(diagType.dicitem)}
	                }
			        <c:if test="${not status.last }">
							,
					</c:if>
				</c:forEach>
            ]
        },
        {
            "inputId": "disease_isCharge",
            "label": "是否主管",
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
            "inputId": "disease_isRescue",
            "label": "是否抢救",
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
            "inputId": "disease_treatStep",
            "label": "治疗措施",
            "required":true,
            "inputType": "textarea"
        }
