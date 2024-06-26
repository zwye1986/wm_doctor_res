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
<%--<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>--%>
<script type="text/javascript" src="<s:url value='/js/itemSelect/itemSelect2.js'/>"></script>
<script type="text/javascript">
    $(document).ready(function () {
        toPage();
    });

    function toPage(page) {
        $("#currentPage").val(page);
        jboxStartLoading();
        jboxPostLoad("doctorListZi", "<s:url value='/jsres/supervisio/personList'/>?roleFlag=${roleFlag}&suAoth=${suAoth}", $("#searchForm").serialize(), false);
    }

    function editSupervisioUser(type, userFlow) {
        var title = "新增";
        if (type == 'edit') {
            title = "修改";
        }
        var url = "<s:url value ='/jsres/supervisio/editSupervisioUser'/>?userFlow=" + userFlow + "&type=" + type+"&suAoth=${suAoth}";
        jboxOpen(url, title, 800, 315);
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
    <h2 class="underline">督导管理 — 专家管理</h2>
</div>
<div class="div_search" style="width: 95%;line-height:normal;">
    <form id="searchForm">
        <input type="hidden" id="currentPage" name="currentPage"/>
        <table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">
            <tr>
                <td class="td_left">
                    <nobr>专家姓名：</nobr>
                </td>
                <td>
                    <input type="text" name="userName" value="${param.userName}" class="input"
                           style="width: 145px;margin-left: 0px;"/>
                </td>
                <td class="td_left">
                    <nobr>手机号码：</nobr>
                </td>
                <td>
                    <input type="text" name="userPhone" value="${param.userName}" class="input"
                           style="width: 145px;margin-left: 0px;"/>
                </td>

            </tr>
            <tr>
                <td class="td_left">
                    <nobr>专家分类：</nobr>
                </td>
                <td>
                    <select name="userLevelId" id="userLevelId" class="select" style="width: 150px;">
                        <c:if test="${suAoth eq 'Y'}">
                            <option value="baseExpert" ${param.userLevelId eq 'baseExpert'?'selected':''}>专业专家</option>
                        </c:if>
                        <c:if test="${suAoth ne 'Y'}">
                            <option value="">全部</option>
                            <option value="expertLeader" ${param.userLevelId eq 'expertLeader'?'selected':''}>专业专家</option>
                            <option value="management" ${param.userLevelId eq 'management'?'selected':''}>管理专家</option>
                        </c:if>
                    </select>
                </td>
                <td class="td_left">
                    <nobr>专&#12288;&#12288;业：</nobr>
                </td>
                <td>
                    <select name="trainingSpeId" id="trainingSpeId" class="select" style="width: 150px;">
                        <option value="">全部</option>
                        <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                            <option value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}
                                    <c:if test="${'3500' eq dict.dictId}">style="display: none" </c:if>
                                    <c:if test="${'3700' eq dict.dictId}">style="display: none" </c:if>
                            >${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="8">
                    <input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>&#12288;
                    <input class="btn_green" type="button" value="新&#12288;增" onclick="editSupervisioUser('add','');"/>&#12288;
                    <input class="btn_green" type="button" value="导&#12288;入" onclick="importExcelUser();"/>&#12288;
                    <input class="btn_green" type="button" value="导&#12288;出" onclick="exportSupervisioUser();"/>
                </td>
            </tr>
        </table>

    </form>
</div>
<div id="doctorListZi" style="width: 95%">

</div>