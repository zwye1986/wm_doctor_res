<!-- 历史 -->
<div class="statistics-row row-doctor-history">
    <div class="statistics-row-item">
        <div>住院医师</div>
        <div><span
                class="statistics-row-num zl-blue">${empty summaryCount["doctorTraining"] ? 0 : summaryCount["doctorTraining"]}</span>人
        </div>
    </div>
    <div class="statistics-row-item">
        <div>在校专硕</div>
        <div><span
                class="statistics-row-num zl-blue">${empty summaryCount["graduate"] ? 0 : summaryCount["graduate"]}</span>人
        </div>
    </div>
</div>

<div class="table-container">
    <table cellspacing="0" border="1" class="table">
        <thead>
        <tr>
            <td class="table-title-header" rowspan="2">
                <span class="table-title-left">培训基地</span>
                <span class="slash"></span>
                <span class="table-title-right">专业基地</span>
            </td>
            <c:set var="dictName" value="dictTypeEnumDoctorTrainingSpeList"/>
            <c:forEach items="${applicationScope[dictName] }" var="dict">
                <td colspan="2" <c:if test="${dict.dictId eq speId}">style="background: lightgrey" </c:if>>${dict.dictName}</td>
                <td colspan="2" <c:if test="${dict.dictId eq speId}">style="background: lightgrey" </c:if> rowspan="2" class="total-td">合计</td>
            </c:forEach>
            <td colspan="2" rowspan="2" class="total-all-td">总计</td>
        </tr>
        <tr>
            <c:set var="dictName" value="dictTypeEnumDoctorTrainingSpeList"/>
            <c:forEach items="${applicationScope[dictName] }" var="dict">
                <td <c:if test="${dict.dictId eq speId}">style="background: lightgrey" </c:if>>住院医师</td>
                <td <c:if test="${dict.dictId eq speId}">style="background: lightgrey" </c:if>>在校专硕</td>
            </c:forEach>
        </tr>
        </thead>
        <!-- 主体 -->
        <tbody>
        <c:forEach items="${searchOrgList}" var="org">
            <c:set var="orgFlow" value="${org.orgFlow}"/>
            <tr>
                <td>${org.orgName}</td>
                <c:set var="dictName" value="dictTypeEnumDoctorTrainingSpeList"/>
                <c:forEach items="${applicationScope[dictName] }" var="dict">
                    <c:set var="doctorTraining" value="${org.orgFlow}${dict.dictId}doctorTraining"/>
                    <c:set var="graduate" value="${org.orgFlow}${dict.dictId}graduate"/>
                    <c:set var="all" value="${org.orgFlow}${dict.dictId}all"/>
                    <c:set var="orgAll" value="${org.orgFlow}all"/>
                    <td <c:if test="${isModify eq 'Y'}">onclick="modifyHistoryData('doctorTraining', '${org.orgFlow}', '${dict.dictId}');"</c:if> <c:if test="${dict.dictId eq speId}">style="background: lightgrey" </c:if>>${empty speAll[doctorTraining] ? 0 : speAll[doctorTraining]}</td>
                    <td <c:if test="${isModify eq 'Y'}">onclick="modifyHistoryData('graduate', '${org.orgFlow}', '${dict.dictId}');" </c:if> <c:if test="${dict.dictId eq speId}">style="background: lightgrey" </c:if>>${empty speAll[graduate] ? 0 : speAll[graduate]}</td>
                    <td <c:if test="${dict.dictId eq speId}">style="background: lightgrey" </c:if> colspan="2" class="total-td-text">${empty speAll[all] ? 0 : speAll[all]}</td>
                </c:forEach>
                <td class="total-all-td" colspan="2">${empty speAll[orgAll] ? 0 : speAll[orgAll]}</td>
            </tr>
        </c:forEach>
        </tbody>

        <!-- footer -->
        <tfoot>
        <td>总计</td>
        <c:set var="dictName" value="dictTypeEnumDoctorTrainingSpeList"/>
        <c:forEach items="${applicationScope[dictName] }" var="dict">
            <c:set var="doctorTraining" value="${dict.dictId}doctorTraining"/>
            <c:set var="graduate" value="${dict.dictId}graduate"/>
            <c:set var="all" value="${dict.dictId}all"/>
            <td>${empty speAll[doctorTraining] ? 0 : speAll[doctorTraining]}</td>
            <td>${empty speAll[graduate] ? 0 : speAll[graduate]}</td>
            <td colspan="2">${empty speAll[all] ? 0 : speAll[all]}</td>
        </c:forEach>
        <td colspan="2"
            class="total-all-td red">${(empty summaryCount["doctorTraining"] ? 0 : summaryCount["doctorTraining"]) + (empty summaryCount["graduate"] ? 0 : summaryCount["graduate"])}</td>
        </tfoot>
    </table>
</div>
<div class="table-container">
    <c:if test="${not empty remarks}">
        备注：${remarks}
    </c:if>
</div>


