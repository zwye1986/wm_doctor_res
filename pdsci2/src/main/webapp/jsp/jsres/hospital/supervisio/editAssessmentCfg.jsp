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

        function dataFunction(date) {
            var year=date.getFullYear();
            /*
            在日期格式中，月份是从0开始的，因此要加0
            使用三元表达式在小于10的前面加0，以达到格式统一 如 09:11:05
            */
            let month= date.getMonth()+1<10 ? "0"+(date.getMonth()+1) : date.getMonth()+1;
            let day=date.getDate()<10 ? "0"+date.getDate() : date.getDate();
            let hours=date.getHours()<10 ? "0"+date.getHours() : date.getHours();
            let minutes=date.getMinutes()<10 ? "0"+date.getMinutes() : date.getMinutes();
            let seconds=date.getSeconds()<10 ? "0"+date.getSeconds() : date.getSeconds();
            // 拼接
            return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
        }

        function saveSubject() {
            jboxStartLoading();
            if (false == $("#editForm").validationEngine("validate")) {
                jboxEndLoading();
                return false;
            }
            var sessionNumber = $("#sessionNumber").val();
            if (sessionNumber == null || sessionNumber == "") {
                jboxTip("自评年份不能为空！");
                jboxEndLoading();
                return false;
            }
            var startTime = $("#startTime").val();
            var endTime = $("#endTime").val();
         /*   var nowData = dataFunction(new Date);
            if (nowData>startTime){
                jboxTip("开始时间不能小于当前时间！");
                jboxEndLoading();
                return false;
            }*/
            if (startTime > endTime) {
                jboxTip("开始时间不能大于结束时间！");
                jboxEndLoading();
                return false;
            }
            var url= "<s:url value='/jsres/supervisio/saveAssessmentCfg'/>";
            jboxPost(url, $('#editForm').serialize(), function (resp) {
                if (resp == '${GlobalConstant.SAVE_SUCCESSED}' || resp == '${GlobalConstant.UPDATE_SUCCESSED}') {
                    window.parent.toPage(1);
                    setTimeout(function () {
                        top.jboxCloseMessager();
                    }, 2000);
                    jboxEndLoading();
                    jboxTip(resp);
                } else {
                    jboxEndLoading();
                    jboxTip(resp);
                }
            }, null, false);
        }
    </script>
</head>

<body>
<div class="infoAudit">
    <div class="div_table">
        <form id="editForm" style="position: relative;height: 245px;" method="post">
            <input type="hidden" name="cfgFlow" value="${cfg.cfgFlow }"/>
            <input type="hidden" name="type" value="${type}"/>
            <table border="0" cellpadding="0" cellspacing="0" class="base_info" style="margin-top: 20px;">
                <colgroup>
                    <col width="35%"/>
                    <col width="65%"/>
                </colgroup>
                <tbody>
                <tr>
                    <th><span style="color:red;">*&nbsp;</span>自评年份：</th>
                    <td><input class="input" name="sessionNumber" id="sessionNumber" type="text" style="width: 200px;"
                               value="${cfg.sessionNumber==null?pdfn:getCurrYear():cfg.sessionNumber}"/>
                    </td>
                </tr>
                <tr>
                    <th><span style="color:red;">*&nbsp;</span>开始时间：</th>
                    <td>
                        <input name="startTime" id="startTime"  class="input validate[required]" style="width: 200px;"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${cfg.startTime }">
                    </td>
                </tr>
                <tr>
                    <th><span style="color:red;">*&nbsp;</span>结束时间：</th>
                    <td>
                        <input name="endTime" id="endTime" class="input validate[required]" style="width: 200px;"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${cfg.endTime}">
                    </td>
                </tr>
                </tbody>
            </table>
        </form>

        <div class="button" >
            <input type="button" class="btn_green" onclick="saveSubject();" value="保&#12288;存"/>
            <input type="button" class="btn_green" onclick="top.jboxCloseMessager();" value="关&#12288;闭"/>
        </div>
    </div>
</div>
</body>
</html>