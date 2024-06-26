<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
    <script>
        $(document).ready(function(){
            $('#assignYear').datepicker({
                startView: 2,
                maxViewMode: 2,
                minViewMode:2,
                format:'yyyy'
            });
            $('#startTime').datepicker({
                startView: 0,
                maxViewMode: 0,
                minViewMode:0,
                format:'yyyy-mm-dd'
            });
            $('#endTime').datepicker({
                startView: 0,
                maxViewMode: 0,
                minViewMode:0,
                format:'yyyy-mm-dd'
            });
            // 显示基地专业信息
            showOrgSpe('${orgFlow}');
        });

        var flag = "";

        // 查询基地专业信息
        function showOrgSpe(orgFlow,assignYear){
            if (assignYear == null || assignYear == '') {
                jboxLoad("gridTable","<s:url value='/jsres/message/addPlanSpe'/>?assignYear=${param.assignYearEdit}&orgFlow=" + orgFlow+"&flag=${param.flag}",true);
            } else {
                jboxLoad("gridTable","<s:url value='/jsres/message/addPlanSpe'/>?assignYear=" + assignYear + "&orgFlow=" + orgFlow+"&flag=${param.flag}",true);
            }
        }

        // 保存用户
        function saveAssign(){
            if (!$("#editForm").validationEngine("validate")) {
                return;
            }
            var orgFlow = $("#orgFlow").val();
            var assignYear = $("#assignYear").val();
            var startTime = $("#startTime").val();
            var endTime = $("#endTime").val();
            // 招生计划信息
            var assignDatas = [];
            $("#gridTable").find("tr").each(function(){
                var recordFlow = $(this).find("input[name='recordFlow']").val();
                var assignPlan = $(this).find("input[name='assignPlan']").val();
                var graduateSpe = $(this).find("input[name='graduateSpe']").val();
                var education = $(this).find("input[name='education']").val();
                if(recordFlow){
                    var data={
                        "recordFlow" : recordFlow,
                        "assignPlan" : assignPlan,
                        "graduateSpe" : graduateSpe,
                        "education" : education,
                        "orgFlow" : orgFlow,
                        "assignYear" : assignYear,
                        "startTime" : startTime,
                        "endTime" : endTime
                    };
                    assignDatas.push(data);
                }
            });
            if(assignDatas.length == 0){
                jboxTip("请先维护招收专业信息");
                return;
            }
            var data = {orgFlow : orgFlow, assignYear : assignYear, assignYearEdit : '${param.assignYearEdit}',
                startTime : startTime, endTime : endTime, isJointOrg:'${isJointOrg}', assignDataStr : JSON.stringify(assignDatas)};
            var url = "<s:url value='/jsres/message/saveAssign'/>";
            jboxPost(url, data, function (resp) {
                if (resp == '${GlobalConstant.SAVE_SUCCESSED}') {
                    setTimeout(function () {
                        window.parent.getPlanList('${param.assignYear}',1);
                        jboxCloseMessager();
                    },800);
                }
            }, null, true);
        }

        // 切换基地重新加载专业信息
        function changeOrg(orgFlow){
            showOrgSpe(orgFlow);
        }

        // 显示专业简介信息
        function showSpeDescView(assignFlow){
            jboxOpen("<s:url value='/jsres/message/addPlanSpeDesc'/>?assignFlow=" + assignFlow, "专业简介", 700, 500);
        }

        //审核
        function saveAudit(auditStatus) {
            var msg = "";
            if (auditStatus == 'UnPassed') {
                msg = "不通过";
            } else if (auditStatus == 'Passed') {
                msg = "通过";
            }
            jboxConfirm("确认审核"+msg+"？",function () {
                var url = "<s:url value='/jsres/message/saveAudit?auditStatus='/>" + auditStatus+ "&orgFlow=${orgFlow}&assignYear=${param.assignYearEdit}";
                jboxPost(url, null, function (resp) {
                    if (resp == '${GlobalConstant.OPRE_SUCCESSED}') {
                        setTimeout(function () {
                            window.parent.getPlanList('${param.assignYear}',1);
                            jboxCloseMessager();
                        },800);
                    }
                }, null, true);
            });
        }

        // 用以解决时间选择器选择时间之后触发事件重复发送请求的问题，有更好的方案的话可优化
        function operate (orgFlow,assignYear) {
            if (flag != assignYear) {
                flag = assignYear;
                showOrgSpe(orgFlow,assignYear);
            }
        }

    </script>
    <div class="div_table" id="divTable" style="height: 580px; overflow-y: auto;">
        <form id="editForm" method="post">
            <input type="hidden" name="orgFlowEdit" value="${param.orgFlowEdit}">
            <input type="hidden" name="assignYearEdit" value="${param.assignYearEdit}">
            <table class="grid">
                <tr>
                    <th style="text-align: right;">基地名称：</th>
                    <td style="text-align: left;">
                        <select name="orgFlow" id="orgFlow" class="select validate[required]" style="width: 163px;height: 30px;" onchange="changeOrg(this.value)" <c:if test="${not empty param.orgFlowEdit}">disabled</c:if>>
                            <c:forEach var="org" items="${orgList}">
                                <option value="${org.orgFlow}" <c:if test="${(empty param.orgFlowEdit and org.orgFlow eq orgFlow) or (not empty param.orgFlowEdit and org.orgFlow eq param.orgFlowEdit)}">selected="selected"</c:if>>${org.orgName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <th style="text-align: right;"><span class="red">*</span>招收年份：</th>
                    <td style="text-align: left;">
                        <input class="input validate[required]" name="assignYear" id="assignYear" readonly="readonly" onchange="operate('${orgFlow}',this.value);" value="${param.assignYearEdit}" <c:if test="${not empty param.assignYearEdit}">disabled</c:if> type="text"/>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;"><span class="red">*</span>开始时间：</th>
                    <td style="text-align: left;">
                        <input class="input validate[required]" name="startTime" id="startTime" readonly="readonly" value="${param.startTime}" <c:if test="${param.flag eq 'audit'}">disabled</c:if> type="text"/>
                    </td>
                    <th style="text-align: right;"><span class="red">*</span>结束时间：</th>
                    <td style="text-align: left;">
                        <input class="input validate[required]" name="endTime" id="endTime" readonly="readonly" value="${param.endTime}" <c:if test="${param.flag eq 'audit'}">disabled</c:if> type="text"/>
                    </td>
                </tr>
            </table>
            <table border="0" cellpadding="0" cellspacing="0" class="grid" id="gridTable">

            </table>
        </form>
        <div align="center" style="margin-top: 20px; margin-bottom:20px;">
            <c:if test="${flag ne 'audit'}">
                <input type="button" class="btn_green" onclick="saveAssign();" value="保&#12288;存"/>&#12288;
                <input type="button" class="btn_green" onclick="jboxCloseMessager();" value="关&#12288;闭"/>
            </c:if>
            <c:if test="${flag eq 'audit'}">
                <input type="button" class="btn_green" onclick="saveAudit('Passed');" value="通过"/>&#12288;
                <input type="button" class="btn_red" onclick="saveAudit('UnPassed');" value="不通过"/>
            </c:if>
        </div>
    </div>