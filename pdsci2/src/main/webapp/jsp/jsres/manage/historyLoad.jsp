<!-- 历史 -->
<div>
    <div class="row">
        <div class="row-item">
            <div>时间节点 :&nbsp;</div>
            <select name="photoTime" id="photoTime" class="notBlank city select" style="width: 128px;margin-left: 0px;">
                <c:forEach items="${photoTimeList}" var="time">
                    <option value="${time}" <c:if test="${photoTime eq time}">selected</c:if>>${time}</option>
                </c:forEach>
            </select>
        </div>
        <div class="row-item">
            <div>培训基地 :&nbsp;</div>
            <select name="orgFlow" id="orgFlow_history" class="notBlank city select" style="width: 128px;margin-left: 0px;">
                <option value="">全部</option>
                <c:forEach items="${orgList}" var="org">
                    <option value="${org.orgFlow}"
                            <c:if test="${orgFlow eq org.orgFlow}">selected</c:if>>${org.orgName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="row-item">
            <div>专业基地 :&nbsp;</div>
            <c:set var="dictName" value="dictTypeEnumDoctorTrainingSpeList"/>
            <select name="speId" id="speId_history" class="notBlank city select" style="width: 128px;margin-left: 0px;">
                <option value="">全部</option>
                <c:forEach items="${applicationScope[dictName] }" var="dict">
                    <option value="${dict.dictId}"
                            <c:if test="${speId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>
                </c:forEach>
            </select>
        </div>

        <div class="row-item">
            <div>人员类型 :&nbsp;</div>
            <select name="docType" id="docType_history" class="notBlank city select" style="width: 128px;margin-left: 0px;">
                <option value="">全部</option>
                <c:forEach items="${jsResDocTypeEnumList}" var="type">
                    <option value="${type.id}"
                            <c:if test="${docType eq type.id}">selected</c:if>>${type.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="row">
        <button class="zl-btn" onclick="searchRecruitHistory()">搜索</button>
        <button class="zl-btn" onclick="clearSearchArgs('history')">重置</button>
        <button class="zl-btn" onclick="exportHistoryData()">导出</button>
        <button class="zl-btn" onclick="modifyRecruitHistory()">修改</button>
        <button class="zl-btn" onclick="removeRecruitHistory()">删除</button>
    </div>
</div>

<div id="history_inner">
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
                        <td <c:if test="${dict.dictId eq speId}">style="background: lightgrey" </c:if>>${empty speAll[doctorTraining] ? 0 : speAll[doctorTraining]}</td>
                        <td <c:if test="${dict.dictId eq speId}">style="background: lightgrey" </c:if>>${empty speAll[graduate] ? 0 : speAll[graduate]}</td>
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
</div>


