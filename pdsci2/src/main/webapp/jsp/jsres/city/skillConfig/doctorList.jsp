<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="queryFont" value="true"/>
</jsp:include>
<script type="text/javascript">
    function toPage(page) {
        if (page) {
            $("#currentPage").val(page);
        }
        searchDocSkill();
    }

    function searchDocSkill() {
        var url = "<s:url value='/jsres/skillTimeConfig/doctorList'/>";
        jboxPostLoad("div_table", url, $("#inForm").serialize(), false);
    }

    function importDocSkill(flag) {
        if(flag == 'N'){
            jboxTip("暂未设置技能考核时间,无法导入！")
            return false;
        }
        var url = "<s:url value='/jsres/skillTimeConfig/showImportDoc'/>";
        jboxOpen(url, "导入", 550, 260);
    }

    // 导出
    function exportDocSkill() {
        var url = "<s:url value='/jsres/skillTimeConfig/exportDocSkill'/>";
        jboxTip("导出中…………");
        jboxExp($("#inForm"), url);
    }


</script>
<div class="main_bd">
    <div class="div_search">
        <form id="inForm">
            <input id="currentPage" type="hidden" name="currentPage"/>
            <table class="searchTable">
                <tr>
                    <td class="td_left" colspan="1">姓&nbsp;&nbsp;名：</td>
                    <td colspan="1">
                        <input type="text" id="doctorName" name="doctorName" value="${param.doctorName}" class="input"/>
                    </td>
                    <td class="td_left" colspan="1">身份证：</td>
                    <td colspan="1">
                        <input type="text" id="idNo" name="idNo" value="${param.idNo}" class="input"/>
                    </td>
                    <td colspan="2">
                        <input type="button" class="btn_green" id="srearchBtn" onclick="searchDocSkill();" value="查询"/>&nbsp;&nbsp;
                        <input type="button" class="btn_green" id="importDoc" onclick="importDocSkill('${flag}');" value="导入"/>&nbsp;&nbsp;
                        <input type="button" class="btn_green" id="exportDoc" onclick="exportDocSkill();" value="导出"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div style="padding: 0px 40px;">
        <div class="main_bd clearfix" style="margin-top:20px;">
        <c:if test="${empty doctorSkillList}">
            <div class="search_table" style="width:100%;margin-top:20px;">
                <table border="0" cellpadding="0" cellspacing="0" class="grid">
                    <tr>
                        <th>考试编号</th>
                        <th>姓名</th>
                        <th>证件号码</th>
                        <th>培训专业</th>
                        <th>准考证号</th>
                        <th>考试地点</th>
                        <th>考试时间</th>
                        <%--<th>考点联系电话</th>--%>
                        <th>准考证标题</th>
<%--                        <th>操作</th>--%>
                    </tr>
                    <tr>
                        <td colspan="10">无记录！</td>
                    </tr>
                </table>
            </div>
        </c:if>
        <c:if test="${not empty doctorSkillList}">
            <div class="main_bd clearfix" style="margin-top:20px;">
                <table id="dataTable" border="0" cellpadding="0" cellspacing="0" class="grid">
                    <thead>
                    <tr>
                        <th>考试编号</th>
                        <th>姓名</th>
                        <th>证件号码</th>
                        <th>培训专业</th>
                        <th>准考证号</th>
                        <th>考试地点</th>
                        <th>考试时间</th>
                        <%--<th style="width: 10%">考点联系电话</th>--%>
                        <th>准考证标题</th>
<%--                        <th>操作</th>--%>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${doctorSkillList}" var="s" varStatus="vs">
                        <tr>
                            <td>${s.testId}</td>
                            <td>${s.doctorName}</td>
                            <td>${s.idNo}</td>
                            <td>${s.speName}</td>
                            <td>${s.ticketNumber}</td>
                            <td>${s.skillOrgName}</td>
                            <td>${s.skillTime}</td>
                            <%--<td style="width: 10%">${s.skillOrgPhone}</td>--%>
                            <td>${s.testName}</td>
                           <%-- <td>
                                <a style="color: #459AE9;" onclick="edit('${s.doctorSkillFlow}')">编辑</a>
                            </td>--%>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
    </div>
        <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(doctorSkillList)}" scope="request"/>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
    </div>
</div>