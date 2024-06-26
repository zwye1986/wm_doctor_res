<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_ui_combobox" value="false"/>
    <jsp:param name="jquery_ui_sortable" value="false"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_scrollTo" value="false"/>
    <jsp:param name="jquery_jcallout" value="false"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_fullcalendar" value="false"/>
    <jsp:param name="jquery_fngantt" value="false"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
    <jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<style type="text/css">
    #boxHome .item:HOVER {
        background-color: #eee;
    }
</style>
<script type="text/javascript">
    function add() {
        jboxOpen("<s:url value='/res/nurse/teachingEdit'/>", "新增带教信息", 600, 300);
    }

    function edit(userFlow) {
        jboxOpen("<s:url value='/res/nurse/teachingEdit?userFlow='/>" + userFlow, "编辑带教信息", 600, 300);
    }

    function resetPasswd(userFlow) {
        jboxConfirm("确认将该带教的密码重置为:${pdfn:getInitPassword()} 吗？", function () {
            var url = "<s:url value='/res/nurse/resetPasswd?userFlow='/>" + userFlow;
            jboxGet(url, null, function () {
                //searchUser();
            });
        });
    }

    function activate(userFlow) {
        jboxConfirm("确认解锁该带教吗？", function () {
            var url = "<s:url value='/res/nurse/activate?userFlow='/>" + userFlow;
            jboxGet(url, null, function () {
                searchUser();
            });
        });
    }

    function audit(userFlow) {
        jboxOpen("<s:url value='/res/nurse/teachingAudit?userFlow='/>" + userFlow, "新增带教信息", 300, 100);
    }

    function resetAudit(userFlow) {
        jboxConfirm("确认重新发起审核？", function () {
            var url = "<s:url value='/res/nurse/resetTeachingAudit?userFlow='/>" + userFlow;
            jboxPost(url, null, function (resp) {
                jboxStartLoading();
                if (resp == "${GlobalConstant.OPRE_SUCCESSED}") {
                    searchUser();
                }
            }, null, false);
        });
    }

    function lock(userFlow) {
        jboxConfirm("确认锁定该带教吗？锁定后该带教将不能登录系统！", function () {
            var url = "<s:url value='/res/nurse/lock?userFlow='/>" + userFlow;
            jboxGet(url, null, function () {
                searchUser();
            });
        });
    }

    function searchUser() {
        var url = "<s:url value='/res/nurse/teachingList'/>";
        jboxStartLoading();
        jboxPostLoad("content", url, $("#searchForm").serialize(), false);
    }

    <%--function exportUser() {--%>
    <%--    var url = "<s:url value='/res/nurse/exportUserlist'/>";--%>
    <%--    jboxTip("导出中…………");--%>
    <%--    jboxSubmit($("#searchForm"), url, null, null, false);--%>
    <%--    jboxEndLoading();--%>
    <%--}--%>

    function toPage(page) {
        if (page != undefined) {
            $("#currentPage").val(page);
        }
        searchUser();
    }

    function importUsers() {
        var url = "<s:url value='/res/nurse/importTeachings'/>";
        jboxOpen(url, "带教导入", 700, 250);
    }

    function deleteUser(userFlow) {
        jboxConfirm("确认删除该用户吗？", function () {
            var url = "<s:url value='/res/nurse/delete?userFlow='/>" + userFlow + "&wsid=${sessionScope.currWsId}";
            jboxGet(url, null, function () {
                searchUser();
            });
        });
    }


    /**
     *模糊查询加载
     */
    (function ($) {
        $.fn.likeSearchInit = function (option) {
            option.clickActive = option.clickActive || null;

            var searchInput = this;
            searchInput.on("keyup focus", function () {
                $("#boxHome").show();
                if ($.trim(this.value)) {
                    $("#boxHome .item").hide();
                    var items = $("#boxHome .item[value*='" + this.value + "']").show();
                    if (!items) {
                        $("#boxHome").hide();
                    }
                } else {
                    $("#boxHome .item").show();
                }
            });
            searchInput.on("blur", function () {
                if (!$("#boxHome.on").length) {
                    $("#boxHome").hide();
                }
            });
            $("#boxHome").on("mouseenter mouseleave", function () {
                $(this).toggleClass("on");
            });
            $("#boxHome .item").click(function () {
                $("#boxHome").hide();
                var value = $(this).attr("value");
                $("#itemName").val(value);
                searchInput.val(value);
                if (option.clickActive) {
                    option['clickActive']($(this).attr("flow"));
                }
            });
        };
    })(jQuery);
    //======================================
    //页面加载完成时调用
    $(function () {
        $("#orgSel").likeSearchInit({
            /* clickActive:function(flow){
                $("#studyUserFlow").val(flow).change();
            } */
        });
    });

    //学会登记
    function societyLoad(userFlow) {
        var url = "<s:url value='/srm/learning/sociery/registerSociety?userFlow='/>" + userFlow;
        jboxOpen(url, "学会任职登记", 800, 600);
    }

    //医院设置超级密码可登陆（所属机构下的学员，带教，科主任）账号
    function setPwd(orgFlow) {
        var url = "<s:url value='/sys/user/setHospitalPwd?orgFlow='/>" + orgFlow;
        jboxOpen(url, "超级密码设置", 360, 160);
    }
</script>
</head>
<body>
<div class="main_hd">
    <h2 class="underline">带教信息管理</h2>
</div>
<div class="main_bd">
    <div class="div_search">
        <form id="searchForm">
            <input id="roleFlow" type="hidden" name="roleFlow" value="${sysRole.roleFlow}"/>
            <input type="hidden" name="orgFlow" value="${sessionScope.currUser.orgFlow}"/>
            <input type="hidden" name="roleFlag" id="roleFlag" value="${roleFlag}"/>
            <input id="currentPage" type="hidden" name="currentPage" value=""/>
            <table>
                <tr>
                    <td>
                        科&#12288;&#12288;室：
                        <select name="deptFlow" class="select" style="width: 134px">
                            <option value="">全部</option>
                            <c:forEach items="${depts}" var="dept">
                                <option value="${dept.deptFlow}"
                                        <c:if test="${param.deptFlow eq dept.deptFlow}">selected</c:if>>${dept.deptName}</option>
                            </c:forEach>
                        </select>
                        姓&#12288;&#12288;名：
                        <input type="text" name="userName" value="${param.userName}" class="input"
                               style="width: 128px;"/>
                        联系方式：
                        <input type="text" name="userPhone" value="${param.userPhone}" class="input"
                               style="width: 128px;"/>
                        审核状态：
                        <select name="auditStatus" class="select" style="width: 134px">
                            <option value="">全部</option>
                            <option value="待审核" <c:if test="${search.auditStatus eq '待审核'}">selected</c:if>>待审核</option>
                            >待审核</option>
                            <option value="审核通过" <c:if test="${search.auditStatus eq '审核通过'}">selected</c:if>>审核通过
                            </option>
                            <option value="审核不通过" <c:if test="${search.auditStatus eq '审核不通过'}">selected</c:if>>审核不通过
                            </option>
                        </select>
                        <%--                        状&#12288;&#12288;态：--%>
                        <%--                        <select name="statusId" class="select" style="width: 134px">--%>
                        <%--                            <option value="">全部</option>--%>
                        <%--                            <c:forEach items="${nurseStatusEnums}" var="nurseStatusEnum">--%>
                        <%--                                <option value="${nurseStatusEnum.id}"--%>
                        <%--                                        <c:if test="${param.statusId eq nurseStatusEnum.id}">selected</c:if>>${nurseStatusEnum.name}</option>--%>
                        <%--                            </c:forEach>--%>
                        <%--                        </select>--%>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input class="btn_green" type="button" onclick="searchUser()" value="查&#12288;询"/>&#12288;
                        <c:if test="${empty roleFlag}">
                            <input class="btn_green" type="button" onclick="add()" value="新&#12288;增"/>&#12288;
                            <%--                        <input type="button" class="btn_green" onclick="exportUser();" value="导&#12288;出">--%>
                            <input type="button" class="btn_green" onclick="importUsers();" value="导&#12288;入">
                        </c:if>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th width="50px">序号</th>
                <th width="50px">登录名</th>
                <th width="50px">科室</th>
                <th width="50px">姓名</th>
                <th width="50px">联系方式</th>
                <th width="50px">审核状态</th>
                <%--            <th width="50px">状态</th>--%>
                <th width="50px">操作</th>
            </tr>
            <c:set var="userNum" value="0"></c:set>
            <c:forEach items="${sysUserList}" var="sysUser" varStatus="status">
                <c:set value="false" var="isDoctor"></c:set>
                <c:if test="${sysUser.userCode!=GlobalConstant.ROOT_USER_CODE or sessionScope.currUser.userCode==GlobalConstant.ROOT_USER_CODE }">
                    <c:set var="userNum" value="${userNum+1 }"></c:set>

                    <tr style="height:20px ">
                        <td>${status.index+1}</td>
                        <td>${sysUser.userCode}</td>
                        <td>${sysUser.deptName}</td>
                        <td>${sysUser.userName}</td>
                        <td>${sysUser.userPhone}</td>
                        <td><c:if test="${empty sysUser.auditStatus}">无</c:if>${sysUser.auditStatus}</td>
                            <%--                    <td>${sysUser.statusDesc}</td>--%>
                        <td style="text-align: left;padding-left: 5px;">

                                <%--                        <c:if test="${sysUser.statusId==userStatusEnumLocked.id}">--%>
                                <%--                            [<a href="javascript:activate('${sysUser.userFlow}');" >解锁</a>]--%>
                                <%--                        </c:if>--%>
                            <c:if test="${not empty roleFlag and 'global' eq roleFlag}">
                                <c:if test="${not empty sysUser.auditStatus and '待审核' eq sysUser.auditStatus}">
                                    [<a href="javascript:audit('${sysUser.userFlow}');" >审核</a>]
                                </c:if>
                                <c:if test="${not empty sysUser.auditStatus and '审核不通过' eq sysUser.auditStatus}">
                                    [<a href="javascript:audit('${sysUser.userFlow}');" >重新审核</a>]
                                </c:if>
                            </c:if>
                            <c:if test="${empty roleFlag}">
                                <c:if test="${sysUser.statusId==userStatusEnumActivated.id}">
                                    [<a href="javascript:edit('${sysUser.userFlow}');" >编辑</a>]
                                    <%--                            |--%>
                                    <%--                            [<a href="javascript:lock('${sysUser.userFlow}');" >锁定</a>] |--%>
                                    <%--                            [<a href="javascript:resetPasswd('${sysUser.userFlow}');" >重置密码</a>]--%>
                                </c:if>
                                <c:if test="${not empty sysUser.auditStatus and '审核不通过' eq sysUser.auditStatus}">
                                    [<a href="javascript:resetAudit('${sysUser.userFlow}');" >重新发起审核</a>]
                                </c:if>
                            </c:if>
                                <%-- <c:if test="${GlobalConstant.FLAG_Y eq applicationScope.sysCfgMap['sys_user_delete_flag']}">
                                     | [<a href="javascript:deleteUser('${sysUser.userFlow}');" >删除</a>]
                                 </c:if>--%>

                        </td>
                    </tr>
                </c:if>
            </c:forEach>
            <c:if test="${userNum == 0 }">
                <c:set var="colNum" value="10"></c:set>
                <c:if test="${sessionScope.currWsId=='srm' and applicationScope.sysCfgMap['srm_for_use']=='local'}">
                    <c:set var="colNum" value="10"></c:set>
                </c:if>
                <tr>
                    <td align="center" colspan="${colNum }">无记录</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(sysUserList)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>

</body>
</html>