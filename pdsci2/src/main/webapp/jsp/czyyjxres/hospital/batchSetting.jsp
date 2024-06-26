
<jsp:include page="/jsp/czyyjxres/htmlhead-czyyjxres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript">
    $(document).ready(function(){
        $('#batchDate').datepicker(
                { language: "zh-CN",
                    todayHighlight: true,
                    format: 'yyyy-mm',
                    autoclose: true,
                    startView: 'months',
                    maxViewMode:'months',
                    minViewMode:'months' });
    });
    //查询
    function search(){
        jboxPostLoad("content","<s:url value='/czyyjxres/hospital/searchSetting'/>",{"batchDate":$("#batchDate").val()},true);
    }
    //新增、编辑
    function edit(flag,batchFlow){
        var isAdd="";
        if(flag=="编辑")
        {
            isAdd="N"
        }
        jboxOpen("<s:url value='/czyyjxres/hospital/edit'/>?batchFlow="+batchFlow+"&isAdd="+isAdd, flag,800,500,true);
    }
    //删除
    function del(batchFlow){
        jboxConfirm("确定删除吗？",function(){
            jboxPost("<s:url value='/czyyjxres/hospital/delSetting'/>",{"batchFlow":batchFlow},function(resp){
                if(resp=="${GlobalConstant.DELETE_SUCCESSED}"){
                    search();
                }
            },null,true);
        },null);
    }
    //设为系统默认批次
    function defaultSet(batchFlow,batchStatus){
        if(batchStatus!='报名中'){
            jboxTip("只有报名中的批次才能设置为默认批次！");
            return;
        }
        jboxConfirm("此操作将覆盖已设系统默认批次，请确认?",  function() {
            jboxPost('<s:url value="/czyyjxres/hospital/defaultSet"/>', {"batchFlow": batchFlow,"batchStatus":batchStatus}, function (resp) {
                if (resp == "1") {
                    jboxTip("操作成功");
                    search();
                }
            }, null, false);
        });
    }
</script>
<div class="main_hd">
    <h2>进修批次设置
        <div class="fr"></div>
    </h2>
</div>
<div class="div_search">
    &#12288;&#12288;进修批次：
    <input id="batchDate" style="width:120px;"  name="batchDate" class="input" readonly="readonly"  value="${param.batchDate}" type="text">
    &#12288;&#12288;&#12288;
    <input type="button" class="btn_green" onclick="search();" value="查&#12288;询" />&#12288;
    <input type="button" class="btn_green" onclick="edit('新增','');" value="新&#12288;增" />&#12288;
</div>
<div class="search_table">
        <table class="grid" width="100%;">
            <tr>
                <th>进修批次</th>
                <th>报到时间</th>
                <%--<th>进修费用（元/月）</th>--%>
                <th>批次状态</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${batchLst}" var="batch">
                <tr>
                    <td>${batch.batchNo}</td>
                    <td>${batch.batchRegDate}</td>
                    <%--<td>${batch.monthFee}</td>--%>
                    <td>${batch.batchStatus}</td>
                    <td>
                        <a onclick="edit('编辑','${batch.batchFlow}')">[编辑]</a>
                        <a onclick="del('${batch.batchFlow}')">[删除]</a>
                        <c:if test="${batch.isDefault eq 'Y'}">
                            <c:choose>
                                <c:when test="${batch.batchStatus eq '报名中'}">
                                    <span style="color:gray">当前为系统默认批次</span>
                                </c:when>
                                <c:otherwise>
                                    <a onclick="defaultSet('${batch.batchFlow}','${batch.batchStatus}')">[设为系统默认批次]</a>
                                </c:otherwise>
                            </c:choose>
                        </c:if>

                        <c:if test="${batch.isDefault ne 'Y'}">
                            <a onclick="defaultSet('${batch.batchFlow}','${batch.batchStatus}')">[设为系统默认批次]</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty batchLst}">
                <tr>
                    <td colspan="4">无记录！</td>
                </tr>
            </c:if>
        </table>
    </div>
