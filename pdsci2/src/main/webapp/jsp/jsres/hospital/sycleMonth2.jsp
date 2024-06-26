<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_ui_tooltip" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<style type="text/css">
    .datepicker {
        overflow: auto;
        height: 144px;
    }
</style>
<script type="text/javascript">
    $(document).ready(function () {
        $('#schStatrDate').datepicker({
            language: "zh-CN",
            todayHighlight: true,
            format: 'yyyy-mm',
            autoclose: true,
            startView: 'months',
            maxViewMode: 'years',
            minViewMode: 'months'
        });
        $('#schEndDate').datepicker({
            language: "zh-CN",
            todayHighlight: true,
            format: 'yyyy-mm',
            autoclose: true,
            startView: 'months',
            maxViewMode: 'years',
            minViewMode: 'months'
        });
    });
    function exportInfo() {
        var papersFlag = $("#exportPapers").attr("checked") == "checked";
        var schStatrDate = $("#schStatrDate").val();
        var schEndDate = $("#schEndDate").val();
        if(!schStatrDate){
            jboxTip("请选择开始时间！");
            return;
        }
        if(!schEndDate){
            jboxTip("请选择结束时间！");
            return;
        }
        if(schEndDate<schStatrDate){
            jboxTip("结束时间不得小于开始时间！");
            return;
        }
        var url = "<s:url value='/jsres/doctorRecruit/exportCycleResults2?schEnd='/>" + schEndDate+"&schStart="+schStatrDate;
        jboxTip("导出中…………");
        jboxExp(top.$("#searchForm"),url);
    }
</script>
<div class="search_table">
    <div>
        <p>
            <span style="padding-top: 30px;">
                出科开始时间：<input type="text" id="schStatrDate" name="schStatrDate"
                                   class="input" readonly="readonly" style="width: 150px;height: 35px"/>
            </span>
            <span style="padding-top: 30px;">
                出科结束时间：<input type="text" id="schEndDate" name="schEndDate"
                                   class="input" readonly="readonly" style="width: 150px;height: 35px"/>
            </span>
        </p>
        <p style="text-align: center;">
            <span style="padding-top: 30px;">
                <input type="button" class="btn_green" onclick="exportInfo();"
                       value="导&#12288;出"/>
                &#12288;
                <input type="button" class="btn_green" onclick="jboxClose();"
                       value="关&#12288;闭"/>
            </span>
        </p>
    </div>
</div>