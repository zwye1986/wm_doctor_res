<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/jsp/common/htmlhead.jsp">
    <jsp:param name="ueditor" value="true"/>
</jsp:include>
<script type="text/javascript">
    function save() {
        if(false==$("#saveCfgForm").validationEngine("validate")){
            return ;
        }
        var url = "<s:url value='/sys/cfg/save'/>";
        var data = $('#saveCfgForm').serialize();
        jboxPost(url, data, function() {
            window.location.href="<s:url value='/sys/cfg/main'/>?tagId=${param.tagId}";
        });
    }
    function checkYear(obj) {
        var dates = $(':text', $(obj).closest("td"));
        if (dates[0].value && dates[1].value && dates[0].value > dates[1].value) {
            jboxTip("开始时间不能大于结束时间！");
            obj.value = "";
        }
    }
</script>
<div class="mainright">
    <div class="content">
        <form id="saveCfgForm" >
            <div class="title1 clearfix">
                <div><font color="red">&#12288;&#12288;所有参数保存后，需刷新内存才能生效!!!</font></div>
                <jsp:include page="/jsp/sys/cfg/sysCfg.jsp" flush="true"/>
                <c:if test="${'systemFuncs'==param.tagId }">
                    <fieldset>
                        <legend>学籍配置</legend>
                        <table class="xllist">
                            <thead>
                            <tr>
                                <th width="20%">配置项</th>
                                <th width="80%">配置内容</th>
                            </tr>
                            </thead>
                            <tr>
                                <td style="text-align: right" width="100px">学籍导入：</td>
                                <td style="text-align: left;padding-left: 5px">
                                    <input type="hidden" name="cfgCode" value="xjgl_imp_student">
                                    <input type="radio" name="xjgl_imp_student" id="xjgl_imp_student_y"  value="${GlobalConstant.FLAG_Y}" <c:if test="${sysCfgMap['xjgl_imp_student']==GlobalConstant.FLAG_Y}">checked="checked"</c:if>  /><label for="xjgl_imp_student_y" >是</label>&nbsp;
                                    <input type="radio" name="xjgl_imp_student" id="xjgl_imp_student_n"  value="${GlobalConstant.FLAG_N}" <c:if test="${sysCfgMap['xjgl_imp_student']==GlobalConstant.FLAG_N || empty sysCfgMap['xjgl_imp_student'] }">checked="checked"</c:if>  /><label for="xjgl_imp_student_n" >否</label>
                                    <input type="hidden" name="xjgl_imp_student_ws_id"  value="gycmis">
                                    <input type="hidden" name="xjgl_imp_student_desc"  value="学籍导入">
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right" width="100px">答辩申请导师审核：</td>
                                <td style="text-align: left;padding-left: 5px">
                                    <input type="hidden" name="cfgCode" value="xjgl_audit_tutor">
                                    <input type="radio" name="xjgl_audit_tutor" id="xjgl_audit_tutor_y"  value="${GlobalConstant.FLAG_Y}" <c:if test="${sysCfgMap['xjgl_audit_tutor']==GlobalConstant.FLAG_Y}">checked="checked"</c:if>  /><label for="xjgl_audit_tutor_y" >是</label>&nbsp;
                                    <input type="radio" name="xjgl_audit_tutor" id="xjgl_audit_tutor_n"  value="${GlobalConstant.FLAG_N}" <c:if test="${sysCfgMap['xjgl_audit_tutor']==GlobalConstant.FLAG_N || empty sysCfgMap['xjgl_audit_tutor'] }">checked="checked"</c:if>  /><label for="xjgl_audit_tutor_n" >否</label>
                                    <input type="hidden" name="xjgl_audit_tutor_ws_id"  value="gycmis">
                                    <input type="hidden" name="xjgl_audit_tutor_desc"  value="答辩申请导师审核">
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right" width="100px">开放选课时间：</td>
                                <td style="text-align: left;padding-left: 5px">
                                    <input type="hidden" name="cfgCode" value="choose_course_start_time">
                                    <input type="hidden" name="cfgCode" value="choose_course_end_time">
                                    <input type="text" style="width: 100px;" name="choose_course_start_time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" onchange="checkYear(this)" value="${sysCfgMap['choose_course_start_time']}"/>
                                    &nbsp;-&nbsp;
                                    <input type="text" style="width: 100px;" name="choose_course_end_time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" onchange="checkYear(this)" value="${sysCfgMap['choose_course_end_time']}"/>
                                    <input type="hidden" name="choose_course_start_time_ws_id"  value="gycmis">
                                    <input type="hidden" name="choose_course_start_time_desc"  value="选课开始时间">
                                    <input type="hidden" name="choose_course_end_time_ws_id"  value="gycmis">
                                    <input type="hidden" name="choose_course_end_time_desc"  value="选课结束时间">
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right" width="100px">学生成绩查询扫码访问路径：</td>
                                <td style="text-align: left;padding-left: 5px" width="200px">
                                    <input type="hidden" name="cfgCode" value="qr_grade_search_url">
                                    <input type="text" class="xltext" name="qr_grade_search_url"
                                           value="${sysCfgMap['qr_grade_search_url']}" style="width: 400px;"
                                           <c:if test="${empty sysCfgMap['qr_grade_search_url']}">placeholder="例如：http://127.0.0.1:9090/****/login"</c:if>/>
                                    <input type="hidden" name="qr_grade_search_url_desc"  value="学生成绩查询扫码访问路径">
                                    <input type="hidden" name="qr_grade_search_url_ws_id"  value="gycmis">
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right" width="100px">学生角色：</td>
                                <td style="text-align: left;padding-left: 5px" width="200px">
                                    <input type="hidden" name="cfgCode" value="xjgl_student_role_flow">
                                    <select name="xjgl_student_role_flow" class="xlselect">
                                        <option></option>
                                        <c:forEach items="${applicationScope.sysRoleWsMap['gycmis'] }" var="role">
                                            <c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
                                                <option value="${role.roleFlow }" <c:if test="${sysCfgMap['xjgl_student_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    <input type="hidden" name="xjgl_student_role_flow_ws_id" value="gycmis">
                                    <input type="hidden" name="xjgl_student_role_flow_desc"  value="学籍学生角色">
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right" width="100px">授课组角色：</td>
                                <td style="text-align: left;padding-left: 5px" width="200px">
                                    <input type="hidden" name="cfgCode" value="xjgl_group_role_flow">
                                    <select name="xjgl_group_role_flow" class="xlselect">
                                        <option></option>
                                        <c:forEach items="${applicationScope.sysRoleWsMap['gycmis'] }" var="role">
                                            <c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
                                                <option value="${role.roleFlow }" <c:if test="${sysCfgMap['xjgl_group_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    <input type="hidden" name="xjgl_group_role_flow_ws_id" value="gycmis">
                                    <input type="hidden" name="xjgl_group_role_flow_desc"  value="学籍授课组角色">
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right" width="100px">教学组长角色：</td>
                                <td style="text-align: left;padding-left: 5px" width="200px">
                                    <input type="hidden" name="cfgCode" value="xjgl_master_role_flow">
                                    <select name="xjgl_master_role_flow" class="xlselect">
                                        <option></option>
                                        <c:forEach items="${applicationScope.sysRoleWsMap['gycmis'] }" var="role">
                                            <c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
                                                <option value="${role.roleFlow }" <c:if test="${sysCfgMap['xjgl_master_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    <input type="hidden" name="xjgl_master_role_flow_ws_id" value="gycmis">
                                    <input type="hidden" name="xjgl_master_role_flow_desc"  value="学籍教学组长角色">
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right" width="100px">授课老师角色：</td>
                                <td style="text-align: left;padding-left: 5px" width="200px">
                                    <input type="hidden" name="cfgCode" value="xjgl_teacher_role_flow">
                                    <select name="xjgl_teacher_role_flow" class="xlselect">
                                        <option></option>
                                        <c:forEach items="${applicationScope.sysRoleWsMap['gycmis'] }" var="role">
                                            <c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
                                                <option value="${role.roleFlow }" <c:if test="${sysCfgMap['xjgl_teacher_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    <input type="hidden" name="xjgl_teacher_role_flow_ws_id" value="gycmis">
                                    <input type="hidden" name="xjgl_teacher_role_flow_desc"  value="学籍授课老师角色">
                                </td>
                            </tr>

                            <tr>
                                <td style="text-align: right" width="100px">学费管理角色：</td>
                                <td style="text-align: left;padding-left: 5px" width="200px">
                                    <input type="hidden" name="cfgCode" value="xjgl_feeMaster_role_flow">
                                    <select name="xjgl_feeMaster_role_flow" class="xlselect">
                                        <option></option>
                                        <c:forEach items="${applicationScope.sysRoleWsMap['gycmis'] }" var="role">
                                            <c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
                                                <option value="${role.roleFlow }" <c:if test="${sysCfgMap['xjgl_feeMaster_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    <input type="hidden" name="xjgl_feeMaster_role_flow_ws_id" value="gycmis">
                                    <input type="hidden" name="xjgl_feeMaster_role_flow_desc"  value="学费管理角色">
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right" width="100px">二级机构角色：</td>
                                <td style="text-align: left;padding-left: 5px" width="200px">
                                    <input type="hidden" name="cfgCode" value="xjgl_secondaryOrg_role_flow">
                                    <select name="xjgl_secondaryOrg_role_flow" class="xlselect">
                                        <option></option>
                                        <c:forEach items="${applicationScope.sysRoleWsMap['gycmis'] }" var="role">
                                            <c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
                                                <option value="${role.roleFlow }" <c:if test="${sysCfgMap['xjgl_secondaryOrg_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    <input type="hidden" name="xjgl_secondaryOrg_role_flow_ws_id" value="gycmis">
                                    <input type="hidden" name="xjgl_secondaryOrg_role_flow_desc"  value="二级机构角色">
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right" width="100px">导师角色：</td>
                                <td style="text-align: left;padding-left: 5px" width="200px">
                                    <input type="hidden" name="cfgCode" value="xjgl_tutor_role_flow">
                                    <select name="xjgl_tutor_role_flow" class="xlselect">
                                        <option></option>
                                        <c:forEach items="${applicationScope.sysRoleWsMap['gycmis'] }" var="role">
                                            <c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
                                                <option value="${role.roleFlow }" <c:if test="${sysCfgMap['xjgl_tutor_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    <span>说明：通过注册或导入进来的人员绑定系统导师角色--导师管理需求</span>
                                    <input type="hidden" name="xjgl_tutor_role_flow_ws_id" value="gycmis">
                                    <input type="hidden" name="xjgl_tutor_role_flow_desc"  value="导师角色">
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right" width="100px">学校管理员角色：</td>
                                <td style="text-align: left;padding-left: 5px" width="200px">
                                    <input type="hidden" name="cfgCode" value="xjgl_school_role_flow">
                                    <select name="xjgl_school_role_flow" class="xlselect">
                                        <option></option>
                                        <c:forEach items="${applicationScope.sysRoleWsMap['gycmis'] }" var="role">
                                            <c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
                                                <option value="${role.roleFlow }" <c:if test="${sysCfgMap['xjgl_school_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    <span>说明：通过注册或导入进来的人员绑定系统导师角色--导师管理需求</span>
                                    <input type="hidden" name="xjgl_school_role_flow_ws_id" value="gycmis">
                                    <input type="hidden" name="xjgl_school_role_flow_desc"  value="学校管理员角色">
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                    <fieldset>
                        <legend>客户配置</legend>
                        <table class="xllist">
                            <thead>
                            <tr>
                                <th width="20%">配置项</th>
                                <th width="80%">配置内容</th>
                            </tr>
                            </thead>
                            <tr>
                                <td style="text-align: right" width="100px">学籍管理客户：</td>
                                <td style="text-align: left;padding-left: 5px" width="200px">
                                    <input type="hidden" name="cfgCode" value="xjgl_customer">
                                    <select name="xjgl_customer" class="xlselect">
                                        <option></option>
                                        <option value="nfykdx" <c:if test="${sysCfgMap['xjgl_customer'] eq 'nfykdx'}">selected</c:if>>南方医科大学</option>
                                        <option value="gzykdx" <c:if test="${sysCfgMap['xjgl_customer'] eq 'gzykdx'}">selected</c:if>>广州医科大学</option>
                                    </select>
                                    <input type="hidden" name="xjgl_customer_ws_id" value="gycmis">
                                    <input type="hidden" name="xjgl_customer_desc"  value="学籍管理客户">
                                    <span>说明：因广州医科大学与南方医科大学学籍管理系统有所区别，故通过配置实现差异化</span>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </c:if>
                <div class="button" >
                    <input type="button" class="search" onclick="save();" value="保&#12288;存">
                </div>
            </div>
        </form>
    </div>
</div>