    <div class="statistics-row row-doctor">
        <div class="statistics-row-item">
            <div>应结业学员</div>
            <div><span class="statistics-row-num zl-blue">${empty statisticsMap['graduationNum'] ? 0 : statisticsMap['graduationNum']}</span>人</div>
        </div>
        <div class="statistics-row-item">
            <div>已申请学员</div>
            <div><span class="statistics-row-num zl-blue">${empty statisticsMap['applyNum'] ? 0 : statisticsMap['applyNum']}</span>人</div>
        </div>
        <div class="statistics-row-item">
            <div>基地审核通过</div>
            <div><span class="statistics-row-num zl-blue">${empty statisticsMap['localAuditNum'] ? 0 : statisticsMap['localAuditNum']}</span>人</div>
        </div>
        <div class="statistics-row-item">
            <div>市局审核通过</div>
            <div><span class="statistics-row-num zl-blue">${empty statisticsMap['cityAuditNum'] ? 0 : statisticsMap['cityAuditNum']}</span>人</div>
        </div>
        <div class="statistics-row-item">
            <div>省厅审核通过</div>
            <div><span class="statistics-row-num zl-blue">${empty statisticsMap['globalAuditNum'] ? 0 : statisticsMap['globalAuditNum']}</span>人</div>
        </div>
    </div>

    <div class="table-container">
        <table class="grid">
            <tr>
                <th>基地编号</th>
                <th>基地名称</th>
                <th>应结业学员</th>
                <th>已申请学员</th>
                <th>基地审核通过</th>
                <th>市局审核通过</th>
                <th>省厅审核通过</th>
            </tr>
            <c:set var="graduationNum" value="0" />
            <c:set var="applyNum" value="0" />
            <c:set var="localAuditNum" value="0" />
            <c:set var="cityAuditNum" value="0" />
            <c:set var="globalAuditNum" value="0" />
            <c:choose>
                <c:when test="${catSpeId == 'DoctorTrainingSpe'}">
                    <c:forEach items="${orgInfoList}" var="orgInfo">
                        <tr>
                            <td>${orgInfo.orgCode}</td>
                            <td>${orgInfo.orgName}</td>
                            <c:set var="existsFlag" value="N" />
                            <c:forEach items="${graduationDoctorList}" var="graduationDoctor">
                                <c:if test="${graduationDoctor.orgCode == orgInfo.orgCode}">
                                    <c:set var="existsFlag" value="Y" />
                                    <td>
                                        ${graduationDoctor.graduationNum}
                                        <c:set var="graduationNum" value="${graduationNum + graduationDoctor.graduationNum}" />
                                    </td>
                                    <td>
                                        ${graduationDoctor.applyNum}
                                        <c:set var="applyNum" value="${applyNum + graduationDoctor.applyNum}" />
                                    </td>
                                    <td>
                                        ${graduationDoctor.localAuditNum}
                                        <c:set var="localAuditNum" value="${localAuditNum + graduationDoctor.localAuditNum}" />
                                    </td>
                                    <td>
                                        ${graduationDoctor.cityAuditNum}
                                        <c:set var="cityAuditNum" value="${cityAuditNum + graduationDoctor.cityAuditNum}" />
                                    </td>
                                    <td>
                                        ${graduationDoctor.globalAuditNum}
                                        <c:set var="globalAuditNum" value="${globalAuditNum + graduationDoctor.globalAuditNum}" />
                                    </td>
                                </c:if>
                            </c:forEach>
                            <c:if test="${existsFlag == 'N'}">
                                <td>0</td>
                                <td>0</td>
                                <td>0</td>
                                <td>0</td>
                                <td>0</td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${graduationDoctorList}" var="graduationDoctor">
                        <tr>
                            <td>${graduationDoctor.orgCode}</td>
                            <td>${graduationDoctor.orgName}</td>
                            <td>
                                ${graduationDoctor.graduationNum}
                                <c:set var="graduationNum" value="${graduationNum + graduationDoctor.graduationNum}" />
                            </td>
                            <td>
                                ${graduationDoctor.applyNum}
                                <c:set var="applyNum" value="${applyNum + graduationDoctor.applyNum}" />
                            </td>
                            <td>
                                ${graduationDoctor.localAuditNum}
                                <c:set var="localAuditNum" value="${localAuditNum + graduationDoctor.localAuditNum}" />
                            </td>
                            <td>
                                ${graduationDoctor.cityAuditNum}
                                <c:set var="cityAuditNum" value="${cityAuditNum + graduationDoctor.cityAuditNum}" />
                            </td>
                            <td>
                                ${graduationDoctor.globalAuditNum}
                                <c:set var="globalAuditNum" value="${globalAuditNum + graduationDoctor.globalAuditNum}" />
                            </td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            <tfoot>
                <tr>
                    <td>总计</td>
                    <td></td>
                    <td>
                        ${graduationNum}
                    </td>
                    <td>
                        ${applyNum}
                    </td>
                    <td>
                        ${localAuditNum}
                    </td>
                    <td>
                        ${cityAuditNum}
                    </td>
                    <td>
                        ${globalAuditNum}
                    </td>
                </tr>
            </tfoot>
        </table>
    </div>