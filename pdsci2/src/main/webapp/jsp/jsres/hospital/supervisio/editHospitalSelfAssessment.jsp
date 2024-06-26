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
    <script type="text/javascript"
            src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript"
            src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#sessionNumber').datepicker({
                startView: 2,
                maxViewMode: 2,
                minViewMode: 2,
                format: 'yyyy'
            });
        });

        function saveSubject() {
            jboxStartLoading();
            if (false == $("#editForm").validationEngine("validate")) {
                jboxEndLoading();
                return false;
            }
            var sessionNumber = $("#sessionNumber").val();
            if (sessionNumber == null || sessionNumber == "") {
                jboxTip("检查年份不能为空！");
                jboxEndLoading();
                return false;
            }
            var startTime = $("#startTime").val();
            var endTime = $("#endTime").val();
            if (startTime > endTime) {
                jboxTip("开始时间不能大于结束时间！");
                jboxEndLoading();
                return false;
            }
            var url= "<s:url value='/jsres/supervisio/saveHospitalSelfAssessment'/>";
            jboxPost(url, $('#editForm').serialize(), function (resp) {
                if (resp == '${GlobalConstant.SAVE_SUCCESSED}') {
                    window.parent.toPage(1);
                    setTimeout(function () {
                        top.jboxCloseMessager();
                    }, 2000);
                    jboxEndLoading();
                    jboxTip(resp);
                } else if (resp == '${GlobalConstant.SAVE_FAIL}') {
                    jboxTip(resp);
                }
            }, null, false);
        }
    </script>
</head>

<body>
<div class="infoAudit">
    <div class="div_table">
        <form id="editForm" style="position: relative;" method="post">
            <input type="hidden" name="recordFlow" value="${assessment.recordFlow }"/>
            <table border="0" cellpadding="0" cellspacing="0" class="base_info" style="margin-top: 20px;">
                <colgroup>
                    <col width="35%"/>
                    <col width="65%"/>
                </colgroup>
                <tbody>
                <tr>
                    <th><span style="color:red;">*&nbsp;</span>项目名称：</th>
                    <td>
                        <input class="validate[required] input" style="width: 270px;" name="subjectName" type="text"
                               value="${assessment.subjectName }"
                               <c:if test="${type eq 'edit'}">readonly</c:if>/>
                    </td>
                </tr
                <tr>
                    <th><span style="color:red;">*&nbsp;</span>督导年份：</th>
                    <td><input class="input" name="sessionNumber" id="sessionNumber" type="text"
                               value="${assessment.sessionNumber==null?pdfn:getCurrYear():assessment.sessionNumber}"/>
                    </td>
                </tr>
                <tr>
                    <th><span style="color:red;">*&nbsp;</span>开始时间：</th>
                    <td>
                        <input name="startTime" id="startTime"  class="input validate[required]"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${assessment.startTime }">
                    </td>
                </tr>
                <tr>
                    <th><span style="color:red;">*&nbsp;</span>结束时间：</th>
                    <td>
                        <input name="endTime" id="endTime" class="input validate[required]"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${assessment.endTime}">
                    </td>
                </tr>
                </tbody>
            </table>
        </form>

        <div class="button">
            <input type="button" class="btn_green" onclick="saveSubject();" value="保&#12288;存"/>
            <input type="button" class="btn_green" onclick="top.jboxCloseMessager();" value="关&#12288;闭"/>
        </div>
    </div>
</div>
</body>
</html>