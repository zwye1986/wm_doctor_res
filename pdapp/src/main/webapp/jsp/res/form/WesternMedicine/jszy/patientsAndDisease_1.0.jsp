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
                        <td style="width: 120px;text-align: right;"><font color="red">*</font>姓名：</td>
                        <td>
                            <c:if test="${!empty doctor}">
                                <input class="validate[required]" name="diseasePersonName" type="text"
                                       value="${formDataMap['diseasePersonName']}" style="width: 150px;"/>
                            </c:if>
                            <c:if test="${empty doctor}">
                                <label>${formDataMap['diseasePersonName']}</label>
                            </c:if>
                        </td>
                        <td style="width: 120px;text-align: right;"><font color="red">*</font>性别：</td>
                        <td>
                            <c:if test="${!empty doctor}">
                                <select name="gender" style="width: 153px;"
                                        class="autoValue validate[required]">
                                    <option value="">请选择</option>
                                    <option value="Man"
                                            <c:if test="${'Man' eq formDataMap['gender_id']}">selected</c:if>>男
                                    </option>
                                    <option value="Woman"
                                            <c:if test="${'Woman' eq formDataMap['gender_id']}">selected</c:if>>女
                                    </option>
                                </select>
                            </c:if>
                            <c:if test="${empty doctor}">
                                <label>${formDataMap['gender']}</label>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 120px;text-align: right;"><font color="red">*</font>年龄：</td>
                        <td>
                            <c:if test="${!empty doctor}">
                                <input class="validate[required]" name="age" type="text" value="${formDataMap['age']}"
                                       style="width: 150px;"/>
                            </c:if>
                            <c:if test="${empty doctor}">
                                <label>${formDataMap['age']}</label>
                            </c:if>
                        </td>
                        <td style="width: 120px;text-align: right;"><font color="red">*</font>就诊号/病历号：</td>
                        <td>
                            <c:if test="${!empty doctor}">
                                <input class="validate[required]" name="medicalRecordNumber" type="text"
                                       value="${formDataMap['medicalRecordNumber']}" style="width: 150px;"/>
                            </c:if>
                            <c:if test="${empty doctor}">
                                <label>${formDataMap['medicalRecordNumber']}</label>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 120px;text-align: right;"><font color="red">*</font>健康问题：</td>
                        <td colspan="3">
                            <c:if test="${!empty doctor}">
                                <textarea style="width: 92%;height: 90%;" class="validate[required]"
                                          name="healthProblem">${formDataMap['healthProblem']}</textarea>
                            </c:if>
                            <c:if test="${empty doctor}">
                                <label>${formDataMap['healthProblem']}</label>
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