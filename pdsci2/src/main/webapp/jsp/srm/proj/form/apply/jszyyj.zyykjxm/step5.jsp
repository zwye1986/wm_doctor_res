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
                    <input type="hidden" id="pageName" name="pageName" value="step5"/>
                    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
                    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
                    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

                    <font style="font-size: 14px; font-weight:bold;color: #333;">四、研究目标、可行性分析</font>
                    <table class="basic" style="width: 100%; margin-top: 10px;">
                        <tr>
                            <th style="text-align: left;">
                                &#12288;&#12288;1．研究目标
                            </th>
                        </tr>
                        <tr>
                            <td>
                                <textarea class="" name="study_target"
                                                      style="width:98%;height: 150px;">${resultMap.study_target}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align: left;">
                                &#12288;&#12288; 2．研究内容
                            </th>
                        </tr>
                        <tr>
                            <td >
                                <textarea class="" name="study_content"
                                                      style="width:98%;height: 150px;">${resultMap.study_content}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align: left;">
                                &#12288;&#12288; 3．研究方法、技术路线、可行性分析
                            </th>
                        </tr>
                        <tr>
                            <td >
                                <textarea class="" name="study_method"
                                                      style="width:98%;height: 150px;">${resultMap.study_method}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align: left;">
                                &#12288;&#12288; 4．本课题拟解决的关键问题
                            </th>
                        </tr>
                        <tr>
                            <td >
                                <textarea class="" name="study_problem"
                                                      style="width:98%;height: 150px;">${resultMap.study_problem}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align: left;">
                                &#12288;&#12288; 5．本课题的特色、创新点及预期研究结果
                            </th>
                        </tr>
                        <tr>
                            <td >
                                <textarea class="" name="study_special"
                                                      style="width:98%;height: 150px;">${resultMap.study_special}</textarea>
                                <br/>
                            </td>
                        </tr>
                    </table>
                </form>
                <c:if test="${param.view!=GlobalConstant.FLAG_Y}">
                    <div align="center" style="margin-top: 10px">
                        <input id="prev" type="button" onclick="nextOpt('step4')" class="search" value="上一步"/>
                        <input id="nxt" type="button" onclick="nextOpt('step6')" class="search" value="下一步"/>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>
