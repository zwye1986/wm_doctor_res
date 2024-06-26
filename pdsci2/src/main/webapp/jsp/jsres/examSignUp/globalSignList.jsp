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
<script type="text/javascript"
        src="<s:url value='/js/Scoll/Scorll2.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript"
        src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#currentPage").val("${param.currentPage}");
    });

    function showAllSignup(signupFlow, flag, roleFlag) {
        var url = "<s:url value ='/jsres/examSignUp/auditSignup'/>?signupFlow=" + signupFlow + "&flag=" + flag + "&roleFlag=" + roleFlag;
        jboxOpen(url, "补考审核", 1000, 600);
    }

    function tableFixed(div) {
        $("#dateFixed,#topTitle").css("top", $(div).scrollTop() + "px");
        $("#deptFixed,#topTitle").css("left", $(div).scrollLeft() + "px");
    }

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

    function checkboxOnclick(checkbox, auditStatusId) {

        if (checkbox.checked == true) {
            if (auditStatusId != 'WaitGlobalPass') {
                checkbox.checked = false;
                jboxTip("只能批量审核待审核的资格审查");
            }

        }
    }

    function selectAll() {
        $("td input[type='checkbox'][name='signupFlow']").attr("checked", "checked");
    }

    function Contrary(exp) {
        var selects = $("td input[type='checkbox'][name='signupFlow']");
        for (var i = 0; i < selects.length; i++) {
            var exp = $(selects[i]).attr("checked");
            if (exp == "checked") {
                $(selects[i]).attr("checked", false);
            } else {
                $(selects[i]).attr("checked", "checked");
            }
        }
    }
</script>
<c:if test="${empty list}">
    <div class="search_table" style="width: 100%;padding: 0px 0px">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>姓名</th>
                <th>地市</th>
                <th>培训基地</th>
                <th>考试编号</th>
                <th>报考科目</th>
                <th>培训起止时间</th>
                <th>审核</th>
                <c:if test="${param.tabTag ne 'SecondWait' and param.tabTag ne 'SecondWait2'}">
                    <th>审核状态</th>
                </c:if>
            </tr>
            <tr>
                <td colspan="8">无记录！</td>
            </tr>
        </table>
    </div>
</c:if>
<c:if test="${not empty list}">
    <div class="main_bd clearfix" style="width: 100%;">
        <table id="dataTable" border="0" cellpadding="0" cellspacing="0" class="grid">
            <thead>
            <tr>
                <c:if test="${f eq 'Y' and param.auditStatusId eq 'WaitGlobalPass' and (param.tabTag eq 'SecondWait' or param.tabTag eq 'SecondWait2')}">
                    <th style="width: 62px " class="toFiexdDept">
                        <%--<c:if test="${param.auditStatusId eq 'WaitGlobalPass'}">--%>
                            <a href="javascript:void(0);"
                               onclick="selectAll();">全选</a>/<a href="javascript:void(0);" onclick="Contrary();">反选</a>
                        <%--</c:if>--%>
                    </th>
                </c:if>
                <th class="fixedBy" style="width: 80px">姓名</th>
                <th class="fixedBy" style="width: 100px;">地市</th>
                <th class="fixedBy" style="width: 130px">培训基地</th>
                <th class="fixedBy" style="width: 100px">考试编号</th>
                <th class="fixedBy" style="width: 80px">报考科目</th>
                <th class="fixedBy" style="width: 180px">培训起止时间</th>
                <c:if test="${param.tabTag ne 'SecondWait' and param.tabTag ne 'SecondWait2'}">
                    <th class="toFiexdDept" style="width: 80px">审核</th>
                </c:if>
                <th class="fixedBy" style="width: 125px;">审核状态</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="s">
                <c:set value="${s.signupFlow}startDate" var="startDate"></c:set>
                <c:set value="${s.signupFlow}endTime" var="endTime"></c:set>
                <tr class="fixTrTd">
                    <c:if test="${f eq 'Y' and param.auditStatusId eq 'WaitGlobalPass' and (param.tabTag eq 'SecondWait' or param.tabTag eq 'SecondWait2')}">
                        <td class="by">
                            <input value="${s.signupFlow}" name="signupFlow" type="checkbox" onclick="checkboxOnclick(this,'${s.auditStatusId}')"/>
                        </td>
                    </c:if>
                    <td class="by">${s.userName}</td>
                    <td class="by">${s.orgCityName}</td>
                    <td class="by">${s.orgName}</td>
                    <td class="by">${s.testId}</td>
                    <td class="by">
                        <c:if test="${s.signupTypeId eq 'Theory'}">理论</c:if>
                        <c:if test="${s.signupTypeId eq 'Skill'}">技能</c:if>
                    </td>
                    <td class="by">${trainMap[startDate]}&nbsp;~&nbsp;${trainMap[endTime]}</td>
                    <td class="by">
                        <c:if test="${f eq 'Y'}">
                            <c:if test="${maintenance ne 'Y'}"> <%--客服（运维角色）只能查看——--%>
                                <c:if test="${s.auditStatusId eq 'WaitGlobalPass' and (param.tabTag eq 'SecondWait' or param.tabTag eq 'SecondWait2')}">
                                    <a class="btn" onclick="showAllSignup('${s.signupFlow}','Y','${roleFlag}');"
                                       style="margin-top: 5px;margin-bottom: 5px;padding:0px;width: 70px">审核</a>
                                </c:if>
                                <c:if test="${s.auditStatusId eq 'GlobalPassed' or s.auditStatusId eq 'GlobalNotPassed'}">
                                    <a class="btn" onclick="showAllSignup('${s.signupFlow}','Y','${roleFlag}');"
                                       style="margin-top: 5px;margin-bottom: 5px;padding:0px;width: 70px">更改</a>
                                </c:if>
                            </c:if>
                            <c:if test="${s.auditStatusId ne 'GlobalPassed' and s.auditStatusId ne 'GlobalNotPassed'}">
                                <a class="btn" onclick="showAllSignup('${s.signupFlow}','N','${roleFlag}');"
                                   style="margin-top: 5px;margin-bottom: 5px;padding:0;width: 70px">查看</a>
                            </c:if>

                        </c:if>
                        <c:if test="${f eq 'N'}">
                            <a class="btn" onclick="showAllSignup('${s.signupFlow}','N','${roleFlag}');"
                               style="margin-top: 5px;margin-bottom: 5px; width: 80px; padding: 0px">查看</a>
                        </c:if>
                    </td>
                    <c:if test="${param.tabTag ne 'SecondWait' and param.tabTag ne 'SecondWait2'}">
                        <td class="by">${s.auditStatusName}</td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>

<div class="page">
    <c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
    <pd:pagination-jsres toPage="toPage"/>
</div>
      
