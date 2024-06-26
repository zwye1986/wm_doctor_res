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
                    <input type="hidden" id="pageName" name="pageName" value="step4"/>
                    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
                    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
                    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>
                    <font style="font-size: 14px; font-weight:bold;color: #333; ">四、考核指标</font>
                    <table class="basic" style="width: 100%">
                        <tr>
                            <th style="text-align: left;padding-left: 20px;">
                                围绕主攻方向，提出可以量化和考核的年度和最终考核指标。分应用基础研究和临床研究两个部分。包括成果、课题、论文、专利、新药证书、特色和优势技术开展、四级手术量等。
                            </th>
                        </tr>
                        <tr>
                            <td style="text-align:left;padding-left: 10px;padding-top: 10px;padding-bottom: 10px;">
                                <textarea placeholder="" class="xltxtarea"
                                          name="examStandard">${resultMap.examStandard}</textarea>
                            </td>
                        </tr>
                    </table>
                    <font style="font-size: 14px; font-weight:bold;color: #333; ">五、单位培养措施</font>
                    <table class="basic" style="width: 100%">
                        <tr>
                            <th style="text-align: left;padding-left: 20px;">须列出具体培养措施，包括业务条件改善、人才梯队建设、配套资金落实、出国进修等。
                            </th>
                        </tr>
                        <tr>
                            <td style="text-align:left;padding-left: 10px;padding-top: 10px;padding-bottom: 10px;">
                                <textarea placeholder="" class="xltxtarea"
                                          name="orgCulture">${resultMap.orgCulture}</textarea>
                            </td>
                        </tr>
                    </table>
                </form>
                <div class="button"
                     style="width: 100%; <c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none</c:if>">
                    <%--<a href="javascript:void(0)" target="_self" class="search" onclick="nextOpt('step3')">上一步</a>--%>
                    <%--<a href="javascript:void(0)" target="_self" class="search" onclick="nextOpt('step5')">下一步</a>--%>
                        <input id="prev" type="button" onclick="nextOpt('step3')" class="search" value="上一步"/>
                        <input id="prev" type="button" onclick="nextOpt('step5')" class="search" value="下一步"/>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
		