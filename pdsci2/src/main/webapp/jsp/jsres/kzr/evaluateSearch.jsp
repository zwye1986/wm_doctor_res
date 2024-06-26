<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>${sysCfgMap['sys_title_name']}</title>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="login" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
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
        .ith {
            height: 40px;
            line-height: 40px;
            padding-left: 10px;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            if ("${empty param.roleType}" == "true") {
                $("#reducationTab li:first").click();
            }
            $('#startDate').datepicker();
            $('#endDate').datepicker();
            var biaoz = 0;
            $(".biaoz").each(function () {
                var s = $(this).text();
                if (s != "" && !isNaN(s)) {
                    var numItem = parseInt(s);
                    biaoz += numItem;
                }
            });
            $("#biaoZ").text(biaoz);
        });
        function setType(flag) {
            $("#roleType").val(flag);
            var form = $("#searchForm").serialize();
            evaluateSearch(form);
        }
        function checkDetails(roleType, userFlow) {
            var startDate = $("#startDate").val();
            var endDate = $("#endDate").val();
            var deptFlow = $("#deptFlow").val();
            var url = "<s:url value='/jsres/kzr/checkDetails'/>?roleType=" + roleType + "&userFlow=" + userFlow + "&startDate=" + startDate + "&endDate=" + endDate + "&deptFlow=" + deptFlow;
            jboxOpen(url, "查看", 1000, 550, true);
        }
    </script>
</head>
<body>
<div class="main_hd">
    <h2>评价查询</h2>
    <div class="title_tab">
        <ul id="reducationTab">
            <li class="${param.roleType eq GlobalConstant.RES_ROLE_SCOPE_TEACHER?'tab_select':'tab'}"
                onclick="setType('${GlobalConstant.RES_ROLE_SCOPE_TEACHER}');"><a>带教老师</a></li>
            <li class="${param.roleType eq GlobalConstant.RES_ROLE_SCOPE_HEAD?'tab_select':'tab'}"
                onclick="setType('${GlobalConstant.RES_ROLE_SCOPE_HEAD}');"><a>科室</a></li>
        </ul>
    </div>
</div>
<div class="div_search">
    <form id="searchForm">
        <input type="hidden" id="roleType" name="roleType" value="${param.roleType}">
        <c:if test="${param.roleType eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
            姓名：<input type="text" name="doctorName" class="input" style="width: 100px;" value="${param.doctorName}"  />&#12288;
        </c:if>
        &#12288;评分时间：
        <input type="text" id="startDate" name="startDate" value="${param.startDate}" class="input datepicker"
               readonly="readonly" style="width: 100px;" />
        ~
        <input type="text" id="endDate" name="endDate" value="${param.endDate}" class="input datepicker"
               readonly="readonly" style="width: 100px;" />
        <c:if test="${param.roleType eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
            &#12288;所在科室：
            <select id="deptFlow" name="deptFlow" class="select" style="width: 107px;" >
                <c:forEach items="${deptList}" var="dept">
                    <option value="${dept.deptFlow }"
                            <c:if test="${dept.deptFlow eq param.deptFlow or (dept.deptFlow eq sysUser.deptFlow)}">selected="selected"</c:if>>
                            ${dept.deptName }
                    </option>
                </c:forEach>
            </select>
        </c:if>
        &#12288;<input class="btn_green" type="button" value="查&#12288;询" onclick="setType('${param.roleType}');"/>
        <c:if test="${param.roleType eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
            &#12288;<input type="button" class="btn_green" value="查看详情" onclick="checkDetails('${param.roleType}');"/>
        </c:if>
    </form>
</div>
<div class="search_table" style="overflow-x: auto;">
    <c:if test="${param.roleType eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">

        <table border="0" cellpadding="0" cellspacing="0" class="grid" style="overflow: auto;">
            <tr>
                <th>老师姓名</th>
                <th>当前带教人数</th>
                <th>总人数</th>
                <th>平均得分</th>
            </tr>
            <c:forEach items="${sysUserList}" var="user">
                <c:set value="curr${user.userFlow}" var="currKey"/>
                <c:set value="student${user.userFlow}" var="studentKey"/>
                <tr>
                    <td>${user.userName}</td>
                    <td>${studentNumMap[currKey]}</td>
                    <td>${studentNumMap[studentKey]}</td>
                    <td>
                        <a onclick="checkDetails('${param.roleType}','${user.userFlow}');">${averageMap[user.userFlow]}</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>


    <c:if test="${param.roleType eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <colgroup>
                <col width="70%"/>
                <col width="15%"/>
                <col width="15%"/>
            </colgroup>
            <tr>
                <th>评分项</th>
                <th>标准分</th>
                <th>平均得分</th>
            </tr>
            <c:forEach items="${titleFormList}" var="title">
                <tr>
                    <td colspan="3"
                        style="text-align: left;padding-left: 10px;font-weight:bold;font-size: 16px;">${title.name}</td>
                </tr>
                <c:forEach items="${title.itemList}" var="item">
                    <tr>
                        <td style="text-align: left;padding-left: 10px;">${item.name}</td>
                        <td class="biaoz" style="font-weight:normal;color: #C5C5C5;"><c:out value="${item.score}"
                                                                                            default="-"/></td>
                        <td><c:out value="${heJiMap[item.id]}" default="-"/></td>
                    </tr>
                </c:forEach>
            </c:forEach>
            <tr>
                <th style="text-align: left;padding-left: 10px;">总标准分/总平均分</th>
                <th><span id="biaoZ" style="font-weight:bold;color: #C5C5C5;"></span></th>
                <th>${average}</th>
            </tr>
        </table>
    </c:if>

</div>
</body>
</html>