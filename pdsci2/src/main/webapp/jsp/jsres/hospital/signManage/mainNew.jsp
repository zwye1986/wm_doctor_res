<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="false"/>
    <jsp:param name="queryFont" value="true"/>
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
    <jsp:param name="jquery_ui_tooltip" value="true"/>
</jsp:include>

<script type="text/javascript">

	$(document).ready(function(){
		// selTag("Audit");
        selTag("${param.tabTag}");
	});
	function selTag(tabTag){
		if("SignManage"==tabTag)
		{
			$("#SignManage").removeClass("tab");
			$("#SignManage").addClass("tab_select");
			$("#CertificateSearch").addClass("tab");
			$("#CertificateSearch").removeClass("tab_select");
            jboxLoad("mainDiv","<s:url value='/jsres/hospital/signMain'/>?roleFlag=${param.roleFlag}&tabTag="+tabTag,true);
		}else{
			$("#CertificateSearch").removeClass("tab");
			$("#CertificateSearch").addClass("tab_select");
			$("#SignManage").addClass("tab");
			$("#SignManage").removeClass("tab_select");
            jboxLoad("mainDiv","<s:url value='/jsres/certificateManage/main'/>?roleFlag=${param.roleFlag}&tabTag="+tabTag+"&catSpeId=${param.catSpeId}",true);
		}
	}
</script>

<div class="main_hd">
	<h2 class="underline">结业证书管理</h2>
</div>
<div class="title_tab" style="margin-top: 0px;">
        <ul id="reducationTab">
            <li id="CertificateSearch" class="tab" onclick="selTag('CertificateSearch');"><a>证书查询</a></li>
            <li id="SignManage" class="tab" onclick="selTag('SignManage');"><a>签名管理</a></li>
        </ul>
    </div>
</div>
<div id="mainDiv">
</div>
</html>