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
        height: 175px;
    }
</style>
<script type="text/javascript">
    $(document).ready(function () {
        // 初始化日期控件
        $("#schStatrDate").datepicker();
        $("#schEndDate").datepicker();
    });

    // 导出
    function exportInfo() {
        // 是否导出试卷 Y 是 N 否
        var papersFlag = $("#exportPapers").attr("checked") == "checked" ? 'Y' : 'N';
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
        if(schEndDate < schStatrDate){
            jboxTip("结束时间不得小于开始时间！");
            return;
        }
        var url = "<s:url value='/jsres/doctorRecruit/exportDoctorRecruitResult?schEnd='/>" + schEndDate + "&schStart=" + schStatrDate + "&papersFlag=" + papersFlag;
        jboxTip("导出中…………");
        jboxExp(top.$("#searchForm"),url);
    }
</script>
<%--<div class="search_table">--%>
    <%--<div>--%>
        <%--<p>--%>
            <%--<span style="padding-top: 30px;">--%>
                <%--请选择您所需的出科月份：<input type="text" id="sycleMonth" name="sycleMonth"--%>
                                   <%--class="input" readonly="readonly" style="width: 100px;"/>--%>
            <%--</span>--%>
            <%--<span style="padding-top: 30px;">--%>
              <%--<input type="checkbox" id="exportPapers" name="exportPapers" class="input" />  是否导出试卷--%>
            <%--</span>--%>
        <%--</p>--%>
        <%--<p style="text-align: center;">--%>
            <%--<span style="padding-top: 30px;">--%>
                <%--<input type="button" class="btn_green" onclick="exportInfo();"--%>
                       <%--value="导&#12288;出"/>--%>
                <%--&#12288;--%>
                <%--<input type="button" class="btn_green" onclick="jboxClose();"--%>
                       <%--value="关&#12288;闭"/>--%>
            <%--</span>--%>
        <%--</p>--%>
    <%--</div>--%>
<%--</div>--%>
<table width="100%">
    <tr>
        <td style="padding-left: 40px;">选择导出学员出科时间：</td>
    </tr>
    <tr>
        <td style="padding-top: 20px;padding-left: 40px;">
            <input type="text" id="schStatrDate" name="schStatrDate" class="input" readonly="readonly" style="width: 150px;height: 25px"/> -
            <input type="text" id="schEndDate" name="schEndDate" class="input" readonly="readonly" style="width: 150px;height: 25px"/>
        </td>
    </tr>
    <tr>
        <td style="padding-top: 20px;padding-left: 40px;">
            <input type="checkbox" id="exportPapers" name="exportPapers" class="input" />  是否导出试卷
        </td>
    </tr>
    <tr>
        <td style="padding-top: 20px;text-align: center">
            <input type="button" class="btn_green" onclick="exportInfo();"
                   value="导&#12288;出"/>
            &#12288;
            <input type="button" class="btn_green" onclick="jboxClose();"
                   value="关&#12288;闭"/>
        </td>
    </tr>
</table>