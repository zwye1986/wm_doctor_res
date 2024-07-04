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
            "readonly" : false,
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
        "readonly" : false,
        "required" : true,
        "value" : ${pdfn:toJsonString(doctorRecruit.sessionNumber)}
        },
        {
        "inputId" : "catSpeName",
        "inputType" : "text",
        "label" : "培训类别",
        "readonly" : false,
        "required" : true,
        "value" : ${pdfn:toJsonString(doctorRecruit.catSpeName)}
        },
        {
        "inputId" : "speName",
        "inputType" : "text",
        "label" : "培训专业",
        "readonly" : false,
        "required" : true,
        "value" : ${pdfn:toJsonString(doctorRecruit.speName)}
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

        ]
    </c:if>
}
