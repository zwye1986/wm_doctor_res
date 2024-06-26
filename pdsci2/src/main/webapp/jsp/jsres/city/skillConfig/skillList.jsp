<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="queryFont" value="true"/>
</jsp:include>
<script type="text/javascript">
    function searchSkills() {
        var url = "<s:url value='/jsres/skillTimeConfig/skillList'/>";
        jboxPostLoad("div_table", url, $("#inForm").serialize(), false);
    }

    function addSkillConfig(addFlag) {
        if(addFlag == 'N'){
            jboxTip("非发布时间段，暂无法新增！");
            return false;
        }
        var url = "<s:url value='/jsres/skillTimeConfig/addSkillConfig'/>?type=add";
        jboxOpen(url, "新增考试", 900, 600);
    }

    function edit(skillFlow) {
        var url = "<s:url value ='/jsres/skillTimeConfig/addSkillConfig'/>?skillFlow=" + skillFlow + "&type=edit";
        jboxOpen(url, "编辑考试", 900, 600);
    }

    function deleteSkillConfig(skillFlow) {
        var url = "<s:url value ='/jsres/skillTimeConfig/deleteSkillConfig'/>?skillFlow=" + skillFlow;
        jboxConfirm("考试一旦关闭，不能恢复，所有报名数据作废，请谨慎处理", function () {
            jboxPost(url, null, function (data) {
                jboxTip(data);
                if ('${GlobalConstant.OPRE_SUCCESSED}' == data) {
                    setTimeout(function () {
                        searchSkills();
                    },2000);
                }
            });
        });
    }
</script>
<div class="main_bd">
    <div class="div_search">
        <form id="inForm">
            <table class="searchTable">
                <tr>
                    <td class="td_left">考试编号：</td>
                    <td>
                        <input type="text" id="testId" name="testId" value="${param.testId}" class="input"/>
                    </td>
                    <td class="td_left">考试名称：</td>
                    <td>
                        <input type="text" id="testName" name="testName" value="${param.testName}" class="input"/>
                    </td>
                    <td>
                        <input type="button" class="btn_green" id="srearchBtn" onclick="searchSkills();" value="查询"/>&nbsp;&nbsp;
                        <input type="button" class="btn_green" id="submitBtn" onclick="addSkillConfig('${addFlag}');" value="新增"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div style="padding: 0px 40px;">
    <div class="main_bd clearfix" style="margin-top:20px;">
        <c:if test="${empty skillConfigs}">
            <div class="search_table" style="width:100%;margin-top:20px;">
                <table border="0" cellpadding="0" cellspacing="0" class="grid">
                    <tr>
                        <th>&nbsp;&nbsp;序号&nbsp;&nbsp;&nbsp;&nbsp;</th>
                        <th>考试编号&nbsp;&nbsp;</th>
                        <th>考试名称&nbsp;&nbsp;</th>
                        <th>考核开始时间</th>
                        <th>考核结束时间</th>
                        <th>操作&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    </tr>
                    <tr>
                        <td colspan="8">无记录！</td>
                    </tr>
                </table>
            </div>
        </c:if>
        <c:if test="${not empty skillConfigs}">
            <div class="main_bd clearfix" style="margin-top:20px;">
                <table id="dataTable" border="0" cellpadding="0" cellspacing="0" class="grid">
                    <thead>
                    <tr>
                        <th>&nbsp;&nbsp;序号&nbsp;&nbsp;&nbsp;&nbsp;</th>
                        <th>考试编号&nbsp;&nbsp;</th>
                        <th>考核名称&nbsp;&nbsp;</th>
                        <th>考核开始时间</th>
                        <th>考核结束时间</th>
                        <th>考核方式</th>
                        <th>操作&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${skillConfigs}" var="s" varStatus="vs">
                        <tr>
                            <td>${vs.index + 1}</td>
                            <td>${s.testId}</td>
                            <td>${s.testName}</td>
                            <td>${s.skillStartTime}</td>
                            <td>${s.skillEndTime}</td>
                            <td>
                                <c:if test="${s.skillWay eq 'order'}">预约</c:if>
                                <c:if test="${s.skillWay eq 'export'}">导入</c:if>
                            </td>
                            <td>
                                <c:if test="${s.skillEndTime gt pdfn:getCurrDateTime('yyyy-MM-dd HH:mm:ss')}">
                                    <a style="margin-left:35px;margin-right: 30px;color: #459AE9;"
                                       onclick="edit('${s.skillFlow}')"
                                    >编辑</a>
                                </c:if>

                                <a style="margin-left:35px;margin-right: 30px;color: #459AE9;"
                                   onclick="deleteSkillConfig('${s.skillFlow}')"
                                >删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
    </div>
</div>
</div>