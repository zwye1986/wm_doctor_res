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
            <c:set value="jsres_${sessionScope.currUser.orgFlow }_daoru" var="orgFlow"/>
            <c:if test="${pdfn:jsresPowerCfgMap(orgFlow) eq GlobalConstant.RECORD_STATUS_Y}">
                <li class="tab" onclick="selectTag(this,'<s:url value='/jsres/cfgManager/edit'/>?tagId=dataCfg')" >
                    <a id="dataCfg">培训数据导入开关配置</a>
                </li>
            </c:if>
            <c:set value="jsres_${sessionScope.currUser.orgFlow }_guocheng" var="orgFlow"/>
            <c:if test="${pdfn:jsresPowerCfgMap(orgFlow) eq GlobalConstant.RECORD_STATUS_Y}">
                <li class="tab" onclick="selectTag(this,'<s:url value='/jsres/cfgManager/edit'/>?tagId=ckshCfg')">
                    <a id="ckshCfg">出科成绩审核配置</a>
                </li>
                <li class="tab" onclick="selectTag(this,'<s:url value='/jsres/cfgManager/edit'/>?tagId=jxhdTimeCfg')">
                    <a id="jxhdTimeCfg">教学活动配置</a>
                </li>
                <li class="tab" onclick="selectTag(this,'<s:url value='/jsres/cfgManager/edit'/>?tagId=activityCfg')">
                    <a id="activityCfg">教学活动流程配置</a>
                </li>
                <li class="tab" onclick="selectTag(this,'<s:url value='/jsres/cfgManager/edit'/>?tagId=ckxzCfg')">
                    <a id="ckxzCfg">学员出科限制配置</a>
                </li>
                <li class="tab" onclick="selectTag(this,'<s:url value='/jsres/cfgManager/edit'/>?tagId=szsqshCfg')">
                    <a id="szsqshCfg">师资申请审核配置</a>
                </li>
                <li class="tab" onclick="selectTag(this,'<s:url value='/jsres/cfgManager/edit'/>?tagId=kqcxdcCfg')">
                    <a id="kqcxdcCfg">考勤查询导出配置</a>
                </li>
            </c:if>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table" >
</div>