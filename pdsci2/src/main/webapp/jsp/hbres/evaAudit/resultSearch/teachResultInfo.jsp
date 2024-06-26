<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <style type="text/css">
        td{border: 1px solid #e7e7eb}
        td{border: 1px solid #e7e7eb}
    </style>
</head>
<body>
<div class="mainright" style="overflow: auto;">
    <div class="basic" style="overflow-y: auto;height: 600px;" >
        <form id="addForm" style="position: relative;">
            <table class="grid" style="width:94%;margin-left: 3%; margin-top: 10px;">
                <tr>
                    <th width="20%">分类</th>
                    <th width="60%">评估内容</th>
                    <th width="10%">标准分</th>
                    <th width="10%">得分</th>
                </tr>
                <c:if test="${empty titleFormList}">
                    <table width="100%" class="basic">
                        <tr>
                            <th style="text-align: center;">无记录！</th>
                        </tr>
                    </table>
                </c:if>
                <c:if test="${not empty titleFormList}">
                    <c:set var="score" value="0"/>
                    <c:forEach items="${titleFormList}" var="title">
                        <c:forEach items="${title.itemList}" var="item" varStatus="status">
                            <tr>
                                <c:if test="${not empty item.row}">
                                    <td <c:if test="${not empty item.row}"> rowspan="${item.row}" </c:if> >${item.type}</td>
                                </c:if>
                                <td>${item.name}</td>
                                <c:set var="score" value="${score+item.score}"/>
                                <td>${item.score}</td>
                                <td>${gradeMap[item.id].score}</td>
                            </tr>
                        </c:forEach>

                    </c:forEach>
                    <tr>
                        <td colspan="2">合计</td>
                        <td>${score}</td>
                        <td>${gradeInfo.allScore}</td>
                    </tr>
                </c:if>
            </table>
            <c:if test="${not empty titleFormList}">
                <div style="margin-top: 20px;float: right;margin-right: 120px;">
                    评&ensp;价&ensp;人：&#12288;${gradeInfo.teacherNameOne} <br>
                    评价时间：&#12288;${pdfn:transDateTime(gradeInfo.operTime)}
                </div>
            </c:if>
        </form>
    </div>
    <div class="button">
        <input type="button" onclick="jboxClose();" class="btn_green" value="关&#12288;闭"/>
    </div>
</div>

</body>
</html>
