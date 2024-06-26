<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>${sysCfgMap['sys_title_name']}</title>
    <jsp:include page="/jsp/czyyjxres/htmlhead-czyyjxres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <script type="text/javascript">

        function saveWorkday(){
            if(false == $("#editForm").validationEngine("validate")){
                return false;
            }
            jboxStartLoading();
            jboxPost("<s:url value='/czyyjxres/hospital/saveWorkday'/>",$("#editForm").serialize(),function(resp){
                jboxEndLoading();
                if(resp=='${GlobalConstant.SAVE_SUCCESSED}') {
                    jboxTip("保存成功");
                    window.parent.workdaysMaintenance();
                    setTimeout(function(){
                        jboxClose();
                    },2000)
                }else{
                    jboxTip(resp);
                }
            },null,false);
        }

        $(document).ready(function(){
            $('.datepicker').datepicker(
                    {format:'yyyy-mm-dd'});
//            $('#batchDate').datepicker(
//                    { language: "zh-CN",
//                        todayHighlight: true,
//                        format: 'yyyy-mm',
//                        autoclose: true,
//                        startView: 'months',
//                        maxViewMode:'months',
//                        minViewMode:'months' });
        });
    </script>
</head>
<body>
    <div class="div_table">
        <form id="editForm">
            <table class="grid" style="margin-top: 20px;">
                <input type="hidden" name="recordFlow" value="${workday.recordFlow}" />
                <tr>
                    <th style="text-align: right;">日期：</th>
                    <td style="text-align: left;">
                        <input type="text" readonly="readonly" class="input validate[required] datepicker" name="weekendDate" value="${workday.weekendDate}"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div align="center" style="margin-top: 20px; margin-bottom:20px;">
        <input type="button" class="btn_green" style="width:100px;" onclick="saveWorkday();" value="保&#12288;存" />
        <input type="button" class="btn_green" style="width:100px;" onclick="jboxClose();" value="关&#12288;闭"/>
    </div>
</body>
</html>