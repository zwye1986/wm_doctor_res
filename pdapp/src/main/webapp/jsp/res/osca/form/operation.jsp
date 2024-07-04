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

</head>
<body>
<div>
    <table  class="basic" width="100%" style="margin-top: 10px;">
        <tr>
            <th colspan="4"><h2 style="font-size:150%;text-align: center">手术操作考核成绩评定表</h2></th>
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
            <td>
                <label><input name="examinationPeriod" type="radio" value="firstPeriod"
                              <c:if test="${dataMap['examinationPeriod'] eq 'firstPeriod'}">checked="checked"</c:if>>第一阶段</label>
                <label><input name="examinationPeriod" type="radio" value="secondPeriod"
                              <c:if test="${dataMap['examinationPeriod'] eq 'secondPeriod'}">checked="checked"</c:if>>第二阶段</label>
            </td>
        </tr>
        <tr>
            <th>评分项目</th>
            <th>评分要素</th>
            <th>满分</th>
            <th style="width: 15%">得分</th>
        </tr>
        <tr>
            <th rowspan="7">手术操作<br/>(名称：<input type="text">)</th>
            <th>无菌操作严密</th>
            <th>15</th>
            <td><input type="text" name="score1" value="${dataMap['score1']}"></td>
        </tr>
        <tr>
            <th>基本操作规范</th>
            <th>15</th>
            <td><input type="text" name="score2" value="${dataMap['score2']}"></td>
        </tr>
        <tr>
            <th>手术技巧熟练</th>
            <th>15</th>
            <td><input type="text" name="score3" value="${dataMap['score3']}"></td>
        </tr>
        <tr>
            <th>解剖层次清晰</th>
            <th>15</th>
            <td><input type="text" name="score4" value="${dataMap['score4']}"></td>
        </tr>
        <tr>
            <th>术野显露清楚</th>
            <th>10</th>
            <td><input type="text" name="score5" value="${dataMap['score5']}"></td>
        </tr>
        <tr>
            <th>器械使用合理</th>
            <th>10</th>
            <td><input type="text" name="score6" value="${dataMap['score6']}"></td>
        </tr>
        <tr>
            <th>应变能力敏捷</th>
            <th>10</th>
            <td><input type="text" name="score7" value="${dataMap['score7']}"></td>
        </tr>
        <tr>
            <th>辅助检查</th>
            <th>基本操作规范，无菌观念强，技术熟练</th>
            <th>10</th>
            <td><input type="text" name="score8" value="${dataMap['score8']}"></td>
        </tr>
        <tr>
            <th>总分</th>
            <td></td>
            <th>100</th>
            <td><input type="text" name="scoreSum" value="${dataMap['scoreSum']}"></td>
        </tr>
    </table>
</div>
<div>
    备注：请注明手术操作名称。
</div>
</body>
</html>
