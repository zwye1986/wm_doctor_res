<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/common/htmlhead.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>

<div class="main_hd">


    <div class="mainright" style="margin-top: 20px">
        <div class="content">
            <c:forEach items="${doctorList}" var="doctor">
                <table class="xllist" style="width: 60%">
                    <tr>
                        <th colspan="2" style="text-align: center">实习理论考试查询</th>
                    </tr>
                    <tr>
                        <th style="width: 40%">10月（第一轮）：</th>
                        <td>${doctor.resScore.octScore == 0?"":doctor.resScore.octScore}</td>
                    </tr>
                    <tr>
                        <th style="width: 40%">3月（第二轮）：</th>
                        <td>${doctor.resScore.marScore == 0?"":doctor.resScore.marScore}</td>
                    </tr>
                    <tr>
                        <th style="width: 40%">6月（第三轮）：</th>
                        <td>${doctor.resScore.junScore == 0?"":doctor.resScore.junScore}</td>
                    </tr>
                    <tr>
                        <th style="width: 40%">平均分：</th>
                        <td>${doctor.avgScore == 0?"":doctor.avgScore}</td>
                    </tr>
                </table>
                <%--<tr>--%>
                <%--<td>${doctor.sysUser.userName}</td>--%>
                <%--<td>${doctor.sysUser.sexName}</td>--%>
                <%--<td>${doctor.sysUser.userCode}</td>--%>
                <%--<td>${doctor.orgName}</td>--%>
                <%--<td>${doctor.sessionNumber}</td>--%>
                <%--</tr>--%>
            </c:forEach>

            <c:if test="${empty doctorList}">
                <span style="font-size: x-large; font-weight: 500;margin-left: 20px;">无记录</span>
            </c:if>
        </div>
    </div>
</html>