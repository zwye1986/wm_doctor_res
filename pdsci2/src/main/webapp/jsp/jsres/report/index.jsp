
<%--<%@ page language="java" contentType="text/html; charset=UTF-8"--%>
<%--pageEncoding="UTF-8"%>--%>
<%--<%@include file="/jsp/common/doctype.jsp" %>--%>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_ui_combobox" value="false"/>
    <jsp:param name="jquery_ui_sortable" value="false"/>
    <jsp:param name="jquery_ui_tooltip" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
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
<html>
<link href="<s:url value='/css/form.css'/>" rel="stylesheet" type="text/css">
<head>
    <style type="text/css">
        .boxHome .item:HOVER{background-color: #eee;}
        .cur{color:red;}
        .searchTable{
            width: auto;
        }
        .searchTable td{
            width: auto;
            height: auto;
            line-height: auto;
            text-align: left;
            max-width: 150px;;
        }
        .searchTable .td_left{
            word-wrap:break-word;
            width:5em;
            height: auto;
            line-height: auto;
            text-align: right;
        }
    </style>
    <script type="text/javascript">

    </script>
</head>

<body>
<%--<h1>测试</h1>--%>
<iframe  frameborder="0" style="min-height: calc(100vh); width: 100%;"  src="${url}" id="${pageId}"></iframe>

</body>
</html>

