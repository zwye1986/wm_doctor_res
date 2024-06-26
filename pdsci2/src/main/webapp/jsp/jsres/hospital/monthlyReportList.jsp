<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<style type="text/css">
    table {
        border-collapse: collapse;
        border: 2px solid #D3D3D3;
    }

    table th {
        border-top: 0;
        border-right: 2px solid #D3D3D3;
        border-bottom: 2px solid #D3D3D3;
        border-left: 0;
        height: 150px;
        width: 230px;
    }

    table tr.lastrow td {
        border-bottom: 0;
    }
</style>
<script type="text/javascript">
    $(function () {
        var now = new Date();
        var year = now.getFullYear() - 3;
        for (var a = 1; a <= 8; a++) {
            $("#selectedYear").append("<option value='" + year + "'>" + year + "</option>");
            year = year + 1;
        }
        var nowYear = now.getFullYear();
       // $("#selectedYear option[value='" + nowYear + "']").attr('selected', 'selected');
    });
    function outDocSchProExcel(exp, mon) {
        var year = $("#selectedYear").val();
        if(!year)
        {
            jboxTip("请选择年份！");
            return false;
        }
        var schStartDate = year + "-" + mon;
        var year2 = year;
        var mon2 = parseInt(mon) - 1;
        if (mon2 == 0) {
            year2 = parseInt(year2) - 1;
            mon2 = 12;
        } else if (mon2 < 10) {
            mon2 = "0" + mon2;
        }
        var schStartDate1 = year2 + "-" + mon2;
        var url = "<s:url value='/jsres/base/searchMonthlyReport'/>?schStartDate=" + schStartDate + "&schStartDate1=" + schStartDate1;
        jboxTip("导出中…………");
        $("#" + exp).attr("href", url);
        $("#outToExcelSpan").click();
    }
</script>

<div style="padding-top: 10px; padding-left: 10px; padding-right: 10px;">
    <div class="main_hd">
        <h2 class="underline">
            月报查询
        </h2>
    </div>
    <div id="calendar">
        <%--<a class="btn" id="outToExcel" ><span id="outToExcelSpan"> </span></a>--%>
        <%--<a class="btn" href="/jsres/base/searchMonthlyReport?schStartDate=2015-09&schStartDate1=2015-08">导出</a>--%>
        <div id="yearDiv" class="div_search">
            &nbsp;&nbsp;选择月报查询年份：&nbsp;&nbsp;
            <input type="text" value="${pdfn:getCurrYear()}" name="selectedYear" id="selectedYear" readonly="readonly" style="width: 100px;border: 1px solid #e7e7eb;"  onclick="WdatePicker({dateFmt:'yyyy'})" >
            &#12288;<input type="button" class="btn_green" value="查&#12288;询"/>
        </div>
        <div class="search_table">
            <table cellpadding="4">
                <tr>
                    <th colspan="10"><a id="outToExcel1"><span onclick="outDocSchProExcel('outToExcel1','01')"><h3>
                        1月</h3></span></a></th>
                    <th colspan="10"><a id="outToExcel2"><span onclick="outDocSchProExcel('outToExcel2','02')"><h3>
                        2月</h3></span></a></th>
                    <th colspan="10"><a id="outToExcel3"><span onclick="outDocSchProExcel('outToExcel3','03')"><h3>
                        3月</h3></span></a></th>
                    <th colspan="10"><a id="outToExcel4"><span onclick="outDocSchProExcel('outToExcel4','04')"><h3>
                        4月</h3></span></a></th>
                </tr>
                <tr>
                    <th colspan="10"><a id="outToExcel5"><span onclick="outDocSchProExcel('outToExcel5','05')"><h3>
                        5月</h3></span></a></th>
                    <th colspan="10"><a id="outToExcel6"><span onclick="outDocSchProExcel('outToExcel6','06')"><h3>
                        6月</h3></span></a></th>
                    <th colspan="10"><a id="outToExcel7"><span onclick="outDocSchProExcel('outToExcel7','07')"><h3>
                        7月</h3></span></a></th>
                    <th colspan="10"><a id="outToExcel8"><span onclick="outDocSchProExcel('outToExcel8','08')"><h3>
                        8月</h3></span></a></th>
                </tr>
                <tr>
                    <th colspan="10"><a id="outToExcel9"><span onclick="outDocSchProExcel('outToExcel9','09')"><h3>
                        9月</h3></span></a></th>
                    <th colspan="10"><a id="outToExcel10"><span onclick="outDocSchProExcel('outToExcel10','10')"><h3>
                        10月</h3></span></a></th>
                    <th colspan="10"><a id="outToExcel11"><span onclick="outDocSchProExcel('outToExcel11','11')"><h3>
                        11月</h3></span></a></th>
                    <th colspan="10"><a id="outToExcel12"><span onclick="outDocSchProExcel('outToExcel12','12')"><h3>
                        12月</h3></span></a></th>
                </tr>
            </table>
        </div>

    </div>
</div>
