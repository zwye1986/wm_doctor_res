<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
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
    <script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select2.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

    <script type="text/javascript">
        $(document).ready(function(){
            $(".showInfo").on("mouseover mouseout",
                    function(){
                        $(".theInfo",this).toggle();
                    }
            );
            initDepts();
        });
        function initDepts()
        {
            var datas=[];
            <c:forEach items="${sysDeptList}" var="dept">
            var d={};
                d.id="${dept.deptFlow}";
                d.text="${dept.deptName}";
                datas.push(d);
            </c:forEach>
            var itemSelectFuntion = function(){
                $("#deptFlow").val(this.id);
            };
            $.selectSuggest('trainDept',datas,itemSelectFuntion,"deptFlow",true);
        }
        function add(orgFlow) {
            jboxOpen("<s:url value='/sys/user/edit/${GlobalConstant.USER_LIST_LOCAL}'/>?orgFlow=" + orgFlow, "新增用户信息", 900, 400);
        }
        function edit(userFlow) {
            jboxOpen("<s:url value='/sys/user/edit/${GlobalConstant.USER_LIST_LOCAL}?userFlow='/>" + userFlow, "编辑用户信息", 900, 400);
        }
        function allotRole(userFlow) {
            jboxOpen("<s:url value='/sys/user/allotRole?userFlow='/>" + userFlow, "分配用户角色", 900, 500);
        }
        function resetPasswd(userFlow) {
            jboxConfirm("确认将该用户的密码重置为:${pdfn:getInitPass()} 吗？", function () {
                var url = "<s:url value='/sys/user/resetPasswd?userFlow='/>" + userFlow;
                jboxGet(url, null, function () {
                    //searchUser();
                });
            });
        }

        function activate(userFlow) {
            jboxConfirm("确认启用该用户吗？", function () {
                var url = "<s:url value='/sys/user/activate?userFlow='/>" + userFlow;
                jboxGet(url, null, function () {
                    searchUser();
                });
            });
        }
        function lock(userFlow) {
            jboxConfirm("确认停用该用户吗？停用后该用户将不能登录系统！", function () {
                var url = "<s:url value='/sys/user/lock?userFlow='/>" + userFlow;
                jboxGet(url, null, function () {
                    searchUser();
                });
            });
        }
        function searchUser() {
            jboxStartLoading();
            $("#searchForm").submit();
        }
        function searchDept(orgFlow, deptFlow) {
            if (orgFlow == "") {
                return;
            }
            var url = "<s:url value='/sys/user/getDept'/>?orgFlow=" + orgFlow + "&deptFlow=" + deptFlow;
            jboxGet(url, null, function (resp) {
                $("#deptSelectId").html(resp);
            }, null, false);
        }
        function toPage(page) {
            if (page != undefined) {
                $("#currentPage").val(page);
            }
            searchUser();
        }

        function editDoc(doctorFlow, userFlow) {
            jboxOpen("<s:url value='/res/doc/user/editDoc'/>?roleFlag=${GlobalConstant.USER_LIST_GLOBAL}&doctorFlow=" + doctorFlow + "&userFlow=" + userFlow, (doctorFlow == '') ? "新增医师信息" : "编辑医师信息", 1100, 500);
        }
        function selRole(userFlow, roleFlow) {
            var url = "<s:url value='/res/manager/selRole'/>?userFlow=" + userFlow + "&roleFlow=" + roleFlow + "&wsId=${GlobalConstant.RES_WS_ID}";
            jboxGet(url, null, function (obj) {
                if (obj == "${GlobalConstant.SAVE_SUCCESSED}") {
                    check(userFlow);
                }
            }, null, true);
        }
        function check(userFlow) {
            var a = $("#" + userFlow + "box");
            $("#" + userFlow + "speRel").toggle(a[0].checked);
        }

        function userSpeRel(userFlow) {
            jboxOpen("<s:url value='/res/manager/userSpeRel'/>?userFlow=" + userFlow, "关联专业", 700, 500);
        }
        function daoRu() {
            var url = "<s:url value='/jsp/res/manager/importPerManage.jsp'/>";
            jboxOpen(url, "导入", 700, 290);

        }
        function daoChu() {
            var url = "<s:url value='/res/manager/exportTeacher'/>";
            jboxTip("导出中…………");
            jboxExp($("#searchForm"), url);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/res/manager/manageUser" />" method="post">
                <div class="queryDiv" style="padding-left: 20px;max-width: 980px;min-width:980px;">
                    <c:if test="${sessionScope.userListScope==GlobalConstant.USER_LIST_LOCAL }">
                        <input type="hidden" name="orgFlow" value="${sessionScope.currUser.orgFlow }"/>
                    </c:if>
                    <input id="currentPage" type="hidden" name="currentPage" value=""/>
                    <div class="inputDiv">
                        <label class="qlable">手机号码：</label>
                        <input type="text" name="userPhone" value="${param.userPhone}" class="qtext"/>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">
                            电子邮箱：
                        </label>
                        <input type="text" name="userEmail" value="${param.userEmail}" class="qtext"/>
                    </div>
                    <div class="inputDiv"
                            <c:if test="${ applicationScope.sysCfgMap['sys_user_dept_flag']==GlobalConstant.FLAG_Y }">
                                style="max-width: 313px;min-width: 313px;"
                            </c:if>
                    >
                        <label class="qlable">科&#12288;&#12288;室：</label>

                        <input id="trainDept" name="deptName" value="${param.deptName}"  class="toggleView qtext" type="text"  autocomplete="off"  />
                        <input type="hidden" name="deptFlow" value="${param.deptFlow}" id="deptFlow">
                        <%--<select class="qselect" id="deptFlow" name="deptFlow" onchange="searchUser();">--%>
                            <%--<option value="">请选择</option>--%>
                            <%--<c:forEach items="${sysDeptList}" var="dept">--%>
                                <%--<option value="${dept.deptFlow}" <c:if test="${dept.deptFlow==param.deptFlow}">selected</c:if> >${dept.deptName}</option>--%>
                            <%--</c:forEach>--%>
                        <%--</select>--%>
                        <c:if test="${ applicationScope.sysCfgMap['sys_user_dept_flag']==GlobalConstant.FLAG_Y }">
                            <input name="moreDept" type="checkbox" value="${GlobalConstant.FLAG_Y}"
                                   onclick="searchUser();"
                                   <c:if test='${param.moreDept eq GlobalConstant.FLAG_Y}'>checked="checked"</c:if>>
                            &nbsp;多科室
                        </c:if>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">身&ensp;份&ensp;证：</label>
                        <input type="text" name="idNo" value="${param.idNo}" class="qtext"/>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">姓&#12288;&#12288;名：</label>
                        <input type="text" name="userName" value="${param.userName}" class="qtext"/>
                    </div>
                    <div class="inputDiv"
                            <c:if test="${ applicationScope.sysCfgMap['sys_user_dept_flag']==GlobalConstant.FLAG_Y }">
                                style="max-width: 310px;min-width: 310px;"
                            </c:if>
                    >
                        <label class="qlable">
                            角&#12288;&#12288;色：</label>
                        <select name="roleFlow" class="qselect">
                            <option></option>
                            <c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
                                <c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
                                    <option value="${role.roleFlow }"
                                            <c:if test="${param.roleFlow ==role.roleFlow }">selected</c:if>
                                            <c:if test="${sysCfgMap['res_teacher_role_flow'] !=role.roleFlow
										and sysCfgMap['res_secretary_role_flow'] !=role.roleFlow
										and sysCfgMap['res_head_role_flow'] !=role.roleFlow
										and sysCfgMap['res_manager_role_flow'] !=role.roleFlow
										and sysCfgMap['res_disciple_role_flow'] !=role.roleFlow
										and sysCfgMap['res_tutor_role_flow'] !=role.roleFlow
										and sysCfgMap['res_responsible_teacher_role_flow'] !=role.roleFlow
										and sysCfgMap['res_train_teacher_role_flow'] !=role.roleFlow
										}">style="display: none"</c:if>
                                    >${role.roleName }</option>
                                </c:if>
                            </c:forEach>
                        </select>
                        <c:if test="${ applicationScope.sysCfgMap['sys_user_dept_flag']==GlobalConstant.FLAG_Y }">
                            &#12288;&#12288;&#12288;&#12288;&nbsp;
                        </c:if>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">用&ensp;户&ensp;名：</label>
                        <input type="text" name="userCode" value="${param.userCode}" class="qtext"/>
                    </div>
                    <%--<div class="inputDiv">--%>
                        <%--<label class="qlable">证书级别：</label>--%>
                        <%--<select name="certificateLevelId" class="qselect">--%>
                            <%--<option></option>--%>
                            <%--<c:forEach items="${dictTypeEnumCertificatelevelList}" var="title">--%>
                                <%--<option value="${title.dictId}"--%>
                                        <%--<c:if test='${param.certificateLevelId==title.dictId}'>selected="selected"</c:if>>${title.dictName}</option>--%>
                            <%--</c:forEach>--%>
                        <%--</select>--%>
                    <%--</div>--%>
                    <div class="inputDiv" style="max-width: 318px;min-width: 318px;">
                        <label class="qlable">用户状态：&nbsp;</label>
                        <input id="all" name="statusId" type="radio" value="" onclick="searchUser();"
                               <c:if test='${empty param.statusId}'>checked="checked"</c:if>>
                        <label for="all">全部</label>&#12288;
                        <c:if test="${sessionScope.currWsId==GlobalConstant.EDC_WS_ID}">
                            <input id="${userStatusEnumAdded.id}" name="statusId" type="radio"
                                   value="${userStatusEnumAdded.id}" onclick="searchUser();"
                                   <c:if test='${param.statusId==userStatusEnumAdded.id}'>checked="checked"</c:if>>
                            <label for="${userStatusEnumAdded.id }">${userStatusEnumAdded.name}</label>&#12288;
                        </c:if>
                        <c:if test="${sessionScope.currWsId==GlobalConstant.SRM_WS_ID}">
                            <input id="${userStatusEnumReged.id}" name="statusId" type="radio"
                                   value="${userStatusEnumReged.id}" onclick="searchUser();"
                                   <c:if test='${param.statusId==userStatusEnumReged.id}'>checked="checked"</c:if>>
                            <label for="${userStatusEnumReged.id }">${userStatusEnumReged.name}</label>&#12288;
                        </c:if>
                        <input id="${userStatusEnumActivated.id}" name="statusId" type="radio"
                               value="${userStatusEnumActivated.id}" onclick="searchUser();"
                               <c:if test='${param.statusId==userStatusEnumActivated.id}'>checked="checked"</c:if>>
                        <label for="${userStatusEnumActivated.id }">${userStatusEnumActivated.name}</label>&#12288;
                        <input id="${userStatusEnumLocked.id}" name="statusId" type="radio"
                               value="${userStatusEnumLocked.id}" onclick="searchUser();"
                               <c:if test='${param.statusId==userStatusEnumLocked.id}'>checked="checked"</c:if>>
                        <label for="${userStatusEnumLocked.id }">${userStatusEnumLocked.name}</label>
                        &#12288;&#12288;&nbsp;&nbsp;
                    </div>
                    <div class="lastDiv">
                        <input type="button" class="searchInput" onclick="searchUser();" value="查&#12288;询">
                    </div>
                </div>
                <div class="funcDiv">
                    &nbsp;&nbsp;&nbsp;
                    <c:if test="${sessionScope.currWsId!=GlobalConstant.SRM_WS_ID}">
                        <input type="button" class="search" onclick="add($('#orgFlow').val());" value="新&#12288;增">
                    </c:if>
                    <c:if test="${sessionScope.currWsId==GlobalConstant.SRM_WS_ID}">
                        <c:if test="${applicationScope.sysCfgMap['srm_add_user_flag'] ==  GlobalConstant.FLAG_Y }">
                            <input type="button" class="search" onclick="add($('#orgFlow').val());"
                                   value="新&#12288;增">
                        </c:if>
                    </c:if>

                    <input type="button" class="search" onclick="daoRu();" value="导&#12288;入">
                    <input type="button" class="search" onclick="daoChu();" value="导&#12288;出">
                </div>
            </form>
        </div>
        <table class="xllist">
            <tr>
                <!-- <th width="20px"></th> -->
                <th width="50px">姓名</th>
                <th width="50px">状态</th>
                <th width="30px">性别</th>
                <th width="60px">科室</th>
                <th width="80px">职称</th>
                <th width="60px">手机号码</th>
                <th width="150px">电子邮箱</th>
                <th width="200px">角色</th>
                <c:if test="${applicationScope.sysCfgMap['sys_weixin_qiye_flag']==GlobalConstant.FLAG_Y}">
                    <th width="60px">微信关注状态</th>
                </c:if>
                <th width="180px">操作</th>
            </tr>
            <c:forEach items="${sysUserList}" var="sysUser">

                <tr style="height:20px ">
                    <td title="${sysUser.userCode}">${sysUser.userName}</td>
                    <td>${sysUser.statusDesc}</td>
                    <td>${sysUser.sexName}</td>
                    <td>${sysUser.deptName}
                    </td>
                    <td>${sysUser.titleName}</td>
                    <td>${sysUser.userPhone}</td>
                    <td>${sysUser.userEmail}</td>
                    <td style="text-align: left;padding-left: 10px;">
                            <%-- 					<c:if test="${!empty applicationScope.sysCfgMap['res_doctor_role_flow']}"> --%>
                        <!-- 						&#12288; -->
                        <!-- 						<label> -->
                            <%-- 							<input type="checkbox" onclick="selRole('${sysUser.userFlow}',this.value);" value="${applicationScope.sysCfgMap['res_doctor_role_flow']}" <c:if test="${pdfn:contain(applicationScope.sysCfgMap['res_doctor_role_flow'],sysUserRoleMap[sysUser.userFlow]) }">checked='checked'</c:if>/>${!empty sysRoleMap[sysCfgMap['res_doctor_role_flow']]?sysRoleMap[sysCfgMap['res_doctor_role_flow']].roleName:'学员'}&#12288; --%>
                        <!-- 						</label> -->
                            <%-- 					</c:if> --%>
                        <c:if test="${!empty applicationScope.sysCfgMap['res_teacher_role_flow']}">
                            <label style="width: 35%;display: inline-block;">
                                <input type="checkbox" onclick="selRole('${sysUser.userFlow}',this.value)"
                                       value="${applicationScope.sysCfgMap['res_teacher_role_flow'] }"
                                       <c:if test="${pdfn:contain(applicationScope.sysCfgMap['res_teacher_role_flow'],sysUserRoleMap[sysUser.userFlow]) }">checked='checked'</c:if>/>${!empty sysRoleMap[sysCfgMap['res_teacher_role_flow']]?sysRoleMap[sysCfgMap['res_teacher_role_flow']].roleName:'带教老师'}
                            </label>
                        </c:if>
                        <c:if test="${!empty applicationScope.sysCfgMap['res_secretary_role_flow']}">
                            <label style="width: 35%;display: inline-block;">
                                <input type="checkbox" onclick="selRole('${sysUser.userFlow}',this.value)"
                                       value="${applicationScope.sysCfgMap['res_secretary_role_flow'] }"
                                       <c:if test="${pdfn:contain(applicationScope.sysCfgMap['res_secretary_role_flow'],sysUserRoleMap[sysUser.userFlow]) }">checked='checked'</c:if>/>${!empty sysRoleMap[sysCfgMap['res_secretary_role_flow']]?sysRoleMap[sysCfgMap['res_secretary_role_flow']].roleName:'科秘'}
                            </label>
                        </c:if>
                        <c:if test="${!empty applicationScope.sysCfgMap['res_head_role_flow']}">
                            <label style="width: 35%;display: inline-block;">
                                <input type="checkbox" onclick="selRole('${sysUser.userFlow}',this.value);"
                                       value="${applicationScope.sysCfgMap['res_head_role_flow']}"
                                       <c:if test="${pdfn:contain(applicationScope.sysCfgMap['res_head_role_flow'],sysUserRoleMap[sysUser.userFlow]) }">checked='checked'</c:if>/>${!empty sysRoleMap[sysCfgMap['res_head_role_flow']]?sysRoleMap[sysCfgMap['res_head_role_flow']].roleName:'科主任'}
                            </label>
                        </c:if>
                        <c:if test="${!empty applicationScope.sysCfgMap['res_manager_role_flow']}">
                            <label style="width: 35%;display: inline-block;" title="
							<c:if test="${empty spesMap[sysUser.userFlow]}">
								暂无关联专业！
							</c:if>
							<c:if test="${!empty spesMap[sysUser.userFlow]}">
								<div style='width:100px;'>
								<c:forEach items="${spesMap[sysUser.userFlow]}" var="userSpe" varStatus="status">
									<c:if test="${!status.first}">
										 ，
									</c:if>
									${userSpe.trainingSpeName}
								</c:forEach>
								</div>
							</c:if>
						">
                                <input type="checkbox" class="box" id="${sysUser.userFlow}box"
                                       onchange="check('${sysUser.userFlow}');"
                                       onclick="selRole('${sysUser.userFlow}',this.value)"
                                       value="${applicationScope.sysCfgMap['res_manager_role_flow'] }"
                                       <c:if test="${pdfn:contain(applicationScope.sysCfgMap['res_manager_role_flow'],sysUserRoleMap[sysUser.userFlow]) }">checked='checked'</c:if>
                                />${!empty sysRoleMap[sysCfgMap['res_manager_role_flow']]?sysRoleMap[sysCfgMap['res_manager_role_flow']].roleName:'基地主任'}
                            </label>
                        </c:if>
                        <c:if test="${!empty applicationScope.sysCfgMap['res_disciple_role_flow']}">
                            <label style="width: 35%;display: inline-block;">
                                <input type="checkbox" onclick="selRole('${sysUser.userFlow}',this.value);"
                                       value="${applicationScope.sysCfgMap['res_disciple_role_flow']}"
                                       <c:if test="${pdfn:contain(applicationScope.sysCfgMap['res_disciple_role_flow'],sysUserRoleMap[sysUser.userFlow]) }">checked='checked'</c:if>/>${!empty sysRoleMap[sysCfgMap['res_disciple_role_flow']]?sysRoleMap[sysCfgMap['res_disciple_role_flow']].roleName:'师承老师'}
                            </label>
                        </c:if>
                        <c:if test="${!empty applicationScope.sysCfgMap['res_tutor_role_flow']}">
                            <label style="width: 35%;display: inline-block;">
                                <input type="checkbox" onclick="selRole('${sysUser.userFlow}',this.value);"
                                       value="${applicationScope.sysCfgMap['res_tutor_role_flow']}"
                                       <c:if test="${pdfn:contain(applicationScope.sysCfgMap['res_tutor_role_flow'],sysUserRoleMap[sysUser.userFlow]) }">checked='checked'</c:if>/>${!empty sysRoleMap[sysCfgMap['res_tutor_role_flow']]?sysRoleMap[sysCfgMap['res_tutor_role_flow']].roleName:'导师'}
                            </label>
                        </c:if>
                        <c:if test="${!empty applicationScope.sysCfgMap['res_responsible_teacher_role_flow']}">
                            <label style="width: 35%;display: inline-block;">
                                <input type="checkbox" onclick="selRole('${sysUser.userFlow}',this.value);"
                                       value="${applicationScope.sysCfgMap['res_responsible_teacher_role_flow']}"
                                       <c:if test="${pdfn:contain(applicationScope.sysCfgMap['res_responsible_teacher_role_flow'],sysUserRoleMap[sysUser.userFlow]) }">checked='checked'</c:if>/>${!empty sysRoleMap[sysCfgMap['res_responsible_teacher_role_flow']]?sysRoleMap[sysCfgMap['res_responsible_teacher_role_flow']].roleName:'责任导师'}
                            </label>
                        </c:if>
                        <c:if test="${!empty applicationScope.sysCfgMap['res_train_teacher_role_flow']}">
                            <label style="width: 35%;display: inline-block;">
                                <input type="checkbox" onclick="selRole('${sysUser.userFlow}',this.value);"
                                       value="${applicationScope.sysCfgMap['res_train_teacher_role_flow']}"
                                       <c:if test="${pdfn:contain(applicationScope.sysCfgMap['res_train_teacher_role_flow'],sysUserRoleMap[sysUser.userFlow]) }">checked='checked'</c:if>/>${!empty sysRoleMap[sysCfgMap['res_train_teacher_role_flow']]?sysRoleMap[sysCfgMap['res_train_teacher_role_flow']].roleName:'培训老师'}
                            </label>
                        </c:if>
                    </td>
                    <c:if test="${applicationScope.sysCfgMap['sys_weixin_qiye_flag']==GlobalConstant.FLAG_Y}">
                        <td width="60px" title="${sysUser.weiXinId}">${sysUser.weiXinStatusDesc}</td>
                    </c:if>
                    <td style="text-align: center;padding-left: 5px;">
                        <c:if test="${sysUser.statusId==userStatusEnumAdded.id}">
                            等待验证...
                        </c:if>
                        <c:if test="${sysUser.statusId==userStatusEnumReged.id}">
                            [<a href="javascript:audit('${sysUser.userFlow}');" >审核</a>]
                            <c:if test="${sessionScope.currUser.userCode==GlobalConstant.ROOT_USER_CODE}"> |
                                [<a href="javascript:edit('${sysUser.userFlow}');" >编辑</a>]
                            </c:if>
                        </c:if>
                        <c:if test="${sysUser.statusId==userStatusEnumActivated.id}">
                            [<a href="javascript:edit('${sysUser.userFlow}');" >编辑</a>] |
                            [<a href="javascript:resetPasswd('${sysUser.userFlow}');" >重置密码</a>] |
                            [<a href="javascript:lock('${sysUser.userFlow}');" >停用</a>]
                        </c:if>

                        <c:if test="${sysUser.statusId==userStatusEnumLocked.id}">
                            [<a href="javascript:activate('${sysUser.userFlow}');" >启用</a>]
                        </c:if>
                        <c:if test="${!empty applicationScope.sysCfgMap['res_manager_role_flow']}">
                            <label id="${sysUser.userFlow}speRel" style="<c:if
                                    test="${!(pdfn:contain(applicationScope.sysCfgMap['res_manager_role_flow'],sysUserRoleMap[sysUser.userFlow]))}">display: none;</c:if>">|
                                [<a onclick="userSpeRel('${sysUser.userFlow}');"
                                    style="cursor: pointer;">关联专业</a>]</label>
                        </c:if>
                            <%-- [<a href="javascript:markExpert('${sysUser.userFlow}');" >标记为专家</a>] --%>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty sysUserList}">
                <tr>
                    <td align="center" colspan="9">无记录</td>
                </tr>
            </c:if>
        </table>
        <c:set var="pageView" value="${pdfn:getPageView(sysUserList)}" scope="request"></c:set>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
</body>
</html>