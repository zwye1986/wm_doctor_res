<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="/jsp/czyyjxres/htmlhead-czyyjxres.jsp">
    <jsp:param name="basic" value="true"/>
</jsp:include>
<script type="text/javascript">
    //分页
    function toPage(page) {
        if (page != undefined) {
            $('#currentPage').val(page);
        }
        jboxPostLoad("content", "<s:url value='/czyyjxres/hospital/staffManage'/>", $('#searchForm').serialize(), true);
    }
    $(document).ready(function () {
        $("li").click(function () {
            $(".tab_select").addClass("tab");
            $(".tab_select").removeClass("tab_select");
            $(this).removeClass("tab");
            $(this).addClass("tab_select");
            $('#isOrgAdmin').val($(this).attr("isOrgAdmin"));
            $("#currentPage").val(1);
            toPage(1);
        });
    });
    //新增、编辑
    function edit(flag, userFlow) {
        var role = $("li[class='tab_select']").find("a").text();
        flag = flag + role;
        var roleId = $("#isOrgAdmin").val();
        jboxOpen("<s:url value='/czyyjxres/hospital/editStaff'/>?userFlow=" + userFlow + "&roleId=" + roleId, flag, 600, 350, true);
    }
    //导入
    function showImport() {
        var url = "<s:url value='/czyyjxres/hospital/showImport'/>";
        typeName = "导入医院人员";
        jboxOpen(url, typeName, 550, 260);
    }
    //删除
    function del(userFlow) {
        jboxConfirm("确定刪除吗？", function () {
            jboxPost("<s:url value='/czyyjxres/hospital/delStaff'/>", {"userFlow": userFlow}, function (resp) {
                staffManage();
            }, null, true);
        }, null);
    }
    <%--function operRole(resp, userFlow) {--%>
        <%--var role = '';--%>
        <%--if ($(resp).parent("td").find("input[type=checkbox]:checked").size() == 2)--%>
            <%--role = '1,2';--%>
        <%--else--%>
            <%--role = $(resp).parent("td").find("input[type=checkbox]:checked").val();--%>
        <%--jboxPost("<s:url value='/czyyjxres/hospital/operRole'/>", {"role": role, "userFlow": userFlow}, null, null, true);--%>
    <%--}--%>
</script>
<div class="main_hd">
    <h2>本院人员维护</h2>
    <div class="title_tab" id="toptab">
        <ul>
            <li class="${isOrgAdmin==stuRoleEnumTeacher.id?'tab_select':'tab' }" isOrgAdmin="${stuRoleEnumTeacher.id }">
                <a>带教老师</a></li>
            <li class="${isOrgAdmin==stuRoleEnumSecretary.id?'tab_select':'tab' }"
                isOrgAdmin="${stuRoleEnumSecretary.id }"><a>教学秘书</a></li>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table_0">
    <div class="div_table" style="">
        <form id="searchForm">
            <%--人员角色--%>
            <input type="hidden" id="isOrgAdmin" name="isOrgAdmin"
                   value="${param.isOrgAdmin == null ? stuRoleEnumTeacher.id: param.isOrgAdmin}"/>
            <%--本院人员--%>
            <input type="hidden" id="isOwnerStu" name="isOwnerStu" value="Y"/>
            <input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
            <table>
                <tr>
                    <td>&#12288;姓&#12288;&#12288;名：</td>
                    <td><input type="text" style="width: 100px;" name="userName" value="${param.userName}"
                               class="input"/></td>
                    <td>
                    <td>&#12288;科&#12288;&#12288;室：</td>
                    <td>
                        <select name="deptFlow" class="select" style="width:107px;">
                            <option value="">--请选择--</option>
                            <c:forEach items="${dictTypeEnumDwjxSpeList}" var="dept">
                                <option value="${dept.dictId}"
                                        <c:if test="${dept.dictId == param.deptFlow}">selected</c:if> >${dept.dictName}</option>
                            </c:forEach>
                        </select>
                    <td>
                    <td>&#12288;工&#12288;&#12288;号：</td>
                    <td><input type="text" style="width: 100px;" name="userCode" value="${param.userCode}"
                               class="input"/></td>
                    <td>
                    <td>&#12288;
                        <input type="button" class="btn_green" onclick="toPage(1);" value="查&#12288;询"/>&#12288;
                        <input type="button" class="btn_green" onclick="edit('新增');" value="新&#12288;增"/>&#12288;
                        <input type="button" class="btn_green" onclick="showImport();" value="导&#12288;入"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="div_table">
        <table class="grid" width="100%;">
            <tr>
                <th>科室</th>
                <th>姓名</th>
                <th>工号</th>
                <th>联系电话</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${userList}" var="user">
                <tr>
                    <td>
                        <c:forEach items="${deptForUser[user.userFlow]}" var="dept">
                            ${dept.dictName}<br/>
                        </c:forEach>
                    </td>
                    <td>${user.userName}</td>
                    <td>${user.userCode}</td>
                    <td>${user.userPhone}</td>
                    <td>
                        <a onclick="edit('编辑','${user.userFlow}')">[编辑]</a>
                        <a onclick="del('${user.userFlow}')">[删除]</a>
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
</div>