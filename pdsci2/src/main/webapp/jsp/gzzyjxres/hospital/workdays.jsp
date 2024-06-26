<script type="text/javascript">
    //分页
    function toPage(page) {
        if(page!=undefined){
            $('#currentPage').val(page);
        }
        jboxPostLoad("content","<s:url value='/gzzyjxres/hospital/workdays'/>",{"currentPage":$('#currentPage').val()},true);
    }

    //新增、编辑
    function editWorkday(flag,recordFlow){

        jboxOpen("<s:url value='/gzzyjxres/hospital/editWorkday'/>?recordFlow="+recordFlow, flag,800,500,true);
    }
    //删除
    function delWorkday(recordFlow,weekendDate){
        jboxConfirm("确定刪除吗？",function(){
            jboxPost("<s:url value='/gzzyjxres/hospital/delWorkday'/>",{"recordFlow":recordFlow},function(resp){
                workdaysMaintenance();
            },null,true);
        },null);
    }
</script>
<div class="main_hd">
    <h2>周末为工作日登记
        <div class="fr"></div>
    </h2>
</div>
<div class="div_search" style="float:left;margin-left:5px;">
    <input type="hidden" id="currentPage" value="1" />
    <input type="button" class="btn_green" onclick="editWorkday('新增');" value="新&#12288;增" />&#12288;
</div>
<div class="search_table">
        <table class="grid" width="100%;">
            <tr>
                <th>序号</th>
                <th>时间</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${weekendsRegisterList}" var="workday" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${workday.weekendDate}</td>
                    <td>
                        <a onclick="editWorkday('编辑','${workday.recordFlow}')">[编辑]</a>
                        <a onclick="delWorkday('${workday.recordFlow}')">[删除]</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty weekendsRegisterList}">
                <tr>
                    <td colspan="99">无记录！</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(weekendsRegisterList)}" scope="request"></c:set>
        <pd:pagination-dwjxres toPage="toPage"/>
    </div>
