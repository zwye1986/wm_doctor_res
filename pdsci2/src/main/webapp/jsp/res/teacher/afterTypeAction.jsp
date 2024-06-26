
<c:set var="showKey" value="res_${after.id}_form_flag"/>
<c:set var="key" value="${process.processFlow}${after.id}"/>
<c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap[showKey]}">
    <c:set var="isShow" value="false"/>
    <c:set var="cfgKey" value="jswjw_${result.orgFlow}_P013"/>
    <%--//带教--%>
    <c:if test="${GlobalConstant.RES_ROLE_SCOPE_TEACHER eq roleFlag}">
        <c:if test="${(afterRecTypeEnumAfterSummary.id eq after.id || afterRecTypeEnumDiscipleSummary.id eq after.id)
         && !empty recMap[key] }">
            <c:set var="isShow" value="true"/>
        </c:if>
        <c:if test="${!afterRecTypeEnumAfterSummary.id eq after.id &&! afterRecTypeEnumDiscipleSummary.id eq after.id
            &&!resRecTypeEnumDeptEval.id eq after.id &&! afterRecTypeEnumMonthlyAssessment_clinic.id eq after.id
            &&! afterRecTypeEnumMonthlyAssessment_inpatientArea.id eq after.id}">
            <c:set var="isShow" value="true"/>
        </c:if>
        <c:if test="${( afterRecTypeEnumMonthlyAssessment_clinic.id eq after.id
            || afterRecTypeEnumMonthlyAssessment_inpatientArea.id eq after.id) and isMonthOpen eq 'Y'}">
            <c:set var="isShow" value="true"/>
        </c:if>
        <c:if test="${ resRecTypeEnumDeptEval.id eq after.id  }">
            <c:if test="${sysCfgMap[cfgKey] eq 'Y'and sysCfgMap['res_isGlobalSch_flag'] ne 'Y'}">
                <c:set var="isShow" value="true"/>
            </c:if>
        </c:if>
    </c:if>
    <%--非带教--%>
    <c:if test="${GlobalConstant.RES_ROLE_SCOPE_TEACHER ne roleFlag}">
        <c:if test="${(afterRecTypeEnumAfterSummary.id eq after.id || afterRecTypeEnumDiscipleSummary.id eq after.id)
         && !empty recMap[key] }">
            <c:set var="isShow" value="true"/>
        </c:if>
        <c:if test="${!afterRecTypeEnumAfterSummary.id eq after.id &&! afterRecTypeEnumDiscipleSummary.id eq after.id
            &&!resRecTypeEnumDeptEval.id eq after.id &&! afterRecTypeEnumMonthlyAssessment_clinic.id eq after.id
            &&! afterRecTypeEnumMonthlyAssessment_inpatientArea.id eq after.id}">
            <c:set var="isShow" value="true"/>
        </c:if>
        <c:if test="${( afterRecTypeEnumMonthlyAssessment_clinic.id eq after.id
            || afterRecTypeEnumMonthlyAssessment_inpatientArea.id eq after.id) and isMonthOpen eq 'Y'}">
            <c:set var="isShow" value="true"/>
        </c:if>
        <c:if test="${ resRecTypeEnumDeptEval.id eq after.id  }">
            <c:if test="${sysCfgMap[cfgKey] eq 'Y'and sysCfgMap['res_isGlobalSch_flag'] ne 'Y'}">
                <c:set var="isShow" value="true"/>
            </c:if>
        </c:if>
    </c:if>
    <c:if test="${isShow}">
        <c:set var="color" value="blue"/>
        <c:set var="auditStatusId" value="N"/>
        <c:set var="headAuditStatusId" value="N"/>
        <c:if test="${!empty recMap[key].auditStatusId}">
            <c:set var="auditStatusId" value="Y"/>
        </c:if>
        <c:if test="${!empty recMap[key].headAuditStatusId }">
            <c:set var="headAuditStatusId" value="Y"/>
        </c:if>
        <c:if test="${(afterRecTypeEnumAfterSummary.id eq after.id
        || afterRecTypeEnumDiscipleSummary.id eq after.id
        || afterRecTypeEnumAfterEvaluation.id eq after.id
        || afterRecTypeEnumMonthlyAssessment_clinic.id eq after.id
        || afterRecTypeEnumMonthlyAssessment_inpatientArea.id eq after.id
        || resRecTypeEnumDeptEval.id eq after.id) }">
            <c:if test="${GlobalConstant.RES_ROLE_SCOPE_TEACHER eq roleFlag && !empty recMap[key].auditStatusId}">
                <c:set var="color" value="black"/>
            </c:if>
            <c:if test="${GlobalConstant.RES_ROLE_SCOPE_HEAD eq roleFlag && !empty recMap[key].headAuditStatusId}">
                <c:set var="color" value="black"/>
            </c:if>
            <c:if test="${GlobalConstant.RES_ROLE_SCOPE_MANAGER eq roleFlag && !empty recMap[key].managerAuditStatusId}">
                <c:set var="color" value="black"/>
            </c:if>
        </c:if>
        <c:if test="${(afterRecTypeEnumDOPS.id eq after.id
        || afterRecTypeEnumMini_CEX.id eq after.id) }">
            <c:if test="${GlobalConstant.RES_ROLE_SCOPE_TEACHER eq roleFlag && !empty recMap[key].auditStatusId}">
                <c:set var="color" value="black"/>
            </c:if>
            <c:if test="${GlobalConstant.RES_ROLE_SCOPE_HEAD eq roleFlag  }">
                <c:set var="color" value="black"/>
            </c:if>
            <c:if test="${GlobalConstant.RES_ROLE_SCOPE_MANAGER eq roleFlag  }">
                <c:set var="color" value="black"/>
            </c:if>
        </c:if>

        <c:if test="${afterRecTypeEnumDeptEval.id ne after.id }">
            <a id="${after.id}" style="color: ${color};cursor:pointer;width: 48%;float: left" auditStatusId="${auditStatusId}" headAuditStatusId="${headAuditStatusId}"
               onclick="opRec('${recMap[key].recFlow}','${process.schDeptFlow}','${doctor.rotationFlow}','${process.userFlow}','${process.schResultFlow}','${after.id}','${after.name}','${process.processFlow}','${process.schDeptName}');">${after.name}
            </a>
        </c:if>
        <c:if test="${afterRecTypeEnumDeptEval.id eq after.id }">
            <a id="${after.id}" style="color: ${color};cursor:pointer;width: 48%;float: left" auditStatusId="${auditStatusId}" headAuditStatusId="${headAuditStatusId}"
               onclick="deptEval('${resRecTypeEnumDeptEval.name}','${resRecTypeEnumDeptEval.id}','${recMap[key].recFlow}','${process.processFlow}','${process.userFlow}');">${resRecTypeEnumDeptEval.name}
            </a>
        </c:if>
    </c:if>
</c:if>