<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
        function doBack() {
            window.location.href = "<s:url value='/srm/proj/mine/process?projFlow='/>" + $("#projFlow").val();
        }
        function checkBDDate(dt){
            var dates = $(':text',$(dt).closest("td"));
            if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
                jboxTip("开始时间不能大于结束时间！");
                dt.value = "";
            }
        }
    </script>
    <style type="text/css">
        .bs_tb tbody th {
            background: #fff;
        }

        .bs_tb tbody td {
            text-align: left;
        }

        .bs_tb tbody td input {
            text-align: left;
            margin-left: 10px;
        }
    </style>
</head>
<body>
<div id="main">
    <div class="mainright">
        <div class="content">
            <div style="margin-top: 10px;">
                <form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post"
                      id="projForm">
                    <input type="hidden" id="pageName" name="pageName" value="step1"/>
                    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
                    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
                    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

                    <font style="font-size: 14px; font-weight:bold;color: #333; "></font>
                    <table class="basic" style="width: 100%">
                        <tr>
                            <td style="text-align: center">项目编号：</td>
                            <td style="text-align: left;padding-left: 10px;" colspan="3">
                                <input type="text" name="projNo" value="${proj.projNo}" class="validate[required] inputText" style="width: 46%" readonly="readonly"/>
                                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center">项目名称：</td>
                            <td style="text-align: left;padding-left: 10px;" colspan="3">
                                <input type="text" name="projName" class="validate[required] inputText" style="width: 46%"
                                       value="<c:if test='${empty resultMap.projName and param.view!=GlobalConstant.FLAG_Y}'>${proj.projName}</c:if><c:if test='${! empty resultMap.projName}'>${resultMap.projName}</c:if>"/>
                                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center">项目负责人：</td>
                            <td style="text-align: left;padding-left: 10px;" colspan="3">
                                <input type="text" name="projLeader" class="validate[required] inputText" style="width: 46%"
                                       value="<c:if test='${empty resultMap.projLeader and param.view!=GlobalConstant.FLAG_Y}'>${proj.applyUserName}</c:if><c:if test='${! empty resultMap.projLeader}'>${resultMap.projLeader}</c:if>"/>
                                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center">起止年限：</td>
                            <td style="text-align: left;padding-left: 10px;" colspan="3">
                                <input type="text" class="inputText validate[required]" name="startTime" value="${resultMap.startTime}"
                                       onclick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly"
                                       style="width: 20%" onchange="checkBDDate(this)"/>&#12288;至&#12288;
                                <input type="text" class="inputText validate[required]" name="endTime" value="${resultMap.endTime}"
                                       onclick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly"
                                       style="width: 20%" onchange="checkBDDate(this)"/>
                                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center">
                               承担单位：
                            </td>
                            <td style="text-align: left;padding-left: 10px;" colspan="3">
                                <input class="validate[required] inputText" name="applyOrgName"
                                       value="${resultMap.applyOrgName}" style="width: 46%" />
                                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center">
                                单位地址：
                            </td>
                            <td style="text-align: left;padding-left: 10px;" colspan="3">
                                <input class="validate[required] inputText" name="orgAdress"
                                       value="${resultMap.orgAdress}" style="width: 46%"/>
                                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                            </td>
                        </tr>
                            <tr>
                                <td style="text-align: center">联系部门：</td>
                                <td style="text-align: left;padding-left: 10px;" colspan="3">
                                    <input type="text" class="validate[required] inputText" name="orgSection"
                                           value="${resultMap.orgSection}"
                                           style="width: 46%"/>
                                    <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: center">联系人：</td>
                                <td style="text-align: left;padding-left: 10px;" colspan="3">
                                    <input type="text" class="inputText validate[required]" name="contacts"
                                           value="<c:if test='${empty resultMap.contacts and param.view!=GlobalConstant.FLAG_Y}'>${resultMap.contacts}</c:if><c:if test='${! empty resultMap.contacts}'>${resultMap.contacts}</c:if>"
                                           style="width: 46%"/>
                                    <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: center">联系电话：</td>
                                <td style="text-align: left;padding-left: 10px;" colspan="3">
                                    <input type="text" class="inputText validate[required,custom[phone]]" name="phoneNum"
                                           value="<c:if test='${empty resultMap.phoneNum and param.view!=GlobalConstant.FLAG_Y}'>${resultMap.phoneNum}</c:if><c:if test='${! empty resultMap.phoneNum}'>${resultMap.phoneNum}</c:if>"
                                           style="width: 46%"/>
                                    <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: center">
                                    电子邮箱：
                                </td>
                                <td style="text-align: left;padding-left: 10px;" colspan="3">
                                    <input class="validate[required] inputText" name="email" value="${resultMap.email}"
                                           style="width: 46%"/>
                                    <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                                </td>
                            </tr>
                    </table>
                </form>
                <c:if test="${param.view!=GlobalConstant.FLAG_Y}">
                    <div align="center" style="margin-top: 10px">
                        <input id="prev" type="button" onclick="doBack()" class="search" value="返&#12288;回"/>
                        <input id="nxt" type="button" onclick="nextOpt('step2')" class="search" value="下一步"/>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>
		