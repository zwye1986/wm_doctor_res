<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="login" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<style type="text/css">
    .boxHome .item:HOVER {
        background-color: #eee;
    }

    .cur {
        color: red
    }
</style>
<script type="text/javascript">
    $(document).ready(function () {
        toPage(1);
    });

    function search() {
        var url = "<s:url value='/jsres/attendanceNew/doctorleaveVerifyList'/>";
        jboxPostLoad("contentDiv", url, $("#searchForm").serialize(), true);
    }

    function exportExcel(){
        var url = "<s:url value='/jsres/attendanceNew/exportExcel'/>";
        jboxTip("导出中…………");
        jboxSubmit($("#searchForm"), url, null, null, false);
        jboxEndLoading();
    }

    function addLeave(isLeaveFlag) {
        if(isLeaveFlag == "N"){
            jboxTip("暂无轮转信息，无法申请请假！");
            return false;
        }
        var url = "<s:url value='/jsres/attendanceNew/editLeave'/>" ;
        jboxOpen(url, "新增", 600, 540);
    }

    function toPage(page) {
        if (!page) {
            page = 1;
        }
        $("#currentPage").val(page);
        search();
    }
</script>
<div class="main_hd">
    <h2 class="underline">请假管理</h2>
</div>
<div class="main_bd" id="div_table_0">
    <div class="div_search">
        <form id="searchForm">
            <input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage }">
            <table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;margin-top: -35px;">
                <tr>
                    <td class="td_left">请假类型：</td>
                    <td>
                        <select class="select" name="typeId" style="width: 160px">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumLeaveTypeList}" var="dict">
                                <option <c:if test="${param.typeId eq dict.dictId}">selected="selected"</c:if>
                                        value="${dict.dictId}">${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td class="td_left">&#12288;轮转科室：</td>
                    <td>
                        <select class="select" name="schDeptFlow" style="width: 160px">
                            <option value="">请选择</option>
                            <c:forEach items="${deptList}" var="dept">
                                <option <c:if test="${param.schDeptFlow eq dept.schDeptFlow}">selected="selected"</c:if>
                                        value="${dept.schDeptFlow}">${dept.schDeptName}(${dept.schStartDate}~${dept.schEndDate})</option>
                            </c:forEach>
                        </select>
                    </td>&#12288;
                    <td class="td_left">&#12288;请假状态：</td>
                    <td>
                        <select class="select" name="auditStatusId" style="width: 160px">
                            <option value="">请选择</option>
                            <option value="Auditing" <c:if test="${'Auditing' eq param.auditStatusId}">selected="selected"</c:if>>请假申请中</option>
                            <option value="ManagerPass" <c:if test="${'ManagerPass' eq param.auditStatusId}">selected="selected"</c:if>>请假审核通过</option>
                            <option value="ManagerUnPass" <c:if test="${'ManagerUnPass' eq param.auditStatusId}">selected="selected"</c:if>>请假审核不通过</option>
                            <option value="Revokeing" <c:if test="${'Revokeing' eq param.auditStatusId}">selected="selected"</c:if>>销假申请中</option>
                            <option value="RevokeManagerPass" <c:if test="${'RevokeManagerPass' eq param.auditStatusId}">selected="selected"</c:if>>销假审核通过</option>
                            <option value="BackLeave" <c:if test="${'BackLeave' eq param.auditStatusId}">selected="selected"</c:if>>已撤销</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="6">
                        <input class="btn_green" type="button" onclick="toPage(1);" value="查&#12288;询"/>&#12288;
                        <input class="btn_green" type="button" onclick="addLeave('${isLeaveFlag}')" value="新&#12288;增"/>&#12288;
                        <input class="btn_green" type="button" value="导&#12288;出" onclick="exportExcel();"/>&#12288;
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="search_table" id="contentDiv" style="padding: 0 10px;">

    </div>
</div>