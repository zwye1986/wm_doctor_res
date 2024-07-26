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

    .searchTable {
        width: auto;
    }

    .searchTable td {
        width: auto;
        height: auto;
        line-height: auto;
        text-align: left;
        max-width: 150px;;
    }

    .searchTable .td_left {
        word-wrap: break-word;
        width: 5em;
        height: auto;
        line-height: auto;
        text-align: right;
    }
</style>
<script type="text/javascript"
        src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#subjectYear').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
        initOrg();
        toPage();
    });

    var allOrgs = [];

    function initOrg() {
        var datas = [];
        <c:forEach items="${orgs}" var="org">
        var d = {};
        d.id = "${org.orgFlow}";
        d.text = "${org.orgName}";
        d.cityId = "${org.orgCityId}";
        datas.push(d);
        allOrgs.push(d);
        </c:forEach>
        var itemSelectFuntion = function () {
            $("#orgFlow").val(this.id);
        };
        $.selectSuggest('trainOrg', datas, itemSelectFuntion, "orgFlow", true);
    }

    function toPage(page) {
        $("#currentPage").val(page);
        jboxStartLoading();
        jboxPostLoad("doctorListZi", "<s:url value='/jsres/supervisio/planReportList'/>?roleFlag=${roleFlag}&suAoth=${suAoth}", $("#searchForm").serialize(), false);
    }

    function trainOrgHidden() {
        $("#trainOrg-suggest").css({"display": "none"})
    }
</script>
<div class="main_hd">
    <h2 class="underline">督导管理 — <c:if test="${GlobalConstant.USER_LIST_LOCAL ne roleFlag}">基地自评反馈</c:if><c:if test="${GlobalConstant.USER_LIST_LOCAL eq roleFlag}">基地自评</c:if></h2>
</div>
<div class="div_search" style="width: 95%;line-height:normal;">
    <form id="searchForm">
        <input type="hidden" id="currentPage" name="currentPage"/>

        <div style="display: flex;justify-content: flex-start; column-gap: 56px;margin-top: 15px">
            <div>
                <label class="from_label">基地名称：</label>
                <c:if test="${GlobalConstant.USER_LIST_LOCAL eq roleFlag}">
                    <select name="orgFlow" class="select" style="width: 161px;">
                        <c:forEach items="${orgs}" var="org">
                            <option value="${org.orgFlow}" ${orgFlow eq org.orgFlow?'selected':''}
                            >${org.orgName}</option>
                        </c:forEach>
                    </select>
                </c:if>
            </div>
            <div>
                <label class="from_label">基地代码：</label>
                <input type="text" name="baseCode" value="${param.baseCode}" class="input"
                       style="width: 161px;margin-left: 0px;"/>
            </div>
            <div>
                <label class="from_label">检查年份：</label>
                <input class="input" name="subjectYear" id="subjectYear" style="width: 161px;margin-left: 0px;"
                       value="${param.subjectYear==null?currentTime:param.subjectYear}"/>
            </div>

        </div>
        <div style="margin-top: 15px;margin-bottom: 15px">
            <input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>
        </div>



<%--        <table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">--%>
<%--                <tr>--%>
<%--                    <td class="td_left">--%>
<%--                        <nobr>基地名称：</nobr>--%>
<%--                    </td>--%>
<%--                    <c:if test="${GlobalConstant.USER_LIST_LOCAL eq roleFlag}">--%>
<%--                        <td>--%>
<%--                            <select name="orgFlow" class="select" style="width: 181px;">--%>
<%--                                <c:forEach items="${orgs}" var="org">--%>
<%--                                    <option value="${org.orgFlow}" ${orgFlow eq org.orgFlow?'selected':''}--%>
<%--                                    >${org.orgName}</option>--%>
<%--                                </c:forEach>--%>
<%--                            </select>--%>
<%--                        </td>--%>
<%--                    </c:if>--%>
<%--                    <td class="td_left">--%>
<%--                        <nobr>基地代码：</nobr>--%>
<%--                    </td>--%>
<%--                    <td>--%>
<%--                        <input type="text" name="baseCode" value="${param.baseCode}" class="input"--%>
<%--                               style="width: 164px;margin-left: 0px;"/>--%>
<%--                    </td>--%>
<%--                    <td class="td_left">--%>
<%--                        <nobr>检查年份：</nobr>--%>
<%--                    </td>--%>
<%--                    <td>--%>
<%--                        <input class="input" name="subjectYear" id="subjectYear" style="width: 100px;margin-left: 0px;"--%>
<%--                            value="${param.subjectYear==null?currentTime:param.subjectYear}"/>--%>
<%--                    </td>--%>

<%--                    <td>--%>
<%--                        <input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>&#12288;--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--        </table>--%>
    </form>
</div>
<div id="doctorListZi" style="width: 100%">

</div>