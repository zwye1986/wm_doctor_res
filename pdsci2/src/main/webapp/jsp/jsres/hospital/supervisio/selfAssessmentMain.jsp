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
<script type="text/javascript">
    $(document).ready(function () {
        $('#sessionNumber').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
        toPage(1);
    });

    function toPage(page) {
        $("#currentPage").val(page);
        jboxStartLoading();
        jboxPostLoad("doctorListZi", "<s:url value='/jsres/supervisio/selfAssessmentList'/>", $("#searchForm").serialize(), false);
    }

    function expertInfo() {
        var url = "<s:url value='/jsres/supervisio/exportSpeAssessment'/>";
        jboxTip("导出中…………");
        jboxExp($("#searchForm"), url);
        jboxEndLoading();
    }

    function addSub(type,recordFlow) {
        var title = "新增";
        if (type == 'edit') {
            title = "修改";
        }
        var url = "<s:url value ='/jsres/supervisio/editHospitalSpeSelfAssessment'/>?recordFlow=" + recordFlow + "&type=" + type;
        var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
        jboxMessager(iframe, title + "项目", 550, 350, false);
    }
</script>
<div class="main_hd">
    <h2 class="underline">专业基地自评</h2>
</div>
<div class="div_search">
    <form id="searchForm">
        <input type="hidden" id="currentPage" name="currentPage"/>

        <div style="display: flex;justify-content: flex-start; column-gap: 56px;margin-top: 15px">
            <div>
                <label class="from_label">自评年份：</label>
                <input class="input" name="sessionNumber" id="sessionNumber" type="text" style="width: 161px;"
                       value="${param.sessionNumber==null?pdfn:getCurrYear():param.sessionNumber}"/>
            </div>
            <div>
                <label class="from_label">专业：</label>
                <select name="speId" id="speId" class="select" style="margin-left:5px;width: 160px;">
                    <option value="">全部</option>
                    <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                        <option value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}
                                <c:if test="${'50' eq dict.dictId}">style="display: none" </c:if>
                        >${dict.dictName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div style="margin-top: 15px;margin-bottom: 15px">
            <input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>
        </div>




<%--        <table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">--%>
<%--            <tr>--%>
<%--                <td class="td_left">--%>
<%--                    <nobr>自评年份：</nobr>--%>
<%--                </td>--%>
<%--                <td>--%>
<%--                    <input class="input" name="sessionNumber" id="sessionNumber" type="text" style="width: 161px;"--%>
<%--                           value="${param.sessionNumber==null?pdfn:getCurrYear():param.sessionNumber}"/>--%>
<%--                </td>--%>

<%--                <td class="td_left">--%>
<%--                    <nobr>专业：</nobr>--%>
<%--                </td>--%>
<%--                <td>--%>
<%--                    <select name="speId" id="speId" class="select" style="margin-left:5px;width: 160px;">--%>
<%--                        <option value="">全部</option>--%>
<%--                        <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">--%>
<%--                            <option value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}--%>
<%--                                    <c:if test="${'50' eq dict.dictId}">style="display: none" </c:if>--%>
<%--                            >${dict.dictName}</option>--%>
<%--                        </c:forEach>--%>
<%--                    </select>--%>
<%--                </td>--%>

<%--                <td colspan="2">--%>
<%--                    <input class="btn_green" style="float: right" type="button" value="查&#12288;询" onclick="toPage(1);"/>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--        </table>--%>
    </form>
</div>
<div id="doctorListZi" >

</div>