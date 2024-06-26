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
                    <input type="hidden" id="pageName" name="pageName" value="step4"/>
                    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
                    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
                    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

                    <font style="font-size: 14px; font-weight:bold;color: #333;">三、重点学科带头人概况</font>
                    <table class="basic" style="width: 100%; margin-top: 10px;">
                        <tr>
                            <th colspan="6" style="text-align: left;padding-left: 20px;">基本情况</th>
                        </tr>
                        <tr>
                            <td style="text-align: left;"><font color="red">*</font>姓名：</td>
                            <td><input type="text" name="name" value="${resultMap.name}"
                                       class="inputText validate[required]" style="width: 80%"/></td>
                            <td style="text-align: left;"><font color="red">*</font>出生年月：</td>
                            <td><input class="inputText ctime validate[required]" type="text" name="birthday"
                                       value="${resultMap.birthday}" onClick="WdatePicker({dateFmt:'yyyy-MM'})"
                                       readonly="readonly" style="width: 80%"/></td>
                        </tr>
                        <tr>
                            <td style="text-align: left;">技术职称：</td>
                            <td>
                                <select name="technologyTitle" class="inputText" style="width: 80%;">
                                    <option value="">请选择</option>
                                    <c:forEach var="dict" items="${dictTypeEnumUserTitleList}">
                                        <option value="${dict.dictName }"
                                                <c:if test="${resultMap.technologyTitle eq dict.dictName }">selected="selected"</c:if>>${dict.dictName }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td style="text-align: left;">研究生导师：</td>
                            <td>
                                <input type="radio" name="isTutor" id="tutor_bd" value="博导" class="inputText"
                                       <c:if test="${resultMap.isTutor eq '博导'}">checked="checked"</c:if>/><label
                                    for="tutor_bd">&nbsp;博导</label>&nbsp;
                                <input type="radio" name="isTutor" id="tutor_sd" value="硕导" class="inputText"
                                       <c:if test="${resultMap.isTutor eq '硕导'}">checked="checked"</c:if>/><label
                                    for="tutor_sd">&nbsp;硕导</label>
                                <input type="radio" name="isTutor" id="tutor_none" value="否" class="inputText"
                                       <c:if test="${resultMap.isTutor eq '否'}">checked="checked"</c:if>/><label
                                    for="tutor_none">&nbsp;否</label>&nbsp;
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: left;"><font color="red">*</font>最高学会任职：</td>
                            <td>
                                <select name="highestServe" class="inputText validate[required]" style="width: 90%;">
                                    <option value="">请选择</option>
                                    <option value="主委"
                                            <c:if test="${resultMap.highestServe eq '主委'}">selected</c:if>>主委
                                    </option>
                                    <option value="副主委"
                                            <c:if test="${resultMap.highestServe eq '副主委'}">selected</c:if>>
                                        副主委
                                    </option>
                                    <option value="组长"
                                            <c:if test="${resultMap.highestServe eq '组长'}">selected</c:if>>组长
                                    </option>
                                    <option value="副组长"
                                            <c:if test="${resultMap.highestServe eq '副组长'}">selected</c:if>>副组长
                                    </option>
                                    <option value="常委"
                                            <c:if test="${resultMap.highestServe eq '常委'}">selected</c:if>>常委
                                    </option>
                                    <option value="委员"
                                            <c:if test="${resultMap.highestServe eq '委员'}">selected</c:if>>委员
                                    </option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th colspan="6" style="text-align: left;padding-left: 20px;"><font color="red">*</font>个人简介（业务能力、学术地位等）
                            </th>
                        </tr>
                        <tr>
                            <td colspan="6"><textarea class="validate[required]" name="personalInfo"
                                                      style="width:98%;height: 150px;">${resultMap.personalInfo}</textarea>
                            </td>
                        </tr>
                    </table>
                </form>
                <c:if test="${param.view!=GlobalConstant.FLAG_Y}">
                    <div align="center" style="margin-top: 10px">
                        <input id="prev" type="button" onclick="nextOpt('step3')" class="search" value="上一步"/>
                        <input id="nxt" type="button" onclick="nextOpt('step5')" class="search" value="下一步"/>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>
