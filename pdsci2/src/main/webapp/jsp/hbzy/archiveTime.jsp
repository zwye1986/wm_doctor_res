<jsp:include page="/jsp/hbzy/htmlhead-jszy.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_ui_tooltip" value="true"/>
</jsp:include>
<script type="text/javascript">
    $(document).ready(function(){
        $('#sessionNumber').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode:2,
            format:'yyyy'
        });
    });
    function saveArchive() {
        var archiveTime = $("#archiveTime").val();
        var sessionNumber = $("#sessionNumber").val();
        if(!sessionNumber){
            jboxTip("请选择年级！");
            return false;
        }
        var url = "<s:url value="/hbzy/archive/archiveInfo"/>?archiveTime=" + archiveTime + "&sessionNumber=" + sessionNumber;
        jboxStartLoading();
        jboxPost(url, null, function (resp) {
            if (resp == "${GlobalConstant.OPRE_SUCCESSED}") {
                jboxEndLoading();
                jboxTip("存档成功！");
                setTimeout(function(){
                    jboxClose();
                },1000);
            } else {
                jboxEndLoading();
                jboxTip("存档失败！请确认该年级下是否有学员！");
                setTimeout(function(){
                    jboxClose();
                },2000);
                jboxEndLoading();
            }
        }, null, false);

    }
</script>
<div class="div_table">
    <form>
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th style="width: 30%">年&#12288;&#12288;级：</th>
                <td style="width: 70%">
                    <input type="text" id="sessionNumber" name="sessionNumber" class="input" readonly="readonly" style="width: 100px;height: 30px;"/>
                </td>
            </tr>
            <tr>
                <th style="width: 30%">存档时间：</th>
                <td style="width: 70%">
                    <input type="hidden" value="${pdfn:getCurrDateTime('yyyy-MM-dd HH:mm:ss')}" id="archiveTime"/>
                    <label>${pdfn:getCurrDateTime('yyyy-MM-dd HH:mm:ss')}</label>
                </td>
            </tr>
        </table>
    </form>
    <div style="padding-bottom: 20px;padding-top: 80px;text-align: center;">
        <input class="btn_brown" type="button" value="保&#12288;存"
               onclick="saveArchive();"/>
    </div>
</div>