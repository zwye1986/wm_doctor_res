<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
    <div class="div_table" id="divTable" style="height: 580px; overflow-y: auto;">
        <form id="editForm" method="post">
            <table class="grid">
                <tr>
                    <th style="text-align: right;">姓名：</th>
                    <td style="text-align: left;">
                       ${doctor.doctorName}
                    </td>
                    <th style="text-align: right;">年级：</th>
                    <td style="text-align: left;">
                        ${doctor.sessionNumber}
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">手机号：</th>
                    <td style="text-align: left;">
                        ${sysUser.userPhone}
                    </td>
                    <th style="text-align: right;">证件号：</th>
                    <td style="text-align: left;">
                        ${sysUser.idNo}
                    </td>
                </tr>
            </table>
            <table border="0" cellpadding="0" cellspacing="0" class="grid" id="gridTable">
                <tr>
                    <th style="text-align: center;">科室</th>
                    <th style="text-align: center;">入科时间</th>
                    <th style="text-align: center;">出科时间</th>
                    <th style="text-align: center;">状态</th>
                </tr>
                <c:forEach items="${resDoctorSchProcesses}" var="rds">
                    <tr>
                        <td style="text-align: center;width: 180px;">${rds.schDeptName}</td>
                        <td style="text-align: center;width: 120px;">${rds.schStartDate}</td>
                        <td style="text-align: center;width: 80px;">${rds.outDate}</td>
                        <td style="text-align: center;width: 80px;">已超时${rds.monthDate}天</td>
                    </tr>
                </c:forEach>
                <c:if test="${empty resDoctorSchProcesses}">
                    <tr>
                        <td colspan="9">无记录</td>
                    </tr>
                </c:if>
            </table>
        </form>
    </div>