<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="cKey" value="${param.deptFlow}CaseRegistry"/>
<c:set var="drKey" value="${param.deptFlow}DiseaseRegistry"/>
<c:set var="orKey" value="${param.deptFlow}OperationRegistry"/>
<c:set var="srKey" value="${param.deptFlow}SkillRegistry"/>
<c:set var="cmKey" value="${param.deptFlow}CampaignNoItemRegistry"/>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"funcList": [
        <c:if test="${isMonthOpen eq 'Y'}">
	 	{
            "funcTypeId": "inProcessInfo",
            "funcTypeName": "入科教育信息规范",
            "funcFlow": "inProcessInfo",
            "funcName": "${process.schDeptName}入科教育信息规范",
            "disabled": ${not empty inProcessInfo},
            "disabledTip": ${pdfn:toJsonString(inProcessInfo)},
            "img": "rukejiaoyu"
        },
        </c:if>
	 	<%--{--%>
            <%--"funcTypeId": "qrCode",--%>
            <%--"funcTypeName": "二维码功能",--%>
            <%--"funcFlow": "signin",--%>
            <%--"funcName": "每日签到",--%>
            <%--"disabled": ${disabled},--%>
            <%--"disabledTip": ${pdfn:toJsonString(disabledTip)},--%>
            <%--"img": "Education",--%>
            <%--"lastSignin": ${pdfn:toJsonString(lastSignin)}--%>
        <%--},--%>
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
            "funcFlow": "SkillRegistry",
            "funcName": "操作技能",
            "disabled": ${disabled},
            "disabledTip": ${pdfn:toJsonString(disabledTip)},
            "img": "Clinical",
            "progress":${pdfn:toJsonString(perMap[srKey]+0)}
        },
        {
            "funcTypeId": "dataInputNN",
            "funcTypeName": "数据列表",
            "funcFlow": "OperationRegistry",
            "funcName": "手术",
            "disabled": ${disabled},
            "disabledTip": ${pdfn:toJsonString(disabledTip)},
            "img": "Clinical",
            "progress":${pdfn:toJsonString(perMap[orKey]+0)}
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
    ]
    </c:if>
}
