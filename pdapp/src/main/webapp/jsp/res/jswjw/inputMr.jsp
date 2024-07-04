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
            "inputId": "disease_pName",
            "label": "疾病名称",
            "required":true,
            "inputType": "text"
        },
        {
            "inputId": "mr_diagType",
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
        }
        <c:if test="${isChargeOrg}">
        ,
        {
            "inputId": "imageList",
            "label": "附件图片",
            "required":false,
            "inputType": "imageList",
            "images": [
                    <c:set value="${resultData.imageList}" var="imageList"></c:set>
                    <c:forEach items="${imageList}" var="dataMap" varStatus="status">
                        {
                        "imageFlow": ${pdfn:toJsonString(dataMap.imageFlow)},
                        "imageUrl": ${pdfn:toJsonString(dataMap.imageUrl)},
                        "thumbUrl":${pdfn:toJsonString(dataMap.thumbUrl)}
                        }
                        <c:if test="${not status.last }">
                            ,
                        </c:if>
                    </c:forEach>
            ]
        }
        </c:if>

