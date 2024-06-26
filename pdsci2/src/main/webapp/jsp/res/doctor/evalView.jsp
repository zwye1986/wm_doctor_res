<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
    </jsp:include>
    <style type="text/css">
        .bg2{
            width: 60px;
            height: 16px;
            background: url(<s:url value='/jsp/res/activity/img/star_gray.png'/>);
            margin-left: 15px;
        }
        .over{
            height:16px;
            background:url(<s:url value='/jsp/res/activity/img/star_org.png'/>) no-repeat;
        }
    </style>
</head>
<body>

    <div style="min-height: 260px;max-height: 260px;overflow: auto;">
        <form id="addForm">
            <table class="grid" style="width:100%; margin-bottom: 10px;">
                <tr>
                    <th style="text-align: center;" width="400px">指标</th>
                    <th style="text-align: center;" colspan="2" width="200px">评分</th>
                </tr>
                    <c:forEach items="${lectureTargetList}" var="t" varStatus="s">
                        <tr>
                            <td style="text-align: center;">
                                ${t.targetName}
                            </td>
                            <td style="text-align: left;">
                                <c:set var="key" value="${scanRegist.recordFlow}${t.targetFlow}"></c:set>
                                <div class="bg2" style="margin-left: 38%;width: 60px;">
                                    <div class="over" style="width:${12*evalDetailMap[key]}px"></div>
                                </div>
                            </td>
                            <td>
                                <span id="${t.targetFlow}Message">
                                    <c:choose>
                                        <c:when test="${evalDetailMap[key] == 1}">
                                            没听懂
                                        </c:when>
                                        <c:when test="${evalDetailMap[key] == 2}">
                                            同意
                                        </c:when>
                                        <c:when test="${evalDetailMap[key] == 3}">
                                            赞成
                                        </c:when>
                                        <c:when test="${evalDetailMap[key] == 4}">
                                            棒极了
                                        </c:when>
                                        <c:when test="${evalDetailMap[key] == 5}">
                                            好听哭了
                                        </c:when>
                                        <c:otherwise></c:otherwise>
                                    </c:choose>
                                </span>
                            </td>
                        </tr>
                    </c:forEach>
            </table>
        </form>
    </div>
    <p style="text-align: center;">
        <input type="button" onclick="top.jboxCloseMessager();" class="btn_green" value="关&#12288;闭"/>
    </p>
</body>
</html>
