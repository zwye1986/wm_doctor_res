<script type="text/javascript">
    //分页
    function toPage(page) {
        if(page!=undefined){
            $('#currentPage').val(page);
        }
        search();
    }
    //查询
    function search(){
        var userName = $("input[name='userName']").val();
        jboxPostLoad("content","<s:url value='/zseyjxres/hospital/headMaintenance?'/>",{"currentPage":$('#currentPage').val(),"userName":userName},true);
    }
    //新增、编辑
    function edit(flag,userFlow){
        jboxOpen("<s:url value='/zseyjxres/hospital/editHead'/>?userFlow="+userFlow, flag,800,500,true);
    }
    //停用
    function del(userFlow){
        jboxConfirm("确定删除吗？",function(){
            jboxPost("<s:url value='/zseyjxres/hospital/delHead'/>",{"userFlow":userFlow},function(resp){
                headMaintenance();
            },null,true);
        },null);
    }
</script>
<div class="main_hd">
    <h2>科主任账号维护
        <div class="fr"></div>
    </h2>
</div>
<div class="div_search" style="float:left;margin-left:5px;">
    <input type="hidden" id="currentPage" value="1" />
    姓名：<input type="text" style="width:120px;" class="input" name="userName" value="${param.userName}"/>
   &#12288;
    <input type="button" class="btn_green" onclick="search();" value="查&#12288;询" />&#12288;
    <input type="button" class="btn_green" onclick="edit('新增','');" value="新&#12288;增" />&#12288;
</div>
<div class="search_table">
        <table class="grid" width="100%;">
            <tr>
                <th>科室</th>
                <th>姓名</th>
                <th>登录名</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${userList}" var="user">
                <tr>
                    <td>${user.deptName}</td>
                    <td>${user.userName}</td>
                    <td>${user.userCode}</td>
                    <td>
                        <a onclick="edit('编辑','${user.userFlow}')">[编辑]</a>
                        <a onclick="del('${user.userFlow}')">[删除]</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty userList}">
                <tr>
                    <td colspan="4>无记录！</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(userList)}" scope="request"></c:set>
        <pd:pagination-dwjxres toPage="toPage"/>
    </div>
