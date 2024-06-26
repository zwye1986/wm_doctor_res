<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#sessionGrade').datepicker({
                startView: 2,
                maxViewMode: 2,
                minViewMode: 2,
                format: 'yyyy'
            });
            $('#sessionYear').datepicker({
                startView: 2,
                maxViewMode: 1,
                minViewMode: 1,
                format: 'yyyy-mm'
            });
        });

        function save() {
            var sessionGrade = $("#sessionGrade").val();
            var sessionYear = $("#sessionYear").val();
            if (sessionGrade == '') {
                jboxTip("请选择年级！");
                return;
            }
            if (sessionYear == '') {
                jboxTip("请选择节点！");
                return;
            }
            if (sessionGrade > sessionYear) {
                jboxTip("节点必须大于年级！");
                return;
            }
            var url = "<s:url value='/jsres/cfgManager/saveCkxzConfig'/>";
            var data = $('#addDeptCfg').serialize();
            jboxPost(url, data, function (resp) {
                if ('${GlobalConstant.SAVE_SUCCESSED}' == resp) {
                    setTimeout(function () {
                        jboxClose();
                    }, 1000);
                }
            }, null, true);
            window.parent.jboxLoad("div_table", '<s:url value='/jsres/cfgManager/searchDeptConfig'/>?tagId=ckxzCfg', true);
        }
    </script>
</head>
<body>
<div class="infoAudit">
    <form id="addDeptCfg" method="post" style="position:relative;height: 280px;">
        <input type="hidden" name="recordFlow" value="${ckxz.recordFlow}">
        <input type="hidden" name="orgFlow" value="${ckxz.orgFlow}">
        <input type="hidden" name="orgName" value="${ckxz.orgName}">
        <input type="hidden" name="createTime" value="${ckxz.createTime}">
        <input type="hidden" name="createUserFlow" value="${ckxz.createUserFlow}">
        <input type="hidden" name="recordStatus" value="${ckxz.recordStatus}">
        <table border="0" cellpadding="0" cellspacing="0" class="base_info" style="margin-top: 20px;">
            <tr>
                <th style="width: 25%;">年&#12288;&#12288;级：</th>
                <td>
                    <input class="input" name="sessionGrade" id="sessionGrade" style="width: 95%;margin-left: 0px;"
                           class="validate[required]" value="${ckxz.sessionGrade}"/>
                </td>
            </tr>
            <tr>
                <th>节&#12288;&#12288;点：</th>
                <td>
                    <input class="input" name="sessionYear" id="sessionYear" style="width: 95%;margin-left: 0px;"
                           class="validate[required]" value="${ckxz.sessionYear}"/>
                </td>
            </tr>
        </table>
    </form>
    <div class="button">
        <input type="button" class="btn_green" onclick="save();" value="保&#12288;存"/>
        <input type="button" class="btn_green" onclick="jboxClose();" value="关&#12288;闭"/>
    </div>
</div>
</body>
</html>