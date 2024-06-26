<%@include file="/jsp/common/doctype.jsp" %>
<c:if test="${empty view}">
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
        <jsp:param name="ueditor" value="true"/>
    </jsp:include>
</c:if>
<html>
<head>
<style type="text/css">

</style>
    <script type="text/javascript">
        function expertFrom(flow) {
            var url='<s:url value="/osca/provincial/exportFrom?fromFlow="/>'+flow;
            jboxTip("导出中…………");
            jboxExp($("#searchForm"), url);
        }
    </script>
</head>
<body>
<div>
    <input class="search" type="button"  value="导&#12288;出" style="margin-bottom: 10px;margin-left: 0px" onclick="expertFrom('00000001copy');"/>
<table  class="basic" width="100%" style="margin-top: 10px;">
    <tr>
        <th colspan="5" style="text-align: center"><h2 style="font-size:150%">回答问题评分表</h2>
        </th>
    </tr>
    <%--<tr>--%>
        <%--<th>考生姓名</th>--%>
        <%--<td><input name="doctorName" type="text" value="${dataMap['doctorName']}"></td>--%>
        <%--<th>准考证号</th>--%>
        <%--<td colspan="2"><input name="tickNum" value="${dataMap['tickNum']}" type="text"></td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
        <%--<th>考试科目</th>--%>
        <%--<td><input name="examinationSubject" value="${dataMap['examinationSubject']}" type="text"></td>--%>
        <%--<th>考试阶段</th>--%>
        <%--<td colspan="2">--%>
            <%--<label><input name="examinationPeriod" type="radio" value="firstPeriod"--%>
            <%--<c:if test="${dataMap['examinationPeriod'] eq 'firstPeriod'}">checked="checked"</c:if>>第一阶段</label>--%>
            <%--<label><input name="examinationPeriod" type="radio" value="secondPeriod"--%>
            <%--<c:if test="${dataMap['examinationPeriod'] eq 'secondPeriod'}">checked="checked"</c:if>>第二阶段</label>--%>
        <%--</td>--%>
    <%--</tr>--%>
    <tr>
        <th rowspan="2" style="text-align: center;">评分项目</th>
        <th rowspan="2" style="text-align: center;">评分要素</th>
        <th colspan="2" style="text-align: center;">标准分</th>
        <th rowspan="2" style="text-align: center;width: 10%">得分</th>
    </tr>
    <tr>
        <th style="text-align: center;">手术科室</th>
        <th style="text-align: center;">非手术科室</th>
    </tr>
    <tr>
        <th style="text-align: center;">诊断与依据</th>
        <th style="text-align: center;">诊断明确，依据充分</th>
        <th style="text-align: center;">20</th>
        <th style="text-align: center;">20</th>
        <td style="text-align: center;">
            <c:if test="${view eq 'Y'}">${dataMap['score1']}</c:if>
            <c:if test="${empty view}"><input type="text" name="score1" value="${dataMap['score1']}"></c:if>
        </td>
    </tr>
    <tr>
        <th style="text-align: center;">鉴别诊断与分析</th>
        <th style="text-align: center;">鉴别诊断准确，分析得当</th>
        <th style="text-align: center;">15</th>
        <th style="text-align: center;">20</th>
        <td style="text-align: center;">
            <c:if test="${view eq 'Y'}">${dataMap['score2']}</c:if>
            <c:if test="${empty view}"><input type="text" name="score2" value="${dataMap['score2']}"></c:if>
        </td>
    </tr>
    <tr>
        <th style="text-align: center;">检查项目与原则</th>
        <th style="text-align: center;">检查项目得当</th>
        <th style="text-align: center;">15</th>
        <th style="text-align: center;">15</th>
        <td style="text-align: center;">
            <c:if test="${view eq 'Y'}">${dataMap['score3']}</c:if>
            <c:if test="${empty view}"><input type="text" name="score3" value="${dataMap['score3']}"></c:if>
        </td>
    </tr>
    <tr>
        <th style="text-align: center;">治疗原则与方案</th>
        <th style="text-align: center;">治疗方案明确有效</th>
        <th style="text-align: center;">20</th>
        <th style="text-align: center;">20</th>
        <td style="text-align: center;">
            <c:if test="${view eq 'Y'}">${dataMap['score4']}</c:if>
            <c:if test="${empty view}"><input type="text" name="score4" value="${dataMap['score4']}"></c:if>
        </td>
    </tr>
    <tr>
        <th style="text-align: center;">预后判断与分析</th>
        <th style="text-align: center;">预后判断准确</th>
        <th style="text-align: center;">20</th>
        <th style="text-align: center;">10</th>
        <td style="text-align: center;">
            <c:if test="${view eq 'Y'}">${dataMap['score5']}</c:if>
            <c:if test="${empty view}"><input type="text" name="score5" value="${dataMap['score5']}"></c:if>
        </td>
    </tr>
    <tr>
        <th style="text-align: center;">思路与逻辑性</th>
        <th style="text-align: center;">思路敏捷，逻辑性强</th>
        <th style="text-align: center;">10</th>
        <th style="text-align: center;">15</th>
        <td style="text-align: center;">
            <c:if test="${view eq 'Y'}">${dataMap['score6']}</c:if>
            <c:if test="${empty view}"><input type="text" name="score6" value="${dataMap['score6']}"></c:if>
        </td>
    </tr>
    <tr>
        <th style="text-align: center;">总分</th>
        <td style="text-align: center;"></td>
        <th style="text-align: center;">100</th>
        <th style="text-align: center;">100</th>
        <td style="text-align: center;">
            <c:if test="${view eq 'Y'}">${dataMap['scoreSum']}</c:if>
            <c:if test="${empty view}"><input type="text" name="scoreSum" value="${dataMap['scoreSum']}"></c:if>
        </td>
    </tr>
</table>
</div>
<div style="${empty view?'':'display:none'}">
（注：折合成20分后计入第二部分总分）
</div>
</body>
</html>
