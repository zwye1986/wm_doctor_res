<script type="text/javascript">
    //分页
    function toPage(page) {
        if(page!=undefined){
            $('#currentPage').val(page);
        }
        jboxPostLoad("content","<s:url value='/czyyjxres/hospital/speMaintenance'/>",{"currentPage":$('#currentPage').val()},true);
    }
    //刷新
    function refresh(){
        jboxPost('<s:url value="/czyyjxres/hospital/refresh"/>', null, function (resp) {
            if (resp == "1") {
                jboxTip("刷新成功");
                speMaintenance();
            }
        }, null, false);
    }
    //新增、编辑
    function edit(flag,dictFlow){
        jboxOpen("<s:url value='/czyyjxres/hospital/editNational'/>?dictFlow="+dictFlow, flag,800,500,true);
    }
    //删除
    function del(dictFlow){
        jboxConfirm("确定刪除吗？",function(){
            jboxPost("<s:url value='/czyyjxres/hospital/delSpe'/>",{"dictFlow":dictFlow},function(resp){
                speMaintenance();
            },null,true);
        },null);
    }
</script>
<div class="main_hd">
    <h2>国籍维护
        <div class="fr"></div>
    </h2>
</div>
<div class="div_search" style="float:left;margin-left:5px;">
    <input type="hidden" id="currentPage" value="1" />
    <%--<span style="color:red;float:left;line-height:40px;">注：科室维护后请刷新即时生效！</span>&#12288;--%>
    <input type="button" class="btn_green" onclick="edit('新增');" value="新&#12288;增" />&#12288;
    <%--<input type="button" class="btn_green" onclick="refresh('');" value="刷 新" />--%>
</div>
<div class="search_table">
        <table class="grid" width="100%;">
            <tr>
                <th>国籍编码</th>
                <th>国籍名称</th>
                <th>国籍英文名称</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${nationalList}" var="national">
                <tr>
                    <td>${national.dictId}</td>
                    <td>${national.dictName}</td>
                    <td>${national.dictDesc}</td>
                    <td>
                        <a onclick="edit('编辑','${national.dictFlow}')">[编辑]</a>
                        <a onclick="del('${national.dictFlow}')">[删除]</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty nationalList}">
                <tr>
                    <td colspan="99">无记录！</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(nationalList)}" scope="request"></c:set>
        <pd:pagination-dwjxres toPage="toPage"/>
    </div>
