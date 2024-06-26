<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_ui_tooltip" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
    <jsp:param name="ueditor" value="true"/>
</jsp:include>
<script type="text/javascript">
    $(document).ready(function(){
        if ("${param.tagId}" != "") {
            $("#${param.tagId}").click();
        } else {
            $("li a:first").click();
        }
    });

    function selectTag(selfObj,url) {
        var selLi = $(selfObj);
        $(".tab_select").removeClass("tab_select");
        selLi.removeClass("tab");
        selLi.addClass("tab_select");
        jboxLoad("div_table",url,true);
    }
</script>
<div class="main_hd">
    <h2>参数配置</h2>
    <div class="title_tab" style="margin-top: 0;">
        <ul id="tags">
            <li class="tab" onclick="selectTag(this,'<s:url value='/jsres/sysCfg/edit'/>?tagId=recruitCfg')" >
                <a id="recruitCfg">招录配置</a>
            </li>
            <li class="tab" onclick="selectTag(this,'<s:url value='/jsres/sysCfg/edit'/>?tagId=graduationAuditCfg')">
                <a id="graduationAuditCfg">结业考核资格审核配置</a>
            </li>
            <%--<li class="tab" onclick="selectTag(this,'<s:url value='/jsres/sysCfg/edit'/>?tagId=graduationScoreCfg')">--%>
                <%--<a id="graduationScoreCfg">结业理论成绩分数线配置</a>--%>
            <%--</li>--%>
            <li class="tab" onclick="selectTag(this,'<s:url value='/jsres/sysCfg/edit'/>?tagId=graduationDateCfg')">
                <a id="graduationDateCfg">结业证书日期配置</a>
            </li>
            <li class="tab" onclick="selectTag(this,'<s:url value='/jsres/sysCfg/edit'/>?tagId=studentProcessCfg')">
                <a id="studentProcessCfg">学员过程管理配置</a>
            </li>
            <li class="tab" onclick="selectTag(this,'<s:url value='/jsres/sysCfg/edit'/>?tagId=fundingReportAudit')">
                <a id="fundingReportAudit">基地上报经费配置</a>
            </li>

            <li class="tab" onclick="selectTag(this,'<s:url value='/jsres/supervisio/assessmentCfgMain'/>?tagId=assessment')">
                <a id="assessment">基地自评配置</a>
            </li>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table" >
</div>