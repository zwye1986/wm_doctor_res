<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_ui_combobox" value="false"/>
    <jsp:param name="jquery_ui_sortable" value="false"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
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
    }
    .searchTable .td_right{
        width: 200px;
        text-align:left;
    }
</style>
<link href="<s:url value='/css/form.css'/>" rel="stylesheet" type="text/css">
<script type="text/javascript"
        src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#sessionNumber').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode:2,
            format:'yyyy'
        });
        $('#form_btn').empty();
        let checkOpen = $("#checkOpen").val();
        if (checkOpen && "Y" == checkOpen) {
            $('#form_btn').append("" +
                "<input class='btn_green' type='button' " +
                "value='查&#12288;询' onclick='toPage(1);'/>" +
                "<input class='btn_green' type='button' value='导&#12288;入' onclick='toImport2();'/>"
            )
        }else {
            $('#form_btn').append("" +
                "<input class='btn_green' type='button' " +
                "value='查&#12288;询' onclick='toPage(1);'/>" +
                "<input class='btn_green' type='button' value='导&#12288;入' onclick='toImport();'/>"
            )
        }
        toPage();
    });

    function toPage(page) {
        $("#currentPage").val(page);
        jboxStartLoading();
        jboxPostLoad("doctorListZi", "<s:url value='/jsres/doctorRecruit/schedulingAuditList'/>", $("#searchForm").serialize(), false);
    }

    function toImport() {
        jboxOpen("<s:url value='/jsres/doctorRecruit/importSchedulingAudit'/>", "导入信息", 500, 220);
    }
    function toImport2() {
        jboxOpen("<s:url value='/jsres/doctorRecruit/importSchedulingAudit2'/>", "导入信息", 500, 220);
    }
    function toImportCache() {
        console.log('toImportCache1',new Date().getMinutes()+":"+new Date().getSeconds())
        var width = 1500;
        var height = 800;
        var url ='<s:url value="/jsres/doctorRecruit/importSchedulingiImport"/>';
        var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='"+width+"px' height='"+height+"px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
        jboxMessager(iframe,'导入预览',width,height);
        console.log('toImportCach2',new Date().getMinutes()+":"+new Date().getSeconds())
    }

    function checkTime() {
        var schStartDate = $("#schStartDate").val();
        var schEndDate = $("#schEndDate").val();
        if (schStartDate !="" && schEndDate!="" && schStartDate>schEndDate){
            $("#schEndDate").val("")
            jboxTip("轮转开始时间不能大于结束时间")
        }
    }

</script>
<div class="main_hd">
    <h2 class="underline">排班管理 — 排班安排</h2>
</div>
<div class="div_search" style="line-height:normal;">
    <input type="hidden" id="importCacheFlag" name="importCacheFlag" value="${importCacheFlag}"/>
    <input type="hidden" id="checkOpen" name="checkOpen" value="${checkOpen}">
    <form id="searchForm">
        <input type="hidden" id="currentPage" name="currentPage"/>

        <div class="form_search">
            <div class="form_item">
                <div class="form_label">专&#12288;&#12288;业：</div>
                <div class="form_content">
                    <select name="speId" id="speId" class="select" >
                        <option value="">全部</option>
                        <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                            <option value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}
                                    <c:if test="${'3500' eq dict.dictId}">style="display: none" </c:if>
                                    <c:if test="${'3700' eq dict.dictId}">style="display: none" </c:if>
                            >${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form_item">
                <div class="form_label">轮转科室：</div>
                <div class="form_content">
                    <select name="schDeptFlow" id="schDeptFlow" class="select" >
                        <option value="">全部</option>
                        <c:forEach items="${deptList}" var="d">
                            <option value="${d.deptFlow}" ${deptFlow eq d.deptFlow?'selected':''}>${d.deptName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form_item">
                <div class="form_label">姓&#12288;&#12288;名：</div>
                <div class="form_content">
                   <input class="input" name="userName"  value=""/>
                </div>
            </div>

            <div class="form_item">
                <div class="form_label">开始时间：</div>
                <div class="form_content">
                    <input name="schStartDate" id="schStartDate"  placeholder="请选择开始时间"
                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="checkTime();" value="" class="input">
                </div>
            </div>

            <div class="form_item">
                <div class="form_label">结束时间：</div>
                <div class="form_content">
                    <input name="schEndDate" id="schEndDate"  placeholder="请选择结束时间"
                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="checkTime();" value="" class="input">
                </div>
            </div>

            <div class="form_item">
                <div class="form_label">年&#12288;&#12288;份：</div>
                <div class="form_content">
                    <input class="input" name="sessionNumber" id="sessionNumber" readonly="readonly"  value="${param.sessionNumber}"/>
                </div>
            </div>

        </div>

        <div class="form_btn" id="form_btn">
            <input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>
            <input class="btn_green" type="button" value="导&#12288;入" onclick="toImport2();"/>
        </div>


<%--        <table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">--%>
<%--            <tr>--%>
<%--                <td class="td_left">--%>
<%--                    <nobr>专&#12288;&#12288;业：</nobr>--%>
<%--                </td>--%>
<%--                <td class="td_right">--%>
<%--                    <select name="speId" id="speId" class="select" >--%>
<%--                        <option value="">全部</option>--%>
<%--                        <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">--%>
<%--                            <option value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}--%>
<%--                                    <c:if test="${'3500' eq dict.dictId}">style="display: none" </c:if>--%>
<%--                                    <c:if test="${'3700' eq dict.dictId}">style="display: none" </c:if>--%>
<%--                            >${dict.dictName}</option>--%>
<%--                        </c:forEach>--%>
<%--                    </select>--%>
<%--                </td>--%>
<%--                <td class="td_left">--%>
<%--                    <nobr>轮转科室：</nobr>--%>
<%--                </td>--%>
<%--                <td class="td_right">--%>
<%--                    <select name="schDeptFlow" id="schDeptFlow" class="select" >--%>
<%--                        <option value="">全部</option>--%>
<%--                        <c:forEach items="${deptList}" var="d">--%>
<%--                            <option value="${d.deptFlow}" ${deptFlow eq d.deptFlow?'selected':''}>${d.deptName}</option>--%>
<%--                        </c:forEach>--%>
<%--                    </select>--%>
<%--                </td>--%>
<%--                <td class="td_left">--%>
<%--                    <nobr>姓&#12288;&#12288;名：</nobr>--%>
<%--                </td>--%>
<%--                <td class="td_right">--%>
<%--                    <input class="input" name="userName"  value=""/>--%>
<%--                </td>--%>

<%--                <td class="td_left">--%>
<%--                    <nobr>开始时间：</nobr>--%>
<%--                </td>--%>
<%--                <td class="td_right">--%>
<%--                    <input name="schStartDate" id="schStartDate"  placeholder="请选择开始时间"--%>
<%--                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="checkTime();" value="" class="input">--%>
<%--                </td>--%>
<%--                <td class="td_left">--%>
<%--                    <nobr>结束时间：</nobr>--%>
<%--                </td>--%>
<%--                <td class="td_right">--%>
<%--                    <input name="schEndDate" id="schEndDate"  placeholder="请选择开始时间"--%>
<%--                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="checkTime();" value="" class="input">--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td>--%>
<%--                    <nobr>年&#12288;&#12288;份：</nobr>--%>
<%--                </td>--%>
<%--                <td class="td_right">--%>
<%--                    <input class="input" name="sessionNumber" id="sessionNumber" readonly="readonly"  value="${param.sessionNumber}"/>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--           &lt;%&ndash;     <td class="td_left">--%>
<%--                    <nobr>状&#12288;&#12288;态：</nobr>--%>
<%--                </td>--%>
<%--                <td>--%>
<%--                    <select name="baseAudit" class="select" style="width: 180px;">--%>
<%--                        <option value="">请选择</option>--%>
<%--                        <option value="Passing">待审核</option>--%>
<%--                        <option value="Passed">审核通过</option>--%>
<%--                        <option value="UnPassed">审核不通过</option>--%>
<%--                    </select>--%>
<%--                </td>&ndash;%&gt;--%>

<%--                <td colspan="6">--%>
<%--                    <input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>&#12288;--%>
<%--                    <input class="btn_green" type="button" value="导&#12288;入" onclick="toImport();"/>&#12288;--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--        </table>--%>

    </form>
</div>
<div id="doctorListZi" style="padding: 0 30px;">

</div>