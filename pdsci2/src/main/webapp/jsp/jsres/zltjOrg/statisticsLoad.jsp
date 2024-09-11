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
            <td class="table-title-header">
                <span>专业基地</span>
            </td>
            <td class="table-title-header">
                <span>本单位人</span>
            </td>
            <td class="table-title-header">
                <span>委培单位人</span>
            </td>
            <td class="table-title-header">
                <span>社会人</span>
            </td>
            <td class="table-title-header">
                <span>在校专硕</span>
            </td>
            <td class="table-title-header">
                <span>合计</span>
            </td>
        </tr>
        </thead>
        <!-- 主体 -->
        <tbody>
        <c:set var="dictName" value="dictTypeEnumDoctorTrainingSpeList"/>
        <c:forEach items="${applicationScope[dictName] }" var="dict">
            <c:set var="Company" value="${dict.dictId}Company"/>
            <c:set var="CompanyEntrust" value="${dict.dictId}CompanyEntrust"/>
            <c:set var="Social" value="${dict.dictId}Social"/>
            <c:set var="Graduate" value="${dict.dictId}Graduate"/>
            <c:set var="all" value="${dict.dictId}all"/>
            <tr>
                <td>${dict.dictName}</td>
                <td>${empty speInfos[Company] ? 0 : speInfos[Company]}</td>
                <td>${empty speInfos[CompanyEntrust] ? 0 : speInfos[CompanyEntrust]}</td>
                <td>${empty speInfos[Social] ? 0 : speInfos[Social]}</td>
                <td>${empty speInfos[Graduate] ? 0 : speInfos[Graduate]}</td>
                <td>${empty speInfos[all] ? 0 : speInfos[all]}</td>
            </tr>
        </c:forEach>
        </tbody>

        <!-- footer -->
        <tfoot>
        <td>合计</td>
        <td>${empty speAll["Company"] ? 0 : speAll["Company"]}</td>
        <td>${empty speAll["CompanyEntrust"] ? 0 : speAll["CompanyEntrust"]}</td>
        <td>${empty speAll["Social"] ? 0 : speAll["Social"]}</td>
        <td>${empty speAll["Graduate"] ? 0 : speAll["Graduate"]}</td>
        <td>${empty speAll["all"] ? 0 : speAll["all"]}</td>
        </tfoot>
    </table>
</div>
