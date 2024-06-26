<script type="text/javascript">
    //分页
    function toPage(page) {
        if(page!=undefined){
            $('#currentPage').val(page);
        }
        jboxPostLoad("content","<s:url value='/dwjxres/hospital/registManage'/>",$("#searchForm").serialize(),false);
    }
    //还原密码
    function resetPasswd(userFlow){
        jboxConfirm("确认将该用户的密码重置为:${pdfn:getInitPass()} 吗？",function () {
            var url = "<s:url value='/sys/user/resetPasswd?userFlow='/>"+userFlow;
            jboxGet(url,null,function(){
                jboxTip("操作成功！");
            });
        });
    }
</script>
<div class="main_hd">
    <h2 class="underline">外院人员管理
        <div class="fr"></div>
    </h2>
</div>
<div class="div_search" style="float:left;margin-left:40px;">
    <form id="searchForm">
        <input type="hidden" id="currentPage" name="currentPage" value="1" />
        <table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">
            <tr>
                <td class="td_left">姓名：</td>
                <td>
                    <input type="text" id="userName" name="userName" class="input" style="width: 160px;margin-left: 0px"  value="${param.userName}" />
                </td>
                <td class="td_left">&#12288;身份证号：</td>
                <td>
                    <input type="text" id="idNo" name="idNo" class="input" style="width: 160px;margin-left: 0px"  value="${param.idNo}" />
                </td>
                <td>&#12288;
                    <input type="button" class="btn_green" onclick="toPage(1);" value="查&#12288;询" />
                </td>
            </tr>
        </table>
    </form>
</div>
<div class="search_table">
        <table class="grid" width="100%;">
            <tr>
                <%--<th>注册账号</th>--%>
                <th>姓名</th>
                <th>身份证号</th>
                <th>联系电话</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${userList}" var="user">
                <tr>
                    <%--<td>${user.userCode}</td>--%>
                    <td>${user.userName}</td>
                    <td>${user.idNo}</td>
                    <td>${user.userPhone}</td>
                    <td>
                        <%--<input type="button" class="btn_green" onclick="edit('${user.userFlow}')" value="还原密码" />--%>
                        <a onclick="resetPasswd('${user.userFlow}')">[还原密码]</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty userList}">
                <tr>
                    <td colspan="6">无记录！</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(userList)}" scope="request"></c:set>
        <pd:pagination-dwjxres toPage="toPage"/>
    </div>
