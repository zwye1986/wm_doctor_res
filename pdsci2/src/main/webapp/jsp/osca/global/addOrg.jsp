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
        function saveOrg() {
            if(false==$("#sysOrgForm").validationEngine("validate")){
                return ;
            }
            $("#orgProvId").attr("disabled",false);
            var orgProvNameText = $("#orgProvId option:selected").text();
            if(orgProvNameText=="选择省"){
                orgProvNameText = "";
            }
            var orgCityNameText = $("#orgCityId option:selected").text();
            if(orgCityNameText=="选择市"){
                orgCityNameText = "";
            }
            var orgAreaNameText = $("#orgAreaId option:selected").text();
            if(orgAreaNameText=="选择地区"){
                orgAreaNameText = "";
            }
            $("#orgProvName").val(orgProvNameText);
            $("#orgCityName").val(orgCityNameText);
            $("#orgAreaName").val(orgAreaNameText);
            var url = "<s:url value='/sys/org/save'/>";
            if(!'${sysOrg.orgFlow}'){
                $("#orgTypeId").val('Hospital');
            }
            var data = $('#sysOrgForm').serialize();
            jboxPost(url, data, function() {
                window.parent.frames['mainIframe'].window.toPage('${page}');
                jboxClose();
            });
        }
    </script>
</head>
<body>
    <form id="sysOrgForm">
        <input type="hidden" name="orgFlow" value="${sysOrg.orgFlow}"/>
        <input type="hidden" name="orgTypeId" id="orgTypeId" value="${sysOrg.orgTypeId}"/>
        <table class="basic" style="width: 100%">

            <tr>
                <td style="width: 16%;text-align: right;">基地名称：
                </td>
                <td style="width: 84%">
                    <input class="validate[required] xltext" name="orgName" type="text" value="${sysOrg.orgName }"/>
                </td>
            </tr>
            <tr>
                <td style="width: 16%;text-align: right;">基地代码：
                </td>
                <td style="width: 84%">
                    <input class="validate[required] xltext" name="orgCode" type="text" value="${sysOrg.orgCode }"/>
                </td>
            </tr>
            <tr>
                <td style="text-align: right;">所属地区：
                </td>
                <td>
                    <c:if test="${empty param.orgFlow}">
                        <div id="provCityAreaId">
                            <select id="orgProvId" name="orgProvId" class="province xlselect" data-value="${sysOrg.orgProvId}" data-first-title="选择省" style="display:none"></select>
                            <select id="orgCityId" name="orgCityId" class="city xlselect" data-value="" data-first-title="选择市"></select>
                            <select id="orgAreaId" name="orgAreaId" class="area xlselect" data-value="" data-first-title="选择地区"></select>
                        </div>
                    </c:if>
                    <c:if test="${not empty param.orgFlow}">
                        <div id="provCityAreaId">
                            <select id="orgProvId" name="orgProvId" class="province xlselect" data-value="${sysOrg.orgProvId}" data-first-title="选择省" style="display:none"></select>
                            <select id="orgCityId" name="orgCityId" class="city xlselect" data-value="${sysOrg.orgCityId}" data-first-title="选择市"></select>
                            <select id="orgAreaId" name="orgAreaId" class="area xlselect" data-value="${sysOrg.orgAreaId}" data-first-title="选择地区"></select>
                        </div>
                    </c:if>
                    <input id="orgProvName" name="orgProvName" type="hidden" value="${sysOrg.orgProvName}">
                    <input id="orgCityName" name="orgCityName" type="hidden" value="${sysOrg.orgCityName}">
                    <input id="orgAreaName" name="orgAreaName" type="hidden" value="${sysOrg.orgAreaName}">
                    <script type="text/javascript">
                        // 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
                        $.cxSelect.defaults.url = "<s:url value='/js/provCityAreaJson.min.json'/>";
                        $.cxSelect.defaults.nodata = "none";

                        $("#provCityAreaId").cxSelect({
                            selects : ["province", "city", "area"],
                            nodata : 'none',
                            firstValue : ""
                        });

                    </script>
                </td>
            </tr>
            <tr>
                <td style="text-align: right;">是否为考点：
                </td>
                <td>
                    <label><input type="radio" name="isExamOrg" value="Y" ${sysOrg.isExamOrg eq 'Y'?'checked':''} class="validate[required]">是</label>
                    <label><input type="radio" name="isExamOrg" value="N" ${sysOrg.isExamOrg ne 'Y'?'checked':''} class="validate[required]">否</label>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center">
                    <input type="button" class="search" value="保&#12288;存" onclick="saveOrg();">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
