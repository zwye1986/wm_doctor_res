<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>${sysCfgMap['sys_title_name']}</title>
    <jsp:include page="/jsp/zseyjxres/htmlhead-gzzyjxres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        //退回操作
        function returnInfo(){
            var data ={
                "resumeFlow":'${resumeFlow}',
                "userFlow":'${userFlow}',
                "reason":$("#reason").val()
            }
            jboxConfirm("确认退回?", function(){
                jboxPost("<s:url value='/zseyjxres/hospital/returnInfo'/>",data, function(resp){
                    jboxTip(resp);
                   jboxClose();
                } , null , true);
            });
        }
    </script>
</head>
<body>
    <div class="div_table">
         <table class="grid" style="margin-top: 20px;">
                <tr>
                    <th style="text-align: right;">原因：</th>
                    <td style="text-align: left;">
                        <textarea id="reason" class="validate[required]"></textarea>
                    </td>
                </tr>
            </table>
    </div>
    <div align="center" style="margin-top: 20px; margin-bottom:20px;">
        <input class="btn_green" style="width:60px;" readonly="readonly" onclick="returnInfo();" value="确&#12288;定" />
        <input class="btn_green" style="width:60px;" readonly="readonly" onclick="jboxClose();" value="关&#12288;闭" />
    </div>
</body>
</html>