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
                autoValue($("#editform"),"autoValue");
                jboxSubmit($("#editform"), "<s:url value='/res/rec/saveRegistryForm'/>", function (resp) {
                    if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                        parentRefresh();
                        jboxClose();
                    }
                }, function (resp) {
                    jboxTip("${GlobalConstant.SAVE_FAIL}");
                }, true);
            }
        }
        function parentRefresh() {
            window.parent.frames['mainIframe'].window.loadProcess();
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
                <input type="hidden" name="recFlow" value="${rec.recFlow}"/>
                <input type="hidden" name="processFlow" value="${currRegProcess.processFlow}"/>
                <input type="hidden" name="operUserFlow" value="${param.operUserFlow}">
                <table class="basic" width="100%">
                    <tr>
                        <th colspan="4" style="text-align: left;">&#12288;登记信息</th>
                    </tr>
                    <tr>
                        <td style="width: 120px;text-align: right;"><font color="red">*</font>日期：</td>
                        <td>
                            <c:if test="${!empty doctor}">
                                <input name="communityDate" type="text"
                                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                                       value="${formDataMap['communityDate']}" style="width: 150px;"/>
                            </c:if>
                            <c:if test="${empty doctor}">
                                <label>${ formDataMap['communityDate']}</label>
                            </c:if>
                        </td>
                        <td style="width: 120px;text-align: right;"><font color="red">*</font>主题：</td>
                        <td>
                            <c:if test="${!empty doctor}">
                                <input class="validate[required]" name="theme" type="text" value="${formDataMap['theme']}"
                                       style="width: 150px;"/>
                            </c:if>
                            <c:if test="${empty doctor}">
                                <label>${formDataMap['theme']}</label>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 120px;text-align: right;"><font color="red">*</font>活动形式：</td>
                        <td>
                            <c:if test="${!empty doctor}">
                                <select name="activities" style="width: 153px;"
                                        class="autoValue validate[required]">
                                    <option value="">请选择</option>
                                    <option value="1"
                                            <c:if test="${'1' eq formDataMap['activities_id']}">selected</c:if>>案例讨论
                                    </option>
                                    <option value="2"
                                            <c:if test="${'2' eq formDataMap['activities_id']}">selected</c:if>>学术活动
                                    </option>
                                    <option value="3"
                                            <c:if test="${'3' eq formDataMap['activities_id']}">selected</c:if>>社区调查
                                    </option>
                                    <option value="4"
                                            <c:if test="${'4' eq formDataMap['activities_id']}">selected</c:if>>其他学习
                                    </option>
                                </select>
                            </c:if>
                            <c:if test="${empty doctor}">
                                <label>${formDataMap['activities']}</label>
                            </c:if>
                        </td>
                        <td style="width: 120px;text-align: right;"><font color="red">*</font>学时：</td>
                        <td>
                            <c:if test="${!empty doctor}">
                                <input class="validate[required]" name="hours" type="text"
                                       value="${formDataMap['hours']}" style="width: 150px;"/>
                            </c:if>
                            <c:if test="${empty doctor}">
                                <label>${formDataMap['hours']}</label>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 120px;text-align: right;"><font color="red">*</font>主讲人：</td>
                        <td colspan="3">
                            <c:if test="${!empty doctor}">
                                <input class="validate[required]" name="speaker" type="text"
                                       value="${formDataMap['speaker']}" style="width: 150px;"/>
                            </c:if>
                            <c:if test="${empty doctor}">
                                <label>${formDataMap['speaker']}</label>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 120px;text-align: right;">备注：</td>
                        <td colspan="3">
                            <c:if test="${!empty doctor}">
                                <textarea style="width: 92%;height: 90%;" name="remarks">${formDataMap['remarks']}</textarea>
                            </c:if>
                            <c:if test="${empty doctor}">
                                <label>${formDataMap['remarks']}</label>
                            </c:if>
                        </td>
                    </tr>
                </table>
                <p align="center">
                    <c:if test="${!(rec.auditStatusId eq recStatusEnumTeacherAuditY.id) && !param.isRead}">
                        <input class="search" type="button" value="保&#12288;存" onclick="saveForm();"/>
                    </c:if>
                    <input class="search" type="button" value="关&#12288;闭" onclick="jboxClose();"/>
                </p>
            </form>
        </div>
    </div>
</div>
</body>
</html>