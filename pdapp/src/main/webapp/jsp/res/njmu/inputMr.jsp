<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>

    	{
            "inputId": "mr_pName",
            "label": "病人姓名",
            "required":true,
            "inputType": "text"
        },
        {
            "inputId": "mr_no",
            "label": "病历号",
            "required":true,
            "inputType": "text"
        },
        {
            "inputId": "mr_diagType",
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
        }
