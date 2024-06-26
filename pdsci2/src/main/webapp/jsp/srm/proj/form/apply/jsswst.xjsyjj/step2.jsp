<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <script type="text/javascript">
<c:if test="${param.view != GlobalConstant.FLAG_Y}">
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
</c:if>
    </script>
    <style type="text/css">
        .basic .inputText {
             text-align: left;
         }
    </style>
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

                    <font style="font-size: 14px; font-weight:bold;color: #333;">二、项目简介</font>
                    <table class="basic" style="width: 100%;margin-top: 10px;">
                        <tr>
                            <th style="text-align: left;padding-left: 15px;" >
                                1、新技术首创单位和时间,国内和省内首先开展单位及时间,新技术主要技术指标。
                            </th>
                        </tr>
                        <tr>
                            <td>
                                <textarea name="projContentOne" style="width:98%;height: 150px;margin-bottom: 20px">${resultMap.projContentOne}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align: left;padding-left: 15px;" >
                                2、申报单位引进该新技术时间，主要的技术改进之处，应用的例次数（涉及人体应用必须由病案管理部门提供病例数、病案号）。引进技术与国内外同类技术相比较所具有的技术水平与优缺点。本单位在省内外推广应用该技术情况。
                            </th>
                        </tr>
                        <tr>
                            <td>
                                <textarea name="projContentTwo" style="width:98%;height: 150px;margin-bottom: 20px">${resultMap.projContentTwo}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align: left;padding-left: 15px;" >
                                3、引进该技术项目后发表的论文、获得的专利、获奖等情况（请列出清单，论文等复印件附后）。
                            </th>
                        </tr>
                        <tr>
                            <td>
                                <textarea name="projContentThree" style="width:98%;height: 150px;margin-bottom: 20px">${resultMap.projContentThree}</textarea>
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

