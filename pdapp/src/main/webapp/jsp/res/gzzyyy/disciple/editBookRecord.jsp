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
                    "value":${pdfn:toJsonString(recordFlow)}
                },
                {
                    "inputId":"studyStartTime",
                    "label":"学习开始时间：",
                    "img":"calendar",
                    "inputType":"date",
                    "verify": {
                        "dateFmt": "yyyy-MM-dd",
                        "type": "date"
                    },
                    "readonly":false,
                    "required":true,
                    "value":${pdfn:toJsonString(record.studyStartTime)}
                },
                {
                    "inputId":"studyEndTime",
                    "label":"学习结束时间：",
                    "img":"calendar",
                    "inputType":"date",
                    "verify": {
                        "dateFmt": "yyyy-MM-dd",
                        "type": "date"
                    },
                    "readonly":false,
                    "required":true,
                    "value":${pdfn:toJsonString(record.studyEndTime)}
                },
                {
                    "inputId":"studyActionId",
                    "label":"学习方式：",
                    "placeholder":"点击选择",
                    "img":"book",
                    "inputType":"select",
                    "options": [
                        {
                            "optionId": "JIZHONG",
                            "optionDesc": "集中"
                        },
                        {
                            "optionId": "ZIXUE",
                            "optionDesc": "自学"
                        }
                    ],
                    "readonly":false,
                    "required":true,
                    "value":${pdfn:toJsonString(record.studyActionId)}
                },
                {
                    "inputId":"doctorFlow",
                    "label":"跟师规培学员标识符：",
                    "inputType":"hidden",
                    "readonly":true,
                    "required":true,
                    "value":${pdfn:toJsonString(empty record.doctorFlow?user.userFlow:record.doctorFlow)}
                },
                {
                    "inputId":"studyContent",
                    "label":"学习书目及内容：",
                    "placeholder":"",
                    "inputType":"textarea",
                    "verify": {
                        "length": 3000,
                        "type": "textarea"
                    },
                    "readonly":false,
                    "required":true,
                    "value":${pdfn:toJsonString(record.studyContent)}
                },
                {
                    "inputId":"remark",
                    "label":"备注：",
                    "placeholder":"",
                    "inputType":"textarea",
                    "verify": {
                        "length": 400,
                        "type": "textarea"
                    },
                    "readonly":false,
                    "required":true,
                    "value":${pdfn:toJsonString(record.remark)}
                }
    ]
    </c:if>
}