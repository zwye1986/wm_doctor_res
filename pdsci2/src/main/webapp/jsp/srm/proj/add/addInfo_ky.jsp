<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_ztree" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        <c:if test="${param.view != GlobalConstant.FLAG_Y}">
        function checkBDDate() {
            if ($('#projStartTime').val() && $('#projEndTime').val() && $('#projStartTime').val() > $('#projEndTime').val()) {
                jboxTip("计划开始时间不能大于计划结束时间！");
                return false;
            }
            return true;
        }
    </script>

    <script type="text/javascript">
        function back() {
            jboxCloseMessager();
            ;
        }


        function save() {
            if (false == $("#projForm").validationEngine("validate")) {
                return false;
            }
            if (!checkBDDate()) return false;
            jboxConfirm("确认提交?", function () {
                //var data = $('#projForm').serialize();//k=v&k=v
                //var url = "<s:url value='/srm/proj/add/saveProjInfo/ky'/>";
                $('#projForm').submit();
            });
        }
    </script>
    </c:if>
    <div id="main">
        <div class="mainright">
            <div class="content">
                <form id="projForm" method="post" action="<s:url value='/srm/proj/add/saveProjInfo/ky'/>" style="position: relative;">
                    <input type="hidden" name="pageName" value="step1"/>
                    <table class="bs_tb" style="width: 100%">
                        <tr>
                            <th colspan="4" class="theader">立项信息</th>
                        </tr>
                        <tr>
                            <td style="text-align: center">项目名称：</td>
                            <td style="text-align: left;padding-left: 10px;">
                                <input type="text" class="validate[required] inputText" name='projName' value=""
                                       style="width: 46%"/>
                                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                            </td>
                            <td style="text-align: center">项目类别：</td>
                            <td style="text-align: left;padding-left: 10px;">
                                <select name="projTypeId" class="validate[required] inputText xlselect" style="width: 46%">
                                    <option value="">请选择</option>
                                    <c:choose>
                                        <c:when test="${sessionScope.projCateScope==projCategroyEnumKy.id}">
                                            <c:forEach var="dictEnuProjType" items="${dictTypeEnumProjTypeList}">
                                                <option value="${dictEnuProjType.dictId}"
                                                        <c:if test='${proj.projTypeId==dictEnuProjType.dictId}'>selected="selected"</c:if>>${dictEnuProjType.dictName}</option>
                                            </c:forEach>
                                        </c:when>
                                        <c:when test="${sessionScope.projCateScope==projCategroyEnumQw.id}">
                                            <c:forEach var="dictEnuProjType" items="${dictTypeEnumEdusTypeList}">
                                                <option value="${dictEnuProjType.dictId}"
                                                        <c:if test='${proj.projTypeId==dictEnuProjType.dictId}'>selected="selected"</c:if>>${dictEnuProjType.dictName}</option>
                                            </c:forEach>
                                        </c:when>
                                        <c:when test="${sessionScope.projCateScope==projCategroyEnumXk.id}">
                                            <c:forEach var="dictEnuSubjType" items="${dictTypeEnumSubjTypeList}">
                                                <option value="${dictEnuSubjType.dictId}"
                                                        <c:if test='${proj.projTypeId==dictEnuSubjType.dictId}'>selected="selected"</c:if>>${dictEnuSubjType.dictName}</option>
                                            </c:forEach>
                                        </c:when>
                                        <c:when test="${sessionScope.projCateScope==projCategroyEnumRc.id}">
                                            <c:forEach var="dictEnuTalentType" items="${dictTypeEnumTalentTypeList}">
                                                <option value="${dictEnuTalentType.dictId}"
                                                        <c:if test='${proj.projTypeId==dictEnuTalentType.dictId}'>selected="selected"</c:if>>${dictEnuTalentType.dictName}</option>
                                            </c:forEach>
                                        </c:when>
                                    </c:choose>
                                </select>
                                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                            </td>
                        </tr>
                        <tr>


                            <td style="text-align: center">负责人部门：</td>
                            <td style="text-align: left;padding-left: 10px;">
                                <script>
                                    function loadUsers(deptFlow) {
                                        jboxPost('<s:url value="/srm/proj/add/getUsersByDept"/>', {deptFlow: deptFlow}, function (resp) {
                                            //List<SysUser>
                                            //resp : [{userFlow:'xxxxx',userName:'xxxx'},{userFlow:'zzzzz',userName:'zzzzz'}]
                                            $('#usersContent').empty().append('<option />');
                                            for (var index in resp) {
                                                var user = resp[index];
                                                var option = $('<option value="' + user.userFlow + '">' + user.userName + '</option>');
                                                $('#usersContent').append(option);
                                            }
                                        }, null, false);
                                    }

                                </script>
                                <select name="applyDeptFlow" class="validate[required] inputText xlselect" onchange="loadUsers(this.value);">
                                    <option value="">请选择</option>
                                    <c:forEach items="${depts}" var="dept">
                                        <option value="${dept.deptFlow}">${dept.deptName}</option>
                                    </c:forEach>
                                    <%--<option value="">其他部门</option>--%>
                                </select>
                                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                            <td style="text-align: center">项目负责人：</td>
                            <td style="text-align: left;padding-left: 10px;">
                                <select id="usersContent" name="applyUserFlow"
                                        class="validate[required] inputText xlselect">
                                    <option value="">请选择</option>
                                </select>
                                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                            </td>
                        </tr>
                        <tr id="planTimeTr">
                            <td style="text-align: center;">项目开始时间：</td>
                            <td width="30%" style="text-align: left; padding-left: 10px;">
                                <input id="projStartTime" name="projStartTime" type="text" value="<c:if
        test='${empty projInfoMap.projStartTime}'>${applicationScope.sysCfgMap['srm_projInfo_ky_start_time']}</c:if><c:if
        test='${! empty projInfoMap.projStartTime}'>${projInfoMap.projStartTime}</c:if>" class="inputText ctime"
                                       style="width: 160px;" readonly="readonly"
                                       <c:if test="${empty applicationScope.sysCfgMap['srm_projInfo_ky_start_time']}">onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"</c:if>
                                       onchange="checkBDDate()"/>
                                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                            </td>
                            <td style="text-align: center;">项目结束时间：</td>
                            <td width="30%" style="text-align: left; padding-left: 10px;">
                                <input id="projEndTime" name="projEndTime" type="text" value="<c:if
        test='${empty projInfoMap.projEndTime}'>${applicationScope.sysCfgMap['srm_projInfo_ky_end_time']}</c:if><c:if
        test='${! empty projInfoMap.projEndTime}'>${projInfoMap.projEndTime}</c:if>" class="inputText ctime"
                                       style="width: 160px;" readonly="readonly"
                                       <c:if test="${empty applicationScope.sysCfgMap['srm_projInfo_ky_end_time']}">onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"</c:if>
                                       onchange="checkBDDate()"/>
                                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                            </td>
                        </tr>
                    </table>
                </form>
                <div align="center" style="margin-top: 10px">
                    <input id="search" type="button" onclick="save()" class="search" value="提&#12288;交"/>
                    <input id="close" type="button" onclick="back()" class="search" value="关&#12288;闭"/>
                </div>
            </div>
        </div>
    </div>
