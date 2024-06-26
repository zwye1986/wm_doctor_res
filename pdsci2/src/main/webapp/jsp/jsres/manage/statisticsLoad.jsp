<!-- 统计 -->
<div class="statistics-row row-doctor">
    <div class="statistics-row-title">住院医师</div>

    <div class="statistics-row-item">
        <div>招录</div>
        <div><span class="statistics-row-num zl-blue">${empty doctorTrainingMap['all'] ? 0 : doctorTrainingMap['all']}</span>人</div>
    </div>
    <div class="statistics-row-item">
        <div>在培</div>
        <div><span class="statistics-row-num zl-blue">${empty doctorTrainingMap['20'] ? 0 : doctorTrainingMap['20']}</span>人</div>
    </div>
    <div class="statistics-row-item">
        <div>结业</div>
        <div><span class="statistics-row-num zl-blue">${empty doctorTrainingMap['21'] ? 0 : doctorTrainingMap['21']}</span>人</div>
    </div>
    <div class="statistics-row-item">
        <div>退培</div>
        <div><span class="statistics-row-num zl-blue">${empty doctorTrainingMap['23'] ? 0 : doctorTrainingMap['23']}</span>人</div>
    </div>
    <div class="statistics-row-item">
        <div>已考核待结业</div>
        <div><span class="statistics-row-num zl-blue">${empty doctorTrainingMap['22'] ? 0 : doctorTrainingMap['22']}</span>人</div>
    </div>
</div>

<div class="statistics-row row-master">
    <div class="statistics-row-title">在校专硕</div>

    <div class="statistics-row-item">
        <div>招录</div>
        <div><span class="statistics-row-num green">${empty graduateMap['all'] ? 0 : graduateMap['all']}</span>人</div>
    </div>
    <div class="statistics-row-item">
        <div>在培</div>
        <div><span class="statistics-row-num green">${empty graduateMap['20'] ? 0 : graduateMap['20']}</span>人</div>
    </div>
    <div class="statistics-row-item">
        <div>结业</div>
        <div><span class="statistics-row-num green">${empty graduateMap['21'] ? 0 : graduateMap['21']}</span>人</div>
    </div>
    <div class="statistics-row-item">
        <div>退培</div>
        <div><span class="statistics-row-num green">${empty graduateMap['23'] ? 0 : graduateMap['23']}</span>人</div>
    </div>
    <div class="statistics-row-item">
        <div>已考核待结业</div>
        <div><span class="statistics-row-num green">${empty graduateMap['22'] ? 0 : graduateMap['22']}</span>人</div>
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
            <c:set var="orgSpe" value="${orgSpeList[orgFlow]}"/>
            <tr>
                <td>${org.orgName}</td>
                <c:set var="orgSpeInfo" value="${orgSpe[orgFlow]}"/>
                <c:set var="dictName" value="dictTypeEnumDoctorTrainingSpeList"/>
                <c:forEach items="${applicationScope[dictName] }" var="dict">
                    <c:set var="doctorTraining" value="${dict.dictId}doctorTraining"/>
                    <c:set var="graduate" value="${dict.dictId}graduate"/>
                    <c:set var="all" value="${dict.dictId}all"/>
                    <td <c:if test="${dict.dictId eq speId}">style="background: lightgrey" </c:if>>${empty orgSpeInfo[doctorTraining] ? 0 : orgSpeInfo[doctorTraining]}</td>
                    <td <c:if test="${dict.dictId eq speId}">style="background: lightgrey" </c:if>>${empty orgSpeInfo[graduate] ? 0 : orgSpeInfo[graduate]}</td>
                    <td <c:if test="${dict.dictId eq speId}">style="background: lightgrey" </c:if> colspan="2" class="total-td-text">${empty orgSpeInfo[all] ? 0 : orgSpeInfo[all]}</td>
                </c:forEach>
                <td class="total-all-td" colspan="2">${empty orgSpeInfo['all'] ? 0 : orgSpeInfo['all']}</td>
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
        <td colspan="2" class="total-all-td red">${doctorTrainingMap['all'] + graduateMap['all']}</td>
        </tfoot>
    </table>
</div>
