<script type="text/javascript">
    //分页
    function toPage(page) {
        if(page!=undefined){
            $('#currentPage').val(page);
        }
        jboxPostLoad("content","<s:url value='/zseyjxres/hospital/speMaintenance'/>",{"currentPage":$('#currentPage').val()},true);
    }
    //刷新
    function refresh(){
        jboxPost('<s:url value="/zseyjxres/hospital/refresh"/>', null, function (resp) {
            if (resp == "1") {
                jboxTip("刷新成功");
                speMaintenance();
            }
        }, null, false);
    }
    //新增、编辑
    function edit(flag,dictFlow){
        jboxOpen("<s:url value='/zseyjxres/hospital/editSpe'/>?dictFlow="+dictFlow, flag,800,500,true);
    }
    //删除
    function del(dictFlow){
        jboxConfirm("确定刪除吗？",function(){
            jboxPost("<s:url value='/zseyjxres/hospital/delSpe'/>",{"dictFlow":dictFlow},function(resp){
                speMaintenance();
            },null,true);
        },null);
    }
</script>
<div class="main_hd">
    <h2>科室维护
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
                <th>科室编码</th>
                <th>科室名称</th>
                <th>科室英文名称</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${dwjxSpeLst}" var="spe">
                <tr>
                    <td>${spe.dictId}</td>
                    <td>${spe.dictName}</td>
                    <td>${spe.dictDesc}</td>
                    <td>
                        <a onclick="edit('编辑','${spe.dictFlow}')">[编辑]</a>
                        <a onclick="del('${spe.dictFlow}')">[删除]</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty dwjxSpeLst}">
                <tr>
                    <td colspan="99>无记录！</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(dwjxSpeLst)}" scope="request"></c:set>
        <pd:pagination-dwjxres toPage="toPage"/>
    </div>
