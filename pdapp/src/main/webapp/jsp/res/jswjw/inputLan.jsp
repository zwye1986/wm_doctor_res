<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
    	{
            "inputId": "research_content",
            "label": "培养内容",
            "required":true,
            "inputType": "text"
        },
        {
            "inputId": "research_num",
            "label": "学习数量",
            "required":true,
            "inputType": "text"
        }
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


