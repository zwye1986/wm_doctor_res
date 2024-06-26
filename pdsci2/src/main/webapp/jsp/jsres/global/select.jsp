<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <script>
        function nextPage(pagenum){
            var currentDiv = $(".wsdiv:visible");
            if(pagenum=="-1"){
                if(currentDiv.prev("div").hasClass("wsdiv")){
                    currentDiv.hide();
                    currentDiv.prev(".wsdiv").show();
                }
            }else if(pagenum=="1"){
                if(currentDiv.next("div").hasClass("wsdiv")){
                    currentDiv.hide();
                    currentDiv.next(".wsdiv").show();
                }
            }
        }

        function toMain(){

        }
    </script>
</head>

<body>
<c:set var="viewUrl" value="" scope="session"/>
<c:set var="pageSize" value="5"/>
<div id="station">
    <c:set var="currentPage" value="1"/>
    <div class="wsdiv">
        <ul>
            <li>
                <a href="<s:url value='/jsres/manage/global'/>" target="_top">
                    <img src="<s:url value='/css/skin/${skinPath}/images/res.png' />" width="48"/>
                    住院医师规范化培训管理平台
                </a>
            </li>
            <li>
                <a href="<s:url value='/jsres/manage/globalToEval'/>" target="_top">
                    <img src="<s:url value='/css/skin/${skinPath}/images/eval.png' />" width="48"/>
                    基地评估管理系统
                </a>
            </li>
        </ul>
        <c:set var="currentPage" value="${currentPage+1 }"/>
    </div>
    <%--<div class="fy-btn">--%>
        <%--<c:if test="${fn:length(currUserWorkStationList)>5 }">--%>
            <%--<a href="javascript:nextPage('-1');"><img src="<s:url value='/css/skin/${skinPath}/images/pre.png' />"/></a>--%>
            <%--<a href="javascript:nextPage('1');"><img src="<s:url value='/css/skin/${skinPath}/images/next.png' />"/></a>--%>
        <%--</c:if>--%>
    <%--</div>--%>
</div>
</body>
</html>