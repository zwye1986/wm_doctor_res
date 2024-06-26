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
    <style type="text/css">
    </style>
    <script type="text/javascript">

        function expertOrgSpe(obj,speId,orgFlow,evalYear,recordFlow)
        {
            var checkedVal = $(obj).attr("checked");
            var recordStatus = "${GlobalConstant.RECORD_STATUS_N}";
            if ("checked" == checkedVal) {
                recordStatus = "${GlobalConstant.RECORD_STATUS_Y}";
            }
            var evalOrgSpe={
                orgFlow:orgFlow,
                speId:speId,
                evalYear:evalYear,
                recordFlow:recordFlow,
                recordStatus:recordStatus
            };
            jboxPost("<s:url value='/eval/evalOrg/saveEvalOrgSpe'/>",evalOrgSpe,function(resp){
                if('${GlobalConstant.SAVE_SUCCESSED}'!=resp){
                    $(obj).attr("checked",false);
                }else{
                    var count=$("input[type='checkbox']:checked").length;
                    window.parent.frames['mainIframe'].$('#'+orgFlow).html(count);
                }
            },null,true);
        }
    </script>
</head>
<body>
<div class="content">
    <div class="title1 clearfix">
        <div id="tagContent">
            <div class="tagContent selectTag" id="tagContent0">
            <%--<div>--%>
                <%--【${org.orgName}】${param.evalYear}年专业基地评估配置--%>
            <%--</div>--%>
            <div class="div_search" style="padding: 10px;margin: 10px;float:left;overflow:auto;height: 290px;max-height: 290px;width: 95%;">
                <c:if test="${empty orgSpeList}">
                    基地【${org.orgName}】下无专业基地
                </c:if>
                <c:forEach items="${orgSpeList}" var="spe">
                    <div style="width: 24%;float: left;" deptName="${spe.speName}">
                        <table cellpadding="0" cellspacing="0" class="basic" style="width: 100%;border: 0px;">
                            <tr>
                                <td style="width: 100%;border: 0px;"  cellpadding="0" cellspacing="0">
                                    <label>
                                        <input type="checkbox" name="speId"
                                               onclick="expertOrgSpe(this,'${spe.speId}','${param.orgFlow}','${param.evalYear}','${orgSpeMap[spe.speId].recordFlow}')"
                                               style="vertical-align: middle; cursor: pointer;"
                                               <c:if test="${orgSpeMap[spe.speId].recordStatus eq 'Y'}">checked</c:if>  value="${spe.speId}"/>
                                            ${spe.speName}
                                    </label>
                                </td>
                            </tr>
                        </table>
                    </div>
                </c:forEach>
            </div>
                <div class="button">
                    <input type="button" class="search" onclick="jboxClose();" value="关&#12288;闭"/>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>