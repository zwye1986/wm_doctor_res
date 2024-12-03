<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="queryFont" value="true"/>
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
    .boxHome .item:HOVER{background-color: #eee;}
    .cur{color:red;}
    .grid td{
        border: 1px solid #e7e7eb;
    }
</style>
<script type="text/javascript" src="<s:url value='/js/Scoll/Scorll2.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $("#count_").text("1456");
        $('#sessionNumber').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode:2,
            format:'yyyy'
        });
        var style={"margin-left":"0px","width":"940px"};
        var options ={
            "colums":2//根据固定列的数量
        };

        var sum = 0;
        <c:forEach items="${orgList}" var="org">
            $(".count_${org.orgFlow}").each(function () {
                var text = $(this).text();
                sum = sum + Number(text);
            })
        </c:forEach>
        $("#count").text(sum);
    });

    // 导出
    function exportExcel(){
        var url = "<s:url value='/jsres/manage/exportRecruitStatisticsAcc'/>";
        jboxTip("导出中…………");
        jboxSubmit($("#searchForm"), url, null, null, false);
        jboxEndLoading();
    }
    function toPage() {
        jboxPostLoad("content","<s:url value='/jsres/manage/recruitStatisticsReportAcc'/>",$("#searchForm").serialize(),true);
    }
    $(function () {
        <c:forEach items="${resDocTypeEnumList}" var="type">
        <c:forEach items="${datas}" var="data">
        if("${data}"=="${type.id}"){
            $("#"+"${data}").attr("checked","checked");
        }
        </c:forEach>
        <c:if test="${empty datas}">
        $("#"+"${type.id}").attr("checked","checked");
        </c:if>
        </c:forEach>
    });
</script>
<div class="main_hd">
    <h2 class="underline">招录统计报表</h2>
</div>

<div class="main_bd" id="div_table_0" >
    <div class="div_search">
        <form id="searchForm">
            <table class="searchTable" >
                <tr>
                    <td class="td_left">年&#12288;&#12288;级：</td>
                    <td>
                        <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input" readonly="readonly"/>
                    </td>
                    <td class="td_left">学员状态：</td>
                    <td>
                        <select name="statusId" class="select">
                            <option value="">全部</option>
                            <option value="20" ${param.statusId eq '20'?'selected':''}>在培</option>
                            <option value="22" ${param.statusId eq '22'?'selected':''}>已考核待结业</option>
                            <option value="21" ${param.statusId eq '21'?'selected':''}>结业</option>
                        </select>
                    </td>
                    <td class="td_left">人员类型：</td>
                    <td colspan="3">
                        <c:forEach items="${resDocTypeEnumList}" var="type">
                            <label><input type="checkbox" id="${type.id}"value="${type.id}" class="docType" name="datas"/>${type.name}&nbsp;</label>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td id="func" colspan="8">
                        <input class="btn_green" style="margin-left: 0px;" type="button" value="查&#12288;询" onclick="toPage();"/>&nbsp;
                        <input class="btn_green" style="margin-left: 0px;" type="button" value="导&#12288;出" onclick="exportExcel();"/>&nbsp;
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<form id="checkedForm">
    <div id="tableContext"style="width:900px; margin-top: 10px;margin-bottom: 10px;overflow: auto;margin-left: 0px;" onscroll="tableFixed(this);">
        <table border="0" cellpadding="0" cellspacing="0" class="grid" >
            <tr>
                <th style="padding: 0 65px;">基地名称</th>

                <th style="width: 40px">助理全科</th>

            </tr>
            <c:forEach items="${orgList}" var="org">
                <c:set var="orgFlow" value="${org.orgFlow}" />
                <c:set var="orgSpe" value="${orgSpeList[orgFlow]}" />
                <tr>
                    <c:set var="orgSpeInfo" value="${orgSpe[orgFlow]}" />
                    <td title="${org.orgName}">${org.orgName}</td>
                    <c:forEach var="speInfo" items="${orgSpeInfo}" varStatus="status" >
                        <td style="width: 40px" class="count_${org.orgFlow}">${orgSpeInfo[status.index].count}</td>
                    </c:forEach>
                </tr>
            </c:forEach>
            <tr>
                <td style="width: 200px">合计</td>

                    <td style="width: 40px" id="count" class="total_count"></td>
            </tr>
        </table>
    </div>

</form>
