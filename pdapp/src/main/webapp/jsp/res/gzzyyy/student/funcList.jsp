<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="cKey" value="${param.deptFlow}CaseRegistry"/>
<c:set var="chKey" value="${param.deptFlow}CaseRegistryHandwriting"/>
<c:set var="ecKey" value="${param.deptFlow}EmergencyCase"/>
<c:set var="drKey" value="${param.deptFlow}DiseaseRegistry"/>
<c:set var="saoKey" value="${param.deptFlow}SkillAndOperationRegistry"/>
<c:set var="hlKey" value="${param.deptFlow}HospitalizationLog"/>
<c:set var="cmKey" value="${param.deptFlow}CampaignNoItemRegistry"/>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"funcList": [
        {
            "funcTypeId": "dataInput1N",
            "funcTypeName": "数据列表",
            "funcFlow": "CaseRegistry",
            "funcName": "大病历",
            "disabled": ${disabled},
            "disabledTip": ${pdfn:toJsonString(disabledTip)},
            "img": "Study",
            "progress":${pdfn:toJsonString(perMap[cKey]+0)}
        },
        {
            "funcTypeId": "dataInput1N",
            "funcTypeName": "数据列表",
            "funcFlow": "CaseRegistryHandwriting",
            "funcName": "手写大病历",
            "disabled": ${disabled},
            "disabledTip": ${pdfn:toJsonString(disabledTip)},
            "img": "Study",
            "progress":${pdfn:toJsonString(perMap[chKey]+0)}
        },
        {
            "funcTypeId": "dataInput1N",
            "funcTypeName": "门急诊病例",
            "funcFlow": "EmergencyCase",
            "funcName": "门急诊病例",
            "disabled": ${disabled},
            "disabledTip": ${pdfn:toJsonString(disabledTip)},
            "img": "Study",
            "progress":${pdfn:toJsonString(perMap[ecKey]+0)}
        },
        {
            "funcTypeId": "dataInputNN",
            "funcTypeName": "数据列表",
            "funcFlow": "DiseaseRegistry",
            "funcName": "病种",
            "disabled": ${disabled},
            "disabledTip": ${pdfn:toJsonString(disabledTip)},
            "img": "Clinical",
            "progress":${pdfn:toJsonString(perMap[drKey]+0)}
        },
        {
            "funcTypeId": "dataInputNN",
            "funcTypeName": "数据列表",
            "funcFlow": "SkillAndOperationRegistry",
            "funcName": "操作技能和手术",
            "disabled": ${disabled},
            "disabledTip": ${pdfn:toJsonString(disabledTip)},
            "img": "Clinical",
            "progress":${pdfn:toJsonString(perMap[saoKey]+0)}
        },
        {
            "funcTypeId": "dataInput1N",
            "funcTypeName": "数据列表",
            "funcFlow": "HospitalizationLog",
            "funcName": "住院志",
            "disabled": ${disabled},
            "disabledTip": ${pdfn:toJsonString(disabledTip)},
            "img": "Teaching",
            "progress":${pdfn:toJsonString(perMap[hlKey]+0)}
        },
        {
            "funcTypeId": "dataInput1N",
            "funcTypeName": "数据列表",
            "funcFlow": "CampaignNoItemRegistry",
            "funcName": "参加活动",
            "disabled": ${disabled},
            "disabledTip": ${pdfn:toJsonString(disabledTip)},
            "img": "Teaching",
            "progress":${pdfn:toJsonString(perMap[cmKey]+0)}
        },
        {
            "funcTypeId": "dataInput11",
            "funcTypeName": "数据输入",
            "funcFlow": "DOPS",
            "funcName": "临床操作技能评估量化表",
            "disabled": ${disabled},
            "disabledTip": ${pdfn:toJsonString(disabledTip)},
            "img": "Education"
        },
        {
            "funcTypeId": "dataInput11",
            "funcTypeName": "数据输入",
            "funcFlow": "Mini_CEX",
            "funcName": "迷你临床演练评估量化表",
            "disabled": ${disabled},
            "disabledTip": ${pdfn:toJsonString(disabledTip)},
            "img": "Education"
        },
        <%--<c:if test="${isRotationFlag eq 'Y'}">--%>
        <%--{--%>
            <%--"funcTypeId": "dataInput11",--%>
            <%--"funcTypeName": "数据输入",--%>
            <%--"funcFlow": "TeacherGrade",--%>
            <%--"funcName": "评价老师",--%>
            <%--"disabled": ${disabled},--%>
            <%--"disabledTip": ${pdfn:toJsonString(disabledTip)},--%>
            <%--"img": "Teacher"--%>
        <%--},--%>
        <%--{--%>
            <%--"funcTypeId": "dataInput11",--%>
            <%--"funcTypeName": "数据输入",--%>
            <%--"funcFlow": "DeptGrade",--%>
            <%--"funcName": "评价科室",--%>
            <%--"disabled": ${disabled},--%>
            <%--"disabledTip": ${pdfn:toJsonString(disabledTip)},--%>
            <%--"img": "Dept"--%>
        <%--},--%>
        <%--</c:if>--%>
        <%--<c:if test="${ckk}">--%>
            <%--{--%>
            <%--"funcTypeId": "joinExam",--%>
            <%--"funcTypeName": "参加出科考试",--%>
            <%--"funcFlow": "joinExam",--%>
            <%--"funcName": "${process.schDeptName}出科考核",--%>
            <%--"disabled": ${disabled},--%>
            <%--"disabledTip": ${pdfn:toJsonString(disabledTip)},--%>
            <%--"img": "ckk"--%>
            <%--},--%>
        <%--</c:if>--%>
        {
            "funcTypeId": "dataInput11",
            "funcTypeName": "数据输入",
            "funcFlow": "AfterSummary",
            "funcName": "出科小结",
            <c:set var="canclick" value="${pdfn:isWriteAfterSummary(disabled,process.processFlow)}"/>
            "disabled": ${canclick},
            <c:set var="tip" value="${disabledTip}"/>
            <c:if test="${canclick}">
                <c:set var="tip" value="请先对带教老师和科室进行评价！"/>
            </c:if>
            "disabledTip": ${pdfn:toJsonString(tip)},
            "img": "Education"
        }
        <c:if test="${isAppFlag eq 'Y'}">
        ,
        {
            "funcTypeId": "dataInput11",
            "funcTypeName": "数据输入",
            "funcFlow": "AfterEvaluation",
            "funcName": "出科考核表",
            <c:set value="${empty afterEva}" var="canclick"></c:set>
            "disabled": ${canclick},
            <c:set var="tip" value="${disabledTip}"/>
            <c:if test="${canclick}">
                <c:set var="tip" value="带教老师暂未审核出科考核表！"/>
            </c:if>
            "disabledTip": ${pdfn:toJsonString(tip)},
            "img": "Teacher"
        }
        </c:if>
        <c:if test="${haveMonth eq 'Y'}">
        ,
        <c:set value="${empty clinicRec and empty areaRec}" var="canclick"></c:set>
        <c:set var="funcFlow" value="${clinicRec.recTypeId}"></c:set>
        <c:set var="funcNmae" value="${clinicRec.recTypeName}"></c:set>
        <c:if test="${empty funcFlow}">
            <c:set var="funcFlow" value="${areaRec.recTypeId}"    ></c:set>
            <c:set var="funcNmae" value="${areaRec.recTypeName}"></c:set>
        </c:if>
            <c:if test="${empty funcNmae}">
                <c:set var="funcNmae" value="月度考核表"></c:set>
            </c:if>

        {
            "funcTypeId": "dataInputMonth",
            "funcTypeName": "数据输入",
            "funcFlow": "${funcFlow}",
            "funcName": "${funcNmae}",
            "disabled": ${canclick},
            <c:set var="tip" value="${disabledTip}"/>
            <c:if test="${canclick}">
                <c:set var="tip" value="带教老师暂未审核月度考核表！"/>
            </c:if>
            "disabledTip": ${pdfn:toJsonString(tip)},
            "img": "Teacher"
        }
        </c:if>
    ]
    </c:if>
}
