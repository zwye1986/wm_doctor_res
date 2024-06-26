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
            <c:if test="${(not empty sessionScope.hbRoleFlow) && sessionScope.hbRoleFlow ne applicationScope.sysCfgMap['res_doctor_role_flow']}">
            <li>
                <a href="<s:url value='${sessionScope.hbUrlPath}'/>" target="_top">
                    <img src="<s:url value='/css/skin/${skinPath}/images/gzykdx.png' />" width="48"/>
                    住院医师招录管理系统
                </a>
            </li>
            </c:if>
            <c:if test="${pdfn:hasSchRole(sessionScope.currUser.userFlow)}">
                <li>
                    <a href="<s:url value='/hbres/singup/login'/>?wsId=sch&userFlow=${sessionScope.currUser.userFlow}" target="_top">
                        <img src="<s:url value='/css/skin/${skinPath}/images/sch.png' />" width="48"/>
                        医院管理排班系统
                    </a>
                </li>
            </c:if>
            <%--省级部门和医院管理员等（除了学员角色且未开通过程） 可进入过程--%>
            <c:if test="${(not empty sessionScope.hbRoleFlow) && !(sessionScope.hbRoleFlow eq applicationScope.sysCfgMap['res_doctor_role_flow'] && !empty hbJyUrlPath)}">
                <li>
                    <a href="<s:url value='/hbres/singup/login'/>?wsId=res&userFlow=${sessionScope.currUser.userFlow}" target="_top">
                        <img src="<s:url value='/css/skin/${skinPath}/images/res.png' />" width="48"/>
                        住院医师过程管理系统
                    </a>
                </li>
            </c:if>
            <%--临时逻辑：只有五家试点医院学员才能进入结业系统。--%>
            <c:set var="flag" value="Y"></c:set>
            <c:if test="${ sessionScope.currUser.orgFlow ne '4207' &&
                                sessionScope.currUser.orgFlow ne '4228' &&
                                sessionScope.currUser.orgFlow ne '4232' &&
                                sessionScope.currUser.orgFlow ne '4237' &&
                                sessionScope.currUser.orgFlow ne '4243' &&
                                sessionScope.currUser.orgFlow ne '6515bed589b141ceb06a94fbf9576739' &&
                                sessionScope.hbRoleFlow eq applicationScope.sysCfgMap['res_doctor_role_flow']
                }">
                <c:set var="flag" value="N"></c:set>
            </c:if>
            <c:if test="${not empty sessionScope.hbRoleFlow && flag eq 'Y'}">
            <li>
                <c:choose>
                    <c:when test="${sessionScope.hbRoleFlow eq applicationScope.sysCfgMap['res_global_role_flow']}">
                        <c:set var="hbJyUrlPath" value="/hbres/singup/graduate/manage/global"/>
                    </c:when>
                    <c:when test="${sessionScope.hbRoleFlow eq applicationScope.sysCfgMap['res_charge_role_flow']}">
                        <c:set var="hbJyUrlPath" value="/hbres/singup/graduate/manage/charge"/>
                    </c:when>
                    <c:when test="${sessionScope.hbRoleFlow eq applicationScope.sysCfgMap['res_doctor_role_flow']}">
                        <c:set var="hbJyUrlPath" value="/hbres/singup/graduate/doctor"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="hbJyUrlPath" value="/hbres/singup/graduate/manage/local"/>
                    </c:otherwise>
                </c:choose>
                <a href="<s:url value='${hbJyUrlPath}'/>" target="_top">
                    <img src="<s:url value='/css/skin/${skinPath}/images/jieye.png' />" width="48"/>
                    住院医师结业管理系统
                </a>
            </li>
            </c:if>
            <c:if test="${not empty portalRole}">
                <li>
                    <a href="<s:url value='/main/portals/'/>?hbPortals=Y" target="_top">
                        <img src="<s:url value='/css/skin/${skinPath}/images/portals.png' />" width="48"/>
                        门户网站维护平台
                    </a>
                </li>
            </c:if>
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