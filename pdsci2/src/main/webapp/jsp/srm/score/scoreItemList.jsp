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
        <jsp:param name="jquery_ui_combobox" value="false"/>
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
        function achList(scoreFlow,flow) {
            var url = "<s:url value='/srm/ach/score/achScoreList'/>?isPublish=${isPublish}&flow="+flow+"&scoreFlow="+scoreFlow+"&typeId=${typeId}"+"&startYear=${startYear}&endYear=${endYear}";
            var dg = dialog({
                id: 'openDialog',
                fixed: true,
                padding: 5,
                title: "积分详情",
                url: url,
                width: 400,
                height: 350,
                cancelDisplay: false,
                cancelValue: '关闭',
                backdropOpacity: 0.1,
            });
            dg.showModal();
        }
    </script>
    <style type="text/css">
        a:link{color: #0000ff;}
        a:hover{color: #FF6615;}
    </style>
</head>
<body>
<div class="mainright">
    <div class="content">
        <table class="xllist" id="deptData">
            <tr>
                <th width="34%">积分项</th>
                <th width="22%">数量</th>
                <th width="22%">分值</th>
                <th width="22%">总分</th>
            </tr>
            <c:forEach items="${scoreItemList}" var="score" varStatus="status">
                <tr>
                    <td>${score.SCORENAME}</td>
                    <td><a href="javascript:void(0)"
                           onclick="achList('${score.SCOREFLOW}','${flow}')">${score.AMOUNT}</a></td>
                    <c:choose>
                        <c:when test="${(isPublish eq 'isPublish') or (isPublish eq 'publish') or (isPublish eq 'fixedFlag')}">
                            <td>${score.SCOREOLDVALUE} 分</td>
                        </c:when>
                        <c:otherwise>
                            <td>${score.SCOREVALUE} 分</td>
                        </c:otherwise>
                    </c:choose>
                    <td>${score.AMOUNTACHSCORE}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>