<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="true"/>
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
    <script type="text/javascript">
        function nextOpt(step) {
            if (false == $("#projForm").validationEngine("validate")) {
                return false;
            }
            var form = $('#projForm');
            var action = form.attr('action');
            action += "?nextPageName=" + step;
            form.attr("action", action);
            form.submit();
        }
    </script>
</head>
<body>
<div id="main">
    <div class="mainright">
        <div class="content">
            <div style="margin-top: 10px;">
                <form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" style="position: relative;"
                      id="projForm">
                    <input type="hidden" id="pageName" name="pageName" value="step2"/>
                    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
                    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
                    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

                    <font style="font-size: 14px; font-weight:bold;color: #333;">一、简表</font>

                    <table class="basic" style="width: 100%; margin-top: 10px;">
                        <tr>
                            <th colspan="8" style="text-align: left;padding-left: 15px;">项目负责人</th>
                        </tr>
                        <tr>
                            <td style="text-align: right;"><font color="red">*</font>姓名：</td>
                            <td><input type="text" name="name" value="${resultMap.name}"
                                       class="inputText validate[required]" style="width: 80%"/></td>
                            <td style="text-align: right;"><font color="red">*</font>性别：</td>
                            <td>
                                <select name="sexId" class="inputText validate[required]" style="width: 80%;">
                                    <option value="">请选择</option>
                                    <c:forEach var="sex" items="${userSexEnumList}">
                                        <c:if test="${sex.id != userSexEnumUnknown.id}">
                                            <option value="${sex.name}"
                                                    <c:if test="${resultMap.sexId eq sex.name}">selected="selected"</c:if>>${sex.name}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </td>
                            <td style="text-align: right;"><font color="red">*</font>年龄：</td>
                            <td colspan="3"><input type="text" name="age" value="${resultMap.age}"
                                                   class="inputText validate[required]" style="width: 80%"/></td>
                        </tr>
                        <tr>
                            <td style="text-align: right;">学历：</td>
                            <td><input type="text" name="eduRecord" value="${resultMap.eduRecord}"
                                       class="inputText" style="width: 80%"/></td>
                            <td style="text-align: right;">学位：</td>
                            <td><input type="text" name="degree" value="${resultMap.degree}" class="inputText"
                                       style="width: 80%"/></td>
                            <td style="text-align: right;">职称：</td>
                            <td>
                                <input type="text" name="positional_titles" value="${resultMap.positional_titles}"
                                       class="inputText"
                                       style="width: 80%"/>
                            </td>
                            <td style="text-align: right;">职务：</td>
                            <td><input type="text" name="business" value="${resultMap.business}" class="inputText"
                                       style="width: 80%"/></td>
                        </tr>
                        <tr>
                            <td style="text-align: right;">工作单位</td>
                            <td colspan="2"><input type="text" name="workUnit" value="${resultMap.workUnit}" class="inputText"
                                       style="width: 80%"/></td>
                            <td style="text-align: right;">所在科室</td>
                            <td colspan="2"><input type="text" name="department" value="${resultMap.department}" class="inputText"
                                       style="width: 80%"/></td>
                        </tr>
                        <tr>
                            <td style="text-align: right;">从事专业</td>
                            <td colspan="2"><input type="text" name="major" value="${resultMap.major}" class="inputText"
                                       style="width: 80%"/></td>
                            <td style="text-align: right;">联系电话（手机）</td>
                            <td colspan="2"><input type="text" name="telPhone" value="${resultMap.telPhone}" class="inputText"
                                       style="width: 80%"/></td>
                        </tr>
                    </table>
                    <table class="basic" style="width: 100%; margin-top: 10px;">
                        <tr>
                            <th colspan="4" style="text-align: left;padding-left: 15px;">推广项目</th>
                        </tr>
                        <tr>
                            <td width="20%" style="text-align: left;padding-left: 20px;"><font color="red">*</font>项目名称
                            </td>
                            <td colspan="3"><input type="text" name="proj_name"
                                                   value="<c:if test='${empty resultMap.proj_name and param.view!=GlobalConstant.FLAG_Y}'>${proj.projName}</c:if><c:if test='${!empty resultMap.proj_name}'>${resultMap.proj_name}</c:if>"
                                                   class="inputText validate[required]"
                                                   style="width: 80%"/></td>
                        </tr>
                        <tr>
                            <td width="20%" style="text-align: left;padding-left: 20px;">所属学科
                            </td>
                            <td width="30%"><input type="text" name="proj_subject"
                                                   value="${resultMap.proj_subject}"
                                                   class="inputText"
                                                   style="width: 80%"/></td>
                            <td width="20%" style="text-align: left;padding-left: 20px;">相关学科</td>
                            <td width="30%"><input type="text" name="related_subject"
                                                   value="${resultMap.related_subject}"
                                                   class="inputText"
                                                   style="width: 80%"/></td>
                        </tr>
                        <tr>
                            <td width="20%" style="text-align: left;padding-left: 20px;"><font color="red">*</font>市卫生计生委资助经费</td>
                            <td><input type="text" name="globe_fee"
                                       value="${resultMap.globe_fee}"
                                       class="inputText validate[required]"
                                       style="width: 80%"/> 万元</td>
                            <td width="20%" style="text-align: left;padding-left: 20px;"><font color="red">*</font>总经费</td>
                            <td><input type="text" name="amount_fee"
                                       value="${resultMap.amount_fee}"
                                       class="inputText validate[required]"
                                       style="width: 80%"/> 万元</td>
                        </tr>
                        <tr>
                            <td width="20%" style="text-align: left;padding-left: 20px;"><font color="red">*</font>项目起止年月</td>
                            <td colspan="3">
                                <input type="text" class="inputText validate[required]" name="projStartTime" value="${resultMap.projStartTime}"
                                       onclick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly"
                                       style="width: 20%"/>&#12288;至&#12288;
                                <input type="text" class="inputText validate[required]" name="projEndTime" value="${resultMap.projEndTime}"
                                       onclick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly"
                                       style="width: 20%"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4" style="text-align: left;">
                                &#12288;&#12288;推广项目概述（简要说明推广内容、方式、目标及成效，限400字）
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4"><textarea name="approveSummary" class="validate[maxSize[400]]"
                                          style="width:98%;height: 150px;">${resultMap.approveSummary}</textarea>
                            </td>
                        </tr>
                    </table>
                </form>

                <c:if test="${param.view!=GlobalConstant.FLAG_Y}">
                    <div align="center" style="margin-top: 10px">
                        <input id="prev" type="button" onclick="nextOpt('step1')" class="search" value="上一步"/>
                        <input id="nxt" type="button" onclick="nextOpt('step3')" class="search" value="下一步"/>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>
