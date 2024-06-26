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
<%--<link href="<s:url value='/css/UCFORM.css'/>" rel="stylesheet" type="text/css">
<script src="<s:url value='/js/jQuery.UCSelect.js'/>" type="text/javascript"></script>--%>
<script type="text/javascript">
    $(document).ready(function () {
        // $('select[name=speTypeId]').UCFormSelect();
        $('#subjectYear').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
        toPage();
    });


    function toPage(page) {
        $("#currentPage").val(page);
        jboxStartLoading();
        jboxPostLoad("doctorListZi", "<s:url value='/jsres/supervisio/manageSupervisioList'/>?roleFlag=${roleFlag}", $("#searchForm").serialize(), false);
    }

</script>
<div class="main_hd">
    <h2 class="underline">督导管理 — 基地自评汇总</h2>
</div>
<div class="div_search" style="width: 95%;line-height:normal;">
    <form id="searchForm">
        <input type="hidden" id="currentPage" name="currentPage"/>
        <table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">
            <tr>
                <td class="td_left">
                    <nobr>基地名称：</nobr>
                </td>
                <td>
                    <select name="orgFlow" class="select" style="width: 240px;">
                        <option value="">全部</option
                        <c:forEach items="${orgs}" var="org">
                            <option value="${org.orgFlow}" ${orgFlow eq org.orgFlow?'selected':''}
                            >${org.orgName}</option>
                        </c:forEach>
                    </select>
                </td>


                <td class="td_left">
                    <nobr style="margin-left: 167px">基地自评：</nobr>
                </td>
                <td>
                    <select name="manageAllSub" class="select" style="width: 97px;margin-left: 83px" >
                        <option value="">全部</option>
                        <option value="Y">已完成</option>
                        <option value="N">未完成</option>
                    </select>
                </td>

                <td class="td_left">
                    <nobr style="margin-left: 84px">基地排序：</nobr>
                </td>
                <td>
                    <select name="orgPaiXu" class="select" style="width: 97px;margin-left: 4px;margin-right: 26px">
                        <option value="">默认</option>
                        <option value="">升序</option>
                        <option value="desc">降序</option>
                    </select>

                </td>
            </tr>
            <tr>
                <td class="td_left">
                    <nobr>督导年份：</nobr>
                </td>
                <td>
                    <input class="input" name="subjectYear" id="subjectYear" style="width: 229px;"
                           value="${pdfn:getCurrYear()}"/>
                </td>
                <td class="td_left">
                    <nobr style="margin-left: 167px">自评情况：</nobr>
                </td>
                <td>
                    <select name="basePaiXu"  class="select" style="width: 97px;margin-left: 83px">
                        <option value="">默认</option>
                        <option value="Y">已完成</option>
                        <option value="N">未完成</option>
                    </select>
                </td>
                <td></td>
                <td></td>
                <td></td>
                <td>
                    <div  onclick="toPage(1);" style="background-color:rgba(68,181,73,1);width: 94px;height: 35px;border-radius: 4px;margin-left: -237px;padding-top: 1px">
                        <img alt= "上传附件" style="margin-top: 12px;margin-left: 15px" src="<s:url value='/css/skin/LightBlue/images/u67.svg'/>">
                        <p style="font-size: 14px;line-height: 24px;margin-top: -20px;margin-left: 39px;color: white">查&#12288;询</p>
                    </div>
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="doctorListZi" style="width: 95%">

</div>