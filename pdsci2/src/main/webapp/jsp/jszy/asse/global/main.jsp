<%@include file="/jsp/common/doctype.jsp" %>
<html>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
	<jsp:param name="basic" value="true"/>
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
</jsp:include>
<script type="text/javascript">

	$(document).ready(function(){
		selTag("WaitAudit");
	});
	function selTag(tabTag){
		if("WaitAudit"==tabTag)
		{
			$("#WaitAudit").removeClass("tab");
			$("#WaitAudit").addClass("tab_select");
			$("#AuditList").addClass("tab");
			$("#AuditList").removeClass("tab_select");
			//待审核
			jboxLoad("mainDiv","<s:url value='/jszy/asseGlobal/waitAuditMain'/>?roleFlag=${param.roleFlag}",true);
		}else{
			$("#AuditList").removeClass("tab");
			$("#AuditList").addClass("tab_select");
			$("#WaitAudit").addClass("tab");
			$("#WaitAudit").removeClass("tab_select");
			//审核列表
			jboxLoad("mainDiv","<s:url value='/jszy/asseGlobal/AuditListMain'/>?roleFlag=${param.roleFlag}",true);
		}
	}
</script>

<div class="main_hd">
	<h2>结业考核管理</h2>
    <div class="title_tab">
        <ul id="reducationTab">
            <li id="WaitAudit" class="tab" onclick="selTag('WaitAudit');"><a>待审核</a></li>
            <li id="AuditList" class="tab" onclick="selTag('AuditList');"><a>情况总览</a></li>
        </ul>
    </div>
</div>
<div id="mainDiv">
</div>
</html>