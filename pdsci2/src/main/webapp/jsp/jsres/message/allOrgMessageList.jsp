<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>江苏省住院医师规范化培训管理平台</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>

</jsp:include>
</head>
<table cellpadding="0" cellspacing="0" class="xllist" style="width: 100%;margin-left: 10px">
     <colgroup>
         <col width="20%"/>
         <col width="20%"/>
         <col width="20%"/>
         <col width="20%"/>
         <col width="20%"/>
     </colgroup>
     <tr style="height: 60px">
         <c:forEach items="${orgs}" var="s"  varStatus="status">
         <c:if test="${(status.index+1)%5==0}">
         <th style="text-align: left">
             <div style="height: 40px;width: 90%;background-color: #F6F6F6;text-align: center;line-height: 40px">
                 <c:if test="${empty messageMap[s.orgFlow]}">
                     ${s.orgName}
                 </c:if>
                 <c:if test="${not empty messageMap[s.orgFlow]}">
                     <strong>
                         <a style="font-weight: bold;color: blue" href="<s:url value='/inx/jsres/messageViewByOrg'/>?orgFlow=${s.orgFlow}&sessionNumber=${sessionNumber}" target="_blank">${s.orgName}</a>
                     </strong>
                 </c:if>
                 <c:if test="${not empty newMap[s.orgFlow]}">
                     <i class="new1"></i>
                 </c:if>
             </div>
         </th>
     </tr><tr style="height: 60px">
     </c:if>
     <c:if test="${(status.index+1)%5!=0}">
         <th style="text-align: left">
             <div style="height: 40px;width: 90%;background-color: #F6F6F6;text-align: center;line-height: 40px">
                 <c:if test="${empty messageMap[s.orgFlow]}">
                     ${s.orgName}
                 </c:if>
                 <c:if test="${not empty messageMap[s.orgFlow]}">
                     <strong>
                         <a style="font-weight: bold;color: blue" href="<s:url value='/inx/jsres/messageViewByOrg'/>?orgFlow=${s.orgFlow}&sessionNumber=${sessionNumber}" target="_blank">${s.orgName}</a>
                     </strong>
                 </c:if>
                 <c:if test="${not empty newMap[s.orgFlow]}">
                     <i class="new1"></i>
                 </c:if>
             </div>
         </th>
     </c:if>
     </c:forEach>
 </tr>
 </table>

