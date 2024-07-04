<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>

{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "action":{
        "save":"保存"
    },
	"datas":[
            {
                "inputId":"recordFlow",
                "label":"流水号",
                "inputType":"hidden",
                "readonly":true,
                "required":true,
                "value":${pdfn:toJsonString(teacherInfo.recordFlow)}
            },
            {
                "inputId":"teacherName",
                "label":"姓名：",
                "placeholder":"点击填写",
                "inputType":"text",
                "verify": {
                    "length": 20,
                    "type": "text"
                },
                "readonly":false,
                "required":true,
                "value":${pdfn:toJsonString(teacherInfo.teacherName)}
            },
            {
                "inputId":"sexId",
                "label":"性别：",
                "placeholder":"点击选择",
                "inputType":"select",
				"options": [
					{
						"optionId": "Man",
						"optionDesc": "男"
					},
					{
						"optionId": "Woman",
						"optionDesc": "女"
					}
				],
                "readonly":false,
                "required":true,
                "value":${pdfn:toJsonString(teacherInfo.sexId)}
            },
            {
                "inputId":"birthdate",
                "label":"出生年月：",
                "placeholder":"点击选择",
                "inputType":"date",
                "verify": {
                    "dateFmt": "yyyy-MM-dd",
                    "type": "date"
                },
                "readonly":false,
                "required":true,
                "value":${pdfn:toJsonString(teacherInfo.birthdate)}
            },
            {
                "inputId":"workSpeName",
                "label":"从事专业：",
                "placeholder":"点击填写",
                "inputType":"text",
                "verify": {
                    "length": 80,
                    "type": "text"
                },
                "readonly":false,
                "required":true,
                "value":${pdfn:toJsonString(teacherInfo.workSpeName)}
            },
            {
                "inputId":"teacherTitleName",
                "label":"职称：",
                "placeholder":"点击填写",
                "inputType":"text",
                "verify": {
                    "length": 80,
                    "type": "text"
                },
                "readonly":false,
                "required":true,
                "value":${pdfn:toJsonString(teacherInfo.teacherTitleName)}
            },
            {
                "inputId":"educationId",
                "label":"学历：",
                "placeholder":"点击填写",
                "inputType":"text",
                "verify": {
                    "length": 80,
                    "type": "text"
                },
                "readonly":false,
                "required":true,
                "value":${pdfn:toJsonString(teacherInfo.educationId)}
            },
            {
                "inputId":"address",
                "label":"通讯地址：",
                "placeholder":"点击填写",
                "inputType":"text",
                "verify": {
                    "length": 120,
                    "type": "text"
                },
                "readonly":false,
                "required":true,
                "value":${pdfn:toJsonString(teacherInfo.address)}
            },
            {
                "inputId":"phone",
                "label":"电话：",
                "placeholder":"点击填写",
                "inputType":"text",
                "verify": {
                    "length": 20,
                    "type": "phone"
                },
                "readonly":false,
                "required":true,
                "value":${pdfn:toJsonString(teacherInfo.phone)}
            },
            {
                "inputId":"content",
                "label":"简介：",
                "placeholder":"主要学术经验、专长及成就（内容包括基本学术思想、临床技能、科研教学、论文论著等情况）",
                "inputType":"textarea",
                "verify": {
                    "length": 2000,
                    "type": "textarea"
                },
                "readonly":false,
                "required":true,
                "value":${pdfn:toJsonString(teacherInfo.content)}
            }
        ]
    </c:if>
}
