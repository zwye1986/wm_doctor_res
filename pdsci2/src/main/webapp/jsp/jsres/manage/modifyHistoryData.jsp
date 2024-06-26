<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function closeWindow() {
            window.parent.searchRecruitHistory();
            jboxClose();
        }

        function save() {
            var remarks = "";
            if (${docType eq 'doctorTraining'}) {
                remarks = $("#remarks").val();
            }
            if (${docType eq 'graduate'}) {
                remarks = $("#graduate_remarks").val();
            }
            if (remarks === "") {
                jboxTip("请填写备注信息！");
                return;
            }
            jboxStartLoading();
            var url = "<s:url value='/jsres/manage/saveHistoryReport'/>";
            var data = $('#modifyHistoryData').serialize();
            jboxPost(url, data, function (resp) {
                if ('${GlobalConstant.OPERATE_SUCCESSED}' == resp) {
                    setTimeout(function () {
                        var url = "<s:url value='/jsres/manage/recruitHistoryReport'/>";
                        window.parent.jboxPostLoad("history_load", url, null, true);
                        jboxClose();
                        jboxEndLoading();
                    }, 1000);
                }
            }, null, true);
        }
    </script>
</head>
<body>
<div class="infoAudit">
    <form id="modifyHistoryData" style="position:relative;height: 300px;">
        <input type="hidden" name="historyFlow" value="${recruitHistory.historyFlow}">
        <input type="hidden" name="orgFlow" value="${recruitHistory.orgFlow}">
        <input type="hidden" name="orgName" value="${recruitHistory.orgName}">
        <input type="hidden" name="speId" value="${recruitHistory.speId}">
        <input type="hidden" name="speName" value="${recruitHistory.speName}">
        <input type="hidden" name="photoTime" value="${recruitHistory.photoTime}">
        <c:if test="${docType eq 'doctorTraining'}">
            <input type="hidden" name="graduateNumber" value="${recruitHistory.graduateNumber}">
        </c:if>
        <c:if test="${docType eq 'graduate'}">
            <input type="hidden" name="companyNumber" value="${recruitHistory.companyNumber}">
            <input type="hidden" name="companyEntrustNumber" value="${recruitHistory.companyEntrustNumber}">
            <input type="hidden" name="socialNumber" value="${recruitHistory.socialNumber}">
        </c:if>
        <table border="0" cellpadding="0" cellspacing="0" class="base_info" style="margin-top: 20px;">
            <c:if test="${docType eq 'doctorTraining'}">
                <tr>
                    <th style="width: 40%;">本单位人：</th>
                    <td>
                        <input class="input" name="companyNumber" id="companyNumber" style="width: 95%;margin-left: 0px;"
                               class="validate[required]" value="${recruitHistory.companyNumber}"/>
                    </td>
                </tr>
                <tr>
                    <th>委培单位人：</th>
                    <td>
                        <input class="input" name="companyEntrustNumber" id="companyEntrustNumber" style="width: 95%;margin-left: 0px;"
                               class="validate[required]" value="${recruitHistory.companyEntrustNumber}"/>
                    </td>
                </tr>
                <tr>
                    <th>社会人：</th>
                    <td>
                        <input class="input" name="socialNumber" id="socialNumber" style="width: 95%;margin-left: 0px;"
                               class="validate[required]" value="${recruitHistory.socialNumber}"/>
                    </td>
                </tr>
                <tr>
                    <th>备注：</th>
                    <td style="height: 150px">
                        <textarea class="input" name="remarks" id="remarks" style="width: 95%;height:90%;margin-left: 0px;"
                                  class="validate[required]">${recruitHistory.remarks}</textarea>
                    </td>
                </tr>
            </c:if>
            <c:if test="${docType eq 'graduate'}">
                <tr>
                    <th style="width: 40%;">在校专硕：</th>
                    <td>
                        <input class="input" name="graduateNumber" id="graduateNumber" style="width: 95%;margin-left: 0px;"
                               class="validate[required]" value="${recruitHistory.graduateNumber}"/>
                    </td>
                </tr>
                <tr>
                    <th>备注：</th>
                    <td style="height: 150px">
                        <textarea class="input" name="remarks" id="graduate_remarks" style="width: 95%;height:90%;margin-left: 0px;"
                                  class="validate[required]">${recruitHistory.remarks}</textarea>
                    </td>
                </tr>
            </c:if>
        </table>
    </form>
    <div class="button">
        <input type="button" class="btn_green" onclick="save();" value="保&#12288;存"/>
        <input type="button" class="btn_green" onclick="closeWindow();" value="关&#12288;闭"/>
    </div>
</div>
</body>
</html>