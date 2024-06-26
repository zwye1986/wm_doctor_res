
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
    <html>
    <head>
        <script type="text/javascript">
            function saveAssignsExam(userFlow){
                var orgFlow=$("input[type='radio']:checked").attr("value");
                var url = "<s:url value='/osca/oscaExaminerManage/editExamTestCenter'/>?orgFlow="+orgFlow+"&userFlow="+userFlow;
                jboxPost(url,null, function(resp) {

                    if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
                        jboxTip(resp);
                        window.parent.frames['mainIframe'].location.reload(true);
                        jboxClose();
                    }else{
                        jboxTip(resp);
                    }
                },null,true);
            }
        </script>
    </head>
<body>
<div id="base" style="height: 80% ;overflow: auto;">
    <table  class="xllist" align="center" style="overflow: auto;">
        <colgroup>
            <col width="20%"/>
            <col width="40%"/>
            <col width="20%"/>
            <col width="20%"/>
        </colgroup>
        <tr><th>考点列表</th></tr>
        <c:if test="${not empty orgSpeList}">
            <c:forEach items="${orgSpeList}" var="orgSpe">
                <tr>
                    <td style="text-align: left">
                        &#12288;&#12288;<input id="orgFlow" name="orgFlow" value="${orgSpe.orgFlow}" type="radio" />
                        &#12288;${orgSpe.orgName}&#12288;&#12288;
                    </td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
    <br/>
</div>
<div>
    <p align="center">
        <input type="button" id="saveBtn" value="保&#12288;存" onclick="saveAssignsExam('${userFlow}');" class="search"/>
        <input type="button" value="关&#12288;闭" onclick="jboxClose();" class="search"/>
    </p>
</div>
</body>
</html>
