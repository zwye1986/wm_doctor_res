<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
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
        <jsp:param name="ueditor" value="true"/>
    </jsp:include>
    <script>
        function saveForm() {
            if ($("#editform").validationEngine("validate")) {
                var title = "确认提交?";
                <c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
                title = "确认填写出科小结？填写后科室审核通过即可出科!";
                </c:if>
                autoValue($("#editform"),"autoValue");
                jboxConfirm(title, function () {
                    jboxPost("<s:url value='/res/rec/saveRegistryFormNew'/>", $('#editform').serialize(), function (resp) {
                        if (resp = "${GlobalConstant.SAVE_SUCCESSED}") {
                            parentRefresh();
                            jboxClose();
                        }
                    }, null, true);
                }, null);
            }
        }
        function parentRefresh() {
            window.parent.frames['mainIframe'].window.loadProcess();
        }
        function jboxPrint(id) {
            jboxTip("正在准备打印…")
            setTimeout(function(){
                $("#title").show();
                var newstr = $("#"+id).html();
                var oldstr = document.body.innerHTML;
                document.body.innerHTML = newstr;
                window.print();
                document.body.innerHTML = oldstr;
                $("#title").hide();
                jboxEndLoading();
                return false;
            },2000);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="editform" enctype="multipart/form-data" method="post">
                <input type="hidden" name="formFileName" value="${formFileName}"/>
                <input type="hidden" name="schDeptFlow" value="${param.schDeptFlow}"/>
                <input type="hidden" name="processFlow" value="${param.processFlow}"/>
                <input type="hidden" name="recFlow" value="${rec.recFlow}"/>
                <input type="hidden" name="operUserFlow"
                       value="${empty rec.operUserFlow?param.userFlow:rec.operUserFlow}"/>
                <c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
                    <input type="hidden" name="auditStatusId" value="${recStatusEnumTeacherAuditY.id}"/>
                </c:if>
                <c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
                    <input type="hidden" name="headAuditStatusId" value="${recStatusEnumHeadAuditY.id}"/>
                </c:if>
                <table class="basic" width="100%">
                    <tr>
                        <th colspan="4" style="text-align: left;">&#12288;出科小结</th>
                    </tr>
                    <td>
                        <p style="text-indent:0">&#12288;&#12288;个人小结内容：结合培养细则对医德医风、组织纪律、服务态度及质量、理论学习、学习的病种、</p>
                        <p style="text-indent:0">所参加的治疗、技术操作、社会实践等方面进行小结</p>
                    </td>
                    <tr>
                        <td style="padding-top: 10px;padding-bottom: 10px;">
                            <c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && empty rec.auditStatusId}">
                                <textarea class="validate[required]" style="width: 92%;min-height: 200px;"
                                          name="summary">${formDataMap['summary']}</textarea>
                            </c:if>
                            <c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && (!empty rec.auditStatusId || !empty rec.headAuditStatusId)}">
                                <label>${formDataMap['summary']}</label>
                            </c:if>
                            <c:if test="${(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER) or (param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD)}">
                                <label>${formDataMap['summary']}</label>
                                <textarea style="display: none;" name="summary">${formDataMap['summary']}</textarea>
                            </c:if>
                            <c:if test="${empty rec && !(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR)}">
                                暂无出科小结!
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            带教评价：
                        </td>
                    </tr>
                    <tr>
                        <td style="padding-top: 10px;padding-bottom: 10px;">
                            <c:choose>
                                <c:when test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId}">
                                    <textarea style="width: 92%;min-height: 200px;"
                                              name="teacherAppraise">${formDataMap['teacherAppraise']}</textarea>
                                </c:when>
                                <c:otherwise>
                                    <label>${formDataMap['teacherAppraise']}</label>
                                    <textarea style="display: none;" name="teacherAppraise">${formDataMap['teacherAppraise']}</textarea>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            科主任评价：
                        </td>
                    </tr>
                    <tr>
                        <td style="padding-top: 10px;padding-bottom: 10px;">
                            <c:choose>
                                <c:when test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD && (empty rec.headAuditStatusId && not empty rec.auditStatusId)}">
                                    <textarea style="width: 92%;min-height: 200px;"
                                              name="deptAppraise">${formDataMap['deptAppraise']}</textarea>
                                </c:when>
                                <c:otherwise>
                                    <label>${formDataMap['deptAppraise']}</label>
                                    <textarea style="display: none;" name="deptAppraise">${formDataMap['deptAppraise']}</textarea>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
                        <tr>
                            <td colspan="3">
                                <label>
                                    同意出科：
                                    <label>
                                        <input type="radio" name="isAgree" class="autoValue validate[required]"
                                               <c:if test="${formDataMap['isAgree_id'] ne GlobalConstant.FLAG_N}">checked</c:if>
                                               value="${GlobalConstant.FLAG_Y}"
                                        /> 是
                                    </label>
                                    <label>
                                        <input type="radio" name="isAgree" class="autoValue validate[required]"
                                               <c:if test="${formDataMap['isAgree_id'] eq GlobalConstant.FLAG_N}">checked</c:if>
                                               value="${GlobalConstant.FLAG_N}"
                                        /> 否
                                    </label>
                                </label>
                            </td>
                        </tr>
                    </c:if>
                </table>
            </form>
            <p align="center">
                <c:if test="${empty rec.auditStatusId && param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !param.isRead}">
                    <input class="search BC" type="button" value="保&#12288;存" onclick="saveForm();"/>
                </c:if>
                <c:if test="${empty rec.headAuditStatusId && param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.auditStatusId}">
                    [<font color="red">带教老师还未审核，请等待！</font>]
                </c:if>
                <c:if test="${(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD && (not empty rec.auditStatusId)) || (param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId)}">
                    <input class="search" type="button" value="提&#12288;交" onclick="saveForm();"/>
                </c:if>
                <c:if test="${GlobalConstant.RES_ROLE_SCOPE_DOCTOR eq param.roleFlag && not empty rec.auditStatusId}">
                    <input class="search" type="button" value="打&#12288;印" onclick="jboxPrint('editform');"/>
                </c:if>
                <%-- 				<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD && empty formDataMap['isAgree']}"> --%>
                <!-- 					<input class="search" type="button" value="确&#12288;认"  onclick="saveForm();"/> -->
                <%-- 				</c:if> --%>
                <input class="search" type="button" value="关&#12288;闭" onclick="jboxClose();"/>
            </p>
        </div>
    </div>
</div>
</body>
</html>
