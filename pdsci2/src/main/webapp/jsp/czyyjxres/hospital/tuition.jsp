<script type="text/javascript">
    //分页
    function toPage(page) {
        if(page!=undefined){
            $('#currentPage').val(page);
        }
        jboxPostLoad("content","<s:url value='/czyyjxres/hospital/tuition'/>",{"currentPage":$('#currentPage').val()},true);
    }

    //新增、编辑
    function edit(flag,recordFlow){

        jboxOpen("<s:url value='/czyyjxres/hospital/editTuition'/>?recordFlow="+recordFlow, flag,800,500,true);
    }
    //删除
    function delTuition(recordFlow){
        jboxConfirm("确定刪除吗？",function(){
            jboxPost("<s:url value='/czyyjxres/hospital/delTuition'/>",{"recordFlow":recordFlow},function(resp){
                tuitionMaintenance();
            },null,true);
        },null);
    }
</script>
<div class="main_hd">
    <h2>学费管理
        <div class="fr"></div>
    </h2>
</div>
<div class="div_search" style="float:left;margin-left:5px;">
    <input type="hidden" id="currentPage" value="1" />
    <input type="button" class="btn_green" onclick="edit('新增');" value="新&#12288;增" />&#12288;
</div>
<div class="search_table">
        <table class="grid" width="100%;">
            <tr>
                <th>序号</th>
                <th>费用项</th>
                <th>费用</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${managementList}" var="tuition" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${tuition.costCategory}</td>
                    <td>${tuition.costValue}元</td>
                    <td>
                        <a onclick="edit('编辑','${tuition.recordFlow}')">[编辑]</a>
                        <a onclick="delTuition('${tuition.recordFlow}')">[删除]</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty managementList}">
                <tr>
                    <td colspan="99">无记录！</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(managementList)}" scope="request"></c:set>
        <pd:pagination-dwjxres toPage="toPage"/>
    </div>
