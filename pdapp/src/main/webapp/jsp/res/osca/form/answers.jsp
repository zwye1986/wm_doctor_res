<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
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
<html>
<head>
<style type="text/css">
</style>
</head>
<body>
<div>
<table  class="basic" width="100%" style="margin-top: 10px;">
    <tr>
        <th colspan="5" style="text-align: center"><h2 style="font-size:150%">回答问题评分表</h2>
        </th>
    </tr>
    <tr>
        <th>考生姓名</th>
        <td><input name="doctorName" type="text" value="${dataMap['doctorName']}"></td>
        <th>准考证号</th>
        <td colspan="2"><input name="tickNum" value="${dataMap['tickNum']}" type="text"></td>
    </tr>
    <tr>
        <th>考试科目</th>
        <td><input name="examinationSubject" value="${dataMap['examinationSubject']}" type="text"></td>
        <th>考试阶段</th>
        <td colspan="2">
            <label><input name="examinationPeriod" type="radio" value="firstPeriod"
            <c:if test="${dataMap['examinationPeriod'] eq 'firstPeriod'}">checked="checked"</c:if>>第一阶段</label>
            <label><input name="examinationPeriod" type="radio" value="secondPeriod"
            <c:if test="${dataMap['examinationPeriod'] eq 'secondPeriod'}">checked="checked"</c:if>>第二阶段</label>
        </td>
    </tr>
    <tr>
        <th rowspan="2">评分项目</th>
        <th rowspan="2">评分要素</th>
        <th colspan="2">标准分</th>
        <th rowspan="2" style="text-align: center;width: 10%">得分</th>
    </tr>
    <tr>
        <th>手术科室</th>
        <th>非手术科室</th>
    </tr>
    <tr>
        <th>诊断与依据</th>
        <th>诊断明确，依据充分</th>
        <th>20</th>
        <th>20</th>
        <td><input type="text" name="score1" value="${dataMap['score1']}"></td>
    </tr>
    <tr>
        <th>鉴别诊断与分析</th>
        <th>鉴别诊断准确，分析得当</th>
        <th>15</th>
        <th>20</th>
        <td><input type="text" name="score2" value="${dataMap['score2']}"></td>
    </tr>
    <tr>
        <th>检查项目与原则</th>
        <th>检查项目得当</th>
        <th>15</th>
        <th>15</th>
        <td><input type="text" name="score3" value="${dataMap['score3']}"></td>
    </tr>
    <tr>
        <th>治疗原则与方案</th>
        <th>治疗方案明确有效</th>
        <th>20</th>
        <th>20</th>
        <td><input type="text" name="score4" value="${dataMap['score4']}"></td>
    </tr>
    <tr>
        <th>预后判断与分析</th>
        <th>预后判断准确</th>
        <th>20</th>
        <th>10</th>
        <td><input type="text" name="score5" value="${dataMap['score5']}"></td>
    </tr>
    <tr>
        <th>思路与逻辑性</th>
        <th>思路敏捷，逻辑性强</th>
        <th>10</th>
        <th>15</th>
        <td><input type="text" name="score6" value="${dataMap['score6']}"></td>
    </tr>
    <tr>
        <th>总分</th>
        <td></td>
        <th>100</th>
        <th>100</th>
        <td><input type="text" name="scoreSum" value="${dataMap['scoreSum']}"></td>
    </tr>
</table>
</div>
<div>
    （注：折合成20分后计入第二部分总分）
</div>
</body>
</html>
