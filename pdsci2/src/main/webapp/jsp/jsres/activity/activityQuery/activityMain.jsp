<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_ui_tooltip" value="true"/>
</jsp:include>
<script type="text/javascript"
        src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript"
        src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
    $(document).ready(function () {
        initOrg();
        toPage(1);
        $("#sjzjr").css("width", "92px");
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
        jboxPostLoad("doctorListZi", "<s:url value='/jsres/activityQuery/activityStatisticsList'/>", $("#searchForm").serialize(), false);
    }

    function exportExcel() {
        var url = "<s:url value='/jsres/activityQuery/activityStatisticsExport'/>";
        jboxTip("导出中…………");
        jboxSubmit($("#searchForm"), url, null, null, false);
        jboxEndLoading();
    }
</script>

<div class="main_bd" id="div_table_0">
    <div class="div_search">
        <form id="searchForm">
            <input type="hidden" id="currentPage" name="currentPage"/>
            <table class="searchTable">
                <tr>
                    <td class="td_left">活动名称：</td>
                    <td>
                        <input type="text" name="activityName" value="" class="input" placeholder="请输入活动名称"/>
                    </td>
                    <td class="td_left">培训基地：</td>
                    <td>
                        <input id="trainOrg" class="toggleView input" type="text"  placeholder="请输入培训基地"
                               autocomplete="off" style="margin-left: 0px;"/>
                        <input type="hidden" name="orgFlow" id="orgFlow">
                    </td>
                    <td class="td_left">主&nbsp;讲&nbsp;人：</td>
                    <td>
                        <input type="text" name="speakerName" value="" class="input" placeholder="请输入主讲人"/>
                    </td>
                    <td class="td_left">所在科室：</td>
                    <td>
                        <input type="text" name="deptName" value="" class="input" placeholder="请输入所在科室"/>
                    </td>
                </tr>
                <tr>
                    <td class="td_left">开始时间：</td>
                    <td>
                        <input type="text" id="startDate" name="startTime" value="" class="input" readonly="readonly"
                               placeholder="请选择开始时间"
                               onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
                    </td>
                    <td class="td_left">结束时间：</td>
                    <td>
                        <input type="text" id="endDate" name="endTime" value="" class="input" readonly="readonly"
                               placeholder="请选择活动结束时间"
                               onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
                    </td>
                    <td class="td_left" id="sjzjr">实际主讲人：</td>
                    <td>
                        <input type="text" name="realitySpeaker" value="" class="input" placeholder="请输入实际主讲人" />
                    </td>
                </tr>
                <tr>
                    <td id="func" colspan="3">
                        <input class="btn_green" style="margin-left: 0px;" type="button" value="查&#12288;询" onclick="toPage(1);"/>&nbsp;
                        <input class="btn_green" style="margin-left: 0px;" type="button" value="导&#12288;出" onclick="exportExcel();"/>&nbsp;
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="doctorListZi">
    </div>
</div>
