<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <style type="text/css">
        td{text-align: left}
        textarea{width: 90%;height: 100px;max-height: 100px;max-width: 96%;margin: 5px 0;}
    </style>
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
<div class="mainright">
    <div class="">
        <div style="min-height: 260px;max-height: 260px;overflow: auto;">
        <form id="addForm">
            <table class="xllist" style="width:100%; margin-bottom: 10px; margin-top: 10px;">
                <tr>
                    <td style="text-align: center;" width="400px">指标</td>
                    <td style="text-align: center;" colspan="2" width="200px">评分</td>
                </tr>
                    <c:forEach items="${targets}" var="t" varStatus="s">
                        <tr>
                            <td style="text-align: center;">
                                ${t.targetName}
                            </td>
                            <td colspan="2" style="text-align: left;">
                                <c:set var="key" value="${result.resultFlow}${t.targetFlow}"></c:set>
                                <div class="bg2"style="margin-left: 38%;    width: 60px;">
                                    <div class="over" style="width:${12*evalDetailMap[key]}px"></div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
            </table>
        </form>
        </div>
        <p style="text-align: center;">
            <input type="button" onclick="jboxClose();" class="search" value="关&#12288;闭"/>
        </p>
    </div>
</div>
</body>
</html>
