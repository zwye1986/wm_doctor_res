<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_ui_sortable" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <style type="text/css">
        table.basic th, table.basic td {
            text-align: center;
            padding: 0;
        }

    </style>

    <script type="text/javascript">
        function changeGroup(gSelect) {
            $("#stardandDept option[value != '']").remove();
            var groupFlow = $(gSelect).find("option:selected").val();
            $("#" + groupFlow + "_select").find("option").each(function () {
                $(this).clone().appendTo($("#stardandDept"));
            });
        }
        function saveSelResult(selResultTd) {
            var groupFlow = $("#schGroup").find("option:selected").val();
            if (!groupFlow) {
                jboxTip("请选择组名称！");
                return;
            }
            var standardDeptId = $("#stardandDept").find("option:selected").val();
            var standardDeptName = $("#stardandDept").find("option:selected").text();
            if (!standardDeptId) {
                jboxTip("请选标准科室！");
                return;
            }
            var schDeptFlow = $("#schDept").find("option:selected").val();
            if (!schDeptFlow) {
                jboxTip("请选轮转科室！");
                return;
            }
            var doctorFlow = $("#doctorFlow").val();
            var resultFlow = $("#resultFlow").val();
            var data = {
                resultFlow: resultFlow,
                doctorFlow: doctorFlow,
                groupFlow: groupFlow,
                standardDeptId: standardDeptId,
                standardDeptName: standardDeptName,
                schDeptFlow: schDeptFlow
            };
            jboxPost("<s:url value='/sch/arrangeImport/saveRostering'/>", data, function (resp) {
                if (resp == "${GlobalConstant.OPRE_SUCCESSED_FLAG}") {
                    jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
                    setTimeout(function(){
                        window.parent.frames["mainIframe"].window.viewUser("${doctorFlow}");
                        jboxClose();
                    },1000);
                }
            }, null, false);
        }

    </script>
</head>
<body>
<div id="selResultTd">
    <table class="basic" style="margin-left: 10px;width:98%;">
        <input type="hidden" name="doctorFlow"  id="doctorFlow" value="${doctorFlow}">
        <input type="hidden" name="resultFlow"  id="resultFlow" value="${result.resultFlow}">
        <tr>
            <th style="text-align: center;">组名称</th>
            <th style="text-align: center;">标准科室</th>
            <th style="text-align: center;">轮转科室</th>
        </tr>
        <tr>
            <td style="text-align: center;">
                <select id="schGroup" onchange="changeGroup(this);">
                    <option value="">选择组</option>
                    <c:forEach items="${groups}" var="group">
                        <option value="${group.groupFlow}">${group.groupName}</option>
                    </c:forEach>
                </select>
            </td>
            <td style="text-align: center;">
                <select id="stardandDept">
                    <option value="">选择标准科室</option>
                </select>
            </td>
            <td style="text-align: center;">
                <select id="schDept" style="${not empty result.schDeptFlow  ?'display: none':''}">
                    <option value="">选择轮转科室</option>
                    <c:forEach items="${deptList}" var="dept">
                        <option value="${dept.schDeptFlow}" ${result.schDeptFlow eq dept.schDeptFlow ?'selected':''}>${dept.schDeptName}</option>
                    </c:forEach>
                </select>
                ${result.schDeptName}
            </td>
        </tr>
    </table>
</div>
<div style="display: none;">
    <c:forEach items="${groups}" var="group">
        <select id="${group.groupFlow}_select">
            <c:forEach items="${schDeptMap[group.groupFlow]}" var="standardRotationDept">
                <option value="${standardRotationDept.standardDeptId}">${standardRotationDept.standardDeptName}</option>
            </c:forEach>
        </select>
    </c:forEach>
</div>
<div align="center" style="margin: 10px 0px;">
    <input type="button" value="保&#12288;存" class="search"
           onclick="saveSelResult($('#selResultTd'));"/>
    <input type="button" value="关&#12288;闭" class="search" onclick="jboxClose();"/>
</div>
</body>
</html>