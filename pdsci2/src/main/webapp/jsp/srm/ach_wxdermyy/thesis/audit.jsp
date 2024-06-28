<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
    <script type="text/javascript">

        function saveAudit(agreeFlag, thesisFlow,tip) {
           // var tip = agreeFlag == "${GlobalConstant.FLAG_Y}" ? "审核通过" : "退回";
            var url = "<s:url value='/srm/ach/thesis/saveAudit'/>?agreeFlag=" + agreeFlag + "&thesisFlow=" + thesisFlow;
            jboxConfirm("确认" + tip + "?", function () {
                if ((tip=="审核通过")&&(!saveThesis(agreeFlag))) {
                    return false;
                } else {
                    jboxStartLoading();
                    jboxPostAsync(url, $('#auditForm').serialize(), function (resp) {
                        $("#searchForm", window.parent.frames['mainIframe'].document).submit();
                        window.parent.frames['mainIframe'].window.search();
                        jboxClose();
                    }, null, true);
                }
            });
        }
        function updateEdit(agreeFlag,tip) {
            jboxConfirm("确认" + tip + "?", function () {
                if (saveThesis(agreeFlag)) {
                    jboxTip("保存成功");
                    setTimeout("window.parent.frames['mainIframe'].location.reload(true);", 2000);
                }
            }, null,true);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <jsp:include page="edit.jsp" flush="true">
            <jsp:param name="auditFlag" value="audit"/>
        </jsp:include>
        <c:if test="${param.editFlag != GlobalConstant.FLAG_N}">
        <c:if test="${thesis.operStatusId ne achStatusEnumFirstAudit.id}">
            <h2>审核意见：</h2>
            <hr/>
            <form method="post" id="auditForm">
                <div style="text-align: center;">
                <textarea id="auditContent" name="auditContent"
                          <c:if test="${param.editFlag == GlobalConstant.FLAG_N}">readonly="readonly"</c:if>
                          style="width: 100%" rows="7"></textarea><br/><br/><br/>

                    <input class='search' onclick="saveAudit('${GlobalConstant.FLAG_Y}','${thesis.thesisFlow }','审核通过')"
                           type='button' value='同&#12288;意'/>&#12288;
                    <input class='search' onclick="saveAudit('${GlobalConstant.FLAG_N}','${thesis.thesisFlow }','退回')"
                           type='button' value='退&#12288;回'/>&#12288;

                </div>
            </form>
        </c:if>

        <c:if test="${thesis.operStatusId eq achStatusEnumFirstAudit.id}">
            <div align="center">
                <input class='search' onclick="updateEdit('${GlobalConstant.FLAG_Y}','保存')" type='button'
                       value='保&#12288;存'/>&#12288;
                <input class='search' onclick="jboxClose();" type='button'
                       value='关&#12288;闭'/>&#12288;
            </div>
        </c:if>
        </c:if>
    </div>
</div>
</body>
</html>