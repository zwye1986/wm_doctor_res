<script type="text/javascript">
    //分页
    function toPage(page) {
        if(page!=undefined){
            $('#currentPage').val(page);
        }
        search();
    }
    function search(){
        jboxPostLoad("content","<s:url value='/zseyjxres/hospital/headInfo'/>?isQuery=Y",$('#headInfoForm').serialize(),true);
    }

</script>
<div class="main_hd">
    <h2>科室信息查询
        <div class="fr"></div>
    </h2>
</div>
<form id="headInfoForm">
<div class="div_search">
    <input type="hidden" id="currentPage" value="1" />
    科&#12288;室：<select name="deptFlow" class="select" style="width: 106px;">
        <option value="">全部</option>
        <c:forEach items="${sessionScope.currDeptList}" var="SysDept">
            <option value="${SysDept.deptFlow}"  <c:if test="${SysDept.deptFlow eq param.deptFlow}">selected</c:if>  >${SysDept.deptName}</option>
        </c:forEach>
    </select>&#12288;
    姓&#12288;名：<input type="text" style="width: 100px;" name="userName" value="${param.userName}" class="input" />&#12288;
   用户名：<input type="text" style="width: 100px;" name="userCode" value="${param.userCode}" class="input" />&#12288;
    <input type="button"  class="btn_green" value="查&#12288;询" onclick="search()">
</div>
</form>

<div class="search_table">
        <table class="grid" width="100%;">
            <colgroup>
                <col width="15%"/>
                <col width="35%"/>
                <col width="25%"/>
                <col width="25%"/>
            </colgroup>
            <tr>
                <th>序号</th>
                <th>科室名称</th>
                <th>科主任姓名</th>
                <th>科主任用户名</th>
            </tr>
            <c:forEach items="${sysUserList}" var="user" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${user.deptName}</td>
                    <td>${user.userName}</td>
                    <td>${user.userCode}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty sysUserList}">
                <tr>
                    <td colspan="99>无记录！</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(sysUserList)}" scope="request"></c:set>
        <pd:pagination-dwjxres toPage="toPage"/>
    </div>
