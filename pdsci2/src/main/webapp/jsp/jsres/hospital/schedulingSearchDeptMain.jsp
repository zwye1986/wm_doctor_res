<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"/>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="font" value="true"/>
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
</jsp:include>
<script src="<s:url value='/js/yearSelect/checkYear.js'/>" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function () {
        searchResult();
    });

    function searchResult(){
        jboxStartLoading();
        jboxPostLoad("achievementContent2","<s:url value='/jsres/doctorRecruit/schedulingSearchDeptList'/>",$("#searchForm").serialize(),false);
    }
</script>

<div class="div_search" style="box-sizing: border-box;line-height:normal;">
    <form id="searchForm">
        <table class="searchTable" style="border-collapse:separate; border-spacing:0px 10px;">
            <tr>
                <td class="td_left">时间：</td>
                <td class="td_right">
                    <input type="text" name="searchTime" id="ym" class="input" onchange="datechange(this)"  value="${searchTime}"
                           onClick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false,onpicked:function(dp){$dp.$('ym').blur();}})" readonly="readonly"/>
                </td>
                <td class="td_left">科室名称：</td>
                <td class="td_right">
                    <select name="deptFlow" class="select" style="width: 150px;">
                        <option value="">请选择</option>
                        <c:forEach items="${deptList}" var="dept">
                            <option value="${dept.deptFlow }" ${dept.deptFlow eq param.deptFlow?'selected':'' }>${dept.deptName }</option>
                        </c:forEach>
                </select>
                </td>
                <td>
                    <input type="button" class="btn_green" onclick="searchResult();" value="查询"/>
                </td>
            </tr>

        </table>
    </form>
</div>
<div class="main_bd" id="div_table_0" >
    <div id="achievementContent2">
    </div>
</div>


