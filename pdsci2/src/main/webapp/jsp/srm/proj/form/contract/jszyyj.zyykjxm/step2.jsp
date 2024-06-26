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
            $('#nxt').attr({"disabled":"disabled"});
            $('#prev').attr({"disabled":"disabled"});
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
                <form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post"
                      id="projForm">
                    <input type="hidden" id="pageName" name="pageName" value="step2"/>
                    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
                    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
                    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>
                    <font style="font-size: 14px; font-weight:bold;color: #333; "></font>
                    <table class="basic" style="width: 100%">

                        <tr>
                            <th style="width: 25%">委托部门（甲方）：</th>
                            <td style="width: 25%">江苏省中医药局</td>
                            <th style="width: 25%">法定代表人或委托代理人：</th>
                            <td style="width: 25%">
                                <input type="text" class="validate[required] inputText" name="orgUserName"
                                       style="width: 150px"
                                       value="${resultMap.orgUserName}"/>
                            </td>
                        </tr>
                        <tr>
                            <th>地址：</th>
                            <td>南京市中央路42号</td>
                            <th>邮政编码：</th>
                            <td>210008</td>
                        </tr>
                        <tr>
                            <td colspan="4"></td>
                        </tr>
                        <tr>
                            <th>项目承担方（乙方）：</th>
                            <td>
                                <input type="text" class="validate[required] inputText" name="applyOrgName"
                                       style="width: 150px"
                                       value="${resultMap.applyOrgName}"/>
                            </td>
                            <th>法定代表人：</th>
                            <td>
                                <input type="text" class="validate[required] inputText" name="applyUserName"
                                       style="width: 150px"
                                       value="${resultMap.applyUserName}"/>
                            </td>
                        </tr>
                        <tr>
                            <th>地址：</th>
                            <td>
                                <input type="text" class="validate[required] inputText" name="applyOrgAddress"
                                       style="width: 250px"
                                       value="${resultMap.applyOrgAddress}"/>
                            </td>
                            <th>邮政编码：</th>
                            <td>
                                <input type="text" class="validate[required] inputText" name="applyPostNum"
                                       style="width: 150px"
                                       value="${resultMap.applyPostNum}"/>
                            </td>
                        </tr>
                        <tr>
                            <th>项目负责人：</th>
                            <td>
                                <input type="text" class="validate[required] inputText" name="applyChangeName"
                                       style="width: 150px"
                                       value="<c:if test='${empty resultMap.applyChangeName and param.view!=GlobalConstant.FLAG_Y}'>${proj.applyUserName}</c:if><c:if test='${! empty resultMap.applyChangeName}'>${resultMap.applyChangeName}</c:if>"/>
                            </td>
                            <th>电话：</th>
                            <td>
                                <input type="text" class="validate[required] inputText" name="applyPhoneCall"
                                       style="width: 150px"
                                       value="<c:if test='${empty resultMap.applyPhoneCall and param.view!=GlobalConstant.FLAG_Y}'>${proj.applyUserPhone}</c:if><c:if test='${!empty resultMap.applyPhoneCall}'>${resultMap.applyPhoneCall}</c:if>"/>
                            </td>
                        </tr>
                        <tr>
                            <th>传真：</th>
                            <td>
                                <input type="text" class="validate[required] inputText" name="applyFax"
                                       style="width: 150px"
                                       value="${resultMap.applyFax}"/>
                            </td>
                            <th>电子邮件：</th>
                            <td>
                                <input type="text" class="validate[required] inputText" name="applyEmail"
                                       style="width: 150px"
                                       value="${resultMap.applyEmail}"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4"></td>
                        </tr>
                        <tr>
                            <th>保证单位（丙方、项目主管部门）：</th>
                            <td><input type="text" class="validate[required] inputText" name="guaranteeOrg"
                                       value="${resultMap.guaranteeOrg}"
                                             style="width: 150px"/></td>
                            <th>法定代表人：</th>
                            <td>
                                <input type="text" class="validate[required] inputText" name="guaranteeName"
                                       value="${resultMap.guaranteeName}"
                                       style="width: 150px"/>
                            </td>
                        </tr>
                        <tr>
                            <th>地址：</th>
                            <td>
                                <input type="text" class="validate[required] inputText" name="guaranteeAddress"
                                       value="${resultMap.guaranteeAddress}"
                                       style="width: 250px"/>
                            </td>
                            <th>邮政编码：</th>
                            <td>
                                <input type="text" class="validate[required] inputText" name="guaranteePostNum"
                                       value="${resultMap.guaranteePostNum}"
                                       style="width: 150px"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4"></td>
                        </tr>
                        <tr>
                            <td colspan="4">
                                &#12288;&#12288;&#12288;&#12288;甲方批准由乙方承担江苏省中医药局科技项目<input type="text" class="validate[required] inputText" name="applyProj"
                                                          value="<c:if test='${empty resultMap.applyProj and param.view!=GlobalConstant.FLAG_Y}'>${proj.projName}</c:if><c:if test='${! empty resultMap.applyProj}'>${resultMap.applyProj}</c:if>" style="width: 350px"/>的研究开发或建设任务。依据《中华人民共和国合同法》的规定，为明确甲、乙、丙三方的权利和责任，保证项目的顺利实施和科研经费的合理使用，签订本合同。
                            </td>
                        </tr>
                    </table>
                </form>
                <div class="button"
                     style="width: 100%; <c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none</c:if>">
                    <input id="" type="button" onclick="nextOpt('step1')" class="search" value="上一步"/>
                    <input id="prev" type="button" onclick="nextOpt('step3')" class="search" value="下一步"/>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
		