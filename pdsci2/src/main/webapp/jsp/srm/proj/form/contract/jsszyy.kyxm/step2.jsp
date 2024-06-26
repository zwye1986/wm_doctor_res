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
                            <td>江苏省中医院科技处</td>
                        </tr>
                        <tr>
                            <th>负责人：</th>
                            <td>
                                <input type="text" class="validate[required] inputText" name="orgUserName"
                                       style="width: 100px"
                                       value="<c:if test="${empty resultMap.orgUserName}">何伟明</c:if>${resultMap.orgUserName}"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"></td>
                        </tr>
                        <tr>
                            <th>项目承担方（乙方）：</th>
                            <td>项目负责人</td>
                        </tr>
                        <tr>
                            <th>项目负责人：</th>
                            <td>
                                <input type="text" class="validate[required] inputText" name="applyUserName"
                                       style="width: 100px"
                                       value="<c:if test='${empty resultMap.applyUserName and param.view!=GlobalConstant.FLAG_Y}'>${proj.applyUserName}</c:if><c:if test='${! empty resultMap.applyUserName}'>${resultMap.applyUserName}</c:if>"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"></td>
                        </tr>
                        <tr>
                            <th>项目保证方（丙方）：</th>
                            <td>项目承担科室<input type="text" class="validate[required] inputText" name="deptName"
                                             value="<c:if test='${empty resultMap.deptName and param.view!=GlobalConstant.FLAG_Y}'>${proj.applyDeptName}</c:if>${resultMap.deptName}"
                                             style="width: 100px"/></td>
                        </tr>
                        <tr>
                            <th>科室负责人：</th>
                            <td>
                                <input type="text" class="validate[required] inputText" name="deptUserName"
                                       value="${resultMap.deptUserName}"
                                       style="width: 100px"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                甲方批准由乙方承担江苏省中医院科技项目<input type="text" class="validate[required] inputText" name="applyProj"
                                                          value="<c:if test='${empty resultMap.applyProj and param.view!=GlobalConstant.FLAG_Y}'>${proj.projName}</c:if><c:if test='${! empty resultMap.applyProj}'>${resultMap.applyProj}</c:if>" style="width: 350px"/>的研究开发或建设任务。依据《中华人民共和国合同法》的规定，为明确甲、乙、丙三方的权利和责任，保证项目的顺利实施和科研经费的合理使用，签订本合同。
                            </td>
                        </tr>
                    </table>
                </form>
                <div class="button"
                     style="width: 100%; <c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none</c:if>">
                    <input id="" type="button" onclick="nextOpt('step1')" class="search" value="上一步"/>
                    <input id="prev" type="button" onclick="nextOpt('step3')" class="search" value="保&#12288;存"/>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
		