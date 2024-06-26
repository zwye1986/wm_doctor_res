<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<style type="text/css">
</style>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script src="<s:url value='/js/yearSelect/checkYear.js'/>" type="text/javascript"></script>
<script type="text/javascript">
    function showCheckReductionInfo(recordFlow) {
        var url = "<s:url value='/jsres/reduction/showCheckReductionInfo'/>?roleId=${roleId}&recordFlow=" + recordFlow;
        jboxOpen(url, "审核", 500, 300);
    }

    function doctorPassedList(doctorFlow, recruitFlow) {
        var hideApprove = "hideApprove";
        var url = "<s:url value='/jsres/manage/province/doctor/doctorPassedList'/>?info=${GlobalConstant.FLAG_Y}&liId=" + recruitFlow + "&recruitFlow=" + recruitFlow + "&openType=open&doctorFlow=" + doctorFlow + "&hideApprove=" + hideApprove;
        jboxOpen(url, "学员信息", 1050, 550);

    }

    function checkRecruitAll(obj) {
        if (obj.checked) {
            $(":checkbox[name='reductionCheck']").attr("checked", true);
        } else {
            $(":checkbox[name='reductionCheck']").attr("checked", false);
        }
    }

    function showCheckReductionInfo(recordFlow) {
        var url = "<s:url value='/jsres/reduction/showCheckReductionInfo'/>?roleId=${roleId}&recordFlow=" + recordFlow;
        jboxOpen(url, "审核", 500, 300);
    }
</script>
<div class="main_bd">
    <div style="padding-bottom: 200px;">
        <div class="search_table">
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
                <%--<c:if test="${operType eq 'isQuery'}">--%>
                    <%--<colgroup>--%>
                        <%--<col width="10%"/>--%>
                        <%--<col width="10%"/>--%>
                        <%--<col width="10%"/>--%>
                        <%--<col width="12%"/>--%>
                        <%--<col width="12%"/>--%>
                        <%--<col width="12%"/>--%>
                        <%--<col width="12%"/>--%>
                        <%--<col width="12%"/>--%>
                        <%--<col width="12%"/>--%>
                        <%--<col width="12%"/>--%>
                    <%--</colgroup>--%>
                <%--</c:if>--%>
                <%--<c:if test="${operType eq 'isCheck'}">--%>
                    <%--<colgroup>--%>
                        <%--<col width="15%"/>--%>
                        <%--<col width="20%"/>--%>
                        <%--<col width="15%"/>--%>
                        <%--<col width="20%"/>--%>
                        <%--<col width="20%"/>--%>
                        <%--<col width="20%"/>--%>
                        <%--<col width="20%"/>--%>
                        <%--<col width="20%"/>--%>
                        <%--<col width="15%"/>--%>
                    <%--</colgroup>--%>
                <%--</c:if>--%>
                <thead>
                <tr>
                    <c:if test="${not empty resDoctorReductions and operType eq 'isCheck'}">
                        <th width="2%"><input type="checkbox" onclick="checkRecruitAll(this);"></th>
                    </c:if>
                    <th>姓名</th>
                    <th>证件号码</th>
                    <th>基地</th>
                    <th>培训专业</th>
                    <th>申请减免附件</th>
                    <th>申请减免年限</th>
                    <th>基地审核意见</th>
                    <c:if test="${operType eq 'isQuery'}">
                        <th>省厅审核意见</th>
                        <th>审核状态</th>
                        <th>操作</th>
                    </c:if>
                    <c:if test="${operType eq 'isCheck'}">
                        <th>操作</th>
                    </c:if>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${resDoctorReductions}" var="reduction">
                    <tr>
                        <c:if test="${not empty resDoctorReductions and operType eq 'isCheck'}">
                            <td><input type="checkbox" name="reductionCheck" value="${reduction.recordFlow}"></td>
                        </c:if>
                        <td>${reduction.userName}</td>
                        <td>${reduction.idNo}</td>
                        <td>${reduction.orgName}</td>
                        <td>${reduction.trainingSpeName}</td>
                        <td>
                            <c:if test="${not empty reductionFilePaths[reduction.recordFlow]}">
                                <a href="${sysCfgMap['upload_base_url']}/${reductionFilePaths[reduction.recordFlow]}"
                                   target="_blank">查看减免证明</a>
                            </c:if>
                            <c:if test="${empty reductionFilePaths[reduction.recordFlow]}">
                                <c:forEach items="${reductionFileMaps[reduction.recordFlow]}" var="reductionFile">
                                    <span title="${reductionFile.fileName}">
                                        <a href="${sysCfgMap['upload_base_url']}/${reductionFile.filePath}"
                                           target="_blank">${pdfn:cutString(reductionFile.fileName,10,true,6)}</a>
                                    </span>
                                    <br/>
                                </c:forEach>
                            </c:if>
                            <c:if test="${empty reductionFilePaths[reduction.recordFlow] and empty reductionFileMaps[reduction.recordFlow]}">
                                暂无
                            </c:if>
                        </td>
                        <td>${reduction.reduceYear}年</td>
                        <td title="${pdfn:transDate(reduction.localAuditTime)}">${empty reduction.localAuditOpinion?"无": pdfn:cutString(reduction.localAuditOpinion,10,true,3)}</td>
                        <c:if test="${operType eq 'isQuery'}">
                            <td title="${pdfn:transDate(reduction.globalAuditTime)}">${empty reduction.globalAuditOpinion?"无": pdfn:cutString(reduction.globalAuditOpinion,10,true,3)}</td>
                            <td>${reduction.auditStatusName}</td>
                            <td width="10%">
                                <a class="btn show" onclick="doctorPassedList('${reduction.doctorFlow}','${reduction.recruitFlow}');">详情</a>
                            </td>
                        </c:if>
                        <c:if test="${operType eq 'isCheck'}">
                            <td>
                                <a class="btn show" onclick="doctorPassedList('${reduction.doctorFlow}','${reduction.recruitFlow}');">详情</a>
                                <a class="btn"
                                   onclick="showCheckReductionInfo('${reduction.recordFlow}');">审核</a>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
                <c:if test="${empty resDoctorReductions}">
                    <tr>
                        <td colspan="99">无记录</td>
                    </tr>
                </c:if>
                </tbody>
            </table>

        </div>
        <div class="page" style="padding-right: 40px;">
            <c:set var="pageView" value="${pdfn:getPageView(resDoctorReductions)}" scope="request"></c:set>
            <pd:pagination-jsres toPage="toPage"/>
        </div>

    </div>

</div>
