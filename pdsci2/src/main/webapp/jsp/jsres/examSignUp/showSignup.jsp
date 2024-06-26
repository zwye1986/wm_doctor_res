<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
    </jsp:include>
    <style type="text/css">
        td{text-align: left}
        textarea{width: 90%;height: 100px;max-height: 100px;max-width: 96%;margin: 5px 0;}
    </style>
    <script type="text/javascript">

    </script>
</head>
<body>
<div class="mainright">
    <div class="basic" style="max-height: 392px;overflow: auto;">
        <form id="addForm" style="position: relative;">

            <input hidden name="signupTypeId" value="${param.typeId}"/>
            <table class="grid" style="width:100%; margin-bottom: 10px; margin-top: 10px;">
                <tr>
                    <th style="text-align: right;width: 88px;">姓&#12288;&#12288;名：</th>
                    <td>
                        ${user.userName}
                    </td>
                    <th style="text-align: right;width: 88px;">申请年份：</th>
                    <td>
                        ${signup.signupYear}
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;width: 88px;">证件类型：</th>
                    <td>
                        ${user.cretTypeName}
                    </td>
                    <th style="text-align: right;width: 88px;">证件号码：</th>
                    <td>
                        ${user.idNo}
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;width: 88px;">培训年级：</th>
                    <td>
                        <input hidden name="sessionNumber" value="${doctor.sessionNumber}"/>
                        ${signup.sessionNumber}
                    </td>
                    <th style="text-align: right;width: 88px;">培养年限：</th>
                    <td>
                        <c:if test="${'OneYear' eq signup.trainingYears}">一年</c:if>
                        <c:if test="${'TwoYear' eq signup.trainingYears}">两年</c:if>
                        <c:if test="${'ThreeYear' eq signup.trainingYears}">三年</c:if>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;width: 88px;">培训基地：</th>
                    <td>
                        ${signup.orgName}
                    </td>
                    <th style="text-align: right;width: 88px;">培训类别：</th>
                    <td>
                        ${signup.trainingTypeName}
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;width: 88px;">培训专业：</th>
                    <td>
                        ${signup.trainingSpeName}
                    </td>
                    <th style="text-align: right;width: 88px;">报考专业：</th>
                    <td>
                        ${signup.changeSpeName}
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;width: 88px;">补考类型：</th>
                    <td>
                        <c:if test="${signup.signupTypeId eq 'Theory'}">理论补考</c:if>
                        <c:if test="${signup.signupTypeId eq 'Skill'}">技能补考</c:if>
                    </td>
                    <th style="text-align: right;width: 88px;">提交时间：</th>
                    <td >${pdfn:transDateTime(signup.createTime)}</td>
                </tr>
            </table>
        </form>
        <p style="text-align: center;">
            <input type="button" onclick="jboxClose();" class="btn_green" value="关&#12288;闭"/>
        </p>
    </div>
</div>
</body>
</html>
