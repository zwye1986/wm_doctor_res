<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "catSpeId":${pdfn:toJsonString(doctorRecruit.catSpeName)},
    "recruitFlow":${pdfn:toJsonString(doctorRecruit.recruitFlow)},
    "startDateCheck":${pdfn:toJsonString(startDate)},
    "endDateCheck":${pdfn:toJsonString(endDate)},
    "trainInfo": [{
        "inputId" : "currDegreeCategoryId",
        "inputType" : "select",
        "label" : "当前学位类别",
        "readonly" : false,
        "required" : true,
        "value" : ${pdfn:toJsonString(doctorRecruit.currDegreeCategoryId)},
        "options": [
        <c:forEach items="${jsResDegreeCategoryEnumList}" var="dict" varStatus="status">
            {
            "optionId": ${pdfn:toJsonString(dict.id)},
            "optionDesc": ${pdfn:toJsonString(dict.name)}
            }
            <c:if test="${not status.last }">
                ,
            </c:if>
        </c:forEach>
        ]
        },
        <c:if test="${not empty doctorRecruit.auditStatusName }">
            {
            "inputId" : "auditStatusName",
            "inputType" : "text",
            "label" : "审核状态",
            "readonly" : true,
            "required" : true,
            "value" : ${pdfn:toJsonString(doctorRecruit.auditStatusName)}
            },
        </c:if>
        {
        "inputId" : "recruitDate",
        "inputType" : "date",
        "label" : "规培起始日期",
        "readonly" : false,
        "required" : true,
        "verify": {"dateFmt": "yyyy-MM-dd","type": "date"},
        "value" : ${pdfn:toJsonString(doctorRecruit.recruitDate)}
        },
        {
        "inputId" : "sessionNumber",
        "inputType" : "text",
        "label" : "届别",
        "readonly" : true,
        "required" : true,
        "value" : ${pdfn:toJsonString(doctorRecruit.sessionNumber)}
        },
        {
        "inputId" : "placeId",
        "inputType" : "select",
        "label" : "所在地区",
        "readonly" : false,
        "required" : true,
        "options" : [{
        "optionDesc" : "南京市",
        "optionId" : "320100"
        },{
        "optionDesc" : "无锡市",
        "optionId" : "320200"
        },{
        "optionDesc" : "徐州市",
        "optionId" : "320300"
        },{
        "optionDesc" : "常州市",
        "optionId" : "320400"
        },{
        "optionDesc" : "苏州市",
        "optionId" : "320500"
        },{
        "optionDesc" : "南通市",
        "optionId" : "320600"
        },{
        "optionDesc" : "连云港市",
        "optionId" : "320700"
        },{
        "optionDesc" : "淮安市",
        "optionId" : "320800"
        },{
        "optionDesc" : "盐城市",
        "optionId" : "320900"
        },{
        "optionDesc" : "扬州市",
        "optionId" : "321000"
        },{
        "optionDesc" : "镇江市",
        "optionId" : "321100"
        },{
        "optionDesc" : "泰州市",
        "optionId" : "321200"
        },{
        "optionDesc" : "宿迁市",
        "optionId" : "321300"
        }],
        "value" : ${pdfn:toJsonString(doctorRecruit.placeId)}
        },
        {
        "inputId" : "orgFlow",
        "inputType" : "select",
        "label" : "培训基地",
        "readonly" : false,
        "required" : true,
        "options" : [],
        "value" : ${pdfn:toJsonString(doctorRecruit.orgFlow)}
        }
        <c:if test="${doctorRecruit.catSpeName eq '住院医师'}">
            , {
            "inputId" : "jointOrgFlow",
            "inputType" : "select",
            "label" : "协同基地",
            "readonly" : false,
            "required" : false,
            "options" : [],
            "value" : ${pdfn:toJsonString(doctorRecruit.jointOrgFlow)}
            }
        </c:if>
       ,
        {
        "inputId" : "catSpeName",
        "inputType" : "text",
        "label" : "培训类别",
        "readonly" : true,
        "required" : true,
        "value" : ${pdfn:toJsonString(doctorRecruit.catSpeName)}
        },
        {
        "inputId" : "speId",
        "inputType" : "select",
        "label" : "培训专业",
        "readonly" : false,
        "required" : true,
        "options" : [],
        "value" : ${pdfn:toJsonString(doctorRecruit.speId)}
        },
        {
        "inputId" : "trainYear",
        "inputType" : "select",
        "label" : "培养年限",
        "readonly" : false,
        "required" : true,
        "value" : ${pdfn:toJsonString(doctorRecruit.trainYear)},
        "options": [
        <c:forEach items="${jsResTrainYearEnumList}" var="dict" varStatus="status">
            {
            "optionId": ${pdfn:toJsonString(dict.id)},
            "optionDesc": ${pdfn:toJsonString(dict.name)}
            }
            <c:if test="${not status.last }">
                ,
            </c:if>
        </c:forEach>
        ]
        },
        {
        "inputId" : "yetTrainYear",
        "inputType" : "select",
        "label" : "已培养年限",
        "readonly" : false,
        "required" : true,
        "value" : ${pdfn:toJsonString(doctorRecruit.yetTrainYear)},
        "options": [
        <c:forEach items="${dictTypeEnumYetTrainYearList}" var="dict" varStatus="status">
            {
            "optionId": ${pdfn:toJsonString(dict.dictId)},
            "optionDesc": ${pdfn:toJsonString(dict.dictName)}
            }
            <c:if test="${not status.last }">
                ,
            </c:if>
        </c:forEach>
        ]
        },
        {
        "inputId" : "doctorStatusId",
        "inputType" : "select",
        "label" : "医师状态",
        "readonly" : false,
        "required" : true,
        "value" : ${pdfn:toJsonString(doctorRecruit.doctorStatusId)},
        "options": [
        <c:forEach items="${dictTypeEnumDoctorStatusList}" var="dict" varStatus="status">
            {
            "optionId": ${pdfn:toJsonString(dict.dictId)},
            "optionDesc": ${pdfn:toJsonString(dict.dictName)}
            <c:if test="${dict.dictId eq '21'}">
            ,"item":[{
                "inputId" : "prevCompleteCertNo",
                "inputType" : "text",
                "label" : "结业证书编号",
                "readonly" : false,
                "required" : true,
                "value" : "${doctorRecruit.specialCertNo}"
                },{
                "inputId" : "prevCompleteFileUrl",
                "inputType" : "img",
                "label" : "结业证书附件",
                "readonly" : false,
                "required" : true,
                "value" : "${doctorRecruit.specialFileUrl}"
                }]
            </c:if>
            }
            <c:if test="${not status.last }">
                ,
            </c:if>
        </c:forEach>
        ]
        },
        {
        "inputId" : "doctorStrikeId",
        "inputType" : "select",
        "label" : "医师走向",
        "readonly" : false,
        "required" : true,
        "value" : ${pdfn:toJsonString(doctorRecruit.doctorStrikeId)},
        "options": [
        <c:forEach items="${dictTypeEnumDoctorStrikeList}" var="dict" varStatus="status">
            {
            "optionId": ${pdfn:toJsonString(dict.dictId)},
            "optionDesc": ${pdfn:toJsonString(dict.dictName)}
            }
            <c:if test="${not status.last }">
                ,
            </c:if>
        </c:forEach>
        ]
        }
        <c:if test="${not empty doctorRecruit.admitNotice}">
            ,{
            "inputId" : "admitNotice",
            "inputType" : "text",
            "label" : "基地意见",
            "readonly" : true,
            "required" : true,
            "value" : ${pdfn:toJsonString(doctorRecruit.admitNotice)}
            }
        </c:if>
        <c:if test="${not empty doctorRecruit.globalNotice}">
            ,{
            "inputId" : "globalNotice",
            "inputType" : "text",
            "label" : "省厅意见",
            "readonly" : true,
            "required" : true,
            "value" : ${pdfn:toJsonString(doctorRecruit.globalNotice)}
            }
        </c:if>

        ]
    </c:if>
}
