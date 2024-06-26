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
        var url = "<s:url value='/jsres/statistic/auditTeacherApplication'/>";
        jboxPostLoad("contentDiv", url, $("#submitForm").serialize(), true);
    }

    function toPage(page) {
        if (!page) {
            page = 1;
        }
        console.log(page);
        $("#currentPage").val(page);
        search();
    }

    function changeStatus() {
        if ($("#trainOrg").val().replace(/(^\s*)|(\s*$)/g, "") == "") {
            $("#orgFlow").val("");
        }
    }

    function batchAuditTeacherApplication() {
        var checkLen = $(":checkbox[class='check']:checked").length;
        if(checkLen == 0){
            jboxTip("请勾选待审核信息！");
            return;
        }
        var recordFlowList = [];
        $(":checkbox[class='check']:checked").each(function(){
            recordFlowList.push(this.value);
        })

        var roleFlag = "${roleFlag }";
        var url = "<s:url value='/jsres/statistic/batchAuditTeacherApplication'/>?recordFlowList=" + recordFlowList + "&roleFlag=" + roleFlag;
        jboxOpen(url, "批量审核", 400, 200);
    }
    
</script>
<div class="main_hd">
    <h2 class="underline">审核师资申请</h2>
</div>
<div class="main_bd">
    <div class="div_search">
        <form id="submitForm">
            <input type="hidden" id="roleFlag" name="roleFlag" value="${roleFlag }">
            <input type="hidden" id="deptFlow" name="deptFlow" value="${deptFlow }">
            <input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage }">
            <table>
                <tr>
                    <td>
                       姓&#12288;&#12288;名：<input type="text" value="${param.doctorName}" class="input" name="doctorName"
                                                  style="width: 100px;"/>
                        &#12288;手机号码：<input type="text" value="${param.userPhone}" class="input" name="userPhone"
                                                          style="width: 100px;"/>
                        &#12288;师资类型：&nbsp;
                        <select class="select" name="teacherLevelId" style="width: 100px">
                            <option value=""></option>
                            <option <c:if test="${param.teacherLevelId eq 'GeneralFaculty'}">selected="selected"</c:if> value="GeneralFaculty">一般师资</option>
                            <option <c:if test="${param.teacherLevelId eq 'KeyFaculty'}">selected="selected"</c:if> value="KeyFaculty">骨干师资</option>
                        </select>
                        &#12288;审核状态：&nbsp;
                        <select class="select" name="applicationAuditStatus" style="width: 100px">
                            <option value=""></option>
<%--                            <c:if test="${roleFlag == 'head'}">--%>
<%--                                <option <c:if test="${param.applicationAuditStatus eq 'HeadAuditing'}">selected="selected"</c:if> value="HeadAuditing">待科主任审核</option>--%>
<%--                                <option <c:if test="${param.applicationAuditStatus eq 'HeadPassed'}">selected="selected"</c:if> value="HeadPassed">科主任审核通过</option>--%>
<%--                                <option <c:if test="${param.applicationAuditStatus eq 'HeadNotPassed'}">selected="selected"</c:if> value="HeadNotPassed">科主任审核不通过</option>--%>
<%--                            </c:if>--%>
<%--                            <c:if test="${roleFlag == 'local'}">--%>
                                <option <c:if test="${param.applicationAuditStatus eq 'HeadAuditing'}">selected="selected"</c:if> value="HeadAuditing">待科主任审核</option>
                                <option <c:if test="${param.applicationAuditStatus eq 'HeadNotPassed'}">selected="selected"</c:if> value="HeadNotPassed">科主任审核不通过</option>
                                <option <c:if test="${param.applicationAuditStatus eq 'HeadPassed'}">selected="selected"</c:if> value="HeadPassed">待基地审核</option>
                                <option <c:if test="${param.applicationAuditStatus eq 'Passed'}">selected="selected"</c:if> value="Passed">基地审核通过</option>
                                <option <c:if test="${param.applicationAuditStatus eq 'NotPassed'}">selected="selected"</c:if> value="NotPassed">基地审核不通过</option>
<%--                            </c:if>--%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input class="btn_green" type="button" onclick="toPage(1);" value="查&#12288;询"/>&#12288;
                        <input class="btn_green" type="button" onclick="batchAuditTeacherApplication();" value="一键审核"/>&#12288;
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="search_table" id="contentDiv" style="padding: 0 10px;">

    </div>
</div>