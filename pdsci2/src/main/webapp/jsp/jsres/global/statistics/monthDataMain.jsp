
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_ui_tooltip" value="true"/>
</jsp:include>
<style type="text/css">
    .boxHome .item:HOVER{background-color: #eee;}
    .cur{color:red;}
</style>
<script type="text/javascript">
    $(document).ready(function(){
        toPage(1);
    });

    function toPage(page) {
        jboxStartLoading();
        jboxPostLoad("doctorListZi","<s:url value='/jsres/manage/monthDataStatistics'/>",$("#searchForm").serialize(),false);
    }

    function exportMonthList() {
        var url = "<s:url value='/jsres/manage/exportMonthList'/>";
        jboxTip("导出中…………");
        jboxExp($("#searchForm"), url);
    }

</script>
<div class="main_hd">
    <h2 class="underline">月度统计报表</h2>
</div>
<div class="main_bd" id="div_table_0" style="margin-top: 5px;">
    <div class="div_search">
        <form id="searchForm">
            <table class="searchTable">
                <tr>
                    <td class="td_left">
                        报表时间：
                    </td>
                    <td>
                        <input type="text" name="monthDate" id="monthDate" value="${empty param.monthDate ? month : param.monthDate}" class="input"
                               onClick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false,onpicked:function(dp){$dp.$('monthDate').blur();}})"/>
                    </td>
                    <td id="func" colspan="4">
                        &nbsp;<input class="btn_green" style="margin-left: 0px;" type="button" value="查&#12288;询" onclick="toPage(1);"/>&nbsp;
                        &nbsp;<input class="btn_green" style="margin-left: 0px;" type="button" value="导&#12288;出" onclick="exportMonthList()"/>&nbsp;
                        <span style="color: red;margin-right: 240px">*报表数据截至当月最后一天</span>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="doctorListZi">
    </div>
</div>