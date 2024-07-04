<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
        ,
        "action":{
             "save":"保存"
            <c:if test="${activity.speakerFlow eq user.userFlow and not empty activity.activityFlow}">
            ,
            "upload":"上传"
            </c:if>
        },
        "formMap":{
            <c:forEach items="${activityList}" var="act" varStatus="status">
            ${pdfn:toJsonString(act.dictId)}: [
                <c:set var="dictForms" value="${formMap[act.dictId]}"></c:set>
                <c:forEach items="${dictForms}" var="dict" varStatus="dstatus">
                {
                    "inputId":"${dict.recordFlow}",
                    "label":"${dict.detailKey}",
                    "inputType":"${dict.inputType eq '0'?'text':'textarea'}",
                    "readonly":false,
                    "required":true,
                    "value":${pdfn:toJsonString(valueMap[dict.recordFlow].detailValue)}
                }
                <c:if test="${not status.last }">
                ,
                </c:if>
                </c:forEach>
            ]
            <c:if test="${not status.last }">
                ,
            </c:if>
            </c:forEach>
        },
        "inputs":[
            {
                "inputId":"activityFlow",
                "label":"活动流水号",
                "inputType":"text",
                "readonly":true,
                "required":false,
                "isHidden": true,
                "value":${pdfn:toJsonString(activity.activityFlow)}
            },
            {
                "inputId":"deptFlow",
                "label":"所在科室",
                "inputType":"select",
                "options": [
                    <c:forEach items="${depts}" var="dept" varStatus="status">
                    <c:set var="users" value="${deptUserMap[dept.deptFlow]}"></c:set>
                    {
                        "optionId": ${pdfn:toJsonString(dept.deptFlow)},
                        "optionDesc": ${pdfn:toJsonString(dept.deptName)},
                        "items":[
                            <c:forEach items="${users}" var="u" varStatus="s">
                            {
                                "optionId": ${pdfn:toJsonString(u.userFlow)},
                                "optionDesc": ${pdfn:toJsonString(u.userName)},
                                "userPhone": ${pdfn:toJsonString(u.userPhone)}
                            }
                            <c:if test="${not s.last }">
                            ,
                            </c:if>
                            </c:forEach>
                        ]
                    }
                    <c:if test="${not status.last }">
                    ,
                    </c:if>
                    </c:forEach>
                ],
                "readonly":false,
                "required":true,
                "isHidden": false,
                "value":${pdfn:toJsonString(empty activity.deptFlow ?user.deptFlow:activity.deptFlow)}
            },
            {
                "inputId":"speakerFlow",
                "label":"主讲人",
                "inputType":"select",
                "options": [
                    <c:set var="deptFlow" value="${empty activity.deptFlow ?user.deptFlow:activity.deptFlow}"></c:set>
                    <c:forEach items="${deptUserMap[deptFlow]}" var="u" varStatus="status">
                    {
                        "optionId": ${pdfn:toJsonString(u.userFlow)},
                        "optionDesc": ${pdfn:toJsonString(u.userName)},
                        "userPhone": ${pdfn:toJsonString(u.userPhone)}
                    }
                    <c:if test="${not status.last }">
                    ,
                    </c:if>
                    </c:forEach>
                ],
                "readonly":false,
                "required":true,
                "isHidden": false,
                <c:if test="${roleFlag eq 'Seretary'   }">
                    "value":${pdfn:toJsonString(activity.speakerFlow)}
                </c:if>
                <c:if test="${roleFlag eq 'Head'   }">
                    "value":${pdfn:toJsonString(empty activity.speakerFlow ?user.userFlow:activity.speakerFlow)}
                </c:if>
            },
            {
                "inputId":"activityTypeId",
                "label":"活动形式",
                "inputType":"select",
                "options": [

                        {
                            "optionId": "",
                            "optionDesc": ""
                        },
                        <c:forEach items="${activityList}" var="act" varStatus="status">
                        {
                            "optionId": ${pdfn:toJsonString(act.dictId)},
                            "optionDesc": ${pdfn:toJsonString(act.dictName)}
                        }
                        <c:if test="${not status.last }">
                        ,
                        </c:if>
                        </c:forEach>
                ],
                "readonly":false,
                "required":true,
                "isHidden": false,
                "value":${pdfn:toJsonString(activity.activityTypeId)}
            },
            {
                "inputId":"activityName",
                "label":"活动名称",
                "inputType":"text",
                "readonly":false,
                "required":true,
                "isHidden": false,
                "value":${pdfn:toJsonString(activity.activityName)}
            },
            {
                "inputId":"activityAddress",
                "label":"活动地点",
                "inputType":"text",
                "readonly":false,
                "required":true,
                "isHidden": false,
                "value":${pdfn:toJsonString(activity.activityAddress)}
            },
            {
                "inputId":"speakerPhone",
                "label":"联系方式",
                "inputType":"text",
                "readonly":false,
                "required":true,
                "isHidden": false,
                "value":${pdfn:toJsonString(activity.speakerPhone)}
            },
            {
                "inputId":"startTime",
                "label":"开始时间：",
                "inputType":"date",
                "verify": {
                    "dateFmt": "yyyy-MM-dd HH:mm",
                    "type": "date"
                },
                "readonly":false,
                "required":true,
                "isHidden": false,
                "value":${pdfn:toJsonString(activity.startTime)}
            },
            {
                "inputId":"endTime",
                "label":"结束时间：",
                "inputType":"date",
                "verify": {
                    "dateFmt": "yyyy-MM-dd HH:mm",
                    "type": "date"
                },
                "readonly":false,
                "required":true,
                "isHidden": false,
                "value":${pdfn:toJsonString(activity.endTime)}
            },
            {
                "inputId":"fileName",
                "label":"附        件",
                "inputType":"text",
                "readonly":true,
                "required":true,
                "value":${pdfn:toJsonString(empty activity.fileName?"无":activity.fileName)}
            },
            {
                "inputId":"activityRemark",
                "label":"活动简介",
                "inputType":"textarea",
                "readonly":false,
                "required":true,
                "isHidden": false,
                "value":${pdfn:toJsonString(activity.activityRemark)}
            }

        ]
    </c:if>
}