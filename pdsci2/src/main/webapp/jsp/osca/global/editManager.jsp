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
    <script type="text/javascript">
        function saveManager(){
            jboxConfirm("确认保存？",function(){
                var orgName = $("[name='orgFlow'] option:checked").text();
                $("[name='orgName']").val(orgName);
                jboxPost("<s:url value='/osca/orgSpeGlobal/saveManager'/>",$("#sysOrgForm").serialize(),function(resp){
                    if(resp!='1'){
                        jboxTip(resp);
                    }else{
                        window.parent.frames['mainIframe'].window.toPage('${param.currentPage}');
                        jboxClose();
                    }
                },null,false);
            })
        }
    </script>
</head>
<body>
    <form id="sysOrgForm">
        <input type="hidden" value="${param.newFlag}" name="newFlag">
        <input type="hidden" value="${user.userFlow}" name="userFlow">
        <table class="basic" style="width: 100%">
            <tr>
                <td style="width: 30%;text-align: right;">基地名称：
                </td>
                <td style="width: 70%">
                    <select class="xlselect validate[required]" name="orgFlow">
                        <option value="">请选择</option>
                        <c:forEach items="${sysOrgList}" var="org">
                            <option value="${org.orgFlow}" ${user.orgFlow eq org.orgFlow?'selected':''}>${org.orgName}</option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="orgName">
                </td>
            </tr>
            <tr>
                <td style="text-align: right;">用户名：
                </td>
                <td>
                    <input class="xltext validate[required]" name="userCode" value="${user.userCode}">
                </td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center">
                    <input type="button" class="search" value="保&#12288;存" onclick="saveManager();">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
