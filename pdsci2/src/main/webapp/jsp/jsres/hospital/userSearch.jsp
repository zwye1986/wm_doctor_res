<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="bootstrapSelect" value="true"/>
</jsp:include>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<script type="text/javascript" src="<s:url value='/js/itemSelect/itemSelect.js'/>"></script>
<style type="text/css">
    .boxHome .item:HOVER {
        background-color: #eee;
    }

    /*.input{*/
    /*    margin: 0 12px 0 4px;*/
    /*    width: 161px;*/
    /*}*/

    .btn-group.bootstrap-select {
        margin: 0 12px 0 4px !important;
        width: 182px !important;
        height: 30px;
    }

    .text{
        margin-left: 0;
        width: auto;
        height: auto;
        line-height: inherit;
        color: black;
    }
    .selected a{
        padding: 0;
        background: #459ae9;
    }
    .btn{
        height: 30px !important;
        border: 1px solid #e7e7eb
    }
    .body{
        width: 90%;
        margin-left: auto;
        margin-right: auto;
        padding: 0 0 88px;
    }
    .container{
        padding-left: 0;
        padding-right: 0;
    }
    .btn-default{
        background-color: #fff;
    }
    .div_search span{
        font-weight: inherit;
        float: none;
    }
    .div_search{
        line-height: 35px;
    }
    .clearfix {
        clear: both;
        height: 0;
    }
</style>
<script type="text/javascript">
    (function ($) {
        $.fn.likeSearchInit = function (option) {
            option.clickActive = option.clickActive || null;

            var searchInput = this;
            var spaceId = this[0].id;
            searchInput.on("keyup focus", function () {
                var boxHome = $("#" + spaceId + "Sel");
                boxHome.show();
                if ($.trim(this.value)) {
                    $("p." + spaceId + ".item", boxHome).hide();
                    var items = boxHome.find("p." + spaceId + ".item[value*='" + this.value + "']").show();
                    if (!items) {
                        boxHome.hide();
                    }
                } else {
                    $("p." + spaceId + ".item", boxHome).show();
                }
            });
            searchInput.on("blur", function () {
                var boxHome = $("#" + spaceId + "Sel");
                if (!$("." + spaceId + ".boxHome.on").length) {
                    boxHome.hide();
                }
            });
            $("." + spaceId + ".boxHome").on("mouseenter mouseleave", function () {
                $(this).toggleClass("on");
            });

            $("." + spaceId + ".boxHome .item").click(function () {
                var boxHome = $("." + spaceId + ".boxHome");
                boxHome.hide();
                var value = $(this).attr("value");
                var flow = $(this).attr("flow");
                var input = $("#" + spaceId);
                var inputFlow = $("#" + spaceId+"Flow");
                input.val(value);
                inputFlow.val(flow);
                if (option.clickActive) {
                    option.clickActive($(this).attr("flow"));
                }
// 			var content = $("#clone").html().replace("title",value);
// 			$("#"+input.attr("id")+"Div").append(content);
            });
        };
    })(jQuery);

    $(document).ready(function () {
        $("#statusId,#userRoleList").selectpicker({});
        search();
        $("#ksmc").likeSearchInit({
            clickActive: function (flow) {
                $("." + flow).show();
            }
        });
    });

    function toPage(page) {
        $("#currentPage").val(page);
        search();
    }

    function search() {
        if($("input[name='deptName']").val()==''){
            $("input[name='deptFlow']").val('');
        }
        var url = "<s:url value='/jsres/manage/userList'/>?orgFlow=${sessionScope.currUser.orgFlow}";
        jboxPostLoad("searchContent", url, $("#searchForm").serialize(), true);
    }

    function selRole(userFlow, roleFlow,obj) {
        var checked = $(obj).attr("checked");
        if("${applicationScope.sysCfgMap['res_teacher_role_flow'] }"==roleFlow)
        {
            if(!checked){
                checked = 'N';
            }else {
                checked = 'Y';
            }
        }else {
            checked = 'Y';
        }
        var url = "<s:url value='/jsres/manage/selRole'/>?userFlow=" + userFlow + "&roleFlow=" + roleFlow + "&wsId=${GlobalConstant.RES_WS_ID}"+ "&checked=" + checked ;
        jboxGet(url, null, function (resp) {
            /* if("
            ${GlobalConstant.SAVE_SUCCESSED}" == resp){
             alert(resp);
             } */
        }, null, true);
    }

    function editUser(userFlow) {
        var title = "新增";
        if ("" != userFlow) {
            title = "编辑";
        }
        var url = "<s:url value='/jsres/manage/editUser?userFlow='/>" + userFlow;
        jboxOpen(url, title + "用户信息", 900, 500);
    }


    function resetPasswd(userFlow) {
        jboxConfirm("确认将该用户的密码重置为:${pdfn:getInitPass()} 吗？", function () {
            var url = "<s:url value='/sys/user/resetPasswd?userFlow='/>" + userFlow;
            jboxGet(url, null, function () {
                //searchUser();
            });
        });
    }

    function lock(userFlow) {
        var url = "<s:url value='/jsres/manage/reasonForLockUser?userFlow='/>" + userFlow;
        jboxOpen(url, "停用用户", 500, 200);
        <%--jboxConfirm("确认锁定该用户吗？锁定后该用户将不能登录系统！", function () {--%>
        <%--    var url = "<s:url value='/sys/user/lock?userFlow='/>" + userFlow;--%>
        <%--    jboxGet(url, null, function () {--%>
        <%--        search();--%>
        <%--    });--%>
        <%--});--%>
    }

    function exportUser() {
        var url = "<s:url value='/jsres/manage/exportUser?orgFlow=${sessionScope.currUser.orgFlow}'/>";
        jboxExp($("#searchForm"), url);
    }

    function importUsers() {
        var url = "<s:url value='/jsres/manage/importUsers'/>";
        jboxOpen(url, "师资导入", 700, 250);
    }

    function activate(userFlow) {
        jboxConfirm("确认解锁该用户吗？", function () {
            var url = "<s:url value='/sys/user/activate?userFlow='/>" + userFlow;
            jboxGet(url, null, function () {
                search();
            });
        });
    }

    // 设置角色
    function setRoleInfo(userFlow, userName){
        var url = "<s:url value='/jsres/manage/authRole'/>?userFlow=" + userFlow;
        jboxOpen(url, "分配角色-" + userName, 400, 250, false);
    }
</script>

<!-- <div class="main_hd">
<h2 class="underline">师资维护</h2>
</div> -->
<div class="main_bd" id="div_table_0">
    <div class="div_search">
        <form id="searchForm" action="<s:url value="/jsres/manage/teacherList" />" method="post">
            <input type="hidden" name="currentPage" id="currentPage" value="${param.currentPage}">

            <div style="display: flex;justify-content: flex-start; column-gap: 56px;margin-top: 15px">
                <div>
                    <label class="from_label">科室名称：</label>
                    <div style="display: inline-block">
                        <input type="text" id="ksmc" name="deptName" value="${param.deptName}" class=" input" autocomplete="off"/>
                        <input id="ksmcFlow" name="deptFlow" value="${param.deptFlow}" hidden="hidden"/>
                        <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; left:0px; top:30px;">
                            <div class="boxHome ksmc" id="ksmcSel"
                                 style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
                                <c:forEach items="${sysDeptList}" var="dept">
                                    <p class="item ksmc" flow="${dept.deptFlow}" value="${dept.deptName}"
                                       style="line-height: 25px; padding:0px 0;cursor: default;width: 100% ">${dept.deptName}</p>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
                <div>
                    <label class="from_label">姓 名：</label>
                    <input type="text" name="userName" value="${param.userName}" class="input" />
                </div>
                <div>
                    <label class="from_label">用户名：</label>
                    <input type="text" name="userCode" value="${param.userCode}" class="input"  />
                </div>
                <div>
                    <label class="from_label">手机号：</label>
                    <input type="text" name="userPhone" value="${param.userPhone}"  class="input"  />
                </div>
                <div>
                    <label class="from_label">证书编号：</label>
                    <input type="text" name="certificateId" value="${param.certificateId}"  class="input" />
                </div>

            </div>
            <div style="margin-top: 15px;margin-bottom: 15px">
                <input type="button" class="btn_green" onclick="toPage(1)" value="查&#12288;询">
                <input type="button" class="btn_green" onclick="editUser('')" value="新&#12288;增">
                <input type="button" class="btn_green" onclick="importUsers()" value="导&#12288;入">
                <input type="button" class="btn_green" onclick="exportUser()" value="导&#12288;出">
            </div>


<%--            <div style="float: left; margin-bottom: 18px">--%>
<%--                <label class="td_left">科室名称</label>--%>
<%--                <div style="display: inline-block">--%>
<%--                    <input type="text" id="ksmc" name="deptName" value="${param.deptName}" class=" input" autocomplete="off"/>--%>
<%--                    <input id="ksmcFlow" name="deptFlow" value="${param.deptFlow}" hidden="hidden"/>--%>
<%--                    <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; left:0px; top:30px;">--%>
<%--                        <div class="boxHome ksmc" id="ksmcSel"--%>
<%--                             style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">--%>
<%--                            <c:forEach items="${sysDeptList}" var="dept">--%>
<%--                                <p class="item ksmc" flow="${dept.deptFlow}" value="${dept.deptName}"--%>
<%--                                   style="line-height: 25px; padding:0px 0;cursor: default;width: 100% ">${dept.deptName}</p>--%>
<%--                            </c:forEach>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div style="float: left; margin-bottom: 18px">--%>
<%--                <label class="td_left">姓名</label>--%>
<%--                <input type="text" name="userName" value="${param.userName}" class="input" />--%>
<%--            </div>--%>
<%--            <div style="float: left; margin-bottom: 18px">--%>
<%--                <label class="td_left">用户名</label>--%>
<%--                <input type="text" name="userCode" value="${param.userCode}" class="input"  />--%>
<%--            </div>--%>
<%--            <div style="float: left; margin-bottom: 18px">--%>
<%--                <label class="td_left">手机号</label>--%>
<%--                <input type="text" name="userPhone" value="${param.userPhone}"  class="input"  />--%>
<%--            </div>--%>
<%--            <div style="float: left; margin-bottom: 18px">--%>
<%--                <label class="td_left">证书编号</label>--%>
<%--                <input type="text" name="certificateId" value="${param.certificateId}"  class="input" />--%>
<%--            </div>--%>
<%--            <div style="float: left; margin-bottom: 18px">--%>
<%--                <label class="td_left">用户状态</label>--%>
<%--                <select name="statusId" class="selectpicker" id="statusId">--%>
<%--                    <option value="" >全部</option>--%>
<%--                    <option value="${userStatusEnumActivated.id }" <c:if test='${param.statusId==userStatusEnumActivated.id or empty param.statusId}'>selected</c:if>>${userStatusEnumActivated.name}</option>--%>
<%--                    <option value="${userStatusEnumLocked.id }" <c:if test='${param.statusId==userStatusEnumLocked.id}'>selected</c:if>>${userStatusEnumLocked.name}</option>--%>
<%--                </select>--%>
<%--            </div>--%>
<%--            <div style="float: left; margin-bottom: 18px">--%>
<%--                <label class="td_left">角色</label>--%>
<%--                <select multiple class="selectpicker" name="userRoleList" id="userRoleList" title="请选择角色">--%>
<%--                    <c:if test="${!empty applicationScope.sysCfgMap['res_teacher_role_flow']}">--%>
<%--                        <option selected value="${applicationScope.sysCfgMap['res_teacher_role_flow'] }">${!empty sysRoleMap[sysCfgMap['res_teacher_role_flow']]?sysRoleMap[sysCfgMap['res_teacher_role_flow']].roleName:'带教老师'}</option>--%>
<%--                    </c:if>--%>
<%--                    <c:if test="${!empty applicationScope.sysCfgMap['res_head_role_flow']}">--%>
<%--                        <option selected value="${applicationScope.sysCfgMap['res_head_role_flow'] }">${!empty sysRoleMap[sysCfgMap['res_head_role_flow']]?sysRoleMap[sysCfgMap['res_head_role_flow']].roleName:'科主任'}</option>--%>
<%--                    </c:if>--%>
<%--                    <c:if test="${!empty applicationScope.sysCfgMap['res_secretary_role_flow']}">--%>
<%--                        <option selected value="${applicationScope.sysCfgMap['res_secretary_role_flow'] }">${!empty sysRoleMap[sysCfgMap['res_secretary_role_flow']]?sysRoleMap[sysCfgMap['res_secretary_role_flow']].roleName:'科秘'}</option>--%>
<%--                    </c:if>--%>
<%--                    <c:if test="${!empty applicationScope.sysCfgMap['res_teaching_head_role_flow']}">--%>
<%--                        <option selected value="${applicationScope.sysCfgMap['res_teaching_head_role_flow'] }">${!empty sysRoleMap[sysCfgMap['res_teaching_head_role_flow']]?sysRoleMap[sysCfgMap['res_teaching_head_role_flow']].roleName:'教学主任'}</option>--%>
<%--                    </c:if>--%>
<%--                    <c:if test="${!empty applicationScope.sysCfgMap['res_teaching_secretary_role_flow']}">--%>
<%--                        <option selected value="${applicationScope.sysCfgMap['res_teaching_secretary_role_flow'] }">${!empty sysRoleMap[sysCfgMap['res_teaching_secretary_role_flow']]?sysRoleMap[sysCfgMap['res_teaching_secretary_role_flow']].roleName:'教学秘书'}</option>--%>
<%--                    </c:if>--%>
<%--                    <c:if test="${!empty applicationScope.sysCfgMap['res_hospitalLeader_role_flow']}">--%>
<%--                        <option selected value="${applicationScope.sysCfgMap['res_hospitalLeader_role_flow'] }">${!empty sysRoleMap[sysCfgMap['res_hospitalLeader_role_flow']]?sysRoleMap[sysCfgMap['res_hospitalLeader_role_flow']].roleName:'评分专家'}</option>--%>
<%--                    </c:if>--%>
<%--                </select>--%>
<%--            </div>--%>
<%--            <div style="float: left; margin-bottom: 18px">--%>
<%--                <c:if test="${ applicationScope.sysCfgMap['sys_user_dept_flag']==GlobalConstant.FLAG_Y }">--%>
<%--                    <input name="moreDept" type="checkbox" value="${GlobalConstant.FLAG_Y}" class="" <c:if test='${param.moreDept eq GlobalConstant.FLAG_Y}'>checked="checked"</c:if>>--%>
<%--                    <label style="display: inline-block;margin-left: 8px;margin-right: 12px">多科室</label>--%>
<%--                </c:if>--%>
<%--            </div>--%>
<%--            <div style="float: left;margin-bottom: 18px">--%>
<%--                <input type="button" class="btn_green" onclick="toPage(1)" value="查询">--%>
<%--                <input type="button" class="btn_green" onclick="editUser('')" value="新增">--%>
<%--                <input type="button" class="btn_green" onclick="importUsers()" value="导入">--%>
<%--                <input type="button" class="btn_green" onclick="exportUser()" value="导出">--%>
<%--            </div>--%>
<%--            <div class="clearfix"></div>--%>
        </form>
    </div>

    <div id="searchContent">
    </div>
</div>