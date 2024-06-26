<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="ueditor" value="true"/>
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
        $(document).ready(function(){
            var ue1 = initUEditer("benefitAndValue");
        });
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
                    <font style="font-size: 14px; font-weight:bold;color: #333; ">项目验收内容和考核指标。</font>
                    <table class="basic" style="width: 100%">
                        <tr>
                            <th style="text-align: left;padding-left: 20px;">包括：1、主要技术指标：如完成研究任务情况（临床完成病例数）、形成的专利、新技术、新产品、新装置、论文专著等数量、指标及其水平等；
                                2、其他应考核的指标。
                            </th>
                        </tr>
                        <tr>
                            <td style="text-align:left;padding-left: 10px;padding-top: 10px;padding-bottom: 10px;">
                                <%--<textarea placeholder="" class="xltxtarea"
                                          name="benefitAndValue">${resultMap.benefitAndValue}</textarea>--%>
                                <c:choose>
                                    <c:when test="${param.view==GlobalConstant.FLAG_Y}">${resultMap.benefitAndValue}</c:when>
                                    <c:otherwise>
                                        <script id="benefitAndValue" type="text/plain" name="benefitAndValue" style="width:100%;height:400px;margin-right: 10px;">${resultMap.benefitAndValue}</script>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </table>
                </form>
                <div class="button"
                     style="width: 100%; <c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none</c:if>">
                    <input type="button" onclick="nextOpt('step3')" class="search" value="上一步"/>
                    <input type="button" onclick="nextOpt('step5')" class="search" value="保&#12288;存"/>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
		