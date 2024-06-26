<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="false"/>
        <jsp:param name="jquery_datePicker" value="false"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="false"/>
        <jsp:param name="jquery_iealert" value="false"/>
        <jsp:param name="treetable" value="true"/>
    </jsp:include>
    <script type="text/javascript">

    </script>
    <style type="text/css">
        a {
            color: #00a0ff;
        }

        a:hover {
            color: #ff6600
        }
    </style>
</head>
<body>
<div id="main">
    <div class="mainright">

        <%--<p style="padding-top: 15px; padding-bottom:10px; font-weight: bold;"></p>--%>
        <div style="height:300px;overflow: auto;margin-top: 10px">
            <table style="width: 99%;" class="xllist">
                <tr>
                    <th style="text-align: left;padding-left: 10px" colspan="5">操作过程</th>
                </tr>
                <tr>
                    <th width="27%">项目名称</th>
                    <th width="15%">结余金额</th>
                    <th width="22%">操作时间</th>
                    <th width="20%">操作内容</th>
                    <th width="15%">操作人</th>
                </tr>
                <tbody id="appendTbody">
                <c:forEach items="${surplusDetailList}" var="detail" varStatus="status">
                    <tr>
                        <td>${detail.reimburseName}</td>
                        <td>${pdfn:transMultiply(detail.money,10000)}</td>
                        <td>
                                ${pdfn:transDateTime(detail.createTime)}
                        </td>
                        <td>
                                ${detail.content}
                        </td>
                        <td>
                                ${detail.fundOperator}
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty surplusDetailList}">
                    <tr>
                        <td colspan="5">无数据！</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
        <p align="center" style="width:100%;padding-top: 10px;">
            <input class="search" type="button" value="关&#12288;闭" onclick="jboxClose();"/>
        </p>

    </div>
</div>
</div>
</body>
</html>