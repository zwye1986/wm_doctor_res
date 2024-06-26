<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_ui_combobox" value="false"/>
    <jsp:param name="jquery_ui_sortable" value="false"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_scrollTo" value="false"/>
    <jsp:param name="jquery_jcallout" value="false"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_fullcalendar" value="false"/>
    <jsp:param name="jquery_fngantt" value="false"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
    <jsp:param name="jquery_iealert" value="false"/>
    <jsp:param name="jquery_datePicker" value="true"/>
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
<script type="text/javascript" src="<s:url value='/js/itemSelect/itemSelect2.js'/>"></script>
<script type="text/javascript">
    $(document).ready(function () {
        toPage();
    });

    function toPage(page) {
        $("#currentPage").val(page);
        jboxStartLoading();
        jboxPostLoad("doctorListZi", "<s:url value='/jsres/phyAss/plannedMaintainList'/>", $("#searchForm").serialize(), false);
    }

    function editPhyAssMain(type) {
        var title = "新增";
        if (type == 'edit') {
            title = "修改";
        }
        var url = "<s:url value ='/jsres/phyAss/editPlannedReleaseMain'/>?type="+type;
        jboxOpen(url, title+"培训计划", 1000, 700);
    }

    function importExcelUser() {
        jboxOpen("<s:url value='/jsres/supervisio/importExcelUser'/>", "导入专家信息", 500, 200);
    }

    function exportSupervisioUser() {
        var url = "<s:url value='/jsres/supervisio/exportSupervisioUser'/>";
        jboxTip("导出中…………");
        jboxSubmit($("#searchForm"), url, null, null, false);
        jboxEndLoading();
    }
</script>
<div class="main_hd">
    <h2 class="underline"></h2>
</div>
<div class="div_search" style="width: 95%;line-height:normal;">
    <form id="searchForm">
        <input type="hidden" id="currentPage" name="currentPage"/>
        <table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">
            <tr>
                <td class="td_left">
                    <nobr>培训计划：</nobr>
                </td>
                <td>
                    <select name="planFlow" id="planFlow" class="select" style="width: 150px;">
                        <option value="">全部</option>
                        <c:forEach items="${contentList}" var="c">
                            <option value="${c.planFlow}" ${planFlow eq c.planFlow?'selected':''}>${c.planContent}</option>
                        </c:forEach>
                    </select>
                </td>
                <td class="td_left">
                    <nobr>培训专业：</nobr>
                </td>
                <td>
                    <select name="speName" id="speName" class="select" style="width: 150px;">
                        <option value="">全部</option>
                        <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                            <option value="${dict.dictName}" ${sepName eq dict.dictName?'selected':''}>${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </td>
                <td class="td_left">
                    <nobr>科&#12288;&#12288;室：</nobr>
                </td>
                <td>
                    <select name="deptFlow" id="deptFlow" class="select" style="width: 150px;">
                        <option value="">全部</option>
                        <c:forEach items="${depts}" var="dept">
                            <option value="${dept.deptFlow}"
                                    <c:if test="${param.deptFlow eq dept.deptFlow}">selected</c:if>>${dept.deptName}</option>
                        </c:forEach>
                    </select>
                </td>

            </tr>
            <tr>
                <td class="td_left">
                    <nobr>姓&#12288;&#12288;名：</nobr>
                </td>
                <td>
                    <input name="doctorName" style="margin-left: 0px;width: 145px;" placeholder="请选择姓名" value="${doctorName}" class="input">
                </td>
                <td class="td_left">
                    <nobr>状&#12288;&#12288;态：</nobr>
                </td>
                <td>
                    <select name="type" id="type" class="select" style="width: 150px;">
                        <option value="">全部</option>
                        <option value="Y">已上报</option>
                        <option value="N">未上报</option>

                    </select>
                </td>
                <td colspan="2">
                    <input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>
                </td>
            </tr>
        </table>

    </form>
</div>
<div id="doctorListZi" style="width: 95%">

</div>