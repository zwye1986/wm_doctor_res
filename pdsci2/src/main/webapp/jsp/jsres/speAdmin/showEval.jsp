 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
    </jsp:include>
    <style type="text/css">
        td{text-align: left}
        textarea{width: 90%;height: 100px;max-height: 100px;max-width: 96%;margin: 5px 0;}
    </style>
    <style type="text/css">
        .bg{
            width: 60px;
            height: 16px;
            background: url(<s:url value='/jsp/jsres/activity/img/star_gray.png'/>);
            margin-left: 15px;
        }
        .over{
            height:16px;
            background:url(<s:url value='/jsp/jsres/activity/img/star_org.png'/>) no-repeat;
        }
    </style>
    <script>

        $(document).ready(function(){
            var n="签到学员（${fn:length(results)+0}）";
            $("#scanA").html(n);
        });
        function showEval(resultFlow)
        {
            $("."+resultFlow).toggle();
        }
        function exportSiginList(){
            var url = "<s:url value='/jsres/activityQuery/exportSiginList'/>?activityFlow=${param.activityFlow}";
            jboxExp(null, url);
            jboxEndLoading();
        }


        $("a").click(function(event){
            event.stopPropagation();
        });
    </script>
</head>
<body>
<div class="mainright">
    <div class="basic">
        <div>
        <form id="addForm">

            <table class="grid" style="width:100%; margin-bottom: 10px; margin-top: 10px;">
                <tr>
                    <td width="100%" style="text-align: right;">
                        <input type="button" onclick="exportSiginList();" class="btn_green" value="导出签到学员"/>
                    </td>
                </tr>
            </table>
            <table class="grid" style="width:100%; margin-bottom: 10px; margin-top: 10px;">
                <c:forEach items="${targets}" var="t" varStatus="s">
                    <c:if test="${s.first}">
                        <tr>
                            <th style="text-align: right;border: 1px solid #e7e7eb;" rowspan="${fn:length(targets)}">指标均分：</th>
                            <td style="text-align: center;border: 1px solid #e7e7eb;">
                                ${t.targetName}
                            </td>
                            <td style="text-align: left;border: 1px solid #e7e7eb;">
                                <div class="bg">
                                    <div class="over" style="width:${12*t.evalScore}px"></div>
                                </div>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${!s.first}">
                        <c:if test="${t.isText eq 'N'}">
                            <tr>
                                <td style="text-align: center;border: 1px solid #e7e7eb;">
                                        ${t.targetName}
                                </td>
                                <td style="text-align: left;border: 1px solid #e7e7eb;">
                                    <div class="bg">
                                        <div class="over" style="width:${12*t.evalScore}px"></div>
                                    </div>
                                </td>
                            </tr>
                        </c:if>
                    </c:if>
                </c:forEach>
            </table>
            <table class="grid" style="width:100%; margin-bottom: 10px; margin-top: 10px;">
                <tr>
                    <td style="text-align: center;width:10%">姓名</td>
                    <td style="text-align: center;width:6%">年级</td>
                    <td style="text-align: center;width:10%">专业</td>
                    <td style="text-align: center;width:10%">人员类型</td>
                    <td style="text-align: center;width:18% ">签到时间</td>
                    <td style="text-align: center;width:18% ">签退时间</td>
                    <td style="text-align: center;width:10%">评分</td>
                    <td colspan="2" style="text-align: center;width:9%">状态</td>
                </tr>
                <c:forEach items="${results}" var="r" varStatus="s">
                    <tr onclick="showEval('${r.resultFlow}')">
                        <th style="text-align: center;">
                             ${r.userName}
                        </th>
                        <th style="text-align: center;">
                             ${r.sessionNumber}
                        </th>
                        <th style="text-align: center;">
                             ${r.speName}
                        </th>
                        <th style="text-align: center;">
                             ${r.doctorTypeName}
                        </th>
                        <th style="text-align: center;">
                             ${r.siginTime}
                        </th>
                        <th style="text-align: center;">
                             ${r.siginTime2}
                        </th>
                        <th style="text-align: center;">
                             ${r.evalScore}
                        </th>
                        <th style="text-align: center;" id="${r.resultFlow}rkname" colspan="2">
                                ${not empty r.evalScore?'认可':'不认可'}
                        </th>
                    </tr>
                    <c:forEach items="${targets}" var="t" varStatus="s">
                        <tr class="${r.resultFlow}" style="display: none;">
                            <td></td>
                            <td colspan="2" style="text-align: center;">
                                ${t.targetName}
                            </td>
                            <c:if test="${t.isText eq 'N'}">
                                <td colspan="1" style="text-align: left;">
                                    <c:set var="key" value="${r.resultFlow}${t.targetFlow}"></c:set>
                                    <div class="bg">
                                        <div class="over" style="width:${12*evalDetailMap[key]}px"></div>
                                    </div>
                                </td>
                                <td colspan="4" style="text-align: left;">
                                    <div>
                                        ${evalRemarksMap[key]}
                                    </div>
                                </td>
                            <c:if test="${approve ne 'Y'}">
                                <td></td>
                            </c:if>

                            </c:if>
                            <c:if test="${t.isText eq 'Y'}">
                                <td colspan="1"></td>
                                <td colspan="4" style="text-align: left;">
                                    <c:set var="key" value="${r.resultFlow}${t.targetFlow}"></c:set>
                                    <div>
                                        ${evalRemarksMap[key]}
                                    </div>
                                </td>
                                <td></td>
                            </c:if>

                        </tr>
                    </c:forEach>
                </c:forEach>
                <c:if test="${empty results}">
                    <tr>
                        <td colspan="8">暂无学员评分</td>
                    </tr>
                </c:if>
            </table>
        </form>
        </div>
    </div>
</div>
</body>
</html>
