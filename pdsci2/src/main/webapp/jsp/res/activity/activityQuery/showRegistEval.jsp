 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
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
            background: url(<s:url value='/jsp/res/activity/img/star_gray.png'/>);
            margin-left: 15px;
        }
        .over{
            height:16px;
            background:url(<s:url value='/jsp/res/activity/img/star_org.png'/>) no-repeat;
        }
    </style>
    <script>

        $(document).ready(function(){

            var n="报名学员（${fn:length(results)+0}）";
            $("#regiestA").html(n);
        });
        function exportRegiestList(){
            var url = "<s:url value='/res/activityQuery/exportRegiestList'/>?activityFlow=${param.activityFlow}";
            jboxExp(null, url);
            jboxEndLoading();
        }

    </script>
</head>
<body>
<div class="mainright">
    <div class="">
        <div>
        <form id="addForm">

            <table class="xllist" style="width:100%; margin-bottom: 10px;">
                <tr>
                    <td width="100%" style="text-align: right;">
                        <input type="button" onclick="exportRegiestList();" class="search" value="导出报名学员"/>
                    </td>
                </tr>
            </table>
            <table class="xllist" style="width:100%; margin-bottom: 10px; margin-top: 10px;">
                <tr>
                    <td style="text-align: center;width:15%">姓名</td>
                    <td style="text-align: center;width:15%">年级</td>
                    <td style="text-align: center;width:50%">专业</td>
                    <td style="text-align: center;width:20% ">报名时间</td>
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
                            <c:set var="tj" value="${applicationScope.sysCfgMap['sys_index_url'] eq '/inx/jszy'}"/>
                            <c:if test="${tj}">${r.doctorCategoryName}</c:if>
                            <c:if test="${!tj}">${r.speName}</c:if>
                        </th>
                        <th style="text-align: center;">
                             ${r.regiestTime1}
                        </th>
                    </tr>
                </c:forEach>
                <c:if test="${empty results}">
                    <tr>
                        <td colspan="4">暂无报名学员</td>
                    </tr>
                </c:if>

            </table>
        </form>
        </div>
    </div>

</div>
</body>
</html>
