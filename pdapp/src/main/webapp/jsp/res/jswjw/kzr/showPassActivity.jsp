<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)},
    "submitRole":${pdfn:toJsonString(activity.submitRole)}
    <c:if test="${resultId eq '200' }">
,
    "action":{
        "save":"保存"
        <c:if test="${activity.speakerFlow eq user.userFlow and not empty activity.activityFlow}">
    ,
        "upload":"上传"
        </c:if>
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
            "readonly":true,
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
            "readonly":true,
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
                    "optionId": "17",
                    "optionDesc": "入院教育"
                },
                {
                    "optionId": "2",
                    "optionDesc": "疑难病例讨论"
                },
                {
                    "optionId": "3",
                    "optionDesc": "危重病例讨论"
                },
                {
                    "optionId": "1",
                    "optionDesc": "教学查房"
                },
                 {
                    "optionId": "5",
                    "optionDesc": "死亡病例讨论"
                },
                {
                    "optionId": "11",
                    "optionDesc": "教学病例讨论"
                },
                {
                    "optionId": "4",
                    "optionDesc": "临床小讲课"
                },
                {
                    "optionId": "12",
                    "optionDesc": "临床操作技能床旁教学"
                },
                {
                    "optionId":"13",
                    "optionDesc":"住院病历书写指导教学"
                },
                {
                    "optionId":"18",
                    "optionDesc":"入专业基地教育"
                },
                {
                    "optionId":"6",
                    "optionDesc":"入轮转科室教育"
                },
                {
                    "optionId":"14",
                    "optionDesc":"手术操作指导教学"
                },
                {
                    "optionId":"16",
                    "optionDesc":"临床文献研读会"
                },
                {
                    "optionId":"9",
                    "optionDesc":"教学阅片"
                },
                {
                    "optionId":"15",
                    "optionDesc":"影像诊断报告书写指导教学"
                },
                {
                    "optionId":"10",
                    "optionDesc":"门诊教学"
                },
                {
                    "optionId":"19",
                    "optionDesc":"晨间报告"
                },
                {
                    "optionId":"20",
                    "optionDesc":"报告单分析"
                },
                {
                    "optionId":"21",
                    "optionDesc":"教学上机"
                },
                {
                    "optionId":"22",
                    "optionDesc":"上机演示"
                },
                {
                    "optionId":"7",
                    "optionDesc":"出科考核"
                },
                {
                    "optionId":"8",
                    "optionDesc":"技能培训"
                }

            ],
            "readonly":true,
            "required":true,
            "isHidden": false,
            "value":${pdfn:toJsonString(activity.activityTypeId)}
        },
        {
            "inputId":"activityName",
            "label":"活动名称",
            "inputType":"text",
            "readonly":true,
            "required":true,
            "isHidden": false,
            "value":${pdfn:toJsonString(activity.activityName)}
        },
        {
            "inputId":"activityAddress",
            "label":"活动地点",
            "inputType":"text",
            "readonly":true,
            "required":true,
            "isHidden": false,
            "value":${pdfn:toJsonString(activity.activityAddress)}
        },
        {
            "inputId":"speakerPhone",
            "label":"联系方式",
            "inputType":"text",
            "readonly":true,
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
            "readonly":true,
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
            "readonly":true,
            "required":true,
            "isHidden": false,
            "value":${pdfn:toJsonString(activity.endTime)}
        },
<%--        {--%>
<%--            "inputId":"fileName",--%>
<%--            "label":"附        件",--%>
<%--            "inputType":"text",--%>
<%--            "readonly":false,--%>
<%--            "required":true,--%>
<%--&lt;%&ndash;            "value":${pdfn:toJsonString(empty activity.fileName?"无":activity.fileName)}&ndash;%&gt;--%>
<%--            "value":${pdfn:toJsonString(empty activity.FILE_FLOWS?"无":"查看")}--%>
<%--        },--%>
        {
            "inputId":"realitySpeaker",
            "label":"实际主讲人",
            "inputType":"text",
            "readonly":false,
            "required":false,
            "value":${pdfn:toJsonString(activity.realitySpeaker)}
        },
        {
            "inputId":"submitRole",
            "label":"活动发起人角色",
            "inputType":"textarea",
            "readonly":false,
            "required":false,
            "isHidden": true,
            "value":${pdfn:toJsonString(activity.submitRole)}
        },
        {
            "inputId":"activityRemark",
            "label":"活动简介",
            "inputType":"textarea",
            "readonly":true,
            "required":true,
            "isHidden": false,
            "value":${pdfn:toJsonString(activity.activityRemark)}
        },
        {
            "inputId":"opinion",
            "label":"审核意见",
            "inputType":"textarea",
            "readonly":true,
            "required":false,
            "value":${pdfn:toJsonString(activity.opinion)}
        }

    ]
    </c:if>
}