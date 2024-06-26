<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
    </jsp:include>
    <style type="text/css">
        .boxHome .item:HOVER{background-color: #eee;}
        .cur{color:red}
    </style>
    <script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">

    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="questionDetailForm" >
            <table class="basic" style="width: 100%;margin: 10px 0px;">
                <tr>
                    <td style="text-align: center;"><span style="font-size: 16px;font-weight:600;">${courseQuestion.questionName}</span></td>
                </tr>
                <c:forEach items="${dataList}" var="data">
                    <tr>
                        <td style="text-align: left;font-weight:600;">
                            &ensp;${data.questionDetail.serial}、${data.questionDetail.questionTitle}
                        </td>
                    </tr>
                    <tr id="answerTr" questionType="${data.questionDetail.questionTypeId}" questionSerial="${data.questionDetail.serial}">
                        <td style="text-align: left;">
                            <c:if test="${data.questionDetail.questionTypeId eq 'Radio'}">
                                &ensp;<c:forEach items="${data.answerDetailList}" var="answer">
                                <c:set value="${data.questionDetail.serial}${','}${answer.serial}" var="key"/>
                                <input type="radio" value="${answer.serial}" <c:if test="${answerDetailMap[key]>0}">checked="checked"</c:if>  disabled="disabled"/>&ensp;${answer.answerName}&ensp;&ensp;
                            </c:forEach>
                            </c:if>
                            <c:if test="${data.questionDetail.questionTypeId eq 'Multiselect'}">
                                &ensp;<c:forEach items="${data.answerDetailList}" var="answer">
                                <c:set value="${data.questionDetail.serial}${','}${answer.serial}" var="key"/>
                                <input type="checkbox" value="${answer.serial}" <c:if test="${answerDetailMap[key]>0}">checked="checked"</c:if>  disabled="disabled">&nbsp;${answer.answerName}&ensp;&ensp;
                            </c:forEach>
                            </c:if>
                            <c:if test="${data.questionDetail.questionTypeId eq 'Subjective'}">
                                <c:set value="${data.questionDetail.serial}" var="key"/>
                                &ensp;${answerDetailMap[key]}
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </div>
    <div style="text-align: center;">
        <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
    </div>
</div>
</body>
</html>