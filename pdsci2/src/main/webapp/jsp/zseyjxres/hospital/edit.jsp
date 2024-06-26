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
        function saveSetting(){
            if(false == $("#editForm").validationEngine("validate")){
                return false;
            }
            jboxPost("<s:url value='/zseyjxres/hospital/saveSetting'/>",$("#editForm").serialize(),function(resp){
                jboxTip(resp);
                if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){
                    window.parent.search();
                    setTimeout(function(){
                        jboxClose();
                    },2000)
                }
            },null,false);
        }
        $(document).ready(function(){
            $('.datepicker').datepicker(
                    {format:'yyyy-mm-dd'});
            $('#batchDate').datepicker(
                    { language: "zh-CN",
                        todayHighlight: true,
                        format: 'yyyy-mm',
                        autoclose: true,
                        startView: 'months',
                        maxViewMode:'months',
                        minViewMode:'months' });
        });
    </script>
</head>
<body>
    <div class="div_table">
        <form id="editForm">
            <input type="hidden" name="isAdd" value="${param.isAdd}">
            <input type="hidden" name="batchFlow" value="${stuBatch.batchFlow}">
            <table class="grid" style="margin-top: 20px;">
                <tr>
                    <th style="text-align: right;">进修批次：</th>
                    <td style="text-align: left;">
                        <c:if test="${empty stuBatch.batchFlow}">
                             <input id="batchDate" style="width:156px;"  name="batchDate" class="validate[required] input" readonly="readonly"  value="${stuBatch.batchDate}" type="text">
                        </c:if>
                        <c:if test="${not empty stuBatch.batchFlow}">

                            <input style="width:156px;"  name="batchDate" class="validate[required] input" readonly="readonly"  value="${stuBatch.batchDate}" type="text">
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">报到时间：</th>
                    <td style="text-align: left;">
                        <input type="text" style="width:156px;" class="input validate[required] datepicker" readonly="readonly" name="registerTime" value="${stuBatch.batchRegDate}" />
                    </td>
                </tr>
                <%--<tr>--%>
                    <%--<th style="text-align: right;">进修费用（元/月）：</th>--%>
                    <%--<td style="text-align: left;"><input type="text" style="width:160px;" class="input validate[required,custom[integer],min[0],max[999999]]" name="fee" value="${stuBatch.monthFee}" /></td>--%>
                <%--</tr>--%>
                <tr>
                    <th style="text-align: right;">批次状态：</th>
                    <td style="text-align: left;">
                        <select class="validate[required] input" name="batchStatus" style="width:160px;height: 30px">
                            <option value="" >请选择</option>
                            <option value="未开始" <c:if test="${stuBatch.batchStatus eq '未开始'}">selected="selected"</c:if> >未开始</option>
                            <option value="报名中" <c:if test="${stuBatch.batchStatus eq '报名中'}">selected="selected"</c:if> >报名中</option>
                            <option value="已结束" <c:if test="${stuBatch.batchStatus eq '已结束'}">selected="selected"</c:if> >已结束</option>
                        </select>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div align="center" style="margin-top: 20px; margin-bottom:20px;">
        <input class="btn_green" style="width:60px;" onclick="saveSetting();" value="保&#12288;存" />
        <input class="btn_green" style="width:60px;" onclick="jboxClose();" value="关&#12288;闭" />
    </div>
</body>
</html>