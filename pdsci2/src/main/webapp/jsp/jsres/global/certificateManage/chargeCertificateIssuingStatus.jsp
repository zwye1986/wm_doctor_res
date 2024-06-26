<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
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
    <style type="text/css">
        #boxHome .item:HOVER {
            background-color: #eee;
        }
    </style>
    <script type="text/javascript">

        function chargeStatus() {
            jboxConfirm("确认更改该学员的证书发放状态？", function () {
                var url = "<s:url value='/jsres/certificateManage/chargeStatus'/>?recruitFlow=" + '${param.recruitFlow}';
                jboxPost(url, $('#statusForm').serialize(), function (resp) {
                    if ("${GlobalConstant.UPDATE_SUCCESSED}" == resp) {
                        window.parent.toPage();
                        jboxTip(resp);
                        setTimeout(function () {
                            jboxClose();
                        }, 1000);
                    }
                }, null, false);
            });
        }
    </script>
</head>
<body>

<form id="statusForm" method="post">
    <table class="grid" style="width: 100%;margin-top: 50px;">
        <tr>
            <th style="text-align: right;width: 150px">证书发放状态</th>
            <td style="text-align: left;">
                <select name="certificateIssuingStatusId" id="certificateIssuingStatusId" class="select"
                        style="width: 200px">
                    <option id="a" value="Y" <c:if test="${param.flag eq 'Y'}">selected</c:if>>是</option>
                    <option id="b" value="N" <c:if test="${param.flag eq 'N'}">selected</c:if>>否</option>
                </select>
            </td>
        </tr>
    </table>
</form>
<div style="text-align: center; margin-top: 10px;">
    <input type="button" onclick="chargeStatus();" value="确&#12288;定" class="btn_green"/>
</div>
</body>
</html>