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

    </script>
</head>

<body>
<c:set var="pageSize" value="5"/>
<div id="register">
    <div id="logo"><img src="<s:url value='/css/skin/${skinPath}/images/${applicationScope.sysCfgMap["sys_login_img"]}_head.png'/>" /></div>
</div>
<div class="mainright">
    <div style="height:80px;">&nbsp;</div>
    <div id="station">
        <c:set var="currentPage" value="1"/>
        <div class="wsdiv">
            <ul>
                <li>
                    <a href="<s:url value='${sessionScope.hbUrlPath}'/>" target="_top">
                        <img src="<s:url value='/css/skin/${skinPath}/images/gzykdx.png' />" width="48"/>
                        住院医师招录管理系统
                    </a>
                </li>
                <li>
                    <a href="<s:url value='/hbres/singup/login'/>?userFlow=${sessionScope.currUser.userFlow}" target="_top">
                        <img src="<s:url value='/css/skin/${skinPath}/images/res.png' />" width="48"/>
                        住院医师过程管理系统
                    </a>
                </li>
            </ul>
            <c:set var="currentPage" value="${currentPage+1 }"/>
        </div>
    </div>
    <div id="footer">技术支持：南京品德信息技术有限公司<br />Copyright © 2001- 2014 Character Technology, Inc. All rights reserved.</div>
</div>
</body>
</html>