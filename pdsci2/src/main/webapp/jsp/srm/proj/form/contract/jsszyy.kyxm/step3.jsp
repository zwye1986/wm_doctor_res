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
            var ue1 = initUEditer("mainViewAndContent");
        });
    </script>

</head>
<body>
<div id="main">
    <div class="mainright">
        <div class="content">
            <div style="margin-top: 10px;">
                <form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post"
                      id="projForm">
                    <input type="hidden" id="pageName" name="pageName" value="step3"/>
                    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
                    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
                    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

                    <font style="font-size: 14px; font-weight:bold;color: #333; ">项目目标和主要研究内容</font>
                    <table class="basic" style="width: 100%">
                        <tr>
                            <th style="text-align: left;padding-left: 20px;">

                            </th>
                        </tr>
                        <tr>
                            <td style="text-align:left;padding-left: 10px;padding-top: 10px;padding-bottom: 10px;">
                                <%--<textarea placeholder="" class="xltxtarea"--%>
                                          <%--name="mainViewAndContent">${resultMap.mainViewAndContent}</textarea>--%>
                                <c:choose>
                                    <c:when test="${param.view==GlobalConstant.FLAG_Y}">${resultMap.mainViewAndContent}</c:when>
                                    <c:otherwise>
                                        <script id="mainViewAndContent" type="text/plain" name="mainViewAndContent" style="width:100%;height:400px;margin-right: 10px;">${resultMap.mainViewAndContent}</script>
                                    </c:otherwise>
                                </c:choose>

                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p>
                                    注：国自然培育项目请在本章节后面简述本项目（院级课题）完成的研究内容和总体研究项目的关系，如作为总体研究项目的预实验、或总体项目中的一部分研究内容、或前期的数据收集等等。
                                </p>
                            </td>
                        </tr>
                    </table>
                </form>
                <div class="button"
                     style="width: 100%; <c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none</c:if>">
                    <input type="button" onclick="nextOpt('step2')" class="search" value="上一步"/>
                    <input type="button" onclick="nextOpt('step4')" class="search" value="保&#12288;存"/>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
		