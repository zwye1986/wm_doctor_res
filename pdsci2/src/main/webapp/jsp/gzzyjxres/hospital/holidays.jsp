<script type="text/javascript">
    //分页
    function toPage(page) {
        if(page!=undefined){
            $('#currentPage').val(page);
        }
        jboxPostLoad("content","<s:url value='/gzzyjxres/hospital/nationalHolidays'/>",{"currentPage":$('#currentPage').val()},true);
    }

    //新增、编辑
    function editHoliday(flag,recordFlow){

        jboxOpen("<s:url value='/gzzyjxres/hospital/editHoliday'/>?recordFlow="+recordFlow, flag,800,500,true);
    }
    //删除
    function delHoliday(recordFlow){
        jboxConfirm("确定刪除吗？",function(){
            jboxPost("<s:url value='/gzzyjxres/hospital/delHoliday'/>",{"recordFlow":recordFlow},function(resp){
                nationalHolidaysMaintenance();
            },null,true);
        },null);
    }
</script>
<div class="main_hd">
    <h2>法定节假日登记
        <div class="fr"></div>
    </h2>
</div>
<div class="div_search" style="float:left;margin-left:5px;">
    <input type="hidden" id="currentPage" value="1" />
    <input type="button" class="btn_green" onclick="editHoliday('新增');" value="新&#12288;增" />&#12288;
</div>
<div class="search_table">
        <table class="grid" width="100%;">
            <tr>
                <th>序号</th>
                <th>名称</th>
                <%--<th>时间</th>--%>
                <th>开始时间</th>
                <th>结束时间</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${holidaysRegisterList}" var="holiday" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${holiday.nationalHolidayName}</td>
                    <%--<td>${holiday.nationalHolidayDate}</td>--%>
                    <td>${holiday.beginDate}</td>
                    <td>${holiday.endDate}</td>
                    <td>
                        <a onclick="editHoliday('编辑','${holiday.recordFlow}')">[编辑]</a>
                        <a onclick="delHoliday('${holiday.recordFlow}')">[删除]</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty holidaysRegisterList}">
                <tr>
                    <td colspan="99">无记录！</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(holidaysRegisterList)}" scope="request"></c:set>
        <pd:pagination-dwjxres toPage="toPage"/>
    </div>
