<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_ui_combobox" value="false"/>
    <jsp:param name="jquery_ui_sortable" value="false"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_scrollTo" value="false"/>
    <jsp:param name="jquery_jcallout" value="false"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_fullcalendar" value="false"/>
    <jsp:param name="jquery_fngantt" value="false"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
    <jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<style type="text/css">
    .boxHome .item:HOVER {
        background-color: #eee;
    }

    .cur {
        color: red;
    }
</style>
<script type="text/javascript"
        src="<s:url value='/js/Scoll/Scorll2.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript"
        src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
    $(document).ready(function () {


    });

    function heightFiexed() {
        //修正高度
        var toFixedTd = $(".toFiexdDept");
        $(".fixedBy").each(function (i) {
            $(toFixedTd[i]).height($(this).height());
        });
        var fixTrTd = $(".fixTrTd");
        var fixTrTh = $(".fixTrTh");
        var fixTd = $(".fix");
        $(fixTrTd).each(function (i) {
            var maxheight = -1;
            $(fixTrTd[i]).find(".by").each(function () {
                if ($(this).height() > maxheight) maxheight = $(this).height();
            });
            if (maxheight != -1) {
                $(fixTrTh[i]).find(".fix").each(function () {
                    $(this).height(maxheight);
                });
            }
        });
    }

    onresize = function () {
        heightFiexed();
    };

    function addTest() {
        var addFlag = '${addFlag}';
        if("N" == addFlag){
            jboxTip("当前年已设置两次考核，无法新增！");
            return false;
        }
        var url = "<s:url value ='/jsres/testConfig/addTest'/>";
        jboxOpen(url, "新增考试", 800, 400);
    }

    function search() {
        var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
        jboxLoad("content", "<s:url value='/jsres/testConfig/testMain'/>?roleFlag=" + roleFlag+"&tabTag=GraduationTest", true);
    }

    function edit(testFlow) {
        var url = "<s:url value ='/jsres/testConfig/addTest'/>?testFlow=" + testFlow;
        jboxOpen(url, "编辑考试", 800, 400);
    }

    function lookOver(testFlow, flag) {
        var url = "<s:url value ='/jsres/testConfig/addTest'/>?testFlow=" + testFlow + "&flag=" + flag;
        jboxOpen(url, "查看考试", 800, 400);
    }

    function closeTest(testFlow) {
        var url = "<s:url value ='/jsres/testConfig/closeTest'/>?testFlow=" + testFlow;
        jboxConfirm("考试一旦关闭，不能恢复，所有报名数据作废，请谨慎处理", function () {
            jboxPost(url, null, function (data) {
                jboxTip(data);
                if ('${GlobalConstant.OPRE_SUCCESSED}' == data) {
                    setTimeout(function () {
                        search();
                    },2000);
                }
            });
        });
    }
</script>
<%--<div class="main_hd">--%>
    <%--<h2 class="underline">考试配置</h2>--%>
<%--</div>--%>

<div style="padding: 0px 40px;">
    <div class="main_bd clearfix" style="margin-top:20px;">
        <div class="main_bd clearfix" >
            <input type="button" class="btn_green" id="submitBtn" onclick="addTest();" value="新增"/>
        </div>

        <c:if test="${empty resTestConfigs}">
            <div class="search_table" style="width:100%;margin-top:20px;">
                <table border="0" cellpadding="0" cellspacing="0" class="grid">
                    <tr>
                        <th>&nbsp;&nbsp;序号&nbsp;&nbsp;&nbsp;&nbsp;</th>
                        <th>考试编号&nbsp;&nbsp;</th>
                        <th>考试名称&nbsp;&nbsp;</th>
                        <th>开始报名时间</th>
                        <th>结束报名时间</th>
                        <th>考核状态</th>
                        <th>操作&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    </tr>
                    <tr>
                        <td colspan="8">无记录！</td>
                    </tr>
                </table>
            </div>
        </c:if>
        <c:if test="${not empty resTestConfigs}">
            <div class="main_bd clearfix" style="margin-top:20px;">
                <table id="dataTable" border="0" cellpadding="0" cellspacing="0" class="grid">
                    <thead>
                    <tr>
                        <th>&nbsp;&nbsp;序号&nbsp;&nbsp;&nbsp;&nbsp;</th>
                        <th>考试编号&nbsp;&nbsp;</th>
                        <th>考试名称&nbsp;&nbsp;</th>
                        <th>开始报名时间</th>
                        <th>结束报名时间</th>
                        <th>考核状态</th>
                        <th>操作&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${resTestConfigs}" var="s" varStatus="vs">
                        <tr>
                            <td>${vs.index + 1}</td>
                            <td>${s.testId}</td>
                            <td>${s.testName}</td>
                            <td>${s.applyStartTime}</td>
                            <td>${s.applyEndTime}</td>
                            <td>
                                <c:if test="${map[s.testFlow]}">
                                    <span style="margin-left: 30px;margin-right: 20px;">已结束</span>
                                </c:if>
                                <c:if test="${!map[s.testFlow]}">
                                    <span style="margin-left: 30px;margin-right: 20px;">进行中</span>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${map[s.testFlow]}">
                                    <a style="margin-left:35px;margin-right: 30px;color: #459AE9;"
                                       onclick="lookOver('${s.testFlow}','N')"
                                    >查看</a>
                                </c:if>
                                <c:if test="${!map[s.testFlow]}">
                                    <c:if test="${s.recordStatus eq 'Y'}">
                                        <a style="margin-left:35px;margin-right: 30px;color: #459AE9;"
                                           onclick="edit('${s.testFlow}')"
                                        >编辑</a>
                                    </c:if>
                                    <c:if test="${s.recordStatus eq 'N'}">
                                        <a style="margin-left:35px;margin-right: 30px;color: #459AE9;"
                                           onclick="lookOver('${s.testFlow}','N')"
                                        >查看</a>
                                    </c:if>
                                </c:if>
                                <%--<c:if test="${map[s.testFlow]}">--%>
                                    <%--<span style="margin-left: 30px;margin-right: 20px;">已结束</span>--%>
                                <%--</c:if>--%>
                                <c:if test="${!map[s.testFlow]}">
                                    <c:if test="${s.recordStatus eq 'Y'}">
                                        <a style="margin-left:35px;margin-right: 30px;color: #FFB45B;"
                                           onclick="closeTest('${s.testFlow}')"
                                        >关闭</a>
                                    </c:if>
                                    <%--<c:if test="${s.recordStatus eq 'N'}">--%>
                                        <%--<span style="margin-left: 30px;margin-right: 20px;">已关闭</span>--%>
                                    <%--</c:if>--%>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
    </div>
</div>
