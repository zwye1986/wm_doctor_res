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
        if ('${roleFlag}' == 'local'){
            jboxPostLoad("doctorListZi", "<s:url value='/jsres/phyAss/baseListEntry'/>", $("#searchForm").serialize(), false);
        }else {
            jboxPostLoad("doctorListZi", "<s:url value='/jsres/phyAss/plannedReleaseList'/>", $("#searchForm").serialize(), false);
        }
    }

    function importMain() {
        var planFlow = $("#planFlow").val();
        var speName = $("#speName").val();
        var planStatusId = $("#planStatusId").val();
        var enrollStartTime = $("#enrollStartTime").val();
        var enrollEndTime = $("#enrollEndTime").val();
        var url = "<s:url value ='/jsres/phyAss/baseListEntry'/>?type=import&currentPage=1&planFlow="+planFlow+"&speName="+speName+
            "&planStatusId="+planStatusId+"&enrollStartTime="+enrollStartTime+"&enrollEndTime="+enrollEndTime;

        var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
        jboxMessager(iframe, '导入人员', 800 ,700);

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
                    <nobr>培&ensp;训&ensp;计&ensp;划&ensp;：</nobr>
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
                    <nobr>培&ensp;训&ensp;专&ensp;业&ensp;：</nobr>
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
                    <nobr>状态：</nobr>
                </td>
                <td>
                    <select name="planStatusId" id="planStatusId" class="select" style="width: 150px;">
                        <option value="">全部</option>
                        <c:forEach items="${resTeachQualifiedStatusEnumList}" var="r">
                            <c:if test="${roleFlag ne 'local'}">
                                <option value="${r.id}">${r.name}</option>
                            </c:if>

                            <c:if test="${roleFlag eq 'local' and r.name ne '暂存'}">
                                <option value="${r.id}">${r.name}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </td>

            </tr>
            <tr>
                <td class="td_left">
                    <nobr>报名开始日期：</nobr>
                </td>
                <td>
                    <input name="enrollStartTime"  style="margin-left: 0px;width: 145px;" placeholder="请选择报名开始时间"
                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" id="enrollStartTime"
                           value="${plan.enrollStartTime}" class="input">
                </td>
                <td class="td_left">
                    <nobr>报名结束日期：</nobr>
                </td>
                <td>
                    <input name="enrollEndTime"  style="margin-left: 0px;width: 145px;" placeholder="请选择报名结束时间"
                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" id="enrollEndTime"
                           value="${plan.enrollEndTime}" class="input">
                </td>
                <td colspan="2">
                    <input class="btn_green" type="button" style="margin-left: 27px" value="查&#12288;询" onclick="toPage(1);"/>
                    <c:if test="${roleFlag eq 'local'}">
                        <input class="btn_green" type="button" value="导&#12288;入" onclick="importMain();"/>
                    </c:if>
                    <c:if test="${roleFlag ne 'local'}">
                        <input class="btn_green" type="button" value="新&#12288;增" onclick="editPhyAssMain('add');"/>
                    </c:if>

                </td>

            </tr>
        </table>

    </form>
</div>
<div id="doctorListZi" style="width: 95%">

</div>