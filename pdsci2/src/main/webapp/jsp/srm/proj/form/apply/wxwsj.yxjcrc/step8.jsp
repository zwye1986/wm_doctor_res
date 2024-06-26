<%@include file="/jsp/common/doctype.jsp" %>
<jsp:include page="/jsp/common/htmlhead.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_ui_tooltip" value="true"/>
    <jsp:param name="jquery_ui_combobox" value="true"/>
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
    function nextOpt(step) {
        if (false == $("#projForm").validationEngine("validate")) {
            return false;
        }
        var form = $('#projForm');
        var action = form.attr('action');
        action += "?nextPageName=" + step;
        form.attr("action", action);
        $('#prev').attr({"disabled": "disabled"});
        form.submit();
    }

</script>


<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" style="position: relative;"
      id="projForm">
    <input type="hidden" id="pageName" name="pageName" value="step8"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

    <table class="basic" style="width: 100%; margin-top: 10px;">
        <tr>
            <th style="text-align: left;padding-left: 20px;">九、审核意见</th>
        </tr>
        <tr>
            <td style="text-align: left;">&#12288;对本《申报书》中各项内容的真实性、经费预算的合理性签署具体意见</td>
        </tr>
        <tr>
            <td>
                <textarea name="deptOpinion" style="width:98%;height: 150px;">${resultMap.deptOpinion}</textarea>
            </td>
        </tr>
    </table>
    <c:if test="${param.view != GlobalConstant.FLAG_Y}">
        <div class="button" style="width: 100%;">
            <input id="prev" type="button" onclick="nextOpt('step7')" class="search" value="上一步"/>
            <input id="nxt" type="button" onclick="nextOpt('step9')" class="search" value="下一步"/>
        </div>
    </c:if>
</form>

		